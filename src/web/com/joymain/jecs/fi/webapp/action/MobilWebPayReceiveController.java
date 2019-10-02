package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.ServerDateUtil;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.mobil.MobilWebUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

/**
 * 手机网页快钱支付收款处理控制器
 * 
 * @author syh
 * 
 */
public class MobilWebPayReceiveController extends BaseController implements Controller {

	private final Log log = LogFactory.getLog(Jfi99billLogController.class);
	private MobilWebUtil mobilWebUtil=null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private SysUserManager sysUserManager = null;
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    
	public void setMobilWebUtil(MobilWebUtil mobilWebUtil) {
		this.mobilWebUtil = mobilWebUtil;
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

	public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response)
	throws Exception {
				
		try{
			log.info("[手机请求地址]信息为："+request.getRequestURL().toString() + "?" + request.getQueryString());
			//1.获取快钱返回信息，验签，保存快钱支付记录
			Jfi99billLog jfi99billLog=mobilWebUtil.getJfi99billLog(request);
			
			//返回验签结果， 支付成功
	        if("10".equals(jfi99billLog.getReturnMsg())){
	
				SysUser sysUser = sysUserManager.getSysUser(jfi99billLog.getUserCode());//查询会员对象
				
				//2.快钱数据进存折
				jfiBankbookJournalManager.saveJfiPayDataVerifyByMobil("CN", sysUser, new BigDecimal(jfi99billLog.getPayAmount()).divide(new BigDecimal(100)), sysUser.getUserCode(), sysUser.getUserName(),jfi99billLog.getDealId(),jfi99billLog,BigDecimal.ZERO);
	        	
				 //获取订单编号、会员编号、flag
		        //String externalTraceNo[] = request.getParameter("ext2").split(",");
		      //  String flag = externalTraceNo[1];//标识,1:订单支付,0：充值
		        
		        String reserved = request.getParameter("ext2");
		        String jstr = reserved.substring(reserved.indexOf("[") + 1, reserved.indexOf("]"));
				JSONObject json = JSONObject.fromObject(jstr);;
		        String flag = json.getString("payType");//externalTraceNo[1];//标识,1:订单支付,0：充值
		        
		        if("1".equals(flag)){//订单审核
		        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(jfi99billLog.getOrderId());
		        	
		        	//支付时间
					jpoMemberOrder.setSubmitTime(ServerDateUtil.getNowTimeFromDateServer());
					jpoMemberOrder.setSubmitUserCode(jpoMemberOrder.getSysUser().getUserCode());
					jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
		        	
		        	//调用审单
                	boolean isCheck = this.checkFlagOne(jfi99billLog.getOrderId(), sysUser);

                	//审单没问题
                	if(isCheck){
                		
                		//更改订单isPay状态
                		jpoMemberOrder.setIsPay("1");
                		jpoMemberOrder.setPayCode(jfi99billLog.getMerchantAcctId());
                		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
                	}
		        }
		       	
		       	//反馈成功结果信息给快钱读取
		       	return new ModelAndView("fi/mobilPayShow").addObject("rtnOK","1");
	        }
	        
	        //数据重发
	        if("3".equals(jfi99billLog.getReturnMsg())){
	        	return new ModelAndView("fi/mobilPayShow").addObject("rtnOK","1");
	        }
	        
			//反馈验签失败的结果信息给快钱读取
			return new ModelAndView("fi/mobilPayShow").addObject("rtnOK",jfi99billLog.getReturnMsg());
		}catch(Exception e){
			log.info("[调测日志]错误信息为："+e.getMessage());
			log.info("[调测日志]错误地址："+RequestUtil.paramStr2(request));
        	e.printStackTrace();
        	return new ModelAndView("redirect:403.jsp");
        }
	}
	
	/**
	 * 审单
	 * @param orderId
	 * @param sysUser
	 */
	private boolean checkFlagOne(String orderId, SysUser sysUser){
		
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);//查询订单对象

       	//订单不为空，订单状态为待审
       	if(jpoMemberOrder!=null && "1".equals(jpoMemberOrder.getStatus())){
       		
       		try{
	       		//审单
	       		jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, sysUser,"2");	       		
       		}catch(AppException app){
       			return false;
       		}catch(Exception e){
       			return false;
    		}
       		return true;
       	}
       	return false;
	}
}
