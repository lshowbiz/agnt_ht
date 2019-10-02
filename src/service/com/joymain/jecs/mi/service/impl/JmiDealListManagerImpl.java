
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiDealList;
import com.joymain.jecs.mi.dao.JmiDealListDao;
import com.joymain.jecs.mi.service.JmiDealListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiDealListManagerImpl extends BaseManager implements JmiDealListManager {
    private JmiDealListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiDealListDao(JmiDealListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiDealListManager#getJmiDealLists(com.joymain.jecs.mi.model.JmiDealList)
     */
    public List getJmiDealLists(final JmiDealList jmiDealList) {
        return dao.getJmiDealLists(jmiDealList);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiDealListManager#getJmiDealList(String id)
     */
    public JmiDealList getJmiDealList(final String id) {
        return dao.getJmiDealList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiDealListManager#saveJmiDealList(JmiDealList jmiDealList)
     */
    public void saveJmiDealList(JmiDealList jmiDealList) {
        dao.saveJmiDealList(jmiDealList);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiDealListManager#removeJmiDealList(String id)
     */
    public void removeJmiDealList(final String id) {
        dao.removeJmiDealList(new Long(id));
    }
    //added for getJmiDealListsByCrm
    public List getJmiDealListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiDealListsByCrm(crm,pager);
    }
}
