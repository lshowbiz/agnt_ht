package com.joymain.jecs.vt.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.vt.model.VtVote;
import com.joymain.jecs.vt.service.VtVoteManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class VtVotePollController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(VtVotePollController.class);
    private VtVoteManager vtVoteManager = null;

	public void setVtVoteManager(VtVoteManager vtVoteManager) {
        this.vtVoteManager = vtVoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String strAction=request.getParameter("strAction");

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("vtVoteListTable",request, 20);

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	String voterCompanyCode="";
    	String voterUserType="";
    	if("M".equals(defSysUser.getUserType())){
    		JmiMember jmiMember=defSysUser.getJmiMember();
    		voterUserType=jmiMember.getCardType();
    	}else if("C".equals(defSysUser.getUserType())){
    		voterUserType=defSysUser.getUserType();
    	}

		voterCompanyCode=defSysUser.getCompanyCode();
		
		crm.addField("voterUserType", voterUserType);
		crm.addField("voterCompanyCode", voterCompanyCode);
		crm.addField("status", "2");
        
        List vtVotes = vtVoteManager.getVtVotesByCrm(crm,pager);
        request.setAttribute("vtVoteListTable_totalRows", pager.getTotalObjects());


        return new ModelAndView("vt/vtVotePollList", Constants.VTVOTE_LIST, vtVotes);
    }
}
