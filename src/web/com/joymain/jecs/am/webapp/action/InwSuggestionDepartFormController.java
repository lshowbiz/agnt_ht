package com.joymain.jecs.am.webapp.action;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.am.model.InwIntegration;
import com.joymain.jecs.am.model.InwSuggestion;
import com.joymain.jecs.am.service.InwDemandsortManager;
import com.joymain.jecs.am.service.InwIntegrationManager;
import com.joymain.jecs.am.service.InwIntegrationTotalManager;
import com.joymain.jecs.am.service.InwSuggestionManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class InwSuggestionDepartFormController extends BaseFormController {
    private InwSuggestionManager inwSuggestionManager = null;
    
    private InwIntegrationTotalManager inwIntegrationTotalManager;
       
    
    //定义inwIntegrationManager的对象,因为要调用该对象中的方法
    private InwIntegrationManager inwIntegrationManager;
    
    //定义创新共赢分类的大类的inwDemandsortManager
    private InwDemandsortManager inwDemandsortManager;
    
    //定义一个全局变量   用来区分是活动,还是需求
    //private String distinguish;

    public void setInwSuggestionManager(InwSuggestionManager inwSuggestionManager) {
        this.inwSuggestionManager = inwSuggestionManager;
    }
    
    /**
	 *  给变量inwIntegrationTotalManager赋值
	 */
    public void setInwIntegrationTotalManager(
			InwIntegrationTotalManager inwIntegrationTotalManager) {
		this.inwIntegrationTotalManager = inwIntegrationTotalManager;
	}

	/**
	 *  给变量inwIntegrationManager赋值
	 */
	public void setInwIntegrationManager(InwIntegrationManager inwIntegrationManager) {
		this.inwIntegrationManager = inwIntegrationManager;
	}
		
	/**
	 *  给变量inwDemandsortManager赋值
	 */
	public void setInwDemandsortManager(InwDemandsortManager inwDemandsortManager) {
		this.inwDemandsortManager = inwDemandsortManager;
	}

	
	public InwSuggestionDepartFormController() {
        setCommandName("inwSuggestion");
        setCommandClass(InwSuggestion.class);
    }
    
    /**
     * 创新共赢的建议-回复建议的初始化查询方法------------新需求
     * @author gw 2013-08-21    update  2013-11-14 gw
     * @param request
     * @return  object
     */
    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	//获取当前登录用户的信息对象
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	//获取当前登录用户的编号(登录账号)
    	String userCode = sessionLogin.getUserCode();
    	InwSuggestion inwSuggestion = null;
    	String id = request.getParameter("id");
    	String strAction = request.getParameter("strAction");
    	String departmentId = request.getParameter("departmentId");
    	
    	//建议回复的查询方法
    	//suggestionReply 表面是建议回复,suggestionContentAudit表面是对建议的内容进行审核
    	if((!StringUtil.isEmpty(id))&& ( "suggestionReplyDepart".equals(strAction)||"suggestionContentAuditDepart".equals(strAction))){
    		 //获得该条建议的实体对象
    		 inwSuggestion = inwSuggestionManager.getInwSuggestion(id);
    		 String demandId = inwSuggestion.getDemandId();
    		 request.getSession().setAttribute("demandId", demandId);
    		 
    		 String demandsortId = inwSuggestion.getDemandsortId();
    		 request.getSession().setAttribute("demandsortId", demandsortId);

    		 
    		//查询出所有的创新的需求分类的大类-------update 20140625 yxzz begin
   		     List demandSortList = inwDemandsortManager.getDemandsortList();
    		 request.setAttribute("demandSortList", demandSortList);
     		//查询出所有的创新的需求分类的大类-------update 20140625 yxzz end    		 
    		 
    	}
    	else{
    		 inwSuggestion = new InwSuggestion();
    	}
    	String differenceInwDepart = request.getParameter("differenceInwDepart");
		request.getSession().setAttribute("differenceInwDepart",differenceInwDepart);
		
		//strAction用来区别是编辑内容,还是对建议回复的内容进行审核的
		request.setAttribute("strAction", strAction);
		request.setAttribute("departmentId", departmentId);

		
    	return inwSuggestion;
    }

    /**
     * 创新共赢的回复建议或者建议被采纳后向创新共赢的创新积分表中添加积分-------新需求
     * @author gw 2013-09-05   update 2013-11-14
     * @param request
     * @param response
     * @param command
     * @param errors
     */
    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        String userCodeOne = defSysUser.getUserCode();
        InwSuggestion inwSuggestion = (InwSuggestion) command;
        
        String departmentId = request.getParameter("departmentId");
      
	    //保存之前,先对需求分类和需求名称不为空的校验
	    boolean isEmptyContent = inwSuggestionManager.getReplyContent(inwSuggestion,errors,defSysUser.getDefCharacterCoding());
	    if(isEmptyContent){
        	return showForm(request,response,errors);
	    }
	    String differenceInwDepart = (String) request.getSession().getAttribute("differenceInwDepart");
	    String strAction = request.getParameter("strAction");
	   
			    //审核-----建议分流前的审核  或者   建议内容的多次审核		   
			    if("suggestionContentAuditDepart".equals(strAction)){			    	
				    	   if((!StringUtil.isEmpty(inwSuggestion.getFirstReplyAudit()))&&(StringUtil.isEmpty(inwSuggestion.getFirseReplyPerson()))){
				    		       inwSuggestion.setFirseReplyPerson(userCodeOne);
				    		       inwSuggestion.setFirstReplyTime(new Date());
								    inwSuggestion.setReplyYesNo("Y");
				    	   }else if((!StringUtil.isEmpty(inwSuggestion.getSecondReplyAudit()))&&(StringUtil.isEmpty(inwSuggestion.getSecondReplyPerson()))){
				    		       inwSuggestion.setSecondReplyPerson(userCodeOne);
				    		       inwSuggestion.setSecondReplyTime(new Date());
								    inwSuggestion.setReplyYesNo("Y");
				    	   }else if((!StringUtil.isEmpty(inwSuggestion.getThirdReplyAudit()))&&(StringUtil.isEmpty(inwSuggestion.getThirdReplyPerson()))){
				    		       inwSuggestion.setThirdReplyPerson(userCodeOne);
				    		       inwSuggestion.setThirdReplyTime(new Date());
								    inwSuggestion.setReplyYesNo("Y");
				    	   }
				    	//给于提醒的标志(表面职能部门已经对这条建议进行了相关的处理)
				    	inwSuggestion.setUpdateFlag("Y");
				    	
				    	inwSuggestionManager.saveInwSuggestion(inwSuggestion);
						MessageUtil.saveLocaleMessage(request, "审核成功");
			    }
			    //编辑
			    else{
					            String status = inwSuggestion.getStatus();
							    Date date = new Date();
							    //update gw 2014-05-13  begin
							    //给第一次建议回复的回复人,回复时间赋值
							    if(StringUtil.isEmpty(inwSuggestion.getReplyUserCode())){
								    inwSuggestion.setReplyTime(date);
								    inwSuggestion.setReplyUserCode(defSysUser.getUserCode());
								    inwSuggestion.setReplyYesNo("Y");
							    }
							    //给第二次建议回复的回复人,回复时间赋值
							    if(!StringUtil.isEmpty(inwSuggestion.getReplyContentSecond())){
							    	 if(StringUtil.isEmpty(inwSuggestion.getReplyUserCodeSecond())){
										    inwSuggestion.setReplyUserCodeSecond(defSysUser.getUserCode());
										    inwSuggestion.setReplyTimeSecond(date);
										    inwSuggestion.setReplyYesNo("Y");
							    	 }
							    }
							    //给第三次建议回复的回复人,回复时间赋值
							    if(!StringUtil.isEmpty(inwSuggestion.getReplyContentThird())){
							    	 if(StringUtil.isEmpty(inwSuggestion.getReplyUserCodeThird())){
										    inwSuggestion.setReplyUserCodeThird(defSysUser.getUserCode());
										    inwSuggestion.setReplyTimeThird(date);
										    inwSuggestion.setReplyYesNo("Y");
							    	 }
							    }
							    
							  //给于提醒的标志(表面职能部门已经对这条建议进行了相关的处理)
						    	inwSuggestion.setUpdateFlag("Y");
							    //update gw 2014-05-13  end
							    
								inwSuggestionManager.saveInwSuggestion(inwSuggestion);
								MessageUtil.saveLocaleMessage(request, "更新成功");
					   	    
					            //-------------------------创新积分明细-------------------------------------begin update by yxzz 2014-06-05
								
					            //3代表建议已采纳,已采纳的建议将给予用户创新积分的奖励
					            //-----------------------创新积分的录入(无论建议是否采纳,都有可能被赋予创新积分-----update yxzz 2014-06-05
								InwIntegration inwIntegration = new InwIntegration();
						    	String userCode = inwSuggestion.getUserCode();
						    	inwIntegration.setSuggestionUserCode(userCode);
						    	
						    	inwIntegration.setIntegrationAddTime(date);
						    	String id = inwSuggestion.getId().toString();
						    	//在增加创新积分前,先去数据库中查询.如果该条建议已经为会员增加了创新积分,那么不再为该会员添加创新积分
						    	InwIntegration  inwInte = inwIntegrationManager.getInwIntegrationByParam(userCode,id);
						    	//这条建议之前没有录入创新积分
						    	if(null==inwInte){
						    		String intreg = inwSuggestion.getIntegration();
						    		if(!StringUtil.isEmpty(intreg)){
						    			//创新共赢新的需求---创新积分让后台操作人员手动输入----update  gw  2014-05-07
								    	inwIntegration.setIntegrationAdd(inwSuggestion.getIntegration());
						    			inwIntegration.setSuggestionid(id);
								    	inwIntegrationManager.saveInwIntegration(inwIntegration);
								    	//在录入创新积分明细的同时,修改创新总积分
								    	inwIntegrationTotalManager.updateOrSaveInwIntegrationTotal(userCode,Integer.parseInt(intreg));
						    		}
						    	}
						    	//这条建议之前录入了创新积分
						    	else{
						    		    //数据库中原来这条建议的创新积分
						    		    String a = inwInte.getIntegrationAdd();
						    		    //现在这条建议新的创新积分
						    		    String b = inwSuggestion.getIntegration();
						    		    //如果创新积分发生了变化
						    		    if((!(a.equals(b)))&& (!StringUtil.isEmpty(b))){
						    		    	//更改本条建议的创新积分
						    		    	inwInte.setIntegrationAdd(inwSuggestion.getIntegration());
						    		    	inwIntegrationManager.saveInwIntegration(inwInte);
						    		    	
						    		    	int c = Integer.parseInt(b)-Integer.parseInt(a);
						    		    	inwIntegrationTotalManager.updateOrSaveInwIntegrationTotal(userCode,c);
						    		    }
						    		    
						    	}
			      }
	        
	   //-----------------------------创新积分明细------------------------------------end   update by yxzz 2014-06-05
	    
	        //新需求
		    //活动查看菜单修改的修改保存方法后的返回页面
		    if("activitySuggestionDepart".equals(differenceInwDepart)){
		    	return new ModelAndView("redirect:inwSuggestionsDepart.html?strAction=activitySuggestionDepart&departmentId="+departmentId);
		    }
		    //项目建议初次审核菜单的修改保存方法后的返回页面
		    else if("initialAuditDepart".equals(differenceInwDepart)){
		    	return new ModelAndView("redirect:inwSuggestionsDepart.html?strAction=initialAuditDepart&departmentId="+departmentId);
		    }
		    //项目建议可行性审核菜单的修改保存后的返回页面
		    else{
		    	return new ModelAndView("redirect:inwSuggestionsDepart.html?strAction=feasibilityAuditDepart&departmentId="+departmentId);
		    }
        
    }
    
    
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
