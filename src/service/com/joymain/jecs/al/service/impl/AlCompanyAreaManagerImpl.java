
package com.joymain.jecs.al.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.al.model.AlCompanyArea;
import com.joymain.jecs.al.dao.AlCompanyAreaDao;
import com.joymain.jecs.al.service.AlCompanyAreaManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class AlCompanyAreaManagerImpl extends BaseManager implements AlCompanyAreaManager {
    private AlCompanyAreaDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAlCompanyAreaDao(AlCompanyAreaDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.al.service.AlCompanyAreaManager#getAlCompanyAreas(com.joymain.jecs.al.model.AlCompanyArea)
     */
    public List getAlCompanyAreas(final AlCompanyArea alCompanyArea) {
        return dao.getAlCompanyAreas(alCompanyArea);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCompanyAreaManager#getAlCompanyArea(String id)
     */
    public AlCompanyArea getAlCompanyArea(final String id) {
        return dao.getAlCompanyArea(new Long(id));
    }

    /**
     * @see com.joymain.jecs.al.service.AlCompanyAreaManager#saveAlCompanyArea(AlCompanyArea alCompanyArea)
     */
    public void saveAlCompanyArea(AlCompanyArea alCompanyArea) {
        dao.saveAlCompanyArea(alCompanyArea);
    }

    /**
     * @see com.joymain.jecs.al.service.AlCompanyAreaManager#removeAlCompanyArea(String id)
     */
    public void removeAlCompanyArea(final String id) {
        dao.removeAlCompanyArea(new Long(id));
    }
    //added for getAlCompanyAreasByCrm
		public List getAlCompanyAreasByCrm(CommonRecord crm, Pager pager){
				return dao.getAlCompanyAreasByCrm(crm,pager);
		}
}
