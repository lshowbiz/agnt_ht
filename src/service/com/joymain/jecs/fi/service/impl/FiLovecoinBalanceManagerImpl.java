
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiLovecoinBalance;
import com.joymain.jecs.fi.dao.FiLovecoinBalanceDao;
import com.joymain.jecs.fi.service.FiLovecoinBalanceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiLovecoinBalanceManagerImpl extends BaseManager implements FiLovecoinBalanceManager {
    private FiLovecoinBalanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiLovecoinBalanceDao(FiLovecoinBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiLovecoinBalanceManager#getFiLovecoinBalances(com.joymain.jecs.fi.model.FiLovecoinBalance)
     */
    public List getFiLovecoinBalances(final FiLovecoinBalance fiLovecoinBalance) {
        return dao.getFiLovecoinBalances(fiLovecoinBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiLovecoinBalanceManager#getFiLovecoinBalance(String userCode)
     */
    public FiLovecoinBalance getFiLovecoinBalance(final String userCode) {
        return dao.getFiLovecoinBalance(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiLovecoinBalanceManager#saveFiLovecoinBalance(FiLovecoinBalance fiLovecoinBalance)
     */
    public void saveFiLovecoinBalance(FiLovecoinBalance fiLovecoinBalance) {
        dao.saveFiLovecoinBalance(fiLovecoinBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiLovecoinBalanceManager#removeFiLovecoinBalance(String userCode)
     */
    public void removeFiLovecoinBalance(final String userCode) {
        dao.removeFiLovecoinBalance(new String(userCode));
    }
    //added for getFiLovecoinBalancesByCrm
    public List getFiLovecoinBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getFiLovecoinBalancesByCrm(crm,pager);
    }
}
