package com.ecap.activemq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Queue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.UncategorizedJmsException;
import org.springframework.jms.core.JmsTemplate;


public class EcRegisterSender {
	private JmsTemplate jmsTemplate;

	private Queue destination;

	private String testMode;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(Queue destination) {
		this.destination = destination;
	}

	public void send(HashMap hashMap) {
		if("false".equals(testMode)){
			try {
				jmsTemplate.convertAndSend(this.destination, hashMap);
			} catch (UncategorizedJmsException e) {
				ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext-jms-activemq.xml");
				jmsTemplate=(JmsTemplate) applicationContext.getBean("jmsActiveMQTemplate");
				jmsTemplate.convertAndSend(this.destination, hashMap);
			}
		}
	}

	public void setTestMode(String testMode) {
		this.testMode = testMode;
	}

}
