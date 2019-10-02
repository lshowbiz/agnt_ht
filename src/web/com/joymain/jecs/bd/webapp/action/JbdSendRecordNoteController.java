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
import com.joymain.jecs.bd.model.JbdSendRecordNote;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSendRecordNoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendRecordNoteController.class);
    private JbdSendRecordNoteManager jbdSendRecordNoteManager = null;

    public void setJbdSendRecordNoteManager(JbdSendRecordNoteManager jbdSendRecordNoteManager) {
        this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdSendRecordNote jbdSendRecordNote = new JbdSendRecordNote();
        // populate object with request parameters
        BeanUtils.populate(jbdSendRecordNote, request.getParameterMap());

	//List jbdSendRecordNotes = jbdSendRecordNoteManager.getJbdSendRecordNotes(jbdSendRecordNote);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdSendRecordNoteListTable",request, 20);
        List jbdSendRecordNotes = jbdSendRecordNoteManager.getJbdSendRecordNotesByCrm(crm,pager);
        request.setAttribute("jbdSendRecordNoteListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdSendRecordNoteList", "jbdSendRecordNoteList", jbdSendRecordNotes);
    }
}
