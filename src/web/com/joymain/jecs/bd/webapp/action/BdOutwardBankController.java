package com.joymain.jecs.bd.webapp.action;

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
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class BdOutwardBankController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(BdOutwardBankController.class);
    private BdOutwardBankManager bdOutwardBankManager = null;

    public void setBdOutwardBankManager(BdOutwardBankManager bdOutwardBankManager) {
        this.bdOutwardBankManager = bdOutwardBankManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        if("true".equals(request.getParameter("view"))){
        	 String bankId=request.getParameter("bankId");
        	 if(!StringUtil.isEmail(bankId)){
        		 BdOutwardBank  bdOutwardBank= bdOutwardBankManager.getBdOutwardBank(bankId);
        		 request.setAttribute("bdOutwardBank", bdOutwardBank);
        		 return new ModelAndView("bd/bdOutwardBank");
        	 }
        }
        if ("post".equalsIgnoreCase(request.getMethod()) && "deleteSelected".equals(request.getParameter("strAction"))) {
        	try {
        	String[] adviceCodes = request.getParameter("strAdviceCodes").split(",");
			for (int i = 0; i < adviceCodes.length; i++) {
				if (!StringUtils.isEmpty(adviceCodes[i])) {
					bdOutwardBankManager.removeBdOutwardBank(adviceCodes[i]);
				}
			}
			
				saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteSuccess"));
			} catch (Exception e) {
				saveMessage(request, LocaleUtil.getLocalText("bdOutWardBank.deleteFail"));
			}
			
			return new ModelAndView("redirect:/bdOutwardBanks.html?strAction=bdOutwardBanks");
        }
        String companyCode="";
        SysUser defSysUser = SessionLogin.getLoginUser(request);
        if("C".equals(defSysUser.getUserType())){
        	 companyCode=defSysUser.getCompanyCode();
        	 if("AA".equals(defSysUser.getCompanyCode())){
     			companyCode = null;
     		}
        }
        List bdOutwardBanks =null;
        Pager pager = new Pager("bdOutwardBankListTable",request, 20);


			CommonRecord crm=RequestUtil.toCommonRecord(request);
	        crm.addField("companyCode", companyCode);
	        bdOutwardBanks = bdOutwardBankManager.getBdOutwardBanksByCrm(crm,pager);
	        request.setAttribute("bdOutwardBankListTable_totalRows", pager.getTotalObjects());
		


        return new ModelAndView("bd/bdOutwardBankList", Constants.BDOUTWARDBANK_LIST, bdOutwardBanks);
    }
}
