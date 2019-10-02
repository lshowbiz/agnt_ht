package com.joymain.jecs.sys.service.impl;

import java.util.List;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.dao.SysUserIpDao;
import com.joymain.jecs.sys.model.SysUserIp;
import com.joymain.jecs.sys.service.SysUserIpManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class SysUserIpManagerImpl extends BaseManager implements SysUserIpManager {
	private SysUserIpDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setSysUserIpDao(SysUserIpDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUserIpManager#getSysUserIps(com.joymain.jecs.sys.model.SysUserIp)
	 */
	public List getSysUserIps(final SysUserIp sysUserIp) {
		return dao.getSysUserIps(sysUserIp);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUserIpManager#getSysUserIp(String ipId)
	 */
	public SysUserIp getSysUserIp(final String ipId) {
		return dao.getSysUserIp(new Long(ipId));
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUserIpManager#saveSysUserIp(SysUserIp sysUserIp)
	 */
	public void saveSysUserIp(SysUserIp sysUserIp) {
		dao.saveSysUserIp(sysUserIp);
	}

	/**
	 * @see com.joymain.jecs.sys.service.SysUserIpManager#removeSysUserIp(String ipId)
	 */
	public void removeSysUserIp(final String ipId) {
		dao.removeSysUserIp(new Long(ipId));
	}

	//added for getSysUserIpsByCrm
	public List getSysUserIpsByCrm(CommonRecord crm, Pager pager) {
		return dao.getSysUserIpsByCrm(crm, pager);
	}

	/**
	 * 获取某用户是否有某IP的记录
	 * @param userCode
	 * @param ipAddress
	 * @return
	 */
	public SysUserIp getSysUserIp(final String userCode, final String ipAddress) {
		return dao.getSysUserIp(userCode, ipAddress);
	}
	
	/**
     * 删除某用户不在IP范围内的IP记录
     * @param userCode
     * @param ipAddress
     */
    public void removeSysUserIpsNotIn(final String userCode, final String[] ipAddress){
    	dao.removeSysUserIpsNotIn(userCode, ipAddress);
    }
    
    /**
     * 批量保存多条记录
     * @param sysUserIps
     */
    public void saveSysUserIps(List sysUserIps){
    	dao.saveSysUserIps(sysUserIps);
    }
    
    /**
     * 获取某用户对应的IP列表
     * @param userCode
     * @return
     */
    public List getSysUserIpsByUser(final String userCode){
    	return dao.getSysUserIpsByUser(userCode);
    }

	
    
    
}
