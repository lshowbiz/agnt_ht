
package com.joymain.jecs.bd.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.bd.model.JbdUserValidList;
import com.joymain.jecs.bd.dao.JbdUserValidListDao;
import com.joymain.jecs.bd.service.JbdUserValidListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JbdUserValidListManagerImpl extends BaseManager implements JbdUserValidListManager {
    private JbdUserValidListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJbdUserValidListDao(JbdUserValidListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserValidListManager#getJbdUserValidLists(com.joymain.jecs.bd.model.JbdUserValidList)
     */
    public List getJbdUserValidLists(final JbdUserValidList jbdUserValidList) {
        return dao.getJbdUserValidLists(jbdUserValidList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserValidListManager#getJbdUserValidList(String id)
     */
    public JbdUserValidList getJbdUserValidList(final String id) {
        return dao.getJbdUserValidList(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserValidListManager#saveJbdUserValidList(JbdUserValidList jbdUserValidList)
     */
    public void saveJbdUserValidList(JbdUserValidList jbdUserValidList) {
        dao.saveJbdUserValidList(jbdUserValidList);
    }

    /**
     * @see com.joymain.jecs.bd.service.JbdUserValidListManager#removeJbdUserValidList(String id)
     */
    public void removeJbdUserValidList(final String id) {
        dao.removeJbdUserValidList(new BigDecimal(id));
    }
    //added for getJbdUserValidListsByCrm
    public List getJbdUserValidListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJbdUserValidListsByCrm(crm,pager);
    }
}
