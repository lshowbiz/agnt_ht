package com.joymain.jecs.pd.model;
// Generated 2009-9-24 14:25:41 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_STATUS_EXC_STOCK"
 *     
 */

public class PdStatusExcStock extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String seNo;
    private PdWarehouse pdWarehouse = new PdWarehouse();
    private String companyCode;
    private BigDecimal amount;
    private String createUrsCode;
    private Date createTime;
    private String remark;
    private Date checkTime;
    private String checkUrsCode;
    private String checkRemark;
    private Date okTime;
    private String okUrsCode;
    private String okRemark;
    private String editUrsCode;
    private Date editTime;
    private String stockFlag;
    private Integer orderFlag;
    private Set pdStatusExcStockDetails = new HashSet();

    // Constructors

    /** default constructor */
    public PdStatusExcStock() {
    }

	/** minimal constructor */
    public PdStatusExcStock(String companyCode, BigDecimal amount, String createUrsCode, Date createTime) {
        this.companyCode = companyCode;
        this.amount = amount;
        this.createUrsCode = createUrsCode;
        this.createTime = createTime;
    }
    
    /** full constructor */
    public PdStatusExcStock(String companyCode, BigDecimal amount, String createUrsCode, Date createTime, String remark, Date checkTime, String checkUrsCode, String checkRemark, Date okTime, String okUrsCode, String okRemark, String editUrsCode, Date editTime, String stockFlag, Integer orderFlag) {
        this.companyCode = companyCode;
        this.amount = amount;
        this.createUrsCode = createUrsCode;
        this.createTime = createTime;
        this.remark = remark;
        this.checkTime = checkTime;
        this.checkUrsCode = checkUrsCode;
        this.checkRemark = checkRemark;
        this.okTime = okTime;
        this.okUrsCode = okUrsCode;
        this.okRemark = okRemark;
        this.editUrsCode = editUrsCode;
        this.editTime = editTime;
        this.stockFlag = stockFlag;
        this.orderFlag = orderFlag;
    }
    /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="product_No"
	 * @hibernate.collection-key column="SE_NO"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pd.model.PdStatusExcStockDetail"
	 * 
	 */
    public Set getPdStatusExcStockDetails() {
		return pdStatusExcStockDetails;
	}

	public void setPdStatusExcStockDetails(Set pdStatusExcStockDetails) {
		this.pdStatusExcStockDetails = pdStatusExcStockDetails;
	}

	/**
     * *
     * @hibernate.many-to-one not-null="true" inverse="true"  
     * @hibernate.column name="WAREHOUSE_NO"
     * 
     */
   
    public PdWarehouse getPdWarehouse() {
		return pdWarehouse;
	}

	public void setPdWarehouse(PdWarehouse pdWarehouse) {
		this.pdWarehouse = pdWarehouse;
	}

	// Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="SE_NO"
     *         
     */

    public String getSeNo() {
        return this.seNo;
    }
    
    public void setSeNo(String seNo) {
        this.seNo = seNo;
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
     *             length="22"
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
     *             column="CREATE_URS_CODE"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getCreateUrsCode() {
        return this.createUrsCode;
    }
    
    public void setCreateUrsCode(String createUrsCode) {
        this.createUrsCode = createUrsCode;
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
     *             column="CHECK_URS_CODE"
     *             length="20"
     *         
     */

    public String getCheckUrsCode() {
        return this.checkUrsCode;
    }
    
    public void setCheckUrsCode(String checkUrsCode) {
        this.checkUrsCode = checkUrsCode;
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
     *             column="OK_URS_CODE"
     *             length="20"
     *         
     */

    public String getOkUrsCode() {
        return this.okUrsCode;
    }
    
    public void setOkUrsCode(String okUrsCode) {
        this.okUrsCode = okUrsCode;
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
     *             column="EDIT_URS_CODE"
     *             length="20"
     *         
     */

    public String getEditUrsCode() {
        return this.editUrsCode;
    }
    
    public void setEditUrsCode(String editUrsCode) {
        this.editUrsCode = editUrsCode;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("amount").append("='").append(getAmount()).append("' ");			
      buffer.append("createUrsCode").append("='").append(getCreateUrsCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("checkUrsCode").append("='").append(getCheckUrsCode()).append("' ");			
      buffer.append("checkRemark").append("='").append(getCheckRemark()).append("' ");			
      buffer.append("okTime").append("='").append(getOkTime()).append("' ");			
      buffer.append("okUrsCode").append("='").append(getOkUrsCode()).append("' ");			
      buffer.append("okRemark").append("='").append(getOkRemark()).append("' ");			
      buffer.append("editUrsCode").append("='").append(getEditUrsCode()).append("' ");			
      buffer.append("editTime").append("='").append(getEditTime()).append("' ");			
      buffer.append("stockFlag").append("='").append(getStockFlag()).append("' ");			
      buffer.append("orderFlag").append("='").append(getOrderFlag()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdStatusExcStock) ) return false;
		 PdStatusExcStock castOther = ( PdStatusExcStock ) other; 
         
		 return ( (this.getSeNo()==castOther.getSeNo()) || ( this.getSeNo()!=null && castOther.getSeNo()!=null && this.getSeNo().equals(castOther.getSeNo()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && ( (this.getCreateUrsCode()==castOther.getCreateUrsCode()) || ( this.getCreateUrsCode()!=null && castOther.getCreateUrsCode()!=null && this.getCreateUrsCode().equals(castOther.getCreateUrsCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getCheckUrsCode()==castOther.getCheckUrsCode()) || ( this.getCheckUrsCode()!=null && castOther.getCheckUrsCode()!=null && this.getCheckUrsCode().equals(castOther.getCheckUrsCode()) ) )
 && ( (this.getCheckRemark()==castOther.getCheckRemark()) || ( this.getCheckRemark()!=null && castOther.getCheckRemark()!=null && this.getCheckRemark().equals(castOther.getCheckRemark()) ) )
 && ( (this.getOkTime()==castOther.getOkTime()) || ( this.getOkTime()!=null && castOther.getOkTime()!=null && this.getOkTime().equals(castOther.getOkTime()) ) )
 && ( (this.getOkUrsCode()==castOther.getOkUrsCode()) || ( this.getOkUrsCode()!=null && castOther.getOkUrsCode()!=null && this.getOkUrsCode().equals(castOther.getOkUrsCode()) ) )
 && ( (this.getOkRemark()==castOther.getOkRemark()) || ( this.getOkRemark()!=null && castOther.getOkRemark()!=null && this.getOkRemark().equals(castOther.getOkRemark()) ) )
 && ( (this.getEditUrsCode()==castOther.getEditUrsCode()) || ( this.getEditUrsCode()!=null && castOther.getEditUrsCode()!=null && this.getEditUrsCode().equals(castOther.getEditUrsCode()) ) )
 && ( (this.getEditTime()==castOther.getEditTime()) || ( this.getEditTime()!=null && castOther.getEditTime()!=null && this.getEditTime().equals(castOther.getEditTime()) ) )
 && ( (this.getStockFlag()==castOther.getStockFlag()) || ( this.getStockFlag()!=null && castOther.getStockFlag()!=null && this.getStockFlag().equals(castOther.getStockFlag()) ) )
 && ( (this.getOrderFlag()==castOther.getOrderFlag()) || ( this.getOrderFlag()!=null && castOther.getOrderFlag()!=null && this.getOrderFlag().equals(castOther.getOrderFlag()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSeNo() == null ? 0 : this.getSeNo().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getCreateUrsCode() == null ? 0 : this.getCreateUrsCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getCheckUrsCode() == null ? 0 : this.getCheckUrsCode().hashCode() );
         result = 37 * result + ( getCheckRemark() == null ? 0 : this.getCheckRemark().hashCode() );
         result = 37 * result + ( getOkTime() == null ? 0 : this.getOkTime().hashCode() );
         result = 37 * result + ( getOkUrsCode() == null ? 0 : this.getOkUrsCode().hashCode() );
         result = 37 * result + ( getOkRemark() == null ? 0 : this.getOkRemark().hashCode() );
         result = 37 * result + ( getEditUrsCode() == null ? 0 : this.getEditUrsCode().hashCode() );
         result = 37 * result + ( getEditTime() == null ? 0 : this.getEditTime().hashCode() );
         result = 37 * result + ( getStockFlag() == null ? 0 : this.getStockFlag().hashCode() );
         result = 37 * result + ( getOrderFlag() == null ? 0 : this.getOrderFlag().hashCode() );
         return result;
   }   





}
