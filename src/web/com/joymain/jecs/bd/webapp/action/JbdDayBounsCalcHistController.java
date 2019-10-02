package com.joymain.jecs.bd.webapp.action;

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
import com.joymain.jecs.bd.model.JbdDayBounsCalcHist;
import com.joymain.jecs.bd.service.JbdDayBounsCalcHistManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdDayBounsCalcHistController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdDayBounsCalcHistController.class);
    private JbdDayBounsCalcHistManager jbdDayBounsCalcHistManager = null;

    public void setJbdDayBounsCalcHistManager(JbdDayBounsCalcHistManager jbdDayBounsCalcHistManager) {
        this.jbdDayBounsCalcHistManager = jbdDayBounsCalcHistManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdDayBounsCalcHist jbdDayBounsCalcHist = new JbdDayBounsCalcHist();
        // populate object with request parameters
        BeanUtils.populate(jbdDayBounsCalcHist, request.getParameterMap());

	//List jbdDayBounsCalcHists = jbdDayBounsCalcHistManager.getJbdDayBounsCalcHists(jbdDayBounsCalcHist);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdDayBounsCalcHistListTable",request, 20);
        List jbdDayBounsCalcHists = jbdDayBounsCalcHistManager.getJbdDayBounsCalcHistsByCrm(crm,pager);
        request.setAttribute("jbdDayBounsCalcHistListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jbdDayBounsCalcHistList", Constants.JBDDAYBOUNSCALCHIST_LIST, jbdDayBounsCalcHists);
    }
}
