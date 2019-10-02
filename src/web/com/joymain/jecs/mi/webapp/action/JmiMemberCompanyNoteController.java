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
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiMemberCompanyNote;
import com.joymain.jecs.mi.service.JmiMemberCompanyNoteManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiMemberCompanyNoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiMemberCompanyNoteController.class);
    private JmiMemberCompanyNoteManager jmiMemberCompanyNoteManager = null;

    public void setJmiMemberCompanyNoteManager(JmiMemberCompanyNoteManager jmiMemberCompanyNoteManager) {
        this.jmiMemberCompanyNoteManager = jmiMemberCompanyNoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


        String userCode=request.getParameter("userCode");

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiMemberCompanyNoteListTable",request, 20);
        List jmiMemberCompanyNotes =null;
        if(!StringUtil.isEmpty(userCode)){
           jmiMemberCompanyNotes = jmiMemberCompanyNoteManager.getJmiMemberCompanyNotesByCrm(crm,pager);
        }
        request.setAttribute("jmiMemberCompanyNoteListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiMemberCompanyNoteList", Constants.JMIMEMBERCOMPANYNOTE_LIST, jmiMemberCompanyNotes);
    }
}
