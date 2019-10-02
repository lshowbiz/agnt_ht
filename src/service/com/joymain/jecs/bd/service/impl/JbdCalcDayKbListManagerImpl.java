
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdCalcDayKbList;
import com.joymain.jecs.bd.dao.JbdCalcDayKbListDao;
import com.joymain.jecs.bd.service.JbdCalcDayKbListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdCalcDayKbListManagerImpl extends BaseManager implements JbdCalcDayKbListManager {
    private JbdCalcDayKbListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdCalcDayKbListDao(JbdCalcDayKbListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCalcDayKbListManager#getJbdCalcDayKbLists(com.joymain.jecs.bd.model.JbdCalcDayKbList)
     */
    public List getJbdCalcDayKbLists(final JbdCalcDayKbList jbdCalcDayKbList) {
        return dao.getJbdCalcDayKbLists(jbdCalcDayKbList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCalcDayKbListManager#getJbdCalcDayKbList(String id)
     */
    public JbdCalcDayKbList getJbdCalcDayKbList(final String id) {
        return dao.getJbdCalcDayKbList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCalcDayKbListManager#saveJbdCalcDayKbList(JbdCalcDayKbList jbdCalcDayKbList)
     */
    public void saveJbdCalcDayKbList(JbdCalcDayKbList jbdCalcDayKbList) {
        dao.saveJbdCalcDayKbList(jbdCalcDayKbList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdCalcDayKbListManager#removeJbdCalcDayKbList(String id)
     */
    public void removeJbdCalcDayKbList(final String id) {
        dao.removeJbdCalcDayKbList(new Long(id));
    }
    //added for getJbdCalcDayKbListsByCrm
    public List getJbdCalcDayKbListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdCalcDayKbListsByCrm(crm,pager);
    }
}
