
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;

import com.joymain.jecs.bd.model.JbdDayBounsCalcHist;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdDayBounsCalcHistDao extends Dao {

    /**
     * Retrieves all of the jbdDayBounsCalcHists
     */
    public List getJbdDayBounsCalcHists(JbdDayBounsCalcHist jbdDayBounsCalcHist);

    /**
     * Gets jbdDayBounsCalcHist's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdDayBounsCalcHist's id
     * @return jbdDayBounsCalcHist populated jbdDayBounsCalcHist object
     */
    public JbdDayBounsCalcHist getJbdDayBounsCalcHist(final Long id);

    /**
     * Saves a jbdDayBounsCalcHist's information
     * @param jbdDayBounsCalcHist the object to be saved
     */    
    public void saveJbdDayBounsCalcHist(JbdDayBounsCalcHist jbdDayBounsCalcHist);

    /**
     * Removes a jbdDayBounsCalcHist from the database by id
     * @param id the jbdDayBounsCalcHist's id
     */
    public void removeJbdDayBounsCalcHist(final Long id);
    //added for getJbdDayBounsCalcHistsByCrm
    public List getJbdDayBounsCalcHistsByCrm(CommonRecord crm, Pager pager);
}

