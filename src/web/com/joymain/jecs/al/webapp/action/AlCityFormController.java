package com.joymain.jecs.al.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class AlCityFormController extends BaseFormController {
    private AlCityManager alCityManager = null;
    private AlStateProvinceManager alStateProvinceManager;
    public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}
	public void setAlCityManager(AlCityManager alCityManager) {
        this.alCityManager = alCityManager;
    }
    public AlCityFormController() {
        setCommandName("alCity");
        setCommandClass(AlCity.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String cityId = request.getParameter("cityId");
        AlCity alCity = null;

        if (!StringUtils.isEmpty(cityId)) {
            alCity = alCityManager.getAlCity(cityId);
        } else {
            alCity = new AlCity();
    		String stateProvinceId = request.getParameter("stateProvinceId");
    		alCity.setAlStateProvince(alStateProvinceManager.getAlStateProvince(stateProvinceId));
        }

        return alCity;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        AlCity alCity = (AlCity) command;
        boolean isNew = (alCity.getCityId() == null);
        
    	if ("deleteAlCity".equalsIgnoreCase(request.getParameter("strAction"))) {
    		if(alCity.getAlDistricts().size()>0){
    			saveMessage(request, getText("sysModule.error.cannot.select.child"));
    			return showForm(request, response, errors);
    			
    		}
    		alCityManager.removeAlCity(alCity.getCityId().toString());

			saveMessage(request, getText("alCity.deleted"));
		} else {
//			if ("addAlCity".equalsIgnoreCase(request.getParameter("strAction"))) {
//				// 判断是否存在
//
//			}
//			AlCountry alCountry=this.alCountryManager.getAlCountry(countryId);
//			alStateProvince.setAlCountry(alCountry);
			alCityManager.saveAlCity(alCity);

			String key = (isNew) ? "alCity.added" : "alCity.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("stateProvinceId", alCity.getAlStateProvince().getStateProvinceId());
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
