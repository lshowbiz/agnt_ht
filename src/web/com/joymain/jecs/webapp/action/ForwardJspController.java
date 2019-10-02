package com.joymain.jecs.webapp.action;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;



/**
 *
 * <p>Title: is a Class</p>
 *
 * <p>Description:重转换到jsp，转发jsp </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: sunrise</p>
 *
 * @author islph
 * @version 1.0
 */
public class ForwardJspController implements Controller {
    /**日志记录**/
    private final Log log = LogFactory.getLog(ForwardJspController.class);


    /**
     * 转发jsp
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @throws Exception
     */
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        String requestUrl=request.getServletPath();
//        request.getQueryString();

        String forwardUrl=requestUrl.substring(0,requestUrl.indexOf(".html"));

        log.debug("forwardUrl:"+forwardUrl);

        Map requestMap=request.getParameterMap();
        Iterator iter=requestMap.keySet().iterator();
        while(iter.hasNext()){
            String paraName=(String)iter.next();
            request.setAttribute(paraName,request.getParameter(paraName));
        }



        return new ModelAndView(forwardUrl);

    }
}
