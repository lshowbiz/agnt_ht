package com.joymain.jecs.al.webapp.action;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCompanyArea;
import com.joymain.jecs.al.service.AlCompanyAreaManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.webapp.action.BaseFormController;

public class AlCompanyAreaFormController extends BaseFormController {
	private AlCompanyAreaManager alCompanyAreaManager = null;
	private AlCompanyManager alCompanyManager = null;
	private AlCountryManager alCountryManager = null;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setAlCompanyAreaManager(AlCompanyAreaManager alCompanyAreaManager) {
		this.alCompanyAreaManager = alCompanyAreaManager;
	}

	public AlCompanyAreaFormController() {
		setCommandName("alCompanyArea");
		setCommandClass(AlCompanyArea.class);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String companyId = request.getParameter("companyId");
		AlCompany alCompany = this.alCompanyManager.getAlCompany(companyId);
		List companyAlCountrys = this.alCountryManager.getAlCountrysByCompany(alCompany.getCompanyCode());
		List noCompanyAlCountrys = this.alCountryManager.getAlCountrysNoCompany();

		request.setAttribute("alCompany", alCompany);
		request.setAttribute("companyAlCountrys", companyAlCountrys);
		request.setAttribute("noCompanyAlCountrys", noCompanyAlCountrys);

		return new AlCompanyArea();
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
	        throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		
		String companyId = request.getParameter("companyId");
		AlCompany alCompany = this.alCompanyManager.getAlCompany(companyId);
		
		String[] countryCodes=request.getParameterValues("companyAlCountry");
		this.alCountryManager.saveAlCountryCompany(alCompany.getCompanyCode(), countryCodes);
		
		saveMessage(request, getText("alCompany.updated"));

		return new ModelAndView(getSuccessView());
	}
}
