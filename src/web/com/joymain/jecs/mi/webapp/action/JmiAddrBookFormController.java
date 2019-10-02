package com.joymain.jecs.mi.webapp.action;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.mi.service.JmiMemberManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiAddrBookFormController extends BaseFormController {
    private JmiAddrBookManager jmiAddrBookManager = null;
    private JmiMemberManager jmiMemberManager = null;
    private AlCountryManager alCountryManager;
    public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }

    public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager) {
        this.jmiAddrBookManager = jmiAddrBookManager;
    }
    public JmiAddrBookFormController() {
        setCommandName("jmiAddrBook");
        setCommandClass(JmiAddrBook.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
    	SysUser defSysUser=SessionLogin.getLoginUser(request);

    	String countryCode=null;
    	
    	if("M".equals(defSysUser.getUserType())){
        	JmiMember jmiMember=jmiMemberManager.getJmiMember(defSysUser.getUserCode());
        	countryCode=jmiMember.getCompanyCode();
        }else{
        	countryCode=defSysUser.getCompanyCode();
        	
        }
    	
        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode(countryCode);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	
    	
        String fabId = request.getParameter("fabId");
        JmiAddrBook jmiAddrBook = null;

        if (!StringUtils.isEmpty(fabId)) {
            jmiAddrBook = jmiAddrBookManager.getJmiAddrBook(fabId);
        } else {
        	
            jmiAddrBook = new JmiAddrBook();
            jmiAddrBook.setJmiMember(new JmiMember());
        }
        
    	if("TW".equals(defSysUser.getCompanyCode())){
//    		台湾预设省
        	String twProvince=ConfigUtil.getConfigValue(defSysUser.getCompanyCode().toUpperCase(), "tw.province");
        	jmiAddrBook.setProvince(twProvince);
    	}
    	
        return jmiAddrBook;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        JmiAddrBook jmiAddrBook = (JmiAddrBook) command;

        String userCode=null;
        if("M".equals(defSysUser.getUserType())){
        	userCode=defSysUser.getUserCode();
        }else{
        	userCode=jmiAddrBook.getJmiMember().getUserCode();
        }
    	JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);
    	if(null==jmiMember){

			errors.reject("miMember.notFound");
    		return showForm(request, response, errors);
    	}
    	jmiAddrBook.setJmiMember(jmiMember);
        
        

		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteJmiAddrBook".equals(strAction)  ) {
			jmiAddrBookManager.removeJmiAddrBook(jmiAddrBook.getFabId().toString());
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		}else{
			
			if(checkJmiAddrBook(jmiAddrBook, errors,defSysUser.getCompanyCode())){
	    		return showForm(request, response, errors);
			}
	    	
	    	
			
			jmiAddrBookManager.saveJmiAddrBook(jmiAddrBook);
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
		}
   
			this.saveMessage(request, LocaleUtil.getLocalText(key));

        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
    private boolean checkJmiAddrBook(JmiAddrBook jmiAddrBook,BindException errors,String companyCode){
    	boolean isNotPass=false;
    	if (StringUtil.isEmpty(jmiAddrBook.getFirstName())) {
			errors.reject("shippingFirstName.isNotNull");
			isNotPass = true;
    	}
    	if (StringUtils.isEmpty(jmiAddrBook.getLastName())) {
			errors.reject("shippingLastName.isNotNull");
			isNotPass = true;
    	}
    	if (StringUtils.isEmpty(jmiAddrBook.getProvince())) {
    		String provinceCharacter="";
    		if("PH".equals(companyCode)){
    			provinceCharacter="shippingIsland.isNotNull";
    		}else{
    			provinceCharacter="shippingProvince.isNotNull";
    		}
			errors.reject(provinceCharacter);
			isNotPass = true;
    	}
    	if (StringUtils.isEmpty(jmiAddrBook.getCity()) && !"RU".equals(companyCode)) {
    		String cityCharacter="";
    		if("PH".equals(companyCode)){
    			cityCharacter="shippingRegion.isNotNulll";
    		}else{
    			cityCharacter="shippingCity.isNotNull";
    		}
			errors.reject(cityCharacter);
			isNotPass = true;
    	}
    	//收货区不能为空
    	if (StringUtils.isEmpty(jmiAddrBook.getDistrict())) {
    	    String cityCharacter="";
            if("PH".equals(companyCode)){
                cityCharacter="shippingProvince.isNotNull";
            }else{
                cityCharacter="shippingRegion.isNotNull";
            }
            errors.reject(cityCharacter);
            isNotPass = true;
        }
    	if("PH".equals(companyCode)){

//        	if (StringUtils.isEmpty(jmiAddrBook.getDistrict())) {
//    			errors.reject("shippingProvince.isNotNull");
//    			isNotPass = true;
//        	}
        	if (StringUtils.isEmpty(jmiAddrBook.getTown())) {
    			errors.reject("shippingCity.isNotNull");
    			isNotPass = true;
        	}
        	if (StringUtils.isEmpty(jmiAddrBook.getEmail())) {
        		errors.rejectValue("email", "isNotNull",new Object[] { this.getText("miMember.email")}, "");
    			isNotPass = true;
        	}else{
		    	Pattern pattern = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
	    		Matcher matcher = pattern.matcher(jmiAddrBook.getEmail());
	    		if(!matcher.matches()){
	    			errors.rejectValue("email", "errors.email",new Object[] {this.getText("miMember.email") }, "");
	    			isNotPass = true;
	    		}
        	}
        	
    	}
    	
    	if (!StringUtils.isEmpty(jmiAddrBook.getEmail())) {
    		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
    		Matcher matcher = pattern.matcher(jmiAddrBook.getEmail());
    		if(!matcher.matches()){
    			errors.rejectValue("email", "errors.email",new Object[] {this.getText("miMember.email") }, "");
    			isNotPass = true;
    		}
    	}
    	
    	
    	if (StringUtils.isEmpty(jmiAddrBook.getAddress())) {
			errors.reject("shippingAddress.isNotNull");
			isNotPass = true;
    	}
    	if("JP".equals(companyCode)){
//    		if (StringUtils.isEmpty(jmiAddrBook.getBuilding())) {
//    			errors.rejectValue("building", "isNotNull",new Object[] {this.getText("miMember.building") }, "");
//    			isNotPass = true;
//    		}
    		
        	if (!StringUtils.isEmpty(jmiAddrBook.getPhone())) {
//    			errors.reject("shippingPhone.isNotNull");
//    			isNotPass = true;
//        	}else {
     				boolean phoneMatcher=false;
    	    		Pattern pattern = Pattern.compile("^([0-9]{2})[-]([0-9]{4})[-]([0-9]{4})$");
    	    		Matcher matcher = pattern.matcher(jmiAddrBook.getPhone());
    	    		if(!matcher.find()){
    	    			phoneMatcher=false;
    		    	}else{
    		    		phoneMatcher=true;
    		    	}
    	    		if(!phoneMatcher){
    	    			pattern = Pattern.compile("^([0-9]{4})[-]([0-9]{2})[-]([0-9]{4})$");
    	    			matcher = pattern.matcher(jmiAddrBook.getPhone());
    	    			if(!matcher.find()){
    		    			phoneMatcher=false;
    			    	}else{
    			    		phoneMatcher=true;
    			    	}
    	    		}
    	    		if(!phoneMatcher){
    	    			pattern = Pattern.compile("^([0-9]{3})[-]([0-9]{3})[-]([0-9]{4})$");
    	    			matcher = pattern.matcher(jmiAddrBook.getPhone());
    	    			if(!matcher.find()){
    		    			phoneMatcher=false;
    			    	}else{
    			    		phoneMatcher=true;
    			    	}
    	    		}
    	    		if(!phoneMatcher){
    					errors.rejectValue("phone", "errors.invalid",new Object[] { this.getText( "miMember.phone") }, "");
    					isNotPass = true;
    	    		}
        	}
    		
    		
    	}
    	if (StringUtils.isEmpty(jmiAddrBook.getPostalcode())) {
			errors.reject("shippingPostalcode.isNotNull");
			isNotPass = true;
    	}else if("JP".equals(companyCode)){
    		if(!StringUtils.isEmpty(jmiAddrBook.getPostalcode())&&jmiAddrBook.getPostalcode().length()>10){
        		errors.reject("member.postalcode.too.long");
    			isNotPass = true;
        	}else{
        		Pattern pattern  = Pattern.compile("^([0-9]{3})[-]([0-9]{4})$");
        		Matcher matcher = pattern.matcher(jmiAddrBook.getPostalcode());
        		if(!matcher.find()){
    				errors.rejectValue("postalcode", "errors.invalid",new Object[] { this.getText( "miMember.postalcode") }, "");
    				isNotPass = true;
    	    	}
        	}
    	}else{
    		if(!StringUtils.isEmpty(jmiAddrBook.getPostalcode())&&jmiAddrBook.getPostalcode().length()>8){
        		errors.reject("member.postalcode.too.long");
    			isNotPass = true;
        	}
    	}

    		
        	
    		
//    	if (StringUtils.isEmpty(jmiAddrBook.getEmail())) {
//			errors.reject("shippingEmail.isNotNull");
//			isNotPass = true;
//    	}else if(!StringUtil.isEmail(jmiAddrBook.getEmail())){
//			errors.reject("error.email");
//			isNotPass = true;
//    	}
    	
    	if (StringUtils.isEmpty(jmiAddrBook.getMobiletele())) {
			errors.reject("shippingMobiletele.isNotNull");
			isNotPass = true;
    	}else if("CN".equals(jmiAddrBook.getJmiMember().getCompanyCode())&&(StringUtil.isInteger(jmiAddrBook.getMobiletele())||jmiAddrBook.getMobiletele().length()!=11)){
			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
			isNotPass = true;
    	}else if("TW".equals(jmiAddrBook.getJmiMember().getCompanyCode())){
    		Pattern pattern = Pattern.compile("^[0][9]([0-9]{8})$");
    		Matcher matcher = pattern.matcher(jmiAddrBook.getMobiletele());
    		if(!matcher.find()){
    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
    			isNotPass = true;
    		}
    	}else if("PH".equals(jmiAddrBook.getJmiMember().getCompanyCode())){
    		Pattern pattern = Pattern.compile("^[0][9]([0-9]{9})$");
    		Matcher matcher = pattern.matcher(jmiAddrBook.getMobiletele());
    		if(!matcher.find()){
    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
    			isNotPass = true;
    		}
    	}else if("US".equals(jmiAddrBook.getJmiMember().getCompanyCode())){
    		Pattern pattern = Pattern.compile("^[0-9]{10}$");
    		Matcher matcher = pattern.matcher(jmiAddrBook.getMobiletele());
    		if(!matcher.find()){
    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
    			isNotPass = true;
    		}
    	}else if("JP".equals(companyCode)){
//    	 	if (!StringUtils.isEmpty(jmiAddrBook.getMobiletele())) {
//    			errors.rejectValue("mobiletele", "isNotNull",new Object[] { this.getText( "miMember.mobiletele") }, "");
//    			isNotPass = true;
//        	}else{
        		Pattern pattern = Pattern.compile("^([0-9]{3})[-]([0-9]{4})[-]([0-9]{4})$");
        		Matcher matcher = pattern.matcher(jmiAddrBook.getMobiletele());
        		if(!matcher.find()){
        			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText( "miMember.mobiletele") }, "");
        			isNotPass = true;
        		}
//        	}
    	}
    	
    	if("US".equals(jmiAddrBook.getJmiMember().getCompanyCode())&&!StringUtil.isEmpty(jmiAddrBook.getPhone())){
    		Pattern pattern = Pattern.compile("^[0-9]{10}$");
    		Matcher matcher = pattern.matcher(jmiAddrBook.getMobiletele());
    		if(!matcher.find()){
    			errors.rejectValue("phone", "errors.phone",new Object[] {this.getText("miMember.phone") }, "");
    			isNotPass = true;
    		}
    	}
    	
    	
    	return isNotPass;
    }
}
