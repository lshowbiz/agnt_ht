
package com.joymain.jecs.vt.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.vt.model.VtVotePow;
import com.joymain.jecs.vt.dao.VtVotePowDao;
import com.joymain.jecs.vt.service.VtVotePowManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class VtVotePowManagerImpl extends BaseManager implements VtVotePowManager {
    private VtVotePowDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setVtVotePowDao(VtVotePowDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVotePowManager#getVtVotePows(com.joymain.jecs.vt.model.VtVotePow)
     */
    public List getVtVotePows(final VtVotePow vtVotePow) {
        return dao.getVtVotePows(vtVotePow);
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVotePowManager#getVtVotePow(String vpId)
     */
    public VtVotePow getVtVotePow(final String vpId) {
        return dao.getVtVotePow(new Long(vpId));
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVotePowManager#saveVtVotePow(VtVotePow vtVotePow)
     */
    public void saveVtVotePow(VtVotePow vtVotePow) {
        dao.saveVtVotePow(vtVotePow);
    }

    /**
     * @see com.joymain.jecs.vt.service.VtVotePowManager#removeVtVotePow(String vpId)
     */
    public void removeVtVotePow(final String vpId) {
        dao.removeVtVotePow(new Long(vpId));
    }
    //added for getVtVotePowsByCrm
    public List getVtVotePowsByCrm(CommonRecord crm, Pager pager){
	return dao.getVtVotePowsByCrm(crm,pager);
    }

	public void removeVtVotePowsByVtId(Long vtId) {
		dao.removeVtVotePowsByVtId(vtId);
	}
}
