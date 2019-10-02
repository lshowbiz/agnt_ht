package com.joymain.jecs.po.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.model.JpoMemberOrderTask;
import com.joymain.jecs.po.service.JpoMemberOrderTaskManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpoMemberOrderTaskController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderTaskController.class);
    private JpoMemberOrderTaskManager jpoMemberOrderTaskManager = null;

    public void setJpoMemberOrderTaskManager(JpoMemberOrderTaskManager jpoMemberOrderTaskManager) {
        this.jpoMemberOrderTaskManager = jpoMemberOrderTaskManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpoMemberOrderTaskListTable",request, 20);
        
        String userCode=request.getParameter("userCode");

        if("C".equals(defSysUser.getUserType())){
        	crm.addField("companyCode", defSysUser.getCompanyCode());
        }else{
        	crm.addField("userCode", defSysUser.getUserCode());
        	crm.addField("taskType", "1");
            JpoMemberOrderTask jpoMemberOrderTask = jpoMemberOrderTaskManager.getMainJpoMemberOrderTask(defSysUser.getUserCode());
            request.setAttribute("jpoMemberOrderTask", jpoMemberOrderTask);
        }

    	
    	 List jpoMemberOrderTasks = null;
    	if(!StringUtil.isEmpty(userCode)||"M".equals(defSysUser.getUserType())){
    		jpoMemberOrderTasks = jpoMemberOrderTaskManager.getJpoMemberOrderTasksByCrm(crm,pager);
    	}
        
        
        request.setAttribute("jpoMemberOrderTaskListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("po/jpoMemberOrderTaskList", Constants.JPOMEMBERORDERTASK_LIST, jpoMemberOrderTasks);
    }
}
