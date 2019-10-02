
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiEcmallNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiEcmallNoteDao extends Dao {

    /**
     * Retrieves all of the jmiEcmallNotes
     */
    public List getJmiEcmallNotes(JmiEcmallNote jmiEcmallNote);

    /**
     * Gets jmiEcmallNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiEcmallNote's id
     * @return jmiEcmallNote populated jmiEcmallNote object
     */
    public JmiEcmallNote getJmiEcmallNote(final BigDecimal id);

    /**
     * Saves a jmiEcmallNote's information
     * @param jmiEcmallNote the object to be saved
     */    
    public void saveJmiEcmallNote(JmiEcmallNote jmiEcmallNote);

    /**
     * Removes a jmiEcmallNote from the database by id
     * @param id the jmiEcmallNote's id
     */
    public void removeJmiEcmallNote(final BigDecimal id);
    //added for getJmiEcmallNotesByCrm
    public List getJmiEcmallNotesByCrm(CommonRecord crm, Pager pager);
}

