package com.joymain.jecs.pr.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.service.JprRefundManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 * 退货单列表
 * @author Alvin
 *
 */
public class JprRefundController extends BaseController implements Controller {
    private final Log log = LogFactory.getLog(JprRefundController.class);
    private JprRefundManager jprRefundManager = null;

    public void setJprRefundManager(JprRefundManager jprRefundManager) {
        this.jprRefundManager = jprRefundManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'handleRequest' method...");
        }
        
        String strAction = request.getParameter("strAction");
        request.setAttribute("strAction", strAction);
        
    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//=========================================

        RequestUtil.freshSession(request,"prRefunds",10l);
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("strAction", strAction);
		crm.addField("intoStatus", "1");
		crm.addField("refundStatus", "1");
		crm.addField("returnType", "0");
		crm.addField("jpoMemberOrder.memberOrderNo.isNull", "not null");
		if(!"AA".equals(defSysUser.getCompanyCode())){
			crm.addField("companyCode", defSysUser.getCompanyCode());
		}
		
		Pager pager = new Pager("prRefundListTable", request, Constants.PAGE_SIZE);
		String roNo = request.getParameter("roNo");
		String memberOrderNo = request.getParameter("jpoMemberOrder.memberOrderNo");
		String userCode = request.getParameter("sysUser.userCode");
		String createBTime = request.getParameter("createBTime");
		String createETime = request.getParameter("createETime");
		String refundTye = request.getParameter("refundTye");
        if (StringUtil.isEmpty(userCode)&&StringUtil.isEmpty(roNo)&&StringUtil.isEmpty(memberOrderNo)&&(StringUtil.isEmpty(createBTime)&&StringUtil.isEmpty(createETime)&&StringUtil.isEmpty(refundTye))){
			request.setAttribute("prRefundListTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("pr/jprRefundList", Constants.JPRREFUND_LIST, null);
        }else{
        	/*if("C".equals(defSysUser.getUserType())){
        		if(RequestUtil.saveOperationSession(request,"prRefunds",10l)!=0){
        			return new ModelAndView("redirect:jprRefunds.html");
        		}
        	}*/
    		List prRefunds = jprRefundManager.getJprRefundsByCrm(crm, pager);
    		request.setAttribute("prRefundListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("pr/jprRefundList", Constants.JPRREFUND_LIST, prRefunds);
        }
    }
}
