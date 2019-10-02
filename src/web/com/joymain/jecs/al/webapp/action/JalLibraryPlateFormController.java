package com.joymain.jecs.al.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.al.model.JalLibraryPlate;
import com.joymain.jecs.al.service.JalLibraryPlateManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JalLibraryPlateFormController extends BaseFormController {
    private JalLibraryPlateManager jalLibraryPlateManager = null;

    public void setJalLibraryPlateManager(JalLibraryPlateManager jalLibraryPlateManager) {
        this.jalLibraryPlateManager = jalLibraryPlateManager;
    }
    public JalLibraryPlateFormController() {
        setCommandName("jalLibraryPlate");
        setCommandClass(JalLibraryPlate.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String plateId = request.getParameter("plateId");
        JalLibraryPlate jalLibraryPlate = null;

        if (!StringUtils.isEmpty(plateId)) {
            jalLibraryPlate = jalLibraryPlateManager.getJalLibraryPlate(plateId);
        } else {
            jalLibraryPlate = new JalLibraryPlate();
        }

        return jalLibraryPlate;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JalLibraryPlate jalLibraryPlate = (JalLibraryPlate) command;
        boolean isNew = (jalLibraryPlate.getPlateId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJalLibraryPlate".equals(strAction)  ) {
		jalLibraryPlateManager.removeJalLibraryPlate(jalLibraryPlate.getPlateId().toString());
		key="jalLibraryPlate.delete";
	}else{
		jalLibraryPlateManager.saveJalLibraryPlate(jalLibraryPlate);
		key="jalLibraryPlate.update";
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
