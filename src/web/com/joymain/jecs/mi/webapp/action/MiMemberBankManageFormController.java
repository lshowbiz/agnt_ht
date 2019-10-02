package com.joymain.jecs.mi.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiMemberLog;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.GuidHelper;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;


public class MiMemberBankManageFormController extends BaseFormController {

	private JmiMemberManager jmiMemberManager;
    private SysBankManager sysBankManager;
    private AlCountryManager alCountryManager;
    public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public void setSysBankManager(SysBankManager sysBankManager) {
		this.sysBankManager = sysBankManager;
	}
	public MiMemberBankManageFormController() {
		setCommandName("jmiMember");
		setCommandClass(JmiMember.class);
	}

	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	protected Object formBackingObject(HttpServletRequest request) {
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		
		//

        AlCountry alCountry = new AlCountry();//获取地区
    	alCountry = alCountryManager.getAlCountryByCode(defSysUser.getCompanyCode());
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
		
		
//		找出可用银行
        JmiMember member = new JmiMember();
		String userCode = request.getParameter("userCode");
		if (!StringUtil.isEmpty(userCode)) {
			member=jmiMemberManager.getJmiMember(userCode);
	        List sysBankList=sysBankManager.getSysBankByCompanyCode(member.getCompanyCode());
	        request.setAttribute("sysBankList", sysBankList);
		}
		
		if("JP".equals(defSysUser.getCompanyCode())){
	    	if( StringUtil.isEmpty(member.getBankbook()) && ("0".equals(member.getBankType())||StringUtil.isEmpty(member.getBankType()))){
	    		member.setBankbook((member.getFirstNameKana()==null?"":member.getFirstNameKana())+" "+(member.getLastNameKana()==null?"":member.getLastNameKana()));
	    	}
		}
		
		
		return member;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		JmiMember jmiMember = (JmiMember) command;
		SysUser defSysUser = SessionLogin.getLoginUser(request);
		if(!"JP".equals(jmiMember.getCompanyCode())){
	    	if (StringUtils.isEmpty(jmiMember.getBank())) {
				errors.rejectValue("bank", "isNotNull",new Object[] { LocaleUtil.getLocalText("miMember.openBank") }, "");
				return showForm(request, response, errors);
	    	}
		}else{
			if ("0".equals(jmiMember.getBankType())){
		    	if (StringUtils.isEmpty(jmiMember.getBank())) {
					errors.rejectValue("bank", "isNotNull",new Object[] { LocaleUtil.getLocalText("miMember.openBank") }, "");
					return showForm(request, response, errors);
		    	}
		    	if(StringUtils.isEmpty(jmiMember.getBankCode())){
					errors.rejectValue("bankCode", "isNotNull",new Object[] { LocaleUtil.getLocalText("miMember.bankCode") }, "");
					return showForm(request, response, errors);
		    	}
		    	if(StringUtils.isEmpty(jmiMember.getBankKana())){
					errors.rejectValue("bankKana", "isNotNull",new Object[] { LocaleUtil.getLocalText("miMember.bankKana") }, "");
					return showForm(request, response, errors);
		    	}
			}
		}
    	if (StringUtils.isEmpty(jmiMember.getBankaddress())) {
			errors.rejectValue("bankaddress", "isNotNull",new Object[] {LocaleUtil.getLocalText( "miMember.bcity") }, "");
			return showForm(request, response, errors);
    	}else if("JP".equals(jmiMember.getCompanyCode())&&"1".equals(jmiMember.getBankType())){
    		Pattern pattern = Pattern.compile("^([0-9]{5,6})$");
    		Matcher matcher = pattern.matcher(jmiMember.getBankaddress());
    		if(!matcher.find()){
				errors.rejectValue("bankaddress", "errors.invalid",new Object[] { LocaleUtil.getLocalText("miMember.post.num") }, "");
				return showForm(request, response, errors);
	    	}
    	}
    	if (StringUtils.isEmpty(jmiMember.getBankcard())) {
			errors.rejectValue("bankcard", "isNotNull",new Object[] { LocaleUtil.getLocalText( "miMember.bnum") }, "");
			return showForm(request, response, errors);
    	}else if("JP".equals(jmiMember.getCompanyCode())&&"1".equals(jmiMember.getBankType())){
    		Pattern pattern = Pattern.compile("^([0-9]{6,8})$");
    		Matcher matcher = pattern.matcher(jmiMember.getBankcard());
    		if(!matcher.find()){
				errors.rejectValue("bankcard", "errors.invalid",new Object[] { LocaleUtil.getLocalText("miMember.bnum") }, "");
				return showForm(request, response, errors);
	    	}
    	}else if("JP".equals(jmiMember.getCompanyCode())&&"0".equals(jmiMember.getBankType())){
    		Pattern pattern = Pattern.compile("^([0-9]{7})$");
    		Matcher matcher = pattern.matcher(jmiMember.getBankcard());
    		if(!matcher.find()){
				errors.rejectValue("bankcard", "errors.invalid",new Object[] { LocaleUtil.getLocalText("miMember.bnum") }, "");
				return showForm(request, response, errors);
	    	}
    	}
    	
    	if (StringUtils.isEmpty(jmiMember.getBankbook())) {
			errors.rejectValue("bankbook", "isNotNull",new Object[] { LocaleUtil.getLocalText( "miMember.bname") }, "");
			return showForm(request, response, errors);
    	}
    	
    	if(!"JP".equals(jmiMember.getCompanyCode())){
        	if (StringUtils.isEmpty(jmiMember.getBankProvince())) {
    			errors.rejectValue("bankProvince", "isNotNull",new Object[] { LocaleUtil.getLocalText( "miMember.bankProvince") }, "");
    			return showForm(request, response, errors);
        	}
        	if(!"US".equals(jmiMember.getCompanyCode())){
	        	if (StringUtils.isEmpty(jmiMember.getBankCity())) {
	    			errors.rejectValue("bankCity", "isNotNull",new Object[] { LocaleUtil.getLocalText( "miMember.bankCity") }, "");
	    			return showForm(request, response, errors);
	        	}
        	}
    	}
    	if("US".equals(jmiMember.getCompanyCode())){
	    	if(StringUtils.isEmpty(jmiMember.getBuilding())){
	    		errors.rejectValue("building", "isNotNull",new Object[] { LocaleUtil.getLocalText( "miMember.bankCity") }, "");
	    		return showForm(request, response, errors);
	    	}
	    	if(StringUtils.isEmpty(jmiMember.getBankCode())){
	    		errors.rejectValue("bankCode", "isNotNull",new Object[] { LocaleUtil.getLocalText( "miMember.bankCode") }, "");
	    		return showForm(request, response, errors);
	    	}
    	}
    	if ("0".equals(jmiMember.getBankType()) && StringUtils.isEmpty(jmiMember.getTownAddr())) {
			errors.rejectValue("townAddr", "isNotNull",new Object[] { LocaleUtil.getLocalText("jp.bank.port") }, "");
			return showForm(request, response, errors);
    	}
		try {
			
//	    	jmiMemberLogDao
	    	JmiMemberLog JmiMemberLog = new JmiMemberLog();
//	    	JmiMemberLog.setLogId(GuidHelper.genRandomGUID());
	    	JmiMemberLog.setLogTime(new Date());
	    	JmiMemberLog.setLogType("1");//1:后台编辑，2后台导入，3前台编辑'
	    	JmiMemberLog.setLogUserCode(defSysUser.getUserCode());
	    	if(!StringUtil.isEmpty(jmiMember.getUserCode())){
	        	List list = jmiMemberManager.findJmiMemberById(jmiMember.getUserCode());
	        	
	        	JmiMember oldJmiMember = (JmiMember)list.get(0);
	        	if(null != oldJmiMember){
	        		JmiMemberLog.setBeforeBank(oldJmiMember.getBank());
	        		JmiMemberLog.setBeforeBankaddress(oldJmiMember.getBankaddress());
	        		JmiMemberLog.setBeforeBankbook(oldJmiMember.getBankbook());
	        		JmiMemberLog.setBeforeBankcard(oldJmiMember.getBankcard());
	        		JmiMemberLog.setBeforeBankcity(oldJmiMember.getBankCity());
	        		JmiMemberLog.setBeforeBankprovince(oldJmiMember.getBankProvince());
	        	}
	    	}
	    	if(null != jmiMember){
	    		JmiMemberLog.setAfterBank(jmiMember.getBank());
	    		JmiMemberLog.setAfterBankaddress(jmiMember.getBankaddress());
	    		JmiMemberLog.setAfterBankbook(jmiMember.getBankbook());
	    		JmiMemberLog.setAfterBankcard(jmiMember.getBankcard());
	    		JmiMemberLog.setAfterBankcity(jmiMember.getBankCity());
	    		JmiMemberLog.setAfterBankprovince(jmiMember.getBankProvince());
	    		
	    		JmiMemberLog.setUserCode(jmiMember.getUserCode());
	    		JmiMemberLog.setUserName(jmiMember.getFirstName()+jmiMember.getLastName());
	    	}
			
			jmiMemberManager.saveJmiMember(jmiMember,JmiMemberLog);
			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		} catch (Exception e) {
			e.printStackTrace();
			this.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateSuccess"));
		}
		
		return new ModelAndView("redirect:miMemberBankManageList.html");
	}
}