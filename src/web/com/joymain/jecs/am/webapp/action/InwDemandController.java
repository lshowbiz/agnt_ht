package com.joymain.jecs.am.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.am.model.InwDemand;
import com.joymain.jecs.am.service.InwDemandManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class InwDemandController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwDemandController.class);
    private InwDemandManager inwDemandManager = null;

    public void setInwDemandManager(InwDemandManager inwDemandManager) {
        this.inwDemandManager = inwDemandManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        System.out.println(System.getProperty("java.endorsed.dirs"));
        InwDemand inwDemand = new InwDemand();
//        BeanUtils.populate(inwDemand, request.getParameterMap());
        String strAction = request.getParameter("strAction");
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String userCode = sessionLogin.getUserCode();
        
	CommonRecord crm=RequestUtil.toCommonRecord(request);
	  
    if("editDemands".equals(strAction)){
    	crm.setValue("issuerName", userCode);
    }
    
    if("checkDemands".equals(strAction)){
    	request.setAttribute("userName", sessionLogin.getUserName());
    }
    
    crm.setValue("other", "1");
    
        Pager pager = new Pager("inwDemandListTable",request, 20);
        
        
        String name=request.getParameter("name");
		String summary=request.getParameter("summary");
		
		String startCreateTime=request.getParameter("postTime");
		String endCreateTime=request.getParameter("postTimeEnd");
		
		String status=request.getParameter("verfity");
		if(StringUtil.isEmpty(status)&&StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(name)&&StringUtil.isEmpty(summary)&&!StringUtil.isDate(startCreateTime)&&!StringUtil.isDate(endCreateTime)){
			request.setAttribute("bdBounsDeductListTable_totalRows", pager.getTotalObjects());
			request.setAttribute("strAction", strAction);
			return new ModelAndView("am/inwDemandList", "inwDemandList", null);

		}else{
			List inwDemands = inwDemandManager.getInwDemandsByCrm(crm,pager);
			request.setAttribute("inwDemandListTable_totalRows", pager.getTotalObjects());
	        request.setAttribute("strAction", strAction);
	        
			return new ModelAndView("am/inwDemandList", "inwDemandList", inwDemands);
		}
    }
}
