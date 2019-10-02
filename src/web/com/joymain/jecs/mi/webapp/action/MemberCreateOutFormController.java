package com.joymain.jecs.mi.webapp.action;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCharacterCodingManager;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiLinkRefManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;



public class MemberCreateOutFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;
    private AlCountryManager alCountryManager;
    private AlCharacterCodingManager alCharacterCodingManager;
    public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	public MemberCreateOutFormController() {
        setCommandName("jmiMember");
        setCommandClass(JmiMember.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {

    	JmiMember jmiMember = new JmiMember();
    	
//    	String countryCode="";
    	//找出可用银行
//        List sysBankList=sysBankManager.getSysBankByCompanyCode(defSysUser.getCompanyCode());
//        request.setAttribute("sysBankList", sysBankList);
        
        
//    	JmiMember tmpMember = jmiMemberManager.get(defSysUser.getUserCode());
//    	
//    	jmiMember.setCountryCode(tmpMember.getCountryCode());
//    	countryCode=tmpMember.getCountryCode();
//    	request.setAttribute("defCompanyCode", defSysUser.getCompanyCode());

//        JalCountry alCountry = new JalCountry();//获取地区
//    	alCountry = alCountryManager.getJalCountryByCode(countryCode);
//    	alCountry.getJalStateProvinces().iterator();
//    	request.setAttribute("alStateProvinces", alCountry.getJalStateProvinces());
    	
		JmiLinkRef miLinkRef = new JmiLinkRef();
    	miLinkRef.setLinkJmiMember(new JmiMember());
    	
    	JmiRecommendRef miRecommendRef = new JmiRecommendRef();
    	miRecommendRef.setRecommendJmiMember(new JmiMember());
    	
    	jmiMember.setJmiRecommendRef(miRecommendRef);
    	jmiMember.setJmiLinkRef(miLinkRef);
    	
        jmiMember.setSysUser(new SysUser());

		//切换语言
		String lang = request.getParameter("lang");
		if (!StringUtils.isEmpty(lang)) {
			SessionLogin.getLoginUser(request).setDefCharacterCoding(lang);
		}
		
		//获取系统可用的语言
		List alCharacterCodings = this.alCharacterCodingManager.getAlCharacterCodings(null);
		request.setAttribute("alCharacterCodings", alCharacterCodings);
		
    	
    	//台湾预设省
//    	
//    	if("TW".equals(defSysUser.getCompanyCode())){
    		request.setAttribute("provinceView", "none");
//    	}else{
//    		request.setAttribute("provinceView", "");
//    	}
    	//tw.province
        return jmiMember;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        //JsysUser defSysUser=SessionLogin.getLoginUser(request);
    	//JsysUser defSysUser = (JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
        JmiMember jmiMember = (JmiMember) command;    

        
        String defCharacterCoding=getDefCharacterCoding(request);
        
        if(StringUtil.isEmpty(jmiMember.getCompanyCode())|| (!"HK".equals(jmiMember.getCompanyCode()) && !"ID".equals(jmiMember.getCompanyCode()))){
			errors.rejectValue("companyCode", "isNotNull",new Object[] { this.getText("bdReconsumMoneyReport.company") }, "");
    		return showForm(request, response, errors);
        }
        
		//修改密码
    	String password1 =jmiMember.getSysUser().getPassword();
        String password2 =jmiMember.getSysUser().getPassword2();
        
		String password1Confirm=request.getParameter("password1Confirm");
		String password2Confirm=request.getParameter("password2Confirm");
    	
		if(!StringUtil.isEmpty(password1)){
			if(!password1.equals(password1Confirm)){
				errors.reject("error.password.not.accord");
				return showForm(request, response, errors);
			}	
		}
		if(!StringUtil.isEmpty(password2)){
			if(!password2.equals(password2Confirm)){
				errors.reject("miMember.pwd2.isNotEqual");
				return showForm(request, response, errors);
			}

		}

    	if(jmiMemberManager.getCheckMiMember(request, jmiMember, errors, defCharacterCoding, "1")){
    		return showForm(request, response, errors);
    	}  
    	
    	
    	/*发货信息*/
//    	String shippingFirstName=request.getParameter("shippingFirstName");
//    	String shippingLastName=request.getParameter("shippingLastName");
//    	String shippingProvince=request.getParameter("shippingProvince");
//    	String shippingCity=request.getParameter("shippingCity");
//    	String shippingAddress=request.getParameter("shippingAddress");
//    	String shippingPostalcode=request.getParameter("shippingPostalcode");
//    	String shippingPhone=request.getParameter("shippingPhone");
//    	String shippingEmail=request.getParameter("shippingEmail");
//    	String shippingMobiletele=request.getParameter("shippingMobiletele");
//    	String shippingDistrict=request.getParameter("shippingDistrict");
//    	
//    	JmiAddrBook jmiAddrBook=new JmiAddrBook();
//    	jmiAddrBook.setFirstName(shippingFirstName);
//    	jmiAddrBook.setLastName(shippingLastName);
//    	jmiAddrBook.setProvince(shippingProvince);
//    	jmiAddrBook.setCity(shippingCity);
//    	jmiAddrBook.setAddress(shippingAddress);
//    	jmiAddrBook.setPostalcode(shippingPostalcode);
//    	jmiAddrBook.setPhone(shippingPhone);
//    	jmiAddrBook.setEmail(shippingEmail);
//    	jmiAddrBook.setMobiletele(shippingMobiletele);
//    	jmiAddrBook.setDistrict(shippingDistrict);
//    	jmiMember.getJmiAddrBooks().add(jmiAddrBook);
//    	jmiAddrBook.setJmiMember(jmiMember);
    	
    	
    	
    	//推荐人不存在
		if(!StringUtil.isEmpty(jmiMemberManager.getCheckRecommendNo(errors, jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode(), "",defCharacterCoding))){
    		return showForm(request, response, errors);
		}
		// 判断接点人是否存在
		if(!StringUtil.isEmpty(jmiMemberManager.getCheckLinkNo(errors, jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode(), jmiMember.getJmiLinkRef().getLinkJmiMember().getUserCode(), "",defCharacterCoding))){
			return showForm(request, response, errors);
		}

		AlCountry jalCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(jmiMember.getCompanyCode()).get(0);
		jmiMember.setCountryCode(jalCountry.getCountryCode());
		
		jmiMember.getSysUser().setPassword(StringUtil.encodePassword(password1, "md5"));
		jmiMember.getSysUser().setPassword2(StringUtil.encodePassword(password1, "md5"));
		//设置汇款银行
		SysUser defSysUser=new SysUser();
		defSysUser.setCompanyCode(jmiMember.getCompanyCode());
		defSysUser.setDefCharacterCoding(defCharacterCoding);
		
    	try {
			String userCode=jmiMemberManager.saveNewJmiMember(jmiMember, defSysUser,request);
			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
			request.setAttribute("resJmiMember", jmiMember);
			return new ModelAndView("mi/miMemberCreatedOutList");
		} catch (Exception e) {
			e.printStackTrace();
			this.saveMessage(request, LocaleUtil.getLocalText(e.getMessage()));
		}

        

		return showForm(request, response, errors);
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    private String getDefCharacterCoding(HttpServletRequest request){

    	Cookie[] cookies=request.getCookies();
    	String defCharacterCoding="";
    	if(cookies!=null){
        	for (Cookie cookie : cookies) {
        		if("locale".equals(cookie.getName())){
        			defCharacterCoding=cookie.getValue();
        		}
    			
    		}
    	}
    	if(StringUtil.isEmpty(defCharacterCoding)){
    		defCharacterCoding=request.getLocale().toString();
    	}
    	defCharacterCoding.replaceFirst("-", "_");
    	if("en".equals(defCharacterCoding)){
    		defCharacterCoding+="_US";
    	}else{
    		defCharacterCoding=defCharacterCoding.replaceAll("-", "_");
    		int ii=defCharacterCoding.indexOf("_");
    		if(ii>0){
    			String sub=defCharacterCoding.substring(ii+1);
    			defCharacterCoding=defCharacterCoding.replaceAll(sub, sub.toUpperCase());
    		}
    	}
    	return defCharacterCoding;
    }

	public void setAlCharacterCodingManager(
			AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}
    

}
