package com.joymain.jecs.fi.model;
// Generated 2014-4-3 18:11:02 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_TRANSFER_FUNDBOOK"
 *     
 */

public class FiTransferFundbook extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long taId;
    private String transferUserCode;//转账会员编号
    private String destinationUserCode;//收款会员编号
    private BigDecimal money;
    private String notes;
    private Integer status;//状态:1.未核准，2.已核准，3.已撤销
    private String createrCode;
    private String createrName;
    private Date createTime;
    private String checkerCode;
    private String checkerName;
    private Date checkeTime;
    private Date dealDate;
    private String bankbookType;//基金类型1：分红基金，2：定向基金
    private String transferType;//转账到目标帐户的产业化基金类型，1：分红基金，2：定向基金，3：发展基金


    // Constructors

    /** default constructor */
    public FiTransferFundbook() {
    }

	/** minimal constructor */
    public FiTransferFundbook(String transferUserCode, String destinationUserCode, String bankbookType, String transferType) {
        this.transferUserCode = transferUserCode;
        this.destinationUserCode = destinationUserCode;
        this.bankbookType = bankbookType;
        this.transferType = transferType;
    }
    
    /** full constructor */
    public FiTransferFundbook(String transferUserCode, String destinationUserCode, BigDecimal money, String notes, Integer status, String createrCode, String createrName, Date createTime, String checkerCode, String checkerName, Date checkeTime, Date dealDate, String bankbookType, String transferType) {
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
        this.bankbookType = bankbookType;
        this.transferType = transferType;
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
    
    public void setDestinationUserCode(String destinationUserCode) {
        this.destinationUserCode = destinationUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONEY"
     *             length="18"
     *         
     */

    public BigDecimal getMoney() {
        return this.money;
    }
    
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
     *             length="300"
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
     *             length="7"
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
     *      *            @hibernate.property
     *             column="BANKBOOK_TYPE"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getBankbookType() {
        return this.bankbookType;
    }
    
    public void setBankbookType(String bankbookType) {
        this.bankbookType = bankbookType;
    }
    /**       
     *      *            @hibernate.property
     *             column="TRANSFER_TYPE"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getTransferType() {
        return this.transferType;
    }
    
    public void setTransferType(String transferType) {
        this.transferType = transferType;
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
      buffer.append("bankbookType").append("='").append(getBankbookType()).append("' ");			
      buffer.append("transferType").append("='").append(getTransferType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiTransferFundbook) ) return false;
		 FiTransferFundbook castOther = ( FiTransferFundbook ) other; 
         
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
 && ( (this.getDealDate()==castOther.getDealDate()) || ( this.getDealDate()!=null && castOther.getDealDate()!=null && this.getDealDate().equals(castOther.getDealDate()) ) )
 && ( (this.getBankbookType()==castOther.getBankbookType()) || ( this.getBankbookType()!=null && castOther.getBankbookType()!=null && this.getBankbookType().equals(castOther.getBankbookType()) ) )
 && ( (this.getTransferType()==castOther.getTransferType()) || ( this.getTransferType()!=null && castOther.getTransferType()!=null && this.getTransferType().equals(castOther.getTransferType()) ) );
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
         result = 37 * result + ( getBankbookType() == null ? 0 : this.getBankbookType().hashCode() );
         result = 37 * result + ( getTransferType() == null ? 0 : this.getTransferType().hashCode() );
         return result;
   }   





}
