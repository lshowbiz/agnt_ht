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
import com.joymain.jecs.fi.model.JfiReapalLog;
import com.joymain.jecs.fi.service.JfiReapalLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiReapalLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiReapalLogController.class);
    private JfiReapalLogManager jfiReapalLogManager = null;

    public void setJfiReapalLogManager(JfiReapalLogManager jfiReapalLogManager) {
        this.jfiReapalLogManager = jfiReapalLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiReapalLog jfiReapalLog = new JfiReapalLog();
        // populate object with request parameters
        BeanUtils.populate(jfiReapalLog, request.getParameterMap());

	//List jfiReapalLogs = jfiReapalLogManager.getJfiReapalLogs(jfiReapalLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiReapalLogListTable",request, 20);
        List jfiReapalLogs = jfiReapalLogManager.getJfiReapalLogsByCrm(crm,pager);
        request.setAttribute("jfiReapalLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiReapalLogList", "jfiReapalLogList", jfiReapalLogs);
    }
}
