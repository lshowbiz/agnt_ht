package com.joymain.jecs.po.webapp.action;

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
import com.joymain.jecs.po.model.JpoCounterOrderDetail;
import com.joymain.jecs.po.service.JpoCounterOrderDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpoCounterOrderDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoCounterOrderDetailController.class);
    private JpoCounterOrderDetailManager jpoCounterOrderDetailManager = null;

    public void setJpoCounterOrderDetailManager(JpoCounterOrderDetailManager jpoCounterOrderDetailManager) {
        this.jpoCounterOrderDetailManager = jpoCounterOrderDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpoCounterOrderDetail jpoCounterOrderDetail = new JpoCounterOrderDetail();
        // populate object with request parameters
        BeanUtils.populate(jpoCounterOrderDetail, request.getParameterMap());

	//List jpoCounterOrderDetails = jpoCounterOrderDetailManager.getJpoCounterOrderDetails(jpoCounterOrderDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpoCounterOrderDetailListTable",request, 20);
        List jpoCounterOrderDetails = jpoCounterOrderDetailManager.getJpoCounterOrderDetailsByCrm(crm,pager);
        request.setAttribute("jpoCounterOrderDetailListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("po/jpoCounterOrderDetailList", Constants.JPOCOUNTERORDERDETAIL_LIST, jpoCounterOrderDetails);
    }
}
