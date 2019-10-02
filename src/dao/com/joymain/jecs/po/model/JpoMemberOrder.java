package com.joymain.jecs.po.model;

// Generated 2009-9-22 10:18:25 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.joymain.jecs.pr.model.JprRefund;
import com.joymain.jecs.sys.model.SysUser;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="JPO_MEMBER_ORDER"
 * 
 */

@SuppressWarnings({"serial","unchecked","unused"})
public class JpoMemberOrder extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

	// Fields

	private Long moId;
	private String companyCode;
	private String countryCode;
	private String memberOrderNo;
	private String orderType;// 订单类型订单属性 ORDER_TYPE 1.首购单 2.升级单 3、续约单,5 辅销品 4、重消
								// 6、店铺首购 8、店铺返单 9、店铺重消 11 二级店铺首购单 12二级店铺升级单
								// 14二级店铺重消单 15 AUTOSHIP,21梦想馆首单,24 梦想馆重消单,30
								// 公益基金
	private SysUser sysUser;
	private String userSpCode;
	private BigDecimal amount = new BigDecimal(0);
	private BigDecimal amount2 = new BigDecimal(0);
	private BigDecimal pvAmt = new BigDecimal(0);
	private BigDecimal consumerAmount;
	private String payMode;
	private String orderUserCode;
	private Date orderTime;
	private String status;
	private Date checkTime;
	private String checkUserCode;
	private Date checkDate;
	private String checkDateUserCode;
	private String remark;
	private String pickup;
	private Date submitTime;
	private String submitUserCode;
	private String submitStatus;
	private String locked;
	private String firstName;
	private String lastName;
	private String province;
	private String city;
	private String district;
	private String town;
	private String address;
	private String postalcode;
	private String phone;
	private String email;
	private String mobiletele;
	private String isPay;
	private String isSpecial;
	private Set jpoMemberOrderList = new HashSet(0);
	private Set jpoMemberOrderFee = new HashSet(0);
	private Set jprRefund = new HashSet(0);
	private String isReturn;
	private String productType;
	private Long motId;
	private String companyPay;
	private String payCode;
	private String shippingPay;
	private Date shippingDay;
	private String building;
	private String isFree;
	private String resendSpNo;
	private String shippingCompany;
	private String shippingSpecial;
	private String payByCoin;// 使用积分标识
	private BigDecimal discountAmount = new BigDecimal(0);// 积分使用数
	private BigDecimal discountPvAmt = new BigDecimal(0);
	private String transactionNumber;
	private String payByJJ;
	private String isShipping;// 是否发货1表示审核订单后即刻发货 0表示不发货
	private String isRetreatOrder = "0";// 退单标识
	private String isShipments = "0";// 暂不发货标识
	private String teamCode;// 团队标识
	// 商品总数量
	private Integer productAmount;

	// 已配置商品数量
	private Integer productConfigAmount;

	// 配置状态
	private String configStatus;
	private String isMobile = "0";// 是否为手机客户端购买，1为是，0为否

	// 订单系统状态
	private Integer statusSysMo;

	// 商户号
	private String saleNo;

	// 是否已推送
	private Integer isSended;

	// Modify By WuCF 20150410 生态家套餐订购
	private String productFlag;

	// 生态家价格标记(5或20)
	private Integer stjPrice;
	// 生态家套餐组合后锁定标记 1为锁定 VARCHAR2(2);
	private String stjLock;
	// 生态家套餐分组标记 VARCHAR2(100)
	private String stjGroup;

	//是否全额支付
	private Integer isFullPay;
	
	private String linkUserName;
	private String recommandName;
	
	// modify gw 2015-02-06 inter_ok_delivery
	// modify gw 添加部分发货状态
	// 接口确认发货（0或者空表示未发货，1代表部分发货，2代表已经发货,3代表已确认收货）
	private String interOkDelivery;
	
	private String exchangeFlag;// 是否换货，1为已换货，0为换货取消
	
	private String returnableStatus;//退单状态   1：挂起，0正常
	
	private String locked2;//订单结算时  更新的标志列
	
	private String selfHelpExchange;// modify by fu 2016-03-28 自助换货:Y允许自助换货，N或空表示不允许自助换货

	 //add by lihao 2017-01-09   添加产品积分支付相关的字段 
    private String payByProduct;				//产品积分支付
    private BigDecimal productPointAmount;		//产品积分总金额
    private String payByCp;				//是否代金券支付
    private BigDecimal cpValue;			//实际使用代金券金额
    
	
	/**
	 * * @hibernate.property column="LOCKED2" length="1"
	 * 
	 */
	public String getLocked2() {
		return locked2;
	}

	public void setLocked2(String locked2) {
		this.locked2 = locked2;
	}
	
	/**
	 * * @hibernate.property column="RETURNABLE_STATUS" length="1"
	 * 
	 */
	public String getReturnableStatus() {
		return returnableStatus;
	}

	public void setReturnableStatus(String returnableStatus) {
		this.returnableStatus = returnableStatus;
	}
	/**
	 * * @hibernate.property column="SALE_NO" length="20"
	 * 
	 */
	public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	/**
	 * * @hibernate.property column="ISSENDED" length="2"
	 * 
	 */
	public Integer getIsSended() {
		return isSended;
	}

	public void setIsSended(Integer isSended) {
		this.isSended = isSended;
	}

	/**
	 * * @hibernate.property column="STATUS_SYS_MO" length="2"
	 * 
	 */
	public Integer getStatusSysMo() {
		return statusSysMo;
	}

	public void setStatusSysMo(Integer statusSysMo) {
		this.statusSysMo = statusSysMo;
	}

	/**
	 * * @hibernate.property column="ISFULL_PAY" length="2"
	 * 
	 */
	public Integer getIsFullPay() {
		return isFullPay;
	}

	public void setIsFullPay(Integer isFullPay) {
		this.isFullPay = isFullPay;
	}
	
	/**
	 * * @hibernate.property column="config_status" length="2"
	 * 
	 */
	public String getConfigStatus() {
		return configStatus;
	}

	public void setConfigStatus(String configStatus) {
		this.configStatus = configStatus;
	}

	public Integer getProductConfigAmount() {
		return productConfigAmount;
	}

	public void setProductConfigAmount(Integer productConfigAmount) {
		this.productConfigAmount = productConfigAmount;
	}

	public Integer getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}

	/**
	 * * @hibernate.property column="is_shipping" length="2"
	 * 
	 */
	public String getIsShipping() {
		return isShipping;
	}

	public void setIsShipping(String isShipping) {
		this.isShipping = isShipping;
	}

	private BigDecimal jjAmount;

	// Constructors

	/** default constructor */
	public JpoMemberOrder() {
	}

	/** minimal constructor */
	public JpoMemberOrder(String userCode) {

	}

	/** full constructor */
	public JpoMemberOrder(String companyCode, String countryCode, String memberOrderNo, String orderType, String userCode, String userSpCode, BigDecimal amount, BigDecimal pvAmt, BigDecimal consumerAmount, String payMode, String orderUserCode, Date orderTime, String status,
			Date checkTime, String checkUserCode, Date checkDate, String remark, String pickup, Date submitTime, String submitUserCode, String submitStatus, String locked, String firstName, String lastName, String province, String city, String address, String postalcode,
			String phone, String email, String mobiletele, Integer productAmount, String interOkDelivery,String selfHelpExchange) {
		this.companyCode = companyCode;
		this.countryCode = countryCode;
		this.memberOrderNo = memberOrderNo;
		this.orderType = orderType;
		this.userSpCode = userSpCode;
		this.amount = amount;
		this.pvAmt = pvAmt;
		this.consumerAmount = consumerAmount;
		this.payMode = payMode;
		this.orderUserCode = orderUserCode;
		this.orderTime = orderTime;
		this.status = status;
		this.checkTime = checkTime;
		this.checkUserCode = checkUserCode;
		this.checkDate = checkDate;
		this.remark = remark;
		this.pickup = pickup;
		this.submitTime = submitTime;
		this.submitUserCode = submitUserCode;
		this.submitStatus = submitStatus;
		this.locked = locked;
		this.firstName = firstName;
		this.lastName = lastName;
		this.province = province;
		this.city = city;
		this.address = address;
		this.postalcode = postalcode;
		this.phone = phone;
		this.email = email;
		this.mobiletele = mobiletele;
		this.productAmount = productAmount;
		this.interOkDelivery = interOkDelivery;
		this.selfHelpExchange = selfHelpExchange;

	}

	// Property accessors
	/**
	 * * @hibernate.id generator-class="sequence" type="java.lang.Long"
	 * column="MO_ID"
	 * 
	 * @hibernate.generator-param name="sequence" value="SEQ_PO"
	 * 
	 */

	public Long getMoId() {
		return this.moId;
	}

	public void setMoId(Long moId) {
		this.moId = moId;
	}

	/**
	 * * @hibernate.property column="COMPANY_CODE" length="2" not-null="true"
	 * 
	 */

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * * @hibernate.property column="COUNTRY_CODE" length="2" not-null="true"
	 * 
	 */

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * * @hibernate.property column="MEMBER_ORDER_NO" length="20"
	 * not-null="true"
	 * 
	 */

	public String getMemberOrderNo() {
		return this.memberOrderNo;
	}

	public void setMemberOrderNo(String memberOrderNo) {
		this.memberOrderNo = memberOrderNo;
	}

	/**
	 * * @hibernate.property column="ORDER_TYPE" length="2" not-null="true"
	 * 
	 */

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * *
	 * 
	 * @hibernate.many-to-one not-null="true" lazy="false"
	 * @hibernate.column name="USER_CODE"
	 * 
	 */
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	/**
	 * * @hibernate.property column="USER_SP_CODE" length="20"
	 * 
	 */

	public String getUserSpCode() {
		return this.userSpCode;
	}

	public void setUserSpCode(String userSpCode) {
		this.userSpCode = userSpCode;
	}

	/**
	 * * @hibernate.property column="AMOUNT" length="18"
	 * 
	 */

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * * @hibernate.property column="AMOUNT2" length="18"
	 * 
	 */

	public BigDecimal getAmount2() {
		return this.amount2;
	}

	public void setAmount2(BigDecimal amount2) {
		this.amount2 = amount;
	}

	/**
	 * * @hibernate.property column="PV_AMT" length="18"
	 * 
	 */

	public BigDecimal getPvAmt() {
		return this.pvAmt;
	}

	public void setPvAmt(BigDecimal pvAmt) {
		this.pvAmt = pvAmt;
	}

	/**
	 * * @hibernate.property column="CONSUMER_AMOUNT" length="18"
	 * 
	 */

	public BigDecimal getConsumerAmount() {
		return this.consumerAmount;
	}

	public void setConsumerAmount(BigDecimal consumerAmount) {
		this.consumerAmount = consumerAmount;
	}

	/**
	 * * @hibernate.property column="PAY_MODE" length="1"
	 * 
	 */

	public String getPayMode() {
		return this.payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	/**
	 * * @hibernate.property column="ORDER_USER_CODE" length="20"
	 * 
	 */

	public String getOrderUserCode() {
		return this.orderUserCode;
	}

	public void setOrderUserCode(String orderUserCode) {
		this.orderUserCode = orderUserCode;
	}

	/**
	 * * @hibernate.property column="ORDER_TIME" length="7"
	 * 
	 */

	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * * @hibernate.property column="STATUS" length="1"
	 * 
	 */

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 *
	 * * @hibernate.property column="IS_PAY" length="1"
	 * 
	 */

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	/**
	 * * @hibernate.property column="CHECK_TIME" length="7"
	 * 
	 */

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	/**
	 * * @hibernate.property column="CHECK_USER_CODE" length="20"
	 * 
	 */

	public String getCheckUserCode() {
		return this.checkUserCode;
	}

	public void setCheckUserCode(String checkUserCode) {
		this.checkUserCode = checkUserCode;
	}

	/**
	 * * @hibernate.property column="CHECK_DATE_USER_CODE" length="20"
	 * 
	 */
	public String getCheckDateUserCode() {
		return checkDateUserCode;
	}

	public void setCheckDateUserCode(String checkDateUserCode) {
		this.checkDateUserCode = checkDateUserCode;
	}

	/**
	 * * @hibernate.property column="CHECK_DATE" length="7"
	 * 
	 */

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * * @hibernate.property column="REMARK" length="4000"
	 * 
	 */

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * * @hibernate.property column="PICKUP" length="1"
	 * 
	 */

	public String getPickup() {
		return this.pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	/**
	 * * @hibernate.property column="SUBMIT_TIME" length="7"
	 * 
	 */

	public Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * * @hibernate.property column="SHIPPING_DAY" length="7"
	 * 
	 */
	public Date getShippingDay() {
		return shippingDay;
	}

	public void setShippingDay(Date shippingDay) {
		this.shippingDay = shippingDay;
	}

	/**
	 * * @hibernate.property column="SUBMIT_USER_CODE" length="20"
	 * 
	 */

	public String getSubmitUserCode() {
		return this.submitUserCode;
	}

	public void setSubmitUserCode(String submitUserCode) {
		this.submitUserCode = submitUserCode;
	}

	/**
	 * * @hibernate.property column="SUBMIT_STATUS" length="1"
	 * 
	 */

	public String getSubmitStatus() {
		return this.submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	/**
	 * * @hibernate.property column="LOCKED" length="1"
	 * 
	 */

	public String getLocked() {
		return this.locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	/**
	 * * @hibernate.property column="FIRST_NAME" length="100"
	 * 
	 */

	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.firstName"
	 * @spring.validator-var name="maxlength" value="100"
	 * @spring.validator-args arg1value="100"
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * * @hibernate.property column="LAST_NAME" length="100"
	 * 
	 */

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.lastName"
	 * @spring.validator-var name="maxlength" value="100"
	 * @spring.validator-args arg1value="100"
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * * @hibernate.property column="PROVINCE" length="20"
	 * 
	 */

	public String getProvince() {
		return this.province;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.province"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * * @hibernate.property column="CITY" length="20"
	 * 
	 */

	public String getCity() {
		return this.city;
	}

	/**
	 * @spring.validator type="maxlength"
	 * @spring.validator-args arg0resource="shipping.city"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * * @hibernate.property column="ADDRESS" length="500"
	 * 
	 */

	public String getAddress() {
		return this.address;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.address"
	 * @spring.validator-var name="maxlength" value="500"
	 * @spring.validator-args arg1value="500"
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * * @hibernate.property column="POSTALCODE" length="10"
	 * 
	 */

	public String getPostalcode() {
		return this.postalcode;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.postalcode"
	 * @spring.validator-var name="maxlength" value="10"
	 * @spring.validator-args arg1value="10"
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	/**
	 * * @hibernate.property column="PHONE" length="30"
	 * 
	 */

	public String getPhone() {
		return this.phone;
	}

	/**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="miMember.phone"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * * @hibernate.property column="EMAIL" length="30"
	 * 
	 */

	public String getEmail() {
		return this.email;
	}

	/**
	 * @spring.validator type="email,maxlength"
	 * @spring.validator-var name="maxlength" value="100"
	 * @spring.validator-args arg1value="100"
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * * @hibernate.property column="MOBILETELE" length="20"
	 * 
	 */

	public String getMobiletele() {
		return this.mobiletele;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setMobiletele(String mobiletele) {
		this.mobiletele = mobiletele;
	}

	/**
	 * *
	 * 
	 * @hibernate.set lazy="false" inverse="true" cascade="all"
	 * @hibernate.collection-key column="MO_ID"
	 * @hibernate.collection-one-to-many 
	 *                                   class="com.joymain.jecs.po.model.JpoMemberOrderList"
	 * 
	 * 
	 */
	public Set getJpoMemberOrderList() {
		return jpoMemberOrderList;
	}

	public void setJpoMemberOrderList(Set jpoMemberOrderList) {
		this.jpoMemberOrderList = jpoMemberOrderList;
	}

	/**
	 * *
	 * 
	 * @hibernate.set lazy="false" inverse="true" cascade="all"
	 * @hibernate.collection-key column="MO_ID"
	 * @hibernate.collection-one-to-many 
	 *                                   class="com.joymain.jecs.po.model.JpoMemberOrderFee"
	 * 
	 */
	public Set getJpoMemberOrderFee() {
		return jpoMemberOrderFee;
	}

	public void setJpoMemberOrderFee(Set jpoMemberOrderFee) {
		this.jpoMemberOrderFee = jpoMemberOrderFee;
	}

	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all"
	 * @hibernate.collection-key column="MO_ID"
	 * @hibernate.collection-one-to-many 
	 *                                   class="com.joymain.jecs.pr.model.JprRefund"
	 * 
	 * 
	 */
	public Set getJprRefund() {
		return jprRefund;
	}

	public void setJprRefund(Set jprRefund) {
		this.jprRefund = jprRefund;
	}

	/**
	 * * @hibernate.property column="DISTRICT" length="20"
	 * 
	 */
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * * @hibernate.property column="TOWN" length="20"
	 * 
	 */
	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * * @hibernate.property column="IS_SPECIAL" length="20"
	 * 
	 */
	public String getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}

	/**
	 * * @hibernate.property column="PRODUCT_TYPE" length="20"
	 * 
	 */
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * * @hibernate.property column="COMPANY_PAY" length="2"
	 * 
	 */
	public String getCompanyPay() {
		return companyPay;
	}

	public void setCompanyPay(String companyPay) {
		this.companyPay = companyPay;
	}

	/**
	 * * @hibernate.property column="MOT_ID" length="10"
	 * 
	 */
	public Long getMotId() {
		return motId;
	}

	public void setMotId(Long motId) {
		this.motId = motId;
	}

	/**
	 * * @hibernate.property column="PAY_CODE" length="100"
	 * 
	 */
	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	/**
	 * * @hibernate.property column="SHIPPING_PAY" length="1"
	 * 
	 */
	public String getShippingPay() {
		return shippingPay;
	}

	public void setShippingPay(String shippingPay) {
		this.shippingPay = shippingPay;
	}

	/**
	 * * @hibernate.property column="BUILDING" length="500"
	 * 
	 */
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getIsReturn() {
		Iterator ite = getJprRefund().iterator();
		while (ite.hasNext()) {
			JprRefund jprRefund = (JprRefund) ite.next();
			if ("2".equals(jprRefund.getRefundStatus())) {
				return "1";
			}
		}
		return "0";
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}
	
	/**
	 * * @hibernate.property column="INTER_OK_DELIVERY" length="20"
	 * 
	 */
	public String getInterOkDelivery() {
		return interOkDelivery;
	}

	public void setInterOkDelivery(String interOkDelivery) {
		this.interOkDelivery = interOkDelivery;
	}


	/**
	 * * @hibernate.property column="SELF_HELP_EXCHANGE" length="20"
	 * 
	 */
	public String getSelfHelpExchange() {
		return selfHelpExchange;
	}

	public void setSelfHelpExchange(String selfHelpExchange) {
		this.selfHelpExchange = selfHelpExchange;
	}
	
	
	/**
	 * * @hibernate.property column="PAY_BY_PRODUCT" length="2"
	 * 
	 */
	public String getPayByProduct() {
		return payByProduct;
	}

	public void setPayByProduct(String payByProduct) {
		this.payByProduct = payByProduct;
	}

	/**
	 * * @hibernate.property column="PRODUCT_AMT" length="20"
	 * 
	 */
	public BigDecimal getProductPointAmount() {
		return productPointAmount;
	}

	public void setProductPointAmount(BigDecimal productPointAmount) {
		this.productPointAmount = productPointAmount;
	}
	/**
	 * * @hibernate.property column="PAY_BY_CP" length="20"
	 * 
	 */
	public String getPayByCp() {
		return payByCp;
	}

	public void setPayByCp(String payByCp) {
		this.payByCp = payByCp;
	}

	/**
	 * * @hibernate.property column="CP_VALUE" length="20"
	 * 
	 */
	public BigDecimal getCpValue() {
		return cpValue;
	}

	public void setCpValue(BigDecimal cpValue) {
		this.cpValue = cpValue;
	}
	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");
		buffer.append("countryCode").append("='").append(getCountryCode()).append("' ");
		buffer.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("' ");
		buffer.append("orderType").append("='").append(getOrderType()).append("' ");
		buffer.append("userSpCode").append("='").append(getUserSpCode()).append("' ");
		buffer.append("amount").append("='").append(getAmount()).append("' ");
		buffer.append("pvAmt").append("='").append(getPvAmt()).append("' ");
		buffer.append("consumerAmount").append("='").append(getConsumerAmount()).append("' ");
		buffer.append("payMode").append("='").append(getPayMode()).append("' ");
		buffer.append("orderUserCode").append("='").append(getOrderUserCode()).append("' ");
		buffer.append("orderTime").append("='").append(getOrderTime()).append("' ");
		buffer.append("status").append("='").append(getStatus()).append("' ");
		buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");
		buffer.append("checkUserCode").append("='").append(getCheckUserCode()).append("' ");
		buffer.append("checkDate").append("='").append(getCheckDate()).append("' ");
		buffer.append("remark").append("='").append(getRemark()).append("' ");
		buffer.append("pickup").append("='").append(getPickup()).append("' ");
		buffer.append("submitTime").append("='").append(getSubmitTime()).append("' ");
		buffer.append("submitUserCode").append("='").append(getSubmitUserCode()).append("' ");
		buffer.append("submitStatus").append("='").append(getSubmitStatus()).append("' ");
		buffer.append("locked").append("='").append(getLocked()).append("' ");
		buffer.append("firstName").append("='").append(getFirstName()).append("' ");
		buffer.append("lastName").append("='").append(getLastName()).append("' ");
		buffer.append("province").append("='").append(getProvince()).append("' ");
		buffer.append("city").append("='").append(getCity()).append("' ");
		buffer.append("address").append("='").append(getAddress()).append("' ");
		buffer.append("postalcode").append("='").append(getPostalcode()).append("' ");
		buffer.append("phone").append("='").append(getPhone()).append("' ");
		buffer.append("email").append("='").append(getEmail()).append("' ");
		buffer.append("productType").append("='").append(getProductType()).append("' ");
		buffer.append("mobiletele").append("='").append(getMobiletele()).append("' ");
		buffer.append("interOkDelivery").append("='").append(getInterOkDelivery()).append("' ");
		buffer.append("selfHelpExchange").append("='").append(getSelfHelpExchange()).append("' ");

		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof JpoMemberOrder))
			return false;
		JpoMemberOrder castOther = (JpoMemberOrder) other;

		return ((this.getMoId() == castOther.getMoId()) || (this.getMoId() != null && castOther.getMoId() != null && this.getMoId().equals(castOther.getMoId())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this.getCompanyCode() != null && castOther.getCompanyCode() != null && this.getCompanyCode().equals(castOther.getCompanyCode())))
				&& ((this.getCountryCode() == castOther.getCountryCode()) || (this.getCountryCode() != null && castOther.getCountryCode() != null && this.getCountryCode().equals(castOther.getCountryCode())))
				&& ((this.getMemberOrderNo() == castOther.getMemberOrderNo()) || (this.getMemberOrderNo() != null && castOther.getMemberOrderNo() != null && this.getMemberOrderNo().equals(castOther.getMemberOrderNo())))
				&& ((this.getOrderType() == castOther.getOrderType()) || (this.getOrderType() != null && castOther.getOrderType() != null && this.getOrderType().equals(castOther.getOrderType())))
				&& ((this.getUserSpCode() == castOther.getUserSpCode()) || (this.getUserSpCode() != null && castOther.getUserSpCode() != null && this.getUserSpCode().equals(castOther.getUserSpCode())))
				&& ((this.getAmount() == castOther.getAmount()) || (this.getAmount() != null && castOther.getAmount() != null && this.getAmount().equals(castOther.getAmount())))
				&& ((this.getPvAmt() == castOther.getPvAmt()) || (this.getPvAmt() != null && castOther.getPvAmt() != null && this.getPvAmt().equals(castOther.getPvAmt())))
				&& ((this.getConsumerAmount() == castOther.getConsumerAmount()) || (this.getConsumerAmount() != null && castOther.getConsumerAmount() != null && this.getConsumerAmount().equals(castOther.getConsumerAmount())))
				&& ((this.getPayMode() == castOther.getPayMode()) || (this.getPayMode() != null && castOther.getPayMode() != null && this.getPayMode().equals(castOther.getPayMode())))
				&& ((this.getOrderUserCode() == castOther.getOrderUserCode()) || (this.getOrderUserCode() != null && castOther.getOrderUserCode() != null && this.getOrderUserCode().equals(castOther.getOrderUserCode())))
				&& ((this.getOrderTime() == castOther.getOrderTime()) || (this.getOrderTime() != null && castOther.getOrderTime() != null && this.getOrderTime().equals(castOther.getOrderTime())))
				&& ((this.getStatus() == castOther.getStatus()) || (this.getStatus() != null && castOther.getStatus() != null && this.getStatus().equals(castOther.getStatus())))
				&& ((this.getCheckTime() == castOther.getCheckTime()) || (this.getCheckTime() != null && castOther.getCheckTime() != null && this.getCheckTime().equals(castOther.getCheckTime())))
				&& ((this.getCheckUserCode() == castOther.getCheckUserCode()) || (this.getCheckUserCode() != null && castOther.getCheckUserCode() != null && this.getCheckUserCode().equals(castOther.getCheckUserCode())))
				&& ((this.getCheckDate() == castOther.getCheckDate()) || (this.getCheckDate() != null && castOther.getCheckDate() != null && this.getCheckDate().equals(castOther.getCheckDate())))
				&& ((this.getRemark() == castOther.getRemark()) || (this.getRemark() != null && castOther.getRemark() != null && this.getRemark().equals(castOther.getRemark())))
				&& ((this.getPickup() == castOther.getPickup()) || (this.getPickup() != null && castOther.getPickup() != null && this.getPickup().equals(castOther.getPickup())))
				&& ((this.getSubmitTime() == castOther.getSubmitTime()) || (this.getSubmitTime() != null && castOther.getSubmitTime() != null && this.getSubmitTime().equals(castOther.getSubmitTime())))
				&& ((this.getSubmitUserCode() == castOther.getSubmitUserCode()) || (this.getSubmitUserCode() != null && castOther.getSubmitUserCode() != null && this.getSubmitUserCode().equals(castOther.getSubmitUserCode())))
				&& ((this.getSubmitStatus() == castOther.getSubmitStatus()) || (this.getSubmitStatus() != null && castOther.getSubmitStatus() != null && this.getSubmitStatus().equals(castOther.getSubmitStatus())))
				&& ((this.getLocked() == castOther.getLocked()) || (this.getLocked() != null && castOther.getLocked() != null && this.getLocked().equals(castOther.getLocked())))
				&& ((this.getFirstName() == castOther.getFirstName()) || (this.getFirstName() != null && castOther.getFirstName() != null && this.getFirstName().equals(castOther.getFirstName())))
				&& ((this.getLastName() == castOther.getLastName()) || (this.getLastName() != null && castOther.getLastName() != null && this.getLastName().equals(castOther.getLastName())))
				&& ((this.getProvince() == castOther.getProvince()) || (this.getProvince() != null && castOther.getProvince() != null && this.getProvince().equals(castOther.getProvince())))
				&& ((this.getCity() == castOther.getCity()) || (this.getCity() != null && castOther.getCity() != null && this.getCity().equals(castOther.getCity())))
				&& ((this.getAddress() == castOther.getAddress()) || (this.getAddress() != null && castOther.getAddress() != null && this.getAddress().equals(castOther.getAddress())))
				&& ((this.getPostalcode() == castOther.getPostalcode()) || (this.getPostalcode() != null && castOther.getPostalcode() != null && this.getPostalcode().equals(castOther.getPostalcode())))
				&& ((this.getPhone() == castOther.getPhone()) || (this.getPhone() != null && castOther.getPhone() != null && this.getPhone().equals(castOther.getPhone())))
				&& ((this.getEmail() == castOther.getEmail()) || (this.getEmail() != null && castOther.getEmail() != null && this.getEmail().equals(castOther.getEmail())))
				&& ((this.getProductType() == castOther.getProductType()) || (this.getProductType() != null && castOther.getProductType() != null && this.getProductType().equals(castOther.getProductType())))
				&& ((this.getMobiletele() == castOther.getMobiletele()) || (this.getMobiletele() != null && castOther.getMobiletele() != null && this.getMobiletele().equals(castOther.getMobiletele())))
		        && ((this.getInterOkDelivery() == castOther.getInterOkDelivery()) || (this.getInterOkDelivery() != null && castOther.getInterOkDelivery() != null && this.getInterOkDelivery().equals(castOther.getInterOkDelivery())))
                && ((this.getSelfHelpExchange() == castOther.getSelfHelpExchange()) || (this.getSelfHelpExchange() != null && castOther.getSelfHelpExchange() != null && this.getSelfHelpExchange().equals(castOther.getSelfHelpExchange())));

	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMoId() == null ? 0 : this.getMoId().hashCode());
		result = 37 * result + (getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode());
		result = 37 * result + (getCountryCode() == null ? 0 : this.getCountryCode().hashCode());
		result = 37 * result + (getMemberOrderNo() == null ? 0 : this.getMemberOrderNo().hashCode());
		result = 37 * result + (getOrderType() == null ? 0 : this.getOrderType().hashCode());
		result = 37 * result + (getUserSpCode() == null ? 0 : this.getUserSpCode().hashCode());
		result = 37 * result + (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37 * result + (getPvAmt() == null ? 0 : this.getPvAmt().hashCode());
		result = 37 * result + (getConsumerAmount() == null ? 0 : this.getConsumerAmount().hashCode());
		result = 37 * result + (getPayMode() == null ? 0 : this.getPayMode().hashCode());
		result = 37 * result + (getOrderUserCode() == null ? 0 : this.getOrderUserCode().hashCode());
		result = 37 * result + (getOrderTime() == null ? 0 : this.getOrderTime().hashCode());
		result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result + (getCheckTime() == null ? 0 : this.getCheckTime().hashCode());
		result = 37 * result + (getCheckUserCode() == null ? 0 : this.getCheckUserCode().hashCode());
		result = 37 * result + (getCheckDate() == null ? 0 : this.getCheckDate().hashCode());
		result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result + (getPickup() == null ? 0 : this.getPickup().hashCode());
		result = 37 * result + (getSubmitTime() == null ? 0 : this.getSubmitTime().hashCode());
		result = 37 * result + (getSubmitUserCode() == null ? 0 : this.getSubmitUserCode().hashCode());
		result = 37 * result + (getSubmitStatus() == null ? 0 : this.getSubmitStatus().hashCode());
		result = 37 * result + (getLocked() == null ? 0 : this.getLocked().hashCode());
		result = 37 * result + (getFirstName() == null ? 0 : this.getFirstName().hashCode());
		result = 37 * result + (getLastName() == null ? 0 : this.getLastName().hashCode());
		result = 37 * result + (getProvince() == null ? 0 : this.getProvince().hashCode());
		result = 37 * result + (getCity() == null ? 0 : this.getCity().hashCode());
		result = 37 * result + (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result + (getPostalcode() == null ? 0 : this.getPostalcode().hashCode());
		result = 37 * result + (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result + (getMobiletele() == null ? 0 : this.getMobiletele().hashCode());
		result = 37 * result + (getProductType() == null ? 0 : this.getProductType().hashCode());
		result = 37 * result + (getInterOkDelivery() == null ? 0 : this.getInterOkDelivery().hashCode());
		result = 37 * result + (getSelfHelpExchange() == null ? 0 : this.getSelfHelpExchange().hashCode());

		return result;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	/**
	 * * @hibernate.property column="RESEND_SP_NO" length="20"
	 * 
	 */
	public String getResendSpNo() {
		return resendSpNo;
	}

	public void setResendSpNo(String resendSpNo) {
		this.resendSpNo = resendSpNo;
	}

	/**
	 * * @hibernate.property column="SHIPPING_COMPANY" length="20"
	 * 
	 */
	public String getShippingCompany() {
		return shippingCompany;
	}

	public void setShippingCompany(String shippingCompany) {
		this.shippingCompany = shippingCompany;
	}

	/**
	 * * @hibernate.property column="SHIPPING_SPECIAL" length="2"
	 * 
	 */
	public String getShippingSpecial() {
		return shippingSpecial;
	}

	public void setShippingSpecial(String shippingSpecial) {
		this.shippingSpecial = shippingSpecial;
	}

	/**
	 * * @hibernate.property column="PAY_BY_COIN" length="2"
	 * 
	 */
	public String getPayByCoin() {
		return payByCoin;
	}

	public void setPayByCoin(String payByCoin) {
		this.payByCoin = payByCoin;
	}

	/**
	 * * @hibernate.property column="DISCOUNT_AMOUNT" length="18"
	 * 
	 */
	public BigDecimal getDiscountAmount() {
		if (discountAmount != null) {
			return discountAmount;
		} else {
			return new BigDecimal(0);
		}

	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * * @hibernate.property column="DISCOUNT_PV_AMT" length="18"
	 * 
	 */
	public BigDecimal getDiscountPvAmt() {
		return discountPvAmt;
	}

	public void setDiscountPvAmt(BigDecimal discountPvAmt) {
		this.discountPvAmt = discountPvAmt;
	}

	/**
	 * * @hibernate.property column="TRANSACTION_NUMBER" length="20"
	 * 
	 */
	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	/**
	 * * @hibernate.property column="JJ_AMOUNT" length="18"
	 * 
	 */
	public BigDecimal getJjAmount() {
		if (jjAmount != null) {
			return jjAmount;
		} else {
			return new BigDecimal(0);
		}

	}

	public void setJjAmount(BigDecimal jjAmount) {
		this.jjAmount = jjAmount;
	}

	/**
	 * * @hibernate.property column="PAY_BY_JJ" length="2"
	 * 
	 */
	public String getPayByJJ() {
		return payByJJ;
	}

	public void setPayByJJ(String payByJJ) {
		this.payByJJ = payByJJ;
	}

	/**
	 * * @hibernate.property column="IS_RETREAT_ORDER" length="2"
	 * 
	 */
	public String getIsRetreatOrder() {// 是否为退货单
		return isRetreatOrder;
	}

	public void setIsRetreatOrder(String isRetreatOrder) {
		this.isRetreatOrder = isRetreatOrder;
	}

	/**
	 * * @hibernate.property column="IS_SHIPMENTS" length="2"
	 * 
	 */
	public String getIsShipments() {// 是否暂不发货
		return isShipments;
	}

	public void setIsShipments(String isShipments) {
		this.isShipments = isShipments;
	}

	/**
	 * * @hibernate.property column="TEAM_CODE" length="10"
	 * 
	 */
	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	/**
	 * @hibernate.property column="IS_MOBILE" length="2"
	 * 
	 */
	public String getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(String isMobile) {
		this.isMobile = isMobile;
	}

	/**
	 * @hibernate.property column="PRODUCTFLAG" length="10" update="false"
	 * 
	 */
	public String getProductFlag() {
		return productFlag;
	}

	public void setProductFlag(String productFlag) {
		this.productFlag = productFlag;
	}
	
	/**
	 * @hibernate.property column="STJ_PRICE" 
	 * 
	 */
	public Integer getStjPrice() {
		return stjPrice;
	}

	public void setStjPrice(Integer stjPrice) {
		this.stjPrice = stjPrice;
	}

	/**
	 * @hibernate.property column="STJ_LOCK" length="2" update="false"
	 * 
	 */
	public String getStjLock() {
		return stjLock;
	}

	public void setStjLock(String stjLock) {
		this.stjLock = stjLock;
	}

	/**
	 * @hibernate.property column="STJ_GROUP" length="100" update="false"
	 * 
	 */
	public String getStjGroup() {
		return stjGroup;
	}

	public void setStjGroup(String stjGroup) {
		this.stjGroup = stjGroup;
	}

	public String getLinkUserName() {
		return linkUserName;
	}

	public void setLinkUserName(String linkUserName) {
		this.linkUserName = linkUserName;
	}

	public String getRecommandName() {
		return recommandName;
	}

	public void setRecommandName(String recommandName) {
		this.recommandName = recommandName;
	}

	/**
	 * * @hibernate.property column="EXCHANGE_FLAG" length="1"
	 * 
	 */
	public String getExchangeFlag() {
		return exchangeFlag;
	}

	public void setExchangeFlag(String exchangeFlag) {
		this.exchangeFlag = exchangeFlag;
	}
}
