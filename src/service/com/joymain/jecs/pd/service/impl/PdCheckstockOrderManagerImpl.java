
package com.joymain.jecs.pd.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdCheckstockOrder;
import com.joymain.jecs.pd.model.PdCheckstockOrderDetail;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.model.PdWarehouseStock;
import com.joymain.jecs.pd.dao.PdCheckstockOrderDao;
import com.joymain.jecs.pd.dao.PdWarehouseDao;
import com.joymain.jecs.pd.dao.PdWarehouseStockDao;
import com.joymain.jecs.pd.service.PdCheckstockOrderManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdCheckstockOrderManagerImpl extends BaseManager implements PdCheckstockOrderManager {
    private PdCheckstockOrderDao dao;
    private PdWarehouseStockDao pdWarehouseStockDao;
    private PdWarehouseDao pdWarehouseDao;
    public void setPdWarehouseDao(PdWarehouseDao pdWarehouseDao) {
		this.pdWarehouseDao = pdWarehouseDao;
	}

	public void setPdWarehouseStockDao(PdWarehouseStockDao pdWarehouseStockDao) {
		this.pdWarehouseStockDao = pdWarehouseStockDao;
	}

	public void confirmPdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder) {
		// TODO Auto-generated method stub
		PdWarehouse pdWarehouse =pdWarehouseDao.getPdWarehouse(pdCheckstockOrder.getWarehouseNo());
		pdWarehouse.setLockFlag("0");
		pdWarehouseDao.savePdWarehouse(pdWarehouse);
		dao.savePdCheckstockOrder(pdCheckstockOrder);
	}

	public void addPdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder) {
		// TODO Auto-generated method stub
		PdWarehouse pdWarehouse =pdWarehouseDao.getPdWarehouse(pdCheckstockOrder.getWarehouseNo());
		pdWarehouse.setLockFlag("1");
		pdWarehouseDao.savePdWarehouse(pdWarehouse);
		
		List list = pdWarehouseStockDao.getPdWarehouseStocksByWarehouseNo(pdCheckstockOrder.getWarehouseNo());
		Set pdCheckstockOrderDetails = new HashSet();
		if(!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				PdWarehouseStock pdWarehouseStock = (PdWarehouseStock) list.get(i);
				PdCheckstockOrderDetail pdCheckstockOrderDetail = new PdCheckstockOrderDetail();
				pdCheckstockOrderDetail.setProductNo(pdWarehouseStock.getProductNo());
				pdCheckstockOrderDetail.setSysQty(pdWarehouseStock.getNormalQty());
				pdCheckstockOrderDetail.setRealQty(pdWarehouseStock.getNormalQty());
				pdCheckstockOrderDetail.setPdCheckstockOrder(pdCheckstockOrder);
				pdCheckstockOrderDetails.add(pdCheckstockOrderDetail);
			}
			pdCheckstockOrder.setPdCheckstockOrderDetails(pdCheckstockOrderDetails);
		}
		dao.savePdCheckstockOrder(pdCheckstockOrder);
		
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdCheckstockOrderDao(PdCheckstockOrderDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCheckstockOrderManager#getPdCheckstockOrders(com.joymain.jecs.pd.model.PdCheckstockOrder)
     */
    public List getPdCheckstockOrders(final PdCheckstockOrder pdCheckstockOrder) {
        return dao.getPdCheckstockOrders(pdCheckstockOrder);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCheckstockOrderManager#getPdCheckstockOrder(String pcoNo)
     */
    public PdCheckstockOrder getPdCheckstockOrder(final String pcoNo) {
        return dao.getPdCheckstockOrder(new String(pcoNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCheckstockOrderManager#savePdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder)
     */
    public void savePdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder) {
        dao.savePdCheckstockOrder(pdCheckstockOrder);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdCheckstockOrderManager#removePdCheckstockOrder(String pcoNo)
     */
    public void removePdCheckstockOrder(final String pcoNo) {
        dao.removePdCheckstockOrder(new String(pcoNo));
    }
    //added for getPdCheckstockOrdersByCrm
    public List getPdCheckstockOrdersByCrm(CommonRecord crm, Pager pager){
	return dao.getPdCheckstockOrdersByCrm(crm,pager);
    }
}
