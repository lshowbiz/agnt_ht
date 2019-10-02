
package com.joymain.jecs.pd.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdEnterWarehouse;
import com.joymain.jecs.pd.model.PdEnterWarehouseDetail;
import com.joymain.jecs.pd.dao.PdEnterWarehouseDao;
import com.joymain.jecs.pd.service.PdEnterWarehouseManager;
import com.joymain.jecs.pd.service.PdWarehouseStockManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
 
public class PdEnterWarehouseManagerImpl extends BaseManager implements PdEnterWarehouseManager {
    public void confirmPdEnterWarehouse(PdEnterWarehouse pdEnterWarehouse) {
		// TODO Auto-generated method stub
    	if(!"1".equals(pdEnterWarehouse.getStockFlag())){//1以更新库存
			pdWarehouseStockManager.updatePdWarehouseStock(pdEnterWarehouse);
			pdEnterWarehouse.setStockFlag("1");
			dao.savePdEnterWarehouse(pdEnterWarehouse);
		}
		
		
	}

	public Long getProductCountByEwNo(String ewNo) {
		// TODO Auto-generated method stub
    	PdEnterWarehouse pdEnterWarehouse = dao.getPdEnterWarehouse(ewNo);
		long ret = 0;
		Set sets = pdEnterWarehouse.getPdEnterWarehouseDetails();
		Iterator iterator =sets.iterator();
		while(iterator.hasNext()){
			ret += ((PdEnterWarehouseDetail)(iterator.next())).getQty();
		}
		return ret;
	}
	private PdEnterWarehouseDao dao;
    private PdWarehouseStockManager pdWarehouseStockManager;
    public void setPdWarehouseStockManager(
			PdWarehouseStockManager pdWarehouseStockManager) {
		this.pdWarehouseStockManager = pdWarehouseStockManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdEnterWarehouseDao(PdEnterWarehouseDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdEnterWarehouseManager#getPdEnterWarehouses(com.joymain.jecs.pd.model.PdEnterWarehouse)
     */
    public List getPdEnterWarehouses(final PdEnterWarehouse pdEnterWarehouse) {
        return dao.getPdEnterWarehouses(pdEnterWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdEnterWarehouseManager#getPdEnterWarehouse(String ewNo)
     */
    public PdEnterWarehouse getPdEnterWarehouse(final String ewNo) {
        return dao.getPdEnterWarehouse(new String(ewNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdEnterWarehouseManager#savePdEnterWarehouse(PdEnterWarehouse pdEnterWarehouse)
     */
    public void savePdEnterWarehouse(PdEnterWarehouse pdEnterWarehouse) {
        dao.savePdEnterWarehouse(pdEnterWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdEnterWarehouseManager#removePdEnterWarehouse(String ewNo)
     */
    public void removePdEnterWarehouse(final String ewNo) {
        dao.removePdEnterWarehouse(new String(ewNo));
    }
    //added for getPdEnterWarehousesByCrm
    public List getPdEnterWarehousesByCrm(CommonRecord crm, Pager pager){
	return dao.getPdEnterWarehousesByCrm(crm,pager);
    }

	public List getEnterDetails(CommonRecord crm) {
		// TODO Auto-generated method stub
		return dao.getEnterDetails(crm);
	}
    
}
