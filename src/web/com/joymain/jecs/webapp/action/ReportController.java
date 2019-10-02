package com.joymain.jecs.webapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.util.report.ReportUtil;

/**
 * 报表测试
 * @author Aidy.Q
 *
 */
public class ReportController implements Controller {
	private transient final Log log = LogFactory.getLog(ReportController.class);

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'execute' method");
		}

		if("post".equalsIgnoreCase(request.getMethod()) && "report".equalsIgnoreCase(request.getParameter("strAction"))){
			//根据报表格式生成对应的报表
			String format=request.getParameter("reportType");
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("ReportTitle", "测试标题");
			
			ReportUtil reportUtil=new ReportUtil(request, response);
			reportUtil.exportReport(format, "testReport.jasper", "test", parameters);
			
			return null;
		}
		

		return new ModelAndView("test");
	}
}