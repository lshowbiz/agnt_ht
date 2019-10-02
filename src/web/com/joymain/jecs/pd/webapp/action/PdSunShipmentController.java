package com.joymain.jecs.pd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;

public class PdSunShipmentController extends BaseController implements
		Controller {
	private PdSendInfoManager pdSendInfoManager = null;
	
	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		super.initStateCodeParem(request);
		String view = "pd/pdSunShipmentsList";
		String strAction = request.getParameter("strAction");
		CommonRecord crm = initCommonRecord(request);
		Pager pager = new Pager("pdSendInfoListTable", request, 20);
		List pdSendInfos = pdSendInfoManager.getSunShipmentsByCrm(crm, pager);
		request.setAttribute("pdSendInfoListTable_totalRows", pager
				.getTotalObjects());
		
		return new ModelAndView(view, Constants.PDSENDINFO_LIST,
				pdSendInfos);
	}

}
