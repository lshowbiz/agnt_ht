package com.joymain.jecs.am.webapp.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.service.InwDemandsortManager;
import com.joymain.jecs.am.service.InwSuggestionManager;
import com.joymain.jecs.am.service.InwViewpeopleManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwSuggestionController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwSuggestionController.class);
    private InwSuggestionManager inwSuggestionManager = null;

    //定义inwViewpeopleManager的对象,因为要调用该对象中的方法
    private InwViewpeopleManager inwViewpeopleManager;
    
    //定义需求分类的相关对象inwDemandsortManager
    private InwDemandsortManager inwDemandsortManager;
    
    public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
        this.inwSuggestionManager = inwSuggestionManager;
    }

   //给局部变量inwViewpeopleManager赋值
    public void setInwViewpeopleManager(InwViewpeopleManager inwViewpeopleManager){
    	this.inwViewpeopleManager = inwViewpeopleManager;
    }
    
	public void setInwDemandsortManager(InwDemandsortManager inwDemandsortManager) {
		this.inwDemandsortManager = inwDemandsortManager;
	}

	/**
     * 创新共赢-查看会员所提的意见---初始化或有条件查询的方法-----新的需求----修改
     * @author gw  2013-08-20   update   gw  2013-11-12
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
    	//-------因需求变更,这个方法弃用
    	if("lookSuggestion".equals(strAction)){
       
	        //获取查询的条件
	        //需求表的需求的名称
	        String name = request.getParameter("name");
	        //建议的建议人
	        String suggestionUserCode = request.getParameter("suggestionUserCode");
	        //建议主题
	        String subject = request.getParameter("subject");
	        //建议时间--开始
	        String createTimeBegin = request.getParameter("createTimeBegin");
	        //建议时间--结束
	        String createTimeEnd = request.getParameter("createTimeEnd");
	        //建议查看状态的查询情况 //0代表未阅，1代表已阅，2全部（已阅＋未阅）
	        String lookStatus = request.getParameter("viewstatus");
	        //查询状态为空,就表明是初始化查询,初始化查询默认让其查未阅的
	        if(StringUtil.isEmpty(lookStatus)){
	        	lookStatus = "0";
	        }
	        //获取CommonRecord的对象,该对象是用来装参数的
	        CommonRecord crm = RequestUtil.toCommonRecord(request);
	        //向CommonRecord中装map类型的数据
	        crm.setValue("userCode", userCode);
	        crm.setValue("name", name);
	        crm.setValue("suggestionUserCode", suggestionUserCode);
	        crm.setValue("subject", subject);
	        crm.setValue("createTimeBegin", createTimeBegin);
	        crm.setValue("createTimeEnd", createTimeEnd);
	        //1代表的是需求
	        crm.setValue("other", "1");
	        //创建分页的对象,该对象定义了每页默认显示的值和分页相关的表
	        Pager pager = new Pager("inwSuggestionListTable",request,20);
	        //执行查询的方法
	        List suggestionViewPeople = inwSuggestionManager.getSuggestionAndViewPeople(crm,pager,lookStatus);
	        request.setAttribute("strAction", strAction);
	        request.setAttribute("inwSuggestionListTable_totalRows", pager.getTotalObjects());
	        //向页面传递页面的查询数据的总条数
	        return new ModelAndView("am/inwSuggestionList", Constants.INWSUGGESTION_LIST, suggestionViewPeople);
    	}
    	//---------------------------------------------------------手机功能修改-------------------------开始--------------------------------------------------------
    	//手机功能建议查看-----------------------------------------------------------------------------------------------------
    	else if("phoneSuggestion".equals(strAction)){
    		//获取查询的条件
	        //需求表的需求的名称
	        String name = request.getParameter("name");
	        //建议的建议人
	        String suggestionUserCode = request.getParameter("suggestionUserCode");
	        //建议主题
	        String subject = request.getParameter("subject");
	        //建议时间--开始
	        String createTimeBegin = request.getParameter("createTimeBegin");
	        //建议时间--结束
	        String createTimeEnd = request.getParameter("createTimeEnd");
	        //建议查看状态的查询情况 //0代表未阅，1代表已阅，2全部（已阅＋未阅）
	        String lookStatus = request.getParameter("viewstatus");
	        //查询状态为空,就表明是初始化查询,初始化查询默认让其查未阅的
	        if(StringUtil.isEmpty(lookStatus)){
	        	lookStatus = "0";
	        }
	        //获取CommonRecord的对象,该对象是用来装参数的
	        CommonRecord crm = RequestUtil.toCommonRecord(request);
	        //向CommonRecord中装map类型的数据
	        crm.setValue("userCode", userCode);
	        crm.setValue("name", name);
	        crm.setValue("suggestionUserCode", suggestionUserCode);
	        crm.setValue("subject", subject);
	        crm.setValue("createTimeBegin", createTimeBegin);
	        crm.setValue("createTimeEnd", createTimeEnd);
	        //---------------------------------------------------------------------------------?
	        //-----------------------------------下面参数是区别手机提的建议.
	        crm.setValue("phone","phone");
	        
	        //创建分页的对象,该对象定义了每页默认显示的值和分页相关的表
	        Pager pager = new Pager("inwSuggestionListTable",request,20);
	        //执行查询的方法
	        List suggestionViewPeople = inwSuggestionManager.getSuggestionAndViewPeople(crm,pager,lookStatus);
	        request.setAttribute("strAction", strAction);
	        request.setAttribute("inwSuggestionListTable_totalRows", pager.getTotalObjects());
	        //向页面传递页面的查询数据的总条数
	        return new ModelAndView("am/inwSuggestionPhone", Constants.INWSUGGESTION_LIST, suggestionViewPeople);
    	}
    	//---------------------------------------------------------手机功能修改-------------------------结束--------------------------------------------------------

    	//查询明细(已阅)的查询方法---活动查看明细  或  需求查看明细
    	else if((!StringUtil.isEmpty(id))&& "suggestionQuery".equals(strAction)){
    		 inwSuggestion = inwSuggestionManager.getInwSuggestion(id);
    		 request.setAttribute("inwSuggestion", inwSuggestion);
    		 
    		 String demandId = inwSuggestion.getDemandId();
    		 request.getSession().setAttribute("demandId", demandId);
    		 
    		 String demandsortId = inwSuggestion.getDemandsortId();
    		 request.getSession().setAttribute("demandsortId", demandsortId);
    		 
    		 //获取标志,此标志区是活动查看,项目建议初次审核,项目建议可行性审核
    		 String differenceInw = request.getParameter("differenceInw");
    		 request.getSession().setAttribute("strAction", differenceInw);
    		 //手机建议有已阅和未阅的区别
    		 if("inwDemandPhone".equals(differenceInw)){
    			 //因为已阅--未阅--全部这个选项干掉了,那么这个就暂时用不到,先注释掉
    	    		
    	    		 //查询详细的同时,在inw_viewPeolple表中输入数据,表面当前用户已经查看了该条记录
    	    		 InwViewpeople inwViewpeople = new InwViewpeople();
    	    		 inwViewpeople.setSuggestionid(id);
    	    		 //先去数据库表inw_viewPeople中查,看该人对该条建议是否已阅
    	    		 List inwViewpeopleIsExist =  inwSuggestionManager.getInwViewpeopleIsExist(id,userCode);
    	    		 if(inwViewpeopleIsExist.size()>0){
    	    			 //表明当前登录用户已经查看了这条建议，因此就不在向inw_viewPeople中插入数据了
    	    		 }else{
    	    			//表明当前登录用户没有查看这条建议，因此就向inw_viewPeople中插入数据了
    				     //获取系统当前的登录时间
    		    		 Date date = new Date();
    		    		 inwViewpeople.setViewTime(date);
    		    		 inwViewpeople.setUserCode(userCode);
    		    		 //viewStatus字段暂时不用,作扩展字段,INW_VIEWPEOPLE该表里面有关于相关建议的信息,则表明该建议已阅;
    		    		 inwViewpeopleManager.saveInwViewpeople(inwViewpeople);
    	    		 }
    			 return new ModelAndView("am/inwSuggestionFormPhone");
    		 }
    		 //其他类型 的建议就没有已阅和未阅的区别了
    		 else{
    		      return new ModelAndView("am/inwSuggestionForm");
    		 }
    	}
    	//活动查看-------------------------------所有建议的查看及相关审核查看---------------新需求----------update 2013-11-13
    	else{
    		//获取查询的条件
    		//建议查看状态的查询情况 //0代表未阅，1代表已阅，2全部（已阅＋未阅）
	        String lookStatus = request.getParameter("viewstatus");
	        //查询状态为空,就表明是初始化查询,初始化查询默认让其查未阅的
	        if(StringUtil.isEmpty(lookStatus)){
	        	lookStatus = "0";
	        }
	        //获取CommonRecord的对象,该对象是用来装参数的
	        CommonRecord crm = RequestUtil.toCommonRecord(request);
	        //向CommonRecord中装map类型的数据
	        crm.setValue("userCode", userCode);
	       	//2代表的是活动
	        crm.setValue("other", "2");
	        
	        //获取建议(活动等所有类型的建议)的查看级别---最低级别,建议初始审核,建议可行性审核
	        crm.setValue("strAction", strAction);
	        
	        //创建分页的对象,该对象定义了每页默认显示的值和分页相关的表
	        Pager pager = new Pager("inwSuggestionListTable",request,20);
	        
	        if("activitySuggestionRestore".equals(strAction)){
	        	crm.setValue("feasibilityAudit", "Y");
	        }	        
	        //执行查询的方法
	        List suggestionViewPeople = inwSuggestionManager.getSuggestionAndViewPeople(crm,pager,lookStatus);
	        request.setAttribute("strAction", strAction);
	        request.setAttribute("inwSuggestionListTable_totalRows", pager.getTotalObjects());
	        //向页面传递页面的查询数据的总条数
	        
	        //查询需求分类的所有分类信息
	        List demandsortList = inwDemandsortManager.getDemandsortList();
	        request.setAttribute("demandsortList", demandsortList);
	        
	        String demandsortId = request.getParameter("demandsortId");
	        request.setAttribute("demandsortId", demandsortId);
	        return new ModelAndView("am/inwSuggestionActivityList", Constants.INWSUGGESTION_LIST, suggestionViewPeople);
    	}
    }
}
