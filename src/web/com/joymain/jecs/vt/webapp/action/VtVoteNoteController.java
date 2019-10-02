package com.joymain.jecs.vt.webapp.action;

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
import com.joymain.jecs.vt.model.VtVoteNote;
import com.joymain.jecs.vt.service.VtVoteNoteManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class VtVoteNoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(VtVoteNoteController.class);
    private VtVoteNoteManager vtVoteNoteManager = null;

    public void setVtVoteNoteManager(VtVoteNoteManager vtVoteNoteManager) {
        this.vtVoteNoteManager = vtVoteNoteManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        VtVoteNote vtVoteNote = new VtVoteNote();
        // populate object with request parameters
        BeanUtils.populate(vtVoteNote, request.getParameterMap());

	//List vtVoteNotes = vtVoteNoteManager.getVtVoteNotes(vtVoteNote);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("vtVoteNoteListTable",request, 20);
        List vtVoteNotes = vtVoteNoteManager.getVtVoteNotesByCrm(crm,pager);
        request.setAttribute("vtVoteNoteListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("vt/vtVoteNoteList", Constants.VTVOTENOTE_LIST, vtVoteNotes);
    }
}
