package com.joymain.jecs.pm.webapp.action;

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
import com.joymain.jecs.pm.model.JpmMemberConfig;
import com.joymain.jecs.pm.service.JpmMemberConfigManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmMemberConfigController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmMemberConfigController.class);
    private JpmMemberConfigManager jpmMemberConfigManager = null;

    public void setJpmMemberConfigManager(JpmMemberConfigManager jpmMemberConfigManager) {
        this.jpmMemberConfigManager = jpmMemberConfigManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmMemberConfig jpmMemberConfig = new JpmMemberConfig();
        // populate object with request parameters
        BeanUtils.populate(jpmMemberConfig, request.getParameterMap());

	//List jpmMemberConfigs = jpmMemberConfigManager.getJpmMemberConfigs(jpmMemberConfig);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmMemberConfigListTable",request, 20);
        List jpmMemberConfigs = jpmMemberConfigManager.getJpmMemberConfigsByCrm(crm,pager);
        request.setAttribute("jpmMemberConfigListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pm/jpmMemberConfigList", Constants.JPMMEMBERCONFIG_LIST, jpmMemberConfigs);
    }
}
