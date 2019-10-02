package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
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
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 查看订单
 * @author Alvin
 *
 */
public class JfiSunMemberOrderViewController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunMemberOrderViewController.class);
    private JfiSunMemberOrderManager jfiSunMemberOrderManager = null;
	private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private AlCountryManager alCountryManager;
    
    public void setJfiSunMemberOrderManager(
			JfiSunMemberOrderManager jfiSunMemberOrderManager) {
		this.jfiSunMemberOrderManager = jfiSunMemberOrderManager;
	}

    public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);

        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany("CN").get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        
        String moId = request.getParameter("moId");
        if(StringUtils.isNotEmpty(moId)){
        	JfiSunMemberOrder jfiSunMemberOrder = jfiSunMemberOrderManager.getJfiSunMemberOrder(moId);
        	if(jfiSunMemberOrder==null){
        		return null;
        	}else{
        		List<AlCity> citys=alCityManager.getAlCityByProvinceId(Long.parseLong(jfiSunMemberOrder.getProvince()));
        		for (AlCity city : citys) {
        			if(city.getCityId().toString().equals(jfiSunMemberOrder.getCity())){
        				jfiSunMemberOrder.setCity(city.getCityName());
        				if(!StringUtils.isEmpty(jfiSunMemberOrder.getDistrict())){
            				List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
            				for (AlDistrict district : alDistricts) {
            					if(district.getDistrictId().toString().equals(jfiSunMemberOrder.getDistrict())){
            						jfiSunMemberOrder.setDistrict(district.getDistrictName());
            						break;
            					}
            				}
        				}
        				break;
        			}
        		}
        	}
        	return new ModelAndView("fi/jfiSunMemberOrderView", "jfiSunMemberOrder", jfiSunMemberOrder);
        }
        return null;
    }
}
