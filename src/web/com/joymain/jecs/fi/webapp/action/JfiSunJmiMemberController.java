package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.fi.model.JfiSunJmiMember;
import com.joymain.jecs.fi.service.JfiSunJmiMemberManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.webapp.action.BaseController;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JfiSunJmiMemberController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiSunJmiMemberController.class);
    private JfiSunJmiMemberManager jfiSunJmiMemberManager = null;
    private AlCountryManager alCountryManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private BdPeriodManager bdPeriodManager;
    
    public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setJfiSunJmiMemberManager(JfiSunJmiMemberManager jfiSunJmiMemberManager) {
        this.jfiSunJmiMemberManager = jfiSunJmiMemberManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

    	CommonRecord crm=RequestUtil.toCommonRecord(request);
        Pager pager = new Pager("jfiSunJmiMemberListTable",request, 20);
        String villageAddr=request.getParameter("villageAddr");
        

        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode("CN");
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        
        request.setAttribute("period", bdPeriodManager.getBdPeriodByTimeFormated(new Date(), null));
        List<JfiSunJmiMember> jfiSunJmiMembers =null;
        if(!StringUtil.isEmpty(villageAddr)){
        	jfiSunJmiMembers = jfiSunJmiMemberManager.getJfiSunJmiMembersByCrm(crm,pager);
        	
        	for (JfiSunJmiMember jfiSunJmiMember : jfiSunJmiMembers) {
    			List<AlCity> citys=alCityManager.getAlCityByProvinceId(new Long(jfiSunJmiMember.getProvince()));
    			for (AlCity city : citys) {
    				if(city.getCityId().toString().equals(jfiSunJmiMember.getCity())){
    					jfiSunJmiMember.setCity(city.getCityName());
    					List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(new Long(city.getCityId()));
    					for (AlDistrict district : alDistricts) {
    						if(district.getDistrictId().toString().equals(jfiSunJmiMember.getDistrict())){
    							jfiSunJmiMember.setDistrict(district.getDistrictName());
    							break;
    						}
    					}
    					break;
    				}
    			}
           	
           	
            	
    		}
            
            
        	
        	
        }
        
        request.setAttribute("jfiSunJmiMemberListTable_totalRows", pager.getTotalObjects());
        /*****/

        return new ModelAndView("fi/jfiSunJmiMemberList", Constants.JFISUNJMIMEMBER_LIST, jfiSunJmiMembers);
    }
}
