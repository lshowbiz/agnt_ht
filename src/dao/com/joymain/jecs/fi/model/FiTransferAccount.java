package com.joymain.jecs.fi.model;
// Generated 2013-5-28 10:10:33 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_TRANSFER_ACCOUNT"
 *     
 */

public class FiTransferAccount extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long taId;
    private String transferUserCode;
    private String destinationUserCode;
    private BigDecimal money;
    private String notes;
    private Integer status;//1. 新单；2：已审核；3：已撤销
    private String createrCode;
    private String createrName;
    private Date createTime;
    private String checkerCode;
    private Date checkeTime;
    private String checkerName;
    private Date dealDate;


    // Constructors

    /** default constructor */
    public FiTransferAccount() {
    }

	/** minimal constructor */
    public FiTransferAccount(String transferUserCode, String destinationUserCode) {
        this.transferUserCode = transferUserCode;
        this.destinationUserCode = destinationUserCode;
    }
    
    /** full constructor */
    public FiTransferAccount(String transferUserCode, String destinationUserCode, BigDecimal money, String notes, Integer status, String createrCode, String createrName, Date createTime, String checkerCode, String checkerName, Date checkeTime, Date dealDate) {
        this.transferUserCode = transferUserCode;
        this.destinationUserCode = destinationUserCode;
        this.money = money;
        this.notes = notes;
        this.status = status;
        this.createrCode = createrCode;
        this.createrName = createrName;
        this.createTime = createTime;
        this.checkerCode = checkerCode;
        this.checkerName = checkerName;
        this.checkeTime = checkeTime;
        this.dealDate = dealDate;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="TA_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */

    public Long getTaId() {
        return this.taId;
    }
    
    public void setTaId(Long taId) {
        this.taId = taId;
    }
    /**       
     *      *            @hibernate.property
     *             column="TRANSFER_USER_CODE"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getTransferUserCode() {
        return this.transferUserCode;
    }
    
    public void setTransferUserCode(String transferUserCode) {
        this.transferUserCode = transferUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="DESTINATION_USER_CODE"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getDestinationUserCode() {
        return this.destinationUserCode;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setDestinationUserCode(String destinationUserCode) {
        this.destinationUserCode = destinationUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONEY"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getMoney() {
        return this.money;
    }
    
    /**
	 * @spring.validator type="required"
	 */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    /**       
     *      *            @hibernate.property
     *             column="NOTES"
     *             length="4000"
     *         
     */

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="2"
     *         
     */

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATER_CODE"
     *             length="20"
     *         
     */

    public String getCreaterCode() {
        return this.createrCode;
    }
    
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATER_NAME"
     *             length="300"
     *         
     */

    public String getCreaterName() {
        return this.createrName;
    }
    
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
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
     *             column="CHECKER_CODE"
     *             length="20"
     *         
     */

    public String getCheckerCode() {
        return this.checkerCode;
    }
    
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECKER_NAME"
     *             length="7"
     *         
     */

    public String getCheckerName() {
        return this.checkerName;
    }
    
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECKE_TIME"
     *             length="300"
     *         
     */

    public Date getCheckeTime() {
        return this.checkeTime;
    }
    
    public void setCheckeTime(Date checkeTime) {
        this.checkeTime = checkeTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEAL_DATE"
     *             length="7"
     *         
     */

    public Date getDealDate() {
        return this.dealDate;
    }
    
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("transferUserCode").append("='").append(getTransferUserCode()).append("' ");			
      buffer.append("destinationUserCode").append("='").append(getDestinationUserCode()).append("' ");			
      buffer.append("money").append("='").append(getMoney()).append("' ");			
      buffer.append("notes").append("='").append(getNotes()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("createrCode").append("='").append(getCreaterCode()).append("' ");			
      buffer.append("createrName").append("='").append(getCreaterName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("checkerCode").append("='").append(getCheckerCode()).append("' ");			
      buffer.append("checkerName").append("='").append(getCheckerName()).append("' ");			
      buffer.append("checkeTime").append("='").append(getCheckeTime()).append("' ");			
      buffer.append("dealDate").append("='").append(getDealDate()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiTransferAccount) ) return false;
		 FiTransferAccount castOther = ( FiTransferAccount ) other; 
         
		 return ( (this.getTaId()==castOther.getTaId()) || ( this.getTaId()!=null && castOther.getTaId()!=null && this.getTaId().equals(castOther.getTaId()) ) )
 && ( (this.getTransferUserCode()==castOther.getTransferUserCode()) || ( this.getTransferUserCode()!=null && castOther.getTransferUserCode()!=null && this.getTransferUserCode().equals(castOther.getTransferUserCode()) ) )
 && ( (this.getDestinationUserCode()==castOther.getDestinationUserCode()) || ( this.getDestinationUserCode()!=null && castOther.getDestinationUserCode()!=null && this.getDestinationUserCode().equals(castOther.getDestinationUserCode()) ) )
 && ( (this.getMoney()==castOther.getMoney()) || ( this.getMoney()!=null && castOther.getMoney()!=null && this.getMoney().equals(castOther.getMoney()) ) )
 && ( (this.getNotes()==castOther.getNotes()) || ( this.getNotes()!=null && castOther.getNotes()!=null && this.getNotes().equals(castOther.getNotes()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCreaterCode()==castOther.getCreaterCode()) || ( this.getCreaterCode()!=null && castOther.getCreaterCode()!=null && this.getCreaterCode().equals(castOther.getCreaterCode()) ) )
 && ( (this.getCreaterName()==castOther.getCreaterName()) || ( this.getCreaterName()!=null && castOther.getCreaterName()!=null && this.getCreaterName().equals(castOther.getCreaterName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCheckerCode()==castOther.getCheckerCode()) || ( this.getCheckerCode()!=null && castOther.getCheckerCode()!=null && this.getCheckerCode().equals(castOther.getCheckerCode()) ) )
 && ( (this.getCheckerName()==castOther.getCheckerName()) || ( this.getCheckerName()!=null && castOther.getCheckerName()!=null && this.getCheckerName().equals(castOther.getCheckerName()) ) )
 && ( (this.getCheckeTime()==castOther.getCheckeTime()) || ( this.getCheckeTime()!=null && castOther.getCheckeTime()!=null && this.getCheckeTime().equals(castOther.getCheckeTime()) ) )
 && ( (this.getDealDate()==castOther.getDealDate()) || ( this.getDealDate()!=null && castOther.getDealDate()!=null && this.getDealDate().equals(castOther.getDealDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTaId() == null ? 0 : this.getTaId().hashCode() );
         result = 37 * result + ( getTransferUserCode() == null ? 0 : this.getTransferUserCode().hashCode() );
         result = 37 * result + ( getDestinationUserCode() == null ? 0 : this.getDestinationUserCode().hashCode() );
         result = 37 * result + ( getMoney() == null ? 0 : this.getMoney().hashCode() );
         result = 37 * result + ( getNotes() == null ? 0 : this.getNotes().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCreaterCode() == null ? 0 : this.getCreaterCode().hashCode() );
         result = 37 * result + ( getCreaterName() == null ? 0 : this.getCreaterName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCheckerCode() == null ? 0 : this.getCheckerCode().hashCode() );
         result = 37 * result + ( getCheckerName() == null ? 0 : this.getCheckerName().hashCode() );
         result = 37 * result + ( getCheckeTime() == null ? 0 : this.getCheckeTime().hashCode() );
         result = 37 * result + ( getDealDate() == null ? 0 : this.getDealDate().hashCode() );
         return result;
   }   





}
