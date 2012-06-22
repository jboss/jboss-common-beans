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

package org.jboss.common.beans.property;

/**
 * @author baranowb
 *
 */
public class TestBean {
    private String info;
    private int x;
    private Double dummy;
    private TestBean iWillFail;
    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }
    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }
    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * @return the dummy
     */
    public Double getDummy() {
        return dummy;
    }
    /**
     * @param dummy the dummy to set
     */
    public void setDummy(Double dummy) {
        this.dummy = dummy;
    }
    /**
     * @return the iWillFail
     */
    public TestBean getiWillFail() {
        return iWillFail;
    }
    /**
     * @param iWillFail the iWillFail to set
     */
    public void setiWillFail(TestBean iWillFail) {
        this.iWillFail = iWillFail;
    }

}
