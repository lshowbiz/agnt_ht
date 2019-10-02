package com.joymain.jecs.pd.webapp.action;

import java.util.ArrayList;
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
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.service.PdReturnPurchaseDetailManager;
import com.joymain.jecs.pd.service.PdReturnPurchaseManager;
import com.joymain.jecs.pd.service.PdWarehouseUserManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdReturnPurchaseController extends BaseController implements
		Controller {
	private final Log log = LogFactory.getLog(PdReturnPurchaseController.class);
	private PdReturnPurchaseManager pdReturnPurchaseManager = null;
	private PdReturnPurchaseDetailManager pdReturnPurchaseDetailManager = null;
	private PdWarehouseUserManager pdWarehouseUserManager = null;
	public void setPdWarehouseUserManager(
			PdWarehouseUserManager pdWarehouseUserManager) {
		this.pdWarehouseUserManager = pdWarehouseUserManager;
	}

	public void setPdReturnPurchaseDetailManager(
			PdReturnPurchaseDetailManager pdReturnPurchaseDetailManager) {
		this.pdReturnPurchaseDetailManager = pdReturnPurchaseDetailManager;
	}

	public void setPdReturnPurchaseManager(
			PdReturnPurchaseManager pdReturnPurchaseManager) {
		this.pdReturnPurchaseManager = pdReturnPurchaseManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		super.initPmProductMap(request);
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String strAction = request.getParameter("strAction");

		//Modify By WuCF 20140603 对于root账号不用限制仓库权限
		String warehouseNoStr = "";
		if(!"root".equals(sessionLogin.getUserCode())){
			warehouseNoStr = pdWarehouseUserManager.getStringWarehouseByUser(sessionLogin.getUserCode());
		}

//		Map map = RequestUtil.populateMap(request);
		CommonRecord crm = initCommonRecord(request);// ����ѯ���д��session
		Pager pager = new Pager("pdReturnPurchaseListTable", request, 20);
		//新增仓库管理权限控制
    	crm.setValue("defaultWarehouse", warehouseNoStr);
		//Modify By WuCF 20131114 仓库权限管理修改，查询时过滤
		crm.setValue("userCodeT", sessionLogin.getUserCode());
		
		String view = "welcome";
		List pdReturnPurchases = new ArrayList();
//		if ("C".equalsIgnoreCase(sessionLogin.getUserType())) {
			if (!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())) {
				crm.setValue("companyCode", sessionLogin.getCompanyCode());
			}
//		}
		String listFlag = "-1";
		if ("editPdReturnPurchase".equals(strAction)) {// 编辑
			listFlag = "1";
			// 只能看到本流程的，先去掉
			// crm.setValue("orderFlagDefault","-1");
			// 只能查看自己的单，现去掉
			// crm.setValue("createUsrNo", sessionLogin.getUserCode());
			view = "pd/pdReturnPurchaseList";
		} else if ("checkPdReturnPurchase".equals(strAction)) {// 初审
			crm.setValue("orderFlagDefault", "0,1,2,3");
//			crm.setValue("checkUsrNo", sessionLogin.getUserCode());
			crm.setValue("hasCheckUsrCodeBlank", "1");
			view = "pd/pdReturnPurchaseList";

		} else if ("financePdReturnPurchase".equals(strAction)) {// 财务审核
			crm.setValue("orderFlagDefault", "1,2,3");
			crm.setValue("financeUsrNo", sessionLogin.getUserCode());
			crm.setValue("hasCheckUsrCodeBlank", "1");
			view = "pd/pdReturnPurchaseList";
		} else if ("confirmPdReturnPurchase".equals(strAction)) {// 确认
			// 新增仓库管理权限控制

			crm.setValue("okUsrNo", sessionLogin.getUserCode());
			crm.setValue("hasCheckUsrCodeBlank", "1");
			crm.setValue("orderFlagDefault", "1,2,3");
			view = "pd/pdReturnPurchaseList";
		} else if ("searchPdReturnPurchase".equals(strAction)) {// 搜索
			view = "pd/pdReturnPurchaseList";
		} else if ("statisticPdReturnPurchase".equals(strAction)) {
			List pdReturnPurchaseTotal = pdReturnPurchaseDetailManager
					.getTotalPdReturnPurchaseDetails(crm);
			request
					.setAttribute("pdReturnPurchaseTotal",
							pdReturnPurchaseTotal);
			view = "pd/pdReturnPurchaseList";
		}

		pdReturnPurchases = pdReturnPurchaseManager.getPdReturnPurchasesByCrm(
				crm, pager);
		request.setAttribute("listFlag", listFlag);
		// request.setAttribute("strAction", strAction);
//		request.setAttribute("pdReturnPurchaseExample", map);
		request.setAttribute("pdReturnPurchaseListTable_totalRows", pager
				.getTotalObjects());

		return new ModelAndView(view,
				Constants.PDRETURNPURCHASE_LIST, pdReturnPurchases);
	}
}
