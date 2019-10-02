
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdFlitWarehouseDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdFlitWarehouseDetailDao extends Dao {

    /**
     * Retrieves all of the pdFlitWarehouseDetails
     */
    public List getPdFlitWarehouseDetails(PdFlitWarehouseDetail pdFlitWarehouseDetail);

    /**
     * Gets pdFlitWarehouseDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdFlitWarehouseDetail's uniNo
     * @return pdFlitWarehouseDetail populated pdFlitWarehouseDetail object
     */
    public PdFlitWarehouseDetail getPdFlitWarehouseDetail(final Long uniNo);

    /**
     * Saves a pdFlitWarehouseDetail's information
     * @param pdFlitWarehouseDetail the object to be saved
     */    
    public void savePdFlitWarehouseDetail(PdFlitWarehouseDetail pdFlitWarehouseDetail);

    /**
     * Removes a pdFlitWarehouseDetail from the database by uniNo
     * @param uniNo the pdFlitWarehouseDetail's uniNo
     */
    public void removePdFlitWarehouseDetail(final Long uniNo);
    //added for getPdFlitWarehouseDetailsByCrm
    public List getPdFlitWarehouseDetailsByCrm(CommonRecord crm, Pager pager);

	public List getTotalPdFlitWarehouseDetails(CommonRecord crm);

	public List getPdFlitWarehouseDetails(String productNo, String fwNo);

	public List getFlitWarehouseStaticsByCrm(CommonRecord crm, String flag);

	public List getOnWayStaticsByCrm(CommonRecord crm);

}

