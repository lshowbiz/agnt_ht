package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.model.Jfi99billLog;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.bill99.Bill99Constants;
import com.joymain.jecs.util.bill99.Bill99Util;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.MessageUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 慈善基金支付快钱接收处理
 * @author shiyh
 *
 */
public class JfiPayFoundationReceiveController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private Bill99Util bill99Util = null;
    private JfiBankbookJournalManager jfiBankbookJournalManager = null;
    private SysUserManager sysUserManager = null;
    private FoundationOrderManager foundationOrderManager = null;

    public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
        this.foundationOrderManager = foundationOrderManager;
    }
    
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

	public void setBill99Util(Bill99Util bill99Util) {
		this.bill99Util = bill99Util;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {

        try{
        	//1、全部采用电子存折支付
            String isAllPay = request.getParameter("isAllPay");//isAllPay代表全部用电子存折支付
            //判断是否全部采用电子存折支付
            if(!StringUtils.isEmpty(isAllPay)){
            	
            	//电子存折支付审单
            	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
            	
            	String orderId = request.getParameter("orderId");
            	FoundationOrder foundationOrder = foundationOrderManager.getFoundationOrder(orderId);
        		try{
        				        
        			foundationOrderManager.checkFoundationOrder(foundationOrder,operatorSysUser);
        		}catch(AppException app){
        			
        			//支付失败
        			saveMessage(request, LocaleUtil.getLocalText("opration.pay.fail")+app.getMessage());
        		}

        		//支付成功
        		saveMessage(request, LocaleUtil.getLocalText("opration.pay.success"));
            	return new ModelAndView("redirect:/foundationOrders.html?strAction=selectFoundationOrderList2");
            }
            
            return new ModelAndView("redirect:/foundationOrders.html?strAction=selectFoundationOrderList2");
        }catch(Exception e){
        	
        	e.printStackTrace();
        	return new ModelAndView("redirect:403.jsp");
        }
    }
}
