package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiSunConfigKey;
import com.joymain.jecs.fi.service.JfiSunConfigKeyManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiSunConfigKeyFormController extends BaseFormController {
    private JfiSunConfigKeyManager jfiSunConfigKeyManager = null;

    public void setJfiSunConfigKeyManager(JfiSunConfigKeyManager jfiSunConfigKeyManager) {
        this.jfiSunConfigKeyManager = jfiSunConfigKeyManager;
    }
    public JfiSunConfigKeyFormController() {
        setCommandName("jfiSunConfigKey");
        setCommandClass(JfiSunConfigKey.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String configCode = request.getParameter("configCode");
        JfiSunConfigKey jfiSunConfigKey = null;

        if (!StringUtils.isEmpty(configCode)) {
            jfiSunConfigKey = jfiSunConfigKeyManager.getJfiSunConfigKey(configCode);
        } else {
            jfiSunConfigKey = new JfiSunConfigKey();
        }

        return jfiSunConfigKey;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiSunConfigKey jfiSunConfigKey = (JfiSunConfigKey) command;
        boolean isNew = (jfiSunConfigKey.getConfigCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiSunConfigKey".equals(strAction)  ) {
		jfiSunConfigKeyManager.removeJfiSunConfigKey(jfiSunConfigKey.getConfigCode().toString());
		key="jfiSunConfigKey.delete";
	}else{
		jfiSunConfigKeyManager.saveJfiSunConfigKey(jfiSunConfigKey);
		key="jfiSunConfigKey.update";
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
