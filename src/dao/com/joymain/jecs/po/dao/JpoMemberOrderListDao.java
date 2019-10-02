package com.joymain.jecs.po.dao;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberOrderListDao extends Dao
{
    
    /**
     * Retrieves all of the jpoMemberOrderLists
     */
    public List getJpoMemberOrderLists(JpoMemberOrderList jpoMemberOrderList);
    
    /**
     * Gets jpoMemberOrderList's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param molId the jpoMemberOrderList's molId
     * @return jpoMemberOrderList populated jpoMemberOrderList object
     */
    public JpoMemberOrderList getJpoMemberOrderList(final Long molId);
    
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
    public void removeJpoMemberOrderList(final Long molId);
    
    // added for getJpoMemberOrderListsByCrm
    public List getJpoMemberOrderListsByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 根据订单明细流水号查询商品重量等信息　
     * 
     * @param map
     * @return
     */
    public JpoMemberOrderList getJpoMemberOrderListByMolId(Map<String, Long> map);
    
    public Long getProSumByProNo(String proNo);

    /**
     * 根据商品编码和订单号查询订单下的商品信息
     * @author gw 2015-02-02
     * @param orderNo
     * @param getproductNo
     * @return jpoMemberOrderList
     */
	public JpoMemberOrderList getJpoMemberOrderListForOrderNoAndProductNo(String orderNo, String productNo);
    
	public Integer getJpoMemberOrderListForReturn(Long molId);

	 /**
     * 库存清货数量限制
     * @param proNo
     * @param statetime
     * @param endtime
     * @return
     */
    public Integer getProSumByProNo(String proNo,String statetime,String endtime);
}
