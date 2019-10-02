package com.joymain.jecs.fi.webapp.action;

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
import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.fi.service.JfiYeepayLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiYeepayLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiYeepayLogController.class);
    private JfiYeepayLogManager jfiYeepayLogManager = null;

    public void setJfiYeepayLogManager(JfiYeepayLogManager jfiYeepayLogManager) {
        this.jfiYeepayLogManager = jfiYeepayLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiYeepayLog jfiYeepayLog = new JfiYeepayLog();
        // populate object with request parameters
        BeanUtils.populate(jfiYeepayLog, request.getParameterMap());

	//List jfiYeepayLogs = jfiYeepayLogManager.getJfiYeepayLogs(jfiYeepayLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiYeepayLogListTable",request, 20);
        List jfiYeepayLogs = jfiYeepayLogManager.getJfiYeepayLogsByCrm(crm,pager);
        request.setAttribute("jfiYeepayLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiYeepayLogList", "jfiYeepayLogList", jfiYeepayLogs);
    }
}
