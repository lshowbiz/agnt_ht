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

import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;



public class BdSendSupplyController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(BdSendSupplyController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager = null;
    private JbdSendRecordNoteManager jbdSendRecordNoteManager = null;
	public void setJbdSendRecordNoteManager(
			JbdSendRecordNoteManager jbdSendRecordNoteManager) {
		this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
	}
    private BdOutwardBankManager bdOutwardBankManager;
	public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
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


    	String reissueStatus=request.getParameter("reissueStatus");
    	String wweek=request.getParameter("wweek");
    	String userCode=request.getParameter("userCode");
    	String name=request.getParameter("name");
    	String bank=request.getParameter("bank");
    	String bankbook=request.getParameter("bankbook");
    	String bankcard=request.getParameter("bankcard");
    	String remittanceBNum=request.getParameter("remittanceBNum");
        String startOperaterTime=request.getParameter("startOperaterTime");
        String endOperaterTime=request.getParameter("endOperaterTime");
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", companyCode);

       
        Pager pager = new Pager("bdSendSupplyListTable",request, 20);
        
        
        
        BdOutwardBank searchBdOutwardBank=new BdOutwardBank();
		if(null!=companyCode){
			searchBdOutwardBank.setCompanyCode(companyCode);
		}
		List bdOutwardBanks=bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);
		request.setAttribute("bdOutwardBanks", bdOutwardBanks);
		
       
        if ("post".equalsIgnoreCase(request.getMethod()) && "supplySubmit".equals(request.getParameter("strAction"))) {
        	String[] strRegisterSuccessCodes = request.getParameter("strRegisterSuccessCodes").split(",");
        	String registerDate = request.getParameter("registerDate");
        	Date date = null;
        	date = DateUtil.convertStringToDate(registerDate);
			if (date == null) {
				MessageUtil.saveLocaleMessage(request, "common.error.dateFormatError");
				return new ModelAndView("redirect:bdSendSupply.html?reissueStatus=2&strAction=bdSendSupply");
			}
			String sendRemark=request.getParameter("sendRemark");
			String remittanceBNumSelect=request.getParameter("remittanceBNumSelect");
			if(StringUtil.isEmpty(remittanceBNumSelect)){
				MessageUtil.saveLocaleMessage(request, "operation.notice.js.select.code");
				return new ModelAndView("redirect:bdSendSupply.html?reissueStatus=2&strAction=bdSendSupply");
			}
			
			BdOutwardBank curBdOutwardBank=bdOutwardBankManager.getBdOutwardBank(remittanceBNumSelect);
			try {
				jbdSendRecordHistManager.supplyJbdSendRecordHists(date, strRegisterSuccessCodes, sendRemark, defSysUser, curBdOutwardBank);
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
        

        WeekFormatUtil.setSearchFweek(crm);

//   	if(!StringUtil.isEmpty(reissueStatus)&&!StringUtil.isInteger(wweek)&&StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(name)){
        if(userCode==null){
    		request.setAttribute("bdSendSupplyListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/bdSendSupplyList");
    	}else{
//    		计数总金额
    		BigDecimal totalRemittanceMoney =jbdSendRecordNoteManager.getSumRemittanceMoney(crm);
    		request.setAttribute("totalRemittanceMoney", totalRemittanceMoney);
    		List bdSendSupplys=jbdSendRecordNoteManager.getJbdSendRecordHistsByCrm(crm, pager);
    		request.setAttribute("bdSendSupplyListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/bdSendSupplyList", "bdSendSupplyList", bdSendSupplys);
    	}
    		
    	

    
    }


}
