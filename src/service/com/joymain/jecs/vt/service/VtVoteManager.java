
package com.joymain.jecs.vt.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.vt.model.VtVote;
import com.joymain.jecs.vt.dao.VtVoteDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface VtVoteManager extends Manager {
    /**
     * Retrieves all of the vtVotes
     */
    public List getVtVotes(VtVote vtVote);

    /**
     * Gets vtVote's information based on vtId.
     * @param vtId the vtVote's vtId
     * @return vtVote populated vtVote object
     */
    public VtVote getVtVote(final String vtId);

    /**
     * Saves a vtVote's information
     * @param vtVote the object to be saved
     */
    public void saveVtVote(VtVote vtVote);

    /**
     * Removes a vtVote from the database by vtId
     * @param vtId the vtVote's vtId
     */
    public void removeVtVote(final String vtId);
    //added for getVtVotesByCrm
    public List getVtVotesByCrm(CommonRecord crm, Pager pager);
}

