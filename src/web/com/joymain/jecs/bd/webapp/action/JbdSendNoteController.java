package com.joymain.jecs.bd.webapp.action;

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
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdOutwardBank;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.bd.service.BdOutwardBankManager;
import com.joymain.jecs.bd.service.JbdSendNoteManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JbdSendNoteController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JbdSendNoteController.class);
    private JbdSendNoteManager jbdSendNoteManager = null;

    public void setJbdSendNoteManager(JbdSendNoteManager jbdSendNoteManager) {
        this.jbdSendNoteManager = jbdSendNoteManager;
    }
    private BdOutwardBankManager bdOutwardBankManager;

    public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
		this.bdOutwardBankManager = bdOutwardBankManager;
	}
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        
        SysUser defSysUser = SessionLogin.getLoginUser(request);

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        
        if("M".equals(defSysUser.getUserType())){
        	crm.addField("userCode", defSysUser.getUserCode());
        }else{
        	String companyCode=defSysUser.getCompanyCode();
        	if("AA".equals(companyCode)){
        		companyCode="";
        	}
        	crm.addField("companyCode",companyCode);
        	
        	

    		String registerStatus=request.getParameter("registerStatus");
        	
        	
        	
            if("1".equals(registerStatus)){//待登记
            	crm.addField("reissueStatus", "1");
            	crm.addField("reissueStatus", "1");
            }
            else if("2".equals(registerStatus)){//登记成功
            	crm.addField("registerStatus", "2");
            	crm.addField("reissueStatus", "1");
            }
            else if("3".equals(registerStatus)){//待补发
            	crm.addField("registerStatus", "");
            	crm.addField("reissueStatus", "2");
            }
            else if("5".equals(registerStatus)){//转补发
            	crm.addField("registerStatus", "");
            	crm.addField("toReissue", "toReissue");
            }else if("6".equals(registerStatus)){//补发成功
            	crm.addField("registerStatus", "");
            	crm.addField("reissueStatus", "3");
            }
        	
            BdOutwardBank searchBdOutwardBank=new BdOutwardBank();
    		if(!"AA".equals(companyCode)){
    			searchBdOutwardBank.setCompanyCode(companyCode);
    		}
    		List bdOutwardBanks= bdOutwardBankManager.getBdOutwardBanks(searchBdOutwardBank);

    		request.setAttribute("bdOutwardBanks", bdOutwardBanks);

    		BigDecimal totalRemittanceMoney =jbdSendNoteManager.getSumRemittanceMoney(crm);
    		request.setAttribute("totalRemittanceMoney", totalRemittanceMoney);
        }
        
        
        
        
        
        
        
        Pager pager = new Pager("jbdSendNoteListTable",request, 20);
        List jbdSendNotes = jbdSendNoteManager.getJbdSendNotesByCrm(crm,pager);
        request.setAttribute("jbdSendNoteListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("bd/jbdSendNoteList", Constants.JBDSENDNOTE_LIST, jbdSendNotes);
    }
}
