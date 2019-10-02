package com.joymain.jecs.pd.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.joymain.jecs.pd.model.PdOutWarehouse;
import com.joymain.jecs.pd.service.PdOutWarehouseDetailManager;
import com.joymain.jecs.pd.service.PdOutWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdOutWarehouseController extends BaseController implements
		Controller {
	private final Log log = LogFactory.getLog(PdOutWarehouseController.class);
	private PdOutWarehouseManager pdOutWarehouseManager = null;
	private PdWarehouseUserManager pdWarehouseUserManager = null;
	private PdOutWarehouseDetailManager pdOutWarehouseDetailManager = null;
	public void setPdOutWarehouseDetailManager(
			PdOutWarehouseDetailManager pdOutWarehouseDetailManager) {
		this.pdOutWarehouseDetailManager = pdOutWarehouseDetailManager;
	}

	public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setPdOutWarehouseManager(
			PdOutWarehouseManager pdOutWarehouseManager) {
		this.pdOutWarehouseManager = pdOutWarehouseManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		super.initPmProductMap(request);
		SysUser sessionLogin = SessionLogin.getLoginUser(request);

		// 新增仓库管理权限控制

		//Modify By WuCF 20140603 对于root账号不用限制仓库权限
		String warehouseNoStr = "";
		if(!"root".equals(sessionLogin.getUserCode())){
			warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
		}
		
		String view = "pd/pdOutWarehouseList";
		String strAction = request.getParameter("strAction");
		String owNo = (String) request.getAttribute("owNo");
		// request.setAttribute("owNo", owNo);
		String listFlag = "-1";

		CommonRecord crm = initCommonRecord(request);// ����ѯ���д��session
		Pager pager = new Pager("pdOutWarehouseListTable", request, 20);
		
		//新增仓库管理权限控制
    	crm.setValue("defaultWarehouse", warehouseNoStr);
    	
		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤
		crm.setValue("userCodeT", sessionLogin.getUserCode());
		
		 if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
			 crm.setValue("companyCode", sessionLogin.getCompanyCode());
		 }

//		crm.setValue("companyCode", sessionLogin.getCompanyCode());
		// PdOutWarehouse pdOutWarehouse = new PdOutWarehouse();
		// // populate object with request parameters
		// BeanUtils.populate(pdOutWarehouse, request.getParameterMap());

//		Map requestMap = new HashMap();
//		requestMap = RequestUtil.populateMap(request);

		if ("checkPdOutWarehouse".equals(strAction)) {

			// 新增提交步骤
			crm.setValue("orderFlagDefault","0,1,2");
			crm.setValue("hasCheckUsrCodeBlank", "1");
//			crm.setValue("checkUsrCode", sessionLogin.getUserCode());
			listFlag = "0";

		} else if ("confirmPdOutWarehouse".equals(strAction)) {

			// 新增仓库管理权限控制
			crm.setValue("orderFlagDefault","1,2");

			crm.setValue("hasCheckUsrCodeBlank", "1");
			crm.setValue("okUsrCode", sessionLogin.getUserCode());
			
			listFlag = "0";

		} else if ("editPdOutWarehouse".equals(strAction)) {
			listFlag = "1";
			// 只能查看自己的单，现去掉
			// crm.setValue("createUsrCode", sessionLogin.getUserCode());

		} else if ("statisticPdOutWarehouse".equals(strAction)) {
			List pdOutWarehouseTotal = pdOutWarehouseDetailManager
					.getTotalPdOutWarehouseDetails(crm);
			request.setAttribute("pdOutWarehouseTotal", pdOutWarehouseTotal);
		}
		List pdOutWarehouses = pdOutWarehouseManager.getPdOutWarehousesByCrm(
				crm, pager);

		// List pdOutWarehouses =
		// pdOutWarehouseManager.getPdOutWarehouses(pdOutWarehouse);
		/****
		 * �Լ��ſ� CommonRecord crm =
		 * initCommonRecord(request);//����ѯ���д��session Pager pager = new
		 * Pager("pdOutWarehouseListTable",request, 20); List pdOutWarehouses =
		 * pdOutWarehouseManager.getPdOutWarehousesByCrm(crm,pager);
		 ****/
		request.setAttribute("listFlag", listFlag);
		// request.setAttribute("strAction", strAction);
//		request.setAttribute("pdOutWarehouseExample", requestMap);

		request.setAttribute("pdOutWarehouseListTable_totalRows", pager
				.getTotalObjects());
		return new ModelAndView("pd/pdOutWarehouseList",
				Constants.PDOUTWAREHOUSE_LIST, pdOutWarehouses);
	}
}
