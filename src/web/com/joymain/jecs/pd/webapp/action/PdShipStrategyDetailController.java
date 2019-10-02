package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdShipStrategyDetail;
import com.joymain.jecs.pd.service.PdShipStrategyDetailManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdShipStrategyDetailController extends BaseController implements
		Controller {
	private final Log log = LogFactory
			.getLog(PdShipStrategyDetailController.class);
	private PdShipStrategyDetailManager pdShipStrategyDetailManager = null;

	public void setPdShipStrategyDetailManager(
			PdShipStrategyDetailManager pdShipStrategyDetailManager) {
		this.pdShipStrategyDetailManager = pdShipStrategyDetailManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		PdShipStrategyDetail pdShipStrategyDetail = new PdShipStrategyDetail();
		// populate object with request parameters
		BeanUtils.populate(pdShipStrategyDetail, request.getParameterMap());

		// List pdShipStrategyDetails =
		// pdShipStrategyDetailManager.getPdShipStrategyDetails(pdShipStrategyDetail);
		/**** auto pagination ***/
		super.initStateCodeParem(request);
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String returnStr = "pd/pdShipStrategyDetailList";
		Map map = ListUtil.getListOptions(sessionLogin.getCompanyCode(), "ship.strategy");
		
		//Modify By WuCF 2014-02-26审核处理
		String uniNoStr = request.getParameter("uniNoStr");
		String status2 = request.getParameter("status2"); 
		String ssId = request.getParameter("ssId");
		if(StringUtils.isNotEmpty(uniNoStr) && StringUtils.isNotEmpty(uniNoStr)){
			int i = pdShipStrategyDetailManager.batchAuditPdShipNews(uniNoStr, status2); 
//			returnStr = "pd/pdShipStrategyDetails.html?strAction=ssDetailList&ssId="+ssId;
		}
		
		Pager pager = new Pager("pdShipStrategyDetailListTable", request, 20);
		List pdShipStrategyDetails = pdShipStrategyDetailManager
				.getPdShipStrategyDetailsByCrm(crm, pager);
		request.setAttribute("pdShipStrategyDetailListTable_totalRows", pager
				.getTotalObjects());
		/*****/

		return new ModelAndView(returnStr,
				Constants.PDSHIPSTRATEGYDETAIL_LIST, pdShipStrategyDetails);
	}
}
