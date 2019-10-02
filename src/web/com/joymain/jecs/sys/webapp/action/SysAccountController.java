package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.sys.service.SysManagerManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysAccountController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(SysAccountController.class);
	private SysManagerManager sysManagerManager = null;
	private SysDepartmentManager sysDepartmentManager = null;
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysDepartmentManager(SysDepartmentManager sysDepartmentManager) {
		this.sysDepartmentManager = sysDepartmentManager;
	}

	public void setSysManagerManager(SysManagerManager sysManagerManager) {
		this.sysManagerManager = sysManagerManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("sysManagerListTable", request, 20);
		
		if (StringUtils.isEmpty(request.getParameter("companyCode"))) {
			crm.addField(new CustomField("companyCode", 1111, SessionLogin.getLoginUser(request).getCompanyCode()));
		}
		
		List sysManagers = sysManagerManager.getSysManagersByCrm(crm, pager);
		request.setAttribute("sysManagerListTable_totalRows", pager.getTotalObjects());
		
		List alCompanys = this.alCompanyManager.getAlCompanysExceptAA();
		request.setAttribute("alCompanys", alCompanys);
		
		return new ModelAndView("sys/sysAccountList", Constants.SYSMANAGER_LIST, sysManagers);
	}
}
