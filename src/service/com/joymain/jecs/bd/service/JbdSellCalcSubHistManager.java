
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdSellCalcSubHist;
import com.joymain.jecs.bd.dao.JbdSellCalcSubHistDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdSellCalcSubHistManager extends Manager {
    /**
     * Retrieves all of the jbdSellCalcSubHists
     */
    public List getJbdSellCalcSubHists(JbdSellCalcSubHist jbdSellCalcSubHist);

    /**
     * Gets jbdSellCalcSubHist's information based on id.
     * @param id the jbdSellCalcSubHist's id
     * @return jbdSellCalcSubHist populated jbdSellCalcSubHist object
     */
    public JbdSellCalcSubHist getJbdSellCalcSubHist(final String id);

    /**
     * Saves a jbdSellCalcSubHist's information
     * @param jbdSellCalcSubHist the object to be saved
     */
    public void saveJbdSellCalcSubHist(JbdSellCalcSubHist jbdSellCalcSubHist);

    /**
     * Removes a jbdSellCalcSubHist from the database by id
     * @param id the jbdSellCalcSubHist's id
     */
    public void removeJbdSellCalcSubHist(final String id);
    //added for getJbdSellCalcSubHistsByCrm
    public List getJbdSellCalcSubHistsByCrm(CommonRecord crm, Pager pager);
    public List getJbdSellCalcSubHistsByLink(String linkNo,Integer wweek);
}

