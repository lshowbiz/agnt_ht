
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiFundbookBalance;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiFundbookBalanceDao extends Dao {

    /**
     * Retrieves all of the fiFundbookBalances
     */
    public List getFiFundbookBalances(FiFundbookBalance fiFundbookBalance);

    /**
     * Gets fiFundbookBalance's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param fbbId the fiFundbookBalance's fbbId
     * @return fiFundbookBalance populated fiFundbookBalance object
     */
    public FiFundbookBalance getFiFundbookBalance(final Long fbbId);

    /**
     * Saves a fiFundbookBalance's information
     * @param fiFundbookBalance the object to be saved
     */    
    public void saveFiFundbookBalance(FiFundbookBalance fiFundbookBalance);

    /**
     * Removes a fiFundbookBalance from the database by fbbId
     * @param fbbId the fiFundbookBalance's fbbId
     */
    public void removeFiFundbookBalance(final Long fbbId);
    //added for getFiFundbookBalancesByCrm
    public List getFiFundbookBalancesByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 获取银行记录
     * @param UserCode
     * @param bankbookType 基金类型：1，分红基金；2，定向基金
     * @return
     */
    public FiFundbookBalance getFiFundbookBalanceByUserCodeAndFundbookType(final String userCode, final String bankbookType);
    
    /**
     * 根据会员编号和基金类型，进行锁定一条表记录操作
     * @param fbbId
     * @return
     */
    public FiFundbookBalance getFiFundbookBalanceForUpdate(final Long fbbId);
    
}

