
package com.joymain.jecs.vt.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.vt.model.VtVote;
import com.joymain.jecs.vt.dao.VtVoteDao;
import com.joymain.jecs.vt.service.VtVoteManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class VtVoteManagerImpl extends BaseManager implements VtVoteManager {
    private VtVoteDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setVtVoteDao(VtVoteDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteManager#getVtVotes(com.joymain.jecs.vt.model.VtVote)
     */
    public List getVtVotes(final VtVote vtVote) {
        return dao.getVtVotes(vtVote);
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteManager#getVtVote(String vtId)
     */
    public VtVote getVtVote(final String vtId) {
        return dao.getVtVote(new Long(vtId));
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteManager#saveVtVote(VtVote vtVote)
     */
    public void saveVtVote(VtVote vtVote) {
        dao.saveVtVote(vtVote);
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteManager#removeVtVote(String vtId)
     */
    public void removeVtVote(final String vtId) {
        dao.removeVtVote(new Long(vtId));
    }
    //added for getVtVotesByCrm
    public List getVtVotesByCrm(CommonRecord crm, Pager pager){
	return dao.getVtVotesByCrm(crm,pager);
    }
}
