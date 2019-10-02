
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiDealList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiDealListDao extends Dao {

    /**
     * Retrieves all of the jmiDealLists
     */
    public List getJmiDealLists(JmiDealList jmiDealList);

    /**
     * Gets jmiDealList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiDealList's id
     * @return jmiDealList populated jmiDealList object
     */
    public JmiDealList getJmiDealList(final Long id);

    /**
     * Saves a jmiDealList's information
     * @param jmiDealList the object to be saved
     */    
    public void saveJmiDealList(JmiDealList jmiDealList);

    /**
     * Removes a jmiDealList from the database by id
     * @param id the jmiDealList's id
     */
    public void removeJmiDealList(final Long id);
    //added for getJmiDealListsByCrm
    public List getJmiDealListsByCrm(CommonRecord crm, Pager pager);
}

