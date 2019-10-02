package com.joymain.jecs.mi.webapp.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.interceptor.Soap11FaultOutInterceptor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.joymain.jecs.bd.dao.BdPeriodDao;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiRemitSale;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRemitSaleManager;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysDataLogManager;
import com.joymain.jecs.sys.service.SysLoginLogManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiRemitSaleFormController extends BaseFormController {
	private final Log log = LogFactory.getLog(JmiRemitSaleFormController.class);
	private JmiRemitSaleManager jmiRemitSaleManager;
	private SysDataLogManager sysDataLogManager = null;

	public void setJmiRemitSaleManager(JmiRemitSaleManager jmiRemitSaleManager) {
		this.jmiRemitSaleManager = jmiRemitSaleManager;
	}

	public SysDataLogManager getSysDataLogManager() {
		return sysDataLogManager;
	}

	public void setSysDataLogManager(SysDataLogManager sysDataLogManager) {
		this.sysDataLogManager = sysDataLogManager;
	}

	public JmiRemitSaleFormController() {
		setCommandName("jmiRemitSale");
		setCommandClass(JmiRemitSale.class);
	}

	// 初始化用户数据
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		// 特殊BigDecimal不能为空 id 需要一个初始化的值 -9999代表空值
		String tempId = request.getParameter("id");
		String userCode = request.getParameter("userCode");

		BigDecimal id = null;
		JmiRemitSale jmiRemitSale = null;

		String strAction = request.getParameter("strAction");

		if ("editJmiRemitSale".equals(strAction)) {
			id = (tempId != null || !"".equals(tempId)) ? new BigDecimal(tempId) : new BigDecimal("-9999");
			jmiRemitSale = jmiRemitSaleManager.findJmiRemitSaleById(id);

		} else {

			SysUser sysUser = (SysUser) request.getSession().getAttribute("sessionLogin");

			jmiRemitSale = new JmiRemitSale();
			jmiRemitSale.setCreateUser(sysUser.getUserCode());
		}
		return jmiRemitSale;
	}

	// 提交表单
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		// 被拦截对象 表单传递jmiRemitSale
		JmiRemitSale jmiRemitSale = (JmiRemitSale) command;
		Locale locale = request.getLocale();
		BigDecimal id = jmiRemitSale.getId();
		SysDataLog sysDataLog = new SysDataLog();
		String userCode = jmiRemitSale.getUserCode();
		BigDecimal jStartWeek = jmiRemitSale.getStartWeek();

		id=new BigDecimal(1000);
		jmiRemitSale.setId(id);
		jmiRemitSale.setCreateTime("2016-11-28 16:16:53");
		
		String key = null;
		// 表单传递的参数 判断逻辑是新增 删除或者更新
		String strAction = request.getParameter("strAction");

		if ("addJmiRemitSale".equals(strAction)) {

			// 增加逻辑
			CommonRecord crm = RequestUtil.toCommonRecord(request);
			jmiRemitSale.setUserCode(jmiRemitSale.getUserCode().replace(" ", "").trim());

			List<Map<String, Object>> startEndWeek = jmiRemitSaleManager.ajaxStarEndtWeek(userCode);
			int judeStartEndWeekflag = 0;
			for (int i = 0; i < startEndWeek.size(); i++) {

				BigDecimal value1 = (BigDecimal) startEndWeek.get(i).get("startWeek");
				BigDecimal value2 = (BigDecimal) startEndWeek.get(i).get("endWeek");
				if ( jStartWeek.compareTo(value1) >= 0 && jStartWeek.compareTo(value2) <= 0) {
					judeStartEndWeekflag++;
					 System.out.println("####成功 jStartWeek "+jStartWeek+" : "
							 +startEndWeek.get(i).get("startWeek")+" "
							 +startEndWeek.get(i).get("endWeek") );
					 
				}
				 System.out.println("#### jStartWeek "+jStartWeek+" : "
				 +startEndWeek.get(i).get("startWeek")+" "
				 +startEndWeek.get(i).get("endWeek") );
			}
			if (judeStartEndWeekflag == 0) {
				if (jmiRemitSaleManager.saveJmiRemitSale(jmiRemitSale) > 0) {
					key = "增加成功";
				}
			} else {
				key = "用戶记录已存在免重消期";
			}
		}
		saveMessage(request, getText(SessionLogin.getLoginUser(request).getDefCharacterCoding(), key));
		return new ModelAndView(getSuccessView());
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		super.initBinder(request, binder);

	}
}