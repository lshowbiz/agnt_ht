package com.joymain.jecs.fi.webapp.action;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.JfiChinapayPosLog;
import com.joymain.jecs.fi.service.Jfi99billPosLogManager;
import com.joymain.jecs.fi.service.Jfi99billPosSendLogManager;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
/**
 * 银联POS接收页面
 * @author
 *
 */
public class JfiChinaPayPosReceiveController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JfiChinaPayPosReceiveController.class);
    private JpoMemberOrderManager jpoMemberOrderManager = null;

    public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }

    private SysUserManager sysUserManager = null;

    public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering China Pos Pay method...");
        }
        
       
        

        
        //1验签
        if(("10").equals(checkChinaPosPayLog(request))){
        	
        	//2进存折
        	
        }
        
        return null;
	}
	
	public String checkChinaPosPayLog(HttpServletRequest request){
		
		String payType = request.getParameter("payType");//支付类型
		String payDealCode = request.getParameter("payDealCode");//参考号
		String payAmount = request.getParameter("payAmount");//交易金额
		String fee = request.getParameter("fee");//手续费
		String userCode = request.getParameter("ext1");//会员编号
		String mobileNumber = request.getParameter("mobileNumber");//手机
		String payResult = request.getParameter("payResult");//支付结果
		
		JfiChinapayPosLog jfiChinapayPosLog = new JfiChinapayPosLog();
		
		return "10";
	}
}
