package com.joymain.jecs.mi.webapp.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysLoginLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberMController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberMController.class);
    private JmiMemberManager jmiMemberManager = null;
    private JmiLinkRefManager jmiLinkRefManager = null;

    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
        String strAction=request.getParameter("strAction");
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        Pager pager = new Pager("jmiMemberListTable",request, 20);
        
        String userCode=request.getParameter("userCode");
        String petName=request.getParameter("petName");
        String userName=request.getParameter("userName");
        String papernumber=request.getParameter("papernumber");
        String checkBDate=request.getParameter("checkBDate");
        String checkEDate=request.getParameter("checkEDate");
        String createBTime=request.getParameter("createBTime");
        String createETime=request.getParameter("createETime");
        String cardType=request.getParameter("cardType");
        List jmiMembers =null;
        
        crm.addField("companyCode", defSysUser.getCompanyCode());

	
        if (!StringUtil.isEmpty(userCode)&&jmiLinkRefManager.getJmiLinkRefIsM(userCode)){
            jmiMembers = jmiMemberManager.getJmiMembersByCrm(crm,pager);
        }
        request.setAttribute("jmiMemberListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("mi/jmiMemberMList", Constants.JMIMEMBER_LIST, jmiMembers);
    }

	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}
}
