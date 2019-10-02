package com.joymain.jecs.bd.model;
// Generated 2009-9-23 11:32:51 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.sys.model.SysUser;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_YD_REBATE_Hist"
 *     
 */

public class JbdYdRebateHist extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	  /** default constructor */
    public JbdYdRebateHist() {
    }
    private Long id;
    private JmiMember jmiMember;
    private String miUserName;
    private Integer memberLevel;
    private Date calcTime;
    private String rebateUserCode;
    private String rebateOrderNo;
	private BigDecimal rebateRatio;
    private BigDecimal rebateAmount;
    private BigDecimal sendAmount;
    private String memberType;
    private Integer freezeStatus;
    private Integer sendStatus;
    private Date sendDate;
    private String sendOperator;
    //
    private Integer ydStoreLevel;
    

 	/**       
    *      *            @hibernate.property
    *             column="YD_STORE_LEVEL"
    *             length="100"
    *         
    */
    public Integer getYdStoreLevel() {
		return ydStoreLevel;
	}

	public void setYdStoreLevel(Integer ydStoreLevel) {
		this.ydStoreLevel = ydStoreLevel;
	}

	// Property accessors
     /**       
      *      *            @hibernate.id
      *             generator-class="assigned"
      *             type="java.lang.Long"
      *             column="ID"
      *         
      */

     public Long getId() {
         return this.id;
     }
     
     public void setId(Long id) {
         this.id = id;
     }
     /**
      * *
      * @hibernate.many-to-one
      * @hibernate.column name="USER_CODE"
      * 
      */
  	public JmiMember getJmiMember() {
  		return jmiMember;
  	}
  	
  	
  	public void setJmiMember(JmiMember jmiMember) {
  		this.jmiMember = jmiMember;
  	}

  	 /**       
     *      *            @hibernate.property
     *             column="MI_USER_NAME"
     *             length="100"
     *         
     */
     public String getMiUserName() {
 		return miUserName;
 	}

 	public void setMiUserName(String miUserName) {
 		this.miUserName = miUserName;
 	}

 	 /**       
     *      *            @hibernate.property
     *             column="MEMBER_LEVEL"
     *             length="100"
     *         
     */
 	public Integer getMemberLevel() {
 		return memberLevel;
 	}

 	public void setMemberLevel(Integer memberLevel) {
 		this.memberLevel = memberLevel;
 	}

 	 /**       
     *      *            @hibernate.property
     *             column="CALC_TIME"
     *             length="100"
     *         
     */
 	public Date getCalcTime() {
 		return calcTime;
 	}
 	public void setCalcTime(Date calcTime) {
 		this.calcTime = calcTime;
 	}

 	 /**       
     *      *            @hibernate.property
     *             column="REBATE_USER_CODE"
     *             length="100"
     *         
     */
 	public String getRebateUserCode() {
 		return rebateUserCode;
 	}
 	public void setRebateUserCode(String rebateUserCode) {
 		this.rebateUserCode = rebateUserCode;
 	}

 	 /**       
     *      *            @hibernate.property
     *             column="REBATE_ORDER_NO"
     *             length="100"
     *         
     */
 	public String getRebateOrderNo() {
 		return rebateOrderNo;
 	}

 	public void setRebateOrderNo(String rebateOrderNo) {
 		this.rebateOrderNo = rebateOrderNo;
 	}

 	 /**       
     *      *            @hibernate.property
     *             column="REBATE_RATIO"
     *             length="100"
     *         
     */
 	public BigDecimal getRebateRatio() {
 		return rebateRatio;
 	}

 	public void setRebateRatio(BigDecimal rebateRatio) {
 		this.rebateRatio = rebateRatio;
 	}

 	 /**       
     *      *            @hibernate.property
     *             column="REBATE_AMOUNT"
     *             length="100"
     *         
     */
 	public BigDecimal getRebateAmount() {
 		return rebateAmount;
 	}

 	public void setRebateAmount(BigDecimal rebateAmount) {
 		this.rebateAmount = rebateAmount;
 	}

 	 /**       
     *      *            @hibernate.property
     *             column="SEND_AMOUNT"
     *             length="100"
     *         
     */
 	public BigDecimal getSendAmount() {
 		return sendAmount;
 	}

 	public void setSendAmount(BigDecimal sendAmount) {
 		this.sendAmount = sendAmount;
 	}
 	

 	 /**       
     *      *            @hibernate.property
     *             column="MEMBER_TYPE"
     *             length="100"
     *         
     */
 	public String getMemberType() {
 		return memberType;
 	}

 	public void setMemberType(String memberType) {
 		this.memberType = memberType;
 	}
 	

 	 /**       
     *      *            @hibernate.property
     *             column="FREEZE_STATUS"
     *             length="100"
     *         
     */
 	public Integer getFreezeStatus() {
 		return freezeStatus;
 	}

 	public void setFreezeStatus(Integer freezeStatus) {
 		this.freezeStatus = freezeStatus;
 	}

 	 /**       
     *      *            @hibernate.property
     *             column="SEND_STATUS"
     *             length="100"
     *         
     */
 	public Integer getSendStatus() {
 		return sendStatus;
 	}
 	public void setSendStatus(Integer sendStatus) {
 		this.sendStatus = sendStatus;
 	}
 	 /**       
     *      *            @hibernate.property
     *             column="SEND_DATE"
     *             length="100"
     *         
     */
 	public Date getSendDate() {
 		return sendDate;
 	}

 	public void setSendDate(Date sendDate) {
 		this.sendDate = sendDate;
 	}
 	
 	 /**       
     *      *            @hibernate.property
     *             column="SEND_OPERATOR"
     *             length="100"
     *         
     */
    public String getSendOperator() {
 		return sendOperator;
 	}


 	public void setSendOperator(String sendOperator) {
 		this.sendOperator = sendOperator;
 	}


	public JbdYdRebateHist(JmiMember jmiMember, String miUserName, Integer memberLevel, Date calcTime,
			String rebateUserCode, String rebateOrderNo, BigDecimal rebateRatio, BigDecimal rebateAmount,
			BigDecimal sendAmount, String memberType, Integer freezeStatus, Integer sendStatus, Date sendDate,
			String sendOperator) {
		super();
		this.jmiMember = jmiMember;
		this.miUserName = miUserName;
		this.memberLevel = memberLevel;
		this.calcTime = calcTime;
		this.rebateUserCode = rebateUserCode;
		this.rebateOrderNo = rebateOrderNo;
		this.rebateRatio = rebateRatio;
		this.rebateAmount = rebateAmount;
		this.sendAmount = sendAmount;
		this.memberType = memberType;
		this.freezeStatus = freezeStatus;
		this.sendStatus = sendStatus;
		this.sendDate = sendDate;
		this.sendOperator = sendOperator;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("id").append("='").append(getId()).append("' ");			
      buffer.append("miUserName").append("='").append(getMiUserName()).append("' ");
      buffer.append("memberLevel").append("='").append(getMemberLevel()).append("' ");	
      buffer.append("calcTime").append("='").append(getCalcTime()).append("' ");	
      buffer.append("rebateUserCode").append("='").append(getRebateUserCode()).append("' ");	
      buffer.append("rebateOrderNo").append("='").append(getRebateOrderNo()).append("' ");	
      buffer.append("rebateRatio").append("='").append(getRebateRatio()).append("' ");	
      buffer.append("rebateAmount").append("='").append(getRebateAmount()).append("' ");	
      buffer.append("memberType").append("='").append(getMemberType()).append("' ");	
      buffer.append("freezeStatus").append("='").append(getFreezeStatus()).append("' ");	
      buffer.append("sendStatus").append("='").append(getSendStatus()).append("' ");	
      buffer.append("sendDate").append("='").append(getSendDate()).append("' ");	
      buffer.append("sendOperator").append("='").append(getSendOperator()).append("' ");	
      
      buffer.append("]");
      
      return buffer.toString();
     }

	@Override
	public int hashCode() {
        int result = 17;
        
        result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
        result = 37 * result + ( getMiUserName() == null ? 0 : this.getMiUserName().hashCode() );
        result = 37 * result + ( getMemberLevel() == null ? 0 : this.getMemberLevel().hashCode() );
        result = 37 * result + ( getCalcTime() == null ? 0 : this.getCalcTime().hashCode() );
        result = 37 * result + ( getRebateUserCode() == null ? 0 : this.getRebateUserCode().hashCode() );
        result = 37 * result + ( getRebateOrderNo() == null ? 0 : this.getRebateOrderNo().hashCode() );
        result = 37 * result + ( getRebateRatio() == null ? 0 : this.getRebateRatio().hashCode() );
        result = 37 * result + ( getRebateAmount() == null ? 0 : this.getRebateAmount().hashCode() );
        result = 37 * result + ( getSendAmount() == null ? 0 : this.getSendAmount().hashCode() );
        result = 37 * result + ( getMemberType() == null ? 0 : this.getMemberType().hashCode() );
        result = 37 * result + ( getFreezeStatus() == null ? 0 : this.getFreezeStatus().hashCode() );
        result = 37 * result + ( getSendStatus() == null ? 0 : this.getSendStatus().hashCode() );
        result = 37 * result + ( getSendDate() == null ? 0 : this.getSendDate().hashCode() );
        result = 37 * result + ( getSendOperator() == null ? 0 : this.getSendOperator().hashCode() );
        return result;
	}
	 public boolean equals(Object other) {
	       
	        if ( (this == other ) ) return true;
			if ( (other == null ) ) return false;
			if ( !(other instanceof JbdYdRebateHist) ) return false;
			JbdYdRebateHist castOther = ( JbdYdRebateHist ) other; 
	         
			 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
			 && ( (this.getMiUserName()==castOther.getMiUserName()) || ( this.getMiUserName()!=null && castOther.getMiUserName()!=null && this.getMiUserName().equals(castOther.getMiUserName()) ) )
			 && ( (this.getMemberLevel()==castOther.getMemberLevel()) || ( this.getMemberLevel()!=null && castOther.getMemberLevel()!=null && this.getMemberLevel().equals(castOther.getMemberLevel()) ) )
			 && ( (this.getCalcTime()==castOther.getCalcTime()) || ( this.getCalcTime()!=null && castOther.getCalcTime()!=null && this.getCalcTime().equals(castOther.getCalcTime()) ) )
			 && ( (this.getRebateUserCode()==castOther.getRebateUserCode()) || ( this.getRebateUserCode()!=null && castOther.getRebateUserCode()!=null && this.getRebateUserCode().equals(castOther.getRebateUserCode()) ) )
			 && ( (this.getRebateOrderNo()==castOther.getRebateOrderNo()) || ( this.getRebateOrderNo()!=null && castOther.getRebateOrderNo()!=null && this.getRebateOrderNo().equals(castOther.getRebateOrderNo()) ) )
			 && ( (this.getRebateRatio()==castOther.getRebateRatio()) || ( this.getRebateRatio()!=null && castOther.getRebateRatio()!=null && this.getRebateRatio().equals(castOther.getRebateRatio()) ) )
			 && ( (this.getRebateAmount()==castOther.getRebateAmount()) || ( this.getRebateAmount()!=null && castOther.getRebateAmount()!=null && this.getRebateAmount().equals(castOther.getRebateAmount()) ) )
			 && ( (this.getSendAmount()==castOther.getSendAmount()) || ( this.getSendAmount()!=null && castOther.getSendAmount()!=null && this.getSendAmount().equals(castOther.getSendAmount()) ) )
			 && ( (this.getMemberType()==castOther.getMemberType()) || ( this.getMemberType()!=null && castOther.getMemberType()!=null && this.getMemberType().equals(castOther.getMemberType()) ) )
			 && ( (this.getFreezeStatus()==castOther.getFreezeStatus()) || ( this.getFreezeStatus()!=null && castOther.getFreezeStatus()!=null && this.getFreezeStatus().equals(castOther.getFreezeStatus()) ) )
			 && ( (this.getSendStatus()==castOther.getSendStatus()) || ( this.getSendStatus()!=null && castOther.getSendStatus()!=null && this.getSendStatus().equals(castOther.getSendStatus()) ) )
			 && ( (this.getSendOperator()==castOther.getSendOperator()) || ( this.getSendOperator()!=null && castOther.getSendOperator()!=null && this.getSendOperator().equals(castOther.getSendOperator()) ) )
			 && ( (this.getSendDate()==castOther.getSendDate()) || ( this.getSendDate()!=null && castOther.getSendDate()!=null && this.getSendDate().equals(castOther.getSendDate()) ) );
	   
   }
	


	






}
