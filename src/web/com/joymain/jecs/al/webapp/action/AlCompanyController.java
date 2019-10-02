package com.joymain.jecs.al.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class AlCompanyController implements Controller {
	private final Log log = LogFactory.getLog(AlCompanyController.class);
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm=RequestUtil.toCommonRecord(request);
		crm.addField(new CustomField("companyCode", 1111, SessionLogin.getLoginUser(request).getCompanyCode()));
		// 设置分页参数		
		Pager pager = new Pager("alCompanyListTable",request, 20);
		//分页获取数据
		List alCompanys = alCompanyManager.getAlCompanysByPage(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("alCompanyListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("al/alCompanyList", Constants.ALCOMPANY_LIST, alCompanys);
	}
}
