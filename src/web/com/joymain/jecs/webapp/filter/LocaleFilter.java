package com.joymain.jecs.webapp.filter;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Filter to wrap request with a request including user preferred locale.
 */
public class LocaleFilter extends OncePerRequestFilter {

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                 FilterChain chain)
            throws IOException, ServletException {
    	String path = request.getServletPath();
		if(StringUtils.contains(path, "starsExpressCallBackService.html") || StringUtils.contains(path, "testStream.jsp")){
			chain.doFilter(request, response);
			return;
		}
    	
        String locale = request.getParameter("locale");
        Locale preferredLocale = null;

        if (locale != null) {
            preferredLocale = new Locale(locale);
        }
        
        HttpSession session = request.getSession(false);

        if (session != null) {
            if (preferredLocale == null) {
                preferredLocale = (Locale) session.getAttribute(Constants.PREFERRED_LOCALE_KEY);
            } else {
                session.setAttribute(Constants.PREFERRED_LOCALE_KEY, preferredLocale);
                Config.set(session, Config.FMT_LOCALE, preferredLocale);
            }

            if (preferredLocale != null && !(request instanceof LocaleRequestWrapper)) {
                request = new LocaleRequestWrapper(request, preferredLocale);
                LocaleContextHolder.setLocale(preferredLocale);
            }
        }

        String theme = request.getParameter("theme");
        if (theme != null && request.isUserInRole(Constants.ADMIN_ROLE)) {
            Map config = (Map) getServletContext().getAttribute(Constants.CONFIG);
            config.put(Constants.CSS_THEME, theme);
        }
		//将登录用户绑定至当前线程
		ContextUtil.bindResource("sessionLogin", SessionLogin.getLoginUser(request));
        ContextUtil.bindResource("ip", RequestUtil.getIpAddr(request));
        ContextUtil.bindResource("host", request.getRemoteHost());
        ContextUtil.bindResource("url", request.getServletPath());

        chain.doFilter(request, response);
        
        // Reset thread-bound LocaleContext.
        LocaleContextHolder.setLocaleContext(null);
    }
}
