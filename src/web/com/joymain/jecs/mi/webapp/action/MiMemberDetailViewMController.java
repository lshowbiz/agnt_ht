package com.joymain.jecs.mi.webapp.action;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;



public class MiMemberDetailViewMController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(MiMemberDetailViewMController.class);
    private AlCompanyManager alCompanyManager = null;
    private JmiMemberManager jmiMemberManager;
    private AlCountryManager alCountryManager = null;
    private AlStateProvinceManager alStateProvinceManager = null;


    private JmiLinkRefManager jmiLinkRefManager = null;


	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
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
        String memberNo=request.getParameter("memberNo");
    	JmiMember miMember=jmiMemberManager.getJmiMember(memberNo);
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	if("AA".equals(defSysUser.getCompanyCode())){
        	List alStateProvinces = alStateProvinceManager.getAlStateProvinces(new AlStateProvince());
        	request.setAttribute("alStateProvinces", alStateProvinces);
        	List alCountrys = alCountryManager.getAlCountrys(new AlCountry());
        	request.setAttribute("alCountry",alCountrys );
    	}else{
    		AlCompany alCompany = alCompanyManager.getAlCompanyByCode(defSysUser.getCompanyCode());
            AlCountry alCountry = new AlCountry();
        	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
        	alCountry.getAlStateProvinces().iterator();
        	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	}
    	if("C".equals(defSysUser.getUserType())&&!miMember.getCompanyCode().equals(defSysUser.getCompanyCode())&&!"AA".equals(defSysUser.getCompanyCode())){
    		miMember=null;
    	}
    	request.setAttribute("jmiMember", miMember);
    	
    	//
    	if(miMember!=null){
        	String num=(String) Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("member_upgrade_day");
        	Date checkDate=miMember.getCheckDate()==null?miMember.getCreateTime():miMember.getCheckDate();
        	int days=DateUtil.daysBetweenDates( DateUtil.getDateOffset(checkDate, 5, StringUtil.formatInt(num)),new Date());
        	if(new Date().after(DateUtil.getDateOffset(checkDate, 5, StringUtil.formatInt(num)))){
        		days=-1;
        	}
        	if(days>=0){
        		request.setAttribute("days", days);
        	}else{
        		request.setAttribute("days", 0);
        	}
    	}
    	if(!jmiLinkRefManager.getJmiLinkRefIsM(memberNo)){
    		miMember=null;
    	}
        return new ModelAndView("mi/miMemberDetailViewM");
    
    }


	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}


	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}


	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}


}
