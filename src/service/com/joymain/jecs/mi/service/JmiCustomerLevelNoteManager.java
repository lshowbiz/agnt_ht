
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.mi.model.JmiCustomerLevelNote;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.dao.JmiCustomerLevelNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiCustomerLevelNoteManager extends Manager {
    /**
     * Retrieves all of the jmiCustomerLevelNotes
     */
    public List getJmiCustomerLevelNotes(JmiCustomerLevelNote jmiCustomerLevelNote);

    /**
     * Gets jmiCustomerLevelNote's information based on id.
     * @param id the jmiCustomerLevelNote's id
     * @return jmiCustomerLevelNote populated jmiCustomerLevelNote object
     */
    public JmiCustomerLevelNote getJmiCustomerLevelNote(final String id);

    /**
     * Saves a jmiCustomerLevelNote's information
     * @param jmiCustomerLevelNote the object to be saved
     */
    public void saveJmiCustomerLevelNote(JmiCustomerLevelNote jmiCustomerLevelNote);

    /**
     * Removes a jmiCustomerLevelNote from the database by id
     * @param id the jmiCustomerLevelNote's id
     */
    public void removeJmiCustomerLevelNote(final String id);
    //added for getJmiCustomerLevelNotesByCrm
    public List getJmiCustomerLevelNotesByCrm(CommonRecord crm, Pager pager);
   
}

