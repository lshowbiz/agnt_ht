package com.joymain.jecs.webapp.interceptor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.CallbackException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysDataLog;
import com.joymain.jecs.sys.model.SysDataLogKey;
import com.joymain.jecs.util.bean.ContextUtil;

/**
 * 记录数据修改日志
 * @author Aidy.Q
 * @version 1.2
 */
public class DataLog {
	private static Log log = LogFactory.getLog(DataLog.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void logEvent(final SysDataLog sysDataLog, final List<SysDataLogKey> sysDataLogKeys, final String month) {
		if (sysDataLog != null && sysDataLog.getOperationLogId() != null) {
			Session session = null;
			Connection conn = null;
			PreparedStatement st = null;
			try {
				session = sessionFactory.openSession();
				conn = session.connection();
				
				st = conn.prepareStatement("insert into jsys_data_log_"+month+" values(seq_log.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				//Transaction tx = session.beginTransaction();
				//int i = 0;
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
				st.setLong(14, sysDataLog.getOperationLogId());

				st.execute();
				
				//Modify By WuCF 20140304 日志表，不再用，注释!
				/*if(sysDataLogKeys!=null && sysDataLogKeys.size()>0){
					st=conn.prepareStatement("insert into jsys_data_log_key_"+month+" values(seq_log.nextval,?,?,?)");
					for(int i=0;i<sysDataLogKeys.size();i++){
						if(sysDataLogKeys.get(i).getKeyValue()==null){
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
				log.error("Error: ", ex);
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
					//e.printStackTrace();
					throw new CallbackException(e);
				}
			}
		}
	}
	
	public Object getPreviousObject(Class entity, Serializable id){
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
	

	public Object getObject(Class entity, Serializable id) throws CallbackException {
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
	
	/**
	 * 写入日志url
	 * @param request
	 * @param operatorPersion
	 * @param getUrl
	 * @param postUrl
	 * @param agetUrl
	 * @param apostUrl
	 */
	public void logUrl(HttpServletRequest request,String operatorPersion,String getUrl,String postUrl,String agetUrl,String apostUrl) {
		Session session = null;
		Connection conn = null;
		PreparedStatement st = null;
		try {
			sessionFactory = (SessionFactory) ContextUtil.getSpringBeanByName(
					Constants.context, "sessionFactory2");
			session = sessionFactory.openSession();
			conn = session.connection();

			st = conn
					.prepareStatement("insert  /*+append+*/ into jsys_data_url_log(LOG_ID,LOG_TYPE,OPERATOR_PERSON,OPERATOR_TIME,HOUST_NAME,IP_ADDRESS,GET_URL,POST_URL,AGET_URL,APOST_URL)"
							
							+ " values(seq_log.nextval,?,?,sysdate,?,?,?,?,?,?)");
			
			//得到当前登录用户对象
			String host = (String)ContextUtil.getResource("host");
			String ip = (String)ContextUtil.getResource("ip");
			if(host==null || "".equals(host)){
				host = request.getRemoteAddr();
			}
			if(ip==null || "".equals(ip)){
				ip = request.getRemoteAddr();
			}
						
			st.setString(1, "1");//后台人员
			st.setString(2, operatorPersion);
			st.setString(3, host);
			st.setString(4, ip);
			st.setString(5, getUrl);
			st.setString(6, postUrl);
			st.setString(7, agetUrl);
			st.setString(8, apostUrl);

			st.execute();//执行语句

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