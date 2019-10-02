package com.joymain.jecs.webapp.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysModule;
import com.joymain.jecs.sys.model.SysOperationLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysModuleManager;
import com.joymain.jecs.sys.service.SysOperationLogManager;
import com.joymain.jecs.sys.service.SysVisitLogManager;
import com.joymain.jecs.util.LocaleUtil;
import com.joymain.jecs.util.ParameterRequestWrapper;
import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.interceptor.DataLog;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PowerFilter implements Filter {
	private final Log log = LogFactory.getLog(PowerFilter.class);

	public void destroy() {
	}

	
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		//设置编码
		HttpServletRequest requestHis = (HttpServletRequest) req;
		requestHis.setCharacterEncoding("UTF-8");
		//继承HttpServletRequestWrapper，实现request获取的参数被获取并处理之后能够被存入request
		ParameterRequestWrapper request = new ParameterRequestWrapper((HttpServletRequest)requestHis);
		HttpServletResponse response = (HttpServletResponse) res;
		request.setCharacterEncoding("UTF-8");
		
		String strAction = request.getParameter("strAction");
		String requestPath = request.getServletPath();
		if(requestPath!=null && requestPath.indexOf("payReceive")<0){
			//处理之前的url
			String postUrl = request.getRequestURL().toString() + "?" + RequestUtil.paramStr(request);
			String getUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
			
			//处理语句
			boolean bl = true;
			
			//添加一个参数作为开关，只有开关不为N的时候，才会过滤
			String filePath = ConfigUtil.getConfigValue("CN", "sqlflag.inject");
			if(!"N".equals(filePath)){
				bl = this.checkParamStr(request);
			}
			
			//处理之后的url
			String apostUrl = request.getRequestURL().toString() + "?" + RequestUtil.paramStr(request);
			String agetUrl = request.getRequestURL().toString() + "?" + request.getQueryString();
			
			//如果是有问题的URL则写入到表中,
			if(!bl){
				SysUser sessionLogin = SessionLogin.getLoginUser(request);
				String urlUserCode = "";
				if(sessionLogin !=null){
					urlUserCode = sessionLogin.getUserCode();
				}
				//写入表结构
				DataLog dataLog = (DataLog) ContextUtil.getContext().getBean("dataLog");
				dataLog.logUrl(request,sessionLogin.getUserCode(),getUrl, postUrl, agetUrl, apostUrl);
				
				//打印信息：
				String checkWord = LocaleUtil.getLocalText("sqlflag.word");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<font color='red'>"+checkWord+"</font>");
				//返回  
				return ;
			}
		}

		 if(requestPath.startsWith("/druid")){
			 filterChain.doFilter(request, response);
			 return;
		 }
		// 判断是否SSL链接
		if (request.getServerPort() != 443
				&& "true".equalsIgnoreCase(Constants.getConfig("force_ssl"))) {
			response.sendRedirect("https://" + request.getServerName()
					+ request.getContextPath() + "/");
			return;
		}
		//
		if (requestPath.equals("/starsExpressCallBackService.html")|| requestPath.equals("/jfiPayAlipayReceiveNotify.html")||requestPath.equals("/reload.html")) {
			filterChain.doFilter(request, response);
			log.error("reload>>>>>> >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			return;
		}

		System.out.println("");
		//快钱
		
		//if (StringUtils.contains("61.152.114.140,61.152.114.141,61.152.114.142,222.73.27.165",remote)) {
			 
		if (requestPath.equals("/jfi99billPosReceiveLogs.html")
				|| requestPath.equals("/jfi99billPosReceiveBackLogs.html")
				|| requestPath.equals("/jfiPay99billReceive.html")
				|| requestPath.equals("/jfiPayChanjetReceive.html")
				|| requestPath.equals("/fiPayChannelReceive.html")
				|| requestPath.equals("/mobilPayReceive.html")
				|| requestPath.equals("/mobilWebPayReceive.html")
				|| requestPath.equals("/jfiUmbpayReceive.html")
				|| requestPath.equals("/jfiChinapnrReceive.html")
				|| requestPath.equals("/jfiYeePayReceive.html")
				|| requestPath.equals("/jfiPayTenpayReceiveNotify.html")
				|| requestPath.equals("/jfiPayAlipayReceiveReturn.html")
				|| requestPath.equals("/jfiPayAlipayReceiveNotify.html")
				|| requestPath.equals("/jfiReapalPayReceive.html")//接收融宝支付付款通知 Modify By WuCF 20150923
				|| requestPath.equals("/jfipayReceiveController.html")///jfipayReceiveController
				) {
				 
				filterChain.doFilter(request, response);
				return;
				
			}
		//}
		
		
		//台湾信用卡
		if (requestPath.equals("/jfiHiTrustRequest.html") || requestPath.equals("/jfiB2CReturn.html") || requestPath.equals("/jfiB2CUpdate.html")){
			filterChain.doFilter(request, response);
			return;
		}
		
		//美国信用卡
		if (requestPath.equals("/jfiUsPayRequest.html")){
			filterChain.doFilter(request, response);
			return;
		}
		
		//快钱分润
		
		if (requestPath.equals("/jfiPay99billmsShow.html")|| requestPath.equals("/jfiPay99billmsReceivePage.html")|| requestPath.equals("/jfiPay99billmsReceive.html")) {
			filterChain.doFilter(request, response);
			return;
		}
		
	
		//支付宝
		
		if (requestPath.equals("/jfiPayAlipayReceiveReturn.html")|| requestPath.equals("/jfiPayAlipayReceiveNotify.html")) {
			filterChain.doFilter(request, response);
			return;
		}

		
		//财付通
		if (requestPath.equals("/jfiPayTenpayReceiveNotify.html")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if ("post".equalsIgnoreCase(request.getMethod())&&!"/jpoMemberNycQualified.html".equals(requestPath)) {
			String refererUrl = request.getHeader("Referer");
			String serverAddr = request.getServerName();

			if (StringUtils.isEmpty(refererUrl)
					|| !StringUtils.contains(refererUrl, serverAddr)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}

		// 过滤期用
		/*
		 * if(!"/maintaining.jsp".equals(request.getServletPath())){
		 * response.sendRedirect(request.getContextPath()+"/maintaining.jsp");
		 * return; }
		 */

		// 判断系统是否关闭,当前用户是否可以使用系统
		SysUser sysUser = SessionLogin.getLoginUser(request);
		if (sysUser != null && !StringUtils.isEmpty(sysUser.getUserCode())
				&& !sysUser.getIsAdmin()) {
			// 如果不是超级用户

			if (sysUser.getIsMember()) {// 会员
				if (!"1".equals(ConfigUtil.getConfigValue(sysUser
						.getCompanyCode(), "open.to.member"))) {
					response.sendRedirect(request.getContextPath()
							+ "/maintaining.jsp");
					return;
				}
			} else if (sysUser.getIsManager() || sysUser.getIsCompany()) {
				// 如果为公司用户
				if (!"1".equals(ConfigUtil.getConfigValue(sysUser
						.getCompanyCode(), "open.to.company"))) {
					response.sendRedirect(request.getContextPath()
							+ "/maintaining.jsp");
					return;
				}
			}
		}

		

		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession()
						.getServletContext());
		SysModuleManager sysModuleManager = (SysModuleManager) context
				.getBean("sysModuleManager");
		SysOperationLogManager sysOperationLogManager = (SysOperationLogManager) context
				.getBean("sysOperationLogManager");
		SysVisitLogManager sysVisitLogManager = (SysVisitLogManager) context
				.getBean("sysVisitLogManager");

		// 如果是进度条的页面则跳过下面步骤, 加快速度, 节省资源
		if ("sysProgressBar".equals(strAction)
				&& "/sys_progress_bar.html".equals(requestPath)) {
			filterChain.doFilter(request, response);
			
		}

		SysOperationLog sysOperationLog = this.initSysOperationLog(request,
				requestPath);
		// 获取对应的模块
		SysModule sysModule = null;
		if (StringUtils.isNotEmpty(strAction)) {
			sysModule = sysModuleManager.getSysModuleByCode(strAction);
		} else {
			List sysModules = sysModuleManager.getSysModulesByUrl(requestPath);
			if (sysModules != null && sysModules.size() > 0) {
				if (sysModules.size() == 1) {
					sysModule = (SysModule) sysModules.get(0);
				} else {
					log.error("URL: " + requestPath + " 不止一个模块,不能确定.");
				}
			} else {
				log.error("URL: " + requestPath + " 没有找到对应的模块");
			}
		}

		if (sysModule != null) {
			sysOperationLog.setModuleName(sysModule.getModuleName());
		}

		int result = this.checkPower(request, sysModuleManager, strAction,
				requestPath, sysModule);
		sysOperationLog.setDoResult(result);

		if (result == -1) {
			sysOperationLog.setOperateData(null);
		}

		if (StringUtils.isEmpty(sysOperationLog.getOperaterCode())) {
			sysOperationLog.setOperaterCode(request.getParameter("userCode"));
		}

		/** 分表日志 **/
		// 获取月份,以定位对应的表
		String month = DateUtil.getDateTime("yyyyMM", new Date());
		// sysOperationLogManager.saveSysOperationLogBySql(sysOperationLog,
		// month);
		Long logId = 0L;
		String flag = Constants.sysConfigMap.get("CN").get(
				"sys.log.save");
		if("1".equals(flag)){
			logId = sysOperationLogManager.saveSysOperationLogBySql(
					sysOperationLog, month);
		}
		 
		
		// 设置最后的LOGID
		sysUser.setOperationLogId(logId);
		sysUser.setDataMonth(month);

		// sysOperationLogManager.saveSysOperationLog(sysOperationLog);
		// 设置最后的LOGID
		// sysUser.setOperationLogId(sysOperationLog.getLogId());
		SessionLogin.getLoginUser(request).setOperationLogId(logId);
		SessionLogin.setLoginUser(request, sysUser);

		if (result == 1) {
			MessageUtil.saveLocaleMessage(request,
					"sys.message.sessionInvalidOrOverTime");
			request.getRequestDispatcher("/loginRedirect.html").forward(
					request, response);
			return;
		} else if (result == 2) {
			// 解决浏览公告无权限但有其它模块权限时显示的无权限问题
			if (sysModule != null
					&& ("browserAmAnnounce".equals(sysModule.getModuleCode())
							|| "workspace".equals(sysModule.getModuleCode())
							|| "jfi99billPosReceiveLogs".equals(sysModule
									.getModuleCode()) || "jfi99billPosReceiveBackLogs"
							.equals(sysModule.getModuleCode()))) {
				request.getRequestDispatcher("/welcome.html").forward(request,
						response);
				return;
			}
			log.error("403 Error.");
			log.error("URL: http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ request.getServletPath());
			log.error("strAction: " + strAction);
			// MessageUtil.saveLocaleMessage(request,
			// "sys.message.permissionDenied");
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		} else {
			// 通过权限
			if ("get".equalsIgnoreCase(request.getMethod())
					&& !StringUtils.isEmpty(sysOperationLog.getOperaterCode())
					&& sysModule != null
					&& !StringUtils.isEmpty(sysModule.getModuleCode())) {/*
				String queryString = null;
				if (!StringUtils.isEmpty(request.getQueryString())) {
					queryString = StringUtils.replace(request.getQueryString(),
							"needReload=1", "");
				}
				try {
					SysVisitLog sysVisitLog = sysVisitLogManager
							.getSysVisitLog(sysOperationLog.getOperaterCode(),
									sysModule.getModuleCode(), requestPath);
					// 是否已经访问过
					if (sysVisitLog != null) {
						if ("1".equals(request.getParameter("needReload"))) {
							// 跳转
							response.sendRedirect(request.getContextPath()
									+ sysVisitLog.getVisitUrl()
									+ (StringUtils.isEmpty(sysVisitLog
											.getQueryString()) ? ""
											: ("?" + sysVisitLog
													.getQueryString())));
							return;
						} else {
							sysVisitLog.setVisitTime(DateUtil.now());
							sysVisitLog.setVisitUrl(requestPath);
							sysVisitLog.setQueryString(queryString);

//							sysVisitLogManager.saveSysVisitLog(sysVisitLog);
						}
					} else {
						sysVisitLog = new SysVisitLog();
						sysVisitLog.setModuleCode(sysModule.getModuleCode());
						sysVisitLog.setUserCode(sysOperationLog
								.getOperaterCode());
						sysVisitLog.setVisitTime(DateUtil.now());
						sysVisitLog.setVisitUrl(requestPath);
						sysVisitLog.setQueryString(queryString);

//						sysVisitLogManager.saveSysVisitLog(sysVisitLog);
					}
				} catch (Exception ex) {
					log.error(ex);
				}
			*/}

			filterChain.doFilter(request, response);
		}
	}

	/**
	 * 验证权限
	 * 
	 * @param request
	 * @param response
	 * @param strAction
	 * @param requestPath
	 * @param sysModule
	 * @return int 0:通过 -1:通过,不记录数据 1:不通过,超时 2:不通过,无权限
	 */
	private int checkPower(HttpServletRequest request,
			SysModuleManager sysModuleManager, final String strAction,
			final String requestPath, final SysModule sysModule) {
		if (sysModule == null) {
			return 2;
		}
		// 判断是否有效
		if (sysModule.getIsActive() == null || sysModule.getIsActive() == 0) {
			// 如果此模块已经关闭
			return 2;
		}
		// TODO:未判断URL是否和strAction匹配,是否有漏洞?
		// Map<String, String> powerMap =
		// SessionLogin.getLoginUser(request).getPowerMap();

		/* 访问登录页面时跳过登录 */
		if (requestPath.equals("/payFiCreditCard.html")
				|| requestPath.equals("/jfi99billPosReceiveLogs.html")
				|| requestPath.equals("/jfi99billPosReceiveBackLogs.html")
				|| requestPath.equals("/jfiPay99billReceive.html")
				|| requestPath.equals("/mobilPayReceive.html")
				|| requestPath.equals("/jfiPayAlipayReceiveReturn.html")
				|| requestPath.equals("/jfiPayAlipayReceiveNotify.html")
				|| requestPath.equals("/login.html")
				|| requestPath.equals("/loginRedirect.html")
				|| requestPath.equals("/logout.jsp")
				|| requestPath.equals("/permissionDenied.html")
				|| requestPath.equals("/register.html")
				|| (requestPath.equals("/miSelectRecommendRef.html") && "register"
						.equals(strAction))
				|| requestPath.equals("/memberRegister.html")
				|| requestPath.equals("/memberGetPwd.html")
				|| requestPath.equals("/registerUSAgent.html")) {
			return -1;
		}

		/* 未登录或Session已超时时 */
		if (SessionLogin.getLoginUser(request).getUserCode() == null
				|| !SessionLogin.getLoginUser(request).isAuthorized()) {
			return 1;
		}

		/* 开始权限判断 */
		if (SessionLogin.getLoginUser(request).getIsAdmin()) {
			return 0;
		}

		boolean hasPermit = sysModuleManager.getSysUserPower(SessionLogin
				.getLoginUser(request), sysModule);

		if (!hasPermit) {
			log.error("strAction: " + strAction + " 不包含在用户的权限中");
			return 2;
		}

		return 0;
	}

	/**
	 * 检查requestPath是否在url中
	 * 
	 * @param requestPath
	 * @param url
	 * @return
	 */
	private boolean checkUrlPower(String requestPath, String url) {
		if (!StringUtils.isEmpty(url) && url.indexOf('?') > -1) {
			url = url.substring(0, url.indexOf('?'));
		}
		if (StringUtils.isEmpty(url) || !url.equals(requestPath)) {
			return false;
		}
		return true;
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	/**
	 * 初始化操作日志
	 * 
	 * @param request
	 * @param requestPath
	 * @return
	 */
	private SysOperationLog initSysOperationLog(HttpServletRequest request,
			String requestPath) {
		SysOperationLog sysOperationLog = new SysOperationLog();
		sysOperationLog.setVisitUrl(requestPath);

		String refererUrl = request.getHeader("Referer");
		if (StringUtils.isEmpty(refererUrl)
				|| !StringUtils.contains(refererUrl, "/sys_menu.html")) {
			sysOperationLog.setDoType(1);
		} else {
			sysOperationLog.setDoType(0);
		}
		StringBuffer sb = new StringBuffer();
		if ("post".equalsIgnoreCase(request.getMethod())) {
			sb.append("[POST]:\n");
		} else if ("get".equalsIgnoreCase(request.getMethod())) {
			sb.append("[GET]:\n");
		}
		// 获取提交过来的值
		Enumeration parametEnu = request.getParameterNames();
		while (parametEnu.hasMoreElements()) {
			String parameterName = (String) parametEnu.nextElement();
			String parameterValue = request.getParameter(parameterName);

			sb.append(parameterName);
			sb.append("=>");
			sb.append(parameterValue);
			sb.append("\n");
		}
		String strAction = request.getParameter("strAction");
		sb.append("action=" + strAction + "\n");
		sysOperationLog.setCompanyCode(SessionLogin.getLoginUser(request)
				.getCompanyCode());
		sysOperationLog.setIpAddr(RequestUtil.getIpAddr(request));
		sysOperationLog.setOperateData(sb.toString());
		if (SessionLogin.getOperatorUser(request) == null) {
			sysOperationLog.setOperaterCode(SessionLogin.getLoginUser(request)
					.getUserCode());
			sysOperationLog.setOperaterName(SessionLogin.getLoginUser(request)
					.getUserName());
		} else {
			sysOperationLog.setOperaterCode(SessionLogin.getOperatorUser(
					request).getUserCode());
			sysOperationLog.setOperaterName(SessionLogin.getOperatorUser(
					request).getUserName());
		}
		sysOperationLog.setUserCode(SessionLogin.getLoginUser(request)
				.getUserCode());
		sysOperationLog.setUserName(SessionLogin.getLoginUser(request)
				.getUserName());
		sysOperationLog.setOperateTime(DateUtil.now());
		sysOperationLog.setUserType(SessionLogin.getLoginUser(request)
				.getUserType());

		return sysOperationLog;
	}
	
	/**
	 * Modify By WUCF 20170521
	 * @param request
	 * @return
	 */
	public static boolean checkParamStr(ParameterRequestWrapper request) {
		String returnStr = "";
		Enumeration rnames = request.getParameterNames();
		for (Enumeration e = rnames; e.hasMoreElements();) {
			String thisName = e.nextElement().toString();
			String thisValue = request.getParameter(thisName);
			
			thisValue = StringEscapeUtils.unescapeHtml(thisValue);//先强制转html   
			thisValue = thisValue.replaceAll(".*([';]+|(--)+).*", "");
			
			request.setParameterValues(thisName, thisValue);
			if(!"strAction".equals(thisName)){
				if(sqlValidate(thisValue))
				{
					System.out.println("====err:"+thisName+"-"+thisValue);
					return false;
				}
			}
			returnStr += (thisName + "=" + thisValue + "&");
		}
		
		
		
		return true;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean sqlValidate(String str) {
		str = str.toLowerCase();//统一转为小写
		String badStr = "'|dbms|exec|execute|insert|select|delete|update|drop|*|%|master|truncate|" +
				"declare|sitename|net user|xp_cmdshell|%|*|function|procedure|package|" +
				"grant|group_concat|column_name|" +
				"information_schema.columns|table_schema|" +
				"master|where|declare|";//过滤掉的sql关键字，可以手动添加
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (str.indexOf(badStrs[i]) >= 0) {
				System.out.println("======str:"+str);
				return true;
			}
		}
		/*String regex1 = "/\\w*((\\%27)|(\\’))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix";
		String regex2 = "/exec(\\s|\\+)+(s|x)p\\w+/ix";
		if(str.matches(regex1) || str.matches(regex2)){
			return true;
		}*/
		return false;
	}
}