package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class BdPeriodController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(BdPeriodController.class);
	private BdPeriodManager bdPeriodManager = null;

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("bdPeriodListTable", request, 20);
		List bdPeriods = bdPeriodManager.getBdPeriodsByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("bdPeriodListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("bd/bdPeriodList", Constants.BDPERIOD_LIST, bdPeriods);
	}
}
