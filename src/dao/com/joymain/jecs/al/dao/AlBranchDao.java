
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.AlBranch;

public interface AlBranchDao extends Dao {

    /**
     * Retrieves all of the alBranchs
     */
    public List getAlBranchs(AlBranch alBranch);

    /**
     * Gets alBranch's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param branchId the alBranch's branchId
     * @return alBranch populated alBranch object
     */
    public AlBranch getAlBranch(final Long branchId);

    /**
     * Saves a alBranch's information
     * @param alBranch the object to be saved
     */    
    public void saveAlBranch(AlBranch alBranch);

    /**
     * Removes a alBranch from the database by branchId
     * @param branchId the alBranch's branchId
     */
    public void removeAlBranch(final Long branchId);
}

