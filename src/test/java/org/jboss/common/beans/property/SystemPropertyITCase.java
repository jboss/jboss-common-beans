/*
 * JBoss, Home of Professional Open Source.
 * Copyright (c) 2012, Red Hat, Inc., and individual contributors
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.util.Enumeration;

import org.junit.Test;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class SystemPropertyITCase {
    
//    public static Policy ALLOW;
//    static
//    {
//        class Allow extends Policy
//        {
//            public Allow()
//            {
//                
//                
//            }
//
//            /* (non-Javadoc)
//             * @see java.security.Policy#getPermissions(java.security.CodeSource)
//             */
//            @Override
//            public PermissionCollection getPermissions(final CodeSource codesource) {
//                Permission allPerm = new AllPermission();
//                PermissionCollection pc = new PermissionCollection() {
//                    
//                    @Override
//                    public boolean implies(Permission permission) {
//                        System.err.println(">"+permission+" --> "+codesource);
//                        if(permission.toString().contains("user.dir"))
//                            return false;
//                        return true;
//                    }
//                    
//                    @Override
//                    public Enumeration<Permission> elements() {
//                        // TODO Auto-generated method stub
//                        return null;
//                    }
//                    
//                    @Override
//                    public void add(Permission permission) {
//                        // TODO Auto-generated method stub
//                        
//                    }
//                };
//                pc.add(allPerm);
//                return pc;
//            }
//
//            /* (non-Javadoc)
//             * @see java.security.Policy#getPermissions(java.security.ProtectionDomain)
//             */
//            @Override
//            public PermissionCollection getPermissions(ProtectionDomain domain) {
//                return this.getPermissions(domain.getCodeSource());
//            }
//
//            /* (non-Javadoc)
//             * @see java.security.Policy#implies(java.security.ProtectionDomain, java.security.Permission)
//             */
//            @Override
//            public boolean implies(ProtectionDomain domain, Permission permission) {
//                System.err.println("->"+permission+" --> "+domain.getCodeSource());
//                if(permission.toString().contains("user.dir"))
//                    return false;
//                return true;
//            }
//            
//            
//        }
//        ALLOW = new Allow();
//        //Policy.setPolicy(ALLOW);
//        System.setSecurityManager(new SecurityManager());
//    }
//    
//    
    @Test
    public void testUserDir() {
        try {
            final String userDir = PropertiesValueResolver.replaceProperties("${user.dir}");
            System.out.println(userDir);
            fail("Expected a SecurityException, because the test-classes don't have the proper permission");
        } catch (SecurityException e) {
            // good
            e.printStackTrace();
        }
    }

    @Test
    public void testUserName() {
        final String userName = PropertiesValueResolver.replaceProperties("${user.name}");
        assertNotNull(userName);
    }
}
