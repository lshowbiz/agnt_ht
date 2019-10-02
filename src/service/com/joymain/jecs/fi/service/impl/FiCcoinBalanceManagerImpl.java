
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiCcoinBalance;
import com.joymain.jecs.fi.dao.FiCcoinBalanceDao;
import com.joymain.jecs.fi.service.FiCcoinBalanceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiCcoinBalanceManagerImpl extends BaseManager implements FiCcoinBalanceManager {
    private FiCcoinBalanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiCcoinBalanceDao(FiCcoinBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCcoinBalanceManager#getFiCcoinBalances(com.joymain.jecs.fi.model.FiCcoinBalance)
     */
    public List getFiCcoinBalances(final FiCcoinBalance fiCcoinBalance) {
        return dao.getFiCcoinBalances(fiCcoinBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCcoinBalanceManager#getFiCcoinBalance(String userCode)
     */
    public FiCcoinBalance getFiCcoinBalance(final String userCode) {
        return dao.getFiCcoinBalance(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCcoinBalanceManager#saveFiCcoinBalance(FiCcoinBalance fiCcoinBalance)
     */
    public void saveFiCcoinBalance(FiCcoinBalance fiCcoinBalance) {
        dao.saveFiCcoinBalance(fiCcoinBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiCcoinBalanceManager#removeFiCcoinBalance(String userCode)
     */
    public void removeFiCcoinBalance(final String userCode) {
        dao.removeFiCcoinBalance(new String(userCode));
    }
    //added for getFiCcoinBalancesByCrm
    public List getFiCcoinBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getFiCcoinBalancesByCrm(crm,pager);
    }
}
