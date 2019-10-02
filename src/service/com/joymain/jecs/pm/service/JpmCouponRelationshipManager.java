
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.pm.model.JpmCouponRelationship;
import com.joymain.jecs.pm.dao.JpmCouponRelationshipDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmCouponRelationshipManager extends Manager {
    /**
     * Retrieves all of the jpmCouponRelationships
     */
    public List getJpmCouponRelationships(JpmCouponRelationship jpmCouponRelationship);

    /**
     * Gets jpmCouponRelationship's information based on id.
     * @param id the jpmCouponRelationship's id
     * @return jpmCouponRelationship populated jpmCouponRelationship object
     */
    public JpmCouponRelationship getJpmCouponRelationship(final String id);

    /**
     * Saves a jpmCouponRelationship's information
     * @param jpmCouponRelationship the object to be saved
     */
    public void saveJpmCouponRelationship(JpmCouponRelationship jpmCouponRelationship);

    /**
     * Removes a jpmCouponRelationship from the database by id
     * @param id the jpmCouponRelationship's id
     */
    public void removeJpmCouponRelationship(final String id);
    //added for getJpmCouponRelationshipsByCrm
    public List getJpmCouponRelationshipsByCrm(CommonRecord crm, Pager pager);
}

