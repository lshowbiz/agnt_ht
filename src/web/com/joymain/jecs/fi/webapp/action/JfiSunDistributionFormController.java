package com.joymain.jecs.fi.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.fi.model.JfiSunDistribution;
import com.joymain.jecs.fi.service.JfiSunDistributionManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JfiSunDistributionFormController extends BaseFormController {
    private JfiSunDistributionManager jfiSunDistributionManager = null;

    public void setJfiSunDistributionManager(JfiSunDistributionManager jfiSunDistributionManager) {
        this.jfiSunDistributionManager = jfiSunDistributionManager;
    }
    public JfiSunDistributionFormController() {
        setCommandName("jfiSunDistribution");
        setCommandClass(JfiSunDistribution.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");
        JfiSunDistribution jfiSunDistribution = null;

        if (!StringUtils.isEmpty(userCode)) {
            jfiSunDistribution = jfiSunDistributionManager.getJfiSunDistribution(userCode);
        } else {
            jfiSunDistribution = new JfiSunDistribution();
        }

        return jfiSunDistribution;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JfiSunDistribution jfiSunDistribution = (JfiSunDistribution) command;
        boolean isNew = (jfiSunDistribution.getUserCode() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJfiSunDistribution".equals(strAction)  ) {
		jfiSunDistributionManager.removeJfiSunDistribution(jfiSunDistribution.getUserCode().toString());
		key="jfiSunDistribution.delete";
	}else{
		jfiSunDistributionManager.saveJfiSunDistribution(jfiSunDistribution);
		key="jfiSunDistribution.update";
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
