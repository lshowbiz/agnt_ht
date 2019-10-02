package com.joymain.jecs.bd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.bd.model.JbdBonusBalance;
import com.joymain.jecs.bd.service.JbdBonusBalanceManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JbdBonusBalanceFormController extends BaseFormController {
    private JbdBonusBalanceManager jbdBonusBalanceManager = null;

    public void setJbdBonusBalanceManager(JbdBonusBalanceManager jbdBonusBalanceManager) {
        this.jbdBonusBalanceManager = jbdBonusBalanceManager;
    }
    public JbdBonusBalanceFormController() {
        setCommandName("jbdBonusBalance");
        setCommandClass(JbdBonusBalance.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JbdBonusBalance jbdBonusBalance = null;

        if (!StringUtils.isEmpty(userCode)) {
            jbdBonusBalance = jbdBonusBalanceManager.getJbdBonusBalance(userCode);
        } else {
            jbdBonusBalance = new JbdBonusBalance();
        }

        return jbdBonusBalance;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JbdBonusBalance jbdBonusBalance = (JbdBonusBalance) command;
        boolean isNew = (jbdBonusBalance.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJbdBonusBalance".equals(strAction)  ) {
		jbdBonusBalanceManager.removeJbdBonusBalance(jbdBonusBalance.getUserCode().toString());
		key="jbdBonusBalance.delete";
	}else{
		jbdBonusBalanceManager.saveJbdBonusBalance(jbdBonusBalance);
		key="jbdBonusBalance.update";
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
