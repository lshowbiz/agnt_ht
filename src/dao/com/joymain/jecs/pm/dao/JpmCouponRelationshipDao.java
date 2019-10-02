
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmCouponRelationship;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmCouponRelationshipDao extends Dao {

    /**
     * Retrieves all of the jpmCouponRelationships
     */
    public List getJpmCouponRelationships(JpmCouponRelationship jpmCouponRelationship);

    /**
     * Gets jpmCouponRelationship's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jpmCouponRelationship's id
     * @return jpmCouponRelationship populated jpmCouponRelationship object
     */
    public JpmCouponRelationship getJpmCouponRelationship(final BigDecimal id);

    /**
     * Saves a jpmCouponRelationship's information
     * @param jpmCouponRelationship the object to be saved
     */    
    public void saveJpmCouponRelationship(JpmCouponRelationship jpmCouponRelationship);

    /**
     * Removes a jpmCouponRelationship from the database by id
     * @param id the jpmCouponRelationship's id
     */
    public void removeJpmCouponRelationship(final BigDecimal id);
    //added for getJpmCouponRelationshipsByCrm
    public List getJpmCouponRelationshipsByCrm(CommonRecord crm, Pager pager);
}

