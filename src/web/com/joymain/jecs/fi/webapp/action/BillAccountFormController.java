package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.fi.model.BillAccount;
import com.joymain.jecs.fi.service.BillAccountManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class BillAccountFormController extends BaseFormController {
    private BillAccountManager billAccountManager = null;

    public void setBillAccountManager(BillAccountManager billAccountManager) {
        this.billAccountManager = billAccountManager;
    }
    public BillAccountFormController() {
        setCommandName("billAccount");
        setCommandClass(BillAccount.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String baId = request.getParameter("baId");
        BillAccount billAccount = null;

        if (!StringUtils.isEmpty(baId)) {
            billAccount = billAccountManager.getBillAccount(baId);
        } else {
            billAccount = new BillAccount();
        }

        return billAccount;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    
        
        
        BillAccount billAccount = (BillAccount) command;
        


        boolean isNew = (billAccount.getBaId() == null);

		String strAction = request.getParameter("strAction");
		if ("deleteBillAccount".equals(strAction)  ) {
			billAccountManager.removeBillAccount(billAccount.getBaId().toString());
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		}else{
			
			if (StringUtil.isEmpty(billAccount.getBaCode())) {
				errors.rejectValue("baCode", "isNotNull",new Object[] {this.getText("bdOutwardBank.bankCode") }, "");
				return showForm(request, response, errors);
	    	}
			if(isNew&&billAccountManager.getBillAccountsByBaCode(billAccount.getBaCode()).size()>0){
	        	errors.reject("JbdManuallyAdjustStar.exist");
				return showForm(request, response, errors);
			}
	        if (StringUtil.isEmpty(billAccount.getCreditName())) {
				errors.rejectValue("baCode", "isNotNull",new Object[] {this.getText("billAccount.creditName") }, "");
				return showForm(request, response, errors);
	    	}
	        if (StringUtil.isEmpty(billAccount.getCreditContact())) {
				errors.rejectValue("baCode", "isNotNull",new Object[] {this.getText("billAccount.creditContact") }, "");
				return showForm(request, response, errors);
	    	}
	        if (null==billAccount.getPersent()) {
				errors.rejectValue("baCode", "isNotNull",new Object[] {this.getText("billAccount.persent") }, "");
				return showForm(request, response, errors);
	    	}
	        if(billAccount.getPersent().compareTo(new BigDecimal(1))==1||billAccount.getPersent().compareTo(new BigDecimal(1))==0){//billAccount.persent.error
	        	errors.reject("billAccount.persent.error");
				return showForm(request, response, errors);
	        }
	        BigDecimal count=billAccountManager.getBilllAccountsPersent(billAccount.getBaId());
	        if((billAccount.getPersent().add(count)).compareTo(new BigDecimal(1))==1){
	        	errors.reject("billAccount.persent.1");
				return showForm(request, response, errors);
	        }
			billAccountManager.saveBillAccount(billAccount);
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		}
	
	        return new ModelAndView(getSuccessView());
	    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
