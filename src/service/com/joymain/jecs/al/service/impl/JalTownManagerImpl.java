
package com.joymain.jecs.al.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.JalTown;
import com.joymain.jecs.al.dao.JalTownDao;
import com.joymain.jecs.al.service.JalTownManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JalTownManagerImpl extends BaseManager implements JalTownManager {
    private JalTownDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJalTownDao(JalTownDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.JalTownManager#getJalTowns(com.joymain.jecs.al.model.JalTown)
     */
    public List getJalTowns(final JalTown jalTown) {
        return dao.getJalTowns(jalTown);
    }

    /**
     * @see com.joymain.jecs.al.service.JalTownManager#getJalTown(String townId)
     */
    public JalTown getJalTown(final String townId) {
        return dao.getJalTown(new Long(townId));
    }

    /**
     * @see com.joymain.jecs.al.service.JalTownManager#saveJalTown(JalTown jalTown)
     */
    public void saveJalTown(JalTown jalTown) {
        dao.saveJalTown(jalTown);
    }

    /**
     * @see com.joymain.jecs.al.service.JalTownManager#removeJalTown(String townId)
     */
    public void removeJalTown(final String townId) {
        dao.removeJalTown(new Long(townId));
    }
    //added for getJalTownsByCrm
    public List getJalTownsByCrm(CommonRecord crm, Pager pager){
	return dao.getJalTownsByCrm(crm,pager);
    }

	public List getJalTownByDistrictId(Long districtId) {
		return dao.getJalTownByDistrictId(districtId);
	}
}
