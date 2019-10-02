
package com.joymain.jecs.am.service.impl;

import java.util.List;

import com.joymain.jecs.am.dao.AmPermitDao;
import com.joymain.jecs.am.model.AmPermit;
import com.joymain.jecs.am.service.AmPermitManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class AmPermitManagerImpl extends BaseManager implements AmPermitManager {
    private AmPermitDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAmPermitDao(AmPermitDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.AmPermitManager#getAmPermits(com.joymain.jecs.am.model.AmPermit)
     */
    public List getAmPermits(final AmPermit amPermit) {
        return dao.getAmPermits(amPermit);
    }

    /**
     * @see com.joymain.jecs.am.service.AmPermitManager#getAmPermit(String permitNo)
     */
    public AmPermit getAmPermit(final String permitNo) {
        return dao.getAmPermit(new String(permitNo));
    }

    /**
     * @see com.joymain.jecs.am.service.AmPermitManager#saveAmPermit(AmPermit amPermit)
     */
    public void saveAmPermit(AmPermit amPermit) {
        dao.saveAmPermit(amPermit);
    }

    /**
     * @see com.joymain.jecs.am.service.AmPermitManager#removeAmPermit(String permitNo)
     */
    public void removeAmPermit(final String permitNo) {
        dao.removeAmPermit(new String(permitNo));
    }
    //added for getAmPermitsByCrm
		public List getAmPermitsByCrm(CommonRecord crm, Pager pager){
				return dao.getAmPermitsByCrm(crm,pager);
		}
}
