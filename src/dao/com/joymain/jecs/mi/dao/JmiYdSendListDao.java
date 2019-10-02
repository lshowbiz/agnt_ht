
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiYdSendList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiYdSendListDao extends Dao {

    /**
     * Retrieves all of the jmiYdSendLists
     */
    public List getJmiYdSendLists(JmiYdSendList jmiYdSendList);

    /**
     * Gets jmiYdSendList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiYdSendList's id
     * @return jmiYdSendList populated jmiYdSendList object
     */
    public JmiYdSendList getJmiYdSendList(final Long id);

    /**
     * Saves a jmiYdSendList's information
     * @param jmiYdSendList the object to be saved
     */    
    public void saveJmiYdSendList(JmiYdSendList jmiYdSendList);

    /**
     * Removes a jmiYdSendList from the database by id
     * @param id the jmiYdSendList's id
     */
    public void removeJmiYdSendList(final Long id);
    //added for getJmiYdSendListsByCrm
    public List getJmiYdSendListsByCrm(CommonRecord crm, Pager pager);
}

