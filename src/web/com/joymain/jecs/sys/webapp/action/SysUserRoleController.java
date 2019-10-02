package com.joymain.jecs.sys.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class SysUserRoleController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(SysUserRoleController.class);
    private SysUserRoleManager sysUserRoleManager = null;
    private SysUserManager sysUserManager = null;
    private SysRoleManager sysRoleManager = null;
  
    public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
        this.sysUserRoleManager = sysUserRoleManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("sysUserRoleListTable",request, 20);
          SysRole sysRole = null;
          String roleId = request.getParameter("roleId");
          String userCode = request.getParameter("userCode");
          if(StringUtils.isNotEmpty(roleId)){
        	  sysRole = sysRoleManager.getSysRole(roleId);
          }
          if(StringUtils.isNotEmpty(userCode)){
        	  sysUserManager.getSysUser(userCode);
          }
        
          String strAction = request.getParameter("strAction");
          if("roleUserManage".equals(strAction)){//编辑角色下面的用户
        	  crm.setValue("roleId", roleId);
        	  
        	  //增加搜索条件
        	  String companyCode = sysRole.getCompanyCode();
        	  crm.setValue("companyCode",companyCode);
        	  
        	  String searchusercode = request.getParameter("searchusercode");
        	  crm.setValue("searchusercode", searchusercode);
        	  
        	  String hasrole = request.getParameter("hasrole");
        	  crm.setValue("hasrole", hasrole);
        	  //String userType = request.getParameter("userType");
        	  //crm.setValue("userType", userType);
        	  crm.setValue("userType", sysRole.getUserType());
        	  
        	  String state = request.getParameter("state");
        	  crm.setValue("state", state); 
        	  
        	  
          }else if("userRoleManage".equals(strAction)){//编辑用户的角色
        	  crm.setValue("userCode", userCode);
          }
          List userList = sysUserRoleManager.getSysUserRolesByCrm(crm,pager);
          
          request.setAttribute("sysUserRoleListTable_totalRows", pager.getTotalObjects());
          
//        SysUserRole sysUserRole = new SysUserRole();
//        // populate object with request parameters
//        BeanUtils.populate(sysUserRole, request.getParameterMap());
//
//				//List sysUserRoles = sysUserRoleManager.getSysUserRoles(sysUserRole);
//		/**** auto pagination ***/
//		  CommonRecord crm=RequestUtil.toCommonRecord(request);
//        Pager pager = new Pager("sysUserRoleListTable",request, 20);
//        List sysUserRoles = sysUserRoleManager.getSysUserRolesByCrm(crm,pager);
//        request.setAttribute("sysUserRoleListTable_totalRows", pager.getTotalObjects());
//        /*****/
        
        Map userTypes=ListUtil.getListOptions(SessionLogin.getLoginUser(request).getCompanyCode(), "user_type");
  		request.setAttribute("userTypes", userTypes);
  		
        request.setAttribute("sysRole", sysRole);
        return new ModelAndView("sys/sysUserRoleList","sysUserRoleList",userList);
    }

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}
}
