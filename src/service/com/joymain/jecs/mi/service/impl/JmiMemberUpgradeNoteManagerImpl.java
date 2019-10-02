
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiMemberUpgradeNote;
import com.joymain.jecs.mi.dao.JmiMemberUpgradeNoteDao;
import com.joymain.jecs.mi.service.JmiMemberUpgradeNoteManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiMemberUpgradeNoteManagerImpl extends BaseManager implements JmiMemberUpgradeNoteManager {
    private JmiMemberUpgradeNoteDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiMemberUpgradeNoteDao(JmiMemberUpgradeNoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberUpgradeNoteManager#getJmiMemberUpgradeNotes(com.joymain.jecs.mi.model.JmiMemberUpgradeNote)
     */
    public List getJmiMemberUpgradeNotes(final JmiMemberUpgradeNote jmiMemberUpgradeNote) {
        return dao.getJmiMemberUpgradeNotes(jmiMemberUpgradeNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberUpgradeNoteManager#getJmiMemberUpgradeNote(String munId)
     */
    public JmiMemberUpgradeNote getJmiMemberUpgradeNote(final String munId) {
        return dao.getJmiMemberUpgradeNote(new Long(munId));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberUpgradeNoteManager#saveJmiMemberUpgradeNote(JmiMemberUpgradeNote jmiMemberUpgradeNote)
     */
    public void saveJmiMemberUpgradeNote(JmiMemberUpgradeNote jmiMemberUpgradeNote) {
        dao.saveJmiMemberUpgradeNote(jmiMemberUpgradeNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberUpgradeNoteManager#removeJmiMemberUpgradeNote(String munId)
     */
    public void removeJmiMemberUpgradeNote(final String munId) {
        dao.removeJmiMemberUpgradeNote(new Long(munId));
    }
    //added for getJmiMemberUpgradeNotesByCrm
    public List getJmiMemberUpgradeNotesByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiMemberUpgradeNotesByCrm(crm,pager);
    }
}
