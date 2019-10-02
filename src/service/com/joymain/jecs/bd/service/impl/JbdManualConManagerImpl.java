
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdManualCon;
import com.joymain.jecs.bd.dao.JbdManualConDao;
import com.joymain.jecs.bd.service.JbdManualConManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdManualConManagerImpl extends BaseManager implements JbdManualConManager {
    private JbdManualConDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdManualConDao(JbdManualConDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManualConManager#getJbdManualCons(com.joymain.jecs.bd.model.JbdManualCon)
     */
    public List getJbdManualCons(final JbdManualCon jbdManualCon) {
        return dao.getJbdManualCons(jbdManualCon);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManualConManager#getJbdManualCon(String id)
     */
    public JbdManualCon getJbdManualCon(final String id) {
        return dao.getJbdManualCon(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManualConManager#saveJbdManualCon(JbdManualCon jbdManualCon)
     */
    public void saveJbdManualCon(JbdManualCon jbdManualCon) {
        dao.saveJbdManualCon(jbdManualCon);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdManualConManager#removeJbdManualCon(String id)
     */
    public void removeJbdManualCon(final String id) {
        dao.removeJbdManualCon(new Long(id));
    }
    //added for getJbdManualConsByCrm
    public List getJbdManualConsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdManualConsByCrm(crm,pager);
    }

	public void saveJbdManualCons(List<JbdManualCon> jbdManualCons) {
		dao.saveJbdManualCons(jbdManualCons);
		
	}
}
