package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdManuallyAdjustAlgebra;
import com.joymain.jecs.bd.service.JbdManuallyAdjustAlgebraManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdManuallyAdjustAlgebraController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdManuallyAdjustAlgebraController.class);
    private JbdManuallyAdjustAlgebraManager jbdManuallyAdjustAlgebraManager = null;

    public void setJbdManuallyAdjustAlgebraManager(JbdManuallyAdjustAlgebraManager jbdManuallyAdjustAlgebraManager) {
        this.jbdManuallyAdjustAlgebraManager = jbdManuallyAdjustAlgebraManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        
        WeekFormatUtil.setSearchFweek(crm);
        Pager pager = new Pager("jbdManuallyAdjustAlgebraListTable",request, 20);
        List jbdManuallyAdjustAlgebras = jbdManuallyAdjustAlgebraManager.getJbdManuallyAdjustAlgebrasByCrm(crm,pager);
        request.setAttribute("jbdManuallyAdjustAlgebraListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdManuallyAdjustAlgebraList", Constants.JBDMANUALLYADJUSTALGEBRA_LIST, jbdManuallyAdjustAlgebras);
    }
}
