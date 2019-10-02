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
import com.joymain.jecs.bd.model.JbdManuallyAdjustStar;
import com.joymain.jecs.bd.service.JbdManuallyAdjustStarManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdManuallyAdjustStarController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdManuallyAdjustStarController.class);
    private JbdManuallyAdjustStarManager jbdManuallyAdjustStarManager = null;

    public void setJbdManuallyAdjustStarManager(JbdManuallyAdjustStarManager jbdManuallyAdjustStarManager) {
        this.jbdManuallyAdjustStarManager = jbdManuallyAdjustStarManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdManuallyAdjustStarListTable",request, 20);

        WeekFormatUtil.setSearchFweek(crm);
        String userCode=request.getParameter("userCode");
  
        
        List jbdManuallyAdjustStars = jbdManuallyAdjustStarManager.getJbdManuallyAdjustStarsByCrm(crm,pager);
        request.setAttribute("jbdManuallyAdjustStarListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdManuallyAdjustStarList", Constants.JBDMANUALLYADJUSTSTAR_LIST, jbdManuallyAdjustStars);
    }
}
