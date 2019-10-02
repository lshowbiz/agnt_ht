
package com.joymain.jecs.sys.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysBank;
import com.joymain.jecs.sys.dao.SysBankDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface SysBankManager extends Manager {
    /**
     * Retrieves all of the sysBanks
     */
    public List getSysBanks(SysBank sysBank);

    /**
     * Gets sysBank's information based on bankId.
     * @param bankId the sysBank's bankId
     * @return sysBank populated sysBank object
     */
    public SysBank getSysBank(final String bankId);

    /**
     * Saves a sysBank's information
     * @param sysBank the object to be saved
     */
    public void saveSysBank(SysBank sysBank);

    /**
     * Removes a sysBank from the database by bankId
     * @param bankId the sysBank's bankId
     */
    public void removeSysBank(final String bankId);
    //added for getSysBanksByCrm
		public List getSysBanksByCrm(CommonRecord crm, Pager pager);
		/**
		 * 通过公司编码查找会员
		 * @param companyCode
		 * @return
		 */
		public List getSysBankByCompanyCode(String companyCode);
		public SysBank getJsysBankByBankNo(String bankNo);
}

