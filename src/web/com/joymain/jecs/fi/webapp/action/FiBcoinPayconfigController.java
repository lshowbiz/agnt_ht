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
import com.joymain.jecs.fi.model.FiBcoinPayconfig;
import com.joymain.jecs.fi.service.FiBcoinPayconfigManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 积分换购配置控制器
 * @author meiti
 *
 */
public class FiBcoinPayconfigController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiBcoinPayconfigController.class);
    private FiBcoinPayconfigManager fiBcoinPayconfigManager = null;

    public void setFiBcoinPayconfigManager(FiBcoinPayconfigManager fiBcoinPayconfigManager) {
        this.fiBcoinPayconfigManager = fiBcoinPayconfigManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiBcoinPayconfig fiBcoinPayconfig = new FiBcoinPayconfig();
        // populate object with request parameters
        BeanUtils.populate(fiBcoinPayconfig, request.getParameterMap());

        //List fiBcoinPayconfigs = fiBcoinPayconfigManager.getFiBcoinPayconfigs(fiBcoinPayconfig);
        /**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiBcoinPayconfigListTable",request, 20);
        List fiBcoinPayconfigs = fiBcoinPayconfigManager.getFiBcoinPayconfigsByCrm(crm,pager);
        request.setAttribute("fiBcoinPayconfigListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiBcoinPayconfigList", "fiBcoinPayconfigList", fiBcoinPayconfigs);
    }
}
