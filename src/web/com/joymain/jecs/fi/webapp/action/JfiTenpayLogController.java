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
import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.fi.service.JfiTenpayLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiTenpayLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiTenpayLogController.class);
    private JfiTenpayLogManager jfiTenpayLogManager = null;

    public void setJfiTenpayLogManager(JfiTenpayLogManager jfiTenpayLogManager) {
        this.jfiTenpayLogManager = jfiTenpayLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiTenpayLogListTable",request, 20);
        List jfiTenpayLogs = jfiTenpayLogManager.getJfiTenpayLogsByCrm(crm,pager);
        request.setAttribute("jfiTenpayLogListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("fi/jfiTenpayLogList", Constants.JFITENPAYLOG_LIST, jfiTenpayLogs);
    }
}
