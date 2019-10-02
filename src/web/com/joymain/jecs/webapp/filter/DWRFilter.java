package com.joymain.jecs.webapp.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.service.JmiSmsNoteManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;



public class DWRFilter implements AjaxFilter {
	
	
	 private final Log log = LogFactory.getLog(DWRFilter.class);
	public Object doFilter(Object object, Method method, Object[] arr,
			AjaxFilterChain chain) throws Exception {
		// 获取session
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		
		

        SysUser defSysUser = (SysUser) request.getSession().getAttribute(Constants.SESSION_CURRENT_USER);
		
        String methodName = method.getName();
        
        log.info("访问dwr:"+object+",ip:"+request.getRemoteAddr());
        
        if(arr!=null){
        	log.info("访问dwr 参数"+arr.toString());
        }
        log.info(object instanceof JmiSmsNoteManager );
		if ( !(object instanceof JmiSmsNoteManager && "sendSms".equals(methodName) )    &&( defSysUser == null || StringUtil.isEmpty(defSysUser.getUserCode())) ) {
			//System.out.println("session验证失败");
			log.info("未登陆访问dwr:"+methodName+",ip:"+request.getRemoteAddr());
			return "session_error";
		}
		// 拦截调用方法
		
		//System.out.println("拦截目标方法：" + methodName);

/*		if (!CheckLimit(methodName)) {
			System.out.println("权限验证失败");
			return "limit_error";
		}*/
		Object obj = chain.doFilter(object, method, arr);
		//System.out.println("目标方法" + method.getName() + "执行结束");
		return obj;
	}
}