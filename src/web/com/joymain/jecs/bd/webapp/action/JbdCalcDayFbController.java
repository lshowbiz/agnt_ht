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
import com.joymain.jecs.bd.model.JbdCalcDayFb;
import com.joymain.jecs.bd.service.JbdCalcDayFbManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdCalcDayFbController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdCalcDayFbController.class);
    private JbdCalcDayFbManager jbdCalcDayFbManager = null;

    public void setJbdCalcDayFbManager(JbdCalcDayFbManager jbdCalcDayFbManager) {
        this.jbdCalcDayFbManager = jbdCalcDayFbManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


	//List jbdCalcDayFbs = jbdCalcDayFbManager.getJbdCalcDayFbs(jbdCalcDayFb);
	/**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        

        //crm.addField("freezeStatus", "0");
        
        Pager pager = new Pager("jbdCalcDayFbListTable",request, 20);
        List jbdCalcDayFbs = jbdCalcDayFbManager.getJbdCalcDayFbsByCrm(crm,pager);
        request.setAttribute("jbdCalcDayFbListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdCalcDayFbList", "jbdCalcDayFbList", jbdCalcDayFbs);
    }
}
