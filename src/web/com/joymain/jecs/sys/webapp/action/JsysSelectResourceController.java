package com.joymain.jecs.sys.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.joymain.jecs.sys.service.JsysResourceManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JsysSelectResourceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JsysSelectResourceController.class);
    private JsysResourceManager jsysResourceManager = null;

    public void setJsysResourceManager(JsysResourceManager jsysResourceManager) {
        this.jsysResourceManager = jsysResourceManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        List jsysResources = jsysResourceManager.getJsysResources(null);
        request.setAttribute("jsysResources", jsysResources);

        return new ModelAndView("sys/jsysResource_select");
    }
}
