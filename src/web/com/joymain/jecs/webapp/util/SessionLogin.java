package com.joymain.jecs.webapp.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysUser;


/**
 * 当前用户Session类
 * 
 * @author Aidy.Q
 * 
 */
public class SessionLogin implements Serializable{
	public SessionLogin() {
	}

	/**
	 * 获取当前登录人信息
	 * 
	 * @param request
	 * @return
	 */
	public static SysUser getLoginUser(HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constants.SESSION_CURRENT_USER);
		if (sysUser == null) {
			String defLang=request.getLocale().toString();
			if(Constants.localLanguageMap.get(request.getLocale().toString())==null){
				defLang="en_US";
			}
			//instance = new SessionLogin();
			sysUser = new SysUser();
			sysUser.setDefCharacterCoding(defLang);
			//instance.setSysUser(sysUser);
			sysUser.setIsAdmin(false);
			sysUser.setAuthorized(false);
			
			//SysUser operatorSysUser=new SysUser();
			//operatorSysUser.setDefCharacterCoding(defLang);
			//sysUser.setOperatorSysUser(operatorSysUser);
			
			request.getSession().setAttribute(Constants.SESSION_CURRENT_USER, sysUser);
		}
		return sysUser;
	}
	
	/**
	 * 设置当前登录人
	 * @param request
	 * @param sysUser
	 */
	public static void setLoginUser(HttpServletRequest request, SysUser sysUser){
		request.getSession().setAttribute(Constants.SESSION_CURRENT_USER, sysUser);
	}
	
	/**
	 * 获取当前登录人信息
	 * 
	 * @param request
	 * @return
	 */
	public static SysUser getOperatorUser(HttpServletRequest request) {
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constants.SESSION_CURRENT_OPERATOR);
		if (sysUser == null) {
			String defLang=request.getLocale().toString();
			if(Constants.localLanguageMap.get(request.getLocale().toString())==null){
				defLang="en_US";
			}
			sysUser = new SysUser();
			sysUser.setDefCharacterCoding(defLang);
			sysUser.setIsAdmin(false);
			sysUser.setAuthorized(false);
			
			request.getSession().setAttribute(Constants.SESSION_CURRENT_OPERATOR, sysUser);
		}
		return sysUser;
	}
	
	/**
	 * 设置当前登录人
	 * @param request
	 * @param sysUser
	 */
	public static void setOperatorUser(HttpServletRequest request, SysUser sysUser){
		request.getSession().setAttribute(Constants.SESSION_CURRENT_OPERATOR, sysUser);
	}
}