
package com.joymain.jecs.vt.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.vt.model.VtVoteNote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface VtVoteNoteDao extends Dao {

    /**
     * Retrieves all of the vtVoteNotes
     */
    public List getVtVoteNotes(VtVoteNote vtVoteNote);

    /**
     * Gets vtVoteNote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param vnId the vtVoteNote's vnId
     * @return vtVoteNote populated vtVoteNote object
     */
    public VtVoteNote getVtVoteNote(final Long vnId);

    /**
     * Saves a vtVoteNote's information
     * @param vtVoteNote the object to be saved
     */    
    public void saveVtVoteNote(VtVoteNote vtVoteNote);

    /**
     * Removes a vtVoteNote from the database by vnId
     * @param vnId the vtVoteNote's vnId
     */
    public void removeVtVoteNote(final Long vnId);
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
     * 查出投票的各项票数
     * @param vtId
     * @return
     */
    public List getUserNotesForVtVote(Long vtId);
    /**
     * 查出投票的总数
     * @param vtId
     * @return
     */
    public Long getUserNotesCount(Long vtId);
}

