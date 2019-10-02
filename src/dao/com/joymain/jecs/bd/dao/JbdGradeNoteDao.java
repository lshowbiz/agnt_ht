
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdGradeNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdGradeNoteDao extends Dao {

    /**
     * Retrieves all of the jbdGradeNotes
     */
    public List getJbdGradeNotes(JbdGradeNote jbdGradeNote);

    /**
     * Gets jbdGradeNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdGradeNote's id
     * @return jbdGradeNote populated jbdGradeNote object
     */
    public JbdGradeNote getJbdGradeNote(final Long id);

    /**
     * Saves a jbdGradeNote's information
     * @param jbdGradeNote the object to be saved
     */    
    public void saveJbdGradeNote(JbdGradeNote jbdGradeNote);

    /**
     * Removes a jbdGradeNote from the database by id
     * @param id the jbdGradeNote's id
     */
    public void removeJbdGradeNote(final Long id);
    //added for getJbdGradeNotesByCrm
    public List getJbdGradeNotesByCrm(CommonRecord crm, Pager pager);
}

