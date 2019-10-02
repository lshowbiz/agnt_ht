
package com.joymain.jecs.po.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoUserCoupon;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoUserCouponDao extends Dao {

    /**
     * Retrieves all of the jpoUserCoupons
     */
    public List getJpoUserCoupons(JpoUserCoupon jpoUserCoupon);

    /**
     * Gets jpoUserCoupon's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jpoUserCoupon's id
     * @return jpoUserCoupon populated jpoUserCoupon object
     */
    public JpoUserCoupon getJpoUserCoupon(final BigDecimal id);

    /**
     * Saves a jpoUserCoupon's information
     * @param jpoUserCoupon the object to be saved
     */    
    public void saveJpoUserCoupon(JpoUserCoupon jpoUserCoupon);

    /**
     * Removes a jpoUserCoupon from the database by id
     * @param id the jpoUserCoupon's id
     */
    public void removeJpoUserCoupon(final BigDecimal id);
    //added for getJpoUserCouponsByCrm
    public List getJpoUserCouponsByCrm(CommonRecord crm, Pager pager);
    
    public List getJpoUserCouponsByCrmToSql(CommonRecord crm, Pager pager);
    
    public void updateUserCouponByIds(String ids);
}

