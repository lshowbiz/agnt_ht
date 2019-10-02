package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.Jfi99billmsLog;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.bill99ms.Bill99msConstants;
import com.joymain.jecs.util.bill99ms.Bill99msUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 快钱分润接收页面
 * @author Alvin
 *
 */
public class JfiPay99billmsReceiveController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(Jfi99billmsLogController.class);
    private Bill99msUtil bill99msUtil = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private SysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;

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
            	return new ModelAndView("redirect:jfiPay99bill.html?strAction=jfiPay99bill&orderId=" + orderId + "&flag=1");
            }
            
            String[] ext2 = request.getParameter("ext2").split(",");
            String userCode = ext2[0];
            String flag = ext2[1];
            BigDecimal fee = new BigDecimal(ext2[2]);
            
            SysUser sysUser = sysUserManager.getSysUser(userCode);
            
/*            Enumeration pNames=request.getParameterNames();
            while(pNames.hasMoreElements()){
                String name=(String)pNames.nextElement();
                String value=request.getParameter(name);
                System.out.print(name + "=" + value);
            }
            System.out.println();*/
            
            Jfi99billmsLog jfi99billmsLog = bill99msUtil.getJfi99billmsLog(request, sysUser.getUserCode(), "CN");
            if("10".equals(jfi99billmsLog.getReturnMsg())){
            	jfiBankbookJournalManager.saveJfiPayDataVerifyByBill99ms("CN", sysUser, new BigDecimal(jfi99billmsLog.getPayAmount()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfi99billmsLog.getDealId(),jfi99billmsLog,fee);
            	BigDecimal[] moneyArray = {fee};
            	Integer[] moneyType = {30};
            	String[] notes = {"手续费"};
            	jfiBankbookJournalManager.saveJfiBankbookDeduct("CN", sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), "0", notes,"1");
            	if("1".equals(flag)){//订单审核
            		String orderId = request.getParameter("orderId");

            		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
            		jpoMemberOrder.setIsPay("1");
            		//jpoMemberOrder.setPayCode(jfi99billmsLog.getMerchantAcctId());
            		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
            		
                	boolean isCheck = this.checkFlagOne(orderId, sysUser);
                	if(isCheck){
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
                	}else{
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"poOrder.editedFail"));
                	}
            	}
            	return new ModelAndView("fi/jfiPay99billmsReceive").addObject("showUrl", Bill99msConstants.showUrl);
            }else{
            	//不成功
            	switch(Integer.parseInt(jfi99billmsLog.getReturnMsg())){
            		case 0://数据被篡改
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99billms.payFail") + " Code:0");
            			break;
            		case 1://扣款失败
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99billms.payFail") + " Code:1");
            			break;
            		case 2://自定义MD5签名被篡改(快钱签名被破解)
            			MessageUtil.saveMessage(request, LocaleUtil.getLocalText("fiPay99billms.payFail") + " Code:2");
            			break;
            		case 3://数据重新发送
            			return new ModelAndView("fi/jfiPay99billmsReceive").addObject("showUrl", Bill99msConstants.showUrl);
            	}
            }
            
            return new ModelAndView("fi/jfiPay99billmsReceive").addObject("returnMsg",jfi99billmsLog.getReturnMsg());
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

	public void setBill99msUtil(Bill99msUtil bill99msUtil) {
		this.bill99msUtil = bill99msUtil;
	}
}
