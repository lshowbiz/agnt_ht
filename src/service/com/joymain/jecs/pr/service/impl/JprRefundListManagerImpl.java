
package com.joymain.jecs.pr.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pr.model.JprRefundList;
import com.joymain.jecs.pr.dao.JprRefundListDao;
import com.joymain.jecs.pr.service.JprRefundListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JprRefundListManagerImpl extends BaseManager implements JprRefundListManager {
    private JprRefundListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJprRefundListDao(JprRefundListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pr.service.JprRefundListManager#getJprRefundLists(com.joymain.jecs.pr.model.JprRefundList)
     */
    public List getJprRefundLists(final JprRefundList jprRefundList) {
        return dao.getJprRefundLists(jprRefundList);
    }

    /**
     * @see com.joymain.jecs.pr.service.JprRefundListManager#getJprRefundList(String prlId)
     */
    public JprRefundList getJprRefundList(final String prlId) {
        return dao.getJprRefundList(new Long(prlId));
    }

    /**
     * @see com.joymain.jecs.pr.service.JprRefundListManager#saveJprRefundList(JprRefundList jprRefundList)
     */
    public void saveJprRefundList(JprRefundList jprRefundList) {
        dao.saveJprRefundList(jprRefundList);
    }

    /**
     * @see com.joymain.jecs.pr.service.JprRefundListManager#removeJprRefundList(String prlId)
     */
    public void removeJprRefundList(final String prlId) {
        dao.removeJprRefundList(new Long(prlId));
    }
    //added for getJprRefundListsByCrm
    public List getJprRefundListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJprRefundListsByCrm(crm,pager);
    }
}
