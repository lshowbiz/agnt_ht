package com.gt.ejb.jms;

import java.io.Serializable;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 * JMS
 * @author Alvin
 *
 */
public class JmsEjbUtil {
	
	private String destination;

	private InitialContext ctx;
	
	private QueueSession session;
	
	private QueueSender sender;
	
	private QueueConnection connection;
	
	/**
	 * 设置destination
	 * @param destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
		try {
	        // 创建Destination对象
			Queue queue = (Queue)this.ctx.lookup(this.destination);
	        // 创建发送者
	        this.sender = this.session.createSender(queue);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JmsEjbUtil(){
		jmsInitialized();
	}
	
	/**
	 * 关闭会话
	 * @return
	 */
	public boolean close(){
        //关闭会话
        try {
			this.session.close();
	        this.connection.close();
	        return true;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
	}
	
	/**
	 * 发送消息对象(true:成功,false:失败)
	 * @param list
	 * @return
	 */
	public boolean sendObjectMessage(List list){
        ObjectMessage msg;
		try {
			msg = this.session.createObjectMessage();
	        msg.setObject((Serializable) list);
			this.sender.send(msg);//发送消息
			return true;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 发送消息对象(true:成功,false:失败)
	 * @param list
	 * @return
	 */
	public boolean sendStringMessage(String str){
		try {
			TextMessage msg = this.session.createTextMessage(str);
			this.sender.send(msg);//发送消息
			return true;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 初始化JMS
	 */
	private void jmsInitialized(){
		java.util.Properties props = new java.util.Properties();
		props.put("java.naming.rmi.security.manager", "yes");
		props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		props.setProperty("java.naming.provider.url","10.10.6.13:1099");
		props.setProperty("java.naming.factory.url.pkgs","org.jboss.naming");
		try {
			this.ctx = new InitialContext(props);
	        // 获取QueueConnectionFactory对象
	        QueueConnectionFactory factory = (QueueConnectionFactory)ctx.lookup("ConnectionFactory");
	        // 创建QueueConnection对象
	        this.connection = factory.createQueueConnection();
	        // 创建QueueSession对象
	        this.session = this.connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
