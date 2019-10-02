package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiCcoinBalance;
import com.joymain.jecs.fi.service.FiCcoinBalanceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiCcoinBalanceFormController extends BaseFormController {
    private FiCcoinBalanceManager fiCcoinBalanceManager = null;

    public void setFiCcoinBalanceManager(FiCcoinBalanceManager fiCcoinBalanceManager) {
        this.fiCcoinBalanceManager = fiCcoinBalanceManager;
    }
    public FiCcoinBalanceFormController() {
        setCommandName("fiCcoinBalance");
        setCommandClass(FiCcoinBalance.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        FiCcoinBalance fiCcoinBalance = null;

        if (!StringUtils.isEmpty(userCode)) {
            fiCcoinBalance = fiCcoinBalanceManager.getFiCcoinBalance(userCode);
        } else {
            fiCcoinBalance = new FiCcoinBalance();
        }

        return fiCcoinBalance;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiCcoinBalance fiCcoinBalance = (FiCcoinBalance) command;
        boolean isNew = (fiCcoinBalance.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiCcoinBalance".equals(strAction)  ) {
		fiCcoinBalanceManager.removeFiCcoinBalance(fiCcoinBalance.getUserCode().toString());
		key="fiCcoinBalance.delete";
	}else{
		fiCcoinBalanceManager.saveFiCcoinBalance(fiCcoinBalance);
		key="fiCcoinBalance.update";
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
