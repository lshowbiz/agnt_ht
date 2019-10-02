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
import com.joymain.jecs.fi.model.JfiChanjetLog;
import com.joymain.jecs.fi.service.JfiChanjetLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiChanjetLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiChanjetLogController.class);
    private JfiChanjetLogManager jfiChanjetLogManager = null;

    public void setJfiChanjetLogManager(JfiChanjetLogManager jfiChanjetLogManager) {
        this.jfiChanjetLogManager = jfiChanjetLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiChanjetLog jfiChanjetLog = new JfiChanjetLog();
        // populate object with request parameters
        BeanUtils.populate(jfiChanjetLog, request.getParameterMap());

	//List jfiChanjetLogs = jfiChanjetLogManager.getJfiChanjetLogs(jfiChanjetLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiChanjetLogListTable",request, 20);
        List jfiChanjetLogs = jfiChanjetLogManager.getJfiChanjetLogsByCrm(crm,pager);
        request.setAttribute("jfiChanjetLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiChanjetLogList", "jfiChanjetLogList", jfiChanjetLogs);
    }
}
