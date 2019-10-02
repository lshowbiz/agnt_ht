
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.dao.JfiBankbookBalanceDao;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiBankbookBalanceManagerImpl extends BaseManager implements JfiBankbookBalanceManager {
    private JfiBankbookBalanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiBankbookBalanceDao(JfiBankbookBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookBalanceManager#getJfiBankbookBalances(com.joymain.jecs.fi.model.JfiBankbookBalance)
     */
    public List getJfiBankbookBalances(final JfiBankbookBalance jfiBankbookBalance) {
        return dao.getJfiBankbookBalances(jfiBankbookBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookBalanceManager#getJfiBankbookBalance(String userCode)
     */
    public JfiBankbookBalance getJfiBankbookBalance(final String userCode) {
        return dao.getJfiBankbookBalance(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookBalanceManager#saveJfiBankbookBalance(JfiBankbookBalance jfiBankbookBalance)
     */
    public void saveJfiBankbookBalance(JfiBankbookBalance jfiBankbookBalance) {
        dao.saveJfiBankbookBalance(jfiBankbookBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBankbookBalanceManager#removeJfiBankbookBalance(String userCode)
     */
    public void removeJfiBankbookBalance(final String userCode) {
        dao.removeJfiBankbookBalance(new String(userCode));
    }
    //added for getJfiBankbookBalancesByCrm
    public List getJfiBankbookBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiBankbookBalancesByCrm(crm,pager);
    }
}
