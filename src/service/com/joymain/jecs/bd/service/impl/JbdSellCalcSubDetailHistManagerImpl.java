
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdSellCalcSubDetailHist;
import com.joymain.jecs.bd.dao.JbdSellCalcSubDetailHistDao;
import com.joymain.jecs.bd.service.JbdSellCalcSubDetailHistManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdSellCalcSubDetailHistManagerImpl extends BaseManager implements JbdSellCalcSubDetailHistManager {
    private JbdSellCalcSubDetailHistDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdSellCalcSubDetailHistDao(JbdSellCalcSubDetailHistDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSellCalcSubDetailHistManager#getJbdSellCalcSubDetailHists(com.joymain.jecs.bd.model.JbdSellCalcSubDetailHist)
     */
    public List getJbdSellCalcSubDetailHists(final JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist) {
        return dao.getJbdSellCalcSubDetailHists(jbdSellCalcSubDetailHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSellCalcSubDetailHistManager#getJbdSellCalcSubDetailHist(String id)
     */
    public JbdSellCalcSubDetailHist getJbdSellCalcSubDetailHist(final String id) {
        return dao.getJbdSellCalcSubDetailHist(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSellCalcSubDetailHistManager#saveJbdSellCalcSubDetailHist(JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist)
     */
    public void saveJbdSellCalcSubDetailHist(JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist) {
        dao.saveJbdSellCalcSubDetailHist(jbdSellCalcSubDetailHist);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSellCalcSubDetailHistManager#removeJbdSellCalcSubDetailHist(String id)
     */
    public void removeJbdSellCalcSubDetailHist(final String id) {
        dao.removeJbdSellCalcSubDetailHist(new Long(id));
    }
    //added for getJbdSellCalcSubDetailHistsByCrm
    public List getJbdSellCalcSubDetailHistsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdSellCalcSubDetailHistsByCrm(crm,pager);
    }

	public List getJbdSellCalcRecommendBouns(String userCode, Integer wweek,String bounsType) {
		return dao.getJbdSellCalcRecommendBouns(userCode, wweek, bounsType);
	}

	public BigDecimal getSumJbdSellCalcRecommendBouns(CommonRecord crm) {
		return dao.getSumJbdSellCalcRecommendBouns(crm);
	}
}
