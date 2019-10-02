package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiInvoiceChange;
import com.joymain.jecs.fi.service.FiInvoiceChangeManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiInvoiceChangeFormController extends BaseFormController {
    private FiInvoiceChangeManager fiInvoiceChangeManager = null;

    public void setFiInvoiceChangeManager(FiInvoiceChangeManager fiInvoiceChangeManager) {
        this.fiInvoiceChangeManager = fiInvoiceChangeManager;
    }
    public FiInvoiceChangeFormController() {
        setCommandName("fiInvoiceChange");
        setCommandClass(FiInvoiceChange.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        FiInvoiceChange fiInvoiceChange = null;

        if (!StringUtils.isEmpty(id)) {
            fiInvoiceChange = fiInvoiceChangeManager.getFiInvoiceChange(id);
        } else {
            fiInvoiceChange = new FiInvoiceChange();
        }

        return fiInvoiceChange;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiInvoiceChange fiInvoiceChange = (FiInvoiceChange) command;
        boolean isNew = (fiInvoiceChange.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiInvoiceChange".equals(strAction)  ) {
		fiInvoiceChangeManager.removeFiInvoiceChange(fiInvoiceChange.getId().toString());
		key="fiInvoiceChange.delete";
	}else{
		fiInvoiceChangeManager.saveFiInvoiceChange(fiInvoiceChange);
		key="fiInvoiceChange.update";
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
