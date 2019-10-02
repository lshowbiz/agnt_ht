
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTravelPoint;
import com.joymain.jecs.bd.dao.JbdTravelPointDao;
import com.joymain.jecs.bd.service.JbdTravelPointManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPointManagerImpl extends BaseManager implements JbdTravelPointManager {
    private JbdTravelPointDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointDao(JbdTravelPointDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointManager#getJbdTravelPoints(com.joymain.jecs.bd.model.JbdTravelPoint)
     */
    public List getJbdTravelPoints(final JbdTravelPoint jbdTravelPoint) {
        return dao.getJbdTravelPoints(jbdTravelPoint);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointManager#getJbdTravelPoint(String userCode)
     */
    public JbdTravelPoint getJbdTravelPoint(final String userCode) {
        return dao.getJbdTravelPoint(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointManager#saveJbdTravelPoint(JbdTravelPoint jbdTravelPoint)
     */
    public void saveJbdTravelPoint(JbdTravelPoint jbdTravelPoint) {
        dao.saveJbdTravelPoint(jbdTravelPoint);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointManager#removeJbdTravelPoint(String userCode)
     */
    public void removeJbdTravelPoint(final String userCode) {
        dao.removeJbdTravelPoint(new String(userCode));
    }
    //added for getJbdTravelPointsByCrm
    public List getJbdTravelPointsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointsByCrm(crm,pager);
    }

	public List getRecommendVip(String userCode, String startTime, String endTime) {
		return dao.getRecommendVip(userCode, startTime, endTime);
	}
}
