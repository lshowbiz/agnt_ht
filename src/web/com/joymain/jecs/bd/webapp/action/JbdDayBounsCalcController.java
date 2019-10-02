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
import com.joymain.jecs.bd.model.JbdDayBounsCalc;
import com.joymain.jecs.bd.service.JbdDayBounsCalcManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdDayBounsCalcController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdDayBounsCalcController.class);
    private JbdDayBounsCalcManager jbdDayBounsCalcManager = null;

    public void setJbdDayBounsCalcManager(JbdDayBounsCalcManager jbdDayBounsCalcManager) {
        this.jbdDayBounsCalcManager = jbdDayBounsCalcManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdDayBounsCalc jbdDayBounsCalc = new JbdDayBounsCalc();
        // populate object with request parameters
        BeanUtils.populate(jbdDayBounsCalc, request.getParameterMap());

	//List jbdDayBounsCalcs = jbdDayBounsCalcManager.getJbdDayBounsCalcs(jbdDayBounsCalc);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdDayBounsCalcListTable",request, 20);
        List jbdDayBounsCalcs = jbdDayBounsCalcManager.getJbdDayBounsCalcsByCrm(crm,pager);
        request.setAttribute("jbdDayBounsCalcListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdDayBounsCalcList", Constants.JBDDAYBOUNSCALC_LIST, jbdDayBounsCalcs);
    }
}
