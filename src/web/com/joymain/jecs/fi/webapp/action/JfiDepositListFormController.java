package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiDepositList;
import com.joymain.jecs.fi.service.JfiDepositListManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiDepositListFormController extends BaseFormController {
    private JfiDepositListManager jfiDepositListManager = null;

    public void setJfiDepositListManager(JfiDepositListManager jfiDepositListManager) {
        this.jfiDepositListManager = jfiDepositListManager;
    }
    public JfiDepositListFormController() {
        setCommandName("jfiDepositList");
        setCommandClass(JfiDepositList.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        JfiDepositList jfiDepositList = null;

        if (!StringUtils.isEmpty(id)) {
            jfiDepositList = jfiDepositListManager.getJfiDepositList(id);
        } else {
            jfiDepositList = new JfiDepositList();
        }

        return jfiDepositList;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiDepositList jfiDepositList = (JfiDepositList) command;
        boolean isNew = (jfiDepositList.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiDepositList".equals(strAction)  ) {
		jfiDepositListManager.removeJfiDepositList(jfiDepositList.getId().toString());
		key="jfiDepositList.delete";
	}else{
		jfiDepositListManager.saveJfiDepositList(jfiDepositList);
		key="jfiDepositList.update";
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
