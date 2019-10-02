package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberStarController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdMemberStarController.class);
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public void setJbdMemberLinkCalcHistManager(
			JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
		this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode=request.getParameter("companyCode");
    		}
    	}
        Pager pager = new Pager("JbdMemberStarListTable",request, 20);
        crm.addField("companyCode", companyCode);
        
        
        String userCode= crm.getString("userCode",request.getParameter("userCode"));
        String userName= crm.getString("userName",request.getParameter("userName"));
        String wweek= crm.getString("wweek",request.getParameter("wweek"));
        
        List jbdMemberStars=null;

        WeekFormatUtil.setSearchFweek(crm);
        
    	if (!StringUtil.isEmpty(userCode)||StringUtil.isInteger(wweek)||!StringUtil.isEmpty(userName)){

			jbdMemberStars=jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHistsByCrm(crm, pager);
			
		}
    	request.setAttribute("jmiMemberManager", jmiMemberManager);
		request.setAttribute("JbdMemberStarListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("bd/jbdMemberStarList", "jbdMemberStars", jbdMemberStars);
			
		
    }
}
