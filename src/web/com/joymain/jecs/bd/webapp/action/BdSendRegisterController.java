package com.joymain.jecs.bd.webapp.action;



import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;



public class BdSendRegisterController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(BdSendRegisterController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager;

    private JbdSendRecordNoteManager jbdSendRecordNoteManager;
    
    private SysBankManager sysBankManager;
    public void setSysBankManager(SysBankManager sysBankManager) {
		this.sysBankManager = sysBankManager;
	}
    public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	companyCode=defSysUser.getCompanyCode();  
    		if("AA".equals(defSysUser.getCompanyCode())){
    			companyCode = null;
    		}
    	}
        //补发
        if("post".equalsIgnoreCase(request.getMethod()) && "reissueSubmit".equals(request.getParameter("strAction"))){
        	String[] strReissueCodes = request.getParameter("strRegisterSuccessCodes").split(",");
        	String sendLateCause=request.getParameter("sendLateCauseForSelect");
        	String sendLateRemark=request.getParameter("sendLateRemark");
		
			try {
				jbdSendRecordHistManager.saveBdSendSupply(strReissueCodes, sendLateCause, sendLateRemark);
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.reissueSuccess"));
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.reissueFail"));
			}
        }
        //登记成功
        if ("post".equalsIgnoreCase(request.getMethod()) && "registerSuccess".equals(request.getParameter("strAction"))) {
        	String[] strRegisterSuccessCodes = request.getParameter("strRegisterSuccessCodes").split(",");
        	String registerDate = request.getParameter("registerDate");
        	Date date = null;
        	date = DateUtil.convertStringToDate(registerDate);
			if (date == null) {
				MessageUtil.saveLocaleMessage(request, "common.error.dateFormatError");
				return new ModelAndView("redirect:" + request.getHeader("Referer"));
			}
			String sendRemark=request.getParameter("sendRemark");
			
			String bdSendRegisterSuccess=LocaleUtil.getLocalText("bdSendRegister.success");
			String bdSendRecordAllotReportRemittanceBankCH=LocaleUtil.getLocalText("bdSendRecordAllotReport.remittanceBankCH");
			String bdSendRecordOpenBank=LocaleUtil.getLocalText("bdSendRecord.openBank");
			String bdSendRemittanceReportOpenCityCH=LocaleUtil.getLocalText("bdSendRemittanceReport.openCityCH");
			String bdSendRecordToBankReportBankNameCH=LocaleUtil.getLocalText("bdSendRecordToBankReport.bankNameCH");
			String bdSendRecordBnum=LocaleUtil.getLocalText("bdSendRecord.bnum");
			String bdSendRecordRemittanceMoney=LocaleUtil.getLocalText("bdSendRecord.remittanceMoney");
			String bdSendRecordSysUserUserName=LocaleUtil.getLocalText("bdSendRecord.sysUser.userName");
			String bdSendRecordOperaterTime=LocaleUtil.getLocalText("bdSendRecord.operaterTime");
			
			String [] remarkTitle=new String[9];
			remarkTitle[0]=bdSendRegisterSuccess;
			remarkTitle[1]=bdSendRecordAllotReportRemittanceBankCH;
			remarkTitle[2]=bdSendRecordOpenBank;
			remarkTitle[3]=bdSendRemittanceReportOpenCityCH;
			remarkTitle[4]=bdSendRecordToBankReportBankNameCH;
			remarkTitle[5]=bdSendRecordBnum;
			remarkTitle[6]=bdSendRecordRemittanceMoney;
			remarkTitle[7]=bdSendRecordSysUserUserName;
			remarkTitle[8]=bdSendRecordOperaterTime;
			
			try {
				
				jbdSendRecordHistManager.saveBdSendRegister(date, strRegisterSuccessCodes, sendRemark, defSysUser,remarkTitle);
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.success"));
			} catch (Exception e) {
				e.printStackTrace();
				if("bd.send.sended".equals(e.getMessage())){
					saveMessage(request, LocaleUtil.getLocalText("bd.send.sended"));
				}else{
					saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.fail"));
				}
			}
        }

        //补发
        if("post".equalsIgnoreCase(request.getMethod()) && "registerFail".equals(request.getParameter("strAction"))){
        	String[] strReissueCodes = request.getParameter("strRegisterSuccessCodes").split(",");

			try {
				jbdSendRecordHistManager.failJbdSendRecordHist(strReissueCodes);
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.reissueSuccess"));
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.reissueFail"));
			}
        }

        //系统银行
        List sysBanks=sysBankManager.getSysBankByCompanyCode(companyCode);
        request.setAttribute("sysBanks", sysBanks);
        
    	String wweek=request.getParameter("wweek");
    	
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
    	WeekFormatUtil.setSearchFweek(crm);
        crm.addField("companyCode", companyCode);
        if(!"AA".equals(defSysUser.getCompanyCode())){
        	 crm.addField("operaterCode", defSysUser.getUserCode());
        }
        
        Pager pager = new Pager("bdSendRegisterListTable",request, 0);
        crm.addField("allot", "1");
//        crm.addField("registerStatus","1");
        crm.addField("reissueStatus","1");
        crm.addField("exitDateNull","exitDateNull");
        crm.addField("active","0");
        crm.addField("active","0");
        crm.addField("notEqualCardType","0");
        crm.addField("remittanceMoneyGreater","0");
        
        

    	if(null==wweek){
    		request.setAttribute("bdSendRegisterListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/bdSendRegisterList");
    	}else{
//    		计数总金额
    		BigDecimal totalRemittanceMoney =jbdSendRecordNoteManager.getSumRemittanceMoney(crm);
    		request.setAttribute("totalRemittanceMoney", totalRemittanceMoney);
    		List bdSendRegisters=jbdSendRecordNoteManager.getJbdSendRecordHistsByCrm(crm, pager);
    		request.setAttribute("bdSendRegisterListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/bdSendRegisterList", "bdSendRegisterList", bdSendRegisters);
    	}
    		
    	

    
    }
	public void setJbdSendRecordNoteManager(
			JbdSendRecordNoteManager jbdSendRecordNoteManager) {
		this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
	}
}
