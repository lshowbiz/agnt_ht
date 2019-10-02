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
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.service.JbdSendNoteManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSendNoteRegisterController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendNoteRegisterController.class);
    private JbdSendNoteManager jbdSendNoteManager = null;
    private SysBankManager sysBankManager;
    public void setSysBankManager(SysBankManager sysBankManager) {
		this.sysBankManager = sysBankManager;
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

        
        
        
        
       
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        String companyCode=defSysUser.getCompanyCode();  
        
        //补发
        if("post".equalsIgnoreCase(request.getMethod()) && "reissueSubmitNote".equals(request.getParameter("strAction"))){
        	String[] strReissueCodes = request.getParameter("strRegisterSuccessCodes").split(",");
        	String sendLateCause=request.getParameter("sendLateCauseForSelect");
        	String sendLateRemark=request.getParameter("sendLateRemark");
		
			try {
				jbdSendNoteManager.saveJbdSendNoteSupply(strReissueCodes, sendLateCause, sendLateRemark);
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.reissueSuccess"));
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.reissueFail"));
			}
        }
        //登记成功
        if ("post".equalsIgnoreCase(request.getMethod()) && "registerSuccessNote".equals(request.getParameter("strAction"))) {
        	String[] strRegisterSuccessCodes = request.getParameter("strRegisterSuccessCodes").split(",");
        	String registerDate = request.getParameter("registerDate");
        	Date date = null;
        	date = DateUtil.convertStringToDate(registerDate);
			if (date == null) {
				MessageUtil.saveLocaleMessage(request, "common.error.dateFormatError");
				return new ModelAndView("redirect:" + request.getHeader("Referer"));
			}
			String sendRemark=request.getParameter("sendRemark");
			

			try {
				jbdSendNoteManager.saveJbdSendNoteRegister(date, strRegisterSuccessCodes, sendRemark, defSysUser);
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
        if("post".equalsIgnoreCase(request.getMethod()) && "registerFailNote".equals(request.getParameter("strAction"))){
        	String[] strReissueCodes = request.getParameter("strRegisterSuccessCodes").split(",");

			try {
				jbdSendNoteManager.failJbdSendRecordHist(strReissueCodes);
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.reissueSuccess"));
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.reissueFail"));
			}
        }

        //系统银行
        List sysBanks=sysBankManager.getSysBankByCompanyCode(companyCode);
        request.setAttribute("sysBanks", sysBanks);
        
    	String userCode=request.getParameter("userCode");

    	CommonRecord crm=RequestUtil.toCommonRecord(request);
            Pager pager = new Pager("jbdSendNoteListTable",request, 0);
        crm.addField("companyCode", companyCode);
        if(!"AA".equals(defSysUser.getCompanyCode())){
        	 crm.addField("operaterCode", defSysUser.getUserCode());
        }
        

        
        
        
        crm.addField("allot", "1");
        crm.addField("registerStatus","1");
        crm.addField("reissueStatus","1");
        

    	String registerStatus=request.getParameter("registerStatus");
    	if("2".equals(registerStatus)){
            crm.addField("registerStatus","2");
            crm.addField("reissueStatus","1");
    	}
    	
    	

    	if(null==userCode){
            request.setAttribute("jbdSendNoteListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("bd/jbdSendNoteRegisterList");
    	}else{
//    		计数总金额
    		BigDecimal totalRemittanceMoney =jbdSendNoteManager.getSumRemittanceMoney(crm);
    		request.setAttribute("totalRemittanceMoney", totalRemittanceMoney);
            List jbdSendNotes = jbdSendNoteManager.getJbdSendNotesByCrm(crm,pager);
            request.setAttribute("jbdSendNoteListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("bd/jbdSendNoteRegisterList", Constants.JBDSENDNOTE_LIST, jbdSendNotes);
    	}
    		

    }
}
