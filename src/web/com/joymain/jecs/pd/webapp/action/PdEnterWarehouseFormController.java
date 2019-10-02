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

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.model.PdWarehouseStock;
import com.joymain.jecs.pd.model.PdWarehouseStockTrace;
import com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager;
import com.joymain.jecs.pd.service.PdEnterWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pd.service.PdWarehouseStockTraceManager;
import com.joymain.jecs.sys.service.SysIdManager;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class PdEnterWarehouseFormController extends BaseFormController {
	private PdWarehouseStockTraceManager pdWarehouseStockTraceManager = null;
    private PdEnterWarehouseManager pdEnterWarehouseManager = null;
    private SysIdManager sysIdManager = null;
    private PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager = null;
    private PdWarehouseManager pdWarehouseManager = null;
    public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}
	public void setPdEnterWarehouseManager(PdEnterWarehouseManager pdEnterWarehouseManager) {
        this.pdEnterWarehouseManager = pdEnterWarehouseManager;
    }
    public PdEnterWarehouseFormController() {
        setCommandName("pdEnterWarehouse");
        setCommandClass(PdEnterWarehouse.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	super.initPmProductMap(request);
        String ewNo = request.getParameter("ewNo");
        PdEnterWarehouse pdEnterWarehouse = null;
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        CommonRecord crm = RequestUtil.toCommonRecord(request);//����ѯ���д��session
        crm.setValue("ewNo", ewNo);
        Pager pager = new Pager("pdEnterWarehouseDetailListTable",request, 20);
        List pdEnterWarehouseDetails = new ArrayList();
//        Set pdEnterWarehouseDetails = new HashSet();
        boolean disabled = true;//可编辑部分是否有权限编辑
        String checkButtonKey = "operation.button.save";//按钮显示的文字
        String checkFlag="-1";//新建
        String loginCompany = sessionLogin.getCompanyCode();
        boolean companyReadabled =false;
        if("AA".equalsIgnoreCase(loginCompany)){
        	companyReadabled = true;
        	
        }
        if (!StringUtils.isEmpty(ewNo)) {
            pdEnterWarehouse = pdEnterWarehouseManager.getPdEnterWarehouse(ewNo);
//            pdEnterWarehouseDetails = pdEnterWarehouse.getPdEnterWarehouseDetails();
            pdEnterWarehouseDetails = pdEnterWarehouseDetailManager.getPdEnterWarehouseDetailsByCrm(crm, pager);
        } else {
            pdEnterWarehouse = new PdEnterWarehouse();
            
        }
        
        //控制
        String strAction = request.getParameter("strAction");
        
     
        if("checkPdEnterWarehouse".equals(strAction)){
        	checkFlag = "1";
        	checkButtonKey = "button.checked";
        	
        	disabled = true;
        }else if("confirmPdEnterWarehouse".equals(strAction)){
        	checkFlag = "2";
        	checkButtonKey = "operation.button.confirm";
        	disabled = true;
        	
        	
        }else if("editPdEnterWarehouse".equals(strAction)){
        	checkFlag = "0";
        	disabled = false;
        	if(pdEnterWarehouse.getOrderFlag()>=0){//已经初审的入库单不能编辑
        		disabled = true;
        	}
        }else if("searchPdEnterWarehouse".equals(strAction)){
        	disabled = true;
        }else if("addPdEnterWarehouse".equals(strAction)){
        	disabled = false;
        	checkButtonKey="button.next";
        }
        
        request.setAttribute("strAction",strAction);
        request.setAttribute("ewNo", ewNo);
        request.setAttribute("disabledFlag", disabled);
        request.setAttribute("checkFlag", checkFlag);
        request.setAttribute("companyReadabled", companyReadabled);
        request.setAttribute("checkButtonKey", checkButtonKey);
        request.setAttribute("pdEnterWarehouseDetailListTable_totalRows", pager.getTotalObjects());
        log.debug("disabledFlag="+disabled);
        request.setAttribute(Constants.PDENTERWAREHOUSEDETAIL_LIST, pdEnterWarehouseDetails);
        return pdEnterWarehouse;
    }

   
	public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        String strAction = request.getParameter("strAction");
        String view = getSuccessView();
        view="redirect:pdEnterWarehouses.html?strAction="+strAction;
        String key =null;
        List errorMsgs = new ArrayList();
        PdEnterWarehouse pdEnterWarehouse = (PdEnterWarehouse) command;
        boolean isNew = StringUtils.isEmpty(pdEnterWarehouse.getEwNo());
        Locale locale = request.getLocale();
//        if("checkInvoice".equals(strAction)){//
//        	checkInvoice(request,response);
//        	return new ModelAndView("redirect:editPdEnterWarehouse.html", "ewNo", pdEnterWarehouse.getEwNo());
//        }
        
        if("addPdEnterWarehouse".equals(strAction)){//新建
        	String ewNo= addPdEnterWarehouse(request,response,pdEnterWarehouse);
        	key = "pdEnterWarehouse.added";
        	view="redirect:editPdEnterWarehouse.html?strAction=editPdEnterWarehouse&ewNo="+ewNo;
//        	view="redirect:editPdEnterWarehouseDetail.html?strAction=addPdEnterWarehouseDetail&ewNo="+ewNo;
        }else if("checkPdEnterWarehouse".equals(strAction)){//审核
        	view="redirect:pdEnterWarehouses.html?strAction=checkPdEnterWarehouse";
        	if(pdEnterWarehouseManager.getProductCountByEwNo(pdEnterWarehouse.getEwNo())!=0){//
        		checkEnterWarehouse(request,response,pdEnterWarehouse);
        	}else{
        		errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		request.getSession().setAttribute("errorMsgs", errorMsgs);
        		view="redirect:editPdEnterWarehouse.html?strAction=checkPdEnterWarehouse&ewNo="+pdEnterWarehouse.getEwNo();
//				return showForm(request, response, errors);
        	}
        	
        	
        	key="pdEnterWarehouse.checked";
        }else if("confirmPdEnterWarehouse".equals(strAction)){//确认
        	confirmEnterWarehouse(request,response,pdEnterWarehouse);
        	key="pdEnterWarehouse.confirmed";
        	view="redirect:pdEnterWarehouses.html?strAction=confirmPdEnterWarehouse";

        	
        }else if("editPdEnterWarehouse".equals(strAction)){//编辑
        	
        	key="pdEnterWarehouse.hasChecked";
        	if(pdEnterWarehouse.getOrderFlag()<0){
        		key="pdEnterWarehouse.edit";
        		editEnterWarehouse(request,response,pdEnterWarehouse);
        	}
        	view="redirect:pdEnterWarehouses.html?strAction=editPdEnterWarehouse";
        }else if("deletePdEnterWarehouse".equals(strAction)){//删除
        	key="pdEnterWarehouse.canNotDelete";
        	if(pdEnterWarehouse.getOrderFlag()<0){
        		key="pdEnterWarehouse.deleted";
        		deleteEnterWarehouse(request,response,pdEnterWarehouse);
        	}
        	view="redirect:pdEnterWarehouses.html?strAction=editPdEnterWarehouse";
        	
        }else if("toNewPdEnterWarehouse".equals(strAction)){
        	key="error.cannotreturn";
        	if(pdEnterWarehouse.getOrderFlag()==0 || pdEnterWarehouse.getOrderFlag()==1){
        		
        		pdEnterWarehouse.setOrderFlag(-1);
        		key="operation.notice.tonew";
        		pdEnterWarehouseManager.savePdEnterWarehouse(pdEnterWarehouse);
        	}
        	view="redirect:pdEnterWarehouses.html?strAction=checkPdEnterWarehouse";
        }else if("submitPdEnterWarehouse".equals(strAction)){
        	view="redirect:pdEnterWarehouses.html?strAction=editPdEnterWarehouse";
        	key="pd.hasSubmited";
        	if(pdEnterWarehouseManager.getProductCountByEwNo(pdEnterWarehouse.getEwNo())!=0){//
        		editEnterWarehouse(request,response,pdEnterWarehouse);
        	}else{
        		errors.reject("pd.qtyMustBeMoreThanZero", new Object[] {},LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		errorMsgs.add(LocaleUtil.getLocalText("pd.qtyMustBeMoreThanZero"));
        		request.getSession().setAttribute("errorMsgs", errorMsgs);
        		view="redirect:editPdEnterWarehouse.html?strAction=editPdEnterWarehouse&ewNo="+pdEnterWarehouse.getEwNo();
//				return showForm(request, response, errors);
        	}
        	
        	
        	
        }
        
           
            request.setAttribute("ewNo", pdEnterWarehouse.getEwNo());
          
			saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key));      
       

        return new ModelAndView(view);
    }
    
    /**
     * 删除
     * @param request
     * @param response
     * @param pdEnterWarehouse
     */
    private void deleteEnterWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdEnterWarehouse pdEnterWarehouse) {
		// TODO Auto-generated method stub
    	pdEnterWarehouseManager.removePdEnterWarehouse(pdEnterWarehouse.getEwNo().toString());
	}
    /**
     *编辑
     * @param request
     * @param response
     * @param pdEnterWarehouse
     */
	private void editEnterWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdEnterWarehouse pdEnterWarehouse) {
		// TODO Auto-generated method stub
		String pageOrderFlag= request.getParameter("pageOrderFlag");
		pdEnterWarehouse.setOrderFlag(new Integer(pageOrderFlag));
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	pdEnterWarehouse.setCompanyCode(pdWarehouseManager.getPdWarehouse(pdEnterWarehouse.getWarehouseNo()).getCompanyCode());
    	pdEnterWarehouse.setEditTime(new Date());
    	pdEnterWarehouse.setEditUsrCode(sessionLogin.getUserCode());
    	pdEnterWarehouseManager.savePdEnterWarehouse(pdEnterWarehouse);
	}
	/**
	 * 确认
	 * @param request
	 * @param response
	 * @param pdEnterWarehouse
	 */
	private void confirmEnterWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdEnterWarehouse pdEnterWarehouse) {
		// TODO Auto-generated method stub
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	pdEnterWarehouse.setOrderFlag(2);
    	pdEnterWarehouse.setOkTime(new Date());
    	pdEnterWarehouse.setOkUsrCode(sessionLogin.getUserCode());
    	pdEnterWarehouseManager.confirmPdEnterWarehouse(pdEnterWarehouse);
    	
    	
    	
    	
	}
	/**
	 * 审核
	 * @param request
	 * @param response
	 * @param pdEnterWarehouse
	 */
	private void checkEnterWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdEnterWarehouse pdEnterWarehouse) {
		// TODO Auto-generated method stub
		SysUser sessionLogin = SessionLogin.getLoginUser(request);
		pdEnterWarehouse.setCheckUsrCode(sessionLogin.getUserCode());
    	pdEnterWarehouse.setOrderFlag(1);
    	pdEnterWarehouse.setCheckTime(new Date());
    	pdEnterWarehouseManager.savePdEnterWarehouse(pdEnterWarehouse);
	}
	
	/**
	 * 新建
	 * @param request
	 * @param response
	 * @param pdEnterWarehouse
	 * @return
	 */
	private String addPdEnterWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdEnterWarehouse pdEnterWarehouse) {
		// TODO Auto-generated method stub
		String pageOrderFlag= request.getParameter("pageOrderFlag");
    	SysUser sessionLogin = SessionLogin.getLoginUser(request);
    	pdEnterWarehouse.setEwNo(sysIdManager.buildIdStr("ewno"));
    	/*if(!"AA".equalsIgnoreCase(sessionLogin.getCompanyCode())){
    		pdEnterWarehouse.setCompanyCode(sessionLogin.getCompanyCode());
    	}*/
    
    	pdEnterWarehouse.setCompanyCode(pdWarehouseManager.getPdWarehouse(pdEnterWarehouse.getWarehouseNo()).getCompanyCode());
		pdEnterWarehouse.setCreateUsrCode(sessionLogin.getUserCode());
		pdEnterWarehouse.setAmount(new BigDecimal(0));
		pdEnterWarehouse.setCreateTime(new Date());
		pdEnterWarehouse.setOrderFlag(new Integer(pageOrderFlag));
//		pdEnterWarehouse.setFstatus("1");
//		pdEnterWarehouse.setRecheckStatus("1");
//		pdEnterWarehouse.setOkStatus("1");
		pdEnterWarehouse.setStockFlag("0");
//		pdEnterWarehouse.setEwDate(new Date());
		pdEnterWarehouseManager.savePdEnterWarehouse(pdEnterWarehouse);
		return pdEnterWarehouse.getEwNo();
	}
	
   
    
    
    
    
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	public void setPdEnterWarehouseDetailManager(
			PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager) {
		this.pdEnterWarehouseDetailManager = pdEnterWarehouseDetailManager;
	}
	public void setPdWarehouseStockTraceManager(
			PdWarehouseStockTraceManager pdWarehouseStockTraceManager) {
		this.pdWarehouseStockTraceManager = pdWarehouseStockTraceManager;
	}
	
}
