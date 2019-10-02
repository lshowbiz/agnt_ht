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
import com.joymain.jecs.fi.model.JfiChinapayPosLog;
import com.joymain.jecs.fi.service.JfiChinapayPosLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiChinapayPosLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiChinapayPosLogController.class);
    private JfiChinapayPosLogManager jfiChinapayPosLogManager = null;

    public void setJfiChinapayPosLogManager(JfiChinapayPosLogManager jfiChinapayPosLogManager) {
        this.jfiChinapayPosLogManager = jfiChinapayPosLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiChinapayPosLog jfiChinapayPosLog = new JfiChinapayPosLog();
        // populate object with request parameters
        BeanUtils.populate(jfiChinapayPosLog, request.getParameterMap());

	//List jfiChinapayPosLogs = jfiChinapayPosLogManager.getJfiChinapayPosLogs(jfiChinapayPosLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiChinapayPosLogListTable",request, 20);
        List jfiChinapayPosLogs = jfiChinapayPosLogManager.getJfiChinapayPosLogsByCrm(crm,pager);
        request.setAttribute("jfiChinapayPosLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiChinapayPosLogList", "jfiChinapayPosLogList", jfiChinapayPosLogs);
    }
}
