
package com.joymain.jecs.mi.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.MiCoinLog;
import com.joymain.jecs.mi.dao.MiCoinLogDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface MiCoinLogManager extends Manager {
    /**
     * Retrieves all of the miCoinLogs
     */
    public List getMiCoinLogs(MiCoinLog miCoinLog);

    /**
     * Gets miCoinLog's information based on id.
     * @param id the miCoinLog's id
     * @return miCoinLog populated miCoinLog object
     */
    public MiCoinLog getMiCoinLog(final String id);

    /**
     * Saves a miCoinLog's information
     * @param miCoinLog the object to be saved
     */
    public void saveMiCoinLog(MiCoinLog miCoinLog);

    /**
     * Removes a miCoinLog from the database by id
     * @param id the miCoinLog's id
     */
    public void removeMiCoinLog(final String id);
    //added for getMiCoinLogsByCrm
    public List getMiCoinLogsByCrm(CommonRecord crm, Pager pager);
    public BigDecimal getSumCoin(CommonRecord crm) ;
}

