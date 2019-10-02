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
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.bd.service.JbdTravelPointLog2013Manager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPointLog2013Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointLog2013Controller.class);
    private JbdTravelPointLog2013Manager jbdTravelPointLog2013Manager = null;

    public void setJbdTravelPointLog2013Manager(JbdTravelPointLog2013Manager jbdTravelPointLog2013Manager) {
        this.jbdTravelPointLog2013Manager = jbdTravelPointLog2013Manager;
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
        List jbdTravelPointLog2013s = null;
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPointLog2013ListTable",request, 20);

        if(!StringUtil.isEmpty(userCode)||StringUtil.isDate(startTime)||StringUtil.isDate(endTime)){
        	jbdTravelPointLog2013s = jbdTravelPointLog2013Manager.getJbdTravelPointLog2013sByCrm(crm,pager);
        }
        request.setAttribute("jbdTravelPointLog2013ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPointLog2013List", Constants.JBDTRAVELPOINTLOG2013_LIST, jbdTravelPointLog2013s);
    }
}
