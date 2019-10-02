
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdTravelPoint2015;
import com.joymain.jecs.bd.dao.JbdTravelPoint2015Dao;
import com.joymain.jecs.bd.service.JbdTravelPoint2015Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdTravelPoint2015ManagerImpl extends BaseManager implements JbdTravelPoint2015Manager {
    private JbdTravelPoint2015Dao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdTravelPoint2015Dao(JbdTravelPoint2015Dao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2015Manager#getJbdTravelPoint2015s(com.joymain.jecs.bd.model.JbdTravelPoint2015)
     */
    public List getJbdTravelPoint2015s(final JbdTravelPoint2015 jbdTravelPoint2015) {
        return dao.getJbdTravelPoint2015s(jbdTravelPoint2015);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2015Manager#getJbdTravelPoint2015(String userCode)
     */
    public JbdTravelPoint2015 getJbdTravelPoint2015(final String userCode) {
        return dao.getJbdTravelPoint2015(new String(userCode));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2015Manager#saveJbdTravelPoint2015(JbdTravelPoint2015 jbdTravelPoint2015)
     */
    public void saveJbdTravelPoint2015(JbdTravelPoint2015 jbdTravelPoint2015) {
        dao.saveJbdTravelPoint2015(jbdTravelPoint2015);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdTravelPoint2015Manager#removeJbdTravelPoint2015(String userCode)
     */
    public void removeJbdTravelPoint2015(final String userCode) {
        dao.removeJbdTravelPoint2015(new String(userCode));
    }
    //added for getJbdTravelPoint2015sByCrm
    public List getJbdTravelPoint2015sByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdTravelPoint2015sByCrm(crm,pager);
    }
}
