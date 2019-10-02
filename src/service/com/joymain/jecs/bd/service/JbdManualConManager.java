
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdManualCon;
import com.joymain.jecs.bd.dao.JbdManualConDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdManualConManager extends Manager {
    /**
     * Retrieves all of the jbdManualCons
     */
    public List getJbdManualCons(JbdManualCon jbdManualCon);

    /**
     * Gets jbdManualCon's information based on id.
     * @param id the jbdManualCon's id
     * @return jbdManualCon populated jbdManualCon object
     */
    public JbdManualCon getJbdManualCon(final String id);

    /**
     * Saves a jbdManualCon's information
     * @param jbdManualCon the object to be saved
     */
    public void saveJbdManualCon(JbdManualCon jbdManualCon);

    /**
     * Removes a jbdManualCon from the database by id
     * @param id the jbdManualCon's id
     */
    public void removeJbdManualCon(final String id);
    //added for getJbdManualConsByCrm
    public List getJbdManualConsByCrm(CommonRecord crm, Pager pager);
    public void saveJbdManualCons(List<JbdManualCon> jbdManualCons);
}

