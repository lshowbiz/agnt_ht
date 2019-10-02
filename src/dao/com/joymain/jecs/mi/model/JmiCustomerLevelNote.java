package com.joymain.jecs.mi.model;
// Generated 2011-2-15 20:41:47 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_CUSTOMER_LEVEL_NOTE"
 *     
 */

public class JmiCustomerLevelNote extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String newCustomerLevel;
    private String oldCustomerLevel;
    private String companyCode;
    private Date createTime;
    private String createNo;
    private String remark;
    private BigDecimal amount;
    private String sendRemark;
    private String status;


    // Constructors

	/**       
     *      *            @hibernate.property
     *             column="SEND_REMARK"
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
     *             column="STATUS"
     *         
     */
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	/**       
     *      *            @hibernate.property
     *             column="AMOUNT"
     *         
     */
    public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	/** default constructor */
    public JmiCustomerLevelNote() {
    }

    
    /** full constructor */
    public JmiCustomerLevelNote(String userCode, String newCustomerLevel, String oldCustomerLevel, String companyCode, Date createTime, String createNo, String remark) {
        this.userCode = userCode;
        this.newCustomerLevel = newCustomerLevel;
        this.oldCustomerLevel = oldCustomerLevel;
        this.companyCode = companyCode;
        this.createTime = createTime;
        this.createNo = createNo;
        this.remark = remark;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID" 
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
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
     *             column="USER_CODE"
     *             length="20"
     *         
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_CUSTOMER_LEVEL"
     *             length="3"
     *         
     */

    public String getNewCustomerLevel() {
        return this.newCustomerLevel;
    }
    
    public void setNewCustomerLevel(String newCustomerLevel) {
        this.newCustomerLevel = newCustomerLevel;
    }
    /**       
     *      *            @hibernate.property
     *             column="OLD_CUSTOMER_LEVEL"
     *             length="3"
     *         
     */

    public String getOldCustomerLevel() {
        return this.oldCustomerLevel;
    }
    
    public void setOldCustomerLevel(String oldCustomerLevel) {
        this.oldCustomerLevel = oldCustomerLevel;
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
     *             column="REMARK"
     *             length="500"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("newCustomerLevel").append("='").append(getNewCustomerLevel()).append("' ");			
      buffer.append("oldCustomerLevel").append("='").append(getOldCustomerLevel()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiCustomerLevelNote) ) return false;
		 JmiCustomerLevelNote castOther = ( JmiCustomerLevelNote ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getNewCustomerLevel()==castOther.getNewCustomerLevel()) || ( this.getNewCustomerLevel()!=null && castOther.getNewCustomerLevel()!=null && this.getNewCustomerLevel().equals(castOther.getNewCustomerLevel()) ) )
 && ( (this.getOldCustomerLevel()==castOther.getOldCustomerLevel()) || ( this.getOldCustomerLevel()!=null && castOther.getOldCustomerLevel()!=null && this.getOldCustomerLevel().equals(castOther.getOldCustomerLevel()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getNewCustomerLevel() == null ? 0 : this.getNewCustomerLevel().hashCode() );
         result = 37 * result + ( getOldCustomerLevel() == null ? 0 : this.getOldCustomerLevel().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   





}
