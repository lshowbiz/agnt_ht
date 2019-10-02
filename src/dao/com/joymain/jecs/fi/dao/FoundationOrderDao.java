
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FoundationOrderDao extends Dao {

    /**
     * Retrieves all of the foundationOrders
     */
    public List getFoundationOrders(FoundationOrder foundationOrder);

    /**
     * Gets foundationOrder's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param orderId the foundationOrder's orderId
     * @return foundationOrder populated foundationOrder object
     */
    public FoundationOrder getFoundationOrder(final Long orderId);

    /**
     * Saves a foundationOrder's information
     * @param foundationOrder the object to be saved
     */    
    public void saveFoundationOrder(FoundationOrder foundationOrder);
    
    /**
     * Saves a foundationOrder's information
     * @param foundationOrder the object to be saved
     */    
    public Long saveFoundationOrder2(FoundationOrder foundationOrder);

    /**
     * Removes a foundationOrder from the database by orderId
     * @param orderId the foundationOrder's orderId
     */
    public void removeFoundationOrder(final Long orderId);
    //added for getFoundationOrdersByCrm
    public List getFoundationOrdersByCrm(CommonRecord crm, Pager pager);
    
    //added for getFoundationOrdersByCrm
    public List foundationItemsByCrm(CommonRecord crm, Pager pager);
    
    public List getOrdersByItemTypeAndTime(String userCode);
}

