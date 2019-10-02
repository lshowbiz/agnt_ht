package com.joymain.jecs.pd.webapp.action;

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
import com.joymain.jecs.pd.model.PdAdjustStock;
import com.joymain.jecs.pd.service.PdAdjustStockDetailManager;
import com.joymain.jecs.pd.service.PdAdjustStockManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdAdjustStockController extends BaseController implements
		Controller {
	private final Log log = LogFactory.getLog(PdAdjustStockController.class);
	private PdAdjustStockManager pdAdjustStockManager = null;
	private PdWarehouseUserManager pdWarehouseUserManager=null;
	private PdAdjustStockDetailManager pdAdjustStockDetailManager = null;
	public void setPdAdjustStockDetailManager(
			PdAdjustStockDetailManager pdAdjustStockDetailManager) {
		this.pdAdjustStockDetailManager = pdAdjustStockDetailManager;
	}

	public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setPdAdjustStockManager(
			PdAdjustStockManager pdAdjustStockManager) {
		this.pdAdjustStockManager = pdAdjustStockManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		super.initPmProductMap(request);
		String view = "pd/pdAdjustStockList";
		String strAction = request.getParameter("strAction");
		String asNo = (String) request.getAttribute("asNo");
		request.setAttribute("asNo", asNo);
		PdAdjustStock pdAdjustStock = new PdAdjustStock();
		// populate object with request parameters
		BeanUtils.populate(pdAdjustStock, request.getParameterMap());

		Map requestMap = RequestUtil.populateMap(request);
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		CommonRecord crm = initCommonRecord(request);// ����ѯ���д��session
		Pager pager = new Pager("pdAdjustStockListTable", request, 20);

		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤
		crm.setValue("userCodeT", sessionLogin.getUserCode());
				
		List pdAdjustStocks = null;
		String listFlag = "-1";
		// 新增仓库管理权限控制

		//Modify By WuCF 20140603 对于root账号不用限制仓库权限
		String warehouseNoStr = "";
		if(!"root".equals(sessionLogin.getUserCode())){
			warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
		}

		//新增仓库管理权限控制
    	crm.setValue("defaultWarehouse", warehouseNoStr);
		
		if (!sessionLogin.getIsManager()) {
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		}

		if ("checkPdAdjustStock".equals(strAction)) {
			crm.setValue("hasCheckUsrCodeBlank", "1");
			crm.setValue("orderFlagDefault","0,1,2,3,4");
//			crm.setValue("checkUsrCode", sessionLogin.getUserCode());

		} else if ("confirmPdAdjustStock".equals(strAction)) {
			// 新增仓库管理权限控制
			crm.setValue("orderFlagDefault","1,2,3,4");

//			crm.setValue("orderFlag", "2");

			// confirmInvoice(request,response);
		} else if ("searchPdAdjustStock".equals(strAction)) {

			// showList(request,response);
		} else if ("editPdAdjustStock".equals(strAction)) {// 制单
			
			listFlag = "1";

		} else if ("statisticPdAdjustStock".equals(strAction)) {
			List pdAdjustStockTotal = pdAdjustStockDetailManager
					.getTotalPdAdjustStockDetails(crm);
			request.setAttribute("pdAdjustStockTotal", pdAdjustStockTotal);
		}

		pdAdjustStocks = pdAdjustStockManager
				.getPdAdjustStocksByCrm(crm, pager);
		request.setAttribute("listFlag", listFlag);
		// request.setAttribute("strAction", strAction);
		request.setAttribute("pdAdjustStockExample", requestMap);
		request.setAttribute("pdAdjustStockListTable_totalRows", pager
				.getTotalObjects());
		// request.setAttribute(Constants.PDADJUSTSTOCK_LIST, pdAdjustStocks);
		/*
		 * PdAdjustStock pdAdjustStock = new PdAdjustStock(); // populate object
		 * with request parameters BeanUtils.populate(pdAdjustStock,
		 * request.getParameterMap());
		 * 
		 * //List pdAdjustStocks =
		 * pdAdjustStockManager.getPdAdjustStocks(pdAdjustStock);
		 *//**** auto pagination ***/
		/*
		 * CommonRecord crm=RequestUtil.toCommonRecord(request); Pager pager =
		 * new Pager("pdAdjustStockListTable",request, 20); List pdAdjustStocks
		 * = pdAdjustStockManager.getPdAdjustStocksByCrm(crm,pager);
		 * request.setAttribute("pdAdjustStockListTable_totalRows",
		 * pager.getTotalObjects());
		 *//*****/

		return new ModelAndView("pd/pdAdjustStockList",
				Constants.PDADJUSTSTOCK_LIST, pdAdjustStocks);
	}
}
