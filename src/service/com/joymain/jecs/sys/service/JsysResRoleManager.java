
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JsysResRoleManager extends Manager {
    /**
     * Retrieves all of the jsysResRoles
     */
    public List getJsysResRoles(JsysResRole jsysResRole);

    /**
     * Gets jsysResRole's information based on pid.
     * @param pid the jsysResRole's pid
     * @return jsysResRole populated jsysResRole object
     */
    public JsysResRole getJsysResRole(final String pid);

    /**
     * Saves a jsysResRole's information
     * @param jsysResRole the object to be saved
     */
    public void saveJsysResRole(JsysResRole jsysResRole);

    /**
     * Removes a jsysResRole from the database by pid
     * @param pid the jsysResRole's pid
     */
    public void removeJsysResRole(final String pid);
    //added for getJsysResRolesByCrm
    public List getJsysResRolesByCrm(CommonRecord crm, Pager pager);
    
    public void saveSysRoleResList(List<JsysResRole> jSysRoleRes);
}

