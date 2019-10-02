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
import com.joymain.jecs.am.model.InwDepartment;
import com.joymain.jecs.am.service.InwDepartmentManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class InwDepartmentFormController extends BaseFormController {
    private InwDepartmentManager inwDepartmentManager = null;

    public void setInwDepartmentManager(InwDepartmentManager inwDepartmentManager) {
        this.inwDepartmentManager = inwDepartmentManager;
    }
    public InwDepartmentFormController() {
        setCommandName("inwDepartment");
        setCommandClass(InwDepartment.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        String strAction = request.getParameter("strAction");
        request.setAttribute("strAction", strAction);
        InwDepartment inwDepartment = null;

        if (!StringUtils.isEmpty(id)) {
            inwDepartment = inwDepartmentManager.getInwDepartment(id);
        } else {
            inwDepartment = new InwDepartment();
        }
        return inwDepartment;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        InwDepartment inwDepartment = (InwDepartment) command;
	    String strAction = request.getParameter("strAction");
	    SysUser sessionLogin = SessionLogin.getLoginUser(request);
	    
	    //录入或编辑功能
	    if("editorInwDepartment".equals(strAction)){
	    	//录入或编辑之前,首先做不为空的校验
	    	boolean isEmpty = inwDepartmentManager.getCheckInwDepartmentEmpty(inwDepartment,errors,sessionLogin.getDefCharacterCoding());
	        if(isEmpty){
	        	return showForm(request, response,errors);
	        }else{
	        	//部门名称唯一性校验
	        	boolean nameIsUnique = inwDepartmentManager.getNameUniqueCheckResult(inwDepartment);
	        	if(nameIsUnique){
	        		this.saveMessage(request, "部门名称已经存在，请重新输入部门名称！");
		        	return showForm(request, response,errors);
	        	}else{
	        		String createUserCode = inwDepartment.getCreateUserCode();
	        		Date date = new Date();
	        		//新增
	        		if(StringUtil.isEmpty(createUserCode)){
	        			inwDepartment.setCreateUserCode(sessionLogin.getUserCode());
	        			inwDepartment.setCreateTime(date);
	        			//状态status:1新建,2审核未通过,此时可以重新编辑；3审核通过(扩展功能用)
	        			inwDepartment.setStatus("1");
	        		}
	        		//审核
	        		else{
	        			String auditUserCode = inwDepartment.getAuditUserCode();
	        			//判断是否是审核
	        			if(StringUtil.isEmpty(auditUserCode)){
	        				inwDepartment.setAuditTime(date);
	        				inwDepartment.setAuditUserCode(createUserCode);
	        				//status该字段在页面弄个下拉框形式的隐藏域
	        			}
	        		}
	        		inwDepartmentManager.saveInwDepartment(inwDepartment);
	        		this.saveMessage(request, "更新成功!");
	        	}
	        }
	    }
	    //删除功能
	    else if("deleteInwDepartment".equals(strAction)){
	    	       inwDepartmentManager.inwDepartmentRemove(inwDepartment);
	    	       return new ModelAndView("redirect:inwDepartments.html?strAction=deleteInwQueryDepartment");
	    }
	    else{
	    	
	    }
        return new ModelAndView(getSuccessView());
    }
    
    //这个方法的作用??-----调用父方法,然后进行各种类型的数据之间的转换.
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
