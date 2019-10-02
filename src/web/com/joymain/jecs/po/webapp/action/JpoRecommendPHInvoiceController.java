package com.joymain.jecs.po.webapp.action;

import java.util.Date;
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
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JpoRecommendPHInvoiceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoRecommendPHInvoiceController.class);
    private JmiMemberManager jmiMemberManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private JalTownManager jalTownManager;
    private JpoMemberOrderManager jpoMemberOrderManager;
    private AlCountryManager alCountryManager;
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	
    	String moId=request.getParameter("moId");
    	
    	JpoMemberOrder jpoMemberOrder=jpoMemberOrderManager.getJpoMemberOrder(moId);
    	
    	if(jpoMemberOrder!=null){
            //收货地区
            AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
        	alCountry.getAlStateProvinces().iterator();
        	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    		if(!StringUtils.isEmpty(jpoMemberOrder.getProvince())){
        		List<AlCity> citys=alCityManager.getAlCityByProvinceId(Long.parseLong(jpoMemberOrder.getProvince()));
        		for (AlCity city : citys) {
        			if(city.getCityId().toString().equals(jpoMemberOrder.getCity())){
        				jpoMemberOrder.setCity(city.getCityName());
        				if(!StringUtils.isEmpty(jpoMemberOrder.getDistrict())){
            				List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
            				for (AlDistrict district : alDistricts) {
            					if(district.getDistrictId().toString().equals(jpoMemberOrder.getDistrict())){
            						jpoMemberOrder.setDistrict(district.getDistrictName());
        	        				if(!StringUtils.isEmpty(jpoMemberOrder.getDistrict())){
        	            				List<JalTown> alTowns = jalTownManager.getJalTownByDistrictId(district.getDistrictId());
        	            				for (JalTown town : alTowns) {
        	            					if(town.getTownId().toString().equals(jpoMemberOrder.getTown())){
        	            						jpoMemberOrder.setTown(town.getTownName());
        	            					}
        	            				}
        	        				}
            						break;
            					}
            				}
        				}
        				break;
        			}
        		}
    		}
    		if(!StringUtils.isEmpty(jpoMemberOrder.getSysUser().getJmiMember().getProvince())){
        		List<AlCity> citys=alCityManager.getAlCityByProvinceId(Long.parseLong(jpoMemberOrder.getSysUser().getJmiMember().getProvince()));
        		for (AlCity city : citys) {
        			if(city.getCityId().toString().equals(jpoMemberOrder.getSysUser().getJmiMember().getCity())){
        				jpoMemberOrder.getSysUser().getJmiMember().setCity(city.getCityName());
        				if(!StringUtils.isEmpty(jpoMemberOrder.getSysUser().getJmiMember().getDistrict())){
            				List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
            				for (AlDistrict district : alDistricts) {
            					if(district.getDistrictId().toString().equals(jpoMemberOrder.getSysUser().getJmiMember().getDistrict())){
            						jpoMemberOrder.getSysUser().getJmiMember().setDistrict(district.getDistrictName());
        	        				if(!StringUtils.isEmpty(jpoMemberOrder.getSysUser().getJmiMember().getDistrict())){
        	            				List<JalTown> alTowns = jalTownManager.getJalTownByDistrictId(district.getDistrictId());
        	            				for (JalTown town : alTowns) {
        	            					if(town.getTownId().toString().equals(jpoMemberOrder.getSysUser().getJmiMember().getTown())){
        	            						jpoMemberOrder.getSysUser().getJmiMember().setTown(town.getTownName());
        	            					}
        	            				}
        	        				}
            						break;
            					}
            				}
        				}
        				break;
        			}
        		}
    		}
    		request.setAttribute("jpoMemberOrder", jpoMemberOrder);
    		request.setAttribute("curDate", new Date());
    		return new ModelAndView("po/jpoRecommendPHInvoice");
    	}else{
    		return null;
    	}
    	
    	
    	

    }

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setJalTownManager(JalTownManager jalTownManager) {
		this.jalTownManager = jalTownManager;
	}

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
}
