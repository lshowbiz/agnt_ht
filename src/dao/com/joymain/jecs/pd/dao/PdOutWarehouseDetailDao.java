
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdOutWarehouseDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdOutWarehouseDetailDao extends Dao {

    /**
     * Retrieves all of the pdOutWarehouseDetails
     */
    public List getPdOutWarehouseDetails(PdOutWarehouseDetail pdOutWarehouseDetail);

    /**
     * Gets pdOutWarehouseDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdOutWarehouseDetail's uniNo
     * @return pdOutWarehouseDetail populated pdOutWarehouseDetail object
     */
    public PdOutWarehouseDetail getPdOutWarehouseDetail(final Long uniNo);

    /**
     * Saves a pdOutWarehouseDetail's information
     * @param pdOutWarehouseDetail the object to be saved
     */    
    public void savePdOutWarehouseDetail(PdOutWarehouseDetail pdOutWarehouseDetail);

    /**
     * Removes a pdOutWarehouseDetail from the database by uniNo
     * @param uniNo the pdOutWarehouseDetail's uniNo
     */
    public void removePdOutWarehouseDetail(final Long uniNo);
    //added for getPdOutWarehouseDetailsByCrm
    public List getPdOutWarehouseDetailsByCrm(CommonRecord crm, Pager pager);

	public List getTotalPdOutWarehouseDetails(CommonRecord crm);
	
	public List getPdOutWarehouseDetails(String productNo,String owNo,Long owdNo);
	
}

