package com.joymain.jecs.al.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

public class AlCountryController implements Controller {
	private final Log log = LogFactory.getLog(AlCountryController.class);
	private AlCountryManager alCountryManager = null;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm=RequestUtil.toCommonRecord(request);		
		// 设置分页参数		
		Pager pager = new Pager("alCountryListTable",request, 20);
		//分页获取数据
		List alCountrys = alCountryManager.getAlCountrysByPage(crm,pager);
		//根据数据重新设置分页数据
		request.setAttribute("alCountryListTable_totalRows", pager.getTotalObjects());

		return new ModelAndView("al/alCountryList", Constants.ALCOUNTRY_LIST, alCountrys);
	}
}
