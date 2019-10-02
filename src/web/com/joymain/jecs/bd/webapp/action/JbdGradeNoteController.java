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
import com.joymain.jecs.bd.model.JbdGradeNote;
import com.joymain.jecs.bd.service.JbdGradeNoteManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdGradeNoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdGradeNoteController.class);
    private JbdGradeNoteManager jbdGradeNoteManager = null;

    public void setJbdGradeNoteManager(JbdGradeNoteManager jbdGradeNoteManager) {
        this.jbdGradeNoteManager = jbdGradeNoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JbdGradeNote jbdGradeNote = new JbdGradeNote();
        // populate object with request parameters
        BeanUtils.populate(jbdGradeNote, request.getParameterMap());

	//List jbdGradeNotes = jbdGradeNoteManager.getJbdGradeNotes(jbdGradeNote);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdGradeNoteListTable",request, 20);
        List jbdGradeNotes = jbdGradeNoteManager.getJbdGradeNotesByCrm(crm,pager);
        request.setAttribute("jbdGradeNoteListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("bd/jbdGradeNoteList", Constants.JBDGRADENOTE_LIST, jbdGradeNotes);
    }
}
