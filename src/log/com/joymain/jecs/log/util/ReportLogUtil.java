package com.joymain.jecs.log.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.util.bean.ContextUtil;

public class ReportLogUtil {
//	private static SessionFactory sessionFactory;
//
//	public static void setSessionFactory(SessionFactory sessionFactory) {
//		ReportLogUtil.sessionFactory = sessionFactory;
//	}
	private static JdbcTemplate jdbcTemplate;
	public static Long beginReport(String user,String ip,String type,String content){
		jdbcTemplate = (JdbcTemplate) ContextUtil.getSpringBeanByName(
				Constants.context, "jdbcTemplate"); 
		String idSql = "select seq_log.nextval from dual";
		Long id = jdbcTemplate.queryForLong(idSql);
		 
		String sql = "insert /*+append+*/ into jsys_click_log(RUN_DATE,IS_VALID, LOG_ID,LOG_TYPE,REMOTE_USER,IP_ADDRESS,CONTENT )" +
				" values(sysdate,1,?,?,?,?,?)";
		
		
		
		
		jdbcTemplate.update(sql, new Object[]{id,type,user,ip,content});
	
		return id;
		
		
	}
	
public static void endReport(Long refId,String type){
		
	
		 
		String sql = "insert /*+append+*/ into jsys_click_log(RUN_DATE,IS_VALID, LOG_ID,LOG_TYPE,ref_id)" +
				" values(sysdate,0,seq_log.nextval,?,?)";
		
		jdbcTemplate = (JdbcTemplate) ContextUtil.getSpringBeanByName(
				Constants.context, "jdbcTemplate");
		
		
		jdbcTemplate.update(sql, new Object[]{type,refId});
	
		
		
		
	}

	/**
	 * Add By WuCF 新增接口日志文件方法
	 * 新增日志文件
	 * @param jocsInterfaceLog
	 * @return
	 */
	public static String addJocsInterface(JocsInterfaceLog jocsInterfaceLog){
		//设置默认值
		jocsInterfaceLog.setFlag("jocs");
		jocsInterfaceLog.setType("json");
		jocsInterfaceLog.setCharset("utf-8");
		jocsInterfaceLog.setVer("1");
		
		/*org.json.JSONObject jo = new org.json.JSONObject(jocsInterfaceLog.getReturnDesc());
	    if (jo.has("response")){//正常返回
	    	org.json.JSONObject json = jo.getJSONObject("response");
	    	if(json.get("code")!=null){
	    		jocsInterfaceLog.setReturnResult(json.get("code").toString());
	    	}
	    }else if (jo.has("error_response")){//异常返回的错误编码
	    	org.json.JSONObject json = jo.getJSONObject("error_response");
	    	if(json.get("code")!=null){
	    		jocsInterfaceLog.setReturnResult(json.get("code").toString());
	    	}
	    }*/
		
		jdbcTemplate = (JdbcTemplate) ContextUtil.getSpringBeanByName(
				Constants.context, "jdbcTemplate");
		//得到insert序列值，以便能返回
		String idSql = "select seq_log.nextval from dual";
		Long id = jdbcTemplate.queryForLong(idSql);
		jocsInterfaceLog.setLogId(id);
		
		//sql语句
		StringBuffer sqlBuf = new StringBuffer("INSERT /*+append+*/ INTO JOCS_INTERFACE_LOG(LOG_ID,LOG_TYPE,FLAG,METHOD,TYPE,");
		sqlBuf.append("CHARSET,VER,CONTENT,SIGN,RETURN_RESULT,");
		sqlBuf.append("RETURN_DESC,SEND_TIME,RECIVER_TIME,EDIT_TIME,REMARK,SENDER)");
		sqlBuf.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,TO_DATE(?,'yyyy-MM-dd hh24:mi:ss'),?,?)");
	
		//执行语句
		jdbcTemplate.update(sqlBuf.toString(), new Object[]{
			jocsInterfaceLog.getLogId(),jocsInterfaceLog.getLogType(),jocsInterfaceLog.getFlag(),jocsInterfaceLog.getMethod(),jocsInterfaceLog.getType(),
			jocsInterfaceLog.getCharset(),jocsInterfaceLog.getVer(),jocsInterfaceLog.getContent(),jocsInterfaceLog.getSign(),jocsInterfaceLog.getReturnResult(),
			jocsInterfaceLog.getReturnDesc(),jocsInterfaceLog.getEditTimeStr(),jocsInterfaceLog.getRemark(),jocsInterfaceLog.getSender()});
		
		//返回序列值
		return String.valueOf(jocsInterfaceLog.getLogId());
	}
	
}
