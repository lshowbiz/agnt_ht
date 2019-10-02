package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.model.JbdMemberStarRewards;
import com.joymain.jecs.bd.service.JbdMemberStarRewardsManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdMemberStarRewardsController extends BaseController implements Controller {
    private JbdMemberStarRewardsManager jbdMemberStarRewardsManager;

    public void setJbdMemberStarRewardsManager(
            JbdMemberStarRewardsManager jbdMemberStarRewardsManager)
    {
        this.jbdMemberStarRewardsManager = jbdMemberStarRewardsManager;
    }



    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("JbdMemberStarRewardsListTable",request, 20);
        
        String userCode= crm.getString("userCode",request.getParameter("userCode"));
        String fyear= crm.getString("fyear",request.getParameter("fyear"));
        String rewards= crm.getString("rewards",request.getParameter("rewards"));
        String isReach= crm.getString("isReach",request.getParameter("isReach"));
        
        String strAction=request.getParameter("strAction");
        if ("deleteJbdMemberStarRewards".equals(strAction)) {
            String id=request.getParameter("id");
            jbdMemberStarRewardsManager.removeObject(JbdMemberStarRewards.class, Long.valueOf(id));
            return new ModelAndView("redirect:jbdMemberStarRewards.html?userCode="+userCode+"&fyear="+fyear+"&rewards="+rewards+"&isReach="+isReach);
        }
        
        if ("batchDeleteJbdMemberStarRewards".equals(strAction)) {
            String ids=request.getParameter("ids");
            jbdMemberStarRewardsManager.batchDeleteJbdMemberStarRewards(ids);
            return new ModelAndView("redirect:jbdMemberStarRewards.html?userCode="+userCode+"&fyear="+fyear+"&rewards="+rewards+"&isReach="+isReach);
        }
        
        List jbdMemberStarRewards=null;

        
    	if (!StringUtil.isEmpty(userCode)||StringUtil.isInteger(fyear)||!StringUtil.isEmpty(rewards)||!StringUtil.isEmpty(isReach)){

    	    jbdMemberStarRewards=jbdMemberStarRewardsManager.getJbdMemberStarRewardsByCrm(crm, pager);
			
		}
		request.setAttribute("JbdMemberStarRewardsListTable_totalRows", pager.getTotalObjects());
        return new ModelAndView("bd/jbdMemberStarRewardsList", "jbdMemberStarRewards", jbdMemberStarRewards);
			
		
    }
}
