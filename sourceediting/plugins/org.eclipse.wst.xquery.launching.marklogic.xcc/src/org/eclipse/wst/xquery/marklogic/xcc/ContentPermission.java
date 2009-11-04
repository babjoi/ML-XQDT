/*
 * Copyright (c) 2003-2009 Mark Logic Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 */
package org.eclipse.wst.xquery.marklogic.xcc;

/**
 * An individual {@link Content} (document) permission, a capability/role pair. Instances of
 * ContentPermission are immutable and may be shared.
 * 
 * @see ContentCapability
 */
public class ContentPermission {
    /** Convenience reference to {@link ContentCapability#READ} */
    public static final ContentCapability READ = ContentCapability.READ;

    /** Convenience reference to {@link ContentCapability#INSERT} */
    public static final ContentCapability INSERT = ContentCapability.INSERT;

    /** Convenience reference to {@link ContentCapability#UPDATE} */
    public static final ContentCapability UPDATE = ContentCapability.UPDATE;

    /** Convenience reference to {@link ContentCapability#EXECUTE} */
    public static final ContentCapability EXECUTE = ContentCapability.EXECUTE;

    private ContentCapability capability;
    private String role;

    /**
     * Instantiate a new permission object with the given capability and role.
     * 
     * @param capability
     *            One of the capabilities defined in the typesafe enumeration class
     *            {@link ContentCapability}
     * @param role
     *            A Role name.
     */
    public ContentPermission(ContentCapability capability, String role) {
//		if (capability == null) {
//			throw new IllegalArgumentException ("Capability may not be null");
//		}

        if (role == null) {
            throw new IllegalArgumentException("Role may not be null");
        }

        String trimmedRole = role.trim();

        if (trimmedRole.length() == 0) {
            throw new IllegalArgumentException("Role may not be empty");
        }

        this.capability = capability;
        this.role = trimmedRole;
    }

    // -----------------------------------------------------------

    /**
     * Convenience factory method to create a permission object with read capability and the given
     * role.
     * 
     * @param role
     *            A Role name
     * @return A new, immutable ContentPermission instance.
     */
    public static ContentPermission newReadPermission(String role) {
        return (new ContentPermission(READ, role));
    }

    /**
     * Convenience factory method to create a permission object with insert capability and the given
     * role.
     * 
     * @param role
     *            A Role name
     * @return A new, immutable ContentPermission instance.
     */
    public static ContentPermission newInsertPermission(String role) {
        return (new ContentPermission(INSERT, role));
    }

    /**
     * Convenience factory method to create a permission object with update capability and the given
     * role.
     * 
     * @param role
     *            A Role name
     * @return A new, immutable ContentPermission instance.
     */
    public static ContentPermission newUpdatePermission(String role) {
        return (new ContentPermission(UPDATE, role));
    }

    /**
     * Convenience factory method to create a permission object with execute capability and the
     * given role.
     * 
     * @param role
     *            A Role name
     * @return A new, immutable ContentPermission instance.
     */
    public static ContentPermission newExecutePermission(String role) {
        return (new ContentPermission(EXECUTE, role));
    }

    // -----------------------------------------------------------

    /**
     * Get this permission's capability.
     * 
     * @return A member of the {@link ContentCapability} enumeration.
     */
    public ContentCapability getCapability() {
        return capability;
    }

    /**
     * Get the name of the role associated with this permission.
     * 
     * @return The role name as a String.
     */
    public String getRole() {
        return role;
    }

    // -----------------------------------------------------------

    @Override
    public String toString() {
        return ("[DocPermission: role=" + role + ", capability=" + capability + "]");
    }

    // -----------------------------------------------------------
}
