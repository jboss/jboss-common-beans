/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
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

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Simple class to ensure that proper events are fired.
 * 
 * @author baranowb
 * 
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
