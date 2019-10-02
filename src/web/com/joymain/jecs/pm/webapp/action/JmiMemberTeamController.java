package com.joymain.jecs.pm.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiMemberTeamController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberTeamController.class);
    private JmiMemberTeamManager jmiMemberTeamManager = null;

    public JmiMemberTeamManager getJmiMemberTeamManager() {
		return jmiMemberTeamManager;
	}
    public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
        this.jmiMemberTeamManager = jmiMemberTeamManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiMemberTeam jmiMemberTeam = new JmiMemberTeam();
        // populate object with request parameters
        BeanUtils.populate(jmiMemberTeam, request.getParameterMap());

	//List jmiMemberTeams = jmiMemberTeamManager.getJmiMemberTeams(jmiMemberTeam);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiMemberTeamListTable",request, 20);
        List jmiMemberTeams = jmiMemberTeamManager.getJmiMemberTeamsByCrm(crm,pager); 
        request.setAttribute("jmiMemberTeamListTable_totalRows", pager.getTotalObjects());
        /*****/ 

        return new ModelAndView("pm/jmiMemberTeamList", Constants.JMIMEMBERTEAM_LIST, jmiMemberTeams);
    }

	
}
