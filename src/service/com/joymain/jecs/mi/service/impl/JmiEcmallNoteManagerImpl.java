
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiEcmallNote;
import com.joymain.jecs.mi.dao.JmiEcmallNoteDao;
import com.joymain.jecs.mi.service.JmiEcmallNoteManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiEcmallNoteManagerImpl extends BaseManager implements JmiEcmallNoteManager {
    private JmiEcmallNoteDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiEcmallNoteDao(JmiEcmallNoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiEcmallNoteManager#getJmiEcmallNotes(com.joymain.jecs.mi.model.JmiEcmallNote)
     */
    public List getJmiEcmallNotes(final JmiEcmallNote jmiEcmallNote) {
        return dao.getJmiEcmallNotes(jmiEcmallNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiEcmallNoteManager#getJmiEcmallNote(String id)
     */
    public JmiEcmallNote getJmiEcmallNote(final String id) {
        return dao.getJmiEcmallNote(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiEcmallNoteManager#saveJmiEcmallNote(JmiEcmallNote jmiEcmallNote)
     */
    public void saveJmiEcmallNote(JmiEcmallNote jmiEcmallNote) {
        dao.saveJmiEcmallNote(jmiEcmallNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiEcmallNoteManager#removeJmiEcmallNote(String id)
     */
    public void removeJmiEcmallNote(final String id) {
        dao.removeJmiEcmallNote(new BigDecimal(id));
    }
    //added for getJmiEcmallNotesByCrm
    public List getJmiEcmallNotesByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiEcmallNotesByCrm(crm,pager);
    }
}
