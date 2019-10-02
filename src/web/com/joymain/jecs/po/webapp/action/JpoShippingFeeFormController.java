package com.joymain.jecs.po.webapp.action;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.AlStateProvinceManager;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.po.service.JpoShippingFeeManager;
import com.joymain.jecs.sys.model.SysUser;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
/**
 * 物流费表单
 * @author Alvin
 *
 */
public class JpoShippingFeeFormController extends BaseFormController {
    private JpoShippingFeeManager jpoShippingFeeManager = null;
    private AlCountryManager alCountryManager = null;
    private AlStateProvinceManager alStateProvinceManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    
    public JpoShippingFeeFormController() {
        setCommandName("jpoShippingFee");
        setCommandClass(JpoShippingFee.class);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String jpsId = request.getParameter("jpsId");
        JpoShippingFee jpoShippingFee = null;
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        String strAction = request.getParameter("strAction");
        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	
        if (!StringUtils.isEmpty(jpsId)) {
            jpoShippingFee = jpoShippingFeeManager.getJpoShippingFee(jpsId);
        } else {
            jpoShippingFee = new JpoShippingFee();
            jpoShippingFee.setCountryCode(alCountry.getCountryCode());
        }
        if(strAction.equalsIgnoreCase("editJpoShippingFee")){
        	AlStateProvince provicne = alStateProvinceManager.
        			getAlStateProvince(jpoShippingFee.getProvince());
        	request.setAttribute("provicneName", provicne.getStateProvinceName());
        	
        	AlCity city = alCityManager.getAlCity(jpoShippingFee.getCity());
        	request.setAttribute("cityName", city.getCityName());
        	
        	AlDistrict dis = alDistrictManager.getAlDistrict(jpoShippingFee.getDistrict());
        	request.setAttribute("disName", dis.getDistrictName());
        }
        
        return jpoShippingFee;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        
        SysUser loginSysUser = SessionLogin.getLoginUser(request);

        JpoShippingFee jpoShippingFee = (JpoShippingFee) command;
//        boolean isNew = (jpoShippingFee.getJpsId() == null);
//        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deleteJpoShippingFee".equals(strAction)  ) {
			jpoShippingFeeManager.removeJpoShippingFee(jpoShippingFee.getJpsId().toString());
			key="jpoShippingFee.delete";
		}else{
    		CommonRecord crm = RequestUtil.toCommonRecord(request);
    		crm.addField("countryCode", jpoShippingFee.getCountryCode());
    		crm.addField("province", jpoShippingFee.getProvince());
    		crm.addField("city", jpoShippingFee.getCity());
    		crm.addField("district", jpoShippingFee.getDistrict());
    		crm.addField("shippingType", jpoShippingFee.getShippingType());
    		crm.addField("shippingFee", "");
    		List jpoShippingFees = jpoShippingFeeManager.getJpoShippingFeesByCrm(crm, new Pager(request,0));
    		if("addJpoShippingFee".equals(strAction)){
    			key="jpoShippingFee.add";
    			if(jpoShippingFees.size()>0){
            		errors.reject("poShippingFee.isIn", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poShippingFee.isIn"));
            		return showForm(request, response, errors);
    			}
    		}else{
    			key="jpoShippingFee.update";
    			if(jpoShippingFees.size()>0){
    				for(int i = 0 ; i < jpoShippingFees.size() ; i++){
    					JpoShippingFee jpoShippingFeeTmp = (JpoShippingFee) jpoShippingFees.get(i);
    					
    					if(!jpoShippingFeeTmp.getJpsId().toString().equals(jpoShippingFee.getJpsId().toString())){
    	            		errors.reject("poShippingFee.isIn", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poShippingFee.isIn"));
    	            		return showForm(request, response, errors);
    					}
    				}
    			}
    		}
    		
			jpoShippingFeeManager.saveJpoShippingFee(jpoShippingFee);
			key="jpoShippingFee.update";
		}
		saveMessage(request, getText(loginSysUser.getDefCharacterCoding(),key));
        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	String[] allowedFields = {
    			"province",
    			"city",
    			"district",
    			"shippingType",
    			"shippingFee",
    			"shippingWeight",
    			"shippingRefee",
    			"shippingReweight"
    	};
    	binder.setAllowedFields(allowedFields);
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
	public AlStateProvinceManager getAlStateProvinceManager() {
		return alStateProvinceManager;
	}
	public void setAlStateProvinceManager(
			AlStateProvinceManager alStateProvinceManager) {
		this.alStateProvinceManager = alStateProvinceManager;
	}
	public AlCityManager getAlCityManager() {
		return alCityManager;
	}
	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}
	public AlDistrictManager getAlDistrictManager() {
		return alDistrictManager;
	}
	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public JpoShippingFeeManager getJpoShippingFeeManager() {
		return jpoShippingFeeManager;
	}
	public AlCountryManager getAlCountryManager() {
		return alCountryManager;
	}
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public void setJpoShippingFeeManager(JpoShippingFeeManager jpoShippingFeeManager) {
        this.jpoShippingFeeManager = jpoShippingFeeManager;
    }
	
}
