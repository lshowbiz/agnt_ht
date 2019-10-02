
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiFundbookBalance;
import com.joymain.jecs.fi.dao.FiFundbookBalanceDao;
import com.joymain.jecs.fi.service.FiFundbookBalanceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiFundbookBalanceManagerImpl extends BaseManager implements FiFundbookBalanceManager {
    private FiFundbookBalanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiFundbookBalanceDao(FiFundbookBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookBalanceManager#getFiFundbookBalances(com.joymain.jecs.fi.model.FiFundbookBalance)
     */
    public List getFiFundbookBalances(final FiFundbookBalance fiFundbookBalance) {
        return dao.getFiFundbookBalances(fiFundbookBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookBalanceManager#getFiFundbookBalance(String fbbId)
     */
    public FiFundbookBalance getFiFundbookBalance(final String fbbId) {
        return dao.getFiFundbookBalance(new Long(fbbId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookBalanceManager#saveFiFundbookBalance(FiFundbookBalance fiFundbookBalance)
     */
    public void saveFiFundbookBalance(FiFundbookBalance fiFundbookBalance) {
        dao.saveFiFundbookBalance(fiFundbookBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiFundbookBalanceManager#removeFiFundbookBalance(String fbbId)
     */
    public void removeFiFundbookBalance(final String fbbId) {
        dao.removeFiFundbookBalance(new Long(fbbId));
    }
    //added for getFiFundbookBalancesByCrm
    public List getFiFundbookBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getFiFundbookBalancesByCrm(crm,pager);
    }
}
