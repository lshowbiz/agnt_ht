package com.joymain.jecs.po.service;

import java.util.List;
import java.util.Map;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.dao.JpoMemberOrderListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberOrderListManager extends Manager
{
    /**
     * Retrieves all of the jpoMemberOrderLists
     */
    public List getJpoMemberOrderLists(JpoMemberOrderList jpoMemberOrderList);
    
    /**
     * Gets jpoMemberOrderList's information based on molId.
     * 
     * @param molId the jpoMemberOrderList's molId
     * @return jpoMemberOrderList populated jpoMemberOrderList object
     */
    public JpoMemberOrderList getJpoMemberOrderList(final String molId);
    
    /**
     * Saves a jpoMemberOrderList's information
     * 
     * @param jpoMemberOrderList the object to be saved
     */
    public void saveJpoMemberOrderList(JpoMemberOrderList jpoMemberOrderList);
    
    /**
     * Removes a jpoMemberOrderList from the database by molId
     * 
     * @param molId the jpoMemberOrderList's molId
     */
    public void removeJpoMemberOrderList(final String molId);
    
    // added for getJpoMemberOrderListsByCrm
    public List getJpoMemberOrderListsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 根据订单明细流水号查询商品重量等信息　
     * 
     * @param map
     * @return
     */
    public JpoMemberOrderList getJpoMemberOrderListByMolId(Map<String, Long> map);
    
    public Integer getSumQtyByProNo();
}
