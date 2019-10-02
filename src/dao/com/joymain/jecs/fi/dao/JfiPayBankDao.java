
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.JfiPayBank;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JfiPayBankDao extends Dao {

    /**
     * Retrieves all of the jfiPayBanks
     */
    public List getJfiPayBanks(JfiPayBank jfiPayBank);

    /**
     * Gets jfiPayBank's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param accountCode the jfiPayBank's accountCode
     * @return jfiPayBank populated jfiPayBank object
     */
    public JfiPayBank getJfiPayBank(final String accountCode);

    /**
     * Saves a jfiPayBank's information
     * @param jfiPayBank the object to be saved
     */    
    public void saveJfiPayBank(JfiPayBank jfiPayBank);

    /**
     * Removes a jfiPayBank from the database by accountCode
     * @param accountCode the jfiPayBank's accountCode
     */
    public void removeJfiPayBank(final String accountCode);
    //added for getJfiPayBanksByCrm
    public List getJfiPayBanksByCrm(CommonRecord crm, Pager pager);
    
	/**
	 * 获取帐号代码在一个数组范围内的帐号资料
	 * @param accountCodes
	 * @return
	 */
	public List getJfiPayBanksWithStr(final String[] accountCodes);
	
	/**
	 * 获取公司所对应的银行
	 * @param companyCode
	 * @return
	 */
	public List getJfiPayBanksByCompany(final String companyCode);
}

