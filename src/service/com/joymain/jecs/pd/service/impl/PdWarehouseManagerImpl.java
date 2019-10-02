
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.dao.PdWarehouseDao;
import com.joymain.jecs.pd.service.PdWarehouseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdWarehouseManagerImpl extends BaseManager implements PdWarehouseManager {
	public boolean existWarehouseNoByCompany(String warehouseNo,
			String companyCode) {
		// TODO Auto-generated method stub
		CommonRecord crm = new CommonRecord();
		try {
			crm.setValue("warehouseNo", warehouseNo);
			crm.setValue("companyCode", companyCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("e="+e.getMessage());
		}
		List list = dao.getPdWarehousesByCrm(crm, null);
		if(list != null && list.size()>0){
			return true;
		}
		return false;
	}
	public boolean existWarehouseNo(String warehouseNo) {
		// TODO Auto-generated method stub
    	
		CommonRecord crm = new CommonRecord();
		try {
			crm.setValue("warehouseNo", warehouseNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.debug("e="+e.getMessage());
		}
		List list = dao.getPdWarehousesByCrm(crm, null);
		if(list != null && list.size()>0){
			return true;
		}
		return false;
	}
    private PdWarehouseDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdWarehouseDao(PdWarehouseDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseManager#getPdWarehouses(com.joymain.jecs.pd.model.PdWarehouse)
     */
    public List getPdWarehouses(final PdWarehouse pdWarehouse) {
        return dao.getPdWarehouses(pdWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseManager#getPdWarehouse(String warehouseNo)
     */
    public PdWarehouse getPdWarehouse(final String warehouseNo) {
        return dao.getPdWarehouse(new String(warehouseNo));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseManager#savePdWarehouse(PdWarehouse pdWarehouse)
     */
    public void savePdWarehouse(PdWarehouse pdWarehouse) {
        dao.savePdWarehouse(pdWarehouse);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseManager#removePdWarehouse(String warehouseNo)
     */
    public void removePdWarehouse(final String warehouseNo) {
        dao.removePdWarehouse(new String(warehouseNo));
    }
    //added for getPdWarehousesByCrm
    public List getPdWarehousesByCrm(CommonRecord crm, Pager pager){
	return dao.getPdWarehousesByCrm(crm,pager);
    }
    public List getPdWarehousesByCrm(CommonRecord crm){
    	return dao.getPdWarehousesByCrm(crm);
    }
}
