package com.joymain.jecs.mi.webapp.action;

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
import com.joymain.jecs.mi.model.JmiTicket;
import com.joymain.jecs.mi.service.JmiTicketManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiTicketController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiTicketController.class);
    private JmiTicketManager jmiTicketManager = null;

    public void setJmiTicketManager(JmiTicketManager jmiTicketManager) {
        this.jmiTicketManager = jmiTicketManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiTicket jmiTicket = new JmiTicket();
        // populate object with request parameters
        BeanUtils.populate(jmiTicket, request.getParameterMap());

	//List jmiTickets = jmiTicketManager.getJmiTickets(jmiTicket);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiTicketListTable",request, 20);
        List jmiTickets = jmiTicketManager.getJmiTicketsByCrm(crm,pager);
        request.setAttribute("jmiTicketListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiTicketList", "jmiTickets", jmiTickets);
    }
}
