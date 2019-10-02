
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdGradeNote;
import com.joymain.jecs.bd.dao.JbdGradeNoteDao;
import com.joymain.jecs.bd.service.JbdGradeNoteManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdGradeNoteManagerImpl extends BaseManager implements JbdGradeNoteManager {
    private JbdGradeNoteDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdGradeNoteDao(JbdGradeNoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdGradeNoteManager#getJbdGradeNotes(com.joymain.jecs.bd.model.JbdGradeNote)
     */
    public List getJbdGradeNotes(final JbdGradeNote jbdGradeNote) {
        return dao.getJbdGradeNotes(jbdGradeNote);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdGradeNoteManager#getJbdGradeNote(String id)
     */
    public JbdGradeNote getJbdGradeNote(final String id) {
        return dao.getJbdGradeNote(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdGradeNoteManager#saveJbdGradeNote(JbdGradeNote jbdGradeNote)
     */
    public void saveJbdGradeNote(JbdGradeNote jbdGradeNote) {
        dao.saveJbdGradeNote(jbdGradeNote);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdGradeNoteManager#removeJbdGradeNote(String id)
     */
    public void removeJbdGradeNote(final String id) {
        dao.removeJbdGradeNote(new Long(id));
    }
    //added for getJbdGradeNotesByCrm
    public List getJbdGradeNotesByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdGradeNotesByCrm(crm,pager);
    }
}
