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
import com.joymain.jecs.bd.service.JbdTravelPoint2014Manager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPoint2014Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPoint2014Controller.class);
    private JbdTravelPoint2014Manager jbdTravelPoint2014Manager = null;

    public void setJbdTravelPoint2014Manager(JbdTravelPoint2014Manager jbdTravelPoint2014Manager) {
        this.jbdTravelPoint2014Manager = jbdTravelPoint2014Manager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPoint2014ListTable",request, 20);
        


        String userCode=request.getParameter("userCode");
        
        List jbdTravelPoint2014s = null;
        if(!StringUtil.isEmpty(userCode) ){
        	jbdTravelPoint2014s = jbdTravelPoint2014Manager.getJbdTravelPoint2014sByCrm(crm,pager);
        }
        
        
        request.setAttribute("jbdTravelPoint2014ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPoint2014List", Constants.JBDTRAVELPOINT2014_LIST, jbdTravelPoint2014s);
    }
}
