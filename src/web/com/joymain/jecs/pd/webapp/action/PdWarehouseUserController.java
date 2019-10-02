package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdWarehouseUser;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdWarehouseUserController extends BaseController implements
		Controller {
	private final Log log = LogFactory.getLog(PdWarehouseUserController.class);
	private PdWarehouseUserManager pdWarehouseUserManager = null;
	private PdWarehouseManager pdWarehouseManager = null;

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String view = "pd/pdWarehouseUserMain";

		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		CommonRecord crm = initCommonRecord(request);// ����ѯ���д��session
		String strAction = request.getParameter("strAction");
		// String warehouseNo = request.getParameter("warehouseNo");
		// PdWarehouseUser pdWarehouseUser = new PdWarehouseUser();
		// // populate object with request parameters
		// BeanUtils.populate(pdWarehouseUser, request.getParameterMap());

		// List pdWarehouseUsers =
		// pdWarehouseUserManager.getPdWarehouseUsers(pdWarehouseUser);
		/**** auto pagination ***/

		Pager pager = new Pager("pdWarehouseUserListTable", request, 20);
		// List pdWarehouseUsers =
		// pdWarehouseUserManager.getPdWarehouseUsersByCrm(crm,pager);
	

		/*****/

		if (!sessionLogin.getIsManager()) {
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		}

		List warehouseList = pdWarehouseManager.getPdWarehousesByCrm(crm, null);

		List pdWarehouseUsers = pdWarehouseUserManager
				.getPdWarehouseUsersByCrm(crm, pager);// 得到

		if ("pdWarehouseUserMain".equals(strAction)) {
			view = "pd/pdWarehouseUserMain";

		}else if("pdWarehouseUserMain2".equals(strAction)){//EC需求优化5、仓库权限新增以员工账号为单位勾选仓库信息
			view = "pd/pdWarehouseUserMain2";
		} else if ("pdWarehouseUserContent".equals(strAction)) {
			view = "pd/pdWarehouseUserList";
			request.setAttribute("pdWarehouseUserListTable_totalRows", pager
					.getTotalObjects());
			request.setAttribute("pdWarehouseUsers", pdWarehouseUsers);
		} else if ("pdWarehouseTree".equals(strAction)) {
			view = "pd/pdWarehouseTree";
			request.setAttribute("warehouseList", warehouseList);
		}

		return new ModelAndView(view);

		/*
		 * PdWarehouseUser pdWarehouseUser = new PdWarehouseUser(); // populate
		 * object with request parameters BeanUtils.populate(pdWarehouseUser,
		 * request.getParameterMap());
		 * 
		 * //List pdWarehouseUsers =
		 * pdWarehouseUserManager.getPdWarehouseUsers(pdWarehouseUser);
		 * 
		 * CommonRecord crm=RequestUtil.toCommonRecord(request); Pager pager =
		 * new Pager("pdWarehouseUserListTable",request, 20); List
		 * pdWarehouseUsers =
		 * pdWarehouseUserManager.getPdWarehouseUsersByCrm(crm,pager);
		 * request.setAttribute("pdWarehouseUserListTable_totalRows",
		 * pager.getTotalObjects());
		 */

		
	}
}
