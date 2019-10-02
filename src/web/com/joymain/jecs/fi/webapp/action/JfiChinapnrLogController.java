package com.joymain.jecs.fi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiChinapnrLog;
import com.joymain.jecs.fi.service.JfiChinapnrLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

@SuppressWarnings("unchecked")
public class JfiChinapnrLogController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JfiChinapnrLogController.class);
	private JfiChinapnrLogManager jfiChinapnrLogManager = null;

	public void setJfiChinapnrLogManager(JfiChinapnrLogManager jfiChinapnrLogManager) {
		this.jfiChinapnrLogManager = jfiChinapnrLogManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		JfiChinapnrLog jfiChinapnrLog = new JfiChinapnrLog();
		// populate object with request parameters
		BeanUtils.populate(jfiChinapnrLog, request.getParameterMap());

		// List jfiChinapnrLogs =
		// jfiChinapnrLogManager.getJfiChinapnrLogs(jfiChinapnrLog);
		/**** auto pagination ***/
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("jfiChinapnrLogListTable", request, 20);
		List jfiChinapnrLogs = jfiChinapnrLogManager.getJfiChinapnrLogsByCrm(crm, pager);
		request.setAttribute("jfiChinapnrLogListTable_totalRows", pager.getTotalObjects());
		/*****/

		return new ModelAndView("fi/jfiChinapnrLogList", Constants.JFICHINAPNRLOG_LIST, jfiChinapnrLogs);
	}
}
