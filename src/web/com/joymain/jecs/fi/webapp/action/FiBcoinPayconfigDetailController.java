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
import com.joymain.jecs.fi.model.FiBcoinPayconfigDetail;
import com.joymain.jecs.fi.service.FiBcoinPayconfigDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 积分换购配置子表
 * @author 美体
 *
 */
public class FiBcoinPayconfigDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiBcoinPayconfigDetailController.class);
    private FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager = null;

    public void setFiBcoinPayconfigDetailManager(FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager) {
        this.fiBcoinPayconfigDetailManager = fiBcoinPayconfigDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiBcoinPayconfigDetail fiBcoinPayconfigDetail = new FiBcoinPayconfigDetail();
        // populate object with request parameters
        BeanUtils.populate(fiBcoinPayconfigDetail, request.getParameterMap());

        //List fiBcoinPayconfigDetails = fiBcoinPayconfigDetailManager.getFiBcoinPayconfigDetails(fiBcoinPayconfigDetail);
        /**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiBcoinPayconfigDetailListTable",request, 20);
        List fiBcoinPayconfigDetails = fiBcoinPayconfigDetailManager.getFiBcoinPayconfigDetailsByCrm(crm,pager);
        request.setAttribute("fiBcoinPayconfigDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiBcoinPayconfigDetailList", "fiBcoinPayconfigDetailList", fiBcoinPayconfigDetails);
    }
}
