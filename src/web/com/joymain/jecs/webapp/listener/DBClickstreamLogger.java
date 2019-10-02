package com.joymain.jecs.webapp.listener;

import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.clickstream.Clickstream;
import com.opensymphony.clickstream.ClickstreamRequest;
import com.opensymphony.clickstream.logger.ClickstreamLogger;
import com.joymain.jecs.sys.model.SysClickLog;
import com.joymain.jecs.sys.service.SysClickLogManager;
import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 保存用户访问记录
 * @author Aidy.Q
 * 
 */
public class DBClickstreamLogger implements ClickstreamLogger {
	private static final Log log = LogFactory.getLog(DBClickstreamLogger.class);

	public void log(Clickstream clickstream) {
		if (clickstream == null)
			return;

		/*StringBuffer output = new StringBuffer();

		String hostname = clickstream.getHostname();
		HttpSession session = clickstream.getSession();
		String initialReferrer = clickstream.getInitialReferrer();
		Date start = clickstream.getStart();
		Date lastRequest = clickstream.getLastRequest();

		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		SysClickLogManager sysClickLogManager = (SysClickLogManager) ctx.getBean("sysClickLogManager");

		output.append("Clickstream for: " + hostname + "\n");
		output.append("Session ID: " + (session != null ? session.getId() + "" : "") + "\n");
		output.append("Initial Referrer: " + initialReferrer + "\n");
		output.append("Stream started: " + start + "\n");
		output.append("Last request: " + lastRequest + "\n");

		long streamLength = lastRequest.getTime() - start.getTime();

		output.append("Stream length:" + (streamLength > 3600000 ? " " + (streamLength / 3600000) + " hours" : "") + (streamLength > 60000 ? " " + ((streamLength / 60000) % 60) + " minutes" : "") + (streamLength > 1000 ? " " + ((streamLength / 1000) % 60) + " seconds" : "") + "\n");

		int count = 0;
		for (Iterator iterator = clickstream.getStream().iterator(); iterator.hasNext();) {
			try {
				ClickstreamRequest request = (ClickstreamRequest) iterator.next();
				count++;
				output.append(count + ": " + request + (iterator.hasNext() ? "\n" : ""));

				String contextName = session.getServletContext().getServletContextName();
				String reuqestURI = request.getRequestURI().replaceAll("/" + contextName, "");
				SysClickLog sysClickLog = new SysClickLog();
				sysClickLog.setContent(output.toString());
				sysClickLog.setHostName(hostname);
				sysClickLog.setIpAddress(hostname);
				sysClickLog.setRemoteUser(request.getRemoteUser());
				sysClickLog.setLogType("TYPE_OPER");//
				sysClickLog.setClickUri(reuqestURI);
				sysClickLog.setRunDate(request.getTimestamp());
				sysClickLog.setIsValid(new Integer(1));
				sysClickLogManager.saveSysClickLog(sysClickLog);
			} catch (Exception ex) {
				
			}
		}
		log.debug(output);*/
	}
}
