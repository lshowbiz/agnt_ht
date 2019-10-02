package com.smsgate.server.jms.queue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.smsgate.server.jms.model.SmsSendLog;

public class Tt extends HttpServlet {
	private static Log log = LogFactory.getLog(Tt.class);

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		System.out.println("----------------begin");
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		ResourceMessageProducer resourceMessageProducer = (ResourceMessageProducer) context
				.getBean("resourceMessageProducer");
		SmsSendLog smsSendLog = new SmsSendLog();
		smsSendLog.setMobile("13822258592");
		smsSendLog.setSendMsg("你好啊");
		smsSendLog.setSendNum("1232");
		smsSendLog.setStatus("1");
		smsSendLog.setSslId(new Long(123456));
		smsSendLog.setUserCode("123213123");

		resourceMessageProducer.send(smsSendLog);
		System.out.println("----------------end");
	}

}
