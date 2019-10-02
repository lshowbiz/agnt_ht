
package com.joymain.jecs.vt.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.vt.dao.VtVoteNoteDao;
import com.joymain.jecs.vt.model.VtVoteNote;
import com.joymain.jecs.vt.service.VtVoteNoteManager;
public class VtVoteNoteManagerImpl extends BaseManager implements VtVoteNoteManager {
    private VtVoteNoteDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setVtVoteNoteDao(VtVoteNoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteNoteManager#getVtVoteNotes(com.joymain.jecs.vt.model.VtVoteNote)
     */
    public List getVtVoteNotes(final VtVoteNote vtVoteNote) {
        return dao.getVtVoteNotes(vtVoteNote);
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteNoteManager#getVtVoteNote(String vnId)
     */
    public VtVoteNote getVtVoteNote(final String vnId) {
        return dao.getVtVoteNote(new Long(vnId));
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteNoteManager#saveVtVoteNote(VtVoteNote vtVoteNote)
     */
    public void saveVtVoteNote(VtVoteNote vtVoteNote) {
        dao.saveVtVoteNote(vtVoteNote);
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteNoteManager#removeVtVoteNote(String vnId)
     */
    public void removeVtVoteNote(final String vnId) {
        dao.removeVtVoteNote(new Long(vnId));
    }
    //added for getVtVoteNotesByCrm
    public List getVtVoteNotesByCrm(CommonRecord crm, Pager pager){
	return dao.getVtVoteNotesByCrm(crm,pager);
    }

	public Long getUserNotes(String userCode, Long vtId) {
		return dao.getUserNotes(userCode, vtId);
	}

	public List getUserNotesForVtVote(Long vtId) {
		List list=dao.getUserNotesForVtVote(vtId);
		Double count=new Double(dao.getUserNotesCount(vtId));
		for (Object oo : list) {
			Map map=(Map) oo;
			map.put("detail_rate", new BigDecimal((new Double((String)map.get("note_count"))/count)*100).setScale(0, BigDecimal.ROUND_HALF_UP));
		}
		return list;
	}

	public Long getUserNotesCount(Long vtId) {
		return dao.getUserNotesCount(vtId);
	}
}
