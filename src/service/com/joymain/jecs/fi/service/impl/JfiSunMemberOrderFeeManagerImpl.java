
package com.joymain.jecs.fi.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.fi.model.JfiSunMemberOrderFee;
import com.joymain.jecs.fi.dao.JfiSunMemberOrderFeeDao;
import com.joymain.jecs.fi.service.JfiSunMemberOrderFeeManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JfiSunMemberOrderFeeManagerImpl extends BaseManager implements JfiSunMemberOrderFeeManager {
    private JfiSunMemberOrderFeeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJfiSunMemberOrderFeeDao(JfiSunMemberOrderFeeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderFeeManager#getJfiSunMemberOrderFees(com.joymain.jecs.fi.model.JfiSunMemberOrderFee)
     */
    public List getJfiSunMemberOrderFees(final JfiSunMemberOrderFee jfiSunMemberOrderFee) {
        return dao.getJfiSunMemberOrderFees(jfiSunMemberOrderFee);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderFeeManager#getJfiSunMemberOrderFee(String mofId)
     */
    public JfiSunMemberOrderFee getJfiSunMemberOrderFee(final String mofId) {
        return dao.getJfiSunMemberOrderFee(new Long(mofId));
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderFeeManager#saveJfiSunMemberOrderFee(JfiSunMemberOrderFee jfiSunMemberOrderFee)
     */
    public void saveJfiSunMemberOrderFee(JfiSunMemberOrderFee jfiSunMemberOrderFee) {
        dao.saveJfiSunMemberOrderFee(jfiSunMemberOrderFee);
    }

    /**
     * @see com.joymain.jecs.fi.service.JfiSunMemberOrderFeeManager#removeJfiSunMemberOrderFee(String mofId)
     */
    public void removeJfiSunMemberOrderFee(final String mofId) {
        dao.removeJfiSunMemberOrderFee(new Long(mofId));
    }
    //added for getJfiSunMemberOrderFeesByCrm
    public List getJfiSunMemberOrderFeesByCrm(CommonRecord crm, Pager pager){
	return dao.getJfiSunMemberOrderFeesByCrm(crm,pager);
    }
}
