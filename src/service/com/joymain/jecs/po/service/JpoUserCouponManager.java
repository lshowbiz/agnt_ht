
package com.joymain.jecs.po.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.po.model.JpoUserCoupon;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoUserCouponManager extends Manager {
    /**
     * Retrieves all of the jpoUserCoupons
     */
    public List getJpoUserCoupons(JpoUserCoupon jpoUserCoupon);

    /**
     * Gets jpoUserCoupon's information based on id.
     * @param id the jpoUserCoupon's id
     * @return jpoUserCoupon populated jpoUserCoupon object
     */
    public JpoUserCoupon getJpoUserCoupon(final String id);

    /**
     * Saves a jpoUserCoupon's information
     * @param jpoUserCoupon the object to be saved
     */
    public void saveJpoUserCoupon(JpoUserCoupon jpoUserCoupon);

    /**
     * Removes a jpoUserCoupon from the database by id
     * @param id the jpoUserCoupon's id
     */
    public void removeJpoUserCoupon(final String id);
    //added for getJpoUserCouponsByCrm
    public List getJpoUserCouponsByCrm(CommonRecord crm, Pager pager);
    
    public List getJpoUserCouponsByCrmToSql(CommonRecord crm, Pager pager);
    
    /**
     * 修改启用禁用
     * @param ids
     */
    public void updateUserCouponByIds(String ids);
    
    public void saveJpoUserCouponMany(Map map);
}

