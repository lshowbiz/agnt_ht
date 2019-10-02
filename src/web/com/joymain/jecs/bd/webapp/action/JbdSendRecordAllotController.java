package com.joymain.jecs.bd.webapp.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.bd.service.JbdSendRecordManager;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdSendRecordAllotController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendRecordAllotController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager;
    private JbdSendRecordNoteManager jbdSendRecordNoteManager;
    private BdOutwardBankManager bdOutwardBankManager;
    private SysUserManager sysUserManager;
    private SysBankManager sysBankManager;
    public void setSysBankManager(SysBankManager sysBankManager) {
		this.sysBankManager = sysBankManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
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
    			companyCode = request.getParameter("company");
    		}
    	}
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jbdSendRecordListTable",request, 0);

        crm.addField("companyCode", companyCode);
        crm.addField("registerStatus","1");
        crm.addField("reissueStatus","1");
        crm.addField("remittanceMoneyGreater","0");
        crm.addField("notEqualCardType","0");
        crm.addField("exitDateNull","exitDateNull");
        crm.addField("active","0");
        crm.addField("freezeStatus","0");
        
        String allot=request.getParameter("allot");
        request.setAttribute("allot", allot);
        request.setAttribute("startAllotMoney", 500);
        
        if ("post".equalsIgnoreCase(request.getMethod()) &&"allotSelected".equals(request.getParameter("strAction"))) {
        	String remittanceBNumAllot=request.getParameter("remittanceBNumAllot");
        	String operaterCodeInput=request.getParameter("operaterCodeInput");
        	SysUser resSysUser=null;
   
    		resSysUser=sysUserManager.getSysUser(operaterCodeInput);
    		if(resSysUser==null){
    			this.saveMessage(request, LocaleUtil.getLocalText("BdSendRecordallot.sysUserNotEixit"));
    			
    		}else{
            	BdOutwardBank allotBdOutwardBank=bdOutwardBankManager.getBdOutwardBank(remittanceBNumAllot);
            	String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
            	try {
            		if(adviceCodes.length>1){
                    	jbdSendRecordHistManager.allotBdSendRecord(adviceCodes, allotBdOutwardBank.getBankCode(), resSysUser);
            		}
    				this.saveMessage(request, LocaleUtil.getLocalText("BdSendRecordallot.allotSuccess"));
    			} catch (AppException e) {
    				e.printStackTrace();
    				this.saveMessage(request, LocaleUtil.getLocalText(e.getErrMsg()));
    			}
    			
    		}
        }
        if ("post".equalsIgnoreCase(request.getMethod()) &&"unAllotSelected".equals(request.getParameter("strAction"))) {
        	
        	String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
        	try {
        		if(adviceCodes.length>1){
        			jbdSendRecordHistManager.unallotBdSendRecord(adviceCodes);
        		}
				this.saveMessage(request, LocaleUtil.getLocalText("bdSendRecordallot.unAllotSuccess"));
				
			} catch (AppException e) {
				e.printStackTrace();
				this.saveMessage(request, LocaleUtil.getLocalText(e.getErrMsg()));
			}
        }
        //系统银行
        List sysBanks=sysBankManager.getSysBankByCompanyCode(companyCode);
        request.setAttribute("sysBanks", sysBanks);
        
		//找已存银行
        BdOutwardBank searchBdOutwardBank=new BdOutwardBank();
		if(null!=companyCode){
			searchBdOutwardBank.setCompanyCode(companyCode);
		}
		List bdOutwardBanks= bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);
		request.setAttribute("bdOutwardBanks", bdOutwardBanks);
        String userCode=request.getParameter("userCode");
        List jbdSendRecords =null;
        BigDecimal sumMoney=new BigDecimal(0);
        if(userCode!=null){
            jbdSendRecords = jbdSendRecordNoteManager.getJbdSendRecordsBySqlByCrm(crm,pager);
            sumMoney=jbdSendRecordNoteManager.getJbdSendRecordsBySqlByCrmSum(crm);
            
        }
        request.setAttribute("sumMoney", sumMoney);
        request.setAttribute("jbdSendRecordListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdSendRecordAllot", Constants.JBDSENDRECORD_LIST, jbdSendRecords);
    }

	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}

	public void setJbdSendRecordNoteManager(
			JbdSendRecordNoteManager jbdSendRecordNoteManager) {
		this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
	}
}
