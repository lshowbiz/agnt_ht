package com.joymain.jecs.sys.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysDepartment;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.model.SysManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.sys.service.SysManagerManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysModifyPwdFormController extends BaseFormController {
	private SysUserManager sysUserManager = null;
	private SysManagerManager sysManagerManager = null;
	private SysDepartmentManager sysDepartmentManager = null;
	private SysLoginLogManager sysLoginLogManager;

	public void setSysLoginLogManager(SysLoginLogManager sysLoginLogManager) {
		this.sysLoginLogManager = sysLoginLogManager;
	}

	public void setSysDepartmentManager(SysDepartmentManager sysDepartmentManager) {
		this.sysDepartmentManager = sysDepartmentManager;
	}

	public void setSysManagerManager(SysManagerManager sysManagerManager) {
		this.sysManagerManager = sysManagerManager;
	}
	
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public SysModifyPwdFormController() {
		setCommandName("sysUser");
		setCommandClass(SysUser.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		SysUser sysUser = null;
		String userCode = request.getParameter("userCode");
		if (StringUtils.isEmpty(userCode)) {
			userCode = SessionLogin.getLoginUser(request).getUserCode();
		}

		sysUser = this.sysUserManager.getSysUser(userCode);
		if (sysUser == null) {
			throw new AppException("没有找到对应的用户");
		}		
		if("M".equals(SessionLogin.getLoginUser(request).getUserType())){
			return sysUser;
		}
		SysManager sysManager=this.sysManagerManager.getSysManager(sysUser.getUserCode());
		SysDepartment sysDepartment = this.sysDepartmentManager.getSysDepartment(sysManager.getDepartmentId().toString());
		request.setAttribute("sysDepartment", sysDepartment);

		return sysUser;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysUser sysUser = (SysUser) command;
		SysManager sysManager=null;
		if(!"M".equals(SessionLogin.getLoginUser(request).getUserType())){
			sysManager=this.sysManagerManager.getSysManager(sysUser.getUserCode());
		}

		String md5NewPassword = StringUtil.encodePassword(request.getParameter("newPassword"), "md5");
		String maxLoginTimes = Constants.sysConfigMap.get(
				sysUser.getCompanyCode()).get("sys.login.maxtimes");
		if ("sysModifyPwd".equals(request.getParameter("strAction"))) {
			String modifyType = request.getParameter("modifyType");
			String passwordType = request.getParameter("passwordType");
			if (StringUtils.isEmpty(modifyType)) {
				ModelAndView mv = new ModelAndView("redirect:sys_modify_pwd.html");
				mv.addObject("strAction", "sysModifyPwd");
				mv.addObject("modifyType", request.getParameter("modifyType"));
				//修改自己的密码
				try {
					String md5OldPassword = StringUtil.encodePassword(request.getParameter("oldPassword"), "md5");
					SysUser oldSysUser = this.sysUserManager.getSysUser(sysUser.getUserCode());
					if (oldSysUser != null) {
						if ("userPassword".equals(passwordType)) {
							//更改密码
							boolean flag=false;
							if (!md5OldPassword.equals(oldSysUser.getPassword())) {
								//modify by 2019-01-15 修改密码时，记录密码错误次数---begin
								Integer errNum = sysUser.getErrNum()==null?0:sysUser.getErrNum();
								String msg="";
								if ("0".equals(sysUser.getState()) || "2".equals(sysUser.getState())) {
									msg="账号已锁定";
								}else if (errNum +1 >= StringUtil.formatInt(maxLoginTimes)) {
									sysUser.setState("0");
									sysUser.setErrNum(StringUtil.formatInt(maxLoginTimes));
									this.sysUserManager.saveSysUser(sysUser);
									msg="账号已锁定";
									
								}else{
									sysUser.setErrNum(errNum + 1);
									int remainingTimes= StringUtil.formatInt(maxLoginTimes) -sysUser.getErrNum();
									msg="密码不正确!连续错误"+(maxLoginTimes)+"次后将锁定账号,剩余"+(remainingTimes<0?0:remainingTimes)+"次";
									this.sysUserManager.saveSysUser(sysUser);
								}
								this.saveMessage(request, msg);
							}else if(flag){
								this.saveMessage(request, "密码过于简单，请重新输入");
							}else {
								sysUser.setPassword(md5NewPassword);
								sysUser.setToken("");
								sysUser.setErrNum(0);
								this.sysUserManager.saveSysUser(sysUser);
								this.saveLogin(request, sysUser);
								this.saveMessage(request, getText("sysUser.password.updated"));
							}
						} else if ("password2".equals(passwordType)) {
							//更改高级密码
							if (!md5OldPassword.equals(oldSysUser.getPassword2())) {
								this.saveMessage(request, getText("error.oldPassword"));
							} else {
								sysUser.setPassword2(md5NewPassword);

								this.sysUserManager.saveSysUser(sysUser);
								this.saveLogin(request, sysUser);
								this.saveMessage(request, getText("sysUser.password2.updated"));
							}
						}
					}
				} catch (Exception e) {
					this.saveMessage(request, getText("error.oldPassword"));

					return mv;
				}
			} else {
				if ("userPassword".equals(passwordType)) {
					//更改密码
					if(!"root".equals(sysUser.getUserCode()) && !"global".equals(sysUser.getUserCode())){
						sysUser.setPassword(md5NewPassword);
						this.sysUserManager.saveSysUser(sysUser);
						this.saveLogin(request, sysUser);
						this.saveMessage(request, getText("sysUser.password.updated"));
					}else{
						this.saveLogin(request, sysUser);
						this.saveMessage(request, "不允许修改!");
					}
				} else if ("password2".equals(passwordType)) {
					//更改高级密码
					sysUser.setPassword2(md5NewPassword);

					this.sysUserManager.saveSysUser(sysUser);
					this.saveLogin(request, sysUser);
					this.saveMessage(request, getText("sysUser.password2.updated"));
				}
				
				if("other".equalsIgnoreCase(modifyType)){
					ModelAndView mv = new ModelAndView("redirect:sysManagers.html");
					mv.addObject("companyCode", sysManager.getCompanyCode());
					mv.addObject("departmentId", sysManager.getDepartmentId());
					
					return mv;
				}else if("account".equalsIgnoreCase(modifyType)){
					ModelAndView mv = new ModelAndView("redirect:sysAccounts.html");
					mv.addObject("needReload", "1");
					return mv;
				}
				
			}
		}

		ModelAndView mv = new ModelAndView("redirect:sys_modify_pwd.html");
		mv.addObject("strAction", "sysModifyPwd");
		mv.addObject("modifyType", request.getParameter("modifyType"));
		if(!"M".equals(SessionLogin.getLoginUser(request).getUserType())){
			mv.addObject("companyCode", sysManager.getCompanyCode());
			mv.addObject("departmentId", sysManager.getDepartmentId());
		}
		
		return mv;
	}
	private void saveLogin(HttpServletRequest request,SysUser sysUser){
		SysUser defSysUser=SessionLogin.getLoginUser(request);
		String ipAddress=RequestUtil.getIpAddr(request);
		SysLoginLog sysLoginLog = new SysLoginLog();
		sysLoginLog.setUserCode(sysUser.getUserCode());
		sysLoginLog.setIpAddr(ipAddress);
		sysLoginLog.setOperaterCode(defSysUser.getUserCode());
		sysLoginLog.setOperateTime(new Date());
		sysLoginLog.setOperationType("2");
		sysLoginLogManager.saveSysLoginLog(sysLoginLog);
	}
}