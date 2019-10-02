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
import com.joymain.jecs.bd.service.JbdBonusBalanceManager;
import com.joymain.jecs.mi.model.JmiMember;
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

public class JmiMemberCheckController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberCheckController.class);
    private JbdBonusBalanceManager jbdBonusBalanceManager;

    public void setJbdBonusBalanceManager(
			JbdBonusBalanceManager jbdBonusBalanceManager) {
		this.jbdBonusBalanceManager = jbdBonusBalanceManager;
	}

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiMemberListTable",request, 20);
        
        crm.addField("companyCode", defSysUser.getCompanyCode());
        if( "checkJmiMemberSubmit".equals(request.getParameter("strAction"))){
        	String[] strReissueCodes = request.getParameter("strRegisterSuccessCodes").split(",");
		
			try {
				jbdBonusBalanceManager.checkJmiMember(strReissueCodes, defSysUser);
				saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
			}
        }
        
        
        List jmiMembers = jbdBonusBalanceManager.getJbdBonusBalancesByCrm(crm, pager);

        request.setAttribute("jmiMemberListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("mi/jmiMemberCheckList", Constants.JMIMEMBER_LIST, jmiMembers);
    }
	
}
