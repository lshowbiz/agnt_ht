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
import com.joymain.jecs.mi.model.JmiStateLog;
import com.joymain.jecs.mi.service.JmiStateLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiStateLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiStateLogController.class);
    private JmiStateLogManager jmiStateLogManager = null;

    public void setJmiStateLogManager(JmiStateLogManager jmiStateLogManager) {
        this.jmiStateLogManager = jmiStateLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiStateLog jmiStateLog = new JmiStateLog();
        // populate object with request parameters
        BeanUtils.populate(jmiStateLog, request.getParameterMap());

	//List jmiStateLogs = jmiStateLogManager.getJmiStateLogs(jmiStateLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiStateLogListTable",request, 20);
        List jmiStateLogs = jmiStateLogManager.getJmiStateLogsByCrm(crm,pager);
        request.setAttribute("jmiStateLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiStateLogList", "jmiStateLogList", jmiStateLogs);
    }
}
