package com.joymain.jecs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.util.bean.ContextUtil;

public class ReportLogUtilService {
	//	private static SessionFactory sessionFactory;
	//
	//	public static void setSessionFactory(SessionFactory sessionFactory) {
	//		ReportLogUtil.sessionFactory = sessionFactory;
	//	}
	private static JdbcTemplate jdbcTemplate;
	
	private static final String SYMBOL = "@#$*&";//分割符号
	private static String path = "D:/";//文件路径
	private static String filenameTemp = "";//文件名称
	
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
	
	/**
	 * 接口日志写入到txt
	 * @return：0成功  1失败
	 */
	public static String addJocsInterfaceTxt(JocsInterfaceLog jocsInterfaceLog){
		String result = "0";//默认成功
		try{
			SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMDD");
			filenameTemp = format2.format(new Date());//文件名以当前日期命名
			creatTxtFile(filenameTemp);
//			writeTxtFile(getJocsInterfaceLogStr(jocsInterfaceLog));
			writeMethod(filenameTemp,getJocsInterfaceLogStr(jocsInterfaceLog));
		}catch(Exception e){
			e.printStackTrace();
			result = "1";//失败
		}
		return result;
	}
	
	/**
	 * 得到返回的字符串，按指定格式分割
	 */
	public static String getJocsInterfaceLogStr(JocsInterfaceLog jocsInterfaceLog){
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		StringBuffer JocsInterfaceLogStr = new StringBuffer("");
		JocsInterfaceLogStr.append("LOG_ID:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getLogId());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("LOG_TYPE:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getLogType());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("FLAG:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getFlag());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("METHOD:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getMethod());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("TYPE:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getType());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("CHARSET:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getCharset());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("VER:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getVer());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("CONTENT:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getContent());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("SIGN:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getSign());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("RETURN_RESULT:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getReturnResult());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("RETURN_DESC:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getReturnDesc());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("SEND_TIME:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getSendTime());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("RECIVER_TIME:");
		JocsInterfaceLogStr.append(format2.format(new Date()));
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("EDIT_TIME:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getEditTime());
		JocsInterfaceLogStr.append(SYMBOL);
		JocsInterfaceLogStr.append("REMARK:");
		JocsInterfaceLogStr.append(jocsInterfaceLog.getRemark());
		return JocsInterfaceLogStr.toString();
	}
	
	/**   
     * 追加文件：使用FileWriter   
     *    
     * @param fileName   
     * @param content   
     */    
    public static synchronized void writeMethod(String fileName, String content) {   
        FileWriter writer = null;  
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件      
            writer = new FileWriter(fileName, true);     
            writer.write(content+"\r\n");       
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(writer != null){  
                    writer.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }   
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
}
