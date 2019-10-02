
package com.joymain.jecs.po.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.dao.JpoMemberOrderFeeDao;
import com.joymain.jecs.po.service.JpoMemberOrderFeeManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoMemberOrderFeeManagerImpl extends BaseManager implements JpoMemberOrderFeeManager {
    private JpoMemberOrderFeeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoMemberOrderFeeDao(JpoMemberOrderFeeDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderFeeManager#getJpoMemberOrderFees(com.joymain.jecs.po.model.JpoMemberOrderFee)
     */
    public List getJpoMemberOrderFees(final JpoMemberOrderFee jpoMemberOrderFee) {
        return dao.getJpoMemberOrderFees(jpoMemberOrderFee);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderFeeManager#getJpoMemberOrderFee(String mofId)
     */
    public JpoMemberOrderFee getJpoMemberOrderFee(final String mofId) {
        return dao.getJpoMemberOrderFee(new Long(mofId));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderFeeManager#saveJpoMemberOrderFee(JpoMemberOrderFee jpoMemberOrderFee)
     */
    public void saveJpoMemberOrderFee(JpoMemberOrderFee jpoMemberOrderFee) {
        dao.saveJpoMemberOrderFee(jpoMemberOrderFee);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoMemberOrderFeeManager#removeJpoMemberOrderFee(String mofId)
     */
    public void removeJpoMemberOrderFee(final String mofId) {
        dao.removeJpoMemberOrderFee(new Long(mofId));
    }
    //added for getJpoMemberOrderFeesByCrm
    public List getJpoMemberOrderFeesByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoMemberOrderFeesByCrm(crm,pager);
    }
}
