package com.joymain.jecs.sun.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.sun.model.SunProductInfo;
import com.joymain.jecs.sun.service.SunProductInfoManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class SunProductInfoFormController extends BaseFormController {
    private SunProductInfoManager sunProductInfoManager = null;

    public void setSunProductInfoManager(SunProductInfoManager sunProductInfoManager) {
        this.sunProductInfoManager = sunProductInfoManager;
    }
    public SunProductInfoFormController() {
        setCommandName("sunProductInfo");
        setCommandClass(SunProductInfo.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String jpiId = request.getParameter("jpiId");
        SunProductInfo sunProductInfo = null;

        if (!StringUtils.isEmpty(jpiId)) {
            sunProductInfo = sunProductInfoManager.getSunProductInfo(jpiId);
        } else {
            sunProductInfo = new SunProductInfo();
        }

        return sunProductInfo;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SunProductInfo sunProductInfo = (SunProductInfo) command;
        boolean isNew = (sunProductInfo.getJpiId() == null);
        Locale locale = request.getLocale();
	String key=null;
	String strAction = request.getParameter("strAction");
	if("addSunProductInfo".equals(strAction)){
		sunProductInfoManager.saveSunProductInfo(sunProductInfo);
		sunProductInfoManager.updateSunOrder(sunProductInfo);
		
	}else if("updateSunProductInfo".equals(strAction)){
		sunProductInfoManager.saveSunProductInfo(sunProductInfo);
		sunProductInfoManager.updateSunOrder(sunProductInfo);
	}
	
	/*if ("deleteSunProductInfo".equals(strAction)  ) {
		sunProductInfoManager.removeSunProductInfo(sunProductInfo.getJpiId().toString());
		key="sunProductInfo.delete";
	}else{
		sunProductInfoManager.saveSunProductInfo(sunProductInfo);
		key="sunProductInfo.update";
	}*/

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
