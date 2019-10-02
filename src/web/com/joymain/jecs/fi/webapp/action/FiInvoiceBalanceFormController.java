package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiInvoiceBalance;
import com.joymain.jecs.fi.service.FiInvoiceBalanceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiInvoiceBalanceFormController extends BaseFormController {
    private FiInvoiceBalanceManager fiInvoiceBalanceManager = null;

    public void setFiInvoiceBalanceManager(FiInvoiceBalanceManager fiInvoiceBalanceManager) {
        this.fiInvoiceBalanceManager = fiInvoiceBalanceManager;
    }
    public FiInvoiceBalanceFormController() {
        setCommandName("fiInvoiceBalance");
        setCommandClass(FiInvoiceBalance.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        FiInvoiceBalance fiInvoiceBalance = null;

        if (!StringUtils.isEmpty(id)) {
            fiInvoiceBalance = fiInvoiceBalanceManager.getFiInvoiceBalance(id);
        } else {
            fiInvoiceBalance = new FiInvoiceBalance();
        }

        return fiInvoiceBalance;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiInvoiceBalance fiInvoiceBalance = (FiInvoiceBalance) command;
        boolean isNew = (fiInvoiceBalance.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiInvoiceBalance".equals(strAction)  ) {
		fiInvoiceBalanceManager.removeFiInvoiceBalance(fiInvoiceBalance.getId().toString());
		key="fiInvoiceBalance.delete";
	}else{
		fiInvoiceBalanceManager.saveFiInvoiceBalance(fiInvoiceBalance);
		key="fiInvoiceBalance.update";
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
