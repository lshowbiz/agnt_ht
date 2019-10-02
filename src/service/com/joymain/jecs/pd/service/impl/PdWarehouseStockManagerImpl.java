package com.joymain.jecs.pd.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import service.MsgSendService;

import com.joymain.jecs.Constants;
import com.joymain.jecs.pd.dao.PdWarehouseDao;
import com.joymain.jecs.pd.dao.PdWarehouseStockDao;
import com.joymain.jecs.pd.dao.PdWarehouseStockTraceDao;
import com.joymain.jecs.pd.model.PdAdjustStock;
import com.joymain.jecs.pd.model.PdAdjustStockDetail;
import com.joymain.jecs.pd.model.PdCombinationDetail;
import com.joymain.jecs.pd.model.PdCombinationOrder;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.model.PdEnterWarehouseDetail;
import com.joymain.jecs.pd.model.PdExchangeOrder;
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.pd.model.PdFlitWarehouse;
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.pd.model.PdInquiryMechanism;
import com.joymain.jecs.pd.model.PdOutWarehouse;
import com.joymain.jecs.pd.model.PdOutWarehouseDetail;
import com.joymain.jecs.pd.model.PdReturnPurchase;
import com.joymain.jecs.pd.model.PdReturnPurchaseDetail;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.PdSendInfoDetail;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.model.PdWarehouseStock;
import com.joymain.jecs.pd.model.PdWarehouseStockTrace;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoCounterOrder;
import com.joymain.jecs.po.model.JpoCounterOrderDetail;
import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.JocsInterfaceLog;
import com.joymain.jecs.util.ConfigUtil;
import com.joymain.jecs.util.ReportLogUtilService;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.exception.AppListException;
import com.joymain.jecs.util.string.StringUtil;

public class PdWarehouseStockManagerImpl extends BaseManager implements
		PdWarehouseStockManager {

	private PdWarehouseStockDao dao;
	private PdWarehouseDao pdWarehouseDao;
	private PdWarehouseStockTraceDao pdWarehouseStockTraceDao;
	private JpmProductSaleManager jpmProductSaleManager;

	public void setJpmProductSaleManager(
			JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	public void updatePdWarehouseStock(PdEnterWarehouse pdEnterWarehouse) {
		// TODO Auto-generated method stub
		log.info("-->入库单 更新库存");
		Set sets = pdEnterWarehouse.getPdEnterWarehouseDetails();
		Iterator iterator = sets.iterator();
		String warehouseNo = pdEnterWarehouse.getWarehouseNo();
	
		// PdWarehouseStockTrace pdWarehouseStockTrace = new
		// PdWarehouseStockTrace();
	
		while (iterator.hasNext()) {
			/* trace begain */
			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("1");
			pdWarehouseStockTrace.setRemark(pdEnterWarehouse.getRemark() + ".."
					+ pdEnterWarehouse.getCheckRemark() + ".."
					+ pdEnterWarehouse.getOkRemark());
			/* trace end */
			PdEnterWarehouseDetail pdEnterWarehouseDetail = new PdEnterWarehouseDetail();
			PdWarehouseStock pdWarehouseStock = null;
			pdEnterWarehouseDetail = (PdEnterWarehouseDetail) iterator.next();
			pdWarehouseStock = dao.getPdWarehouseStockByProductNo(warehouseNo,
					pdEnterWarehouseDetail.getProductNo());
			if (pdWarehouseStock == null) {
				pdWarehouseStock = new PdWarehouseStock();
				pdWarehouseStock.setCompanyCode(pdEnterWarehouse
						.getCompanyCode());
				pdWarehouseStock.setProductNo(pdEnterWarehouseDetail
						.getProductNo());
				pdWarehouseStock.setPdWarehouse(pdWarehouseDao
						.getPdWarehouse(pdEnterWarehouse.getWarehouseNo()));
				// pdWarehouseStock.setProductNo(pdEnterWarehouseDetail.getProductNo());
	
			}
	
			// log
			/* trace begain */
			pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
			/* trace end */
	
			pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
					+ pdEnterWarehouseDetail.getQty());
	
			if (pdWarehouseStock.getNormalQty() < 0) {
				throw new AppException("pd.notEnoughStock");
			}
			dao.savePdWarehouseStock(pdWarehouseStock);
			// pdWarehouseStock=pdWarehouseStockDao.getPdWarehouseStock(uniNo);
	
			/* trace begain */
	
			pdWarehouseStockTrace.setPdWarehouse(pdWarehouseDao
					.getPdWarehouse(pdEnterWarehouse.getWarehouseNo()));
			pdWarehouseStockTrace.setProductNo(pdEnterWarehouseDetail
					.getProductNo());
			// pdWarehouseStockTrace.setPmProduct(pmProductManager.getPmProduct(pdEnterWarehouseDetail.getProductNo()));
			pdWarehouseStockTrace.setChangeQty(pdEnterWarehouseDetail.getQty());
			pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
	
			pdWarehouseStockTrace.setOrderNo(pdEnterWarehouse.getEwNo());
			pdWarehouseStockTrace.setOperatorCode(pdEnterWarehouse
					.getOkUsrCode());
	
			pdWarehouseStockTrace.setCreateTime(new Date());
	
			// 新增分公司分组(按仓库所属分公司)
			pdWarehouseStockTrace.setCompanyCode(pdEnterWarehouse
					.getCompanyCode());
			// 新增分类
			pdWarehouseStockTrace.setActionType("1");
			pdWarehouseStockTraceDao
					.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			/* trace end */
	
		}
	}

	public void updatePdWarehouseStock(PdOutWarehouse pdOutWarehouse) {
		// TODO Auto-generated method stub
	
		log.info("-->出库单 更新库存");
		Set sets = pdOutWarehouse.getPdOutWarehouseDetails();
		Iterator iterator = sets.iterator();
		String warehouseNo = pdOutWarehouse.getWarehouseNo();
		PdWarehouse pdWarehouse = pdWarehouseDao.getPdWarehouse(warehouseNo);
	
		while (iterator.hasNext()) {
	
			/* trace begain */
			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("2");
			pdWarehouseStockTrace.setSubType(pdOutWarehouse.getOwtNo());
			pdWarehouseStockTrace.setRemark(pdOutWarehouse.getRemark() + ".."
					+ pdOutWarehouse.getCheckRemark() + ".."
					+ pdOutWarehouse.getOkRemark());
			/* trace end */
			PdOutWarehouseDetail pdOutWarehouseDetail = new PdOutWarehouseDetail();
			PdWarehouseStock pdWarehouseStock = null;
			pdOutWarehouseDetail = (PdOutWarehouseDetail) iterator.next();
	
			/*
			 * if (!enoughStock(pdOutWarehouse.getWarehouseNo(),
			 * pdOutWarehouseDetail.getProductNo(), pdOutWarehouseDetail
			 * .getQty())) { throw new AppException("pd.notEnoughStock"); }
			 */
			pdWarehouseStock = dao.getPdWarehouseStockByProductNo(warehouseNo,
					pdOutWarehouseDetail.getProductNo());
			if (pdWarehouseStock == null) {
				pdWarehouseStock = new PdWarehouseStock();
				pdWarehouseStock.setProductNo(pdOutWarehouseDetail
						.getProductNo());
				// pdWarehouseStock.setPmProduct(pmProductManager.getPmProduct(pdOutWarehouseDetail.getProductNo()));
				// pdWarehouseStock.setWarehouseNo(warehouseNo);
				pdWarehouseStock.setPdWarehouse(pdWarehouse);
				pdWarehouseStock.setCompanyCode(pdWarehouse.getCompanyCode());
			}
			/* trace begain */
			pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
			/* trace end */
			pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
					- pdOutWarehouseDetail.getQty());
			if (pdWarehouseStock.getNormalQty() < 0) {
				throw new AppException("pd.notEnoughStock");
			}
			dao.savePdWarehouseStock(pdWarehouseStock);
			// pdWarehouseStock=pdWarehouseStockDao.getPdWarehouseStock(uniNo);
	
			/* trace begain */
			pdWarehouseStockTrace.setPdWarehouse(pdWarehouse);
			pdWarehouseStockTrace.setProductNo(pdOutWarehouseDetail
					.getProductNo());
			// pdWarehouseStockTrace.setPmProduct(pmProductManager.getPmProduct(pdOutWarehouseDetail.getProductNo()));
			pdWarehouseStockTrace.setChangeQty(-pdOutWarehouseDetail.getQty());
			pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
			/*
			 * pdWarehouseStockTrace.setAction("outOfStorage");
			 * pdWarehouseStockTrace.setOrderType("pdOutWarehouse");
			 */
			pdWarehouseStockTrace.setOrderNo(pdOutWarehouse.getOwNo());
			pdWarehouseStockTrace
					.setOperatorCode(pdOutWarehouse.getOkUsrCode());
	
			pdWarehouseStockTrace.setCreateTime(new Date());
			// 新增分公司分组(按仓库所属分公司)
			pdWarehouseStockTrace.setCompanyCode(pdWarehouse.getCompanyCode());
			// 新增分类
			pdWarehouseStockTrace.setActionType("6");
	
			pdWarehouseStockTraceDao
					.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			/* trace end */
		}
	
	}

	public void updatePdWarehouseStock(PdAdjustStock pdAdjustStock) {
		// TODO Auto-generated method stub
		log.info("-->异动单 更新库存");
		Set sets = pdAdjustStock.getPdAdjustStockDetails();
		Iterator iterator = sets.iterator();
		String warehouseNo = pdAdjustStock.getWarehouseNo();
	
		while (iterator.hasNext()) {
			PdAdjustStockDetail pdAdjustStockDetail = new PdAdjustStockDetail();
	
			/* trace begain */
			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("3");
			pdWarehouseStockTrace.setSubType(pdAdjustStock.getAstNo());
			pdWarehouseStockTrace.setRemark(pdAdjustStock.getRemark() + ".."
					+ pdAdjustStock.getCheckRemark() + ".."
					+ pdAdjustStock.getOkRemark());
			/* trace end */
			PdWarehouseStock pdWarehouseStock = null;
			pdAdjustStockDetail = (PdAdjustStockDetail) iterator.next();
			PdWarehouse pdWarehouse = pdWarehouseDao
					.getPdWarehouse(warehouseNo);
			// if(!enoughStock(pdAdjustStock.getWarehouseNo(),
			// pdAdjustStockDetail.getProductNo(),
			// -pdAdjustStockDetail.getQty())){
			// throw new AppException("pd.notEnoughStock");
			// }
			pdWarehouseStock = dao.getPdWarehouseStockByProductNo(warehouseNo,
					pdAdjustStockDetail.getProductNo());
			if (pdWarehouseStock == null) {
				pdWarehouseStock = new PdWarehouseStock();
	
				pdWarehouseStock.setProductNo(pdAdjustStockDetail
						.getProductNo());
				// pdWarehouseStock.setPmProduct(pmProductManager.getPmProduct(pdAdjustStockDetail.getProductNo()));
				pdWarehouseStock.setPdWarehouse(pdWarehouse);
				pdWarehouseStock.setCompanyCode(pdAdjustStock.getCompanyCode());
			}
	
			/* trace begain */
			pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
			/* trace end */
			pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
					+ pdAdjustStockDetail.getQty());
	
			/*if (pdWarehouseStock.getNormalQty() < 0) {
				throw new AppException("pd.notEnoughStock");
			}*/
	
			dao.savePdWarehouseStock(pdWarehouseStock);
			// pdWarehouseStock=pdWarehouseStockDao.getPdWarehouseStock(uniNo);
			/* trace begain */
			pdWarehouseStockTrace.setPdWarehouse(pdWarehouse);
			pdWarehouseStockTrace.setProductNo(pdAdjustStockDetail
					.getProductNo());
			pdWarehouseStockTrace.setChangeQty(pdAdjustStockDetail.getQty());
			pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
			pdWarehouseStockTrace.setOrderNo(pdAdjustStock.getAsNo());
			pdWarehouseStockTrace.setOperatorCode(pdAdjustStock.getOkUsrCode());
			/*
			 * pdWarehouseStockTrace.setAction("unnormalChange");
			 * pdWarehouseStockTrace.setOrderType("pdAdjustStock");
			 * 
			 * pdWarehouseStockTrace.setSysUser(null);
			 * pdWarehouseStockTrace.setUsrNo(pdAdjustStock.getOkUsrNo());
			 */
			pdWarehouseStockTrace.setCreateTime(new Date());
	
			// 新增分公司分组(按仓库所属分公司)
			pdWarehouseStockTrace.setCompanyCode(pdWarehouse.getCompanyCode());
			// 新增分类
			if (pdAdjustStockDetail.getQty() > 0) {// 异动入
				pdWarehouseStockTrace.setActionType("3");
			} else {// 异动出
				pdWarehouseStockTrace
						.setSubType("-" + pdAdjustStock.getAstNo());
				pdWarehouseStockTrace.setActionType("8");
			}
	
			pdWarehouseStockTraceDao
					.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			/* trace end */
	
		}
	}

	public void updatePdWarehouseStock(PdSendInfo pdSendInfo) {
		// TODO Auto-generated method stub
		Set sets = pdSendInfo.getPdSendInfoDetails();
		Iterator iterator = sets.iterator();
		String warehouseNo = pdSendInfo.getWarehouseNo();
	
		while (iterator.hasNext()) {
			/* trace begain */
			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("4");
			pdWarehouseStockTrace.setSubType(pdSendInfo.getOrderType());
			pdWarehouseStockTrace.setCustomCode(pdSendInfo.getCustomer()
					.getUserCode());
			pdWarehouseStockTrace.setRemark(pdSendInfo.getRemark() + ".."
					+ pdSendInfo.getCheckRemark() + ".."
					+ pdSendInfo.getOkRemark());
			/* trace end */
			PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
	
			PdWarehouseStock pdWarehouseStock = null;
			pdSendInfoDetail = (PdSendInfoDetail) iterator.next();
			
			String OkUsrCode = pdSendInfo.getOkUsrCode();
			//modify fu 2015-11-19 接口发货确认的话，就不校验库存
			if((!StringUtil.isEmpty(OkUsrCode))&& ("interface".equals(OkUsrCode))){
			}
			//modify fu 2015-11-19  非接口发货确认的话，就校验库存
			else{
				//modify by fu 2015-09-23 经需求重新定义，发货单改库存，但是不判断库存是否足够
				//modify fu 2015-10-21 因WMS未上线，EC需要校验库存，所以这部分先放开，等后期在完善
				if (!enoughStock(pdSendInfo.getWarehouseNo(), pdSendInfoDetail
						.getProductNo(), pdSendInfoDetail.getQty())) {
					throw new AppException("pd.notEnoughStock");
				}
			 }
			pdWarehouseStock = dao.getPdWarehouseStockByProductNo(warehouseNo,
					pdSendInfoDetail.getProductNo());
			if (pdWarehouseStock == null) {
				pdWarehouseStock = new PdWarehouseStock();
				pdWarehouseStock.setProductNo(pdSendInfoDetail.getProductNo());
				// pdWarehouseStock.setPmProduct(pdSendInfoDetail.getPmProduct());
				pdWarehouseStock.setPdWarehouse(pdWarehouseDao
						.getPdWarehouse(warehouseNo));
				pdWarehouseStock.setCompanyCode(pdSendInfo.getCompanyCode());
			}
	
			/* trace begain */
			pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
			/* trace end */
			pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
					- pdSendInfoDetail.getQty());
	
			dao.savePdWarehouseStock(pdWarehouseStock);
			// pdWarehouseStock=pdWarehouseStockDao.getPdWarehouseStock(uniNo);
	
			/* trace begain */
			pdWarehouseStockTrace.setPdWarehouse(pdWarehouseDao
					.getPdWarehouse(warehouseNo));
			pdWarehouseStockTrace.setProductNo(pdSendInfoDetail.getProductNo());
			pdWarehouseStockTrace.setChangeQty(-pdSendInfoDetail.getQty()
					.longValue());
			pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
	
			pdWarehouseStockTrace.setOrderNo(pdSendInfo.getSiNo());
			pdWarehouseStockTrace.setOperatorCode(pdSendInfo.getOkUsrCode());
	
			pdWarehouseStockTrace.setCreateTime(new Date());
	
			// 新增分公司分组(按仓库所属分公司)
			pdWarehouseStockTrace.setCompanyCode(pdSendInfo.getCompanyCode());
			// 新增分类
			pdWarehouseStockTrace.setActionType("5");
			if (pdWarehouseStockTrace.getChangeQty() != 0) {
				pdWarehouseStockTraceDao
						.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			}
		}
	}

	public void updatePdWarehouseStock(PdCombinationOrder pdCombinationOrder) {
		// TODO Auto-generated method stub
		List erroList = new ArrayList();
		Set sets = pdCombinationOrder.getPdCombinationDetails();
	
		Iterator iterator = sets.iterator();
		while (iterator.hasNext()) {
			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("5");
			pdWarehouseStockTrace.setRemark(pdCombinationOrder.getRemark()
					+ ".." + pdCombinationOrder.getCheckRemark() + ".."
					+ pdCombinationOrder.getOkRemark());
			PdCombinationDetail pdCombinationDetail = new PdCombinationDetail();
	
			PdWarehouseStock pdWarehouseStock = new PdWarehouseStock();
			pdCombinationDetail = (PdCombinationDetail) iterator.next();
	
			pdWarehouseStock = dao.getPdWarehouseStockByProductNo(
					pdCombinationOrder.getWarehouseNo(), pdCombinationDetail
							.getProductNo());
	
			if (pdWarehouseStock == null) {
				pdWarehouseStock = new PdWarehouseStock();
	
				pdWarehouseStock.setProductNo(pdCombinationDetail
						.getProductNo());
				// pdWarehouseStock.setPmProduct(pmProductManager.getPmProduct(pdAdjustStockDetail.getProductNo()));
				pdWarehouseStock.setPdWarehouse(pdWarehouseDao
						.getPdWarehouse(pdCombinationOrder.getWarehouseNo()));
				pdWarehouseStock.setCompanyCode(pdCombinationOrder
						.getCompanyCode());
			}
	
			/* trace begain */
			pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
			/* trace end */
			pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
					+ pdCombinationDetail.getQty());
	
			//modify fu 2016-1-5 OA通知去掉商品组合单的库存校验  modify by fu 2017-1-18 安杰玛系统重新添加商品组合单的库存校验
			if (pdWarehouseStock.getNormalQty() < 0) {
				erroList.add(pdWarehouseStock.getProductNo());
				// throw new AppException("pd.notEnoughStock");
			}
	
			/*
			 * if(poCounterOrder.getCsStatus() == 3 ){
			 * pdWarehouseStock.setNormalQty
			 * (pdWarehouseStock.getNormalQty()+jpoCounterOrderDetail.getQty());
			 * }else{ if (!enoughStock(poCounterOrder.getWarehouseNo(),
			 * jpoCounterOrderDetail.getJpmProductSale().getJpmProduct()
			 * .getProductNo(), jpoCounterOrderDetail.getQty())) { throw new
			 * AppException("pd.notEnoughStock"); }
			 * pdWarehouseStock.setNormalQty
			 * (pdWarehouseStock.getNormalQty()-jpoCounterOrderDetail.getQty());
			 * }
			 */
	
			dao.savePdWarehouseStock(pdWarehouseStock);
			// pdWarehouseStock=pdWarehouseStockDao.getPdWarehouseStock(uniNo);
			/* trace begain */
			pdWarehouseStockTrace.setPdWarehouse(pdWarehouseDao
					.getPdWarehouse(pdCombinationOrder.getWarehouseNo()));
			pdWarehouseStockTrace.setProductNo(pdCombinationDetail
					.getProductNo());
			pdWarehouseStockTrace.setChangeQty(pdCombinationDetail.getQty());
			/*
			 * if(poCounterOrder.getCsStatus() == 3 ){
			 * pdWarehouseStockTrace.setChangeQty
			 * (jpoCounterOrderDetail.getQty().longValue());
			 * 
			 * }else{
			 * pdWarehouseStockTrace.setChangeQty(-jpoCounterOrderDetail.getQty
			 * ().longValue()); }
			 */
	
			pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
			pdWarehouseStockTrace.setOrderNo(pdCombinationOrder.getPcNo());
			pdWarehouseStockTrace.setOperatorCode(pdCombinationOrder
					.getOkUsrCode());
			/*
			 * pdWarehouseStockTrace.setAction("unnormalChange");
			 * pdWarehouseStockTrace.setOrderType("pdAdjustStock");
			 * 
			 * pdWarehouseStockTrace.setSysUser(null);
			 * pdWarehouseStockTrace.setUsrNo(pdAdjustStock.getOkUsrNo());
			 */
			pdWarehouseStockTrace.setCreateTime(new Date());
	
			// 新增分公司分组(按仓库所属分公司)
			pdWarehouseStockTrace.setCompanyCode(pdCombinationOrder
					.getCompanyCode());
			// 新增分类
	
			// pdWarehouseStockTrace.setActionType("17");
			if (pdCombinationDetail.getQty() > 0) {// 异动入
				pdWarehouseStockTrace.setActionType("9");
				pdWarehouseStockTrace.setSubType("1");
			} else {// 异动出
				pdWarehouseStockTrace.setActionType("-9");
				pdWarehouseStockTrace.setSubType("-1");
			}
	
			pdWarehouseStockTraceDao
					.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			/* trace end */
	
		}
		//modify fu 2016-1-5 OA通知去掉商品组合单的库存校验 modify by fu 2017-1-18 安杰玛系统重新添加商品组合单的库存校验
		if (erroList.size() > 0) {
			throw new AppListException("pd.notEnoughStock", erroList);
		}
	
	}

	public void updatePdWarehouseStock(PdReturnPurchase pdReturnPurchase) {
		// TODO Auto-generated method stub
		log.info("-->发货退货单 更新库存");
		Set sets = pdReturnPurchase.getPdReturnPurchaseDetails();
		log.info("sets=" + sets.size());
		Iterator iterator = sets.iterator();
	
		// PdReturnPurchaseDetail pdReturnPurchaseDetail = new
		// PdReturnPurchaseDetail();
		// PdWarehouseStock pdWarehouseStock = null;
	
		while (iterator.hasNext()) {
			/* trace begain */
			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("6");
			pdWarehouseStockTrace.setCustomCode(pdReturnPurchase.getCustomer()
					.getUserCode());
			pdWarehouseStockTrace.setRemark(pdReturnPurchase.getRemark() + ".."
					+ pdReturnPurchase.getCheckRemark() + ".."
					+ pdReturnPurchase.getOkRemark());
			/* trace end */
			PdReturnPurchaseDetail pdReturnPurchaseDetail = (PdReturnPurchaseDetail) iterator
					.next();
	
			PdWarehouseStock pdWarehouseStock = new PdWarehouseStock();
			pdWarehouseStock = dao.getPdWarehouseStockByProductNo(
					pdReturnPurchase.getReturnWarehouseNo(),
					pdReturnPurchaseDetail.getProductNo());
	
			if (pdWarehouseStock == null) {
				pdWarehouseStock = new PdWarehouseStock();
				pdWarehouseStock.setProductNo(pdReturnPurchaseDetail
						.getProductNo());
				// pdWarehouseStock.setPmProduct(pmProductManager.getPmProduct(pdReturnPurchaseDetail.getProductNo()));
				pdWarehouseStock
						.setPdWarehouse(pdWarehouseDao
								.getPdWarehouse(pdReturnPurchase
										.getReturnWarehouseNo()));
				pdWarehouseStock.setCompanyCode(pdReturnPurchase
						.getCompanyCode());
			}
			// log.info("pdReturnPurchaseDetail.getQty()="+pdReturnPurchaseDetail.getQty());
			pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
			pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
					+ pdReturnPurchaseDetail.getQty());
			
			// 增加trace
	
			pdWarehouseStockTrace.setPdWarehouse(pdWarehouseDao
					.getPdWarehouse(pdReturnPurchase.getReturnWarehouseNo()));
			pdWarehouseStockTrace.setProductNo(pdReturnPurchaseDetail
					.getProductNo());
	
			pdWarehouseStockTrace.setChangeQty(pdReturnPurchaseDetail.getQty());
			pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
	
			pdWarehouseStockTrace.setOrderNo(pdReturnPurchase.getRpNo());
			pdWarehouseStockTrace.setOperatorCode(pdReturnPurchase
					.getOkUsrCode());
	
			pdWarehouseStockTrace.setCreateTime(new Date());
			// 新增分公司分组(按仓库所属分公司)
			pdWarehouseStockTrace.setCompanyCode(pdReturnPurchase
					.getCompanyCode());
	
			// 新增分类
			pdWarehouseStockTrace.setActionType("4");
	
			//modify fu 2015-12-26 判断发货退回单是否已经做过入库的操作-------------begin
			boolean a = pdWarehouseStockTraceDao.getPdReturnPurhcaseChangeStock(pdReturnPurchase.getRpNo(),pdReturnPurchaseDetail.getProductNo());
			if(a){
				return;
			}
			//modify fu 2015-12-26 判断发货退回单是否已经做过入库的操作-------------end
			pdWarehouseStockTraceDao
					.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			dao.savePdWarehouseStock(pdWarehouseStock);
			// pdWarehouseStockTrace.setAgentNo(pdReturnPurchase.getAiAgent());
			// pdWarehouseStockTrace.setProductNo(productNo)
		}
	}

	public void updatePdWarehouseStock(PdFlitWarehouse pdFlitWarehouse,
			String flag) {
		// TODO Auto-generated method stub
		log.info("-->调拨单 更新库存");
		Set sets = pdFlitWarehouse.getPdFlitWarehouseDetails();
		Iterator iterator = sets.iterator();
		// String warehouseNo = "";
		PdWarehouse pdWarehouse = new PdWarehouse();
		if ("out".equals(flag)) {
			pdWarehouse = pdFlitWarehouse.getOutWarehouse();
		} else if ("in".equals(flag)) {
			pdWarehouse = pdFlitWarehouse.getInWarehouse();
		}
	
		// String outWarehouseNo = pdFlitWarehouse.getOutWarehouseNo();
		// String inWarehouseNo = pdFlitWarehouse.getInWarehouseNo();
	
		PdWarehouse pdOutWarehouse = pdFlitWarehouse.getOutWarehouse();
		PdWarehouse pdInWarehouse = pdFlitWarehouse.getInWarehouse();
	
		while (iterator.hasNext()) {
			/* trace begain */
			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("7");
			pdWarehouseStockTrace.setRemark(pdFlitWarehouse.getRemark() + ".."
					+ pdFlitWarehouse.getCheckRemark() + ".."
					+ pdFlitWarehouse.getOkRemark() + ".."
					+ pdFlitWarehouse.getToRemark());
			/* trace end */
			PdFlitWarehouseDetail pdFlitWarehouseDetail = new PdFlitWarehouseDetail();
			PdWarehouseStock pdWarehouseStock = null;
			pdFlitWarehouseDetail = (PdFlitWarehouseDetail) iterator.next();
			pdWarehouseStock = dao.getPdWarehouseStockByProductNo(pdWarehouse
					.getWarehouseNo(), pdFlitWarehouseDetail.getProductNo());
			if (pdWarehouseStock == null) {
				pdWarehouseStock = new PdWarehouseStock();
				pdWarehouseStock.setProductNo(pdFlitWarehouseDetail
						.getProductNo());
				pdWarehouseStock.setPdWarehouse(pdWarehouse);
				pdWarehouseStock.setCompanyCode(pdWarehouse.getCompanyCode());
			}
	
			/* trace begain */
			pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
			/* trace end */
	
			if ("out".equals(flag)) {
				/*
				 * if (!enoughStock(pdFlitWarehouse.getOutWarehouse()
				 * .getWarehouseNo(), pdFlitWarehouseDetail.getProductNo(),
				 * pdFlitWarehouseDetail.getQty())) { throw new
				 * AppException("pd.notEnoughStock"); }
				 */
	
				pdWarehouseStock.setCompanyCode(pdFlitWarehouse
						.getOutCompanyCode());
				pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
						- pdFlitWarehouseDetail.getQty());
				pdWarehouseStockTrace.setChangeQty(-pdFlitWarehouseDetail
						.getQty());
				pdWarehouseStockTrace.setOperatorCode(pdFlitWarehouse
						.getOkUsrCode());
	
				if (pdOutWarehouse.getCompanyCode().equalsIgnoreCase(
						pdInWarehouse.getCompanyCode())) {// 同司出
					pdWarehouseStockTrace.setActionType("-2");
					pdWarehouseStockTrace.setSubType("-1");
				} else {
					pdWarehouseStockTrace.setActionType("-10");
					pdWarehouseStockTrace.setSubType("-2");
				}
	
				/*
				 * //新增分类
				 * if("0".equals(pdOutWarehouse.getWarehouseLevel())){//总仓
				 * pdWarehouseStockTrace.setActionType("-9"); }else
				 * if("1".equals(pdOutWarehouse.getWarehouseLevel())){//一级分仓
				 * if(pdOutWarehouse
				 * .getCompanyNo().equalsIgnoreCase(pdInWarehouse
				 * .getCompanyNo())){//同一公司 //调本公司二级分仓
				 * pdWarehouseStockTrace.setActionType("11"); }else{//不同公司
				 * if("0".equals(pdInWarehouse.getWarehouseLevel())){//回调总仓
				 * pdWarehouseStockTrace.setActionType("10"); }else{
				 * pdWarehouseStockTrace.setActionType("11");//调其他分公司 } }
				 * 
				 * }else{//二级分仓
				 * if(pdOutWarehouse.getCompanyNo().equalsIgnoreCase
				 * (pdInWarehouse.getCompanyNo())){//同一公司
				 * if("1".equals(pdInWarehouse.getWarehouseLevel())){//回调一级分仓
				 * pdWarehouseStockTrace.setActionType("10"); }else{//平调
				 * pdWarehouseStockTrace.setActionType("9"); } }else{//调到其他公司
				 * pdWarehouseStockTrace.setActionType("11"); } }
				 */
	
			} else if ("in".equals(flag)) {
				pdWarehouseStock.setCompanyCode(pdFlitWarehouse
						.getInCompanyCode());
				pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
						+ pdFlitWarehouseDetail.getQty());
				pdWarehouseStockTrace.setChangeQty(pdFlitWarehouseDetail
						.getQty());
				pdWarehouseStockTrace.setOperatorCode(pdFlitWarehouse
						.getToUsrCode());
				// 新增分类
	
				if (pdOutWarehouse.getCompanyCode().equalsIgnoreCase(
						pdInWarehouse.getCompanyCode())) {// 同一分公司内
					pdWarehouseStockTrace.setActionType("2");
					pdWarehouseStockTrace.setSubType("1");
				} else {
					pdWarehouseStockTrace.setActionType("10");
					pdWarehouseStockTrace.setSubType("2");
				}
	
			}
	
			if (pdWarehouseStock.getNormalQty() < 0) {
				throw new AppException("pd.notEnoughStock");
			}
			dao.savePdWarehouseStock(pdWarehouseStock);
	
			/* trace begain */
			pdWarehouseStockTrace.setPdWarehouse(pdWarehouse);
			pdWarehouseStockTrace.setProductNo(pdFlitWarehouseDetail
					.getProductNo());
	
			pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
			//			
			pdWarehouseStockTrace.setOrderNo(pdFlitWarehouse.getFwNo());
			// pdWarehouseStockTrace.setOperatorCode(pdFlitWarehouse.getOkUsrCode());
	
			pdWarehouseStockTrace.setCreateTime(new Date());
			// 新增分公司分组(按仓库所属分公司)
			pdWarehouseStockTrace.setCompanyCode(pdWarehouse.getCompanyCode());
	
			pdWarehouseStockTraceDao
					.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			/* trace end */
		}
	}

	public void updatePdWarehouseStock(JpoCounterOrder poCounterOrder) {
		// TODO Auto-generated method stub
		log.info("-->前台销售 更新库存");
		Set sets = poCounterOrder.getJpoCounterOrderDetails();
		Iterator iterator = sets.iterator();
		while (iterator.hasNext()) {

			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("8");
			pdWarehouseStockTrace.setRemark(poCounterOrder.getRemark());
			JpoCounterOrderDetail jpoCounterOrderDetail = new JpoCounterOrderDetail();
			pdWarehouseStockTrace.setCustomCode(poCounterOrder.getSysUser()
					.getUserCode());
			PdWarehouseStock pdWarehouseStock = new PdWarehouseStock();
			jpoCounterOrderDetail = (JpoCounterOrderDetail) iterator.next();

			if (jpoCounterOrderDetail.getQty() != 0) {
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				/*pdWarehouseStock = dao.getPdWarehouseStockByProductNo(
						poCounterOrder.getWarehouseNo(), jpoCounterOrderDetail
								.getJpmProductSale().getJpmProduct()
								.getProductNo());*/
				pdWarehouseStock = dao.getPdWarehouseStockByProductNo(
						poCounterOrder.getWarehouseNo(), jpoCounterOrderDetail
								.getJpmProductSaleNew().getJpmProduct()
								.getProductNo());
				if (pdWarehouseStock == null) {
					pdWarehouseStock = new PdWarehouseStock();
					//WuCF JpmProductSaleNew Modify By WuCF 20130917
					/*pdWarehouseStock
							.setProductNo(jpoCounterOrderDetail
									.getJpmProductSale().getJpmProduct()
									.getProductNo());*/
					pdWarehouseStock.setProductNo(jpoCounterOrderDetail
							.getJpmProductSaleNew().getJpmProduct()
							.getProductNo());
					// pdWarehouseStock.setPmProduct(pmProductManager.getPmProduct(pdAdjustStockDetail.getProductNo()));
					pdWarehouseStock.setPdWarehouse(pdWarehouseDao
							.getPdWarehouse(poCounterOrder.getWarehouseNo()));
					pdWarehouseStock.setCompanyCode(poCounterOrder
							.getCompanyCode());
				}

				/* trace begain */
				pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock
						.getNormalQty());
				/* trace end */
				if (poCounterOrder.getCsStatus() == 3) {
					pdWarehouseStock.setNormalQty(pdWarehouseStock
							.getNormalQty()
							+ jpoCounterOrderDetail.getQty());
				} else {
					//WuCF JpmProductSaleNew Modify By WuCF 20130917
					/*if (!enoughStock(poCounterOrder.getWarehouseNo(),
							jpoCounterOrderDetail.getJpmProductSale()
									.getJpmProduct().getProductNo(),
							jpoCounterOrderDetail.getQty())) {
						throw new AppException("pd.notEnoughStock");
					}*/
					if (!enoughStock(poCounterOrder.getWarehouseNo(),
							jpoCounterOrderDetail.getJpmProductSaleNew()
									.getJpmProduct().getProductNo(),
							jpoCounterOrderDetail.getQty())) {
						throw new AppException("pd.notEnoughStock");
					}
					pdWarehouseStock.setNormalQty(pdWarehouseStock
							.getNormalQty()
							- jpoCounterOrderDetail.getQty());
				}

				dao.savePdWarehouseStock(pdWarehouseStock);
				// pdWarehouseStock=pdWarehouseStockDao.getPdWarehouseStock(uniNo);
				/* trace begain */
				pdWarehouseStockTrace.setPdWarehouse(pdWarehouseDao
						.getPdWarehouse(poCounterOrder.getWarehouseNo()));
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				/*pdWarehouseStockTrace.setProductNo(jpoCounterOrderDetail
						.getJpmProductSale().getJpmProduct().getProductNo());*/
				pdWarehouseStockTrace.setProductNo(jpoCounterOrderDetail
						.getJpmProductSaleNew().getJpmProduct().getProductNo());
				if (poCounterOrder.getCsStatus() == 3) {
					pdWarehouseStockTrace.setChangeQty(jpoCounterOrderDetail
							.getQty().longValue());

				} else {
					pdWarehouseStockTrace.setChangeQty(-jpoCounterOrderDetail
							.getQty().longValue());
				}

				pdWarehouseStockTrace.setBehindQty(pdWarehouseStock
						.getNormalQty());
				pdWarehouseStockTrace.setOrderNo(poCounterOrder.getCoNo());
				pdWarehouseStockTrace.setOperatorCode(poCounterOrder
						.getShipper().getUserCode());
				/*
				 * pdWarehouseStockTrace.setAction("unnormalChange");
				 * pdWarehouseStockTrace.setOrderType("pdAdjustStock");
				 * 
				 * pdWarehouseStockTrace.setSysUser(null);
				 * pdWarehouseStockTrace.setUsrNo(pdAdjustStock.getOkUsrNo());
				 */
				pdWarehouseStockTrace.setCreateTime(new Date());

				// 新增分公司分组(按仓库所属分公司)
				pdWarehouseStockTrace.setCompanyCode(poCounterOrder
						.getCompanyCode());
				// 新增分类
				pdWarehouseStockTrace.setActionType("7");
				/*
				 * if(pdAdjustStockDetail.getQty() > 0){//异动入
				 * pdWarehouseStockTrace.setActionType("3"); }else{//异动出
				 * pdWarehouseStockTrace.setActionType("8"); }
				 */

				pdWarehouseStockTraceDao
						.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			}

			/* trace end */

		}
		/*
		 * Set sets = poCounterOrder.getPoCounterOrderDetails(); Iterator
		 * iterator =sets.iterator();
		 * 
		 * 
		 * while(iterator.hasNext()){ trace begain PdWarehouseStockTrace
		 * pdWarehouseStockTrace = new PdWarehouseStockTrace(); trace end
		 * PoCounterOrderDetail poCounterOrderDetail = new
		 * PoCounterOrderDetail();
		 * 
		 * PdWarehouseStock pdWarehouseStock = new PdWarehouseStock(); //
		 * log.info(">>>"+poCounterOrderDetail.getPmProduct());
		 * poCounterOrderDetail = (PoCounterOrderDetail) iterator.next();
		 * 
		 * pdWarehouseStock =
		 * dao.getPdWarehouseStockByProductNo(poCounterOrder.getOutWatehouseNo
		 * (), poCounterOrderDetail.getPmProduct().getProductNo());
		 * 
		 * if(pdWarehouseStock==null){ pdWarehouseStock = new
		 * PdWarehouseStock(); //
		 * pdWarehouseStock.setProductNo(poCounterOrderDetail
		 * .getPmProduct().getProductNo());
		 * pdWarehouseStock.setPmProduct(poCounterOrderDetail.getPmProduct());
		 * pdWarehouseStock.setWarehouseNo(poCounterOrder.getOutWatehouseNo());
		 * } trace begain
		 * pdWarehouseStockTrace.setBeforQty(pdWarehouseStock.getTotalQuantity
		 * ()); trace end if(poCounterOrder.getCsStatus() == 2 ){
		 * pdWarehouseStock
		 * .setTotalQuantity(pdWarehouseStock.getTotalQuantity()+
		 * poCounterOrderDetail.getQty());
		 * 
		 * }else{ if(!enoughStock(poCounterOrder.getOutWatehouseNo(),
		 * poCounterOrderDetail.getPmProduct().getProductNo(),
		 * poCounterOrderDetail.getQty().longValue())){ throw new
		 * AppException("pd.notEnoughStock"); }
		 * pdWarehouseStock.setTotalQuantity
		 * (pdWarehouseStock.getTotalQuantity()-poCounterOrderDetail.getQty());
		 * 
		 * }
		 * 
		 * dao.savePdWarehouseStock(pdWarehouseStock);
		 * 
		 * trace begain
		 * pdWarehouseStockTrace.setWarehouseNo(poCounterOrder.getOutWatehouseNo
		 * ());
		 * pdWarehouseStockTrace.setPmProduct(poCounterOrderDetail.getPmProduct
		 * ()); if(poCounterOrder.getCsStatus() == 2 ){
		 * pdWarehouseStockTrace.setChangeQty
		 * (poCounterOrderDetail.getQty().longValue()); }else{
		 * pdWarehouseStockTrace
		 * .setChangeQty(-poCounterOrderDetail.getQty().longValue()); }
		 * 
		 * 
		 * 
		 * pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getTotalQuantity()
		 * ); pdWarehouseStockTrace.setAction("sold");
		 * pdWarehouseStockTrace.setOrderType("poCounterOrder");
		 * pdWarehouseStockTrace.setOrderNo(poCounterOrder.getCoNo());
		 * pdWarehouseStockTrace.setSysUser(poCounterOrder.getAgentUser());
		 * pdWarehouseStockTrace.setUsrNo(poCounterOrder.getConfirmUserNo());
		 * pdWarehouseStockTrace.setCreateTime(new Date()); //新增分公司分组(按仓库所属分公司)
		 * pdWarehouseStockTrace
		 * .setCompanyCode(pdWarehouseManager.getPdWarehouseByNo
		 * (poCounterOrder.getOutWatehouseNo()).getCompanyNo());
		 * 
		 * 
		 * //新增分类 pdWarehouseStockTrace.setActionType("7");
		 * 
		 * if(pdWarehouseStockTrace.getChangeQty() !=0){
		 * pdWarehouseStockTraceDao
		 * .savePdWarehouseStockTrace(pdWarehouseStockTrace); }
		 * 
		 * trace end }
		 */
	}

	public void updatePdWarehouseStock(JprRefund prRefund) {
		// TODO Auto-generated method stub
		Set sets = prRefund.getJprReFundList();
		Iterator iterator = sets.iterator();
		while (iterator.hasNext()) {
			/* trace begain */
			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("9");
			pdWarehouseStockTrace.setRemark(prRefund.getRemark() + ".."
					+ prRefund.getIntoRemark() + ".."
					+ prRefund.getRefundRemark());
			/* trace end */
			PdWarehouseStock pdWarehouseStock = new PdWarehouseStock();
			JprRefundList prRefundList = new JprRefundList();
	
			prRefundList = (JprRefundList) iterator.next();
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*pdWarehouseStock = dao.getPdWarehouseStockByProductNo(prRefund
					.getResendSpNo(), prRefundList.getJpmProductSale()
					.getJpmProduct().getProductNo());*/
			pdWarehouseStock = dao.getPdWarehouseStockByProductNo(prRefund
					.getResendSpNo(), prRefundList.getJpmProductSaleTeamType().getJpmProductSaleNew()
					.getJpmProduct().getProductNo());
			if (pdWarehouseStock == null) {
				pdWarehouseStock = new PdWarehouseStock();
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				/*pdWarehouseStock.setProductNo(prRefundList.getJpmProductSale()
						.getJpmProduct().getProductNo());*/
				pdWarehouseStock.setProductNo(prRefundList.getJpmProductSaleTeamType().getJpmProductSaleNew()
						.getJpmProduct().getProductNo());
				pdWarehouseStock.setPdWarehouse(pdWarehouseDao
						.getPdWarehouse(prRefund.getResendSpNo()));
				pdWarehouseStock.setCompanyCode(prRefund.getCompanyCode());
				// pdWarehouseStock.setNormalQty(new
				// Long(prRefundList.getQty()));
	
			}
			/* trace begain */
			pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
			/* trace end */
	
			if ("2".equals(prRefund.getIntoStatus())) {
				pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
						+ prRefundList.getQty());
				pdWarehouseStockTrace.setChangeQty(new Long(prRefundList
						.getQty()));
			} else {
				pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
						- prRefundList.getQty());
				pdWarehouseStockTrace.setChangeQty(new Long(-prRefundList
						.getQty()));
			}
	
			dao.savePdWarehouseStock(pdWarehouseStock);
	
			/* trace begain */
			pdWarehouseStockTrace.setPdWarehouse(pdWarehouseDao
					.getPdWarehouse(prRefund.getResendSpNo()));
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*pdWarehouseStockTrace.setProductNo(prRefundList.getJpmProductSale()
					.getJpmProduct().getProductNo());*/
			pdWarehouseStockTrace.setProductNo(prRefundList.getJpmProductSaleTeamType().getJpmProductSaleNew()
					.getJpmProduct().getProductNo());
			
			pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
	
			pdWarehouseStockTrace.setOrderNo(prRefund.getRoNo());
	
			pdWarehouseStockTrace.setCustomCode(prRefund.getSysUser()
					.getUserCode());
			pdWarehouseStockTrace.setOperatorCode(prRefund.getIntoUNo());
			pdWarehouseStockTrace.setCreateTime(new Date());
	
			// 新增分类
			pdWarehouseStockTrace.setActionType("9");
			// 新增分公司分组(按仓库所属分公司)
			pdWarehouseStockTrace.setCompanyCode(prRefund.getCompanyCode());
	
			pdWarehouseStockTraceDao
					.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			/* trace end */
		}
	
	}

	public void updatePdWarehouseStock(PdExchangeOrder pdExchangeOrder) {
		// TODO Auto-generated method stub
		log.info("-->发货退货单 更新库存");
		Set sets = pdExchangeOrder.getPdExchangeOrderBacks();
		log.info("sets=" + sets.size());
		Iterator iterator = sets.iterator();
	
		// PdReturnPurchaseDetail pdReturnPurchaseDetail = new
		// PdReturnPurchaseDetail();
		// PdWarehouseStock pdWarehouseStock = null;
	
		while (iterator.hasNext()) {
			
			PdExchangeOrderBack pdExchangeOrderBack = (PdExchangeOrderBack) iterator
			.next();
			if(pdExchangeOrderBack.getQty()!=0){
				/* trace begain */
				PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
				pdWarehouseStockTrace.setOrderType("10");
				pdWarehouseStockTrace.setCustomCode(pdExchangeOrder.getCustomer()
						.getUserCode());
				pdWarehouseStockTrace.setRemark(pdExchangeOrder.getRemark() + ".."
						+ pdExchangeOrder.getCheckRemark() + ".."
						+ pdExchangeOrder.getOkRemark());
				/* trace end */
				
				
				PdWarehouseStock pdWarehouseStock = new PdWarehouseStock();
				pdWarehouseStock = dao.getPdWarehouseStockByProductNo(
						pdExchangeOrder.getWarehouseNo(), pdExchangeOrderBack
								.getProductNo());
	
				if (pdWarehouseStock == null) {
					pdWarehouseStock = new PdWarehouseStock();
					pdWarehouseStock.setProductNo(pdExchangeOrderBack
							.getProductNo());
					// pdWarehouseStock.setPmProduct(pmProductManager.getPmProduct(pdReturnPurchaseDetail.getProductNo()));
					pdWarehouseStock.setPdWarehouse(pdWarehouseDao
							.getPdWarehouse(pdExchangeOrder.getWarehouseNo()));
					pdWarehouseStock.setCompanyCode(pdExchangeOrder
							.getCompanyCode());
				}
				// log.info("pdReturnPurchaseDetail.getQty()="+pdReturnPurchaseDetail.getQty());
				pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
				pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
						+ pdExchangeOrderBack.getQty());
				dao.savePdWarehouseStock(pdWarehouseStock);
				// 增加trace
	
				pdWarehouseStockTrace.setPdWarehouse(pdWarehouseDao
						.getPdWarehouse(pdExchangeOrder.getWarehouseNo()));
				pdWarehouseStockTrace.setProductNo(pdExchangeOrderBack
						.getProductNo());
	
				pdWarehouseStockTrace.setChangeQty(pdExchangeOrderBack.getQty());
				pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
	
				pdWarehouseStockTrace.setOrderNo(pdExchangeOrder.getEoNo());
				pdWarehouseStockTrace.setOperatorCode(pdExchangeOrder
						.getOkUsrCode());
	
				pdWarehouseStockTrace.setCreateTime(new Date());
				// 新增分公司分组(按仓库所属分公司)
				pdWarehouseStockTrace.setCompanyCode(pdExchangeOrder
						.getCompanyCode());
	
				// 新增分类
				pdWarehouseStockTrace.setActionType("100");
	
				pdWarehouseStockTraceDao
						.savePdWarehouseStockTrace(pdWarehouseStockTrace);
				// pdWarehouseStockTrace.setAgentNo(pdReturnPurchase.getAiAgent());
				// pdWarehouseStockTrace.setProductNo(productNo)
			}
			
		}
	}

	public List getPdWarehouseStocksByGroup(CommonRecord crm, String groupType) {
		// TODO Auto-generated method stub

		List list = new ArrayList();
		List retList = new ArrayList();
		String companyCode = crm.getString("companyCode", "");
		
		String showFlag = crm.getString("showFlag", "");
		
		if (StringUtils.isEmpty(companyCode)) {
			try {
				crm.setValue("companyCode", "AA");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		List productSalelist = jpmProductSaleManager.getJpmProductSalesByCrm(
//				crm, null);

		if ("productNo".equals(groupType)) {
			list = dao.getPdWarehouseStocksByProductNo(crm, groupType);
		}

		// Map stocksMap = rebuildList2MapBase(list);
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		Map<String,JpmProductSaleNew> pmProductSaleMap = jpmProductSaleManager.getProductSaleMapNew2(crm,companyCode);
//				.getCompanyProductMap(companyCode);

		List unSendList = dao.getSumUnSendInfo(crm);
		Map unSendMap = rebuildList2MapBase(unSendList);
		List onWayList = dao.getSumOnWay(crm);
		Map onWayMap = rebuildList2MapBase(onWayList);
		log.info("list.size=" + list.size());
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map retMap = new HashMap();
				Map stocksMap = (Map) list.get(i);

				// PdWarehouseStock pdWarehouseStock = (PdWarehouseStock) list
				// .get(i);

				BigDecimal unSendQty = new BigDecimal(0);
				if (unSendMap.get(stocksMap.get("PRODUCT_NO")) != null) {
					unSendQty = (BigDecimal) unSendMap.get(stocksMap
							.get("PRODUCT_NO"));
				}
				BigDecimal onWayQty = new BigDecimal(0);
				if (onWayMap.get(stocksMap.get("PRODUCT_NO")) != null) {
					onWayQty = (BigDecimal) onWayMap.get(stocksMap
							.get("PRODUCT_NO"));
				}
				// retMap.put("stock", pdWarehouseStock);
				retMap.put("warehouse", stocksMap.get("WAREHOUSE_NO"));
				retMap.put("productNo", stocksMap.get("PRODUCT_NO"));
				retMap.put("productName", ((JpmProductSaleNew)pmProductSaleMap.get(stocksMap
						.get("PRODUCT_NO"))).getProductName());
				retMap.put("normalQty", stocksMap.get("NORMAL_QTY"));// 合格实物数
				retMap.put("damageQty", stocksMap.get("DAMAGE_QTY"));// 破损数
				retMap.put("unknownQty", stocksMap.get("UNKNOWN_QTY"));// 未确认数
				retMap.put("unSendQty", unSendQty);// 制单数
				retMap.put("onWayQty", onWayQty);// 在途数
				retMap.put("useQty", ((BigDecimal) stocksMap.get("NORMAL_QTY"))
						.subtract(unSendQty));// 可以数
				
				retMap.put("storageCordon", ((JpmProductSaleNew)pmProductSaleMap.get(stocksMap
						.get("PRODUCT_NO"))).getStorageCordon());
				retMap.put("saleState", ((JpmProductSaleNew)pmProductSaleMap.get(stocksMap
						.get("PRODUCT_NO"))).getStatus());
				if("1".equals(showFlag)){
					if(((BigDecimal)retMap.get("useQty")).longValue() < ((JpmProductSaleNew)pmProductSaleMap.get(stocksMap
							.get("PRODUCT_NO"))).getStorageCordon()){
								retList.add(retMap);
							}
				}else{
					retList.add(retMap);
				}
				

			}
		}
		return retList;
	}

	public boolean enoughStock(String warehouseNo, String productNo, long qty) {
		// TODO Auto-generated method stub
		boolean ret = false;
		PdWarehouseStock pdWarehouseStock = dao.getPdWarehouseStockByProductNo(
				warehouseNo, productNo);
		if (pdWarehouseStock == null) {
			if (qty <= 0) {
				ret = true;
			}

		} else {
			if (pdWarehouseStock.getNormalQty() >= qty) {
				ret = true;
			}
		}

		return ret;
	}

	public void setPdWarehouseStockTraceDao(
			PdWarehouseStockTraceDao pdWarehouseStockTraceDao) {
		this.pdWarehouseStockTraceDao = pdWarehouseStockTraceDao;
	}

	public void setPdWarehouseDao(PdWarehouseDao pdWarehouseDao) {
		this.pdWarehouseDao = pdWarehouseDao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setPdWarehouseStockDao(PdWarehouseStockDao dao) {
		this.dao = dao;
	}

	public Long enoughStockSend(String warehouseNo, String productNo, long qty) {
		// TODO Auto-generated method stub
		boolean ret = false;
		Long realStock = new Long(0);
		
		//获取库存数量
		PdWarehouseStock pdWarehouseStock = dao.getPdWarehouseStockByProductNo(
				warehouseNo, productNo);
		//获取发货单的未发货数量
		Integer i = dao.getUnSendInfo(warehouseNo, productNo);
		if (pdWarehouseStock == null) {

			if ((qty + i) <= 0) {
				ret = true;
			}
		} else {
			realStock = pdWarehouseStock.getNormalQty();
			if ((pdWarehouseStock.getNormalQty() - i - qty) >= 0) {
				ret = true;
			}
		}
		realStock = realStock - i - qty;
		return realStock;
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseStockManager#getPdWarehouseStocks(com.joymain.jecs.pd.model.PdWarehouseStock)
	 */
	public List getPdWarehouseStocks(final PdWarehouseStock pdWarehouseStock) {
		return dao.getPdWarehouseStocks(pdWarehouseStock);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseStockManager#getPdWarehouseStock(String
	 *      uniNo)
	 */
	public PdWarehouseStock getPdWarehouseStock(final String uniNo) {
		return dao.getPdWarehouseStock(new Long(uniNo));
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseStockManager#savePdWarehouseStock(PdWarehouseStock
	 *      pdWarehouseStock)
	 */
	public void savePdWarehouseStock(PdWarehouseStock pdWarehouseStock) {
		dao.savePdWarehouseStock(pdWarehouseStock);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseStockManager#removePdWarehouseStock(String
	 *      uniNo)
	 */
	public void removePdWarehouseStock(final String uniNo) {
		dao.removePdWarehouseStock(new Long(uniNo));
	}

	// added for getPdWarehouseStocksByCrm
	public List getPdWarehouseStocksByCrm(CommonRecord crm, Pager pager) {
		String companyCode = crm.getString("companyCode", "");
		if (StringUtils.isEmpty(companyCode)) {
			companyCode = "AA";
		}
		
		List retList = null;
		List list = null;
		List unSendList = null;//制单数量
		Map unSendMap = null;
		List onWayList = null;//在途数量
		Map onWayMap = null;
		List outWarehouseList = null;//Modify By WuCF 出库单已经审核状态
		Map outWarehouseMap = null;
		List pdFlitWarehouseList = null;//Modify By WuCF 调拨已经审核状态数量
		Map pdFlitWarehouseMap = null;
		Map pmProductSaleMap = null;
		//Modify By WuCF 20130705 点击左边菜单初次进入默认不查询数据
		String cxFlag = crm.getString("cxFlag",""); 
		if(!"n".equals(cxFlag)){
			retList = new ArrayList();
			list = dao.getPdWarehouseStocksByCrm(crm, pager);
			unSendList = dao.getUnSendInfo(crm);
			unSendMap = rebuildList2Map(unSendList);
			onWayList = dao.getOnWay(crm);
			onWayMap = rebuildList2Map(onWayList);
			
			outWarehouseList = dao.getOutWarehouses(crm);
			outWarehouseMap = rebuildList2Map(outWarehouseList);
			
			pdFlitWarehouseList = dao.getPdFlitWarehouse(crm);//调拨已经审核状态数量
			pdFlitWarehouseMap = rebuildList2Map(pdFlitWarehouseList);
			
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*pmProductSaleMap = jpmProductSaleManager.getCompanyProductMap(companyCode);*/
			pmProductSaleMap = jpmProductSaleManager.getCompanyProductMapNew(companyCode);
		}
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map retMap = new HashMap();
				PdWarehouseStock pdWarehouseStock = (PdWarehouseStock) list
						.get(i);
				//制单数：已审核的发货单
				BigDecimal unSendQty = new BigDecimal(0);
				if (unSendMap.get(pdWarehouseStock.getPdWarehouse()
						.getWarehouseNo()
						+ "-" + pdWarehouseStock.getProductNo()) != null) {
					unSendQty = (BigDecimal) unSendMap.get(pdWarehouseStock
							.getPdWarehouse().getWarehouseNo()
							+ "-" + pdWarehouseStock.getProductNo());
				}
				//调拨单在途的单
				BigDecimal onWayQty = new BigDecimal(0);
				if (onWayMap.get(pdWarehouseStock.getPdWarehouse()
						.getWarehouseNo()
						+ "-" + pdWarehouseStock.getProductNo()) != null) {
					onWayQty = (BigDecimal) onWayMap.get(pdWarehouseStock
							.getPdWarehouse().getWarehouseNo()
							+ "-" + pdWarehouseStock.getProductNo());
				}
				//Add By WuCF 20140509 已经审核的出库单数据
				BigDecimal outWarehouseAudit = new BigDecimal(0);
				if (outWarehouseMap.get(pdWarehouseStock.getPdWarehouse()
						.getWarehouseNo()
						+ "-" + pdWarehouseStock.getProductNo()) != null) {
					outWarehouseAudit = (BigDecimal) outWarehouseMap.get(pdWarehouseStock
							.getPdWarehouse().getWarehouseNo()
							+ "-" + pdWarehouseStock.getProductNo());
				}
				//Add By WuCF 20140509 调拨单已经审核的单
				BigDecimal pdFlitWarehouseAudit = new BigDecimal(0);
				if (pdFlitWarehouseMap.get(pdWarehouseStock.getPdWarehouse()
						.getWarehouseNo()
						+ "-" + pdWarehouseStock.getProductNo()) != null) {
					pdFlitWarehouseAudit = (BigDecimal) pdFlitWarehouseMap.get(pdWarehouseStock
							.getPdWarehouse().getWarehouseNo()
							+ "-" + pdWarehouseStock.getProductNo());
				}
				retMap.put("stock", pdWarehouseStock); 
				retMap.put("warehouse", pdWarehouseStock.getPdWarehouse());
				retMap.put("warehouseNo", pdWarehouseStock.getPdWarehouse()
						.getWarehouseNo());
				retMap.put("productNo", pdWarehouseStock.getProductNo());
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				/*if(pmProductSaleMap.get(pdWarehouseStock.getProductNo())!=null){
					retMap.put("productName", ((JpmProductSale)pmProductSaleMap.get(pdWarehouseStock
							.getProductNo())).getProductName());
					retMap.put("saleState", ((JpmProductSale)pmProductSaleMap.get(pdWarehouseStock
							.getProductNo())).getStatus());
				} */
				if(pmProductSaleMap.get(pdWarehouseStock.getProductNo())!=null){
					retMap.put("productName", ((JpmProductSaleNew)pmProductSaleMap.get(pdWarehouseStock
							.getProductNo())).getProductName());
					retMap.put("saleState", ((JpmProductSaleNew)pmProductSaleMap.get(pdWarehouseStock
							.getProductNo())).getStatus());
				} 
				retMap.put("normalQty", pdWarehouseStock.getNormalQty());// 合格实物数
				retMap.put("damageQty", pdWarehouseStock.getDamageQty());// 破损数
				retMap.put("unknownQty", pdWarehouseStock.getUnknownQty());// 未确认数
				retMap.put("unSendQty", unSendQty);// 制单数：已经审核的发货单
				retMap.put("onWayQty", onWayQty);// 在途数：以入库仓库为准的，已经出库确认的调拨单
				retMap.put("outWarehouseAudit", outWarehouseAudit);// 出库单审核数量
				retMap.put("pdFlitWarehouseAudit", pdFlitWarehouseAudit);// 调拨单审核：以入库仓库为准的，已经审核的调拨单
				retMap.put("useQty", new BigDecimal(pdWarehouseStock
						.getNormalQty()).subtract(unSendQty).subtract(outWarehouseAudit).subtract(pdFlitWarehouseAudit));//可用库存数
				//retMap.put("warnQty", pdWarehouseStock.getWarnQty());// Modify By WuCF20150706 警戒库存编辑
				//retMap.put("uniNo", pdWarehouseStock.getUniNo());
				//Modify By WuCF 20140515
//				用库存=合格数-制单数-出库单审核-调拨单审核。这样能看出来，某个仓库的某个货，去除需要发的货后还剩余的数量。
				retList.add(retMap);

			}
		}
		return retList;
		// return dao.getPdWarehouseStocksByCrm(crm, pager);
	}

	private Map rebuildList2Map(List list) {
		Map retMap = new HashMap();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				retMap.put((String) map.get("WAREHOUSE_NO") + "-"
						+ (String) map.get("PRODUCT_NO"), (BigDecimal) map
						.get("QTY"));
			}
		}
		return retMap;
	}

	private Map rebuildList2MapBase(List list) {
		Map retMap = new HashMap();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				retMap.put((String) map.get("PRODUCT_NO"), (BigDecimal) map
						.get("QTY"));
			}
		}
		return retMap;
	}

	public Long getStock(String companyCode, String warehouseNo,
			String productNo) {
		// TODO Auto-generated method stub
		return dao.getStock(companyCode, warehouseNo, productNo);
	}
	public void reupdatePdWarehouseStock(PdSendInfo pdSendInfo) {
		// TODO Auto-generated method stub
		Set sets = pdSendInfo.getPdSendInfoDetails();
		Iterator iterator = sets.iterator();
		String warehouseNo = pdSendInfo.getWarehouseNo();
	
		while (iterator.hasNext()) {
			/* trace begain */
			PdWarehouseStockTrace pdWarehouseStockTrace = new PdWarehouseStockTrace();
			pdWarehouseStockTrace.setOrderType("4");
			pdWarehouseStockTrace.setSubType(pdSendInfo.getOrderType());
			pdWarehouseStockTrace.setCustomCode(pdSendInfo.getCustomer().getUserCode());
			pdWarehouseStockTrace.setRemark(pdSendInfo.getRemark() + ".."
					+ pdSendInfo.getCheckRemark() + ".."
					+ pdSendInfo.getOkRemark());
			/* trace end */
			PdSendInfoDetail pdSendInfoDetail = new PdSendInfoDetail();
	
			PdWarehouseStock pdWarehouseStock = null;
			pdSendInfoDetail = (PdSendInfoDetail) iterator.next();
			if (!enoughStock(pdSendInfo.getWarehouseNo(), pdSendInfoDetail
					.getProductNo(), -pdSendInfoDetail.getQty())) {
				throw new AppException("pd.notEnoughStock");
			}
			pdWarehouseStock = dao.getPdWarehouseStockByProductNo(warehouseNo,
					pdSendInfoDetail.getProductNo());
			if (pdWarehouseStock == null) {
				pdWarehouseStock = new PdWarehouseStock();
				pdWarehouseStock.setProductNo(pdSendInfoDetail.getProductNo());
				// pdWarehouseStock.setPmProduct(pdSendInfoDetail.getPmProduct());
				pdWarehouseStock.setPdWarehouse(pdWarehouseDao
						.getPdWarehouse(warehouseNo));
				pdWarehouseStock.setCompanyCode(pdSendInfo.getCompanyCode());
			}
	
			/* trace begain */
			pdWarehouseStockTrace.setBeforeQty(pdWarehouseStock.getNormalQty());
			/* trace end */
			pdWarehouseStock.setNormalQty(pdWarehouseStock.getNormalQty()
					+ pdSendInfoDetail.getQty());
	
			dao.savePdWarehouseStock(pdWarehouseStock);
			// pdWarehouseStock=pdWarehouseStockDao.getPdWarehouseStock(uniNo);
	
			/* trace begain */
			pdWarehouseStockTrace.setPdWarehouse(pdWarehouseDao
					.getPdWarehouse(warehouseNo));
			pdWarehouseStockTrace.setProductNo(pdSendInfoDetail.getProductNo());
			pdWarehouseStockTrace.setChangeQty(pdSendInfoDetail.getQty());
			pdWarehouseStockTrace.setBehindQty(pdWarehouseStock.getNormalQty());
	
			pdWarehouseStockTrace.setOrderNo(pdSendInfo.getSiNo());
			pdWarehouseStockTrace.setOperatorCode(pdSendInfo.getOkUsrCode());
	
			pdWarehouseStockTrace.setCreateTime(new Date());
	
			// 新增分公司分组(按仓库所属分公司)
			pdWarehouseStockTrace.setCompanyCode(pdSendInfo.getCompanyCode());
			// 新增分类
			pdWarehouseStockTrace.setActionType("5");
			if (pdWarehouseStockTrace.getChangeQty() != 0) {
				pdWarehouseStockTraceDao
						.savePdWarehouseStockTrace(pdWarehouseStockTrace);
			}
		}
	}

	/**
	 * param:siNo 发货单号
	 * return: 返回修改状态  true:成功  false:失败
	 */
	public boolean updateHurryFlag(String siNo) {
		return dao.updateHurryFlag(siNo);
	}

	
	//Add By WuCF 20140624 AWT实现导出订单以及明细并返回下载的URL连接地址
	public String exportOrderAndInfo(String memberOrderNo,String userCode,String logType,String startLogTime,String endLogTime,
			String orderType,String status,String isRetreatOrder,String province,String city,
			String district,String productType,String payByCoin,String payByJJ,String memberType,String operUserCode,String type,String logDesc){
		String url = "";//返回的下载地址
		
		//传递的参数
		String args[] = new String[19];
		args[0] = memberOrderNo;
		args[1] = userCode;
		args[2] = logType;
		args[3] = startLogTime;
		args[4] = endLogTime;		
		args[5] = orderType;
		args[6] = status;
		args[7] = isRetreatOrder;
		args[8] = province;
		args[9] = city;		
		args[10] = district;
		args[11] = productType;
		args[12] = payByCoin;
		args[13] = payByJJ;
		args[14] = memberType;		
		args[15] = "CN";
		args[16] = type;//Modify By WuCF 导出类型：1：订单详细
		args[17] = logDesc;
		args[18] = operUserCode;//操作人
		Runtime r=Runtime.getRuntime();    
		List<String> strList = new ArrayList<String>();
		
		try {
			//处理字符串
			String[] strs = getReport(args[0],args[1],args[2],args[3],args[4],
							args[5],args[6],args[7],args[8],args[9],
							args[10],args[11],args[12],args[13],args[14],args[15],args[16],args[17],args[18]);
			
			String cmdStr = "";
			if("1".equals(type)){
				cmdStr = "ecOrderReport.sh";
			}else if("2".equals(type)){
				cmdStr = "ecReport.sh";
			}
			
			StringBuffer shellCmd = new StringBuffer(" sh /root/workspace/domain/jecsImage/rpt/"+cmdStr+" '");//调用的sh命令          
			int paramNum = strs.length;                
			int i;                
			for( i = 0; i < paramNum; i++){
				shellCmd.append(strs[i]);
			}			
			shellCmd.append("' ");
			System.out.println("=====shellCmd:"+shellCmd.toString());
			//执行查询语句
			Process process = r.exec(shellCmd.toString());
			InputStreamReader ir = new InputStreamReader(process.getInputStream());  
			LineNumberReader input = new LineNumberReader(ir);  
			String line;  
			process.waitFor();  
			while ((line = input.readLine()) != null){  
				strList.add(line);  
			}  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(strList!=null && strList.size()>=1){
			url = strList.get(0);
		}else{
			url = "#";
		}
		return url;
	}
	
	private String runReport(String shellFile, String[] ... params)
	throws IOException, InterruptedException {
		String zipPath = "";
		String shell = "sh " + shellFile + " '";

		for (int i = 0; i < params.length; i++) {
			if ("".equals(params[i]) || params[i] == null) {
				shell = shell + "N";
			} else {
				shell = shell + params[i];
			}
		}
		shell = shell + "'";
		System.out.println(shell);
		Process process;
		process = Runtime.getRuntime().exec(shell);
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line;

		process.waitFor();
		while ((line = input.readLine()) != null) {
			zipPath = zipPath + line;
		}

		return zipPath;
	}

	private String[] getReport(String p_member_order_no,
			String p_user_code, String p_logType, String p_startLogTime,
			String p_endLogTime, String p_order_type, String p_order_status,
			String p_isRetreatOrder, String p_province, String p_city,
			String p_district, String p_productType, String p_payByCoin,
			String p_payByJJ, String p_member_type, String p_company_code, 
			String p_log_type, String p_log_desc,String p_oper_user_code)
	throws IOException, InterruptedException {

		if ("".equals(p_member_order_no) || p_member_order_no == null) {
			p_member_order_no = "N]";
		} else {
			p_member_order_no = "" + p_member_order_no + "]";
		}

		if ("".equals(p_user_code) || p_user_code == null) {
			p_user_code = "N]";
		} else {
			p_user_code = "" + p_user_code + "]";
		}

		if ("".equals(p_logType) || p_logType == null) {
			p_logType = "A]";
		} else {
			p_logType = "" + p_logType + "]";
		}

		if ("".equals(p_startLogTime) || p_startLogTime == null) {
			p_startLogTime = "N]";
		} else {
			p_startLogTime = "" + p_startLogTime + "]";
		}

		if ("".equals(p_endLogTime) || p_endLogTime == null) {
			p_endLogTime = "N]";
		} else {
			p_endLogTime = "" + p_endLogTime + "]";
		}

		if ("".equals(p_order_type) || p_order_type == null) {
			p_order_type = "N]";
		} else {
			p_order_type = "" + p_order_type + "]";
		}

		if ("".equals(p_order_status) || p_order_status == null) {
			p_order_status = "N]";
		} else {
			p_order_status = "" + p_order_status + "]";
		}

		if ("".equals(p_isRetreatOrder) || p_isRetreatOrder == null) {
			p_isRetreatOrder = "9]";
		} else {
			p_isRetreatOrder = "" + p_isRetreatOrder + "]";
		}

		if ("".equals(p_province) || p_province == null) {
			p_province = "N]";
		} else {
			p_province = "" + p_province + "]";
		}

		if ("".equals(p_city) || p_city == null) {
			p_city = "N]";
		} else {
			p_city = "" + p_city + "]";
		}

		if ("".equals(p_district) || p_district == null) {
			p_district = "N]";
		} else {
			p_district = "" + p_district + "]";
		}

		if ("".equals(p_productType) || p_productType == null) {
			p_productType = "ALL]";
		} else {
			p_productType = "" + p_productType + "]";
		}

		if ("".equals(p_payByCoin) || p_payByCoin == null) {
			p_payByCoin = "N]";
		} else {
			p_payByCoin = "" + p_payByCoin + "]";
		}

		if ("".equals(p_payByJJ) || p_payByJJ == null) {
			p_payByJJ = "N]";
		} else {
			p_payByJJ = "" + p_payByJJ + "]";
		}

		if ("".equals(p_member_type) || p_member_type == null) {
			p_member_type = "N]";
		} else {
			p_member_type = "" + p_member_type + "]";
		}

		if ("".equals(p_company_code) || p_company_code == null) {
			p_company_code = "CN]";
		} else {
			p_company_code = "" + p_company_code + "]";
		}
		
		if ("".equals(p_log_type) || p_log_type == null) {
			p_log_type = "]";
		} else {
			p_log_type = "" + p_log_type + "]";
		}
		
		if ("".equals(p_log_desc) || p_log_desc == null) {
			p_log_desc = "]";
		} else {
			p_log_desc = "" + p_log_desc + "]";
		}
		
		if ("".equals(p_oper_user_code) || p_oper_user_code == null) {
			p_oper_user_code = "]";
		} else {
			p_oper_user_code = "" + p_oper_user_code + "]";
		}
		String[] params = new String[] { p_member_order_no, p_user_code,
				p_logType, p_startLogTime, p_endLogTime, p_order_type,
				p_order_status, p_isRetreatOrder, p_province, p_city,
				p_district, p_productType, p_payByCoin, p_payByJJ,
				p_member_type, p_company_code, p_log_type, p_log_desc, p_oper_user_code};
		return params;
	}

	public static void main(String[] args) {
//		RunOrder ro = new RunOrder();
		String shell = "/orabak/test/ecReport.sh";
		String zipPath = "";
		String p_member_order_no = "";
		String p_user_code = "CN15619244";
		String p_logType = "C";
		String p_startLogTime = "";
		String p_endLogTime = "";
		String p_order_type = "";
		String p_order_status = "";
		String p_isRetreatOrder = "";
		String p_province = "";
		String p_city = "";
		String p_district = "";
		String p_productType = "";
		String p_payByCoin = "";
		String p_payByJJ = "";
		String p_member_type = "";
		String p_company_code = "";

		try {
			/*zipPath = ro.getReport(shell, p_member_order_no, p_user_code,
					p_logType, p_startLogTime, p_endLogTime, p_order_type,
					p_order_status, p_isRetreatOrder, p_province, p_city,
					p_district, p_productType, p_payByCoin, p_payByJJ,
					p_member_type, p_company_code);*/
			System.out.println("ZipPath:" + zipPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 接口询问机制-公共方法
	 * @author fu 2016-03-21
	 * @param pdLogisticsNumbers 物流单号：发货单号，退货单号，换货单号
	 * @param type 用数字表示询问类型:11发货单编辑、12发货单的暂不发货、13发货单的暂停发货、14发货单的发货作废；21退货单编辑、22退货单删除；31换货单编辑、32换货单删除；
	 * @param methodName 接口方法名
	 * @return  
	 */
	public String  getPdLogisticsCanModify(String pdLogisticsNumbers,String type,String methodName){
		//modify by fu 2016-05-10 接口询问机制添加开关  
		String interfaceAskOpenStatus = ConfigUtil.getConfigValue("CN", "interfaceask.open.status");
		String result = "allow";
		//modify by fu 2016-1-18 接口询问机制的代码特意注释掉------安杰玛系统物流不用WMS发货，所以注释掉；
		/*if((!StringUtil.isEmpty(interfaceAskOpenStatus))&&("Y".equals(interfaceAskOpenStatus))){
				log.info("在类PdWarehouseStockManagerImpl的方法getPdLogisticsCanModify中运行:pdLogisticsNumbers为"+pdLogisticsNumbers+",接口方法名methodName为"+methodName);
				try{
						
						if(StringUtil.isEmpty(pdLogisticsNumbers) || StringUtil.isEmpty(type) || StringUtil.isEmpty(methodName)){
							result = "访问接口询问机制公共方法的参数有空值，请检查！";
							return result;
						}else{
							PdInquiryMechanism  pdInquiryMechanism = new PdInquiryMechanism();
							pdInquiryMechanism.setPdLogisticsNumbers(pdLogisticsNumbers);
							pdInquiryMechanism.setType(type);
							JSONObject jo = JSONObject.fromObject(pdInquiryMechanism);//将java对象转换成json对象
							String joString = jo.toString();//将json对象转换成json字符串
							MsgSendService msgSendService = new MsgSendService();
							msgSendService.setSender(Constants.WMS_SEND);//WMS平台
							log.info("在类PdWarehouseStockManagerImpl的方法getPdLogisticsCanModify中运行:开始去WMS询问");
							String bb = msgSendService.postToWms(joString, methodName);
							
							//modify by fu 2016-04-29 ----对返回接口异常的处理---begin
							if(StringUtil.isEmpty(bb)){
								 result = "后续系统接收接口消息超时或返回接口消息异常";
								 return result;
							}
							//modify by fu 2016-04-29 ----对返回接口异常的处理---end
							
							log.info("在类PdWarehouseStockManagerImpl的方法getPdLogisticsCanModify中运行:去WMS询问结束");
							
							org.json.JSONObject joTwo = new org.json.JSONObject(bb);
						    if (joTwo.has("error_response")){
						    	    result = "来自WMS的消息：";//返回错误消息，表明物流单号不允许修改
						            org.json.JSONObject json = joTwo.getJSONObject("error_response");
						            result += json.getString("msg").toString();
						    }
						    
						    //modify by fu 2016-04-29 ----对接口消息格式的校验---begin
						    else if(joTwo.has("response")){
						    	//接口消息格式正确
						    }else{
						    	//接口消息格式不正确
						    	result = "后续系统返回的接口消息格式与规定的不符";
						    }
						    //modify by fu 2016-04-29 ----对对接口消息格式的校验---end
							log.info("在类PdWarehouseStockManagerImpl的方法getPdLogisticsCanModify中运行:接口日志写入开始");
							//=============================接口日志写入开始
							JocsInterfaceLog jocsInterfaceLog = new JocsInterfaceLog();
							jocsInterfaceLog.setLogType("0");//0：本地发送数据    1：本地接收数据
							jocsInterfaceLog.setSender(Constants.WMS_SEND);//数据的接收方
							jocsInterfaceLog.setMethod(methodName);//方法名称
							jocsInterfaceLog.setContent(joString.toString());//发送内容content
							jocsInterfaceLog.setReturnDesc(bb);//返回内容
							
							ReportLogUtilService.addJocsInterface(jocsInterfaceLog);//写入日志操作
							//=============================接口日志写入完毕	
							log.info("在类PdWarehouseStockManagerImpl的方法getPdLogisticsCanModify中运行:接口日志写入完毕");
							return result;
						}
				}catch(Exception e){
						e.printStackTrace();
						log.info("在类PdWarehouseStockManagerImpl的方法getPdLogisticsCanModify中运行异常:"+e.toString());
						return "接口询问机制异常，请联系相关工作人员解决！";
				}
		}*/
		return result;
	}
	
	
	 public static Map toMap(String jsonString){

		    org.json.JSONObject jsonObject = new org.json.JSONObject(jsonString);
	        
	        Map result = new HashMap();
	        Iterator iterator = jsonObject.keys();
	        String key = null;
	        String value = null;
	        
	        while (iterator.hasNext()) {

	            key = (String) iterator.next();
	            value = jsonObject.getString(key);
	            result.put(key, value);

	        }
	        return result;

	    }

	
}
