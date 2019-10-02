package com.joymain.jecs.mi.webapp.action;

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
import com.joymain.jecs.mi.model.JmiMemberLog;
import com.joymain.jecs.mi.service.JmiMemberLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiMemberLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberLogController.class);
    private JmiMemberLogManager jmiMemberLogManager = null;

    public void setJmiMemberLogManager(JmiMemberLogManager jmiMemberLogManager) {
        this.jmiMemberLogManager = jmiMemberLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiMemberLog jmiMemberLog = new JmiMemberLog();
        // populate object with request parameters
        BeanUtils.populate(jmiMemberLog, request.getParameterMap());

	//List jmiMemberLogs = jmiMemberLogManager.getJmiMemberLogs(jmiMemberLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiMemberLogListTable",request, 20);
        List jmiMemberLogs = jmiMemberLogManager.getJmiMemberLogsByCrm(crm,pager);
        request.setAttribute("jmiMemberLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiMemberLogList", "111", jmiMemberLogs);
    }
}
