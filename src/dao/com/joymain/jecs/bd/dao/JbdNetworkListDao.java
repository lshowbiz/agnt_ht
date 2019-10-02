
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdNetworkList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdNetworkListDao extends Dao {

    /**
     * Retrieves all of the jbdNetworkLists
     */
    public List getJbdNetworkLists(JbdNetworkList jbdNetworkList);

    /**
     * Gets jbdNetworkList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdNetworkList's id
     * @return jbdNetworkList populated jbdNetworkList object
     */
    public JbdNetworkList getJbdNetworkList(final BigDecimal id);

    /**
     * Saves a jbdNetworkList's information
     * @param jbdNetworkList the object to be saved
     */    
    public void saveJbdNetworkList(JbdNetworkList jbdNetworkList);

    /**
     * Removes a jbdNetworkList from the database by id
     * @param id the jbdNetworkList's id
     */
    public void removeJbdNetworkList(final BigDecimal id);
    //added for getJbdNetworkListsByCrm
    public List getJbdNetworkListsByCrm(CommonRecord crm, Pager pager);

    public Object[] getJbdNetworkListsByCrmSum(CommonRecord crm);
}

