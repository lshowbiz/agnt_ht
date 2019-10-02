
package com.joymain.jecs.mi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.mi.model.JmiYdSendList;
import com.joymain.jecs.mi.dao.JmiYdSendListDao;
import com.joymain.jecs.mi.service.JmiYdSendListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JmiYdSendListManagerImpl extends BaseManager implements JmiYdSendListManager {
    private JmiYdSendListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJmiYdSendListDao(JmiYdSendListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiYdSendListManager#getJmiYdSendLists(com.joymain.jecs.mi.model.JmiYdSendList)
     */
    public List getJmiYdSendLists(final JmiYdSendList jmiYdSendList) {
        return dao.getJmiYdSendLists(jmiYdSendList);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiYdSendListManager#getJmiYdSendList(String id)
     */
    public JmiYdSendList getJmiYdSendList(final String id) {
        return dao.getJmiYdSendList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiYdSendListManager#saveJmiYdSendList(JmiYdSendList jmiYdSendList)
     */
    public void saveJmiYdSendList(JmiYdSendList jmiYdSendList) {
        dao.saveJmiYdSendList(jmiYdSendList);
    }

    /**
     * @see com.joymain.jecs.mi.service.JmiYdSendListManager#removeJmiYdSendList(String id)
     */
    public void removeJmiYdSendList(final String id) {
        dao.removeJmiYdSendList(new Long(id));
    }
    //added for getJmiYdSendListsByCrm
    public List getJmiYdSendListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJmiYdSendListsByCrm(crm,pager);
    }
}
