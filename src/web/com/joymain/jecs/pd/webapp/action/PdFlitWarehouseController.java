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
import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager;
import com.joymain.jecs.pd.service.PdFlitWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdFlitWarehouseController extends BaseController implements
		Controller {
	private final Log log = LogFactory.getLog(PdFlitWarehouseController.class);
	private PdFlitWarehouseManager pdFlitWarehouseManager = null;
	private PdWarehouseUserManager pdWarehouseUserManager = null;
private PdFlitWarehouseDetailManager pdFlitWarehouseDetailManager = null;
	public void setPdFlitWarehouseDetailManager(
		PdFlitWarehouseDetailManager pdFlitWarehouseDetailManager) {
	this.pdFlitWarehouseDetailManager = pdFlitWarehouseDetailManager;
}

	public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setPdFlitWarehouseManager(
			PdFlitWarehouseManager pdFlitWarehouseManager) {
		this.pdFlitWarehouseManager = pdFlitWarehouseManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String strAction = request.getParameter("strAction");
		PdFlitWarehouse pdFlitWarehouse = new PdFlitWarehouse();
		// populate object with request parameters
		BeanUtils.populate(pdFlitWarehouse, request.getParameterMap());

		super.initPmProductMap(request);
		String fwNo = (String) request.getAttribute("fwNo");
		request.setAttribute("fwNo", fwNo);
		String listFlag = "-1";// 新建
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		CommonRecord crm = initCommonRecord(request);// ����ѯ���д��session
		Pager pager = new Pager("pdFlitWarehouseListTable", request, 20);

		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤
		crm.setValue("userCodeT", sessionLogin.getUserCode());

		// 新增仓库管理权限控制

		//Modify By WuCF 20140603 对于root账号不用限制仓库权限
		String warehouseNoStr = "";
		if(!"root".equals(sessionLogin.getUserCode())){
			warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
		}
		//新增仓库管理权限控制
//    	crm.setValue("inDefaultWarehouse", warehouseNoStr);
//    	crm.setValue("outDefaultWarehouse", warehouseNoStr);
    	
		if (!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())) {
			crm.setValue("companyCode", sessionLogin.getCompanyCode());
		}
		if ("checkPdFlitWarehouse".equals(strAction)) {// 审核者
			crm.setValue("orderFlagDefault", "0,1");
			crm.setValue("searchSwitch", "out");
			crm.setValue("checkUsrNo", sessionLogin.getUserCode());
			crm.setValue("hasCheckUsrNoBlank", "1");
			// crm.setValue("checkStatus", "1");
			listFlag = "0";

		} else if ("confirmPdFlitWarehouse".equals(strAction)) {// 确认者
			// 新增仓库管理权限控制
			crm.setValue("orderFlagDefault", "1,2");
//			crm.setValue("checkStatus", "2");
			crm.setValue("searchSwitch", "out");
			crm.setValue("okUsrNo", sessionLogin.getUserCode());
			crm.setValue("hasCheckUsrNoBlank", "1");
			// crm.setValue("okStatus", "1");
			listFlag = "0";

		} else if ("searchPdFlitWarehouse".equals(strAction)) {// 查询
		// showList(request,response);
		} else if ("onWayPdFlitWarehouse".equals(strAction)) {// 在途查询
			crm.setValue("orderFlagDefault", "2");
			crm.setValue("searchSwitch", "in");
			/*crm.setValue("checkStatus", "2");
			crm.setValue("okStatus", "2");
			crm.setValue("toStatus", "1");*/

		} else if ("arrivedPdFlitWarehouse".equals(strAction)) {// 到库确认
			// 新增仓库管理权限控制
			crm.setValue("orderFlagDefault", "2,3");

			crm.setValue("searchSwitch", "in");
			/*crm.setValue("checkStatus", "2");
			crm.setValue("okStatus", "2");
			 */
		} else if ("editPdFlitWarehouse".equals(strAction)) {
			// 只能查看自己的单，现去掉
			// crm.setValue("createUNo", sessionLogin.getUserCode());
			listFlag = "1";

		} else if ("statisticPdFlitWarehouse".equals(strAction)) {
			List pdFlitWarehouseTotal = pdFlitWarehouseDetailManager
					.getTotalPdFlitWarehouseDetails(crm);
			request.setAttribute("pdFlitWarehouseTotal", pdFlitWarehouseTotal);
		}

		List<PdFlitWarehouse> pdFlitWarehouses = pdFlitWarehouseManager
				.getPdFlitWarehousesByCrm(crm, pager);
		// request.setAttribute("strAction", strAction);
		request.setAttribute("pdFlitWarehouseExample", pdFlitWarehouse);
		request.setAttribute("listFlag", listFlag);
		request.setAttribute("pdFlitWarehouseListTable_totalRows", pager
				.getTotalObjects());

		return new ModelAndView("pd/pdFlitWarehouseList",
				Constants.PDFLITWAREHOUSE_LIST, pdFlitWarehouses);
	}
}
