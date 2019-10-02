
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiBcoinBalance;
import com.joymain.jecs.fi.dao.FiBcoinBalanceDao;
import com.joymain.jecs.fi.service.FiBcoinBalanceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiBcoinBalanceManagerImpl extends BaseManager implements FiBcoinBalanceManager {
    private FiBcoinBalanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBcoinBalanceDao(FiBcoinBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinBalanceManager#getFiBcoinBalances(com.joymain.jecs.fi.model.FiBcoinBalance)
     */
    public List getFiBcoinBalances(final FiBcoinBalance fiBcoinBalance) {
        return dao.getFiBcoinBalances(fiBcoinBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinBalanceManager#getFiBcoinBalance(String userCode)
     */
    public FiBcoinBalance getFiBcoinBalance(final String userCode) {
        return dao.getFiBcoinBalance(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinBalanceManager#saveFiBcoinBalance(FiBcoinBalance fiBcoinBalance)
     */
    public void saveFiBcoinBalance(FiBcoinBalance fiBcoinBalance) {
        dao.saveFiBcoinBalance(fiBcoinBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiBcoinBalanceManager#removeFiBcoinBalance(String userCode)
     */
    public void removeFiBcoinBalance(final String userCode) {
        dao.removeFiBcoinBalance(new String(userCode));
    }
    //added for getFiBcoinBalancesByCrm
    public List getFiBcoinBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getFiBcoinBalancesByCrm(crm,pager);
    }
}
