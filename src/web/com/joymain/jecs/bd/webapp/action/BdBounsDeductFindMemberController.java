package com.joymain.jecs.bd.webapp.action;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;



public class BdBounsDeductFindMemberController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(BdBounsDeductFindMemberController.class);

    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = null;
    		}
    	}
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", companyCode);    
        Pager pager = new Pager("bdBounsDeductFindMemberListTable",request, 20);
        
        
        String userCode=request.getParameter("userCode");
        String userName=request.getParameter("userName");
        if(StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(userName)){
        	request.setAttribute("bdBounsDeductFindMemberListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("bd/bdBounsDeductFindMemberList","members",null);
        }else{
        	 List members=jmiMemberManager.getJmiMembersByCrm(crm, pager);
     		request.setAttribute("bdBounsDeductFindMemberListTable_totalRows", pager.getTotalObjects());
             return new ModelAndView("bd/bdBounsDeductFindMemberList","members",members);
        }
   
    }
}
