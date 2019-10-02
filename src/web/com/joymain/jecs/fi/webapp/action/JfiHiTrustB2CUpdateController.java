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

import com.hitrust.b2ctoolkit.b2cpay.B2CPayUpdate;
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

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 台湾信用卡支付请求
 * @author Alvin
 *
 */
public class JfiHiTrustB2CUpdateController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiHiTrustB2CUpdateController.class);
    private HiTrustUtil hiTrustUtil = null;
    private JfiHiTrustLogManager jfiHiTrustLogManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private SysUserManager sysUserManager = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;

    public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
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

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        String url = request.getRequestURL().toString() + "?";
        Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化（现在已经使用）
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "GBK");
			url += name+ "=" +valueStr;
		}

		B2CPayUpdate payUpdate = new B2CPayUpdate();
		payUpdate.setStoreId("50615");
		payUpdate.setKey((String)request.getParameter("KEY"));
		payUpdate.setMac((String)request.getParameter("MAC"));
		payUpdate.setCipher((String)request.getParameter("CIPHER"));
		payUpdate.transaction();
		
		JfiHiTrustLog jfiHiTrustLog = new JfiHiTrustLog();
		jfiHiTrustLog.setTtype(payUpdate.getType());
		jfiHiTrustLog.setOrderNo(payUpdate.getOrderNo());
		jfiHiTrustLog.setRetCode(payUpdate.getRetCode());
		jfiHiTrustLog.setCurrency(payUpdate.getCurrency());
		jfiHiTrustLog.setOrderDate(payUpdate.getOrderDate());
		jfiHiTrustLog.setOrderStatus(payUpdate.getOrderStatus());
		jfiHiTrustLog.setApproveAmount(payUpdate.getApproveAmount());
		jfiHiTrustLog.setAuthCode(payUpdate.getAuthCode());
		jfiHiTrustLog.setAuthRrn(payUpdate.getAuthRRN());
		jfiHiTrustLog.setCaptureAmount(payUpdate.getCaptureAmount());
		jfiHiTrustLog.setCaptureDate(payUpdate.getCaptureDate());
		jfiHiTrustLog.setAcquirer(payUpdate.getAcquirer());
		jfiHiTrustLog.setCardType(payUpdate.getCardType());
		jfiHiTrustLog.setEci(payUpdate.getEci());
		jfiHiTrustLog.setInc("0");
		jfiHiTrustLog.setUrl(url);
		jfiHiTrustLog.setReferer(request.getHeader("referer"));
		jfiHiTrustLog.setCreateTime(new Date());
		jfiHiTrustLog.setPageType("2");
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
	        			jfiBankbookJournalManager.saveJfiPayDataVerifyByHiTrust("TW", sysUser, new BigDecimal(jfiHiTrustLog.getCaptureAmount()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfiHiTrustLog.getOrderNo(),jfiHiTrustLog);
	        			boolean isCheck = this.checkFlagOne(orderNo, sysUser);
	    	            if(isCheck){
	    	            	//saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
	    	            }else{
	    	            	//saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"poOrder.editedFail"));
	    	            }
	    	    		request.setAttribute("returnMsg", "R01=00");
	    	            return new ModelAndView("fi/jfiHiTrustB2CUpdate");
	    			}else{
	        			jfiHiTrustLog.setReturnMsg("R1");//重复发送
	            		jfiHiTrustLogManager.saveJfiHiTrustLog(jfiHiTrustLog);
	    	    		request.setAttribute("returnMsg", "R01=00");
	    	            return new ModelAndView("fi/jfiHiTrustB2CUpdate");
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
		request.setAttribute("returnMsg", "R01=" + jfiHiTrustLog.getReturnMsg());
        return new ModelAndView("fi/jfiHiTrustB2CUpdate");
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
}
