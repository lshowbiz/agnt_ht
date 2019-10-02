
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiSunMemberOrderList;
import com.joymain.jecs.fi.dao.JfiSunMemberOrderListDao;
import com.joymain.jecs.fi.service.JfiSunMemberOrderListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiSunMemberOrderListManagerImpl extends BaseManager implements JfiSunMemberOrderListManager {
    private JfiSunMemberOrderListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiSunMemberOrderListDao(JfiSunMemberOrderListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderListManager#getJfiSunMemberOrderLists(com.joymain.jecs.fi.model.JfiSunMemberOrderList)
     */
    public List getJfiSunMemberOrderLists(final JfiSunMemberOrderList jfiSunMemberOrderList) {
        return dao.getJfiSunMemberOrderLists(jfiSunMemberOrderList);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderListManager#getJfiSunMemberOrderList(String molId)
     */
    public JfiSunMemberOrderList getJfiSunMemberOrderList(final String molId) {
        return dao.getJfiSunMemberOrderList(new Long(molId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderListManager#saveJfiSunMemberOrderList(JfiSunMemberOrderList jfiSunMemberOrderList)
     */
    public void saveJfiSunMemberOrderList(JfiSunMemberOrderList jfiSunMemberOrderList) {
        dao.saveJfiSunMemberOrderList(jfiSunMemberOrderList);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderListManager#removeJfiSunMemberOrderList(String molId)
     */
    public void removeJfiSunMemberOrderList(final String molId) {
        dao.removeJfiSunMemberOrderList(new Long(molId));
    }
    //added for getJfiSunMemberOrderListsByCrm
    public List getJfiSunMemberOrderListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiSunMemberOrderListsByCrm(crm,pager);
    }
}
