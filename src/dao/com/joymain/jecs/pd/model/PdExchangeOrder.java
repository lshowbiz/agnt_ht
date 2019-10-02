package com.joymain.jecs.pd.model;
// Generated 2010-4-7 11:19:05 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_EXCHANGE_ORDER"
 *     
 */

public class PdExchangeOrder extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String eoNo;
    private String companyCode;
    private String warehouseNo;
    private SysUser customer = new SysUser();
    
//    private String customerCode;
    private Date createTime;
    private String createUsrCode;
    private String remark;
    private Date checkTime;
    private String checkUsrCode;
    private String checkRemark;
    private Date okTime;
    private String okUsrCode;
    private String okRemark;
    private Date editTime;
    private String editUsrCode;
    private Integer orderFlag;
    private String stockFlag;
    private String firstName;
    private String lastName;
    private String province;
    private String city;
    private String district;
    private String address;
    private String postalcode;
    private String phone;
    private String email;
    private String mobiletele;
    private String orderNo;
    
    private String shipType;//发货方式

    //modify by fu 2016-04-07自助换货新增字段----begin
    private String selfReplacement;//是否是自助换货:Y是，N或空表示否 
    private String isPay;//是否支付:Y是，N或空表示否,B表示自助换货单不需要支付 
    private BigDecimal pvAmtEx = new BigDecimal(0);//总PV
    private BigDecimal amountEx = new BigDecimal(0);//总金额
	private String needPay;// 是否需要支付:Y表示需要支付，N或空表示不需要支付 
    private String whetherPd;//生成发货单的标志:1生成发货单，2生成发货单异常，空表示未生成发货单,3生成发货单中，其他数值均表示生成发货单异常 
    private BigDecimal needPayAmount = new BigDecimal(0);//自助换货单需要支付的金额   
    private String selfRemark;//自助换货单审核不通过备注
    private String selfCheckStatus;//自助换货单审核状态：Y审核通过，N审核不通过，空表示初始化制单的时候不用赋值
    private String sendStatus;//自助换货单推送到后续系统的标志。1表示已推送，空表示未推送
    private String cancelExchange;// 是否取消自助换货单：Y取消，N恢复
    //modify by fu 2016-04-07自助换货新增字段----end


    private Set<PdExchangeOrderBack> pdExchangeOrderBacks = new HashSet<PdExchangeOrderBack>();
    private Set<PdExchangeOrderDetail> pdExchangeOrderDetails = new HashSet<PdExchangeOrderDetail>();
    // Constructors

    /** default constructor */
    public PdExchangeOrder() {
    }

	/** minimal constructor */
    public PdExchangeOrder(String companyCode, String customerCode, Date createTime, String createUsrCode, String orderNo) {
        this.companyCode = companyCode;
//        this.customerCode = customerCode;
        this.createTime = createTime;
        this.createUsrCode = createUsrCode;
        this.orderNo = orderNo;
    }
    
    /** full constructor */
    public PdExchangeOrder(String companyCode, String warehouseNo, String customerCode, Date createTime, String createUsrCode, String remark, Date checkTime, String checkUsrCode, String checkRemark, Date okTime, String okUsrCode, String okRemark, Date editTime, String editUsrCode, Integer orderFlag, String stockFlag, String firstName, String lastName, String province, String city, String district, String address, String postalcode, String phone, String email, String mobiletele, String orderNo,String selfReplacement,String isPay,BigDecimal pvAmtEx,BigDecimal amountEx,String needPay,String whetherPd,BigDecimal needPayAmount,String selfRemark,String selfCheckStatus,String sendStatus,String cancelExchange) {
        this.companyCode = companyCode;
        this.warehouseNo = warehouseNo;
//        this.customerCode = customerCode;
        this.createTime = createTime;
        this.createUsrCode = createUsrCode;
        this.remark = remark;
        this.checkTime = checkTime;
        this.checkUsrCode = checkUsrCode;
        this.checkRemark = checkRemark;
        this.okTime = okTime;
        this.okUsrCode = okUsrCode;
        this.okRemark = okRemark;
        this.editTime = editTime;
        this.editUsrCode = editUsrCode;
        this.orderFlag = orderFlag;
        this.stockFlag = stockFlag;
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.postalcode = postalcode;
        this.phone = phone;
        this.email = email;
        this.mobiletele = mobiletele;
        this.orderNo = orderNo;
        
        this.selfReplacement = selfReplacement;
        this.isPay = isPay;
        this.pvAmtEx = pvAmtEx;
        this.amountEx = amountEx;
        this.needPay = needPay;
        this.whetherPd = whetherPd;
        this.needPayAmount = needPayAmount;    
        this.selfRemark = selfRemark;
        this.selfCheckStatus = selfCheckStatus;
        this.sendStatus = sendStatus;
        this.cancelExchange = cancelExchange;

    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id generator-class="assigned" 
     *             type="java.lang.String"
     *             column="EO_NO"
     *         
     */

    public String getEoNo() {
        return this.eoNo;
    }
    
    public void setEoNo(String eoNo) {
        this.eoNo = eoNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="WAREHOUSE_NO"
     *             length="20"
     *         
     */

    public String getWarehouseNo() {
        return this.warehouseNo;
    }
    /**
	 * @spring.validator type="required"
	 * @spring.validator-args arg0resource="pdReturnPurchase.returnWarehouseNo"
	 * 
	 */
    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }
  
    
    /**
	 * *
	 * 
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="CUSTOMER_CODE"
	 * 
	 */
	public SysUser getCustomer() {
		return customer;
	}
	
	public void setCustomer(SysUser customer) {
		this.customer = customer;
	}
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USR_CODE"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getCreateUsrCode() {
        return this.createUsrCode;
    }
    
    public void setCreateUsrCode(String createUsrCode) {
        this.createUsrCode = createUsrCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="200"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_TIME"
     *             length="7"
     *         
     */

    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_USR_CODE"
     *             length="20"
     *         
     */

    public String getCheckUsrCode() {
        return this.checkUsrCode;
    }
    
    public void setCheckUsrCode(String checkUsrCode) {
        this.checkUsrCode = checkUsrCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_REMARK"
     *             length="200"
     *         
     */

    public String getCheckRemark() {
        return this.checkRemark;
    }
    
    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }
    /**       
     *      *            @hibernate.property
     *             column="OK_TIME"
     *             length="7"
     *         
     */

    public Date getOkTime() {
        return this.okTime;
    }
    
    public void setOkTime(Date okTime) {
        this.okTime = okTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="OK_USR_CODE"
     *             length="20"
     *         
     */

    public String getOkUsrCode() {
        return this.okUsrCode;
    }
    
    public void setOkUsrCode(String okUsrCode) {
        this.okUsrCode = okUsrCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="OK_REMARK"
     *             length="200"
     *         
     */

    public String getOkRemark() {
        return this.okRemark;
    }
    
    public void setOkRemark(String okRemark) {
        this.okRemark = okRemark;
    }
    /**       
     *      *            @hibernate.property
     *             column="EDIT_TIME"
     *             length="7"
     *         
     */

    public Date getEditTime() {
        return this.editTime;
    }
    
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="EDIT_USR_CODE"
     *             length="20"
     *         
     */

    public String getEditUsrCode() {
        return this.editUsrCode;
    }
    
    public void setEditUsrCode(String editUsrCode) {
        this.editUsrCode = editUsrCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_FLAG"
     *             length="2"
     *         
     */

    public Integer getOrderFlag() {
        return this.orderFlag;
    }
    
    public void setOrderFlag(Integer orderFlag) {
        this.orderFlag = orderFlag;
    }
    /**       
     *      *            @hibernate.property
     *             column="STOCK_FLAG"
     *             length="1"
     *         
     */

    public String getStockFlag() {
        return this.stockFlag;
    }
    
    public void setStockFlag(String stockFlag) {
        this.stockFlag = stockFlag;
    }
    /**       
     *      *            @hibernate.property
     *             column="FIRST_NAME"
     *             length="100"
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
     *      *            @hibernate.property
     *             column="LAST_NAME"
     *             length="100"
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
     *      *            @hibernate.property
     *             column="PROVINCE"
     *             length="500"
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
     *      *            @hibernate.property
     *             column="CITY"
     *             length="500"
     *         
     */

    public String getCity() {
        return this.city;
    }
    /**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.city"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
    public void setCity(String city) {
        this.city = city;
    }
    /**       
     *      *            @hibernate.property
     *             column="DISTRICT"
     *             length="20"
     *         
     */

    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    /**       
     *      *            @hibernate.property
     *             column="ADDRESS"
     *             length="500"
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
     *      *            @hibernate.property
     *             column="POSTALCODE"
     *             length="10"
     *         
     */

    public String getPostalcode() {
        return this.postalcode;
    }
    /**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="shipping.postalcode"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    /**       
     *      *            @hibernate.property
     *             column="PHONE"
     *             length="30"
     *         
     */

    public String getPhone() {
        return this.phone;
    }
    /**
	 * @spring.validator type="maxlength"
	 * @spring.validator-args arg0resource="miMember.phone"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**       
     *      *            @hibernate.property
     *             column="EMAIL"
     *             length="30"
     *         
     */

    public String getEmail() {
        return this.email;
    }
    /**
	 * @spring.validator type="email,maxlength"
	 * @spring.validator-args arg0resource="miMember.email"
	 * @spring.validator-var name="maxlength" value="100"
	 * @spring.validator-args arg1value="100"
	 */
    public void setEmail(String email) {
        this.email = email;
    }
    /**       
     *      *            @hibernate.property
     *             column="MOBILETELE"
     *             length="20"
     *         
     */

    public String getMobiletele() {
        return this.mobiletele;
    }
    /**
	 * @spring.validator type="maxlength"
	 * 
	 * @spring.validator-args arg0resource="miMember.mobiletele"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
    public void setMobiletele(String mobiletele) {
        this.mobiletele = mobiletele;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_NO"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
   

    
    
    /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="product_No desc"
	 * @hibernate.collection-key column="EO_NO"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pd.model.PdExchangeOrderBack"
	 * 
	 */
    public Set<PdExchangeOrderBack> getPdExchangeOrderBacks() {
		return pdExchangeOrderBacks;
	}

	public void setPdExchangeOrderBacks(
			Set<PdExchangeOrderBack> pdExchangeOrderBacks) {
		this.pdExchangeOrderBacks = pdExchangeOrderBacks;
	}
	 /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="product_No desc"
	 * @hibernate.collection-key column="EO_NO"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pd.model.PdExchangeOrderDetail"
	 * 
	 */
	public Set<PdExchangeOrderDetail> getPdExchangeOrderDetails() {
		return pdExchangeOrderDetails;
	}

	public void setPdExchangeOrderDetails(
			Set<PdExchangeOrderDetail> pdExchangeOrderDetails) {
		this.pdExchangeOrderDetails = pdExchangeOrderDetails;
	}

	 /**       
     *      *            @hibernate.property
     *             column="SHIP_TYPE"
     *         
     */
	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	
	
	
	
	/**
	 * * @hibernate.property column="SELF_REPLACEMENT" length="20"
	 * 
	 */
	public String getSelfReplacement() {
		return selfReplacement;
	}

	public void setSelfReplacement(String selfReplacement) {
		this.selfReplacement = selfReplacement;
	}

	/**
	 * * @hibernate.property column="IS_PAY" length="20"
	 * 
	 */
	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	/**
	 * * @hibernate.property column="PV_AMT_EX" length="18"
	 * 
	 */
	public BigDecimal getPvAmtEx() {
		return pvAmtEx;
	}

	public void setPvAmtEx(BigDecimal pvAmtEx) {
		this.pvAmtEx = pvAmtEx;
	}

	/**
	 * * @hibernate.property column="AMOUNT_EX" length="18"
	 * 
	 */
	public BigDecimal getAmountEx() {
		return amountEx;
	}

	public void setAmountEx(BigDecimal amountEx) {
		this.amountEx = amountEx;
	}

	/**
	 * * @hibernate.property column="NEED_PAY" length="20"
	 * 
	 */
	public String getNeedPay() {
		return needPay;
	}

	public void setNeedPay(String needPay) {
		this.needPay = needPay;
	}

	/**
	 * * @hibernate.property column="WHETHER_PD" length="20"
	 * 
	 */
	public String getWhetherPd() {
		return whetherPd;
	}

	public void setWhetherPd(String whetherPd) {
		this.whetherPd = whetherPd;
	}
	
	
	/**
	 * * @hibernate.property column="NEED_PAY_AMOUNT" length="20"
	 * 
	 */
	public BigDecimal getNeedPayAmount() {
		return needPayAmount;
	}

	public void setNeedPayAmount(BigDecimal needPayAmount) {
		this.needPayAmount = needPayAmount;
	}
	
	
	
	/**
	 * * @hibernate.property column="SELF_REMARK" length="500"
	 * 
	 */
	public String getSelfRemark() {
		return selfRemark;
	}

	public void setSelfRemark(String selfRemark) {
		this.selfRemark = selfRemark;
	}

	/**
	 * * @hibernate.property column="SELF_CHECK_STATUS" length="500"
	 * 
	 */
	public String getSelfCheckStatus() {
		return selfCheckStatus;
	}

	public void setSelfCheckStatus(String selfCheckStatus) {
		this.selfCheckStatus = selfCheckStatus;
	}
	
	/**
	 * * @hibernate.property column="SEND_STATUS" length="20"
	 * 
	 */
	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	/**
	 * * @hibernate.property column="CANCEL_EXCHANGE" length="20"
	 * 
	 */
	public String getCancelExchange() {
		return cancelExchange;
	}

	public void setCancelExchange(String cancelExchange) {
		this.cancelExchange = cancelExchange;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("warehouseNo").append("='").append(getWarehouseNo()).append("' ");			
      buffer.append("customerCode").append("='").append(getCustomer().getUserCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUsrCode").append("='").append(getCreateUsrCode()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("checkUsrCode").append("='").append(getCheckUsrCode()).append("' ");			
      buffer.append("checkRemark").append("='").append(getCheckRemark()).append("' ");			
      buffer.append("okTime").append("='").append(getOkTime()).append("' ");			
      buffer.append("okUsrCode").append("='").append(getOkUsrCode()).append("' ");			
      buffer.append("okRemark").append("='").append(getOkRemark()).append("' ");			
      buffer.append("editTime").append("='").append(getEditTime()).append("' ");			
      buffer.append("editUsrCode").append("='").append(getEditUsrCode()).append("' ");			
      buffer.append("orderFlag").append("='").append(getOrderFlag()).append("' ");			
      buffer.append("stockFlag").append("='").append(getStockFlag()).append("' ");			
      buffer.append("firstName").append("='").append(getFirstName()).append("' ");			
      buffer.append("lastName").append("='").append(getLastName()).append("' ");			
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("district").append("='").append(getDistrict()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("postalcode").append("='").append(getPostalcode()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("mobiletele").append("='").append(getMobiletele()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");
      
      buffer.append("selfReplacement").append("='").append(getSelfReplacement()).append("' ");
      buffer.append("isPay").append("='").append(getIsPay()).append("' ");
      buffer.append("pvAmtEx").append("='").append(getPvAmtEx()).append("' ");
      buffer.append("amountEx").append("='").append(getAmountEx()).append("' ");
      buffer.append("needPay").append("='").append(getNeedPay()).append("' ");
      buffer.append("whetherPd").append("='").append(getWhetherPd()).append("' ");
      buffer.append("needPayAmount").append("='").append(getNeedPayAmount()).append("' ");
      buffer.append("selfRemark").append("='").append(getSelfRemark()).append("' ");
      buffer.append("selfCheckStatus").append("='").append(getSelfCheckStatus()).append("' ");
      buffer.append("sendStatus").append("='").append(getSendStatus()).append("' ");
      buffer.append("cancelExchange").append("='").append(getCancelExchange()).append("' ");

      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdExchangeOrder) ) return false;
		 PdExchangeOrder castOther = ( PdExchangeOrder ) other; 
         
		 return ( (this.getEoNo()==castOther.getEoNo()) || ( this.getEoNo()!=null && castOther.getEoNo()!=null && this.getEoNo().equals(castOther.getEoNo()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getWarehouseNo()==castOther.getWarehouseNo()) || ( this.getWarehouseNo()!=null && castOther.getWarehouseNo()!=null && this.getWarehouseNo().equals(castOther.getWarehouseNo()) ) )
 && ( (this.getCustomer().getUserCode()==castOther.getCustomer().getUserCode()) || ( this.getCustomer().getUserCode()!=null && castOther.getCustomer().getUserCode()!=null && this.getCustomer().getUserCode().equals(castOther.getCustomer().getUserCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUsrCode()==castOther.getCreateUsrCode()) || ( this.getCreateUsrCode()!=null && castOther.getCreateUsrCode()!=null && this.getCreateUsrCode().equals(castOther.getCreateUsrCode()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getCheckUsrCode()==castOther.getCheckUsrCode()) || ( this.getCheckUsrCode()!=null && castOther.getCheckUsrCode()!=null && this.getCheckUsrCode().equals(castOther.getCheckUsrCode()) ) )
 && ( (this.getCheckRemark()==castOther.getCheckRemark()) || ( this.getCheckRemark()!=null && castOther.getCheckRemark()!=null && this.getCheckRemark().equals(castOther.getCheckRemark()) ) )
 && ( (this.getOkTime()==castOther.getOkTime()) || ( this.getOkTime()!=null && castOther.getOkTime()!=null && this.getOkTime().equals(castOther.getOkTime()) ) )
 && ( (this.getOkUsrCode()==castOther.getOkUsrCode()) || ( this.getOkUsrCode()!=null && castOther.getOkUsrCode()!=null && this.getOkUsrCode().equals(castOther.getOkUsrCode()) ) )
 && ( (this.getOkRemark()==castOther.getOkRemark()) || ( this.getOkRemark()!=null && castOther.getOkRemark()!=null && this.getOkRemark().equals(castOther.getOkRemark()) ) )
 && ( (this.getEditTime()==castOther.getEditTime()) || ( this.getEditTime()!=null && castOther.getEditTime()!=null && this.getEditTime().equals(castOther.getEditTime()) ) )
 && ( (this.getEditUsrCode()==castOther.getEditUsrCode()) || ( this.getEditUsrCode()!=null && castOther.getEditUsrCode()!=null && this.getEditUsrCode().equals(castOther.getEditUsrCode()) ) )
 && ( (this.getOrderFlag()==castOther.getOrderFlag()) || ( this.getOrderFlag()!=null && castOther.getOrderFlag()!=null && this.getOrderFlag().equals(castOther.getOrderFlag()) ) )
 && ( (this.getStockFlag()==castOther.getStockFlag()) || ( this.getStockFlag()!=null && castOther.getStockFlag()!=null && this.getStockFlag().equals(castOther.getStockFlag()) ) )
 && ( (this.getFirstName()==castOther.getFirstName()) || ( this.getFirstName()!=null && castOther.getFirstName()!=null && this.getFirstName().equals(castOther.getFirstName()) ) )
 && ( (this.getLastName()==castOther.getLastName()) || ( this.getLastName()!=null && castOther.getLastName()!=null && this.getLastName().equals(castOther.getLastName()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getDistrict()==castOther.getDistrict()) || ( this.getDistrict()!=null && castOther.getDistrict()!=null && this.getDistrict().equals(castOther.getDistrict()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getPostalcode()==castOther.getPostalcode()) || ( this.getPostalcode()!=null && castOther.getPostalcode()!=null && this.getPostalcode().equals(castOther.getPostalcode()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getMobiletele()==castOther.getMobiletele()) || ( this.getMobiletele()!=null && castOther.getMobiletele()!=null && this.getMobiletele().equals(castOther.getMobiletele()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) )
 && ( (this.getSelfReplacement()==castOther.getSelfReplacement()) || ( this.getSelfReplacement()!=null && castOther.getSelfReplacement()!=null && this.getSelfReplacement().equals(castOther.getSelfReplacement()) ) )
 && ( (this.getIsPay()==castOther.getIsPay()) || ( this.getIsPay()!=null && castOther.getIsPay()!=null && this.getIsPay().equals(castOther.getIsPay()) ) )
 && ( (this.getPvAmtEx()==castOther.getPvAmtEx()) || ( this.getPvAmtEx()!=null && castOther.getPvAmtEx()!=null && this.getPvAmtEx().equals(castOther.getPvAmtEx()) ) )
 && ( (this.getAmountEx()==castOther.getAmountEx()) || ( this.getAmountEx()!=null && castOther.getAmountEx()!=null && this.getAmountEx().equals(castOther.getAmountEx()) ) )
 && ( (this.getNeedPay()==castOther.getNeedPay()) || ( this.getNeedPay()!=null && castOther.getNeedPay()!=null && this.getNeedPay().equals(castOther.getNeedPay()) ) )
 && ( (this.getWhetherPd()==castOther.getWhetherPd()) || ( this.getWhetherPd()!=null && castOther.getWhetherPd()!=null && this.getWhetherPd().equals(castOther.getWhetherPd()) ) )
 && ( (this.getNeedPayAmount()==castOther.getNeedPayAmount()) || ( this.getNeedPayAmount()!=null && castOther.getNeedPayAmount()!=null && this.getNeedPayAmount().equals(castOther.getNeedPayAmount()) ) )
 && ( (this.getSelfRemark()==castOther.getSelfRemark()) || ( this.getSelfRemark()!=null && castOther.getSelfRemark()!=null && this.getSelfRemark().equals(castOther.getSelfRemark()) ) )
 && ( (this.getSelfCheckStatus()==castOther.getSelfCheckStatus()) || ( this.getSelfCheckStatus()!=null && castOther.getSelfCheckStatus()!=null && this.getSelfCheckStatus().equals(castOther.getSelfCheckStatus()) ) )
 && ( (this.getSendStatus()==castOther.getSendStatus()) || ( this.getSendStatus()!=null && castOther.getSendStatus()!=null && this.getSendStatus().equals(castOther.getSendStatus()) ) )
 && ( (this.getCancelExchange()==castOther.getCancelExchange()) || ( this.getCancelExchange()!=null && castOther.getCancelExchange()!=null && this.getCancelExchange().equals(castOther.getCancelExchange()) ) );

   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getEoNo() == null ? 0 : this.getEoNo().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getWarehouseNo() == null ? 0 : this.getWarehouseNo().hashCode() );
         result = 37 * result + ( getCustomer().getUserCode() == null ? 0 : this.getCustomer().getUserCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUsrCode() == null ? 0 : this.getCreateUsrCode().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getCheckUsrCode() == null ? 0 : this.getCheckUsrCode().hashCode() );
         result = 37 * result + ( getCheckRemark() == null ? 0 : this.getCheckRemark().hashCode() );
         result = 37 * result + ( getOkTime() == null ? 0 : this.getOkTime().hashCode() );
         result = 37 * result + ( getOkUsrCode() == null ? 0 : this.getOkUsrCode().hashCode() );
         result = 37 * result + ( getOkRemark() == null ? 0 : this.getOkRemark().hashCode() );
         result = 37 * result + ( getEditTime() == null ? 0 : this.getEditTime().hashCode() );
         result = 37 * result + ( getEditUsrCode() == null ? 0 : this.getEditUsrCode().hashCode() );
         result = 37 * result + ( getOrderFlag() == null ? 0 : this.getOrderFlag().hashCode() );
         result = 37 * result + ( getStockFlag() == null ? 0 : this.getStockFlag().hashCode() );
         result = 37 * result + ( getFirstName() == null ? 0 : this.getFirstName().hashCode() );
         result = 37 * result + ( getLastName() == null ? 0 : this.getLastName().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getDistrict() == null ? 0 : this.getDistrict().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getPostalcode() == null ? 0 : this.getPostalcode().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getMobiletele() == null ? 0 : this.getMobiletele().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         
         result = 37 * result + ( getSelfReplacement() == null ? 0 : this.getSelfReplacement().hashCode() );
         result = 37 * result + ( getIsPay() == null ? 0 : this.getIsPay().hashCode() );
         result = 37 * result + ( getPvAmtEx() == null ? 0 : this.getPvAmtEx().hashCode() );
         result = 37 * result + ( getAmountEx() == null ? 0 : this.getAmountEx().hashCode() );
         result = 37 * result + ( getNeedPay() == null ? 0 : this.getNeedPay().hashCode() );
         result = 37 * result + ( getWhetherPd() == null ? 0 : this.getWhetherPd().hashCode() );
         result = 37 * result + ( getNeedPayAmount() == null ? 0 : this.getNeedPayAmount().hashCode() );
         result = 37 * result + ( getSelfRemark() == null ? 0 : this.getSelfRemark().hashCode() );
         result = 37 * result + ( getSelfCheckStatus() == null ? 0 : this.getSelfCheckStatus().hashCode() );
         result = 37 * result + ( getSendStatus() == null ? 0 : this.getSendStatus().hashCode() );
         result = 37 * result + ( getCancelExchange() == null ? 0 : this.getCancelExchange().hashCode() );

         return result;
   }   

}
