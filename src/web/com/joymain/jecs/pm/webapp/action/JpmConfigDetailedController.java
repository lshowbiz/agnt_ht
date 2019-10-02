package com.joymain.jecs.pm.webapp.action;

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
import com.joymain.jecs.pm.model.JpmConfigDetailed;
import com.joymain.jecs.pm.service.JpmConfigDetailedManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmConfigDetailedController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmConfigDetailedController.class);
    private JpmConfigDetailedManager jpmConfigDetailedManager = null;

    public void setJpmConfigDetailedManager(JpmConfigDetailedManager jpmConfigDetailedManager) {
        this.jpmConfigDetailedManager = jpmConfigDetailedManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmConfigDetailed jpmConfigDetailed = new JpmConfigDetailed();
        // populate object with request parameters
        BeanUtils.populate(jpmConfigDetailed, request.getParameterMap());

	//List jpmConfigDetaileds = jpmConfigDetailedManager.getJpmConfigDetaileds(jpmConfigDetailed);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmConfigDetailedListTable",request, 20);
        List jpmConfigDetaileds = jpmConfigDetailedManager.getJpmConfigDetailedsByCrm(crm,pager);
        request.setAttribute("jpmConfigDetailedListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pm/jpmConfigDetailedList", Constants.JPMCONFIGDETAILED_LIST, jpmConfigDetaileds);
    }
}
