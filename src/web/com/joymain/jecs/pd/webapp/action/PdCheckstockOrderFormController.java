package com.joymain.jecs.pd.webapp.action;

import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdCheckstockOrder;
import com.joymain.jecs.pd.model.PdCheckstockOrderDetail;
import com.joymain.jecs.pd.service.PdCheckstockOrderManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdCheckstockOrderFormController extends BaseFormController {
	private PdCheckstockOrderManager pdCheckstockOrderManager = null;
	private PdWarehouseManager pdWarehouseManager =null;
	private SysIdManager sysIdManager = null;
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public void setPdCheckstockOrderManager(
			PdCheckstockOrderManager pdCheckstockOrderManager) {
		this.pdCheckstockOrderManager = pdCheckstockOrderManager;
	}

	public PdCheckstockOrderFormController() {
		setCommandName("pdCheckstockOrder");
		setCommandClass(PdCheckstockOrder.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		super.initPmProductMap(request);
		String pcoNo = request.getParameter("pcoNo");
		PdCheckstockOrder pdCheckstockOrder = null;
		String strAction = request.getParameter("strAction");
		Set pdCheckstockOrderDetails = null;
		if (!StringUtils.isEmpty(pcoNo)) {
			pdCheckstockOrder = pdCheckstockOrderManager
					.getPdCheckstockOrder(pcoNo);
			pdCheckstockOrderDetails = pdCheckstockOrder
					.getPdCheckstockOrderDetails();
		} else {
			pdCheckstockOrder = new PdCheckstockOrder();
		}
		request.setAttribute(Constants.PDCHECKSTOCKORDERDETAIL_LIST,
				pdCheckstockOrderDetails);
		return pdCheckstockOrder;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		PdCheckstockOrder pdCheckstockOrder = (PdCheckstockOrder) command;
		boolean isNew = (pdCheckstockOrder.getPcoNo() == null);
		Locale locale = request.getLocale();
		String key = null;
		String strAction = request.getParameter("strAction");
		if("addPdCheckstockOrder".equals(strAction)){
			addPdCheckstockOrder(request,response,pdCheckstockOrder);
		}else if("checkPdCheckstockOrder".equals(strAction)){
			checkPdCheckstockOrder(request,response,pdCheckstockOrder);
			
		}else if("confirmPdCheckstockOrder".equals(strAction)){
			confirmPdCheckstockOrder(request,response,pdCheckstockOrder);
		}else if ("deletePdCheckstockOrder".equals(strAction)) {
			pdCheckstockOrderManager.removePdCheckstockOrder(pdCheckstockOrder
					.getPcoNo().toString());
			key = "pdCheckstockOrder.delete";
		} /*else {
			pdCheckstockOrderManager.savePdCheckstockOrder(pdCheckstockOrder);
			key = "pdCheckstockOrder.update";
		}*/

		return new ModelAndView(getSuccessView());
	}

	private void confirmPdCheckstockOrder(HttpServletRequest request,
			HttpServletResponse response, PdCheckstockOrder pdCheckstockOrder) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdCheckstockOrder.setOkUsrCode(sessionLogin.getUserCode());
		pdCheckstockOrder.setOkTime(new Date());
		pdCheckstockOrder.setOrderFlag(2);
		pdCheckstockOrderManager.confirmPdCheckstockOrder(pdCheckstockOrder);
	}

	private void checkPdCheckstockOrder(HttpServletRequest request,
			HttpServletResponse response, PdCheckstockOrder pdCheckstockOrder) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdCheckstockOrder.setCheckUsrCode(sessionLogin.getUserCode());
		pdCheckstockOrder.setCheckTime(new Date());
		pdCheckstockOrder.setOrderFlag(1);
		
		Set pdCheckstockOrderDetails = pdCheckstockOrder.getPdCheckstockOrderDetails();
		Iterator iterator = pdCheckstockOrderDetails.iterator();
		while(iterator.hasNext()){
			PdCheckstockOrderDetail pdCheckstockOrderDetail =(PdCheckstockOrderDetail) iterator.next();
			String realQty = request.getParameter(pdCheckstockOrderDetail.getUniNo().toString() +"-realQty");
			Long realQtyL = 0l;
			if(StringUtils.isNotEmpty(realQty)&&StringUtils.isNumeric(realQty)){
				realQtyL = new Long(realQty);
			}
			pdCheckstockOrderDetail.setRealQty(realQtyL);
		}
		pdCheckstockOrderManager.savePdCheckstockOrder(pdCheckstockOrder);
		
		
	}

	private void addPdCheckstockOrder(HttpServletRequest request,
			HttpServletResponse response, PdCheckstockOrder pdCheckstockOrder) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdCheckstockOrder.setOrderFlag(0);
		pdCheckstockOrder.setPcoNo(sysIdManager.buildIdStr("pcono"));
		pdCheckstockOrder.setCreateTime(new Date());
		pdCheckstockOrder.setCreateUsrCode(sessionLogin.getUserCode());
		pdCheckstockOrder.setCompanyCode(pdWarehouseManager.getPdWarehouse(pdCheckstockOrder.getWarehouseNo()).getCompanyCode());
		
		pdCheckstockOrderManager.addPdCheckstockOrder(pdCheckstockOrder);
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
