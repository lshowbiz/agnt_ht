package com.joymain.jecs.fi.webapp.action;

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
import com.joymain.jecs.fi.model.JfiDepositList;
import com.joymain.jecs.fi.service.JfiDepositListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiDepositListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiDepositListController.class);
    private JfiDepositListManager jfiDepositListManager = null;

    public void setJfiDepositListManager(JfiDepositListManager jfiDepositListManager) {
        this.jfiDepositListManager = jfiDepositListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiDepositList jfiDepositList = new JfiDepositList();
        // populate object with request parameters
        BeanUtils.populate(jfiDepositList, request.getParameterMap());

	//List jfiDepositLists = jfiDepositListManager.getJfiDepositLists(jfiDepositList);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiDepositListListTable",request, 20);
        List jfiDepositLists = jfiDepositListManager.getJfiDepositListsByCrm(crm,pager);
        request.setAttribute("jfiDepositListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiDepositListList", "jfiDepositListList", jfiDepositLists);
    }
}
