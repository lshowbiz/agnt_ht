package com.joymain.jecs.mi.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.joymain.jecs.Constants;
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
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class MemberRegisterController extends AbstractWizardFormController {
	private JmiMemberManager jmiMemberManager = null;
    private SysBankManager sysBankManager;
    private AlCountryManager alCountryManager;
    private JmiRecommendRefManager jmiRecommendRefManager;
    private JmiLinkRefManager jmiLinkRefManager;
    private AlCompanyManager alCompanyManager;
	private AlCharacterCodingManager alCharacterCodingManager = null;
	public void setAlCharacterCodingManager(
			AlCharacterCodingManager alCharacterCodingManager) {
		this.alCharacterCodingManager = alCharacterCodingManager;
	}
	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
		this.jmiLinkRefManager = jmiLinkRefManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJmiRecommendRefManager(
			JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
	}
	public void setSysBankManager(SysBankManager sysBankManager) {
		this.sysBankManager = sysBankManager;
	}
	public MemberRegisterController() {

		setCommandClass(JmiMember.class); // 设置命令类 
		setCommandName("jmiMember");

	}
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		JmiMember jmiMember=new JmiMember();

		JmiLinkRef miLinkRef = new JmiLinkRef();
    	miLinkRef.setLinkJmiMember(new JmiMember());
    	
    	JmiRecommendRef miRecommendRef = new JmiRecommendRef();
    	miRecommendRef.setRecommendJmiMember(new JmiMember());
    	
    	jmiMember.setJmiRecommendRef(miRecommendRef);
    	jmiMember.setJmiLinkRef(miLinkRef);
    	
        jmiMember.setSysUser(new SysUser());
        SessionLogin.getLoginUser(request).setDefCharacterCoding("en_US");
		return jmiMember;
	}
	//处理表单数据
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		System.out.println("submit");
		return null;
	}
	/**

     * 向导中每一页面都要调用的验证方法，用 page 识别当前面

     */


 	//取消向导要执行的方法
 	protected ModelAndView processCancel(HttpServletRequest request,
 			HttpServletResponse response, Object command, BindException errors)
 			throws Exception {
 		response.sendRedirect("login.html");
 		//处理点击取消按扭的事件
 		return null;
 	}
	@Override
	protected boolean isFormSubmission(HttpServletRequest request) {
		//切换语言
		String lang = request.getParameter("lang");
		if (!StringUtils.isEmpty(lang)) {
			SessionLogin.getLoginUser(request).setDefCharacterCoding(lang);
		}
		
		//获取系统可用的语言
		List alCharacterCodings = this.alCharacterCodingManager.getAlCharacterCodings(null);
		request.setAttribute("alCharacterCodings", alCharacterCodings);
		return super.isFormSubmission(request);
	}
	@Override
	protected void postProcessPage(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {

		JmiMember jmiMember=(JmiMember) command;
		
		
		if(jmiMember.getCompanyCode()!=null&&(page==0||page==1||page==2)){     
			AlCountry alCountry = new AlCountry();//获取地区
	    	alCountry = alCountryManager.getAlCountryByCode(jmiMember.getCompanyCode());
	    	alCountry.getAlStateProvinces().iterator();
	    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
	    	
	        List sysBankList=sysBankManager.getSysBankByCompanyCode(jmiMember.getCompanyCode());
	        request.setAttribute("sysBankList", sysBankList);
			
		}
		super.postProcessPage(request, command, errors, page);
	}
	@Override
	protected int getTargetPage(HttpServletRequest request, Object command, Errors errors, int page) {
		return super.getTargetPage(request, command, errors, page);
	}
	@Override
	protected void validatePage(Object command, Errors errors, int page, boolean finish) {
		JmiMember jmiMember=(JmiMember) command;

		 if(page == 0){
	        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyCode", "register.us.select.companyName");
	        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jmiRecommendRef.recommendJmiMember.userCode", "register.us.select.recommendNo");
	        	 
			 if(!StringUtil.isEmpty(jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode())&&null==jmiRecommendRefManager.getJmiRecommendRef(jmiMember.getJmiRecommendRef().getRecommendJmiMember().getUserCode())){
				errors.rejectValue("jmiRecommendRef.recommendJmiMember.userCode", "miRecommendRef.isNotFound");
			 }
         }
         else if(page == 1){
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jmiLinkRef.linkJmiMember.userCode", "linkNo.isNotEmpty");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.firstName")},"miMember.firstName");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.lastName")},"miMember.lastName");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.birthday")},"miMember.birthday");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bank","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.openBank")},"miMember.openBank");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankaddress","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.bcity")},"miMember.bcity");
        	 
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankcard","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.bnum")},"miMember.bnum");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.phone")},"miMember.phone");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobiletele","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.mobiletele")},"miMember.mobiletele");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "province","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.province")},"miMember.province");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("busi.city")},"busi.city");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("busi.address")},"busi.address");
        	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postalcode","isNotNull" ,new Object[]{ LocaleUtil.getLocalText("miMember.postalcode")},"miMember.postalcode");
        	 
        	 if(1==1){
            	 errors.reject("register.submit.error");
        	 }
         }
         else if(page == 2){ 
        	 
         }
         if(finish){
        	 System.out.println("finish");
         }
	}	

	@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {


		
		
		
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
		
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());//BLOB   
		binder.registerCustomEditor(String.class, new StringMultipartFileEditor());//CLOB
		//SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}



}
