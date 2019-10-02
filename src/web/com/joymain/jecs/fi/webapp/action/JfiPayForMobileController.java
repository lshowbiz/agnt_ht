package com.joymain.jecs.fi.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;

/**
 * 手机支付
 * @author Alvin
 *
 */
public class JfiPayForMobileController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private SysUserManager sysUserManager=null;
    
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
                
        //支付前准备
        String istopay = request.getParameter("istopay");
        if(!StringUtils.isEmpty(istopay)){
        	
        	JSONObject json = new JSONObject();
        	
        	String userCode = request.getParameter("usercode");
        	
        	//查询用户电子存折余额
            JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(userCode);
            if(jfiBankbookBalance!=null && !StringUtils.isEmpty(jfiBankbookBalance.getBalance().toString())){
            	json.put("result", jfiBankbookBalance.getBalance().toString());
            }else{
            	json.put("result", "0");
            }
            
    		this.outJsonString(response, "([" + json.toString() + "])");
        }
        
        //电子存折支付
        String isPay = request.getParameter("ispay");
        if(!StringUtils.isEmpty(isPay)){
        	
        	JSONObject json = new JSONObject();
        	
        	String userCode = request.getParameter("usercode");
        	String orderId = request.getParameter("orderid");
        	
        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);
        	
        	//订单为空，订单状态已审核，不是当前用户
        	if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !userCode.equals(jpoMemberOrder.getSysUser().getUserCode())){
        	
        		json.put("result", "error");
        		this.outJsonString(response, "([" + json.toString() + "])");
        		return null;
        	}
        	
        	SysUser operatorSysUser=sysUserManager.getSysUser(userCode);
        	//支付审单
        	String result = this.checkFlagOne(orderId, operatorSysUser);
        	if("1".equals(result)){
        		
        		//支付完成，自动审核订单
        		jpoMemberOrder.setIsPay("1");
        		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
        		
        		json.put("result", "success");
        		this.outJsonString(response, "([" + json.toString() + "])");
        	}else{
        		
        		json.put("result", result);
        		this.outJsonString(response, "([" + json.toString() + "])");
        	}
        }
        
        return null;
    }
		
	public void outJsonString(HttpServletResponse response, String json) {
		response.setContentType("application/json;charset=utf-8");
		outString(response, json);
	}
	
	public void outString(HttpServletResponse response, String json) {
		try {
			PrintWriter out = response.getWriter();
			out.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 审核订单
	 * @param orderId
	 * @param operatorSysUser
	 */
	private String checkFlagOne(String orderId, SysUser operatorSysUser){
		
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(orderId);

		try{
			
			
			jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser,"2");
			jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
		}catch(AppException app){

			if("checkError.Code2".equals(app.getMessage())){//已存在已审首购单
				return app.getMessage();
			}
			return app.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "1";
	}
}
