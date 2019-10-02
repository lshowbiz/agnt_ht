package com.joymain.jecs.mi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.JbdMemberLinkCalcHist;
import com.joymain.jecs.bd.service.JbdMemberLinkCalcHistManager;
import com.joymain.jecs.bd.service.JbdSellCalcSubHistManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;

public class MiBonusKpvChangeFormController extends BaseFormController {
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager = null;


	public void setJbdMemberLinkCalcHistManager(JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
        this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
    }
    public MiBonusKpvChangeFormController() {
        setCommandName("jbdMemberLinkCalcHist");
        setCommandClass(JbdMemberLinkCalcHist.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdMemberLinkCalcHist jbdMemberLinkCalcHist = null;

        if (!StringUtils.isEmpty(id)) {
            jbdMemberLinkCalcHist = jbdMemberLinkCalcHistManager.getJbdMemberLinkCalcHist(id);
        } else {
            jbdMemberLinkCalcHist = new JbdMemberLinkCalcHist();
        }

        return jbdMemberLinkCalcHist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdMemberLinkCalcHist jbdMemberLinkCalcHist = (JbdMemberLinkCalcHist) command;
        

        
        try {
        	jbdMemberLinkCalcHistManager.saveJbdMemberLinkCalcHistPv(jbdMemberLinkCalcHist);
			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
        	
		} catch (Exception e) {
			e.printStackTrace();
			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
		}
	
	    return new ModelAndView("redirect:miBonusKpvChanges.html");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
