
package com.joymain.jecs.pd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface PdExchangeOrderBackDao extends Dao {

    /**
     * Retrieves all of the pdExchangeOrderBacks
     */
    public List getPdExchangeOrderBacks(PdExchangeOrderBack pdExchangeOrderBack);

    /**
     * Gets pdExchangeOrderBack's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param uniNo the pdExchangeOrderBack's uniNo
     * @return pdExchangeOrderBack populated pdExchangeOrderBack object
     */
    public PdExchangeOrderBack getPdExchangeOrderBack(final Long uniNo);

    /**
     * Saves a pdExchangeOrderBack's information
     * @param pdExchangeOrderBack the object to be saved
     */    
    public void savePdExchangeOrderBack(PdExchangeOrderBack pdExchangeOrderBack);

    /**
     * Removes a pdExchangeOrderBack from the database by uniNo
     * @param uniNo the pdExchangeOrderBack's uniNo
     */
    public void removePdExchangeOrderBack(final Long uniNo);
    //added for getPdExchangeOrderBacksByCrm
    public List getPdExchangeOrderBacksByCrm(CommonRecord crm, Pager pager);
}

