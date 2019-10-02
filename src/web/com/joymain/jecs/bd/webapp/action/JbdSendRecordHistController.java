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
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSendRecordHistController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendRecordHistController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager = null;

    public void setJbdSendRecordHistManager(JbdSendRecordHistManager jbdSendRecordHistManager) {
        this.jbdSendRecordHistManager = jbdSendRecordHistManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdSendRecordHist jbdSendRecordHist = new JbdSendRecordHist();
        // populate object with request parameters
        BeanUtils.populate(jbdSendRecordHist, request.getParameterMap());

	//List jbdSendRecordHists = jbdSendRecordHistManager.getJbdSendRecordHists(jbdSendRecordHist);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdSendRecordHistListTable",request, 20);
        List jbdSendRecordHists = jbdSendRecordHistManager.getJbdSendRecordHistsByCrm(crm,pager);
        request.setAttribute("jbdSendRecordHistListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdSendRecordHistList", Constants.JBDSENDRECORDHIST_LIST, jbdSendRecordHists);
    }
}
