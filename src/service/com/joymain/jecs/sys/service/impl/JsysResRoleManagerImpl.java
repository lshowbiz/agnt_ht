
package com.joymain.jecs.sys.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.JsysResRole;
import com.joymain.jecs.sys.dao.JsysResRoleDao;
import com.joymain.jecs.sys.service.JsysResRoleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JsysResRoleManagerImpl extends BaseManager implements JsysResRoleManager {
    private JsysResRoleDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJsysResRoleDao(JsysResRoleDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.JsysResRoleManager#getJsysResRoles(com.joymain.jecs.sys.model.JsysResRole)
     */
    public List getJsysResRoles(final JsysResRole jsysResRole) {
        return dao.getJsysResRoles(jsysResRole);
    }

    /**
     * @see com.joymain.jecs.sys.service.JsysResRoleManager#getJsysResRole(String pid)
     */
    public JsysResRole getJsysResRole(final String pid) {
        return dao.getJsysResRole(new Long(pid));
    }

    /**
     * @see com.joymain.jecs.sys.service.JsysResRoleManager#saveJsysResRole(JsysResRole jsysResRole)
     */
    public void saveJsysResRole(JsysResRole jsysResRole) {
        dao.saveJsysResRole(jsysResRole);
    }

    /**
     * @see com.joymain.jecs.sys.service.JsysResRoleManager#removeJsysResRole(String pid)
     */
    public void removeJsysResRole(final String pid) {
        dao.removeJsysResRole(new Long(pid));
    }
    //added for getJsysResRolesByCrm
    public List getJsysResRolesByCrm(CommonRecord crm, Pager pager){
	return dao.getJsysResRolesByCrm(crm,pager);
    }
    
    public void saveSysRoleResList(List<JsysResRole> jSysRoleRes){
    	
    	this.dao.saveSysRoleResList(jSysRoleRes);
    }
}
