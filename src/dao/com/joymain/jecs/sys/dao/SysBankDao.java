
package com.joymain.jecs.sys.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.sys.model.SysBank;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface SysBankDao extends Dao {

	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
    /**
     * Retrieves all of the sysBanks
     */
    public List getSysBanks(SysBank sysBank);

    /**
     * Gets sysBank's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param bankId the sysBank's bankId
     * @return sysBank populated sysBank object
     */
    public SysBank getSysBank(final Long bankId);

    /**
     * Saves a sysBank's information
     * @param sysBank the object to be saved
     */    
    public void saveSysBank(SysBank sysBank);

    /**
     * Removes a sysBank from the database by bankId
     * @param bankId the sysBank's bankId
     */
    public void removeSysBank(final Long bankId);
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

