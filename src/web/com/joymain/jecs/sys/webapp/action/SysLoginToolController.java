package com.joymain.jecs.sys.webapp.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCompanyManager;
//import com.joymain.jecs.bd.model.BdPeriod;
//import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysLoginToolController extends BaseController implements Controller {
	private SysUserManager sysUserManager = null;
	private AlCompanyManager alCompanyManager = null;
	private AlCharacterCodingManager alCharacterCodingManager = null;
//	private BdPeriodManager bdPeriodManager = null;
	private SysModuleManager sysModuleManager = null;
	private SysLoginLogManager sysLoginLogManager = null;
	public void setSysLoginLogManager(SysLoginLogManager sysLoginLogManager) {
		this.sysLoginLogManager = sysLoginLogManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setAlCharacterCodingManager(AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}

//	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
//		this.bdPeriodManager = bdPeriodManager;
//	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser sessionLogin = SessionLogin.getLoginUser(request);//获得操作者 的session信息
		String characterCode = request.getParameter("characterCode");
		String userCode = request.getParameter("userCode");//被操作者账号

		if (!StringUtils.isEmpty(characterCode) && "post".equalsIgnoreCase(request.getMethod())) {
			SessionLogin.getLoginUser(request).setDefCharacterCoding(characterCode);

			return new ModelAndView("redirect:index.html");
		}

		String operatorCode = sessionLogin.getUserCode();//操作者账号
		SysUser user = sysUserManager.getSysUser(userCode);
		SysUser operatorSysUser = sysUserManager.getSysUser(operatorCode);
		String ipAddress=RequestUtil.getIpAddr(request);
		//sessionLogin.setOperatorSysUser(operatorSysUser);
		user.setOperatorSysUser(operatorSysUser);

		if (user == null) {
			String msg = LocaleUtil.getLocalText(sessionLogin.getDefCharacterCoding(), "error.msg.noexistperson");
			this.saveMessage(request, msg);
		} else if ("m".equalsIgnoreCase(user.getUserType())) {//更新成会员的session
			/*
			 * sessionLogin.setSysUser(user); sessionLogin.setIsAdmin(false); sessionLogin.setIsAgent(false); sessionLogin.setIsCompany(false); sessionLogin.setIsManager(false); sessionLogin.setIsMember(true);
			 */
			user.setIsAdmin(false);
			user.setIsAgent(false);
			user.setIsCompany(false);
			user.setIsManager(false);
			user.setIsMember(true);
			SessionLogin.setLoginUser(request, user);
		} else if ("p".equalsIgnoreCase(user.getUserType()) || "q".equalsIgnoreCase(user.getUserType())) {//代理商
			/*
			 * sessionLogin.setSysUser(user); sessionLogin.setIsAdmin(false); sessionLogin.setIsAgent(true); sessionLogin.setIsCompany(false); sessionLogin.setIsManager(false); sessionLogin.setIsMember(false);
			 */
			user.setIsAdmin(false);
			user.setIsAgent(true);
			user.setIsCompany(false);
			user.setIsManager(false);
			user.setIsMember(false);
			SessionLogin.setLoginUser(request, user);
		}

		//获取有权限的模块
		//Map<String, String> powerMap=this.sysModuleManager.getSysPowerMap(SessionLogin.getLoginUser(request));
		//SessionLogin.getLoginUser(request).setPowerMap(powerMap);
		user.setAuthorized(true);
		SessionLogin.setLoginUser(request, user);
		SessionLogin.setOperatorUser(request, operatorSysUser);
		//SessionLogin.getLoginUser(request).setAuthorized(true);
		sessionLogin = SessionLogin.getLoginUser(request);

		//获取登录人公司的资料
		AlCompany alCompany = alCompanyManager.getAlCompanyByCode(sessionLogin.getCompanyCode());
		request.setAttribute("alCompany", alCompany);
		//获取系统可用的语言
		List alCharacterCodings = this.alCharacterCodingManager.getAlCharacterCodings(null);
		request.setAttribute("alCharacterCodings", alCharacterCodings);
		//获取当前对应的期数
//		BdPeriod bdPeriod = this.bdPeriodManager.getBdPeriodByTime(DateUtil.getToday("yyyy-MM-dd"), null);
//		if (bdPeriod == null) {
//			bdPeriod = new BdPeriod();
//		}
//		request.setAttribute("bdPeriod", bdPeriod);
		//显示系统名称
		String webappName = "webapp.name";

		if (sessionLogin.getIsAgent()) {
			webappName = "webapp.name.agent";
		} else if (sessionLogin.getIsMember()) {
			webappName = "webapp.name.member";
		} else if (sessionLogin.getIsManager()) {
			webappName = "webapp.name.manager";
		} else if (sessionLogin.getIsCompany()) {
			webappName = "webapp.name.company";
		}
		request.setAttribute("webappName", LocaleUtil.getLocalText(webappName));

		SysLoginLog sysLoginLog = new SysLoginLog();
		sysLoginLog.setUserCode(userCode);
		sysLoginLog.setIpAddr(ipAddress);
		sysLoginLog.setOperaterCode(operatorCode);
		sysLoginLog.setOperateTime(new Date());
		sysLoginLog.setOperationType("1");
		sysLoginLogManager.saveSysLoginLog(sysLoginLog);
		return new ModelAndView("redirect:index.html");
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setSysModuleManager(SysModuleManager sysModuleManager) {
		this.sysModuleManager = sysModuleManager;
	}

}
