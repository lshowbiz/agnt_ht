
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTravelPoint2013;
import com.joymain.jecs.bd.dao.JbdTravelPoint2013Dao;
import com.joymain.jecs.bd.service.JbdTravelPoint2013Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPoint2013ManagerImpl extends BaseManager implements JbdTravelPoint2013Manager {
    private JbdTravelPoint2013Dao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPoint2013Dao(JbdTravelPoint2013Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2013Manager#getJbdTravelPoint2013s(com.joymain.jecs.bd.model.JbdTravelPoint2013)
     */
    public List getJbdTravelPoint2013s(final JbdTravelPoint2013 jbdTravelPoint2013) {
        return dao.getJbdTravelPoint2013s(jbdTravelPoint2013);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2013Manager#getJbdTravelPoint2013(String userCode)
     */
    public JbdTravelPoint2013 getJbdTravelPoint2013(final String userCode) {
        return dao.getJbdTravelPoint2013(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2013Manager#saveJbdTravelPoint2013(JbdTravelPoint2013 jbdTravelPoint2013)
     */
    public void saveJbdTravelPoint2013(JbdTravelPoint2013 jbdTravelPoint2013) {
        dao.saveJbdTravelPoint2013(jbdTravelPoint2013);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2013Manager#removeJbdTravelPoint2013(String userCode)
     */
    public void removeJbdTravelPoint2013(final String userCode) {
        dao.removeJbdTravelPoint2013(new String(userCode));
    }
    //added for getJbdTravelPoint2013sByCrm
    public List getJbdTravelPoint2013sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPoint2013sByCrm(crm,pager);
    }

	public List getJbdTravelPoint2013sByUserCode(String userCode) {
		return dao.getJbdTravelPoint2013sByUserCode(userCode);
	}
}
