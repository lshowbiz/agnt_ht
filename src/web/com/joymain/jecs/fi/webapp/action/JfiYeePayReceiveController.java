package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.model.JfiYeepayLog;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.ServerDateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.util.yeepay.YeePayUtil;
import com.joymain.jecs.util.yspay.PayUtils;
import com.joymain.jecs.util.yspay.RemarkBean;
import com.joymain.jecs.webapp.action.PayBaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 接收易宝支付付款通知
 * @author Alvin
 *
 */
public class JfiYeePayReceiveController extends PayBaseController implements Controller {

    private final Log log = LogFactory.getLog(JfiYeePayReceiveController.class);
    private YeePayUtil yeePayUtil = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private SysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private FoundationOrderManager foundationOrderManager = null;

	public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
        this.foundationOrderManager = foundationOrderManager;
    }

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public void setYeePayUtil(YeePayUtil yeePayUtil) {
		this.yeePayUtil = yeePayUtil;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		SysUser loginSysUser = SessionLogin.getLoginUser(request);
		try{
    		log.info("===================yeepay is comming!");
	    	//验签、保存通知数据
			JfiYeepayLog jfiYeepayLog = yeePayUtil.getJfiYeepayLog(request);
			log.info("===================jfiYeepayLog"+jfiYeepayLog.getReturnMsg());
	    	//验签成功
	    	if(("10").equals(jfiYeepayLog.getReturnMsg())){
	            
	            String reserved = request.getParameter("r8_MP");
	    		RemarkBean bean = PayUtils.getRemarkBean(reserved);
	    		SysUser sysUser = sysUserManager.getSysUser(bean.getUserCode());	    		 
	    		//付款数据进存折
	    		jfiBankbookJournalManager.saveJfiPayDataVerifyByYeePay("CN", sysUser, new BigDecimal(jfiYeepayLog.getAmount()), sysUser.getUserCode(), sysUser.getUserName(),jfiYeepayLog.getDetailId(),jfiYeepayLog);
	    		log.info("===================flag"+bean.getPayType());
	    		boolean isCheck = false;
	    		if("1".equals(bean.getPayType())){//订单审核
		    		
	    			//订单编号
		    		String orderId = request.getParameter("r6_Order");
		    		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
		    		
		    		//=======================================是否全额支付
		    		Integer isFullPay = 0;
		    		if(bean.getIsFull()){
		    			isFullPay = getFullpay(jfiYeepayLog, jpoMemberOrder);
		    		}
		    		jpoMemberOrder.setIsFullPay(isFullPay);
					//=======================================是否全额支付
					// 支付时间
					jpoMemberOrder.setSubmitTime(ServerDateUtil.getNowTimeFromDateServer());
					jpoMemberOrder.setSubmitUserCode(jpoMemberOrder.getSysUser().getUserCode());
					jpoMemberOrder.setPayCode(jfiYeepayLog.getMerchantId());
					
					jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
		    		//审单、扣款
		    		isCheck = this.checkFlagOne(orderId, sysUser);
		    		BigDecimal payAmount = new BigDecimal(jfiYeepayLog.getAmount());
		    		log.info("-------------------------------------isfullpay :" + jpoMemberOrder.getAmount2() +"aaaaaaa" + payAmount);
		    		log.info("JfiYeePayReceive ischeck:" + isCheck +"  jpoMemberOrder1.getIsPay: " + jpoMemberOrder.getIsPay());
		    		if(isCheck){
	                    log.info("-------------------------------------isfullpay :" + jpoMemberOrder.getAmount2() +"aaaaaaa" + payAmount);
	            		//jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
	            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
	            	}else{
	            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poOrder.editedFail"));
	            	}
		    
	    		}
	    		//慈善基金订单审核
            	if("2".equals(bean.getPayType())){
            		
            		String orderId = request.getParameter("orderId");
            		FoundationOrder foundationOrder = foundationOrderManager.getFoundationOrder(orderId);
            		try{
            			//支付完成，自动审核订单
            			foundationOrderManager.checkFoundationOrder(foundationOrder,sysUser);
            		}catch(AppException app){
            		}
            	}
            	log.info("===================yeepay success success");
            	//支付成功返回信息给易宝
            	response.getWriter().write("SUCCESS");
	    	}
	
	    	if(("3").equals(jfiYeepayLog.getReturnMsg())){//数据重发
	    		
	    		response.getWriter().write("SUCCESS");
	    	}
	
	    	if(("4").equals(jfiYeepayLog.getReturnMsg())){//验签失败
	    		
	    		response.getWriter().write("Hmac Not Correction!");
	    	}
	    	return null;
    	}catch(Exception e){
    		
    		log.info("============易宝支付入账异常=================="+e.getMessage());
        	e.printStackTrace();
        	System.err.println("=========失败第三方请求支付地址===============" +  RequestUtil.paramStr2(request));
        	return new ModelAndView("redirect:403.jsp");
        }
    }
	
	public Integer getFullpay(JfiYeepayLog entity, JpoMemberOrder jpoMemberOrder) {
		JfiPayLog payLog = new JfiPayLog();
		payLog.setMerchantId(entity.getMerchantId());
		payLog.setOrderAmount(entity.getOrderAmount());
		payLog.setUserCode(entity.getUserCode());
		return super.getFullpay(payLog, jpoMemberOrder);
	}
	
	/**
	 * 审核订单
	 * @param orderId
	 * @param operatorSysUser
	 */
	private boolean checkFlagOne(String orderId, SysUser operatorSysUser){
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
		try{
			jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser,"1");
//			jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
		}catch(AppException app){
			//记录日志 Modify By WuCF 20140304 
			log.info("审核订单抛出异常-----checkFlagOne===:orderId"+orderId+"---err:"+app);
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}