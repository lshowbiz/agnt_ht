package com.joymain.jecs.po.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.al.dao.AlCityDao;
import com.joymain.jecs.al.dao.AlDistrictDao;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 订单统计
 * @author Alvin
 *
 */
public class JpoMemberOrderStatisticController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JpoMemberOrderStatisticController.class);
    private JpoMemberOrderManager jpoMemberOrderManagerReport = null;
    
    private JmiMemberTeamManager jmiMemberTeamManager;
    private AlCountryManager alCountryManager;
    private AlCityDao alCityDao;
    private AlDistrictDao alDistrictDao;
    
    public void setJpoMemberOrderManagerReport(
			JpoMemberOrderManager jpoMemberOrderManagerReport) {
		this.jpoMemberOrderManagerReport = jpoMemberOrderManagerReport;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        
    	if("C".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberOrderStatistic",3l);
    	}else if("M".equals(loginSysUser.getUserType())){
            RequestUtil.freshSession(request,"poMemberOrderStatistic",3l);
    	}

        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.
        		getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        
    	String cityId = request.getParameter("city");
    	if(StringUtils.isNotBlank(cityId)){
    		AlCity alcity = alCityDao.
    				getAlCity(new Long(cityId));
    		request.setAttribute("alcity", alcity);
    	}
    	
    	String districtId = request.getParameter("district");
    	if(StringUtils.isNotBlank(districtId)){
    		AlDistrict aldistrict = alDistrictDao.
    				getAlDistrict(new Long(districtId));
    		request.setAttribute("aldistrict", aldistrict);
    	}
    	
    	if(request.getParameter("search")==null){
            Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
            request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("po/jpoMemberOrderStatistic", Constants.JPOMEMBERORDER_LIST, null);
        }

        CommonRecord crm = RequestUtil.toCommonRecord(request);
        String isMobile = request.getParameter("isMobile");
        
        crm.addField("ismobile",isMobile);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	if(loginSysUser.getIsMember()){
    		crm.addField("sysUser.userCode",loginSysUser.getUserCode());
    	}
    	if("C".equals(loginSysUser.getUserType())){
    		if("xls".equals(request.getParameter("jpoMemberOrderListTable_ev"))){
        		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatisticXML",20l)!=0){
        			return new ModelAndView("redirect:jpoMemberOrderStatistic.html");
        		}
    		}else{
        		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatistic",3l)!=0){
        			return new ModelAndView("redirect:jpoMemberOrderStatistic.html");
        		}
    		}
    	}else if("M".equals(loginSysUser.getUserType())){
    		if(RequestUtil.saveOperationSession(request,"poMemberOrderStatistic",3l)!=0){
    			return new ModelAndView("redirect:jpoMemberOrderStatistic.html");
    		}
    	}
    	
        Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
        List jpoMemberOrders = jpoMemberOrderManagerReport.getJpoMemberOrdersByCrm(crm,pager);
      
        Map sumMap = jpoMemberOrderManagerReport.getSumAmountByCrm(crm);
        request.setAttribute("sumMap", sumMap);
        
        if(jpoMemberOrders.size()>0){
            Map<String,String> mapProvince = new HashMap<String,String>();
            Map<String,String> mapCity = new HashMap<String,String>();
            Map<String,String> mapAlDistrict = new HashMap<String,String>();
            Map<String,String> mapJalTown = new HashMap<String,String>();
            List<AlStateProvince> alStateProvinces = Constants.alStateProvinceList;
            for (AlStateProvince alStateProvince : alStateProvinces) {
        		mapProvince.put(alStateProvince.getStateProvinceId().toString(), alStateProvince.getStateProvinceName());
        	}
            List<AlCity> citys = Constants.alCityList;
            for (AlCity city : citys) {
            	mapCity.put(city.getCityId().toString(), city.getCityName());
            }
    		List<AlDistrict> alDistricts = Constants.alDistrictList;
    		for (AlDistrict district : alDistricts) {
    			mapAlDistrict.put(district.getDistrictId().toString(), district.getDistrictName());
    		}
    		List<JalTown> alTowns = Constants.jalTownList;
    		for (JalTown town : alTowns) {
    			mapJalTown.put(town.getTownId().toString(), town.getTownName());
    		}
            for(int i =0; i < jpoMemberOrders.size();i++){
            	JpoMemberOrder jpoMemberOrder = (JpoMemberOrder)jpoMemberOrders.get(i);
            	jpoMemberOrder.setProvince(mapProvince.get(jpoMemberOrder.getProvince()));
            	jpoMemberOrder.setCity(mapCity.get(jpoMemberOrder.getCity()));
            	jpoMemberOrder.setDistrict(mapAlDistrict.get(jpoMemberOrder.getDistrict()));
            	jpoMemberOrder.setTown(mapJalTown.get(jpoMemberOrder.getTown()));
            }
        }
        
        List products = jpoMemberOrderManagerReport.getStatisticProductSale(crm);
     
        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView("po/jpoMemberOrderStatistic","jpoMemberOrderList",jpoMemberOrders).addObject("pmProductList", products);
    }

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public JmiMemberTeamManager getJmiMemberTeamManager() {
		return jmiMemberTeamManager;
	}

	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}

	public JpoMemberOrderManager getJpoMemberOrderManagerReport() {
		return jpoMemberOrderManagerReport;
	}

	public AlCountryManager getAlCountryManager() {
		return alCountryManager;
	}

	public AlCityDao getAlCityDao() {
		return alCityDao;
	}

	public void setAlCityDao(AlCityDao alCityDao) {
		this.alCityDao = alCityDao;
	}

	public AlDistrictDao getAlDistrictDao() {
		return alDistrictDao;
	}

	public void setAlDistrictDao(AlDistrictDao alDistrictDao) {
		this.alDistrictDao = alDistrictDao;
	}
	
}
