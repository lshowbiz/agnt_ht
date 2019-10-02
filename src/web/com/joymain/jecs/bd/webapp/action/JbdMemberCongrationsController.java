package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.JbdMemberCongrations;
import com.joymain.jecs.bd.service.JbdMemberCongrationsManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class JbdMemberCongrationsController extends BaseController implements Controller {
    private JbdMemberCongrationsManager jbdMemberCongrationsManager;

    public void setJbdMemberCongrationsManager(
            JbdMemberCongrationsManager jbdMemberCongrationsManager)
    {
        this.jbdMemberCongrationsManager = jbdMemberCongrationsManager;
    }



    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("JbdMemberCongrationsTable",request, 20);
        
        String userCode= crm.getString("userCode",request.getParameter("userCode"));
        String yearMonth= crm.getString("yearMonth",request.getParameter("yearMonth"));
        String starLevel= crm.getString("starLevel",request.getParameter("starLevel"));
        
        String strAction=request.getParameter("strAction");
        if ("deleteJbdMemberCongrations".equals(strAction)) {
            String id=request.getParameter("id");
            jbdMemberCongrationsManager.removeObject(JbdMemberCongrations.class, Long.valueOf(id));
            return new ModelAndView("redirect:jbdMemberCongrations.html?userCode="+userCode+"&yearMonth="+yearMonth+"&starLevel="+starLevel);
        }
        
        
        List jbdMemberCongrations=null;

        
    	if (!StringUtil.isEmpty(userCode)||StringUtil.isInteger(yearMonth)||!StringUtil.isEmpty(starLevel)){

    	    jbdMemberCongrations=jbdMemberCongrationsManager.getJbdMemberCongrationsByCrm(crm, pager);
			
		}
		request.setAttribute("JbdMemberCongrationsTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("bd/jbdMemberCongrations", "jbdMemberCongrations", jbdMemberCongrations);
			
		
    }
}
