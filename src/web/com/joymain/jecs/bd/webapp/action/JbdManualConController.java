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
import com.joymain.jecs.bd.model.JbdManualCon;
import com.joymain.jecs.bd.service.JbdManualConManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdManualConController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdManualConController.class);
    private JbdManualConManager jbdManualConManager = null;

    public void setJbdManualConManager(JbdManualConManager jbdManualConManager) {
        this.jbdManualConManager = jbdManualConManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdManualCon jbdManualCon = new JbdManualCon();
        // populate object with request parameters
        BeanUtils.populate(jbdManualCon, request.getParameterMap());

	//List jbdManualCons = jbdManualConManager.getJbdManualCons(jbdManualCon);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdManualConListTable",request, 20);
        List jbdManualCons = jbdManualConManager.getJbdManualConsByCrm(crm,pager);
        request.setAttribute("jbdManualConListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdManualConList", "jbdManualConList", jbdManualCons);
    }
}
