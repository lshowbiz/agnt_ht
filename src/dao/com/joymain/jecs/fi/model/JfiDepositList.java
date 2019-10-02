package com.joymain.jecs.fi.model;
// Generated 2015-10-10 17:26:52 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_DEPOSIT_LIST"
 *     
 */

public class JfiDepositList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Long dmId;
    private BigDecimal depositMoney;
    private String createNo;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JfiDepositList() {
    }

    
    /** full constructor */
    public JfiDepositList(Long dmId, BigDecimal depositMoney, String createNo, Date createTime) {
        this.dmId = dmId;
        this.depositMoney = depositMoney;
        this.createNo = createNo;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_FI"
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
     *             column="DM_ID"
     *             length="22"
     *         
     */

    public Long getDmId() {
        return this.dmId;
    }
    
    public void setDmId(Long dmId) {
        this.dmId = dmId;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEPOSIT_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getDepositMoney() {
        return this.depositMoney;
    }
    
    public void setDepositMoney(BigDecimal depositMoney) {
        this.depositMoney = depositMoney;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("dmId").append("='").append(getDmId()).append("' ");			
      buffer.append("depositMoney").append("='").append(getDepositMoney()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiDepositList) ) return false;
		 JfiDepositList castOther = ( JfiDepositList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getDmId()==castOther.getDmId()) || ( this.getDmId()!=null && castOther.getDmId()!=null && this.getDmId().equals(castOther.getDmId()) ) )
 && ( (this.getDepositMoney()==castOther.getDepositMoney()) || ( this.getDepositMoney()!=null && castOther.getDepositMoney()!=null && this.getDepositMoney().equals(castOther.getDepositMoney()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getDmId() == null ? 0 : this.getDmId().hashCode() );
         result = 37 * result + ( getDepositMoney() == null ? 0 : this.getDepositMoney().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
