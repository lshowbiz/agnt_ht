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
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiMemberUpgradeNote;
import com.joymain.jecs.mi.service.JmiMemberUpgradeNoteManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiMemberUpgradeNoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberUpgradeNoteController.class);
    private JmiMemberUpgradeNoteManager jmiMemberUpgradeNoteManager = null;

    public void setJmiMemberUpgradeNoteManager(JmiMemberUpgradeNoteManager jmiMemberUpgradeNoteManager) {
        this.jmiMemberUpgradeNoteManager = jmiMemberUpgradeNoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
        CommonRecord crm=RequestUtil.toCommonRecord(request);

        crm.addField("companyCode", defSysUser.getCompanyCode());
        Pager pager = new Pager("jmiMemberUpgradeNoteListTable",request, 20);
        String userCode=request.getParameter("userCode");
        List jmiMemberUpgradeNotes=null;
        if(!StringUtil.isEmpty(userCode)){
           jmiMemberUpgradeNotes = jmiMemberUpgradeNoteManager.getJmiMemberUpgradeNotesByCrm(crm,pager);
        }
        
        request.setAttribute("jmiMemberUpgradeNoteListTable_totalRows", pager.getTotalObjects());


        return new ModelAndView("mi/jmiMemberUpgradeNoteList", Constants.JMIMEMBERUPGRADENOTE_LIST, jmiMemberUpgradeNotes);
    }
}
