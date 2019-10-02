
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiLevelNote;
import com.joymain.jecs.mi.dao.JmiLevelNoteDao;
import com.joymain.jecs.mi.service.JmiLevelNoteManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiLevelNoteManagerImpl extends BaseManager implements JmiLevelNoteManager {
    private JmiLevelNoteDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiLevelNoteDao(JmiLevelNoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLevelNoteManager#getJmiLevelNotes(com.joymain.jecs.mi.model.JmiLevelNote)
     */
    public List getJmiLevelNotes(final JmiLevelNote jmiLevelNote) {
        return dao.getJmiLevelNotes(jmiLevelNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLevelNoteManager#getJmiLevelNote(String id)
     */
    public JmiLevelNote getJmiLevelNote(final String id) {
        return dao.getJmiLevelNote(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLevelNoteManager#saveJmiLevelNote(JmiLevelNote jmiLevelNote)
     */
    public void saveJmiLevelNote(JmiLevelNote jmiLevelNote) {
        dao.saveJmiLevelNote(jmiLevelNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiLevelNoteManager#removeJmiLevelNote(String id)
     */
    public void removeJmiLevelNote(final String id) {
        dao.removeJmiLevelNote(new Long(id));
    }
    //added for getJmiLevelNotesByCrm
    public List getJmiLevelNotesByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiLevelNotesByCrm(crm,pager);
    }
}
