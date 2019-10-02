package com.joymain.jecs.pd.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager;
import com.joymain.jecs.pd.service.PdFlitWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;




import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdFlitWarehouseFormController extends BaseFormController {
	private PdFlitWarehouseManager pdFlitWarehouseManager = null;
	private PdFlitWarehouseDetailManager pdFlitWarehouseDetailManager = null;
	private SysIdManager sysIdManager = null;
	private PdWarehouseManager pdWarehouseManager = null;
	private PdWarehouseStockManager pdWarehouseStockManager = null;

	public void setPdFlitWarehouseDetailManager(
			PdFlitWarehouseDetailManager pdFlitWarehouseDetailManager) {
		this.pdFlitWarehouseDetailManager = pdFlitWarehouseDetailManager;
	}

	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}

	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public void setPdFlitWarehouseManager(
			PdFlitWarehouseManager pdFlitWarehouseManager) {
		this.pdFlitWarehouseManager = pdFlitWarehouseManager;
	}

	public PdFlitWarehouseFormController() {
		setCommandName("pdFlitWarehouse");
		setCommandClass(PdFlitWarehouse.class);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		super.initPmProductMap(request);
		String fwNo = request.getParameter("fwNo");
		PdFlitWarehouse pdFlitWarehouse = null;
		// SysUser sessionLogin = SessionLogin.getLoginUser(request);
		CommonRecord crm = RequestUtil.toCommonRecord(request);
		;// ����ѯ���д��session
		// crm.setValue("fwNo", fwNo);
		Pager pager = new Pager("pdFlitWarehouseDetailListTable", request, 20);
		 Set pdFlitWarehouseDetailLists = new HashSet();
//		List pdFlitWarehouseDetailLists = new ArrayList();.
//		Set 
		if (!StringUtils.isEmpty(fwNo)) {
			pdFlitWarehouse = pdFlitWarehouseManager.getPdFlitWarehouse(fwNo);
			 pdFlitWarehouseDetailLists =
			 pdFlitWarehouse.getPdFlitWarehouseDetails();
			/*pdFlitWarehouseDetailLists = pdFlitWarehouseDetailManager
					.getPdFlitWarehouseDetailsByCrm(crm, pager);*/
		} else {
			pdFlitWarehouse = new PdFlitWarehouse();
		}
		// 控制
		String strAction = request.getParameter("strAction");
		String checkFlag = "-1";// 新建
		boolean disabled = false;// 可编辑部分是否有权限编辑 默认可以编辑
		String checkButtonKey = "operation.button.save";// 按钮显示的文字
		if ("checkPdFlitWarehouse".equals(strAction)) {
			// request.setAttribute("checkFlag", 1);//审核者登陆
			checkFlag = "1";
			checkButtonKey = "button.checked";
			disabled = true;
		} else if ("confirmPdFlitWarehouse".equals(strAction)) {
			// request.setAttribute("checkFlag", 2);//确认者登陆
			checkFlag = "2";
			checkButtonKey = "operation.button.confirm";
			disabled = true;
		} else if ("arrivedPdFlitWarehouse".equals(strAction)) {
			// request.setAttribute("checkFlag", 3);//到库确认者登陆
			checkFlag = "3";
			checkButtonKey = "pdFlitWarehouse.arrivedPdFlitWarehouse";
			disabled = true;
		} else if ("onWayPdFlitWarehouse".equals(strAction)) {
			// request.setAttribute("checkFlag", 4);//在途查看者登陆
			checkFlag = "4";
			checkButtonKey = "pdFlitWarehouse.onWayPdFlitWarehouse";
			disabled = true;
		} else if ("editPdFlitWarehouse".equals(strAction)) {
			// 修改者登陆

			checkFlag = "0";
			if (pdFlitWarehouse.getOrderFlag()>-1) {// 已经初审的单不能编辑
				disabled = true;
			}
		}
		request.setAttribute("fwNo", fwNo);
		request.setAttribute("disabledFlag", disabled);
		request.setAttribute("checkButtonKey", checkButtonKey);
		request.setAttribute("strAction", strAction);
		request.setAttribute("checkFlag", checkFlag);

		request.setAttribute("pdFlitWarehouseDetailListTable_totalRows", pager
				.getTotalObjects());
		request.setAttribute(Constants.PDFLITWAREHOUSEDETAIL_LIST,
				pdFlitWarehouseDetailLists);
		
		super.initStateCodeParem(request); //Modify By WuCF 20130515
		return pdFlitWarehouse;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		String outWarehouseNo = request.getParameter("outWarehouse.warehouseNo");
		String inWarehouseNo = request.getParameter("inWarehouse.warehouseNo");
		
		 SysUser sessionLogin = SessionLogin.getLoginUser(request);
	        String strAction = request.getParameter("strAction");
	        String view = "redirect:pdFlitWarehouses.html?strAction="+strAction;
	        String key =null;
	        
	        PdFlitWarehouse pdFlitWarehouse = (PdFlitWarehouse) command;
	        
	        List errorMsgs = new ArrayList();
	        boolean isNew = (StringUtils.isEmpty(pdFlitWarehouse.getFwNo()));
	        Locale locale = request.getLocale();
	        log.info("isNew="+isNew);
	        if("checkPdFlitWarehouse".equals(strAction)){//初审
	        	if(pdFlitWarehouseManager.getProductCountByFwNo(pdFlitWarehouse.getFwNo())!=0){
	        		checkPdFlitWarehouse(request,response,pdFlitWarehouse);
	            	key="pdFlitWarehouse.checked";
	        	}else{
	        		errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
//	        		request.setAttribute("errors", errors);
	        		errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
	        		request.getSession().setAttribute("errorMsgs", errorMsgs);
	        		view ="redirect:editPdFlitWarehouse.html?strAction=checkPdFlitWarehouse&fwNo="+pdFlitWarehouse.getFwNo();
//					return showForm(request, response, errors);
	        	}
	        	
	        }else if("confirmPdFlitWarehouse".equals(strAction)){//确认
	        	key="pdFlitWarehouse.confirm";
	        	if(verifyStock(pdFlitWarehouse)){//验证库存
	        		confirmPdFlitWarehouse(request,response,pdFlitWarehouse);
	        	}else{
	        		errors.reject("pd.notEnoughStock", new Object[] {},LocaleUtil.getLocalText("pd.notEnoughStock"));
	        		errorMsgs.add(LocaleUtil.getLocalText("pd.notEnoughStock"));
	        		request.getSession().setAttribute("errorMsgs", errorMsgs);
	        		view ="redirect:editPdFlitWarehouse.html?strAction=confirmPdFlitWarehouse&fwNo="+pdFlitWarehouse.getFwNo();
//					return showForm(request, response, errors);
	        	}
	        	
	        	
	        	
	        	
	        }else if("arrivedPdFlitWarehouse".equals(strAction)){//到库确认 才更新库存
	        	arrivedPdFlitWarehouse(request,response,pdFlitWarehouse);
	        	key="pdFlitWarehouse.arrived";

	        }else if("editPdFlitWarehouse".equals(strAction)){
	        	boolean rs = pdFlitWarehouseManager.getEmptyCheckResult(outWarehouseNo, inWarehouseNo, errors, sessionLogin.getDefCharacterCoding());
	        	if(rs){
	        		return showForm(request, response, errors);
	        	}
	        	
	        	key="pdFlitWarehouse.hasChecked";
	        	if(pdFlitWarehouse.getOrderFlag()== -1){
	        		key="pdFlitWarehouse.update";
	        		editPdFlitWarehouse(request,response,pdFlitWarehouse);
	        	}
	        	
	        }else if("addPdFlitWarehouse".equals(strAction)){
	        	boolean rs = pdFlitWarehouseManager.getEmptyCheckResult(outWarehouseNo, inWarehouseNo, errors, sessionLogin.getDefCharacterCoding());
	        	if(rs){
	        		return showForm(request, response, errors);
	        	}
	        	
	        	key="button.next";
	        	String fwNo=addPdFlitWarehouse(request,response,pdFlitWarehouse);
	        	view ="redirect:editPdFlitWarehouse.html?strAction=editPdFlitWarehouse&fwNo="+fwNo;
//	        	view ="redirect:editPdFlitWarehouseDetail.html?strAction=addPdFlitWarehouseDetail&fwNo="+fwNo;
	        }else if("deletePdFlitWarehouse".equals(strAction)){
	        	key="pdFlitWarehouse.hasChecked";
	        	if(pdFlitWarehouse.getOrderFlag()==-1){
	        		key="pdFlitWarehouse.deleted";
	        		deletePdFlitWarehouse(request,response,pdFlitWarehouse);
	        	}
	        	view = "redirect:pdFlitWarehouses.html?strAction=editPdFlitWarehouse";
	        }else if("toNewPdFlitWarehouse".equals(strAction)){
	        	key="error.cannotreturn";
	        	if(pdFlitWarehouse.getOrderFlag()<2){
	        		pdFlitWarehouse.setOrderFlag(-1);
	        		key="operation.notice.tonew";
	        		pdFlitWarehouseManager.savePdFlitWarehouse(pdFlitWarehouse);
	        	}
	        	view = "redirect:pdFlitWarehouses.html?strAction=checkPdFlitWarehouse";
	        }else if("submitPdFlitWarehouse".equals(strAction)){
	        	boolean rs = pdFlitWarehouseManager.getEmptyCheckResult(outWarehouseNo, inWarehouseNo, errors, sessionLogin.getDefCharacterCoding());
	        	if(rs){
	        		return showForm(request, response, errors);
	        	}
	        	if(pdFlitWarehouseManager.getProductCountByFwNo(pdFlitWarehouse.getFwNo())!=0){
	        		submitPdFlitWarehouse(request,response,pdFlitWarehouse);
	            	key="button.submit";
	        	}else{
	        		errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
	        		errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
	        		request.getSession().setAttribute("errorMsgs", errorMsgs);
	        		view ="redirect:editPdFlitWarehouse.html?strAction=checkPdFlitWarehouse&fwNo="+pdFlitWarehouse.getFwNo();
//					return showForm(request, response, errors);
	        	}
	        }
	        
	        
	        
	       
	        saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key));
	        return new ModelAndView(view);
	}

	private void submitPdFlitWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdFlitWarehouse pdFlitWarehouse) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdFlitWarehouse.setEditTime(new Date());
		pdFlitWarehouse.setEditUsrCode(sessionLogin.getUserCode());
		pdFlitWarehouse.setOrderFlag(0);
		pdFlitWarehouseManager.savePdFlitWarehouse(pdFlitWarehouse);
	}

	private boolean verifyStock(PdFlitWarehouse pdFlitWarehouse) {
		// TODO Auto-generated method stub
		boolean ret = true;
		Set sets = pdFlitWarehouse.getPdFlitWarehouseDetails();
		Iterator iterator=sets.iterator() ;
		while (iterator.hasNext()){
			PdFlitWarehouseDetail pdFlitWarehouseDetail = (PdFlitWarehouseDetail) iterator.next();
			if(!pdWarehouseStockManager.enoughStock(pdFlitWarehouse.getOutWarehouse().getWarehouseNo(), pdFlitWarehouseDetail.getProductNo(), pdFlitWarehouseDetail.getQty())){
					ret = false;
				}
		}
		return ret;
		
	}
	private void deletePdFlitWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdFlitWarehouse pdFlitWarehouse) {
		// TODO Auto-generated method stub
		pdFlitWarehouseManager.removePdFlitWarehouse(pdFlitWarehouse.getFwNo().toString());
		
	}
	private String addPdFlitWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdFlitWarehouse pdFlitWarehouse) {
		// TODO Auto-generated method stub
		pdFlitWarehouse.setFwNo(sysIdManager.buildIdStr("fwno"));
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		String pageOrderFlag = request.getParameter("pageOrderFlag");
		String outWarehouseNo = request.getParameter("outWarehouse.warehouseNo");
		String inWarehouseNo = request.getParameter("inWarehouse.warehouseNo");
		PdWarehouse outPdWarehouse = pdWarehouseManager.getPdWarehouse(outWarehouseNo);
		PdWarehouse inPdWarehouse = pdWarehouseManager.getPdWarehouse(inWarehouseNo);
		pdFlitWarehouse.setOutCompanyCode(outPdWarehouse.getCompanyCode());
		pdFlitWarehouse.setOutWarehouse(outPdWarehouse);
		pdFlitWarehouse.setInCompanyCode(inPdWarehouse.getCompanyCode());
		pdFlitWarehouse.setInWarehouse(inPdWarehouse);
		pdFlitWarehouse.setStockFlag("0");
		pdFlitWarehouse.setOrderFlag(new Integer(pageOrderFlag));
		pdFlitWarehouse.setCreateTime(new Date());
		pdFlitWarehouse.setCreateUsrCode(sessionLogin.getUserCode());
		/*pdFlitWarehouse.setCompanyNo(sessionLogin.getCompanyCode());
		
		pdFlitWarehouse.setCheckStatus("1");
		pdFlitWarehouse.setOkStatus("1");
		pdFlitWarehouse.setRecheckStatus("1");
		pdFlitWarehouse.setToStatus("1");
		pdFlitWarehouse.setFwDate(new Date());
		pdFlitWarehouse.setOutCompanyNo(pdWarehouseManager.getPdWarehouseByNo(pdFlitWarehouse.getOutWarehouseNo()).getCompanyNo());
		pdFlitWarehouse.setInCompanyNo(pdWarehouseManager.getPdWarehouseByNo(pdFlitWarehouse.getInWarehouseNo()).getCompanyNo());
		pdFlitWarehouse.setCompanyNo(sessionLogin.getCompanyCode());*/
		
		
		pdFlitWarehouseManager.savePdFlitWarehouse(pdFlitWarehouse);
		return pdFlitWarehouse.getFwNo();
	}
	private void editPdFlitWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdFlitWarehouse pdFlitWarehouse) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdFlitWarehouse.setEditTime(new Date());
		pdFlitWarehouse.setEditUsrCode(sessionLogin.getUserCode());
		String pageOrderFlag = request.getParameter("pageOrderFlag");
		pdFlitWarehouse.setOrderFlag(new Integer(pageOrderFlag));
		String outWarehouseNo = request.getParameter("outWarehouse.warehouseNo");
		String inWarehouseNo = request.getParameter("inWarehouse.warehouseNo");
		PdWarehouse outPdWarehouse = pdWarehouseManager.getPdWarehouse(outWarehouseNo);
		PdWarehouse inPdWarehouse = pdWarehouseManager.getPdWarehouse(inWarehouseNo);
		pdFlitWarehouse.setOutCompanyCode(outPdWarehouse.getCompanyCode());
		pdFlitWarehouse.setInCompanyCode(inPdWarehouse.getCompanyCode());
		pdFlitWarehouseManager.savePdFlitWarehouse(pdFlitWarehouse);
	}
	private void arrivedPdFlitWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdFlitWarehouse pdFlitWarehouse) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdFlitWarehouse.setToTime(new Date());
		
    	pdFlitWarehouse.setToUsrCode(sessionLogin.getUserCode());
    	pdFlitWarehouse.setOrderFlag(3);
    	pdFlitWarehouseManager.confirmFlitWarehouse(pdFlitWarehouse,"arrived");
	}
	private void confirmPdFlitWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdFlitWarehouse pdFlitWarehouse) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdFlitWarehouse.setOkTime(new Date());
    	pdFlitWarehouse.setOkUsrCode(sessionLogin.getUserCode());
    	pdFlitWarehouse.setOrderFlag(2);
    	pdFlitWarehouseManager.confirmFlitWarehouse(pdFlitWarehouse,"confirm");
	}
	private void checkPdFlitWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdFlitWarehouse pdFlitWarehouse) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdFlitWarehouse.setCheckTime(new Date());
		pdFlitWarehouse.setCheckUsrCode(sessionLogin.getUserCode());
		pdFlitWarehouse.setOrderFlag(1);
    	pdFlitWarehouseManager.savePdFlitWarehouse(pdFlitWarehouse);
    	
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
