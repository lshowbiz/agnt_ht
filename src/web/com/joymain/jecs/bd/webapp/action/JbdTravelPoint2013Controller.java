package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.JbdTravelPoint2013Manager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPoint2013Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPoint2013Controller.class);
    private JbdTravelPoint2013Manager jbdTravelPoint2013Manager = null;

    public void setJbdTravelPoint2013Manager(JbdTravelPoint2013Manager jbdTravelPoint2013Manager) {
        this.jbdTravelPoint2013Manager = jbdTravelPoint2013Manager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPoint2013ListTable",request, 20);
        


        String userCode=request.getParameter("userCode");
        
        List jbdTravelPoint2013s = null;
        if(!StringUtil.isEmpty(userCode) ){
        	jbdTravelPoint2013s = jbdTravelPoint2013Manager.getJbdTravelPoint2013sByCrm(crm,pager);
        }
        
        
        request.setAttribute("jbdTravelPoint2013ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPoint2013List", Constants.JBDTRAVELPOINT2013_LIST, jbdTravelPoint2013s);
    }
}
