package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pm.model.JpmConfigSpecDetailed;
import com.joymain.jecs.pm.service.JpmConfigSpecDetailedManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmConfigSpecDetailedFormController extends BaseFormController {
    private JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager = null;

    public void setJpmConfigSpecDetailedManager(JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager) {
        this.jpmConfigSpecDetailedManager = jpmConfigSpecDetailedManager;
    }
    public JpmConfigSpecDetailedFormController() {
        setCommandName("jpmConfigSpecDetailed");
        setCommandClass(JpmConfigSpecDetailed.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String specNo = request.getParameter("specNo");
        JpmConfigSpecDetailed jpmConfigSpecDetailed = null;

        if (!StringUtils.isEmpty(specNo)) {
            jpmConfigSpecDetailed = jpmConfigSpecDetailedManager.getJpmConfigSpecDetailed(specNo);
        } else {
            jpmConfigSpecDetailed = new JpmConfigSpecDetailed();
        }

        return jpmConfigSpecDetailed;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpmConfigSpecDetailed jpmConfigSpecDetailed = (JpmConfigSpecDetailed) command;
        boolean isNew = (jpmConfigSpecDetailed.getSpecNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpmConfigSpecDetailed".equals(strAction)  ) {
		jpmConfigSpecDetailedManager.removeJpmConfigSpecDetailed(jpmConfigSpecDetailed.getSpecNo().toString());
		key="jpmConfigSpecDetailed.delete";
	}else{
		jpmConfigSpecDetailedManager.saveJpmConfigSpecDetailed(jpmConfigSpecDetailed);
		key="jpmConfigSpecDetailed.update";
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
