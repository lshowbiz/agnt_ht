
package com.joymain.jecs.vt.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.vt.model.VtVotePow;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface VtVotePowDao extends Dao {

    /**
     * Retrieves all of the vtVotePows
     */
    public List getVtVotePows(VtVotePow vtVotePow);

    /**
     * Gets vtVotePow's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param vpId the vtVotePow's vpId
     * @return vtVotePow populated vtVotePow object
     */
    public VtVotePow getVtVotePow(final Long vpId);

    /**
     * Saves a vtVotePow's information
     * @param vtVotePow the object to be saved
     */    
    public void saveVtVotePow(VtVotePow vtVotePow);

    /**
     * Removes a vtVotePow from the database by vpId
     * @param vpId the vtVotePow's vpId
     */
    public void removeVtVotePow(final Long vpId);
    //added for getVtVotePowsByCrm
    public List getVtVotePowsByCrm(CommonRecord crm, Pager pager);
    public void removeVtVotePowsByVtId(Long vtId);
}

