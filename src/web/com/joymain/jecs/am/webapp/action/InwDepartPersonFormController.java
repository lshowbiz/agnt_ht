package com.joymain.jecs.am.webapp.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.am.model.InwDepartPerson;
import com.joymain.jecs.am.service.InwDepartPersonManager;
import com.joymain.jecs.am.service.InwDepartmentManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class InwDepartPersonFormController extends BaseFormController {
	
    private InwDepartPersonManager inwDepartPersonManager = null;
    
    private InwDepartmentManager inwDepartmentManager;

    public void setInwDepartPersonManager(InwDepartPersonManager inwDepartPersonManager) {
        this.inwDepartPersonManager = inwDepartPersonManager;
    }
    
    public void setInwDepartmentManager(InwDepartmentManager inwDepartmentManager) {
    	this.inwDepartmentManager = inwDepartmentManager;
	}

	public InwDepartPersonFormController() {
        setCommandName("inwDepartPerson");
        setCommandClass(InwDepartPerson.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        InwDepartPerson inwDepartPerson = null;
        String strAction = request.getParameter("strAction");
        request.setAttribute("strAction", strAction);
        
        //查询出所有部门的信息列表
        List inwDepartmentList = inwDepartmentManager.getInwDepartmentList();
        request.setAttribute("inwDepartmentList",inwDepartmentList);       
        
        if (!StringUtils.isEmpty(id)) {
            inwDepartPerson = inwDepartPersonManager.getInwDepartPerson(id);
        } else {
            inwDepartPerson = new InwDepartPerson();
        }

        return inwDepartPerson;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        InwDepartPerson inwDepartPerson = (InwDepartPerson) command;       
		String strAction = request.getParameter("strAction");
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		 //查询出所有部门的信息列表
        List inwDepartmentList = inwDepartmentManager.getInwDepartmentList();
        request.setAttribute("inwDepartmentList",inwDepartmentList);  
		
		
		//编辑(新增或修改)
		if ("editorInwDepartPerson".equals(strAction) || "addInwDepartPerson".equals(strAction) ) {
		    //在编辑之前,做不为空的校验
			boolean checkEmpty = inwDepartPersonManager.getCheckEmpty(inwDepartPerson,errors,sessionLogin.getDefCharacterCoding());
			if(checkEmpty){
		        request.setAttribute("strAction", strAction);
				return showForm(request,response,errors);
			}else{
				
				//会员编号唯一性校验
	        	boolean userCodeIsUnique = inwDepartPersonManager.getUserCodeUniqueCheckResult(inwDepartPerson);
				if(userCodeIsUnique){
					this.saveMessage(request, "会员编号已经存在，请重新输入会员编号！");
		        	return showForm(request, response,errors);
				}
	        	
				String createUserCode = inwDepartPerson.getCreateUserCode();
				if(StringUtil.isEmpty(createUserCode)){
					inwDepartPerson.setCreateUserCode(sessionLogin.getUserCode());
					inwDepartPerson.setCreateTime(new Date());
				}	
        		this.saveMessage(request, "更新成功!");
				inwDepartPersonManager.saveInwDepartPerson(inwDepartPerson);
			}			
		}
		//删除
		else if("deleteInwDepartPerson".equals(strAction)){
			inwDepartPersonManager.removeInwDepartPerson(inwDepartPerson.getId().toString());
    		this.saveMessage(request, "删除成功!");

		}		
		else{
			
		}

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
}
