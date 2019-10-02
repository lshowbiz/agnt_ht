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
import com.joymain.jecs.am.service.InwDemandsortManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 创新共赢的活动   update   创新共赢的新需求---创新共赢的子需求
 * @author gw 2013-09-06  update 2013-11-12  
 * 
 *
 */
public class InwDemandActivityController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwDemandController.class);
    private InwDemandManager inwDemandManager = null;
    
    //创新共赢的需求分类表的的Manager
    private InwDemandsortManager inwDemandsortManager;
    

    public void setInwDemandManager(InwDemandManager inwDemandManager) {
        this.inwDemandManager = inwDemandManager;
    }

    //创新共赢的需求分类的inwDemandsortManager的set方法
	public void setInwDemandsortManager(InwDemandsortManager inwDemandsortManager) {
		this.inwDemandsortManager = inwDemandsortManager;
	}
    
	
	/**
	 * 创新共赢的新需求----创新共赢的子需求(原本是创新共赢的活动的,现在改了)
	 * @author gw 2013-11-12 
	 */
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
	  
    if("editDemandsActivity".equals(strAction)){
    	crm.setValue("issuerName", userCode);
    }
    
    if("checkDemandsActivity".equals(strAction)){
    	request.setAttribute("userName", sessionLogin.getUserName());
    }
    
        crm.setValue("other", "2");    
    
        Pager pager = new Pager("inwDemandActivityListTable",request, 20);
        
        
        String name=request.getParameter("name");
		String summary=request.getParameter("summary");
		
		String startCreateTime=request.getParameter("postTime");
		String endCreateTime=request.getParameter("postTimeEnd");
		
		String status=request.getParameter("verfity");
		
		String demandsortId=request.getParameter("demandsortId");
		
		//查创新 共赢的需求分类表的所有数据
		List demandsortList = inwDemandsortManager.getDemandsortList();
		request.setAttribute("demandsortList", demandsortList);
		
		//创新共赢的部门的权限录入或编辑时查询权限(需求)的方法-------begin
		if("selectInwDemandByInwDC".equals(strAction)){
			List inwDemands = inwDemandManager.getInwDemandsByCrm(crm,pager);
			request.setAttribute("inwDemandActivityListTable_totalRows", pager.getTotalObjects());
	        request.setAttribute("strAction", strAction);
	        request.setAttribute("demandsortId", demandsortId);
			return new ModelAndView("am/inwDemandActivitySelect", "inwDemandList", inwDemands);
		}
		//创新共赢的部门的权限录入或编辑时查询权限(需求)的方法-------end

		
		if(StringUtil.isEmpty(status)&&StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(name)&&StringUtil.isEmpty(summary)&&!StringUtil.isDate(startCreateTime)&&!StringUtil.isDate(endCreateTime)){
			request.setAttribute("bdBounsDeductActivityListTable_totalRows", pager.getTotalObjects());
			request.setAttribute("strAction", strAction);
			return new ModelAndView("am/inwDemandActivityList", "inwDemandList", null);

		}else{
			List inwDemands = inwDemandManager.getInwDemandsByCrm(crm,pager);
			request.setAttribute("inwDemandActivityListTable_totalRows", pager.getTotalObjects());
	        request.setAttribute("strAction", strAction);
	        request.setAttribute("demandsortId", demandsortId);
	        
			return new ModelAndView("am/inwDemandActivityList", "inwDemandList", inwDemands);
		}
        

        
    }
    
}
