
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdSellCalcSubDetailHist;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdSellCalcSubDetailHistDao extends Dao {

    /**
     * Retrieves all of the jbdSellCalcSubDetailHists
     */
    public List getJbdSellCalcSubDetailHists(JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist);

    /**
     * Gets jbdSellCalcSubDetailHist's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdSellCalcSubDetailHist's id
     * @return jbdSellCalcSubDetailHist populated jbdSellCalcSubDetailHist object
     */
    public JbdSellCalcSubDetailHist getJbdSellCalcSubDetailHist(final Long id);

    /**
     * Saves a jbdSellCalcSubDetailHist's information
     * @param jbdSellCalcSubDetailHist the object to be saved
     */    
    public void saveJbdSellCalcSubDetailHist(JbdSellCalcSubDetailHist jbdSellCalcSubDetailHist);

    /**
     * Removes a jbdSellCalcSubDetailHist from the database by id
     * @param id the jbdSellCalcSubDetailHist's id
     */
    public void removeJbdSellCalcSubDetailHist(final Long id);
    //added for getJbdSellCalcSubDetailHistsByCrm
    public List getJbdSellCalcSubDetailHistsByCrm(CommonRecord crm, Pager pager);
    public List getJbdSellCalcRecommendBouns(String userCode , Integer wweek,String bounsType);

	public BigDecimal getSumJbdSellCalcRecommendBouns(CommonRecord crm);
}

