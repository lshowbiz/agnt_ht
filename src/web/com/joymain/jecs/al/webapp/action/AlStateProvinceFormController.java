package com.joymain.jecs.al.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class AlStateProvinceFormController extends BaseFormController {
	private AlStateProvinceManager alStateProvinceManager = null;
	private AlCountryManager alCountryManager = null;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlStateProvinceManager(AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}

	public AlStateProvinceFormController() {
		setCommandName("alStateProvince");
		setCommandClass(AlStateProvince.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String stateProvinceId = request.getParameter("stateProvinceId");
		AlStateProvince alStateProvince = null;

		if (!StringUtils.isEmpty(stateProvinceId)) {
			alStateProvince = alStateProvinceManager.getAlStateProvince(stateProvinceId);
		} else {
			alStateProvince = new AlStateProvince();
			
			String countryId=request.getParameter("countryId");
			AlCountry alCountry=this.alCountryManager.getAlCountry(countryId);
			alStateProvince.setAlCountry(alCountry);
			
		}

		return alStateProvince;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		AlStateProvince alStateProvince = (AlStateProvince) command;
		String countryId=request.getParameter("countryId");
		boolean isNew = (alStateProvince.getStateProvinceId() == null);

		if ("deleteAlStateProvince".equalsIgnoreCase(request.getParameter("strAction"))) {
			if(alStateProvince.getAlCitys().size()>0){
    			saveMessage(request, getText("sysModule.error.cannot.select.child"));
    			return showForm(request, response, errors);
			}
			alStateProvinceManager.removeAlStateProvince(alStateProvince.getStateProvinceId().toString());

			saveMessage(request, getText("alStateProvince.deleted"));
		} else {
			if ("addAlStateProvince".equalsIgnoreCase(request.getParameter("strAction"))) {
				// 判断是否存在
				AlStateProvince oldAlStateProvince = this.alStateProvinceManager.getAlStateProvinceByCode(countryId, alStateProvince.getStateProvinceCode());
				if (oldAlStateProvince != null) {
					// 存在
					errors.rejectValue("stateProvinceCode","error.stateProvinceCode.existed");
					return showForm(request, response, errors);
				}
			}
			AlCountry alCountry=this.alCountryManager.getAlCountry(countryId);
			alStateProvince.setAlCountry(alCountry);
			alStateProvinceManager.saveAlStateProvince(alStateProvince);

			String key = (isNew) ? "alStateProvince.added" : "alStateProvince.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("countryId", countryId);
		mv.addObject("needReload", "1");
		return mv;
	}
}
