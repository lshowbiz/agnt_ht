
package com.joymain.jecs.pd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pd.model.PdExchangeOrderBack;
import com.joymain.jecs.pd.dao.PdExchangeOrderBackDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface PdExchangeOrderBackManager extends Manager {
    /**
     * Retrieves all of the pdExchangeOrderBacks
     */
    public List getPdExchangeOrderBacks(PdExchangeOrderBack pdExchangeOrderBack);

    /**
     * Gets pdExchangeOrderBack's information based on uniNo.
     * @param uniNo the pdExchangeOrderBack's uniNo
     * @return pdExchangeOrderBack populated pdExchangeOrderBack object
     */
    public PdExchangeOrderBack getPdExchangeOrderBack(final String uniNo);

    /**
     * Saves a pdExchangeOrderBack's information
     * @param pdExchangeOrderBack the object to be saved
     */
    public void savePdExchangeOrderBack(PdExchangeOrderBack pdExchangeOrderBack);

    /**
     * Removes a pdExchangeOrderBack from the database by uniNo
     * @param uniNo the pdExchangeOrderBack's uniNo
     */
    public void removePdExchangeOrderBack(final String uniNo);
    //added for getPdExchangeOrderBacksByCrm
    public List getPdExchangeOrderBacksByCrm(CommonRecord crm, Pager pager);
}

