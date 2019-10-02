
package com.joymain.jecs.sys.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.sys.model.JsysResource;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.dao.JsysResourceDao;
import com.joymain.jecs.sys.service.JsysResourceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JsysResourceManagerImpl extends BaseManager implements JsysResourceManager {
    private JsysResourceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJsysResourceDao(JsysResourceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.JsysResourceManager#getJsysResources(com.joymain.jecs.sys.model.JsysResource)
     */
    public List getJsysResources(final JsysResource jsysResource) {
        return dao.getJsysResources(jsysResource);
    }

    /**
     * @see com.joymain.jecs.sys.service.JsysResourceManager#getJsysResource(String resId)
     */
    public JsysResource getJsysResource(final String resId) {
        return dao.getJsysResource(new Long(resId));
    }

    /**
     * @see com.joymain.jecs.sys.service.JsysResourceManager#saveJsysResource(JsysResource jsysResource)
     */
    public void saveJsysResource(JsysResource jsysResource) {
        dao.saveJsysResource(jsysResource);
    }

    /**
     * @see com.joymain.jecs.sys.service.JsysResourceManager#removeJsysResource(String resId)
     */
    public void removeJsysResource(final String resId) {
        dao.removeJsysResource(new Long(resId));
    }
    //added for getJsysResourcesByCrm
    public List getJsysResourcesByCrm(CommonRecord crm, Pager pager){
	
    	return dao.getJsysResourcesByCrm(crm,pager);
    }

	public void saveJsysResourcesRebuild() {
		// TODO Auto-generated method stub
		dao.saveJsysResourcesRebuild();
	}
	
	 public List getDirectChildJsysResources(final String resId, final String orderField){
		 
		if (StringUtils.isEmpty(resId)) {
			return dao.getDirectChildJsysResources(new Long(0),orderField);
		} else {
			return dao.getDirectChildJsysResources(new Long(resId),orderField);
		}
	 }

	 /**
	  *  通过角色查询资源
	  */
	public List getJsysResourcesJoinRole(SysRole sysRole) {
		// TODO Auto-generated method stub
		return dao.getJsysResourcesJoinRole(sysRole);
	}

	public void saveSysRoleRes(List<JsysResRole> jSysRoleRes) {
		// TODO Auto-generated method stub
		dao.saveSysRoleRes(jSysRoleRes);
	}

	public void removeSysRoleResByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		dao.removeSysRoleResByRoleId(roleId);
	}
}
