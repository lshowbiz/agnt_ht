
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdShipmentsDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdShipmentsDetailDao extends Dao {

    /**
     * Retrieves all of the pdShipmentsDetails
     */
    public List getPdShipmentsDetails(PdShipmentsDetail pdShipmentsDetail);

    /**
     * Gets pdShipmentsDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param sdId the pdShipmentsDetail's sdId
     * @return pdShipmentsDetail populated pdShipmentsDetail object
     */
    public PdShipmentsDetail getPdShipmentsDetail(final Long sdId);

    /**
     * Saves a pdShipmentsDetail's information
     * @param pdShipmentsDetail the object to be saved
     */    
    public void savePdShipmentsDetail(PdShipmentsDetail pdShipmentsDetail);

    /**
     * Removes a pdShipmentsDetail from the database by sdId
     * @param sdId the pdShipmentsDetail's sdId
     */
    public void removePdShipmentsDetail(final Long sdId);
    //added for getPdShipmentsDetailsByCrm
    public List getPdShipmentsDetailsByCrm(CommonRecord crm, Pager pager);

	public PdShipmentsDetail getPdShipmentsDetail(String orderNo,
			String productNo);
}

