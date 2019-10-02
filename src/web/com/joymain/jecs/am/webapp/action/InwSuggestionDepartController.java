package com.joymain.jecs.am.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.model.InwSuggestion;
import com.joymain.jecs.am.service.InwDemandsortManager;
import com.joymain.jecs.am.service.InwDepartCompetenceManager;
import com.joymain.jecs.am.service.InwDepartPersonManager;
import com.joymain.jecs.am.service.InwSuggestionManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwSuggestionDepartController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwSuggestionController.class);
    private InwSuggestionManager inwSuggestionManager = null;

    //定义需求分类的相关对象inwDemandsortManager
    private InwDemandsortManager inwDemandsortManager;
    
    //定义部门权限的对象inwDepartCompetenceManager
    private InwDepartCompetenceManager inwDepartCompetenceManager;
    
    private InwDepartPersonManager inwDepartPersonManager;
    
    public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
        this.inwSuggestionManager = inwSuggestionManager;
    }
   
	public void setInwDemandsortManager(InwDemandsortManager inwDemandsortManager) {
		this.inwDemandsortManager = inwDemandsortManager;
	}
	
	public void setInwDepartCompetenceManager(InwDepartCompetenceManager inwDepartCompetenceManager) {
		this.inwDepartCompetenceManager = inwDepartCompetenceManager;
	}

	public void setInwDepartPersonManager(InwDepartPersonManager inwDepartPersonManager) {
		this.inwDepartPersonManager = inwDepartPersonManager;
	}

	/**
     * 创新共赢-具体的业务部门查看建议
     * @author gw  2014-07-01
     * @param request
     * @param response
     * @return  modelAndView
     */
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
    	//日志文件
    	if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
    	 //获取当前登录用户的信息
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        //获取当前登录用户的编号
        String userCode = sessionLogin.getUserCode();
    	String strAction = request.getParameter("strAction");
    	InwSuggestion inwSuggestion = null;
    	String id = request.getParameter("id");   	
    	//部门建议的查询明细的查询方法
        if((!StringUtil.isEmpty(id))&& "suggestionQueryDepart".equals(strAction)){
    		 inwSuggestion = inwSuggestionManager.getInwSuggestion(id);
    		 request.setAttribute("inwSuggestion", inwSuggestion);
    		 
    		 String demandId = inwSuggestion.getDemandId();
    		 request.getSession().setAttribute("demandId", demandId);
    		 
    		 String demandsortId = inwSuggestion.getDemandsortId();
    		 request.getSession().setAttribute("demandsortId", demandsortId);
    		 
    		 //获取标志,此标志区是活动查看,项目建议初次审核,项目建议可行性审核
    		 String differenceInwDepart = request.getParameter("differenceInwDepart");
    		 request.getSession().setAttribute("strAction", differenceInwDepart);   		
    		 return new ModelAndView("am/inwSuggestionForm");
    		
    	}
    	//活动查看-------------------------------所有建议的查看及相关审核查看---------------新需求----------update 2013-11-13
    	else{
    		//获取查询的条件
	        //获取CommonRecord的对象,该对象是用来装参数的
	        CommonRecord crm = RequestUtil.toCommonRecord(request);
	        //向CommonRecord中装map类型的数据
	        crm.setValue("userCode", userCode);
	       	//2代表的是活动
	        crm.setValue("other", "2");
	        
	        //获取建议(活动等所有类型的建议)的查看级别---最低级别,建议初始审核,建议可行性审核
	        crm.setValue("strAction", strAction);
	        
	        //----------------------------update  2014-07-01  begin
	        //获取部门的ID
	        String userCodeHD = sessionLogin.getUserCode();
	        String departmentId = inwDepartPersonManager.getDepartmentId(userCodeHD); 
	        request.setAttribute("departmentId", departmentId); 
	        if(!StringUtil.isEmpty(departmentId)){
		        //查询出该部门的查看建议的权限名称集合(权限名称就是对应的需求名称集合)
		        String demandIdList = inwDepartCompetenceManager.getDemandIdListByDepartmentId(departmentId);
	            if(!StringUtil.isEmpty(demandIdList)){
	            	String demandIdListTwo = demandIdList.substring(0, demandIdList.length()-1);
	            	crm.setValue("demandIdListTwo", demandIdListTwo);
	            }
	        }
	        //---------------------------update 2014-07-01    end 
	        
	        
	        //创建分页的对象,该对象定义了每页默认显示的值和分页相关的表
	        Pager pager = new Pager("inwSuggestionListTable",request,20);
        
	        //执行查询的方法
	        List suggestionViewPeople = inwSuggestionManager.getSuggestionForDepartment(crm,pager);
	        
	        
	        request.setAttribute("strAction", strAction);
	        request.setAttribute("inwSuggestionListTable_totalRows", pager.getTotalObjects());
	        //向页面传递页面的查询数据的总条数
	        
	        //查询需求分类的所有分类信息
	        List demandsortList = inwDemandsortManager.getDemandsortList();
	        request.setAttribute("demandsortList", demandsortList);
	        
	        String demandsortId = request.getParameter("demandsortId");
	        request.setAttribute("demandsortId", demandsortId);
	        return new ModelAndView("am/inwSuggestionDepartList", Constants.INWSUGGESTION_LIST, suggestionViewPeople);
    	}
        
    }
    
}
