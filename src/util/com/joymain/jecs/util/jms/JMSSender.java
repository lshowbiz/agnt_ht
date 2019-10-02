package com.joymain.jecs.util.jms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;




public class JMSSender {

	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

	public void sendObjecsMessage(final Object jmsObject){
		jmsTemplate.convertAndSend(jmsObject);
	}
	public void sendTextMessage(final String message) {
		jmsTemplate.convertAndSend(message);
	}

}
