package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.fi.model.FiPayAccount;
import com.joymain.jecs.fi.model.FiPayAccountConfig;
import com.joymain.jecs.fi.service.FiPayAccountConfigManager;
import com.joymain.jecs.fi.service.FiPayAccountManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiPayAccountConfigFormController extends BaseFormController {
    private FiPayAccountConfigManager fiPayAccountConfigManager = null;
    private AlCompanyManager alCompanyManager;
	private AlCountryManager alCountryManager = null;
	private FiPayAccountManager fiPayAccountManager = null;

    public void setFiPayAccountManager(FiPayAccountManager fiPayAccountManager) {
        this.fiPayAccountManager = fiPayAccountManager;
    }
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

    public void setFiPayAccountConfigManager(FiPayAccountConfigManager fiPayAccountConfigManager) {
        this.fiPayAccountConfigManager = fiPayAccountConfigManager;
    }
    public FiPayAccountConfigFormController() {
        setCommandName("fiPayAccountConfig");
        setCommandClass(FiPayAccountConfig.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String province = request.getParameter("province");
        FiPayAccountConfig fiPayAccountConfig = null;

        if (!StringUtils.isEmpty(province)) {
            fiPayAccountConfig = fiPayAccountConfigManager.getFiPayAccountConfig(province);
        } else {
            fiPayAccountConfig = new FiPayAccountConfig();
        }
      

		//读取商户号
		FiPayAccount fiPayAccount = new FiPayAccount();
		List fiPayAccounts = fiPayAccountManager.getFiPayAccounts(fiPayAccount);
		request.setAttribute("fiPayAccounts", fiPayAccounts);
				
        return fiPayAccountConfig;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiPayAccountConfig fiPayAccountConfig = (FiPayAccountConfig) command;
        boolean isNew = (fiPayAccountConfig.getProvince() == null);
        Locale locale = request.getLocale();
        String key=null;
        String strAction = request.getParameter("strAction");
		if ("deleteFiPayAccountConfig".equals(strAction)  ) {
			fiPayAccountConfigManager.removeFiPayAccountConfig(fiPayAccountConfig.getProvince().toString());
			key="fiPayAccountConfig.delete";
		}else{
			
			String accountId = request.getParameter("accountId");
			FiPayAccount fiPayAccount = fiPayAccountManager.getFiPayAccount(accountId);
			
			fiPayAccountConfig.setFiPayAccount(fiPayAccount);
			
			fiPayAccountConfigManager.saveFiPayAccountConfig(fiPayAccountConfig);
			key="fiPayAccountConfig.update";
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
