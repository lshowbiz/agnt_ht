
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdYkJiandianList;
import com.joymain.jecs.bd.dao.JbdYkJiandianListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdYkJiandianListManager extends Manager {
    /**
     * Retrieves all of the jbdYkJiandianLists
     */
    public List getJbdYkJiandianLists(JbdYkJiandianList jbdYkJiandianList);

    /**
     * Gets jbdYkJiandianList's information based on id.
     * @param id the jbdYkJiandianList's id
     * @return jbdYkJiandianList populated jbdYkJiandianList object
     */
    public JbdYkJiandianList getJbdYkJiandianList(final String id);

    /**
     * Saves a jbdYkJiandianList's information
     * @param jbdYkJiandianList the object to be saved
     */
    public void saveJbdYkJiandianList(JbdYkJiandianList jbdYkJiandianList);

    /**
     * Removes a jbdYkJiandianList from the database by id
     * @param id the jbdYkJiandianList's id
     */
    public void removeJbdYkJiandianList(final String id);
    //added for getJbdYkJiandianListsByCrm
    public List getJbdYkJiandianListsByCrm(CommonRecord crm, Pager pager);
}

