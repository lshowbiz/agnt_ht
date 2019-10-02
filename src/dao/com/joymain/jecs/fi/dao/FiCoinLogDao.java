
package com.joymain.jecs.fi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.fi.model.FiCoinLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface FiCoinLogDao extends Dao {

    /**
     * Retrieves all of the fiCoinLogs
     */
    public List getFiCoinLogs(FiCoinLog fiCoinLog);

    /**
     * Gets fiCoinLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param fclId the fiCoinLog's fclId
     * @return fiCoinLog populated fiCoinLog object
     */
    public FiCoinLog getFiCoinLog(final Long fclId);

    /**
     * Saves a fiCoinLog's information
     * @param fiCoinLog the object to be saved
     */    
    public void saveFiCoinLog(FiCoinLog fiCoinLog);

    /**
     * Removes a fiCoinLog from the database by fclId
     * @param fclId the fiCoinLog's fclId
     */
    public void removeFiCoinLog(final Long fclId);
    //added for getFiCoinLogsByCrm
    public List getFiCoinLogsByCrm(CommonRecord crm, Pager pager);

    /**
     * 获取赠送的所有积分
     * @param userCode
     * @param coinType
     * @return
     */
    public BigDecimal getFiCoinLogAmtByUserCode(final String userCode, final String coinType);
}

