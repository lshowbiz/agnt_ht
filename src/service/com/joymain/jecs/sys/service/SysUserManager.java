package com.joymain.jecs.sys.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;

public interface SysUserManager extends Manager {
	public List getObjsBySQL(final String sqlQuery);
    
    public void saveObjecsBySQL(final String sqlQuery);
	
	public List getSysUserNotInRole(CommonRecord crm, Pager pager);
	public SysUser login(String userCode, String password) throws AppException;
	public Map isLogin(String userCode, String password);
	/**
	 * 根据用户编号获取用户信息
	 * @param userCode
	 * @return
	 */
	public SysUser getSysUser(final String userCode);
	
	public SysUser getUserByToken(String userCode,String token);
	
	public List getSysUsers(CommonRecord crm, Pager pager);
	
	/**
	 * @see com.joymain.jecs.sys.dao.SysUserDao#saveSysUser(SysUser sysUser)
	 */
	public void saveSysUser(final SysUser sysUser);
	
	public List getSysUserRoleList(CommonRecord crm, Pager pager);
	
	public String getLoginKey(String userCode,String operationCode);
	
	/**
	 * 返回登录用户/密码匹配
	 * @userCodeAndPsswordJson：用户名和密码的json字符串
	 * @return：-2：用户名密码解析异常   -1：json解析异常  0：用户名为空   1：用户名不存在   2：用户名密码不匹配      3：匹配成功
	 */
	public RspEntity getLoginStatus(String userCodeAndPsswordJson);
}
