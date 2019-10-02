package com.joymain.jecs.po.webapp.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.pm.service.JmiMemberTeamManager;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.pm.service.JpmSalePromoterManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.po.service.JpoShippingFeeManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.GlobalVar;
import com.joymain.jecs.util.MeteorUtil;
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
/**
 * 会员重销订购表单
 * @author Alvin
 *
 */
public class JpoMemberROrderFormController extends BaseFormController {
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JmiMemberManager jmiMemberManager = null;
    private JmiAddrBookManager jmiAddrBookManager = null;
    private AlCountryManager alCountryManager = null;
    private JpmProductSaleManager jpmProductSaleManager = null;
    private JpoShippingFeeManager jpoShippingFeeManager = null;
    private JmiMemberTeamManager jmiMemberTeamManager=null;
    private JpmSalePromoterManager jpmSalePromoterManager;
    
    public JpoMemberROrderFormController() {
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
		String orderType = "";
		
		orderType = getParValue(request, "orderType");//订单类型
        if(MeteorUtil.isBlank(orderType)){
        	orderType = "4";
        }
        
        if ("deletePoMemberROrder".equals(strAction) || "editPoMemberROrder".equals(strAction) && !StringUtils.isEmpty(moId)){
            jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);
            if("1".equals(jpoMemberOrder.getIsPay())){
            	throw new AppException("订单已支付!");
            }
            if("M".equals(loginSysUser.getUserType())){
                if(!jpoMemberOrder.getSysUser().getUserCode().equals(loginSysUser.getUserCode())){
                	throw new AppException("订单不属于当前登录者!");
                }
                if("1".equals(jpoMemberOrder.getCompanyPay())){
                	throw new AppException("跨国重消订单不可编辑!");
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
            jpoMemberOrder.setOrderType(orderType);
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
        
        //新的获取团队的方法,返回顶点
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
    				orderType,"1",teamCode,loginSysUser);
    	}else{
    		jpmProductSales = jpmProductSaleManager.getPmProductSalesNew(
    				loginSysUser.getCompanyCode(),
    				orderType,"2",teamCode,loginSysUser);
    	}
    	
    	request.setAttribute("jpmProductSales", jpmProductSales);
    	
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = DateUtil.getDateOffset(new Date(),5,3);
    	request.setAttribute("toDay",format.format(date));
        
    	if(RequestUtil.isMobileRequest(request)){
        	this.setFormView("/mobile/po/jpoMemberROrderForm");
        } else {
        	this.setFormView("/po/jpoMemberROrderForm");
        	
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
    	JpoMemberOrder jpoMemberOrder = (JpoMemberOrder) command;
    	
    	String personflag = request.getParameter("personflag");
        if("1".equals(personflag)){
        	String userCode = request.getParameter("sysUser.userCode");
        	if(!StringUtils.isEmpty(userCode)){
                JmiMember jmiMember = jmiMemberManager.getJmiMember(userCode);
                if(null == jmiMember){
                	errors.reject("miMember.notFound", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"miMember.notFound"));
                }
        	}
        	return showForm(request, response, errors);
        }
		    	
    	String orderType = jpoMemberOrder.getOrderType();
       
		String key=null;
		String strAction = request.getParameter("strAction");
		if ("deletePoMemberROrder".equals(strAction)) {
			jpoMemberOrderManager.removeJpoMemberOrder(jpoMemberOrder.getMoId().toString());
			key="poMemberOrder.deleted";
		}else{

			String companyPay = request.getParameter("companyPay");//是否为公司支付,1为是，0为否
			if(StringUtil.isEmpty(companyPay)){
				jpoMemberOrder.setCompanyPay("");
			}else{
				jpoMemberOrder.setCompanyPay("1");
			}

			if("addPoMemberROrder".equals(strAction)){
				JmiMember jmiMember = jmiMemberManager.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
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
				
				jpoMemberOrder.getSysUser().setJmiMember(jmiMember);
				if(validateOrder(jpoMemberOrder, errors, loginSysUser)){
		    		return showForm(request, response, errors);
		    	}
				
				String userCode = jmiMember.getUserCode();
				CommonRecord crm = new CommonRecord();
				crm.addField("sysUser.userCode", userCode);
				crm.addField("orderType", "1");
				crm.addField("status", "2");
				/*
				 * 判断首单是否存在
				 */
				Integer notfirst = jmiMember.getNotFirst();
				if(notfirst==null || notfirst !=1){
					List jpoMemberOrders = jpoMemberOrderManager
							.getJpoMemberOrdersByCrm(crm, new Pager(request, 0));
					if (jpoMemberOrders.size() == 0 ) {
						
						errors.reject("poMemberFOrder.isNotExist", new Object[] {},
								LocaleUtil.getLocalText(
										loginSysUser.getDefCharacterCoding(),
										"poMemberFOrder.isNotExist"));
						
						return showForm(request, response, errors);
					}
				}
				
			}
			
			if("C".equals(loginSysUser.getUserType())){
				JmiMember jmiMember = jmiMemberManager.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
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
        		}else{
            		if(!jpoIsOnly(jpoMemberOrderSet,GlobalVar.jpoList)){ //9种单独订购
            			errors.reject("nineonly", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"nineonly"));
            			return showForm(request, response, errors);
            		}
            		if(!jpoIsOnly(jpoMemberOrderSet,GlobalVar.zqList)){ //中秋礼盒单独订购
            			errors.reject("zqonly", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"zqonly"));
            			return showForm(request, response, errors);
            		}
        		}
        		
        		Set jpoMemberOrderFeeSet = null;
        		jpoMemberOrderFeeSet = this.fillInJfoMemberOrderFee(request,jpoMemberOrder,jpoMemberOrderSet);//生成订单明细
            	if(jpoMemberOrderFeeSet == null || jpoMemberOrderFeeSet.size()==0){
            		//没有指定物流公司
            		errors.reject("erro.pd.shippingFee.isEmpty", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"erro.pd.shippingFee.isEmpty"));
            		//RequestUtil.cleanOperationSession(request,"addPoMemberROrder");
            		return showForm(request, response, errors);
            	}
            	
            	java.util.Calendar startc2=java.util.Calendar.getInstance();
        		startc2.set(2013, 7, 10, 00, 00, 00);
        		java.util.Calendar endc2=java.util.Calendar.getInstance();
        		endc2.set(2013, 7, 24, 00, 00, 00);
        		java.util.Date startDate2=startc2.getTime();
        		java.util.Date endDate2=endc2.getTime();
        		Date curDate2=new Date();
        		if("CN".equals(jpoMemberOrder.getCompanyCode()) &&(curDate2.after(startDate2))&&(curDate2.before(endDate2))){
    					int numberValue=this.getPreferentialOrder(jpoMemberOrderSet);
    					if(numberValue==2)
    					{
    					 
    					errors.reject("poMemberOrder.tip", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poMemberOrder.tip"));
    					return showForm(request, response, errors);
    					}
    				
        		}
    		
        		this.calcOrderAmount(request,jpoMemberOrderSet,jpoMemberOrderFeeSet,jpoMemberOrder);//计算总PV总金额
				if("40".equals(orderType)){
        			//全年重消单  金额要大于等于3024
        			if(jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getCompanyCode(), "rorderyear.order.amount")))==-1){
        				errors.reject("poMemberOrder.amount.3024", new Object[] {},LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poMemberOrder.amount.3024"));
    					return showForm(request, response, errors);
        			}
        		}
    			
    			if("addPoMemberROrder".equals(strAction)){
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
        				String proNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
        				if("P08520100101CN0".equals(proNo)){
        					key="erro.pd.shNo.Monthly";
        	    		}
        			}
        		}
        		
        			jpoMemberOrderManager.editJpoMemberOrder(jpoMemberOrder, jpoMemberOrderSet, jpoMemberOrderFeeSet);	
        		
				
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
        ModelAndView mav=new ModelAndView(getSuccessView());
	    mav.addObject("orderType", orderType);
        return mav;
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
	private int getPreferentialOrder(Set jpoMemberOrderSet) {
			int booleanValue=0;
			int number=0;
			Iterator its1 = jpoMemberOrderSet.iterator();
	    	while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				if("P1501010101CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())) {
					
					 if(number==1)
					 {
						 booleanValue = 2;
						 break;
					 }else
					 {
						 number=2;
						 
					 }		
			    }else
			    {
			    	
			    	 if(number==2)
			    	 { 
			    		 booleanValue = 2;
			    		 break;
			    	 }else
			    	 {
			    		 
			    		 number=1;
			    		 
			    	 }
			    	 
			    }
			}
		return booleanValue;

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
    	BigDecimal old_sumPirce = sumPrice;
		String stime = DateUtil.convertDateToString(Calendar.getInstance().getTime());
		List<JpmSalePromoter> saleList = jpmSalePromoterManager.
				getSaleByDate(stime, Constants.JPMSALE_ACTIVA, 
						Constants.JPMSALE_DISCOUNT_ORDER);
		log.info("saleList size is:"+saleList.size());
		try {
			for(JpmSalePromoter sale: saleList){
				if(jpoMemberOrderManager.isOrderSales(sale, jpoMemberOrder)){
					if(sale.getAmount().compareTo(new BigDecimal(0))>0 && sale.getPv()>0L){
						if(sumPrice.compareTo(sale.getAmount())>0 && 
								sumPV.compareTo(new BigDecimal(sale.getPv()))>0L &&
								jpoMemberOrderManager.isOrderSales(sale, jpoMemberOrder)){
							//订单总金额大于促销规定的金额,则打折
							sumPrice = sumPrice.multiply(sale.getDiscount());
						}
					} else if(sale.getAmount().compareTo(new BigDecimal(0))<=0 && sale.getPv()> 0L){
						if(sumPV.compareTo(new BigDecimal(sale.getPv()))>0L &&
								jpoMemberOrderManager.isOrderSales(sale, jpoMemberOrder)){
							sumPrice = sumPrice.multiply(sale.getDiscount());
						}
					} else if(sale.getAmount().compareTo(new BigDecimal(0))>0 && sale.getPv() <= 0L){
						if(sumPrice.compareTo(sale.getAmount())>0 &&
								jpoMemberOrderManager.isOrderSales(sale, jpoMemberOrder)){
							sumPrice = sumPrice.multiply(sale.getDiscount());
						}
					}
		    	}
			}
		} catch (Exception e) {
			log.error("",e);
		}
		log.info("discount sumPrice is:["+sumPrice.toString()+"]");
		//折扣
		jpoMemberOrder.setDiscountAmount(old_sumPirce.subtract(sumPrice));
		
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
        	JpoMemberOrderFee jpoMemberOrderFee = jpoShippingFeeManager.
        			shippingFeeCalc(jpoMemberOrder, jpoMemberOrderSet, request);
        	if(jpoMemberOrderFee!=null){
        		//重销单物流费店铺出
        		jpoMemberOrderFee.setFee(new BigDecimal("0"));
        	
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
		
		java.util.Calendar start=java.util.Calendar.getInstance();
		start.set(2016, 3, 1, 00, 00, 00); //2016-4-1
    	java.util.Date startDat=start.getTime();
    	Date curDat=new Date();
    	if(curDat.before(startDat)){
    		
    		//全年重消满3276pv 打9折取消
    		if("CN".equals(jpoMemberOrder.getCompanyCode())  ){
    			BigDecimal allyearRorder = new BigDecimal(ConfigUtil.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "allyear.r.order"));//原先1638 全年重消新财年3276
    	    	java.util.Calendar startc=java.util.Calendar.getInstance();
    	    	startc.set(2012, 4, 14, 00, 00, 00);
    	    	java.util.Date startDate=startc.getTime();
    	    	Date curDate=new Date();
    	    	if((curDate.after(startDate)) && sumPV.compareTo(allyearRorder)!=-1 ){
    	    		Iterator its1 = jpoMemberOrderListSet.iterator();
    	    		while (its1.hasNext()) {
    	    			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
    	    			if(jpoMemberOrderList.getPv().compareTo(new BigDecimal("0"))==1){
    	    				jpoMemberOrderList.setPrice(jpoMemberOrderList.getJpmProductSaleTeamType().getPrice().multiply(new BigDecimal("0.9")));
    	    			}
    	    		}
    	    	}
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
	
	public void setJmiMemberTeamManager(JmiMemberTeamManager jmiMemberTeamManager) {
		this.jmiMemberTeamManager = jmiMemberTeamManager;
	}

	public JpmSalePromoterManager getJpmSalePromoterManager() {
		return jpmSalePromoterManager;
	}

	public void setJpmSalePromoterManager(
			JpmSalePromoterManager jpmSalePromoterManager) {
		this.jpmSalePromoterManager = jpmSalePromoterManager;
	}
	
	/**
	 * @Description 获取请求对象值
	 * @author houxyu
	 * @date 2016-4-10
	 * @param request
	 * @param param
	 * @return
	 */
	private static String getParValue(HttpServletRequest request, String param){
		String s = "";
		
		try {
			s = request.getParameter(param);
			if(MeteorUtil.isBlank(s)){
				s = (String)request.getAttribute(param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;
	}
	

}
