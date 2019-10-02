
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdSellCalcSubHist;
import com.joymain.jecs.bd.dao.JbdSellCalcSubHistDao;
import com.joymain.jecs.bd.service.JbdSellCalcSubHistManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdSellCalcSubHistManagerImpl extends BaseManager implements JbdSellCalcSubHistManager {
    private JbdSellCalcSubHistDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdSellCalcSubHistDao(JbdSellCalcSubHistDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSellCalcSubHistManager#getJbdSellCalcSubHists(com.joymain.jecs.bd.model.JbdSellCalcSubHist)
     */
    public List getJbdSellCalcSubHists(final JbdSellCalcSubHist jbdSellCalcSubHist) {
        return dao.getJbdSellCalcSubHists(jbdSellCalcSubHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSellCalcSubHistManager#getJbdSellCalcSubHist(String id)
     */
    public JbdSellCalcSubHist getJbdSellCalcSubHist(final String id) {
        return dao.getJbdSellCalcSubHist(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSellCalcSubHistManager#saveJbdSellCalcSubHist(JbdSellCalcSubHist jbdSellCalcSubHist)
     */
    public void saveJbdSellCalcSubHist(JbdSellCalcSubHist jbdSellCalcSubHist) {
        dao.saveJbdSellCalcSubHist(jbdSellCalcSubHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSellCalcSubHistManager#removeJbdSellCalcSubHist(String id)
     */
    public void removeJbdSellCalcSubHist(final String id) {
        dao.removeJbdSellCalcSubHist(new BigDecimal(id));
    }
    //added for getJbdSellCalcSubHistsByCrm
    public List getJbdSellCalcSubHistsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdSellCalcSubHistsByCrm(crm,pager);
    }

	public List getJbdSellCalcSubHistsByLink(String linkNo, Integer wweek) {
		return dao.getJbdSellCalcSubHistsByLink(linkNo, wweek);
	}

}
