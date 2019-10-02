package com.joymain.jecs.pd.webapp.action;

import java.io.IOException;
import java.io.InputStream;
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

import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.service.PdFlitWarehouseDetailManager;
import com.joymain.jecs.pd.service.PdFlitWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.service.JpmProductManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

public class PdFlitWarehouseUploadFormController extends BaseFormController {
	private PdFlitWarehouseManager pdFlitWarehouseManager = null;
	private PdFlitWarehouseDetailManager pdFlitWarehouseDetailManager = null;
	private SysIdManager sysIdManager = null;
	private PdWarehouseManager pdWarehouseManager = null;
	private PdWarehouseStockManager pdWarehouseStockManager = null;
	private JpmProductManager jpmProductManager = null;
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

	
	

	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	public PdFlitWarehouseUploadFormController() {
		setCommandName("pdFlitWarehouse");
		setCommandClass(PdFlitWarehouse.class);
		
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		super.initPmProductMap(request);
		String fwNo = request.getParameter("fwNo");
		PdFlitWarehouse pdFlitWarehouse = null;
		if (!StringUtils.isEmpty(fwNo)) {
			pdFlitWarehouse = pdFlitWarehouseManager.getPdFlitWarehouse(fwNo);
			
		} else {
			pdFlitWarehouse = new PdFlitWarehouse();
		}
	
		return pdFlitWarehouse;
	}

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'onSubmit' method...");
		}

		 SysUser sessionLogin = SessionLogin.getLoginUser(request);
	        String strAction = request.getParameter("strAction");
	        String view = "redirect:pdFlitWarehouses.html?strAction="+strAction;
	        String key =null;
	        
	        PdFlitWarehouse pdFlitWarehouse = (PdFlitWarehouse) command;
	        
	        List errorMsgs = new ArrayList();
	        boolean isNew = (StringUtils.isEmpty(pdFlitWarehouse.getFwNo()));
	        Locale locale = request.getLocale();
	        log.info("isNew="+isNew);
	        if("uploadPdFlitWarehouse".equals(strAction)){
	        	key="button.next";
	        	String fwNo=addPdFlitWarehouse(request,response,pdFlitWarehouse);
	        	view ="redirect:editPdFlitWarehouse.html?strAction=editPdFlitWarehouse&fwNo="+fwNo;
//	        	view ="redirect:editPdFlitWarehouseDetail.html?strAction=addPdFlitWarehouseDetail&fwNo="+fwNo;
	        }
	        
	        
	        
	       
	        saveMessage(request, getText(sessionLogin.getDefCharacterCoding(),key));
	        return new ModelAndView(view);
	}

	
	private String addPdFlitWarehouse(HttpServletRequest request,
			HttpServletResponse response, PdFlitWarehouse pdFlitWarehouse) throws IOException, BiffException {
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
		
		/**
		 * 通过导入的excel生成明细
		*/
		Set<PdFlitWarehouseDetail> pdFlitWarehouseDetails = new HashSet();
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
    			PdFlitWarehouseDetail detail = new PdFlitWarehouseDetail();
    			detail.setFwNo(pdFlitWarehouse.getFwNo());
    			detail.setProductNo(productNo);
    			detail.setQty(Long.valueOf(qty));
    			pdFlitWarehouseDetails.add(detail);
    			
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
        pdFlitWarehouse.setPdFlitWarehouseDetails(pdFlitWarehouseDetails);
		pdFlitWarehouseManager.savePdFlitWarehouse(pdFlitWarehouse);
		request.setAttribute("errorMsgs", errorMsgs);
		return pdFlitWarehouse.getFwNo();
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
