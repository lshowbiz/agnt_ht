
package com.joymain.jecs.vt.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.vt.model.VtVote;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface VtVoteDao extends Dao {

    /**
     * Retrieves all of the vtVotes
     */
    public List getVtVotes(VtVote vtVote);

    /**
     * Gets vtVote's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param vtId the vtVote's vtId
     * @return vtVote populated vtVote object
     */
    public VtVote getVtVote(final Long vtId);

    /**
     * Saves a vtVote's information
     * @param vtVote the object to be saved
     */    
    public void saveVtVote(VtVote vtVote);

    /**
     * Removes a vtVote from the database by vtId
     * @param vtId the vtVote's vtId
     */
    public void removeVtVote(final Long vtId);
    //added for getVtVotesByCrm
    public List getVtVotesByCrm(CommonRecord crm, Pager pager);
}

