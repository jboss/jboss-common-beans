/*
 * Copyright Red Hat
 * SPDX-License-Identifier: Apache-2.0
 */

package org.jboss.common.beans.property;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Simple class to ensure that proper events are fired.
 *
 * @author baranowb
 */
public class PropertyChangeListenerTestCase {

    private DoubleEditor editor;
    private TestListener testListener;
    private OldTestListener oldTestListener;

    @Before
    public void init() {
        this.editor = new DoubleEditor();
        this.testListener = new TestListener();
        this.oldTestListener = new OldTestListener();
        this.editor.addPropertyChangeListener(this.testListener);
        this.editor.addPropertyChangeListener(this.oldTestListener);
    }

    @Test
    public void testNullToNullTransition() {
        this.editor.setValue(null);
        assertEquals(0, this.testListener.events.size());
        assertEquals(0, this.oldTestListener.events.size());
    }

    @Test
    public void testNullToValueTransition() {
        this.editor.setValue(new Double(1));
        assertEquals(1, this.testListener.events.size());
        assertEquals(1, this.oldTestListener.events.size());
        org.jboss.common.beans.property.PropertyChangeEvent<Double> newEvent = this.testListener.events.get(0);
        assertEquals(editor, newEvent.getSource());
        assertEquals(null, newEvent.getOldValue());
        assertEquals(new Double(1), newEvent.getNewValue());

        PropertyChangeEvent oldEvent = this.oldTestListener.events.get(0);
        assertEquals(editor, newEvent.getSource());
        assertEquals(null, newEvent.getOldValue());
        assertEquals(new Double(1), newEvent.getNewValue());
    }

    @Test
    public void testValueToNullTransition() {
        this.editor.setValue(new Double(2));
        this.editor.setValue(null);
        assertEquals(2, this.testListener.events.size());
        assertEquals(2, this.oldTestListener.events.size());


        org.jboss.common.beans.property.PropertyChangeEvent<Double> newEvent = this.testListener.events.get(0);
        assertEquals(editor, newEvent.getSource());
        assertEquals(null, newEvent.getOldValue());
        assertEquals(new Double(2), newEvent.getNewValue());

        newEvent = this.testListener.events.get(1);
        assertEquals(editor, newEvent.getSource());
        assertEquals(new Double(2), newEvent.getOldValue());
        assertEquals(null, newEvent.getNewValue());

        PropertyChangeEvent oldEvent = this.oldTestListener.events.get(0);
        assertEquals(editor, newEvent.getSource());
        assertEquals(new Double(2), newEvent.getOldValue());
        assertEquals(null, newEvent.getNewValue());

        oldEvent = this.oldTestListener.events.get(1);
        assertEquals(editor, newEvent.getSource());
        assertEquals(new Double(2), newEvent.getOldValue());
        assertEquals(null, newEvent.getNewValue());
    }

    private class OldTestListener implements java.beans.PropertyChangeListener {
        List<PropertyChangeEvent> events = new ArrayList<PropertyChangeEvent>();

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            assertNotNull(evt);
            this.events.add(evt);
        }

    }

    private class TestListener implements PropertyChangeListener<Double> {
        List<org.jboss.common.beans.property.PropertyChangeEvent<Double>> events = new ArrayList<org.jboss.common.beans.property.PropertyChangeEvent<Double>>();

        /*
         * (non-Javadoc)
         *
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            fail();
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * org.jboss.common.beans.property.PropertyChangeListener#propertyChange(org.jboss.common.beans.property.PropertyChangeEvent
         * )
         */
        @Override
        public void propertyChange(org.jboss.common.beans.property.PropertyChangeEvent<Double> evt) {
            assertNotNull(evt);
            this.events.add(evt);
        }

    }
}
