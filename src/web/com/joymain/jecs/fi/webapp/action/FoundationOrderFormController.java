package com.joymain.jecs.fi.webapp.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.fi.model.FoundationItem;
import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.service.FoundationItemManager;
import com.joymain.jecs.fi.service.FoundationOrderManager;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
@SuppressWarnings("rawtypes")
public class FoundationOrderFormController extends BaseFormController {
	private FoundationOrderManager foundationOrderManager = null;
	private FoundationItemManager foundationItemManager = null;

	public void setFoundationOrderManager(FoundationOrderManager foundationOrderManager) {
		this.foundationOrderManager = foundationOrderManager;
	}

	public void setFoundationItemManager(FoundationItemManager foundationItemManager) {
		this.foundationItemManager = foundationItemManager;
	}

	public FoundationOrderFormController() {
		setCommandName("foundationOrder");
		setCommandClass(FoundationOrder.class); 
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		String userCode = SessionLogin.getLoginUser(request).getUserCode();
		
		String fiId = request.getParameter("fiId"); 
		String fType=request.getParameter("ftype");
		
		FoundationOrder foundationOrder = new FoundationOrder();
		FoundationItem foundationItem =null;
		
		if(!StringUtil.isEmpty(fiId)){
			foundationItem = foundationItemManager.getFoundationItem(fiId);
		}
		if(("1").equals(fType)){
			
			foundationItem=foundationItemManager.getFoundationItemByType("1");
		}
		
		foundationOrder.setFoundationItem(foundationItem);
		JmiMember member = new JmiMember();
		member.setUserCode(userCode);
		foundationOrder.setJmiMember(member);
		foundationOrder.setFiNum(1);//数量默认为1个
		
		/*if (!StringUtil.isEmpty(orderId)) {
			foundationOrder = foundationOrderManager.getFoundationOrder(orderId);
		} else {
			foundationOrder = new FoundationOrder();
		}*/

		return foundationOrder;
	}

	
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			BindException errors)
	throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		
		//项目编号
		String fiId = request.getParameter("fiId");
		String amountV = request.getParameter("amountV");//单价金额
		//
		FoundationOrder foundationOrder = (FoundationOrder) command;

		//判断爱心365,参与过的不需要再参与
		FoundationItem tempItem = foundationItemManager.getFoundationItem(fiId);
		if(("1").equals(tempItem.getType())){
			
			//查询当前会员订单
			List tempList=foundationOrderManager.getOrdersByItemTypeAndTime(foundationOrder.getJmiMember().getUserCode());
			if(tempList!=null && tempList.size()>0){
				
				saveMessage(request, LocaleUtil.getLocalText("365foundation.already.pay"));
            	return new ModelAndView("redirect:/foundationOrders.html?strAction=selectFoundationOrderList2");
			}
		}
		  
		FoundationItem foundationItem = new FoundationItem();
		foundationItem.setFiId(Long.parseLong(fiId));//基金项目编号
		foundationOrder.setFoundationItem(foundationItem);
		foundationOrder.setStatus("0"); //默认为未审核
		foundationOrder.setCreateTime(new Date());//创建时间
		foundationOrder.setAmount(new BigDecimal(foundationOrder.getFiNum()).multiply(new BigDecimal(amountV)));//总金额
		
		//保存数据并返回订单号
		Long orderId = foundationOrderManager.saveFoundationOrder2(foundationOrder);

		//直接跳转到支付页面
		ModelAndView mv=new ModelAndView("redirect:jfiPayFoundation.html");
		mv.addObject("orderId", orderId);
		return mv;
	}
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub

	}
}
