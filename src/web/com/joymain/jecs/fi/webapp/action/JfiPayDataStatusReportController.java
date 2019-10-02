package com.joymain.jecs.fi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 到款条目状态报表
 * @author Alvin
 *
 */
public class JfiPayDataStatusReportController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiPayDataStatusReportController.class);
	private JfiPayDataManager jfiPayDataManager = null;
	private AlCompanyManager alCompanyManager = null;

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setJfiPayDataManager(JfiPayDataManager jfiPayDataManager) {
		this.jfiPayDataManager = jfiPayDataManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		if ("post".equalsIgnoreCase(request.getMethod()) && "fiPayDataStatusReport".equals(request.getParameter("strAction"))) {
			String companyCode=request.getParameter("companyCode");
			if(StringUtils.isEmpty(companyCode)){
				companyCode=SessionLogin.getLoginUser(request).getCompanyCode();
			}

			List jfiPayDatas = this.jfiPayDataManager.getJfiPayDataGroupTotal(companyCode, request.getParameter("startDealDate"), request.getParameter("endDealDate"));
			request.setAttribute("jfiPayDatas", jfiPayDatas);

			request.setAttribute("isReporting", "1");
			request.setAttribute("currentTime", DateUtil.getToday("yyyy-MM-dd HH:mm:ss"));
		}

		return new ModelAndView("fi/jfi_pay_data_status_report");
	}
}