package com.joymain.jecs.fi.webapp.action;

import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;

import com.joymain.jecs.Constants;
import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.webapp.action.BaseController;
import com.joymain.jecs.webapp.util.SessionLogin;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FoundationOrderController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(FoundationOrderController.class);
	private FoundationOrderManager foundationOrderManager = null;

	public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
		this.foundationOrderManager = foundationOrderManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		FoundationOrder foundationOrder = new FoundationOrder();
		// populate object with request parameters
		BeanUtils.populate(foundationOrder, request.getParameterMap());

		//List foundationOrders = foundationOrderManager.getFoundationOrders(foundationOrder);
		/**** auto pagination ***/
		String strAction = request.getParameter("strAction");
		String url = "";
		
		CommonRecord crmItem = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("foundationOrderListTable",request, 20);
		
		List foundationOrderItems = foundationOrderManager.foundationItemsByCrm(crmItem,pager); 
        request.setAttribute("foundationOrderItems", foundationOrderItems);  
		
        CommonRecord crm=RequestUtil.toCommonRecord(request);
		
		
		if("selectFoundationOrderList".equals(strAction)){//会员捐赠管理
			url = "fi/foundationOrderList";
		}else if("selectFoundationOrderList2".equals(strAction)){//查询会员自己的捐赠记录
			url = "fi/foundationOrderList2";
			//设置当前用户USER_CODE，其他条件设置为空值。 
			SysUser loginSysUser = SessionLogin.getLoginUser(request);
			crm.setValue("userCode",loginSysUser.getUserCode());//
			crm.setValue("payType","");
			crm.setValue("status","");
			crm.setValue("fiId","");
			crm.setValue("createTimeStart","");
			crm.setValue("createTimeEnd","");
		}
 		 
		List foundationOrders = foundationOrderManager.getFoundationOrdersByCrm(crm,pager);
		request.setAttribute("foundationOrderListTable_totalRows", pager.getTotalObjects());
		/*****/

		return new ModelAndView(url, Constants.FOUNDATIONORDER_LIST, foundationOrders);
	}
}
