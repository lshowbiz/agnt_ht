
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiAmountDetail;
import com.joymain.jecs.fi.dao.JfiAmountDetailDao;
import com.joymain.jecs.fi.service.JfiAmountDetailManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiAmountDetailManagerImpl extends BaseManager implements JfiAmountDetailManager {
    private JfiAmountDetailDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiAmountDetailDao(JfiAmountDetailDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiAmountDetailManager#getJfiAmountDetails(com.joymain.jecs.fi.model.JfiAmountDetail)
     */
    public List getJfiAmountDetails(final JfiAmountDetail jfiAmountDetail) {
        return dao.getJfiAmountDetails(jfiAmountDetail);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiAmountDetailManager#getJfiAmountDetail(String id)
     */
    public JfiAmountDetail getJfiAmountDetail(final String id) {
        return dao.getJfiAmountDetail(new Long(id));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiAmountDetailManager#saveJfiAmountDetail(JfiAmountDetail jfiAmountDetail)
     */
    public void saveJfiAmountDetail(JfiAmountDetail jfiAmountDetail) {
        dao.saveJfiAmountDetail(jfiAmountDetail);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiAmountDetailManager#removeJfiAmountDetail(String id)
     */
    public void removeJfiAmountDetail(final String id) {
        dao.removeJfiAmountDetail(new Long(id));
    }
    //added for getJfiAmountDetailsByCrm
    public List getJfiAmountDetailsByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiAmountDetailsByCrm(crm,pager);
    }
}
