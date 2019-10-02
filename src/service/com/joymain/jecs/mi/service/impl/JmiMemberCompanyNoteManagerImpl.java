
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiMemberCompanyNote;
import com.joymain.jecs.mi.dao.JmiMemberCompanyNoteDao;
import com.joymain.jecs.mi.service.JmiMemberCompanyNoteManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiMemberCompanyNoteManagerImpl extends BaseManager implements JmiMemberCompanyNoteManager {
    private JmiMemberCompanyNoteDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiMemberCompanyNoteDao(JmiMemberCompanyNoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberCompanyNoteManager#getJmiMemberCompanyNotes(com.joymain.jecs.mi.model.JmiMemberCompanyNote)
     */
    public List getJmiMemberCompanyNotes(final JmiMemberCompanyNote jmiMemberCompanyNote) {
        return dao.getJmiMemberCompanyNotes(jmiMemberCompanyNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberCompanyNoteManager#getJmiMemberCompanyNote(String id)
     */
    public JmiMemberCompanyNote getJmiMemberCompanyNote(final String id) {
        return dao.getJmiMemberCompanyNote(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberCompanyNoteManager#saveJmiMemberCompanyNote(JmiMemberCompanyNote jmiMemberCompanyNote)
     */
    public void saveJmiMemberCompanyNote(JmiMemberCompanyNote jmiMemberCompanyNote) {
        dao.saveJmiMemberCompanyNote(jmiMemberCompanyNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiMemberCompanyNoteManager#removeJmiMemberCompanyNote(String id)
     */
    public void removeJmiMemberCompanyNote(final String id) {
        dao.removeJmiMemberCompanyNote(new Long(id));
    }
    //added for getJmiMemberCompanyNotesByCrm
    public List getJmiMemberCompanyNotesByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiMemberCompanyNotesByCrm(crm,pager);
    }
}
