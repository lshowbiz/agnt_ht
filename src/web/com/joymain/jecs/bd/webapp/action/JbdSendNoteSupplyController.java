package com.joymain.jecs.bd.webapp.action;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendNoteManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSendNoteSupplyController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendNoteSupplyController.class);
    private JbdSendNoteManager jbdSendNoteManager = null;
    private BdOutwardBankManager bdOutwardBankManager;
	public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}
    public void setJbdSendNoteManager(JbdSendNoteManager jbdSendNoteManager) {
        this.jbdSendNoteManager = jbdSendNoteManager;
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


    	String userCode=request.getParameter("userCode");
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", companyCode);

        Pager pager = new Pager("jbdSendNoteListTable",request, 20);
       
        
        
        
        BdOutwardBank searchBdOutwardBank=new BdOutwardBank();
		if(null!=companyCode){
			searchBdOutwardBank.setCompanyCode(companyCode);
		}
		List bdOutwardBanks=bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);
		request.setAttribute("bdOutwardBanks", bdOutwardBanks);
		
       
        if ("post".equalsIgnoreCase(request.getMethod()) && "supplySubmitNote".equals(request.getParameter("strAction"))) {
        	String[] strRegisterSuccessCodes = request.getParameter("strRegisterSuccessCodes").split(",");
        	String registerDate = request.getParameter("registerDate");
        	Date date = null;
        	date = DateUtil.convertStringToDate(registerDate);
			if (date == null) {
				MessageUtil.saveLocaleMessage(request, "common.error.dateFormatError");
				return new ModelAndView("redirect:jbdSendNoteSupply.html?reissueStatus=2&strAction=jbdSendNoteSupply");
			}
			String sendRemark=request.getParameter("sendRemark");
			String remittanceBNumSelect=request.getParameter("remittanceBNumSelect");
			if(StringUtil.isEmpty(remittanceBNumSelect)){
				MessageUtil.saveLocaleMessage(request, "operation.notice.js.select.code");
				return new ModelAndView("redirect:jbdSendNoteSupply.html?reissueStatus=2&strAction=jbdSendNoteSupply");
			}
			
			BdOutwardBank curBdOutwardBank=bdOutwardBankManager.getBdOutwardBank(remittanceBNumSelect);
			try {
				jbdSendNoteManager.supplyJbdSendNote(date, strRegisterSuccessCodes, sendRemark, defSysUser, curBdOutwardBank);
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
        
        

        if(userCode==null){
            request.setAttribute("jbdSendNoteListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("bd/jbdSendNoteSupplyList");
    	}else{
//    		计数总金额
    		BigDecimal totalRemittanceMoney =jbdSendNoteManager.getSumRemittanceMoney(crm);
    		request.setAttribute("totalRemittanceMoney", totalRemittanceMoney);
            List jbdSendNotes = jbdSendNoteManager.getJbdSendNotesByCrm(crm,pager);
            request.setAttribute("jbdSendNoteListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("bd/jbdSendNoteSupplyList", Constants.JBDSENDNOTE_LIST, jbdSendNotes);
    	}
        
        
        


    }
}
