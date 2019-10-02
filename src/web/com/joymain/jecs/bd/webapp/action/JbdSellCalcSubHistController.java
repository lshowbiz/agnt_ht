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
import com.joymain.jecs.bd.model.JbdSellCalcSubHist;
import com.joymain.jecs.bd.service.JbdSellCalcSubHistManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSellCalcSubHistController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSellCalcSubHistController.class);
    private JbdSellCalcSubHistManager jbdSellCalcSubHistManager = null;

    public void setJbdSellCalcSubHistManager(JbdSellCalcSubHistManager jbdSellCalcSubHistManager) {
        this.jbdSellCalcSubHistManager = jbdSellCalcSubHistManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdSellCalcSubHist jbdSellCalcSubHist = new JbdSellCalcSubHist();
        // populate object with request parameters
        BeanUtils.populate(jbdSellCalcSubHist, request.getParameterMap());

	//List jbdSellCalcSubHists = jbdSellCalcSubHistManager.getJbdSellCalcSubHists(jbdSellCalcSubHist);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdSellCalcSubHistListTable",request, 20);
        List jbdSellCalcSubHists = jbdSellCalcSubHistManager.getJbdSellCalcSubHistsByCrm(crm,pager);
        request.setAttribute("jbdSellCalcSubHistListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdSellCalcSubHistList", Constants.JBDSELLCALCSUBHIST_LIST, jbdSellCalcSubHists);
    }
}
