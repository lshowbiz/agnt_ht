
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.MiCoinLog;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface MiCoinLogDao extends Dao {

    /**
     * Retrieves all of the miCoinLogs
     */
    public List getMiCoinLogs(MiCoinLog miCoinLog);

    /**
     * Gets miCoinLog's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the miCoinLog's id
     * @return miCoinLog populated miCoinLog object
     */
    public MiCoinLog getMiCoinLog(final Long id);

    /**
     * Saves a miCoinLog's information
     * @param miCoinLog the object to be saved
     */    
    public void saveMiCoinLog(MiCoinLog miCoinLog);

    /**
     * Removes a miCoinLog from the database by id
     * @param id the miCoinLog's id
     */
    public void removeMiCoinLog(final Long id);
    //added for getMiCoinLogsByCrm
    public List getMiCoinLogsByCrm(CommonRecord crm, Pager pager);
    public BigDecimal getSumCoin(CommonRecord crm);
}
