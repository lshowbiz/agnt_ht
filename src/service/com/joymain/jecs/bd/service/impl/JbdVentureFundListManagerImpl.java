
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdVentureFundList;
import com.joymain.jecs.bd.dao.JbdVentureFundListDao;
import com.joymain.jecs.bd.service.JbdVentureFundListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdVentureFundListManagerImpl extends BaseManager implements JbdVentureFundListManager {
    private JbdVentureFundListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdVentureFundListDao(JbdVentureFundListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdVentureFundListManager#getJbdVentureFundLists(com.joymain.jecs.bd.model.JbdVentureFundList)
     */
    public List getJbdVentureFundLists(final JbdVentureFundList jbdVentureFundList) {
        return dao.getJbdVentureFundLists(jbdVentureFundList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdVentureFundListManager#getJbdVentureFundList(String id)
     */
    public JbdVentureFundList getJbdVentureFundList(final String id) {
        return dao.getJbdVentureFundList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdVentureFundListManager#saveJbdVentureFundList(JbdVentureFundList jbdVentureFundList)
     */
    public void saveJbdVentureFundList(JbdVentureFundList jbdVentureFundList) {
        dao.saveJbdVentureFundList(jbdVentureFundList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdVentureFundListManager#removeJbdVentureFundList(String id)
     */
    public void removeJbdVentureFundList(final String id) {
        dao.removeJbdVentureFundList(new Long(id));
    }
    //added for getJbdVentureFundListsByCrm
    public List getJbdVentureFundListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdVentureFundListsByCrm(crm,pager);
    }
}
