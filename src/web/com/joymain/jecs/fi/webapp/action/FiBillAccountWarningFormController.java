package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.FiBillAccountWarning;
import com.joymain.jecs.fi.service.FiBillAccountWarningManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiBillAccountWarningFormController extends BaseFormController {
    private FiBillAccountWarningManager fiBillAccountWarningManager = null;

    public void setFiBillAccountWarningManager(FiBillAccountWarningManager fiBillAccountWarningManager) {
        this.fiBillAccountWarningManager = fiBillAccountWarningManager;
    }
    public FiBillAccountWarningFormController() {
        setCommandName("fiBillAccountWarning");
        setCommandClass(FiBillAccountWarning.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String billAccountCode = request.getParameter("billAccountCode");
        FiBillAccountWarning fiBillAccountWarning = null;

        if (!StringUtils.isEmpty(billAccountCode)) {
            fiBillAccountWarning = fiBillAccountWarningManager.getFiBillAccountWarning(billAccountCode);
        } else {
            fiBillAccountWarning = new FiBillAccountWarning();
        }

        return fiBillAccountWarning;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        FiBillAccountWarning fiBillAccountWarning = (FiBillAccountWarning) command;
        boolean isNew = (fiBillAccountWarning.getBillAccountCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteFiBillAccountWarning".equals(strAction)  ) {
		fiBillAccountWarningManager.removeFiBillAccountWarning(fiBillAccountWarning.getBillAccountCode().toString());
		key="fiBillAccountWarning.delete";
	}else{
		fiBillAccountWarningManager.saveFiBillAccountWarning(fiBillAccountWarning);
		key="fiBillAccountWarning.update";
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
