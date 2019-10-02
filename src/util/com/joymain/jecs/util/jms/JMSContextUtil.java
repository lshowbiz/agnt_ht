package com.joymain.jecs.util.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JMSContextUtil {
	public static ApplicationContext ac;
	public static ApplicationContext getAc() {
		if(ac==null){
			ac = new ClassPathXmlApplicationContext("applicationContext-jms.xml");
		}
		return ac;
	}
	public static JMSSender getJMSSender(){
		return (JMSSender) getAc().getBean("jmsSender");
	}
	public static JMSSender getJMSSender(String senderName){
		return (JMSSender) getAc().getBean(senderName);
	}
	public static Object getBeanByName(String beanName){
		return getAc().getBean(beanName);
	}
	public static void resetContext(){
		ac=null;
	}
}
