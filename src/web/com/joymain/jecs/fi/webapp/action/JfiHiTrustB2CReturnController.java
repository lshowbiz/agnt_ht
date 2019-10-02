package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.hitrust.b2ctoolkit.b2cpay.B2CPayOther;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.hitrust.HiTrustUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.model.JfiHiTrustLog;
import com.joymain.jecs.fi.service.JfiAlipayLogManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiHiTrustLogManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 台湾信用卡支付请求
 * @author Alvin
 *
 */
public class JfiHiTrustB2CReturnController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiHiTrustB2CReturnController.class);
    private HiTrustUtil hiTrustUtil = null;
    private JfiHiTrustLogManager jfiHiTrustLogManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private SysUserManager sysUserManager = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String url = "http://ec.joylifeglobal.com/tw/jpoMemberOrderStatistics.html";
        request.setAttribute("url", url);
        
    	if (!((String)request.getParameter("retcode")).equals("00")){
    		System.out.print("交易失敗!!");
    		System.out.print("<br>");
    		System.out.print("訂單編號 : ["+request.getParameter("ordernumber")+"]");
    		System.out.print("<br>");
    		System.out.print("回傳代碼 : ["+request.getParameter("retcode")+"]");
    	}else{
    		B2CPayOther trx = new B2CPayOther();
    		trx.setStoreId("50615");
    		trx.setType(trx.QUERY);
    		trx.setOrderNo((String)request.getParameter("ordernumber"));
    		trx.transaction();
    		  
    		JfiHiTrustLog jfiHiTrustLog = new JfiHiTrustLog();
    		jfiHiTrustLog.setOrderNo(trx.getOrderNo());
    		jfiHiTrustLog.setOrderDesc(trx.getOrderDesc());
    		jfiHiTrustLog.setRetCode(trx.getRetCode());
    		jfiHiTrustLog.setCurrency(trx.getCurrency());
    		jfiHiTrustLog.setOrderDate(trx.getOrderDate());
    		jfiHiTrustLog.setOrderStatus(trx.getOrderStatus());
    		jfiHiTrustLog.setApproveAmount(trx.getApproveAmount());
    		jfiHiTrustLog.setAuthCode(trx.getAuthCode());
    		jfiHiTrustLog.setAuthRrn(trx.getAuthRRN());
    		jfiHiTrustLog.setCaptureAmount(trx.getCaptureAmount());
    		jfiHiTrustLog.setPayBatchNum(trx.getPayBatchNum());
    		jfiHiTrustLog.setCaptureDate(trx.getCaptureDate());
    		jfiHiTrustLog.setRefundAmount(trx.getRefundAmount());
    		jfiHiTrustLog.setRefundBatch(trx.getRefundBatch());
    		jfiHiTrustLog.setRefundRrn(trx.getRefundRRN());
    		jfiHiTrustLog.setRefundCode(trx.getRefundCode());
    		jfiHiTrustLog.setRefundDate(trx.getRefundDate());
    		jfiHiTrustLog.setAcquirer(trx.getAcquirer());
    		jfiHiTrustLog.setCardType(trx.getCardType());
    		jfiHiTrustLog.setEci(trx.getEci());
    		jfiHiTrustLog.setInc("0");
    		jfiHiTrustLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());
    		jfiHiTrustLog.setReferer(request.getHeader("referer"));
    		jfiHiTrustLog.setCreateTime(new Date());
    		jfiHiTrustLog.setPageType("1");
    		jfiHiTrustLog.setReturnMsg("N");
    		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
    		
    		String orderNo = jfiHiTrustLog.getOrderNo().split(",")[0];
    		String userCode = jfiHiTrustLog.getOrderNo().split(",")[1];
    		
    		SysUser sysUser = sysUserManager.getSysUser(userCode);
    		if(sysUser==null){
    			jfiHiTrustLog.setReturnMsg("M1");//会员不存在
        		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
    		}else{
    			if("00".equals(jfiHiTrustLog.getRetCode())){
            		JfiHiTrustLog jfiHiTrustLogTmp = new JfiHiTrustLog();
            		jfiHiTrustLogTmp.setOrderNo(jfiHiTrustLog.getOrderNo());
            		jfiHiTrustLogTmp.setInc("1");
        			List jfiHiTrustLogs = jfiHiTrustLogManager.getJfiHiTrustLogs(jfiHiTrustLogTmp);
        			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderNo);
        			if(jpoMemberOrder!=null ){
        				if(!"1".equals(jpoMemberOrder.getIsPay())){
                			jpoMemberOrder.setIsPay("1");
                			jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
        				}
        			}else{
            			jfiHiTrustLog.setReturnMsg("O1");//订单不存在
                		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
        			}
        			if("03".equals(jfiHiTrustLog.getOrderStatus())){
            			if(jfiHiTrustLogs.size()==0){
            				try{
            					//TODO:2011-08-30sysUser与订单的userSpCode不同,存款需改成userSpCode
            					jfiBankbookJournalManager.saveJfiPayDataVerifyByHiTrust("TW", sysUser, new BigDecimal(jfiHiTrustLog.getCaptureAmount()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfiHiTrustLog.getOrderNo(),jfiHiTrustLog);
                    			boolean isCheck = this.checkFlagOne(orderNo, sysUser);
                	            if(isCheck){
                	            	saveMessage(request, LocaleUtil.getLocalText("zh_TW","opration.notice.js.orderNo.auditSuccess"));
                	                return new ModelAndView("/fi/jfiHiTrustB2CReturn");
                	            }else{
                	            	saveMessage(request, LocaleUtil.getLocalText("zh_TW","poOrder.editedFail"));
                	                return new ModelAndView("/fi/jfiHiTrustB2CReturn");
                	            }
            				}catch(AppException e){
            					if("重复操作".equals(e.getMessage())){
                        			jfiHiTrustLog.setReturnMsg("R1");//重复发送
                            		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
                            		saveMessage(request, LocaleUtil.getLocalText("zh_TW","notice.payment.success"));
                            		return new ModelAndView("/fi/jfiHiTrustB2CReturn");
            					}
            				}
            			}else{
                			jfiHiTrustLog.setReturnMsg("R1");//重复发送
                    		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
        	            	saveMessage(request, LocaleUtil.getLocalText("zh_TW","opration.notice.js.orderNo.auditSuccess"));
        	                return new ModelAndView("/fi/jfiHiTrustB2CReturn");
            			}
        			}else{
            			jfiHiTrustLog.setReturnMsg("T1");//请款标志有误
                		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
        			}
    			}else{
        			jfiHiTrustLog.setReturnMsg("N0");//标志有误
            		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
    			}
    		}
    		MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99bill.payFail") + " Code:" + jfiHiTrustLog.getReturnMsg());
            return new ModelAndView("/fi/jfiHiTrustB2CReturn");
    	}
    	return null;
    }
	
	/**
	 * 审核订单
	 * @param orderId
	 * @param operatorSysUser
	 */
	private boolean checkFlagOne(String orderNo, SysUser operatorSysUser){
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderNo);
		try{
			jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser,"1");
			//jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
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

	public void setHiTrustUtil(HiTrustUtil hiTrustUtil) {
		this.hiTrustUtil = hiTrustUtil;
	}

	public void setJfiHiTrustLogManager(JfiHiTrustLogManager jfiHiTrustLogManager) {
		this.jfiHiTrustLogManager = jfiHiTrustLogManager;
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
}
