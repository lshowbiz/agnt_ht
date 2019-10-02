
package com.joymain.jecs.am.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.am.model.AmNew;
import com.joymain.jecs.am.dao.AmNewDao;
import com.joymain.jecs.am.service.AmNewManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class AmNewManagerImpl extends BaseManager implements AmNewManager {
    private AmNewDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAmNewDao(AmNewDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.am.service.AmNewManager#getAmNews(com.joymain.jecs.am.model.AmNew)
     */
    public List getAmNews(final AmNew amNew) {
        return dao.getAmNews(amNew);
    }

    /**
     * @see com.joymain.jecs.am.service.AmNewManager#getAmNew(String newId)
     */
    public AmNew getAmNew(final String newId) {
        return dao.getAmNew(new String(newId));
    }

    /**
     * @see com.joymain.jecs.am.service.AmNewManager#saveAmNew(AmNew amNew)
     */
    public void saveAmNew(AmNew amNew) {
        dao.saveAmNew(amNew);
    }

    /**
     * @see com.joymain.jecs.am.service.AmNewManager#removeAmNew(String newId)
     */
    public void removeAmNew(final String newId) {
        dao.removeAmNew(new String(newId));
    }
    //added for getAmNewsByCrm
    public List getAmNewsByCrm(CommonRecord crm, Pager pager){
	return dao.getAmNewsByCrm(crm,pager);
    }
}
