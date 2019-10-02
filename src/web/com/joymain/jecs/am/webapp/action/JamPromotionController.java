package com.joymain.jecs.am.webapp.action;

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
import com.joymain.jecs.am.model.JamPromotion;
import com.joymain.jecs.am.service.JamPromotionManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JamPromotionController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JamPromotionController.class);
    private JamPromotionManager jamPromotionManager = null;

    public void setJamPromotionManager(JamPromotionManager jamPromotionManager) {
        this.jamPromotionManager = jamPromotionManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        SysUser defSysUser = SessionLogin.getLoginUser(request);
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", defSysUser.getCompanyCode());
        Pager pager = new Pager("jamPromotionListTable",request, 20);
        List jamPromotions = jamPromotionManager.getJamPromotionsByCrm(crm,pager);
        request.setAttribute("jamPromotionListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("am/jamPromotionList", Constants.JAMPROMOTION_LIST, jamPromotions);
    }
}
