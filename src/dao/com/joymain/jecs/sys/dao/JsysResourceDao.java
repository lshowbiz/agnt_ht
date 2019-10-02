
package com.joymain.jecs.sys.dao;

import java.util.List;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.sys.model.JsysResource;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JsysResourceDao extends Dao {

    /**
     * Retrieves all of the jsysResources
     */
    public List getJsysResources(JsysResource jsysResource);

    /**
     * Gets jsysResource's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param resId the jsysResource's resId
     * @return jsysResource populated jsysResource object
     */
    public JsysResource getJsysResource(final Long resId);

    /**
     * Saves a jsysResource's information
     * @param jsysResource the object to be saved
     */    
    public void saveJsysResource(JsysResource jsysResource);

    /**
     * Removes a jsysResource from the database by resId
     * @param resId the jsysResource's resId
     */
    public void removeJsysResource(final Long resId);
    //added for getJsysResourcesByCrm
    public List getJsysResourcesByCrm(CommonRecord crm, Pager pager);
    
    public void saveJsysResourcesRebuild();
    
    public List getDirectChildJsysResources(final Long resId, final String orderField);
    
    public List getJsysResourcesJoinRole(SysRole sysRole);
    
    public void saveSysRoleRes(List<JsysResRole> jSysRoleRes);
    
    public void removeSysRoleResByRoleId(final Long roleId);
}

