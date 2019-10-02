package com.joymain.jecs.fi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.fi.model.JfiSunOrder;
import com.joymain.jecs.fi.service.JfiSunOrderManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 查看订单
 * @author Alvin
 *
 */
public class JfiSunOrderViewController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunOrderViewController.class);
    private JfiSunOrderManager jfiSunOrderManager = null;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private AlCountryManager alCountryManager;
    private JalTownManager jalTownManager;

    public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

    public void setJfiSunOrderManager(JfiSunOrderManager jfiSunOrderManager) {
		this.jfiSunOrderManager = jfiSunOrderManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);

        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        
        String moId = request.getParameter("moId");
        if(StringUtils.isNotEmpty(moId)){
        	JfiSunOrder jfiSunOrder = jfiSunOrderManager.getJfiSunOrder(moId);
        	if(jfiSunOrder==null){
        		return null;
        	}else{
        		if(!StringUtils.isEmpty(jfiSunOrder.getProvince())){
            		List<AlCity> citys=alCityManager.getAlCityByProvinceId(Long.parseLong(jfiSunOrder.getProvince()));
            		for (AlCity city : citys) {
            			if(city.getCityId().toString().equals(jfiSunOrder.getCity())){
            				jfiSunOrder.setCity(city.getCityName());
            				if(!StringUtils.isEmpty(jfiSunOrder.getDistrict())){
                				List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
                				for (AlDistrict district : alDistricts) {
                					if(district.getDistrictId().toString().equals(jfiSunOrder.getDistrict())){
                						jfiSunOrder.setDistrict(district.getDistrictName());
/*	        	        				if(!StringUtils.isEmpty(jfiSunOrder.getDistrict())){
	        	            				List<JalTown> alTowns = jalTownManager.getJalTownByDistrictId(district.getDistrictId());
	        	            				for (JalTown town : alTowns) {
	        	            					if(town.getTownId().toString().equals(jfiSunOrder.getTown())){
	        	            						jfiSunOrder.setTown(town.getTownName());
	        	            					}
	        	            				}
	        	        				}*/
                						break;
                					}
                				}
            				}
            				break;
            			}
            		}
        		}
        	}
        	if("C".equals(loginSysUser.getUserType())){
    			if(!"AA".equals(loginSysUser.getCompanyCode())){
    				if(jfiSunOrder.getCompanyCode().equals(loginSysUser.getCompanyCode())){
    					return new ModelAndView("fi/jfiSunOrderView", "jpoMemberOrder", jfiSunOrder);
    				}
    			}else{
    				return new ModelAndView("fi/jfiSunOrderView", "jpoMemberOrder", jfiSunOrder);
    			}
    		}
        }
        return null;
    }

	public void setJalTownManager(JalTownManager jalTownManager) {
		this.jalTownManager = jalTownManager;
	}
}
