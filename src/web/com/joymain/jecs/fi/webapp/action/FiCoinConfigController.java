package com.joymain.jecs.fi.webapp.action;

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
import com.joymain.jecs.fi.model.FiCoinConfig;
import com.joymain.jecs.fi.service.FiCoinConfigManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 积分换购活动配置查询
 * @author Administrator
 *
 */
public class FiCoinConfigController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiCoinConfigController.class);
    private FiCoinConfigManager fiCoinConfigManager = null;

    public void setFiCoinConfigManager(FiCoinConfigManager fiCoinConfigManager) {
        this.fiCoinConfigManager = fiCoinConfigManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiCoinConfig fiCoinConfig = new FiCoinConfig();
        // populate object with request parameters
        BeanUtils.populate(fiCoinConfig, request.getParameterMap());

	//List fiCoinConfigs = fiCoinConfigManager.getFiCoinConfigs(fiCoinConfig);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiCoinConfigListTable",request, 20);
        List fiCoinConfigs = fiCoinConfigManager.getFiCoinConfigsByCrm(crm,pager);
        request.setAttribute("fiCoinConfigListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiCoinConfigList", Constants.FICOINCONFIG_LIST, fiCoinConfigs);
    }
}
