
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.AlBranch;
import com.joymain.jecs.al.dao.AlBranchDao;

public interface AlBranchManager extends Manager {
    /**
     * Retrieves all of the alBranchs
     */
    public List getAlBranchs(AlBranch alBranch);

    /**
     * Gets alBranch's information based on branchId.
     * @param branchId the alBranch's branchId
     * @return alBranch populated alBranch object
     */
    public AlBranch getAlBranch(final String branchId);

    /**
     * Saves a alBranch's information
     * @param alBranch the object to be saved
     */
    public void saveAlBranch(AlBranch alBranch);

    /**
     * Removes a alBranch from the database by branchId
     * @param branchId the alBranch's branchId
     */
    public void removeAlBranch(final String branchId);
}

