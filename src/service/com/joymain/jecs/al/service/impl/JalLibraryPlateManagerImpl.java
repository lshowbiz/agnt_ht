
package com.joymain.jecs.al.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.JalLibraryPlate;
import com.joymain.jecs.al.dao.JalLibraryPlateDao;
import com.joymain.jecs.al.service.JalLibraryPlateManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JalLibraryPlateManagerImpl extends BaseManager implements JalLibraryPlateManager {
    private JalLibraryPlateDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJalLibraryPlateDao(JalLibraryPlateDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryPlateManager#getJalLibraryPlates(com.joymain.jecs.al.model.JalLibraryPlate)
     */
    public List getJalLibraryPlates(final JalLibraryPlate jalLibraryPlate) {
        return dao.getJalLibraryPlates(jalLibraryPlate);
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryPlateManager#getJalLibraryPlate(String plateId)
     */
    public JalLibraryPlate getJalLibraryPlate(final String plateId) {
        return dao.getJalLibraryPlate(new Long(plateId));
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryPlateManager#saveJalLibraryPlate(JalLibraryPlate jalLibraryPlate)
     */
    public void saveJalLibraryPlate(JalLibraryPlate jalLibraryPlate) {
        dao.saveJalLibraryPlate(jalLibraryPlate);
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryPlateManager#removeJalLibraryPlate(String plateId)
     */
    public void removeJalLibraryPlate(final String plateId) {
        dao.removeJalLibraryPlate(new Long(plateId));
    }
    //added for getJalLibraryPlatesByCrm
    public List getJalLibraryPlatesByCrm(CommonRecord crm, Pager pager){
	return dao.getJalLibraryPlatesByCrm(crm,pager);
    }

	public List getAllJalLibraryPlates() {
		// TODO Auto-generated method stub
		return dao.getAllJalLibraryPlates();
	}
}
