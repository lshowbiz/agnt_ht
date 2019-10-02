
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTravelPointLog2013;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2013Dao;
import com.joymain.jecs.bd.service.JbdTravelPointLog2013Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPointLog2013ManagerImpl extends BaseManager implements JbdTravelPointLog2013Manager {
    private JbdTravelPointLog2013Dao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointLog2013Dao(JbdTravelPointLog2013Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2013Manager#getJbdTravelPointLog2013s(com.joymain.jecs.bd.model.JbdTravelPointLog2013)
     */
    public List getJbdTravelPointLog2013s(final JbdTravelPointLog2013 jbdTravelPointLog2013) {
        return dao.getJbdTravelPointLog2013s(jbdTravelPointLog2013);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2013Manager#getJbdTravelPointLog2013(String logId)
     */
    public JbdTravelPointLog2013 getJbdTravelPointLog2013(final String logId) {
        return dao.getJbdTravelPointLog2013(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2013Manager#saveJbdTravelPointLog2013(JbdTravelPointLog2013 jbdTravelPointLog2013)
     */
    public void saveJbdTravelPointLog2013(JbdTravelPointLog2013 jbdTravelPointLog2013) {
        dao.saveJbdTravelPointLog2013(jbdTravelPointLog2013);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2013Manager#removeJbdTravelPointLog2013(String logId)
     */
    public void removeJbdTravelPointLog2013(final String logId) {
        dao.removeJbdTravelPointLog2013(new Long(logId));
    }
    //added for getJbdTravelPointLog2013sByCrm
    public List getJbdTravelPointLog2013sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointLog2013sByCrm(crm,pager);
    }

	public List getJbdTravelPointLogByPassStar(String userCode, String passStar) {
		return dao.getJbdTravelPointLogByPassStar(userCode, passStar);
	}
}
