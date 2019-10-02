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
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.mi.service.JmiLevelNoteManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiLevelNoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiLevelNoteController.class);
    private JmiLevelNoteManager jmiLevelNoteManager = null;

    public void setJmiLevelNoteManager(JmiLevelNoteManager jmiLevelNoteManager) {
        this.jmiLevelNoteManager = jmiLevelNoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiLevelNote jmiLevelNote = new JmiLevelNote();
        // populate object with request parameters
        BeanUtils.populate(jmiLevelNote, request.getParameterMap());

	//List jmiLevelNotes = jmiLevelNoteManager.getJmiLevelNotes(jmiLevelNote);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiLevelNoteListTable",request, 20);
        List jmiLevelNotes = jmiLevelNoteManager.getJmiLevelNotesByCrm(crm,pager);
        request.setAttribute("jmiLevelNoteListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiLevelNoteList", "jmiLevelNoteList", jmiLevelNotes);
    }
}
