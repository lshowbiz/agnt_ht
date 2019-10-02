
package com.joymain.jecs.fi.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.FiCoinLog;
import com.joymain.jecs.fi.dao.FiCoinLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface FiCoinLogManager extends Manager {
    /**
     * Retrieves all of the fiCoinLogs
     */
    public List getFiCoinLogs(FiCoinLog fiCoinLog);

    /**
     * Gets fiCoinLog's information based on fclId.
     * @param fclId the fiCoinLog's fclId
     * @return fiCoinLog populated fiCoinLog object
     */
    public FiCoinLog getFiCoinLog(final String fclId);

    /**
     * Saves a fiCoinLog's information
     * @param fiCoinLog the object to be saved
     */
    public void saveFiCoinLog(FiCoinLog fiCoinLog);

    /**
     * Removes a fiCoinLog from the database by fclId
     * @param fclId the fiCoinLog's fclId
     */
    public void removeFiCoinLog(final String fclId);
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

