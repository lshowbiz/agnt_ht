package com.joymain.jecs.mi.webapp.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysRole;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.model.SysUserRole;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.sys.service.SysRoleManager;
import com.joymain.jecs.sys.service.SysUserRoleManager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberProfileFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;
    private SysBankManager sysBankManager;
	private AlCountryManager alCountryManager = null;
	private AlStateProvinceManager alStateProvinceManager = null;
	private AlCompanyManager alCompanyManager;
	private SysUserRoleManager sysUserRoleManager;
	private SysRoleManager sysRoleManager;
	private JpoMemberOrderManager jpoMemberOrderManager;
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}
	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}
	public void setSysBankManager(SysBankManager sysBankManager) {
		this.sysBankManager = sysBankManager;
	}
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }
    public JmiMemberProfileFormController() {
        setCommandName("jmiMember");
        setCommandClass(JmiMember.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	

    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	
    	//找出可用银行
        List sysBankList=sysBankManager.getSysBankByCompanyCode(defSysUser.getCompanyCode());
        request.setAttribute("sysBankList", sysBankList);
        
 		JmiMember jmiMember = new JmiMember();
 		
 		jmiMember = jmiMemberManager.getJmiMember(defSysUser.getUserCode());
 		if(null==jmiMember){
 			return new JmiMember();
 		}
 		//String userCodeStr=ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "memberno.unlimit.user");

    	boolean unlimitUserFlag=false;
    	Set valueSets = Constants.sysListMap.get("memberno.unlimit.user").entrySet();
    	if (valueSets != null) {
			Iterator iter = valueSets.iterator();
			while (iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				String curUserCode=(String) entry.getKey();
				String loginUserCode=defSysUser.getUserCode();
				if(curUserCode.equals(loginUserCode)){
					unlimitUserFlag=true;
				}
				
			}
		}
		
 		
		if(unlimitUserFlag){
			request.setAttribute("bankViewUnLimit", "bankViewUnLimit");
    	}
		
    	//读取省份
		AlCompany alCompany = alCompanyManager.getAlCompanyByCode(jmiMember.getCompanyCode());
        AlCountry alCountry = new AlCountry();
    	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	
    	
//    	if("2".equals(jmiMember.getMemberType())&&StringUtil.isEmpty(jmiMember.getChangeStatus())){
//        	String mPower=ConfigUtil.getConfigValue(jmiMember.getCompanyCode(), "m.bankcardno.power");
//        	//如果MemberType=2 且mPower =0 0不允许改，1允许改 2为M公司
//        	if("2".equals(jmiMember.getMemberType())&&"0".equals(mPower)){
//        			request.setAttribute("bankcardModify", "true");
//        	}else{
//        		if(!StringUtil.isEmpty(jmiMember.getBankcard())){
//            		request.setAttribute("bankcardModify", "true");
//            	}
//        	}
//    	}else{
    		if(!StringUtil.isEmpty(jmiMember.getBank())){
        		request.setAttribute("bankModify", "true");
        	}
        	if(!StringUtil.isEmpty(jmiMember.getBankaddress())){
        		request.setAttribute("bankaddressModify", "true");
        	}
        	String mPower=ConfigUtil.getConfigValue(jmiMember.getCompanyCode(), "m.bankcardno.power");
        	//如果MemberType=2 且mPower =0 0不允许改，1允许改 2为M公司
        	if("2".equals(jmiMember.getMemberType())&&"0".equals(mPower)){
        			request.setAttribute("bankcardModify", "true");
        	}else{
        		if(!StringUtil.isEmpty(jmiMember.getBankcard())){
            		request.setAttribute("bankcardModify", "true");
            	}
        	}
        	if(!StringUtil.isEmpty(jmiMember.getBankProvince())){
        		request.setAttribute("bankProvinceModify", "true");
            	if(!StringUtil.isEmpty(jmiMember.getBankCity())){
            		request.setAttribute("bankCityModify", "true");
            	}
        	}

        	if(!StringUtil.isEmpty(jmiMember.getPapernumber())){
        		request.setAttribute("papernumberModify", "true");
        	}
//        	if(!StringUtil.isEmpty(jmiMember.getMiUserName())){
//        		request.setAttribute("userNameModify", "true");
//        	}
        	if(!StringUtil.isEmpty(jmiMember.getSex())){
        		request.setAttribute("sexModify", "true");
        	}
//    	}
    	
    	
    	//
    	String num=(String) Constants.sysConfigMap.get(defSysUser.getCompanyCode()).get("member_upgrade_day");
    	if(jmiMember.getCheckDate()==null&&jmiMember.getCreateTime()==null){
    		request.setAttribute("days", 0);
    	}else{
    		String checkTime = jpoMemberOrderManager.getMemberFirstOrderStatusTime(defSysUser.getUserCode());
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
    	}
    	//如果角色为cn.member.6，跳出重登陆 
//    	SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
//		SysRole curSysRole=sysRoleManager.getSysRole(sysUserRole.getRoleId().toString());
//    	if("cn.member.6".equals(curSysRole.getRoleCode())){
//    		request.setAttribute("redirectTager", "redirectTager");
//    	}
    	
    	request.setAttribute("oldBank", jmiMember.getBank());
    	request.setAttribute("oldBankaddress", jmiMember.getBankaddress());
    	request.setAttribute("oldBankbook", jmiMember.getBankbook());
    	request.setAttribute("oldBankcard", jmiMember.getBankcard());

        if(RequestUtil.isMobileRequest(request)){
        	this.setFormView("/mobile/mi/jmiMemberProfileForm");
        }else{
        	this.setFormView("/mi/jmiMemberProfileForm");
        }
        return jmiMember;
    }

    public ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command,BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser = SessionLogin.getLoginUser(request);
        JmiMember jmiMember = (JmiMember) command;

    	if(jmiMemberManager.getCheckMiMember(request, jmiMember, errors, defSysUser.getDefCharacterCoding(), "3")){
    		return showForm(request, response, errors);
    	}  

    	if(!jmiMember.getUserCode().equals(defSysUser.getUserCode())){
    		return showForm(request, response, errors);
    	}
    	
    	SysUserRole sysUserRole= sysUserRoleManager.getSysUserRoleByUserCode(jmiMember.getUserCode());
		SysRole curSysRole=sysRoleManager.getSysRole(sysUserRole.getRoleId().toString());
		
		String oldBank=request.getParameter("oldBank");
		String oldBankaddress=request.getParameter("oldBankaddress");
		String oldBankbook=request.getParameter("oldBankbook");
		String oldBankcard=request.getParameter("oldBankcard");
		
		if( (!StringUtil.isEmpty(oldBank)&&!oldBank.equals(jmiMember.getBank())) || (!StringUtil.isEmpty(oldBankaddress)&&!oldBankaddress.equals(jmiMember.getBankaddress()))|| (!StringUtil.isEmpty(oldBankbook)&&!oldBankbook.equals(jmiMember.getBankbook())) || (!StringUtil.isEmpty(oldBankcard)&&!oldBankcard.equals(jmiMember.getBankcard())) ){
			MessageUtil.saveLocaleMessage(request, "修改错误");
			  return new ModelAndView("redirect:editJmiMemberProfile.html");
		}
		
		
		try {
			jmiMemberManager.saveJmiMemberAndName(jmiMember);
			//joyme会员更新成功，跳出重登陆 
				if(("2".equals(jmiMember.getMemberType())||"3".equals(jmiMember.getMemberType())||"4".equals(jmiMember.getMemberType())||"6".equals(jmiMember.getMemberType()))&&"cn.member.6".equals(curSysRole.getRoleCode())){
		            request.getSession().setAttribute("active_success", this.getText("data.success"));
		            request.getSession().setAttribute("redirectTager", "redirectTager");
		            new ModelAndView("redirect:editJmiMemberProfile.html");
				}
			//
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		} catch (Exception e) {
			e.printStackTrace();
    		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
		}
        return new ModelAndView("redirect:editJmiMemberProfile.html");
    }
    
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
	public void setSysRoleManager(SysRoleManager sysRoleManager) {
		this.sysRoleManager = sysRoleManager;
	}
	public void setSysUserRoleManager(SysUserRoleManager sysUserRoleManager) {
		this.sysUserRoleManager = sysUserRoleManager;
	} 
  
    

}
