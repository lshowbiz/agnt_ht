
package com.joymain.jecs.vt.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.vt.model.VtVoteNote;
import com.joymain.jecs.vt.dao.VtVoteNoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface VtVoteNoteManager extends Manager {
    /**
     * Retrieves all of the vtVoteNotes
     */
    public List getVtVoteNotes(VtVoteNote vtVoteNote);

    /**
     * Gets vtVoteNote's information based on vnId.
     * @param vnId the vtVoteNote's vnId
     * @return vtVoteNote populated vtVoteNote object
     */
    public VtVoteNote getVtVoteNote(final String vnId);

    /**
     * Saves a vtVoteNote's information
     * @param vtVoteNote the object to be saved
     */
    public void saveVtVoteNote(VtVoteNote vtVoteNote);

    /**
     * Removes a vtVoteNote from the database by vnId
     * @param vnId the vtVoteNote's vnId
     */
    public void removeVtVoteNote(final String vnId);
    //added for getVtVoteNotesByCrm
    public List getVtVoteNotesByCrm(CommonRecord crm, Pager pager);
    /**
     * 查出会员投票数
     * @param userCode
     * @param vtId
     * @return
     */
    public Long getUserNotes(String userCode,Long vtId);
    /**
     * 统计出投票数与各项比率
     * @param vtId
     * @return
     */
    public List getUserNotesForVtVote(Long vtId);
    public Long getUserNotesCount(Long vtId);
}

