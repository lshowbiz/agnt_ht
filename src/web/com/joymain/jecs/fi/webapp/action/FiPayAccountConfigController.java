package com.joymain.jecs.fi.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.fi.model.FiPayAccountConfig;
import com.joymain.jecs.fi.service.FiPayAccountConfigManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FiPayAccountConfigController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(FiPayAccountConfigController.class);
    private FiPayAccountConfigManager fiPayAccountConfigManager = null;
    private AlCompanyManager alCompanyManager;
	private AlCountryManager alCountryManager = null;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

    public void setFiPayAccountConfigManager(FiPayAccountConfigManager fiPayAccountConfigManager) {
        this.fiPayAccountConfigManager = fiPayAccountConfigManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        // 读取省份
		AlCompany alCompany = alCompanyManager.getAlCompanyByCode("CN");
		AlCountry alCountry = new AlCountry();
		alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
		alCountry.getAlStateProvinces().iterator();
		request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
		
		//初始化	
        String strAction = request.getParameter("strAction");
    	if ("unitFiPayAccountConfig".equals(strAction)  ) {
    		
    		List<FiPayAccountConfig> list = new ArrayList<FiPayAccountConfig>();
    		
    		Iterator its1 = alCountry.getAlStateProvinces().iterator();
    		 while (its1.hasNext()){
    			 
    			 AlStateProvince alStateProvince = (AlStateProvince) its1.next();
    			 
    			 FiPayAccountConfig fiPayAccountConfig = new FiPayAccountConfig();
    			 fiPayAccountConfig.setProvince(alStateProvince.getStateProvinceId().toString());
    			 fiPayAccountConfig.setProvinceName(alStateProvince.getStateProvinceName());
    			 
    			 list.add(fiPayAccountConfig);
    		 }
    		 
    		 fiPayAccountConfigManager.saveFiPayAccountConfigs(list);
    	}

        FiPayAccountConfig fiPayAccountConfig = new FiPayAccountConfig();
        // populate object with request parameters
        BeanUtils.populate(fiPayAccountConfig, request.getParameterMap());

	//List fiPayAccountConfigs = fiPayAccountConfigManager.getFiPayAccountConfigs(fiPayAccountConfig);
	/**** auto pagination ***/
	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("fiPayAccountConfigListTable",request, 20);
        List fiPayAccountConfigs = fiPayAccountConfigManager.getFiPayAccountConfigsByCrm(crm,pager);
        request.setAttribute("fiPayAccountConfigListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/fiPayAccountConfigList", "fiPayAccountConfigList", fiPayAccountConfigs);
    }
}
