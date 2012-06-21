/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.common.beans.property.token;

import java.util.StringTokenizer;

/**
 * Simple abstract class to abstract tokenization process. Implementation provides rules of tokenization, ie. chars(delimiters) based on which
 * String is broken down into tokens.
 *
 * @author baranowb
 *
 */
public abstract class ArrayTokenizer {

    /**
     * Implementation of this method breaks down passed string into tokens.
     *
     * @param value
     * @return
     */
    public final String[] tokenize(String value) {
        StringTokenizer stringTokenizer = new StringTokenizer(value, getDelimiters());
        String[] broken = new String[stringTokenizer.countTokens()];
        for(int index = 0; index< broken.length;index++){
            broken[index] = stringTokenizer.nextToken();
        }
        return broken;
    }
    /**
     * Returns delimiters which are used in tokenization.
     * @return
     */
    protected abstract String getDelimiters();
}
