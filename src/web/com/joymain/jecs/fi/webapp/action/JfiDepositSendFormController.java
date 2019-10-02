package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiDepositSend;
import com.joymain.jecs.fi.service.JfiDepositSendManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiDepositSendFormController extends BaseFormController {
    private JfiDepositSendManager jfiDepositSendManager = null;

    public void setJfiDepositSendManager(JfiDepositSendManager jfiDepositSendManager) {
        this.jfiDepositSendManager = jfiDepositSendManager;
    }
    public JfiDepositSendFormController() {
        setCommandName("jfiDepositSend");
        setCommandClass(JfiDepositSend.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JfiDepositSend jfiDepositSend = null;

        if (!StringUtils.isEmpty(id)) {
            jfiDepositSend = jfiDepositSendManager.getJfiDepositSend(id);
        } else {
            jfiDepositSend = new JfiDepositSend();
        }

        return jfiDepositSend;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiDepositSend jfiDepositSend = (JfiDepositSend) command;
        boolean isNew = (jfiDepositSend.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiDepositSend".equals(strAction)  ) {
		jfiDepositSendManager.removeJfiDepositSend(jfiDepositSend.getId().toString());
		key="jfiDepositSend.delete";
	}else{
		jfiDepositSendManager.saveJfiDepositSend(jfiDepositSend);
		key="jfiDepositSend.update";
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
