
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdNetworkList;
import com.joymain.jecs.bd.dao.JbdNetworkListDao;
import com.joymain.jecs.bd.service.JbdNetworkListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdNetworkListManagerImpl extends BaseManager implements JbdNetworkListManager {
    private JbdNetworkListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdNetworkListDao(JbdNetworkListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdNetworkListManager#getJbdNetworkLists(com.joymain.jecs.bd.model.JbdNetworkList)
     */
    public List getJbdNetworkLists(final JbdNetworkList jbdNetworkList) {
        return dao.getJbdNetworkLists(jbdNetworkList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdNetworkListManager#getJbdNetworkList(String id)
     */
    public JbdNetworkList getJbdNetworkList(final String id) {
        return dao.getJbdNetworkList(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdNetworkListManager#saveJbdNetworkList(JbdNetworkList jbdNetworkList)
     */
    public void saveJbdNetworkList(JbdNetworkList jbdNetworkList) {
        dao.saveJbdNetworkList(jbdNetworkList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdNetworkListManager#removeJbdNetworkList(String id)
     */
    public void removeJbdNetworkList(final String id) {
        dao.removeJbdNetworkList(new BigDecimal(id));
    }
    //added for getJbdNetworkListsByCrm
    public List getJbdNetworkListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdNetworkListsByCrm(crm,pager);
    }

	public Object[] getJbdNetworkListsByCrmSum(CommonRecord crm) {
		return dao.getJbdNetworkListsByCrmSum(crm);
	}
}
