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
import com.joymain.jecs.fi.model.JfiAmountDetail;
import com.joymain.jecs.fi.service.JfiAmountDetailManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiAmountDetailController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiAmountDetailController.class);
    private JfiAmountDetailManager jfiAmountDetailManager = null;

    public void setJfiAmountDetailManager(JfiAmountDetailManager jfiAmountDetailManager) {
        this.jfiAmountDetailManager = jfiAmountDetailManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiAmountDetail jfiAmountDetail = new JfiAmountDetail();
        // populate object with request parameters
        BeanUtils.populate(jfiAmountDetail, request.getParameterMap());

	//List jfiAmountDetails = jfiAmountDetailManager.getJfiAmountDetails(jfiAmountDetail);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
	String quotaId = request.getParameter("quotaId");
	crm.setValue("quotaId", quotaId);
        Pager pager = new Pager("jfiAmountDetailListTable",request, 20);
        List jfiAmountDetails = jfiAmountDetailManager.getJfiAmountDetailsByCrm(crm,pager);
        request.setAttribute("jfiAmountDetailListTable_totalRows", pager.getTotalObjects());
        /*****/
        request.setAttribute("quotaId", quotaId);
        return new ModelAndView("fi/jfiAmountDetailList", Constants.JFIAMOUNTDETAIL_LIST, jfiAmountDetails);
    }
}
