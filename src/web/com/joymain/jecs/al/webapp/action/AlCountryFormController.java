package com.joymain.jecs.al.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class AlCountryFormController extends BaseFormController {
	private AlCountryManager alCountryManager = null;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public AlCountryFormController() {
		setCommandName("alCountry");
		setCommandClass(AlCountry.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String countryId = request.getParameter("countryId");
		AlCountry alCountry = null;

		if (!StringUtils.isEmpty(countryId)) {
			alCountry = alCountryManager.getAlCountry(countryId);
		} else {
			alCountry = new AlCountry();
		}

		return alCountry;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		AlCountry alCountry = (AlCountry) command;
		boolean isNew = (alCountry.getCountryId() == null);

		if ("deleteAlCountry".equalsIgnoreCase(request.getParameter("strAction"))) {
			if(alCountry.getAlStateProvinces().size()>0){
    			saveMessage(request, getText("sysModule.error.cannot.select.child"));
    			return showForm(request, response, errors);
			}
			alCountryManager.removeAlCountry(alCountry.getCountryId().toString());

			saveMessage(request, getText("alCountry.deleted"));
		} else {
			if ("addAlCountry".equalsIgnoreCase(request.getParameter("strAction"))) {
				// 判断是否存在
				AlCountry oldAlCountry = this.alCountryManager.getAlCountryByCode(alCountry.getCountryCode());
				if (oldAlCountry != null) {
					// 存在
					errors.rejectValue("countryCode","error.countryCode.existed");
					return showForm(request, response, errors);
				}
			}
			alCountryManager.saveAlCountry(alCountry);

			String key = (isNew) ? "alCountry.added" : "alCountry.updated";
			saveMessage(request, getText(key));
		}

		ModelAndView mv=new ModelAndView(getSuccessView());
		mv.addObject("needReload", "1");
		return mv;
	}
}
