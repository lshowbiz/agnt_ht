
package com.joymain.jecs.pd.service.impl;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.model.PdWarehouseArea;
import com.joymain.jecs.pd.dao.PdWarehouseAreaDao;
import com.joymain.jecs.pd.service.PdWarehouseAreaManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class PdWarehouseAreaManagerImpl extends BaseManager implements PdWarehouseAreaManager {
    public String getPdWarehouseNo(String companyCode, String province,
			String shNo) {
		// TODO Auto-generated method stub
    	String ret = null;
    	List list = dao.getPdWarehouseNo(companyCode, province, shNo);
    	if(!list.isEmpty()){
    		ret = (String) ((Map) list.get(0)).get("warehouse_No".toUpperCase());
    	}
    	/*List list = dao.getPdWarehouses(companyCode,province,shNo);
    	if(!list.isEmpty()){
    		PdWarehouseArea pdWarehouseArea =(PdWarehouseArea) list.get(0);
    		ret = pdWarehouseArea.getWarehouseNo();
    	}*/
    	
    	return ret;
	}

	public List getPdWarehouses(String companyCode, String areaCode) {
		// TODO Auto-generated method stub
    	return dao.getPdWarehouses(companyCode,areaCode);
    	
	}
	private PdWarehouseAreaDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setPdWarehouseAreaDao(PdWarehouseAreaDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseAreaManager#getPdWarehouseAreas(com.joymain.jecs.pd.model.PdWarehouseArea)
     */
    public List getPdWarehouseAreas(final PdWarehouseArea pdWarehouseArea) {
        return dao.getPdWarehouseAreas(pdWarehouseArea);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseAreaManager#getPdWarehouseArea(String waId)
     */
    public PdWarehouseArea getPdWarehouseArea(final String waId) {
        return dao.getPdWarehouseArea(new Long(waId));
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseAreaManager#savePdWarehouseArea(PdWarehouseArea pdWarehouseArea)
     */
    public void savePdWarehouseArea(PdWarehouseArea pdWarehouseArea) {
        dao.savePdWarehouseArea(pdWarehouseArea);
    }

    /**
     * @see com.joymain.jecs.pd.service.PdWarehouseAreaManager#removePdWarehouseArea(String waId)
     */
    public void removePdWarehouseArea(final String waId) {
        dao.removePdWarehouseArea(new Long(waId));
    }
    //added for getPdWarehouseAreasByCrm
    public List getPdWarehouseAreasByCrm(CommonRecord crm, Pager pager){
	return dao.getPdWarehouseAreasByCrm(crm,pager);
    }
}
