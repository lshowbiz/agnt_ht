package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.model.JfiAmountDetail;
import com.joymain.jecs.fi.model.JfiQuota;
import com.joymain.jecs.fi.service.JfiAmountDetailManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiQuotaManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.ServerDateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.mpos.MobilUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;

/**
 * 手机快钱支付收款处理控制器
 * 
 * @author syh
 * 
 */
public class MobilPayReceiveController extends BaseController implements Controller {

	private final Log log = LogFactory.getLog(Jfi99billLogController.class);
	private MobilUtil mobilUtil=null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private SysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiAmountDetailManager jfiAmountDetailManager;
	private JfiQuotaManager jfiQuotaManager;

    
	public void setJfiAmountDetailManager(JfiAmountDetailManager jfiAmountDetailManager) {
		this.jfiAmountDetailManager = jfiAmountDetailManager;
	}
	public void setJfiQuotaManager(JfiQuotaManager jfiQuotaManager) {
		this.jfiQuotaManager = jfiQuotaManager;
	}
    
	public void setMobilUtil(MobilUtil mobilUtil) {
		this.mobilUtil = mobilUtil;
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

	public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response)
	throws Exception {
				
		try{

			//1.获取快钱返回信息，验签，保存快钱支付记录
			Jfi99billLog jfi99billLog=mobilUtil.getJfi99billLog(request);
			//返回验签结果， 支付成功
	        if("10".equals(jfi99billLog.getReturnMsg())){
				SysUser sysUser = sysUserManager.getSysUser(jfi99billLog.getUserCode());//查询会员对象
				//2.快钱数据进存折
				jfiBankbookJournalManager.saveJfiPayDataVerifyByMobil("CN", sysUser, new BigDecimal(jfi99billLog.getPayAmount()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfi99billLog.getDealId(),jfi99billLog,BigDecimal.ZERO);
				 //获取订单编号、会员编号、flag
		        String externalTraceNo[] = request.getParameter("externalTraceNo").split(",");
		        String flag = externalTraceNo[2];//标识,1:订单支付,0：充值
		        
		        if("1".equals(flag)){//订单审核
		        	
		        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(jfi99billLog.getOrderId());
            		log.info("jpoMemberOrder:...." + jpoMemberOrder);
            		//Integer IsFullPay = getFullpay(jfi99billLog, jpoMemberOrder, 0);
				//	jpoMemberOrder.setIsFullPay(IsFullPay);
					// 支付时间
					jpoMemberOrder.setSubmitTime(ServerDateUtil.getNowTimeFromDateServer());
					jpoMemberOrder.setSubmitUserCode(jpoMemberOrder.getSysUser().getUserCode());
					jpoMemberOrder.setPayCode(jfi99billLog.getMerchantAcctId());
		        	//调用审单
                	boolean isCheck = this.checkFlagOne(jfi99billLog.getOrderId(), sysUser);

                	//审单没问题
                	if(isCheck){
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
                		/*
                		//更改订单isPay状态
                		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(jfi99billLog.getOrderId());
                		jpoMemberOrder.setIsPay("1");
                		jpoMemberOrder.setPayCode(jfi99billLog.getMerchantAcctId());
                		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);*/
                	}else{
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"poOrder.editedFail"));
                	}
		        }
		       	
		       	//反馈成功结果信息给快钱读取
		       	return new ModelAndView("fi/mobilPayReceive").addObject("returnMsg","0");
	        }
	        
	        //手机支付失败
	        if("20".equals(jfi99billLog.getReturnMsg())){
	        	return new ModelAndView("fi/mobilPayReceive").addObject("returnMsg","0");
	        }
	        
			//反馈验签失败的结果信息给快钱读取
			return new ModelAndView("fi/mobilPayReceive").addObject("returnMsg",jfi99billLog.getReturnMsg());
		}catch(Exception e){
			log.info("[调测日志]错误地址："+RequestUtil.paramStr2(request));
			log.info("[调测日志]错误信息为："+e.getMessage());
        	e.printStackTrace();
        	return new ModelAndView("redirect:403.jsp");
        }
	}
	
	/**
	 * 审单
	 * @param orderId
	 * @param sysUser
	 */
	private boolean checkFlagOne(String orderId, SysUser sysUser){
		
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);//查询订单对象

       	//订单不为空，订单状态为待审
       	if(jpoMemberOrder!=null && "1".equals(jpoMemberOrder.getStatus())){
       		
       		try{
	       		//审单
	       		jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, sysUser,"2");	       		
       		}catch(AppException app){
       			return false;
       		}catch(Exception e){
       			return false;
    		}
       		return true;
       	}
       	return false;
	}
	
	public Integer getFullpay(Jfi99billLog entity, JpoMemberOrder jpoMemberOrder) {
		Integer isFullPay = 0;
		if (jpoMemberOrder.getAmount2().compareTo(new BigDecimal(entity.getOrderAmount())) == 0) {
			JfiQuota quota = new JfiQuota();
			quota.setValidityPeriod(new SimpleDateFormat("yyyyMM").format(new Date()));
			quota = jfiQuotaManager.getJfiQuota(quota, entity.getMerchantAcctId());
			if (quota.getFiBillAccount() != null) {
				JfiAmountDetail amDetail = new JfiAmountDetail();
				amDetail.setCreateTime(new Date());
				amDetail.setUserCode(entity.getUserCode());
				amDetail.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
				amDetail.setMoney(entity.getOrderAmount());
				amDetail.setQuotaId(quota.getQuotaId());
				jfiAmountDetailManager.saveJfiAmountDetail(amDetail);// 增加明细
				isFullPay = 1;
			}
		}
		return isFullPay;
	}
}
