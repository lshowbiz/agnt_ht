
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdWarehouse;
import com.joymain.jecs.pd.model.PdWarehouseArea;
import com.joymain.jecs.pd.dao.PdWarehouseAreaDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdWarehouseAreaManager extends Manager {
	public List getPdWarehouses(String companyCode,String areaCode);
    /**
     * Retrieves all of the pdWarehouseAreas
     */
    public List getPdWarehouseAreas(PdWarehouseArea pdWarehouseArea);

    /**
     * Gets pdWarehouseArea's information based on waId.
     * @param waId the pdWarehouseArea's waId
     * @return pdWarehouseArea populated pdWarehouseArea object
     */
    public PdWarehouseArea getPdWarehouseArea(final String waId);

    /**
     * Saves a pdWarehouseArea's information
     * @param pdWarehouseArea the object to be saved
     */
    public void savePdWarehouseArea(PdWarehouseArea pdWarehouseArea);

    /**
     * Removes a pdWarehouseArea from the database by waId
     * @param waId the pdWarehouseArea's waId
     */
    public void removePdWarehouseArea(final String waId);
    //added for getPdWarehouseAreasByCrm
    public List getPdWarehouseAreasByCrm(CommonRecord crm, Pager pager);
	public String getPdWarehouseNo(String companyCode, String province,
			String shNo);
}

