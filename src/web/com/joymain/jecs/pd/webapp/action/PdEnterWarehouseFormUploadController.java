package com.joymain.jecs.pd.webapp.action;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.model.PdEnterWarehouseDetail;
import com.joymain.jecs.pd.service.PdEnterWarehouseDetailManager;
import com.joymain.jecs.pd.service.PdEnterWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseStockTraceManager;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdEnterWarehouseFormUploadController extends BaseFormController {
	private PdWarehouseStockTraceManager pdWarehouseStockTraceManager = null;
    private PdEnterWarehouseManager pdEnterWarehouseManager = null;
    private SysIdManager sysIdManager = null;
    private PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager = null;
    private PdWarehouseManager pdWarehouseManager = null;
    private JpmProductManager jpmProductManager = null;
    public void setPdWarehouseStockTraceManager(
			PdWarehouseStockTraceManager pdWarehouseStockTraceManager) {
		this.pdWarehouseStockTraceManager = pdWarehouseStockTraceManager;
	}
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}
	public void setPdEnterWarehouseDetailManager(
			PdEnterWarehouseDetailManager pdEnterWarehouseDetailManager) {
		this.pdEnterWarehouseDetailManager = pdEnterWarehouseDetailManager;
	}
	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}
	public void setPdWarehouseManager(PdWarehouseManager pdWarehouseManager) {
		this.pdWarehouseManager = pdWarehouseManager;
	}
	public void setPdEnterWarehouseManager(PdEnterWarehouseManager pdEnterWarehouseManager) {
        this.pdEnterWarehouseManager = pdEnterWarehouseManager;
    }
    public PdEnterWarehouseFormUploadController() {
        setCommandName("pdEnterWarehouse");
        setCommandClass(PdEnterWarehouse.class);
    }
    
    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	super.initPmProductMap(request);
        String ewNo = request.getParameter("ewNo");
        PdEnterWarehouse pdEnterWarehouse = null;
        SysUser sessionLogin = SessionLogin.getLoginUser(request);
        CommonRecord crm = RequestUtil.toCommonRecord(request);
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
        System.out.println("PdEnterWarehouseFormUploadController -- formObject--:");
//        return new ModelAndView("pd/pdEnterWarehouseFormUpload", "pdEnterWarehouseFormUpload", pdEnterWarehouse);
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
        
        if("uploadPdEnterWarehouse".equals(strAction)){//新建
        	String ewNo= addPdEnterWarehouse(request,response,pdEnterWarehouse);
        	key = "pdEnterWarehouse.added";
        	view="redirect:editPdEnterWarehouse.html?strAction=editPdEnterWarehouse&ewNo="+ewNo;
//        	view="redirect:editPdEnterWarehouseDetail.html?strAction=addPdEnterWarehouseDetail&ewNo="+ewNo;
        }
        
           
            request.setAttribute("ewNo", pdEnterWarehouse.getEwNo());
          
			saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key));      
			System.out.println("PdEnterWarehouseFormUploadController---onsubmit--:view:" + view);

        return new ModelAndView(view);
    }
	
	
	private String addPdEnterWarehouse(HttpServletRequest request,
		HttpServletResponse response, PdEnterWarehouse pdEnterWarehouse) throws IOException, BiffException {
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
//		pdEnterWarehouseManager.savePdEnterWarehouse(pdEnterWarehouse);
	
		/**
		 * 通过导入的excel生成明细
		*/
		Set<PdEnterWarehouseDetail> pdEnterWarehouseDetails = new HashSet();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
		
		InputStream stream = file.getInputStream();
		Workbook wb = Workbook.getWorkbook(stream);
		Sheet sheet1 = wb.getSheet(0); 
		
		Cell cell = null;
		int row = sheet1.getRows();//总行数
		int col = sheet1.getColumns();//总列数
		List errorMsgs = new ArrayList();
		
		for(int i=1;i<row;i++){
			Cell[] column =sheet1.getRow(i);
			try {
				String productNo = column[0].getContents();
				String qty = column[1].getContents();
				if(!jpmProductManager.checkProductNo(productNo)){
					errorMsgs.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+",此商品不存在");
					continue;
				}
				if(!StringUtil.isInteger(qty)){
					errorMsgs.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+",数量不是整数");
					continue;
				}
				PdEnterWarehouseDetail detail = new PdEnterWarehouseDetail();
				detail.setEwNo(pdEnterWarehouse.getEwNo());
				detail.setProductNo(productNo);
				detail.setQty(Long.valueOf(qty));
				pdEnterWarehouseDetails.add(detail);
				
			} catch (AppException e) {
				// TODO Auto-generated catch block
				log.info("e="+e.getMessage());
				errorMsgs.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("e="+e.getMessage());
				errorMsgs.add(LocaleUtil.getLocalText("notice.row.number", new Object[]{i})+LocaleUtil.getLocalText(e.getMessage()));
			}
		//	
		}
		pdEnterWarehouse.setPdEnterWarehouseDetails(pdEnterWarehouseDetails);
		pdEnterWarehouseManager.savePdEnterWarehouse(pdEnterWarehouse);
		request.setAttribute("errorMsgs", errorMsgs);
		return pdEnterWarehouse.getEwNo();
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
