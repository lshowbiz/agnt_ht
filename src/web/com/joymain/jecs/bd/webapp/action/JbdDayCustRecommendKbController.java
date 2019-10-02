package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.JbdDayCustRecommendKb;
import com.joymain.jecs.bd.service.JbdDayCustRecommendKbManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class JbdDayCustRecommendKbController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdDayCustRecommendKbController.class);
    private JbdDayCustRecommendKbManager jbdDayCustRecommendKbManager = null;

    public void setJbdDayCustRecommendKbManager(JbdDayCustRecommendKbManager jbdDayCustRecommendKbManager) {
        this.jbdDayCustRecommendKbManager = jbdDayCustRecommendKbManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


	//List jbdDayCustRecommendKbs = jbdDayCustRecommendKbManager.getJbdDayCustRecommendKbs(jbdDayCustRecommendKb);
	/**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        String userCode = request.getParameter("userCode");
        String startwweek = request.getParameter("startwweek");
        String endwweek = request.getParameter("endwweek");
        crm.addField("userCode", userCode);
        crm.addField("startwweek", startwweek);
        crm.addField("endwweek", endwweek);
        
        Pager pager = new Pager("jbdDayCustRecommendKbListTable",request, 20);
        List jbdDayCustRecommendKbs = jbdDayCustRecommendKbManager.getJbdDayCustRecommendKbsByCrm(crm,pager);
        request.setAttribute("jbdDayCustRecommendKbListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdDayCustRecommendKbList", "jbdDayCustRecommendKbList", jbdDayCustRecommendKbs);
    }
    
    
}
