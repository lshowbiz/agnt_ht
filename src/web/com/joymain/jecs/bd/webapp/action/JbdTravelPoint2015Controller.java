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
import com.joymain.jecs.bd.service.JbdTravelPoint2015Manager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPoint2015Controller extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPoint2015Controller.class);
    private JbdTravelPoint2015Manager jbdTravelPoint2015Manager = null;

    public void setJbdTravelPoint2015Manager(JbdTravelPoint2015Manager jbdTravelPoint2015Manager) {
        this.jbdTravelPoint2015Manager = jbdTravelPoint2015Manager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPoint2015ListTable",request, 20);
        


        String userCode=request.getParameter("userCode");
        
        List jbdTravelPoint2015s = null;
        if(!StringUtil.isEmpty(userCode) ){
        	jbdTravelPoint2015s = jbdTravelPoint2015Manager.getJbdTravelPoint2015sByCrm(crm,pager);
        }
        
        
        request.setAttribute("jbdTravelPoint2015ListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPoint2015List", "jbdTravelPoint2015List", jbdTravelPoint2015s);
    }
}
