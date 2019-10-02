
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdSendNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdSendNoteDao extends Dao {

    /**
     * Retrieves all of the jbdSendNotes
     */
    public List getJbdSendNotes(JbdSendNote jbdSendNote);

    /**
     * Gets jbdSendNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdSendNote's id
     * @return jbdSendNote populated jbdSendNote object
     */
    public JbdSendNote getJbdSendNote(final Long id);

    /**
     * Saves a jbdSendNote's information
     * @param jbdSendNote the object to be saved
     */    
    public void saveJbdSendNote(JbdSendNote jbdSendNote);

    /**
     * Removes a jbdSendNote from the database by id
     * @param id the jbdSendNote's id
     */
    public void removeJbdSendNote(final Long id);
    //added for getJbdSendNotesByCrm
    public List getJbdSendNotesByCrm(CommonRecord crm, Pager pager);
	public BigDecimal getSumRemittanceMoney(CommonRecord crm) ;
}

