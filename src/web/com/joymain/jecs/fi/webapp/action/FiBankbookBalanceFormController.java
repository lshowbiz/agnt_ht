package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiBankbookBalance;
import com.joymain.jecs.fi.service.FiBankbookBalanceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiBankbookBalanceFormController extends BaseFormController {
    private FiBankbookBalanceManager fiBankbookBalanceManager = null;

    public void setFiBankbookBalanceManager(FiBankbookBalanceManager fiBankbookBalanceManager) {
        this.fiBankbookBalanceManager = fiBankbookBalanceManager;
    }
    public FiBankbookBalanceFormController() {
        setCommandName("fiBankbookBalance");
        setCommandClass(FiBankbookBalance.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String fbbId = request.getParameter("fbbId");
        FiBankbookBalance fiBankbookBalance = null;

        if (!StringUtils.isEmpty(fbbId)) {
            fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(fbbId);
        } else {
            fiBankbookBalance = new FiBankbookBalance();
        }

        return fiBankbookBalance;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiBankbookBalance fiBankbookBalance = (FiBankbookBalance) command;
        boolean isNew = (fiBankbookBalance.getFbbId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiBankbookBalance".equals(strAction)  ) {
		fiBankbookBalanceManager.removeFiBankbookBalance(fiBankbookBalance.getFbbId().toString());
		key="fiBankbookBalance.delete";
	}else{
		fiBankbookBalanceManager.saveFiBankbookBalance(fiBankbookBalance);
		key="fiBankbookBalance.update";
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
