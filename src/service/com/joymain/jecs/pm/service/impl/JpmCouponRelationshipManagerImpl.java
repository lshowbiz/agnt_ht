
package com.joymain.jecs.pm.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.pm.model.JpmCouponRelationship;
import com.joymain.jecs.pm.dao.JpmCouponRelationshipDao;
import com.joymain.jecs.pm.service.JpmCouponRelationshipManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpmCouponRelationshipManagerImpl extends BaseManager implements JpmCouponRelationshipManager {
    private JpmCouponRelationshipDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmCouponRelationshipDao(JpmCouponRelationshipDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCouponRelationshipManager#getJpmCouponRelationships(com.joymain.jecs.pm.model.JpmCouponRelationship)
     */
    public List getJpmCouponRelationships(final JpmCouponRelationship jpmCouponRelationship) {
        return dao.getJpmCouponRelationships(jpmCouponRelationship);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCouponRelationshipManager#getJpmCouponRelationship(String id)
     */
    public JpmCouponRelationship getJpmCouponRelationship(final String id) {
        return dao.getJpmCouponRelationship(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCouponRelationshipManager#saveJpmCouponRelationship(JpmCouponRelationship jpmCouponRelationship)
     */
    public void saveJpmCouponRelationship(JpmCouponRelationship jpmCouponRelationship) {
        dao.saveJpmCouponRelationship(jpmCouponRelationship);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCouponRelationshipManager#removeJpmCouponRelationship(String id)
     */
    public void removeJpmCouponRelationship(final String id) {
        dao.removeJpmCouponRelationship(new BigDecimal(id));
    }
    //added for getJpmCouponRelationshipsByCrm
    public List getJpmCouponRelationshipsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmCouponRelationshipsByCrm(crm,pager);
    }
}
