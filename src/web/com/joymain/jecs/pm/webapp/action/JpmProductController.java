package com.joymain.jecs.pm.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpmProductController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpmProductController.class);

    private JpmProductManager jpmProductManager = null;

    public void setJpmProductManager(JpmProductManager jpmProductManager) {
        this.jpmProductManager = jpmProductManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JpmProduct jpmProduct = new JpmProduct();
        String view = "pm/jpmProductList";

        // populate object with request parameters
        //        BeanUtils.populate(pmProduct, request.getParameterMap());
        String strAction = request.getParameter("strAction");
        //List jpmProducts = jpmProductManager.getJpmProducts(jpmProduct);
        String selectControl = request.getParameter("selectControl");
        String combineFlag = request.getParameter("combineFlag");
        CommonRecord crm = new CommonRecord();
        if ("selectProduct".equals(strAction)) {
            crm = RequestUtil.toCommonRecord(request);
            request.setAttribute("combineFlag", combineFlag);
            view = "pm/jpmProductSelect";
        } else {
            crm = initCommonRecord(request);
        }

        Pager pager = null;

        if ("1".equals(selectControl) || selectControl == null) {
            pager = new Pager("jpmProductListTable", request, 20);
            List jpmProducts = jpmProductManager.getJpmProductsByCrm(crm, pager);
            request.setAttribute("jpmProductListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView(view, Constants.JPMPRODUCT_LIST, jpmProducts);
        } else if ("2".equals(selectControl) || "3".equals(selectControl) || "4".equals(selectControl) || "5".equals(selectControl)) {
            pager = new Pager("jpmProductSaleNewListTable", request, 20);

            //如果不是全球，则设置区域
            SysUser loginUser = SessionLogin.getLoginUser(request);
            if (!"AA".equalsIgnoreCase(loginUser.getCompanyCode())) {
                crm.setValue("companyCode", loginUser.getCompanyCode());

            }
            String uniNo = request.getParameter("uniNo");
            String productNo = request.getParameter("productNo");
            String productName = request.getParameter("productName");
            crm.setValue("productNo", productNo);
            crm.setValue("productName", productName);
            List jpmProductSaleNews = jpmProductManager.getJpmProductsByCrm2(crm, pager);
            request.setAttribute("jpmProductSaleNewListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView(view, Constants.JPMPRODUCTSALENEW_LIST, jpmProductSaleNews);
        }
        /*****/

        return new ModelAndView(view, Constants.JPMPRODUCT_LIST, null);
    }
}
