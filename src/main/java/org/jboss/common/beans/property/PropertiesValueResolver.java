/*
* Copyright [2023] [Red Hat, Inc.]
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.jboss.common.beans.property;

import java.io.File;

/**
 * Parses a string and replaces any references to system properties or environment variables in the string
 *
 * @author Jaikiran Pai (copied from JBoss DMR project)
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public class PropertiesValueResolver {

    private static final int INITIAL = 0;
    private static final int GOT_DOLLAR = 1;
    private static final int GOT_OPEN_BRACE = 2;
    private static final int RESOLVED = 3;
    private static final int DEFAULT = 4;

    /**
     * Replace properties of the form:
     * <code>${<i>&lt;[env.]name&gt;[</i>,<i>&lt;[env.]name2&gt;[</i>,<i>&lt;[env.]name3&gt;...]][</i>:<i>&lt;default&gt;]</i>}</code>
     * Method inspects given string and replaces all encountered properties. If method is able to replace all properties, it
     * returns String with replaced values. If it fails to replace property and ${} does not contain <i>default</i> declaration, method throws {@link IllegalStateException}.
     * <br>
     * <b>Example1:</b><br>
     * <pre>
     * String toReplace = "userLang=<b>${user.language}</b>;user=<i>${env.USER}</i>";
     * String result    = "userLang=<b>en</b>;user=<i>santa</i>";
     * </pre>
     * <br>
     * <b>Example2:</b><br>
     * <pre>
     * String toReplace = "userLang=<b>${IDoneExist,user.language}</b>;user=<i>${ImNotThereEither,env.USER}</i>";
     * String result    = "userLang=<b>en</b>;user=<i>santa</i>";
     * </pre>
     * <br>
     * <b>Example3:</b><br>
     * <pre>
     * String toReplace = "userLang=<b>${IDoneExist:user.language}</b>;user=<i>${ImNotThereEither,env.USER:defaultWontBeUsed}</i>";
     * String result    = "userLang=<b>user.language</b>;user=<i>santa</i>";
     * </pre>
     * <b>NOTE:</b> in <i>Example 3</i> value of <i>userLang</i> in result String. The <b>default</b> value IS NOT RESOLVED. It is used as fallback value.
     * @param value - string containig system or env variable reference(s)
     * @return the value of the system property or environment variable referenced if it exists
     * @throws IllegalStateException - thrown when state of properties does not allow to replace all variable references.
     */
    public static String replaceProperties(final String value) throws IllegalStateException {
        final StringBuilder builder = new StringBuilder();
        final int len = value.length();
        int state = INITIAL;
        int start = -1;
        int nameStart = -1;
        String resolvedValue = null;
        for (int i = 0; i < len; i = value.offsetByCodePoints(i, 1)) {
            final int ch = value.codePointAt(i);
            switch (state) {
                case INITIAL: {
                    switch (ch) {
                        case '$': {
                            state = GOT_DOLLAR;
                            continue;
                        }
                        default: {
                            builder.appendCodePoint(ch);
                            continue;
                        }
                    }
                    // not reachable
                }
                case GOT_DOLLAR: {
                    switch (ch) {
                        case '$': {
                            builder.appendCodePoint(ch);
                            state = INITIAL;
                            continue;
                        }
                        case '{': {
                            start = i + 1;
                            nameStart = start;
                            state = GOT_OPEN_BRACE;
                            continue;
                        }
                        default: {
                            // invalid; emit and resume
                            builder.append('$').appendCodePoint(ch);
                            state = INITIAL;
                            continue;
                        }
                    }
                    // not reachable
                }
                case GOT_OPEN_BRACE: {
                    switch (ch) {
                        case ':':
                        case '}':
                        case ',': {
                            final String name = value.substring(nameStart, i).trim();
                            if ("/".equals(name)) {
                                builder.append(File.separator);
                                state = ch == '}' ? INITIAL : RESOLVED;
                                continue;
                            } else if (":".equals(name)) {
                                builder.append(File.pathSeparator);
                                state = ch == '}' ? INITIAL : RESOLVED;
                                continue;
                            }
                            // First check for system property, then env variable
                            String val = System.getProperty(name);
                            if (val == null && name.startsWith("env."))
                                val = System.getenv(name.substring(4));

                            if (val != null) {
                                builder.append(val);
                                resolvedValue = val;
                                state = ch == '}' ? INITIAL : RESOLVED;
                                continue;
                            } else if (ch == ',') {
                                nameStart = i + 1;
                                continue;
                            } else if (ch == ':') {
                                start = i + 1;
                                state = DEFAULT;
                                continue;
                            } else {
                                throw new IllegalStateException("Failed to resolve expression: "
                                        + value.substring(start - 2, i + 1));
                            }
                        }
                        default: {
                            continue;
                        }
                    }
                    // not reachable
                }
                case RESOLVED: {
                    if (ch == '}') {
                        state = INITIAL;
                    }
                    continue;
                }
                case DEFAULT: {
                    if (ch == '}') {
                        state = INITIAL;
                        builder.append(value.substring(start, i));
                    }
                    continue;
                }
                default:
                    throw new IllegalStateException("Unexpected char seen: " + ch);
            }
        }
        switch (state) {
            case GOT_DOLLAR: {
                builder.append('$');
                break;
            }
            case DEFAULT: {
                builder.append(value.substring(start - 2));
                break;
            }
            case GOT_OPEN_BRACE: {
                // We had a reference that was not resolved, throw ISE
                if (resolvedValue == null)
                    throw new IllegalStateException("Incomplete expression: " + builder.toString());
                break;
            }
        }
        return builder.toString();
    }
}