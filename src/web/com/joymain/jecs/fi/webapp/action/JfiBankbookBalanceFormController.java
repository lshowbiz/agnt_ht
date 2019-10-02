package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiBankbookBalanceFormController extends BaseFormController {
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;

    public void setJfiBankbookBalanceManager(JfiBankbookBalanceManager jfiBankbookBalanceManager) {
        this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
    }
    public JfiBankbookBalanceFormController() {
        setCommandName("jfiBankbookBalance");
        setCommandClass(JfiBankbookBalance.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JfiBankbookBalance jfiBankbookBalance = null;

        if (!StringUtils.isEmpty(userCode)) {
            jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(userCode);
        } else {
            jfiBankbookBalance = new JfiBankbookBalance();
        }

        return jfiBankbookBalance;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiBankbookBalance jfiBankbookBalance = (JfiBankbookBalance) command;
        boolean isNew = (jfiBankbookBalance.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiBankbookBalance".equals(strAction)  ) {
		jfiBankbookBalanceManager.removeJfiBankbookBalance(jfiBankbookBalance.getUserCode().toString());
		key="jfiBankbookBalance.delete";
	}else{
		jfiBankbookBalanceManager.saveJfiBankbookBalance(jfiBankbookBalance);
		key="jfiBankbookBalance.update";
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
