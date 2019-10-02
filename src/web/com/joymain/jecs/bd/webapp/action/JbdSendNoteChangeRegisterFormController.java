package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendNoteManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class JbdSendNoteChangeRegisterFormController extends BaseFormController {
    private BdOutwardBankManager bdOutwardBankManager;
    private JbdSendNoteManager jbdSendNoteManager = null;
    public void setJbdSendNoteManager(JbdSendNoteManager jbdSendNoteManager) {
        this.jbdSendNoteManager = jbdSendNoteManager;
    }

    public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}

	public JbdSendNoteChangeRegisterFormController() {
        setCommandName("jbdSendNote");
        setCommandClass(JbdSendNote.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSendNote jbdSendNote = null;
        
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = null;
    		}
    	}
        BdOutwardBank searchBdOutwardBank=new BdOutwardBank();
		if(null!=companyCode){
		searchBdOutwardBank.setCompanyCode(companyCode);
		}
		List bdOutwardBanks= bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);
		request.setAttribute("bdOutwardBanks", bdOutwardBanks);
        if (!StringUtils.isEmpty(id)) {
        	jbdSendNote = jbdSendNoteManager.getJbdSendNote(id);
        } else {
        	jbdSendNote = new JbdSendNote();
        	jbdSendNote.setJmiMember(new JmiMember());
        }

        return jbdSendNote;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        
        JbdSendNote jbdSendNote = (JbdSendNote) command;
        
        BdOutwardBank resBdOutwardBank =bdOutwardBankManager.getBdOutwardBank(request.getParameter("remittanceBNum"));
        jbdSendNote.setRemittanceBNum(resBdOutwardBank.getBankCode());
    	 try {
    		 jbdSendNoteManager.saveJbdSendNote(jbdSendNote);
             saveMessage(request,LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
 		} catch (Exception e) {
 			saveMessage(request,LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
 		}

        return new ModelAndView("redirect:jbdSendNoteChangeRegister.html?strAction=jbdSendNoteChangeRegister&needReload=1");
    }
}
;