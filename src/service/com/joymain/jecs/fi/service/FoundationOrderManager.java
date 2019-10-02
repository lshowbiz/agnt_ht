
package com.joymain.jecs.fi.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.fi.model.FoundationOrder;
import com.joymain.jecs.fi.dao.FoundationOrderDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
public interface FoundationOrderManager extends Manager {
    /**
     * Retrieves all of the foundationOrders
     */
    public List getFoundationOrders(FoundationOrder foundationOrder);

    /**
     * Gets foundationOrder's information based on orderId.
     * @param orderId the foundationOrder's orderId
     * @return foundationOrder populated foundationOrder object
     */
    public FoundationOrder getFoundationOrder(final String orderId);

    /**
     * Saves a foundationOrder's information
     * @param foundationOrder the object to be saved
     */
    public void saveFoundationOrder(FoundationOrder foundationOrder);
    
    /**
     * Saves a foundationOrder's information
     * @param foundationOrder the object to be saved
     */
    public Long saveFoundationOrder2(FoundationOrder foundationOrder);

    /**
     * Removes a foundationOrder from the database by orderId
     * @param orderId the foundationOrder's orderId
     */
    public void removeFoundationOrder(final String orderId);
    //added for getFoundationOrdersByCrm
    public List getFoundationOrdersByCrm(CommonRecord crm, Pager pager);
    
    //added for getFoundationOrdersByCrm
    public List foundationItemsByCrm(CommonRecord crm, Pager pager);
    
    //慈善公益订单审单
    public void checkFoundationOrder(FoundationOrder foundationOrder,SysUser sysUser) throws AppException;
    
    //查询会员在过去的1年里面参加的爱心365活动的订单
    public List getOrdersByItemTypeAndTime(String userCode);
}

