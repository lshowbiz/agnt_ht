package com.joymain.jecs.util.mail;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateUtil {
	private final static Log log = LogFactory.getLog(TemplateUtil.class);

	static Configuration cfg;
	static {
		cfg = new Configuration();
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setNumberFormat("0.##########");
	}

	@SuppressWarnings("finally")
	public static String templateParse(String templateName, Map<String, String> root) {
		StringWriter writer = new StringWriter();
		String templateStr = "";
		try {
			Template t = cfg.getTemplate(templateName);
			t.process(root, writer);
			templateStr = writer.toString();
			writer.close();
		} catch (IOException ioe) {
			log.error(ioe.getMessage(), ioe);
		} catch (TemplateException te) {
			log.error(te.getMessage(), te);
		} finally {
			return templateStr;
		}
	}

	public static void main(String[] args) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("userCode", "11111");
		model.put("userName", "22222");
		model.put("verifycodeMd5", "12321321");
		model.put("curDate", "12312321");
		System.out.println(TemplateUtil.templateParse("email.ftl",  model));
	}
}
