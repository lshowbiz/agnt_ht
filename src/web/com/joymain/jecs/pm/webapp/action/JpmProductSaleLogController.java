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
import com.joymain.jecs.pm.model.JpmProductSaleLog;
import com.joymain.jecs.pm.service.JpmProductSaleLogManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmProductSaleLogController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmProductSaleLogController.class);
    private JpmProductSaleLogManager jpmProductSaleLogManager = null;

    public void setJpmProductSaleLogManager(JpmProductSaleLogManager jpmProductSaleLogManager) {
        this.jpmProductSaleLogManager = jpmProductSaleLogManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmProductSaleLog jpmProductSaleLog = new JpmProductSaleLog();
        // populate object with request parameters
        BeanUtils.populate(jpmProductSaleLog, request.getParameterMap());

	//List jpmProductSaleLogs = jpmProductSaleLogManager.getJpmProductSaleLogs(jpmProductSaleLog);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmProductSaleLogListTable",request, 20);
        List jpmProductSaleLogs = jpmProductSaleLogManager.getJpmProductSaleLogsByCrm(crm,pager);
        request.setAttribute("jpmProductSaleLogListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pm/jpmProductSaleLogList", Constants.JPMPRODUCTSALELOG_LIST, jpmProductSaleLogs);
    }
}
