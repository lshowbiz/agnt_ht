package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiDepositTemp;
import com.joymain.jecs.fi.service.JfiDepositTempManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiDepositTempFormController extends BaseFormController {
    private JfiDepositTempManager jfiDepositTempManager = null;

    public void setJfiDepositTempManager(JfiDepositTempManager jfiDepositTempManager) {
        this.jfiDepositTempManager = jfiDepositTempManager;
    }
    public JfiDepositTempFormController() {
        setCommandName("jfiDepositTemp");
        setCommandClass(JfiDepositTemp.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String tempId = request.getParameter("tempId");
        JfiDepositTemp jfiDepositTemp = null;

        if (!StringUtils.isEmpty(tempId)) {
            jfiDepositTemp = jfiDepositTempManager.getJfiDepositTemp(tempId);
        } else {
            jfiDepositTemp = new JfiDepositTemp();
        }

        return jfiDepositTemp;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiDepositTemp jfiDepositTemp = (JfiDepositTemp) command;
        boolean isNew = (jfiDepositTemp.getTempId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiDepositTemp".equals(strAction)  ) {
		jfiDepositTempManager.removeJfiDepositTemp(jfiDepositTemp.getTempId().toString());
		key="jfiDepositTemp.delete";
	}else{
		jfiDepositTempManager.saveJfiDepositTemp(jfiDepositTemp);
		key="jfiDepositTemp.update";
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
