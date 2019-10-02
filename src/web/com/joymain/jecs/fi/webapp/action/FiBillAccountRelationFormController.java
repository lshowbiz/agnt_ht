package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.al.model.AlCompany;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.service.AlCompanyManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.fi.model.FiBillAccount;
import com.joymain.jecs.fi.model.FiBillAccountRelation;
import com.joymain.jecs.fi.service.FiBillAccountManager;
import com.joymain.jecs.fi.service.FiBillAccountRelationManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class FiBillAccountRelationFormController extends BaseFormController {
	private FiBillAccountRelationManager fiBillAccountRelationManager = null;
	private FiBillAccountManager fiBillAccountManager = null;
	private AlCompanyManager alCompanyManager;
	private AlCountryManager alCountryManager = null;

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setAlCompanyManager(AlCompanyManager alCompanyManager) {
		this.alCompanyManager = alCompanyManager;
	}

	public void setFiBillAccountManager(
			FiBillAccountManager fiBillAccountManager) {
		this.fiBillAccountManager = fiBillAccountManager;
	}

	public void setFiBillAccountRelationManager(
			FiBillAccountRelationManager fiBillAccountRelationManager) {
		this.fiBillAccountRelationManager = fiBillAccountRelationManager;
	}

	public FiBillAccountRelationFormController() {
		setCommandName("fiBillAccountRelation");
		setCommandClass(FiBillAccountRelation.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		//初始化添加页面
		String billAccountCode = request.getParameter("billAccountCode");
		FiBillAccountRelation fiBillAccountRelation = new FiBillAccountRelation();

		FiBillAccount fiBillAccount = fiBillAccountManager.getFiBillAccount(billAccountCode);
		fiBillAccountRelation.setFiBillAccount(fiBillAccount);

		// 读取省份
		AlCompany alCompany = alCompanyManager.getAlCompanyByCode("CN");
		AlCountry alCountry = new AlCountry();
		alCountry = alCountryManager.getAlCountryByCode(alCompany
				.getCountryCode());
		alCountry.getAlStateProvinces().iterator();
		request.setAttribute("alStateProvinces", alCountry
				.getAlStateProvinces());

		return fiBillAccountRelation;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		FiBillAccountRelation fiBillAccountRelation = (FiBillAccountRelation) command;
		
		String billAccountCode = fiBillAccountRelation.getFiBillAccount().getBillAccountCode();
		ModelAndView model = new ModelAndView("redirect:editFiBillAccount.html?strAction=editFiBillAccount&billAccountCode="+billAccountCode);

		String strAction = request.getParameter("strAction");
		if ("addFiBillAccountRelation".equals(strAction)) {
			
			//验证填写信息
			if(StringUtil.isEmpty(fiBillAccountRelation.getProvince())){
				saveMessage(request, "请选择区域!");
				return model;
			}
			
			//验证是否重复设置
    		List list = fiBillAccountRelationManager.getFiBillAccountRelationsByConditions(fiBillAccountRelation);
    		
    		if(list.size() > 0){
    			
    			FiBillAccountRelation tempMfiBillAccountRelation = (FiBillAccountRelation) list.get(0);
    			
    			saveMessage(request, "对不起，该区域重复设置!具体请参考商户号："+tempMfiBillAccountRelation.getFiBillAccount().getBillAccountCode());
    		}else{
    			
    			//保存
    			fiBillAccountRelationManager.saveFiBillAccountRelation(fiBillAccountRelation);
    		}
		}
		
		return model;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
