package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.fi.model.JfiSunMemberOrder;
import com.joymain.jecs.fi.service.JfiSunMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiSunMemberOrderController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunMemberOrderController.class);
    private JfiSunMemberOrderManager jfiSunMemberOrderManager = null;
    private AlCountryManager alCountryManager = null;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;

    public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setJfiSunMemberOrderManager(JfiSunMemberOrderManager jfiSunMemberOrderManager) {
        this.jfiSunMemberOrderManager = jfiSunMemberOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiSunMemberOrderListTable",request, 20);
        List<JfiSunMemberOrder> jfiSunMemberOrders = jfiSunMemberOrderManager.getJfiSunMemberOrdersByCrm(crm,pager);
        request.setAttribute("jfiSunMemberOrderListTable_totalRows", pager.getTotalObjects());

        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany("CN").get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	
/*        for (JfiSunMemberOrder jfiSunMemberOrder : jfiSunMemberOrders) {
			List<AlCity> citys=alCityManager.getAlCityByProvinceId(Long.parseLong(jfiSunMemberOrder.getProvince()));
			for (AlCity city : citys) {
				if(city.getCityId().toString().equals(jfiSunMemberOrder.getCity())){
					jfiSunMemberOrder.setCity(city.getCityName());
					List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
					for (AlDistrict district : alDistricts) {
						if(district.getDistrictId().toString().equals(jfiSunMemberOrder.getDistrict())){
							jfiSunMemberOrder.setDistrict(district.getDistrictName());
							break;
						}
					}
					break;
				}
			}
		}*/

        return new ModelAndView("fi/jfiSunMemberOrderList", Constants.JFISUNMEMBERORDER_LIST, jfiSunMemberOrders);
    }
}
