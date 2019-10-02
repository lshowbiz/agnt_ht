package com.joymain.jecs.pm.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.joymain.jecs.pm.model.JocsInterfaceRetransmission;
import com.joymain.jecs.pm.service.JocsInterfaceRetransmissionManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JocsInterfaceRetransmissionController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(JocsInterfaceRetransmissionController.class);
	private JocsInterfaceRetransmissionManager jocsInterfaceRetransmissionManager = null;

	public void setJocsInterfaceRetransmissionManager(JocsInterfaceRetransmissionManager jocsInterfaceRetransmissionManager) {
		this.jocsInterfaceRetransmissionManager = jocsInterfaceRetransmissionManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		JocsInterfaceRetransmission jocsInterfaceRetransmission = new JocsInterfaceRetransmission();
		// populate object with request parameters
		BeanUtils.populate(jocsInterfaceRetransmission, request.getParameterMap());

		//List jocsInterfaceRetransmissions = jocsInterfaceRetransmissionManager.getJocsInterfaceRetransmissions(jocsInterfaceRetransmission);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
		//默认当天日期
        SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd");   
        String logStartTime = crm.getString("logStartTime");
        if(StringUtil.isEmpty(logStartTime) || "null".equals(logStartTime)){
        	logStartTime = dateformat2.format(new Date());
        	crm.setValue("logStartTime", logStartTime);
        }
        String logEndTime = crm.getString("logEndTime");
        if(StringUtil.isEmpty(logEndTime) || "null".equals(logEndTime)){
        	logEndTime = dateformat2.format(new Date());
        	crm.setValue("logEndTime", logEndTime);
        }
        crm.setValue("userCode", sessionLogin.getUserCode());
		Pager pager = new Pager("jocsInterfaceRetransmissionListTable",request, 20);
		List jocsInterfaceRetransmissions = jocsInterfaceRetransmissionManager.getJocsInterfaceRetransmissionsByCrm(crm,pager);
		request.setAttribute("jocsInterfaceRetransmissionListTable_totalRows", pager.getTotalObjects());
		/*****/

		request.setAttribute("logStartTime",logStartTime);
        request.setAttribute("logEndTime",logEndTime);
		return new ModelAndView("pm/jocsInterfaceRetransmissionList", Constants.JOCSINTERFACERETRANSMISSION_LIST, jocsInterfaceRetransmissions);
	}
}
