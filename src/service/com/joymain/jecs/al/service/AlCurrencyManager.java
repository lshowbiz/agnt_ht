
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.AlCurrency;
import com.joymain.jecs.al.dao.AlCurrencyDao;

public interface AlCurrencyManager extends Manager {
    /**
     * Retrieves all of the alCurrencys
     */
    public List getAlCurrencys(AlCurrency alCurrency);

    /**
     * Gets alCurrency's information based on currencyId.
     * @param currencyId the alCurrency's currencyId
     * @return alCurrency populated alCurrency object
     */
    public AlCurrency getAlCurrency(final String currencyId);

    /**
     * Saves a alCurrency's information
     * @param alCurrency the object to be saved
     */
    public void saveAlCurrency(AlCurrency alCurrency);

    /**
     * Removes a alCurrency from the database by currencyId
     * @param currencyId the alCurrency's currencyId
     */
    public void removeAlCurrency(final String currencyId);
}

