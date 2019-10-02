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
import com.joymain.jecs.bd.model.JbdSendRecord;
import com.joymain.jecs.bd.service.JbdSendRecordManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSendRecordController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendRecordController.class);
    private JbdSendRecordManager jbdSendRecordManager = null;

    public void setJbdSendRecordManager(JbdSendRecordManager jbdSendRecordManager) {
        this.jbdSendRecordManager = jbdSendRecordManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdSendRecordListTable",request, 20);
        
        String userCode=request.getParameter("userCode");
        String name=request.getParameter("name");
        String wweek=request.getParameter("wweek");
        String cardType=request.getParameter("cardType");
        List jbdSendRecords =null;
        if(!StringUtil.isEmpty(userCode)||!StringUtil.isEmpty(name)||!StringUtil.isEmpty(wweek)||!StringUtil.isEmpty(cardType)){
            jbdSendRecords = jbdSendRecordManager.getJbdSendRecordsByCrm(crm,pager);
        }
        request.setAttribute("jbdSendRecordListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdSendRecordList", Constants.JBDSENDRECORD_LIST, jbdSendRecords);
    }
}
