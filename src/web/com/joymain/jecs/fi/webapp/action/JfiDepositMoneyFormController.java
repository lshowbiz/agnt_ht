package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.fi.model.JfiDepositMoney;
import com.joymain.jecs.fi.service.JfiDepositMoneyManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiDepositMoneyFormController extends BaseFormController {
    private JfiDepositMoneyManager jfiDepositMoneyManager = null;

    public void setJfiDepositMoneyManager(JfiDepositMoneyManager jfiDepositMoneyManager) {
        this.jfiDepositMoneyManager = jfiDepositMoneyManager;
    }
    public JfiDepositMoneyFormController() {
        setCommandName("jfiDepositMoney");
        setCommandClass(JfiDepositMoney.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JfiDepositMoney jfiDepositMoney = null;

        if (!StringUtils.isEmpty(id)) {
            jfiDepositMoney = jfiDepositMoneyManager.getJfiDepositMoney(id);
        } else {
            jfiDepositMoney = new JfiDepositMoney();
        }

        return jfiDepositMoney;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiDepositMoney jfiDepositMoney = (JfiDepositMoney) command;
        boolean isNew = (jfiDepositMoney.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");

		jfiDepositMoneyManager.saveJfiDepositMoney(jfiDepositMoney);
		MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");

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
