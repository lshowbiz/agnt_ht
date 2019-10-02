package com.joymain.jecs.pd.model;

// Generated 2009-9-21 11:36:16 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
  * @hibernate.class
 *         table="PD_SEND_INFO"
 *           	dynamic-update="true"
 *		dynamic-insert="true"
 *		optimistic-lock="version" 
 */

public class PdSendInfo extends com.joymain.jecs.model.BaseObject implements
		java.io.Serializable {

	// Fields

	private String siNo;
	private String companyCode;
	private BigDecimal amount=new BigDecimal(0);
	private String warehouseNo;
	
	
	// private String customCode;

	

	private SysUser customer=new SysUser();

	private String recipientName;
	private String recipientFirstName;
	private String recipientLastName;
	private String recipientCaNo;
	private String city;
	private String cityName;
	private String district;
	private String townId;
	private String recipientAddr;
	private String recipientZip;
	private String recipientPhone;
	private String email;
	private String shNo;
	private String shipType="0";//发货方式:0正常发货;2暂不发货;3暂停发货;4发货作废
	private String createUsrCode;
	private Date createTime;
	private String remark;
	private Date checkTime;
	private String checkUsrCode;
	private String checkRemark;
	private Date okTime;
	private String okUsrCode;
	private String okRemark;
	private String editUsrCode;
	private Date editTime;
	private String trackingNo;
	private String orderNo;
	private Integer orderFlag;
	private String countryCode;
	private Long version=new Long(0);
	private String recipientMobile;
	private String stockFlag="0";
	private Set pdSendInfoDetails = new HashSet();
	private Set pdShipmentsDetails = new HashSet();
	 private Set<PdSendExtra> pdSendExtras = new HashSet<PdSendExtra>(0);
	 
	private BigDecimal weight=new BigDecimal(0);
	private BigDecimal volume=new BigDecimal(0);
	
	private String orderType;
	private String subOrderType;
	
	private String codFlag;
	
    private String shipmentDigest;
    private String shipmentIdentificationNumber;//  modify fu 20150825日起这个字段就有用了。字段的意思是：临时保存发货单在更新前的发货方式
    
    private String hurryFlag;
    
    private Date recipientTime;
    //Modify By WuCF 20140711 条形码
    private String barCode;
    
    // modify by fu 215-09-16  借款用字段，N表示不可以生成DO单，空值表示可以生成DO单
    private String canDo;
    //modify by fu 2015-11-17 添加由DO单号获取的物流单号  LOGISTICS_DO
    private String logisticsDo;
    private String partSend;//发货单的部分发货---接口关联；
    
    //modify by fu 2016-1-18
    private String inteferaceShNo;//接口物流公司
    private String whetherStock;// 是否备货，空或N表示未备货，Y表示已备货
    
    //modify by fu 2016-07-12
    private String suspendShipment;//SUSPEND_SHIPMENT  暂停发货原因：1退单；2换货；3定制床垫
    
    
	/**       
     *      *            @hibernate.property
     *             column="STOCK_FLAG"
     *             length="1"
     *             
     *         
     */
	public String getStockFlag() {
		return stockFlag;
	}

	public PdSendInfo(String siNo, String userCode,String userName, String recipientName,
			String recipientCaNo, String city, String recipientAddr,
			String recipientZip, String recipientPhone, String email,
			Date checkTime, String orderNo, String recipientMobile,
			 String codFlag, String hurryFlag
			,String createUsrCode,Date createTime,String okUsrCode,Date okTime,
			String warehouseNo,String shNo,Integer orderFlag,String trackingNo,String barCode,Set pdSendInfoDetails,String canDo,String logisticsDo,String partSend,
			String inteferaceShNo,String whetherStock,String suspendShipment) {
//		super();
		this.siNo = siNo;
		this.customer = new SysUser(userCode, userName);
		this.recipientName = recipientName;
		this.recipientCaNo = recipientCaNo;
		this.city = city;
		this.recipientAddr = recipientAddr;
		this.recipientZip = recipientZip;
		this.recipientPhone = recipientPhone;
		this.email = email;
		this.checkTime = checkTime;
		this.orderNo = orderNo;
		this.recipientMobile = recipientMobile;
		this.codFlag = codFlag;
		this.hurryFlag = hurryFlag;
		this.createUsrCode = createUsrCode;
		this.createTime = createTime;
		this.okUsrCode = okUsrCode;
		this.okTime = okTime;
		this.warehouseNo = warehouseNo;
		this.shNo = shNo;
		this.orderFlag = orderFlag;
		this.trackingNo = trackingNo;
		this.barCode = barCode;
		this.pdSendInfoDetails = pdSendInfoDetails;
		this.canDo = canDo;
		this.logisticsDo = logisticsDo;
		this.partSend = partSend;
		this.inteferaceShNo = inteferaceShNo;
		this.whetherStock = whetherStock;
		this.suspendShipment = suspendShipment;
	}

	public void setStockFlag(String stockFlag) {
		this.stockFlag = stockFlag;
	}

	/**       
     *      *            @hibernate.property
     *             column="HURRY_FLAG"
     *              
     *             
     *         
     */
	public String getHurryFlag() {
		return hurryFlag;
	}

	public void setHurryFlag(String hurryFlag) {
		this.hurryFlag = hurryFlag;
	}

	/**       
     *      *            @hibernate.property
     *             column="ORDER_TYPE"
     *             
     *             
     *         
     */
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**       
     *      *            @hibernate.version
     *             column="VERSION"
     *             length="10"
     *         
     */

    public Long getVersion() {
        return this.version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
	/**       
     *      *            @hibernate.property
     *             column="DISTRICT"
     *             
     *             
     *         
     */
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	/**       
     *      *            @hibernate.property
     *             column="town_id"
     *             
     *             
     *         
     */
	public String getTownId() {
		return townId;
	}

	public void setTownId(String townId) {
		this.townId = townId;
	}

	/**
	 * *
	 * 
	 * @hibernate.set lazy="false" inverse="true" cascade="all-delete-orphan"
	 *                order-by="product_no"
	 * @hibernate.collection-key column="SI_NO"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pd.model.PdSendInfoDetail"
	 * 
	 */
	public Set getPdSendInfoDetails() {
		return pdSendInfoDetails;
	}

	public void setPdSendInfoDetails(Set pdSendInfoDetails) {
		this.pdSendInfoDetails = pdSendInfoDetails;
	}

	/**
	 * @hibernate.set table="PD_SHIPMENTS_SEND_INFO" cascade="save-update"
	 *                lazy="true" 
	 * @hibernate.collection-key column="SI_NO"
	 * @hibernate.collection-many-to-many 
	 *                                    class="com.joymain.jecs.pd.model.PdShipmentsDetail"
	 *                                    column="SD_ID"
	 */
	public Set getPdShipmentsDetails() {
		return pdShipmentsDetails;
	}

	public void setPdShipmentsDetails(Set pdShipmentsDetails) {
		this.pdShipmentsDetails = pdShipmentsDetails;
	}

	public void addPdShipmentsDetail(PdShipmentsDetail pdShipmentsDetail){
		pdShipmentsDetails.add(pdShipmentsDetail);
	}
	
	// Constructors
	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="product_No desc"
	 * @hibernate.collection-key column="SI_NO"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pd.model.PdSendExtra"
	 * 
	 */
	public Set<PdSendExtra> getPdSendExtras() {
		return pdSendExtras;
	}

	public void setPdSendExtras(Set<PdSendExtra> pdSendExtras) {
		this.pdSendExtras = pdSendExtras;
	}

	/**
	 * * @hibernate.property column="ORDER_FLAG" length="1"
	 * 
	 */
	public Integer getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
	}
	
	
	/**
	 * * @hibernate.property column="SHIPMENT_DIGEST" 
	 * 
	 */
	public String getShipmentDigest() {
		return shipmentDigest;
	}

	public void setShipmentDigest(String shipmentDigest) {
		this.shipmentDigest = shipmentDigest;
	}
	/**
	 * * @hibernate.property column="SHIPMENT_IDENTIFICATION_NUMBER" 
	 * 
	 */
	public String getShipmentIdentificationNumber() {
		return shipmentIdentificationNumber;
	}

	public void setShipmentIdentificationNumber(String shipmentIdentificationNumber) {
		this.shipmentIdentificationNumber = shipmentIdentificationNumber;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_MOBILE"  
	 * 
	 */
	
	public String getRecipientMobile() {
		return recipientMobile;
	}

	public void setRecipientMobile(String recipientMobile) {
		this.recipientMobile = recipientMobile;
	}
	/**
	 * * @hibernate.property column="COUNTRY_CODE" length="2"
	 * 
	 */
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * *
	 * 
	 * @hibernate.many-to-one 
	 * @hibernate.column name="CUSTOM_CODE"
	 * 
	 */
	public SysUser getCustomer() {
		return customer;
	}

	public void setCustomer(SysUser customer) {
		this.customer = customer;
	}

	/**
	 * * @hibernate.property column="sub_Order_Type" length="2"
	 * 
	 */
	public String getSubOrderType() {
		return subOrderType;
	}

	public void setSubOrderType(String subOrderType) {
		this.subOrderType = subOrderType;
	}

	/**
	 * * @hibernate.property column="CAN_DO" length="100"
	 * 
	 */
	public String getCanDo() {
		return canDo;
	}

	public void setCanDo(String canDo) {
		this.canDo = canDo;
	}

	/** default constructor */
	public PdSendInfo() {
	}

	/** minimal constructor */
	public PdSendInfo(String companyCode, BigDecimal amount,
			String warehouseNo, String customCode, String recipientName,
			String recipientCaNo, String recipientAddr, String recipientZip,
			String recipientPhone, String shNo, String createUsrCode,
			Date createTime) {
		this.companyCode = companyCode;
		this.amount = amount;
		this.warehouseNo = warehouseNo;
		// this.customCode = customCode;
		this.recipientName = recipientName;
		this.recipientCaNo = recipientCaNo;
		this.recipientAddr = recipientAddr;
		this.recipientZip = recipientZip;
		this.recipientPhone = recipientPhone;
		this.shNo = shNo;
		this.createUsrCode = createUsrCode;
		this.createTime = createTime;
	}

	/** full constructor */
	public PdSendInfo(String companyCode, BigDecimal amount,
			String warehouseNo, String customCode, String recipientName,
			String recipientCaNo, String city, String recipientAddr,
			String recipientZip, String recipientPhone, String email,
			String shNo, String shipType, String createUsrCode,
			Date createTime, String remark, Date checkTime,
			String checkUsrCode, String checkRemark, Date okTime,
			String okUsrCode, String okRemark, String editUsrCode,
			Date editTime, String trackingNo, String orderNo) {
		this.companyCode = companyCode;
		this.amount = amount;
		this.warehouseNo = warehouseNo;
		// this.customCode = customCode;
		this.recipientName = recipientName;
		this.recipientCaNo = recipientCaNo;
		this.city = city;
		this.recipientAddr = recipientAddr;
		this.recipientZip = recipientZip;
		this.recipientPhone = recipientPhone;
		this.email = email;
		this.shNo = shNo;
		this.shipType = shipType;
		this.createUsrCode = createUsrCode;
		this.createTime = createTime;
		this.remark = remark;
		this.checkTime = checkTime;
		this.checkUsrCode = checkUsrCode;
		this.checkRemark = checkRemark;
		this.okTime = okTime;
		this.okUsrCode = okUsrCode;
		this.okRemark = okRemark;
		this.editUsrCode = editUsrCode;
		this.editTime = editTime;
		this.trackingNo = trackingNo;
		this.orderNo = orderNo;
	}

	// Property accessors
	/**
	 * * @hibernate.id generator-class="assigned" 
	 * 		type="java.lang.String"
	 * 
	 * column="SI_NO"
	 * 
	 */

	public String getSiNo() {
		return this.siNo;
	}

	public void setSiNo(String siNo) {
		this.siNo = siNo;
	}

	/**
	 * * @hibernate.property column="COMPANY_CODE" length="2" 
	 * 
	 */

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * * @hibernate.property column="AMOUNT" length="22" 
	 * 
	 */

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * * @hibernate.property column="WAREHOUSE_NO" length="10" 
	 * 
	 */

	public String getWarehouseNo() {
		return this.warehouseNo;
	}
	 /**
     * @spring.validator type="required"
     */
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_NAME" length="20" 
	 * 
	 */

	public String getRecipientName() {
		return this.recipientName;
	}
	
	
	

	/**
     * @spring.validator type="required"
     */
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_FIRST_NAME" length="100" 
	 * 
	 */
	 public String getRecipientFirstName() {
			return recipientFirstName;
		}

		public void setRecipientFirstName(String recipientFirstName) {
			this.recipientFirstName = recipientFirstName;
		}
		/**
		 * * @hibernate.property column="RECIPIENT_LAST_NAME" length="100" 
		 * 
		 */
		public String getRecipientLastName() {
			return recipientLastName;
		}

		public void setRecipientLastName(String recipientLastName) {
			this.recipientLastName = recipientLastName;
		}
		
		
	/**
	 * * @hibernate.property column="RECIPIENT_CA_NO" length="10"
	 * 
	 * 
	 */

	public String getRecipientCaNo() {
		return this.recipientCaNo;
	}
	 /**
     * @spring.validator type="required"
     */
	public void setRecipientCaNo(String recipientCaNo) {
		this.recipientCaNo = recipientCaNo;
	}

	/**
	 * * @hibernate.property column="CITY" length="200"
	 * 
	 */

	public String getCity() {
		return this.city;
	}
	 /**
     * @spring.validator type="required"
     */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_ADDR" length="100"
	 * 
	 * 
	 */

	public String getRecipientAddr() {
		return this.recipientAddr;
	}
	 /**
     * @spring.validator type="required"
     */
	public void setRecipientAddr(String recipientAddr) {
		this.recipientAddr = recipientAddr;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_ZIP" length="10" 
	 * 
	 */

	public String getRecipientZip() {
		return this.recipientZip;
	}
	 /**
     * @spring.validator type="required"
     */
	public void setRecipientZip(String recipientZip) {
		this.recipientZip = recipientZip;
	}

	/**
	 * * @hibernate.property column="RECIPIENT_PHONE" length="254"
	 * 
	 * 
	 */

	public String getRecipientPhone() {
		return this.recipientPhone;
	}
	 /**
     * @spring.validator type="required"
     */
	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	/**
	 * * @hibernate.property column="EMAIL" length="200"
	 * 
	 */

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * * @hibernate.property column="SH_NO" length="10" 
	 * 
	 */

	public String getShNo() {
		return this.shNo;
	}
	 /**
     * @spring.validator type="required"
     */
	public void setShNo(String shNo) {
		this.shNo = shNo;
	}

	/**
	 * * @hibernate.property column="SHIP_TYPE" length="4"
	 * 
	 */

	public String getShipType() {
		return this.shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	/**
	 * * @hibernate.property column="CREATE_USR_CODE" length="20"
	 * 
	 * 
	 */

	public String getCreateUsrCode() {
		return this.createUsrCode;
	}

	public void setCreateUsrCode(String createUsrCode) {
		this.createUsrCode = createUsrCode;
	}

	/**
	 * * @hibernate.property column="CREATE_TIME" length="7" 
	 * 
	 */

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * * @hibernate.property column="REMARK" length="200"
	 * 
	 */

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	 * * @hibernate.property column="CHECK_USR_CODE" length="20"
	 * 
	 */

	public String getCheckUsrCode() {
		return this.checkUsrCode;
	}

	public void setCheckUsrCode(String checkUsrCode) {
		this.checkUsrCode = checkUsrCode;
	}

	/**
	 * * @hibernate.property column="CHECK_REMARK" length="200"
	 * 
	 */

	public String getCheckRemark() {
		return this.checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

	/**
	 * * @hibernate.property column="OK_TIME" length="7"
	 * 
	 */

	public Date getOkTime() {
		return this.okTime;
	}

	public void setOkTime(Date okTime) {
		this.okTime = okTime;
	}

	/**
	 * * @hibernate.property column="OK_USR_CODE" length="20"
	 * 
	 */

	public String getOkUsrCode() {
		return this.okUsrCode;
	}

	public void setOkUsrCode(String okUsrCode) {
		this.okUsrCode = okUsrCode;
	}

	/**
	 * * @hibernate.property column="OK_REMARK" length="200"
	 * 
	 */

	public String getOkRemark() {
		return this.okRemark;
	}

	public void setOkRemark(String okRemark) {
		this.okRemark = okRemark;
	}

	/**
	 * * @hibernate.property column="EDIT_USR_CODE" length="20"
	 * 
	 */

	public String getEditUsrCode() {
		return this.editUsrCode;
	}

	public void setEditUsrCode(String editUsrCode) {
		this.editUsrCode = editUsrCode;
	}

	/**
	 * * @hibernate.property column="EDIT_TIME" length="7"
	 * 
	 */

	public Date getEditTime() {
		return this.editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	/**
	 * * @hibernate.property column="TRACKING_NO" length="200"
	 * 
	 */

	public String getTrackingNo() {
		return this.trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	/**
	 * * @hibernate.property column="ORDER_NO" length="20"
	 * 
	 */

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**       
     *      *            @hibernate.property
     *             column="WEIGHT"
     *             length="10"
     *         
     */
	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	/**       
     *      *            @hibernate.property
     *             column="VOLUME"
     *             length="10"
     *         
     */
	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	/**       
     *      *            @hibernate.property
     *             column="COD_FLAG"
     *             
     *         
     */
	public String getCodFlag() {
		return codFlag;
	}

	public void setCodFlag(String codFlag) {
		this.codFlag = codFlag;
	}

	/**       
     *      *            @hibernate.property
     *             column="CITY_NAME"
     *             
     *         
     */
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**       
     *      *            @hibernate.property
     *             column="RECIPIENT_TIME"
     *             
     *         
     */
	public Date getRecipientTime() {
		return recipientTime;
	}

	public void setRecipientTime(Date recipientTime) {
		this.recipientTime = recipientTime;
	}

	/**       
     *      *            @hibernate.property
     *             column="BAR_CODE"
     *             
     *         
     */
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	/**
	 * * @hibernate.property column="LOGISTICS_DO" length="500"
	 * 
	 */
	public String getLogisticsDo() {
		return logisticsDo;
	}

	public void setLogisticsDo(String logisticsDo) {
		this.logisticsDo = logisticsDo;
	}

	/**
	 * * @hibernate.property column="PART_SEND" length="20"
	 * 
	 */
	public String getPartSend() {
		return partSend;
	}

	public void setPartSend(String partSend) {
		this.partSend = partSend;
	}
	
	
	/**
	 * * @hibernate.property column="INTEFERACE_SH_NO" length="500"
	 * 
	 */
	public String getInteferaceShNo() {
		return inteferaceShNo;
	}

	public void setInteferaceShNo(String inteferaceShNo) {
		this.inteferaceShNo = inteferaceShNo;
	}
	
	
	/**
	 * * @hibernate.property column="WHETHER_STOCK" length="2"
	 * 
	 */
	public String getWhetherStock() {
		return whetherStock;
	}

	public void setWhetherStock(String whetherStock) {
		this.whetherStock = whetherStock;
	}
	
	
	/**
	 * * @hibernate.property column="SUSPEND_SHIPMENT" length="10"
	 * 
	 */
	public String getSuspendShipment() {
		return suspendShipment;
	}

	public void setSuspendShipment(String suspendShipment) {
		this.suspendShipment = suspendShipment;
	}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(
				Integer.toHexString(hashCode())).append(" [");
		buffer.append("companyCode").append("='").append(getCompanyCode())
				.append("' ");
		buffer.append("amount").append("='").append(getAmount()).append("' ");
		buffer.append("warehouseNo").append("='").append(getWarehouseNo())
				.append("' ");
		// buffer.append("customCode").append("='").append(getCustomCode()).append("' ");
		buffer.append("recipientName").append("='").append(getRecipientName())
				.append("' ");
		buffer.append("recipientCaNo").append("='").append(getRecipientCaNo())
				.append("' ");
		buffer.append("city").append("='").append(getCity()).append("' ");
		buffer.append("recipientAddr").append("='").append(getRecipientAddr())
				.append("' ");
		buffer.append("recipientZip").append("='").append(getRecipientZip())
				.append("' ");
		buffer.append("recipientPhone").append("='")
				.append(getRecipientPhone()).append("' ");
		buffer.append("email").append("='").append(getEmail()).append("' ");
		buffer.append("shNo").append("='").append(getShNo()).append("' ");
		buffer.append("shipType").append("='").append(getShipType()).append(
				"' ");
		buffer.append("createUsrCode").append("='").append(getCreateUsrCode())
				.append("' ");
		buffer.append("createTime").append("='").append(getCreateTime())
				.append("' ");
		buffer.append("remark").append("='").append(getRemark()).append("' ");
		buffer.append("checkTime").append("='").append(getCheckTime()).append(
				"' ");
		buffer.append("checkUsrCode").append("='").append(getCheckUsrCode())
				.append("' ");
		buffer.append("checkRemark").append("='").append(getCheckRemark())
				.append("' ");
		buffer.append("okTime").append("='").append(getOkTime()).append("' ");
		buffer.append("okUsrCode").append("='").append(getOkUsrCode()).append(
				"' ");
		buffer.append("okRemark").append("='").append(getOkRemark()).append(
				"' ");
		buffer.append("editUsrCode").append("='").append(getEditUsrCode())
				.append("' ");
		buffer.append("editTime").append("='").append(getEditTime()).append(
				"' ");
		buffer.append("trackingNo").append("='").append(getTrackingNo())
				.append("' ");
		buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");
		buffer.append("canDo").append("='").append(getCanDo()).append("' ");
		buffer.append("logisticsDo").append("='").append(getLogisticsDo()).append("' ");
		buffer.append("partSend").append("='").append(getPartSend()).append("' ");
		buffer.append("inteferaceShNo").append("='").append(getInteferaceShNo()).append("' ");
		buffer.append("whetherStock").append("='").append(getWhetherStock()).append("' ");
		buffer.append("suspendShipment").append("='").append(getSuspendShipment()).append("' ");

		buffer.append("]");

		return buffer.toString();
	}
	

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PdSendInfo))
			return false;
		PdSendInfo castOther = (PdSendInfo) other;

		return ((this.getSiNo() == castOther.getSiNo()) || (this.getSiNo() != null
				&& castOther.getSiNo() != null && this.getSiNo().equals(
				castOther.getSiNo())))
				&& ((this.getCompanyCode() == castOther.getCompanyCode()) || (this
						.getCompanyCode() != null
						&& castOther.getCompanyCode() != null && this
						.getCompanyCode().equals(castOther.getCompanyCode())))
				&& ((this.getAmount() == castOther.getAmount()) || (this
						.getAmount() != null
						&& castOther.getAmount() != null && this.getAmount()
						.equals(castOther.getAmount())))
				&& ((this.getWarehouseNo() == castOther.getWarehouseNo()) || (this
						.getWarehouseNo() != null
						&& castOther.getWarehouseNo() != null && this
						.getWarehouseNo().equals(castOther.getWarehouseNo())))
				// && ( (this.getCustomCode()==castOther.getCustomCode()) || (
				// this.getCustomCode()!=null && castOther.getCustomCode()!=null
				// && this.getCustomCode().equals(castOther.getCustomCode()) ) )
				&& ((this.getRecipientName() == castOther.getRecipientName()) || (this
						.getRecipientName() != null
						&& castOther.getRecipientName() != null && this
						.getRecipientName()
						.equals(castOther.getRecipientName())))
				&& ((this.getRecipientCaNo() == castOther.getRecipientCaNo()) || (this
						.getRecipientCaNo() != null
						&& castOther.getRecipientCaNo() != null && this
						.getRecipientCaNo()
						.equals(castOther.getRecipientCaNo())))
				&& ((this.getCity() == castOther.getCity()) || (this.getCity() != null
						&& castOther.getCity() != null && this.getCity()
						.equals(castOther.getCity())))
				&& ((this.getRecipientAddr() == castOther.getRecipientAddr()) || (this
						.getRecipientAddr() != null
						&& castOther.getRecipientAddr() != null && this
						.getRecipientAddr()
						.equals(castOther.getRecipientAddr())))
				&& ((this.getRecipientZip() == castOther.getRecipientZip()) || (this
						.getRecipientZip() != null
						&& castOther.getRecipientZip() != null && this
						.getRecipientZip().equals(castOther.getRecipientZip())))
				&& ((this.getRecipientPhone() == castOther.getRecipientPhone()) || (this
						.getRecipientPhone() != null
						&& castOther.getRecipientPhone() != null && this
						.getRecipientPhone().equals(
								castOther.getRecipientPhone())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null
						&& castOther.getEmail() != null && this.getEmail()
						.equals(castOther.getEmail())))
				&& ((this.getShNo() == castOther.getShNo()) || (this.getShNo() != null
						&& castOther.getShNo() != null && this.getShNo()
						.equals(castOther.getShNo())))
				&& ((this.getShipType() == castOther.getShipType()) || (this
						.getShipType() != null
						&& castOther.getShipType() != null && this
						.getShipType().equals(castOther.getShipType())))
				&& ((this.getCreateUsrCode() == castOther.getCreateUsrCode()) || (this
						.getCreateUsrCode() != null
						&& castOther.getCreateUsrCode() != null && this
						.getCreateUsrCode()
						.equals(castOther.getCreateUsrCode())))
				&& ((this.getCreateTime() == castOther.getCreateTime()) || (this
						.getCreateTime() != null
						&& castOther.getCreateTime() != null && this
						.getCreateTime().equals(castOther.getCreateTime())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null
						&& castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())))
				&& ((this.getCheckTime() == castOther.getCheckTime()) || (this
						.getCheckTime() != null
						&& castOther.getCheckTime() != null && this
						.getCheckTime().equals(castOther.getCheckTime())))
				&& ((this.getCheckUsrCode() == castOther.getCheckUsrCode()) || (this
						.getCheckUsrCode() != null
						&& castOther.getCheckUsrCode() != null && this
						.getCheckUsrCode().equals(castOther.getCheckUsrCode())))
				&& ((this.getCheckRemark() == castOther.getCheckRemark()) || (this
						.getCheckRemark() != null
						&& castOther.getCheckRemark() != null && this
						.getCheckRemark().equals(castOther.getCheckRemark())))
				&& ((this.getOkTime() == castOther.getOkTime()) || (this
						.getOkTime() != null
						&& castOther.getOkTime() != null && this.getOkTime()
						.equals(castOther.getOkTime())))
				&& ((this.getOkUsrCode() == castOther.getOkUsrCode()) || (this
						.getOkUsrCode() != null
						&& castOther.getOkUsrCode() != null && this
						.getOkUsrCode().equals(castOther.getOkUsrCode())))
				&& ((this.getOkRemark() == castOther.getOkRemark()) || (this
						.getOkRemark() != null
						&& castOther.getOkRemark() != null && this
						.getOkRemark().equals(castOther.getOkRemark())))
				&& ((this.getEditUsrCode() == castOther.getEditUsrCode()) || (this
						.getEditUsrCode() != null
						&& castOther.getEditUsrCode() != null && this
						.getEditUsrCode().equals(castOther.getEditUsrCode())))
				&& ((this.getEditTime() == castOther.getEditTime()) || (this
						.getEditTime() != null
						&& castOther.getEditTime() != null && this
						.getEditTime().equals(castOther.getEditTime())))
				&& ((this.getTrackingNo() == castOther.getTrackingNo()) || (this
						.getTrackingNo() != null
						&& castOther.getTrackingNo() != null && this
						.getTrackingNo().equals(castOther.getTrackingNo())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this
						.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo()
						.equals(castOther.getOrderNo())))
				&& ((this.getCanDo() == castOther.getCanDo()) || (this
						.getCanDo() != null
						&& castOther.getCanDo() != null && this.getCanDo()
						.equals(castOther.getCanDo())))
				&& ((this.getLogisticsDo() == castOther.getLogisticsDo()) || (this
						.getLogisticsDo() != null
						&& castOther.getLogisticsDo() != null && this.getLogisticsDo()
						.equals(castOther.getLogisticsDo())))
				&& ((this.getPartSend() == castOther.getPartSend()) || (this
						.getPartSend() != null
						&& castOther.getPartSend() != null && this.getPartSend()
						.equals(castOther.getPartSend())))
				&& ((this.getInteferaceShNo() == castOther.getInteferaceShNo()) || (this
						.getInteferaceShNo() != null
						&& castOther.getInteferaceShNo() != null && this.getInteferaceShNo()
						.equals(castOther.getInteferaceShNo())))
				&& ((this.getWhetherStock() == castOther.getWhetherStock()) || (this
						.getWhetherStock() != null
						&& castOther.getWhetherStock() != null && this.getWhetherStock()
						.equals(castOther.getWhetherStock())))
				&& ((this.getSuspendShipment() == castOther.getSuspendShipment()) || (this
						.getSuspendShipment() != null
						&& castOther.getSuspendShipment() != null && this.getSuspendShipment()
						.equals(castOther.getSuspendShipment())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSiNo() == null ? 0 : this.getSiNo().hashCode());
		result = 37
				* result
				+ (getCompanyCode() == null ? 0 : this.getCompanyCode()
						.hashCode());
		result = 37 * result
				+ (getAmount() == null ? 0 : this.getAmount().hashCode());
		result = 37
				* result
				+ (getWarehouseNo() == null ? 0 : this.getWarehouseNo()
						.hashCode());
		// result = 37 * result + ( getCustomCode() == null ? 0 :
		// this.getCustomCode().hashCode() );
		result = 37
				* result
				+ (getRecipientName() == null ? 0 : this.getRecipientName()
						.hashCode());
		result = 37
				* result
				+ (getRecipientCaNo() == null ? 0 : this.getRecipientCaNo()
						.hashCode());
		result = 37 * result
				+ (getCity() == null ? 0 : this.getCity().hashCode());
		result = 37
				* result
				+ (getRecipientAddr() == null ? 0 : this.getRecipientAddr()
						.hashCode());
		result = 37
				* result
				+ (getRecipientZip() == null ? 0 : this.getRecipientZip()
						.hashCode());
		result = 37
				* result
				+ (getRecipientPhone() == null ? 0 : this.getRecipientPhone()
						.hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result
				+ (getShNo() == null ? 0 : this.getShNo().hashCode());
		result = 37 * result
				+ (getShipType() == null ? 0 : this.getShipType().hashCode());
		result = 37
				* result
				+ (getCreateUsrCode() == null ? 0 : this.getCreateUsrCode()
						.hashCode());
		result = 37
				* result
				+ (getCreateTime() == null ? 0 : this.getCreateTime()
						.hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getCheckTime() == null ? 0 : this.getCheckTime().hashCode());
		result = 37
				* result
				+ (getCheckUsrCode() == null ? 0 : this.getCheckUsrCode()
						.hashCode());
		result = 37
				* result
				+ (getCheckRemark() == null ? 0 : this.getCheckRemark()
						.hashCode());
		result = 37 * result
				+ (getOkTime() == null ? 0 : this.getOkTime().hashCode());
		result = 37 * result
				+ (getOkUsrCode() == null ? 0 : this.getOkUsrCode().hashCode());
		result = 37 * result
				+ (getOkRemark() == null ? 0 : this.getOkRemark().hashCode());
		result = 37
				* result
				+ (getEditUsrCode() == null ? 0 : this.getEditUsrCode()
						.hashCode());
		result = 37 * result
				+ (getEditTime() == null ? 0 : this.getEditTime().hashCode());
		result = 37
				* result
				+ (getTrackingNo() == null ? 0 : this.getTrackingNo()
						.hashCode());
		result = 37 * result
				+ (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37 * result
		        + (getCanDo() == null ? 0 : this.getCanDo().hashCode());
		
		result = 37 * result
                + (getLogisticsDo() == null ? 0 : this.getLogisticsDo().hashCode());
		result = 37 * result
                + (getPartSend() == null ? 0 : this.getPartSend().hashCode());
		result = 37 * result
        + (getInteferaceShNo() == null ? 0 : this.getInteferaceShNo().hashCode());
		result = 37 * result
        + (getWhetherStock() == null ? 0 : this.getWhetherStock().hashCode());
		result = 37 * result
		        + (getSuspendShipment() == null ? 0 : this.getSuspendShipment().hashCode());
		return result;
	}

}
