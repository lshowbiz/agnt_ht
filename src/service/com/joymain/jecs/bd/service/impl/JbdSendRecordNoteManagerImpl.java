
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdSendRecordNote;
import com.joymain.jecs.bd.dao.JbdSendRecordNoteDao;
import com.joymain.jecs.bd.service.JbdSendRecordNoteManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdSendRecordNoteManagerImpl extends BaseManager implements JbdSendRecordNoteManager {
    private JbdSendRecordNoteDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdSendRecordNoteDao(JbdSendRecordNoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordNoteManager#getJbdSendRecordNotes(com.joymain.jecs.bd.model.JbdSendRecordNote)
     */
    public List getJbdSendRecordNotes(final JbdSendRecordNote jbdSendRecordNote) {
        return dao.getJbdSendRecordNotes(jbdSendRecordNote);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordNoteManager#getJbdSendRecordNote(String id)
     */
    public JbdSendRecordNote getJbdSendRecordNote(final String id) {
        return dao.getJbdSendRecordNote(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordNoteManager#saveJbdSendRecordNote(JbdSendRecordNote jbdSendRecordNote)
     */
    public void saveJbdSendRecordNote(JbdSendRecordNote jbdSendRecordNote) {
        dao.saveJbdSendRecordNote(jbdSendRecordNote);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdSendRecordNoteManager#removeJbdSendRecordNote(String id)
     */
    public void removeJbdSendRecordNote(final String id) {
        dao.removeJbdSendRecordNote(new Long(id));
    }
    //added for getJbdSendRecordNotesByCrm
    public List getJbdSendRecordNotesByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdSendRecordNotesByCrm(crm,pager);
    }

	public List getJbdSendRecordsBySqlByCrm(CommonRecord crm, Pager pager) {
		return dao.getJbdSendRecordsBySqlByCrm(crm, pager);
	}

	public BigDecimal getJbdSendRecordsBySqlByCrmSum(CommonRecord crm) {
		return dao.getJbdSendRecordsBySqlByCrmSum(crm);
	}

	public List getJbdSendRecordHistsByCrm(CommonRecord crm, Pager pager) {
		return dao.getJbdSendRecordHistsByCrm(crm, pager);
	}

	public BigDecimal getSumRemittanceMoney(CommonRecord crm) {
		return dao.getSumRemittanceMoney(crm);
	}

	public List getJbdSendRecordsBySqlByCrmNew(CommonRecord crm, Pager pager) {
		return dao.getJbdSendRecordsBySqlByCrmNew(crm, pager);
	}

	public JbdSendRecordNote getJbdSendRecordNoteForUpdate(String id) {
		return dao.getJbdSendRecordNoteForUpdate(new Long(id));
	}
}
