
package com.joymain.jecs.po.service.impl;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.po.model.JpoCouponRelationship;
import com.joymain.jecs.po.dao.JpoCouponRelationshipDao;
import com.joymain.jecs.po.service.JpoCouponRelationshipManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public class JpoCouponRelationshipManagerImpl extends BaseManager implements JpoCouponRelationshipManager {
    private JpoCouponRelationshipDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpoCouponRelationshipDao(JpoCouponRelationshipDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCouponRelationshipManager#getJpoCouponRelationships(com.joymain.jecs.po.model.JpoCouponRelationship)
     */
    public List getJpoCouponRelationships(final JpoCouponRelationship jpoCouponRelationship) {
        return dao.getJpoCouponRelationships(jpoCouponRelationship);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCouponRelationshipManager#getJpoCouponRelationship(String id)
     */
    public JpoCouponRelationship getJpoCouponRelationship(final String id) {
        return dao.getJpoCouponRelationship(new BigDecimal(id));
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCouponRelationshipManager#saveJpoCouponRelationship(JpoCouponRelationship jpoCouponRelationship)
     */
    public void saveJpoCouponRelationship(JpoCouponRelationship jpoCouponRelationship) {
        dao.saveJpoCouponRelationship(jpoCouponRelationship);
    }

    /**
     * @see com.joymain.jecs.po.service.JpoCouponRelationshipManager#removeJpoCouponRelationship(String id)
     */
    public void removeJpoCouponRelationship(final String id) {
        dao.removeJpoCouponRelationship(new BigDecimal(id));
    }
    //added for getJpoCouponRelationshipsByCrm
    public List getJpoCouponRelationshipsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpoCouponRelationshipsByCrm(crm,pager);
    }
}
