package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.mi.model.JmiCustomerLevelNote;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiCustomerLevelNoteManager;
import com.joymain.jecs.mi.service.JmiMemberManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiCustomerLevelNoteFormController extends BaseFormController {
    private JmiCustomerLevelNoteManager jmiCustomerLevelNoteManager = null;
    private JmiMemberManager jmiMemberManager;
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJmiCustomerLevelNoteManager(JmiCustomerLevelNoteManager jmiCustomerLevelNoteManager) {
        this.jmiCustomerLevelNoteManager = jmiCustomerLevelNoteManager;
    }
    public JmiCustomerLevelNoteFormController() {
        setCommandName("jmiCustomerLevelNote");
        setCommandClass(JmiCustomerLevelNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JmiCustomerLevelNote jmiCustomerLevelNote = null;

//        if (!StringUtils.isEmpty(id)) {
//            jmiCustomerLevelNote = jmiCustomerLevelNoteManager.getJmiCustomerLevelNote(id);
//        } else {
//            jmiCustomerLevelNote = new JmiCustomerLevelNote();
//        }

        return new JmiCustomerLevelNote();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	
        JmiCustomerLevelNote jmiCustomerLevelNote = (JmiCustomerLevelNote) command;
        
        if(StringUtil.isEmpty(jmiCustomerLevelNote.getUserCode())){
    		saveMessage(request, LocaleUtil.getLocalText("operation.notice.js.orderNo.miMember.memberNoError"));
    		return showForm(request, response, errors);
        }
        JmiMember jmiMember=jmiMemberManager.getJmiMember(jmiCustomerLevelNote.getUserCode());
        if(jmiMember==null){
    		saveMessage(request, LocaleUtil.getLocalText("operation.notice.js.orderNo.miMember.memberNoError"));
    		return showForm(request, response, errors);
        }
        if(jmiMember.getCustomerLevel().equals(jmiCustomerLevelNote.getNewCustomerLevel())){
    		saveMessage(request, LocaleUtil.getLocalText("jmiMember.cardType.same"));
    		return showForm(request, response, errors);
        }
        
        try {
        	jmiMemberManager.sendPcnMananger(jmiMember, "ModifyLevel", jmiCustomerLevelNote.getNewCustomerLevel(), jmiCustomerLevelNote.getRemark(), defSysUser,"");
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		} catch (Exception e) {
			this.saveMessage(request, e.getMessage());
		}
        

        return new ModelAndView("redirect:jmiCustomerLevelNotes.html?strAction=jmiCustomerLevelNotes");
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
