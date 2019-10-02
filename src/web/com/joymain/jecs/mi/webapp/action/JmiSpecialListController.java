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
import com.joymain.jecs.mi.model.JmiSpecialList;
import com.joymain.jecs.mi.service.JmiSpecialListManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiSpecialListController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiSpecialListController.class);
    private JmiSpecialListManager jmiSpecialListManager = null;

    public void setJmiSpecialListManager(JmiSpecialListManager jmiSpecialListManager) {
        this.jmiSpecialListManager = jmiSpecialListManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiSpecialList jmiSpecialList = new JmiSpecialList();
        // populate object with request parameters
        BeanUtils.populate(jmiSpecialList, request.getParameterMap());

	//List jmiSpecialLists = jmiSpecialListManager.getJmiSpecialLists(jmiSpecialList);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiSpecialListListTable",request, 20);
        List jmiSpecialLists = jmiSpecialListManager.getJmiSpecialListsByCrm(crm,pager);
        request.setAttribute("jmiSpecialListListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiSpecialListList", "jmiSpecialListList", jmiSpecialLists);
    }
}
