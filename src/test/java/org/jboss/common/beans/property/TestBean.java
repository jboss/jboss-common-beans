/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

/**
 * @author baranowb
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
