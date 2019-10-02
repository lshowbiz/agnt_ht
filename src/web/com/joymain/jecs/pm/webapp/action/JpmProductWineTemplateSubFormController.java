package com.joymain.jecs.pm.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.pm.model.JpmProductWineTemplateSub;
import com.joymain.jecs.pm.service.JpmProductWineTemplateSubManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpmProductWineTemplateSubFormController extends BaseFormController {
    private JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager = null;

    public void setJpmProductWineTemplateSubManager(JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager) {
        this.jpmProductWineTemplateSubManager = jpmProductWineTemplateSubManager;
    }
    public JpmProductWineTemplateSubFormController() {
        setCommandName("jpmProductWineTemplateSub");
        setCommandClass(JpmProductWineTemplateSub.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String subNo = request.getParameter("subNo");
        JpmProductWineTemplateSub jpmProductWineTemplateSub = null;

        if (!StringUtils.isEmpty(subNo)) {
            jpmProductWineTemplateSub = jpmProductWineTemplateSubManager.getJpmProductWineTemplateSub(subNo);
        } else {
            jpmProductWineTemplateSub = new JpmProductWineTemplateSub();
        }

        return jpmProductWineTemplateSub;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpmProductWineTemplateSub jpmProductWineTemplateSub = (JpmProductWineTemplateSub) command;
        boolean isNew = (jpmProductWineTemplateSub.getSubNo() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpmProductWineTemplateSub".equals(strAction)  ) {
		jpmProductWineTemplateSubManager.removeJpmProductWineTemplateSub(jpmProductWineTemplateSub.getSubNo().toString());
		key="jpmProductWineTemplateSub.delete";
	}else{
		jpmProductWineTemplateSubManager.saveJpmProductWineTemplateSub(jpmProductWineTemplateSub);
		key="jpmProductWineTemplateSub.update";
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
