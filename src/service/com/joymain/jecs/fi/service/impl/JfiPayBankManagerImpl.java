
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiPayBank;
import com.joymain.jecs.fi.dao.JfiPayBankDao;
import com.joymain.jecs.fi.service.JfiPayBankManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiPayBankManagerImpl extends BaseManager implements JfiPayBankManager {
    private JfiPayBankDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiPayBankDao(JfiPayBankDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayBankManager#getJfiPayBanks(com.joymain.jecs.fi.model.JfiPayBank)
     */
    public List getJfiPayBanks(final JfiPayBank jfiPayBank) {
        return dao.getJfiPayBanks(jfiPayBank);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayBankManager#getJfiPayBank(String accountCode)
     */
    public JfiPayBank getJfiPayBank(final String accountCode) {
        return dao.getJfiPayBank(new String(accountCode));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayBankManager#saveJfiPayBank(JfiPayBank jfiPayBank)
     */
    public void saveJfiPayBank(JfiPayBank jfiPayBank) {
        dao.saveJfiPayBank(jfiPayBank);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiPayBankManager#removeJfiPayBank(String accountCode)
     */
    public void removeJfiPayBank(final String accountCode) {
        dao.removeJfiPayBank(new String(accountCode));
    }
    //added for getJfiPayBanksByCrm
    public List getJfiPayBanksByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiPayBanksByCrm(crm,pager);
    }
	
	/**
	 * 获取帐号代码在一个数组范围内的帐号资料
	 * @param accountCodes
	 * @return
	 */
	public List getJfiPayBanksWithStr(final String[] accountCodes){
		return dao.getJfiPayBanksWithStr(accountCodes);
	}
	
	/**
	 * 获取公司所对应的银行
	 * @param companyCode
	 * @return
	 */
	public List getJfiPayBanksByCompany(final String companyCode){
		return dao.getJfiPayBanksByCompany(companyCode);
	}
}
