
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiDepositList;
import com.joymain.jecs.fi.dao.JfiDepositListDao;
import com.joymain.jecs.fi.service.JfiDepositListManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiDepositListManagerImpl extends BaseManager implements JfiDepositListManager {
    private JfiDepositListDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiDepositListDao(JfiDepositListDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositListManager#getJfiDepositLists(com.joymain.jecs.fi.model.JfiDepositList)
     */
    public List getJfiDepositLists(final JfiDepositList jfiDepositList) {
        return dao.getJfiDepositLists(jfiDepositList);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositListManager#getJfiDepositList(String id)
     */
    public JfiDepositList getJfiDepositList(final String id) {
        return dao.getJfiDepositList(new Long(id));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositListManager#saveJfiDepositList(JfiDepositList jfiDepositList)
     */
    public void saveJfiDepositList(JfiDepositList jfiDepositList) {
        dao.saveJfiDepositList(jfiDepositList);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositListManager#removeJfiDepositList(String id)
     */
    public void removeJfiDepositList(final String id) {
        dao.removeJfiDepositList(new Long(id));
    }
    //added for getJfiDepositListsByCrm
    public List getJfiDepositListsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiDepositListsByCrm(crm,pager);
    }
}
