package com.smsgate.server.jms.queue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.support.converter.MessageConverter;

import com.smsgate.server.jms.model.SmsSendLog;

public class ResourceMessageConverter implements MessageConverter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jms.support.converter.MessageConverter#toMessage(
	 * java.lang.Object, javax.jms.Session)
	 */
	// public Message toMessage(Object obj, Session session) throws JMSException
	// {
	// // check Type
	// if (obj instanceof SmsSendLog) {
	// ActiveMQObjectMessage objMsg = (ActiveMQObjectMessage) session
	// .createObjectMessage();
	// Map<String, SmsSendLog> map = new HashMap<String, SmsSendLog>();
	// map.put("SmsSendLog", (SmsSendLog) obj);
	// objMsg.setObjectProperty("Map", map);
	// return objMsg;
	// } else {
	// throw new JMSException("Object:[" + obj + "] is not SmsSendLog");
	// }
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jms.support.converter.MessageConverter#fromMessage
	 * (javax.jms.Message)
	 */
	// public Object fromMessage(Message msg) throws JMSException {
	// if (msg instanceof ObjectMessage) {
	// return ((Map) ((ObjectMessage) msg).getObjectProperty("Map"))
	// .get("SmsSendLog");
	// } else {
	// throw new JMSException("Msg:[" + msg + "] is not Map");
	// }
	// }

	public Message toMessage(Object obj, Session session) throws JMSException {
		// check Type
		if (obj instanceof SmsSendLog) {
			ActiveMQObjectMessage objMsg = (ActiveMQObjectMessage) session
					.createObjectMessage();
			Map<String, byte[]> map = new HashMap<String, byte[]>();
			byte[] bytes = null;
			ByteArrayOutputStream buf = null;
			ObjectOutputStream o = null;
			try {
				buf = new ByteArrayOutputStream();
				o = new ObjectOutputStream(buf);
				o.writeObject(obj);
				bytes = buf.toByteArray();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (o != null) {
					try {
						o.flush();
						o.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (buf != null) {
					try {
						buf.flush();
						buf.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			map.put("Order", bytes);
			objMsg.setObjectProperty("Map", map);
			return objMsg;
		} else {
			throw new JMSException("Object:[" + obj + "] is not Order");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jms.support.converter.MessageConverter#fromMessage
	 * (javax.jms.Message)
	 */
	public Object fromMessage(Message msg) throws JMSException {
		if (msg instanceof ObjectMessage) {
			if (msg != null) {
				Object obj = null;
				byte[] bytes = (byte[]) ((Map) ((ObjectMessage) msg)
						.getObjectProperty("Map")).get("Order");
				ObjectInputStream in = null;
				try {
					in = new ObjectInputStream(new ByteArrayInputStream(bytes));
					obj = in.readObject();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				return obj;
			}
		}
		return msg;
	}

}
