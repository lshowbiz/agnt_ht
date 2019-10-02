package com.joymain.jecs.pr.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
/**
 * 选择订单
 * @author Alvin
 *
 */
public class JprRefundBackingController implements Controller {

	private JpoMemberOrderManager jpoMemberOrderManager;

	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!"addPrRefund".equals(request.getParameter("strAction"))) {
			request.setAttribute("prRefundBackingListTable_totalRows", 0);
			return new ModelAndView("pr/prRefundBacking");
		}
        
    	//================AiAgent LOGIN IMFORMATION
    	SysUser defSysUser = SessionLogin.getLoginUser(request);
    	//=========================================

		SysUser sessionLogin = SessionLogin.getLoginUser(request);

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		crm.addField("status", "2");
		//crm.addField("isRetreatOrder", "2");
		if(!"AA".equals(defSysUser.getCompanyCode())){
			crm.addField("companyCode", defSysUser.getCompanyCode());
		}

		Pager pager = new Pager("prRefundBackingListTable", request, Constants.PAGE_SIZE);
		String memberOrderNo = request.getParameter("memberOrderNo");
		String sysUser = request.getParameter("sysUser.userCode");
        if (StringUtil.isEmpty(memberOrderNo)&&StringUtil.isEmpty(sysUser)){
			request.setAttribute("prRefundBackingListTable_totalRows", pager.getTotalObjects());
			return new ModelAndView("pr/jprRefundBacking", "prRefundBackingList", null);
        }else{
    		List poMemberOrderList = jpoMemberOrderManager.getJpoMemberOrdersByCrmForRefund(crm, pager);
    		request.setAttribute("prRefundBackingListTable_totalRows", pager.getTotalObjects());
    		return new ModelAndView("pr/jprRefundBacking", "prRefundBackingList", poMemberOrderList);
        }

	}

}
