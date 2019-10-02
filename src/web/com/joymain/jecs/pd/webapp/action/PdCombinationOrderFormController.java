package com.joymain.jecs.pd.webapp.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.exception.AppListException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdCombinationDetail;
import com.joymain.jecs.pd.model.PdCombinationOrder;
import com.joymain.jecs.pd.service.PdCombinationDetailManager;
import com.joymain.jecs.pd.service.PdCombinationOrderManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.model.JpmProductCombination;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdCombinationOrderFormController extends BaseFormController {
	private PdCombinationOrderManager pdCombinationOrderManager = null;
	private JpmProductManager jpmProductManager = null;
	private PdCombinationDetailManager pdCombinationDetailManager = null;
	private PdWarehouseManager pdWarehouseManager = null;
	private SysIdManager sysIdManager = null;
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public void setPdCombinationDetailManager(
			PdCombinationDetailManager pdCombinationDetailManager) {
		this.pdCombinationDetailManager = pdCombinationDetailManager;
	}

	public void setJpmProductManage(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	public void setPdCombinationOrderManager(
			PdCombinationOrderManager pdCombinationOrderManager) {
		this.pdCombinationOrderManager = pdCombinationOrderManager;
	}

	public PdCombinationOrderFormController() {
		setCommandName("pdCombinationOrder");
		setCommandClass(PdCombinationOrder.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		super.initPmProductMap(request);
		String pcNo = request.getParameter("pcNo");
		PdCombinationOrder pdCombinationOrder = null;
		boolean disabled = true;
		if (!StringUtils.isEmpty(pcNo)) {
			pdCombinationOrder = pdCombinationOrderManager
					.getPdCombinationOrder(pcNo);
			//modify by fu 商品组合单数量不允许编辑
			/*if (pdCombinationOrder.getOrderFlag() == -1) {
				disabled = false;
			}*/
		} else {
			pdCombinationOrder = new PdCombinationOrder();
			disabled = false;
		}
		request.setAttribute("disabled", disabled);
		return pdCombinationOrder;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		PdCombinationOrder pdCombinationOrder = (PdCombinationOrder) command;
		boolean isNew = (pdCombinationOrder.getPcNo() == null);
		Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		String view = "redirect:pdCombinationOrders.html?strAction="
				+ strAction;
		if ("addPdCombinationOrder".equals(strAction)) {
			addPdCombinationOrder(request, response, pdCombinationOrder);
			view = "redirect:pdCombinationOrders.html?strAction=editPdCombinationOrder";
		} else if ("editPdCombinationOrder".equals(strAction)) {
			if (pdCombinationOrder.getOrderFlag() == -1) {
//				pdCombinationOrder.setCompanyCode(pdWarehouseManager
//						.getPdWarehouse(pdCombinationOrder.getWarehouseNo())
//						.getCompanyCode());
//				pdCombinationOrderManager
//						.savePdCombinationOrder(pdCombinationOrder);
//				
				editPdCombinationOrder(pdCombinationOrder,request,response);
			}
		} else if ("submitPdCombinationOrder".equals(strAction)) {
			submitPdCombinationOrder(request, response, pdCombinationOrder);
			view = "redirect:pdCombinationOrders.html?strAction=editPdCombinationOrder";
		} else if ("checkPdCombinationOrder".equals(strAction)) {
			checkPdCombinationOrder(request, response, pdCombinationOrder);
		} else if ("confirmPdCombinationOrder".equals(strAction)) {
			log.info("confirmPdCombinationOrder>>>");
			view = "redirect:pdCombinationOrders.html?strAction=confirmPdCombinationOrder";
			try {
				confirmPdCombinationOrder(request, response, pdCombinationOrder);
				log.info("confirmPdCombinationOrder>>>222");
			} catch (AppListException e) {// 捕获代理商库存不足
				// TODO Auto-generated catch block
				List errorList = e.getErrorList();
//				errors.reject(null, new Object[] {}, LocaleUtil
//						.getLocalText(e.getErrMsg()));
				key = "pd.agent.notEnoughStock";
				request.getSession().setAttribute("errorList", errorList);
				request.getSession().setAttribute("errorCode", e.getErrMsg());
//				request.setAttribute("pcNo", pdCombinationOrder.getPcNo());
				view = "redirect:editPdCombinationOrder.html?strAction=confirmPdCombinationOrder&pcNo="+pdCombinationOrder.getPcNo();
//				view = "pd/pdCombinationOrderForm";
				
//				return showForm(request, response, errors);
			}
			
		} else if ("deletePdCombinationOrder".equals(strAction)) {
			pdCombinationOrderManager
					.removePdCombinationOrder(pdCombinationOrder.getPcNo()
							.toString());
			 view = "redirect:pdCombinationOrders.html?strAction=editPdCombinationOrder";
			key = "pdCombinationOrder.delete";
		}else if("toNewPdCombinationOrder".equals(strAction)){
			if(pdCombinationOrder.getOrderFlag() == 1 || pdCombinationOrder.getOrderFlag() == 0){
				pdCombinationOrder.setOrderFlag(-1);
				pdCombinationOrderManager.savePdCombinationOrder(pdCombinationOrder);
			}
			view = "redirect:pdCombinationOrders.html?strAction=checkPdCombinationOrder";
		}

		return new ModelAndView(view);
	}

	
	private void editPdCombinationOrder(PdCombinationOrder pdCombinationOrder,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		pdCombinationOrder.setCompanyCode(pdWarehouseManager
				.getPdWarehouse(pdCombinationOrder.getWarehouseNo())
				.getCompanyCode());
		Set<PdCombinationDetail> pdCombinationDetails =pdCombinationOrder.getPdCombinationDetails();
		Iterator iterator=pdCombinationDetails.iterator();
		while(iterator.hasNext()){
			PdCombinationDetail pdCombinationDetail =(PdCombinationDetail) iterator.next();
			String qty = request.getParameter(pdCombinationDetail.getProductNo()+"-qty");
			if(StringUtil.isDouble(qty)){
				pdCombinationDetail.setQty(new Long(qty));
			}
			pdCombinationDetailManager.savePdCombinationDetail(pdCombinationDetail);
		}
		pdCombinationOrderManager
				.savePdCombinationOrder(pdCombinationOrder);
	}

	private void checkPdCombinationOrder(HttpServletRequest request,
			HttpServletResponse response, PdCombinationOrder pdCombinationOrder) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdCombinationOrder.setCheckTime(new Date());
		pdCombinationOrder.setOrderFlag(1);
		pdCombinationOrder.setCheckUsrCode(sessionLogin.getUserCode());
		pdCombinationOrderManager.savePdCombinationOrder(pdCombinationOrder);
	}

	private void confirmPdCombinationOrder(HttpServletRequest request,
			HttpServletResponse response, PdCombinationOrder pdCombinationOrder) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdCombinationOrder.setOrderFlag(2);
		pdCombinationOrder.setOkTime(new Date());
		pdCombinationOrder.setOkUsrCode(sessionLogin.getUserCode());
		pdCombinationOrderManager.confirmPdCombinationOrder(pdCombinationOrder,
				sessionLogin);
	}

	private void submitPdCombinationOrder(HttpServletRequest request,
			HttpServletResponse response, PdCombinationOrder pdCombinationOrder) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
	

		pdCombinationOrder.setOrderFlag(0);
		Set<PdCombinationDetail> pdCombinationDetails =pdCombinationOrder.getPdCombinationDetails();
		Iterator iterator=pdCombinationDetails.iterator();
		while(iterator.hasNext()){
			PdCombinationDetail pdCombinationDetail =(PdCombinationDetail) iterator.next();
			String qty = request.getParameter(pdCombinationDetail.getProductNo()+"-qty");
			if(StringUtils.isNumeric(qty)){
				pdCombinationDetail.setQty(new Long(qty));
			}
			pdCombinationDetailManager.savePdCombinationDetail(pdCombinationDetail);
		}
		pdCombinationOrderManager
				.savePdCombinationOrder(pdCombinationOrder);
		
		/*pdCombinationOrderManager.savePdCombinationOrder(pdCombinationOrder);
		
		if(StringUtils.isEmpty(pdCombinationOrder.getPcNo())){
			pdCombinationOrder.setPcNo(sysIdManager.buildIdStr("pcno"));
		}
		pdCombinationOrder.setCompanyCode(pdWarehouseManager.getPdWarehouse(
				pdCombinationOrder.getWarehouseNo()).getCompanyCode());
		pdCombinationOrderManager.submitPdCombinationOrder(pdCombinationOrder,
				sessionLogin);
*/
	}

	private void addPdCombinationOrder(HttpServletRequest request,
			HttpServletResponse response, PdCombinationOrder pdCombinationOrder) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdCombinationOrder.setPcNo(sysIdManager.buildIdStr("pcno"));
		pdCombinationOrder.setCreateTime(new Date());
		pdCombinationOrder.setOrderFlag(-1);
		pdCombinationOrder.setCreateUsrCode(sessionLogin.getUserCode());
		pdCombinationOrder.setCompanyCode(pdWarehouseManager.getPdWarehouse(
				pdCombinationOrder.getWarehouseNo()).getCompanyCode());
		pdCombinationOrderManager.addPdCombinationOrder(pdCombinationOrder);

	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
}
