
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.dao.JbdDayBounsCalcHistDao;
import com.joymain.jecs.bd.model.JbdDayBounsCalcHist;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdDayBounsCalcHistManager extends Manager {
    /**
     * Retrieves all of the jbdDayBounsCalcHists
     */
    public List getJbdDayBounsCalcHists(JbdDayBounsCalcHist jbdDayBounsCalcHist);

    /**
     * Gets jbdDayBounsCalcHist's information based on id.
     * @param id the jbdDayBounsCalcHist's id
     * @return jbdDayBounsCalcHist populated jbdDayBounsCalcHist object
     */
    public JbdDayBounsCalcHist getJbdDayBounsCalcHist(final String id);

    /**
     * Saves a jbdDayBounsCalcHist's information
     * @param jbdDayBounsCalcHist the object to be saved
     */
    public void saveJbdDayBounsCalcHist(JbdDayBounsCalcHist jbdDayBounsCalcHist);

    /**
     * Removes a jbdDayBounsCalcHist from the database by id
     * @param id the jbdDayBounsCalcHist's id
     */
    public void removeJbdDayBounsCalcHist(final String id);
    //added for getJbdDayBounsCalcHistsByCrm
    public List getJbdDayBounsCalcHistsByCrm(CommonRecord crm, Pager pager);
}

