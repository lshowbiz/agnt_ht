package com.joymain.jecs.sms.webapp.action;

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
import com.joymain.jecs.sms.model.SmsSendLog;
import com.joymain.jecs.sms.service.SmsSendLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SmsSendLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SmsSendLogController.class);
    private SmsSendLogManager smsSendLogManager = null;

    public void setSmsSendLogManager(SmsSendLogManager smsSendLogManager) {
        this.smsSendLogManager = smsSendLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SmsSendLog smsSendLog = new SmsSendLog();
        // populate object with request parameters
        BeanUtils.populate(smsSendLog, request.getParameterMap());

	//List smsSendLogs = smsSendLogManager.getSmsSendLogs(smsSendLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("smsSendLogListTable",request, 20);
        List smsSendLogs = smsSendLogManager.getSmsSendLogsByCrm(crm,pager);
        request.setAttribute("smsSendLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sms/smsSendLogList", Constants.SMSSENDLOG_LIST, smsSendLogs);
    }
}
