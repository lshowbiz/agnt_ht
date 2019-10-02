package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdShipStrategyMain;
import com.joymain.jecs.pd.service.PdShipStrategyMainManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdShipStrategyMainController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(PdShipStrategyMainController.class);
	private PdShipStrategyMainManager pdShipStrategyMainManager = null;

	public void setPdShipStrategyMainManager(PdShipStrategyMainManager pdShipStrategyMainManager) {
		this.pdShipStrategyMainManager = pdShipStrategyMainManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		PdShipStrategyMain pdShipStrategyMain = new PdShipStrategyMain();
		// populate object with request parameters
		BeanUtils.populate(pdShipStrategyMain, request.getParameterMap());

		//List pdShipStrategyMains = pdShipStrategyMainManager.getPdShipStrategyMains(pdShipStrategyMain);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("pdShipStrategyMainListTable",request, 20);
		List pdShipStrategyMains = pdShipStrategyMainManager.getPdShipStrategyMainsByCrm(crm,pager);
		request.setAttribute("pdShipStrategyMainListTable_totalRows", pager.getTotalObjects());
		/*****/

		return new ModelAndView("pd/pdShipStrategyMainList", Constants.PDSHIPSTRATEGYMAIN_LIST, pdShipStrategyMains);
	}
}
