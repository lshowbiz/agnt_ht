
package com.joymain.jecs.po.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoAutoShip;
import com.joymain.jecs.po.dao.JpoAutoShipDao;
import com.joymain.jecs.po.service.JpoAutoShipManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoAutoShipManagerImpl extends BaseManager implements JpoAutoShipManager {
    private JpoAutoShipDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoAutoShipDao(JpoAutoShipDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoAutoShipManager#getJpoAutoShips(com.joymain.jecs.po.model.JpoAutoShip)
     */
    public List getJpoAutoShips(final JpoAutoShip jpoAutoShip) {
        return dao.getJpoAutoShips(jpoAutoShip);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoAutoShipManager#getJpoAutoShip(String jasId)
     */
    public JpoAutoShip getJpoAutoShip(final String jasId) {
        return dao.getJpoAutoShip(new Long(jasId));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoAutoShipManager#saveJpoAutoShip(JpoAutoShip jpoAutoShip)
     */
    public void saveJpoAutoShip(JpoAutoShip jpoAutoShip) {
        dao.saveJpoAutoShip(jpoAutoShip);
    }

    /**
     * 获取AutoShip的定时器是否在执行
     * @param configKey
     * @return
     */
    public String getAutoShipConfigValue(final String configKey){
    	return dao.getAutoShipConfigValue(configKey);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoAutoShipManager#removeJpoAutoShip(String jasId)
     */
    public void removeJpoAutoShip(final String jasId) {
        dao.removeJpoAutoShip(new Long(jasId));
    }
    //added for getJpoAutoShipsByCrm
    public List getJpoAutoShipsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoAutoShipsByCrm(crm,pager);
    }
}
