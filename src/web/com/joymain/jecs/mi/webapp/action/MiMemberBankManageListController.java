package com.joymain.jecs.mi.webapp.action;

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
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class MiMemberBankManageListController implements Controller {
    private final Log log = LogFactory.getLog(MiMemberBankManageListController.class);
    private JmiMemberManager jmiMemberManager = null;

    private AlCountryManager alCountryManager;
    private AlCityManager alCityManager;
	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }


    	String companyCode = "";
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		if ("C".equals(defSysUser.getUserType())) {
			companyCode = defSysUser.getCompanyCode();
			if ("AA".equals(defSysUser.getCompanyCode())) {
				companyCode = null;
			}
		}
		

        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode(companyCode);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
		
		
    	//=========================================
    	CommonRecord crm=RequestUtil.toCommonRecord(request);
        crm.addField("companyCode", companyCode);
       
        Pager pager = new Pager("miMemberManageTable",request, 20);
    	String userCode = request.getParameter("userCode");
    	String firstName= request.getParameter("firstName");
    	String lastName= request.getParameter("lastName");
    	String bankcard= request.getParameter("bankcard");
    	List<JmiMember> miMemberManageList=null;
    	if (!StringUtil.isEmpty(bankcard)||!StringUtil.isEmpty(userCode)||!StringUtil.isEmpty(firstName)||!StringUtil.isEmpty(lastName)){
    		miMemberManageList=jmiMemberManager.getJmiMembersByCrm(crm, pager);
    		for (JmiMember member : miMemberManageList) {
    			List<AlCity> citys=alCityManager.getAlCityByProvinceId(new Long(StringUtils.defaultString(member.getBankProvince(),"0")));
    			for (AlCity city : citys) {
    				if(city.getCityId().toString().equals(member.getBankCity())){
    					member.setBankCity(city.getCityName());
    					break;
    				}
    			}
			}
    	}
    	
		request.setAttribute("miMemberManageTable_totalRows", pager.getTotalObjects());
    	return new ModelAndView("mi/miMemberBankManageList","miMemberManageList",miMemberManageList);
    }
}
