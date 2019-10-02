package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdNotChangeProduct;
import com.joymain.jecs.pd.service.PdNotChangeProductManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PdNotChangeProductController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdNotChangeProductController.class);
    private PdNotChangeProductManager pdNotChangeProductManager = null;

    public void setPdNotChangeProductManager(PdNotChangeProductManager pdNotChangeProductManager) {
        this.pdNotChangeProductManager = pdNotChangeProductManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        PdNotChangeProduct pdNotChangeProduct = new PdNotChangeProduct();
        BeanUtils.populate(pdNotChangeProduct, request.getParameterMap());

	    CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("pdNotChangeProductListTable",request, 20);
        crm.addField("companyCode", "CN");
        List pdNotChangeProducts = pdNotChangeProductManager.getPdNotChangeProductsByCrm(crm,pager);
        request.setAttribute("pdNotChangeProductListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("pd/pdNotChangeProductList", Constants.PDNOTCHANGEPRODUCT_LIST, pdNotChangeProducts);
    }
}
