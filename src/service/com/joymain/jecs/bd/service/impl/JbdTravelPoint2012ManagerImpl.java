
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTravelPoint2012;
import com.joymain.jecs.bd.dao.JbdTravelPoint2012Dao;
import com.joymain.jecs.bd.service.JbdTravelPoint2012Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPoint2012ManagerImpl extends BaseManager implements JbdTravelPoint2012Manager {
    private JbdTravelPoint2012Dao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPoint2012Dao(JbdTravelPoint2012Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2012Manager#getJbdTravelPoint2012s(com.joymain.jecs.bd.model.JbdTravelPoint2012)
     */
    public List getJbdTravelPoint2012s(final JbdTravelPoint2012 jbdTravelPoint2012) {
        return dao.getJbdTravelPoint2012s(jbdTravelPoint2012);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2012Manager#getJbdTravelPoint2012(String userCode)
     */
    public JbdTravelPoint2012 getJbdTravelPoint2012(final String userCode) {
        return dao.getJbdTravelPoint2012(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2012Manager#saveJbdTravelPoint2012(JbdTravelPoint2012 jbdTravelPoint2012)
     */
    public void saveJbdTravelPoint2012(JbdTravelPoint2012 jbdTravelPoint2012) {
        dao.saveJbdTravelPoint2012(jbdTravelPoint2012);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2012Manager#removeJbdTravelPoint2012(String userCode)
     */
    public void removeJbdTravelPoint2012(final String userCode) {
        dao.removeJbdTravelPoint2012(new String(userCode));
    }
    //added for getJbdTravelPoint2012sByCrm
    public List getJbdTravelPoint2012sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPoint2012sByCrm(crm,pager);
    }
}
