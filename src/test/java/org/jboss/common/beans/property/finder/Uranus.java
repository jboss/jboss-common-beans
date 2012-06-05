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

package org.jboss.common.beans.property.finder;

/**
 * @author baranowb
 * 
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Uranus other = (Uranus) obj;
        if (nexusJupiterIncident == null) {
            if (other.nexusJupiterIncident != null)
                return false;
        } else if (!nexusJupiterIncident.equals(other.nexusJupiterIncident))
            return false;
        return true;
    }

}
