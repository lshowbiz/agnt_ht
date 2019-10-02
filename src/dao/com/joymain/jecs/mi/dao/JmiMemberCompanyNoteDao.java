
package com.joymain.jecs.mi.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiMemberCompanyNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiMemberCompanyNoteDao extends Dao {

    /**
     * Retrieves all of the jmiMemberCompanyNotes
     */
    public List getJmiMemberCompanyNotes(JmiMemberCompanyNote jmiMemberCompanyNote);

    /**
     * Gets jmiMemberCompanyNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jmiMemberCompanyNote's id
     * @return jmiMemberCompanyNote populated jmiMemberCompanyNote object
     */
    public JmiMemberCompanyNote getJmiMemberCompanyNote(final Long id);

    /**
     * Saves a jmiMemberCompanyNote's information
     * @param jmiMemberCompanyNote the object to be saved
     */    
    public void saveJmiMemberCompanyNote(JmiMemberCompanyNote jmiMemberCompanyNote);

    /**
     * Removes a jmiMemberCompanyNote from the database by id
     * @param id the jmiMemberCompanyNote's id
     */
    public void removeJmiMemberCompanyNote(final Long id);
    //added for getJmiMemberCompanyNotesByCrm
    public List getJmiMemberCompanyNotesByCrm(CommonRecord crm, Pager pager);
}

