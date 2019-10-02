package com.joymain.jecs.po.webapp.action;

import java.math.BigDecimal;
import java.util.Date;
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
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.ListUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 会员续约订购表单
 * @author Alvin
 *
 */
public class JpoMemberRSOrderFormController extends BaseFormController {
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JmiMemberManager jmiMemberManager = null;
    private JmiAddrBookManager jmiAddrBookManager = null;
    private AlCountryManager alCountryManager = null;
    private JpmProductSaleManager jpmProductSaleManager = null;
    private JpoShippingFeeManager jpoShippingFeeManager = null;
    private JmiMemberTeamManager jmiMemberTeamManager=null;

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
	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}
    public JpoMemberRSOrderFormController() {
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
		
        if ("deletePoMemberRSOrder".equals(strAction) || "editPoMemberRSOrder".equals(strAction) && !StringUtils.isEmpty(moId)){
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
            if(!"1".equals(jpoMemberOrder.getStatus()) || !"1".equals(jpoMemberOrder.getSubmitStatus())){
            	throw new AppException("订单不能修改或删除!");
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
            jpoMemberOrder.setOrderType("3");
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
        // 新的获取团队的方法,返回顶点
        String teamCode=jmiMemberTeamManager.getTeamStr(loginSysUser);
        
        //和前台一致的获取团队方法 2015-02-28 w
//        String teamCode=jmiMemberTeamManager.teamStr(loginSysUser);//获取团队编号
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
    	if(loginSysUser.getIsMember()){
    		jpmProductSales = jpmProductSaleManager.getPmProductSalesNew(
    				loginSysUser.getCompanyCode(),
    				Constants.AUTO_PURCHASE,"1",teamCode,loginSysUser);
    	}else{
    		jpmProductSales = jpmProductSaleManager.getPmProductSalesNew(
    				loginSysUser.getCompanyCode(),
    				Constants.AUTO_PURCHASE,"2",teamCode,loginSysUser);
    	}
    
    	request.setAttribute("jpmProductSales", jpmProductSales);
        
    	if (RequestUtil.isMobileRequest(request)) {
			this.setFormView("/mobile/po/jpoMemberRSOrderForm");
		} else {
			
			this.setFormView("/po/jpoMemberRSOrderForm");
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
      	if("M".equals(loginSysUser.getUserType())){
      	  if(RequestUtil.saveOperationSession(request,"addPoMemberRSOrder", 10l)!=0){
      		  return new ModelAndView(getSuccessView());
      		 
      	  }
      }else if("C".equals(loginSysUser.getUserType()))
      {
          if(RequestUtil.saveOperationSession(request, "addPoMemberRSOrder", 30l)!=0){
      			  return new ModelAndView(getSuccessView());
           }
      }
        JpoMemberOrder jpoMemberOrder = (JpoMemberOrder) command;
        boolean isNew = (jpoMemberOrder.getMoId() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePoMemberRSOrder".equals(strAction)) {
			jpoMemberOrderManager.removeJpoMemberOrder(jpoMemberOrder.getMoId().toString());
			key="poMemberOrder.deleted";
		}else{

			if("addPoMemberRSOrder".equals(strAction)){
				JmiMember jmiMember = jmiMemberManager.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
				if(jmiMember==null){
            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
            		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
            		return showForm(request, response, errors);
				} else {
					if(jmiMember.getFreezeStatus()==0){
						errors.reject("miMemberrs.notFreeze", 
								new Object[] {},LocaleUtil.getLocalText(
										loginSysUser.getDefCharacterCoding(),"miMemberrs.notFreeze"));
	            		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
	            		return showForm(request, response, errors);
					}
				}
//				if("0".equals(jmiMember.getCardType())){//待审会员不允许下续约单
//            		errors.reject("poMemberFOrder.isNotExist", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poMemberFOrder.isNotExist"));
//            		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
//            		return showForm(request, response, errors);
//
//				}
			}
			
			if("C".equals(loginSysUser.getUserType())){
				JmiMember jmiMember = jmiMemberManager.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
				if(jmiMember==null){
            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
            		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
            		return showForm(request, response, errors);
				}else{
					if(!loginSysUser.getCompanyCode().equals(jmiMember.getCompanyCode())){
	            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
	            		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
	            		return showForm(request, response, errors);
					}
	                jpoMemberOrder.setSysUser(jmiMember.getSysUser());
	                jpoMemberOrder.setCompanyCode(jmiMember.getCompanyCode());
	                jpoMemberOrder.setUserSpCode(jmiMember.getUserCode());
	                jpoMemberOrder.setCountryCode(jmiMember.getCountryCode());
				}
			}
			
			if(this.checkPoMemberOrder(jpoMemberOrder, errors)){
				RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
				return showForm(request, response, errors);
			}
			
			if("1".equals(jpoMemberOrder.getPickup())){//会员自动提货
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
            		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
            		return showForm(request, response, errors);
        		}
        		
        		Set jpoMemberOrderFeeSet = null;
        		jpoMemberOrderFeeSet = this.fillInJfoMemberOrderFee(request,jpoMemberOrder,jpoMemberOrderSet);//生成订单明细
            	if(jpoMemberOrderFeeSet == null || jpoMemberOrderFeeSet.size()==0){
            		//没有指定物流公司
            		errors.reject("erro.pd.shippingFee.isEmpty", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"erro.pd.shippingFee.isEmpty"));
            		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
            		return showForm(request, response, errors);
            	}
        		
        		this.calcOrderAmount(request,jpoMemberOrderSet,jpoMemberOrderFeeSet,jpoMemberOrder);//计算总PV总金额
        		
        		if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus()==0){//1代表冻结，0代表正常
/*        			if(jpoMemberOrder.getPvAmt().compareTo(new BigDecimal("70"))==-1){
                		errors.reject("pv.notEnough", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"pv.notEnough"));
                		return showForm(request, response, errors);
        			}*/
        		}else{
        			
        			java.util.Calendar startcPre=java.util.Calendar.getInstance();
        			startcPre.set(2012, 8, 29, 00, 00, 00);
        			java.util.Date startDatePre=startcPre.getTime();
        			Date curDatePre=new Date();
        			if("CN".equals(jpoMemberOrder.getCompanyCode()) ){
        				if( curDatePre.after(startDatePre) && jpoMemberOrder.getPvAmt().compareTo(new BigDecimal("252"))==-1){
                    		errors.reject("pv.notEnough", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"pv.notEnough"));
                    		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
                    		return showForm(request, response, errors);
            			}/*else if(curDatePre.before(startDatePre) &&jpoMemberOrder.getPvAmt().compareTo(new BigDecimal("126"))==-1){//以前是续约126解冻
            				errors.reject("pv.notEnough", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"pv.notEnough"));
                    		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
                    		return showForm(request, response, errors);
            			}
            			*/
        			}
        			if(!"CN".equals(jpoMemberOrder.getCompanyCode()) && jpoMemberOrder.getPvAmt().compareTo(new BigDecimal("126"))==-1){//其他国别的
                		errors.reject("pv.notEnough", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"pv.notEnough"));
                		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
                		return showForm(request, response, errors);
        			}
        		}
    			
    			if("addPoMemberRSOrder".equals(strAction)){
    				jpoMemberOrder.setOrderTime(new Date());
    				//String memberOrderNo = sysIdManager.buildIdStr("pono");//生成订单编号
    				//jpoMemberOrder.setMemberOrderNo(memberOrderNo);
    				key="poMemberOrder.added";
    			}else{
    				key="poMemberOrder.updated";
    			}
    	  		//月刊分12月发送提示
            	java.util.Calendar startc=java.util.Calendar.getInstance();
        		startc.set(2012, 4, 12, 00, 00, 00);
        		java.util.Calendar endc=java.util.Calendar.getInstance();
        		endc.set(2019, 11, 31, 23, 59, 59);
        		java.util.Date startDate=startc.getTime();
        		java.util.Date endDate=endc.getTime();
        		Date curDate=new Date();
        		if((curDate.after(startDate))&&(curDate.before(endDate)) && "CN".equals(jpoMemberOrder.getCompanyCode())){
        	    	Iterator its1 = jpoMemberOrderSet.iterator();
        	    	while (its1.hasNext()) {
        				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        				if("P08520100101CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
        					key="erro.pd.shNo.Monthly";
        	    		}
        			
        			}
        		}
        		jpoMemberOrderManager.editJpoMemberOrder(jpoMemberOrder, jpoMemberOrderSet, jpoMemberOrderFeeSet);
    		}catch(NumberFormatException e){
        		errors.reject("error.isNotInteger", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"error.isNotInteger"));
        		RequestUtil.cleanOperationSession(request,"addPoMemberRSOrder");
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
        			"address",
        			"postalcode",
        			"email",
        			"phone",
        			"mobiletele",
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
        			"address",
        			"postalcode",
        			"email",
        			"phone",
        			"mobiletele",
        			"sysUser.userCode",
        			"pickup",
        			"remark",
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
    	if("TW".equals(jpoMemberOrder.getCompanyCode())){
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
    				errors.rejectValue("city", "errors.required",new Object[] {this.getText("shipping.city") }, "");
    				isNotPass = true;
    	    	}else if(jpoMemberOrder.getCity().length()>20){
    				errors.rejectValue("city", "errors.maxlength",new Object[] {this.getText("shipping.city"),"20" }, "");
    				isNotPass = true;
    	    	}
    	    	
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getDistrict())){
    /*				errors.rejectValue("district", "errors.required",new Object[] {this.getText("shipping.district") }, "");
    				isNotPass = true;*/
    	    	}else if(jpoMemberOrder.getCity().length()>20){
    				errors.rejectValue("district", "errors.maxlength",new Object[] {this.getText("shipping.district"),"20" }, "");
    				isNotPass = true;
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
    	    	}
    	    	
    	    	if(StringUtils.isEmpty(jpoMemberOrder.getPhone())){
    				errors.rejectValue("phone", "errors.required",new Object[] {this.getText("shipping.phone") }, "");
    				isNotPass = true;
    	    	}else if(jpoMemberOrder.getPhone().length()>20){
    				errors.rejectValue("phone", "errors.maxlength",new Object[] {this.getText("shipping.phone"),"20" }, "");
    				isNotPass = true;
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
    	    	Matcher matcher = pattern.matcher(jpoMemberOrder.getMobiletele());
    	    	if(!matcher.find()){
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
        		jpoMemberOrderFee.setFee(new BigDecimal("0"));
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
        	while (its1.hasNext()) {
        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        		sumWeight = sumWeight.add(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getWeight().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
        		sumVolume = sumVolume.add(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getVolume().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
/*        		if("P04010118001CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
    			dtProduct = 1;
	    		}
	    		if("P04010115001CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
	    			dtProduct = 1;
	    		}
	    		if("P08010100101CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
	    			dtProduct = 1;
	    		}
	    		if("P04010119001CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
	    			dtProduct = 1;
	    		}
	    		if("P08130100101CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
	    			dtProduct = 1;
	    		}
	    		if("P08120100101CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
	    			dtProduct = 1;
	    		}
	    		if("P08110100101CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
	    			dtProduct = 1;
	    		}*/
	    		if(list.get(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())!=null){
	    			dtProduct = 1;
	    		}
        	}
    		if("163732".equals(jpoMemberOrder.getProvince())){
    			dtProduct = 0;
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

    		List jpoShippingFees = jpoShippingFeeManager.getJpoShippingFeesByCrm(crm, new Pager(request,0));
    		if(jpoShippingFees.size()>0){
    			JpoShippingFee jpoShippingFee = (JpoShippingFee)jpoShippingFees.get(0);
        		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
        		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
        		jpoMemberOrderFee.setFeeType("1");//物流费
        		jpoMemberOrderFee.setDetailType(jpoShippingFee.getShippingType());
        		BigDecimal sumPrice = new BigDecimal(0);
        		if(dtProduct==1){
        			//大田
        			sumPrice = sumVolume.multiply(jpoShippingFee.getShippingFee());
        			//sumPrice = sumPrice.add(jpoShippingFee.getShippingRefee());
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
	    				jpoMemberOrderList.setPrice(jpoMemberOrderList.getJpmProductSale().getMpPrice().multiply(new BigDecimal("0.95")));
	    			}
	    		}
	    	}
		}*/
		if(jpoMemberOrderListSet.size()==0){
			return null;
		}
    	return jpoMemberOrderListSet;
    }
	
}
