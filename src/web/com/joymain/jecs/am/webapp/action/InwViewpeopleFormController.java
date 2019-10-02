package com.joymain.jecs.am.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.am.model.InwViewpeople;
import com.joymain.jecs.am.service.InwViewpeopleManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class InwViewpeopleFormController extends BaseFormController {
    private InwViewpeopleManager inwViewpeopleManager = null;

    public void setInwViewpeopleManager(InwViewpeopleManager inwViewpeopleManager) {
        this.inwViewpeopleManager = inwViewpeopleManager;
    }
    public InwViewpeopleFormController() {
        setCommandName("inwViewpeople");
        setCommandClass(InwViewpeople.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");
        InwViewpeople inwViewpeople = null;

        if (!StringUtils.isEmpty(id)) {
            inwViewpeople = inwViewpeopleManager.getInwViewpeople(id);
        } else {
            inwViewpeople = new InwViewpeople();
        }

        return inwViewpeople;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        InwViewpeople inwViewpeople = (InwViewpeople) command;
        boolean isNew = (inwViewpeople.getId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteInwViewpeople".equals(strAction)  ) {
		inwViewpeopleManager.removeInwViewpeople(inwViewpeople.getId().toString());
		key="inwViewpeople.delete";
	}else{
		inwViewpeopleManager.saveInwViewpeople(inwViewpeople);
		key="inwViewpeople.update";
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
