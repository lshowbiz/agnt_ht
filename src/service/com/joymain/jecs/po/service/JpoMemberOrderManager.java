
package com.joymain.jecs.po.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.joymain.jecs.bd.model.JbdSummaryList;
import com.joymain.jecs.fi.model.FiCoinLog;
import com.joymain.jecs.pm.model.JpmSalePromoter;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpoMemberOrderManager extends Manager {
	
	public List getChageableJpoMemberOrders(String userCode);
    /**
     * Retrieves all of the jpoMemberOrders
     */
    public List getJpoMemberOrders(JpoMemberOrder jpoMemberOrder);

    /**
     * Gets jpoMemberOrder's information based on moId.
     * @param moId the jpoMemberOrder's moId
     * @return jpoMemberOrder populated jpoMemberOrder object
     */
    public JpoMemberOrder getJpoMemberOrder(final String moId);
    
    public JpoMemberOrder getJpoMemberOrderByMemberOrderNo(String memberOrderNo);

    /**
     * Saves a jpoMemberOrder's information
     * @param jpoMemberOrder the object to be saved
     */
    public void saveJpoMemberOrder(JpoMemberOrder jpoMemberOrder);

    /**
     * Removes a jpoMemberOrder from the database by moId
     * @param moId the jpoMemberOrder's moId
     */
    public void removeJpoMemberOrder(final String moId);
	
	/**
	 * 订单总金额
	 * @param crm
	 * @return
	 */
	public Map getSumAmountByCrm(CommonRecord crm);
    //added for getJpoMemberOrdersByCrm
    public List getJpoMemberOrdersByCrm(CommonRecord crm, Pager pager);
    
    /**
     * 生态家订单金额
     * @param crm
     * @return
     */
    public Map getSumAmountSTJByCrm(CommonRecord crm);
    /**
     * 生态家订单查询
     * @param crm
     * @param pager
     * @return
     */
    public List getJpoMemberOrdersSTJByCrm(CommonRecord crm, Pager pager);
    
    //查出符合发货条件的订单
    public List getShipOrder(CommonRecord crm, Pager pager);

	public List getJpoMemberOrdersByCrmForRefund(CommonRecord crm, Pager pager);

    
	/**
	 * 更改审核日期
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
    public void changeJpoMemberOrderDate(JpoMemberOrder jpoMemberOrder,SysUser operatorSysUser,Date changeDate);


	/**
	 * 批量修改订单
	 * 
	 * @param poMemberOrder
	 * @param poMemberOrderSet
	 */
	public void editJpoMemberOrderBatch(List<JpoMemberOrder> jpoMemberOrders, List<Set> jpoMemberOrderSets, List<Set> jpoMemberOrderFeeSets);


	/**
	 * 修改订单
	 * 
	 * @param poMemberOrder
	 * @param poMemberOrderSet
	 */
	public void editJpoMemberOrder(JpoMemberOrder jpoMemberOrder, Set jpoMemberOrderSet, Set jpoMemberOrderFeeSet);

	
	/**
	 * JMS送分(重发)
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void resendJmsCoin(FiCoinLog fiCoinLog, SysUser operatorSysUser);
	
	/**
	 * JMS送分
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void sendJmsCoin(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser);
	
	/**
	 * JMS送分
	 * @param uniqueCode唯一码
	 * @param userCode用户编号
	 * @param userCode用户名称
	 * @param operatorSysUser操作者
	 */
	public void sendJmsCoinByType(String uniqueCode,String userCode,String userName, SysUser operatorSysUser);
	
	/**
	 * 自动生成订单并审核，不扣款
	 * @param json
	 * @throws Exception
	 */
	public void orderJSON(String json) throws Exception;


	/**
	 * 发展基金抵扣审核会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void checkJpoMemberOrderByJJ(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType) throws Exception;
	
	
	
	/**
	 * 积分抵扣审核会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void checkJpoMemberOrderByCoinAndBankbook(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType)throws Exception;
	
	/**
	 * 积分抵扣审核会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void checkJpoMemberOrderByCoin(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType) throws Exception;
	
	/**
	 * 审核会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @throws Exception 
	 */
	public void checkJpoMemberOrder(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser,String dataType) throws Exception;
	/**
	 * 修改订单发货状态
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void editJpoMemberOrderShipments(JpoMemberOrder jpoMemberOrder, SysUser operatorSysUser);
	/**
	 * 会员编号查找
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public List getJpoMemberOrdersByMiMember(JpoMemberOrder jpoMemberOrder);
	
	/**
	 * 查询首购单的审核时间
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
	 * 抢购时间：2010年5月11日-5月16日
	 * 剩下多少个
	 * @param orderProductMax
	 * @return
	 */
	public int getProductsLeave(String productNo);
	
	/**
	 * 抢购时间：2010年5月11日-5月16日
	 * @param productNo
	 * @return
	 */
	public boolean getIsOver(String productNo);
	
	/**
	 * 抢购时间：2012年4月21日-5月4日
	 * @param productNo
	 * @return
	 */
	public int getIsOver2(String productNo);
	
	/**
	 * 积分换购抢购
	 * @param productNo
	 * @return
	 */
	public int getIsOver3(String productNo);
	
	/**
	 * 时间段内获取商品订购量
	 * @param productNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getProductsSum(String productNo,String startTime,String endTime,String payBy);
	
	/**
	 * 获取一段时间内各个公司(如指定companyCode,则获取对应的公司)的已审核订单
	 * @param startDate
	 * @param endDate
	 * @param companyCode
	 * @return
	 */
	public List getJpoMemberOrderStaticsCheckedCompany(final String startDate, final String endDate, final String companyCode, final String productType, final String checkType);
	public List getJpoMemberOrderStaticsCheckedCompanyByTree(String startDate, String endDate, String companyCode, String checkType, String treeIndex,String relationType);
	/**
	 * 统计物理费
	 * @param crm
	 * @param pager
	 * @return 
	 * @author 罗婷
	 */
	
	public List getShippingfeeByCrm(CommonRecord crm, Pager pager);
	public Map getTotalShippingfeeByCrm(CommonRecord crm);
	public int getPreferentialOrder(JpoMemberOrder jpoMemberOrder);
	//表彰荣誉称号
	public Map getJpoMemberPraiseMeetingUserCode();
    //判断会员是否已经购买过事业锦囊
	public boolean getJpoMemberMark(String papernumber,String productNo,String orderType);
	/**
	 * 根据订单类型,用户团队,国别 确定此订单是否参与促销
	 * @param sp
	 * @param order
	 * @return true or false;
	 */
	public boolean isOrderSales(JpmSalePromoter sp,JpoMemberOrder order) throws Exception;
	
	public void modifyOrderStatusByMoId(Map<String, String> map);
	
	/**
	 * 查找所有支付完成，审核完成，已发货,未推送的订单
	 * @return
	 */
	public List getNotSendOrders();
	public void updateOrderSended(JpoMemberOrder jpo);
	
	/**
     * 根据订单号查询订单----由暂不发货转到正常发货的接口时候用（为表独立性单独建方法）
     * @author fx 2015-8-10
     * @param memberOrderNo
     * @return JpoMemberOrder
     */
	public JpoMemberOrder getJpoMemberOrderByInterface(String memberOrderNo);

	/**
	 * @Description 保存数据
	 * @author houxyu
	 * @date 2016-3-14
	 * @param jpoMemberOrder
	 * @param jbdSummaryList
	 */
	public void saveJpoMemberOrder(JpoMemberOrder jpoMemberOrder,JbdSummaryList jbdSummaryList);
	
	
	/**
	 * 判断订单是否是挂起状态
	 * fu 2016-03-16
	 * @param memberOrderNo
	 * @return
	 */
	public boolean getOrderReturnableStatus(String memberOrderNo);
	
	/**
	 * 订单的自助换货--允许自助换货
	 * fu 2016-03-28
	 * @param memberOrderNo
	 * @return
	 */
	public void orderSelfHelpExchangeYes(String memberOrderNo);
	
	/**
	 * 订单的自助换货--禁止自助换货
	 * fu 2016-03-28
	 * @param memberOrderNo
	 * @return
	 */
	public void orderSelfHelpExchangeNo(String memberOrderNo);
	
}

