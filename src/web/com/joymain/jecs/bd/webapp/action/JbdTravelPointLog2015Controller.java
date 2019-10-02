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
import com.joymain.jecs.bd.model.JbdTravelPointLog2015;
import com.joymain.jecs.bd.service.JbdTravelPointLog2015Manager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPointLog2015Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointLog2015Controller.class);
    private JbdTravelPointLog2015Manager jbdTravelPointLog2015Manager = null;

    public void setJbdTravelPointLog2015Manager(JbdTravelPointLog2015Manager jbdTravelPointLog2015Manager) {
        this.jbdTravelPointLog2015Manager = jbdTravelPointLog2015Manager;
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
        List jbdTravelPointLog2015s = null;
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPointLog2015ListTable",request, 20);

        if(!StringUtil.isEmpty(userCode)||StringUtil.isDate(startTime)||StringUtil.isDate(endTime)){
        	jbdTravelPointLog2015s = jbdTravelPointLog2015Manager.getJbdTravelPointLog2015sByCrm(crm,pager);
        }
        request.setAttribute("jbdTravelPointLog2015ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPointLog2015List", "jbdTravelPointLog2015List", jbdTravelPointLog2015s);
    }
}
