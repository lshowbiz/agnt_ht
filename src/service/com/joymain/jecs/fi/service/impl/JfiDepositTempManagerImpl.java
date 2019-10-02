
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiDepositTemp;
import com.joymain.jecs.fi.dao.JfiDepositTempDao;
import com.joymain.jecs.fi.service.JfiDepositTempManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiDepositTempManagerImpl extends BaseManager implements JfiDepositTempManager {
    private JfiDepositTempDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiDepositTempDao(JfiDepositTempDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositTempManager#getJfiDepositTemps(com.joymain.jecs.fi.model.JfiDepositTemp)
     */
    public List getJfiDepositTemps(final JfiDepositTemp jfiDepositTemp) {
        return dao.getJfiDepositTemps(jfiDepositTemp);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositTempManager#getJfiDepositTemp(String tempId)
     */
    public JfiDepositTemp getJfiDepositTemp(final String tempId) {
        return dao.getJfiDepositTemp(new Long(tempId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositTempManager#saveJfiDepositTemp(JfiDepositTemp jfiDepositTemp)
     */
    public void saveJfiDepositTemp(JfiDepositTemp jfiDepositTemp) {
        dao.saveJfiDepositTemp(jfiDepositTemp);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiDepositTempManager#removeJfiDepositTemp(String tempId)
     */
    public void removeJfiDepositTemp(final String tempId) {
        dao.removeJfiDepositTemp(new Long(tempId));
    }
    //added for getJfiDepositTempsByCrm
    public List getJfiDepositTempsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiDepositTempsByCrm(crm,pager);
    }
}
