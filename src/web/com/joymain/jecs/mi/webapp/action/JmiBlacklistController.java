package com.joymain.jecs.mi.webapp.action;

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
import com.joymain.jecs.mi.model.JmiBlacklist;
import com.joymain.jecs.mi.service.JmiBlacklistManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiBlacklistController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiBlacklistController.class);
    private JmiBlacklistManager jmiBlacklistManager = null;

    public void setJmiBlacklistManager(JmiBlacklistManager jmiBlacklistManager) {
        this.jmiBlacklistManager = jmiBlacklistManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


        String strAction = request.getParameter("strAction");

		if ("deleteJmiBlacklist".equals(strAction)  ) {
			jmiBlacklistManager.removeJmiBlacklist(request.getParameter("blId").toString());
			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
        }
        
        
        
        
        
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", defSysUser.getCompanyCode());
        
        Pager pager = new Pager("jmiBlacklistListTable",request, 20);
        List jmiBlacklists = jmiBlacklistManager.getJmiBlacklistsByCrm(crm,pager);
        request.setAttribute("jmiBlacklistListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiBlacklistList", Constants.JMIBLACKLIST_LIST, jmiBlacklists);
    }
}
