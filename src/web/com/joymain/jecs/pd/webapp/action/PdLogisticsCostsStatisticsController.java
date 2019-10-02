package com.joymain.jecs.pd.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;


/**
 * 订单统计
 * @author Alvin
 *
 */
public class PdLogisticsCostsStatisticsController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(PdLogisticsCostsStatisticsController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
//    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private AlCountryManager alCountryManager;
    private JalTownManager jalTownManager;

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
        super.initStateCodeParem(request);
        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
        
        if(request.getParameter("search")==null){
            Pager pager = new Pager("jpoMemberOrderListTable",request, 20);
            request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
            return new ModelAndView("pd/pdLogisticsCostsStatistics", Constants.JPOMEMBERORDER_LIST, null);
        }

        CommonRecord crm = RequestUtil.toCommonRecord(request);
    	crm.addField("companyCode", loginSysUser.getCompanyCode());
    	if(loginSysUser.getIsMember()){
    		crm.addField("sysUser.userCode",loginSysUser.getUserCode());
    	}
        
    	
    	
        Pager pager = new Pager("jpoMemberOrderListTable",request, 0);
      
        List jpoMemberOrderFee = jpoMemberOrderManager.getShippingfeeByCrm(crm,pager);
 
       Map sumMap = jpoMemberOrderManager.getTotalShippingfeeByCrm(crm);
        request.setAttribute("sumMap", sumMap);

        request.setAttribute("jpoMemberOrderListTable_totalRows", pager.getTotalObjects());
        
        return new ModelAndView("pd/pdLogisticsCostsStatistics","jpoMemberOrderFeeList",jpoMemberOrderFee);
    }

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}



	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setJalTownManager(JalTownManager jalTownManager) {
		this.jalTownManager = jalTownManager;
	}
}
