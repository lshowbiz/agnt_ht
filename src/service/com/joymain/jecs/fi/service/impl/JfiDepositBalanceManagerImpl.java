
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiDepositBalance;
import com.joymain.jecs.fi.dao.JfiDepositBalanceDao;
import com.joymain.jecs.fi.service.JfiDepositBalanceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiDepositBalanceManagerImpl extends BaseManager implements JfiDepositBalanceManager {
    private JfiDepositBalanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiDepositBalanceDao(JfiDepositBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositBalanceManager#getJfiDepositBalances(com.joymain.jecs.fi.model.JfiDepositBalance)
     */
    public List getJfiDepositBalances(final JfiDepositBalance jfiDepositBalance) {
        return dao.getJfiDepositBalances(jfiDepositBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositBalanceManager#getJfiDepositBalance(String fdbId)
     */
    public JfiDepositBalance getJfiDepositBalance(final String fdbId) {
        return dao.getJfiDepositBalance(new Long(fdbId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositBalanceManager#saveJfiDepositBalance(JfiDepositBalance jfiDepositBalance)
     */
    public void saveJfiDepositBalance(JfiDepositBalance jfiDepositBalance) {
        dao.saveJfiDepositBalance(jfiDepositBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositBalanceManager#removeJfiDepositBalance(String fdbId)
     */
    public void removeJfiDepositBalance(final String fdbId) {
        dao.removeJfiDepositBalance(new Long(fdbId));
    }
    //added for getJfiDepositBalancesByCrm
    public List getJfiDepositBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiDepositBalancesByCrm(crm,pager);
    }
}
