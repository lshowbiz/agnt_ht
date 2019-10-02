
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdCheckstockOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdCheckstockOrderDao extends Dao {

    /**
     * Retrieves all of the pdCheckstockOrders
     */
    public List getPdCheckstockOrders(PdCheckstockOrder pdCheckstockOrder);

    /**
     * Gets pdCheckstockOrder's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param pcoNo the pdCheckstockOrder's pcoNo
     * @return pdCheckstockOrder populated pdCheckstockOrder object
     */
    public PdCheckstockOrder getPdCheckstockOrder(final String pcoNo);

    /**
     * Saves a pdCheckstockOrder's information
     * @param pdCheckstockOrder the object to be saved
     */    
    public void savePdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder);

    /**
     * Removes a pdCheckstockOrder from the database by pcoNo
     * @param pcoNo the pdCheckstockOrder's pcoNo
     */
    public void removePdCheckstockOrder(final String pcoNo);
    //added for getPdCheckstockOrdersByCrm
    public List getPdCheckstockOrdersByCrm(CommonRecord crm, Pager pager);
}

