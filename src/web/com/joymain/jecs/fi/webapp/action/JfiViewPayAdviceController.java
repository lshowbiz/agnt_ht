package com.joymain.jecs.fi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.JfiPayAdvice;
import com.joymain.jecs.fi.service.JfiPayAdviceManager;
import com.joymain.jecs.webapp.action.BaseController;
/**
 * 查看付款通知
 * @author Alvin
 *
 */
public class JfiViewPayAdviceController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiViewPayAdviceController.class);
	private JfiPayAdviceManager jfiPayAdviceManager = null;

	public void setJfiPayAdviceManager(JfiPayAdviceManager jfiPayAdviceManager) {
		this.jfiPayAdviceManager = jfiPayAdviceManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		String adviceCode = request.getParameter("adviceCode");
		JfiPayAdvice jfiPayAdvice  = jfiPayAdviceManager.getJfiPayAdvice(adviceCode);
		if(!StringUtils.isEmpty(jfiPayAdvice.getRemark())){
			jfiPayAdvice.setRemark(StringUtils.replace(jfiPayAdvice.getRemark(), "\n", "<br>"));
		}
		request.setAttribute("jfiPayAdvice", jfiPayAdvice);

		return new ModelAndView("fi/jfiViewPayAdvice");
	}
}
