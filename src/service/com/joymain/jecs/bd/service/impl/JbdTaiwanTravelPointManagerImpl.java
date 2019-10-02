
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTaiwanTravelPoint;
import com.joymain.jecs.bd.dao.JbdTaiwanTravelPointDao;
import com.joymain.jecs.bd.service.JbdTaiwanTravelPointManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTaiwanTravelPointManagerImpl extends BaseManager implements JbdTaiwanTravelPointManager {
    private JbdTaiwanTravelPointDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTaiwanTravelPointDao(JbdTaiwanTravelPointDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTaiwanTravelPointManager#getJbdTaiwanTravelPoints(com.joymain.jecs.bd.model.JbdTaiwanTravelPoint)
     */
    public List getJbdTaiwanTravelPoints(final JbdTaiwanTravelPoint jbdTaiwanTravelPoint) {
        return dao.getJbdTaiwanTravelPoints(jbdTaiwanTravelPoint);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTaiwanTravelPointManager#getJbdTaiwanTravelPoint(String id)
     */
    public JbdTaiwanTravelPoint getJbdTaiwanTravelPoint(final String id) {
        return dao.getJbdTaiwanTravelPoint(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTaiwanTravelPointManager#saveJbdTaiwanTravelPoint(JbdTaiwanTravelPoint jbdTaiwanTravelPoint)
     */
    public void saveJbdTaiwanTravelPoint(JbdTaiwanTravelPoint jbdTaiwanTravelPoint) {
        dao.saveJbdTaiwanTravelPoint(jbdTaiwanTravelPoint);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTaiwanTravelPointManager#removeJbdTaiwanTravelPoint(String id)
     */
    public void removeJbdTaiwanTravelPoint(final String id) {
        dao.removeJbdTaiwanTravelPoint(new BigDecimal(id));
    }
    //added for getJbdTaiwanTravelPointsByCrm
    public List getJbdTaiwanTravelPointsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTaiwanTravelPointsByCrm(crm,pager);
    }
}
