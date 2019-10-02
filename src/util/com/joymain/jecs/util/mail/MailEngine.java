package com.joymain.jecs.util.mail;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MailEngine {
	protected static final Log log = LogFactory.getLog(MailEngine.class);

	// private VelocityEngine velocityEngine;
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	static Configuration cfg;
	static {
		cfg = new Configuration();
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setNumberFormat("0.##########");
	}

	@SuppressWarnings("finally")
	public static String templateParse(String templateName,
			Map<String, String> root) {
		StringWriter writer = new StringWriter();
		String templateStr = "";
		try {
			Template t = cfg.getTemplate(templateName);
			t.process(root, writer);
			templateStr = writer.toString();
			writer.close();
		} catch (IOException ioe) {
			System.out.println("templateParse(ioe)==="+ioe.getMessage());
			log.error(ioe.getMessage(), ioe);
		} catch (TemplateException te) {
			System.out.println("templateParse(te)==="+te.getMessage());
			log.error(te.getMessage(), te);
		} finally {
			return templateStr;
		}
	}

	/**
	 * 发送邮件
	 * 
	 * @param emailAddress
	 *            收件人Email地址的数组
	 * @param fromEmail
	 *            寄件人Email地址, null为默认寄件人
	 * @param bodyText
	 *            邮件正文
	 * @param subject
	 *            邮件主题
	 * @param attachmentName
	 *            附件名
	 * @param resource
	 *            附件
	 * @throws MessagingException
	 */
	public void sendMessage(String[] emailAddresses, String fromEmail,
			String bodyText, String subject, String attachmentName,
			ClassPathResource resource) throws MessagingException {
		MimeMessage message = ((JavaMailSenderImpl) mailSender)
				.createMimeMessage();

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(emailAddresses);
		if (fromEmail != null) {
			helper.setFrom(fromEmail);
		}
		helper.setText(bodyText, true);
		helper.setSubject(subject);

		if (attachmentName != null && resource != null)
			helper.addAttachment(attachmentName, resource);

		((JavaMailSenderImpl) mailSender).send(message);
	}

	/**
	 * 发送简单邮件
	 * 
	 * @param msg
	 */
	public void send(SimpleMailMessage msg) {
		try {
			((JavaMailSenderImpl) mailSender).send(msg);
		} catch (MailException ex) {
			// log it and go on
			log.error(ex.getMessage());
		}
	}

	/**
	 * 使用模版发送HTML格式的邮件
	 * 
	 * @param msg
	 *            装有to,from,subject信息的SimpleMailMessage
	 * @param templateName
	 *            模版名,模版根路径已在配置文件定义于freemakarengine中
	 * @param model
	 *            渲染模版所需的数据
	 */
	public void send(SimpleMailMessage msg, String templateName, Map model) {
		// 生成html邮件内容
		// String content = generateEmailContent(templateName, model);
		String content = templateParse(templateName, model);
		MimeMessage mimeMsg = null;
		try {
			mimeMsg = ((JavaMailSenderImpl) mailSender).createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true,
					"utf-8");
			helper.setTo(msg.getTo());

			if (msg.getSubject() != null)
				helper.setSubject(msg.getSubject());

			if (msg.getFrom() != null)
				helper.setFrom(msg.getFrom());

			helper.setText(content, true);

			((JavaMailSenderImpl) mailSender).send(mimeMsg);
		} catch (MessagingException ex) {
			System.out.println("send()==="+ex.getMessage());
			log.error(ex.getMessage(), ex);
		}

	}

}
