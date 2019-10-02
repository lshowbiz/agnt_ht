package com.joymain.jecs.bd.webapp.action;



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
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class BdSendChangeRegisterController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(BdSendChangeRegisterController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager;
    private JbdSendRecordNoteManager jbdSendRecordNoteManager;

    private BdOutwardBankManager bdOutwardBankManager = null;
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
        
        //取消登记
        if ("get".equalsIgnoreCase(request.getMethod()) && "cancalRegister".equals(request.getParameter("strAction"))) {
        	String[] strAdvicesCodes = request.getParameter("strAdvicesCodes").split(",");
        	
			
        	
			try {		
				jbdSendRecordHistManager.cancalJbdSendRecordHist(strAdvicesCodes, defSysUser);
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
			} catch (Exception e) {
				e.printStackTrace();
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
			}
        }

        
    	String startOperaterTime=request.getParameter("startOperaterTime");
    	String endOperaterTime=request.getParameter("endOperaterTime");
    	String wweek=request.getParameter("wweek");
    	String userCode=request.getParameter("userCode");
    	String name=request.getParameter("name");
    	String remittanceBNum=request.getParameter("remittanceBNum");
    	String userName=request.getParameter("userName");


    	CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", companyCode);
        
        Pager pager = new Pager("bdSendChangeRegisterListTable",request, 20);

        WeekFormatUtil.setSearchFweek(crm);
        
        BdOutwardBank searchBdOutwardBank=new BdOutwardBank();
		if(null!=companyCode){
		searchBdOutwardBank.setCompanyCode(companyCode);
		}
		List bdOutwardBanks= bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);
		request.setAttribute("bdOutwardBanks", bdOutwardBanks);
        

    	if(StringUtil.isEmpty(userName)&&!StringUtil.isInteger(wweek)&&StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(name)&&!StringUtil.isDate(startOperaterTime)&&!StringUtil.isDate(endOperaterTime)){
    		request.setAttribute("bdSendChangeRegisterListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/bdSendChangeRegisterList");
    	}else{
    		List bdSendChangeRegisters=jbdSendRecordNoteManager.getJbdSendRecordHistsByCrm(crm, pager);
    		request.setAttribute("bdSendChangeRegisterListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/bdSendChangeRegisterList", "bdSendChangeRegisterList", bdSendChangeRegisters);
    	}
    		
    	

    
    }
	public void setJbdSendRecordNoteManager(
			JbdSendRecordNoteManager jbdSendRecordNoteManager) {
		this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
	}
}
