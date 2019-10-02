package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdTravelPointLog2014;
import com.joymain.jecs.bd.service.JbdTravelPointLog2014Manager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPointLog2014Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointLog2014Controller.class);
    private JbdTravelPointLog2014Manager jbdTravelPointLog2014Manager = null;

    public void setJbdTravelPointLog2014Manager(JbdTravelPointLog2014Manager jbdTravelPointLog2014Manager) {
        this.jbdTravelPointLog2014Manager = jbdTravelPointLog2014Manager;
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
        List jbdTravelPointLog2014s = null;
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPointLog2014ListTable",request, 20);

        if(!StringUtil.isEmpty(userCode)||StringUtil.isDate(startTime)||StringUtil.isDate(endTime)){
        	jbdTravelPointLog2014s = jbdTravelPointLog2014Manager.getJbdTravelPointLog2014sByCrm(crm,pager);
        }
        request.setAttribute("jbdTravelPointLog2014ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPointLog2014List", Constants.JBDTRAVELPOINTLOG2014_LIST, jbdTravelPointLog2014s);
    }
}
