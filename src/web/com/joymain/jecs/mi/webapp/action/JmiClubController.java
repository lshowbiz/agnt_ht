package com.joymain.jecs.mi.webapp.action;

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
import com.joymain.jecs.mi.model.JmiBlacklist;
import com.joymain.jecs.mi.service.JmiBlacklistManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiClubController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiClubController.class);
    private JmiMemberManager jmiMemberManager;

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


        SysUser defSysUser = SessionLogin.getLoginUser(request);
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", defSysUser.getCompanyCode());

        Pager pager = new Pager("jmiClubsListTable",request, 20);
        
        List jmiClubs  =null;
        if("C".equals(defSysUser.getUserType())){
        	jmiClubs = jmiMemberManager.getJmiClubs(crm, pager);
        }else{
        	String userCode=request.getParameter("userCode");
        	if(!StringUtil.isEmpty(userCode)){
        		jmiClubs = jmiMemberManager.getJmiClubs(crm, pager);
        	}
        }
        
        
        
        
        
        request.setAttribute("jmiClubsListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiClubList", "jmiClubList", jmiClubs);
    }

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
}
