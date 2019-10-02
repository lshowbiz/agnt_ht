package com.joymain.jecs.am.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.am.model.JamMsnModule;
import com.joymain.jecs.am.model.JamMsnType;
import com.joymain.jecs.am.service.JamMsnModuleManager;
import com.joymain.jecs.am.service.JamMsnTypeManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JamMsnModuleTreeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JamMsnModuleTreeController.class);
    private JamMsnTypeManager jamMsnTypeManager;

    public void setJamMsnTypeManager(JamMsnTypeManager jamMsnTypeManager) {
		this.jamMsnTypeManager = jamMsnTypeManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        List jamMsnTypes = jamMsnTypeManager.getJamMsnTypes(new JamMsnType());
        request.setAttribute("jamMsnTypes", jamMsnTypes);

        return new ModelAndView("am/jam_msn_module_tree");
    }
}
