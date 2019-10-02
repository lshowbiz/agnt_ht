
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.dao.JbdDayBounsCalcHistDao;
import com.joymain.jecs.bd.model.JbdDayBounsCalcHist;
import com.joymain.jecs.bd.service.JbdDayBounsCalcHistManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdDayBounsCalcHistManagerImpl extends BaseManager implements JbdDayBounsCalcHistManager {
    private JbdDayBounsCalcHistDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdDayBounsCalcHistDao(JbdDayBounsCalcHistDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayBounsCalcHistManager#getJbdDayBounsCalcHists(com.joymain.jecs.bd.model.JbdDayBounsCalcHist)
     */
    public List getJbdDayBounsCalcHists(final JbdDayBounsCalcHist jbdDayBounsCalcHist) {
        return dao.getJbdDayBounsCalcHists(jbdDayBounsCalcHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayBounsCalcHistManager#getJbdDayBounsCalcHist(String id)
     */
    public JbdDayBounsCalcHist getJbdDayBounsCalcHist(final String id) {
        return dao.getJbdDayBounsCalcHist(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayBounsCalcHistManager#saveJbdDayBounsCalcHist(JbdDayBounsCalcHist jbdDayBounsCalcHist)
     */
    public void saveJbdDayBounsCalcHist(JbdDayBounsCalcHist jbdDayBounsCalcHist) {
        dao.saveJbdDayBounsCalcHist(jbdDayBounsCalcHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdDayBounsCalcHistManager#removeJbdDayBounsCalcHist(String id)
     */
    public void removeJbdDayBounsCalcHist(final String id) {
        dao.removeJbdDayBounsCalcHist(new Long(id));
    }
    //added for getJbdDayBounsCalcHistsByCrm
    public List getJbdDayBounsCalcHistsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdDayBounsCalcHistsByCrm(crm,pager);
    }
}
