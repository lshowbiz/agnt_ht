package com.joymain.jecs.bd.webapp.action;

import java.util.List;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendNoteManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSendNoteAllotController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendNoteAllotController.class);
    private JbdSendNoteManager jbdSendNoteManager = null;
    private SysBankManager sysBankManager;
    private BdOutwardBankManager bdOutwardBankManager;
    private SysUserManager sysUserManager;
    public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}

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
        

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        

        crm.addField("companyCode", companyCode);
        crm.addField("registerStatus","1");
        crm.addField("reissueStatus","1");

        
/*        *//** add by hdg 2016-12-23 *//*
        CustomField[] fields = crm.getFields();
		w:for(CustomField field : fields) {
			String name=field.getName();
			if(!StringUtils.isEmpty(name)) {
				if("endcreatetime".equals(name)) {
					String value = (String)field.getValue();
					if(!StringUtils.isEmpty(value)) {
						field.setValue(value+" 23:59:59");
					}
					break w;
				}
			}
		}
		 *//** add by hdg 2016-12-23 */
        
        if ("post".equalsIgnoreCase(request.getMethod()) &&"allotSelectedNote".equals(request.getParameter("strAction"))) {
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
            			jbdSendNoteManager.allotJbdSendNote(adviceCodes, allotBdOutwardBank.getBankCode(), resSysUser);
            		}
    				this.saveMessage(request, LocaleUtil.getLocalText("BdSendRecordallot.allotSuccess"));
    			} catch (AppException e) {
    				this.saveMessage(request, LocaleUtil.getLocalText(e.getErrMsg()));
    			}
    			
    		}
        }
        if ("post".equalsIgnoreCase(request.getMethod()) &&"unAllotSelectedNote".equals(request.getParameter("strAction"))) {
        	
        	String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
        	try {
        		if(adviceCodes.length>1){
        			jbdSendNoteManager.unallotJbdSendNote(adviceCodes);
        		}
				this.saveMessage(request, LocaleUtil.getLocalText("bdSendRecordallot.unAllotSuccess"));
				
			} catch (AppException e) {
				this.saveMessage(request, LocaleUtil.getLocalText(e.getErrMsg()));
			}
        }
        
        
        
        
        
        
        
        

        //系统银行
        List sysBanks=sysBankManager.getSysBankByCompanyCode(companyCode);
        request.setAttribute("sysBanks", sysBanks);
        
        
//      找已存银行
        BdOutwardBank searchBdOutwardBank=new BdOutwardBank();
		if(null!=companyCode){
			searchBdOutwardBank.setCompanyCode(companyCode);
		}
		List bdOutwardBanks= bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);
		request.setAttribute("bdOutwardBanks", bdOutwardBanks);
		
		
        String startCreateTime=request.getParameter("startCreateTime");
        String endCreateTime=request.getParameter("endCreateTime");
        String userCode=request.getParameter("userCode");

        Pager pager = new Pager("jbdSendNoteListTable",request, 0);
        
        List jbdSendNotes =null;
        if(!StringUtil.isEmpty(startCreateTime)||!StringUtil.isEmpty(endCreateTime)||!StringUtil.isEmpty(userCode)){
        	
            jbdSendNotes = jbdSendNoteManager.getJbdSendNotesByCrm(crm,pager);
            request.setAttribute("sumMoney", jbdSendNoteManager.getSumRemittanceMoney(crm));
        }
        
        
        
        
        
        request.setAttribute("jbdSendNoteListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdSendNoteAllotList", Constants.JBDSENDNOTE_LIST, jbdSendNotes);
    }
}
