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
import com.joymain.jecs.mi.model.JmiEcmallNote;
import com.joymain.jecs.mi.service.JmiEcmallNoteManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JmiEcmallNoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JmiEcmallNoteController.class);
    private JmiEcmallNoteManager jmiEcmallNoteManager = null;

    public void setJmiEcmallNoteManager(JmiEcmallNoteManager jmiEcmallNoteManager) {
        this.jmiEcmallNoteManager = jmiEcmallNoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        JmiEcmallNote jmiEcmallNote = new JmiEcmallNote();
        // populate object with request parameters
        BeanUtils.populate(jmiEcmallNote, request.getParameterMap());

	//List jmiEcmallNotes = jmiEcmallNoteManager.getJmiEcmallNotes(jmiEcmallNote);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jmiEcmallNoteListTable",request, 20);
        List jmiEcmallNotes = jmiEcmallNoteManager.getJmiEcmallNotesByCrm(crm,pager);
        request.setAttribute("jmiEcmallNoteListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("mi/jmiEcmallNoteList", "jmiEcmallNoteList", jmiEcmallNotes);
    }
}
