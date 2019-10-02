
package com.joymain.jecs.bd.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.bd.model.JbdGradeNote;
import com.joymain.jecs.bd.dao.JbdGradeNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JbdGradeNoteManager extends Manager {
    /**
     * Retrieves all of the jbdGradeNotes
     */
    public List getJbdGradeNotes(JbdGradeNote jbdGradeNote);

    /**
     * Gets jbdGradeNote's information based on id.
     * @param id the jbdGradeNote's id
     * @return jbdGradeNote populated jbdGradeNote object
     */
    public JbdGradeNote getJbdGradeNote(final String id);

    /**
     * Saves a jbdGradeNote's information
     * @param jbdGradeNote the object to be saved
     */
    public void saveJbdGradeNote(JbdGradeNote jbdGradeNote);

    /**
     * Removes a jbdGradeNote from the database by id
     * @param id the jbdGradeNote's id
     */
    public void removeJbdGradeNote(final String id);
    //added for getJbdGradeNotesByCrm
    public List getJbdGradeNotesByCrm(CommonRecord crm, Pager pager);
}

