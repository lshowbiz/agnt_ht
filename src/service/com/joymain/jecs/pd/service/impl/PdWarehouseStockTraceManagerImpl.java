package com.joymain.jecs.pd.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.pd.dao.PdFlitWarehouseDetailDao;
import com.joymain.jecs.pd.dao.PdWarehouseStockTraceDao;
import com.joymain.jecs.pd.model.PdWarehouseStockTrace;
import com.joymain.jecs.pd.service.PdWarehouseStockTraceManager;
import com.joymain.jecs.pm.model.JpmProductSale;
import com.joymain.jecs.pm.model.JpmProductSaleNew;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdWarehouseStockTraceManagerImpl extends BaseManager implements
		PdWarehouseStockTraceManager {
	private PdWarehouseStockTraceDao dao;
	private JpmProductSaleManager jpmProductSaleManager;
	private PdFlitWarehouseDetailDao pdFlitWarehouseDetailDao;
	public void setPdFlitWarehouseDetailDao(
			PdFlitWarehouseDetailDao pdFlitWarehouseDetailDao) {
		this.pdFlitWarehouseDetailDao = pdFlitWarehouseDetailDao;
	}

	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}
	
	

	public List getStockTraceDetailByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		List begainStocks = dao.getBeginStocksByCrm(crm);
		List endStocks = dao.getEndStocksByCrm(crm);
		//���
		/*List enterStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "1");//���
		List returnStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "4");//�˻�
		List sameFlitInStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "2");//ͬ��˾����
		List difFlitInStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "5");//ͬ��˾����
		List adjustInStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "3");//ͬ��˾����
		*/
		List stocks = dao.getStockTraceDetailByCrm(crm);
		List onWay = pdFlitWarehouseDetailDao.getOnWayStaticsByCrm(crm);
		
		//-------------
		Map begainStockMap = this.rebuildList2MapBase(begainStocks);
		Map endStockMap = this.rebuildList2MapBase(endStocks);
		Map stockMap = this.rebuildDetailList2Map(stocks);
		Map onWaymap = this.rebuildList2MapBase(onWay);
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		/*List products = jpmProductSaleManager.getJpmProductSalesByCrm(crm, null);*/
		List products = jpmProductSaleManager.getJpmProductSalesByCrm(crm, null);
		List retList = new ArrayList();
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		for(int i=0;i<products.size();i++){
			Map map = new HashMap();
			String productNo = ((JpmProductSaleNew)products.get(i)).getJpmProduct().getProductNo();
			map.put("productNo", productNo);
			map.put("productName", ((JpmProductSaleNew)products.get(i)).getProductName());
			map.put("saleMode", ((JpmProductSaleNew)products.get(i)).getJpmProduct().getSmNo());
			map.put("begainStock", this.getBigDecimal(begainStockMap.get(productNo)));
			map.put("endStock", this.getBigDecimal(endStockMap.get(productNo)));
			
			//type=1
			map.put("sum-1-0", this.getBigDecimal(stockMap.get(productNo+"-1-0")));
			
			
			//type=2
			for(int j=1;j<=6;j++){
				map.put("sum-2-"+String.valueOf(j), this.getBigDecimal(stockMap.get(productNo+"-2-"+String.valueOf(j))));
			}
			
			//type=3
			for(int j=-10;j<=10;j++){
				map.put("sum-3-"+String.valueOf(j), this.getBigDecimal(stockMap.get(productNo+"-3-"+String.valueOf(j))));
			}
			//type=4
			map.put("sum-4-0", this.getBigDecimal(stockMap.get(productNo+"-4-0")));
			map.put("sum-4-1", this.getBigDecimal(stockMap.get(productNo+"-4-1")));
			map.put("sum-4-2", this.getBigDecimal(stockMap.get(productNo+"-4-2")));
			map.put("sum-4-3", this.getBigDecimal(stockMap.get(productNo+"-4-3")));
			//type=5ʱ
			map.put("sum-5-1", this.getBigDecimal(stockMap.get(productNo+"-5-1")));
			map.put("sum-5--1", this.getBigDecimal(stockMap.get(productNo+"-5--1")));
			
			//type=6
			map.put("sum-6-0", this.getBigDecimal(stockMap.get(productNo+"-6-0")));
			//type=7
			map.put("sum-7-1", this.getBigDecimal(stockMap.get(productNo+"-7-1")));
			map.put("sum-7-2", this.getBigDecimal(stockMap.get(productNo+"-7-2")));
			map.put("sum-7--1", this.getBigDecimal(stockMap.get(productNo+"-7--1")));
			map.put("sum-7--2", this.getBigDecimal(stockMap.get(productNo+"-7--2")));
			//else
			//type=8
			map.put("sum-8-0", this.getBigDecimal(stockMap.get(productNo+"-8-0")));
			//type=9
			map.put("sum-9-0", this.getBigDecimal(stockMap.get(productNo+"-9-0")));
			
			//type=10
			map.put("sum-10-0", this.getBigDecimal(stockMap.get(productNo+"-10-0")));
//			for(int j=4;j<=9;j++){
//				map.put("sum-"+String.valueOf(j)+"-0", this.getBigDecimal(stockMap.get(productNo+"-"+String.valueOf(j)+"-0")));
//			}
//			for(int j=1;j<=10;j++){
//				map.put("action"+String.valueOf(j), this.getBigDecimal(stockMap.get(productNo+"-"+String.valueOf(j))));
//			}
//			map.put("action-2", this.getBigDecimal(stockMap.get(productNo+"--2")));
//			map.put("action-5", this.getBigDecimal(stockMap.get(productNo+"--5")));
//			map.put("action-9", this.getBigDecimal(stockMap.get(productNo+"--9")));
//			map.put("action-10", this.getBigDecimal(stockMap.get(productNo+"--10")));
			map.put("onWay", this.getBigDecimal(onWaymap.get(productNo)));
			retList.add(map);
		}
		return retList;
	
	}
	/**
	 * �������汨��
	 */
	public List getPbWarehouseStocksTraceFinceReport(CommonRecord crm) {
//		 TODO Auto-generated method stub
		List begainStocks = dao.getBeginStocksByCrm(crm);
		List endStocks = dao.getEndStocksByCrm(crm);
		
		List stocks = dao.getStockTraceDetailByCrm(crm);
		List onWay = pdFlitWarehouseDetailDao.getOnWayStaticsByCrm(crm);
		Map begainStockMap = this.rebuildList2MapBase(begainStocks);
		Map endStockMap = this.rebuildList2MapBase(endStocks);
		Map stockMap = this.rebuildList2Map(stocks);
		Map onWaymap = this.rebuildList2MapBase(onWay);
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		/*List products = jpmProductSaleManager.getJpmProductSalesByCrm(crm, null);*/
		List products = jpmProductSaleManager.getJpmProductSalesByCrm(crm, null);
		List retList = new ArrayList();
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		for(int i=0;i<products.size();i++){
			Map map = new HashMap();
			String productNo = ((JpmProductSaleNew)products.get(i)).getJpmProduct().getProductNo();
			map.put("productNo", productNo);
			map.put("productName", ((JpmProductSaleNew)products.get(i)).getProductName());
			map.put("saleMode", ((JpmProductSaleNew)products.get(i)).getJpmProduct().getSmNo());
			map.put("begainStock", this.getBigDecimal(begainStockMap.get(productNo)));
			map.put("endStock", this.getBigDecimal(endStockMap.get(productNo)));
			
			for(int j=1;j<=10;j++){
				map.put("action"+String.valueOf(j), this.getBigDecimal(stockMap.get(productNo+"-"+String.valueOf(j))));
			}
			map.put("action-2", this.getBigDecimal(stockMap.get(productNo+"--2")));
			map.put("action-5", this.getBigDecimal(stockMap.get(productNo+"--5")));
			map.put("action-9", this.getBigDecimal(stockMap.get(productNo+"--9")));
			map.put("action-10", this.getBigDecimal(stockMap.get(productNo+"--10")));
			map.put("action100", this.getBigDecimal(stockMap.get(productNo+"-100")));
			map.put("onWay", this.getBigDecimal(onWaymap.get(productNo)));
			retList.add(map);
		}
		return retList;
		
	}

	public List getPdWarehouseStockReportByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		List begainStocks = dao.getBeginStocksByCrm(crm);
		List endStocks = dao.getEndStocksByCrm(crm);
		//���
		/*List enterStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "1");//���
		List returnStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "4");//�˻�
		List sameFlitInStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "2");//ͬ��˾����
		List difFlitInStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "5");//ͬ��˾����
		List adjustInStocks = dao.getPdWarehouseStockTraceStaticByCrm(crm, "3");//ͬ��˾����
		*/
		List stocks = dao.getPdWarehouseStockTraceStaticByCrm(crm);
		List onWay = pdFlitWarehouseDetailDao.getOnWayStaticsByCrm(crm);
		Map begainStockMap = this.rebuildList2MapBase(begainStocks);
		Map endStockMap = this.rebuildList2MapBase(endStocks);
		Map stockMap = this.rebuildList2Map(stocks);
		Map onWaymap = this.rebuildList2MapBase(onWay);
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		/*List products = jpmProductSaleManager.getJpmProductSalesByCrm(crm, null);*/
		List products = jpmProductSaleManager.getJpmProductSalesByCrm(crm, null);
		List retList = new ArrayList();
		//WuCF JpmProductSaleNew Modify By WuCF 20130917
		for(int i=0;i<products.size();i++){
			Map map = new HashMap();
			String productNo = ((JpmProductSaleNew)products.get(i)).getJpmProduct().getProductNo();
			map.put("productNo", productNo);
			map.put("productName", ((JpmProductSaleNew)products.get(i)).getProductName());
			map.put("saleMode", ((JpmProductSaleNew)products.get(i)).getJpmProduct().getSmNo());
			map.put("begainStock", this.getBigDecimal(begainStockMap.get(productNo)));
			map.put("endStock", this.getBigDecimal(endStockMap.get(productNo)));
			
			for(int j=1;j<=10;j++){
				map.put("action"+String.valueOf(j), this.getBigDecimal(stockMap.get(productNo+"-"+String.valueOf(j))));
			}
			map.put("action-2", this.getBigDecimal(stockMap.get(productNo+"--2")));
			map.put("action-5", this.getBigDecimal(stockMap.get(productNo+"--5")));
			map.put("action-9", this.getBigDecimal(stockMap.get(productNo+"--9")));
			map.put("action-10", this.getBigDecimal(stockMap.get(productNo+"--10")));
			map.put("action100", this.getBigDecimal(stockMap.get(productNo+"-100")));
			map.put("onWay", this.getBigDecimal(onWaymap.get(productNo)));
			retList.add(map);
		}
		return retList;
	}

	public List getPdWarehouseStockTraceStaticByCrm(CommonRecord crm,
			String actionType) {
		// TODO Auto-generated method stub
		return dao.getPdWarehouseStockTraceStaticByCrm(crm,actionType);
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setPdWarehouseStockTraceDao(PdWarehouseStockTraceDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseStockTraceManager#getPdWarehouseStockTraces(com.joymain.jecs.pd.model.PdWarehouseStockTrace)
	 */
	public List getPdWarehouseStockTraces(
			final PdWarehouseStockTrace pdWarehouseStockTrace) {
		return dao.getPdWarehouseStockTraces(pdWarehouseStockTrace);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseStockTraceManager#getPdWarehouseStockTrace(String
	 *      uniNo)
	 */
	public PdWarehouseStockTrace getPdWarehouseStockTrace(final String uniNo) {
		return dao.getPdWarehouseStockTrace(new Long(uniNo));
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseStockTraceManager#savePdWarehouseStockTrace(PdWarehouseStockTrace
	 *      pdWarehouseStockTrace)
	 */
	public void savePdWarehouseStockTrace(
			PdWarehouseStockTrace pdWarehouseStockTrace) {
		dao.savePdWarehouseStockTrace(pdWarehouseStockTrace);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdWarehouseStockTraceManager#removePdWarehouseStockTrace(String
	 *      uniNo)
	 */
	public void removePdWarehouseStockTrace(final String uniNo) {
		dao.removePdWarehouseStockTrace(new Long(uniNo));
	}

	// added for getPdWarehouseStockTracesByCrm
	public List getPdWarehouseStockTracesByCrm(CommonRecord crm, Pager pager) {
		return dao.getPdWarehouseStockTracesByCrm(crm, pager);
	}

	public List getBeginStocksByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getBeginStocksByCrm(crm);
	}

	public List getEndStocksByCrm(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getEndStocksByCrm(crm);
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
	private Map rebuildList2Map(List list) {
		Map retMap = new HashMap();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				retMap.put((String) map.get("PRODUCT_NO") + "-"
						+ (String) map.get("ACTION_TYPE"), (BigDecimal) map
						.get("QTY"));
			}
		}
		return retMap;
	}
	
	private Map rebuildDetailList2Map(List list) {
		Map retMap = new HashMap();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				retMap.put((String) map.get("PRODUCT_NO") + "-"
						+ (String) map.get("ORDER_TYPE")+"-"+(String) map.get("SUB_TYPE"), (BigDecimal) map
						.get("QTY"));
			}
		}
		return retMap;
	}
	/**
	 * ��ֵת��0
	 * @param l
	 * @return
	 */
	private BigDecimal getBigDecimal (Object l){
		if(l == null){
			return new BigDecimal(0);
		}else{
			return (BigDecimal) l;
		}
	}
	
}
