package com.joymain.jecs.al.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.JalTownManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JalTownFormController extends BaseFormController {
    private JalTownManager jalTownManager = null;

    public void setJalTownManager(JalTownManager jalTownManager) {
        this.jalTownManager = jalTownManager;
    }
    public JalTownFormController() {
        setCommandName("jalTown");
        setCommandClass(JalTown.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String townId = request.getParameter("townId");
        JalTown jalTown = null;

        if (!StringUtils.isEmpty(townId)) {
            jalTown = jalTownManager.getJalTown(townId);
        } else {
            jalTown = new JalTown();
        }

        return jalTown;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JalTown jalTown = (JalTown) command;
        boolean isNew = (jalTown.getTownId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJalTown".equals(strAction)  ) {
		jalTownManager.removeJalTown(jalTown.getTownId().toString());
		key="jalTown.delete";
	}else{
		jalTownManager.saveJalTown(jalTown);
		key="jalTown.update";
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
