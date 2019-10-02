package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.model.JfiReapalLog;
import com.joymain.jecs.fi.service.FiBillAccountWarningManager;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.pm.service.JpmMemberConfigManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.reapal.ReapalUtil;
import com.joymain.jecs.util.yeepay.YeePayUtil;
import com.joymain.jecs.webapp.action.BaseController;

/**
 * 接收融宝支付付款通知
 * @author Alvin
 *
 */
public class JfiReapalPayReceiveController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(JfiReapalPayReceiveController.class);
    private ReapalUtil reapalUtil = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private SysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private FoundationOrderManager foundationOrderManager = null;
    private JpmMemberConfigManager jpmMemberConfigManager = null;
    private FiBillAccountWarningManager fiBillAccountWarningManager = null;

    public void setFiBillAccountWarningManager(
			FiBillAccountWarningManager fiBillAccountWarningManager) {
		this.fiBillAccountWarningManager = fiBillAccountWarningManager;
	}
	public void setJpmMemberConfigManager(JpmMemberConfigManager jpmMemberConfigManager) {
		this.jpmMemberConfigManager = jpmMemberConfigManager;
	}
    public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
        this.foundationOrderManager = foundationOrderManager;
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

	
	public void setReapalUtil(ReapalUtil reapalUtil) {
		this.reapalUtil = reapalUtil;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("=======================融宝支付 comming in ");
		try{
    		
	    	//验签、保存通知数据
			JfiReapalLog jfiReapalLog = reapalUtil.getJfiReapalLog(request);
			log.info("=======================jfiReapalLog:"+jfiReapalLog.getReturnMsg());
	    	//验签成功
	    	if(("10").equals(jfiReapalLog.getReturnMsg())){
	    		
	    		String[] ext2 = request.getParameter("body").split(",");
	            String userCode = ext2[0];//获取付款会员身份
	            String flag = ext2[1];//标识：0充值, 1订单支付, 2公益基金订单支付
	            
	    		SysUser sysUser = sysUserManager.getSysUser(userCode);	    		 

	    		//付款数据进融宝支付存折
	    		jfiBankbookJournalManager.saveJfiPayDataVerifyByReapal("CN", sysUser, new BigDecimal(jfiReapalLog.getAmount()), sysUser.getUserCode(), sysUser.getUserName(),jfiReapalLog.getDetailId(),jfiReapalLog);
	        	
	    		if("1".equals(flag)){//订单审核
		    		
	    			//订单编号
		    		String orderId = jfiReapalLog.getOrderId();
		    		
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
	    		
            	//支付成功返回信息给融宝
            	response.getWriter().write("success");
	    	}
	
	    	if(("3").equals(jfiReapalLog.getReturnMsg())){//数据重发
	    		
	    		response.getWriter().write("success");
	    	}
	
	    	if(("4").equals(jfiReapalLog.getReturnMsg())){//验签失败
	    		
	    		response.getWriter().write("fail");
	    	}
	    	return null;
    	}catch(Exception e){
    		
    		log.info("============融宝支付入账异常=================="+e.getMessage());
    		
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
//			jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
		}catch(AppException app){
			//记录日志 Modify By WuCF 20140304 
			log.info("审核订单抛出异常-----checkFlagOne===:orderId"+orderId+"---err:"+app);
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}