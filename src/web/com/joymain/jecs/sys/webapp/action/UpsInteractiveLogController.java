package com.joymain.jecs.sys.webapp.action;

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
import com.joymain.jecs.sys.model.UpsInteractiveLog;
import com.joymain.jecs.sys.service.UpsInteractiveLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class UpsInteractiveLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(UpsInteractiveLogController.class);
    private UpsInteractiveLogManager upsInteractiveLogManager = null;

    public void setUpsInteractiveLogManager(UpsInteractiveLogManager upsInteractiveLogManager) {
        this.upsInteractiveLogManager = upsInteractiveLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        UpsInteractiveLog upsInteractiveLog = new UpsInteractiveLog();
        // populate object with request parameters
        BeanUtils.populate(upsInteractiveLog, request.getParameterMap());

	//List upsInteractiveLogs = upsInteractiveLogManager.getUpsInteractiveLogs(upsInteractiveLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("upsInteractiveLogListTable",request, 20);
        List upsInteractiveLogs = upsInteractiveLogManager.getUpsInteractiveLogsByCrm(crm,pager);
        request.setAttribute("upsInteractiveLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("sys/upsInteractiveLogList", Constants.UPSINTERACTIVELOG_LIST, upsInteractiveLogs);
    }
}
