
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiSunOrderList;
import com.joymain.jecs.fi.dao.JfiSunOrderListDao;
import com.joymain.jecs.fi.service.JfiSunOrderListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiSunOrderListManagerImpl extends BaseManager implements JfiSunOrderListManager {
    private JfiSunOrderListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiSunOrderListDao(JfiSunOrderListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunOrderListManager#getJfiSunOrderLists(com.joymain.jecs.fi.model.JfiSunOrderList)
     */
    public List getJfiSunOrderLists(final JfiSunOrderList jfiSunOrderList) {
        return dao.getJfiSunOrderLists(jfiSunOrderList);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunOrderListManager#getJfiSunOrderList(String molId)
     */
    public JfiSunOrderList getJfiSunOrderList(final String molId) {
        return dao.getJfiSunOrderList(new Long(molId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunOrderListManager#saveJfiSunOrderList(JfiSunOrderList jfiSunOrderList)
     */
    public void saveJfiSunOrderList(JfiSunOrderList jfiSunOrderList) {
        dao.saveJfiSunOrderList(jfiSunOrderList);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunOrderListManager#removeJfiSunOrderList(String molId)
     */
    public void removeJfiSunOrderList(final String molId) {
        dao.removeJfiSunOrderList(new Long(molId));
    }
    //added for getJfiSunOrderListsByCrm
    public List getJfiSunOrderListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiSunOrderListsByCrm(crm,pager);
    }
}
