
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTravelPointLog2012;
import com.joymain.jecs.bd.dao.JbdTravelPointLog2012Dao;
import com.joymain.jecs.bd.service.JbdTravelPointLog2012Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPointLog2012ManagerImpl extends BaseManager implements JbdTravelPointLog2012Manager {
    private JbdTravelPointLog2012Dao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPointLog2012Dao(JbdTravelPointLog2012Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2012Manager#getJbdTravelPointLog2012s(com.joymain.jecs.bd.model.JbdTravelPointLog2012)
     */
    public List getJbdTravelPointLog2012s(final JbdTravelPointLog2012 jbdTravelPointLog2012) {
        return dao.getJbdTravelPointLog2012s(jbdTravelPointLog2012);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2012Manager#getJbdTravelPointLog2012(String logId)
     */
    public JbdTravelPointLog2012 getJbdTravelPointLog2012(final String logId) {
        return dao.getJbdTravelPointLog2012(new Long(logId));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2012Manager#saveJbdTravelPointLog2012(JbdTravelPointLog2012 jbdTravelPointLog2012)
     */
    public void saveJbdTravelPointLog2012(JbdTravelPointLog2012 jbdTravelPointLog2012) {
        dao.saveJbdTravelPointLog2012(jbdTravelPointLog2012);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPointLog2012Manager#removeJbdTravelPointLog2012(String logId)
     */
    public void removeJbdTravelPointLog2012(final String logId) {
        dao.removeJbdTravelPointLog2012(new Long(logId));
    }
    //added for getJbdTravelPointLog2012sByCrm
    public List getJbdTravelPointLog2012sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPointLog2012sByCrm(crm,pager);
    }
}
