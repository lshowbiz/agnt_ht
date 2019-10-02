package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.model.JfiPayLog;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.pm.service.JpmMemberConfigManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.ServerDateUtil;
import com.joymain.jecs.util.bill99.Bill99Constants;
import com.joymain.jecs.util.bill99.Bill99Util;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.util.yspay.PayUtils;
import com.joymain.jecs.util.yspay.RemarkBean;
import com.joymain.jecs.webapp.action.PayBaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 快钱接收页面
 * @author Alvin
 *
 */
public class JfiPay99billReceiveController extends PayBaseController implements Controller {

    private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private Bill99Util bill99Util = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private SysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private FoundationOrderManager foundationOrderManager = null;
    private JpmMemberConfigManager jpmMemberConfigManager = null;
	
	public void setJpmMemberConfigManager(JpmMemberConfigManager jpmMemberConfigManager) {
		this.jpmMemberConfigManager = jpmMemberConfigManager;
	}
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

	public void setBill99Util(Bill99Util bill99Util) {
		this.bill99Util = bill99Util;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
	    log.info("JfiPay99billReceiveController is come in ");
        if (log.isDebugEnabled()) {
        	log.info("[PC请求地址]："+request.getRequestURL().toString() + "?" + request.getQueryString());
            log.debug("entering 'handleRequest' method...");
        }
        try{
        	//判断是否采用电子存折
            String isAllPay = request.getParameter("isAllPay");//全部用电子存折支付
            log.info("JfiPay99billReceiveController isAllPay is : " + isAllPay);
            if(!StringUtils.isEmpty(isAllPay)){
            	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
            	SysUser loginSysUser = SessionLogin.getLoginUser(request);
            	String orderId = request.getParameter("orderId");
            	boolean isCheck = this.checkFlagOne(orderId, operatorSysUser);
            	log.info("JfiPay99billReceiveController isCheck is : " + isCheck);
            	if(isCheck){
            	    log.info("JfiPay99billReceiveController isCheck is ture ,is come in ");
            		//支付完成，自动审核订单
//            		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
//            		jpoMemberOrder.setIsPay("1");
            		
//            		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
            		
            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
            	}else{
            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poOrder.editedFail"));
            	}
            	return new ModelAndView("redirect:jfiPay99bill.html?strAction=jfiPay99bill&orderId=" + orderId + "&flag=1");
            }
            
            //获得快钱返回信息
           /* String[] ext2 = request.getParameter("ext2").split(",");
            String userCode = ext2[0];
            String flag = ext2[1];
            BigDecimal fee = new BigDecimal(ext2[2]);*/
            
            
            String remark = request.getParameter("ext2");
            RemarkBean bean = PayUtils.getRemarkBean(remark);
            
            
            SysUser sysUser = sysUserManager.getSysUser(bean.getUserCode());

            //获得快钱返回信息
            Jfi99billLog jfi99billLog = bill99Util.getJfi99billLog(request, sysUser.getUserCode(), "CN");
            
            //返回信息 支付成功
            if("10".equals(jfi99billLog.getReturnMsg())){
                log.info("JfiPay99billReceiveController ReturnMsg is 10 ");
            	//快钱数据进存折
            	jfiBankbookJournalManager.saveJfiPayDataVerifyByBill99("CN", sysUser, new BigDecimal(jfi99billLog.getPayAmount()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfi99billLog.getDealId(),jfi99billLog,bean.getPayFee());
            	BigDecimal[] moneyArray = {bean.getPayFee()};
            	Integer[] moneyType = {30};
            	String[] notes = {"手续费"};
            	log.info("JfiPay99billReceiveController deduct bankbook");
            	//从用户帐户扣取金额
            	jfiBankbookJournalManager.saveJfiBankbookDeduct("CN", sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), "0", notes,"1");
            	log.info("JfiPay99billReceiveController flag is :" + bean.getPayType());
            	if("1".equals(bean.getPayType())){//订单审核
            		String orderId = request.getParameter("orderId");

            		//支付完成，自动审核订单
            		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
            		log.info("jpoMemberOrder:...." + jpoMemberOrder);

            		
            		//=======================================是否全额支付
            		Integer isFullPay = 0;
		    		if(bean.getIsFull()){
		    			isFullPay = getFullpay(jfi99billLog, jpoMemberOrder);
		    		}
					jpoMemberOrder.setIsFullPay(isFullPay);
					//=======================================是否全额支付
					// 支付时间
					jpoMemberOrder.setSubmitTime(ServerDateUtil.getNowTimeFromDateServer());
					jpoMemberOrder.setSubmitUserCode(jpoMemberOrder.getSysUser().getUserCode());
					jpoMemberOrder.setPayCode(jfi99billLog.getMerchantAcctId());
            		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
            		
                	boolean isCheck = this.checkFlagOne(orderId, sysUser);   //
                	
                	JpoMemberOrder jpoMemberOrder1 = jpoMemberOrderManager.getJpoMemberOrder(orderId);
                	log.info("Jfi99billReceive ischeck:" + isCheck +"  jpoMemberOrder1.getIsPay: " + jpoMemberOrder1.getIsPay());
                	
                	if(isCheck){
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
                	}else{
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"poOrder.editedFail"));
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
            			//支付失败
            			saveMessage(request, LocaleUtil.getLocalText("opration.pay.fail")+app.getMessage());
            		}
            		
            		//支付成功
            		saveMessage(request, LocaleUtil.getLocalText("opration.pay.success"));
            	}
            	//酒业配置订单审核
            	if("3".equals(bean.getPayType())){
            		
            		String orderId = request.getParameter("orderId");
        			//JpmMemberConfig jpmMemberConfig = jpmMemberConfigManager.getJpmMemberConfig(orderId);
        			try {
        				// 电子存折支付审单
        				jpmMemberConfigManager.checkJpmMemberConfig(orderId, sysUser);
        			} catch (AppException app) {

        				// 支付失败

        			}
            	}
            	
            	//进账累计
            	//fiBillAccountWarningManager.addTotalAmount(jfi99billLog.getPayAmount(), jfi99billLog.getMerchantAcctId());
            	            	
            	return new ModelAndView("fi/jfiPay99billReceive").addObject("showUrl", Bill99Constants.showUrl);
            }else{
                log.info("JfiPay99billReceiveController failed ReturnMsg is : " + jfi99billLog.getReturnMsg());
            	//不成功
            	switch(Integer.parseInt(jfi99billLog.getReturnMsg())){
            		case 0://数据被篡改
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:0");
            			break;
            		case 1://扣款失败
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:1");
            			break;
            		case 2://自定义MD5签名被篡改(快钱签名被破解)
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:2");
            			break;
            		case 3://数据重新发送
            			return new ModelAndView("fi/jfiPay99billReceive").addObject("showUrl", Bill99Constants.showUrl);
            	}
            }
            
            return new ModelAndView("fi/jfiPay99billReceive").addObject("returnMsg",jfi99billLog.getReturnMsg());
        }catch(Exception e){
        	MessageUtil.saveMessage(request, RequestUtil.paramStr2(request));
        	log.info("[PC请求地址]错误信息为："+request.getRequestURL().toString() + "?" + request.getQueryString());
			System.err.println("=========失败第三方请求支付地址===============" +  RequestUtil.paramStr2(request));
			return new ModelAndView("fi/jfiPay99billReceive").addObject("returnMsg", "1");
        }
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
			jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
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
	
	public Integer getFullpay(Jfi99billLog entity, JpoMemberOrder jpoMemberOrder) {
		JfiPayLog payLog = new JfiPayLog();
		payLog.setMerchantId(entity.getMerchantAcctId());
		payLog.setOrderAmount(new BigDecimal(entity.getOrderAmount()).divide(new BigDecimal(100))+"");
		payLog.setUserCode(entity.getUserCode());
		return super.getFullpay(payLog, jpoMemberOrder);
	}
}