
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiInvoiceBalance;
import com.joymain.jecs.fi.dao.FiInvoiceBalanceDao;
import com.joymain.jecs.fi.service.FiInvoiceBalanceManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiInvoiceBalanceManagerImpl extends BaseManager implements FiInvoiceBalanceManager {
    private FiInvoiceBalanceDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiInvoiceBalanceDao(FiInvoiceBalanceDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiInvoiceBalanceManager#getFiInvoiceBalances(com.joymain.jecs.fi.model.FiInvoiceBalance)
     */
    public List getFiInvoiceBalances(final FiInvoiceBalance fiInvoiceBalance) {
        return dao.getFiInvoiceBalances(fiInvoiceBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiInvoiceBalanceManager#getFiInvoiceBalance(String id)
     */
    public FiInvoiceBalance getFiInvoiceBalance(final String id) {
        return dao.getFiInvoiceBalance(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiInvoiceBalanceManager#saveFiInvoiceBalance(FiInvoiceBalance fiInvoiceBalance)
     */
    public void saveFiInvoiceBalance(FiInvoiceBalance fiInvoiceBalance) {
        dao.saveFiInvoiceBalance(fiInvoiceBalance);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiInvoiceBalanceManager#removeFiInvoiceBalance(String id)
     */
    public void removeFiInvoiceBalance(final String id) {
        dao.removeFiInvoiceBalance(new BigDecimal(id));
    }
    //added for getFiInvoiceBalancesByCrm
    public List getFiInvoiceBalancesByCrm(CommonRecord crm, Pager pager){
	return dao.getFiInvoiceBalancesByCrm(crm,pager);
    }
    
    /**
     * 根据会员编号，获取会员的可用发票余额对象
     * @author fu 2015-11-30
     * @param userCode
     * @return fiInvoiceBalance
     */
	public FiInvoiceBalance getFiInvoiceBalanceByUserCode(String userCode){
		return dao.getFiInvoiceBalanceByUserCode(userCode);
	}
}
