package com.joymain.jecs.mi.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.mi.model.JmiAddrBook;
import com.joymain.jecs.mi.model.JmiTaiwanTravel;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.JmiTaiwanTravelManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JmiTaiwanTravelFormController extends BaseFormController {
    private JmiTaiwanTravelManager jmiTaiwanTravelManager = null;
    private JmiMemberManager jmiMemberManager;
    private SysBankManager sysBankManager;
	private AlCompanyManager alCompanyManager;
	private AlCountryManager alCountryManager = null;
	private AlStateProvinceManager alStateProvinceManager = null;
    public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}
	public void setJmiTaiwanTravelManager(JmiTaiwanTravelManager jmiTaiwanTravelManager) {
        this.jmiTaiwanTravelManager = jmiTaiwanTravelManager;
    }
    public JmiTaiwanTravelFormController() {
        setCommandName("jmiTaiwanTravel");
        setCommandClass(JmiTaiwanTravel.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
    	
        String userCode = request.getParameter("userCode");
        JmiTaiwanTravel jmiTaiwanTravel = null;

        if (!StringUtils.isEmpty(userCode)) {
            jmiTaiwanTravel = jmiTaiwanTravelManager.getJmiTaiwanTravel(userCode);
        } 
        if(jmiTaiwanTravel==null){
        	jmiTaiwanTravel = new JmiTaiwanTravel();
        }
        

    	//读取省份
		AlCompany alCompany = alCompanyManager.getAlCompanyByCode(defSysUser.getCompanyCode());
        AlCountry alCountry = new AlCountry();
    	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	

		//找出可用银行
        List sysBankList=sysBankManager.getSysBankByCompanyCode(defSysUser.getCompanyCode());
        request.setAttribute("sysBankList", sysBankList);
        
        return jmiTaiwanTravel;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser=SessionLogin.getLoginUser(request);
        JmiTaiwanTravel jmiTaiwanTravel = (JmiTaiwanTravel) command;

		String strAction = request.getParameter("strAction");
        if(checkJmiTaiwanTravel(jmiTaiwanTravel, errors,strAction)){

    		return showForm(request, response, errors);
        }
        
		String key=null;
//		if ("deleteJmiTaiwanTravel".equals(strAction)  ) {
//			jmiTaiwanTravelManager.removeJmiTaiwanTravel(jmiTaiwanTravel.getUserCode().toString());
//			key="jmiTaiwanTravel.delete";
//		}else{
		if("addJmiTaiwanTravel".equals(strAction)){
			jmiTaiwanTravel.setCreateUser(defSysUser.getUserCode());
			jmiTaiwanTravel.setCreateTime(new Date());
		}
			jmiTaiwanTravelManager.saveJmiTaiwanTravel(jmiTaiwanTravel);
			key="jsys.message.updateSuccess";
//		}
    		MessageUtil.saveMessage(request, LocaleUtil.getLocalText(key));
        return new ModelAndView(getSuccessView());
    }
    
    
    private boolean checkJmiTaiwanTravel(JmiTaiwanTravel jmiTaiwanTravel,BindException errors,String strAction){
    	boolean isNotPass=false;
    	if (null==jmiTaiwanTravel.getFilingDate()) {
			errors.rejectValue("filingDate", "isNotNull",new Object[] {"报名申请日期" }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getUserName())) {
			errors.rejectValue("userName", "isNotNull",new Object[] {"姓名" }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getPetName())) {
			errors.rejectValue("petName", "isNotNull",new Object[] {"别名" }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getIdNo())) {
			errors.rejectValue("idNo", "isNotNull",new Object[] {"身份证号" }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getUserCode())) {
			errors.rejectValue("userCode", "isNotNull",new Object[] {"会员编号" }, "");
			isNotPass = true;
    	}else if(null==jmiMemberManager.getJmiMember(jmiTaiwanTravel.getUserCode())){
			errors.reject("会员不存在", "会员不存在");
			isNotPass = true;
    	}else if("addJmiTaiwanTravel".equals(strAction) && jmiTaiwanTravelManager.getCheckJmiTaiwanTravelExist(jmiTaiwanTravel)){
			errors.reject("会员已申请","会员已申请");
			isNotPass = true;
    	}
    	
    	if (null==jmiTaiwanTravel.getIdProvince()) {
			errors.rejectValue("idProvince", "isNotNull",new Object[] {"户口所在省" }, "");
			isNotPass = true;
    	}
    	if (null==jmiTaiwanTravel.getIdCity()) {
			errors.rejectValue("idCity", "isNotNull",new Object[] {"户口所在市" }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getPhone())) {
			errors.rejectValue("phone", "isNotNull",new Object[] {"联系电话" }, "");
			isNotPass = true;
    	}
    	
    	if (null==jmiTaiwanTravel.getCommonProvince()) {
			errors.rejectValue("commonProvince", "isNotNull",new Object[] {"常住地省" }, "");
			isNotPass = true;
    	}
    	if (null==jmiTaiwanTravel.getCommonCity()) {
			errors.rejectValue("commonCity", "isNotNull",new Object[] {"常住地市" }, "");
			isNotPass = true;
    	}
//    	if (null==jmiTaiwanTravel.getCommonFromCity()) {
//			errors.rejectValue("commonFromCity", "isNotNull",new Object[] {"普通地区选择出发城市" }, "");
//			isNotPass = true;
//    	}
//    	if (null==jmiTaiwanTravel.getSpecialFromCity()) {
//			errors.rejectValue("specialFromCity", "isNotNull",new Object[] {"特殊地区人员选择出发城市" }, "");
//			isNotPass = true;
//    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getAddress())) {
			errors.rejectValue("address", "isNotNull",new Object[] {"通信地址" }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getPostalcode())) {
			errors.rejectValue("postalcode", "isNotNull",new Object[] {"邮政编码" }, "");
			isNotPass = true;
    	}
    	if (null==jmiTaiwanTravel.getRemittanceTime()) {
			errors.rejectValue("remittanceTime", "isNotNull",new Object[] {"汇款时间" }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getRemittanceName())) {
			errors.rejectValue("remittanceName", "isNotNull",new Object[] {"汇款账户名" }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getRemittanceCard())) {
			errors.rejectValue("remittanceCard", "isNotNull",new Object[] {"汇款账号" }, "");
			isNotPass = true;
    	}
    	if (StringUtil.isEmpty(jmiTaiwanTravel.getRemittanceBank())) {
			errors.rejectValue("remittanceBank", "isNotNull",new Object[] {"开户行" }, "");
			isNotPass = true;
    	}
    	if(!StringUtil.isEmpty(jmiTaiwanTravel.getSpouseUserCode())&&null==jmiMemberManager.getJmiMember(jmiTaiwanTravel.getSpouseUserCode())){
			errors.reject("配偶会员编号不存","配偶会员编号不存");
			isNotPass = true;
    	}
    	return isNotPass;
    }
    
    
    
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}
	public void setSysBankManager(SysBankManager sysBankManager) {
		this.sysBankManager = sysBankManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
}
