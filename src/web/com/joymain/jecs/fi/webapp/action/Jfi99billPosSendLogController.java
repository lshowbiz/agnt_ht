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
import com.joymain.jecs.fi.model.Jfi99billPosSendLog;
import com.joymain.jecs.fi.service.Jfi99billPosSendLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Jfi99billPosSendLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(Jfi99billPosSendLogController.class);
    private Jfi99billPosSendLogManager jfi99billPosSendLogManager = null;

    public void setJfi99billPosSendLogManager(Jfi99billPosSendLogManager jfi99billPosSendLogManager) {
        this.jfi99billPosSendLogManager = jfi99billPosSendLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        Jfi99billPosSendLog jfi99billPosSendLog = new Jfi99billPosSendLog();
        // populate object with request parameters
        BeanUtils.populate(jfi99billPosSendLog, request.getParameterMap());

	//List jfi99billPosSendLogs = jfi99billPosSendLogManager.getJfi99billPosSendLogs(jfi99billPosSendLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfi99billPosSendLogListTable",request, 20);
        List jfi99billPosSendLogs = jfi99billPosSendLogManager.getJfi99billPosSendLogsByCrm(crm,pager);
        request.setAttribute("jfi99billPosSendLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfi99billPosSendLogList", Constants.JFI99BILLPOSSENDLOG_LIST, jfi99billPosSendLogs);
    }
}
