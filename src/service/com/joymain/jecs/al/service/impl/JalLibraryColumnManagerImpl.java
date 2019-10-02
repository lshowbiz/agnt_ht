
package com.joymain.jecs.al.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.JalLibraryColumn;
import com.joymain.jecs.al.dao.JalLibraryColumnDao;
import com.joymain.jecs.al.service.JalLibraryColumnManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JalLibraryColumnManagerImpl extends BaseManager implements JalLibraryColumnManager {
    private JalLibraryColumnDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJalLibraryColumnDao(JalLibraryColumnDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryColumnManager#getJalLibraryColumns(com.joymain.jecs.al.model.JalLibraryColumn)
     */
    public List getJalLibraryColumns(final JalLibraryColumn jalLibraryColumn) {
        return dao.getJalLibraryColumns(jalLibraryColumn);
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryColumnManager#getJalLibraryColumn(String columnId)
     */
    public JalLibraryColumn getJalLibraryColumn(final String columnId) {
        return dao.getJalLibraryColumn(new Long(columnId));
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryColumnManager#saveJalLibraryColumn(JalLibraryColumn jalLibraryColumn)
     */
    public void saveJalLibraryColumn(JalLibraryColumn jalLibraryColumn) {
        dao.saveJalLibraryColumn(jalLibraryColumn);
    }

    /**
     * @see com.joymain.jecs.al.service.JalLibraryColumnManager#removeJalLibraryColumn(String columnId)
     */
    public void removeJalLibraryColumn(final String columnId) {
        dao.removeJalLibraryColumn(new Long(columnId));
    }
    //added for getJalLibraryColumnsByCrm
    public List getJalLibraryColumnsByCrm(CommonRecord crm, Pager pager){
	return dao.getJalLibraryColumnsByCrm(crm,pager);
    }

	public List getJalLibraryColumnsByPlateId(String plateId) {
		// TODO Auto-generated method stub
		return dao.getJalLibraryColumnsByPlateId(plateId);
	}
}
