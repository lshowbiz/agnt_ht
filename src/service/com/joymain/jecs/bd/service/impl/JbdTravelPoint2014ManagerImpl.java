
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTravelPoint2014;
import com.joymain.jecs.bd.dao.JbdTravelPoint2014Dao;
import com.joymain.jecs.bd.service.JbdTravelPoint2014Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPoint2014ManagerImpl extends BaseManager implements JbdTravelPoint2014Manager {
    private JbdTravelPoint2014Dao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPoint2014Dao(JbdTravelPoint2014Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2014Manager#getJbdTravelPoint2014s(com.joymain.jecs.bd.model.JbdTravelPoint2014)
     */
    public List getJbdTravelPoint2014s(final JbdTravelPoint2014 jbdTravelPoint2014) {
        return dao.getJbdTravelPoint2014s(jbdTravelPoint2014);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2014Manager#getJbdTravelPoint2014(String userCode)
     */
    public JbdTravelPoint2014 getJbdTravelPoint2014(final String userCode) {
        return dao.getJbdTravelPoint2014(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2014Manager#saveJbdTravelPoint2014(JbdTravelPoint2014 jbdTravelPoint2014)
     */
    public void saveJbdTravelPoint2014(JbdTravelPoint2014 jbdTravelPoint2014) {
        dao.saveJbdTravelPoint2014(jbdTravelPoint2014);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2014Manager#removeJbdTravelPoint2014(String userCode)
     */
    public void removeJbdTravelPoint2014(final String userCode) {
        dao.removeJbdTravelPoint2014(new String(userCode));
    }
    //added for getJbdTravelPoint2014sByCrm
    public List getJbdTravelPoint2014sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPoint2014sByCrm(crm,pager);
    }

	public List getJbdTravelPoint2014sByUserCode(String userCode) {
		return dao.getJbdTravelPoint2014sByUserCode(userCode);
	}
}
