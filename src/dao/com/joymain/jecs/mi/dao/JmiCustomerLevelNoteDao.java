
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiCustomerLevelNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiCustomerLevelNoteDao extends Dao {

    /**
     * Retrieves all of the jmiCustomerLevelNotes
     */
    public List getJmiCustomerLevelNotes(JmiCustomerLevelNote jmiCustomerLevelNote);

    /**
     * Gets jmiCustomerLevelNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiCustomerLevelNote's id
     * @return jmiCustomerLevelNote populated jmiCustomerLevelNote object
     */
    public JmiCustomerLevelNote getJmiCustomerLevelNote(final Long id);

    /**
     * Saves a jmiCustomerLevelNote's information
     * @param jmiCustomerLevelNote the object to be saved
     */    
    public void saveJmiCustomerLevelNote(JmiCustomerLevelNote jmiCustomerLevelNote);

    /**
     * Removes a jmiCustomerLevelNote from the database by id
     * @param id the jmiCustomerLevelNote's id
     */
    public void removeJmiCustomerLevelNote(final Long id);
    //added for getJmiCustomerLevelNotesByCrm
    public List getJmiCustomerLevelNotesByCrm(CommonRecord crm, Pager pager);
}

