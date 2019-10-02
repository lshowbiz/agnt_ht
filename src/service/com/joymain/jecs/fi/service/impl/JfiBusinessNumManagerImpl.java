
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiBusinessNum;
import com.joymain.jecs.fi.dao.JfiBusinessNumDao;
import com.joymain.jecs.fi.service.JfiBusinessNumManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiBusinessNumManagerImpl extends BaseManager implements JfiBusinessNumManager {
    private JfiBusinessNumDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiBusinessNumDao(JfiBusinessNumDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBusinessNumManager#getJfiBusinessNums(com.joymain.jecs.fi.model.JfiBusinessNum)
     */
    public List getJfiBusinessNums(final JfiBusinessNum jfiBusinessNum) {
        return dao.getJfiBusinessNums(jfiBusinessNum);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBusinessNumManager#getJfiBusinessNum(String businessId)
     */
    public JfiBusinessNum getJfiBusinessNum(final String businessId) {
        return dao.getJfiBusinessNum(new Long(businessId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBusinessNumManager#saveJfiBusinessNum(JfiBusinessNum jfiBusinessNum)
     */
    public void saveJfiBusinessNum(JfiBusinessNum jfiBusinessNum) {
        dao.saveJfiBusinessNum(jfiBusinessNum);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiBusinessNumManager#removeJfiBusinessNum(String businessId)
     */
    public void removeJfiBusinessNum(final String businessId) {
        dao.removeJfiBusinessNum(new Long(businessId));
    }
    //added for getJfiBusinessNumsByCrm
    public List getJfiBusinessNumsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiBusinessNumsByCrm(crm,pager);
    }
}
