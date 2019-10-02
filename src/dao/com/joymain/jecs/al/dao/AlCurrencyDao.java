
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.AlCurrency;

public interface AlCurrencyDao extends Dao {

    /**
     * Retrieves all of the alCurrencys
     */
    public List getAlCurrencys(AlCurrency alCurrency);

    /**
     * Gets alCurrency's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param currencyId the alCurrency's currencyId
     * @return alCurrency populated alCurrency object
     */
    public AlCurrency getAlCurrency(final Long currencyId);

    /**
     * Saves a alCurrency's information
     * @param alCurrency the object to be saved
     */    
    public void saveAlCurrency(AlCurrency alCurrency);

    /**
     * Removes a alCurrency from the database by currencyId
     * @param currencyId the alCurrency's currencyId
     */
    public void removeAlCurrency(final Long currencyId);
}

