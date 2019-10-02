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
import com.joymain.jecs.pm.model.JpmCombinationRelated;
import com.joymain.jecs.pm.service.JpmCombinationRelatedManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmCombinationRelatedController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmCombinationRelatedController.class);
    private JpmCombinationRelatedManager jpmCombinationRelatedManager = null;

    public void setJpmCombinationRelatedManager(JpmCombinationRelatedManager jpmCombinationRelatedManager) {
        this.jpmCombinationRelatedManager = jpmCombinationRelatedManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmCombinationRelated jpmCombinationRelated = new JpmCombinationRelated();
        // populate object with request parameters
        BeanUtils.populate(jpmCombinationRelated, request.getParameterMap());

	//List jpmCombinationRelateds = jpmCombinationRelatedManager.getJpmCombinationRelateds(jpmCombinationRelated);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmCombinationRelatedListTable",request, 20);
        List jpmCombinationRelateds = jpmCombinationRelatedManager.getJpmCombinationRelatedsByCrm(crm,pager);
        request.setAttribute("jpmCombinationRelatedListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pm/jpmCombinationRelatedList", Constants.JPMCOMBINATIONRELATED_LIST, jpmCombinationRelateds);
    }
}
