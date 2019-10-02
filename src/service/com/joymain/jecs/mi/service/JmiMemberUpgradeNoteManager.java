
package com.joymain.jecs.mi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.mi.model.JmiMemberUpgradeNote;
import com.joymain.jecs.mi.dao.JmiMemberUpgradeNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JmiMemberUpgradeNoteManager extends Manager {
    /**
     * Retrieves all of the jmiMemberUpgradeNotes
     */
    public List getJmiMemberUpgradeNotes(JmiMemberUpgradeNote jmiMemberUpgradeNote);

    /**
     * Gets jmiMemberUpgradeNote's information based on munId.
     * @param munId the jmiMemberUpgradeNote's munId
     * @return jmiMemberUpgradeNote populated jmiMemberUpgradeNote object
     */
    public JmiMemberUpgradeNote getJmiMemberUpgradeNote(final String munId);

    /**
     * Saves a jmiMemberUpgradeNote's information
     * @param jmiMemberUpgradeNote the object to be saved
     */
    public void saveJmiMemberUpgradeNote(JmiMemberUpgradeNote jmiMemberUpgradeNote);

    /**
     * Removes a jmiMemberUpgradeNote from the database by munId
     * @param munId the jmiMemberUpgradeNote's munId
     */
    public void removeJmiMemberUpgradeNote(final String munId);
    //added for getJmiMemberUpgradeNotesByCrm
    public List getJmiMemberUpgradeNotesByCrm(CommonRecord crm, Pager pager);
}

