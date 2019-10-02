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
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.sys.model.SysDepartment;
import com.joymain.jecs.sys.service.SysDepartmentManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysDepartmentController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(SysDepartmentController.class);
	private SysDepartmentManager sysDepartmentManager = null;
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setSysDepartmentManager(SysDepartmentManager sysDepartmentManager) {
		this.sysDepartmentManager = sysDepartmentManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("sysDepartmentListTable", request, 20);
		List sysDepartments = sysDepartmentManager.getSysDepartmentsByCrm(crm, pager);
		request.setAttribute("sysDepartmentListTable_totalRows", pager.getTotalObjects());

		String parentId = request.getParameter("parentId");
		if (!StringUtils.isEmpty(parentId)) {
			SysDepartment parentSysDepartment = this.sysDepartmentManager.getSysDepartment(parentId);
			request.setAttribute("parentSysDepartment", parentSysDepartment);
			//判断当前登录人是否有权限管理此部门
			boolean hasPermit = false;
			if (SessionLogin.getLoginUser(request).getIsAdmin()) {
				hasPermit = true;
			} else {
				if(parentSysDepartment==null){
					hasPermit = true;
				}else{
					if (!StringUtils.isEmpty(parentSysDepartment.getAllowedUser())) {
						String[] allowedUsers = parentSysDepartment.getAllowedUser().split(",");
						if (StringUtil.hasString(allowedUsers, SessionLogin.getLoginUser(request).getUserCode(), false)) {
							hasPermit = true;
						}
					}
				}
			}

			request.setAttribute("hasPermit", hasPermit);
		}

		String companyCode = request.getParameter("companyCode");
		if (!StringUtils.isEmpty(companyCode)) {
			AlCompany alCompany = this.alCompanyManager.getAlCompanyByCode(companyCode);
			request.setAttribute("alCompany", alCompany);
		}

		return new ModelAndView("sys/sysDepartmentList", Constants.SYSDEPARTMENT_LIST, sysDepartments);
	}
}