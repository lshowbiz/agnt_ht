package com.joymain.jecs.bd.model;
// Generated 2011-1-7 14:07:16 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_SEND_NOTE"
 *     
 */

public class JbdSendNote extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String companyCode;
    private JmiMember jmiMember;
    private String remittanceBNum;
    private String operaterCode;
    private Date operaterTime;
    private Date sendDate;
    private String registerStatus;
    private String reissueStatus;
    private Date supplyTime;
    private String sendRemark;
    private String createNo;
    private Date createTime;
    private BigDecimal remittanceMoney;
    private BigDecimal fee;
    private String sendLateCause;
    private String sendLateRemark;


    // Constructors

    /** default constructor */
    public JbdSendNote() {
    }

    
    /** full constructor */
    public JbdSendNote(String companyCode, String userCode, String remittanceBNum, String operaterCode, Date operaterTime, Date sendDate, String registerStatus, String reissueStatus, Date supplyTime, String sendRemark, String createNo, Date createTime, BigDecimal remittanceMoney, String noteNo, BigDecimal fee) {
        this.companyCode = companyCode;
        this.remittanceBNum = remittanceBNum;
        this.operaterCode = operaterCode;
        this.operaterTime = operaterTime;
        this.sendDate = sendDate;
        this.registerStatus = registerStatus;
        this.reissueStatus = reissueStatus;
        this.supplyTime = supplyTime;
        this.sendRemark = sendRemark;
        this.createNo = createNo;
        this.createTime = createTime;
        this.remittanceMoney = remittanceMoney;
        this.fee = fee;
    }
    

   
    // Property accessors

    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
     *         
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
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
     *             column="REMITTANCE_B_NUM"
     *             length="100"
     *         
     */

    public String getRemittanceBNum() {
        return this.remittanceBNum;
    }
    
    public void setRemittanceBNum(String remittanceBNum) {
        this.remittanceBNum = remittanceBNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATER_CODE"
     *             length="100"
     *         
     */

    public String getOperaterCode() {
        return this.operaterCode;
    }
    
    public void setOperaterCode(String operaterCode) {
        this.operaterCode = operaterCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATER_TIME"
     *             length="7"
     *         
     */

    public Date getOperaterTime() {
        return this.operaterTime;
    }
    
    public void setOperaterTime(Date operaterTime) {
        this.operaterTime = operaterTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_DATE"
     *             length="7"
     *         
     */

    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="REGISTER_STATUS"
     *             length="1"
     *         
     */

    public String getRegisterStatus() {
        return this.registerStatus;
    }
    
    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="REISSUE_STATUS"
     *             length="1"
     *         
     */

    public String getReissueStatus() {
        return this.reissueStatus;
    }
    
    public void setReissueStatus(String reissueStatus) {
        this.reissueStatus = reissueStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUPPLY_TIME"
     *             length="7"
     *         
     */

    public Date getSupplyTime() {
        return this.supplyTime;
    }
    
    public void setSupplyTime(Date supplyTime) {
        this.supplyTime = supplyTime;
    }


    /**       
     *      *            @hibernate.property
     *             column="SEND_REMARK"
     *             length="500"
     *         
     */
    public String getSendRemark() {
		return sendRemark;
	}


	public void setSendRemark(String sendRemark) {
		this.sendRemark = sendRemark;
	}


	/**       
     *      *            @hibernate.property
     *             column="CREATE_NO"
     *             length="20"
     *         
     */

    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
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
     *             column="REMITTANCE_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getRemittanceMoney() {
        return this.remittanceMoney;
    }
    
    public void setRemittanceMoney(BigDecimal remittanceMoney) {
        this.remittanceMoney = remittanceMoney;
    }

    /**       
     *      *            @hibernate.property
     *             column="FEE"
     *             length="22"
     *         
     */

    public BigDecimal getFee() {
        return this.fee;
    }
    
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("remittanceBNum").append("='").append(getRemittanceBNum()).append("' ");			
      buffer.append("operaterCode").append("='").append(getOperaterCode()).append("' ");			
      buffer.append("operaterTime").append("='").append(getOperaterTime()).append("' ");			
      buffer.append("sendDate").append("='").append(getSendDate()).append("' ");			
      buffer.append("registerStatus").append("='").append(getRegisterStatus()).append("' ");			
      buffer.append("reissueStatus").append("='").append(getReissueStatus()).append("' ");			
      buffer.append("supplyTime").append("='").append(getSupplyTime()).append("' ");			
      buffer.append("remark").append("='").append(getSendRemark()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("remittanceMoney").append("='").append(getRemittanceMoney()).append("' ");			
      buffer.append("fee").append("='").append(getFee()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdSendNote) ) return false;
		 JbdSendNote castOther = ( JbdSendNote ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getRemittanceBNum()==castOther.getRemittanceBNum()) || ( this.getRemittanceBNum()!=null && castOther.getRemittanceBNum()!=null && this.getRemittanceBNum().equals(castOther.getRemittanceBNum()) ) )
 && ( (this.getOperaterCode()==castOther.getOperaterCode()) || ( this.getOperaterCode()!=null && castOther.getOperaterCode()!=null && this.getOperaterCode().equals(castOther.getOperaterCode()) ) )
 && ( (this.getOperaterTime()==castOther.getOperaterTime()) || ( this.getOperaterTime()!=null && castOther.getOperaterTime()!=null && this.getOperaterTime().equals(castOther.getOperaterTime()) ) )
 && ( (this.getSendDate()==castOther.getSendDate()) || ( this.getSendDate()!=null && castOther.getSendDate()!=null && this.getSendDate().equals(castOther.getSendDate()) ) )
 && ( (this.getRegisterStatus()==castOther.getRegisterStatus()) || ( this.getRegisterStatus()!=null && castOther.getRegisterStatus()!=null && this.getRegisterStatus().equals(castOther.getRegisterStatus()) ) )
 && ( (this.getReissueStatus()==castOther.getReissueStatus()) || ( this.getReissueStatus()!=null && castOther.getReissueStatus()!=null && this.getReissueStatus().equals(castOther.getReissueStatus()) ) )
 && ( (this.getSupplyTime()==castOther.getSupplyTime()) || ( this.getSupplyTime()!=null && castOther.getSupplyTime()!=null && this.getSupplyTime().equals(castOther.getSupplyTime()) ) )
 && ( (this.getSendRemark()==castOther.getSendRemark()) || ( this.getSendRemark()!=null && castOther.getSendRemark()!=null && this.getSendRemark().equals(castOther.getSendRemark()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getRemittanceMoney()==castOther.getRemittanceMoney()) || ( this.getRemittanceMoney()!=null && castOther.getRemittanceMoney()!=null && this.getRemittanceMoney().equals(castOther.getRemittanceMoney()) ) )
 && ( (this.getFee()==castOther.getFee()) || ( this.getFee()!=null && castOther.getFee()!=null && this.getFee().equals(castOther.getFee()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getRemittanceBNum() == null ? 0 : this.getRemittanceBNum().hashCode() );
         result = 37 * result + ( getOperaterCode() == null ? 0 : this.getOperaterCode().hashCode() );
         result = 37 * result + ( getOperaterTime() == null ? 0 : this.getOperaterTime().hashCode() );
         result = 37 * result + ( getSendDate() == null ? 0 : this.getSendDate().hashCode() );
         result = 37 * result + ( getRegisterStatus() == null ? 0 : this.getRegisterStatus().hashCode() );
         result = 37 * result + ( getReissueStatus() == null ? 0 : this.getReissueStatus().hashCode() );
         result = 37 * result + ( getSupplyTime() == null ? 0 : this.getSupplyTime().hashCode() );
         result = 37 * result + ( getSendRemark() == null ? 0 : this.getSendRemark().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getRemittanceMoney() == null ? 0 : this.getRemittanceMoney().hashCode() );
         result = 37 * result + ( getFee() == null ? 0 : this.getFee().hashCode() );
         return result;
   }


	/**
    * *
    * @hibernate.many-to-one not-null="true"
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
     *             column="SEND_LATE_CAUSE"
     *             length="500"
     *         
     */

	public String getSendLateCause() {
		return sendLateCause;
	}


	public void setSendLateCause(String sendLateCause) {
		this.sendLateCause = sendLateCause;
	}


    /**       
     *      *            @hibernate.property
     *             column="SEND_LATE_REMARK"
     *             length="500"
     *         
     */
	public String getSendLateRemark() {
		return sendLateRemark;
	}


	public void setSendLateRemark(String sendLateRemark) {
		this.sendLateRemark = sendLateRemark;
	}





}
