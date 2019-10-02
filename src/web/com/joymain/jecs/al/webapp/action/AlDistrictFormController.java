package com.joymain.jecs.al.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class AlDistrictFormController extends BaseFormController {
    private AlDistrictManager alDistrictManager = null;
    private AlCityManager alCityManager = null;
    public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}
	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
        this.alDistrictManager = alDistrictManager;
    }
    public AlDistrictFormController() {
        setCommandName("alDistrict");
        setCommandClass(AlDistrict.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String districtId = request.getParameter("districtId");
        AlDistrict alDistrict = null;

        if (!StringUtils.isEmpty(districtId)) {
            alDistrict = alDistrictManager.getAlDistrict(districtId);
        } else {
            alDistrict = new AlDistrict();
            String cityId = request.getParameter("cityId");
            alDistrict.setAlCity(alCityManager.getAlCity(cityId));
        }

        return alDistrict;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AlDistrict alDistrict = (AlDistrict) command;
        boolean isNew = (alDistrict.getDistrictId() == null);
        
    	if ("deleteAlDistrict".equalsIgnoreCase(request.getParameter("strAction"))) {
    		alDistrictManager.removeAlDistrict(alDistrict.getDistrictId().toString());

			saveMessage(request, getText("alDistrict.deleted"));
		} else {
//			if ("addAlCity".equalsIgnoreCase(request.getParameter("strAction"))) {
//				// 判断是否存在
//
//			}
//			AlCountry alCountry=this.alCountryManager.getAlCountry(countryId);
//			alStateProvince.setAlCountry(alCountry);
			alDistrictManager.saveAlDistrict(alDistrict);

			String key = (isNew) ? "alDistrict.added" : "alDistrict.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("cityId", alDistrict.getAlCity().getCityId());
		mv.addObject("needReload", "1");
		return mv;
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
