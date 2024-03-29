package com.joymain.jecs.service;

import com.joymain.jecs.model.Role;

import java.util.List;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="RoleManager.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface RoleManager extends Manager {
    public List getRoles(Role role);
    public Role getRole(String rolename);
    public void saveRole(Role role);
    public void removeRole(String rolename);
}
