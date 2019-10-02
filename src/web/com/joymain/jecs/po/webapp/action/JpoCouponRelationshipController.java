package com.joymain.jecs.po.webapp.action;

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
import com.joymain.jecs.po.model.JpoCouponRelationship;
import com.joymain.jecs.po.service.JpoCouponRelationshipManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpoCouponRelationshipController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoCouponRelationshipController.class);
    private JpoCouponRelationshipManager jpoCouponRelationshipManager = null;

    public void setJpoCouponRelationshipManager(JpoCouponRelationshipManager jpoCouponRelationshipManager) {
        this.jpoCouponRelationshipManager = jpoCouponRelationshipManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpoCouponRelationship jpoCouponRelationship = new JpoCouponRelationship();
        // populate object with request parameters
        BeanUtils.populate(jpoCouponRelationship, request.getParameterMap());

	//List jpoCouponRelationships = jpoCouponRelationshipManager.getJpoCouponRelationships(jpoCouponRelationship);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpoCouponRelationshipListTable",request, 20);
        List jpoCouponRelationships = jpoCouponRelationshipManager.getJpoCouponRelationshipsByCrm(crm,pager);
        request.setAttribute("jpoCouponRelationshipListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("po/jpoCouponRelationshipList", "", jpoCouponRelationships);
    }
}
