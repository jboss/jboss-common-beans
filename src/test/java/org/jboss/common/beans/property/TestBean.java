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
