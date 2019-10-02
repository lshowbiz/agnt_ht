package com.joymain.jecs.webapp.util;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.string.StringUtil;


/**
 * Convenience class for setting and retrieving cookies.
 */
public class RequestUtil {
    private transient static Log log = LogFactory.getLog(RequestUtil.class);

    /**
     * Convenience method to set a cookie
     *
     * @param response
     * @param name
     * @param value
     * @param path
     */
    public static void setCookie(HttpServletResponse response, String name,
                                 String value, String path) {
        if (log.isDebugEnabled()) {
            log.debug("Setting cookie '" + name + "' on path '" + path + "'");
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        cookie.setMaxAge(3600 * 24 * 30); // 30 days

        response.addCookie(cookie);
    }

    /**
     * Convenience method to get a cookie by name
     *
     * @param request the current request
     * @param name the name of the cookie to find
     *
     * @return the cookie (if found), null if not found
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

        if (cookies == null) {
            return returnCookie;
        }

        for (int i = 0; i < cookies.length; i++) {
            Cookie thisCookie = cookies[i];

            if (thisCookie.getName().equals(name)) {
                // cookies with no value do me no good!
                if (!thisCookie.getValue().equals("")) {
                    returnCookie = thisCookie;

                    break;
                }
            }
        }

        return returnCookie;
    }

    /**
     * Convenience method for deleting a cookie by name
     *
     * @param response the current web response
     * @param cookie the cookie to delete
     * @param path the path on which the cookie was set (i.e. /appfuse)
     */
    public static void deleteCookie(HttpServletResponse response,
                                    Cookie cookie, String path) {
        if (cookie != null) {
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }
    
    /**
     * Convenience method to get the application's URL based on request
     * variables.
     */
    public static String getAppURL(HttpServletRequest request) {
        StringBuffer url = new StringBuffer();
    	int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }
    public static boolean isMobileRequest(HttpServletRequest request){
		boolean ret = false;
		String mobiDomain = Constants.sysConfigMap.get("CN").get("domain.mobi");
		if(request.getServerName().equalsIgnoreCase(mobiDomain)){
			ret = true;
		}
		return ret;
	}
    public static boolean isCompanyDomain(HttpServletRequest request){
    	boolean ret = false;
    	String mobiDomain = Constants.sysConfigMap.get("CN").get("domain.company");
    	log.info("mobiDomain="+mobiDomain);
    	log.info("request.getServerName="+request.getServerName());
		if(mobiDomain.contains(request.getServerName())){
			ret = true;
		}
		return ret;
    }
    public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
    public static Map populateMap(Map map,HttpServletRequest request,  String charsetFrom, String charsetTo){
//		Map map = new HashMap();
		if (map == null){
			map = new HashMap();
		}
		Enumeration names = request.getParameterNames();
		String value = "";
		String name = "";
		while(names.hasMoreElements()){
			name = (String) names.nextElement();
			value = StringUtil.replaceSpecialChars(StringUtil.convertCharset(request.getParameter(name), charsetFrom, charsetTo));
			map.put(name, value);
		}
		return map;
	}
	public static Map populateMap(Map map,HttpServletRequest request){
		return populateMap(map,request, "UTF-8", "UTF-8");
	}
	/**
	 * 灏咹ttpServletRequest瀵硅薄涓殑getPrameter灞炴�杞崲鎴恗ap瀵硅薄
	 * @param request HttpServletRequest瀵硅薄
	 * @param charsetFrom WebContext鍒癈ommonRecord鏃剁殑璧峰瀛楃闆�
	 * @param charsetTo WebContext鍒癿ap鏃剁殑缁撴灉瀛楃闆�
	 * @return
	 */
	public static Map populateMap(HttpServletRequest request,  String charsetFrom, String charsetTo){
		Map map = new HashMap();
		Enumeration names = request.getParameterNames();
		String value = "";
		String name = "";
		while(names.hasMoreElements()){
			name = (String) names.nextElement();
			value = StringUtil.replaceSpecialChars(StringUtil.convertCharset(request.getParameter(name), charsetFrom, charsetTo));
			map.put(name, value);
		}
		return map;
	}
	
	public static Map populateMap(HttpServletRequest request){
		return populateMap(request, "UTF-8", "UTF-8");
	}
	/**
	 * 灏咹ttpServletRequest瀵硅薄涓殑getPrameter灞炴�杞崲鎴怌ommonRecord瀵硅薄,淇濈暀session閲岀殑
	 * @param request HttpServletRequest瀵硅薄
	 * @param jdbcType 杞崲鏃剁殑鏁版嵁绫诲瀷
	 * @param charsetFrom WebContext鍒癈ommonRecord鏃剁殑璧峰瀛楃闆�
	 * @param charsetTo WebContext鍒癈ommonRecord鏃剁殑缁撴灉瀛楃闆�
	 * @return
	 */
	public static CommonRecord toCommonRecord(CommonRecord crm,HttpServletRequest request, int jdbcType, String charsetFrom, String charsetTo) {
		String value = "";
		String name = "";
//		CommonRecord crm = new CommonRecord();
		if(crm ==null){
			crm = new CommonRecord();
		}
		CustomField field = null;
		java.util.Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			//value = wc.getParameter(name, charsetFrom, charsetTo);
			value = StringUtil.replaceSpecialChars(StringUtil.convertCharset(StringUtils.trim(request.getParameter(name)), charsetFrom, charsetTo));
			field = new CustomField(name, jdbcType, value);
			crm.addField(field);
		}
		return crm;
	}
	/**
	 * 瑙乼oCommonRecord(HttpServletRequest request, int jdbcType,String charsetFrom, String charsetTo)
	 * @param request
	 * @return
	 */
	public static CommonRecord toCommonRecord(CommonRecord crm,HttpServletRequest request) {
		return toCommonRecord(crm,request, Types.VARCHAR, "UTF-8", "UTF-8");
	}
	/**
	 * 灏咹ttpServletRequest瀵硅薄涓殑getPrameter灞炴�杞崲鎴怌ommonRecord瀵硅薄
	 * @param request HttpServletRequest瀵硅薄
	 * @param jdbcType 杞崲鏃剁殑鏁版嵁绫诲瀷
	 * @param charsetFrom WebContext鍒癈ommonRecord鏃剁殑璧峰瀛楃闆�
	 * @param charsetTo WebContext鍒癈ommonRecord鏃剁殑缁撴灉瀛楃闆�
	 * @return
	 */
	public static CommonRecord toCommonRecord(HttpServletRequest request, int jdbcType, String charsetFrom, String charsetTo) {
		String value = "";
		String name = "";
		CommonRecord crm = new CommonRecord();
		CustomField field = null;
		java.util.Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			//value = wc.getParameter(name, charsetFrom, charsetTo);
			value = StringUtil.replaceSpecialChars(StringUtil.convertCharset(StringUtils.trim(request.getParameter(name)), charsetFrom, charsetTo));
			field = new CustomField(name, jdbcType, value);
			crm.addField(field);
		}
		return crm;
	}

	/**
	 * 瑙乼oCommonRecord(HttpServletRequest request, int jdbcType,String charsetFrom, String charsetTo)
	 * @param request
	 * @return
	 */
	public static CommonRecord toCommonRecord(HttpServletRequest request) {
		return toCommonRecord(request, Types.VARCHAR, "UTF-8", "UTF-8");
	}

	/**
	 * 淇濆瓨閿欒鎴栬�鎻愮ず淇℃伅
	 * @param request
	 * @param msg
	 */
	public static void saveMessage(HttpServletRequest request, String msg) {
		List messages = (List) request.getSession().getAttribute("messages");

		if (messages == null) {
			messages = new ArrayList();
		}

		messages.add(msg);
		request.getSession().setAttribute("messages", messages);
	}
	
	/**
	 * 
	 * @param request
	 * @param operationCode 操作code
	 * @param interval 间隔时间
	 * @return
	 */
	public static Long saveOperationSession(HttpServletRequest request,String operationCode,Long interval){
		Long ret =0L;
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		log.info("saveOperationSession,currentTime="+currentTime);
		String lastTime = StringUtil.getAvailStr(request.getSession(true).getAttribute(operationCode), "0");
		log.info("saveOperationSession,lastTime="+lastTime);
		if(currentTime-Long.valueOf(lastTime)>interval*1000){
			request.getSession().setAttribute(operationCode, currentTime); 
		}else{
			ret = Long.valueOf(lastTime)+interval*1000-currentTime;
		}

		SysUser defSysUser = SessionLogin.getLoginUser(request);
		if("root".equals(defSysUser.getUserCode())){
			ret =0L;
		}
		request.getSession().setAttribute("operationCode",operationCode); 
		request.getSession().setAttribute(operationCode+"-time", ret/1000); 
		
		return ret/1000;
	}
	public static void cleanOperationSession(HttpServletRequest request,String operationCode){
		request.getSession().setAttribute(operationCode, 0); 
	}
	
	public static Long freshSession(HttpServletRequest request,String operationCode,Long interval){
		Long ret =0L;
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		log.info("saveOperationSession,currentTime="+currentTime);
		String lastTime = StringUtil.getAvailStr(request.getSession(true).getAttribute(operationCode), "0");
		log.info("saveOperationSession,lastTime="+lastTime);
		 
		if(currentTime-Long.valueOf(lastTime)<interval*1000){
			ret = Long.valueOf(lastTime)+interval*1000-currentTime;
		} 
	 
		request.getSession().setAttribute("operationCode",operationCode); 
		request.getSession().setAttribute(operationCode+"-time", ret/1000); 
		
		return ret/1000;
	}
}
