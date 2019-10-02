
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.mi.dao.JmiLevelNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiLevelNoteManager extends Manager {
    /**
     * Retrieves all of the jmiLevelNotes
     */
    public List getJmiLevelNotes(JmiLevelNote jmiLevelNote);

    /**
     * Gets jmiLevelNote's information based on id.
     * @param id the jmiLevelNote's id
     * @return jmiLevelNote populated jmiLevelNote object
     */
    public JmiLevelNote getJmiLevelNote(final String id);

    /**
     * Saves a jmiLevelNote's information
     * @param jmiLevelNote the object to be saved
     */
    public void saveJmiLevelNote(JmiLevelNote jmiLevelNote);

    /**
     * Removes a jmiLevelNote from the database by id
     * @param id the jmiLevelNote's id
     */
    public void removeJmiLevelNote(final String id);
    //added for getJmiLevelNotesByCrm
    public List getJmiLevelNotesByCrm(CommonRecord crm, Pager pager);
}

