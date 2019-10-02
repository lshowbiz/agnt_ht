
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdSellCalcSubHist;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdSellCalcSubHistDao extends Dao {

    /**
     * Retrieves all of the jbdSellCalcSubHists
     */
    public List getJbdSellCalcSubHists(JbdSellCalcSubHist jbdSellCalcSubHist);

    /**
     * Gets jbdSellCalcSubHist's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdSellCalcSubHist's id
     * @return jbdSellCalcSubHist populated jbdSellCalcSubHist object
     */
    public JbdSellCalcSubHist getJbdSellCalcSubHist(final BigDecimal id);

    /**
     * Saves a jbdSellCalcSubHist's information
     * @param jbdSellCalcSubHist the object to be saved
     */    
    public void saveJbdSellCalcSubHist(JbdSellCalcSubHist jbdSellCalcSubHist);

    /**
     * Removes a jbdSellCalcSubHist from the database by id
     * @param id the jbdSellCalcSubHist's id
     */
    public void removeJbdSellCalcSubHist(final BigDecimal id);
    //added for getJbdSellCalcSubHistsByCrm
    public List getJbdSellCalcSubHistsByCrm(CommonRecord crm, Pager pager);
    
    public List getJbdSellCalcSubHistsByLink(String linkNo,Integer wweek);
    public List getJbdSellCalcSubHistsByLinkHist(String linkNo, Integer wweek);
}

