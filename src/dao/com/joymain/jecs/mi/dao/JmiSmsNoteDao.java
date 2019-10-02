
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiSmsNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiSmsNoteDao extends Dao {

    /**
     * Retrieves all of the jmiSmsNotes
     */
    public List getJmiSmsNotes(JmiSmsNote jmiSmsNote);

    /**
     * Gets jmiSmsNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiSmsNote's id
     * @return jmiSmsNote populated jmiSmsNote object
     */
    public JmiSmsNote getJmiSmsNote(final BigDecimal id);

    /**
     * Saves a jmiSmsNote's information
     * @param jmiSmsNote the object to be saved
     */    
    public void saveJmiSmsNote(JmiSmsNote jmiSmsNote);

    /**
     * Removes a jmiSmsNote from the database by id
     * @param id the jmiSmsNote's id
     */
    public void removeJmiSmsNote(final BigDecimal id);
    //added for getJmiSmsNotesByCrm
    public List getJmiSmsNotesByCrm(CommonRecord crm, Pager pager);
    public JmiSmsNote getJmiSmsNoteByUserCode(String userCode);
}

