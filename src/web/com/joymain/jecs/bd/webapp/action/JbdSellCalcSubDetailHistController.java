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
import com.joymain.jecs.bd.model.JbdSellCalcSubDetailHist;
import com.joymain.jecs.bd.service.JbdSellCalcSubDetailHistManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSellCalcSubDetailHistController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSellCalcSubDetailHistController.class);
    private JbdSellCalcSubDetailHistManager jbdSellCalcSubDetailHistManager = null;

    public void setJbdSellCalcSubDetailHistManager(JbdSellCalcSubDetailHistManager jbdSellCalcSubDetailHistManager) {
        this.jbdSellCalcSubDetailHistManager = jbdSellCalcSubDetailHistManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdSellCalcSubDetailHistListTable",request, 10);
        List jbdSellCalcSubDetailHists = jbdSellCalcSubDetailHistManager.getJbdSellCalcSubDetailHistsByCrm(crm,pager);
        BigDecimal sumPv=jbdSellCalcSubDetailHistManager.getSumJbdSellCalcRecommendBouns(crm);
        request.setAttribute("jbdSellCalcSubDetailHistListTable_totalRows", pager.getTotalObjects());
        
        request.setAttribute("sumPv", sumPv);


        return new ModelAndView("bd/jbdSellCalcSubDetailHistList", Constants.JBDSELLCALCSUBDETAILHIST_LIST, jbdSellCalcSubDetailHists);
    }
}
