
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiPayAccount;
import com.joymain.jecs.fi.dao.FiPayAccountDao;
import com.joymain.jecs.fi.service.FiPayAccountManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiPayAccountManagerImpl extends BaseManager implements FiPayAccountManager {
    private FiPayAccountDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiPayAccountDao(FiPayAccountDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiPayAccountManager#getFiPayAccounts(com.joymain.jecs.fi.model.FiPayAccount)
     */
    public List getFiPayAccounts(final FiPayAccount fiPayAccount) {
        return dao.getFiPayAccounts(fiPayAccount);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiPayAccountManager#getFiPayAccount(String accountId)
     */
    public FiPayAccount getFiPayAccount(final String accountId) {
        return dao.getFiPayAccount(new Long(accountId));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiPayAccountManager#saveFiPayAccount(FiPayAccount fiPayAccount)
     */
    public void saveFiPayAccount(FiPayAccount fiPayAccount) {
        dao.saveFiPayAccount(fiPayAccount);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiPayAccountManager#removeFiPayAccount(String accountId)
     */
    public void removeFiPayAccount(final String accountId) {
        dao.removeFiPayAccount(new Long(accountId));
    }
    //added for getFiPayAccountsByCrm
    public List getFiPayAccountsByCrm(CommonRecord crm, Pager pager){
	return dao.getFiPayAccountsByCrm(crm,pager);
    }

    /**
     * 查询默认商户号
     * @param accountId
     * @return
     */
	@Override
	public List getDefaultAccounts(Long accountId) {
		// TODO Auto-generated method stub
		return dao.getDefaultAccounts(accountId);
	}
}
