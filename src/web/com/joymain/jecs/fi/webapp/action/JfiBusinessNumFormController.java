package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiBusinessNum;
import com.joymain.jecs.fi.service.JfiBusinessNumManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiBusinessNumFormController extends BaseFormController {
    private JfiBusinessNumManager jfiBusinessNumManager = null;

    public void setJfiBusinessNumManager(JfiBusinessNumManager jfiBusinessNumManager) {
        this.jfiBusinessNumManager = jfiBusinessNumManager;
    }
    public JfiBusinessNumFormController() {
        setCommandName("jfiBusinessNum");
        setCommandClass(JfiBusinessNum.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String businessId = request.getParameter("businessId");
        JfiBusinessNum jfiBusinessNum = null;

        if (!StringUtils.isEmpty(businessId)) {
            jfiBusinessNum = jfiBusinessNumManager.getJfiBusinessNum(businessId);
        } else {
            jfiBusinessNum = new JfiBusinessNum();
        }

        return jfiBusinessNum;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiBusinessNum jfiBusinessNum = (JfiBusinessNum) command;
        boolean isNew = (jfiBusinessNum.getBusinessId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiBusinessNum".equals(strAction)  ) {
		jfiBusinessNumManager.removeJfiBusinessNum(jfiBusinessNum.getBusinessId().toString());
		key="jfiBusinessNum.delete";
	}else{
		jfiBusinessNumManager.saveJfiBusinessNum(jfiBusinessNum);
		key="jfiBusinessNum.update";
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
