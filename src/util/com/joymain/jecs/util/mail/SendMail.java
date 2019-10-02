package com.joymain.jecs.util.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


public class SendMail {
	protected static final Log log = LogFactory.getLog(SendMail.class);
	public static ApplicationContext ac;
//	public static final String from = "services@hwking.com";
//	public static final String hostUrl = "http://localhost:8080/ecap";

	public static ApplicationContext getAc() {
		if (ac == null) {
			ac = new ClassPathXmlApplicationContext(
					"applicationContext-mail.xml");
		}
		return ac;
	}

	public static void sendCreateMemberMail(String to, String subject,String userName, String userCode,String modulePage) throws Exception {

		SimpleMailMessage message = (SimpleMailMessage) getAc().getBean(
				"mailMessage");
		message.setTo(to);
		message.setSubject(subject);

		Map<String, String> model = new HashMap<String, String>();
		model.put("userCode", userCode);
		model.put("userName", userName);

		MailEngine engine = (MailEngine) getAc().getBean("mailEngine");
		// FreeMaker模板
		engine.send(message, modulePage, model);
		log.info("send mail to " + to + " OK!");
	}

}
