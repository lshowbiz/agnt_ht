package com.joymain.jecs.bd.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendRecordHistManager;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JbdSendRecordFreezeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendRecordFreezeController.class);
    private JbdSendRecordHistManager jbdSendRecordHistManager;
    private BdOutwardBankManager bdOutwardBankManager;
    private SysUserManager sysUserManager;
    private SysBankManager sysBankManager;
    private JbdSendRecordNoteManager jbdSendRecordNoteManager = null;
	public void setJbdSendRecordNoteManager(
			JbdSendRecordNoteManager jbdSendRecordNoteManager) {
		this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
	}
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

        WeekFormatUtil.setSearchFweek(crm);
        
        crm.addField("companyCode", companyCode);
       // crm.addField("freezeStatus", "1");
        crm.addField("registerStatus","1");
        crm.addField("reissueStatus","1");
        crm.addField("remittanceMoneyGreater","0");
        crm.addField("notEqualCardType","0");
        
        String strAction=request.getParameter("strAction");
        if("post".equalsIgnoreCase(request.getMethod()) && ("freezeSubmit0".equals(strAction)||"freezeSubmit1".equals(strAction))){
        	String[] strAdviceCodes = request.getParameter("strAdviceCodes").split(",");
		
			try {
				if("freezeSubmit0".equals(strAction)){
					jbdSendRecordHistManager.freezeJbdSendRecord(strAdviceCodes,0);
				}else if("freezeSubmit1".equals(strAction)){
					jbdSendRecordHistManager.freezeJbdSendRecord(strAdviceCodes,1);
					
				}
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterSuccess"));
			} catch (AppException e) {
				saveMessage(request, e.getErrMsg());
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdSendRegister.operaterFail"));
			}
        }
        
        
        String userCode=request.getParameter("userCode");
        String wweek=request.getParameter("wweek");
        String freezeStatus=request.getParameter("freezeStatus");
        List jbdSendRecords =null;
        crm.setValue("userCode", userCode);
        crm.setValue("wweek", wweek);
        crm.setValue("freezeStatus", freezeStatus);
        
        if(!StringUtil.isEmpty(userCode)||!StringUtil.isEmpty(wweek)||"1".equals(freezeStatus)){
            jbdSendRecords = jbdSendRecordNoteManager.getJbdSendRecordsBySqlByCrm(crm,pager);
        }
        request.setAttribute("jbdSendRecordListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdSendRecordFreeze", Constants.JBDSENDRECORD_LIST, jbdSendRecords);
    }

	public void setJbdSendRecordHistManager(
			JbdSendRecordHistManager jbdSendRecordHistManager) {
		this.jbdSendRecordHistManager = jbdSendRecordHistManager;
	}
}
