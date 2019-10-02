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
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.fi.service.JfiUsCreditCardLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiUsCreditCardLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiUsCreditCardLogController.class);
    private JfiUsCreditCardLogManager jfiUsCreditCardLogManager = null;

    public void setJfiUsCreditCardLogManager(JfiUsCreditCardLogManager jfiUsCreditCardLogManager) {
        this.jfiUsCreditCardLogManager = jfiUsCreditCardLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiUsCreditCardLogListTable",request, 20);
        List jfiUsCreditCardLogs = jfiUsCreditCardLogManager.getJfiUsCreditCardLogsByCrm(crm,pager);
        request.setAttribute("jfiUsCreditCardLogListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("fi/jfiUsCreditCardLogList", Constants.JFIUSCREDITCARDLOG_LIST, jfiUsCreditCardLogs);
    }
}
