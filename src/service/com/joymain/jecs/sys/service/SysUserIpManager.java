package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUserIp;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysUserIpManager extends Manager {
	/**
	 * Retrieves all of the sysUserIps
	 */
	public List getSysUserIps(SysUserIp sysUserIp);

	/**
	 * Gets sysUserIp's information based on ipId.
	 * @param ipId the sysUserIp's ipId
	 * @return sysUserIp populated sysUserIp object
	 */
	public SysUserIp getSysUserIp(final String ipId);

	/**
	 * Saves a sysUserIp's information
	 * @param sysUserIp the object to be saved
	 */
	public void saveSysUserIp(SysUserIp sysUserIp);

	/**
	 * Removes a sysUserIp from the database by ipId
	 * @param ipId the sysUserIp's ipId
	 */
	public void removeSysUserIp(final String ipId);

	//added for getSysUserIpsByCrm
	public List getSysUserIpsByCrm(CommonRecord crm, Pager pager);

	/**
	 * 获取某用户是否有某IP的记录
	 * @param userCode
	 * @param ipAddress
	 * @return
	 */
	public SysUserIp getSysUserIp(final String userCode, final String ipAddress);
	
	/**
     * 删除某用户不在IP范围内的IP记录
     * @param userCode
     * @param ipAddress
     */
    public void removeSysUserIpsNotIn(final String userCode, final String[] ipAddress);
    
    /**
     * 批量保存多条记录
     * @param sysUserIps
     */
    public void saveSysUserIps(List sysUserIps);
    
    /**
     * 获取某用户对应的IP列表
     * @param userCode
     * @return
     */
    public List getSysUserIpsByUser(final String userCode);
    
   
}
