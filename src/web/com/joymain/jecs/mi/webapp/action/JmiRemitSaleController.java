package com.joymain.jecs.mi.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiRemitSale;
import com.joymain.jecs.mi.service.JmiRemitSaleManager;
import com.joymain.jecs.sys.service.SysDataLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.RequestUtil;

public class JmiRemitSaleController extends BaseController implements Controller {
	private SysDataLogManager sysDataLogManager = null;
	private final Log log = LogFactory.getLog(JmiRemitSaleController.class);
	private JmiRemitSaleManager jmiRemitSaleManager;

	public void setJmiRemitSaleManager(JmiRemitSaleManager jmiRemitSaleManager) {
		this.jmiRemitSaleManager = jmiRemitSaleManager;
	}

	public SysDataLogManager getSysDataLogManager() {
		return sysDataLogManager;
	}

	public void setSysDataLogManager(SysDataLogManager sysDataLogManager) {
		this.sysDataLogManager = sysDataLogManager;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setAttribute("sysDataLogManager", sysDataLogManager);
		CommonRecord crm = RequestUtil.toCommonRecord(req);
		/*
		 * if(StringUtils.isNotEmpty(req.getParameter("createTime"))){ String
		 * cdate = "TO_DATE('"+req.getParameter("createTime")+
		 * "','yyyy-MM-dd hh24:mi')"; crm.addField("createTime", cdate); }
		 */
		Pager pager = new Pager("jmiRemitSaleTable", req, 20);

		// 默认页面
		List jmiRemitSaleList = null;
		try {
		   jmiRemitSaleList = jmiRemitSaleManager.getJmiMemberTeamsByCrm(crm, pager);
		} catch (Exception e) {
		  jmiRemitSaleList = new ArrayList<Object>();
		}

		req.setAttribute("jmiRemitSaleTable_totalRows", pager.getTotalObjects());		
		return new ModelAndView("mi/jmiRemitSale", "jmiRemitSaleList", jmiRemitSaleList);
	}

}
