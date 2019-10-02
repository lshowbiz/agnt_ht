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
import com.joymain.jecs.pm.model.JpmProductSaleRelated;
import com.joymain.jecs.pm.service.JpmProductSaleRelatedManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmProductSaleRelatedController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmProductSaleRelatedController.class);
    private JpmProductSaleRelatedManager jpmProductSaleRelatedManager = null;

    public void setJpmProductSaleRelatedManager(JpmProductSaleRelatedManager jpmProductSaleRelatedManager) {
        this.jpmProductSaleRelatedManager = jpmProductSaleRelatedManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmProductSaleRelated jpmProductSaleRelated = new JpmProductSaleRelated();
        // populate object with request parameters
        BeanUtils.populate(jpmProductSaleRelated, request.getParameterMap());

		//List jpmProductSaleRelateds = jpmProductSaleRelatedManager.getJpmProductSaleRelateds(jpmProductSaleRelated);
		/**** auto pagination ***/
		CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jpmProductSaleRelatedListTable",request, 20);
        
        String uniNo = request.getParameter("uniNo");
        String relatedproductNo = request.getParameter("relatedproductNo");
    	String productName = request.getParameter("productName");
    	String status = request.getParameter("status"); 
    	crm.setValue("uniNo", uniNo); 
    	crm.setValue("relatedproductNo", relatedproductNo);
    	crm.setValue("productName", productName); 
    	crm.setValue("status", status); 
        
        List jpmProductSaleRelateds = jpmProductSaleRelatedManager.getJpmProductSaleRelatedsByCrm(crm,pager);
        request.setAttribute("jpmProductSaleRelatedListTable_totalRows", pager.getTotalObjects());
        /*****/
        //存放商品的主键编码
        request.setAttribute("uniNo", request.getParameter("uniNo"));
        request.setAttribute("productNo", request.getParameter("productNo"));
        return new ModelAndView("pm/jpmProductSaleRelatedList", Constants.JPMPRODUCTSALERELATED_LIST, jpmProductSaleRelateds);
    }
}
