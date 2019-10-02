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
import com.joymain.jecs.pd.model.PdOutWarehouse;
import com.joymain.jecs.pd.service.PdOutWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;


import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdOutWarehouseFormController extends BaseFormController {
	private PdOutWarehouseManager pdOutWarehouseManager = null;
	private SysIdManager sysIdManager =null;
	private PdWarehouseManager pdWarehouseManager = null;
	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setPdOutWarehouseManager(
			PdOutWarehouseManager pdOutWarehouseManager) {
		this.pdOutWarehouseManager = pdOutWarehouseManager;
	}

	public PdOutWarehouseFormController() {
		setCommandName("pdOutWarehouse");
		setCommandClass(PdOutWarehouse.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		String owNo = request.getParameter("owNo");
		PdOutWarehouse pdOutWarehouse = null;
		super.initPmProductMap(request);
		CommonRecord crm = RequestUtil.toCommonRecord(request);// ����ѯ���д��session
		// Pager pager = new Pager("pdOutWarehouseListTable",request, 20);
		crm.setValue("owNo", owNo);
		// List pdOutWarehouseDetails = new ArrayList();
		Set pdOutWarehouseDetails = new HashSet();
		if (!StringUtils.isEmpty(owNo)) {
			pdOutWarehouse = pdOutWarehouseManager.getPdOutWarehouse(owNo);
			pdOutWarehouseDetails = pdOutWarehouse.getPdOutWarehouseDetails();
			// pdOutWarehouseDetails =
			// pdOutWarehouseDetailManager.getPdOutWarehouseDetailsByCrm(crm,
			// pager);
		} else {
			pdOutWarehouse = new PdOutWarehouse();
		}
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String loginCompany = sessionLogin.getCompanyCode();
		boolean companyReadabled = false;
		if ("AA".equalsIgnoreCase(loginCompany)) {
			companyReadabled = true;

		}
		// 控制
		String strAction = request.getParameter("strAction");
		String checkFlag = "-1";// 新建
		boolean disabled = false;// 可编辑部分是否有权限编辑 默认可以编辑
		String checkButtonKey = "operation.button.save";// 按钮显示的文字

		if ("checkPdOutWarehouse".equals(strAction)) {
			// request.setAttribute("checkFlag", 1);//审核者登陆
			checkFlag = "1";
			checkButtonKey = "button.checked";
			disabled = true;
		} else if ("confirmPdOutWarehouse".equals(strAction)) {
			// request.setAttribute("checkFlag", 2);//确认者登陆
			checkFlag = "2";
			// checkButtonKey = "pdOutWarehouse.confirm";
			checkButtonKey = "operation.button.confirm";
			disabled = true;
		} else if ("editPdOutWarehouse".equals(strAction)) {
			// 修改者登陆

			checkFlag = "0";
			if (pdOutWarehouse.getOrderFlag() == -1) {// 未提交的单能编辑
				disabled = false;
			} else {// 已经提交的单不能编辑
				disabled = true;
			}
		} else if ("".equals(strAction)) {
			checkButtonKey = "button.next";
		}

		request.setAttribute("checkFlag", checkFlag);
		request.setAttribute("strAction", strAction);
		request.setAttribute("owNo", owNo);
		request.setAttribute("disabledFlag", disabled);
		request.setAttribute("checkButtonKey", checkButtonKey);
		log.debug("disabledFlag=" + disabled);
		// request.setAttribute("pdOutWarehouseDetailListTable_totalRows",
		// pager.getTotalObjects());
		request.setAttribute(Constants.PDOUTWAREHOUSEDETAIL_LIST,
				pdOutWarehouseDetails);
 
		super.initStateCodeParem(request); //Modify By WuCF 20130515
		return pdOutWarehouse;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String strAction = request.getParameter("strAction");
        String view = getSuccessView();
        view="redirect:pdOutWarehouses.html?strAction="+strAction;
        String key=null;
        PdOutWarehouse pdOutWarehouse = (PdOutWarehouse) command;
//        boolean isNew = StringUtils.isEmpty(pdOutWarehouse.getOwNo());
//        Locale locale = request.getLocale();
        List errorMsgs = new ArrayList();
        if("addPdOutWarehouse".equals(strAction)){//新建
        	String owNo= addPdOutWarehouse(request,response,pdOutWarehouse);
        	key = "pdOutWarehouse.added";
        	view="redirect:editPdOutWarehouse.html?strAction=editPdOutWarehouse&owNo="+owNo;
        }else if("checkPdOutWarehouse".equals(strAction)){//审核
        	if(pdOutWarehouseManager.getProductCountByOwNo(pdOutWarehouse.getOwNo())!=0){//支持冲红
        		checkPdOutWarehouse(request,response,pdOutWarehouse);
        	
        		key="pdOutWarehouse.checked";
        	}else{
        		errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
//        		request.setAttribute("errors", errors);
        		errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		request.getSession().setAttribute("errorMsgs", errorMsgs);
        		view="redirect:editPdOutWarehouse.html?strAction=checkPdOutWarehouse&owNo="+pdOutWarehouse.getOwNo();
//				return showForm(request, response, errors);
        	}
        	
        }else if("confirmPdOutWarehouse".equals(strAction)){//确认
        	key="pdOutWarehouse.confirmed";
        	try {
				confirmPdOutWarehouse(request,response,pdOutWarehouse);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				errors.reject("pd.notEnoughStock", new Object[] {},LocaleUtil.getLocalText("pd.notEnoughStock"));
				key="pd.notEnoughStock";
				errorMsgs.add(LocaleUtil.getLocalText("pd.notEnoughStock"));
				request.getSession().setAttribute("errorMsgs", errorMsgs);
				view="redirect:editPdOutWarehouse.html?strAction=confirmPdOutWarehouse&owNo="+pdOutWarehouse.getOwNo();
//				return showForm(request, response, errors);
			}
        	
        	
        }else if("editPdOutWarehouse".equals(strAction)){//编辑
        	
        	key="pd.hasChecked";
        	if(pdOutWarehouse.getOrderFlag()==-1){//新增提交功能 0 未提交 1 提交 2 初审
        		key="pdOutWarehouse.update";
        		editPdOutWarehouse(request,response,pdOutWarehouse);
        	}
        	
        }else if("deletePdOutWarehouse".equals(strAction)){//删除
        	key="pd.hasChecked";
        	if(pdOutWarehouse.getOrderFlag()==-1){
        		key="pdOutWarehouse.deleted";
        		deletePdOutWarehouse(request,response,pdOutWarehouse);
        	}
        	
        	view="redirect:pdOutWarehouses.html?strAction=editPdOutWarehouse";
        }else if("toNewPdOutWarehouse".equals(strAction)){
        	key="error.cannotreturn";
        	if(pdOutWarehouse.getOrderFlag()==1 || pdOutWarehouse.getOrderFlag()==0){
        		pdOutWarehouse.setOrderFlag(-1);
        		key="operation.notice.tonew";
        		pdOutWarehouseManager.savePdOutWarehouse(pdOutWarehouse);
        	}
        	view="redirect:pdOutWarehouses.html?strAction=checkPdOutWarehouse";
        	
        }else if("submitPdOutWarehouse".equals(strAction)){//提交
        	view="redirect:pdOutWarehouses.html?strAction=editPdOutWarehouse";
        	if(pdOutWarehouseManager.getProductCountByOwNo(pdOutWarehouse.getOwNo())!=0){//支持冲红
//        		checkPdOutWarehouse(request,response,pdOutWarehouse);
        		submitPdOutWarehouse(request,response,pdOutWarehouse);
        		key="pd.hasSubmited";
        	}else{
        		errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        	
        		errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		request.getSession().setAttribute("errorMsgs", errorMsgs);
        		view="redirect:editPdOutWarehouse.html?strAction=editPdOutWarehouse&owNo="+pdOutWarehouse.getOwNo();
//				return showForm(request, response, errors);
        	}
        	
        	
        }
        
        
        saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key));       

        return new ModelAndView(view);
    }
	 private void submitPdOutWarehouse(HttpServletRequest request,
				HttpServletResponse response, PdOutWarehouse pdOutWarehouse) {
			// TODO Auto-generated method stub
	    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
			pdOutWarehouse.setEditUsrCode(sessionLogin.getUserCode());
	    	pdOutWarehouse.setOrderFlag(0);
	    	pdOutWarehouse.setEditTime(new Date());
	    	pdOutWarehouseManager.savePdOutWarehouse(pdOutWarehouse);
		}
		private void deletePdOutWarehouse(HttpServletRequest request,
				HttpServletResponse response, PdOutWarehouse pdOutWarehouse) {
			// TODO Auto-generated method stub
	    	pdOutWarehouseManager.removePdOutWarehouse(pdOutWarehouse.getOwNo().toString());
		}
		private void editPdOutWarehouse(HttpServletRequest request,
				HttpServletResponse response, PdOutWarehouse pdOutWarehouse) {
			// TODO Auto-generated method stub
			SysUser sessionLogin = SessionLogin.getLoginUser(request);
//			String pageOrderFlag= request.getParameter("pageOrderFlag");
//			pdOutWarehouse.setOrderFlag(new Integer(pageOrderFlag));
			pdOutWarehouse.setEditUsrCode(sessionLogin.getUserCode());
			pdOutWarehouse.setEditTime(new Date());
			pdOutWarehouse.setCompanyCode(pdWarehouseManager.getPdWarehouse(pdOutWarehouse.getWarehouseNo()).getCompanyCode());
			pdOutWarehouseManager.savePdOutWarehouse(pdOutWarehouse);
		}
		private void confirmPdOutWarehouse(HttpServletRequest request,
				HttpServletResponse response, PdOutWarehouse pdOutWarehouse) {
			// TODO Auto-generated method stub
			SysUser sessionLogin = SessionLogin.getLoginUser(request);
//			pdOutWarehouse.setOrderFlag(2);
	    	pdOutWarehouse.setOkTime(new Date());
	    	pdOutWarehouse.setOkUsrCode(sessionLogin.getUserCode());
	    	pdOutWarehouseManager.confirmPdOutWarehouse(pdOutWarehouse);
		}
		private void checkPdOutWarehouse(HttpServletRequest request,
				HttpServletResponse response, PdOutWarehouse pdOutWarehouse) {
			// TODO Auto-generated method stub
			SysUser sessionLogin = SessionLogin.getLoginUser(request);
			pdOutWarehouse.setOrderFlag(1);
	    	pdOutWarehouse.setCheckTime(new Date());
	    	pdOutWarehouse.setCheckUsrCode(sessionLogin.getUserCode());
	    	pdOutWarehouseManager.savePdOutWarehouse(pdOutWarehouse);
		}
		private String addPdOutWarehouse(HttpServletRequest request,
				HttpServletResponse response, PdOutWarehouse pdOutWarehouse) {
			// TODO Auto-generated method stub
	    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
	    	pdOutWarehouse.setOwNo(sysIdManager.buildIdStr("owno"));
			pdOutWarehouse.setCreateUsrCode(sessionLogin.getUserCode());
			pdOutWarehouse.setAmount(new BigDecimal(0));
			pdOutWarehouse.setCreateTime(new Date());
			pdOutWarehouse.setCompanyCode(pdWarehouseManager.getPdWarehouse(pdOutWarehouse.getWarehouseNo()).getCompanyCode());
/*//			pdOutWarehouse.setCheckStatus("1");
			pdOutWarehouse.setCheckStatus("0");//新增提交功能，由前台提交
			pdOutWarehouse.setFcheckStatus("1");
			pdOutWarehouse.setRecheckStatus("1");
			pdOutWarehouse.setOkStatus("1");*/
			pdOutWarehouse.setStockFlag("0");
			pdOutWarehouse.setOrderFlag(-1);
			pdOutWarehouseManager.savePdOutWarehouse(pdOutWarehouse);
			return pdOutWarehouse.getOwNo();
		}
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
		// binder.setAllowedFields(allowedFields);
		// binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields); 
		String[] disallowedFields = new String[] { "orderFlag", "stockFlag" }; 
		// binder.setAllowedFields(allowedFields);
		binder.setDisallowedFields(disallowedFields);
		// binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);  
	}
}
