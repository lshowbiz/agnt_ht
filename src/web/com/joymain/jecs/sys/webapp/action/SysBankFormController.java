package com.joymain.jecs.sys.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.sys.model.SysBank;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;

public class SysBankFormController extends BaseFormController {
    private SysBankManager sysBankManager = null;

    public void setSysBankManager(SysBankManager sysBankManager) {
        this.sysBankManager = sysBankManager;
    }
    public SysBankFormController() {
        setCommandName("sysBank");
        setCommandClass(SysBank.class);
    }
	@Override
	protected ModelAndView showForm(HttpServletRequest arg0, HttpServletResponse arg1, BindException arg2) throws Exception {
		if("addSysBank".equals(arg0.getParameter("strAction"))){
			String companyCode = arg0.getParameter("companyCode");
	        if(StringUtil.isEmpty(companyCode)){
	        	arg1.sendRedirect("sysBanks.html");
	        	this.saveMessage(arg0, getText("please.select.company"));
	        	return null;
	        }
		}
        
		return super.showForm(arg0, arg1, arg2);
	}
	protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String bankId = request.getParameter("bankId");
        SysBank sysBank = null;
        String companyCode = request.getParameter("companyCode");
        if (!StringUtils.isEmpty(bankId)) {
            sysBank = sysBankManager.getSysBank(bankId);
        } else {
            sysBank = new SysBank();
            sysBank.setCompanyCode(companyCode);
        }

        return sysBank;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysBank sysBank = (SysBank) command;

        if ("deleteSysBank".equals(request.getParameter("strAction"))  ) {
           
            try {
            	sysBankManager.removeSysBank(sysBank.getBankId().toString());
        		saveMessage(request, getText("sys.message.updateSuccess"));
			} catch (Exception e) {
				saveMessage(request, getText("sys.message.updateFail"));
			}
           // saveMessage(request, getText("sysBank.deleted", locale));
         //  saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),"sysBank.deleted"));
        } else {
        	if(null==sysBank.getBankId()){
        		SysBank sysBankSearch=new SysBank();
        		sysBankSearch.setBankKey(sysBank.getBankKey());
        		sysBankSearch.setBankValue(sysBank.getBankValue());
        		List resSysBankList=sysBankManager.getSysBanks(sysBankSearch);
        		if(resSysBankList.size()>0){
        			saveMessage(request, getText("operation.notice.js.sysbank.exist"));
        			return showForm(request, response, errors);
        		}
        	}
        	try {
        		
        		sysBankManager.saveSysBank(sysBank);
        		saveMessage(request, getText("sys.message.updateSuccess"));
			} catch (Exception e) {
				saveMessage(request, getText("sys.message.updateFail"));
			}
            

           // String key = (isNew) ? "sysBank.added" : "sysBank.updated";
           // saveMessage(request, getText(key, locale));
					//	saveMessage(request, getText(SessionLogin.getLoginUser(request).getCharacterCoding(),key));
        }

        return new ModelAndView("redirect:sysBanks.html?companyCode="+sysBank.getCompanyCode());
    }
}
