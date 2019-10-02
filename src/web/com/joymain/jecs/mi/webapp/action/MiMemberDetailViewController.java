package com.joymain.jecs.mi.webapp.action;



import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
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
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;



public class MiMemberDetailViewController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(MiMemberDetailViewController.class);
    private AlCompanyManager alCompanyManager = null;
    private JmiMemberManager jmiMemberManager;
    private AlCountryManager alCountryManager = null;
    private AlStateProvinceManager alStateProvinceManager = null;
	private JdbcTemplate jdbcTemplate = null;
	private JpoMemberOrderManager jpoMemberOrderManager;
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
        	
        	
    		String checkTime = jpoMemberOrderManager.getMemberFirstOrderStatusTime(miMember.getUserCode());
    		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    		Date checkDate= null;
        	try {
        		checkDate = format1.parse(checkTime);
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
        	int days=DateUtil.daysBetweenDates( DateUtil.getDateOffset(checkDate, 5, StringUtil.formatInt(num)),new Date());
        	if(new Date().after(DateUtil.getDateOffset(checkDate, 5, StringUtil.formatInt(num)))){
        		days=-1;
        	}
        	if(days>=0){
        		request.setAttribute("days", days);
        	}else{
        		request.setAttribute("days", 0);
        	}
        	//查出原会员的PV
        	if("2".equals(miMember.getMemberType())&&(StringUtil.isEmpty(miMember.getOriCard())||null==miMember.getOriPv())){
        		String sql="select m.* from jmi_comparison_m t, jmi_comparison_m_temp_20100210 c, jmi_member m where t.user_code = c.old_user_code and c.status='2'" +
        				"and m.user_code = t.member_no and c.new_user_code = '"+memberNo+"'";
        		try {

            		List miMembers = this.jdbcTemplate.queryForList(sql);
    				if(miMembers.size()>0){
    					Map map=(Map) miMembers.get(0);
    					miMember.setOriPv(new BigDecimal(map.get("ori_pv").toString()));
    					miMember.setOriCard(map.get("ori_card").toString());
    				}
				} catch (Exception e) {
					
				}
        	}
        	//
        	if(miMember.getMemberType()!=null){
            	miMember.setMemberType(miMember.getMemberType().trim());
        	}
    	}
    	
        return new ModelAndView("mi/miMemberDetailView");
    
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
