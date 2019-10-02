package com.joymain.jecs.po.webapp.action;

import java.math.BigDecimal;
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
import com.joymain.jecs.util.exception.AppException;
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
 * 二级店铺升级订购表单(兰超)
 * @author Alvin
 *
 */
public class JpoMemberSSubUOrderLCFormController extends BaseFormController {
  /*  private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JmiMemberManager jmiMemberManager = null;
    private JmiAddrBookManager jmiAddrBookManager = null;
    private AlCountryManager alCountryManager = null;
    private JpmProductSaleManager jpmProductSaleManager = null;
    private SysIdManager sysIdManager = null;
    private JpoShippingFeeManager jpoShippingFeeManager = null;

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
    public JpoMemberSSubUOrderLCFormController() {
        setCommandName("jpoMemberOrder");
        setCommandClass(JpoMemberOrder.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {

    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
    	
    	String lcDiscount = ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "lc.discount");
    	request.setAttribute("lcDiscount", lcDiscount);
    	
        String moId = request.getParameter("moId");
        JpoMemberOrder jpoMemberOrder = null;
		String strAction = request.getParameter("strAction");
		
        if ("deletePoMemberSSubUOrderLC".equals(strAction) || "editPoMemberSSubUOrderLC".equals(strAction) && !StringUtils.isEmpty(moId)){
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
            jpoMemberOrder.setOrderType("12");
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
    		if("2".equals(loginSysUser.getJmiMember().getIsstore())){
    			jpmProductSales = jpmProductSaleManager.getLCProductSales(loginSysUser.getCompanyCode(),Constants.SUBSTORE_UPDATE_PURCHASE,"1");
    		}
    	}else{
    		jpmProductSales = jpmProductSaleManager.getLCProductSales(loginSysUser.getCompanyCode(),Constants.SUBSTORE_UPDATE_PURCHASE,"2");
    	}
    	
    	BigDecimal sumPrice2 = new BigDecimal(0);
    //	BigDecimal sumPrice3 = new BigDecimal(0);
    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its1.hasNext()) {
    		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
    		if("NLC000000000CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
    		
				sumPrice2 = sumPrice2.add(jpoMemberOrderList.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    		}
    		if("NLC010000000CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
				sumPrice3 = sumPrice3.add(jpoMemberOrderList.getJpmProductSale().getStoreFpPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    		}
		}
     

    	//request.setAttribute("fixeds", sumPrice2.add(sumPrice3));
    	request.setAttribute("fixeds", sumPrice2);
    	request.setAttribute("jpmProductSales", jpmProductSales);
    	request.setAttribute("pv", ConfigUtil.getConfigValue(loginSysUser.getCompanyCode().toUpperCase(), "store.u2.orderlc.pv"));
    	
    	if (RequestUtil.isMobileRequest(request)) {
    		this.setFormView("/mobile/po/jpoMemberSSubUOrderLCForm");
		} else {
			this.setFormView("/po/jpoMemberSSubUOrderLCForm");
			
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
        jpoMemberOrder.setProductType("LC");
        boolean isNew = (jpoMemberOrder.getMoId() == null);
        Locale locale = request.getLocale();
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePoMemberSSubUOrderLC".equals(strAction)) {
			jpoMemberOrderManager.removeJpoMemberOrder(jpoMemberOrder.getMoId().toString());
			key="poMemberOrder.deleted";
		}else{
			
			if("addPoMemberSSubUOrderLC".equals(strAction)){
				CommonRecord crm = new CommonRecord();
				crm.addField("sysUser.userCode", jpoMemberOrder.getSysUser().getUserCode());
				crm.addField("orderType", "12");
				List jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm, new Pager(request, 0));
				if(jpoMemberOrders.size()!=0){
            		errors.reject("poMemberSSubUOrder.isExist", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poMemberSSubUOrder.isExist"));
            		return showForm(request, response, errors);
				}
			}
			JmiMember jmiMember = jmiMemberManager.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
			if("C".equals(loginSysUser.getUserType())){
				if(jmiMember==null){
            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
            		return showForm(request, response, errors);
				}else{
					if(!loginSysUser.getCompanyCode().equals(jmiMember.getCompanyCode())){
	            		errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
	            		return showForm(request, response, errors);
					}
	                jpoMemberOrder.setSysUser(jmiMember.getSysUser());
	                jpoMemberOrder.setCompanyCode(jmiMember.getCompanyCode());
	                jpoMemberOrder.setUserSpCode(jmiMember.getUserCode());
	                jpoMemberOrder.setCountryCode(jmiMember.getCountryCode());
				}
			}
			
    		if(!"2".equals(jmiMember.getIsstore())){
        		errors.reject("member.isNotSubStore", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"member.isNotSubStore"));
        		return showForm(request, response, errors);
    		}
			
			if("0".equals(jmiMember.getCardType())){
        		errors.reject("poMemberUOrder.isNotExist", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poMemberUOrder.isNotExist"));
        		return showForm(request, response, errors);
					CommonRecord crm = new CommonRecord();
				crm.addField("sysUser.userCode", jpoMemberOrder.getSysUser().getUserCode());
				crm.addField("orderType", "1");
				crm.addField("status", "2");
				List jpoMemberOrders = jpoMemberOrderManager.getJpoMemberOrdersByCrm(crm, new Pager(request, 0));
				if(jpoMemberOrders.size()==0){
            		errors.reject("poMemberUOrder.isNotExist", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poMemberUOrder.isNotExist"));
            		return showForm(request, response, errors);
				}
			}
			
			if(this.checkPoMemberOrder(jpoMemberOrder, errors)){
	    		return showForm(request, response, errors);
			}
			
			if("1".equals(jpoMemberOrder.getPickup())){
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
            		return showForm(request, response, errors);
        		}
        		
        		Set jpoMemberOrderFeeSet = null;
        		jpoMemberOrderFeeSet = this.fillInJfoMemberOrderFee(request,jpoMemberOrder,jpoMemberOrderSet);//生成订单明细
            	if(jpoMemberOrderFeeSet == null || jpoMemberOrderFeeSet.size()==0){
	        		//没有指定物流公司
	        		errors.reject("erro.pd.shNo.isEmpty", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"erro.pd.shNo.isEmpty"));
	        		return showForm(request, response, errors);
            	}
        		
        		int result = this.calcOrderAmount(request,jpoMemberOrderSet,jpoMemberOrderFeeSet,jpoMemberOrder);//计算总PV总金额
        		if(result==1){
            		errors.reject("pv.notEnoughs", new Object[] {"NLC000000000CN0"},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"pv.notEnoughs"));
            		return showForm(request, response, errors);
        		}
        		if(result==2){
            		errors.reject("pv.notEnough4", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"pv.notEnough4"));
            		return showForm(request, response, errors);
        		}
        		if(result==3){
            		errors.reject("samount.notEnough", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"samount.notEnough"));
            		return showForm(request, response, errors);
        		}
        		
        		boolean enoughtPV = this.checkAmount(jpoMemberOrder);
        		if(enoughtPV==false){
            		errors.reject("pv.notEnough", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"pv.notEnough"));
            		return showForm(request, response, errors);
        		}
    			
    			if("addPoMemberSSubUOrderLC".equals(strAction)){
    				jpoMemberOrder.setOrderTime(new Date());
    				//String memberOrderNo = sysIdManager.buildIdStr("pono");//生成订单编号
    				//jpoMemberOrder.setMemberOrderNo(memberOrderNo);
    				key="poMemberOrder.added";
    			}else{
    				key="poMemberOrder.updated";
    			}
            	
        		jpoMemberOrderManager.editJpoMemberOrder(jpoMemberOrder, jpoMemberOrderSet, jpoMemberOrderFeeSet);
    		}catch(NumberFormatException e){
        		errors.reject("error.isNotInteger", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"error.isNotInteger"));
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
        			"pickup"
        	};
        	binder.setAllowedFields(allowedFields);
    	}
		// TODO Auto-generated method stub
		//		binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
    *//**
     * 判断订单是否通过检验
     * @param jpoMemberOrder
     * @param errors
     * @return
     *//*
    private boolean checkPoMemberOrder(JpoMemberOrder jpoMemberOrder,BindException errors){
    	boolean isNotPass=false;
    	if("CN".equals(jpoMemberOrder.getCompanyCode())){
    		Pattern pattern = Pattern.compile("^[1]([0-9]{10})$");
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
    	return isNotPass;
    }
    
    *//**
     * 检查订单总价钱
     * @param poMemberOrder
     * @return
     *//*
    private boolean checkAmount(JpoMemberOrder jpoMemberOrder){
    	BigDecimal pv =new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "store.u2.orderlc.pv"));
    	if(pv.compareTo(jpoMemberOrder.getPvAmt())<1){
    		return true;
    	}
    	return false;
    }
    
    *//**
     * 计算费用
     * @param request
     * @param jpoMemberOrderSet
     * @param jpoMemberOrder
     *//*
    private int calcOrderAmount(HttpServletRequest request, Set jpoMemberOrderSet, Set jpoMemberOrderFeeSet, JpoMemberOrder jpoMemberOrder){
    	boolean isPass = false;
    	
    	BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	BigDecimal sumPrice1 = new BigDecimal(0);
    	BigDecimal sumPV1 = new BigDecimal(0);
    	BigDecimal sumPrice2 = new BigDecimal(0);
    	BigDecimal sumPV2 = new BigDecimal(0);
    	//BigDecimal sumPrice3 = new BigDecimal(0);
    	//BigDecimal sumPV3 = new BigDecimal(0);
    	
    	Iterator its1 = jpoMemberOrderSet.iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			if(!"LC".equals(jpoMemberOrderList.getProductType())){
				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty()))) ;
			}else{
				sumPrice1 = sumPrice1.add(jpoMemberOrderList.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				sumPV1 = sumPV1.add(jpoMemberOrderList.getJpmProductSaleTeamType().getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty()))) ;
			}
    		if("NLC000000000CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
    			isPass = true;
				sumPrice2 = sumPrice2.add(jpoMemberOrderList.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				sumPV2 = sumPV2.add(jpoMemberOrderList.getJpmProductSaleTeamType().getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty()))) ;
    		}
    		if("NLC010000000CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
    			isPass = true;
				sumPrice3 = sumPrice3.add(jpoMemberOrderList.getJpmProductSale().getStoreFpPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				sumPV3 = sumPV3.add(jpoMemberOrderList.getJpmProductSale().getStoreFpPv().multiply(new BigDecimal(jpoMemberOrderList.getQty()))) ;
    		}
		}
    	
    	if(sumPrice.subtract(sumPrice2.add(sumPrice3)).compareTo(sumPrice1.multiply(new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getCompanyCode().toUpperCase(), "lc.discount"))))==-1){
    		return 2;//赠品最多为总金额的50%
    	}
    	if(sumPrice.subtract(sumPrice2.add(sumPrice3)).compareTo(new BigDecimal("18000"))==-1){
    		return 3;//小于18000
    	}
    	if(sumPrice.subtract(sumPrice2).compareTo(sumPrice1.multiply(new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getCompanyCode().toUpperCase(), "lc.discount"))))==-1){
    		return 2;//赠品最多为总金额的50%
    	}
    	if(sumPrice.subtract(sumPrice2).compareTo(new BigDecimal("18000"))==-1){
    		return 3;//小于18000
    	}
    	if("CN".equals(jpoMemberOrder.getCompanyCode())){
        	if(sumPrice.compareTo(new BigDecimal("3000"))!=-1){
        		java.util.Calendar startc=java.util.Calendar.getInstance();
        		startc.set(2011, 4, 17, 00, 00, 00);
        		java.util.Calendar endc=java.util.Calendar.getInstance();
        		endc.set(2011, 4, 30, 00, 00, 00);
        		java.util.Date startDate=startc.getTime();
        		java.util.Date endDate=endc.getTime();
        		Date curDate=new Date();
        		if((curDate.after(startDate))&&(curDate.before(endDate))){
        			sumPrice = new BigDecimal("0");
        			its1 = jpoMemberOrderSet.iterator();
        	    	while (its1.hasNext()) {
        				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        				if(!"LC".equals(jpoMemberOrderList.getProductType()) && !"NLC000000000CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
        					jpoMemberOrderList.setPrice(jpoMemberOrderList.getPrice().multiply(new BigDecimal("0.9")));
        					sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
        				}else{
        					sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
        				}
        			}
        	    }
        	}
    	}
    	
		if(isPass == false){
			//入会费没有
    		return 1;
		}
    	
    	Iterator its2 = jpoMemberOrderFeeSet.iterator();
    	while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
		}
    	
    	jpoMemberOrder.setAmount(sumPrice);
    	jpoMemberOrder.setPvAmt(sumPV);
    	
    	return 0;
    }

    *//**
     * 增加费用
     * @param request
     * @param jpoMemberOrder
     * @return
     *//*
    private Set fillInJfoMemberOrderFee(HttpServletRequest request, JpoMemberOrder jpoMemberOrder,Set jpoMemberOrderSet){
    	Set jpoMemberOrderFeeSet = new HashSet(0);
    	if("CN".equals(jpoMemberOrder.getCompanyCode()) || "HK".equals(jpoMemberOrder.getCompanyCode()) ){
        	//物流费
        	JpoMemberOrderFee jpoMemberOrderFee = this.shippingFeeCalc(jpoMemberOrder, jpoMemberOrderSet, request);
        	if(jpoMemberOrderFee!=null){
        		//店铺首购不收物流费
        		jpoMemberOrderFee.setFee(new BigDecimal("0"));
        		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
        	}
        	if("5".equals(jpoMemberOrder.getPayMode())){
        		//快钱费
        		JpoMemberOrderFee jpoMemberOrderFee1 = new JpoMemberOrderFee();
        		jpoMemberOrderFee1.setJpoMemberOrder(jpoMemberOrder);
        		jpoMemberOrderFee1.setFee(sumPrice.multiply(new BigDecimal("0.005")));
        		jpoMemberOrderFee1.setFeeType("2");//手续费
        		jpoMemberOrderFee1.setDetailType("BILL99");
        		jpoMemberOrderFeeSet.add(jpoMemberOrderFee1);
        	}
    	}
    	return jpoMemberOrderFeeSet;
    }
    
    *//**
     * 计算物流费
     * @param jpoShippingFee
     * @param sum
     * @param dtProduct
     * @param request
     * @return
     *//*
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
        		if("P04010118001CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
	    			dtProduct = 1;
	    		}
	    		if("P04010115001CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
	    			dtProduct = 1;
	    		}
	    		if("NLC000000000CN0".equals(jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo())){
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
	    		}
	    		if(list.get(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())!=null){
	    			dtProduct = 1;
	    		}
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
    	}else{
    		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setDetailType("0000");
    		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
    		return jpoMemberOrderFee;
    	}
    	return null;
    }
    
    *//**
     * 生成订单明细
     * @param request
     * @param poMemberOrder
     * @param defSysUser
     * @return
     *//*
    private Set fillInJfoMemberOrderList(HttpServletRequest request, JpoMemberOrder jpoMemberOrder){
    	String[] qty = request.getParameterValues("qty");
        String[] g_no = request.getParameterValues("g_no");
        Set jpoMemberOrderListSet = new HashSet(0);
		for(int i_no = 0 ; i_no < g_no.length ; i_no++){
			if( !StringUtils.isEmpty(qty[i_no]) && !"0".equals(qty[i_no])){
				if(Integer.parseInt(qty[i_no])<0){
					return null;
				}
				String step = g_no[i_no].substring(g_no[i_no].length()-1, g_no[i_no].length());
				String gNO = g_no[i_no].substring(0, g_no[i_no].length()-1);
				JpmProductSale jpmProductSale = jpmProductSaleManager.getJpmProductSale(gNO);
				if(jpmProductSale==null){
					return null;
				}
				if(!"LC".equals(jpmProductSale.getJpmProduct().getProductProvider())){
					return null;
				}
				JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();   
				jpoMemberOrderList.setJpmProductSale(jpmProductSale);
				if("2".equals(step)){
					jpoMemberOrderList.setPrice(jpmProductSale.getStoreFpPrice());
					jpoMemberOrderList.setPv(jpmProductSale.getStoreFpPv());
				}else if("3".equals(step)){
					jpoMemberOrderList.setPrice(new BigDecimal("0"));
					jpoMemberOrderList.setPv(new BigDecimal("0"));
					jpoMemberOrderList.setProductType("LC");
				}
				jpoMemberOrderList.setQty(Integer.parseInt(qty[i_no]));
    			jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
    			jpoMemberOrderList.setVolume(jpmProductSale.getVolume());
    			jpoMemberOrderList.setWeight(jpmProductSale.getWeight());
    			if("NLC000000000CN0".equals(jpmProductSale.getJpmProduct().getProductNo())){
    				jpoMemberOrderList.setQty(1);
    			}
    			if("NLC010000000CN0".equals(jpmProductSale.getJpmProduct().getProductNo())){
    				jpoMemberOrderList.setQty(1);
    			}
    			jpoMemberOrderListSet.add(jpoMemberOrderList);
				
			}
		}
		if(jpoMemberOrderListSet.size()==0){
			return null;
		}
    	return jpoMemberOrderListSet;
    }*/
}
