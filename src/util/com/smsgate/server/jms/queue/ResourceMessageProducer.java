package com.smsgate.server.jms.queue;

import javax.jms.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

import com.smsgate.server.jms.model.SmsSendLog;

public class ResourceMessageProducer {
	private Log log = LogFactory.getLog(getClass());
	private JmsTemplate template;

	private Queue destination;

	public void setTemplate(JmsTemplate template) {
		this.template = template;
	}

	public void setDestination(Queue destination) {
		this.destination = destination;
	}

	public void send(SmsSendLog smsSendLog) {
		log.debug("=======================================");
		log.debug("do send ......");
		log.debug(smsSendLog);
		long l1 = System.currentTimeMillis();
		template.convertAndSend(this.destination, smsSendLog);
		log
				.debug("send time:" + (System.currentTimeMillis() - l1) / 1000
						+ "s");
		log.debug("=======================================");
	}

}
