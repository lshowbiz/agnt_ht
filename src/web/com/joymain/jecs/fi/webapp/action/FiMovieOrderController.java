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
import com.joymain.jecs.fi.model.FiMovieOrder;
import com.joymain.jecs.fi.service.FiMovieOrderManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiMovieOrderController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiMovieOrderController.class);
    private FiMovieOrderManager fiMovieOrderManager = null;

    public void setFiMovieOrderManager(FiMovieOrderManager fiMovieOrderManager) {
        this.fiMovieOrderManager = fiMovieOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiMovieOrder fiMovieOrder = new FiMovieOrder();
        // populate object with request parameters
        BeanUtils.populate(fiMovieOrder, request.getParameterMap());

	//List fiMovieOrders = fiMovieOrderManager.getFiMovieOrders(fiMovieOrder);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiMovieOrderListTable",request, 20);
        List fiMovieOrders = fiMovieOrderManager.getFiMovieOrdersByCrm(crm,pager);
        request.setAttribute("fiMovieOrderListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiMovieOrderList", "fiMovieOrderList", fiMovieOrders);
    }
}
