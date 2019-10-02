
package com.joymain.jecs.po.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.po.dao.JpoShippingFeeDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpoShippingFeeManager extends Manager {
    /**
     * Retrieves all of the jpoShippingFees
     */
    public List getJpoShippingFees(JpoShippingFee jpoShippingFee);

    /**
     * Gets jpoShippingFee's information based on jpsId.
     * @param jpsId the jpoShippingFee's jpsId
     * @return jpoShippingFee populated jpoShippingFee object
     */
    public JpoShippingFee getJpoShippingFee(final String jpsId);

    /**
     * Saves a jpoShippingFee's information
     * @param jpoShippingFee the object to be saved
     */
    public void saveJpoShippingFee(JpoShippingFee jpoShippingFee);

    /**
     * Removes a jpoShippingFee from the database by jpsId
     * @param jpsId the jpoShippingFee's jpsId
     */
    public void removeJpoShippingFee(final String jpsId);
    //added for getJpoShippingFeesByCrm
    public List getJpoShippingFeesByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 快递策略
     * @param fee
     * @param totle
     * @return
     */
	public BigDecimal getFee(JpoShippingFee fee,BigDecimal totle);
	
	/**
     * 零担策略
     * @param fee
     * @param totle
     * @return
     */
	public BigDecimal getFeeV(JpoShippingFee fee,BigDecimal totle);
	
	/**
     * 零担重货策略
     * @param fee
     * @param totle
     * @return
     */
	public BigDecimal getFeeWZ(JpoShippingFee fee,BigDecimal totle);
	
	  /**
     * 物流费计算
     * @param jpoShippingFee
     * @param sum
     * @param dtProduct
     * @param request
     * @return
     */
    public JpoMemberOrderFee shippingFeeCalc(JpoMemberOrder jpoMemberOrder, Set jpoMemberOrderSet, HttpServletRequest request);
}

