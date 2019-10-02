
package com.joymain.jecs.fi.service.impl;

import java.util.List;

import com.joymain.jecs.fi.dao.FiProductPointBalanceDao;
import com.joymain.jecs.fi.model.FiProductPointBalance;
import com.joymain.jecs.fi.service.FiProductPointBalanceManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiProductPointBalanceManagerImpl extends BaseManager implements FiProductPointBalanceManager {
    private FiProductPointBalanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiProductPointBalanceDao(FiProductPointBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointBalanceManager#getFiProductPointBalances(com.sp.spms.fi.model.FiProductPointBalance)
     */
    public List getFiProductPointBalances(final FiProductPointBalance fiProductPointBalance) {
        return dao.getFiProductPointBalances(fiProductPointBalance);
    }
    public FiProductPointBalance getFiProductPointBalance(final String userCode,final String backbookType){
    	return dao.getFiProductPointBalance(userCode,backbookType);
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointBalanceManager#getFiProductPointBalance(String fbbId)
     */
    public FiProductPointBalance getFiProductPointBalance(final String fbbId) {
        return dao.getFiProductPointBalance(new Long(fbbId));
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointBalanceManager#saveFiProductPointBalance(FiProductPointBalance fiProductPointBalance)
     */
    public void saveFiProductPointBalance(FiProductPointBalance fiProductPointBalance) {
        dao.saveFiProductPointBalance(fiProductPointBalance);
    }

    /**
     * @see com.sp.spms.fi.service.FiProductPointBalanceManager#removeFiProductPointBalance(String fbbId)
     */
    public void removeFiProductPointBalance(final String fbbId) {
        dao.removeFiProductPointBalance(new Long(fbbId));
    }
    //added for getFiProductPointBalancesByCrm
    public List getFiProductPointBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getFiProductPointBalancesByCrm(crm,pager);
    }
}
