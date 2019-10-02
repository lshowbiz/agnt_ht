package com.joymain.jecs.pr.model;
// Generated 2009-9-22 10:20:41 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPR_REFUND"
 *     
 */
@SuppressWarnings({ "unchecked", "serial" })
public class JprRefund extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String roNo;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String companyCode;
    private BigDecimal amount;
    private BigDecimal pvAmt;
    private Date orderDate;
    private String resendSpNo;
	private SysUser sysUser;
	private String intoStatus;
    private Date intoTime;
    private String intoUNo;
    private String intoRemark;
    private String refundStatus;
    private Date refundTime;
    private String refundUNo;
    private String refundRemark;
	private JpoMemberOrder jpoMemberOrder;
	private String orderType;
    private String remark;
    private Date createTime;
    private String createUNo;
    private Date editTime;
    private String editUNo;
    private String locked;
    private String returnType;
	private Set jprReFundList = new HashSet();
	
	//modify gw 2014-10-31
	//退货单状态
	private String orderStatus;//modify by fu 2016-05-23  因为退单不用到审核，所以该字段改为：存储原订单的结算期别
	
	//退单的物流结算费用  modify gw 2014-11-03
	private String repairFee;//维修费
	private String renovationFee;//翻新费
	private String logisticsFee;//物流费
	private String settledBonus;//不能扣回的奖金（已结算）
	private String fillFreight;//回补运费
	private String logisticsFeeSix;//退货单消息是否推送出去标识：isNew表明退货单没有推送出去;noNew表明退货单已经推送出去   MODIFY FX 20150817
	private String logisticsFeeSeven;//退货单是否是新单;Y新单，N不是新单  modify by fu 2016-03-16
	private String logisticsFeeEight;//退货单编辑类型：21退货单编辑、22退货单删除 modify by fu 2016-03-21
	private Integer stjFlag;//生态家退单标识 5/20 新增字段
	/*private String repairFee;
	private String renovationFee;
	private String logisticsFeeThree;
	private String settledBonus;
	private String fillFreight;*/
	//modify fu 2015-11-11 fu 退单类型:0表示正常；1表示5折退货
	private String refundTye;

	//modify by fu 修改退货单统计时保存退货单的奇怪问题
	private String jpoCheckDate;//订单的结算期别
	
	
	
    // Constructors

	/** default constructor */
    public JprRefund() {
    }

	/** minimal constructor */
    public JprRefund(String companyCode, BigDecimal amount, BigDecimal pvAmt, Date orderDate, String resendSpNo, String userCode, String intoStatus, String refundStatus, Date createTime, String createUNo,String orderStatus) {
        this.companyCode = companyCode;
        this.amount = amount;
        this.pvAmt = pvAmt;
        this.orderDate = orderDate;
        this.resendSpNo = resendSpNo;
        this.intoStatus = intoStatus;
        this.refundStatus = refundStatus;
        this.createTime = createTime;
        this.createUNo = createUNo;
        this.orderStatus = orderStatus;
    }
    
    /** full constructor */
    public JprRefund(Integer wyear, Integer wmonth, Integer wweek, String companyCode, BigDecimal amount, BigDecimal pvAmt, Date orderDate, String resendSpNo, String userCode, String intoStatus, Date intoTime, String intoUNo, String intoRemark, String refundStatus, Date refundTime, String refundUNo, String refundRemark, Long moId, String orderType, String remark, Date createTime, String createUNo, Date editTime, String editUNo, String locked, String returnType,String orderStatus,
    		String repairFee,String renovationFee,String logisticsFee,String settledBonus,String fillFreight,
    		String logisticsFeeSix,String logisticsFeeSeven,String logisticsFeeEight,String refundType) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.companyCode = companyCode;
        this.amount = amount;
        this.pvAmt = pvAmt;
        this.orderDate = orderDate;
        this.resendSpNo = resendSpNo;
        this.intoStatus = intoStatus;
        this.intoTime = intoTime;
        this.intoUNo = intoUNo;
        this.intoRemark = intoRemark;
        this.refundStatus = refundStatus;
        this.refundTime = refundTime;
        this.refundUNo = refundUNo;
        this.refundRemark = refundRemark;
        this.orderType = orderType;
        this.remark = remark;
        this.createTime = createTime;
        this.createUNo = createUNo;
        this.editTime = editTime;
        this.editUNo = editUNo;
        this.locked = locked;
        this.returnType = returnType;
        this.orderStatus = orderStatus;
        
        this.repairFee = repairFee;
        this.renovationFee = renovationFee;
        this.logisticsFee = logisticsFee;
        this.settledBonus = settledBonus;
        this.fillFreight = fillFreight;
        this.logisticsFeeSix = logisticsFeeSix;
        this.logisticsFeeSeven = logisticsFeeSeven;
        this.logisticsFeeEight = logisticsFeeEight;
        this.refundTye = refundType;

    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="RO_NO"
     *         
     */

    public String getRoNo() {
        return this.roNo;
    }
    
    public void setRoNo(String roNo) {
        this.roNo = roNo;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="STJ_FLAG"
     *         
     */
    public Integer getStjFlag() {
		return stjFlag;
	}

	public void setStjFlag(Integer stjFlag) {
		this.stjFlag = stjFlag;
	}

	/**       
     *      *            @hibernate.property
     *             column="W_YEAR"
     *             length="8"
     *         
     */

    public Integer getWyear() {
        return this.wyear;
    }
    
    public void setWyear(Integer wyear) {
        this.wyear = wyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_MONTH"
     *             length="8"
     *         
     */

    public Integer getWmonth() {
        return this.wmonth;
    }
    
    public void setWmonth(Integer wmonth) {
        this.wmonth = wmonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_WEEK"
     *             length="8"
     *         
     */

    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
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
     *             column="AMOUNT"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**       
     *      *            @hibernate.property
     *             column="PV_AMT"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getPvAmt() {
        return this.pvAmt;
    }
    
    public void setPvAmt(BigDecimal pvAmt) {
        this.pvAmt = pvAmt;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_DATE"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="RESEND_SP_NO"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getResendSpNo() {
        return this.resendSpNo;
    }
    /**
	 * @spring.validator type="required,maxlength"
	 * @spring.validator-args arg0resource="prRefund.resendSpNo"
	 * @spring.validator-var name="maxlength" value="20"
	 * @spring.validator-args arg1value="20"
	 */
    public void setResendSpNo(String resendSpNo) {
        this.resendSpNo = resendSpNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="INTO_STATUS"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getIntoStatus() {
        return this.intoStatus;
    }
    
    public void setIntoStatus(String intoStatus) {
        this.intoStatus = intoStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="INTO_TIME"
     *             length="7"
     *         
     */

    public Date getIntoTime() {
        return this.intoTime;
    }
    
    public void setIntoTime(Date intoTime) {
        this.intoTime = intoTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="INTO_U_NO"
     *             length="20"
     *         
     */

    public String getIntoUNo() {
        return this.intoUNo;
    }
    
    public void setIntoUNo(String intoUNo) {
        this.intoUNo = intoUNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="INTO_REMARK"
     *             length="200"
     *         
     */

    public String getIntoRemark() {
        return this.intoRemark;
    }
    
    public void setIntoRemark(String intoRemark) {
        this.intoRemark = intoRemark;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFUND_STATUS"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getRefundStatus() {
        return this.refundStatus;
    }
    
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFUND_TIME"
     *             length="7"
     *         
     */

    public Date getRefundTime() {
        return this.refundTime;
    }
    
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFUND_U_NO"
     *             length="20"
     *         
     */

    public String getRefundUNo() {
        return this.refundUNo;
    }
    
    public void setRefundUNo(String refundUNo) {
        this.refundUNo = refundUNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="REFUND_REMARK"
     *             length="200"
     *         
     */

    public String getRefundRemark() {
        return this.refundRemark;
    }
    
    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }

	/**
	 * @hibernate.many-to-one not-found="ignore" column="MO_ID" cascade="none"
	 * @return
	 */
    public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}

	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
	}

    /**       
     *      *            @hibernate.property
     *             column="ORDER_TYPE"
     *             length="1"
     *         
     */

    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="4000"
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
     *             column="CREATE_U_NO"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getCreateUNo() {
        return this.createUNo;
    }
    
    public void setCreateUNo(String createUNo) {
        this.createUNo = createUNo;
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
     *             column="EDIT_U_NO"
     *             length="20"
     *         
     */

    public String getEditUNo() {
        return this.editUNo;
    }
    
    public void setEditUNo(String editUNo) {
        this.editUNo = editUNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="LOCKED"
     *             length="1"
     *         
     */

    public String getLocked() {
        return this.locked;
    }
    
    public void setLocked(String locked) {
        this.locked = locked;
    }
    /**       
     *      *            @hibernate.property
     *             column="RETURN_TYPE"
     *             length="1"
     *         
     */

    public String getReturnType() {
        return this.returnType;
    }
    
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
	/**
	 * @hibernate.many-to-one column="USER_CODE" not-null="true"
	 * @return
	 */

    public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	/**
	 * @hibernate.set lazy="true" order-by="PRL_ID" cascade="all" inverse="true"
	 * @hibernate.collection-key column="RO_NO"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.pr.model.JprRefundList"
	 * @return
	 */
    public Set getJprReFundList() {
		return jprReFundList;
	}

	public void setJprReFundList(Set jprReFundList) {
		this.jprReFundList = jprReFundList;
	}
	
	

   
	/**       
     *      *            @hibernate.property
     *             column="ORDER_STATUS"
     *             length="20"
     *         
     */
    public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	/**       
     *      *            @hibernate.property
     *             column="REPAIR_FEE"
     *             length="200"
     *         
     */
	public String getRepairFee() {
		return repairFee;
	}

	public void setRepairFee(String repairFee) {
		this.repairFee = repairFee;
	}

	/**       
     *      *            @hibernate.property
     *             column="RENOVATION_FEE"
     *             length="200"
     *         
     */
	public String getRenovationFee() {
		return renovationFee;
	}

	public void setRenovationFee(String renovationFee) {
		this.renovationFee = renovationFee;
	}

	/**       
     *      *            @hibernate.property
     *             column="LOGISTICS_FEE"
     *             length="200"
     *         
     */
	public String getLogisticsFee() {
		return logisticsFee;
	}

	public void setLogisticsFee(String logisticsFee) {
		this.logisticsFee = logisticsFee;
	}

	/**       
     *      *            @hibernate.property
     *             column="SETTLED_BONUS"
     *             length="200"
     *         
     */
	public String getSettledBonus() {
		return settledBonus;
	}

	public void setSettledBonus(String settledBonus) {
		this.settledBonus = settledBonus;
	}

	/**       
     *      *            @hibernate.property
     *             column="FILL_FREIGHT"
     *             length="200"
     *         
     */
	public String getFillFreight() {
		return fillFreight;
	}

	public void setFillFreight(String fillFreight) {
		this.fillFreight = fillFreight;
	}

	/**       
     *      *            @hibernate.property
     *             column="LOGISTICS_FEE_SIX"
     *             length="200"
     *         
     */
	public String getLogisticsFeeSix() {
		return logisticsFeeSix;
	}

	public void setLogisticsFeeSix(String logisticsFeeSix) {
		this.logisticsFeeSix = logisticsFeeSix;
	}

	/**       
     *      *            @hibernate.property
     *             column="LOGISTICS_FEE_SEVEN"
     *             length="200"
     *         
     */
	public String getLogisticsFeeSeven() {
		return logisticsFeeSeven;
	}

	public void setLogisticsFeeSeven(String logisticsFeeSeven) {
		this.logisticsFeeSeven = logisticsFeeSeven;
	}

	/**       
     *      *            @hibernate.property
     *             column="LOGISTICS_FEE_EIGHT"
     *             length="200"
     *         
     */
	public String getLogisticsFeeEight() {
		return logisticsFeeEight;
	}

	public void setLogisticsFeeEight(String logisticsFeeEight) {
		this.logisticsFeeEight = logisticsFeeEight;
	}
	
	/**       
     *      *            @hibernate.property
     *             column="REFUND_TYPE"
     *             length="20"
     *         
     */
	public String getRefundTye() {
		return refundTye;
	}

	public void setRefundTye(String refundTye) {
		this.refundTye = refundTye;
	}
	
	public String getJpoCheckDate() {
		return jpoCheckDate;
	}

	public void setJpoCheckDate(String jpoCheckDate) {
		this.jpoCheckDate = jpoCheckDate;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("wyear").append("='").append(getWyear()).append("' ");			
      buffer.append("wmonth").append("='").append(getWmonth()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("amount").append("='").append(getAmount()).append("' ");			
      buffer.append("pvAmt").append("='").append(getPvAmt()).append("' ");			
      buffer.append("orderDate").append("='").append(getOrderDate()).append("' ");			
      buffer.append("resendSpNo").append("='").append(getResendSpNo()).append("' ");			
      buffer.append("intoStatus").append("='").append(getIntoStatus()).append("' ");			
      buffer.append("intoTime").append("='").append(getIntoTime()).append("' ");			
      buffer.append("intoUNo").append("='").append(getIntoUNo()).append("' ");			
      buffer.append("intoRemark").append("='").append(getIntoRemark()).append("' ");			
      buffer.append("refundStatus").append("='").append(getRefundStatus()).append("' ");			
      buffer.append("refundTime").append("='").append(getRefundTime()).append("' ");			
      buffer.append("refundUNo").append("='").append(getRefundUNo()).append("' ");			
      buffer.append("refundRemark").append("='").append(getRefundRemark()).append("' ");			
      buffer.append("orderType").append("='").append(getOrderType()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUNo").append("='").append(getCreateUNo()).append("' ");			
      buffer.append("editTime").append("='").append(getEditTime()).append("' ");			
      buffer.append("editUNo").append("='").append(getEditUNo()).append("' ");			
      buffer.append("locked").append("='").append(getLocked()).append("' ");			
      buffer.append("returnType").append("='").append(getReturnType()).append("' ");
      buffer.append("orderStatus").append("='").append(getOrderStatus()).append("' ");
      
      buffer.append("repairFee").append("='").append(getRepairFee()).append("' ");
      buffer.append("renovationFee").append("='").append(getRenovationFee()).append("' ");
      buffer.append("logisticsFee").append("='").append(getLogisticsFee()).append("' ");
      buffer.append("settledBonus").append("='").append(getSettledBonus()).append("' ");
      buffer.append("fillFreight").append("='").append(getFillFreight()).append("' ");
      buffer.append("logisticsFeeSix").append("='").append(getLogisticsFeeSix()).append("' ");
      buffer.append("logisticsFeeSeven").append("='").append(getLogisticsFeeSeven()).append("' ");
      buffer.append("logisticsFeeEight").append("='").append(getLogisticsFeeEight()).append("' ");
      
      buffer.append("refundTye").append("='").append(getRefundTye()).append("' ");

 
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JprRefund) ) return false;
		 JprRefund castOther = ( JprRefund ) other; 
         
		 return ( (this.getRoNo()==castOther.getRoNo()) || ( this.getRoNo()!=null && castOther.getRoNo()!=null && this.getRoNo().equals(castOther.getRoNo()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && (this.getPvAmt()==castOther.getPvAmt())
 && ( (this.getOrderDate()==castOther.getOrderDate()) || ( this.getOrderDate()!=null && castOther.getOrderDate()!=null && this.getOrderDate().equals(castOther.getOrderDate()) ) )
 && ( (this.getResendSpNo()==castOther.getResendSpNo()) || ( this.getResendSpNo()!=null && castOther.getResendSpNo()!=null && this.getResendSpNo().equals(castOther.getResendSpNo()) ) )
 && ( (this.getIntoStatus()==castOther.getIntoStatus()) || ( this.getIntoStatus()!=null && castOther.getIntoStatus()!=null && this.getIntoStatus().equals(castOther.getIntoStatus()) ) )
 && ( (this.getIntoTime()==castOther.getIntoTime()) || ( this.getIntoTime()!=null && castOther.getIntoTime()!=null && this.getIntoTime().equals(castOther.getIntoTime()) ) )
 && ( (this.getIntoUNo()==castOther.getIntoUNo()) || ( this.getIntoUNo()!=null && castOther.getIntoUNo()!=null && this.getIntoUNo().equals(castOther.getIntoUNo()) ) )
 && ( (this.getIntoRemark()==castOther.getIntoRemark()) || ( this.getIntoRemark()!=null && castOther.getIntoRemark()!=null && this.getIntoRemark().equals(castOther.getIntoRemark()) ) )
 && ( (this.getRefundStatus()==castOther.getRefundStatus()) || ( this.getRefundStatus()!=null && castOther.getRefundStatus()!=null && this.getRefundStatus().equals(castOther.getRefundStatus()) ) )
 && ( (this.getRefundTime()==castOther.getRefundTime()) || ( this.getRefundTime()!=null && castOther.getRefundTime()!=null && this.getRefundTime().equals(castOther.getRefundTime()) ) )
 && ( (this.getRefundUNo()==castOther.getRefundUNo()) || ( this.getRefundUNo()!=null && castOther.getRefundUNo()!=null && this.getRefundUNo().equals(castOther.getRefundUNo()) ) )
 && ( (this.getRefundRemark()==castOther.getRefundRemark()) || ( this.getRefundRemark()!=null && castOther.getRefundRemark()!=null && this.getRefundRemark().equals(castOther.getRefundRemark()) ) )
 && ( (this.getOrderType()==castOther.getOrderType()) || ( this.getOrderType()!=null && castOther.getOrderType()!=null && this.getOrderType().equals(castOther.getOrderType()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUNo()==castOther.getCreateUNo()) || ( this.getCreateUNo()!=null && castOther.getCreateUNo()!=null && this.getCreateUNo().equals(castOther.getCreateUNo()) ) )
 && ( (this.getEditTime()==castOther.getEditTime()) || ( this.getEditTime()!=null && castOther.getEditTime()!=null && this.getEditTime().equals(castOther.getEditTime()) ) )
 && ( (this.getEditUNo()==castOther.getEditUNo()) || ( this.getEditUNo()!=null && castOther.getEditUNo()!=null && this.getEditUNo().equals(castOther.getEditUNo()) ) )
 && ( (this.getLocked()==castOther.getLocked()) || ( this.getLocked()!=null && castOther.getLocked()!=null && this.getLocked().equals(castOther.getLocked()) ) )
 && ( (this.getReturnType()==castOther.getReturnType()) || ( this.getReturnType()!=null && castOther.getReturnType()!=null && this.getReturnType().equals(castOther.getReturnType()) ) )
 && ( (this.getOrderStatus()==castOther.getOrderStatus()) || ( this.getOrderStatus()!=null && castOther.getOrderStatus()!=null && this.getOrderStatus().equals(castOther.getOrderStatus()) ) )
 
 && ( (this.getRepairFee()==castOther.getRepairFee()) || ( this.getRepairFee()!=null && castOther.getRepairFee()!=null && this.getRepairFee().equals(castOther.getRepairFee()) ) )
 && ( (this.getRenovationFee()==castOther.getRenovationFee()) || ( this.getRenovationFee()!=null && castOther.getRenovationFee()!=null && this.getRenovationFee().equals(castOther.getRenovationFee()) ) )
 && ( (this.getLogisticsFee()==castOther.getLogisticsFee()) || ( this.getLogisticsFee()!=null && castOther.getLogisticsFee()!=null && this.getLogisticsFee().equals(castOther.getLogisticsFee()) ) )
 && ( (this.getSettledBonus()==castOther.getSettledBonus()) || ( this.getSettledBonus()!=null && castOther.getSettledBonus()!=null && this.getSettledBonus().equals(castOther.getSettledBonus()) ) )
 && ( (this.getFillFreight()==castOther.getFillFreight()) || ( this.getFillFreight()!=null && castOther.getFillFreight()!=null && this.getFillFreight().equals(castOther.getFillFreight()) ) )
 && ( (this.getLogisticsFeeSix()==castOther.getLogisticsFeeSix()) || ( this.getLogisticsFeeSix()!=null && castOther.getLogisticsFeeSix()!=null && this.getLogisticsFeeSix().equals(castOther.getLogisticsFeeSix()) ) )
 && ( (this.getLogisticsFeeSeven()==castOther.getLogisticsFeeSeven()) || ( this.getLogisticsFeeSeven()!=null && castOther.getLogisticsFeeSeven()!=null && this.getLogisticsFeeSeven().equals(castOther.getLogisticsFeeSeven()) ) )
 && ( (this.getLogisticsFeeEight()==castOther.getLogisticsFeeEight()) || ( this.getLogisticsFeeEight()!=null && castOther.getLogisticsFeeEight()!=null && this.getLogisticsFeeEight().equals(castOther.getLogisticsFeeEight()) ) )
 && ( (this.getRefundTye()==castOther.getRefundTye()) || ( this.getRefundTye()!=null && castOther.getRefundTye()!=null && this.getRefundTye().equals(castOther.getRefundTye()) ) );

   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRoNo() == null ? 0 : this.getRoNo().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getPvAmt() == null ? 0 : this.getPvAmt().hashCode() );
         result = 37 * result + ( getOrderDate() == null ? 0 : this.getOrderDate().hashCode() );
         result = 37 * result + ( getResendSpNo() == null ? 0 : this.getResendSpNo().hashCode() );
         result = 37 * result + ( getIntoStatus() == null ? 0 : this.getIntoStatus().hashCode() );
         result = 37 * result + ( getIntoTime() == null ? 0 : this.getIntoTime().hashCode() );
         result = 37 * result + ( getIntoUNo() == null ? 0 : this.getIntoUNo().hashCode() );
         result = 37 * result + ( getIntoRemark() == null ? 0 : this.getIntoRemark().hashCode() );
         result = 37 * result + ( getRefundStatus() == null ? 0 : this.getRefundStatus().hashCode() );
         result = 37 * result + ( getRefundTime() == null ? 0 : this.getRefundTime().hashCode() );
         result = 37 * result + ( getRefundUNo() == null ? 0 : this.getRefundUNo().hashCode() );
         result = 37 * result + ( getRefundRemark() == null ? 0 : this.getRefundRemark().hashCode() );
         result = 37 * result + ( jpoMemberOrder.getMoId() == null ? 0 : this.jpoMemberOrder.getMoId().hashCode() );
         result = 37 * result + ( getOrderType() == null ? 0 : this.getOrderType().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUNo() == null ? 0 : this.getCreateUNo().hashCode() );
         result = 37 * result + ( getEditTime() == null ? 0 : this.getEditTime().hashCode() );
         result = 37 * result + ( getEditUNo() == null ? 0 : this.getEditUNo().hashCode() );
         result = 37 * result + ( getLocked() == null ? 0 : this.getLocked().hashCode() );
         result = 37 * result + ( getReturnType() == null ? 0 : this.getReturnType().hashCode() );
         result = 37 * result + ( getOrderStatus() == null ? 0 : this.getOrderStatus().hashCode() );
         
         result = 37 * result + ( getRepairFee() == null ? 0 : this.getRepairFee().hashCode() );
         result = 37 * result + ( getRenovationFee() == null ? 0 : this.getRenovationFee().hashCode() );
         result = 37 * result + ( getLogisticsFee() == null ? 0 : this.getLogisticsFee().hashCode() );
         result = 37 * result + ( getSettledBonus() == null ? 0 : this.getSettledBonus().hashCode() );
         result = 37 * result + ( getFillFreight() == null ? 0 : this.getFillFreight().hashCode() );
         result = 37 * result + ( getLogisticsFeeSix() == null ? 0 : this.getLogisticsFeeSix().hashCode() );
         result = 37 * result + ( getLogisticsFeeSeven() == null ? 0 : this.getLogisticsFeeSeven().hashCode() );
         result = 37 * result + ( getLogisticsFeeEight() == null ? 0 : this.getLogisticsFeeEight().hashCode() );
         result = 37 * result + ( getRefundTye() == null ? 0 : this.getRefundTye().hashCode() );
         
         return result;
   }   





}
