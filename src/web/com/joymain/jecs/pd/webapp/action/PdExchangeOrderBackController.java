package com.joymain.jecs.pd.webapp.action;

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
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.pd.service.PdExchangeOrderBackManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdExchangeOrderBackController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdExchangeOrderBackController.class);
    private PdExchangeOrderBackManager pdExchangeOrderBackManager = null;

    public void setPdExchangeOrderBackManager(PdExchangeOrderBackManager pdExchangeOrderBackManager) {
        this.pdExchangeOrderBackManager = pdExchangeOrderBackManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdExchangeOrderBack pdExchangeOrderBack = new PdExchangeOrderBack();
        // populate object with request parameters
        BeanUtils.populate(pdExchangeOrderBack, request.getParameterMap());

	//List pdExchangeOrderBacks = pdExchangeOrderBackManager.getPdExchangeOrderBacks(pdExchangeOrderBack);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdExchangeOrderBackListTable",request, 20);
        List pdExchangeOrderBacks = pdExchangeOrderBackManager.getPdExchangeOrderBacksByCrm(crm,pager);
        request.setAttribute("pdExchangeOrderBackListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdExchangeOrderBackList", Constants.PDEXCHANGEORDERBACK_LIST, pdExchangeOrderBacks);
    }
}
