
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.AmMessagePermit;
import com.joymain.jecs.am.dao.AmMessagePermitDao;
import com.joymain.jecs.am.service.AmMessagePermitManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class AmMessagePermitManagerImpl extends BaseManager implements AmMessagePermitManager {
    private AmMessagePermitDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAmMessagePermitDao(AmMessagePermitDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.AmMessagePermitManager#getAmMessagePermits(com.joymain.jecs.am.model.AmMessagePermit)
     */
    public List getAmMessagePermits(final AmMessagePermit amMessagePermit) {
        return dao.getAmMessagePermits(amMessagePermit);
    }

    /**
     * @see com.joymain.jecs.am.service.AmMessagePermitManager#getAmMessagePermit(String ampNo)
     */
    public AmMessagePermit getAmMessagePermit(final String ampNo) {
        return dao.getAmMessagePermit(new Long(ampNo));
    }

    /**
     * @see com.joymain.jecs.am.service.AmMessagePermitManager#saveAmMessagePermit(AmMessagePermit amMessagePermit)
     */
    public void saveAmMessagePermit(AmMessagePermit amMessagePermit) {
        dao.saveAmMessagePermit(amMessagePermit);
    }

    /**
     * @see com.joymain.jecs.am.service.AmMessagePermitManager#removeAmMessagePermit(String ampNo)
     */
    public void removeAmMessagePermit(final String ampNo) {
        dao.removeAmMessagePermit(new Long(ampNo));
    }
    //added for getAmMessagePermitsByCrm
    public List getAmMessagePermitsByCrm(CommonRecord crm, Pager pager){
	return dao.getAmMessagePermitsByCrm(crm,pager);
    }

	public List getAmMessagePermitsByUserCode(String userCode) {
		return dao.getAmMessagePermitsByUserCode(userCode);
	}
}
