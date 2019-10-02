
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiDealList;
import com.joymain.jecs.mi.dao.JmiDealListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiDealListManager extends Manager {
    /**
     * Retrieves all of the jmiDealLists
     */
    public List getJmiDealLists(JmiDealList jmiDealList);

    /**
     * Gets jmiDealList's information based on id.
     * @param id the jmiDealList's id
     * @return jmiDealList populated jmiDealList object
     */
    public JmiDealList getJmiDealList(final String id);

    /**
     * Saves a jmiDealList's information
     * @param jmiDealList the object to be saved
     */
    public void saveJmiDealList(JmiDealList jmiDealList);

    /**
     * Removes a jmiDealList from the database by id
     * @param id the jmiDealList's id
     */
    public void removeJmiDealList(final String id);
    //added for getJmiDealListsByCrm
    public List getJmiDealListsByCrm(CommonRecord crm, Pager pager);
}

