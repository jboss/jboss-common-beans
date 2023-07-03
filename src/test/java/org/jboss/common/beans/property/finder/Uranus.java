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

package org.jboss.common.beans.property.finder;

/**
 * @author baranowb
 */
public class Uranus {

    private String nexusJupiterIncident;

    /**
     * @param nexusJupiterIncident
     */
    public Uranus(String nexusJupiterIncident) {
        super();
        this.nexusJupiterIncident = nexusJupiterIncident;
    }

    /**
     * @return the nexusJupiterIncident
     */
    public String getNexusJupiterIncident() {
        return nexusJupiterIncident;
    }

    /**
     * @param nexusJupiterIncident the nexusJupiterIncident to set
     */
    public void setNexusJupiterIncident(String nexusJupiterIncident) {
        this.nexusJupiterIncident = nexusJupiterIncident;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nexusJupiterIncident == null) ? 0 : nexusJupiterIncident.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        Uranus other = (Uranus) obj;
        if (nexusJupiterIncident == null) {
            if (other.nexusJupiterIncident != null) { return false; }
        } else if (!nexusJupiterIncident.equals(other.nexusJupiterIncident)) { return false; }
        return true;
    }

}
