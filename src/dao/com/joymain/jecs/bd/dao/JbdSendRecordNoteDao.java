
package com.joymain.jecs.bd.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.bd.model.JbdSendRecordNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JbdSendRecordNoteDao extends Dao {

    /**
     * Retrieves all of the jbdSendRecordNotes
     */
    public List getJbdSendRecordNotes(JbdSendRecordNote jbdSendRecordNote);

    /**
     * Gets jbdSendRecordNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jbdSendRecordNote's id
     * @return jbdSendRecordNote populated jbdSendRecordNote object
     */
    public JbdSendRecordNote getJbdSendRecordNote(final Long id);

    /**
     * Saves a jbdSendRecordNote's information
     * @param jbdSendRecordNote the object to be saved
     */    
    public void saveJbdSendRecordNote(JbdSendRecordNote jbdSendRecordNote);

    /**
     * Removes a jbdSendRecordNote from the database by id
     * @param id the jbdSendRecordNote's id
     */
    public void removeJbdSendRecordNote(final Long id);
    //added for getJbdSendRecordNotesByCrm
    public List getJbdSendRecordNotesByCrm(CommonRecord crm, Pager pager);
    public List getJbdSendRecordsBySqlByCrm(CommonRecord crm, Pager pager);
	public BigDecimal getJbdSendRecordsBySqlByCrmSum(CommonRecord crm);
	
	public BigDecimal getSumRemittanceMoney(CommonRecord crm);
    public List getJbdSendRecordHistsByCrm(CommonRecord crm, Pager pager);
    
	public List getJbdSendRecordsBySqlByCrmNew(CommonRecord crm, Pager pager); 

    public JbdSendRecordNote getJbdSendRecordNoteForUpdate(final Long id);
}

