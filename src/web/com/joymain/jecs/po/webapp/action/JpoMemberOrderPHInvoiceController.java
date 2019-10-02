package com.joymain.jecs.po.webapp.action;

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
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * PH订单打印
 * @author Alvin
 *
 */
public class JpoMemberOrderPHInvoiceController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderPHInvoiceController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private AlCountryManager alCountryManager;

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
    	SysUser loginSysUser = SessionLogin.getLoginUser(request);

        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        
        String moId = request.getParameter("moId");
        if(StringUtils.isNotEmpty(moId)){
        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);
        	if(jpoMemberOrder==null){
        		return null;
        	}else{
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
                						break;
                					}
                				}
            				}
            				break;
            			}
            		}
            		
            		List<AlCity> citys1=alCityManager.getAlCityByProvinceId(Long.parseLong(jpoMemberOrder.getSysUser().getJmiMember().getProvince()));
            		for (AlCity city : citys1) {
            			if(city.getCityId().toString().equals(jpoMemberOrder.getSysUser().getJmiMember().getCity())){
            				jpoMemberOrder.getSysUser().getJmiMember().setCity(city.getCityName());
            				if(!StringUtils.isEmpty(jpoMemberOrder.getSysUser().getJmiMember().getDistrict())){
                				List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
                				for (AlDistrict district : alDistricts) {
                					if(district.getDistrictId().toString().equals(jpoMemberOrder.getSysUser().getJmiMember().getDistrict())){
                						jpoMemberOrder.getSysUser().getJmiMember().setDistrict(district.getDistrictName());
                						break;
                					}
                				}
            				}
            				break;
            			}
            		}
            		
            		
        		}
        	}
        	if(!"2".equals(jpoMemberOrder.getStatus())){
        		return null;
        	}
        	if("C".equals(loginSysUser.getUserType())){
    			if("PH".equals(loginSysUser.getCompanyCode())){
    				if(jpoMemberOrder.getCompanyCode().equals(loginSysUser.getCompanyCode())){
    					return new ModelAndView("po/jpoMemberOrderPHInvoice", "jpoMemberOrder", jpoMemberOrder);
    				}
    			}else{
    				return new ModelAndView("po/jpoMemberOrderPHInvoice", "jpoMemberOrder", jpoMemberOrder);
    			}
    		}
        }
		return null;
    }
}
