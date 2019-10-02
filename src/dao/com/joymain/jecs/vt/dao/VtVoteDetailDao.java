
package com.joymain.jecs.vt.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.vt.model.VtVoteDetail;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface VtVoteDetailDao extends Dao {

    /**
     * Retrieves all of the vtVoteDetails
     */
    public List getVtVoteDetails(VtVoteDetail vtVoteDetail);

    /**
     * Gets vtVoteDetail's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param vdId the vtVoteDetail's vdId
     * @return vtVoteDetail populated vtVoteDetail object
     */
    public VtVoteDetail getVtVoteDetail(final Long vdId);

    /**
     * Saves a vtVoteDetail's information
     * @param vtVoteDetail the object to be saved
     */    
    public void saveVtVoteDetail(VtVoteDetail vtVoteDetail);

    /**
     * Removes a vtVoteDetail from the database by vdId
     * @param vdId the vtVoteDetail's vdId
     */
    public void removeVtVoteDetail(final Long vdId);
    //added for getVtVoteDetailsByCrm
    public List getVtVoteDetailsByCrm(CommonRecord crm, Pager pager);

	public void removeVtVoteDetailByVtId(Long vtId);
}

