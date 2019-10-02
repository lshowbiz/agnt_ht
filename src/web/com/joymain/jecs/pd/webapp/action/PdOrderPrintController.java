package com.joymain.jecs.pd.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.pd.model.PdAdjustStock;
import com.joymain.jecs.pd.model.PdCheckstockOrder;
import com.joymain.jecs.pd.model.PdCombinationOrder;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.pd.model.PdOutWarehouse;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.service.PdAdjustStockManager;
import com.joymain.jecs.pd.service.PdCheckstockOrderManager;
import com.joymain.jecs.pd.service.PdCombinationOrderManager;
import com.joymain.jecs.pd.service.PdEnterWarehouseManager;
import com.joymain.jecs.pd.service.PdFlitWarehouseManager;
import com.joymain.jecs.pd.service.PdOutWarehouseManager;
import com.joymain.jecs.pd.service.PdReturnPurchaseManager;
import com.joymain.jecs.pd.service.PdSendInfoManager;
import com.joymain.jecs.po.model.JpoCounterOrder;
import com.joymain.jecs.po.service.JpoCounterOrderManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.webapp.action.BaseController;




public class PdOrderPrintController extends BaseController implements
		Controller {
	private PdSendInfoManager pdSendInfoManager =null;
	private PdOutWarehouseManager pdOutWarehouseManager =null;
	private JpoCounterOrderManager jpoCounterOrderManager;
	private PdReturnPurchaseManager pdReturnPurchaseManager;
	private PdAdjustStockManager pdAdjustStockManager;
	private PdFlitWarehouseManager pdFlitWarehouseManager;
	private PdEnterWarehouseManager pdEnterWarehouseManager;
	private PdCheckstockOrderManager pdCheckstockOrderManager;
	private PdCombinationOrderManager pdCombinationOrderManager;
	public void setPdCombinationOrderManager(
			PdCombinationOrderManager pdCombinationOrderManager) {
		this.pdCombinationOrderManager = pdCombinationOrderManager;
	}

	public void setPdCheckstockOrderManager(
			PdCheckstockOrderManager pdCheckstockOrderManager) {
		this.pdCheckstockOrderManager = pdCheckstockOrderManager;
	}

	public void setPdEnterWarehouseManager(
			PdEnterWarehouseManager pdEnterWarehouseManager) {
		this.pdEnterWarehouseManager = pdEnterWarehouseManager;
	}

	public void setPdFlitWarehouseManager(
			PdFlitWarehouseManager pdFlitWarehouseManager) {
		this.pdFlitWarehouseManager = pdFlitWarehouseManager;
	}

	public void setPdAdjustStockManager(PdAdjustStockManager pdAdjustStockManager) {
		this.pdAdjustStockManager = pdAdjustStockManager;
	}

	public void setPdReturnPurchaseManager(
			PdReturnPurchaseManager pdReturnPurchaseManager) {
		this.pdReturnPurchaseManager = pdReturnPurchaseManager;
	}

	public void setJpoCounterOrderManager(
			JpoCounterOrderManager jpoCounterOrderManager) {
		this.jpoCounterOrderManager = jpoCounterOrderManager;
	}

	public void setPdOutWarehouseManager(PdOutWarehouseManager pdOutWarehouseManager) {
		this.pdOutWarehouseManager = pdOutWarehouseManager;
	}

	public void setPdSendInfoManager(PdSendInfoManager pdSendInfoManager) {
		this.pdSendInfoManager = pdSendInfoManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strAction=request.getParameter("strAction");
		String view="";
		super.initPmProductMap(request);
		super.initStateCodeParem(request);
//		this.initAttributes(request);
		if("printSendInfo".equals(strAction)){
			view="pd/pdSendInfoPrint";
			printSendInfo(request,response);
		}else if("printOutWarehouse".equals(strAction)){
			view="pd/pdOutWarehousePrint";
			printOutWarehouse(request,response);
		}else if("printEnterWarehouse".equals(strAction)){
			view="pd/pdEnterWarehousePrint";
			printEnterWarehouse(request,response);
		}else if("printFlitWarehouse".equals(strAction)){
			view="pd/pdFlitWarehousePrint";
			printFlitWarehouse(request,response);
		}else if("printAdjustStock".equals(strAction)){
			view="pd/pdAdjustStockPrint";
			printAdjustStock(request,response);
		}else if("printReturnPurchase".equals(strAction)){
			view="pd/pdReturnPurchasePrint";
			printReturnPurchase(request,response);
		}else if("printPoCounterOrder".equals(strAction)){
			view="po/jpoCounterOrderPrint";
			printPoCounterOrder(request,response);
		}else if("printPdCheckstockOrder".equals(strAction)){
			view="pd/pdCheckstockOrderPrint";
			pdCheckstockOrder(request,response);
		}else if("printPdCombinationOrder".equals(strAction)){
			view="pd/pdCombinationOrderPrint";
			pdCombinationOrderPrint(request,response);
		}
		request.setAttribute("now", new Date());
		return new ModelAndView(view);
	}

	private void pdCombinationOrderPrint(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String orderNo = request.getParameter("orderNo");
		PdCombinationOrder pdCombinationOrder = pdCombinationOrderManager.getPdCombinationOrder(orderNo);
		request.setAttribute("pdCombinationOrder", pdCombinationOrder);
	}

	private void pdCheckstockOrder(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String orderNo = request.getParameter("orderNo");
		PdCheckstockOrder pdCheckstockOrder =pdCheckstockOrderManager.getPdCheckstockOrder(orderNo);
		request.setAttribute("pdCheckstockOrder", pdCheckstockOrder);
	}

	private void printPoCounterOrder(HttpServletRequest request,
			HttpServletResponse response) {
		String orderNo = request.getParameter("orderNo");
		JpoCounterOrder poCounterOrder = jpoCounterOrderManager.getJpoCounterOrder(orderNo);
		request.setAttribute("poCounterOrder", poCounterOrder);
	}

	private void printReturnPurchase(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String orderNo = request.getParameter("orderNo");
		PdReturnPurchase pdReturnPurchase =pdReturnPurchaseManager.getPdReturnPurchase(orderNo);
		request.setAttribute("pdReturnPurchase", pdReturnPurchase);
	}

	private void printAdjustStock(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String orderNo = request.getParameter("orderNo");
		PdAdjustStock pdAdjustStock =pdAdjustStockManager.getPdAdjustStock(orderNo);
		request.setAttribute("pdAdjustStock", pdAdjustStock);
	}

	private void printFlitWarehouse(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String orderNo = request.getParameter("orderNo");
		PdFlitWarehouse pdFlitWarehouse =pdFlitWarehouseManager.getPdFlitWarehouse(orderNo);
		request.setAttribute("pdFlitWarehouse", pdFlitWarehouse);
	}

	private void printEnterWarehouse(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String orderNo = request.getParameter("orderNo");
		PdEnterWarehouse pdEnterWarehouse = pdEnterWarehouseManager.getPdEnterWarehouse(orderNo);
		request.setAttribute("pdEnterWarehouse", pdEnterWarehouse);
	}

	private void printOutWarehouse(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String orderNo = request.getParameter("orderNo");
		PdOutWarehouse pdOutWarehouse = pdOutWarehouseManager.getPdOutWarehouse(orderNo);
		
		/*Double weight = new Double(0) ;
		Double volume = new Double(0) ;
		Set sets = pdOutWarehouse.getPdOutWarehouseDetails();
		Iterator iterator =sets.iterator();
		
		while(iterator.hasNext()){
			PdOutWarehouseDetail pdOutWarehouseDetail = (PdOutWarehouseDetail) iterator.next();
			PmProduct pmProduct = pmProductManager.getPmProduct(pdOutWarehouseDetail.getProductNo());
			
			weight +=pdOutWarehouseDetail.getQty() * pmProduct.getWeight();
			volume +=pdOutWarehouseDetail.getQty() * pmProduct.getBulk();
		}
		request.setAttribute("weight", weight);
		request.setAttribute("volume", volume);*/
		
		request.setAttribute("pdOutWarehouse", pdOutWarehouse);
	}

	private void printSendInfo(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
//		String orderNo = request.getParameter("orderNo");
//		PdSendInfo pdSendInfo = pdSendInfoManager.getPdSendInfo(orderNo);
		
		/*Double weight = new Double(0) ;
		Double volume = new Double(0) ;
		Set sets = pdSendInfo.getPdSendInfoDetails();
		Iterator iterator =sets.iterator();
		
		while(iterator.hasNext()){
			PdSendInfoDetail pdSendInfoDetail = (PdSendInfoDetail) iterator.next();
			PmProduct pmProduct = pmProductManager.getPmProduct(pdSendInfoDetail.getPmProduct());
			
			weight +=pdSendInfoDetail.getQty() * pdSendInfoDetail.getPmProduct().getWeight();
			volume +=pdSendInfoDetail.getQty() * pdSendInfoDetail.getPmProduct().getBulk();
		}*/
//		request.setAttribute("weight", weight);
//		request.setAttribute("volume", volume);
		String siNo = request.getParameter("siNo"); 
//		log.info("======……siNo:"+siNo); 
		List pdSendInfoList = new ArrayList(); 
    	CommonRecord crm = new CommonRecord();
    	try {
			crm.setValue("siNo",siNo); 
		} catch (Exception e) { 
			e.printStackTrace();
		}
    	pdSendInfoList = pdSendInfoManager.getPdSendInfosByCrm(crm, null );
    	    	   
//    	log.info("======siNo:"+siNo);
//    	log.info("======pdSendInfoList:"+pdSendInfoList.size()); 
    	
    	request.setAttribute("pdSendInfoList", pdSendInfoList);
	
//		request.setAttribute("pdSendInfo", pdSendInfo);
	}

	
}
