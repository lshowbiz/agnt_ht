package com.ecap.activemq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.support.converter.MessageConverter;


public class ActiveMQMessageConverter implements MessageConverter {
	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jms.support.converter.MessageConverter#toMessage(java.lang.Object,
	 *      javax.jms.Session)
	 */
	public Message toMessage(Object obj, Session session) throws JMSException {
		// check Type
		if (obj instanceof HashMap) {
			ActiveMQObjectMessage objMsg = (ActiveMQObjectMessage) session.createObjectMessage();
			HashMap map = new HashMap();
			try {
				// Order,Order,Product must implements Seralizable
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(obj);
				bos.close();
				map.put("HashMap", bos.toByteArray());
				objMsg.setObjectProperty("Map", map);
				objMsg.setStringProperty("JMSUserID", "12345678"); // 消息所属的用户编码
				objMsg.setStringProperty("JMSAppID", "12345678"); // 消息所属的应用程序编码
			} catch (IOException e) {
				e.printStackTrace();
			}
			return objMsg;
		} else {
			throw new JMSException("Object:[" + obj + "] is not HashMap");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jms.support.converter.MessageConverter#fromMessage(javax.jms.Message)
	 */
	public Object fromMessage(Message msg) throws JMSException {
		if (msg instanceof ObjectMessage) {

			// 消息 header 中常有的 属性定义
			System.out.println("消息编码：" + msg.getJMSMessageID());
			System.out.println("目标对象：" + msg.getJMSDestination());
			System.out.println("消息模式：" + msg.getJMSDeliveryMode()); // 消息的模式
																	// 分为持久模式和非持久模式,
																	// 默认是
																	// 非持久的模式（2）

			long sendTime = msg.getJMSTimestamp();
			Date date = new Date(sendTime);
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String temp = f.format(date);

			System.out.println("消息发送时间：" + temp);
			System.out.println("消息失效时间：" + msg.getJMSExpiration()); // 这里是一个 整型值
																	// 0 表示
																	// 该消息永远不会过期
			System.out.println("消息优先级：" + msg.getJMSPriority()); // 优先级 0~9,
																	// 0 表示最低
			System.out.println("关联编码：" + msg.getJMSCorrelationID());

			System.out.println("回复消息的地址：" + msg.getJMSReplyTo()); // 回复消息的地址(Destination类型),由发送者设定
			System.out.println("消息类型：" + msg.getJMSType()); // jms 不使用该字段，
															// 一般类型是由 用户自己定义
			System.out.println("是否签收过：" + msg.getJMSRedelivered()); // 如果是 真
																	// ,表示客户端收到过该消息,但是并没有签收

			// 消息属性 (properties)
			System.out.println("用户编码：" + msg.getStringProperty("JMSXUserID"));
			System.out.println("应用程序编码：" + msg.getStringProperty("JMSXApp1ID"));
			System.out.println("已经尝试发送消息的次数："
					+ msg.getStringProperty("JMSXDeliveryCount"));

			HashMap map = (HashMap) ((ObjectMessage) msg)
					.getObjectProperty("Map");
			try {
				// Order,Order,Product must implements Seralizable
				ByteArrayInputStream bis = new ByteArrayInputStream(
						(byte[]) map.get("HashMap"));
				ObjectInputStream ois = new ObjectInputStream(bis);
				return ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			throw new JMSException("Msg:[" + msg + "] is not Map");
		}
	}
}
