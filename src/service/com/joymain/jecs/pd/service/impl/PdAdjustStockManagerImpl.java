package com.joymain.jecs.pd.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdAdjustStock;
import com.joymain.jecs.pd.model.PdAdjustStockDetail;
import com.joymain.jecs.pd.dao.PdAdjustStockDao;
import com.joymain.jecs.pd.service.PdAdjustStockManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdAdjustStockManagerImpl extends BaseManager implements
		PdAdjustStockManager {
	private PdAdjustStockDao dao;
	private PdWarehouseStockManager pdWarehouseStockManager;

	public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	public void confirmPdAdjustStock(PdAdjustStock pdAdjustStock) {
		// TODO Auto-generated method stub
		if (!"1".equals(pdAdjustStock.getStockFlag())) {// 1以更新库存
			pdWarehouseStockManager.updatePdWarehouseStock(pdAdjustStock);
			pdAdjustStock.setStockFlag("1");
			pdAdjustStock.setOrderFlag(2);
			dao.savePdAdjustStock(pdAdjustStock);
		}

	}

	public boolean hasProductCountByAsNo(String asNo) {
		// TODO Auto-generated method stub
		boolean ret = false;
		PdAdjustStock pdAdjustStock = dao.getPdAdjustStock(asNo);
		Set sets = pdAdjustStock.getPdAdjustStockDetails();

		Iterator iterator = sets.iterator();
		while (iterator.hasNext()) {
			PdAdjustStockDetail PdAdjustStockDetail = (PdAdjustStockDetail) iterator
					.next();
			if (PdAdjustStockDetail.getQty() != 0) {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setPdAdjustStockDao(PdAdjustStockDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdAdjustStockManager#getPdAdjustStocks(com.joymain.jecs.pd.model.PdAdjustStock)
	 */
	public List getPdAdjustStocks(final PdAdjustStock pdAdjustStock) {
		return dao.getPdAdjustStocks(pdAdjustStock);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdAdjustStockManager#getPdAdjustStock(String
	 *      asNo)
	 */
	public PdAdjustStock getPdAdjustStock(final String asNo) {
		return dao.getPdAdjustStock(new String(asNo));
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdAdjustStockManager#savePdAdjustStock(PdAdjustStock
	 *      pdAdjustStock)
	 */
	public void savePdAdjustStock(PdAdjustStock pdAdjustStock) {
		dao.savePdAdjustStock(pdAdjustStock);
	}

	/**
	 * @see com.joymain.jecs.pd.service.PdAdjustStockManager#removePdAdjustStock(String
	 *      asNo)
	 */
	public void removePdAdjustStock(final String asNo) {
		dao.removePdAdjustStock(new String(asNo));
	}

	// added for getPdAdjustStocksByCrm
	public List getPdAdjustStocksByCrm(CommonRecord crm, Pager pager) {
		return dao.getPdAdjustStocksByCrm(crm, pager);
	}

	public List getSumGroupByAst(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getSumGroupByAst(crm);
	}
	
}
