package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.JfiTenpayLog;
import com.joymain.jecs.fi.service.JfiTenpayLogManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.tenpay.TenpayConstants;
import com.joymain.jecs.util.tenpay.TenpayUtil;
import com.joymain.jecs.util.bill99.Bill99Constants;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 财付通接收页面
 * @author Alvin
 *
 */
public class JfiPayTenpayReceiveController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(JfiTenpayLogController.class);
    private TenpayUtil tenpayUtil = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private SysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
	private JfiTenpayLogManager jfiTenpayLogManager = null;

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
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        try{
            String isAllPay = request.getParameter("isAllPay");
            if(!StringUtils.isEmpty(isAllPay)){
            	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
            	SysUser loginSysUser = SessionLogin.getLoginUser(request);
            	String orderId = request.getParameter("orderId");
            	boolean isCheck = this.checkFlagOne(orderId, operatorSysUser);
            	if(isCheck){
            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
            	}else{
            		saveMessage(request, LocaleUtil.getLocalText(loginSysUser.getDefCharacterCoding(),"poOrder.editedFail"));
            	}
            	return new ModelAndView("redirect:jfiPayTenpay.html?strAction=jfiPayTenpay&orderId=" + orderId + "&flag=1");
            }
            
            String userCode = request.getParameter("attach");
            String transactionId = request.getParameter("transaction_id");
            String flag = transactionId.substring(transactionId.length()-10, transactionId.length()-9);
            SysUser sysUser = sysUserManager.getSysUser(userCode);
            
            JfiTenpayLog jfiTenpayLog = tenpayUtil.getJfiTenpayLog(request, sysUser.getUserCode(), "CN");
            
            if(jfiTenpayLog.getReturnMsg().equals("10")){
    			jfiBankbookJournalManager.saveJfiPayDataVerifyByTenpay("CN", sysUser, new BigDecimal(jfiTenpayLog.getTotalFee()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfiTenpayLog.getTransactionId(),jfiTenpayLog);
    			if("1".equals(flag)){//订单审核
            		String orderId = jfiTenpayLog.getSpBillno();
            		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
            		jpoMemberOrder.setIsPay("1");
            		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
                	boolean isCheck = this.checkFlagOne(orderId, sysUser);
                	if(isCheck){
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
                	}else{
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"poOrder.editedFail"));
                	}
    			}
    			return new ModelAndView("fi/jfiPayTenpayReceive").addObject("show_url",TenpayConstants.show_url+"?returnMsg=success");
			}else{
            	//不成功
            	switch(Integer.parseInt(jfiTenpayLog.getReturnMsg())){
            		case 1://交易不存在
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPayTenpay.payFail") + " Code:1");
                    	break;
            		case 2://验签失败
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiTenpay.payFail") + " Code:2");
                    	break;
            		case 3://交易状态
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiTenpay.payFail") + " Code:3");
            			break;
            		case 4://交易重复
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiTenpay.success"));
                        return new ModelAndView("fi/jfiPayTenpayReceive").addObject("show_url",TenpayConstants.show_url+"?returnMsg=success");
            	}
			}
            return new ModelAndView("fi/jfiPayTenpayReceive").addObject("show_url",TenpayConstants.show_url+"?returnMsg=fail");
        }catch(Exception e){
        	e.printStackTrace();
        	return new ModelAndView("redirect:403.jsp");
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

	public void setTenpayUtil(TenpayUtil tenpayUtil) {
		this.tenpayUtil = tenpayUtil;
	}

	public void setJfiTenpayLogManager(JfiTenpayLogManager jfiTenpayLogManager) {
		this.jfiTenpayLogManager = jfiTenpayLogManager;
	}
}
