
package com.joymain.jecs.sys.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysBank;
import com.joymain.jecs.sys.dao.SysBankDao;
import com.joymain.jecs.sys.service.SysBankManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class SysBankManagerImpl extends BaseManager implements SysBankManager {
    private SysBankDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setSysBankDao(SysBankDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.sys.service.SysBankManager#getSysBanks(com.joymain.jecs.sys.model.SysBank)
     */
    public List getSysBanks(final SysBank sysBank) {
        return dao.getSysBanks(sysBank);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysBankManager#getSysBank(String bankId)
     */
    public SysBank getSysBank(final String bankId) {
        return dao.getSysBank(new Long(bankId));
    }

    /**
     * @see com.joymain.jecs.sys.service.SysBankManager#saveSysBank(SysBank sysBank)
     */
    public void saveSysBank(SysBank sysBank) {
        dao.saveSysBank(sysBank);
    }

    /**
     * @see com.joymain.jecs.sys.service.SysBankManager#removeSysBank(String bankId)
     */
    public void removeSysBank(final String bankId) {
        dao.removeSysBank(new Long(bankId));
    }
    //added for getSysBanksByCrm
		public List getSysBanksByCrm(CommonRecord crm, Pager pager){
				return dao.getSysBanksByCrm(crm,pager);
		}

		public List getSysBankByCompanyCode(String companyCode) {
			return dao.getSysBankByCompanyCode(companyCode);
		}

		public SysBank getJsysBankByBankNo(String bankNo) {
			return dao.getJsysBankByBankNo(bankNo);
		}
}
