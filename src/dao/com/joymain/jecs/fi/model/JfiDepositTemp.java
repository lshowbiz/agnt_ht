package com.joymain.jecs.fi.model;
// Generated 2015-10-30 10:59:38 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_DEPOSIT_TEMP"
 *     
 */

public class JfiDepositTemp extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long tempId;
    private String companyCode;
    private String userCode;
    private String dealType;
    private Integer moneyType;
    private BigDecimal money;
    private String notes;
    private String createrNo;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JfiDepositTemp() {
    }

    
    /** full constructor */
    public JfiDepositTemp(String companyCode, String userCode, String dealType, Integer moneyType, BigDecimal money, String notes, String createrNo, Date createTime) {
        this.companyCode = companyCode;
        this.userCode = userCode;
        this.dealType = dealType;
        this.moneyType = moneyType;
        this.money = money;
        this.notes = notes;
        this.createrNo = createrNo;
        this.createTime = createTime;
    }
    

   
    // Property accessors

    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="TEMP_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
     *         
     */
    public Long getTempId() {
        return this.tempId;
    }
    
    public void setTempId(Long tempId) {
        this.tempId = tempId;
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
     *             column="DEAL_TYPE"
     *             length="1"
     *         
     */

    public String getDealType() {
        return this.dealType;
    }
    
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONEY_TYPE"
     *             length="4"
     *         
     */

    public Integer getMoneyType() {
        return this.moneyType;
    }
    
    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
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
     *             column="CREATER_NO"
     *             length="20"
     *         
     */

    public String getCreaterNo() {
        return this.createrNo;
    }
    
    public void setCreaterNo(String createrNo) {
        this.createrNo = createrNo;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("dealType").append("='").append(getDealType()).append("' ");			
      buffer.append("moneyType").append("='").append(getMoneyType()).append("' ");			
      buffer.append("money").append("='").append(getMoney()).append("' ");			
      buffer.append("notes").append("='").append(getNotes()).append("' ");			
      buffer.append("createrNo").append("='").append(getCreaterNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiDepositTemp) ) return false;
		 JfiDepositTemp castOther = ( JfiDepositTemp ) other; 
         
		 return ( (this.getTempId()==castOther.getTempId()) || ( this.getTempId()!=null && castOther.getTempId()!=null && this.getTempId().equals(castOther.getTempId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getDealType()==castOther.getDealType()) || ( this.getDealType()!=null && castOther.getDealType()!=null && this.getDealType().equals(castOther.getDealType()) ) )
 && ( (this.getMoneyType()==castOther.getMoneyType()) || ( this.getMoneyType()!=null && castOther.getMoneyType()!=null && this.getMoneyType().equals(castOther.getMoneyType()) ) )
 && ( (this.getMoney()==castOther.getMoney()) || ( this.getMoney()!=null && castOther.getMoney()!=null && this.getMoney().equals(castOther.getMoney()) ) )
 && ( (this.getNotes()==castOther.getNotes()) || ( this.getNotes()!=null && castOther.getNotes()!=null && this.getNotes().equals(castOther.getNotes()) ) )
 && ( (this.getCreaterNo()==castOther.getCreaterNo()) || ( this.getCreaterNo()!=null && castOther.getCreaterNo()!=null && this.getCreaterNo().equals(castOther.getCreaterNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTempId() == null ? 0 : this.getTempId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getDealType() == null ? 0 : this.getDealType().hashCode() );
         result = 37 * result + ( getMoneyType() == null ? 0 : this.getMoneyType().hashCode() );
         result = 37 * result + ( getMoney() == null ? 0 : this.getMoney().hashCode() );
         result = 37 * result + ( getNotes() == null ? 0 : this.getNotes().hashCode() );
         result = 37 * result + ( getCreaterNo() == null ? 0 : this.getCreaterNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
