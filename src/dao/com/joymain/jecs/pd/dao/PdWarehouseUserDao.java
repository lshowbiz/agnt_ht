
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdWarehouseUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdWarehouseUserDao extends Dao {

	/**
	 */
	public List getPdWarehouseByUser(String userCode);
    /**
     * Retrieves all of the pdWarehouseUsers
     */
    public List getPdWarehouseUsers(PdWarehouseUser pdWarehouseUser);

    /**
     * Gets pdWarehouseUser's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param wuId the pdWarehouseUser's wuId
     * @return pdWarehouseUser populated pdWarehouseUser object
     */
    public PdWarehouseUser getPdWarehouseUser(final Long wuId);

    /**
     * Saves a pdWarehouseUser's information
     * @param pdWarehouseUser the object to be saved
     */    
    public void savePdWarehouseUser(PdWarehouseUser pdWarehouseUser);

    /**
     * Removes a pdWarehouseUser from the database by wuId
     * @param wuId the pdWarehouseUser's wuId
     */
    public void removePdWarehouseUser(final Long wuId);
    //added for getPdWarehouseUsersByCrm
    public List getPdWarehouseUsersByCrm(CommonRecord crm, Pager pager);
}

