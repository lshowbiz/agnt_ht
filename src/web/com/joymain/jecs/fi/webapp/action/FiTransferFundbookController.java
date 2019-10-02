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
import com.joymain.jecs.fi.model.FiTransferFundbook;
import com.joymain.jecs.fi.service.FiTransferFundbookManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 产业基金转账审批
 * @author Administrator
 *
 */
public class FiTransferFundbookController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiTransferFundbookController.class);
    private FiTransferFundbookManager fiTransferFundbookManager = null;

    public void setFiTransferFundbookManager(FiTransferFundbookManager fiTransferFundbookManager) {
        this.fiTransferFundbookManager = fiTransferFundbookManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        FiTransferFundbook fiTransferFundbook = new FiTransferFundbook();
        // populate object with request parameters
        BeanUtils.populate(fiTransferFundbook, request.getParameterMap());

	//List fiTransferFundbooks = fiTransferFundbookManager.getFiTransferFundbooks(fiTransferFundbook);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiTransferFundbookListTable",request, 20);
        List fiTransferFundbooks = fiTransferFundbookManager.getFiTransferFundbooksByCrm(crm,pager);
        request.setAttribute("fiTransferFundbookListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiTransferFundbookList", "fiTransferFundbookList", fiTransferFundbooks);
    }
}
