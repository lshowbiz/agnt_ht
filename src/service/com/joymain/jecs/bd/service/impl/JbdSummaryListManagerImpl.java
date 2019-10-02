
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.bd.dao.JbdSummaryListDao;
import com.joymain.jecs.bd.service.JbdSummaryListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdSummaryListManagerImpl extends BaseManager implements JbdSummaryListManager {
    private JbdSummaryListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdSummaryListDao(JbdSummaryListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSummaryListManager#getJbdSummaryLists(com.joymain.jecs.bd.model.JbdSummaryList)
     */
    public List getJbdSummaryLists(final JbdSummaryList jbdSummaryList) {
        return dao.getJbdSummaryLists(jbdSummaryList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSummaryListManager#getJbdSummaryList(String id)
     */
    public JbdSummaryList getJbdSummaryList(final String id) {
        return dao.getJbdSummaryList(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSummaryListManager#saveJbdSummaryList(JbdSummaryList jbdSummaryList)
     */
    public void saveJbdSummaryList(JbdSummaryList jbdSummaryList) {
        dao.saveJbdSummaryList(jbdSummaryList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSummaryListManager#removeJbdSummaryList(String id)
     */
    public void removeJbdSummaryList(final String id) {
        dao.removeJbdSummaryList(new BigDecimal(id));
    }
    //added for getJbdSummaryListsByCrm
    public List getJbdSummaryListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdSummaryListsByCrm(crm,pager);
    }
}
