
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiMemberCompanyNote;
import com.joymain.jecs.mi.dao.JmiMemberCompanyNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiMemberCompanyNoteManager extends Manager {
    /**
     * Retrieves all of the jmiMemberCompanyNotes
     */
    public List getJmiMemberCompanyNotes(JmiMemberCompanyNote jmiMemberCompanyNote);

    /**
     * Gets jmiMemberCompanyNote's information based on id.
     * @param id the jmiMemberCompanyNote's id
     * @return jmiMemberCompanyNote populated jmiMemberCompanyNote object
     */
    public JmiMemberCompanyNote getJmiMemberCompanyNote(final String id);

    /**
     * Saves a jmiMemberCompanyNote's information
     * @param jmiMemberCompanyNote the object to be saved
     */
    public void saveJmiMemberCompanyNote(JmiMemberCompanyNote jmiMemberCompanyNote);

    /**
     * Removes a jmiMemberCompanyNote from the database by id
     * @param id the jmiMemberCompanyNote's id
     */
    public void removeJmiMemberCompanyNote(final String id);
    //added for getJmiMemberCompanyNotesByCrm
    public List getJmiMemberCompanyNotesByCrm(CommonRecord crm, Pager pager);
}

