package com.joymain.jecs.po.webapp.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.pm.service.JpmSalePromoterManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.po.service.JpoShippingFeeManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class JpoMemberBOrderFormController extends BaseFormController {
    //private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JmiMemberManager jmiMemberManager = null;
    private JmiAddrBookManager jmiAddrBookManager = null;
    private AlCountryManager alCountryManager = null;
    private JpmProductSaleManager jpmProductSaleManager = null;
    private JpoShippingFeeManager jpoShippingFeeManager = null;
    //private JmiMemberTeamManager jmiMemberTeamManager=null;
    private JpmSalePromoterManager jpmSalePromoterManager;
    
    public JpoMemberBOrderFormController() {
        setCommandName("jpoMemberOrder");
        setCommandClass(JpoMemberOrder.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {

    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
  
        String moId = request.getParameter("moId");
        JpoMemberOrder jpoMemberOrder = null;
		String strAction = request.getParameter("strAction");
		
        if ("deletePoMemberBOrder".equals(strAction) || "editPoMemberBOrder".equals(strAction) && !StringUtils.isEmpty(moId)){
            jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);
            if("1".equals(jpoMemberOrder.getIsPay())){
            	throw new AppException("订单已支付!");
            }
           
        } else {
            jpoMemberOrder = new JpoMemberOrder();
            
            if("M".equals(loginSysUser.getUserType())){
                jpoMemberOrder.setSysUser(loginSysUser);
                jpoMemberOrder.setCompanyCode(loginSysUser.getCompanyCode());
                jpoMemberOrder.setUserSpCode(loginSysUser.getUserCode());
            	JmiMember jmiMember = jmiMemberManager.getJmiMember(loginSysUser.getUserCode());
                jpoMemberOrder.setCountryCode(jmiMember.getCountryCode());
            }else{
            	jpoMemberOrder.setSysUser(new SysUser());
            }
            
            jpoMemberOrder.setOrderUserCode(operatorSysUser.getUserCode());
            jpoMemberOrder.setLocked("0");
            jpoMemberOrder.setOrderType(Constants.B_PURCHASE);
            jpoMemberOrder.setPickup("0");
            jpoMemberOrder.setStatus("1");
            jpoMemberOrder.setSubmitStatus("1");
            jpoMemberOrder.setIsPay("0");
            
            jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
            jpoMemberOrder.setAmount(new BigDecimal(0));
            jpoMemberOrder.setPvAmt(new BigDecimal(0));
            jpoMemberOrder.setPayMode("0");
            jpoMemberOrder.setIsSpecial("0");
        }
        
        /**
         * TODO 需要修改
         */
        String teamCode="8888888888";//jmiMemberTeamManager.getTeamStr(loginSysUser);
        jpoMemberOrder.setTeamCode(teamCode);
        //地址簿
        CommonRecord crm = new CommonRecord();
        crm.addField("userCode", loginSysUser.getUserCode());
        if(!loginSysUser.getIsMember()){
        	String userCode = request.getParameter("sysUser.userCode");
        	if(!StringUtils.isEmpty(userCode)){
                crm.addField("userCode", userCode);
                JmiMember jmiMember = jmiMemberManager.getJmiMember(userCode);
                if(jmiMember!=null && jmiMember.getCompanyCode().equals(operatorSysUser.getCompanyCode())){
                	jpoMemberOrder.setSysUser(jmiMember.getSysUser());
                }
        	}
        }
        List jmiAddrBooks = jmiAddrBookManager.getJmiAddrBooksByCrm(crm, new Pager(request,0));
        request.setAttribute("jmiAddrBooks", jmiAddrBooks);

        //收货地区
        AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
    	alCountry.getAlStateProvinces().iterator();
    	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());
    	
    	if(!loginSysUser.getIsMember() && StringUtils.isEmpty(moId) && "TW".equals(loginSysUser.getCompanyCode()) && alCountry.getAlStateProvinces().size()==1){
    		AlStateProvince alStateProvince = (AlStateProvince)alCountry.getAlStateProvinces().iterator().next();
    		jpoMemberOrder.setProvince(alStateProvince.getStateProvinceId().toString());
    	}
    	
    	//获取商品
    	Map jpmProductSales = null;
    
    		jpmProductSales = jpmProductSaleManager.getPmProductSalesNew(
    				loginSysUser.getCompanyCode(),
    				Constants.B_PURCHASE,"2",teamCode,loginSysUser);
    	
    	request.setAttribute("jpmProductSales", jpmProductSales);
    	
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = DateUtil.getDateOffset(new Date(),5,3);
    	request.setAttribute("toDay",format.format(date));
        
    	if(RequestUtil.isMobileRequest(request)){
        	this.setFormView("/mobile/po/jpoMemberROrderForm");
        } else {
        	this.setFormView("/po/jpoMemberBOrderForm");
        	
        }


        return jpoMemberOrder;
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

        JpoMemberOrder jpoMemberOrder = (JpoMemberOrder) command;
        boolean isNew = (jpoMemberOrder.getMoId() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePoMemberBOrder".equals(strAction)) {
			jpoMemberOrderManager.removeJpoMemberOrder(jpoMemberOrder.getMoId().toString());
			key="poMemberOrder.deleted";
		}else{

			String companyPay = request.getParameter("companyPay");//是否为公司支付,1为是，0为否
			if(StringUtil.isEmpty(companyPay)){
				jpoMemberOrder.setCompanyPay("");
			}else{
				jpoMemberOrder.setCompanyPay("1");
			}
			
			JmiMember jmiMember = jmiMemberManager.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
			
			if("addPoMemberBOrder".equals(strAction)){
				if(validateOrder(jpoMemberOrder, errors, loginSysUser)){
		    		return showForm(request, response, errors);
		    	}
				if(jmiMember==null){
            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberROrder");
            		return showForm(request, response, errors);
				}
				boolean isfreez = isFreeze(jmiMember.getFreezeStatus(),errors,
						loginSysUser.getDefCharacterCoding());
				if(isfreez){
					return showForm(request, response, errors);
				}
				//会员是否需要下首购单
				Integer notfirst = jmiMember.getNotFirst();
				boolean isBuyFirst = isBuyFirstOrder(notfirst, jmiMember.getUserCode(),request);
				
				if(isBuyFirst){
					errors.reject("poMemberFOrder.isNotExist", new Object[] {},
							LocaleUtil.getLocalText(
									loginSysUser.getDefCharacterCoding(),
									"poMemberFOrder.isNotExist"));
					
					return showForm(request, response, errors);
				}
			}
			
			if("C".equals(loginSysUser.getUserType())){
				
				if(jmiMember==null){
            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberROrder");
            		return showForm(request, response, errors);
				}else{
					if("1".equals(jpoMemberOrder.getCompanyPay())){
						
					}else{
						if(!loginSysUser.getCompanyCode().equals(jmiMember.getCompanyCode())){
		            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
		            		//RequestUtil.cleanOperationSession(request,"addPoMemberROrder");
		            		return showForm(request, response, errors);
						}
					}
	                jpoMemberOrder.setSysUser(jmiMember.getSysUser());
	                jpoMemberOrder.setCompanyCode(loginSysUser.getCompanyCode());
	                jpoMemberOrder.setUserSpCode(jmiMember.getUserCode());
	                jpoMemberOrder.setCountryCode(loginSysUser.getCompanyCode());
				}
			}
			
			if(this.checkPoMemberOrder(jpoMemberOrder, errors)){
				//RequestUtil.cleanOperationSession(request,"addPoMemberROrder");
				return showForm(request, response, errors);
			}
			
			if("1".equals(jpoMemberOrder.getPickup())){//是否为自动提货
				jpoMemberOrder.setFirstName(jpoMemberOrder.getSysUser().getJmiMember().getFirstName());
				jpoMemberOrder.setLastName(jpoMemberOrder.getSysUser().getJmiMember().getLastName());
				jpoMemberOrder.setProvince(jpoMemberOrder.getSysUser().getJmiMember().getProvince());
				jpoMemberOrder.setCity(jpoMemberOrder.getSysUser().getJmiMember().getCity());
				jpoMemberOrder.setDistrict(jpoMemberOrder.getSysUser().getJmiMember().getDistrict());
				jpoMemberOrder.setAddress(jpoMemberOrder.getSysUser().getJmiMember().getAddress());
				jpoMemberOrder.setPostalcode(jpoMemberOrder.getSysUser().getJmiMember().getPostalcode());
				jpoMemberOrder.setPhone(jpoMemberOrder.getSysUser().getJmiMember().getPhone());
				jpoMemberOrder.setMobiletele(jpoMemberOrder.getSysUser().getJmiMember().getMobiletele());
			}
			
    		try{
    			
    			
        		Set jpoMemberOrderSet = null;
        		jpoMemberOrderSet = this.fillInJfoMemberOrderList(request,jpoMemberOrder);//生成订单明细
        		if(jpoMemberOrderSet==null){//检查订单总数是否小于0
            		errors.reject("amount.notEnough", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"amount.notEnough"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberROrder");
            		return showForm(request, response, errors);
        		}
        		
        		Set jpoMemberOrderFeeSet = null;
        		jpoMemberOrderFeeSet = this.fillInJfoMemberOrderFee(request,jpoMemberOrder,jpoMemberOrderSet);//生成订单明细
            	if(jpoMemberOrderFeeSet == null || jpoMemberOrderFeeSet.size()==0){
            		//没有指定物流公司
            		errors.reject("erro.pd.shNo.isEmpty", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"erro.pd.shNo.isEmpty"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberROrder");
            		return showForm(request, response, errors);
            	}
            	
        		this.calcOrderAmount(request,jpoMemberOrderSet,jpoMemberOrderFeeSet,jpoMemberOrder);//计算总PV总金额
    			
    			if("addPoMemberBOrder".equals(strAction)){
    				jpoMemberOrder.setOrderTime(new Date());
    				//String memberOrderNo = sysIdManager.buildIdStr("pono");//生成订单编号
    				//jpoMemberOrder.setMemberOrderNo(memberOrderNo);
    				key="poMemberOrder.added";
    			}else{
    				key="poMemberOrder.updated";
    			}
            	
        		jpoMemberOrderManager.editJpoMemberOrder(jpoMemberOrder, jpoMemberOrderSet, jpoMemberOrderFeeSet);	
        		
        		jpoMemberOrder.setPvAmt(new BigDecimal(0));
        		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
        		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
        		
				
    		}catch(NumberFormatException e){
    			log.error("",e);
        		errors.reject("error.isNotInteger", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"error.isNotInteger"));
        		//RequestUtil.cleanOperationSession(request,"addPoMemberROrder");
        		return showForm(request, response, errors);
    		}catch(Exception e){
    			log.error("",e);
    			errors.reject(e.getMessage(), new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),e.getMessage()));
        		return showForm(request, response, errors);
    		}
    		
		}
		
		saveMessage(request, getText(loginSysUser.getDefCharacterCoding(),key));
        return new ModelAndView(getSuccessView());
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	if("M".equals(loginSysUser.getUserType())){
        	String[] allowedFields = {
        			"payMode",
        			"firstName",
        			"lastName",
        			"province",
        			"city",
        			"district",
        			"town",
        			"address",
        			"building",
        			"postalcode",
        			"email",
        			"phone",
        			"mobiletele",
        			"shippingPay",
        			"shippingDay",
        			"pickup"
        	};
        	binder.setAllowedFields(allowedFields);
    	}else{
        	String[] allowedFields = {
        			"payMode",
        			"firstName",
        			"lastName",
        			"province",
        			"city",
        			"district",
        			"town",
        			"address",
        			"building",
        			"postalcode",
        			"email",
        			"phone",
        			"mobiletele",
        			"sysUser.userCode",
        			"pickup",
        			"remark",
        			"shippingPay",
        			"shippingDay",
        			"payMode"
        	};
        	binder.setAllowedFields(allowedFields);
    	}
		
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
	
    
    /**
     * 判断订单是否通过检验
     * @param jpoMemberOrder
     * @param errors
     * @return
     */
    private boolean checkPoMemberOrder(JpoMemberOrder jpoMemberOrder,BindException errors){
    	boolean isNotPass=false;
		Pattern pattern = null;
		
    	if("CN".equals(jpoMemberOrder.getCompanyCode())){
    		pattern = Pattern.compile("^[1]([0-9]{10})$");
    		Matcher matcher = pattern.matcher(jpoMemberOrder.getMobiletele());
    		if(!matcher.find()){
    			errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
    			isNotPass = true;
    		}
	    	
	    	if(StringUtils.isEmpty(jpoMemberOrder.getCity())){
	    		errors.rejectValue("city", "errors.required",new Object[] {this.getText("shipping.city") }, "");
				isNotPass = true;
	    	}else if(jpoMemberOrder.getCity().length()>20){
				errors.rejectValue("city", "errors.maxlength",new Object[] {this.getText("shipping.city"),"20" }, "");
				isNotPass = true;
	    	}
    	}
    	if(!"CN".equals(jpoMemberOrder.getCompanyCode()) &&!"HK".equals(jpoMemberOrder.getCompanyCode())){
    		if(!"1".equals(jpoMemberOrder.getPickup())){
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getFirstName())){
    				errors.rejectValue("firstName", "errors.required",new Object[] {this.getText("shipping.firstName") }, "");
    				isNotPass = true;
    	    	}else if(jpoMemberOrder.getFirstName().length()>100){
    				errors.rejectValue("firstName", "errors.maxlength",new Object[] {this.getText("shipping.firstName"),"100" }, "");
    				isNotPass = true;
    	    	}
    	    	
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getLastName())){
    				errors.rejectValue("lastName", "errors.required",new Object[] {this.getText("shipping.lastName") }, "");
    				isNotPass = true;
    	    	}else if(jpoMemberOrder.getLastName().length()>100){
    				errors.rejectValue("lastName", "errors.maxlength",new Object[] {this.getText("shipping.lastName"),"100" }, "");
    				isNotPass = true;
    	    	}
    	    	
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getProvince())){
    				errors.rejectValue("province", "errors.required",new Object[] {this.getText("shipping.province") }, "");
    				isNotPass = true;
    	    	}else if(jpoMemberOrder.getProvince().length()>20){
    				errors.rejectValue("province", "errors.maxlength",new Object[] {this.getText("shipping.province"),"20" }, "");
    				isNotPass = true;
    	    	}
    	    	
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getCity())){
    	    		if(!"RU".equals(jpoMemberOrder.getCompanyCode())){
        				errors.rejectValue("city", "errors.required",new Object[] {this.getText("shipping.city") }, "");
        				isNotPass = true;
    	    		}
    	    	}else if(jpoMemberOrder.getCity().length()>20){
    				errors.rejectValue("city", "errors.maxlength",new Object[] {this.getText("shipping.city"),"20" }, "");
    				isNotPass = true;
    	    	}
    	    	
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getDistrict())){
    	    		if("PH".equals(jpoMemberOrder.getCompanyCode())){
        	    		errors.rejectValue("district", "errors.required",new Object[] {this.getText("shipping.district") }, "");
        				isNotPass = true;
    	    		}
    	    	}else if(jpoMemberOrder.getDistrict().length()>20){
    				errors.rejectValue("district", "errors.maxlength",new Object[] {this.getText("shipping.district"),"20" }, "");
    				isNotPass = true;
    	    	}
    	    	
    	    	if("PH".equals(jpoMemberOrder.getCompanyCode())){
	    	    	if(StringUtils.isEmpty(jpoMemberOrder.getTown())){
	        	    	errors.rejectValue("district", "errors.required",new Object[] {this.getText("shipping.town") }, "");
	        			isNotPass = true;
	    	    	}else if(jpoMemberOrder.getTown().length()>20){
	    				errors.rejectValue("district", "errors.maxlength",new Object[] {this.getText("shipping.town"),"20" }, "");
	    				isNotPass = true;
	    	    	}
    			}
    	    	
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getAddress())){
    				errors.rejectValue("address", "errors.required",new Object[] {this.getText("shipping.address") }, "");
    				isNotPass = true;
    	    	}else if(jpoMemberOrder.getAddress().length()>500){
    				errors.rejectValue("address", "errors.maxlength",new Object[] {this.getText("shipping.address"),"500" }, "");
    				isNotPass = true;
    	    	}
    	    	
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getPostalcode())){
    				errors.rejectValue("postalcode", "errors.required",new Object[] {this.getText("shipping.postalcode") }, "");
    				isNotPass = true;
    	    	}else if(jpoMemberOrder.getPostalcode().length()>10){
    				errors.rejectValue("postalcode", "errors.maxlength",new Object[] {this.getText("shipping.postalcode"),"10" }, "");
    				isNotPass = true;
    	    	}else if("JP".equals(jpoMemberOrder.getCompanyCode())){
    			    pattern = Pattern.compile("^([0-9]{3})[-]([0-9]{4})$");
    		    	Matcher matcher = pattern.matcher(jpoMemberOrder.getPostalcode());
    		    	if(!matcher.matches()){
    		    		errors.rejectValue("postalcode", "errors.invalid",new Object[] {this.getText("shipping.postalcode") }, "");
    		    		isNotPass = true;
    		    	}
    	    	}
    	    	
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getPhone())){
    	    		if(!"JP".equals(jpoMemberOrder.getCompanyCode())){
        				errors.rejectValue("phone", "errors.required",new Object[] {this.getText("shipping.phone") }, "");
        				isNotPass = true;
    	    		}
    	    	}else if(jpoMemberOrder.getPhone().length()>20){
    				errors.rejectValue("phone", "errors.maxlength",new Object[] {this.getText("shipping.phone"),"20" }, "");
    				isNotPass = true;
    	    	}
    	    	pattern = Pattern.compile("^([0-9]{10})$");
    	    	if("US".equals(jpoMemberOrder.getCompanyCode())){
    	    		Matcher matcher = pattern.matcher(jpoMemberOrder.getPhone());
    	    		if(!matcher.matches()){
    	    			errors.rejectValue("phone", "errors.phone",new Object[] {this.getText("shipping.phone") }, "");
    	    			isNotPass = true;
    	    		}
    	    	}
    	    	if("JP".equals(jpoMemberOrder.getCompanyCode())){
    	    		boolean isJPPhone = false;
    		    	pattern = Pattern.compile("^([0-9]{2})[-]([0-9]{4})[-]([0-9]{4})$");
    	    		Matcher matcher = pattern.matcher(jpoMemberOrder.getPhone());
    	    		if(matcher.find()){
    	    			isJPPhone = true;
    	    		}
    		    	pattern = Pattern.compile("^([0-9]{4})[-]([0-9]{2})[-]([0-9]{4})$");
    	    		matcher = pattern.matcher(jpoMemberOrder.getPhone());
    	    		if(matcher.find()){
    	    			isJPPhone = true;
    	    		}
    		    	pattern = Pattern.compile("^([0-9]{3})[-]([0-9]{3})[-]([0-9]{4})$");
    	    		matcher = pattern.matcher(jpoMemberOrder.getPhone());
    	    		if(matcher.find()){
    	    			isJPPhone = true;
    	    		}
    	    		if(isJPPhone==false){
    	    			errors.rejectValue("phone", "errors.phone",new Object[] {this.getText("shipping.phone") }, "");
    	    			isNotPass = true;
    	    		}
    	    	}
    	
    			pattern = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getEmail())){
    	    		
    	    	}else if(jpoMemberOrder.getEmail().length()>100){
    				errors.rejectValue("email", "errors.maxlength",new Object[] {this.getText("shipping.email"),"100" }, "");
    				isNotPass = true;
    	    	}else{
    	    		Matcher matcher = pattern.matcher(jpoMemberOrder.getEmail());
    	    		if(!matcher.matches()){
    	    			errors.rejectValue("email", "errors.email",new Object[] {this.getText("shipping.email") }, "");
    	    			isNotPass = true;
    	    		}
    	    	}

    	    	pattern = Pattern.compile("^[0][9]([0-9]{8})$");
    	    	if("PH".equals(jpoMemberOrder.getCompanyCode())){
    	    		pattern = Pattern.compile("^[0][9]([0-9]{9})$");
    	    	}
    	    	if("JP".equals(jpoMemberOrder.getCompanyCode())){
    	    		pattern = Pattern.compile("^([0-9]{3})[-]([0-9]{4})[-]([0-9]{4})$");
    	    	}
    	    	if("ID".equals(jpoMemberOrder.getCompanyCode())){
    	    		pattern = Pattern.compile("^[0]([0-9]*)$");
       	        	
       	        	
       	        }
    	    	if("JP".equals(jpoMemberOrder.getCompanyCode())){
    	    		if(StringUtil.isEmpty(jpoMemberOrder.getMobiletele())){
        				errors.rejectValue("mobiletele", "errors.required",new Object[] {this.getText("shipping.mobiletele") }, "");
        				isNotPass = true;
    	    		}
    	    	}
    	    	Matcher matcher = pattern.matcher(jpoMemberOrder.getMobiletele());
    	    	if(!matcher.matches()&&!"RU".equals(jpoMemberOrder.getCompanyCode())){
    	    		errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
    	    		isNotPass = true;
    	    	}
    		}
    	}
    	return isNotPass;
    }
    
    /**
     * 计算费用
     * @param request
     * @param jpoMemberOrderSet
     * @param jpoMemberOrder
     */
    private void calcOrderAmount(HttpServletRequest request, Set jpoMemberOrderSet, 
    		Set jpoMemberOrderFeeSet, JpoMemberOrder jpoMemberOrder){
    	
    	BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	
    	Iterator its1 = jpoMemberOrderSet.iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			BigDecimal disPrice = jpoMemberOrderList.getDisPrice();
			if(disPrice !=null && disPrice.compareTo(new BigDecimal(0))>0){
				sumPrice = sumPrice.add(disPrice.multiply(
						new BigDecimal(jpoMemberOrderList.getQty())));
			} else {
				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().
						multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}
			sumPV = sumPV.add(jpoMemberOrderList.getPv().
					multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}
    	
    	if(log.isDebugEnabled())
    		log.debug("order sumPrice is:["+sumPrice.toString()+"] and sumPV is:["+sumPV+"]");
    	/*
		 * TODO 按订单总金额或PV打折
		 */
//    	BigDecimal old_sumPirce = sumPrice;
//		String stime = DateUtil.convertDateToString(Calendar.getInstance().getTime());
//		List<JpmSalePromoter> saleList = jpmSalePromoterManager.
//				getSaleByDate(stime, Constants.JPMSALE_ACTIVA, 
//						Constants.JPMSALE_DISCOUNT_ORDER);
//		log.info("saleList size is:"+saleList.size());
//		try {
//			for(JpmSalePromoter sale: saleList){
//				if(jpoMemberOrderManager.isOrderSales(sale, jpoMemberOrder)){
//					if(sale.getAmount().compareTo(new BigDecimal(0))>0 && sale.getPv()>0L){
//						if(sumPrice.compareTo(sale.getAmount())>0 && 
//								sumPV.compareTo(new BigDecimal(sale.getPv()))>0L &&
//								jpoMemberOrderManager.isOrderSales(sale, jpoMemberOrder)){
//							//订单总金额大于促销规定的金额,则打折
//							sumPrice = sumPrice.multiply(sale.getDiscount());
//						}
//					} else if(sale.getAmount().compareTo(new BigDecimal(0))<=0 && sale.getPv()> 0L){
//						if(sumPV.compareTo(new BigDecimal(sale.getPv()))>0L &&
//								jpoMemberOrderManager.isOrderSales(sale, jpoMemberOrder)){
//							sumPrice = sumPrice.multiply(sale.getDiscount());
//						}
//					} else if(sale.getAmount().compareTo(new BigDecimal(0))>0 && sale.getPv() <= 0L){
//						if(sumPrice.compareTo(sale.getAmount())>0 &&
//								jpoMemberOrderManager.isOrderSales(sale, jpoMemberOrder)){
//							sumPrice = sumPrice.multiply(sale.getDiscount());
//						}
//					}
//		    	}
//			}
//		} catch (Exception e) {
//			log.error("",e);
//		}
		log.info("discount sumPrice is:["+sumPrice.toString()+"]");
		//折扣
//		jpoMemberOrder.setDiscountAmount(old_sumPirce.subtract(sumPrice));
		
    	Iterator its2 = jpoMemberOrderFeeSet.iterator();
    	while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
		}
    	jpoMemberOrder.setAmount(sumPrice);
    	jpoMemberOrder.setPvAmt(sumPV);
    }
    
    /**
     * 增加费用
     * @param request
     * @param jpoMemberOrder
     * @param jpoMemberOrderSet
     * @return
     */
    private Set fillInJfoMemberOrderFee(HttpServletRequest request, JpoMemberOrder jpoMemberOrder,Set jpoMemberOrderSet){
    	Set jpoMemberOrderFeeSet = new HashSet(0);
    	if("CN".equals(jpoMemberOrder.getCompanyCode()) || "HK".equals(jpoMemberOrder.getCompanyCode()) ){
        	//物流费
        	JpoMemberOrderFee jpoMemberOrderFee = jpoShippingFeeManager.shippingFeeCalc(jpoMemberOrder, jpoMemberOrderSet, request);
        	if(jpoMemberOrderFee!=null){
        		//重销单物流费店铺出
//        		jpoMemberOrderFee.setFee(new BigDecimal("0"));
        		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
        	}

    	}else if("TW".equals(jpoMemberOrder.getCompanyCode())&&!"1".equals(jpoMemberOrder.getPickup())){
    		BigDecimal sumPrice = new BigDecimal(0);
    		BigDecimal sumPv = new BigDecimal(0);
	    	Iterator its = jpoMemberOrderSet.iterator();
	    	while (its.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its.next();
				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				sumPv = sumPv.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}
	    	
    		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
    		jpoMemberOrderFee.setDetailType("0000");
    		if(sumPv.compareTo(new BigDecimal(70))!=-1){
        		jpoMemberOrderFee.setFee(new BigDecimal(0));
    		}else{
        		jpoMemberOrderFee.setFee(new BigDecimal(150));
    		}
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
    		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
    	}else if("US".equals(jpoMemberOrder.getCompanyCode())){
    		BigDecimal sumTax = new BigDecimal(0);
    		BigDecimal sumShipping = new BigDecimal(0);
	    	Iterator its = jpoMemberOrderSet.iterator();
	    	Map map = new HashMap(0);
	    	
	    	map.put("20008144P01010100101EN0", new BigDecimal("0.03"));//Utah,Galaxy 
	    	map.put("20008144P05010300101EN0", new BigDecimal("0.0625"));//Utah,Energy Cup
	    	map.put("20008144P06010100101EN0", new BigDecimal("0.0625"));//Utah,Intense
	    	map.put("20008104P01010100101EN0", new BigDecimal("0"));//CA,Galaxy 
	    	map.put("20008104P05010300101EN0", new BigDecimal("0.0875"));//CA,Energy Cup
	    	map.put("20008104P06010100101EN0", new BigDecimal("0.0875"));//CA,Intense
	    	map.put("20008128P01010100101EN0", new BigDecimal("0"));//Nevada,Galaxy 
	    	map.put("20008128P05010300101EN0", new BigDecimal("0.0685"));//Nevada,Energy Cup
	    	map.put("20008128P06010100101EN0", new BigDecimal("0.0685"));//Nevada,Intense
	    	while (its.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its.next();
				if("P01010100101EN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
					if(jpoMemberOrderList.getQty()<6){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.06")));
					}else if(jpoMemberOrderList.getQty()<11){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.05")));
					}else{
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.045")));
					}
				}
				if("P05010300101EN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
					if(jpoMemberOrderList.getQty()<6){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.06")));
					}else if(jpoMemberOrderList.getQty()<11){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.05")));
					}else{
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.045")));
					}
				}
				if("P06010100101EN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
					if(jpoMemberOrderList.getQty()<6){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.05")));
					}else if(jpoMemberOrderList.getQty()<11){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.045")));
					}else{
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.0425")));
					}
				}
				if(map.get(jpoMemberOrder.getProvince()+jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())!=null){
						sumTax = sumTax.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal(map.get(jpoMemberOrder.getProvince()+jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo()).toString())));
				}
			}
    		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
    		jpoMemberOrderFee.setDetailType("UPS");
    		jpoMemberOrderFee.setFee(sumShipping);
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrder.setConsumerAmount(sumShipping);
    		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
    		JpoMemberOrderFee jpoMemberOrderFee1 = new JpoMemberOrderFee();
    		jpoMemberOrderFee1.setDetailType("TAX");
    		jpoMemberOrderFee1.setFee(sumTax);
    		jpoMemberOrderFee1.setFeeType("3");//税
    		jpoMemberOrderFee1.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrderFeeSet.add(jpoMemberOrderFee1);
    	}else{
    		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
    		jpoMemberOrderFee.setDetailType("0000");
    		jpoMemberOrderFee.setFee(new BigDecimal(0));
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
    		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
    	}
    	return jpoMemberOrderFeeSet;
    }
  
    
    /**
     * 生成订单明细
     * @param request
     * @param poMemberOrder
     * @param defSysUser
     * @return
     */
    private Set fillInJfoMemberOrderList(HttpServletRequest request, JpoMemberOrder jpoMemberOrder){
    	String[] qty = request.getParameterValues("qty");
        String[] g_no = request.getParameterValues("g_no");
        Set jpoMemberOrderListSet = new HashSet(0);
        
        BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	
		for(int i_no = 0 ; i_no < g_no.length ; i_no++){
			if( !StringUtils.isEmpty(qty[i_no]) && !"0".equals(qty[i_no])){
				if(Integer.parseInt(qty[i_no])<0){
					return null;
				}
				JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.getJpmProductSaleTeamType(g_no[i_no]);
				if(jpmProductSale==null){
					return null;
				}
				JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
				
				if(jpmProductSale.getDiscountPrice()!=null && 
						jpmProductSale.getDiscountPrice().compareTo(new BigDecimal(0))>0){
					//TODO 如果有打折,使用打折后的费用
					jpoMemberOrderList.setDisPrice(jpmProductSale.getDiscountPrice());
				}
				jpoMemberOrderList.setPrice(jpmProductSale.getPrice());
				jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
				jpoMemberOrderList.setPv(jpmProductSale.getPv());
				jpoMemberOrderList.setProNo(jpmProductSale.getJpmProductSaleNew().getProductNo());
				jpoMemberOrderList.setQty(Integer.parseInt(qty[i_no]));
    			jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
    			jpoMemberOrderList.setVolume(jpmProductSale.getJpmProductSaleNew().getVolume());
    			jpoMemberOrderList.setWeight(jpmProductSale.getJpmProductSaleNew().getWeight());
    			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    			jpoMemberOrderListSet.add(jpoMemberOrderList);
				
			}
		}
		

		if(jpoMemberOrderListSet.size()==0){
			log.warn("order list is null.");
			return null;
		}
    	return jpoMemberOrderListSet;
    }
 
	public void setJpoShippingFeeManager(JpoShippingFeeManager jpoShippingFeeManager) {
		this.jpoShippingFeeManager = jpoShippingFeeManager;
	}
	
	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}
	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public void setJmiAddrBookManager(JmiAddrBookManager jmiAddrBookManager) {
		this.jmiAddrBookManager = jmiAddrBookManager;
	}
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

	public JpmSalePromoterManager getJpmSalePromoterManager() {
		return jpmSalePromoterManager;
	}

	public void setJpmSalePromoterManager(
			JpmSalePromoterManager jpmSalePromoterManager) {
		this.jpmSalePromoterManager = jpmSalePromoterManager;
	}
	
}
