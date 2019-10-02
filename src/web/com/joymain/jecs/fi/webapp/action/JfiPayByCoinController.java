package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
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
import com.joymain.jecs.fi.model.FiBankbookBalance;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.FiBankbookBalanceManager;
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
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 积分支付表单
 * @author Alvin
 *
 */
public class JfiPayByCoinController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FiBcoinBalanceManager fiBcoinBalanceManager = null;
    private AlCountryManager alCountryManager;
    private AlCityManager alCityManager;
    private AlDistrictManager alDistrictManager;
    private FiBankbookBalanceManager fiBankbookBalanceManager = null;

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
        if(!isOver.equals("")){
        	return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品(" + isOver + ")已售完!");
        }
        	
        if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !loginSysUser.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode()) || !"30".equals(jpoMemberOrder.getOrderType())){
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
        
        if(!"30".equals(jpoMemberOrder.getOrderType())){
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
        
        if((jpoMemberOrder.getAmount().divide(new BigDecimal("1280"),2, BigDecimal.ROUND_HALF_EVEN)).compareTo(jpoMemberOrder.getAmount().divide(new BigDecimal("1280")))!=0){
        	return null;
        }
        	
        request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        
		//发展基金
        //基金FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode(),"1");
        //基金request.setAttribute("coin", fiBankbookBalance.getBalance());
        //积分
        FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.getFiBcoinBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("bcoin", fiBcoinBalance.getBalance());
        //电子存折
        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        
		BigDecimal sumCoin = new BigDecimal("0");
		int qty = 0;
		Iterator its = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its.next();
			qty += jpoMemberOrderList.getQty();
		}
    	sumCoin = new BigDecimal("830").multiply(new BigDecimal(qty));
    	request.setAttribute("sumCoin", sumCoin);
    	BigDecimal amountAndJJ = jpoMemberOrder.getAmount().subtract(sumCoin);
        
        String msg = "您本张订单总金额" + jpoMemberOrder.getAmount() + "，使用金额" + amountAndJJ + "，使用积分" + sumCoin + "。基金不足自动扣除电子存折";
        request.setAttribute("msg", msg);
        
        if("POST".equals(request.getMethod())){
        	jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
        	boolean isCheck = false;
        	//基金BigDecimal amount = new BigDecimal(request.getParameter("amount"));
/*基金        	if(amount.compareTo(new BigDecimal("0"))==1){
        		jpoMemberOrder.setPayByJJ("1");
            	if(amount.compareTo(amountAndJJ)!=-1){
            		jpoMemberOrder.setJjAmount(amountAndJJ);
            		jpoMemberOrder.setAmount(new BigDecimal("0"));
            	}else{
            		jpoMemberOrder.setJjAmount(amount);
            		jpoMemberOrder.setAmount(amountAndJJ.subtract(amount));
            	}
            	isCheck = this.checkFlagOne(orderId, loginSysUser);
        	}else if(amount.compareTo(new BigDecimal("0"))==-1){
        		isCheck = false;
        	}else{
        		jpoMemberOrder.setAmount(amountAndJJ);
            	isCheck = this.checkFlagOne(orderId, loginSysUser);
        	}*/
        	//jpoMemberOrder.setAmount(amountAndJJ);
        	isCheck = this.checkFlagOne(orderId, loginSysUser);
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
        
        return new ModelAndView("fi/jfiPayByCoin");
    }
	
	/**
	 * 审核订单
	 * @param orderId
	 * @param operatorSysUser
	 */
	private boolean checkFlagOne(String orderId, SysUser operatorSysUser){
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
		try{
			jpoMemberOrderManager.checkJpoMemberOrderByCoin(jpoMemberOrder, operatorSysUser,"1");
			jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
		}catch(AppException app){
			if("checkError.Code2".equals(app.getMessage())){//已存在已审首购单
				
			}
			app.printStackTrace();
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

	public void setFiBankbookBalanceManager(
			FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
	}
}
