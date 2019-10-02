
package com.joymain.jecs.po.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoCouponRelationship;
import com.joymain.jecs.po.dao.JpoCouponRelationshipDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoCouponRelationshipManager extends Manager {
    /**
     * Retrieves all of the jpoCouponRelationships
     */
    public List getJpoCouponRelationships(JpoCouponRelationship jpoCouponRelationship);

    /**
     * Gets jpoCouponRelationship's information based on id.
     * @param id the jpoCouponRelationship's id
     * @return jpoCouponRelationship populated jpoCouponRelationship object
     */
    public JpoCouponRelationship getJpoCouponRelationship(final String id);

    /**
     * Saves a jpoCouponRelationship's information
     * @param jpoCouponRelationship the object to be saved
     */
    public void saveJpoCouponRelationship(JpoCouponRelationship jpoCouponRelationship);

    /**
     * Removes a jpoCouponRelationship from the database by id
     * @param id the jpoCouponRelationship's id
     */
    public void removeJpoCouponRelationship(final String id);
    //added for getJpoCouponRelationshipsByCrm
    public List getJpoCouponRelationshipsByCrm(CommonRecord crm, Pager pager);
}

