
package com.joymain.jecs.mi.service.impl;

import java.util.Date;
import java.util.List;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.dao.JmiLevelLockDao;
import com.joymain.jecs.mi.model.JmiLevelLock;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLevelLockManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiLevelLockManagerImpl extends BaseManager implements JmiLevelLockManager {
    private JmiLevelLockDao dao;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	private SysRoleManager sysRoleManager;
	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}
	private SysUserRoleManager sysUserRoleManager;
	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiLevelLockDao(JmiLevelLockDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLevelLockManager#getJmiLevelLocks(com.joymain.jecs.mi.model.JmiLevelLock)
     */
    public List getJmiLevelLocks(final JmiLevelLock jmiLevelLock) {
        return dao.getJmiLevelLocks(jmiLevelLock);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLevelLockManager#getJmiLevelLock(String id)
     */
    public JmiLevelLock getJmiLevelLock(final String id) {
        return dao.getJmiLevelLock(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLevelLockManager#saveJmiLevelLock(JmiLevelLock jmiLevelLock)
     */
    public void saveJmiLevelLock(JmiLevelLock jmiLevelLock) {
        dao.saveJmiLevelLock(jmiLevelLock);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLevelLockManager#removeJmiLevelLock(String id)
     */
    public void removeJmiLevelLock(final String id) {
        dao.removeJmiLevelLock(new Long(id));
    }
    //added for getJmiLevelLocksByCrm
    public List getJmiLevelLocksByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiLevelLocksByCrm(crm,pager);
    }

    public void saveJmiLevelLockAndMiMember(JmiLevelLock jmiLevelLock,SysUser defSysUser) {
    	
    	
    	JmiMember jmiMember=jmiMemberManager.getJmiMember(jmiLevelLock.getUserCode());

    	Integer wweek=bdPeriodManager.getBdPeriodByTimeFormated(new Date(), null);
    	
    	jmiMemberManager.changeMemberLevel(jmiMember, wweek, defSysUser, jmiLevelLock.getIsValid(), jmiLevelLock.getMemberLevel(), jmiMember.getMemberLevel());
    	
    	
    	//jmiMemberManager.saveJmiMember(jmiMember);
    	dao.saveJmiLevelLock(jmiLevelLock);
    }

	@Override
	public JmiLevelLock getJmiLevelLockByUserCode(String userCode) {
		return dao.getJmiLevelLockByUserCode(userCode);
	}
    
}
