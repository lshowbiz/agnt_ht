package com.joymain.jecs.bd.webapp.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdSendRecordHist;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class BdSendSupplyFormController extends BaseFormController {
    private JbdSendRecordHistManager jbdSendRecordHistManager = null;
    private BdOutwardBankManager bdOutwardBankManager;

    public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}

	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}

	public BdSendSupplyFormController() {
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
		List bdOutwardBanks=bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);
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
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        
        JbdSendRecordHist jbdSendRecordHist = (JbdSendRecordHist) command;
        
    	if(null!=jbdSendRecordHist.getSendDate()){
			MessageUtil.saveLocaleMessage(request, "bd.send.sended");
			return showForm(request, response, errors);
    	}

    	String registerDate = request.getParameter("registerDate");
    	Date date = null;
    	date = DateUtil.convertStringToDate(registerDate);
		if (date == null) {
			MessageUtil.saveLocaleMessage(request, "common.error.dateFormatError");
			return showForm(request, response, errors);
		}
        BdOutwardBank bdOutwardBank=bdOutwardBankManager.getBdOutwardBank(request.getParameter("remittanceBNum"));

    	jbdSendRecordHist.setRemittanceBNum(bdOutwardBank.getBankCode());
    	jbdSendRecordHist.setOperaterSysUser(defSysUser);
    	jbdSendRecordHist.setSendDate(date);
    	jbdSendRecordHist.setReissueStatus("3");
    	jbdSendRecordHist.setRegisterStatus("2");
    	jbdSendRecordHist.setOperaterTime(new Date());
    	jbdSendRecordHist.setSupplyTime(date);
    	
//      [補發成功] 2007-08-10 15:34:44 - panlili [匯出銀行]  
//    	[匯款銀行] 中国工商银行 [開戶城市]  [戶名] 何慧娟
//    	[帳號] 9558804000165141959 [匯款金額] 82.7442 
//    	[作業者帳號] panlili [作業時間] 2007-08-10 15:34:44 <br>
    	
    	
//    	[登記失敗] 2007-06-19 15:41:19 - zhuruijiang 
//    	[匯出銀行] ghcy [匯款銀行] 中国工商银行 [開戶城市] 
//    	[戶名] 何慧娟 [帳號] 9558804000165141959 [匯款金額] 82.7442 
//    	[作業者帳號] zhuruijiang [作業時間]  <br>
//   	[取消登記] 2007-06-19 15:29:39 - hulanjun 
//    	[匯出銀行]  [匯款銀行]  [開戶城市]  [戶名]  [帳號]  [匯款金額]  [作業者帳號]  
//    	[作業時間]  <br>
//    	[登記成功] 2007-06-16 21:11:16 - zhuruijiang [匯出銀行] ghcy 
//    	[匯款銀行] 中国工商银行 [開戶城市]  [戶名] 何慧娟 [帳號] 9558804000165141959 
//    	[匯款金額] 82.7442 [作業者帳號] zhuruijiang [作業時間] 2007-06-16 21:11:16 <br>
    	
    	String sendTrace="";
    	sendTrace+=" ["+LocaleUtil.getLocalText("bdSendSupply.supplySuccess")+"] "+DateUtil.getToday("yyyy-MM-dd HH:mm:ss")+" - "+defSysUser.getUserCode();
    	sendTrace+=" ["+LocaleUtil.getLocalText("bdSendRecordAllotReport.remittanceBankCH")+"] "+bdOutwardBank.getBankCode();
    	sendTrace+=" ["+LocaleUtil.getLocalText("bdSendRecord.openBank")+"] "+jbdSendRecordHist.getJmiMember().getBank();
    	sendTrace+=" ["+LocaleUtil.getLocalText("bdSendRemittanceReport.openCityCH")+"] "+jbdSendRecordHist.getJmiMember().getBankaddress();
    	sendTrace+=" ["+LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNameCH")+"] "+jbdSendRecordHist.getJmiMember().getBankbook();
    	sendTrace+=" ["+LocaleUtil.getLocalText("bdSendRecord.bnum")+"] "+jbdSendRecordHist.getJmiMember().getBankcard();
    	sendTrace+=" ["+LocaleUtil.getLocalText("bdSendRecord.remittanceMoney")+"] "+jbdSendRecordHist.getRemittanceMoney();
    	sendTrace+=" ["+LocaleUtil.getLocalText("bdSendRecord.sysUser.userName")+"] "+defSysUser.getUserCode();
    	sendTrace+=" ["+LocaleUtil.getLocalText("bdSendRecord.operaterTime")+"] "+DateUtil.getToday("yyyy-MM-dd HH:mm:ss");
    	sendTrace+=" <br> ";
    	
    	String resSendTrace=jbdSendRecordHist.getSendTrace();
    	resSendTrace+=sendTrace;
    	jbdSendRecordHist.setSendTrace(resSendTrace);
    	
    	jbdSendRecordHist.setBankbook(jbdSendRecordHist.getJmiMember().getBankaddress());
    	jbdSendRecordHist.setBankcard(jbdSendRecordHist.getJmiMember().getBankcard());
    	jbdSendRecordHist.setBank(jbdSendRecordHist.getJmiMember().getBank());
    	jbdSendRecordHist.setBankbook(jbdSendRecordHist.getJmiMember().getBankbook());
    	jbdSendRecordHist.setName(jbdSendRecordHist.getJmiMember().getSysUser().getUserName());
    	jbdSendRecordHist.setPetName(jbdSendRecordHist.getJmiMember().getPetName());

    	 try { 		 
    		 if(null!=jbdSendRecordHist){
    			 jbdSendRecordHistManager.supplyJbdSendRecordHist(jbdSendRecordHist);
    			 saveMessage(request,LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
				}		 
            
 		} catch (Exception e) {
 			saveMessage(request,LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
 		}
 		return new ModelAndView("redirect:/bdSendSupply.html?strAction=bdSendSupply&reissueStatus=2&needReload=1");
    }
}
