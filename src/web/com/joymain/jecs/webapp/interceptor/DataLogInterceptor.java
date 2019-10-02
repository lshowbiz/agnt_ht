package com.joymain.jecs.webapp.interceptor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import com.joymain.jecs.model.BaseObject;
import com.joymain.jecs.sys.model.SysClickLog;
import com.joymain.jecs.sys.model.SysConfigValue;
import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.sys.model.SysDataLogKey;
import com.joymain.jecs.sys.model.SysId;
import com.joymain.jecs.sys.model.SysOperationLog;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysVisitLog;
import com.joymain.jecs.util.bean.ContextUtil;

/**
 * 数据变更日志拦截器，实现hibernate提供的Interceptor部分接口
 * @author Aidy.Q
 * @version 1.2
 */

public class DataLogInterceptor implements Interceptor {
	private static Log log = LogFactory.getLog(DataLogInterceptor.class);
	private static final String UPDATE = "update";
	private static final String INSERT = "insert";
	private static final String DELETE = "delete";

	/*private Set<SysDataLog> inserts = Collections.synchronizedSet(new HashSet<SysDataLog>());
	private Set<SysDataLog> updates = Collections.synchronizedSet(new HashSet<SysDataLog>());
	private Set<SysDataLog> deletes = Collections.synchronizedSet(new HashSet<SysDataLog>());*/

	private static List<Class<?>> noAudits = new ArrayList<Class<?>>();
	private static List<String> needAuditColumns=new ArrayList<String>();
	static {
		noAudits.add(SysDataLog.class);
		noAudits.add(SysClickLog.class);
		noAudits.add(SysId.class);
		noAudits.add(SysOperationLog.class);
		noAudits.add(SysVisitLog.class);
		
		needAuditColumns.add("ACCOUNT_CODE");
		needAuditColumns.add("ADVICE_CODE");
		needAuditColumns.add("AGENT_NO");
		needAuditColumns.add("AR_NO");
		needAuditColumns.add("AS_NO");
		needAuditColumns.add("ASSIST_ORDER_NO");
		needAuditColumns.add("CHARACTER_CODE");
		needAuditColumns.add("CHARACTER_KEY");
		needAuditColumns.add("CO_NO");
		needAuditColumns.add("COMPANY_CODE");
		needAuditColumns.add("COMPANY_NO");
		needAuditColumns.add("CONFIG_CODE");
		needAuditColumns.add("COUNTRY_CODE");
		needAuditColumns.add("CURRENCY_CODE");
		needAuditColumns.add("EW_NO");
		needAuditColumns.add("FW_NO");
		needAuditColumns.add("ID_CODE");
		needAuditColumns.add("LIST_CODE");
		needAuditColumns.add("MEMBER_NO");
		needAuditColumns.add("MEMBER_ORDER_NO");
		needAuditColumns.add("MODULE_CODE");
		needAuditColumns.add("OW_NO");
		needAuditColumns.add("PRODUCT_NO");
		needAuditColumns.add("REGION_CODE");
		needAuditColumns.add("RO_NO");
		needAuditColumns.add("ROLE_CODE");
		needAuditColumns.add("RP_NO");
		needAuditColumns.add("S_NO");
		needAuditColumns.add("SR_NO");
		needAuditColumns.add("STATE_PROVINCE_CODE");
		needAuditColumns.add("USER_CODE");
		needAuditColumns.add("W_WEEK");
		needAuditColumns.add("WR_NO");
		needAuditColumns.add("WSO_NO");
	}

	public void afterTransactionBegin(Transaction arg0) {
		// TODO Auto-generated method stub

	}

	public void afterTransactionCompletion(Transaction arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeTransactionCompletion(Transaction arg0) {
		// TODO Auto-generated method stub

	}

	public int[] findDirty(Object arg0, Serializable arg1, Object[] arg2, Object[] arg3, String[] arg4, Type[] arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getEntity(String arg0, Serializable arg1) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEntityName(Object arg0) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object instantiate(String arg0, EntityMode arg1, Serializable arg2) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean isTransient(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onCollectionRecreate(Object arg0, Serializable arg1) throws CallbackException {
		// TODO Auto-generated method stub

	}

	public void onCollectionRemove(Object arg0, Serializable arg1) throws CallbackException {
		// TODO Auto-generated method stub

	}

	public void onCollectionUpdate(Object arg0, Serializable arg1) throws CallbackException {
		// TODO Auto-generated method stub

	}

	public void onDelete(Object obj, Serializable id, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		if (obj instanceof BaseObject) {
			if (noAudits.contains(obj.getClass())) {
				return;
			}
			try {
				Class objectClass = obj.getClass();
				String className = objectClass.getName();
				String[] tokens = className.split("\\.");
				int lastToken = tokens.length - 1;
				className = tokens[lastToken];

				logChanges(obj, null, null, id.toString(), DELETE, className);

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean onFlushDirty(Object obj, Serializable arg1, Object[] arg2, Object[] arg3, String[] arg4, Type[] arg5) throws CallbackException {
		if (obj instanceof BaseObject) {
			if (noAudits.contains(obj.getClass())) {
				return false;
			}
			if (ContextUtil.getContext() == null) {
				return false;
			}
			DataLog log = (DataLog) ContextUtil.getContext().getBean("dataLog");

			Class objectClass = obj.getClass();
			String className = objectClass.getName();

			String[] tokens = className.split("\\.");
			int lastToken = tokens.length - 1;
			className = tokens[lastToken];

			try {
				// Use the id and class to get the pre-update state from the
				// database
				Serializable persistedObjectId = getObjectId(obj);
				Object preUpdateState = log.getObject(objectClass, persistedObjectId);

				logChanges(obj, preUpdateState, null, persistedObjectId.toString(), UPDATE, className);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean onLoad(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		return false;
	}

	public String onPrepareStatement(String arg0) {
		return arg0;
	}

	/**
	 * 插入记录后执行
	 */
	public boolean onSave(Object obj, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		if (obj instanceof BaseObject) {
			if (noAudits.contains(obj.getClass())) {
				return false;
			}
			if (ContextUtil.getContext() == null) {
				return false;
			}
			Class objectClass = obj.getClass();
			String className = objectClass.getName();

			String[] tokens = className.split("\\.");
			int lastToken = tokens.length - 1;
			className = tokens[lastToken];

			try {
				// Use the id and class to get the pre-update state from the
				Serializable persistedObjectId = getObjectId(obj);

				logChanges(obj, null, null, persistedObjectId.toString(), INSERT, className);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void postFlush(Iterator iter) throws CallbackException {
		/*try {
			if (ContextUtil.getContext() != null) {
				DataLog log = (DataLog) ContextUtil.getContext().getBean("dataLog");
				if (log != null) {
					log.logEvent(deletes, updates, inserts);
				}
			}

		} catch (Exception e) {
			// throw new CallbackException(e);
			log.error(e.getMessage(), e);
		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();
		}*/

	}

	public void preFlush(Iterator arg0) throws CallbackException {
		// TODO Auto-generated method stub

	}

	private void logChanges(Object newObject, Object existingObject, Object parentObject, String persistedObjectId, String event, String className) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (ContextUtil.getContext() == null) {
			return;
		}
		DataLog log = (DataLog) ContextUtil.getContext().getBean("dataLog");
		
		Class objectClass = newObject.getClass();
		// 获取所有的包括父对象的属性
		Field[] fields = getAllFields(objectClass, null);

		// Iterate through all the fields in the object

		fieldIteration: for (int ii = 0; ii < fields.length; ii++) {

			// make private fields accessible so we can access their values
			fields[ii].setAccessible(true);

			// 如果当前属性为 static, transient, final则不处理
			if (Modifier.isTransient(fields[ii].getModifiers()) || Modifier.isFinal(fields[ii].getModifiers()) || Modifier.isStatic(fields[ii].getModifiers())) {
				continue fieldIteration;
			}
			String fieldName = fields[ii].getName();
			if (!fieldName.equals("id")) {
				if (fields[ii].getType().getName().equals("java.util.Set") || fields[ii].getType().getName().equals("java.util.List") || fields[ii].getType().getName().startsWith("com.joymain.jecs")) {
					continue fieldIteration;
				}

				String propertyNewState;
				String propertyPreUpdateState;

				// 获取各属性对应的值
				try {
					Object objPropNewState = fields[ii].get(newObject);
					if (objPropNewState != null) {
						propertyNewState = objPropNewState.toString();
					} else {
						propertyNewState = "";
					}

				} catch (Exception e) {
					propertyNewState = "";
				}

				if (event.equals(UPDATE)) {
					try {
						Object objPreUpdateState = fields[ii].get(existingObject);

						if (objPreUpdateState != null) {
							propertyPreUpdateState = objPreUpdateState.toString();
						} else {
							propertyPreUpdateState = "";
						}
					} catch (Exception e) {
						propertyPreUpdateState = "";
					}

					String dbFieldName=modelToTable(fieldName);
					if (propertyNewState.equals(propertyPreUpdateState)) {
						//如果未更改,则跳过
						continue;
					} else {
						SysDataLog logRecord = new SysDataLog();
						logRecord.setTableName(modelToTable(className));
						logRecord.setColumnName(dbFieldName);
						logRecord.setChangeType(event);
						logRecord.setPersonType(((SysUser)ContextUtil.getResource("sessionLogin")).getUserType());
						logRecord.setOperatorPerson(((SysUser)ContextUtil.getResource("sessionLogin")).getUserCode());
						logRecord.setOperatorTime(new Timestamp(System.currentTimeMillis()));
						logRecord.setAfterChange(propertyNewState);
						logRecord.setBeforeChange(propertyPreUpdateState);
						logRecord.setPidName(modelToTable(fields[0].getName()));// 主键
						logRecord.setPidValue(persistedObjectId);// 主键值
						logRecord.setIpAddress((String) ContextUtil.getResource("ip"));
						logRecord.setHostName((String) ContextUtil.getResource("host"));
						logRecord.setModuleName((String) ContextUtil.getResource("url"));
						logRecord.setOperationLogId(((SysUser)ContextUtil.getResource("sessionLogin")).getOperationLogId());
						
						List<SysDataLogKey> sysDataLogKeys=new ArrayList<SysDataLogKey>();
						for (int kk = 0; kk < fields.length; kk++) {
							String keyName = modelToTable(fields[kk].getName());
							if(needAuditColumns.contains(keyName)){
								try {
									Object keyValue = fields[kk].get(existingObject);
									if (keyValue != null) {
										SysDataLogKey sysDataLogKey=new SysDataLogKey();
										sysDataLogKey.setKeyName(keyName);
										sysDataLogKey.setKeyValue(keyValue.toString());
										sysDataLogKeys.add(sysDataLogKey);
									} else {
										continue;
									}

								} catch (Exception e) {
									continue;
								}
								
							}
						}
						log.logEvent(logRecord,sysDataLogKeys,((SysUser)ContextUtil.getResource("sessionLogin")).getDataMonth());
						
						//updates.add(logRecord);
					}
				} else if (event.equals(DELETE)) {
					Object returnValue = fields[ii].get(newObject);

					SysDataLog logRecord = new SysDataLog();
					logRecord.setTableName(modelToTable(className));
					logRecord.setColumnName(modelToTable(fieldName));
					logRecord.setChangeType(event);
					logRecord.setPersonType(((SysUser)ContextUtil.getResource("sessionLogin")).getUserType());
					logRecord.setOperatorPerson(((SysUser)ContextUtil.getResource("sessionLogin")).getUserCode());
					logRecord.setOperatorTime(new Timestamp(System.currentTimeMillis()));
					logRecord.setAfterChange("");
					if (returnValue != null) logRecord.setBeforeChange(returnValue.toString());
					logRecord.setPidName(modelToTable(fields[0].getName()));// 主键
					if (persistedObjectId != null) logRecord.setPidValue(persistedObjectId);// 主键值
					logRecord.setIpAddress((String) ContextUtil.getResource("ip"));
					logRecord.setHostName((String) ContextUtil.getResource("host"));
					logRecord.setModuleName((String) ContextUtil.getResource("url"));
					logRecord.setOperationLogId(((SysUser)ContextUtil.getResource("sessionLogin")).getOperationLogId());

					//deletes.add(logRecord);
					List<SysDataLogKey> sysDataLogKeys=new ArrayList<SysDataLogKey>();
					for (int kk = 0; kk < fields.length; kk++) {
						String keyName = modelToTable(fields[kk].getName());
						if(needAuditColumns.contains(keyName)){
							try {
								Object keyValue = fields[kk].get(existingObject);
								if (keyValue != null) {
									SysDataLogKey sysDataLogKey=new SysDataLogKey();
									sysDataLogKey.setKeyName(keyName);
									sysDataLogKey.setKeyValue(keyValue.toString());
									sysDataLogKeys.add(sysDataLogKey);
								} else {
									continue;
								}

							} catch (Exception e) {
								continue;
							}
							
						}
					}
					log.logEvent(logRecord,sysDataLogKeys,((SysUser)ContextUtil.getResource("sessionLogin")).getDataMonth());

				} else if (event.equals(INSERT)) {
					SysDataLog logRecord = new SysDataLog();
					logRecord.setTableName(modelToTable(className));
					logRecord.setColumnName(modelToTable(fieldName));
					logRecord.setChangeType(event);
					logRecord.setPersonType(((SysUser)ContextUtil.getResource("sessionLogin")).getUserType());
					logRecord.setOperatorPerson(((SysUser)ContextUtil.getResource("sessionLogin")).getUserCode());
					logRecord.setOperatorTime(new Timestamp(System.currentTimeMillis()));
					logRecord.setAfterChange(propertyNewState);
					logRecord.setBeforeChange("");
					logRecord.setPidName(modelToTable(fields[0].getName()));// 主键
					logRecord.setPidValue(persistedObjectId);// 主键值
					logRecord.setIpAddress((String) ContextUtil.getResource("ip"));
					logRecord.setHostName((String) ContextUtil.getResource("host"));
					logRecord.setModuleName((String) ContextUtil.getResource("url"));
					logRecord.setOperationLogId(((SysUser)ContextUtil.getResource("sessionLogin")).getOperationLogId());

					//inserts.add(logRecord);
					List<SysDataLogKey> sysDataLogKeys=new ArrayList<SysDataLogKey>();
					for (int kk = 0; kk < fields.length; kk++) {
						String keyName = modelToTable(fields[kk].getName());
						if(needAuditColumns.contains(keyName)){
							try {
								Object keyValue = fields[kk].get(newObject);
								if (keyValue != null) {
									SysDataLogKey sysDataLogKey=new SysDataLogKey();
									sysDataLogKey.setKeyName(keyName);
									sysDataLogKey.setKeyValue(keyValue.toString());
									sysDataLogKeys.add(sysDataLogKey);
								} else {
									continue;
								}

							} catch (Exception e) {
								continue;
							}
							
						}
					}
					log.logEvent(logRecord,sysDataLogKeys,((SysUser)ContextUtil.getResource("sessionLogin")).getDataMonth());

				}

			}
		}
	}

	/**
	 * Returns an array of all fields used by this object from it's class and all superclasses.
	 * @param objectClass the class 
	 * @param fields the current field list
	 * @return an array of fields
	 */
	private Field[] getAllFields(Class objectClass, Field[] fields) {

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
			System.arraycopy(newFields, 0, totalFields, fieldsSize, newFieldsSize);
		}

		Class superClass = objectClass.getSuperclass();

		Field[] finalFieldsArray;

		if (superClass != null && !superClass.getName().equals("java.lang.Object")) {
			finalFieldsArray = getAllFields(superClass, totalFields);
		} else {
			finalFieldsArray = totalFields;
		}

		return finalFieldsArray;

	}

	/**
	 * Gets the id of the persisted object
	 * @param obj the object to get the id from
	 * @return object Id
	 */
	private Serializable getObjectId(Object obj) {

		Class objectClass = obj.getClass();
		Method[] methods = objectClass.getMethods();

		Field[] newFields = objectClass.getDeclaredFields();
		String idMethodName = null;
		if (newFields.length > 0) {
			String idFieldName = newFields[0].getName();
			idMethodName = "get" + idFieldName.substring(0, 1).toUpperCase() + idFieldName.substring(1);
		}

		Serializable persistedObjectId = null;
		for (int ii = 0; ii < methods.length; ii++) {
			// If the method name equals 'getId' then invoke it to get the id of
			// the object.
			if (methods[ii].getName().equals(idMethodName)) {
				try {
					persistedObjectId = (Serializable) methods[ii].invoke(obj, null);
					break;
				} catch (Exception e) {
					log.warn("Audit Log Failed - Could not get persisted object id: " + e.getMessage());
				}
			}
		}
		return persistedObjectId;
	}

	/**
	 * 
	 * @param modelName
	 * @return
	 */
	public String modelToTable(String modelName) {
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

	public static void main(String[] s) {
		SysConfigValue obj = new SysConfigValue();
		Class objectClass = obj.getClass();
		Method[] methods = objectClass.getMethods();

		Serializable persistedObjectId = null;
		for (int ii = 0; ii < methods.length; ii++) {
			System.out.println("method:" + methods[ii].getName());
		}

		Field[] newFields = objectClass.getDeclaredFields();
		if (newFields.length > 0) {
			String idFieldName = newFields[0].getName();
			String idMethodName = "get" + idFieldName.substring(0, 1).toUpperCase() + idFieldName.substring(1);
			System.out.println(idMethodName);
		}
		for (int ii = 0; ii < newFields.length; ii++) {
			System.out.println("field:" + newFields[ii].getName());
		}

	}
}
