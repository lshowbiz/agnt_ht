
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.mi.model.JmiCustomerLevelNote;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.dao.JmiCustomerLevelNoteDao;
import com.joymain.jecs.mi.service.JmiCustomerLevelNoteManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiCustomerLevelNoteManagerImpl extends BaseManager implements JmiCustomerLevelNoteManager {
    private JmiCustomerLevelNoteDao dao;
	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiCustomerLevelNoteDao(JmiCustomerLevelNoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiCustomerLevelNoteManager#getJmiCustomerLevelNotes(com.joymain.jecs.mi.model.JmiCustomerLevelNote)
     */
    public List getJmiCustomerLevelNotes(final JmiCustomerLevelNote jmiCustomerLevelNote) {
        return dao.getJmiCustomerLevelNotes(jmiCustomerLevelNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiCustomerLevelNoteManager#getJmiCustomerLevelNote(String id)
     */
    public JmiCustomerLevelNote getJmiCustomerLevelNote(final String id) {
        return dao.getJmiCustomerLevelNote(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiCustomerLevelNoteManager#saveJmiCustomerLevelNote(JmiCustomerLevelNote jmiCustomerLevelNote)
     */
    public void saveJmiCustomerLevelNote(JmiCustomerLevelNote jmiCustomerLevelNote) {
        dao.saveJmiCustomerLevelNote(jmiCustomerLevelNote);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiCustomerLevelNoteManager#removeJmiCustomerLevelNote(String id)
     */
    public void removeJmiCustomerLevelNote(final String id) {
        dao.removeJmiCustomerLevelNote(new Long(id));
    }
    //added for getJmiCustomerLevelNotesByCrm
    public List getJmiCustomerLevelNotesByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiCustomerLevelNotesByCrm(crm,pager);
    }
}
