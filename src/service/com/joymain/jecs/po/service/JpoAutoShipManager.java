
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoAutoShip;
import com.joymain.jecs.po.dao.JpoAutoShipDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoAutoShipManager extends Manager {
    /**
     * Retrieves all of the jpoAutoShips
     */
    public List getJpoAutoShips(JpoAutoShip jpoAutoShip);

    /**
     * Gets jpoAutoShip's information based on jasId.
     * @param jasId the jpoAutoShip's jasId
     * @return jpoAutoShip populated jpoAutoShip object
     */
    public JpoAutoShip getJpoAutoShip(final String jasId);

    /**
     * Saves a jpoAutoShip's information
     * @param jpoAutoShip the object to be saved
     */
    public void saveJpoAutoShip(JpoAutoShip jpoAutoShip);

    /**
     * Removes a jpoAutoShip from the database by jasId
     * @param jasId the jpoAutoShip's jasId
     */
    public void removeJpoAutoShip(final String jasId);
    //added for getJpoAutoShipsByCrm
    public List getJpoAutoShipsByCrm(CommonRecord crm, Pager pager);

    /**
     * 获取AutoShip的定时器是否在执行
     * @param configKey
     * @return
     */
    public String getAutoShipConfigValue(final String configKey);
}

