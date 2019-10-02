
package com.joymain.jecs.po.service.impl;

import com.joymain.jecs.po.dao.JpoCheckedFailedDao;
import com.joymain.jecs.po.model.JpoCheckedFailed;
import com.joymain.jecs.po.service.JpoCheckedFailedManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysUserDao;


public class JpoCheckedFailedManagerImpl extends BaseManager implements JpoCheckedFailedManager {
    
    private SysUserDao sysUserDao;
    private JpoMemberOrderManager jpoMemberOrderManager;
	private JpoCheckedFailedDao checkedFailedDao;
    
    public SysUserDao getSysUserDao() {
		return sysUserDao;
	}
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	public JpoMemberOrderManager getJpoMemberOrderManager() {
		return jpoMemberOrderManager;
	}
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	public JpoCheckedFailedDao getCheckedFailedDao() {
		return checkedFailedDao;
	}
	public void setCheckedFailedDao(JpoCheckedFailedDao checkedFailedDao) {
		this.checkedFailedDao = checkedFailedDao;
	}
	
	@Override
	public void saveCheckedFailed(JpoCheckedFailed failedInfo) {
		checkedFailedDao.saveObject(failedInfo);
	}

}
