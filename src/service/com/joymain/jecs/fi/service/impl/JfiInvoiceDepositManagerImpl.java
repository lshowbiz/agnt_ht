
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiInvoiceDeposit;
import com.joymain.jecs.fi.dao.JfiInvoiceDepositDao;
import com.joymain.jecs.fi.service.JfiInvoiceDepositManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiInvoiceDepositManagerImpl extends BaseManager implements JfiInvoiceDepositManager {
    private JfiInvoiceDepositDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiInvoiceDepositDao(JfiInvoiceDepositDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiInvoiceDepositManager#getJfiInvoiceDeposits(com.joymain.jecs.fi.model.JfiInvoiceDeposit)
     */
    public List getJfiInvoiceDeposits(final JfiInvoiceDeposit jfiInvoiceDeposit) {
        return dao.getJfiInvoiceDeposits(jfiInvoiceDeposit);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiInvoiceDepositManager#getJfiInvoiceDeposit(String id)
     */
    public JfiInvoiceDeposit getJfiInvoiceDeposit(final String id) {
        return dao.getJfiInvoiceDeposit(new Long(id));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiInvoiceDepositManager#saveJfiInvoiceDeposit(JfiInvoiceDeposit jfiInvoiceDeposit)
     */
    public void saveJfiInvoiceDeposit(JfiInvoiceDeposit jfiInvoiceDeposit) {
        dao.saveJfiInvoiceDeposit(jfiInvoiceDeposit);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiInvoiceDepositManager#removeJfiInvoiceDeposit(String id)
     */
    public void removeJfiInvoiceDeposit(final String id) {
        dao.removeJfiInvoiceDeposit(new Long(id));
    }
    //added for getJfiInvoiceDepositsByCrm
    public List getJfiInvoiceDepositsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiInvoiceDepositsByCrm(crm,pager);
    }
}
