package com.joymain.jecs.am.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.service.InwDepartmentManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class InwDepartmentController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(InwDepartmentController.class);
    private InwDepartmentManager inwDepartmentManager = null;

    public void setInwDepartmentManager(InwDepartmentManager inwDepartmentManager) {
        this.inwDepartmentManager = inwDepartmentManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String strAction = request.getParameter("strAction");
        String departDeleteQuery = request.getParameter("departDeleteQuery");
        //部门删除(查询)的标志strAction=deleteInwQueryDepartment
        if("deleteInwQueryDepartment".equals(strAction)||"departDeleteQuery".equals(departDeleteQuery)){
           request.setAttribute("departDeleteQuery","departDeleteQuery");
     	   request.setAttribute("strAction", "deleteInwQueryDepartment");
        }
    
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        //在录入或编辑时查询上级部门时用到的---开始
        String departCategory = request.getParameter("departCategory");
        if(!StringUtil.isEmpty(departCategory)){
        	   crm.setValue("departCategory", departCategory);
        	   //部门的录入或编辑页面输入上级部门时查询用到
        	   request.setAttribute("strAction", "queryHigerDepart");
        	   request.setAttribute("departCategory", departCategory);
        }
        
        //在权限录入或编辑的时候管理查询的
        if("selectInwDepartByInwDC".equals(strAction)){
        	  request.setAttribute("strAction", strAction);
        }
        
        //在录入或编辑时查询上级部门时用到的---结束
        Pager pager = new Pager("inwDepartmentListTable",request, 20);
        List inwDepartments = inwDepartmentManager.getInwDepartmentsByCrm(crm,pager);
        request.setAttribute("inwDepartmentListTable_totalRows", pager.getTotalObjects());
      
        return new ModelAndView("am/inwDepartmentList", Constants.INWDEPARTMENT_LIST, inwDepartments);
    }
    
    
}
