
package com.joymain.jecs.bd.service;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdSellCalcSubDetailHist;
import com.joymain.jecs.bd.dao.JbdSellCalcSubDetailHistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdSellCalcSubDetailHistManager extends Manager {
    /**
     * Retrieves all of the jbdSellCalcSubDetailHists
     */
    public List getJbdSellCalcSubDetailHists(JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist);

    /**
     * Gets jbdSellCalcSubDetailHist's information based on id.
     * @param id the jbdSellCalcSubDetailHist's id
     * @return jbdSellCalcSubDetailHist populated jbdSellCalcSubDetailHist object
     */
    public JbdSellCalcSubDetailHist getJbdSellCalcSubDetailHist(final String id);

    /**
     * Saves a jbdSellCalcSubDetailHist's information
     * @param jbdSellCalcSubDetailHist the object to be saved
     */
    public void saveJbdSellCalcSubDetailHist(JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist);

    /**
     * Removes a jbdSellCalcSubDetailHist from the database by id
     * @param id the jbdSellCalcSubDetailHist's id
     */
    public void removeJbdSellCalcSubDetailHist(final String id);
    //added for getJbdSellCalcSubDetailHistsByCrm
    public List getJbdSellCalcSubDetailHistsByCrm(CommonRecord crm, Pager pager);
	public List getJbdSellCalcRecommendBouns(String userCode, Integer wweek,String bounsType);

	public BigDecimal getSumJbdSellCalcRecommendBouns(CommonRecord crm);
}

