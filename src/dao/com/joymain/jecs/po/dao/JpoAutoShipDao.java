
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoAutoShip;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoAutoShipDao extends Dao {

    /**
     * Retrieves all of the jpoAutoShips
     */
    public List getJpoAutoShips(JpoAutoShip jpoAutoShip);

    /**
     * Gets jpoAutoShip's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param jasId the jpoAutoShip's jasId
     * @return jpoAutoShip populated jpoAutoShip object
     */
    public JpoAutoShip getJpoAutoShip(final Long jasId);

    /**
     * Saves a jpoAutoShip's information
     * @param jpoAutoShip the object to be saved
     */    
    public void saveJpoAutoShip(JpoAutoShip jpoAutoShip);

    /**
     * Removes a jpoAutoShip from the database by jasId
     * @param jasId the jpoAutoShip's jasId
     */
    public void removeJpoAutoShip(final Long jasId);
    //added for getJpoAutoShipsByCrm
    public List getJpoAutoShipsByCrm(CommonRecord crm, Pager pager);

    /**
     * 获取AutoShip的定时器是否在执行
     * @param configKey
     * @return
     */
    public String getAutoShipConfigValue(final String configKey);
}

