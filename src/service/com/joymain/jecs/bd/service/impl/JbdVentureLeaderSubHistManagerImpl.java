
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdVentureLeaderSubHist;
import com.joymain.jecs.bd.dao.JbdVentureLeaderSubHistDao;
import com.joymain.jecs.bd.service.JbdVentureLeaderSubHistManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdVentureLeaderSubHistManagerImpl extends BaseManager implements JbdVentureLeaderSubHistManager {
    private JbdVentureLeaderSubHistDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdVentureLeaderSubHistDao(JbdVentureLeaderSubHistDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdVentureLeaderSubHistManager#getJbdVentureLeaderSubHists(com.joymain.jecs.bd.model.JbdVentureLeaderSubHist)
     */
    public List getJbdVentureLeaderSubHists(final JbdVentureLeaderSubHist jbdVentureLeaderSubHist) {
        return dao.getJbdVentureLeaderSubHists(jbdVentureLeaderSubHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdVentureLeaderSubHistManager#getJbdVentureLeaderSubHist(String id)
     */
    public JbdVentureLeaderSubHist getJbdVentureLeaderSubHist(final String id) {
        return dao.getJbdVentureLeaderSubHist(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdVentureLeaderSubHistManager#saveJbdVentureLeaderSubHist(JbdVentureLeaderSubHist jbdVentureLeaderSubHist)
     */
    public void saveJbdVentureLeaderSubHist(JbdVentureLeaderSubHist jbdVentureLeaderSubHist) {
        dao.saveJbdVentureLeaderSubHist(jbdVentureLeaderSubHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdVentureLeaderSubHistManager#removeJbdVentureLeaderSubHist(String id)
     */
    public void removeJbdVentureLeaderSubHist(final String id) {
        dao.removeJbdVentureLeaderSubHist(new Long(id));
    }
    //added for getJbdVentureLeaderSubHistsByCrm
    public List getJbdVentureLeaderSubHistsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdVentureLeaderSubHistsByCrm(crm,pager);
    }

	public List getJbdVentureLeaderSubHistByUserCode(String userCode, String wweek, String bounsType) {
		return dao.getJbdVentureLeaderSubHistByUserCode(userCode, wweek, bounsType);
	}
	public List getJbdVentureLeaderSubHistDetailByUserCode(String userCode, String wweek) {
		return dao.getJbdVentureLeaderSubHistDetailByUserCode(userCode, wweek);
	}
}
