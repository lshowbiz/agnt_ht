
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiSunMemberOrder;
import com.joymain.jecs.fi.dao.JfiSunMemberOrderDao;
import com.joymain.jecs.fi.service.JfiSunMemberOrderManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiSunMemberOrderManagerImpl extends BaseManager implements JfiSunMemberOrderManager {
    private JfiSunMemberOrderDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiSunMemberOrderDao(JfiSunMemberOrderDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderManager#getJfiSunMemberOrders(com.joymain.jecs.fi.model.JfiSunMemberOrder)
     */
    public List getJfiSunMemberOrders(final JfiSunMemberOrder jfiSunMemberOrder) {
        return dao.getJfiSunMemberOrders(jfiSunMemberOrder);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderManager#getJfiSunMemberOrder(String moId)
     */
    public JfiSunMemberOrder getJfiSunMemberOrder(final String moId) {
        return dao.getJfiSunMemberOrder(new Long(moId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderManager#saveJfiSunMemberOrder(JfiSunMemberOrder jfiSunMemberOrder)
     */
    public void saveJfiSunMemberOrder(JfiSunMemberOrder jfiSunMemberOrder) {
        dao.saveJfiSunMemberOrder(jfiSunMemberOrder);
    }

    public List getJfiSunMemberOrderReportByCrm(CommonRecord crm){
    	return dao.getJfiSunMemberOrderReportByCrm(crm);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderManager#removeJfiSunMemberOrder(String moId)
     */
    public void removeJfiSunMemberOrder(final String moId) {
        dao.removeJfiSunMemberOrder(new Long(moId));
    }
    //added for getJfiSunMemberOrdersByCrm
    public List getJfiSunMemberOrdersByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiSunMemberOrdersByCrm(crm,pager);
    }
}
