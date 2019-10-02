package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.service.SysManagerModlPowManager;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysPowerFrameController implements Controller {
	private final Log log = LogFactory.getLog(SysPowerFrameController.class);
	private SysManagerModlPowManager sysManagerModlPowManager = null;
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysManagerModlPowManager(SysManagerModlPowManager sysManagerModlPowManager) {
		this.sysManagerModlPowManager = sysManagerModlPowManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "sysMyPowerManager".equals(request.getParameter("strAction"))) {
			String masterUserCode = request.getParameter("masterUserCode");//所选择的用户编码
			String selectedModule = request.getParameter("selectedModule");//所选择的模块ID 格式:123,113,111
			String selectedSalveUserCode = request.getParameter("selectedSalveUserCode");//所选择的可管理人员 格式: M#code1,M#code2,M#code3
			String companyCode = request.getParameter("companyCode");

			this.sysManagerModlPowManager.saveSysManagerModlPowsAssigned(SessionLogin.getLoginUser(request).getUserCode(),
			        masterUserCode, selectedModule, selectedSalveUserCode, companyCode);

			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sysModuel.setting.sucess"));
			ModelAndView mv = new ModelAndView("redirect:sys_power_frm.html");
			mv.addObject("companyCode", companyCode);
			mv.addObject("masterUserCode", masterUserCode);
			return mv;
		}
		//读取所管理的人员所处公司
		List alCompanys = this.alCompanyManager.getMyAlCompanys(SessionLogin.getLoginUser(request), null, false);
		request.setAttribute("alCompanys", alCompanys);

		if (!StringUtils.isEmpty(request.getParameter("companyCode"))) {
			request.setAttribute("companyCode", request.getParameter("companyCode"));
		} else {
			request.setAttribute("companyCode", SessionLogin.getLoginUser(request).getCompanyCode());
		}

		return new ModelAndView("sys/sys_power_frm");
	}
}