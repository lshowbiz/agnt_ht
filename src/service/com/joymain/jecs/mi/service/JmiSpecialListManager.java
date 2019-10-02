
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiSpecialList;
import com.joymain.jecs.mi.dao.JmiSpecialListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiSpecialListManager extends Manager {
    /**
     * Retrieves all of the jmiSpecialLists
     */
    public List getJmiSpecialLists(JmiSpecialList jmiSpecialList);

    /**
     * Gets jmiSpecialList's information based on id.
     * @param id the jmiSpecialList's id
     * @return jmiSpecialList populated jmiSpecialList object
     */
    public JmiSpecialList getJmiSpecialList(final String id);

    /**
     * Saves a jmiSpecialList's information
     * @param jmiSpecialList the object to be saved
     */
    public void saveJmiSpecialList(JmiSpecialList jmiSpecialList);

    /**
     * Removes a jmiSpecialList from the database by id
     * @param id the jmiSpecialList's id
     */
    public void removeJmiSpecialList(final String id);
    //added for getJmiSpecialListsByCrm
    public List getJmiSpecialListsByCrm(CommonRecord crm, Pager pager);
    public void saveJmiSpecialList(List<JmiSpecialList> jmiSpecialList);
}

