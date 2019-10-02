
package com.joymain.jecs.al.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.AlCurrency;
import com.joymain.jecs.al.dao.AlCurrencyDao;
import com.joymain.jecs.al.service.AlCurrencyManager;

public class AlCurrencyManagerImpl extends BaseManager implements AlCurrencyManager {
    private AlCurrencyDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAlCurrencyDao(AlCurrencyDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.AlCurrencyManager#getAlCurrencys(com.joymain.jecs.al.model.AlCurrency)
     */
    public List getAlCurrencys(final AlCurrency alCurrency) {
        return dao.getAlCurrencys(alCurrency);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCurrencyManager#getAlCurrency(String currencyId)
     */
    public AlCurrency getAlCurrency(final String currencyId) {
        return dao.getAlCurrency(new Long(currencyId));
    }

    /**
     * @see com.joymain.jecs.al.service.AlCurrencyManager#saveAlCurrency(AlCurrency alCurrency)
     */
    public void saveAlCurrency(AlCurrency alCurrency) {
        dao.saveAlCurrency(alCurrency);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCurrencyManager#removeAlCurrency(String currencyId)
     */
    public void removeAlCurrency(final String currencyId) {
        dao.removeAlCurrency(new Long(currencyId));
    }
}
