package com.joymain.jecs.am.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.am.model.InwProblem;
import com.joymain.jecs.am.service.InwProblemManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class InwProblemFormController extends BaseFormController {
    private InwProblemManager inwProblemManager = null;

    public void setInwProblemManager(InwProblemManager inwProblemManager) {
        this.inwProblemManager = inwProblemManager;
    }
    public InwProblemFormController() {
        setCommandName("inwProblem");
        setCommandClass(InwProblem.class);
    }

    /**
     * 创新共赢的共赢帮助的修改初始化查询
     * @author gw 2013-08-26
     * @param  request
     */
    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        InwProblem inwProblem = null;
        //编辑之前的查询
        if (!StringUtils.isEmpty(id)) {
            inwProblem = inwProblemManager.getInwProblem(id);
        } 
        //新增初始化
        else {
            inwProblem = new InwProblem();
        }

        return inwProblem;
    }

    /**
     * 创新共赢的共赢帮助的提交表单-修改或者删除
     * @author gw 2013-08-26
     * @param request
     * @param response
     * @param command
     * @param errors
     * @return ModelAndView
     */
    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        InwProblem inwProblem = (InwProblem) command;
		String key=null;
		String strAction = request.getParameter("strAction");
		//删除操作
		if ("deleteInwProblem".equals(strAction)  ) {
			inwProblemManager.removeInwProblem(inwProblem.getId().toString());
			return null;
		}
		//录入操作或者保存操作
		else if("addInwProblem".equals(strAction)){
			     key = "inwProblem.add";
			     //在录入之前,先做不为空的校验
			     boolean emptyCheck = inwProblemManager.getEmptyCheck(inwProblem,errors,sessionLogin.getDefCharacterCoding());
			     if(emptyCheck){
			    	 return showForm(request, response, errors);
			     }else{
					 inwProblemManager.saveInwProblem(inwProblem);
					 //给出友好提示,表面提交成功(新增成功)
					 saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(),key));
					 return new ModelAndView("redirect:inwProblems.html?strAction=queryInwProblem");
			     }
		}
		//修改操作
		else{
			//页面有隐藏字段 "id"
			//修改之前也做不为空的校验
			 boolean emptyCheck = inwProblemManager.getEmptyCheck(inwProblem,errors,sessionLogin.getDefCharacterCoding());
		     if(emptyCheck){
		    	    return showForm(request, response, errors);
		     }else{
					inwProblemManager.saveInwProblem(inwProblem);
					return new ModelAndView(getSuccessView());
		     }
		}
    }
    
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
