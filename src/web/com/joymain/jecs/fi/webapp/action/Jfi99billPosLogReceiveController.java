package com.joymain.jecs.fi.webapp.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.util.bill99.VerifySignature;
import com.joymain.jecs.fi.model.Jfi99billPosLog;
import com.joymain.jecs.fi.model.Jfi99billPosSendLog;
import com.joymain.jecs.fi.service.Jfi99billPosLogManager;
import com.joymain.jecs.fi.service.Jfi99billPosSendLogManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
/**
 * 快钱POS接收页面(前后台)
 * @author Alvin
 *
 */
public class Jfi99billPosLogReceiveController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(Jfi99billPosLogReceiveController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }
    private Jfi99billPosLogManager jfi99billPosLogManager = null;

    public void setJfi99billPosLogManager(Jfi99billPosLogManager jfi99billPosLogManager) {
        this.jfi99billPosLogManager = jfi99billPosLogManager;
    }
    
    private SysIdManager sysIdManager = null;
	
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
    private SysUserManager sysUserManager = null;

    public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	
    private Jfi99billPosSendLogManager jfi99billPosSendLogManager = null;

    public void setJfi99billPosSendLogManager(
			Jfi99billPosSendLogManager jfi99billPosSendLogManager) {
		this.jfi99billPosSendLogManager = jfi99billPosSendLogManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String[] url = request.getRequestURL().toString().split("/");
        String isBackService = "0";
        if(url[url.length-1].toLowerCase().contains("back")){
        	isBackService = "1";
        }
        Jfi99billPosLog jfi99billPosLog = this.fillInJfi99billPosLog(request,isBackService);
        if(jfi99billPosLog==null){
        	this.saveMessageIsBack(request, "支付失败,系统找不到相应记录",isBackService);
        	return new ModelAndView("redirect:403.jsp");
        }
        StringBuffer paramValue=new StringBuffer();
        paramValue.append(jfi99billPosLog.getProcessFlag());
        paramValue.append(jfi99billPosLog.getTxnType());
        paramValue.append(jfi99billPosLog.getOrgTxnType());
        paramValue.append(jfi99billPosLog.getAmt());
        paramValue.append(jfi99billPosLog.getExternalTraceNo());
        paramValue.append(jfi99billPosLog.getOrgExternalTraceNo());
        paramValue.append(jfi99billPosLog.getTerminalOperId());
        paramValue.append(jfi99billPosLog.getAuthCode());
        paramValue.append(jfi99billPosLog.getRrn());
        paramValue.append(jfi99billPosLog.getTxnTime());
        paramValue.append(jfi99billPosLog.getShortPan());
        paramValue.append(jfi99billPosLog.getResponseCode());
        paramValue.append(jfi99billPosLog.getCardType());
        paramValue.append(jfi99billPosLog.getIssuerId());
        
        VerifySignature verifySignature = VerifySignature.initVerifySignature();//初始化验签类
        verifySignature.setVpos_cp_cer("/vpos_cp4JAVA.cer");  //设置快钱公钥地址
        verifySignature.setData_sign(paramValue.toString());  //设置返回参数串
        verifySignature.setSignature(jfi99billPosLog.getSignature()); //设置返回的加密串
        boolean bill99Test = verifySignature.verifySign("gb2312");//得到验签结果
        if(bill99Test){
        	jfi99billPosLog.setReturnMsg("1");
        	jfi99billPosLogManager.saveJfi99billPosLog(jfi99billPosLog);
        	if("0".equals(jfi99billPosLog.getProcessFlag())){
        		SysUser sysUser = sysUserManager.getSysUser(jfi99billPosLog.getUserCode());
        		if(sysUser==null || !"M".equals(sysUser.getUserType())){
        			this.saveMessageIsBack(request, "支付失败,会员不存在",isBackService);
        		}else{
        			try{
        				jfi99billPosLogManager.saveJfi99billPosLogAndBankAccount(jfi99billPosLog,sysUser);
        				this.saveMessageIsBack(request, "支付成功",isBackService);
                		if("1".equals(jfi99billPosLog.getActionType())){
                			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(jfi99billPosLog.getActionNo());
                        	jpoMemberOrder.setIsPay("1");
                        	jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
                            boolean isCheck = this.checkFlagOne(jfi99billPosLog.getActionNo(), sysUser);
                            if(isCheck){
                            	this.saveMessageIsBack(request, "订单审核成功",isBackService);
                            }else{
                            	this.saveMessageIsBack(request, "订单审核失败,系统稍后处理",isBackService);
                            }
                		}
        			}catch(AppException e){
        				if("重复操作".equals(e.getMessage())){
        					this.saveMessageIsBack(request, "支付成功",isBackService);
        				}else{
        					request.setAttribute("exception", e);
        					this.saveMessageIsBack(request, "支付失败,AppException异常"+e.getMessage(),isBackService);
        				}
        			}
        		}
        		
        	}else{
            	this.saveMessageIsBack(request, "支付失败,快钱异常" + jfi99billPosLog.getResponseMessage(),isBackService);
        	}
        	
        }else{
        	jfi99billPosLog.setReturnMsg("0");
        	jfi99billPosLogManager.saveJfi99billPosLog(jfi99billPosLog);
        	this.saveMessageIsBack(request, "支付失败,验签失败",isBackService);
        }
        if("1".equals(jfi99billPosLog.getIsBackService())){
        	return new ModelAndView("fi/jfiPay99billPosReceive").addObject("isBackService", "1");
        }else{
        	return new ModelAndView("fi/jfiPay99billPosReceive").addObject("isBackService", "0");
        }
    }
    
    /**
     * 封装对像
     * @param request
     * @param isBackService
     * @return
     */
    private Jfi99billPosLog fillInJfi99billPosLog(HttpServletRequest request, String isBackService){
        String processFlag = request.getParameter("processFlag");
        String txnType = request.getParameter("txnType");
        String orgTxnType = request.getParameter("orgTxnType");
        String amt = request.getParameter("amt");
        String externalTraceNo = request.getParameter("externalTraceNo");
        String orgExternalTraceNo = request.getParameter("orgExternalTraceNo");
        String terminalOperId = request.getParameter("terminalOperId");
        String authCode = request.getParameter("authCode");
        String rrn = request.getParameter("RRN");
        String txnTime = request.getParameter("txnTime");
        String shortPan = request.getParameter("shortPAN");
        String responseCode = request.getParameter("responseCode");
        String responseMessage = request.getParameter("responseMessage");
        String cardType = request.getParameter("cardType");
        String issuerId = request.getParameter("issuerId");
        String issuerIdView = request.getParameter("issuerIdView");
        String signature = request.getParameter("signature");
        String url = request.getRequestURL().toString() + "?" + request.getQueryString();
        String referer = request.getHeader("referer");
        
        Jfi99billPosSendLog jfi99billPosSendLog = new Jfi99billPosSendLog();
        jfi99billPosSendLog.setHashMd5Code(externalTraceNo);
        List jfi99billPosSendLogs = jfi99billPosSendLogManager.getJfi99billPosSendLogs(jfi99billPosSendLog);
        if(jfi99billPosSendLogs.size()==0){
        	return null;
        }
        jfi99billPosSendLog = (Jfi99billPosSendLog)jfi99billPosSendLogs.get(0);
        jfi99billPosSendLog.setReturnTime(jfi99billPosSendLog.getReturnTime() + 1);
        jfi99billPosSendLogManager.saveJfi99billPosSendLog(jfi99billPosSendLog);
        String actionNo = jfi99billPosSendLog.getActionNo();
        String userCode = jfi99billPosSendLog.getUserCode();
        String actionType = jfi99billPosSendLog.getActionType();
        String actionId = jfi99billPosSendLog.getLogId().toString();
        
    	Jfi99billPosLog jfi99billPosLog = new Jfi99billPosLog();
    	jfi99billPosLog.setProcessFlag(processFlag);
    	jfi99billPosLog.setTxnType(txnType);
    	jfi99billPosLog.setOrgTxnType(orgTxnType);
    	jfi99billPosLog.setAmt(amt);
    	jfi99billPosLog.setExternalTraceNo(externalTraceNo);
    	jfi99billPosLog.setOrgExternalTraceNo(orgExternalTraceNo);
    	jfi99billPosLog.setTerminalOperId(terminalOperId);
    	jfi99billPosLog.setAuthCode(authCode);
    	jfi99billPosLog.setRrn(rrn);
    	jfi99billPosLog.setTxnTime(txnTime);
    	jfi99billPosLog.setShortPan(shortPan);
    	jfi99billPosLog.setResponseCode(responseCode);
    	jfi99billPosLog.setResponseMessage(responseMessage);
    	jfi99billPosLog.setCardType(cardType);
    	jfi99billPosLog.setIssuerId(issuerId);
    	jfi99billPosLog.setIssuerIdView(issuerIdView);
    	jfi99billPosLog.setSignature(signature);
    	jfi99billPosLog.setUrl(url);
    	jfi99billPosLog.setInc("0");
    	jfi99billPosLog.setCompanyCode("CN");
    	jfi99billPosLog.setReferer(referer);
    	jfi99billPosLog.setUserCode(userCode);
    	jfi99billPosLog.setActionType(actionType);
    	jfi99billPosLog.setActionNo(actionNo);
    	jfi99billPosLog.setActionId(actionId);
    	jfi99billPosLog.setIsBackService(isBackService);
    	jfi99billPosLog.setCreateTime(new Date());
    	jfi99billPosLogManager.saveJfi99billPosLog(jfi99billPosLog);
    	return jfi99billPosLog;
    }
    
    /**
     * 判断是否为前台打印消息
     * @param request
     * @param msg
     * @param isBackService
     */
    private void saveMessageIsBack(HttpServletRequest request, String msg, String isBackService){
    	if("0".equals(isBackService)){
    		saveMessage(request, msg);
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
			if("checkError.Code2".equals(app.getMessage())){//已存在已审首购单
				
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
