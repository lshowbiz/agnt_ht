
package com.joymain.jecs.pr.service;

import java.util.List;
import java.util.Set;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.pr.model.JprRefund;

@SuppressWarnings("unchecked")
public interface JprRefundManager extends Manager {
    /**
     * Retrieves all of the jprRefunds
     */
    public List getJprRefunds(JprRefund jprRefund);

    /**
     * Gets jprRefund's information based on roNo.
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
     * 入库
     * @param jprRefund
     */
    public void saveJprRefundInto(JprRefund jprRefund);


    /**
     * 退款
     * @param jprRefund
     */
    public void saveJprRefundRefund(JprRefund jprRefund,SysUser operaterCode);

    /**
     * 生成明细
     * @param jprRefund
     * @param JprRefundSet
     */
    public void saveJprRefund(JprRefund jprRefund,Set JprRefundSet);

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
	 * 获取已收款的取消审核订单
	 * @param crm
	 * @return
	 */
	public List getRefundGathering(CommonRecord crm);
	public List getRefundSum(CommonRecord crm) ;

	public List getJprReRefundStaticsCheckedCompanyByTree(String startDate, String endDate, String companyCode, String returnType, String treeIndex, String relationType) ;
	/**
	 * 根据退单记录,筛选出当前订单可以退的商品
	 * @param moid
	 * @return JprRefund
	 */
	public JprRefund getRefundByMoId(Long moid,String roNo);
	
	/**
	 * 退货单的接口准备数据
	 * @author gw 2014-11-28
	 * @param jprRefund
	 * @param jprRefundSet
	 * @return string
	 */
	public String pushJprRefund(JprRefund jprRefund, Set jprRefundSet) throws Exception;
	
	/**
	 * 调用sql函数 执行大订单结算降级黄砖会员，幸福会员
	 * @param moId 退货单主键 (退货单编号)
	 */
	public String getFunctionRp(String moId,Integer njtcType);
	
	/**
	 * 退货入库接口
	 * @author fx  2015-08-04
	 * @param jsonString
	 * @return rspEntity
	 */
	public RspEntity reSetJprRefundStatus(String jsonString);
	
	/**
	 * 根据退货单号查询退货入库状态
	 * @author fu 2015-09-10 
	 * @param roNo
	 * @return string 
	 */
	public String getIntoStatus(String roNo);
	
	/**
	 * Modify By WuCF 20151211
     * 修改订单的IS_RETREAT_ORDER2标示
     * @param jprRefund
     * @param JprRefundSet
     */
    public String updateJpoMemberOrderFlag(JprRefund jprRefund);
    
    /**
     * @Description 查询该订单下的退单
     * @author houxyu
     * @date 2016-3-11
     * @param memberOrderNo
     * @return
     */
    public List<JprRefund> getJprRefundForMemberOrder(String memberOrderNo);
}

