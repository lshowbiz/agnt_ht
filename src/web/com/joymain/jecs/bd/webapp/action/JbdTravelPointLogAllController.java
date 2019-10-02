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
import com.joymain.jecs.bd.model.JbdTravelPointLogAll;
import com.joymain.jecs.bd.service.JbdTravelPointLogAllManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdTravelPointLogAllController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdTravelPointLogAllController.class);
    private JbdTravelPointLogAllManager jbdTravelPointLogAllManager = null;

    public void setJbdTravelPointLogAllManager(JbdTravelPointLogAllManager jbdTravelPointLogAllManager) {
        this.jbdTravelPointLogAllManager = jbdTravelPointLogAllManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

	//List jbdTravelPointLogAlls = jbdTravelPointLogAllManager.getJbdTravelPointLogAlls(jbdTravelPointLogAll);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdTravelPointLogAllListTable",request, 20);
        List jbdTravelPointLogAlls = jbdTravelPointLogAllManager.getJbdTravelPointLogAllsByCrm(crm,pager);
        request.setAttribute("jbdTravelPointLogAllListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdTravelPointLogAllList","jbdTravelPointLogAllList", jbdTravelPointLogAlls);
    }
}
