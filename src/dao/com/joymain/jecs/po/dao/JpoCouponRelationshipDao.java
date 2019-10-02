
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoCouponRelationship;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoCouponRelationshipDao extends Dao {

    /**
     * Retrieves all of the jpoCouponRelationships
     */
    public List getJpoCouponRelationships(JpoCouponRelationship jpoCouponRelationship);

    /**
     * Gets jpoCouponRelationship's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jpoCouponRelationship's id
     * @return jpoCouponRelationship populated jpoCouponRelationship object
     */
    public JpoCouponRelationship getJpoCouponRelationship(final BigDecimal id);

    /**
     * Saves a jpoCouponRelationship's information
     * @param jpoCouponRelationship the object to be saved
     */    
    public void saveJpoCouponRelationship(JpoCouponRelationship jpoCouponRelationship);

    /**
     * Removes a jpoCouponRelationship from the database by id
     * @param id the jpoCouponRelationship's id
     */
    public void removeJpoCouponRelationship(final BigDecimal id);
    //added for getJpoCouponRelationshipsByCrm
    public List getJpoCouponRelationshipsByCrm(CommonRecord crm, Pager pager);
}

