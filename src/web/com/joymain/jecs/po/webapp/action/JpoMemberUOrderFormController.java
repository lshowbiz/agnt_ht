package com.joymain.jecs.po.webapp.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlStateProvince;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.service.JmiAddrBookManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.pd.service.PdShipFeeManager;

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
import com.joymain.jecs.sys.service.SysIdManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
/**
 * 会员升级表单
 * @author Alvin
 *
 */
public class JpoMemberUOrderFormController extends BaseFormController {
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JmiMemberManager jmiMemberManager = null;
    private JmiAddrBookManager jmiAddrBookManager = null;
    private AlCountryManager alCountryManager = null;
    private JpmProductSaleManager jpmProductSaleManager = null;
    private SysIdManager sysIdManager = null;
    private JpoShippingFeeManager jpoShippingFeeManager = null;
    private PdShipFeeManager  pdShipFeeManager=null;
    private JmiMemberTeamManager jmiMemberTeamManager=null;
	public void setJpoShippingFeeManager(JpoShippingFeeManager jpoShippingFeeManager) {
		this.jpoShippingFeeManager = jpoShippingFeeManager;
	}

    public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
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
	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}
    public JpoMemberUOrderFormController() {
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
		
        if ("deletePoMemberUOrder".equals(strAction) || "editPoMemberUOrder".equals(strAction) && !StringUtils.isEmpty(moId)){
            jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);
            if("1".equals(jpoMemberOrder.getIsPay())){
            	throw new AppException("订单已支付!");
            }
            if("M".equals(loginSysUser.getUserType())){
                if(!jpoMemberOrder.getSysUser().getUserCode().equals(loginSysUser.getUserCode())){
                	throw new AppException("订单不属于当前登录者!");
                }
            }else{
                if(!jpoMemberOrder.getCompanyCode().equals(loginSysUser.getCompanyCode())){
                	throw new AppException("订单不属于当前登录者!");
                }
            }
        } else {
        	if("0".equals(loginSysUser.getUpGrade())){
        		return null;
        	}
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
            jpoMemberOrder.setOrderType("2");
            jpoMemberOrder.setPickup("0"); //是否自动提款
            jpoMemberOrder.setStatus("1"); 
            jpoMemberOrder.setSubmitStatus("1");
            jpoMemberOrder.setIsPay("0");
            
            jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
            jpoMemberOrder.setAmount(new BigDecimal(0));
            jpoMemberOrder.setPvAmt(new BigDecimal(0));
            jpoMemberOrder.setPayMode("0");//1.现金 2.信用卡 3.汇款 4.网上转账  目前没用
            jpoMemberOrder.setIsSpecial("0");//是否为特殊订单 0为否 1为是
            
        }
//      新的获取团队的方法,返回顶点
        String teamCode=jmiMemberTeamManager.getTeamStr(loginSysUser);
        
     	//和前台一致的获取团队方法 2015-02-28 w
//        String teamCode=jmiMemberTeamManager.teamStr(loginSysUser);//获取团队编号
        jpoMemberOrder.setTeamCode(teamCode);
        
        //地址簿
        CommonRecord crm = new CommonRecord();
        crm.addField("userCode", loginSysUser.getUserCode());
        if(!loginSysUser.getIsMember()){//如果是会员
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
    	if(loginSysUser.getIsMember()){
    		jpmProductSales = jpmProductSaleManager.getPmProductSalesNew(
    				loginSysUser.getCompanyCode(),
    				Constants.UPDATE_PURCHASE,"1",teamCode,loginSysUser);
    	}else{
    		jpmProductSales = jpmProductSaleManager.getPmProductSalesNew(
    				loginSysUser.getCompanyCode(),
    				Constants.UPDATE_PURCHASE,"2",teamCode,loginSysUser);
    	}
       /* //广州排毒
    	if("M".equals(loginSysUser.getUserType())){
    	JmiMember lcMiMember =jmiMemberManager.getJmiMember("CN10829722");
    	if(lcMiMember!=null ){
    		String lcTreeIndex=lcMiMember.getJmiRecommendRef().getTreeIndex();
    		String loninTreeIndex=loginSysUser.getJmiMember().getJmiRecommendRef().getTreeIndex();
    		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, lcTreeIndex.length());
    		if(loninTreeIndex.length() >= lcTreeIndex.length() && rmemberIndexTmp.equals(lcTreeIndex)){
    			jpmProductSales = jpmProductSaleManager.getJpmProductSales("PD");
    		}
    	 }
    	}*/
    	
    	request.setAttribute("jpmProductSales", jpmProductSales);
    	
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = DateUtil.getDateOffset(new Date(),5,3);
    	request.setAttribute("toDay",format.format(date));
    	
    	if(RequestUtil.isMobileRequest(request)){
        	this.setFormView("/mobile/po/jpoMemberUOrderForm");
        } else {
        	this.setFormView("/po/jpoMemberUOrderForm");
        	
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
/*    	if("M".equals(loginSysUser.getUserType())){
        	  if(RequestUtil.saveOperationSession(request,"addPoMemberUOrder", 10l)!=0){
        		  return new ModelAndView(getSuccessView());
        		 
        	  }
        }else if("C".equals(loginSysUser.getUserType()))
        {
            if(RequestUtil.saveOperationSession(request, "addPoMemberUOrder", 30l)!=0){
        			  return new ModelAndView(getSuccessView());
             }
        }*/
        JpoMemberOrder jpoMemberOrder = (JpoMemberOrder) command;
        boolean isNew = (jpoMemberOrder.getMoId() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePoMemberUOrder".equals(strAction)) {
			jpoMemberOrderManager.removeJpoMemberOrder(jpoMemberOrder.getMoId().toString());
			key="poMemberOrder.deleted";
		}else{
			if("addPoMemberUOrder".equals(strAction)){
				JmiMember jmiMember = jmiMemberManager.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
				if(jmiMember==null){
            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
            		return showForm(request, response, errors);
				}
				boolean isfreez = isFreeze(jmiMember.getFreezeStatus(),errors,
						loginSysUser.getDefCharacterCoding());
				if(isfreez){
					return showForm(request, response, errors);
				}
				
				if(jmiMember.getMemberLevel().intValue() == Constants.CARDTYPE_5000){
					errors.reject("userRole.error", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"userRole.error"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
            		return showForm(request, response, errors);
				}
				if(jmiMember.getMemberLevel() ==null || 0 == jmiMember.getMemberLevel() ||
						jmiMember.getMemberLevel().intValue() == Constants.CARDTYPE_5000){//待审会员和最高级别不允许下升级单
					CommonRecord crm = new CommonRecord();
					crm.addField("sysUser.userCode", jpoMemberOrder.getSysUser().getUserCode());
					crm.addField("orderType", "1");
					crm.addField("status", "2");
					List jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm, new Pager(request, 0));
					if(jpoMemberOrders.size()==0){
	            		errors.reject("poMemberFOrder.isNotExist", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poMemberFOrder.isNotExist"));
	            		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
	            		return showForm(request, response, errors);
					}
            		errors.reject("poMemberFOrder.isNotExist", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poMemberFOrder.isNotExist"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
            		return showForm(request, response, errors);
				}
			}
			
			JmiMember jmiMember = jmiMemberManager.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
			if("C".equals(loginSysUser.getUserType())){
				
				if(jmiMember==null){
            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
            		return showForm(request, response, errors);
				}else{
					if(!loginSysUser.getCompanyCode().equals(jmiMember.getCompanyCode())){
	            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
	            		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
	            		return showForm(request, response, errors);
					}
	               jpoMemberOrder.setSysUser(jmiMember.getSysUser());
	                jpoMemberOrder.setCompanyCode(jmiMember.getCompanyCode());
	                jpoMemberOrder.setUserSpCode(jmiMember.getUserCode());
	                jpoMemberOrder.setCountryCode(jmiMember.getCountryCode());
				}
			}
			
			if(this.checkPoMemberOrder(jpoMemberOrder, errors)){
				//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
				return showForm(request, response, errors);
			}
			
			if("1".equals(jpoMemberOrder.getPickup())){//判断是否为会员自动提货
				jpoMemberOrder.setFirstName(
						jpoMemberOrder.getSysUser().getJmiMember().getFirstName());
				jpoMemberOrder.setLastName(
						jpoMemberOrder.getSysUser().getJmiMember().getLastName());
				jpoMemberOrder.setProvince(
						jpoMemberOrder.getSysUser().getJmiMember().getProvince());
				jpoMemberOrder.setCity(
						jpoMemberOrder.getSysUser().getJmiMember().getCity());
				jpoMemberOrder.setDistrict(
						jpoMemberOrder.getSysUser().getJmiMember().getDistrict());
				jpoMemberOrder.setAddress(
						jpoMemberOrder.getSysUser().getJmiMember().getAddress());
				jpoMemberOrder.setPostalcode(
						jpoMemberOrder.getSysUser().getJmiMember().getPostalcode());
				jpoMemberOrder.setPhone(
						jpoMemberOrder.getSysUser().getJmiMember().getPhone());
				jpoMemberOrder.setMobiletele(
						jpoMemberOrder.getSysUser().getJmiMember().getMobiletele());
			}
			
    		try{
        		Set jpoMemberOrderSet = null;
        		jpoMemberOrderSet = this.fillInJfoMemberOrderList(request,jpoMemberOrder);//生成订单明细
        		if(jpoMemberOrderSet==null){//检查订单总数是否小于0
            		errors.reject("amount.notEnough", new Object[] {},
            				LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),
            						"amount.notEnough"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
            		return showForm(request, response, errors);
        		}
        		
        		Set jpoMemberOrderFeeSet = null;
        		jpoMemberOrderFeeSet = this.fillInJfoMemberOrderFee(request,jpoMemberOrder,jpoMemberOrderSet);//生成订单明细
//            	if(jpoMemberOrderFeeSet == null || jpoMemberOrderFeeSet.size()==0){
//	        		//没有指定物流公司
//	        		errors.reject("erro.pd.shNo.isEmpty", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"erro.pd.shNo.isEmpty"));
//	        		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
//	        		return showForm(request, response, errors);
//            	}

        		this.calcOrderAmount(request,jpoMemberOrderSet,jpoMemberOrderFeeSet,jpoMemberOrder);//计算总PV总金额
        		
        		boolean enoughtPV = upgradePV(jmiMember.getMemberLevel(), jpoMemberOrder.getPvAmt());
        		if(enoughtPV){
            		errors.reject("pv.notEnough", new Object[] {},
            				LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"pv.notEnough"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
            		return showForm(request, response, errors);
        		}
        		
    			if("addPoMemberUOrder".equals(strAction)){
    				jpoMemberOrder.setOrderTime(new Date());
    				//String memberOrderNo = sysIdManager.buildIdStr("pono");//生成订单编号
    				//jpoMemberOrder.setMemberOrderNo(memberOrderNo);
    				key="poMemberOrder.added";
    			}else{
    				key="poMemberOrder.updated";
    			}
    	  		//月刊分12月发送提示
//            	java.util.Calendar startc=java.util.Calendar.getInstance();
//        		startc.set(2012, 4, 12, 00, 00, 00);
//        		java.util.Calendar endc=java.util.Calendar.getInstance();
//        		endc.set(2019, 11, 31, 23, 59, 59);
//        		java.util.Date startDate=startc.getTime();
//        		java.util.Date endDate=endc.getTime();
//        		Date curDate=new Date();
//        		if((curDate.after(startDate))&&(curDate.before(endDate)) && "CN".equals(jpoMemberOrder.getCompanyCode())){
//        	    	Iterator its1 = jpoMemberOrderSet.iterator();
//        	    	while (its1.hasNext()) {
//        				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
//        				if("P08520100101CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
//        					key="erro.pd.shNo.Monthly";
//        	    		}
//        			
//        			}
//        		}
        		jpoMemberOrderManager.editJpoMemberOrder(jpoMemberOrder, jpoMemberOrderSet, jpoMemberOrderFeeSet);
    		}catch(NumberFormatException e){
        		errors.reject("error.isNotInteger", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"error.isNotInteger"));
        		//RequestUtil.cleanOperationSession(request,"addPoMemberUOrder");
        		return showForm(request, response, errors);
    		}
		}
		saveMessage(request, getText(loginSysUser.getDefCharacterCoding(),key));
		SessionLogin.getLoginUser(request).setUpGrade(null);
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
        			"payMode",
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
    	    	Matcher matcher = pattern.matcher(jpoMemberOrder.getMobiletele());
    	    	if(!matcher.matches()&&!"RU".equals(jpoMemberOrder.getCompanyCode())){
    	    		errors.rejectValue("mobiletele", "errors.mobiletele",new Object[] {this.getText("miMember.mobiletele") }, "");
    	    		isNotPass = true;
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
    private boolean checkAmount(JpoMemberOrder jpoMemberOrder){
    	JmiMember jmiMember = jmiMemberManager.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
    	if(!"6".equals(jmiMember.getCardType())){//级别没达到vip
    		int cardType = Integer.parseInt(jmiMember.getCardType());
    		BigDecimal pv1=new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype."+String.valueOf(cardType)+".value"));
    		BigDecimal pv2;
    		//中国区优惠顾客
    		if("CN".equals(jpoMemberOrder.getCompanyCode()))
	    	{
    			java.util.Calendar startcY=java.util.Calendar.getInstance();
	    		startcY.set(2012, 8, 29, 00, 00, 00);
	    		java.util.Date startDateY=startcY.getTime();
	    		Date curDateY=new Date();
    			if(curDateY.after(startDateY) && cardType==5){//优惠顾客
    				pv2=new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.2.value"));//金级
    			}else if(curDateY.before(startDateY) && cardType==5)
    			{
    				pv2=new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.1.value"));//银级
    			}else
    			{
    				pv2=new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype."+String.valueOf(cardType + 1)+".value"));

            		if(cardType == 4){//钻石级
            			pv2=new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.6.value"));

                   /* 	java.util.Calendar startc=java.util.Calendar.getInstance();
                    	startc.set(2010, 9, 16, 00, 00, 00);
                    	java.util.Date startDate=startc.getTime();
                    	Date curDate=new Date();
                    	if((curDate.after(startDate))){
                    			
            	    		java.util.Calendar endc=java.util.Calendar.getInstance();
            	    		endc.set(2010, 9, 21, 00, 00, 00);
            	    		java.util.Date endDate=endc.getTime();
            	    		if(curDate.before(endDate)){
            					if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsDiscount())){
            						if("CN".equals(jpoMemberOrder.getCompanyCode()) && jpoMemberOrder.getAmount().compareTo(new BigDecimal("3000"))!=-1){
            							return true;
            						}
            					}
            	    		}
                    	}*/
            	    		
            		}
    			}
    		} 
    		else
    		{
    			//其他国别
    		   if(cardType==5){//优惠顾客
    			pv2=new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.1.value"));//银级
    		}else{
	    			pv2=new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype."+String.valueOf(cardType + 1)+".value"));
	
	        		if(cardType == 4){
	        			pv2=new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "cardtype.6.value"));
	
	               /* 	java.util.Calendar startc=java.util.Calendar.getInstance();
	                	startc.set(2010, 9, 16, 00, 00, 00);
	                	java.util.Date startDate=startc.getTime();
	                	Date curDate=new Date();
	                	if((curDate.after(startDate))){
	                			
	        	    		java.util.Calendar endc=java.util.Calendar.getInstance();
	        	    		endc.set(2010, 9, 21, 00, 00, 00);
	        	    		java.util.Date endDate=endc.getTime();
	        	    		if(curDate.before(endDate)){
	        					if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsDiscount())){
	        						if("CN".equals(jpoMemberOrder.getCompanyCode()) && jpoMemberOrder.getAmount().compareTo(new BigDecimal("3000"))!=-1){
	        							return true;
	        						}
	        					}
	        	    		}
	                	}*/
	        	    		
	        		}
	    		}
    		}
    		
    		BigDecimal orderPv = jpoMemberOrder.getPvAmt();
    		if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){//麦酷团队
    			orderPv=orderPv.add(jpoMemberOrder.getSysUser().getJmiMember().getOriPv());//跟此团队原来的pv加上新增订单pv数
    		}
    		
    		if(pv2.subtract(pv1).compareTo(orderPv)!=1){//将升级后的级别pv与原来会员级别所需pv进行比较
        		return true;
        	}
     
        	
    	}
    	return false;
    }
    
    /**
     * 计算费用
     * @param request
     * @param jpoMemberOrderSet
     * @param jpoMemberOrder
     */
    private void calcOrderAmount(HttpServletRequest request, Set jpoMemberOrderSet, Set jpoMemberOrderFeeSet, JpoMemberOrder jpoMemberOrder){
    	
    	BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	
    	Iterator its1 = jpoMemberOrderSet.iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}
    	
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
     * @return
     */
    private Set fillInJfoMemberOrderFee(HttpServletRequest request, JpoMemberOrder jpoMemberOrder,Set jpoMemberOrderSet){
    	Set jpoMemberOrderFeeSet = new HashSet(0);
    	if("CN".equals(jpoMemberOrder.getCompanyCode()) || "HK".equals(jpoMemberOrder.getCompanyCode()) ){
        	
        	//物流费
        	JpoMemberOrderFee jpoMemberOrderFee = this.shippingFeeCalc(jpoMemberOrder, jpoMemberOrderSet, request);
        	if(jpoMemberOrderFee!=null){
/*        		if(sumPrice.compareTo(new BigDecimal("12000"))!=-1){
        			jpoMemberOrderFee.setFee(new BigDecimal("0"));
        		}*/
        	//	jpoMemberOrderFee.setFee(new BigDecimal("0"));//TODO:2009-11-9 00:00:00取消物流费
        	 //	/**@author 罗婷
            //	 * 2011-11-15
            //	 * 新物流费计算方法
            //	 */
        		java.util.Calendar startc2=java.util.Calendar.getInstance();
        		startc2.set(2011, 11, 10, 00, 00, 00);
        		Date curDate2=new Date();
        		java.util.Date startDate2=startc2.getTime();
        		if((curDate2.after(startDate2))&& "CN".equals(jpoMemberOrder.getCompanyCode())){
        		BigDecimal feePrice = new BigDecimal(0);
        		BigDecimal sumPrice = new BigDecimal(0);
    	    	Iterator its = jpoMemberOrderSet.iterator();
    	    	while (its.hasNext()) {
    				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its.next();
    				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    			}
        		feePrice=pdShipFeeManager.countFee(sumPrice.toString(), "2", jpoMemberOrder.getProvince(),true);
        		jpoMemberOrderFee.setFee(feePrice);
        		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
        		}
        		
     /*   		java.util.Calendar endc=java.util.Calendar.getInstance();
             	endc.set(2013, 1, 9, 00, 00, 00);
        		java.util.Date endDate=endc.getTime();
        		Date curDate=new Date();
        		if(curDate.before(endDate)){
        		//田阳团队2013-2-8前不算物流费
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
        		
////        		田阳团队不算物流费
//        		JmiMember pdMiMember =jmiMemberManager.getJmiMember("CN19394234");//田阳团队(推荐网)
//        		if(pdMiMember!=null){
//	    		String lcTreeIndex=pdMiMember.getJmiRecommendRef().getTreeIndex();
//        		String loninTreeIndex=jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
//        		String rmemberIndexTmp = StringUtil.getLeft(loninTreeIndex, lcTreeIndex.length());
//	    		if(loninTreeIndex.length() >= lcTreeIndex.length() && rmemberIndexTmp.equals(lcTreeIndex)){
//	    			jpoMemberOrderFee.setFee(new BigDecimal("0"));
//	    			jpoMemberOrder.setConsumerAmount(new BigDecimal("0"));
//	    		  }
//	    		}
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
    		jpoMemberOrderFee.setDetailType("0000");//中脉物流公司
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
    		jpoMemberOrderFee.setDetailType("UPS");//美国联合包裹
    		jpoMemberOrderFee.setFee(sumShipping);
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrder.setConsumerAmount(sumShipping);
    		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
    		JpoMemberOrderFee jpoMemberOrderFee1 = new JpoMemberOrderFee();
    		jpoMemberOrderFee1.setDetailType("TAX");//税收公司
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
     * 计算物流费
     * @param jpoShippingFee
     * @param sum
     * @param dtProduct
     * @param request
     * @return
     */
    private JpoMemberOrderFee shippingFeeCalc(JpoMemberOrder jpoMemberOrder, Set jpoMemberOrderSet, HttpServletRequest request){
    	if("CN".equals(jpoMemberOrder.getCompanyCode())){
    		int dtProduct = 0;
    		BigDecimal sumWeight = new BigDecimal(0);
    		BigDecimal sumVolume = new BigDecimal(0);
    		Iterator its1 = jpoMemberOrderSet.iterator();
    		LinkedHashMap list = ListUtil.getListOptions("CN", "dtw.special");
    		boolean flag = false;
        	while (its1.hasNext()) {
        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        		sumWeight = sumWeight.add(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getWeight().multiply(new BigDecimal(jpoMemberOrderList.getQty())));//重量
        		sumVolume = sumVolume.add(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getVolume().multiply(new BigDecimal(jpoMemberOrderList.getQty())));//体积
        		String proNo = jpoMemberOrderList.getJpmProductSaleTeamType().
        				getJpmProductSaleNew().getJpmProduct().getProductNo();
        		
        		if(proNo.equalsIgnoreCase("P17010200101CN0")){
        			flag = true;
        		}
	    		if(list.get(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())!=null){
	    			dtProduct = 1;//大田
	    		}
        	}
    		if("163732".equals(jpoMemberOrder.getProvince())){////新疆维吾尔自治区
    			dtProduct = 0;//0 代表宅急送
    		}
    		CommonRecord crm = new CommonRecord();
    		crm.addField("countryCode", jpoMemberOrder.getCountryCode());
    		crm.addField("province", jpoMemberOrder.getProvince());
    		crm.addField("city", jpoMemberOrder.getCity());
    		crm.addField("district", jpoMemberOrder.getDistrict());
    		if(dtProduct==1){
    			//大田
    			crm.addField("shippingType", "DTW");
    		}else if(dtProduct==0){
    			//宅急送
    			crm.addField("shippingType", "ZJS");
    		}
    		
    		List jpoShippingFees = new ArrayList();
    		
    		if(!flag){
    			jpoShippingFees = jpoShippingFeeManager.
    					getJpoShippingFeesByCrm(crm, new Pager(request,0));
    		}
    		
    		if(jpoShippingFees.size()>0){
    			JpoShippingFee jpoShippingFee = (JpoShippingFee)jpoShippingFees.get(0);
        		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
        		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
        		jpoMemberOrderFee.setFeeType("1");//物流费
        		jpoMemberOrderFee.setDetailType(jpoShippingFee.getShippingType());
        		BigDecimal sumPrice = new BigDecimal(0);
        		if(dtProduct==1){
        			//大田
        			sumPrice = sumVolume.multiply(jpoShippingFee.getShippingFee());//物流公司
        			//sumPrice = sumPrice.add(jpoShippingFee.getShippingRefee());//这个是以前的算法
        		}else if(dtProduct==0){
        			//宅急送
        			if(sumWeight.compareTo(new BigDecimal(1)) == 1){
        				BigDecimal sumSubWeight = sumWeight.subtract(new BigDecimal(1)).setScale(0, BigDecimal.ROUND_UP);
        				sumPrice = sumPrice.add(jpoShippingFee.getShippingFee());
        				sumPrice = sumPrice.add(sumSubWeight.multiply(jpoShippingFee.getShippingRefee()));
        			}else if(sumWeight.compareTo(new BigDecimal(0)) == 1){
        				sumPrice = sumPrice.add(jpoShippingFee.getShippingFee());
        			}else{
        				sumPrice = sumPrice.add(new BigDecimal(0));
        			}
            	}
        		jpoMemberOrderFee.setFee(sumPrice);
        		jpoMemberOrder.setConsumerAmount(sumPrice);
        		return jpoMemberOrderFee;
    		}
    	}else if("HK".equals(jpoMemberOrder.getCompanyCode())){
    		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setDetailType("0000");
    		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
    		return jpoMemberOrderFee;
    	}else{
    		
    	}
    	return null;
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
				jpoMemberOrderList.setPrice(jpmProductSale.getPrice());
		/*		if("TW".equals(jpoMemberOrder.getCompanyCode())){
					Date curDate=new Date();
	        		java.util.Calendar endc=java.util.Calendar.getInstance();
	        		endc.set(2011, 6, 31, 23, 59, 59);
	    			java.util.Date endDate=endc.getTime();
	    			if(curDate.before(endDate)){
	    				String productNo = "P08030200401TW0,P08030200301TW0,P08030200201TW0,P08030200101TW0,P05010200101TW0,P05020100101TW0,P08090200101TW0,P04020300101TW0,P04010400101HK0,P01010100101TW0,";
	    				if(!StringUtil.isEmpty(jpmProductSale.getJpmProduct().getProductNo()) && productNo.contains(jpmProductSale.getJpmProduct().getProductNo()+",")){
	    					jpoMemberOrderList.setPrice((jpmProductSale.getFpPrice().multiply(new BigDecimal("0.88"))).setScale(0, BigDecimal.ROUND_HALF_UP));
	    				}
	    			}
				}*/
				jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
				jpoMemberOrderList.setProNo(jpmProductSale.getJpmProductSaleNew().getProductNo());
				jpoMemberOrderList.setPv(jpmProductSale.getPv());
				jpoMemberOrderList.setQty(Integer.parseInt(qty[i_no]));
    			jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
    			jpoMemberOrderList.setVolume(jpmProductSale.getJpmProductSaleNew().getVolume());
    			jpoMemberOrderList.setWeight(jpmProductSale.getJpmProductSaleNew().getWeight());
  
    			
    			
    			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty()))) ;
    			jpoMemberOrderListSet.add(jpoMemberOrderList);
				
			}
		}
/*		if("CN".equals(jpoMemberOrder.getCompanyCode()) && sumPrice.compareTo(new BigDecimal("1000"))!=-1 ){//2010-10-01~2010-10-07九点五折促销
	    	java.util.Calendar startc=java.util.Calendar.getInstance();
	    	startc.set(2010, 9, 1, 00, 00, 00);
	    	java.util.Calendar endc=java.util.Calendar.getInstance();
	    	endc.set(2010, 9, 8, 00, 00, 00);
	    	java.util.Date startDate=startc.getTime();
	    	java.util.Date endDate=endc.getTime();
	    	Date curDate=new Date();
	    	if((curDate.after(startDate))&&(curDate.before(endDate))){
	    		Iterator its1 = jpoMemberOrderListSet.iterator();
	    		while (its1.hasNext()) {
	    			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
	    			if(jpoMemberOrderList.getPv().compareTo(new BigDecimal("0"))==1){
	    				jpoMemberOrderList.setPrice(jpoMemberOrderList.getJpmProductSale().getFpPrice().multiply(new BigDecimal("0.95")));
	    			}
	    		}
	    	}
		}*/
		//罗婷
/*		if("ID".equals(jpoMemberOrder.getCompanyCode())){//2012-3-17~2012-4-13九折促销
	    	java.util.Calendar startc=java.util.Calendar.getInstance();
	    	startc.set(2012, 2, 17, 00, 00, 00);
	    	java.util.Calendar endc=java.util.Calendar.getInstance();
	    	endc.set(2012, 3,14, 00, 00, 00);
	    	java.util.Date startDate=startc.getTime();
	    	java.util.Date endDate=endc.getTime();
	    	Date curDate=new Date();
	    	if((curDate.after(startDate))&&(curDate.before(endDate))){
	    		Iterator its1 = jpoMemberOrderListSet.iterator();
	    		while (its1.hasNext()) {
	    			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
	    				jpoMemberOrderList.setPrice(jpoMemberOrderList.getJpmProductSale().getFpPrice().multiply(new BigDecimal("0.90")));
	    		}
	    	}
		}*/
		if(jpoMemberOrderListSet.size()==0){
			return null;
		}
    	return jpoMemberOrderListSet;
    }

	public void setPdShipFeeManager(PdShipFeeManager pdShipFeeManager) {
		this.pdShipFeeManager = pdShipFeeManager;
	}
	/**
	 * PV不够升一级则返回true
	 * @param cardType
	 * @param orderPv
	 * @return
	 */
	private boolean upgradePV(int cardType,BigDecimal orderPv) {
	    	String yinLevel = ConfigUtil.getConfigValue("CN", "cardtype.1.value");
	    	String jinLevel = ConfigUtil.getConfigValue("CN", "cardtype.2.value");
	    	String zuanLevel = ConfigUtil.getConfigValue("CN", "cardtype.3.value");
			String tgyLevel = ConfigUtil.getConfigValue("CN", "cardtype.7.value");
	    	BigDecimal zuanBig = new BigDecimal(zuanLevel);
	    	BigDecimal jinBig = new BigDecimal(jinLevel);
	    	BigDecimal yinBig = new BigDecimal(yinLevel);
			BigDecimal tgyBig = new BigDecimal(tgyLevel);
	    	BigDecimal sub_pv = new BigDecimal(0);
	    	//BigDecimal subPV_1 = new BigDecimal(0);
	    	
	    	boolean isup = false;
	    	log.info("cardType:"+cardType+" and orderPV:"+orderPv);
	    	switch (cardType) {
			case 5000://Constants.CARDTYPE_5000:
				isup = true;
				break;
			case 4000://Constants.CARDTYPE_4000:
				sub_pv = zuanBig.subtract(jinBig);
				log.info("subPV_2 is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 3000://Constants.CARDTYPE_3000:
				sub_pv = jinBig.subtract(yinBig);
				log.info("subPV_1 is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 1500://Constants.CARDTYPE_1500:
				sub_pv = yinBig.subtract(tgyBig);
				log.info("subPV_1500 is:"+sub_pv);
				
				if(orderPv.compareTo(sub_pv)<0){
					isup = true;
				}
				break;
			case 1000://Constants.CARDTYPE_1000:
				if(orderPv.compareTo(tgyBig) < 0) isup = true;
				break;
			case 2000://Constants.CARDTYPE_2000:
				if(orderPv.compareTo(tgyBig) < 0) isup = true;
				break;
			default:
				isup = true;
				break;
	    	}
	    	
	    	return isup;
	    }

}
