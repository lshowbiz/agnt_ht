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
import com.joymain.jecs.pd.model.PdWarehouseArea;
import com.joymain.jecs.pd.service.PdWarehouseAreaManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdWarehouseAreaController extends BaseController implements
		Controller {
	private final Log log = LogFactory.getLog(PdWarehouseAreaController.class);
	private PdWarehouseAreaManager pdWarehouseAreaManager = null;
	private PdWarehouseManager pdWarehouseManager = null;

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public void setPdWarehouseAreaManager(
			PdWarehouseAreaManager pdWarehouseAreaManager) {
		this.pdWarehouseAreaManager = pdWarehouseAreaManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		super.initStateCodeParem(request);
		String view = "pd/pdWarehouseAreaMain";
		Pager pager = new Pager("pdWarehouseAreaListTable", request, 20);

		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		CommonRecord crm = initCommonRecord(request);// ����ѯ���д��session
		String strAction = request.getParameter("strAction");
		PdWarehouseArea pdWarehouseArea = new PdWarehouseArea();

		if (!sessionLogin.getIsManager()) {
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		}

		List warehouseList = pdWarehouseManager.getPdWarehousesByCrm(crm, null);

		List pdWarehouseAreas = pdWarehouseAreaManager
				.getPdWarehouseAreasByCrm(crm, pager);

		
		if ("pdWarehouseAreaMain".equals(strAction)) {
			view = "pd/pdWarehouseAreaMain";

		} else if ("pdWarehouseAreaContent".equals(strAction)) {
			view = "pd/pdWarehouseAreaList";
			request.setAttribute("pdWarehouseAreaListTable_totalRows", pager
					.getTotalObjects());
			request.setAttribute("pdWarehouseAreas", pdWarehouseAreas);
		} else if ("pdWarehouseTree".equals(strAction)) {
			view = "pd/pdWarehouseTree";
			request.setAttribute("warehouseList", warehouseList);
		}
		return new ModelAndView(view);
		
		/*// populate object with request parameters
		BeanUtils.populate(pdWarehouseArea, request.getParameterMap());

		// List pdWarehouseAreas =
		// pdWarehouseAreaManager.getPdWarehouseAreas(pdWarehouseArea);
		*//**** auto pagination ***//*
		// CommonRecord crm=RequestUtil.toCommonRecord(request);
		List pdWarehouseAreas = pdWarehouseAreaManager
				.getPdWarehouseAreasByCrm(crm, pager);
		request.setAttribute("pdWarehouseAreaListTable_totalRows", pager
				.getTotalObjects());
		*//*****//*

		return new ModelAndView("pd/pdWarehouseAreaList",
				Constants.PDWAREHOUSEAREA_LIST, pdWarehouseAreas);*/
	}
}
