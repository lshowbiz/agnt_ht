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
import com.joymain.jecs.bd.model.JbdUserValidList;
import com.joymain.jecs.bd.service.JbdUserValidListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdUserValidListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdUserValidListController.class);
    private JbdUserValidListManager jbdUserValidListManager = null;

    public void setJbdUserValidListManager(JbdUserValidListManager jbdUserValidListManager) {
        this.jbdUserValidListManager = jbdUserValidListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdUserValidList jbdUserValidList = new JbdUserValidList();
        // populate object with request parameters
        BeanUtils.populate(jbdUserValidList, request.getParameterMap());

	//List jbdUserValidLists = jbdUserValidListManager.getJbdUserValidLists(jbdUserValidList);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdUserValidListListTable",request, 20);
        List jbdUserValidLists = jbdUserValidListManager.getJbdUserValidListsByCrm(crm,pager);
        request.setAttribute("jbdUserValidListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdUserValidListList", Constants.JBDUSERVALIDLIST_LIST, jbdUserValidLists);
    }
}
