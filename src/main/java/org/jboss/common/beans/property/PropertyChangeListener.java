/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */
package org.jboss.common.beans.property;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public interface PropertyChangeListener<T> extends java.beans.PropertyChangeListener {
    void propertyChange(PropertyChangeEvent<T> evt);
}
