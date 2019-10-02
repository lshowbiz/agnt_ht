package com.joymain.jecs.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.service.AlCompanyManager;

public class CompanySelectController implements Controller {
	private final Log log = LogFactory.getLog(CompanySelectController.class);
	private AlCompanyManager alCompanyManager = null;

    public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
        this.alCompanyManager = alCompanyManager;
    }
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        AlCompany alCompany = new AlCompany();
        // populate object with request parameters
        BeanUtils.populate(alCompany, request.getParameterMap());

        List alCompanys = alCompanyManager.getAlCompanys(alCompany);

        return new ModelAndView("common/companySelect", Constants.ALCOMPANY_LIST, alCompanys);
	}

}
