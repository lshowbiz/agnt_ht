package com.joymain.jecs.webapp.util;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

import com.joymain.jecs.Constants;
 
import com.joymain.jecs.util.exception.PremissionException;
import com.joymain.jecs.util.web.RequestUtil;

public class WecsPowerAdvice implements MethodBeforeAdvice {
	protected Log log = LogFactory.getLog(WecsPowerAdvice.class);

	public void before(Method method, Object[] args, Object target) throws Throwable {
		Class clazz = method.getDeclaringClass();
		HttpServletRequest request = (HttpServletRequest) RequestUtil.getLocalRequest();
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();

		Map contextPowerPermissionMap = (Map) context.getAttribute(Constants.APPLICATION_POWERS_BY_URL);
		Map sessionPermissionPowersMap = (Map) session.getAttribute(Constants.SESSION_CURRENT_POWERS_BY_URL);
		String power = clazz.getName() + "." + method.getName();
		if (contextPowerPermissionMap.containsKey(power)) {
			if (!sessionPermissionPowersMap.containsKey(power)) {
				PremissionException e = new PremissionException("sys.message.permissionDenied");
				MessageUtil.saveExceptionLocaleMessage(request, e);
				throw e;
			}
		}
	}
}
