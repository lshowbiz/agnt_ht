package com.joymain.jecs.pd.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pd.model.MailStatus;
import com.joymain.jecs.pd.service.MailStatusManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class MailStatusFormController extends BaseFormController {
    private MailStatusManager mailStatusManager = null;

    public void setMailStatusManager(MailStatusManager mailStatusManager) {
        this.mailStatusManager = mailStatusManager;
    }
    public MailStatusFormController() {
        setCommandName("mailStatus");
        setCommandClass(MailStatus.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String statusId = request.getParameter("statusId");
        MailStatus mailStatus = null;

        if (!StringUtils.isEmpty(statusId)) {
            mailStatus = mailStatusManager.getMailStatus(Long.parseLong(statusId));
        } else {
            mailStatus = new MailStatus();
        }

        return mailStatus;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        MailStatus mailStatus = (MailStatus) command;
        boolean isNew = (mailStatus.getStatusId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteMailStatus".equals(strAction)  ) {
		mailStatusManager.removeMailStatus(mailStatus.getStatusId());
		key="mailStatus.delete";
	}else{
		//mailStatusManager.saveMailStatus(mailStatus);
		key="mailStatus.update";
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
