package com.joymain.jecs.po.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.po.service.JpoShippingFeeManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 物流费列表
 * @author Alvin
 *
 */
public class JpoShippingFeeController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoShippingFeeController.class);
    private JpoShippingFeeManager jpoShippingFeeManager = null;
    private AlCountryManager alCountryManager = null;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;

    public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setJpoShippingFeeManager(JpoShippingFeeManager jpoShippingFeeManager) {
        this.jpoShippingFeeManager = jpoShippingFeeManager;
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
        
        CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("countryCode", alCountry.getCountryCode());
        Pager pager = new Pager("jpoShippingFeeListTable",request, 20);
        List<JpoShippingFee> jpoShippingFees = new ArrayList<JpoShippingFee>();
        if(!StringUtils.isEmpty(crm.getString("search",""))){
        	jpoShippingFees = jpoShippingFeeManager.getJpoShippingFeesByCrm(crm,pager);
        }

        for (JpoShippingFee jpoShippingFee : jpoShippingFees) {
        	if(jpoShippingFee.getProvince()!=null){
        		List<AlCity> citys=alCityManager.getAlCityByProvinceId(Long.parseLong(jpoShippingFee.getProvince()));
    			for (AlCity city : citys) {
    				if(city.getCityId().toString().equals(jpoShippingFee.getCity())){
    					jpoShippingFee.setCity(city.getCityName());
    					List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
    					for (AlDistrict district : alDistricts) {
    						if(district.getDistrictId().toString().equals(jpoShippingFee.getDistrict())){
    							jpoShippingFee.setDistrict(district.getDistrictName());
    							break;
    						}
    					}
    					break;
    				}
    			}
        	}
		}
        
        request.setAttribute("jpoShippingFeeListTable_totalRows", pager.getTotalObjects());

        return new ModelAndView("po/jpoShippingFeeList", Constants.JPOSHIPPINGFEE_LIST, jpoShippingFees);
    }
}
