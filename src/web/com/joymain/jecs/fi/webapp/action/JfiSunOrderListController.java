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
import com.joymain.jecs.fi.model.JfiSunOrderList;
import com.joymain.jecs.fi.service.JfiSunOrderListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiSunOrderListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunOrderListController.class);
    private JfiSunOrderListManager jfiSunOrderListManager = null;

    public void setJfiSunOrderListManager(JfiSunOrderListManager jfiSunOrderListManager) {
        this.jfiSunOrderListManager = jfiSunOrderListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JfiSunOrderList jfiSunOrderList = new JfiSunOrderList();
        // populate object with request parameters
        BeanUtils.populate(jfiSunOrderList, request.getParameterMap());

	//List jfiSunOrderLists = jfiSunOrderListManager.getJfiSunOrderLists(jfiSunOrderList);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiSunOrderListListTable",request, 20);
        List jfiSunOrderLists = jfiSunOrderListManager.getJfiSunOrderListsByCrm(crm,pager);
        request.setAttribute("jfiSunOrderListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiSunOrderListList", Constants.JFISUNORDERLIST_LIST, jfiSunOrderLists);
    }
}
