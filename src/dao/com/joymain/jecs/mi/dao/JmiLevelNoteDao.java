
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiLevelNoteDao extends Dao {

    /**
     * Retrieves all of the jmiLevelNotes
     */
    public List getJmiLevelNotes(JmiLevelNote jmiLevelNote);

    /**
     * Gets jmiLevelNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiLevelNote's id
     * @return jmiLevelNote populated jmiLevelNote object
     */
    public JmiLevelNote getJmiLevelNote(final Long id);

    /**
     * Saves a jmiLevelNote's information
     * @param jmiLevelNote the object to be saved
     */    
    public void saveJmiLevelNote(JmiLevelNote jmiLevelNote);

    /**
     * Removes a jmiLevelNote from the database by id
     * @param id the jmiLevelNote's id
     */
    public void removeJmiLevelNote(final Long id);
    //added for getJmiLevelNotesByCrm
    public List getJmiLevelNotesByCrm(CommonRecord crm, Pager pager);
}

