package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.FiBcoinBalanceManager;
import com.joymain.jecs.fi.service.FiBcoinJournalManager;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.alipay.Alipay;
import com.joymain.jecs.util.bill99.Bill99;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * EC积分支付表单
 * @author Alvin
 *
 */
public class JfiPayByCoinAndBankbookController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(JfiPayByCoinAndBankbookController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FiBcoinBalanceManager fiBcoinBalanceManager = null;
    private AlCountryManager alCountryManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;

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
        	
        JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
        Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        if("CN".equals(jpoMemberOrder.getCompanyCode())){
            while (its1.hasNext()) {
            	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
            	String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();//商品状态，是否过期
            	if(!"1".equals(status)){
            		return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo() + ")已售完!");
            	}
            }
        }else{
        	return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "订单不存在!");
        }
        	
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
        //抢购时间：2012年4月21日-5月4日
        its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        while (its1.hasNext()) {
        	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        	String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
        	if(jpoMemberOrderManager.getIsOver2(productNo)==0){
        		isOver = productNo;
        		break;
        	}
        }
        //积分换购抢购
        its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        while (its1.hasNext()) {
        	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        	String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
        	if(jpoMemberOrderManager.getIsOver3(productNo)<=0){
        		isOver = productNo;
        		break;
        	}
        }
        if(!isOver.equals("")){
        	return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品(" + isOver + ")已售完!");
        }
        	
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
        	}else{
        		return null;
        	}
        }
        
        if(!"5".equals(jpoMemberOrder.getOrderType())&&!"4".equals(jpoMemberOrder.getOrderType())&&!"9".equals(jpoMemberOrder.getOrderType())&&!"14".equals(jpoMemberOrder.getOrderType())){
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
        	}else{
        		return null;
        	}
        }else{
        	if(!"5".equals(jpoMemberOrder.getOrderType())){
/*            	java.util.Calendar startc=java.util.Calendar.getInstance();
    	    	startc.set(2012, 5, 2, 00, 00, 00);
    	    	java.util.Calendar endc=java.util.Calendar.getInstance();
    	    	endc.set(2012, 5, 9, 00, 00, 00);
    	    	java.util.Date startDate=startc.getTime();
    	    	java.util.Date endDate=endc.getTime();
    	    	Date curDate=new Date();
    	    	if(!((curDate.after(startDate))&&(curDate.before(endDate))&&"LC".equals(jpoMemberOrder.getProductType()))){
    	    		if("4".equals(jpoMemberOrder.getOrderType())){
	               		return new ModelAndView("redirect:jpoMemberROrders.html?needReload=1");
	            	}else if("9".equals(jpoMemberOrder.getOrderType())){
	                	return new ModelAndView("redirect:jpoMemberSROrders.html?needReload=1");
	            	}else if("14".equals(jpoMemberOrder.getOrderType())){
	                	return new ModelAndView("redirect:jpoMemberSSubROrders.html?needReload=1");
	            	}else{
	            		return null;
	            	}
    	    	}
	    		String treeIndex = jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getTreeIndex();
	    		if(!(treeIndex.length()>=36&&treeIndex.substring(0,36).equals("00100000000000000b00n00000u003000013"))){
    	    		if("4".equals(jpoMemberOrder.getOrderType())){
	               		return new ModelAndView("redirect:jpoMemberROrders.html?needReload=1");
	            	}else if("9".equals(jpoMemberOrder.getOrderType())){
	                	return new ModelAndView("redirect:jpoMemberSROrders.html?needReload=1");
	            	}else if("14".equals(jpoMemberOrder.getOrderType())){
	                	return new ModelAndView("redirect:jpoMemberSSubROrders.html?needReload=1");
	            	}else{
	            		return null;
	            	}
	    		}*/
        		int payByCoin = Integer.parseInt(ConfigUtil.getConfigValue("CN","paybycoin"));
        		if(payByCoin==0){
    	    		if("4".equals(jpoMemberOrder.getOrderType())){
	               		return new ModelAndView("redirect:jpoMemberROrders.html?needReload=1");
	            	}else if("9".equals(jpoMemberOrder.getOrderType())){
	                	return new ModelAndView("redirect:jpoMemberSROrders.html?needReload=1");
	            	}else if("14".equals(jpoMemberOrder.getOrderType())){
	                	return new ModelAndView("redirect:jpoMemberSSubROrders.html?needReload=1");
	            	}else{
	            		return null;
	            	}
    	    	}
        	}else{
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
    				return new ModelAndView("redirect:jpoMemberAOrders.html?needReload=1");
    			}else if(preferential==2){
    				
    			}else{
    				return new ModelAndView("redirect:jpoMemberAOrders.html?needReload=1");
    			}
        	}
        }
        	
        request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        
        FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.getFiBcoinBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("coin", fiBcoinBalance.getBalance());
        

		BigDecimal productAmount = new BigDecimal("0");
    	its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			productAmount = productAmount.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    	}
        BigDecimal sumCoin = new BigDecimal("0");

		if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2")))!=1){
			sumCoin = productAmount.multiply(new BigDecimal("0.5"));
		}else{
			sumCoin = fiBcoinBalance.getBalance();
		}
		if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2")))==-1){
			throw new AppException("积分计算有误");
		}
		java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2012, 6, 21, 00, 00, 00);
		java.util.Date startDate=startc.getTime();
		Date curDate=new Date();
		if(curDate.after(startDate)){//7月21日，改成辅消品6折
			if("5".equals(jpoMemberOrder.getOrderType())){
				if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2.5")))!=1){
					sumCoin = productAmount.multiply(new BigDecimal("0.4"));
				}else{
					sumCoin = fiBcoinBalance.getBalance();
				}
				if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2.5")))==-1){
					throw new AppException("积分计算有误");
				}
			}
		}
        
        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        
        if(sumCoin.compareTo(new BigDecimal("0"))==1){
        	String msg = "您本张订单总金额" + jpoMemberOrder.getAmount() + "，使用金额" + jpoMemberOrder.getAmount().subtract(sumCoin) + "，使用积分" + sumCoin + "，PV为0";
        	request.setAttribute("msg", msg);
        }
        
        if("POST".equals(request.getMethod())){
        	jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
        	boolean isCheck = this.checkFlagOne(orderId, loginSysUser);
        	if(isCheck){
        		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
        	}else{
        		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poOrder.editedFail"));
        	}
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
        	}else{
        		return null;
        	}
        }

        //收货地区
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
        
        return new ModelAndView("fi/jfiPayByCoinAndBankbook");
    }
	
	/**
	 * 审核订单
	 * @param orderId
	 * @param operatorSysUser
	 */
	private boolean checkFlagOne(String orderId, SysUser operatorSysUser){
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
		try{
			jpoMemberOrderManager.checkJpoMemberOrderByCoinAndBankbook(jpoMemberOrder, operatorSysUser,"1");
			jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
		}catch(AppException app){
			if("checkError.Code2".equals(app.getMessage())){//已存在已审首购单
				
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void setFiBcoinBalanceManager(FiBcoinBalanceManager fiBcoinBalanceManager) {
		this.fiBcoinBalanceManager = fiBcoinBalanceManager;
	}
}
