package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.al.model.AlCity;
import com.joymain.jecs.al.model.AlCountry;
import com.joymain.jecs.al.model.AlDistrict;
import com.joymain.jecs.al.service.AlCityManager;
import com.joymain.jecs.al.service.AlCountryManager;
import com.joymain.jecs.al.service.AlDistrictManager;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.model.FiBcoinPayconfig;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.FiBcoinBalanceManager;
import com.joymain.jecs.fi.service.FiBcoinPayconfigManager;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderListManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.alipay.Alipay;
import com.joymain.jecs.util.bill99.Bill99;
import com.joymain.jecs.util.bill99ms.Bill99ms;
import com.joymain.jecs.util.tenpay.Tenpay;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 快钱、支付宝支付表单
 * @author Alvin
 *
 */
public class JfiPay99billController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private AlCountryManager alCountryManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private FiBcoinBalanceManager fiBcoinBalanceManager = null;
    private JpoMemberOrderListManager jpoMemberOrderListManager;
    private FiBcoinPayconfigManager fiBcoinPayconfigManager = null;//积分换购活动
    
    public void setFiBcoinPayconfigManager(FiBcoinPayconfigManager fiBcoinPayconfigManager) {
		this.fiBcoinPayconfigManager = fiBcoinPayconfigManager;
	}

	public void setFiBcoinBalanceManager(FiBcoinBalanceManager fiBcoinBalanceManager) {
		this.fiBcoinBalanceManager = fiBcoinBalanceManager;
	}

	public void setAlCityManager(AlCityManager alCityManager) {
		this.alCityManager = alCityManager;
	}

	public void setAlDistrictManager(AlDistrictManager alDistrictManager) {
		this.alDistrictManager = alDistrictManager;
	}

	public void setAlCountryManager(AlCountryManager alCountryManager) {
		this.alCountryManager = alCountryManager;
	}

	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        String orderId = request.getParameter("orderId");
        
        //flag(0:为电子存折  1:订单)
        String flag = request.getParameter("flag");
        if("1".equals(flag)){
        	
        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
        	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        	if("CN".equals(jpoMemberOrder.getCompanyCode())){
        		
        		Integer proSum=0, proNoCount=0,countQty=0;
        		String proCount_str = ConfigUtil.getConfigValue("CN", "product.count");
        		log.info("product sum is:["+proCount_str+"]");
        		if(StringUtils.isNotBlank(proCount_str)){
        			proNoCount = Integer.parseInt(proCount_str);
        		}
        		
            	while (its1.hasNext()) {
            		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
            		String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();//商品状态，是否过期
            		
            		String proNo = jpoMemberOrderList.getJpmProductSaleTeamType().
            				getJpmProductSaleNew().getJpmProduct().getProductNo();
            		
            		if(proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO) || 
            				proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO1) || 
            				proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO2)){
            			
            			Integer temQty = 0;
            			if(proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO)){
            				temQty =jpoMemberOrderList.getQty();
            			}else if(proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO1)){
            				temQty = jpoMemberOrderList.getQty() * 3;
            			} else if(proNo.equalsIgnoreCase(com.joymain.jecs.Constants.PRONO2)){
            				temQty= jpoMemberOrderList.getQty() * 5;
            			}
            			countQty += temQty;
            			
            			log.info("countQty="+countQty);
            			proSum = jpoMemberOrderListManager.getSumQtyByProNo();
            			
            			if((proNoCount-proSum)<countQty){
                			return new ModelAndView("fi/jfiOrderProductMsg", "isOver","产品剩余不足!");
                		}
            		}
            		
            		log.info("proNoCount =["+proNoCount+"] " +
            				"and proSum is=["+proSum+"] countQty="+countQty);
            		
            		if(proSum >= proNoCount){
            			return new ModelAndView("fi/jfiOrderProductMsg", "isOver", 
            					"产品(" +proNo + ")已售完!");
            		}
            		
            		//商品状态不正常
            		if(!"1".equals(status)){
            			
            			//设置支付后返回的url
            			String url = "";
            			
            			
            			if("1".equals(jpoMemberOrder.getOrderType())){
            				url = "jpoMemberFOrders.html?needReload=1";
                		}else if("2".equals(jpoMemberOrder.getOrderType())){
                			if("1".equals(jpoMemberOrder.getIsSpecial())){
                				url = "jpoMemberSpecialUOrders.html?needReload=1";
                			}else{
                				url = "jpoMemberUOrders.html?needReload=1";
                			}
                		}else if("3".equals(jpoMemberOrder.getOrderType())){
                			url = "jpoMemberRSOrders.html?needReload=1";
                		}else if("4".equals(jpoMemberOrder.getOrderType())){
                    		url = "jpoMemberROrders.html?needReload=1";
                		}else if("5".equals(jpoMemberOrder.getOrderType())){
                			url = "jpoMemberAOrders.html?needReload=1";
                		}else if("6".equals(jpoMemberOrder.getOrderType())){
                    		url = "jpoMemberSFOrders.html?needReload=1";
                		}else if("7".equals(jpoMemberOrder.getOrderType())){
                			
                		}else if("8".equals(jpoMemberOrder.getOrderType())){
                			
                		}else if("9".equals(jpoMemberOrder.getOrderType())){
                    		url = "jpoMemberSROrders.html?needReload=1";
                		}else if("11".equals(jpoMemberOrder.getOrderType())){
                    		url = "jpoMemberSSubFOrders.html?needReload=1";
                		}else if("12".equals(jpoMemberOrder.getOrderType())){
                    		url = "jpoMemberSSubUOrders.html?needReload=1";
                		}else if("14".equals(jpoMemberOrder.getOrderType())){
                    		url = "jpoMemberSSubROrders.html?needReload=1";
                		}else if("30".equals(jpoMemberOrder.getOrderType())){
                        	url = "jpoMemberIROrders.html?needReload=1";
                    	}else{
                    		url = "";
                    	}
            			
            			if(!StringUtils.isEmpty(url)){
            				request.setAttribute("url", url);
            			}
            			return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo() + ")已售完!");
            		}            		
            	}
        	}else{
        		return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "订单不存在!");
        	}
//        	
//        	//道和国韵1993积分换购处理
//        	java.util.Calendar beginDate=java.util.Calendar.getInstance();
//        	beginDate.set(2013, 7, 10, 00, 00, 00);
//	    	java.util.Calendar endDate=java.util.Calendar.getInstance();
//	    	endDate.set(2013, 7, 24, 00, 00, 00);
//	    	java.util.Date beginTime=beginDate.getTime();
//	    	java.util.Date endTime=endDate.getTime();
//	    	Date nowDate=new Date();
//	    	//迭代订单里面的产品
//    		its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
//    		while (its1.hasNext()) {
//        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
//        		String productNo = jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo();
//        		
//        		if(("P1501010101CN0").equals(productNo)){
//        			 
//        			//判断是否在促销时间内（8.10-8.23）
//        			if((nowDate.after(beginTime))&&(nowDate.before(endTime))){
//        			
//        				//判断是否有组合
//	        			if(isHaveAotherProduct(jpoMemberOrder)){
//	        				return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品(" + jpoMemberOrderList.getJpmProductSale().getJpmProduct().getProductNo() + ")的订单中含有和其他产品组合，无法完成积分换购!");
//	        			}
//	        			
//	        			//判断积分余额是否足够
//	        			FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.getFiBcoinBalance(jpoMemberOrder.getSysUser().getUserCode());
//    	    	        if(fiBcoinBalance==null || fiBcoinBalance.getBalance()==null){
//    	    	        	return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "您目前还没有积分，无法完成积分换购!");
//    	    	        }
//	        			if(fiBcoinBalance.getBalance().compareTo(BigDecimal.ZERO)==0){
//    	    	        	return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "您的积分余额不足，无法完成积分换购!");
//    	    	        }
//    	    	        
//	        			//积分，只有特定的订单类型能用
//	    	    		if("4".equals(jpoMemberOrder.getOrderType())||"9".equals(jpoMemberOrder.getOrderType())||"14".equals(jpoMemberOrder.getOrderType())){
//	    	    			
//	    	    	        //直接跳转到积分支付页面
//		    	    		return new ModelAndView("redirect:jfiPayByCoinAndBankbook.html?strAction=jfiPayByCoinAndBankbook&orderId=" + orderId);
//	    	    	       
//	    	    		}        
//        			}else{
//        				return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "积分换购已过期，无法完成积分换购!");
//        			}   			
//        		}
//        	}
        	
        	//活动，目前没有用到
        	//抢购时间：2010年5月11日-5月16日
        	String isOver = "";
        	its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        	while (its1.hasNext()) {
        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
        		if(jpoMemberOrderManager.getIsOver(productNo)==true){
        			isOver = productNo;
        			break;
        		}
        	}
        	if(!isOver.equals("")){
        		return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品(" + isOver + ")已售完!");
        	}
        	
        	if("30".equals(jpoMemberOrder.getOrderType())){
        		return new ModelAndView("redirect:jpoMemberIROrders.html?needReload=1");
        	}
        	
        	//订单为空，订单状态已审核，不是当前用户
        	if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !loginSysUser.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode())){
        		if("1".equals(jpoMemberOrder.getOrderType())){
            		return new ModelAndView("redirect:jpoMemberFOrders.html?needReload=1");
        		}else if("2".equals(jpoMemberOrder.getOrderType())){
        			if("1".equals(jpoMemberOrder.getIsSpecial())){
        				return new ModelAndView("redirect:jpoMemberSpecialUOrders.html?needReload=1");
        			}else{
        				return new ModelAndView("redirect:jpoMemberUOrders.html?needReload=1");
        			}
        		}else if("3".equals(jpoMemberOrder.getOrderType())){
        			return new ModelAndView("redirect:jpoMemberRSOrders.html?needReload=1");
        		}else if("4".equals(jpoMemberOrder.getOrderType())){
            		return new ModelAndView("redirect:jpoMemberROrders.html?needReload=1");
        		}else if("5".equals(jpoMemberOrder.getOrderType())){
        			return new ModelAndView("redirect:jpoMemberAOrders.html?needReload=1");
        		}else if("6".equals(jpoMemberOrder.getOrderType())){
            		return new ModelAndView("redirect:jpoMemberSFOrders.html?needReload=1");
        		}else if("7".equals(jpoMemberOrder.getOrderType())){
        			
        		}else if("8".equals(jpoMemberOrder.getOrderType())){
        			
        		}else if("9".equals(jpoMemberOrder.getOrderType())){
            		return new ModelAndView("redirect:jpoMemberSROrders.html?needReload=1");
        		}else if("11".equals(jpoMemberOrder.getOrderType())){
            		return new ModelAndView("redirect:jpoMemberSSubFOrders.html?needReload=1");
        		}else if("12".equals(jpoMemberOrder.getOrderType())){
            		return new ModelAndView("redirect:jpoMemberSSubUOrders.html?needReload=1");
        		}else if("14".equals(jpoMemberOrder.getOrderType())){
            		return new ModelAndView("redirect:jpoMemberSSubROrders.html?needReload=1");
        		}else if("30".equals(jpoMemberOrder.getOrderType())){
                	return new ModelAndView("redirect:jpoMemberIROrders.html?needReload=1");
            	}else{
            		return null;
            	}
        	}

            //收货地区 ajax到订单信息
            AlCountry alCountry = (AlCountry) alCountryManager.getAlCountrysByCompany(loginSysUser.getCompanyCode()).get(0);
        	alCountry.getAlStateProvinces().iterator();
        	request.setAttribute("alStateProvinces", alCountry.getAlStateProvinces());

    		List<AlCity> citys=alCityManager.getAlCityByProvinceId(Long.parseLong(jpoMemberOrder.getProvince()));
    		for (AlCity city : citys) {
    			if(city.getCityId().toString().equals(jpoMemberOrder.getCity())){
    				jpoMemberOrder.setCity(city.getCityName());
        			List<AlDistrict> alDistricts = alDistrictManager.getAlDistrictByCityId(city.getCityId());
        			for (AlDistrict district : alDistricts) {
        				if(district.getDistrictId().toString().equals(jpoMemberOrder.getDistrict())){
        					jpoMemberOrder.setDistrict(district.getDistrictName());
        					break;
        				}
        			}
    			}
    		}
        	
    		//积分
    		if("5".equals(jpoMemberOrder.getOrderType())){
    			int preferential = jpoMemberOrderManager.getPreferentialOrder(jpoMemberOrder);
    			//===========
            	java.util.Calendar startc=java.util.Calendar.getInstance();
    	    	startc.set(2012, 7, 19, 23, 59, 59);
    	    	java.util.Date startDate=startc.getTime();
    	    	Date curDate=new Date();
    	    	if(curDate.after(startDate)){
        			//=================临时取消月饼限制，允许积分换购
    	    		preferential=2;
    	    	}
    			//===========
    	    	
    			if(preferential==0){
    				return new ModelAndView("redirect:jpoMemberAOrders.html?needReload=1");
    			}else if(preferential==1){
    				
    			}else if(preferential==2){
        			request.setAttribute("coinPay", "true");
    			}else{
    				return new ModelAndView("redirect:jpoMemberAOrders.html?needReload=1");
    			}
    		}
    		
/*        	java.util.Calendar startc=java.util.Calendar.getInstance();
	    	startc.set(2012, 5, 2, 00, 00, 00);
	    	java.util.Calendar endc=java.util.Calendar.getInstance();
	    	endc.set(2012, 5, 9, 00, 00, 00);
	    	java.util.Date startDate=startc.getTime();
	    	java.util.Date endDate=endc.getTime();
	    	Date curDate=new Date();
	    	if((curDate.after(startDate))&&(curDate.before(endDate))&&"LC".equals(jpoMemberOrder.getProductType())){
	    		String treeIndex = jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
	    		if(treeIndex.length()>=36&&treeIndex.substring(0,36).equals("00100000000000000b00n00000u003000013")){
		    		if("4".equals(jpoMemberOrder.getOrderType())||"9".equals(jpoMemberOrder.getOrderType())||"14".equals(jpoMemberOrder.getOrderType())){
		    			request.setAttribute("coinPay", "true");
		    		}
	    		}
	    	}*/
//    		int payByCoin = Integer.parseInt(ConfigUtil.getConfigValue("CN","paybycoin"));
//    		if(payByCoin==1){
//    			
//    			//积分，只有特定的订单类型能用
//	    		if("4".equals(jpoMemberOrder.getOrderType())||"9".equals(jpoMemberOrder.getOrderType())||"14".equals(jpoMemberOrder.getOrderType())){
//	    			
//	    			//积分大于0才显示积分支付按钮
//	    			FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.getFiBcoinBalance(jpoMemberOrder.getSysUser().getUserCode());
//        			if(fiBcoinBalance!=null && fiBcoinBalance.getBalance().compareTo(BigDecimal.ZERO)==1){
//        				
//        				request.setAttribute("coinPay", "true");
//	    	        }    
//	    		}
//	    	}
    		
    		//重消单积分换购
    		if("4".equals(jpoMemberOrder.getOrderType())||"9".equals(jpoMemberOrder.getOrderType())||"14".equals(jpoMemberOrder.getOrderType())){
    			
    			//积分大于0才显示积分支付按钮
    			FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.getFiBcoinBalance(jpoMemberOrder.getSysUser().getUserCode());
    			if(fiBcoinBalance!=null && fiBcoinBalance.getBalance().compareTo(BigDecimal.ZERO)==1){
    				
    				//查询积分换购活动
    				FiBcoinPayconfig fiBcoinPayconfig = fiBcoinPayconfigManager.getFiBcoinPayconfigByNowTime();
    				
    				if(fiBcoinPayconfig != null){
            			
            			if(("0").equals(fiBcoinPayconfig.getLimit())){
            				
            				request.setAttribute("coinPay", "true");
            			}else{
            				
            				//查询当前订单积分换购商品权限
                			if(fiBcoinPayconfigManager.getCanUseCoinPayByOrder(fiBcoinPayconfig, jpoMemberOrder)){
                				
                				request.setAttribute("coinPay", "true");
                			}
            			}        			
            		}
    	        }    
    		}
    		
    		
        	request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        	request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        }
        if(StringUtils.isEmpty(flag)){
        	request.setAttribute("flag", 0);
        }else{
        	request.setAttribute("flag", flag);
        }
        //目前这三个都没有用到，快钱分账媒体有用到
        //支付宝
        boolean userAlipay = false;
        //财务通
        boolean userTenpay = false;
        //快钱分账
        boolean userBill99MS = false;
        
        if(userAlipay){
            if("1".equals(loginSysUser.getJmiMember().getMemberType()) || "2".equals(loginSysUser.getJmiMember().getMemberType())){
            	Alipay alipay = new Alipay();
            	alipay.setOut_trade_no(orderId);
                return new ModelAndView("fi/jfiPayAlipay", "jfi99bill", alipay);
            }
        }
        if(userTenpay){
        	String province;
        	if(StringUtils.isEmpty(flag)){
        		if(loginSysUser.getJmiMember().getProvince()==null){
    				province = "null";
    			}else{
    				province = loginSysUser.getJmiMember().getProvince();
    			}
        	}else{
        		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
        		province = jpoMemberOrder.getProvince();
        	}
        	Map<String,String> map = new HashMap<String,String>();
/*        	map.put("163719", "tenpay");
        	map.put("163711", "tenpay");
        	map.put("163715", "tenpay");
        	
        	map.put("163709", "tenpay");
        	map.put("163708", "tenpay");
        	map.put("163707", "tenpay");

        	map.put("163725", "tenpay");
        	map.put("163722", "tenpay");
        	map.put("163724", "tenpay");*/
        	
        	map.put("163730", "tenpay");
        	map.put("163727", "tenpay");
        	map.put("163732", "tenpay");
        	map.put("null", "tenpay");
        	if(map.get(province)!=null){
        		Tenpay tenpay = new Tenpay();
        		return new ModelAndView("fi/jfiPayTenpay", "jfi99bill", tenpay);
        	}
        }
        if(userBill99MS==true){
            Bill99ms jfi99billms = new Bill99ms();
            jfi99billms.setPayerName(loginSysUser.getUserName());
            jfi99billms.setOrderId(orderId);
            return new ModelAndView("fi/jfiPay99billms", "jfi99billms", jfi99billms);
        }
        
        //快钱支付
        Bill99 jfi99bill = new Bill99();
        jfi99bill.setPayerName(loginSysUser.getUserName());
        jfi99bill.setOrderId(orderId);
        
        return new ModelAndView("fi/jfiPay99bill", "jfi99bill", jfi99bill);
    }
	
	/**
	 * 是否含有其他商品
	 * @param its1
	 * @return
	 */
	private boolean isHaveAotherProduct(JpoMemberOrder jpoMemberOrder) {
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			if(!"P1501010101CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())) {
				return true;
		    }
		}
    	return false;
	}

	public JpoMemberOrderListManager getJpoMemberOrderListManager() {
		return jpoMemberOrderListManager;
	}

	public void setJpoMemberOrderListManager(
			JpoMemberOrderListManager jpoMemberOrderListManager) {
		this.jpoMemberOrderListManager = jpoMemberOrderListManager;
	}
	
}
