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

import com.joymain.jecs.fi.model.JfiAlipayLog;
import com.joymain.jecs.fi.service.JfiAlipayLogManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.alipay.AlipayUtil;
import com.joymain.jecs.util.bill99.Bill99Constants;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 支付宝接收页面
 * @author Alvin
 *
 */
public class JfiPayAlipayReceiveController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(JfiAlipayLogController.class);
    private AlipayUtil alipayUtil = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private SysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
	private JfiAlipayLogManager jfiAlipayLogManager = null;

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
        boolean isNotify = false;
        if(request.getRequestURL().toString().contains("jfiPayAlipayReceiveNotify.html")){
        	isNotify = true;
        }
        request.setAttribute("isNotify", isNotify);
        
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
            	return new ModelAndView("redirect:jfiPayAlipay.html?strAction=jfiPayAlipay&orderId=" + orderId + "&flag=1");
            }
            
            String[] extra_common_param = request.getParameter("extra_common_param").split(",");
            String userCode = extra_common_param[0];
            String flag = extra_common_param[1];
            BigDecimal fee = new BigDecimal(extra_common_param[2]);
            
            SysUser sysUser = sysUserManager.getSysUser(userCode);
            
            JfiAlipayLog jfiAlipayLog = alipayUtil.getJfiAlipayLog(request, sysUser.getUserCode(), "CN");
            
            if(jfiAlipayLog.getReturnMsg().equals("9")){
            	if(isNotify==true){
                	return new ModelAndView("fi/jfiPayAlipayReceive").addObject("returnMsg","success");
    			}
            }
            
            if(jfiAlipayLog.getReturnMsg().equals("10")){
    			jfiBankbookJournalManager.saveJfiPayDataVerifyByAlipay("CN", sysUser, new BigDecimal(jfiAlipayLog.getTotalFee()), sysUser.getUserCode(), sysUser.getUserName(),jfiAlipayLog.getTradeNo(),jfiAlipayLog);
    			if("1".equals(flag)){//订单审核
            		String orderId = request.getParameter("orderId");
            		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
            		jpoMemberOrder.setIsPay("1");
            		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
                	boolean isCheck = this.checkFlagOne(orderId, sysUser);
                	if(isCheck && isNotify==false){
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
                	}else{
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"poOrder.editedFail"));
                	}
    			}
    			if(isNotify==true){
                	return new ModelAndView("fi/jfiPayAlipayReceive").addObject("returnMsg","success");
    			}else{
    				return new ModelAndView("fi/jfiPayAlipayReceive");
    			}
			}else{
            	//不成功
            	switch(Integer.parseInt(jfiAlipayLog.getReturnMsg())){
            		case 1://交易不存在
            			if(isNotify==false){
            				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPayAlipay.payFail") + " Code:1");
            			}
                    	break;
            		case 2://验签失败
            			if(isNotify==false){
            				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiAlipay.payFail") + " Code:2");
            			}
                    	break;
            		case 3://交易状态
            			if(isNotify==false){
            				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiAlipay.payFail") + " Code:3");
            			}
            			break;
            		case 4://交易重复
            			if(isNotify==false){
            				MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiAlipay.success"));
            			}
                        return new ModelAndView("fi/jfiPayAlipayReceive").addObject("returnMsg","success");
            	}
			}
            return new ModelAndView("fi/jfiPayAlipayReceive").addObject("returnMsg","fail");
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

	public void setAlipayUtil(AlipayUtil alipayUtil) {
		this.alipayUtil = alipayUtil;
	}

	public void setJfiAlipayLogManager(JfiAlipayLogManager jfiAlipayLogManager) {
		this.jfiAlipayLogManager = jfiAlipayLogManager;
	}
}
