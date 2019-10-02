
package com.joymain.jecs.po.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberOrderDao extends Dao {

    /**
     * Retrieves all of the jpoMemberOrders
     */
    public List getJpoMemberOrders(JpoMemberOrder jpoMemberOrder);

    /**
     * Gets jpoMemberOrder's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param moId the jpoMemberOrder's moId
     * @return jpoMemberOrder populated jpoMemberOrder object
     */
    public JpoMemberOrder getJpoMemberOrder(final Long moId);

    /**
     * Saves a jpoMemberOrder's information
     * @param jpoMemberOrder the object to be saved
     */    
    public void saveJpoMemberOrder(JpoMemberOrder jpoMemberOrder);
    
    public JpoMemberOrder getJpoMemberOrderByMemberOrderNo(String memberOrderNo);

    /**
     * Removes a jpoMemberOrder from the database by moId
     * @param moId the jpoMemberOrder's moId
     */
    public void removeJpoMemberOrder(final Long moId);
    //added for getJpoMemberOrdersByCrm
    public List getJpoMemberOrdersByCrm(CommonRecord crm, Pager pager);
	
	/**
	 * 订单总金额
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm);
	public Map getSumAmountSTJByCrm(CommonRecord crm);

	public List getJpoMemberOrdersByCrmForRefund(CommonRecord crm, Pager pager);
    
    /**
     * 获取订单数量
     * @param orderType
     * @param userCode
     * @param status
     * @return
     */
    public List getJpoMemberOrdersByTCS(String orderType, String userCode, String status);

	/**
	 * 会员编号查找
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public List getJpoMemberOrdersByMiMember(JpoMemberOrder jpoMemberOrder);
	
	/**
	 * 时间段内获取商品订购量
	 * @param productNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getProductsSum(String productNo,String startTime,String endTime,String payBy);

	/**
	 * 查询首购单的审核时间
	 * 
	 * @param memberNo
	 * @return
	 */
	public String getMemberFirstOrderStatusTime(String memberNo);
	
	/**
	 * 根据条件统计商品销售量
	 * @param crm
	 * @return
	 */
	public List getStatisticProductSale(CommonRecord crm);
	

	/**
	 * 按条件获取订单总金额
	 * 
	 * @param crm
	 * @return
	 */
	public BigDecimal getJpoMemberOrderStatics(CommonRecord crm);

	/**
	 * 获取一段时间内各个公司(如指定companyCode,则获取对应的公司)的已审核订单
	 * 
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @return
	 */
	public List getJpoMemberOrderStaticsCheckedCompany(final String startDate,
			final String endDate, final String companyCode, final String productType, final String checkType);

	public List getChageableJpoMemberOrders(String userCode);

	public List getJpoMemberOrderStaticsCheckedCompanyByTree(final String startDate,
			final String endDate, final String companyCode,
			final String checkType,String treeIndex,String relationType);
	public List getShippingfeeByCrm(CommonRecord crm, Pager pager);
	public Map getTotalShippingfeeByCrm(CommonRecord crm) ;
	
	//查出符合发货条件的订单
    public List getShipOrder(CommonRecord crm, Pager pager);
    public Map getJpoMemberPraiseMeetingUserCode();
    //判断会员是否已经购买过事业锦囊
	public List getJpoMemberMark(String papernumber,String productNo,String orderType);
	
	/**
     * 根据订单号修改订单配置状态
     * @param map
     */
    public void modifyOrderStatusByMoId(Map<String,String> map);
    
    /**
     * STJ查询统计
     * @param crm
     * @param pager
     * @return
     */
    public List getJpoMemberOrdersSTJByCrm(CommonRecord crm, Pager pager);

    
    /**
     * 根据订单号查询订单----由暂不发货转到正常发货的接口时候用（为表独立性单独建方法）
     * @author gw 2014-12-04
     * @param memberOrderNo
     * @return JpoMemberOrder
     */
	public JpoMemberOrder getJpoMemberOrderByInterface(String memberOrderNo);
	
	public List getNotSendOrders();
	
	/**
	 * 修改订单推送状态为已推送
	 * @param jpoMemberOrder
	 */
	public void updateOrderSended(JpoMemberOrder jpoMemberOrder);

	/**
	 * 判断订单是否是挂起状态
	 * fu 2016-03-16
	 * @param memberOrderNo
	 * @return
	 */
	public boolean getOrderReturnableStatus(String memberOrderNo);

	/**
	 * 订单的自助换货
	 * fu 2016-03-28
	 * @param memberOrderNo
	 * @param yesOrNo Y表示允许自助换货;空或N表示禁止自助换货
	 * @return
	 */
	public void orderSelfHelpExchange(String memberOrderNo, String yesOrNo);
	
	
}

