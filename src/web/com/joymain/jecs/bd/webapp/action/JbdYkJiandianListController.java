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
import com.joymain.jecs.bd.model.JbdYkJiandianList;
import com.joymain.jecs.bd.service.JbdYkJiandianListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdYkJiandianListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdYkJiandianListController.class);
    private JbdYkJiandianListManager jbdYkJiandianListManager = null;

    public void setJbdYkJiandianListManager(JbdYkJiandianListManager jbdYkJiandianListManager) {
        this.jbdYkJiandianListManager = jbdYkJiandianListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdYkJiandianList jbdYkJiandianList = new JbdYkJiandianList();
        // populate object with request parameters
        BeanUtils.populate(jbdYkJiandianList, request.getParameterMap());

	//List jbdYkJiandianLists = jbdYkJiandianListManager.getJbdYkJiandianLists(jbdYkJiandianList);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdYkJiandianListListTable",request, 20);
        List jbdYkJiandianLists = jbdYkJiandianListManager.getJbdYkJiandianListsByCrm(crm,pager);
        request.setAttribute("jbdYkJiandianListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdYkJiandianListList","jbdYkJiandianListList", jbdYkJiandianLists);
    }
}
