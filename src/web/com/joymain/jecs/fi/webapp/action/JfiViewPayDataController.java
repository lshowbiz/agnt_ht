package com.joymain.jecs.fi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.JfiPayData;
import com.joymain.jecs.fi.service.JfiPayDataManager;
import com.joymain.jecs.webapp.action.BaseController;
/**
 * 查看银行到款条目
 * @author Alvin
 *
 */
public class JfiViewPayDataController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiViewPayDataController.class);
	private JfiPayDataManager jfiPayDataManager = null;
	
	public void setJfiPayDataManager(JfiPayDataManager jfiPayDataManager) {
		this.jfiPayDataManager = jfiPayDataManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		String dataId = request.getParameter("dataId");
		JfiPayData jfiPayData=jfiPayDataManager.getJfiPayData(dataId);
		request.setAttribute("jfiPayData", jfiPayData);
		if(!StringUtils.isEmpty(jfiPayData.getRemark())){
			jfiPayData.setRemark(StringUtils.replace(jfiPayData.getRemark(), "\n", "<br>"));
		}
		if(!StringUtils.isEmpty(jfiPayData.getTraceLog())){
			jfiPayData.setTraceLog(StringUtils.replace(jfiPayData.getTraceLog(), "\n", "<br>"));
		}
		if(!StringUtils.isEmpty(jfiPayData.getExcerpt())){
			jfiPayData.setExcerpt(StringUtils.replace(jfiPayData.getExcerpt(), "\n", "<br>"));
		}

		return new ModelAndView("fi/jfiViewPayData");
	}
}