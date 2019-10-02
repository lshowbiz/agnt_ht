package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysManagerUserDao;
import com.joymain.jecs.sys.model.SysManagerUser;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysManagerUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysManagerUserManagerImpl extends BaseManager implements SysManagerUserManager {
	private SysManagerUserDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysManagerUserDao(SysManagerUserDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerUserManager#getSysManagerUsers(com.joymain.jecs.sys.model.SysManagerUser)
	 */
	public List getSysManagerUsers(final SysManagerUser sysManagerUser) {
		return dao.getSysManagerUsers(sysManagerUser);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerUserManager#getSysManagerUser(String rollId)
	 */
	public SysManagerUser getSysManagerUser(final String rollId) {
		return dao.getSysManagerUser(new Long(rollId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerUserManager#saveSysManagerUser(SysManagerUser sysManagerUser)
	 */
	public void saveSysManagerUser(SysManagerUser sysManagerUser) {
		dao.saveSysManagerUser(sysManagerUser);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysManagerUserManager#removeSysManagerUser(String rollId)
	 */
	public void removeSysManagerUser(final String rollId) {
		dao.removeSysManagerUser(new Long(rollId));
	}

	//added for getSysManagerUsersByCrm
	public List getSysManagerUsersByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysManagerUsersByCrm(crm, pager);
	}

	/**
	 * 获取masterUserCode所管理的用户清单,包含slaveUserCode所管理的用户清单,其所返回的slave_roll_id为两者的交集记录ID
	 * @param masterSysUser
	 * @param slaveUserCode
	 * @param companyCode
	 * @param limitCompany 是否限定于指定的公司
	 * @return
	 */
	public List getSysManagersWithJoin(final SysUser masterSysUser, final String slaveUserCode, final String companyCode, boolean limitCompany) {
		return dao.getSysManagersWithJoin(masterSysUser, slaveUserCode, companyCode, limitCompany);
	}
	
	/**
     * 删除用户指定的被管理用户以外的所有记录
     * @param masterUserCode
     * @param slaveUserCodes
     */
    public void removeSysManagerUsersBySlaveCodes(final String masterUserCode, final String companyCode, final String[] slaveUserCodes){
    	dao.removeSysManagerUsersBySlaveCodes(masterUserCode, companyCode, slaveUserCodes);
    }
    
    /**
     * 获取用户与被管理用户所对应的记录
     * @param masterUserCode
     * @param slaveUserCode
     * @return
     */
    public SysManagerUser getSysManagerUser(final String masterUserCode, final String slaveUserCode) {
    	return  dao.getSysManagerUser(masterUserCode, slaveUserCode);
    }
    
    /**
     * 批量保存多条记录
     * @param sysManagerModlPows
     */
    public void saveSysManagerUsers(List sysManagerUsers){
    	dao.saveSysManagerUsers(sysManagerUsers);
    }
    
    /**
	 * 获取masterUserCode所管理的用户清单,包含被管理用户对指定模块是否拥有权限的信息,其所返回的power_id不为空则代表用户有权限
	 * @param masterSysUser
	 * @param moduleId
	 * @param companyCode
	 * @param limitCompany 是否限定于指定的公司
	 * @return
	 */
	public List getSysManagersWithPowerJoin(final SysUser masterSysUser, final String moduleId, final String companyCode, boolean limitCompany) {
		return dao.getSysManagersWithPowerJoin(masterSysUser, moduleId, companyCode, limitCompany);
	}
	
	/**
	 * Add By WuCF 20140506
	 * 获取指定仓库包含被管理用户对指定模块是否拥有权限的信息,其所返回的power_id不为空则代表用户有权限
	 * @param masterSysUser
	 * @param moduleId
	 * @param companyCode
	 * @param limitCompany 是否限定于指定的公司
	 * @return
	 */
	public List getPdWarehouseWithPowerJoin(final SysUser masterSysUser, final String warehouseNo, final String companyCode, boolean limitCompany) {
		return dao.getPdWarehouseWithPowerJoin(masterSysUser, warehouseNo, companyCode, limitCompany);
	}
	
	/**
	 * 获取用户所管理的用户列表
	 * @param masterUserCode
	 * @return
	 */
	public List getSysManagerUsersByMaster(final String masterUserCode){
		return dao.getSysManagerUsersByMaster(masterUserCode);
	}
	
	/**
	 * 获取可管理某用户的用户列表
	 * @param slaveUserCode
	 * @return
	 */
	public List getSysManagerUsersBySlave(final String slaveUserCode){
		return dao.getSysManagerUsersBySlave(slaveUserCode);
	}
}