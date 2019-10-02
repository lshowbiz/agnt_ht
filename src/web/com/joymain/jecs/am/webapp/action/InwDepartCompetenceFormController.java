package com.joymain.jecs.am.webapp.action;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.am.model.InwDemand;
import com.joymain.jecs.am.model.InwDepartCompetence;
import com.joymain.jecs.am.model.InwDepartment;
import com.joymain.jecs.am.service.InwDemandManager;
import com.joymain.jecs.am.service.InwDepartCompetenceManager;
import com.joymain.jecs.am.service.InwDepartmentManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class InwDepartCompetenceFormController extends BaseFormController {
    private InwDepartCompetenceManager inwDepartCompetenceManager = null;
    
    private InwDepartmentManager inwDepartmentManager;
    private InwDemandManager inwDemandManager;

    public void setInwDepartCompetenceManager(InwDepartCompetenceManager inwDepartCompetenceManager) {
        this.inwDepartCompetenceManager = inwDepartCompetenceManager;
    }
    
    public void setInwDepartmentManager(InwDepartmentManager inwDepartmentManager) {
		this.inwDepartmentManager = inwDepartmentManager;
	}

	public void setInwDemandManager(InwDemandManager inwDemandManager) {
		this.inwDemandManager = inwDemandManager;
	}

	public InwDepartCompetenceFormController() {
        setCommandName("inwDepartCompetence");
        setCommandClass(InwDepartCompetence.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        
        String strAction = request.getParameter("strAction");
        request.setAttribute("strAction", strAction);
        
        InwDepartCompetence inwDepartCompetence = null;

        //查询明细或者编辑初始化
        if (!StringUtils.isEmpty(id)) {
            inwDepartCompetence = inwDepartCompetenceManager.getInwDepartCompetence(id);
            //查询出部门
            InwDepartment inwDepartment = inwDepartmentManager.getInwDepartment(inwDepartCompetence.getDepartmentId());
            request.setAttribute("departmentName", inwDepartment.getName());
            //查询出权限(需求)
            InwDemand inwDemand = inwDemandManager.getInwDemand(inwDepartCompetence.getDemandId());
            request.setAttribute("demandName", inwDemand.getName());
        } else {
            inwDepartCompetence = new InwDepartCompetence();
        }

        return inwDepartCompetence;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        InwDepartCompetence inwDepartCompetence = (InwDepartCompetence) command;
        String strAction = request.getParameter("strAction");
	    SysUser sessionLogin = SessionLogin.getLoginUser(request);

        //新增或编辑
        if("editorInwDepartCompetence".equals(strAction)){
        	//新增或编辑之前,首先做的是不为空的校验
        	boolean checkEmptyResult = inwDepartCompetenceManager.getCheckEmptyResult(inwDepartCompetence,errors,sessionLogin.getDefCharacterCoding());
        	if(checkEmptyResult){
        		return showForm(request,response,errors);
        	}else{
        		//录入的话,得输入创建人和创建时间
        		String createUserCode = inwDepartCompetence.getCreateUserCode();
        		if(StringUtil.isEmpty(createUserCode)){
        			inwDepartCompetence.setCreateTime(new Date());
        			inwDepartCompetence.setCreateUserCode(sessionLogin.getUserCode());
        			inwDepartCompetenceManager.saveInwDepartCompetence(inwDepartCompetence);
        			this.saveMessage(request, "新增成功!");
        		}//编辑的话就不用录入输入创建人和创建时间
        		else{
        			inwDepartCompetenceManager.saveInwDepartCompetence(inwDepartCompetence);
        			this.saveMessage(request, "更新成功!");
        		}
        	}
        }
        else if("deleteInwDepartCompetence".equals(strAction)){
        	inwDepartCompetenceManager.removeInwDepartCompetence(inwDepartCompetence.getId().toString());
        	this.saveMessage(request, "删除成功!");
        }
        return new ModelAndView(getSuccessView());
    }
    
    
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
    
}
