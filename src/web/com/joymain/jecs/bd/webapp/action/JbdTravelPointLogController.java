package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.JbdTravelPointLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPointLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointLogController.class);
    private JbdTravelPointLogManager jbdTravelPointLogManager = null;

    public void setJbdTravelPointLogManager(JbdTravelPointLogManager jbdTravelPointLogManager) {
        this.jbdTravelPointLogManager = jbdTravelPointLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        String userCode=request.getParameter("userCode");
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        
        List jbdTravelPointLogs = null;
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPointLogListTable",request, 20);
        
        
        
        if(!StringUtil.isEmpty(userCode)||StringUtil.isDate(startTime)||StringUtil.isDate(endTime)){
            jbdTravelPointLogs = jbdTravelPointLogManager.getJbdTravelPointLogsByCrm(crm,pager);
        }
        request.setAttribute("jbdTravelPointLogListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdTravelPointLogList", Constants.JBDTRAVELPOINTLOG_LIST, jbdTravelPointLogs);
    }
}
