
package com.joymain.jecs.bd.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdNetworkList;
import com.joymain.jecs.bd.dao.JbdNetworkListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdNetworkListManager extends Manager {
    /**
     * Retrieves all of the jbdNetworkLists
     */
    public List getJbdNetworkLists(JbdNetworkList jbdNetworkList);

    /**
     * Gets jbdNetworkList's information based on id.
     * @param id the jbdNetworkList's id
     * @return jbdNetworkList populated jbdNetworkList object
     */
    public JbdNetworkList getJbdNetworkList(final String id);

    /**
     * Saves a jbdNetworkList's information
     * @param jbdNetworkList the object to be saved
     */
    public void saveJbdNetworkList(JbdNetworkList jbdNetworkList);

    /**
     * Removes a jbdNetworkList from the database by id
     * @param id the jbdNetworkList's id
     */
    public void removeJbdNetworkList(final String id);
    //added for getJbdNetworkListsByCrm
    public List getJbdNetworkListsByCrm(CommonRecord crm, Pager pager);

    public Object[] getJbdNetworkListsByCrmSum(CommonRecord crm);
}

