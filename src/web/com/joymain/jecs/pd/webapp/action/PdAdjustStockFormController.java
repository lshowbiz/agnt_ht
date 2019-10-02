package com.joymain.jecs.pd.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdAdjustStock;
import com.joymain.jecs.pd.service.PdAdjustStockDetailManager;
import com.joymain.jecs.pd.service.PdAdjustStockManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;


import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdAdjustStockFormController extends BaseFormController {
	private PdAdjustStockManager pdAdjustStockManager = null;
	private PdAdjustStockDetailManager pdAdjustStockDetailManager = null;
	private SysIdManager sysIdManager = null;
	private PdWarehouseManager pdWarehouseManager =null;
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public void setPdAdjustStockDetailManager(
			PdAdjustStockDetailManager pdAdjustStockDetailManager) {
		this.pdAdjustStockDetailManager = pdAdjustStockDetailManager;
	}

	public void setPdAdjustStockManager(
			PdAdjustStockManager pdAdjustStockManager) {
		this.pdAdjustStockManager = pdAdjustStockManager;
	}

	public PdAdjustStockFormController() {
		setCommandName("pdAdjustStock");
		setCommandClass(PdAdjustStock.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		super.initPmProductMap(request);
		String asNo = request.getParameter("asNo");
		PdAdjustStock pdAdjustStock = null;

		CommonRecord crm = RequestUtil.toCommonRecord(request);// ����ѯ���д��session
		crm.setValue("asNo", asNo);
		Pager pager = new Pager("pdAdjustStockDetailListTable", request, 20);
		 Set pdAdjustStockDetails = new HashSet();
//		List pdAdjustStockDetails = new ArrayList();
		if (!StringUtils.isEmpty(asNo)) {
			pdAdjustStock = pdAdjustStockManager.getPdAdjustStock(asNo);
			 pdAdjustStockDetails = pdAdjustStock.getPdAdjustStockDetails();
//			pdAdjustStockDetails = pdAdjustStockDetailManager
//					.getPdAdjustStockDetailsByCrm(crm, pager);
		} else {
			pdAdjustStock = new PdAdjustStock();
		}

		// 控制
		String strAction = request.getParameter("strAction");
		String checkFlag = "-1";
		boolean disabled = true;// 可编辑部分是否有权限编辑
		String checkButtonKey = "operation.button.save";// 按钮显示的文字
		if ("checkPdAdjustStock".equals(strAction)) {
			checkFlag = "1";
			// 审核
			checkButtonKey = "button.checked";
			disabled = true;
		} else if ("confirmPdAdjustStock".equals(strAction)) {
			checkFlag = "2";// 确认
			checkButtonKey = "operation.button.confirm";
			disabled = true;
		} else if ("editPdAdjustStock".equals(strAction)) {
			checkFlag = "0";// 修改
			disabled = false;
			if(pdAdjustStock.getOrderFlag()==-1){
				disabled = false;
			}else{
				disabled = true;
			}
			
		} else if ("addPdAdjustStock".equals(strAction)) {
			disabled = false;
		}

		request.setAttribute("checkFlag", checkFlag);
		request.setAttribute("asNo", asNo);
		request.setAttribute("disabledFlag", disabled);
		request.setAttribute("strAction", strAction);
		request.setAttribute("checkButtonKey", checkButtonKey);
		log.debug("disabledFlag=" + disabled);
		request.setAttribute("pdAdjustStockListTable_totalRows", pager
				.getTotalObjects());
		request.setAttribute(Constants.PDADJUSTSTOCKDETAIL_LIST,
				pdAdjustStockDetails);

		return pdAdjustStock;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}
		super.initPmProductMap(request);
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String strAction = request.getParameter("strAction");
       
        List errorMsgs = new ArrayList();
        
        PdAdjustStock pdAdjustStock = (PdAdjustStock) command;
        boolean isNew = (pdAdjustStock.getAsNo() == null);
        Locale locale = request.getLocale();
        String key = null;
        String view = "redirect:pdAdjustStocks.html?strAction="+strAction;
        if("checkPdAdjustStock".equals(strAction)){
        	if(pdAdjustStockManager.hasProductCountByAsNo(pdAdjustStock.getAsNo())){
        		key="pdAdjustStock.checked";
            	checkPdAdjustStock(request,response,pdAdjustStock);
        	}else{
        		errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		request.getSession().setAttribute("errorMsgs", errorMsgs);
        		view="redirect:editPdAdjustStock.html?strAction=checkPdAdjustStock&asNo="+pdAdjustStock.getAsNo();
//				return showForm(request, response, errors);
        	}
        	
        	
//        	return new ModelAndView("redirect:pdAdjustStocks.html?strAction=checkInvoice");
        }else if("confirmPdAdjustStock".equals(strAction)){
        	key="pdAdjustStock.confirm";
        	try {
				confirmPdAdjustStock(request,response,pdAdjustStock);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				key=e.getErrMsg();
			}
//        	
//        	return new ModelAndView("redirect:pdAdjustStocks.html?strAction=confirmInvoice");
        }else if("addPdAdjustStock".equals(strAction)){
        	key="pdAdjustStock.add";
        	String asNo= addPdAdjustStock(request,response,pdAdjustStock);
        	view="redirect:editPdAdjustStock.html?strAction=editPdAdjustStock&asNo="+asNo;
        }else if("editPdAdjustStock".equals(strAction)){
        	key="pdAdjustStock.hasChecked";
        	if(pdAdjustStock.getOrderFlag()==-1){
        		key="pdAdjustStock.update";
        		editPdAdjustStock(request,response,pdAdjustStock);
        	}
        	view="redirect:pdAdjustStocks.html?strAction=editPdAdjustStock";
        }else if("deletePdAdjustStock".equals(strAction)){
        	key="pd.hasChecked";
        	if(pdAdjustStock.getOrderFlag()==-1){
        		key="pdAdjustStock.delete";
        		deletePdAdjustStock(request,response,pdAdjustStock);
        	}
        	view="redirect:pdAdjustStocks.html?strAction=editPdAdjustStock";
        }else if("toNewPdAdjustStock".equals(strAction)){
        	key="error.cannotreturn";
        	if(pdAdjustStock.getOrderFlag()==0 || pdAdjustStock.getOrderFlag()==1){
        		pdAdjustStock.setOrderFlag(-1);
        		key="operation.notice.tonew";
        		pdAdjustStockManager.savePdAdjustStock(pdAdjustStock);
        	}
        	view="redirect:pdAdjustStocks.html?strAction=checkPdAdjustStock";
        }else if("submitPdAdjustStock".equals(strAction)){
        	
        	if(pdAdjustStockManager.hasProductCountByAsNo(pdAdjustStock.getAsNo())){
        		key="pd.hasSubmited";
        		view="redirect:pdAdjustStocks.html?strAction=editPdAdjustStock";
        		strAction="editPdAdjustStock";
        		submitPdAdjustStock(request,response,pdAdjustStock);
        	}else{
        		errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		request.getSession().setAttribute("errorMsgs", errorMsgs);
        		view="redirect:editPdAdjustStock.html?strAction=editPdAdjustStock&asNo="+pdAdjustStock.getAsNo();
        	}
        	
        }
        
        
        
        
        saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key));
        return new ModelAndView(view);
	}

	private void submitPdAdjustStock(HttpServletRequest request,
			HttpServletResponse response, PdAdjustStock pdAdjustStock) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdAdjustStock.setEditUsrCode(sessionLogin.getUserCode());
		pdAdjustStock.setOrderFlag(0);
		pdAdjustStock.setEditTime(new Date());
		pdAdjustStockManager.savePdAdjustStock(pdAdjustStock);
	}

	private void deletePdAdjustStock(HttpServletRequest request,
			HttpServletResponse response, PdAdjustStock pdAdjustStock) {
		// TODO Auto-generated method stub
		pdAdjustStockManager.removePdAdjustStock(pdAdjustStock.getAsNo().toString());
	}

	private void editPdAdjustStock(HttpServletRequest request,
			HttpServletResponse response, PdAdjustStock pdAdjustStock) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdAdjustStock.setEditUsrCode(sessionLogin.getUserCode());
		pdAdjustStock.setEditTime(new Date());
		
		pdAdjustStockManager.savePdAdjustStock(pdAdjustStock);
	}

	private String addPdAdjustStock(HttpServletRequest request,
			HttpServletResponse response, PdAdjustStock pdAdjustStock) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdAdjustStock.setAsNo(sysIdManager.buildIdStr("asno"));
		pdAdjustStock.setCreateUsrCode(sessionLogin.getUserCode());
		pdAdjustStock.setCompanyCode(pdWarehouseManager.getPdWarehouse(pdAdjustStock.getWarehouseNo()).getCompanyCode());
		pdAdjustStock.setAmount(new BigDecimal(0));
		pdAdjustStock.setCreateTime(new Date());
		pdAdjustStock.setOrderFlag(-1);
		/*pdAdjustStock.setCheckStatus("0");
		pdAdjustStock.setFinanceStatus("1");
		pdAdjustStock.setRecheckStatus("1");
		pdAdjustStock.setOkStatus("1");*/
		pdAdjustStock.setStockFlag("0");
		pdAdjustStockManager.savePdAdjustStock(pdAdjustStock);
		return pdAdjustStock.getAsNo();
	}

	private void confirmPdAdjustStock(HttpServletRequest request,
			HttpServletResponse response, PdAdjustStock pdAdjustStock) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	pdAdjustStock.setOkTime(new Date());
    	pdAdjustStock.setOkUsrCode(sessionLogin.getUserCode());
//    	pdAdjustStock.setOkStatus("2");
    	pdAdjustStockManager.confirmPdAdjustStock(pdAdjustStock);
	}

	private void checkPdAdjustStock(HttpServletRequest request,
			HttpServletResponse response, PdAdjustStock pdAdjustStock) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	pdAdjustStock.setCheckTime(new Date());
    	pdAdjustStock.setOrderFlag(1);
    	pdAdjustStock.setCheckUsrCode(sessionLogin.getUserCode());
    	
    	pdAdjustStockManager.savePdAdjustStock(pdAdjustStock);
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
