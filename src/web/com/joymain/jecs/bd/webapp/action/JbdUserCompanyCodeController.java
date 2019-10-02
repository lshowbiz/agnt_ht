package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdUserCompanyCode;
import com.joymain.jecs.bd.service.JbdUserCompanyCodeManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdUserCompanyCodeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdUserCompanyCodeController.class);
    private JbdUserCompanyCodeManager jbdUserCompanyCodeManager = null;

    public void setJbdUserCompanyCodeManager(JbdUserCompanyCodeManager jbdUserCompanyCodeManager) {
        this.jbdUserCompanyCodeManager = jbdUserCompanyCodeManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        String userCode=request.getParameter("userCode");
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdUserCompanyCodeListTable",request, 20);
        List jbdUserCompanyCodes =null;
        if(!StringUtil.isEmpty(userCode)){

           jbdUserCompanyCodes = jbdUserCompanyCodeManager.getJbdUserCompanyCodesByCrm(crm,pager);
        }
        request.setAttribute("jbdUserCompanyCodeListTable_totalRows", pager.getTotalObjects());


        return new ModelAndView("bd/jbdUserCompanyCodeList", Constants.JBDUSERCOMPANYCODE_LIST, jbdUserCompanyCodes);
    }
}
