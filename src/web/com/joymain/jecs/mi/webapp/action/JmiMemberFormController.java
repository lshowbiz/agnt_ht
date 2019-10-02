package com.joymain.jecs.mi.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.bean.WeekFormatUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JmiMemberFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;
    private SysBankManager sysBankManager;
	private AlCountryManager alCountryManager = null;
	private AlStateProvinceManager alStateProvinceManager = null;
	private AlCompanyManager alCompanyManager;
	private JfiPayBankManager jfiPayBankManager;
	
    public void setJfiPayBankManager(JfiPayBankManager jfiPayBankManager) {
		this.jfiPayBankManager = jfiPayBankManager;
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
    public JmiMemberFormController() {
        setCommandName("jmiMember");
        setCommandClass(JmiMember.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	

    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	

 		String userCode = request.getParameter("userCode");
 		
 		JmiMember jmiMember = new JmiMember();
 		if (StringUtil.isEmpty(userCode)) {
 			return jmiMember;
 		}
 		
 		jmiMember = jmiMemberManager.getJmiMember(userCode);
 		if(null==jmiMember){
 			return null;
 		}
 		//检查是否同一公司，AA除外
		if(!isSameCompanyCode(defSysUser,jmiMember)){
			return null;
		}

		//找出汇入银行
		CommonRecord crm = new CommonRecord();
    	Pager pager = new Pager("fiPayBankListTable",request, 0);
    	crm.addField("companyCode", defSysUser.getCompanyCode().equals("AA")?"":defSysUser.getCompanyCode());
    	List fiPayBanks = jfiPayBankManager.getJfiPayBanksByCrm(crm, pager);
    	request.setAttribute("fiPayBanks", fiPayBanks);
    	
		//找出可用银行
        List sysBankList=sysBankManager.getSysBankByCompanyCode(jmiMember.getCompanyCode());
        request.setAttribute("sysBankList", sysBankList);
    	
    	//读取省份
		AlCompany alCompany = alCompanyManager.getAlCompanyByCode(jmiMember.getCompanyCode());
        AlCountry alCountry = new AlCountry();
    	alCountry = alCountryManager.getAlCountryByCode(alCompany.getCountryCode());
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	
    	request.setAttribute("oldCardType", jmiMember.getCardType());
    	request.setAttribute("oldCompanyCode", jmiMember.getCompanyCode());
    	request.setAttribute("oldIsstore", jmiMember.getIsstore());
    	request.setAttribute("oldFreezeStatus", jmiMember.getFreezeStatus());
    	request.setAttribute("oldShopType", jmiMember.getShopType());

    	request.setAttribute("oldIsCloudshop", jmiMember.getIsCloudshop());
    	
    	//***重置重消月份
    	if(jmiMember.getValidWeek()!=null){
        	jmiMember.setValidWeek(StringUtil.formatInt(WeekFormatUtil.getFormatMonth("w", jmiMember.getValidWeek().toString())));
    	} 
    	if(jmiMember.getStartWeek()!=null){
        	jmiMember.setStartWeek(StringUtil.formatInt(WeekFormatUtil.getFormatMonth("w", jmiMember.getStartWeek().toString())));
    	}
    	
    	
    	
        return jmiMember;
    }

    public ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command,BindException errors) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	
        JmiMember jmiMember = (JmiMember) command;

//    	if(1==jmiMember.getBeforeFreezeStatus()){//会员冻结，不能转移
//    		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("bdsendrecord.sendlateremark.17"));
//    		return showForm(request, response, errors);
//    	}
    	
    	if(jmiMemberManager.getCheckMiMember(request, jmiMember, errors, defSysUser.getDefCharacterCoding(), "2")){
    		return showForm(request, response, errors);
    	}  

		try {
			jmiMemberManager.saveMemberCompany(jmiMember, request);
			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			return new ModelAndView("redirect:editJmiMember.html?userCode="+jmiMember.getUserCode()+"&strAction=editMember");
		} catch (Exception e) {
			e.printStackTrace();
			if("not.audit.exsit".equals(e.getMessage())){
	    		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("not.audit.exsit"));
			}
    		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("sys.message.updateFail"));
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
    /**
     * 检查是否与当前用户处于同一分公司(AA除外)
     * @param sysUser
     * @param miMember
     * @return
     */
    private boolean isSameCompanyCode(SysUser sysUser, JmiMember miMember){
    	if("AA".equals(sysUser.getCompanyCode())){
    		return true;
    	}else{
    		if(sysUser.getCompanyCode().equals(miMember.getCompanyCode())){
    			return true;
    		}
    	}
    	return false;
    }
    
}
