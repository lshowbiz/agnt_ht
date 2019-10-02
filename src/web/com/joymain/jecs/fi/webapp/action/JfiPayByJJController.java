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
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.FiBankbookBalanceManager;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 发展基金支付表单
 * @author Alvin
 *
 */
public class JfiPayByJJController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FiBankbookBalanceManager fiBankbookBalanceManager = null;
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
        if(!isOver.equals("")){
        	return new ModelAndView("fi/jfiOrderProductMsg", "isOver", "产品(" + isOver + ")已售完!");
        }
    	
    	if("30".equals(jpoMemberOrder.getOrderType())){
    		return new ModelAndView("redirect:jpoMemberIROrders.html?needReload=1");
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
        	
        request.setAttribute("jpoMemberOrder", jpoMemberOrder);
        
        FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode(),"1");
        request.setAttribute("coin", fiBankbookBalance.getBalance());
        
        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
        
        if("POST".equals(request.getMethod())){
        	BigDecimal amount = new BigDecimal(request.getParameter("amount"));

            	jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
            	boolean isCheck = false;
            	if(amount.compareTo(new BigDecimal("0"))==1){
            		jpoMemberOrder.setPayByJJ("1");
                	if(amount.compareTo(jpoMemberOrder.getAmount())!=-1){
                		jpoMemberOrder.setJjAmount(jpoMemberOrder.getAmount());
                		jpoMemberOrder.setAmount(new BigDecimal("0"));
                	}else{
                		jpoMemberOrder.setJjAmount(amount);
                		jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(amount));
                	}
                	isCheck = this.checkFlagOne(orderId, loginSysUser);
            	}else if(amount.compareTo(new BigDecimal("0"))==-1){
            		isCheck = false;
            	}else{
                	isCheck = this.checkFlagOne(orderId, loginSysUser);
            	}
            	
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
        
        return new ModelAndView("fi/jfiPayByJJ");
    }
	
	/**
	 * 审核订单
	 * @param orderId
	 * @param operatorSysUser
	 */
	private boolean checkFlagOne(String orderId, SysUser operatorSysUser){
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
		try{
			if("1".equals(jpoMemberOrder.getPayByJJ())){
				jpoMemberOrderManager.checkJpoMemberOrderByJJ(jpoMemberOrder, operatorSysUser,"1");
			}else{
				jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser,"1");
			}
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

	public void setFiBankbookBalanceManager(
			FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
	}
}
