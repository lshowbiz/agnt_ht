
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiEcmallNote;
import com.joymain.jecs.mi.dao.JmiEcmallNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiEcmallNoteManager extends Manager {
    /**
     * Retrieves all of the jmiEcmallNotes
     */
    public List getJmiEcmallNotes(JmiEcmallNote jmiEcmallNote);

    /**
     * Gets jmiEcmallNote's information based on id.
     * @param id the jmiEcmallNote's id
     * @return jmiEcmallNote populated jmiEcmallNote object
     */
    public JmiEcmallNote getJmiEcmallNote(final String id);

    /**
     * Saves a jmiEcmallNote's information
     * @param jmiEcmallNote the object to be saved
     */
    public void saveJmiEcmallNote(JmiEcmallNote jmiEcmallNote);

    /**
     * Removes a jmiEcmallNote from the database by id
     * @param id the jmiEcmallNote's id
     */
    public void removeJmiEcmallNote(final String id);
    //added for getJmiEcmallNotesByCrm
    public List getJmiEcmallNotesByCrm(CommonRecord crm, Pager pager);
}

