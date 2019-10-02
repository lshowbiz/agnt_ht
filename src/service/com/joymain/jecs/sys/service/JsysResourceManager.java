
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.sys.model.JsysResource;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysRolePower;
import com.joymain.jecs.sys.dao.JsysResourceDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JsysResourceManager extends Manager {
    /**
     * Retrieves all of the jsysResources
     */
    public List getJsysResources(JsysResource jsysResource);

    /**
     * Gets jsysResource's information based on resId.
     * @param resId the jsysResource's resId
     * @return jsysResource populated jsysResource object
     */
    public JsysResource getJsysResource(final String resId);

    /**
     * Saves a jsysResource's information
     * @param jsysResource the object to be saved
     */
    public void saveJsysResource(JsysResource jsysResource);

    /**
     * Removes a jsysResource from the database by resId
     * @param resId the jsysResource's resId
     */
    public void removeJsysResource(final String resId);
    //added for getJsysResourcesByCrm
    public List getJsysResourcesByCrm(CommonRecord crm, Pager pager);
    
    public void saveJsysResourcesRebuild();
    
    public List getDirectChildJsysResources(final String resId, final String orderField);
    
    public List getJsysResourcesJoinRole(SysRole sysRole);
    
    //
    public void saveSysRoleRes(List<JsysResRole> jSysRoleRes);
    
    
    public void removeSysRoleResByRoleId(Long roleId);
}

