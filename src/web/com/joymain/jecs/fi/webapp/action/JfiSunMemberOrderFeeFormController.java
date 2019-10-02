package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiSunMemberOrderFee;
import com.joymain.jecs.fi.service.JfiSunMemberOrderFeeManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiSunMemberOrderFeeFormController extends BaseFormController {
    private JfiSunMemberOrderFeeManager jfiSunMemberOrderFeeManager = null;

    public void setJfiSunMemberOrderFeeManager(JfiSunMemberOrderFeeManager jfiSunMemberOrderFeeManager) {
        this.jfiSunMemberOrderFeeManager = jfiSunMemberOrderFeeManager;
    }
    public JfiSunMemberOrderFeeFormController() {
        setCommandName("jfiSunMemberOrderFee");
        setCommandClass(JfiSunMemberOrderFee.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String mofId = request.getParameter("mofId");
        JfiSunMemberOrderFee jfiSunMemberOrderFee = null;

        if (!StringUtils.isEmpty(mofId)) {
            jfiSunMemberOrderFee = jfiSunMemberOrderFeeManager.getJfiSunMemberOrderFee(mofId);
        } else {
            jfiSunMemberOrderFee = new JfiSunMemberOrderFee();
        }

        return jfiSunMemberOrderFee;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiSunMemberOrderFee jfiSunMemberOrderFee = (JfiSunMemberOrderFee) command;
        boolean isNew = (jfiSunMemberOrderFee.getMofId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiSunMemberOrderFee".equals(strAction)  ) {
		jfiSunMemberOrderFeeManager.removeJfiSunMemberOrderFee(jfiSunMemberOrderFee.getMofId().toString());
		key="jfiSunMemberOrderFee.delete";
	}else{
		jfiSunMemberOrderFeeManager.saveJfiSunMemberOrderFee(jfiSunMemberOrderFee);
		key="jfiSunMemberOrderFee.update";
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
