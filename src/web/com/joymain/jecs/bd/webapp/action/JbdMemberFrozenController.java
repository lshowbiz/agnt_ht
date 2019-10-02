package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.service.JbdMemberFrozenManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;


public class JbdMemberFrozenController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdMemberFrozenController.class);
    
    private JbdMemberFrozenManager jbdMemberFrozenManager = null;
	public void setJbdMemberFrozenManager(JbdMemberFrozenManager jbdMemberFrozenManager) {
		this.jbdMemberFrozenManager = jbdMemberFrozenManager;
	}

    public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String strAction = request.getParameter("strAction");
        
        if("delJbdMemberFrozen".equals(strAction)){
        	String[] userCodes = request.getParameterValues("selectedId");
        	jbdMemberFrozenManager.deleteJbdMemberFrozen(userCodes);
        }
        
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdMemberFrozenListTable",request, 20);
        List jbdMemberFrozenList = jbdMemberFrozenManager.getJbdMemberFrozenByCrm(crm,pager);
        
        request.setAttribute("jbdMemberFrozenListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("bd/jbdMemberFrozenList", "jbdMemberFrozenList", jbdMemberFrozenList);
    }
}
