
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdYkJiandianList;
import com.joymain.jecs.bd.dao.JbdYkJiandianListDao;
import com.joymain.jecs.bd.service.JbdYkJiandianListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdYkJiandianListManagerImpl extends BaseManager implements JbdYkJiandianListManager {
    private JbdYkJiandianListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdYkJiandianListDao(JbdYkJiandianListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdYkJiandianListManager#getJbdYkJiandianLists(com.joymain.jecs.bd.model.JbdYkJiandianList)
     */
    public List getJbdYkJiandianLists(final JbdYkJiandianList jbdYkJiandianList) {
        return dao.getJbdYkJiandianLists(jbdYkJiandianList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdYkJiandianListManager#getJbdYkJiandianList(String id)
     */
    public JbdYkJiandianList getJbdYkJiandianList(final String id) {
        return dao.getJbdYkJiandianList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdYkJiandianListManager#saveJbdYkJiandianList(JbdYkJiandianList jbdYkJiandianList)
     */
    public void saveJbdYkJiandianList(JbdYkJiandianList jbdYkJiandianList) {
        dao.saveJbdYkJiandianList(jbdYkJiandianList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdYkJiandianListManager#removeJbdYkJiandianList(String id)
     */
    public void removeJbdYkJiandianList(final String id) {
        dao.removeJbdYkJiandianList(new Long(id));
    }
    //added for getJbdYkJiandianListsByCrm
    public List getJbdYkJiandianListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdYkJiandianListsByCrm(crm,pager);
    }
}
