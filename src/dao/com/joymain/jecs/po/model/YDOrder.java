package com.joymain.jecs.po.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="YD_ORDER"   
 */
public class YDOrder  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long moid;
	private String userCode;
	private String orderNo;
	private BigDecimal amount;
	private BigDecimal pvamt;
	private Date orderTime;
	private Date orderPayTime;
	//add by lihao 
	private Date operateTime;
	private Date createTime;
	private BigDecimal ydPV;
	private Set<YDOrderItem> ydOrderItem = new HashSet<YDOrderItem>();
	
	//新增属性
	private String teamCode;//团队标识
	private String orderTimeString;
	
	//新加字段
	private String userLevel;  //会员级别
	private BigDecimal totalRebate;  //总返利
	private BigDecimal ydRebate;  //云店返利
	private BigDecimal ecRebate; //返利
	
	
	
	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getOrderTimeString() {
		return orderTimeString;
	}

	public void setOrderTimeString(String orderTimeString) {
		this.orderTimeString = orderTimeString;
	}

	/**       
     *  @hibernate.id generator-class="sequence" 
     *  type="java.lang.Long" column="MO_ID"
     *	@hibernate.generator-param name="sequence" value="SEQ_PO"
     *         
     */
	public Long getMoid() {
		return moid;
	}
	
	public void setMoid(Long moid) {
		this.moid = moid;
	}
	
	/**       
     * @hibernate.property column="OPERATETIME"
     */
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	/**       
     * @hibernate.property column="USERCODE"
     */
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**       
     * @hibernate.property column="ORDERNO"
     */
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**       
     * @hibernate.property column="AMOUNT"
     */
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**       
     * @hibernate.property column="PVAMT"
     */
	public BigDecimal getPvamt() {
		return pvamt;
	}
	public void setPvamt(BigDecimal pvamt) {
		this.pvamt = pvamt;
	}
	/**       
     * @hibernate.property column="ORDERTIME"
     */
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	/**       
     * @hibernate.property column="ORDERPAYTIME"
     */
	public Date getOrderPayTime() {
		return orderPayTime;
	}
	public void setOrderPayTime(Date orderPayTime) {
		this.orderPayTime = orderPayTime;
	}
	/**       
     * @hibernate.property column="CREATETIME"
     */
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * *
	 * @hibernate.set lazy="false" inverse="true" cascade="all"
	 * @hibernate.collection-key column="MO_ID"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.po.model.YDOrderItem"
	 *
	 */
	public Set<YDOrderItem> getYdOrderItem() {
		return ydOrderItem;
	}

	public void setYdOrderItem(Set<YDOrderItem> ydOrderItem) {
		this.ydOrderItem = ydOrderItem;
	}
	/**       
     * @hibernate.property column="yd_pv"
     */
	public BigDecimal getYdPV() {
		return ydPV;
	}

	public void setYdPV(BigDecimal ydPV) {
		this.ydPV = ydPV;
	}
	/**       
     * @hibernate.property column="user_level"
     */
	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	/**       
     * @hibernate.property column="total_rebate"
     */
	public BigDecimal getTotalRebate() {
		return totalRebate;
	}

	public void setTotalRebate(BigDecimal totalRebate) {
		this.totalRebate = totalRebate;
	}
	/**       
     * @hibernate.property column="yd_rebate"
     */
	public BigDecimal getYdRebate() {
		return ydRebate;
	}

	public void setYdRebate(BigDecimal ydRebate) {
		this.ydRebate = ydRebate;
	}
	/**       
     * @hibernate.property column="ec_rebate"
     */
	public BigDecimal getEcRebate() {
		return ecRebate;
	}

	public void setEcRebate(BigDecimal ecRebate) {
		this.ecRebate = ecRebate;
	}
	
	
	
	  
}
