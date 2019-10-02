package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.alipay.Md5Encrypt;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.hitrust.HiTrustUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.uspay.UsCreditCard;
import com.joymain.jecs.util.uspay.UsCreditCardUtil;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.fi.service.JfiAlipayLogManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiUsCreditCardLogManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 美国信用卡支付请求
 * @author Alvin
 *
 */
public class JfiUsPayRequestController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiUsPayRequestController.class);
    private JpoMemberOrderManager jpoMemberOrderManager;
    private UsCreditCardUtil usCreditCardUtil;
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    private JfiUsCreditCardLogManager jfiUsCreditCardLogManager;

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }

        String requestUrl = request.getParameter("url");
        if("post".equalsIgnoreCase(request.getMethod())){
	        String moId = request.getParameter("moId");
	        if(StringUtil.isEmpty(moId)){
	        	//MOID为空
	        	request.setAttribute("error", "1");
	        	saveMessage(request, LocaleUtil.getLocalText("en_US","notice.payment.failed"));
	        	request.setAttribute("url", requestUrl);
	        	return new ModelAndView("fi/jfiUsPayRequest");
	        }
	        
	        JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(moId);
	        if(jpoMemberOrder==null || "1".equals(jpoMemberOrder.getIsPay()) || !"US".equals(jpoMemberOrder.getCompanyCode())){
	        	//订单末找到
	        	request.setAttribute("error", "2");
	        	saveMessage(request, LocaleUtil.getLocalText("en_US","notice.payment.failed"));
	        	request.setAttribute("url", requestUrl);
	        	return new ModelAndView("fi/jfiUsPayRequest");
	        }
	
			String key = "joylifeus1115";
	        String requestTime = request.getParameter("time");
	        String requestKey = request.getParameter("key");
	        String strKey = Md5Encrypt.md5(jpoMemberOrder.getMoId() + key + requestTime + requestUrl);
	        long time = new Date().getTime();
	        if(Long.parseLong(requestTime) + 10 * 60 * 1000 < time){
	        	//超时
	        	request.setAttribute("error", "3");
	        	return new ModelAndView("jfiUsPayRequest");
	        }
	        if(!strKey.equals(requestKey)){
	        	//KEY出错
	        	request.setAttribute("error", "4");
	        	return new ModelAndView("jfiUsPayRequest");
	        }
	        
	        String amount = jpoMemberOrder.getAmount().toString();
	        SysUser sysUser = jpoMemberOrder.getSysUser();
	        
	        JfiUsCreditCardLog jfiUsCreditCardLog = usCreditCardUtil.payAndLogCreditCard(request,amount, jpoMemberOrder.getMoId().toString());
	        jfiUsCreditCardLog.setUserCode(sysUser.getUserCode());
	        jfiUsCreditCardLog.setCreateTime(new Date());
	        jfiUsCreditCardLogManager.saveJfiUsCreditCardLog(jfiUsCreditCardLog);
	        if("1".equals(jfiUsCreditCardLog.getResponse())){
	        	jfiBankbookJournalManager.saveJfiPayDataVerifyByUsCreditCard("US",sysUser , new BigDecimal(amount), sysUser.getUserCode(), sysUser.getUserName(),jfiUsCreditCardLog.getTransactionId(),jfiUsCreditCardLog);
	        	jpoMemberOrder.setIsPay("1");
	    		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
	    		boolean isCheck = this.checkFlagOne(moId, sysUser);
	        	if(isCheck){
	        		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
	        	}else{
	        		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"poOrder.editedFail"));
	        	}
	        	request.setAttribute("url", requestUrl);
	        }else{
	        	//标志有误
	        	saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"notice.payment.failed"));
	        	request.setAttribute("url", requestUrl);
	        }
	        return new ModelAndView("fi/jfiUsPayRequest");
        }
        return null;
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
			if("checkError.Code2".equals(app.getMessage())){//已存在已审首购单
				
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setUsCreditCardUtil(UsCreditCardUtil usCreditCardUtil) {
		this.usCreditCardUtil = usCreditCardUtil;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public void setJfiUsCreditCardLogManager(
			JfiUsCreditCardLogManager jfiUsCreditCardLogManager) {
		this.jfiUsCreditCardLogManager = jfiUsCreditCardLogManager;
	}
}
