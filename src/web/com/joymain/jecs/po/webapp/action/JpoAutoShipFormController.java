package com.joymain.jecs.po.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.po.model.JpoAutoShip;
import com.joymain.jecs.po.service.JpoAutoShipManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoAutoShipFormController extends BaseFormController {
    private JpoAutoShipManager jpoAutoShipManager = null;

    public void setJpoAutoShipManager(JpoAutoShipManager jpoAutoShipManager) {
        this.jpoAutoShipManager = jpoAutoShipManager;
    }
    public JpoAutoShipFormController() {
        setCommandName("jpoAutoShip");
        setCommandClass(JpoAutoShip.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String jasId = request.getParameter("jasId");
        JpoAutoShip jpoAutoShip = null;
        
        String autoship = jpoAutoShipManager.getAutoShipConfigValue("autoship");
        if(!"0".equals(autoship)){
        	throw new AppException("waiting...");
        }

        if (!StringUtils.isEmpty(jasId)) {
            jpoAutoShip = jpoAutoShipManager.getJpoAutoShip(jasId);
        } else {
            jpoAutoShip = new JpoAutoShip();
        }

        return jpoAutoShip;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        JpoAutoShip jpoAutoShip = (JpoAutoShip) command;
        boolean isNew = (jpoAutoShip.getJasId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if ("deleteJpoAutoShip".equals(strAction)  ) {
		jpoAutoShipManager.removeJpoAutoShip(jpoAutoShip.getJasId().toString());
		key="jpoAutoShip.delete";
	}else{
		jpoAutoShipManager.saveJpoAutoShip(jpoAutoShip);
		key="jpoAutoShip.update";
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
