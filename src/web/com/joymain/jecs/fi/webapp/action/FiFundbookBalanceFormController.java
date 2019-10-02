package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiFundbookBalance;
import com.joymain.jecs.fi.service.FiFundbookBalanceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiFundbookBalanceFormController extends BaseFormController {
    private FiFundbookBalanceManager fiFundbookBalanceManager = null;

    public void setFiFundbookBalanceManager(FiFundbookBalanceManager fiFundbookBalanceManager) {
        this.fiFundbookBalanceManager = fiFundbookBalanceManager;
    }
    public FiFundbookBalanceFormController() {
        setCommandName("fiFundbookBalance");
        setCommandClass(FiFundbookBalance.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String fbbId = request.getParameter("fbbId");
        FiFundbookBalance fiFundbookBalance = null;

        if (!StringUtils.isEmpty(fbbId)) {
            fiFundbookBalance = fiFundbookBalanceManager.getFiFundbookBalance(fbbId);
        } else {
            fiFundbookBalance = new FiFundbookBalance();
        }

        return fiFundbookBalance;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiFundbookBalance fiFundbookBalance = (FiFundbookBalance) command;
        boolean isNew = (fiFundbookBalance.getFbbId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiFundbookBalance".equals(strAction)  ) {
		fiFundbookBalanceManager.removeFiFundbookBalance(fiFundbookBalance.getFbbId().toString());
		key="fiFundbookBalance.delete";
	}else{
		fiFundbookBalanceManager.saveFiFundbookBalance(fiFundbookBalance);
		key="fiFundbookBalance.update";
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
