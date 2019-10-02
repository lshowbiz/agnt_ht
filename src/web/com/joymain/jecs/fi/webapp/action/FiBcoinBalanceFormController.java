package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.service.FiBcoinBalanceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiBcoinBalanceFormController extends BaseFormController {
    private FiBcoinBalanceManager fiBcoinBalanceManager = null;

    public void setFiBcoinBalanceManager(FiBcoinBalanceManager fiBcoinBalanceManager) {
        this.fiBcoinBalanceManager = fiBcoinBalanceManager;
    }
    public FiBcoinBalanceFormController() {
        setCommandName("fiBcoinBalance");
        setCommandClass(FiBcoinBalance.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        FiBcoinBalance fiBcoinBalance = null;

        if (!StringUtils.isEmpty(userCode)) {
            fiBcoinBalance = fiBcoinBalanceManager.getFiBcoinBalance(userCode);
        } else {
            fiBcoinBalance = new FiBcoinBalance();
        }

        return fiBcoinBalance;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiBcoinBalance fiBcoinBalance = (FiBcoinBalance) command;
        boolean isNew = (fiBcoinBalance.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiBcoinBalance".equals(strAction)  ) {
		fiBcoinBalanceManager.removeFiBcoinBalance(fiBcoinBalance.getUserCode().toString());
		key="fiBcoinBalance.delete";
	}else{
		fiBcoinBalanceManager.saveFiBcoinBalance(fiBcoinBalance);
		key="fiBcoinBalance.update";
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
