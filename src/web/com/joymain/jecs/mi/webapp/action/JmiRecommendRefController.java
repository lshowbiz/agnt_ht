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
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiRecommendRefController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiRecommendRefController.class);
    private JmiRecommendRefManager jmiRecommendRefManager = null;

    public void setJmiRecommendRefManager(JmiRecommendRefManager jmiRecommendRefManager) {
        this.jmiRecommendRefManager = jmiRecommendRefManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiRecommendRef jmiRecommendRef = new JmiRecommendRef();
        // populate object with request parameters
        BeanUtils.populate(jmiRecommendRef, request.getParameterMap());

	//List jmiRecommendRefs = jmiRecommendRefManager.getJmiRecommendRefs(jmiRecommendRef);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiRecommendRefListTable",request, 20);
        List jmiRecommendRefs = jmiRecommendRefManager.getJmiRecommendRefsByCrm(crm,pager);
        request.setAttribute("jmiRecommendRefListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiRecommendRefList", Constants.JMIRECOMMENDREF_LIST, jmiRecommendRefs);
    }
}
