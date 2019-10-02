
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiSmsNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiSmsNoteManager extends Manager {
    /**
     * Retrieves all of the jmiSmsNotes
     */
    public List getJmiSmsNotes(JmiSmsNote jmiSmsNote);

    /**
     * Gets jmiSmsNote's information based on id.
     * @param id the jmiSmsNote's id
     * @return jmiSmsNote populated jmiSmsNote object
     */
    public JmiSmsNote getJmiSmsNote(final String id);

    /**
     * Saves a jmiSmsNote's information
     * @param jmiSmsNote the object to be saved
     */
    public void saveJmiSmsNote(JmiSmsNote jmiSmsNote);

    /**
     * Removes a jmiSmsNote from the database by id
     * @param id the jmiSmsNote's id
     */
    public void removeJmiSmsNote(final String id);
    //added for getJmiSmsNotesByCrm
    public List getJmiSmsNotesByCrm(CommonRecord crm, Pager pager);
    public JmiSmsNote getJmiSmsNoteByUserCode(String userCode);
    public String sendSms(String userCode,String passWord );
}

