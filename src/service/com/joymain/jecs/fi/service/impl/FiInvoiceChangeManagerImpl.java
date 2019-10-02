
package com.joymain.jecs.fi.service.impl;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.FiAvailableInvoice;
import com.joymain.jecs.fi.model.FiInvoiceChange;
import com.joymain.jecs.fi.dao.FiInvoiceChangeDao;
import com.joymain.jecs.fi.service.FiInvoiceChangeManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class FiInvoiceChangeManagerImpl extends BaseManager implements FiInvoiceChangeManager {
    private FiInvoiceChangeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setFiInvoiceChangeDao(FiInvoiceChangeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.FiInvoiceChangeManager#getFiInvoiceChanges(com.joymain.jecs.fi.model.FiInvoiceChange)
     */
    public List getFiInvoiceChanges(final FiInvoiceChange fiInvoiceChange) {
        return dao.getFiInvoiceChanges(fiInvoiceChange);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiInvoiceChangeManager#getFiInvoiceChange(String id)
     */
    public FiInvoiceChange getFiInvoiceChange(final String id) {
        return dao.getFiInvoiceChange(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.fi.service.FiInvoiceChangeManager#saveFiInvoiceChange(FiInvoiceChange fiInvoiceChange)
     */
    public void saveFiInvoiceChange(FiInvoiceChange fiInvoiceChange) {
        dao.saveFiInvoiceChange(fiInvoiceChange);
    }

    /**
     * @see com.joymain.jecs.fi.service.FiInvoiceChangeManager#removeFiInvoiceChange(String id)
     */
    public void removeFiInvoiceChange(final String id) {
        dao.removeFiInvoiceChange(new BigDecimal(id));
    }
    //added for getFiInvoiceChangesByCrm
    public List getFiInvoiceChangesByCrm(CommonRecord crm, Pager pager){
	return dao.getFiInvoiceChangesByCrm(crm,pager);
    }
    
    /**
     * 合规可用发票审核后向可用发票流水表和可用发票余额表插入数据
     * @author 2015-11-30 fu 
     * @param fiAvailableInvoice
     * @param userCodeLogin
     */
	public void scSavefiInvoiceChange(FiAvailableInvoice fiAvailableInvoice,String userCodeLogin,BigDecimal balance){
		FiInvoiceChange fiInvoiceChange = new FiInvoiceChange();
		fiInvoiceChange.setUserCode(fiAvailableInvoice.getUserCode());
		fiInvoiceChange.setForeignId(fiAvailableInvoice.getId());//唯一码
		fiInvoiceChange.setMoney(fiAvailableInvoice.getInvoiceValue());
		fiInvoiceChange.setChangeType("-");
		fiInvoiceChange.setJyear(fiAvailableInvoice.getJyear().toString());
		fiInvoiceChange.setJmonth(fiAvailableInvoice.getJmonth().toString());
		fiInvoiceChange.setCreateUserCode(userCodeLogin);
		fiInvoiceChange.setCreateTime(new Date());
		fiInvoiceChange.setRemark("");//摘要
		fiInvoiceChange.setBalance(balance);//余额
		dao.saveFiInvoiceChange(fiInvoiceChange);
	}
	
	
}
