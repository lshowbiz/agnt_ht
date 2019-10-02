package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FiChannelLog;
import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.FiChannelLogManager;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.bill99.Bill99Constants;
import com.joymain.jecs.util.bill99.Bill99Util;
import com.joymain.jecs.util.channel.ChannelUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.ConfigUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * 盛付通支付通知接收页面
 * 
 * @author Alvin
 * 
 */
public class JfiPayChannelReceiveController extends BaseController implements Controller
{
    
    private final Log log = LogFactory.getLog(JfiPayChannelReceiveController.class);
    
    private ChannelUtil channelUtil = null;
    
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    
    private SysUserManager sysUserManager = null;
    
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    
    private FoundationOrderManager foundationOrderManager = null;
    
    public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager)
    {
        this.foundationOrderManager = foundationOrderManager;
    }
    
    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager)
    {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }
    
    public void setSysUserManager(SysUserManager sysUserManager)
    {
        this.sysUserManager = sysUserManager;
    }
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (log.isDebugEnabled())
        {
            log.debug("entering 'handleRequest' method...");
        }
        try
        {

            String[] ext1 = request.getParameter("Ext1").split(",");
            String userCode = ext1[0];
            String flag = ext1[1];
          
            SysUser sysUser = sysUserManager.getSysUser(userCode);
            
            FiChannelLog fiChannelLog = channelUtil.getFiChannelLog(request, userCode);
                        
            if ("10".equals(fiChannelLog.getReturnMsg()))
            {
            	
                //进存折
            	jfiBankbookJournalManager.saveFiPayDataVerifyByChannel("CN",sysUser,new BigDecimal(fiChannelLog.getPayAmount()).divide(new BigDecimal(100)),sysUser.getUserCode(),sysUser.getUserName(),fiChannelLog.getDealId(),fiChannelLog);

                // 订单审核
                if ("1".equals(flag))
                {
                	
                	String orderId = request.getParameter("orderId");

            		//支付完成，自动审核订单
            		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
            		jpoMemberOrder.setIsPay("1");
            		jpoMemberOrder.setPayCode(fiChannelLog.getMerchantAcctId());
            		
//            		//判断是否是全额支付
//            		BigDecimal payAmount = new BigDecimal(fiChannelLog.getPayAmount());
//            		if(jpoMemberOrder.getAmount().compareTo(payAmount) == 0)
//            			jpoMemberOrder.setIsFullPay(1);
//            		else
//            			jpoMemberOrder.setIsFullPay(0);
            		
            		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
            		
                	boolean isCheck = this.checkFlagOne(orderId, sysUser);
                	if(isCheck){
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"opration.notice.js.orderNo.auditSuccess"));
                	}else{
                		//Modify By WuCF 失败后恢复订单支付状态为0
                		JpoMemberOrder jpoMemberOrder2 = jpoMemberOrderManager.getJpoMemberOrder(orderId);
                		jpoMemberOrder2.setIsPay("0");
                		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder2);
                		log.info("审核订单失败！修改支付标示为0！ 订单号："+jpoMemberOrder2.getMemberOrderNo());
                		saveMessage(request, LocaleUtil.getLocalText(sysUser.getDefCharacterCoding(),"poOrder.editedFail"));
                	}
                }
                
                // 慈善基金订单审核
                if ("2".equals(flag))
                {
                    
                	String orderId = request.getParameter("orderId");
            		FoundationOrder foundationOrder = foundationOrderManager.getFoundationOrder(orderId);
            		try{
            			//支付完成，自动审核订单
            			foundationOrderManager.checkFoundationOrder(foundationOrder,sysUser);
            		}catch(AppException app){
            		}
                }
                return new ModelAndView("fi/jfiPayChannelReceive").addObject("returnMsg","OK");
            }
            else
            {
                // 不成功
                switch (Integer.parseInt(fiChannelLog.getReturnMsg()))
                {
                    case 0:// 数据被篡改
                        MessageUtil.saveMessage(request,
                            LocaleUtil.getLocalText("fiPay99billms.payFail") + " Code:0");
                        break;
                    case 1:// 扣款失败
                        MessageUtil.saveMessage(request,
                            LocaleUtil.getLocalText("fiPay99billms.payFail") + " Code:1");
                        break;
                    case 2:// 自定义MD5签名被篡改
                        MessageUtil.saveMessage(request,
                            LocaleUtil.getLocalText("fiPay99billms.payFail") + " Code:2");
                        break;
                    case 3:// 数据重新发送
                    	return new ModelAndView("fi/jfiPayChannelReceive").addObject("returnMsg","OK");
                }
            }
            
            return new ModelAndView("fi/jfiPayChannelReceive").addObject("returnMsg",fiChannelLog.getReturnMsg());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("check order error,orderId is:[" + request.getParameter("orderId") + "] .", e);
            return new ModelAndView("redirect:403.jsp");
        }
    }
    
    /**
     * 审核订单
     * 
     * @param orderId
     * @param operatorSysUser
     */
    private boolean checkFlagOne(String orderId, SysUser operatorSysUser)
    {

        try
        {
            JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
            jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser,"1");
        }
        catch (AppException app)
        {
            log.error("check order error, orderId is:[" + orderId + "]", app);
            return false;
        }
        catch (Exception e)
        {
            log.error("check order error, orderId is:[" + orderId + "]", e);
            return false;
        }
        
        return true;
    }
    
    
    public void setChannelUtil(ChannelUtil channelUtil) {
		this.channelUtil = channelUtil;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}


}
