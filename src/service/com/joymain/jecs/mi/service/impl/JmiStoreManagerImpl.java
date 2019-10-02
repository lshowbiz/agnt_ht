
package com.joymain.jecs.mi.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.mi.model.JmiStore;
import com.joymain.jecs.mi.model.JmiSubStore;
import com.joymain.jecs.mi.dao.JmiStoreDao;
import com.joymain.jecs.mi.service.JmiStoreManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public class JmiStoreManagerImpl extends BaseManager implements JmiStoreManager {
    private JmiStoreDao dao;
    private SysUserRoleManager sysUserRoleManager;
    private SysRoleManager sysRoleManager;
    public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}

	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiStoreDao(JmiStoreDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiStoreManager#getJmiStores(com.joymain.jecs.mi.model.JmiStore)
     */
    public List getJmiStores(final JmiStore jmiStore) {
        return dao.getJmiStores(jmiStore);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiStoreManager#getJmiStore(String id)
     */
    public JmiStore getJmiStore(final String id) {
        return dao.getJmiStore(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiStoreManager#saveJmiStore(JmiStore jmiStore)
     */
    public void saveJmiStore(JmiStore jmiStore) {
        dao.saveJmiStore(jmiStore);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiStoreManager#removeJmiStore(String id)
     */
    public void removeJmiStore(final String id) {
        dao.removeJmiStore(new Long(id));
    }
    //added for getJmiStoresByCrm
    public List getJmiStoresByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiStoresByCrm(crm,pager);
    }

	public JmiStore getJmiStoreByUserCode(String userCode) {
		return dao.getJmiStoreByUserCode(userCode);
	}
	
	 /**
     * 
     * @param strCodes
     * @param sysUser
     * @param checkStatus 1审核成功，3不成功
     */
	public void checkJmiStore(JmiStore jmiStore,SysUser sysUser,String checkStatus) {
				
				if("1".equals(jmiStore.getCheckStatus())){
					throw new AppException("bdSendRegister.operaterFail");
				}
				if("1".equals(checkStatus)){
					jmiStore.setCheckTime(new Date());
					jmiStore.setCheckUser(sysUser.getUserCode());
				}
				jmiStore.setCheckStatus(checkStatus);
				dao.saveJmiStore(jmiStore);
	}
    /**
     * 
     * @param strCodes
     * @param sysUser
     * @param confirmStatus 1 未确认转确认 2 未确认转不确认
     */
	public void confirmJmiStore(JmiStore jmiStore, SysUser sysUser,String confirmStatus) {
				if(!"1".equals(jmiStore.getCheckStatus())||"1".equals(jmiStore.getConfirmStatus())){
					throw new AppException("bdSendRegister.operaterFail");
				}
				if("1".equals(confirmStatus)){
					jmiStore.setConfirmUser(sysUser.getUserCode());
					jmiStore.setConfirmTime(new Date());
					
					
				}else if("2".equals(confirmStatus)){
					jmiStore.setCheckStatus("0");
					jmiStore.setCheckUser("");
					jmiStore.setCheckTime(null);
				}
					
				jmiStore.setConfirmStatus(confirmStatus);
				dao.saveJmiStore(jmiStore);
	}

	
	
}
