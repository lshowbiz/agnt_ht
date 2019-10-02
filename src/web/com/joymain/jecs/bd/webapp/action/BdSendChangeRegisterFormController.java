package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.model.JbdSendRecordNote;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class BdSendChangeRegisterFormController extends BaseFormController {
    private JbdSendRecordHistManager jbdSendRecordHistManager;
    private BdOutwardBankManager bdOutwardBankManager;
    private JbdSendRecordNoteManager jbdSendRecordNoteManager;
    public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}

	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}

	public BdSendChangeRegisterFormController() {
        setCommandName("jbdSendRecordHist");
        setCommandClass(JbdSendRecordHist.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JbdSendRecordHist jbdSendRecordHist = null;
        
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
        	jbdSendRecordHist = jbdSendRecordHistManager.getJbdSendRecordHist(id);
        } else {
        	jbdSendRecordHist = new JbdSendRecordHist();
        	jbdSendRecordHist.setJmiMember(new JmiMember());
        }

        return jbdSendRecordHist;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        
        JbdSendRecordHist jbdSendRecordHist = (JbdSendRecordHist) command;
        
        BdOutwardBank resBdOutwardBank =bdOutwardBankManager.getBdOutwardBank(request.getParameter("remittanceBNum"));
        jbdSendRecordHist.setRemittanceBNum(resBdOutwardBank.getBankCode());
        
        JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNote(jbdSendRecordHist.getId().toString());
        jbdSendRecordNote.setRemittanceBNum(resBdOutwardBank.getBankCode());
        jbdSendRecordNote.setSendLateCause(jbdSendRecordHist.getSendLateCause());
        jbdSendRecordNote.setSendLateRemark(jbdSendRecordHist.getSendLateRemark());
    	 try {
    		 jbdSendRecordHistManager.saveJbdSendRecordHist(jbdSendRecordHist);
    		 jbdSendRecordNoteManager.saveJbdSendRecordNote(jbdSendRecordNote);
             saveMessage(request,LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
 		} catch (Exception e) {
 			saveMessage(request,LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
 		}

        return new ModelAndView("redirect:/bdSendChangeRegister.html?strAction=bdSendChangeRegister&needReload=1");
    }

	public void setJbdSendRecordNoteManager(
			JbdSendRecordNoteManager jbdSendRecordNoteManager) {
		this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
	}
}
;