
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdWarehouseArea;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdWarehouseAreaDao extends Dao {
	public List getPdWarehouseNo(String companyCode, String province,
			String shNo);
    /**
     * Retrieves all of the pdWarehouseAreas
     */
    public List getPdWarehouseAreas(PdWarehouseArea pdWarehouseArea);

    /**
     * Gets pdWarehouseArea's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param waId the pdWarehouseArea's waId
     * @return pdWarehouseArea populated pdWarehouseArea object
     */
    public PdWarehouseArea getPdWarehouseArea(final Long waId);

    /**
     * Saves a pdWarehouseArea's information
     * @param pdWarehouseArea the object to be saved
     */    
    public void savePdWarehouseArea(PdWarehouseArea pdWarehouseArea);

    /**
     * Removes a pdWarehouseArea from the database by waId
     * @param waId the pdWarehouseArea's waId
     */
    public void removePdWarehouseArea(final Long waId);
    //added for getPdWarehouseAreasByCrm
    public List getPdWarehouseAreasByCrm(CommonRecord crm, Pager pager);

	public List getPdWarehouses(String companyCode, String areaCode);

	public List getPdWarehouses(String companyCode, String province,
			String shNo);
}

