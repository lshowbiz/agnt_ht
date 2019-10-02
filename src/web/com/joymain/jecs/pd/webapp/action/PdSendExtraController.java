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
import com.joymain.jecs.pd.model.PdSendExtra;
import com.joymain.jecs.pd.service.PdSendExtraManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdSendExtraController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdSendExtraController.class);
    private PdSendExtraManager pdSendExtraManager = null;

    public void setPdSendExtraManager(PdSendExtraManager pdSendExtraManager) {
        this.pdSendExtraManager = pdSendExtraManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        super.initPmProductMap(request);
        PdSendExtra pdSendExtra = new PdSendExtra();
        // populate object with request parameters
        BeanUtils.populate(pdSendExtra, request.getParameterMap());

	//List pdSendExtras = pdSendExtraManager.getPdSendExtras(pdSendExtra);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdSendExtraListTable",request, 20);
        List pdSendExtras = pdSendExtraManager.getPdSendExtrasByCrm(crm,pager);
        request.setAttribute("pdSendExtraListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pd/pdSendExtraList", Constants.PDSENDEXTRA_LIST, pdSendExtras);
    }
}
