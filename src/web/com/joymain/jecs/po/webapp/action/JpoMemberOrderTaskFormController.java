package com.joymain.jecs.po.webapp.action;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.mi.service.JmiMemberManager;

import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoMemberOrderListTask;
import com.joymain.jecs.po.model.JpoMemberOrderTask;
import com.joymain.jecs.po.service.JpoMemberOrderTaskManager;
import com.joymain.jecs.sys.model.SysUser;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoMemberOrderTaskFormController extends BaseFormController {
    private JpoMemberOrderTaskManager jpoMemberOrderTaskManager = null;
    private JmiMemberManager jmiMemberManager;
    private JmiAddrBookManager jmiAddrBookManager;
    private AlCountryManager alCountryManager;
    private JpmProductSaleManager jpmProductSaleManager;

    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private JalTownManager jalTownManager;
    
    private BdPeriodManager bdPeriodManager;
    public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJpoMemberOrderTaskManager(JpoMemberOrderTaskManager jpoMemberOrderTaskManager) {
        this.jpoMemberOrderTaskManager = jpoMemberOrderTaskManager;
    }
    public JpoMemberOrderTaskFormController() {
        setCommandName("jpoMemberOrderTask");
        setCommandClass(JpoMemberOrderTask.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        
        
    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
    	
    	
        JpoMemberOrderTask jpoMemberOrderTask = jpoMemberOrderTaskManager.getMainJpoMemberOrderTask(loginSysUser.getUserCode());

        if (jpoMemberOrderTask==null) {
        	jpoMemberOrderTask = new JpoMemberOrderTask();
        	jpoMemberOrderTask.setCompanyCode(loginSysUser.getCompanyCode());
        	JmiMember jmiMember = jmiMemberManager.getJmiMember(loginSysUser.getUserCode());
        	jpoMemberOrderTask.setCountryCode(jmiMember.getCountryCode());
        	jpoMemberOrderTask.setUserCode(loginSysUser.getUserCode());
        	jpoMemberOrderTask.setStatus("0");
        	jpoMemberOrderTask.setTaskType("0");
        	request.setAttribute("onOff", "1");
        } else{
        	request.setAttribute("onOff", "0");
        	
        	

    		if(!StringUtils.isEmpty(jpoMemberOrderTask.getProvince())){
        		List<AlCity> citys=alCityManager.getAlCityByProvinceId(Long.parseLong(jpoMemberOrderTask.getProvince()));
        		for (AlCity city : citys) {
        			if(city.getCityId().toString().equals(jpoMemberOrderTask.getCity())){
        				jpoMemberOrderTask.setCity(city.getCityName());
        				if(!StringUtils.isEmpty(jpoMemberOrderTask.getDistrict())){
            				List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
            				for (AlDistrict district : alDistricts) {
            					if(district.getDistrictId().toString().equals(jpoMemberOrderTask.getDistrict())){
            						jpoMemberOrderTask.setDistrict(district.getDistrictName());
        	        				if(!StringUtils.isEmpty(jpoMemberOrderTask.getDistrict())){
        	            				List<JalTown> alTowns = jalTownManager.getJalTownByDistrictId(district.getDistrictId());
        	            				for (JalTown town : alTowns) {
        	            					if(town.getTownId().toString().equals(jpoMemberOrderTask.getTown())){
        	            						jpoMemberOrderTask.setTown(town.getTownName());
        	            					}
        	            				}
        	        				}
            						break;
            					}
            				}
        				}
        				break;
        			}
        		}
    		}
        }
        
        //地址簿
        CommonRecord crm = new CommonRecord();
        crm.addField("userCode", loginSysUser.getUserCode());

        List jmiAddrBooks = jmiAddrBookManager.getJmiAddrBooksByCrm(crm, new Pager(request,0));
        request.setAttribute("jmiAddrBooks", jmiAddrBooks);

        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	
    	
    	//获取商品
    	List jpmProductSales = null;
    	jpmProductSales = jpmProductSaleManager.getProductSales(loginSysUser.getCompanyCode(),Constants.MAINTAIN_PURCHASE,"1");
    	request.setAttribute("jpmProductSales", jpmProductSales);
    	
        
    	
        return jpoMemberOrderTask;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
    	
        JpoMemberOrderTask jpoMemberOrderTask = (JpoMemberOrderTask) command;

        //msnstatus 0 关闭 1开启
        
        JpoMemberOrderTask cuJpoMemberOrderTask= jpoMemberOrderTaskManager.getMainJpoMemberOrderTask(loginSysUser.getUserCode());
        
        if(checkPoMemberOrder(jpoMemberOrderTask, errors)){
        	return showForm(request, response, errors);
        }
        
        
        Date curDate=new Date();
        
        if(cuJpoMemberOrderTask!=null){
        	try {
        		jpoMemberOrderTaskManager.removeJpoMemberOrderTask(cuJpoMemberOrderTask.getMotId().toString());
    			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
			} catch (Exception e) {
				e.printStackTrace();
				MessageUtil.saveLocaleMessage(request, "sys.message.updateFail");
			}
        }else{
        	
        	
        	Set jpoMemberOrderListTask = null;
        	jpoMemberOrderListTask = this.fillInJfoMemberOrderList(request,jpoMemberOrderTask);//生成订单明细
    		if(jpoMemberOrderListTask==null){//检查订单总数是否小于0
        		errors.reject("amount.notEnough", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"amount.notEnough"));
        		return showForm(request, response, errors);
    		}
    		jpoMemberOrderTask.setActionTime(bdPeriodManager.getBdPeriodByTimeFormatedAll(curDate, null));
    		jpoMemberOrderTask.setCreateTime(curDate);
    		jpoMemberOrderTask.setCreateUser(operatorSysUser.getUserCode());
    		jpoMemberOrderTask.getJpoMemberOrderListTask().clear();
    		jpoMemberOrderTask.setJpoMemberOrderListTask(jpoMemberOrderListTask);
    		try {
    			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
    			MessageUtil.saveLocaleMessage(request, "sys.message.updateSuccess");
    			
			} catch (Exception e) {
				e.printStackTrace();
				MessageUtil.saveLocaleMessage(request, "sys.message.updateFail");
				
			}
        }
        

        return new ModelAndView("redirect:editJpoMemberOrderTask.html");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
	}
    
    
    /**
     * 判断订单是否通过检验
     * @param jpoMemberOrderTask
     * @param errors
     * @return
     */
    private boolean checkPoMemberOrder(JpoMemberOrderTask jpoMemberOrderTask,BindException errors){
    	boolean isNotPass=false;
		Pattern pattern = null;
		
    	
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getMobiletele())){
				errors.rejectValue("mobiletele", "errors.required",new Object[] {this.getText("miMember.mobiletele") }, "");
				isNotPass = true;
	    	}else{

		    	if("CN".equals(jpoMemberOrderTask.getCompanyCode())){
		    		pattern = Pattern.compile("^[1]([0-9]{10})$");
		    		Matcher matcher = pattern.matcher(jpoMemberOrderTask.getMobiletele());
		    		if(!matcher.find()){
		    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
		    			isNotPass = true;
		    		}
		    	}

		    	if("TW".equals(jpoMemberOrderTask.getCompanyCode())){
			    	pattern = Pattern.compile("^[0][9]([0-9]{8})$");
		    	}
		    	if("PH".equals(jpoMemberOrderTask.getCompanyCode())){
		    		pattern = Pattern.compile("^[0][9]([0-9]{9})$");
		    	}
		    	Matcher matcher = pattern.matcher(jpoMemberOrderTask.getMobiletele());
		    	if(!matcher.find()){
		    		errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
		    		isNotPass = true;
		    	}
	    	}
	    	
	    	
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getFirstName())){
				errors.rejectValue("firstName", "errors.required",new Object[] {this.getText("shipping.firstName") }, "");
				isNotPass = true;
	    	}else if(jpoMemberOrderTask.getFirstName().length()>100){
				errors.rejectValue("firstName", "errors.maxlength",new Object[] {this.getText("shipping.firstName"),"100" }, "");
				isNotPass = true;
	    	}
	    	
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getLastName())){
				errors.rejectValue("lastName", "errors.required",new Object[] {this.getText("shipping.lastName") }, "");
				isNotPass = true;
	    	}else if(jpoMemberOrderTask.getLastName().length()>100){
				errors.rejectValue("lastName", "errors.maxlength",new Object[] {this.getText("shipping.lastName"),"100" }, "");
				isNotPass = true;
	    	}
	    	
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getProvince())){
				errors.rejectValue("province", "errors.required",new Object[] {this.getText("shipping.province") }, "");
				isNotPass = true;
	    	}else if(jpoMemberOrderTask.getProvince().length()>20){
				errors.rejectValue("province", "errors.maxlength",new Object[] {this.getText("shipping.province"),"20" }, "");
				isNotPass = true;
	    	}
	    	
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getCity())){
				errors.rejectValue("city", "errors.required",new Object[] {this.getText("shipping.city") }, "");
				isNotPass = true;
	    	}else if(jpoMemberOrderTask.getCity().length()>20){
				errors.rejectValue("city", "errors.maxlength",new Object[] {this.getText("shipping.city"),"20" }, "");
				isNotPass = true;
	    	}
	    	
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getDistrict())){
	    		if("PH".equals(jpoMemberOrderTask.getCompanyCode())){
    	    		errors.rejectValue("district", "errors.required",new Object[] {this.getText("shipping.district") }, "");
    				isNotPass = true;
	    		}
	    	}else if(jpoMemberOrderTask.getDistrict().length()>20){
				errors.rejectValue("district", "errors.maxlength",new Object[] {this.getText("shipping.district"),"20" }, "");
				isNotPass = true;
	    	}
	    	
	    	if("PH".equals(jpoMemberOrderTask.getCompanyCode())){
    	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getTown())){
        	    	errors.rejectValue("district", "errors.required",new Object[] {this.getText("shipping.town") }, "");
        			isNotPass = true;
    	    	}else if(jpoMemberOrderTask.getTown().length()>20){
    				errors.rejectValue("district", "errors.maxlength",new Object[] {this.getText("shipping.town"),"20" }, "");
    				isNotPass = true;
    	    	}
			}
	    	
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getAddress())){
				errors.rejectValue("address", "errors.required",new Object[] {this.getText("shipping.address") }, "");
				isNotPass = true;
	    	}else if(jpoMemberOrderTask.getAddress().length()>500){
				errors.rejectValue("address", "errors.maxlength",new Object[] {this.getText("shipping.address"),"500" }, "");
				isNotPass = true;
	    	}
	    	
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getPostalcode())){
				errors.rejectValue("postalcode", "errors.required",new Object[] {this.getText("shipping.postalcode") }, "");
				isNotPass = true;
	    	}else if(jpoMemberOrderTask.getPostalcode().length()>10){
				errors.rejectValue("postalcode", "errors.maxlength",new Object[] {this.getText("shipping.postalcode"),"10" }, "");
				isNotPass = true;
	    	}
	    	
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getPhone())){
				errors.rejectValue("phone", "errors.required",new Object[] {this.getText("shipping.phone") }, "");
				isNotPass = true;
	    	}else if(jpoMemberOrderTask.getPhone().length()>20){
				errors.rejectValue("phone", "errors.maxlength",new Object[] {this.getText("shipping.phone"),"20" }, "");
				isNotPass = true;
	    	}
	
			pattern = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
	    	if(StringUtils.isEmpty(jpoMemberOrderTask.getEmail())){
	    		
	    	}else if(jpoMemberOrderTask.getEmail().length()>100){
				errors.rejectValue("email", "errors.maxlength",new Object[] {this.getText("shipping.email"),"100" }, "");
				isNotPass = true;
	    	}else{
	    		Matcher matcher = pattern.matcher(jpoMemberOrderTask.getEmail());
	    		if(!matcher.matches()){
	    			errors.rejectValue("email", "errors.email",new Object[] {this.getText("shipping.email") }, "");
	    			isNotPass = true;
	    		}
	    	}

    	return isNotPass;
    }
    /**
     * 生成订单明细
     * @param request
     * @param poMemberOrder
     * @param defSysUser
     * @return
     */
    private Set fillInJfoMemberOrderList(HttpServletRequest request, JpoMemberOrderTask jpoMemberOrderTask){
    	String[] qty = request.getParameterValues("qty");
        String[] g_no = request.getParameterValues("g_no");
        Set jpoMemberOrderListTaskSet = new HashSet(0);
        
        BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	
		for(int i_no = 0 ; i_no < g_no.length ; i_no++){
			if( !StringUtils.isEmpty(qty[i_no]) && !"0".equals(qty[i_no])){
				if(Integer.parseInt(qty[i_no])<0){
					return null;
				}
				JpmProductSaleTeamType  jpmProductSale = jpmProductSaleManager.getJpmProductSaleTeamType(g_no[i_no]);
				if(jpmProductSale==null){
					return null;
				}
				JpoMemberOrderListTask jpoMemberOrderList = new JpoMemberOrderListTask();   			
				jpoMemberOrderList.setPrice(jpmProductSale.getPrice());
				jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
				jpoMemberOrderList.setPv(jpmProductSale.getPv());
				jpoMemberOrderList.setQty(Integer.parseInt(qty[i_no]));
    			jpoMemberOrderList.setJpoMemberOrderTask(jpoMemberOrderTask);
    			jpoMemberOrderList.setVolume(jpmProductSale.getJpmProductSaleNew().getVolume());
    			jpoMemberOrderList.setWeight(jpmProductSale.getJpmProductSaleNew().getWeight());
    			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty()))) ;
    			jpoMemberOrderListTaskSet.add(jpoMemberOrderList);
				
			}
		}
		if(jpoMemberOrderListTaskSet.size()==0){
			return null;
		}
    	return jpoMemberOrderListTaskSet;
    }
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager) {
		this.jmiAddrBookManager = jmiAddrBookManager;
	}
	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}
	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}
	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}
	public void setJalTownManager(JalTownManager jalTownManager) {
		this.jalTownManager = jalTownManager;
	}
}
