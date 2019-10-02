
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.fi.model.JfiSunOrder;
import com.joymain.jecs.fi.dao.JfiSunOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JfiSunOrderManager extends Manager {
    /**
     * Retrieves all of the jfiSunOrders
     */
    public List getJfiSunOrders(JfiSunOrder jfiSunOrder);

    /**
     * Gets jfiSunOrder's information based on moId.
     * @param moId the jfiSunOrder's moId
     * @return jfiSunOrder populated jfiSunOrder object
     */
    public JfiSunOrder getJfiSunOrder(final String moId);

    /**
     * Saves a jfiSunOrder's information
     * @param jfiSunOrder the object to be saved
     */
    public void saveJfiSunOrder(JfiSunOrder jfiSunOrder);

    /**
     * Removes a jfiSunOrder from the database by moId
     * @param moId the jfiSunOrder's moId
     */
    public void removeJfiSunOrder(final String moId);
    //added for getJfiSunOrdersByCrm
    public List getJfiSunOrdersByCrm(CommonRecord crm, Pager pager);
	
	/**
	 * 根据条件统计商品销售量
	 * @param crm
	 * @return
	 */
	public List getStatisticProductSale(CommonRecord crm);
}

