
package com.joymain.jecs.pr.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.pr.model.JprRefund;

@SuppressWarnings("unchecked")
public interface JprRefundDao extends Dao {

    /**
     * Retrieves all of the jprRefunds
     */
    public List getJprRefunds(JprRefund jprRefund);

    /**
     * Gets jprRefund's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param roNo the jprRefund's roNo
     * @return jprRefund populated jprRefund object
     */
    public JprRefund getJprRefund(final String roNo);

    /**
     * Saves a jprRefund's information
     * @param jprRefund the object to be saved
     */    
    public void saveJprRefund(JprRefund jprRefund);

    /**
     * Removes a jprRefund from the database by roNo
     * @param roNo the jprRefund's roNo
     */
    public void removeJprRefund(final String roNo);
		//added for getJprRefundsByCrm
	public List getJprRefundsByCrm(CommonRecord crm, Pager pager);
	/**
	 * 根据条件统计商品销售量
	 * @param crm
	 * @return
	 */
	public List statisticProductSale(CommonRecord crm);
	
	/**
	 * 获取会员级别PV=首单PV+升级单PV-退货单PV
	 * @param userCode
	 * @return
	 */
	public BigDecimal getUserOrderPv(String userCode);
	
	/**
	 * 根据时间获取订单总价格与总PV
	 * @param companyCode
	 * @param createBTime
	 * @param createETime
	 * @return
	 */
	public List getJprRefundCByTime(String companyCode, String createBTime, String createETime);
	
	/**
	 * 获取一段时间内各个公司(如指定companyCode,则获取对应的公司)的已退货订单
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @param returnType
	 * @return
	 */
	public List getJprReRefundStaticsCheckedCompany(final String startDate, final String endDate, final String companyCode, final String productType, final String returnType);
	
	/**
	 * 获取会员订单的剩余量=订购量-退货量
	 * @param memberOrderNo
	 * @return
	 */
	public List getPoMemberOrderStock(final Long memberOrderNo);
	/**
	 * 获取已收款的取消审核订单
	 * @param crm
	 * @return
	 */
	public List getRefundGathering(CommonRecord crm);
	public List getRefundSum(CommonRecord crm);
	public List getJprReRefundStaticsCheckedCompanyByTree(final String startDate, final String endDate, final String companyCode, final String returnType,String treeIndex,String relationType);
	/**
	 * get JprRefund by Moid
	 * @param moId orderId
	 * @return list JprRefund
	 */
	public List<JprRefund> getJprRefundByMoId(Long moId,String roNo);
	
	/**
	 * 调用sql函数 执行大订单结算降级黄砖会员，幸福会员
	 * @param moId 退货单主键 (退货单编号)
	 *  * @param njtcType  --jtc_type 1 订单 2 退单
	 */
	public String getFunctionRp(String moId,Integer njtcType);

	/**
	 * 获取这张订单所关联的退货单
	 * @author gw 2015-07-13
	 * @param memberOrderNo
	 * @return list
	 */
	public List<JprRefund> getJprRefundForMemberOrder(String memberOrderNo);

	/**
	 * 根据退货单号查询退货入库状态
	 * @author fu 2015-09-10 
	 * @param roNo
	 * @return string 
	 */
	public String getIntoStatus(String roNo);
	
	/**
	 * 根据退货单号查询退货单
	 * @author fu 2015-09-10 
	 * @param roNo
	 * @return list 
	 */
	public List getJprRefundForRoNo(String roNo);
	
	/**
	 * Modify By WuCF 20151211
     * 修改订单的IS_RETREAT_ORDER2标示
     * @param jprRefund
     * @param JprRefundSet
     */
    public String updateJpoMemberOrderFlag(JprRefund jprRefund);
    
    
    /**
     * @Description SQL删除数据
     * @author houxyu
     * @date 2016-3-4
     * @param roNo
     */
    public void removeJprRefundBySql(final String roNo);
}

