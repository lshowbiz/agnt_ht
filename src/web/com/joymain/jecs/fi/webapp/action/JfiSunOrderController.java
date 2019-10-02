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
import com.joymain.jecs.fi.model.JfiSunOrder;
import com.joymain.jecs.fi.service.JfiSunOrderManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiSunOrderController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunOrderController.class);
    private JfiSunOrderManager jfiSunOrderManager = null;

    public void setJfiSunOrderManager(JfiSunOrderManager jfiSunOrderManager) {
        this.jfiSunOrderManager = jfiSunOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiSunOrder jfiSunOrder = new JfiSunOrder();
        // populate object with request parameters
        BeanUtils.populate(jfiSunOrder, request.getParameterMap());

	//List jfiSunOrders = jfiSunOrderManager.getJfiSunOrders(jfiSunOrder);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiSunOrderListTable",request, 20);
        List jfiSunOrders = jfiSunOrderManager.getJfiSunOrdersByCrm(crm,pager);
        request.setAttribute("jfiSunOrderListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiSunOrderList", Constants.JFISUNORDER_LIST, jfiSunOrders);
    }
}
