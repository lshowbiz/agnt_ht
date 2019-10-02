
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiBankbookBalance;
import com.joymain.jecs.fi.dao.FiBankbookBalanceDao;
import com.joymain.jecs.fi.service.FiBankbookBalanceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiBankbookBalanceManagerImpl extends BaseManager implements FiBankbookBalanceManager {
    private FiBankbookBalanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiBankbookBalanceDao(FiBankbookBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookBalanceManager#getFiBankbookBalances(com.sp.spms.fi.model.FiBankbookBalance)
     */
    public List getFiBankbookBalances(final FiBankbookBalance fiBankbookBalance) {
        return dao.getFiBankbookBalances(fiBankbookBalance);
    }
    public FiBankbookBalance getFiBankbookBalance(final String userCode,final String backbookType){
    	return dao.getFiBankbookBalance(userCode,backbookType);
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookBalanceManager#getFiBankbookBalance(String fbbId)
     */
    public FiBankbookBalance getFiBankbookBalance(final String fbbId) {
        return dao.getFiBankbookBalance(new Long(fbbId));
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookBalanceManager#saveFiBankbookBalance(FiBankbookBalance fiBankbookBalance)
     */
    public void saveFiBankbookBalance(FiBankbookBalance fiBankbookBalance) {
        dao.saveFiBankbookBalance(fiBankbookBalance);
    }

    /**
     * @see com.sp.spms.fi.service.FiBankbookBalanceManager#removeFiBankbookBalance(String fbbId)
     */
    public void removeFiBankbookBalance(final String fbbId) {
        dao.removeFiBankbookBalance(new Long(fbbId));
    }
    //added for getFiBankbookBalancesByCrm
    public List getFiBankbookBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getFiBankbookBalancesByCrm(crm,pager);
    }
    
    public List getFiProductPointBalancesByCrm(CommonRecord crm, Pager pager){
    	return dao.getFiProductPointBalancesByCrm(crm,pager);
        }
}
