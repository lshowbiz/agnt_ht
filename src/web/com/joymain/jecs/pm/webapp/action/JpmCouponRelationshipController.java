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
import com.joymain.jecs.pm.model.JpmCouponRelationship;
import com.joymain.jecs.pm.service.JpmCouponRelationshipManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmCouponRelationshipController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmCouponRelationshipController.class);
    private JpmCouponRelationshipManager jpmCouponRelationshipManager = null;

    public void setJpmCouponRelationshipManager(JpmCouponRelationshipManager jpmCouponRelationshipManager) {
        this.jpmCouponRelationshipManager = jpmCouponRelationshipManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmCouponRelationship jpmCouponRelationship = new JpmCouponRelationship();
        // populate object with request parameters
        BeanUtils.populate(jpmCouponRelationship, request.getParameterMap());

	//List jpmCouponRelationships = jpmCouponRelationshipManager.getJpmCouponRelationships(jpmCouponRelationship);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmCouponRelationshipListTable",request, 20);
        List jpmCouponRelationships = jpmCouponRelationshipManager.getJpmCouponRelationshipsByCrm(crm,pager);
        request.setAttribute("jpmCouponRelationshipListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("pm/jpmCouponRelationshipList", "", jpmCouponRelationships);
    }
}
