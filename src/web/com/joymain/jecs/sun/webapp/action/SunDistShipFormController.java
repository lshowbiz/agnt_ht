package com.joymain.jecs.sun.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.sun.model.SunDistShip;
import com.joymain.jecs.sun.service.SunDistShipManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class SunDistShipFormController extends BaseFormController {
    private SunDistShipManager sunDistShipManager = null;

    public void setSunDistShipManager(SunDistShipManager sunDistShipManager) {
        this.sunDistShipManager = sunDistShipManager;
    }
    public SunDistShipFormController() {
        setCommandName("sunDistShip");
        setCommandClass(SunDistShip.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sdsId = request.getParameter("sdsId");
        SunDistShip sunDistShip = null;

        if (!StringUtils.isEmpty(sdsId)) {
            sunDistShip = sunDistShipManager.getSunDistShip(sdsId);
        } else {
            sunDistShip = new SunDistShip();
        }

        return sunDistShip;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SunDistShip sunDistShip = (SunDistShip) command;
        boolean isNew = (sunDistShip.getSdsId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteSunDistShip".equals(strAction)  ) {
		sunDistShipManager.removeSunDistShip(sunDistShip.getSdsId().toString());
		key="sunDistShip.delete";
	}else{
		sunDistShipManager.saveSunDistShip(sunDistShip);
		key="sunDistShip.update";
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
