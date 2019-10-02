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
import com.joymain.jecs.bd.model.JbdCalcDayKbList;
import com.joymain.jecs.bd.service.JbdCalcDayKbListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdCalcDayKbListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdCalcDayKbListController.class);
    private JbdCalcDayKbListManager jbdCalcDayKbListManager = null;

    public void setJbdCalcDayKbListManager(JbdCalcDayKbListManager jbdCalcDayKbListManager) {
        this.jbdCalcDayKbListManager = jbdCalcDayKbListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


	//List jbdCalcDayKbLists = jbdCalcDayKbListManager.getJbdCalcDayKbLists(jbdCalcDayKbList);
	/**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdCalcDayKbListListTable",request, 20);
        List jbdCalcDayKbLists = jbdCalcDayKbListManager.getJbdCalcDayKbListsByCrm(crm,pager);
        request.setAttribute("jbdCalcDayKbListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdCalcDayKbListList", "jbdCalcDayKbListList", jbdCalcDayKbLists);
    }
}
