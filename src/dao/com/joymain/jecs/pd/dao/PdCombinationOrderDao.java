
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdCombinationOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdCombinationOrderDao extends Dao {

    /**
     * Retrieves all of the pdCombinationOrders
     */
    public List getPdCombinationOrders(PdCombinationOrder pdCombinationOrder);

    /**
     * Gets pdCombinationOrder's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param pcNo the pdCombinationOrder's pcNo
     * @return pdCombinationOrder populated pdCombinationOrder object
     */
    public PdCombinationOrder getPdCombinationOrder(final String pcNo);

    /**
     * Saves a pdCombinationOrder's information
     * @param pdCombinationOrder the object to be saved
     */    
    public void savePdCombinationOrder(PdCombinationOrder pdCombinationOrder);

    /**
     * Removes a pdCombinationOrder from the database by pcNo
     * @param pcNo the pdCombinationOrder's pcNo
     */
    public void removePdCombinationOrder(final String pcNo);
    //added for getPdCombinationOrdersByCrm
    public List getPdCombinationOrdersByCrm(CommonRecord crm, Pager pager);
    
    public void removePdCombinationDetails(PdCombinationOrder pdCombinationOrder);
}

