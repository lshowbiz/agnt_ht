
package com.joymain.jecs.vt.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.vt.model.VtVoteDetail;
import com.joymain.jecs.vt.dao.VtVoteDetailDao;
import com.joymain.jecs.vt.service.VtVoteDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class VtVoteDetailManagerImpl extends BaseManager implements VtVoteDetailManager {
    private VtVoteDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setVtVoteDetailDao(VtVoteDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteDetailManager#getVtVoteDetails(com.joymain.jecs.vt.model.VtVoteDetail)
     */
    public List getVtVoteDetails(final VtVoteDetail vtVoteDetail) {
        return dao.getVtVoteDetails(vtVoteDetail);
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteDetailManager#getVtVoteDetail(String vdId)
     */
    public VtVoteDetail getVtVoteDetail(final String vdId) {
        return dao.getVtVoteDetail(new Long(vdId));
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteDetailManager#saveVtVoteDetail(VtVoteDetail vtVoteDetail)
     */
    public void saveVtVoteDetail(VtVoteDetail vtVoteDetail) {
        dao.saveVtVoteDetail(vtVoteDetail);
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVoteDetailManager#removeVtVoteDetail(String vdId)
     */
    public void removeVtVoteDetail(final String vdId) {
        dao.removeVtVoteDetail(new Long(vdId));
    }
    //added for getVtVoteDetailsByCrm
    public List getVtVoteDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getVtVoteDetailsByCrm(crm,pager);
    }

	public void removeVtVoteDetailByVtId(Long vtId) {
		dao.removeVtVoteDetailByVtId(vtId);
	}
}
