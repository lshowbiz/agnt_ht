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
import com.joymain.jecs.fi.model.JfiSunMemberOrderList;
import com.joymain.jecs.fi.service.JfiSunMemberOrderListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiSunMemberOrderListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunMemberOrderListController.class);
    private JfiSunMemberOrderListManager jfiSunMemberOrderListManager = null;

    public void setJfiSunMemberOrderListManager(JfiSunMemberOrderListManager jfiSunMemberOrderListManager) {
        this.jfiSunMemberOrderListManager = jfiSunMemberOrderListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiSunMemberOrderList jfiSunMemberOrderList = new JfiSunMemberOrderList();
        // populate object with request parameters
        BeanUtils.populate(jfiSunMemberOrderList, request.getParameterMap());

	//List jfiSunMemberOrderLists = jfiSunMemberOrderListManager.getJfiSunMemberOrderLists(jfiSunMemberOrderList);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiSunMemberOrderListListTable",request, 20);
        List jfiSunMemberOrderLists = jfiSunMemberOrderListManager.getJfiSunMemberOrderListsByCrm(crm,pager);
        request.setAttribute("jfiSunMemberOrderListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiSunMemberOrderListList", Constants.JFISUNMEMBERORDERLIST_LIST, jfiSunMemberOrderLists);
    }
}
