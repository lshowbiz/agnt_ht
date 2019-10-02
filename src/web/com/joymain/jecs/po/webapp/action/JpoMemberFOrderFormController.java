package com.joymain.jecs.po.webapp.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.joymain.jecs.mi.service.JmiMemberDubboService;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.webapp.action.JmiTeamManagerController;
import com.joymain.jecs.pd.service.PdShipFeeManager;
import com.joymain.jecs.pm.model.JmiMemberTeam;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.po.service.JpoShippingFeeManager;
import com.joymain.jecs.sys.model.SysUser;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 会员首购单表单
 * @author Alvin
 */
public class JpoMemberFOrderFormController extends BaseFormController {
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private JmiMemberManager jmiMemberManager = null;
	private JmiAddrBookManager jmiAddrBookManager = null;
	private AlCountryManager alCountryManager = null;
	private JpmProductSaleManager jpmProductSaleManager = null;
	private JpoShippingFeeManager jpoShippingFeeManager = null;
	private PdShipFeeManager pdShipManager = null;
	private JmiMemberTeamManager jmiMemberTeamManager;
	
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		SysUser operatorSysUser = SessionLogin.getOperatorUser(request);

		String moId = request.getParameter("moId");
		JpoMemberOrder jpoMemberOrder = null;
		String strAction = request.getParameter("strAction");

		if ("deletePoMemberFOrder".equals(strAction)
				|| "editPoMemberFOrder".equals(strAction)
				&& !StringUtils.isEmpty(moId)) {
			jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);
			if ("1".equals(jpoMemberOrder.getIsPay())) {
				throw new AppException("订单已支付!");
			}
			if ("M".equals(loginSysUser.getUserType())) {
				if (!jpoMemberOrder.getSysUser().getUserCode()
						.equals(loginSysUser.getUserCode())) {
					throw new AppException("订单不属于当前登录者!");
				}
			} else {
				if (!jpoMemberOrder.getCompanyCode().equals(
						loginSysUser.getCompanyCode())) {
					throw new AppException("订单不属于当前登录者!");
				}
			}
			if (!"1".equals(jpoMemberOrder.getStatus())
					|| !"1".equals(jpoMemberOrder.getSubmitStatus())) {
				throw new AppException("订单不能修改或删除!");
			}
		} else {
			jpoMemberOrder = new JpoMemberOrder();

			if ("M".equals(loginSysUser.getUserType())) {
				jpoMemberOrder.setSysUser(loginSysUser);
				jpoMemberOrder.setCompanyCode(loginSysUser.getCompanyCode());
				jpoMemberOrder.setUserSpCode(loginSysUser.getUserCode());
				JmiMember jmiMember = jmiMemberManager
						.getJmiMember(loginSysUser.getUserCode());
				jpoMemberOrder.setCountryCode(jmiMember.getCountryCode());
			} else {
				jpoMemberOrder.setSysUser(new SysUser());
			}

			jpoMemberOrder.setOrderUserCode(operatorSysUser.getUserCode());
			jpoMemberOrder.setLocked("0");
			jpoMemberOrder.setOrderType("1");
			jpoMemberOrder.setPickup("0");// 是否自动提货
			jpoMemberOrder.setStatus("1");
			jpoMemberOrder.setSubmitStatus("1");
			jpoMemberOrder.setIsPay("0");

			jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
			jpoMemberOrder.setAmount(new BigDecimal(0));
			jpoMemberOrder.setPvAmt(new BigDecimal(0));
			jpoMemberOrder.setPayMode("0");// 付款方式
			jpoMemberOrder.setIsSpecial("0");// 是否为特殊订单
		}

		 //新的获取团队的方法,返回顶点
        String teamCode=jmiMemberTeamManager.getTeamStr(loginSysUser);
        
      //和前台一致的获取团队方法 2015-02-28 w
//        String teamCode=jmiMemberTeamManager.teamStr(loginSysUser);//获取团队编号
		jpoMemberOrder.setTeamCode(teamCode);
		// 地址簿
		CommonRecord crm = new CommonRecord();
		crm.addField("userCode", loginSysUser.getUserCode());
		if (!loginSysUser.getIsMember()) {// 如果不是会员
			String userCode = request.getParameter("sysUser.userCode");
			if (!StringUtils.isEmpty(userCode)) {
				crm.addField("userCode", userCode);
				JmiMember jmiMember = jmiMemberManager.getJmiMember(userCode);
				if (jmiMember != null
						&& jmiMember.getCompanyCode().equals(
								operatorSysUser.getCompanyCode())) {
					jpoMemberOrder.setSysUser(jmiMember.getSysUser());
				}
			}
		}
		List jmiAddrBooks = jmiAddrBookManager.getJmiAddrBooksByCrm(crm,
				new Pager(request, 0));
		request.setAttribute("jmiAddrBooks", jmiAddrBooks);

		// 收货地区
		AlCountry alCountry = (AlCountry) alCountryManager
				.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
		alCountry.getAlStateProvinces().iterator();
		request.setAttribute("alStateProvinces",
				alCountry.getAlStateProvinces());

		if (!loginSysUser.getIsMember() && StringUtils.isEmpty(moId)
				&& "TW".equals(loginSysUser.getCompanyCode())
				&& alCountry.getAlStateProvinces().size() == 1) {
			AlStateProvince alStateProvince = (AlStateProvince) alCountry
					.getAlStateProvinces().iterator().next();
			jpoMemberOrder.setProvince(alStateProvince.getStateProvinceId()
					.toString());
		}
		// 会员订购时级别选择时的判断
		String levelVal = request.getParameter("userVal");
		if (!StringUtil.isEmpty(levelVal)) {
			BigDecimal pv = new BigDecimal(ConfigUtil.getConfigValue(
					loginSysUser.getCompanyCode().toUpperCase(), "cardtype."
							+ levelVal + ".value"));
			request.setAttribute("pv", pv);
		}

		// 获取商品
		Map jpmProductSales = null;

		if (loginSysUser.getIsMember()) {
			jpmProductSales = jpmProductSaleManager.getPmProductSalesNew(
					loginSysUser.getCompanyCode(), Constants.FIRST_PURCHASE,
					"1", teamCode, loginSysUser);// 会员用
		} else {
			jpmProductSales = jpmProductSaleManager.getPmProductSalesNew(
					loginSysUser.getCompanyCode(), Constants.FIRST_PURCHASE,
					"2", teamCode, loginSysUser);// 公司用
		}

		request.setAttribute("jpmProductSales", jpmProductSales);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = DateUtil.getDateOffset(new Date(), 5, 3);
		request.setAttribute("toDay", format.format(date));

		if (RequestUtil.isMobileRequest(request)) {
			this.setFormView("/mobile/po/jpoMemberFOrderForm");
		} else {
			this.setFormView("/po/jpoMemberFOrderForm");
		}
		request.setAttribute("effectiveValid", "false");
		return jpoMemberOrder;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		//SysUser operatorSysUser = SessionLogin.getOperatorUser(request);

		JpoMemberOrder jpoMemberOrder = (JpoMemberOrder) command;
		//boolean isNew = (jpoMemberOrder.getMoId() == null);
		//Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		if ("deletePoMemberFOrder".equals(strAction)) {
			jpoMemberOrderManager.removeJpoMemberOrder(jpoMemberOrder.getMoId()
					.toString());
			key = "poMemberOrder.deleted";
		} else {
			
			JmiMember jmiMember = jmiMemberManager.
					getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
			
			if ("C".equals(loginSysUser.getUserType())) {
				
				if (jmiMember == null) {
					errors.reject("miMember.notFound", new Object[] {},
							LocaleUtil.getLocalText(
									loginSysUser.getDefCharacterCoding(),
									"miMember.notFound"));
				
					return showForm(request, response, errors);
				} else {
					if (!loginSysUser.getCompanyCode().equals(
							jmiMember.getCompanyCode())) {
						errors.reject("miMember.notFound", new Object[] {},
								LocaleUtil.getLocalText(
										loginSysUser.getDefCharacterCoding(),
										"miMember.notFound"));
						
						return showForm(request, response, errors);
					}
					jpoMemberOrder.setSysUser(jmiMember.getSysUser());
					jpoMemberOrder.setCompanyCode(jmiMember.getCompanyCode());// 分公司
					jpoMemberOrder.setUserSpCode(jmiMember.getUserCode());
					jpoMemberOrder.setCountryCode(jmiMember.getCountryCode());// 国别
				}
			}
			
			String userCode = jpoMemberOrder.getSysUser().getUserCode();
			//String userType = jmiMember.getMemberType();
			
			if ("addPoMemberFOrder".equals(strAction)) {
				
				if(validateOrder(jpoMemberOrder, errors, loginSysUser)){
		    		return showForm(request, response, errors);
		    	}
				
				CommonRecord crm = new CommonRecord();
				crm.addField("sysUser.userCode", userCode);
				crm.addField("orderType", "1");
				List jpoMemberOrders = jpoMemberOrderManager
						.getJpoMemberOrdersByCrm(crm, new Pager(request, 0));
				
				boolean isfreez = isFreeze(jmiMember.getFreezeStatus(),errors,
						loginSysUser.getDefCharacterCoding());
				if(isfreez){
					return showForm(request, response, errors);
				}
				/*
				 * 判断首单是否存在
				 */
				Integer notfirst = jmiMember.getNotFirst();
				if (jpoMemberOrders.size() != 0 || (notfirst!=null && notfirst==1)) {
					errors.reject("poMemberFOrder.isExist", new Object[] {},
							LocaleUtil.getLocalText(
									loginSysUser.getDefCharacterCoding(),
									"poMemberFOrder.isExist"));
					
					return showForm(request, response, errors);
				}
			}

			if (this.checkPoMemberOrder(jpoMemberOrder, errors)) {
				// RequestUtil.cleanOperationSession(request,"addPoMemberFOrder");
				return showForm(request, response, errors);
			}
			
			if ("1".equals(jpoMemberOrder.getPickup())) {
				jpoMemberOrder.setFirstName(jpoMemberOrder.getSysUser()
						.getJmiMember().getFirstName());
				jpoMemberOrder.setLastName(jpoMemberOrder.getSysUser()
						.getJmiMember().getLastName());
				jpoMemberOrder.setProvince(jpoMemberOrder.getSysUser()
						.getJmiMember().getProvince());
				jpoMemberOrder.setCity(jpoMemberOrder.getSysUser()
						.getJmiMember().getCity());
				jpoMemberOrder.setDistrict(jpoMemberOrder.getSysUser()
						.getJmiMember().getDistrict());
				jpoMemberOrder.setAddress(jpoMemberOrder.getSysUser()
						.getJmiMember().getAddress());
				jpoMemberOrder.setPostalcode(jpoMemberOrder.getSysUser()
						.getJmiMember().getPostalcode());
				jpoMemberOrder.setPhone(jpoMemberOrder.getSysUser()
						.getJmiMember().getPhone());
				jpoMemberOrder.setMobiletele(jpoMemberOrder.getSysUser()
						.getJmiMember().getMobiletele());
			}

			try {
				Set jpoMemberOrderSet = null;
				jpoMemberOrderSet = this.fillInJfoMemberOrderList(request,
						jpoMemberOrder);// 生成订单明细
				
				if (jpoMemberOrderSet == null) {// 检查订单总数是否小于0
					errors.reject("amount.notEnough", new Object[] {},
							LocaleUtil.getLocalText(
									loginSysUser.getDefCharacterCoding(),
									"amount.notEnough"));
					// RequestUtil.cleanOperationSession(request,"addPoMemberFOrder");
					return showForm(request, response, errors);
				}
				Set jpoMemberOrderFeeSet = null;
				jpoMemberOrderFeeSet = this.fillInJfoMemberOrderFee(request,
						jpoMemberOrder, jpoMemberOrderSet);// 生成订单明细
				if (jpoMemberOrderFeeSet == null
						|| jpoMemberOrderFeeSet.size() == 0) {// 没有算物流费
					// 没有指定物流公司
					errors.reject("erro.pd.shNo.isEmpty", new Object[] {},
							LocaleUtil.getLocalText(
									loginSysUser.getDefCharacterCoding(),
									"erro.pd.shNo.isEmpty"));
					// RequestUtil.cleanOperationSession(request,"addPoMemberFOrder");
					return showForm(request, response, errors);
				}
        		
				BigDecimal result = calcOrderAmount(request, jpoMemberOrderSet,
						jpoMemberOrderFeeSet, jpoMemberOrder);// 计算总PV总金额.
				
				//ajm 团队
//				String temCode = jmiMemberTeamManager.teamStr(jpoMemberOrder.getSysUser());
//				if(temCode.equals("CN98708037"))
//				{
//					String pv_value = ConfigUtil.getConfigValue("CN", "cn98708037.pv");
//					if(jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(pv_value)) < 0){
//						errors.reject("pv.notEnough", new Object[] {},
//								LocaleUtil.getLocalText("CN","pv.notEnough"));
//						return showForm(request, response, errors);
//					}
//				}
//				
//				String pv_value = ConfigUtil.getConfigValue("CN", "cardtype.1.value");
//				if(jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(pv_value)) < 0)
//				{
//					errors.reject("pv.notEnough", new Object[] {},
//							LocaleUtil.getLocalText("CN","pv.notEnough"));
//					return showForm(request, response, errors);
//				}
				
				
				if ("addPoMemberFOrder".equals(strAction)) {
					jpoMemberOrder.setOrderTime(new Date());
					key = "poMemberOrder.added";
				} else {
					key = "poMemberOrder.updated";
				}
				// 月刊分12月发送提示
				/*java.util.Calendar startc = java.util.Calendar.getInstance();
				startc.set(2012, 4, 12, 00, 00, 00);
				java.util.Calendar endc = java.util.Calendar.getInstance();
				endc.set(2019, 11, 31, 23, 59, 59);
				java.util.Date startDate = startc.getTime();
				java.util.Date endDate = endc.getTime();
				Date curDate = new Date();
				if ((curDate.after(startDate)) && (curDate.before(endDate))
						&& "CN".equals(jpoMemberOrder.getCompanyCode())) {
					Iterator its1 = jpoMemberOrderSet.iterator();
					while (its1.hasNext()) {
						JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1
								.next();
						if ("P08520100101CN0".equals(jpoMemberOrderList
								.getJpmProductSaleTeamType()
								.getJpmProductSaleNew().getJpmProduct()
								.getProductNo())) {
							key = "erro.pd.shNo.Monthly";
						}

					}
				}*/
				jpoMemberOrderManager.editJpoMemberOrder(jpoMemberOrder,
						jpoMemberOrderSet, jpoMemberOrderFeeSet);
			} catch (NumberFormatException e) {
				errors.reject("error.isNotInteger", new Object[] {}, LocaleUtil
						.getLocalText(loginSysUser.getDefCharacterCoding(),
								"error.isNotInteger"));
				// RequestUtil.cleanOperationSession(request,"addPoMemberFOrder");
				return showForm(request, response, errors);
			}catch(AppException appex){
				errors.reject(appex.getErrMsg());
				log.error("",appex);
				return showForm(request, response, errors);
			}catch (Exception e){
				errors.reject("error.isNotInteger", e.getMessage());
				log.error("",e);
				return showForm(request, response, errors);
			}
		}
		saveMessage(request, getText(loginSysUser.getDefCharacterCoding(), key));
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
		// TODO Auto-generated method stub
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
    		    	if(!matcher.find()){
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
    	    	if(!"US".equals(jpoMemberOrder.getCompanyCode())){
        	    	Matcher matcher = pattern.matcher(jpoMemberOrder.getMobiletele());
        	    	if(!matcher.matches()&&!"RU".equals(jpoMemberOrder.getCompanyCode())){
        	    		errors.rejectValue("mobiletele", "errors.phone",new Object[] {this.getText("miMember.mobiletele") }, "");
        	    		isNotPass = true;
        	    	}
    	    	}
    		}
    	}
    	return isNotPass;
    }
    
    /**
     * 检查订单总价钱
     * @param poMemberOrder
     * @return
     */
    private int checkAmount(JpoMemberOrder jpoMemberOrder){
    	
    	//商務會員參加費用 
    	if("4".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){//广州团队
    		if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getOriCard())){//原来团队的卡别，银级卡别
    			BigDecimal pv1 = new BigDecimal("140");//中脉70pv,广州转进来的团队要140pv
    			if(jpoMemberOrder.getPvAmt().compareTo(pv1)==-1){
    				return 1;//PV不足升一级
    			}
    		}else{//
    			BigDecimal pv1 = new BigDecimal("70");
    			if(jpoMemberOrder.getPvAmt().compareTo(pv1)==-1){
    				return 1;//PV不足升一级
    			}
    		}
    	}
    	//广州排毒团体
    	JmiMember pdMiMember =jmiMemberManager.getJmiMember("CN10829722");
    	if(pdMiMember!=null ){
    		String lcTreeIndex=pdMiMember.getJmiRecommendRef().getTreeIndex();
    		String loninTreeIndex=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
    		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, lcTreeIndex.length());
    		if(loninTreeIndex.length() >= lcTreeIndex.length() && rmemberIndexTmp.equals(lcTreeIndex)){
    			BigDecimal orderPv = jpoMemberOrder.getPvAmt();
    			BigDecimal pv1 = new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.4.value"));//钻石级pv1400pv
            	if(pv1.compareTo(orderPv)==1){
            		return 1;//PV不足升一级
            	}
    		}
    	}
    	
    
		java.util.Calendar endc=java.util.Calendar.getInstance();
		endc.set(2012, 8, 28, 23, 59, 59);
		java.util.Date endDate=endc.getTime();
		Date curDate=new Date();
    	
		//东方药林
    	if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType()) && !"0".equals(jpoMemberOrder.getSysUser().getJmiMember().getOriCard()) ){
        	BigDecimal pv1 = new BigDecimal("70");
        	if(pv1.compareTo(jpoMemberOrder.getPvAmt())!=0){//小于70pv
        		return 2;//YL会员70PV激活
        	}
    	}else{
    		BigDecimal orderPv = jpoMemberOrder.getPvAmt();
    		if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){//麦酷团队
    			orderPv=orderPv.add(jpoMemberOrder.getSysUser().getJmiMember().getOriPv());//原来的pv
    		}
    		if("TW".equals(jpoMemberOrder.getCompanyCode()) || "PH".equals(jpoMemberOrder.getCompanyCode())){//台湾 菲律宾
    			if(orderPv.compareTo(new BigDecimal(0))==1){
	            	BigDecimal pv1 = new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.1.value"));//银级
	            	if(pv1.compareTo(orderPv)==1){
	            		return 1;//PV不足升一级
	            	}
    			}else{
	            	BigDecimal pv5 = new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.5.value"));//优惠顾客
	            	if(pv5.compareTo(orderPv)==1){
	            		return 1;//PV不足升一级
	            	}
    			}
    		}else if("CN".equals(jpoMemberOrder.getCompanyCode()) )//优惠顾客
    		{
    			if((curDate.after(endDate))){
	    			BigDecimal pv5 = new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.5.value"));
	            	if(pv5.compareTo(orderPv)==1){
	            		return 1;//PV不足升一级
	            	}
    			}else
    			{
    				BigDecimal pv1 = new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.1.value"));
                	if(pv1.compareTo(orderPv)==1){
                		return 1;//PV不足升一级
                	}
    			}
            	
    		}else if(!"CN".equals(jpoMemberOrder.getCompanyCode())){    			
            	BigDecimal pv1 = new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.1.value"));//银级
            	if(pv1.compareTo(orderPv)==1){
            		return 1;//PV不足升一级
            	}
    		}
    	}
    	return 0;//成功
    }
    
    /**
	 * 计算费用
	 * 
	 * @param request
	 * @param jpoMemberOrderSet
	 * @param jpoMemberOrder
	 */
	private BigDecimal calcOrderAmount(HttpServletRequest request,
			Set jpoMemberOrderSet, Set jpoMemberOrderFeeSet,
			JpoMemberOrder jpoMemberOrder) {

		BigDecimal	valid=new BigDecimal("0");
		
		BigDecimal sumPrice = new BigDecimal(0);
		BigDecimal sumPV = new BigDecimal(0);
		BigDecimal sumPriceF = new BigDecimal(0);// 辅销品价格总计

		Iterator its1 = jpoMemberOrderSet.iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1
					.next();
			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(
					new BigDecimal(jpoMemberOrderList.getQty())));
			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(
					new BigDecimal(jpoMemberOrderList.getQty())));
		}
		if(sumPrice.compareTo(new BigDecimal(0))>0){
    		valid = sumPrice;
    	}
		
		Iterator its2 = jpoMemberOrderFeeSet.iterator();
		while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2
					.next();
			sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
		}

		jpoMemberOrder.setAmount(sumPrice);
		jpoMemberOrder.setPvAmt(sumPV);
		return valid;
	}
    
    /**
     * 增加费用
     * @param request
     * @param jpoMemberOrder
     * @return
     */
    private Set fillInJfoMemberOrderFee(HttpServletRequest request, JpoMemberOrder jpoMemberOrder,Set jpoMemberOrderSet){
    	Set jpoMemberOrderFeeSet = new HashSet(0);
    	if("CN".equals(jpoMemberOrder.getCompanyCode()) || "HK".equals(jpoMemberOrder.getCompanyCode()) ){
        	//物流费 
        	//JpoMemberOrderFee jpoMemberOrderFee = this.shippingFeeCalc(jpoMemberOrder, jpoMemberOrderSet, request);
    		JpoMemberOrderFee jpoMemberOrderFee = jpoShippingFeeManager.
					shippingFeeCalc(jpoMemberOrder, jpoMemberOrderSet, request);
    		
        	if(jpoMemberOrderFee!=null){
        		jpoMemberOrderFee.setFee(new BigDecimal("0"));        		
        		///**罗婷  新物流费**/
        		java.util.Calendar startc2=java.util.Calendar.getInstance();
        		startc2.set(2011, 11, 10, 00, 00, 00);
        		Date curDate2=new Date();
        		java.util.Date startDate2=startc2.getTime();
        		if((curDate2.after(startDate2)) && "CN".equals(jpoMemberOrder.getCompanyCode())){
        			BigDecimal sumPrice = new BigDecimal(0);
        	    	Iterator its = jpoMemberOrderSet.iterator();
        	    	while (its.hasNext()) {
        				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its.next();
        				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
        			}
        		BigDecimal feePrice = new BigDecimal(0);
        		feePrice=pdShipManager.countFee(sumPrice.toString(), "1", jpoMemberOrder.getProvince(),true);//订单总金额小于6000按订单收货地址的省份收钱
        		jpoMemberOrderFee.setFee(feePrice);
        		jpoMemberOrder.setConsumerAmount(new BigDecimal("0"));//不需要店铺出钱
        		}
      /*  		java.util.Calendar endc=java.util.Calendar.getInstance();
             	endc.set(2013, 1, 9, 00, 00, 00);
        		java.util.Date endDate=endc.getTime();
        		Date curDate=new Date();
        		if(curDate.before(endDate)){
        		//田阳团队1不算物流费
        		JmiMember pdMiMember =jmiMemberManager.getJmiMember("CN18766575");//田阳团队1(推荐网)
        		if(pdMiMember!=null){
	    		String lcTreeIndex=pdMiMember.getJmiRecommendRef().getTreeIndex();
        		String loninTreeIndex=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
        		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, lcTreeIndex.length());
	    		if(loninTreeIndex.length() >= lcTreeIndex.length() && rmemberIndexTmp.equals(lcTreeIndex)){
	    			jpoMemberOrderFee.setFee(new BigDecimal("0"));
	    			jpoMemberOrder.setConsumerAmount(new BigDecimal("0"));
	    		      }
	    	    	}
        		}*/
        		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
        	}
/*        	if("5".equals(jpoMemberOrder.getPayMode())){
        		//快钱费
        		JpoMemberOrderFee jpoMemberOrderFee1 = new JpoMemberOrderFee();
        		jpoMemberOrderFee1.setJpoMemberOrder(jpoMemberOrder);
        		jpoMemberOrderFee1.setFee(sumPrice.multiply(new BigDecimal("0.005")));
        		jpoMemberOrderFee1.setFeeType("2");//手续费
        		jpoMemberOrderFee1.setDetailType("BILL99");
        		jpoMemberOrderFeeSet.add(jpoMemberOrderFee1);
        	}*/
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
    		if(sumPv.compareTo(new BigDecimal(70))!=-1){//台湾订单pv大于70pv则不需要物流费，小于则需要150元物流费
        		jpoMemberOrderFee.setFee(new BigDecimal(0));
    		}else{
        		jpoMemberOrderFee.setFee(new BigDecimal(150));
    		}
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
    		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
    	}else if("US".equals(jpoMemberOrder.getCompanyCode())){
    		BigDecimal sumTax = new BigDecimal(0);//税
    		BigDecimal sumShipping = new BigDecimal(0);//手续费
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
				if("P01010100101EN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){// 	生态保健食品系列 
					if(jpoMemberOrderList.getQty()<6){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.06")));
					}else if(jpoMemberOrderList.getQty()<11){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.05")));
					}else{
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.045")));
					}
				}
				if("P05010300101EN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){//生态能量水系列 
					if(jpoMemberOrderList.getQty()<6){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.06")));
					}else if(jpoMemberOrderList.getQty()<11){
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.05")));
					}else{
						sumShipping = sumShipping.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())).multiply(new BigDecimal("0.045")));
					}
				}
				if("P06010100101EN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){//化妆品 
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
			jpoMemberOrderFee.setFeeType("1");// 物流费
			jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
			jpoMemberOrder.setConsumerAmount(sumShipping);
			jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
			JpoMemberOrderFee jpoMemberOrderFee1 = new JpoMemberOrderFee();
			jpoMemberOrderFee1.setDetailType("TAX");
			jpoMemberOrderFee1.setFee(sumTax);
			jpoMemberOrderFee1.setFeeType("3");// 税
			jpoMemberOrderFee1.setJpoMemberOrder(jpoMemberOrder);
			jpoMemberOrderFeeSet.add(jpoMemberOrderFee1);
		} else {// 除中国，美国，台湾其他国家不用算物流费
			JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
			jpoMemberOrderFee.setDetailType("0000");
			jpoMemberOrderFee.setFee(new BigDecimal(0));
			jpoMemberOrderFee.setFeeType("1");// 物流费
			jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
			jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
			jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
		}
		return jpoMemberOrderFeeSet;
	}

	/**
	 * 计算物流费
	 * 
	 * @param jpoShippingFee
	 * @param sum
	 * @param dtProduct
	 * @param request
	 * @return
	 */
	private JpoMemberOrderFee shippingFeeCalc(JpoMemberOrder jpoMemberOrder,
			Set jpoMemberOrderSet, HttpServletRequest request) {
		if ("CN".equals(jpoMemberOrder.getCompanyCode())) {
			int dtProduct = 0;
			BigDecimal sumWeight = new BigDecimal(0);
			BigDecimal sumVolume = new BigDecimal(0);
			Iterator its1 = jpoMemberOrderSet.iterator();
			LinkedHashMap list = ListUtil.getListOptions("CN", "dtw.special");// 特殊大田寄宅急送的产品，重量，体积
			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1
						.next();
				sumWeight = sumWeight.add(jpoMemberOrderList
						.getJpmProductSaleTeamType().getJpmProductSaleNew()
						.getWeight()
						.multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				sumVolume = sumVolume.add(jpoMemberOrderList
						.getJpmProductSaleTeamType().getJpmProductSaleNew()
						.getVolume()
						.multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				/*
				 * if("P04010118001CN0".equals(jpoMemberOrderList.getJpmProductSale
				 * ().getJpmProduct().getProductNo())){ dtProduct = 1; }
				 * if("P04010115001CN0"
				 * .equals(jpoMemberOrderList.getJpmProductSale
				 * ().getJpmProduct().getProductNo())){ dtProduct = 1; }
				 * if("P08010100101CN0"
				 * .equals(jpoMemberOrderList.getJpmProductSale
				 * ().getJpmProduct().getProductNo())){ dtProduct = 1; }
				 * if("P04010119001CN0"
				 * .equals(jpoMemberOrderList.getJpmProductSale
				 * ().getJpmProduct().getProductNo())){ dtProduct = 1; }
				 * if("P08130100101CN0"
				 * .equals(jpoMemberOrderList.getJpmProductSale
				 * ().getJpmProduct().getProductNo())){ dtProduct = 1; }
				 * if("P08120100101CN0"
				 * .equals(jpoMemberOrderList.getJpmProductSale
				 * ().getJpmProduct().getProductNo())){ dtProduct = 1; }
				 * if("P08110100101CN0"
				 * .equals(jpoMemberOrderList.getJpmProductSale
				 * ().getJpmProduct().getProductNo())){ dtProduct = 1; }
				 */
				if (list.get(jpoMemberOrderList.getJpmProductSaleTeamType()
						.getJpmProductSaleNew().getJpmProduct().getProductNo()) != null) {// 判断产品是否属于大田宅急送
					dtProduct = 1;// 大田
				}
			}
			if ("163732".equals(jpoMemberOrder.getProvince())) {// 新疆维吾尔自治区
				dtProduct = 0;// 宅急送
			}
			CommonRecord crm = new CommonRecord();
			crm.addField("countryCode", jpoMemberOrder.getCountryCode());// 国别
			crm.addField("province", jpoMemberOrder.getProvince());// 省份
			crm.addField("city", jpoMemberOrder.getCity());// 城市
			crm.addField("district", jpoMemberOrder.getDistrict());// 地区
			if (dtProduct == 1) {
				// 大田
				crm.addField("shippingType", "DTW");
			} else if (dtProduct == 0) {
				// 宅急送
				crm.addField("shippingType", "ZJS");
			}

			List jpoShippingFees = jpoShippingFeeManager
					.getJpoShippingFeesByCrm(crm, new Pager(request, 0));
			if (jpoShippingFees.size() > 0) {
				JpoShippingFee jpoShippingFee = (JpoShippingFee) jpoShippingFees
						.get(0);
				JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
				jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
				jpoMemberOrderFee.setFeeType("1");// 物流费
				jpoMemberOrderFee.setDetailType(jpoShippingFee
						.getShippingType());// 快钱 DTW ZJS
				BigDecimal sumPrice = new BigDecimal(0);
				if (dtProduct == 1) {
					// 大田
					sumPrice = sumVolume.multiply(jpoShippingFee
							.getShippingFee());
					sumPrice = sumPrice.add(jpoShippingFee.getShippingRefee());// 续重费用
				} else if (dtProduct == 0) {
					// 宅急送
					if (sumWeight.compareTo(new BigDecimal(1)) == 1) {// 重量为1
																		// (5-1)*shippingRefee+1*shippingFee;
						BigDecimal sumSubWeight = sumWeight.subtract(
								new BigDecimal(1)).setScale(0,
								BigDecimal.ROUND_UP);
						sumPrice = sumPrice
								.add(jpoShippingFee.getShippingFee());
						sumPrice = sumPrice.add(sumSubWeight
								.multiply(jpoShippingFee.getShippingRefee()));
					} else {
						sumPrice = sumPrice
								.add(jpoShippingFee.getShippingFee());
					}
				}
				jpoMemberOrderFee.setFee(sumPrice);
				jpoMemberOrder.setConsumerAmount(sumPrice);

				return jpoMemberOrderFee;
			}
		} else if ("HK".equals(jpoMemberOrder.getCompanyCode())) {
			JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
			jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
			jpoMemberOrderFee.setFeeType("1");// 物流费
			jpoMemberOrderFee.setDetailType("0000");
			jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
			return jpoMemberOrderFee;
		} else {

		}
		return null;
	}

	/**
	 * 生成订单明细
	 * 
	 * @param request
	 * @param poMemberOrder
	 * @param defSysUser
	 * @return
	 */
	private Set fillInJfoMemberOrderList(HttpServletRequest request,
			JpoMemberOrder jpoMemberOrder) throws Exception{
		// 是否强制绑定事业锦囊，true为否，false为是
		//String effectiveValid = request.getParameter("effectiveValid");
		String[] qty = request.getParameterValues("qty");
		String[] g_no = request.getParameterValues("g_no");
		Set jpoMemberOrderListSet = new HashSet(0);
		boolean syjn = false;
		BigDecimal sumPrice_a = new BigDecimal(0);
		
		for (int i_no = 0; i_no < g_no.length; i_no++) {
			if (!StringUtils.isEmpty(qty[i_no]) && !"0".equals(qty[i_no])) {
				if (Integer.parseInt(qty[i_no]) < 0) {// 验证不为负数
					return null;
				}
				// 获取value，判断产品是否存在
				JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager
						.getJpmProductSaleTeamType(g_no[i_no]);

				if (jpmProductSale == null) {
					return null;
				}
				
				JpmProduct pro = jpmProductSale.
						getJpmProductSaleNew().getJpmProduct();
				
				//明细中是否存在事业锦囊,产品编号：P08520100101CN0
				if(pro.getProductNo().equalsIgnoreCase("P08520100101CN0")){
					syjn = true;
				}
				//A 代表辅销类，D代表直销类
				if(pro.getSmNo().equalsIgnoreCase("A")){
					//计算订单明细中，辅销品总金额
					sumPrice_a = sumPrice_a.add(jpmProductSale.getPrice());
				}
				
				JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
				jpoMemberOrderList.setPrice(jpmProductSale.getPrice());
				jpoMemberOrderList.setProNo(jpmProductSale.getJpmProductSaleNew().getProductNo());
				jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
				jpoMemberOrderList.setPv(jpmProductSale.getPv());
				jpoMemberOrderList.setQty(Integer.parseInt(qty[i_no]));
				jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
				jpoMemberOrderList.setVolume(jpmProductSale
						.getJpmProductSaleNew().getVolume());
				jpoMemberOrderList.setWeight(jpmProductSale
						.getJpmProductSaleNew().getWeight());
				
				jpoMemberOrderListSet.add(jpoMemberOrderList);
			}
		}
		
		if(jpoMemberOrderListSet.size()==0){
			return null;
		}
		//String userCode = jpoMemberOrder.getSysUser().getUserCode();
		String perNum = jpoMemberOrder.getSysUser().getJmiMember().getPapernumber();
		JmiMemberTeam team = jmiMemberTeamManager.getJmiMemberTeam(jpoMemberOrder.getTeamCode());
		
		String strAction = request.getParameter("strAction");

		//是否购买事业锦囊   0:否  1:是 
		if(team.getIsBuy().equals("1")){
			boolean valid = jpoMemberOrderManager.
					getJpoMemberMark(perNum, "P08520100101CN0", 
							Constants.FIRST_PURCHASE);
			if(valid){
				/*
				 * 同一身份证多个编号的情况，且其中一个编号绑定过事业锦囊，
				 * 则会员可以自由选择200元辅销品或者购买事业锦囊.
				 * 订单明细中是否有事业锦囊，或者200元辅销品
				 */
				if(!syjn && sumPrice_a.compareTo(new BigDecimal(200))<0){
					String msg = LocaleUtil.getLocalText(
							jpoMemberOrder.getCompanyCode(),"pv.notEnough20");
					throw new AppException(msg);
				}
			} else {
				/*
				 * 此会员同一身份证下，多个会员编号，
				 * 未绑定过事业锦囊，则需要强制绑定事业锦囊
				 */
				if(strAction.equalsIgnoreCase("addPoMemberFOrder")){
					JpmProductSaleTeamType jpmProductSale1 = jpmProductSaleManager
							.getJpmProductSaleTeamType("CN", "P08520100101CN0",
									Constants.FIRST_PURCHASE,
									jpoMemberOrder.getTeamCode());
					JpoMemberOrderList jpoMemberOrderListA = new JpoMemberOrderList();
					jpoMemberOrderListA.setJpmProductSaleTeamType(jpmProductSale1);
					jpoMemberOrderListA.setJpoMemberOrder(jpoMemberOrder);
					jpoMemberOrderListA.setPrice(new BigDecimal("200"));
					jpoMemberOrderListA.setPv(new BigDecimal("0"));
					jpoMemberOrderListA.setQty(1);
					jpoMemberOrderListA.setVolume(new BigDecimal("0"));
					jpoMemberOrderListA.setWeight(new BigDecimal("0"));
					jpoMemberOrderListSet.add(jpoMemberOrderListA);
				}
				
			}
		}
		
		
		return jpoMemberOrderListSet;
	}
	
	public void setJpoShippingFeeManager(
			JpoShippingFeeManager jpoShippingFeeManager) {
		this.jpoShippingFeeManager = jpoShippingFeeManager;
	}

	public void setJpmProductSaleManager(
			JpmProductSaleManager jpmProductSaleManager) {
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

	public void setJpoMemberOrderManager(
			JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public JpoMemberFOrderFormController() {
		setCommandName("jpoMemberOrder");
		setCommandClass(JpoMemberOrder.class);
	}

	public void setPdShipFeeManager(PdShipFeeManager pdShipManager) {
		this.pdShipManager = pdShipManager;
	}

	public JmiMemberTeamManager getJmiMemberTeamManager() {
		return jmiMemberTeamManager;
	}

	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}

	public PdShipFeeManager getPdShipManager() {
		return pdShipManager;
	}

	public void setPdShipManager(PdShipFeeManager pdShipManager) {
		this.pdShipManager = pdShipManager;
	}

	public JpoMemberOrderManager getJpoMemberOrderManager() {
		return jpoMemberOrderManager;
	}

	public JmiMemberManager getJmiMemberManager() {
		return jmiMemberManager;
	}

	public JmiAddrBookManager getJmiAddrBookManager() {
		return jmiAddrBookManager;
	}

	public AlCountryManager getAlCountryManager() {
		return alCountryManager;
	}

	public JpmProductSaleManager getJpmProductSaleManager() {
		return jpmProductSaleManager;
	}

	public JpoShippingFeeManager getJpoShippingFeeManager() {
		return jpoShippingFeeManager;
	}
	
}
