package com.joymain.jecs.log.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.CallbackException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.sys.model.SysDataLogKey;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.ContextUtil;

public class LogUtil {
	private static Log log = LogFactory.getLog(LogUtil.class);
	private static SessionFactory sessionFactory;
	private static final String PARENT_DIR = "/home/jeeu/data/logs/datalog/";
	
	
	private static final String SYMBOL = "@#$*&";//分割符号
	private static String path = "D:/";//文件路径
	private static String filenameTemp = "";//文件名称
	
	public static void setSessionFactory(SessionFactory sessionFactory) {
		LogUtil.sessionFactory = sessionFactory;
	}

	/**
	 * Returns an array of all fields used by this object from it's class and
	 * all superclasses.
	 * 
	 * @param objectClass
	 *            the class
	 * @param fields
	 *            the current field list
	 * @return an array of fields
	 */
	public static Field[] getAllFields(Class objectClass, Field[] fields) {

		Field[] newFields = objectClass.getDeclaredFields();

		int fieldsSize = 0;
		int newFieldsSize = 0;

		if (fields != null) {
			fieldsSize = fields.length;
		}

		if (newFields != null) {
			newFieldsSize = newFields.length;
		}

		Field[] totalFields = new Field[fieldsSize + newFieldsSize];

		if (fieldsSize > 0) {
			System.arraycopy(fields, 0, totalFields, 0, fieldsSize);
		}

		if (newFieldsSize > 0) {
			System.arraycopy(newFields, 0, totalFields, fieldsSize,
					newFieldsSize);
		}

		Class superClass = objectClass.getSuperclass();

		Field[] finalFieldsArray;

		if (superClass != null
				&& !superClass.getName().equals("java.lang.Object")) {
			finalFieldsArray = getAllFields(superClass, totalFields);
		} else {
			finalFieldsArray = totalFields;
		}

		return finalFieldsArray;

	}

	/**
	 * Gets the id of the persisted object
	 * 
	 * @param obj
	 *            the object to get the id from
	 * @return object Id
	 */
	public static Serializable getObjectId(Object obj) {

		Class objectClass = obj.getClass();
		Method[] methods = objectClass.getMethods();

		Field[] newFields = objectClass.getDeclaredFields();
		String idMethodName = null;
		if (newFields.length > 0) {
			String idFieldName = newFields[0].getName();
			idMethodName = "get" + idFieldName.substring(0, 1).toUpperCase()
					+ idFieldName.substring(1);
		}

		Serializable persistedObjectId = null;
		for (int ii = 0; ii < methods.length; ii++) {
			// If the method name equals 'getId' then invoke it to get the id of
			// the object.
			if (methods[ii].getName().equals(idMethodName)) {
				try {
					persistedObjectId = (Serializable) methods[ii].invoke(obj,
							null);
					break;
				} catch (Exception e) {
					log
							.warn("Audit Log Failed - Could not get persisted object id: "
									+ e.getMessage());
				}
			}
		}
		return persistedObjectId;
	}

	public static String javaToColumn(String modelName) {
		try {
			StringBuffer tableName = new StringBuffer();
			for (int i = 0; i < modelName.length(); i++) {
				if (Character.isUpperCase(modelName.charAt(i)) && i != 0) {
					tableName.append("_" + modelName.charAt(i));
				} else {
					tableName.append(modelName.charAt(i));
				}
			}
			return tableName.toString().toUpperCase();
		} catch (Exception e) {
			return modelName;
		}

	}

	public static void excuteLog(Object currentObject, Object previousObject,
			Serializable id, String[] propertyNames, String event) {

	}

	public static void logEvent(final SysDataLog sysDataLog,
			final List<SysDataLogKey> sysDataLogKeys, final String month) {
		 

		
		if (sysDataLog != null ) {
			
			Session session = null;
			Connection conn = null;
			PreparedStatement st = null;
			try {
				sessionFactory = (SessionFactory) ContextUtil.getSpringBeanByName(
						Constants.context, "sessionFactory2");
				session = sessionFactory.openSession();
				conn = session.connection();

				st = conn
						.prepareStatement("insert  /*+append+*/ into jsys_data_log"
								
								+ " values(seq_log.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				// Transaction tx = session.beginTransaction();
				// int i = 0;
				st.setString(1, sysDataLog.getChangeType());
				st.setString(2, sysDataLog.getTableName());
				st.setString(3, sysDataLog.getColumnName());
				st.setString(4, sysDataLog.getPidName());
				st.setString(5, sysDataLog.getPidValue());
				st.setString(6, sysDataLog.getBeforeChange());
				st.setString(7, sysDataLog.getAfterChange());
				st.setString(8, sysDataLog.getOperatorPerson());
				st.setTimestamp(9, sysDataLog.getOperatorTime());
				st.setString(10, sysDataLog.getModuleName());
				st.setString(11, sysDataLog.getHostName());
				st.setString(12, sysDataLog.getIpAddress());
				st.setString(13, sysDataLog.getPersonType());
				if(sysDataLog.getOperationLogId() == null){
					sysDataLog.setOperationLogId(-1L);
				}
				st.setLong(14, sysDataLog.getOperationLogId());
				st.setString(15, sysDataLog.getBid());
				st.setString(16, sysDataLog.getBidValue());

				st.execute();
				
				//Modify By WuCF 20140304 日志表，不再用，注释!
				/*if (sysDataLogKeys != null && sysDataLogKeys.size() > 0) {
					st = conn.prepareStatement("insert    into jsys_data_log_key_"
							+ month + " values(seq_log.nextval,?,?,?)");
					for (int i = 0; i < sysDataLogKeys.size(); i++) {
						if (sysDataLogKeys.get(i).getKeyValue() == null) {
							continue;
						}
						st.setLong(1, sysDataLog.getOperationLogId());
						st.setString(2, sysDataLogKeys.get(i).getKeyName());
						st.setString(3, sysDataLogKeys.get(i).getKeyValue());
						st.execute();
					}
				}*/

				session.flush();
				session.clear();
			} catch (Exception ex) {
				log.info("Error: ", ex);
			} finally {
				try {
					if (st != null) {
						st.close();
					}
					if (conn != null) {
						conn.close();
					}
					if (session != null) {
						session.close();
					}
				} catch (HibernateException ex) {
					throw new CallbackException(ex);
				} catch (SQLException e) {
					// e.printStackTrace();
					throw new CallbackException(e);
				}
			}
		}
	}

	public static void saveLog(Object currentObject, Object previousObject,
			String[] propertyNames, String id, String event, String className)
			throws Exception {
		
		SysUser sysUser = new SysUser();
		// Date d = new Date();
		DateFormat format = new java.text.SimpleDateFormat("yyyyMM");
		String month = format.format(new Date());
		
		Field[] fields = currentObject.getClass().getDeclaredFields();

		String pid = "";
		String bid = "";
		String bidValue = "";

		pid = LogConstants.TABLE_PID_MAP.get(currentObject.getClass()
				.getSimpleName());
		bid = LogConstants.TABLE_BID_MAP.get(currentObject.getClass()
				.getSimpleName());

		if (StringUtils.isNotEmpty(pid)) {
			Field f = currentObject.getClass().getDeclaredField(bid);
			f.setAccessible(true);
			bidValue = object2String(f.get(currentObject));
		}

		for (int i = 0; i < fields.length; i++) {

			String fieldName = fields[i].getName();

			if (fieldName.equals("id")) {
				continue;
			}
			if (fields[i].getType().getName().equals("java.util.Set")
					|| fields[i].getType().getName().equals("java.util.List")
					|| fields[i].getType().getName().startsWith(
							"com.joymain.jecs")) {
				continue;
			}
			fields[i].setAccessible(true);
			String currentState = object2String(fields[i].get(currentObject));
			String previousState = "";

			if (previousObject != null) {
				previousState = object2String(fields[i].get(previousObject));
			}
			if (!previousState.equals(currentState)) {
				
				SysDataLog logRecord = new SysDataLog();
				logRecord
						.setTableName(currentObject.getClass().getSimpleName());
				logRecord.setColumnName(fieldName);
				logRecord.setChangeType(event);
				if(ContextUtil.getResource("sessionLogin")!=null){
					sysUser = (SysUser) ContextUtil
					.getResource("sessionLogin");
				}
				logRecord.setPersonType(sysUser.getUserType());
				logRecord.setOperatorPerson(sysUser.getUserCode());
				logRecord.setOperatorTime(new Timestamp(System
						.currentTimeMillis()));
				logRecord.setAfterChange(currentState);
				logRecord.setBeforeChange(previousState);
				logRecord.setPidName(pid);// 主键
				logRecord.setPidValue(id);// 主键值
				logRecord.setIpAddress((String) ContextUtil.getResource("ip"));
				logRecord.setHostName((String) ContextUtil.getResource("host"));
				logRecord
						.setModuleName((String) ContextUtil.getResource("url"));
				
				logRecord.setOperationLogId(sysUser.getOperationLogId());
				logRecord.setBid(bid);
				logRecord.setBidValue(bidValue);
				logEvent(logRecord, null, month);//原始代码
//				logToFile(logRecord, null, month);//Modify By WuCF 20141010 写入txt
				
				/*DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMdd");
				filenameTemp = format2.format(new Date());//文件名以当前日期命名
				creatTxtFile(filenameTemp);
				writeTxtFile(getLogRecordStr(logRecord));*/
			}
		}
	}
	
	
	/**
	 * 得到返回的字符串，按指定格式分割
	 */
	public static String getLogRecordStr(SysDataLog logRecord){
		StringBuffer logRecordStr = new StringBuffer("");
		logRecordStr.append("logId:");
		logRecordStr.append(logRecord.getLogId());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("changeType:");
		logRecordStr.append(logRecord.getChangeType());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("tableName:");
		logRecordStr.append(logRecord.getTableName());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("columnName:");
		logRecordStr.append(logRecord.getColumnName());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("pidName:");
		logRecordStr.append(logRecord.getPidName());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("pidValue:");
		logRecordStr.append(logRecord.getPidValue());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("beforeChange:");
		logRecordStr.append(logRecord.getBeforeChange());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("afterChange:");
		logRecordStr.append(logRecord.getAfterChange());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("operatorPerson:");
		logRecordStr.append(logRecord.getOperatorPerson());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("operatorTime:");
		logRecordStr.append(logRecord.getOperatorTime());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("moduleName:");
		logRecordStr.append(logRecord.getModuleName());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("hostName:");
		logRecordStr.append(logRecord.getHostName());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("ipAddress:");
		logRecordStr.append(logRecord.getIpAddress());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("personType:");
		logRecordStr.append(logRecord.getPersonType());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("operationLogId:");
		logRecordStr.append(logRecord.getOperationLogId());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("bid:");
		logRecordStr.append(logRecord.getBid());
		logRecordStr.append(SYMBOL);
		logRecordStr.append("bidValue:");
		logRecordStr.append(logRecord.getBidValue());
		return logRecordStr.toString();
	}
	 
	/**
	 * 得到返回的字符串，按指定格式分割
	 * joson格式
	 */
	public static String getLogRecordStr2(SysDataLog logRecord){
		StringBuffer logRecordStr = new StringBuffer("");
		logRecordStr.append("{");
		logRecordStr.append("\"logId\":");
		logRecordStr.append(logRecord.getLogId());
		logRecordStr.append(",\"changeType\":\"");
		logRecordStr.append(logRecord.getChangeType());
		logRecordStr.append("\",\"tableName\":\"");
		logRecordStr.append(logRecord.getTableName());
		logRecordStr.append("\",\"columnName\":\"");
		logRecordStr.append(logRecord.getColumnName());
		logRecordStr.append("\",\"pidName\":");
		logRecordStr.append(logRecord.getPidName());
		logRecordStr.append(",\"pidValue\":\"");
		logRecordStr.append(logRecord.getPidValue());
		logRecordStr.append("\",\"beforeChange\":\"");
		logRecordStr.append(logRecord.getBeforeChange());
		logRecordStr.append("\",\"afterChange\":\"");
		logRecordStr.append(logRecord.getAfterChange());
		logRecordStr.append("\",\"operatorPerson\":\"");
		logRecordStr.append(logRecord.getOperatorPerson());
		logRecordStr.append("\",\"operatorTime\":");
		logRecordStr.append(logRecord.getOperatorTime());
		logRecordStr.append(",\"moduleName\":\"");
		logRecordStr.append(logRecord.getModuleName());
		logRecordStr.append("\",\"hostName\":\"");
		logRecordStr.append(logRecord.getHostName());
		logRecordStr.append("\",\"ipAddress\":\"");
		logRecordStr.append(logRecord.getIpAddress());
		logRecordStr.append("\",\"personType\":\"");
		logRecordStr.append(logRecord.getPersonType());
		logRecordStr.append("\",\"operationLogId\":");
		logRecordStr.append(logRecord.getOperationLogId());
		logRecordStr.append(",\"bid\":");
		logRecordStr.append(logRecord.getBid());
		logRecordStr.append(",\"bidValue\":\"");
		logRecordStr.append(logRecord.getBidValue());
		logRecordStr.append("\"}");
		return logRecordStr.toString();
	}
	
	/** 
	 * 创建文件 
	 * 
	 * @throws IOException 
	 */ 
	public static boolean creatTxtFile(String name) throws IOException { 
		boolean flag = false; 
		filenameTemp = path + name + ".log"; 
		File filename = new File(filenameTemp); 
		if (!filename.exists()) { 
			filename.createNewFile(); 
			flag = true; 
		} 
		return flag; 
	} 

	/** 
	 * 写文件 
	 * 
	 * @param newStr 
	 *            新内容 
	 * @throws IOException 
	 */ 
	public static boolean writeTxtFile (String newStr) 
	throws IOException { 
		// 先读取原有文件内容，然后进行写入操作 
		boolean flag = false; 
		String filein = newStr + "\r\n"; 
		String temp = ""; 

		FileInputStream fis = null; 
		InputStreamReader isr = null; 
		BufferedReader br = null; 

		FileOutputStream fos = null; 
		PrintWriter pw = null; 
		try { 
			// 文件路径 
			File file = new File(filenameTemp); 
			// 将文件读入输入流 
			fis = new FileInputStream(file); 
			isr = new InputStreamReader(fis); 
			br = new BufferedReader(isr); 
			StringBuffer buf = new StringBuffer(); 

			// 保存该文件原有的内容 
			for (int j = 1; (temp = br.readLine()) != null; j++) { 
				buf = buf.append(temp); 
				// System.getProperty("line.separator") 
				// 行与行之间的分隔符 相当于“\n” 
				buf = buf.append(System.getProperty("line.separator")); 
			} 
			buf.append(filein); 

			fos = new FileOutputStream(file); 
			pw = new PrintWriter(fos); 
			pw.write(buf.toString().toCharArray()); 
			pw.flush(); 
			flag = true; 
		} catch (IOException e1) { 
			// TODO 自动生成 catch 块 
			throw e1; 
		} finally { 
			if (pw != null) { 
				pw.close(); 
			} 
			if (fos != null) { 
				fos.close(); 
			} 
			if (br != null) { 
				br.close(); 
			} 
			if (isr != null) { 
				isr.close(); 
			} 
			if (fis != null) { 
				fis.close(); 
			} 
		} 
		return flag; 
	} 
	 
	/*public static void logToFile(final SysDataLog sysDataLog,
			final List<SysDataLogKey> sysDataLogKeys, final String day) throws Exception{
		
		if(sysDataLog != null){
			ObjectMapper objectMapper   = new ObjectMapper();
			 Writer strWriter = new StringWriter();
			 objectMapper.writeValue(strWriter, sysDataLog);
			FileUtils.writeStringToFile(new File(PARENT_DIR+day+"-datalog.log"), strWriter.toString()+"\n", "utf-8", true);
		}
		
	}*/ 

	public static String object2String(Object o) {
		if (o != null) {
			return o.toString();
		} else {
			return "";
		}
	}

	public static Object getPreviousObject(Class entity, Serializable id) {
		sessionFactory = (SessionFactory) ContextUtil.getSpringBeanByName(
				Constants.context, "sessionFactory2");
		Session session = null;
		Object obj = null;
		try {
			session = sessionFactory.openSession();
			obj = session.get(entity, id);
			session.flush();
			return obj;
		} catch (Exception ex) {
			log.error("Error: ", ex);
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (HibernateException ex) {
				throw new CallbackException(ex);
			}
		}
		return obj;
	}
}
