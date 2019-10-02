package com.joymain.jecs.bd.model;
// Generated 2016-7-14 10:11:33 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_SUB_BONUS_LIST"
 *     
 */

public class JbdSubBonusList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private BigDecimal amount;
    private String remark;
    private String status;
    private Date createTime;
    private String createNo;
    private String treatedNo;
    private String sendNo;
    private Date sendTime;
    private JmiMember jmiMember;

    // Constructors

    /** default constructor */
    public JbdSubBonusList() {
    }

    
    /** full constructor */
    public JbdSubBonusList(String userCode, BigDecimal amount, String remark, String status, Date createTime, String createNo, String treatedNo, String sendNo, Date sendTime) {
  
        this.amount = amount;
        this.remark = remark;
        this.status = status;
        this.createTime = createTime;
        this.createNo = createNo;
        this.treatedNo = treatedNo;
        this.sendNo = sendNo;
        this.sendTime = sendTime;
    }
    

   
    // Property accessors

    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="JD_SEQ"
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
     *             column="AMOUNT"
     *             length="22"
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
     *             column="REMARK"
     *             length="1000"
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
     *             column="STATUS"
     *             length="1"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
     *             column="TREATED_NO"
     *             length="100"
     *         
     */

    public String getTreatedNo() {
        return this.treatedNo;
    }
    
    public void setTreatedNo(String treatedNo) {
        this.treatedNo = treatedNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_NO"
     *             length="20"
     *         
     */

    public String getSendNo() {
        return this.sendNo;
    }
    
    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_TIME"
     *             length="7"
     *         
     */

    public Date getSendTime() {
        return this.sendTime;
    }
    
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");		
      buffer.append("amount").append("='").append(getAmount()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("treatedNo").append("='").append(getTreatedNo()).append("' ");			
      buffer.append("sendNo").append("='").append(getSendNo()).append("' ");			
      buffer.append("sendTime").append("='").append(getSendTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdSubBonusList) ) return false;
		 JbdSubBonusList castOther = ( JbdSubBonusList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getTreatedNo()==castOther.getTreatedNo()) || ( this.getTreatedNo()!=null && castOther.getTreatedNo()!=null && this.getTreatedNo().equals(castOther.getTreatedNo()) ) )
 && ( (this.getSendNo()==castOther.getSendNo()) || ( this.getSendNo()!=null && castOther.getSendNo()!=null && this.getSendNo().equals(castOther.getSendNo()) ) )
 && ( (this.getSendTime()==castOther.getSendTime()) || ( this.getSendTime()!=null && castOther.getSendTime()!=null && this.getSendTime().equals(castOther.getSendTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getTreatedNo() == null ? 0 : this.getTreatedNo().hashCode() );
         result = 37 * result + ( getSendNo() == null ? 0 : this.getSendNo().hashCode() );
         result = 37 * result + ( getSendTime() == null ? 0 : this.getSendTime().hashCode() );
         return result;
   }   





}
