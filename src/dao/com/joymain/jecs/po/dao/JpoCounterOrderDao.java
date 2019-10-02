
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoCounterOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoCounterOrderDao extends Dao {

    /**
     * Retrieves all of the jpoCounterOrders
     */
    public List getJpoCounterOrders(JpoCounterOrder jpoCounterOrder);

    /**
     * Gets jpoCounterOrder's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param coNo the jpoCounterOrder's coNo
     * @return jpoCounterOrder populated jpoCounterOrder object
     */
    public JpoCounterOrder getJpoCounterOrder(final String coNo);

    /**
     * Saves a jpoCounterOrder's information
     * @param jpoCounterOrder the object to be saved
     */    
    public void saveJpoCounterOrder(JpoCounterOrder jpoCounterOrder);

    /**
     * Removes a jpoCounterOrder from the database by coNo
     * @param coNo the jpoCounterOrder's coNo
     */
    public void removeJpoCounterOrder(final String coNo);
    //added for getJpoCounterOrdersByCrm
    public List getJpoCounterOrdersByCrm(CommonRecord crm, Pager pager);
    public List getJpoCounterOrdersByCrmSum(CommonRecord crm);
}

