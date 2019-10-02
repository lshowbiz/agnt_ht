
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTravelPointLog2014;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2014Dao;
import com.joymain.jecs.bd.service.JbdTravelPointLog2014Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPointLog2014ManagerImpl extends BaseManager implements JbdTravelPointLog2014Manager {
    private JbdTravelPointLog2014Dao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointLog2014Dao(JbdTravelPointLog2014Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2014Manager#getJbdTravelPointLog2014s(com.joymain.jecs.bd.model.JbdTravelPointLog2014)
     */
    public List getJbdTravelPointLog2014s(final JbdTravelPointLog2014 jbdTravelPointLog2014) {
        return dao.getJbdTravelPointLog2014s(jbdTravelPointLog2014);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2014Manager#getJbdTravelPointLog2014(String logId)
     */
    public JbdTravelPointLog2014 getJbdTravelPointLog2014(final String logId) {
        return dao.getJbdTravelPointLog2014(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2014Manager#saveJbdTravelPointLog2014(JbdTravelPointLog2014 jbdTravelPointLog2014)
     */
    public void saveJbdTravelPointLog2014(JbdTravelPointLog2014 jbdTravelPointLog2014) {
        dao.saveJbdTravelPointLog2014(jbdTravelPointLog2014);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2014Manager#removeJbdTravelPointLog2014(String logId)
     */
    public void removeJbdTravelPointLog2014(final String logId) {
        dao.removeJbdTravelPointLog2014(new Long(logId));
    }
    //added for getJbdTravelPointLog2014sByCrm
    public List getJbdTravelPointLog2014sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointLog2014sByCrm(crm,pager);
    }

	public List getJbdTravelPointLogByPassStar(String userCode, String passStar) {
		return dao.getJbdTravelPointLogByPassStar(userCode, passStar);
	}
}
