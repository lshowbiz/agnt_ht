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
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.model.JpmProductCombination;
import com.joymain.jecs.pm.service.JpmProductCombinationManager;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmProductCombinationController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmProductCombinationController.class);
    private JpmProductCombinationManager jpmProductCombinationManager = null;
    private JpmProductManager jpmProductManager = null;
    public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	public void setJpmProductCombinationManager(JpmProductCombinationManager jpmProductCombinationManager) {
        this.jpmProductCombinationManager = jpmProductCombinationManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        super.initPmProductMap(request);
        JpmProductCombination jpmProductCombination = new JpmProductCombination();
        // populate object with request parameters
        BeanUtils.populate(jpmProductCombination, request.getParameterMap());
        String productNo = request.getParameter("productNo");
        JpmProduct pmProduct = jpmProductManager.getJpmProduct(productNo);
        
        
	//List jpmProductCombinations = jpmProductCombinationManager.getJpmProductCombinations(jpmProductCombination);
	/**** auto pagination ***/
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmProductCombinationListTable",request, 20);
        List jpmProductCombinations = jpmProductCombinationManager.getJpmProductCombinationsByCrm(crm,pager);
        request.setAttribute("jpmProductCombinationListTable_totalRows", pager.getTotalObjects());
        request.setAttribute("pmProduct", pmProduct);
        /*****/
        
        return new ModelAndView("pm/jpmProductCombinationList", Constants.JPMPRODUCTCOMBINATION_LIST, jpmProductCombinations);
    }
}
