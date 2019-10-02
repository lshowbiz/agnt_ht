package com.joymain.jecs.webapp.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.po.service.JpoMemberOrderManager;



public class BoServiceController extends BaseController implements Controller {

	private JpoMemberOrderManager jpoMemberOrderManager;
	private JdbcTemplate jdbcTemplate;
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		StringBuffer resultBuffer = new StringBuffer("");
		String requestStr="";
		
		ServletInputStream in= request.getInputStream();
		ServletOutputStream out =response.getOutputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
//		Writer 
		PrintWriter  writer = new PrintWriter(out);
		String inputLine = null;
		while((inputLine=reader.readLine()) != null){
			resultBuffer.append(inputLine);
		}
		
		reader.close();
		requestStr = resultBuffer.toString();
		Map map = new HashMap();
		if(StringUtils.isNotBlank(requestStr)){
			String[] req = requestStr.split("&");
			for(int i=0;i<req.length;i++){
				String[] self = req[i].split("=");
				if(self.length==2){
					map.put(self[0], self[1]);
				}
			}
		}
		log.info("shoppingcart,requestStr>>"+requestStr);
		this.saveLog(requestStr, "");
		String action = (String) map.get("action");
		String requestJson = (String) map.get("request");
		String digest = (String) map.get("digest");
		 String erroStr="";
		 
		if("addOrder".equals(action)){
			try {
				addOrder(requestJson);
				erroStr = "{\"resultCode\":\"0\",\"discription\":\"Success\"}";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				erroStr="{\"resultCode\":\"1\",\"discription\":\"Failure.Error message>>"+e.getMessage()+"\"}";
				
				log.info("shoppingcart>>>"+e.getMessage());	
			}
		}
		this.saveLog(requestStr, erroStr);
		writer.print(erroStr);
		writer.flush();
		writer.close();
		
		return null;
	}

	private void addOrder(String requestJson) throws Exception {
		// TODO Auto-generated method stub
		jpoMemberOrderManager.orderJSON(requestJson);
	}
	public void saveLog(String requestStr,String responseStr){
		String sql =  "insert into sys_cart_LOG(LOG_ID,OPERATE_DATA,RESPONSE_DATA,CREATE_TIME) values(seq_log.nextval,'"+requestStr+"','"+responseStr+"',sysdate)";
		jdbcTemplate.execute(sql);
	}

}
