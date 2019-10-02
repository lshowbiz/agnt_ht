package com.joymain.jecs.fi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.fi.model.FoundationItem;
import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.FoundationItemManager;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bill99.Bill99;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 基金慈善支付表单
 * @author shiyh
 *
 */
public class JfiPayFoundationController extends BaseController implements Controller {

    private final Log log = LogFactory.getLog(Jfi99billLogController.class);
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    private FoundationOrderManager foundationOrderManager = null;
    private FoundationItemManager foundationItemManager = null;

    public void setFoundationItemManager(FoundationItemManager foundationItemManager) {
        this.foundationItemManager = foundationItemManager;
    }
    public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
        this.foundationOrderManager = foundationOrderManager;
    }
	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        SysUser loginSysUser = SessionLogin.getLoginUser(request);
        
        //基金订单
        String orderId = request.getParameter("orderId");
        FoundationOrder foundationOrder = foundationOrderManager.getFoundationOrder(orderId);
        request.setAttribute("foundationOrder", foundationOrder);
    	
    	//会员电子存折余额
    	JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(loginSysUser.getUserCode());
    	request.setAttribute("bankbook", jfiBankbookBalance.getBalance());

        //快钱支付
        Bill99 jfi99bill = new Bill99();
        jfi99bill.setPayerName(loginSysUser.getUserName());
        jfi99bill.setOrderId(orderId);
        
        return new ModelAndView("fi/jfiPayFoundation", "jfi99bill", jfi99bill);
    }
}
