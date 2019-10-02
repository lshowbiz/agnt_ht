package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiDepositBalance;
import com.joymain.jecs.fi.service.JfiDepositBalanceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiDepositBalanceFormController extends BaseFormController {
    private JfiDepositBalanceManager jfiDepositBalanceManager = null;

    public void setJfiDepositBalanceManager(JfiDepositBalanceManager jfiDepositBalanceManager) {
        this.jfiDepositBalanceManager = jfiDepositBalanceManager;
    }
    public JfiDepositBalanceFormController() {
        setCommandName("jfiDepositBalance");
        setCommandClass(JfiDepositBalance.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String fdbId = request.getParameter("fdbId");
        JfiDepositBalance jfiDepositBalance = null;

        if (!StringUtils.isEmpty(fdbId)) {
            jfiDepositBalance = jfiDepositBalanceManager.getJfiDepositBalance(fdbId);
        } else {
            jfiDepositBalance = new JfiDepositBalance();
        }

        return jfiDepositBalance;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiDepositBalance jfiDepositBalance = (JfiDepositBalance) command;
        boolean isNew = (jfiDepositBalance.getFdbId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiDepositBalance".equals(strAction)  ) {
		jfiDepositBalanceManager.removeJfiDepositBalance(jfiDepositBalance.getFdbId().toString());
		key="jfiDepositBalance.delete";
	}else{
		jfiDepositBalanceManager.saveJfiDepositBalance(jfiDepositBalance);
		key="jfiDepositBalance.update";
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
