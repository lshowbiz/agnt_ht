package com.joymain.jecs.webapp.filter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;


public class RestFilter implements Filter {

	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		request.setCharacterEncoding("UTF-8");

		// 获取参数
		Map<String ,String> map = new HashMap<String ,String> ();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (!"keyId".equals(paramName) &&paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
//					System.out.println("参数：" + paramName + "=" + paramValue);
					map.put(paramName, paramValue);
				}
			}
		}

		
		// 排序

		List list =new ArrayList();
		Iterator iter = map.keySet().iterator(); 
		while (iter.hasNext()) { 
		    Object key = iter.next(); 
		    list.add(key);
		}
		Comparator cmp = Collator.getInstance(Locale.ENGLISH);
        Collections.sort(list, cmp);
//        System.out.println("排序打印"+list.size());
//		for (int i = 0; i < list.size(); i++){
//			System.out.println(list.get(i));
//		}
		
		String keyId =request.getParameter("keyId");
		String authId =request.getParameter("authId");
		String longDate =request.getParameter("longDate");
		
		Date curDate=new Date();
		long afDate=curDate.getTime()+1000*60*60;
		long beDate=curDate.getTime()-1000*60*60;
		if(StringUtil.formatLong(longDate)==null||new Long(longDate) < beDate || new Long(longDate) > afDate){
			 response.setContentType("application/x-json");
			 response.setContentType("text/html;charset=utf-8");
			 PrintWriter out = response.getWriter();
			 out.print("request timeout");
			 out.flush();
			 out.close();
			 return;
		}
		
		Properties p = new Properties();
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("rest.properties");
			p.load(inputStream);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			throw new AppException("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new AppException("装载文件--->失败!");
		}
		
		String authKeyMD5 = p.getProperty(authId);
		
		
		if(StringUtil.isEmpty(keyId)||StringUtil.isEmpty(authId)||StringUtil.isEmpty(authKeyMD5)||!getKeyId(map, list, authKeyMD5).equals(keyId)){
			 response.setContentType("application/x-json");
			 response.setContentType("text/html;charset=utf-8");
			 PrintWriter out = response.getWriter();
			 out.print("request error");
			 out.flush();
			 out.close();
			 return;
		}
		
		
		
		

		chain.doFilter(request, response);

	}
	/**
	 * 参数按字母排序，全部参数 MD5后，再与authKey MD5
	 * @param map
	 * @param list
	 * @param authKey
	 * @return
	 */
	private String getKeyId(Map map,List list,String authKey){
		String keyId="";
		
		
		String paramvalues="";
		for (Object oo : list) {
			String value=(String) map.get(oo);
			paramvalues+=value;
		}
		keyId=StringUtil.encodePassword(paramvalues, "md5");
		keyId+=authKey;
		keyId=StringUtil.encodePassword(keyId, "md5");
		
		
		return keyId;
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
