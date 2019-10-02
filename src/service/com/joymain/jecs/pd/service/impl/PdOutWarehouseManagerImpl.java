
package com.joymain.jecs.pd.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdOutWarehouse;
import com.joymain.jecs.pd.model.PdOutWarehouseDetail;
import com.joymain.jecs.pd.dao.PdOutWarehouseDao;
import com.joymain.jecs.pd.service.PdOutWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class PdOutWarehouseManagerImpl extends BaseManager implements PdOutWarehouseManager {
    public long getProductCountByOwNo(String owNo) {
		// TODO Auto-generated method stub
    	long ret =0;
		PdOutWarehouse pdOutWarehouse =dao.getPdOutWarehouse(owNo);
		Set sets = pdOutWarehouse.getPdOutWarehouseDetails();
		
		Iterator iterator =sets.iterator();
		while(iterator.hasNext()){
			ret += ((PdOutWarehouseDetail)(iterator.next())).getQty();
		}
		return ret;
	}
	private PdOutWarehouseDao dao;
	private PdWarehouseStockManager pdWarehouseStockManager;

    public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdOutWarehouseDao(PdOutWarehouseDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdOutWarehouseManager#getPdOutWarehouses(com.joymain.jecs.pd.model.PdOutWarehouse)
     */
    public List getPdOutWarehouses(final PdOutWarehouse pdOutWarehouse) {
        return dao.getPdOutWarehouses(pdOutWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdOutWarehouseManager#getPdOutWarehouse(String owNo)
     */
    public PdOutWarehouse getPdOutWarehouse(final String owNo) {
        return dao.getPdOutWarehouse(new String(owNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdOutWarehouseManager#savePdOutWarehouse(PdOutWarehouse pdOutWarehouse)
     */
    public void savePdOutWarehouse(PdOutWarehouse pdOutWarehouse) {
        dao.savePdOutWarehouse(pdOutWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdOutWarehouseManager#removePdOutWarehouse(String owNo)
     */
    public void removePdOutWarehouse(final String owNo) {
        dao.removePdOutWarehouse(new String(owNo));
    }
    //added for getPdOutWarehousesByCrm
    public List getPdOutWarehousesByCrm(CommonRecord crm, Pager pager){
	return dao.getPdOutWarehousesByCrm(crm,pager);
    }

	public void confirmPdOutWarehouse(PdOutWarehouse pdOutWarehouse) {
		// TODO Auto-generated method stub
		if(!"1".equals(pdOutWarehouse.getStockFlag())){//1以更新库存
			pdWarehouseStockManager.updatePdWarehouseStock(pdOutWarehouse);
			pdOutWarehouse.setStockFlag("1");
			pdOutWarehouse.setOrderFlag(2);
			dao.savePdOutWarehouse(pdOutWarehouse);
		}
		
	}

	public List getSumGroupByOwt(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getSumGroupByOwt(crm);
	}

	public List getOutDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getOutDetails(crm);
	}
    
    
}
