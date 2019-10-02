package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.model.JfiChanjetLog;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.chanjet.ChanjetUtil;
import com.joymain.jecs.util.chanjet.Md5;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;

/**
 * 畅捷通付款通知处理控制器
 * @author syh
 *
 */

public class JfiPayChanjetReceiveController extends BaseController implements Controller {
	
	private final Log log = LogFactory.getLog(Jfi99billLogController.class);
	
    private JpoMemberOrderManager jpoMemberOrderManager = null;
	
    private ChanjetUtil chanjetUtil = null;
	
	private JfiBankbookJournalManager jfiBankbookJournalManager = null;
	
	private SysUserManager sysUserManager = null;
	
	private FoundationOrderManager foundationOrderManager = null;
	
	public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
        this.foundationOrderManager = foundationOrderManager;
    }

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public void setChanjetUtil(ChanjetUtil chanjetUtil) {
		this.chanjetUtil = chanjetUtil;
	}

	public void setJfiBankbookJournalManager(JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)throws Exception {
    	
    	request.setCharacterEncoding("utf-8");
    	
    	try{
    		log.info("============畅捷支付入账开始==================");
    		System.out.println("============畅捷支付入账开始==================");
	    	//验签、保存通知数据
	    	JfiChanjetLog jfiChanjetLog = chanjetUtil.getJfiChanjetLog(request);
	    	
	    	//验签成功
	    	if(("10").equals(jfiChanjetLog.getReturnMsg())){
	    		
	    		String[] ext2 = request.getParameter("expand").split(",");
	            String userCode = ext2[0];//获取付款会员身份
	            String flag = ext2[1];//标识：0充值, 1订单支付, 2公益基金订单支付
	            
	    		SysUser sysUser = sysUserManager.getSysUser(userCode);	    		 

	    		//付款数据进存折
	    		jfiBankbookJournalManager.saveJfiPayDataVerifyByChanjet("CN", sysUser, new BigDecimal(jfiChanjetLog.getAmount()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfiChanjetLog.getDetailId(),jfiChanjetLog);
	        	
	    		if("1".equals(flag)){//订单审核
		    		
	    			//订单编号
		    		String orderId = request.getParameter("orderId");
		    		
		    		//审单、扣款
		    		this.checkFlagOne(orderId, sysUser);
		    		
		    		//检查订单审核状况,决定是否回改ispay
		    		JpoMemberOrder jpoMemberOrderCheck = jpoMemberOrderManager.getJpoMemberOrder(orderId);
		        	if(("1").equals(jpoMemberOrderCheck.getStatus())){    		
		        		
		        		jpoMemberOrderCheck.setIsPay("0");
		        		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrderCheck);
		        		log.info("畅捷支付审核订单失败！修改支付标示为0！ 订单号："+jpoMemberOrderCheck.getMemberOrderNo());
		        	}
	    		}
	    		//慈善基金订单审核
            	if("2".equals(flag)){
            		
            		String orderId = request.getParameter("orderId");
            		FoundationOrder foundationOrder = foundationOrderManager.getFoundationOrder(orderId);
            		try{
            			//支付完成，自动审核订单
            			foundationOrderManager.checkFoundationOrder(foundationOrder,sysUser);
            		}catch(AppException app){
            		}
            	}
	    		
            	//支付成功返回信息给畅捷通
	    		return new ModelAndView("fi/jfiPayChanjetReceive").addObject("retmsg", "OK");
	    	}
	
	    	if(("3").equals(jfiChanjetLog.getReturnMsg())){//数据重发
	    		
	    		return new ModelAndView("fi/jfiPayChanjetReceive").addObject("retmsg", "OK");
	    	}
	
	    	if(("4").equals(jfiChanjetLog.getReturnMsg())){//验签失败
	    		
	    		return new ModelAndView("fi/jfiPayChanjetReceive").addObject("retmsg", "600002");
	    	}
	
	    	return new ModelAndView("fi/jfiPayChanjetReceive").addObject("retmsg", "600000");
    	}catch(Exception e){
    		
    		log.info("============畅捷支付入账异常=================="+e.getMessage());
    		System.out.println("============畅捷支付入账异常=================="+e.getMessage());
        	e.printStackTrace();
        	return new ModelAndView("redirect:403.jsp");
        }
    }
    
    /**
     * 审单
     * @param orderId
     * @param operatorSysUser
     */
    private void checkFlagOne(String orderId, SysUser operatorSysUser){
    	
    	try{
    		
    		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);   
    		
    		//修改ispay
//			jpoMemberOrder.setIsPay("1");
//    		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder); 
    		
			//审单
			jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser,"1");			   			
		}catch(AppException app){
			
			log.info("畅捷支付审核订单抛出异常,订单ID："+orderId+"---err:"+app);
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
}
