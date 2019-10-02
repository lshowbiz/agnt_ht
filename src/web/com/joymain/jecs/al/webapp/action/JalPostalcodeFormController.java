package com.joymain.jecs.al.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.al.model.JalPostalcode;
import com.joymain.jecs.al.service.JalPostalcodeManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JalPostalcodeFormController extends BaseFormController {
    private JalPostalcodeManager jalPostalcodeManager = null;

    public void setJalPostalcodeManager(JalPostalcodeManager jalPostalcodeManager) {
        this.jalPostalcodeManager = jalPostalcodeManager;
    }
    public JalPostalcodeFormController() {
        setCommandName("jalPostalcode");
        setCommandClass(JalPostalcode.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String postalcodeId = request.getParameter("postalcodeId");
        JalPostalcode jalPostalcode = null;

        if (!StringUtils.isEmpty(postalcodeId)) {
            jalPostalcode = jalPostalcodeManager.getJalPostalcode(postalcodeId);
        } else {
            jalPostalcode = new JalPostalcode();
        }

        return jalPostalcode;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JalPostalcode jalPostalcode = (JalPostalcode) command;
        boolean isNew = (jalPostalcode.getPostalcodeId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJalPostalcode".equals(strAction)  ) {
		jalPostalcodeManager.removeJalPostalcode(jalPostalcode.getPostalcodeId().toString());
		key="jalPostalcode.delete";
	}else{
		jalPostalcodeManager.saveJalPostalcode(jalPostalcode);
		key="jalPostalcode.update";
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
