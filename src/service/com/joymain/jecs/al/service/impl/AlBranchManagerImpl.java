
package com.joymain.jecs.al.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.AlBranch;
import com.joymain.jecs.al.dao.AlBranchDao;
import com.joymain.jecs.al.service.AlBranchManager;

public class AlBranchManagerImpl extends BaseManager implements AlBranchManager {
    private AlBranchDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAlBranchDao(AlBranchDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.AlBranchManager#getAlBranchs(com.joymain.jecs.al.model.AlBranch)
     */
    public List getAlBranchs(final AlBranch alBranch) {
        return dao.getAlBranchs(alBranch);
    }

    /**
     * @see com.joymain.jecs.al.service.AlBranchManager#getAlBranch(String branchId)
     */
    public AlBranch getAlBranch(final String branchId) {
        return dao.getAlBranch(new Long(branchId));
    }

    /**
     * @see com.joymain.jecs.al.service.AlBranchManager#saveAlBranch(AlBranch alBranch)
     */
    public void saveAlBranch(AlBranch alBranch) {
        dao.saveAlBranch(alBranch);
    }

    /**
     * @see com.joymain.jecs.al.service.AlBranchManager#removeAlBranch(String branchId)
     */
    public void removeAlBranch(final String branchId) {
        dao.removeAlBranch(new Long(branchId));
    }
}
