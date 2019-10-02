package com.joymain.jecs.pd.model;
// Generated 2009-9-24 14:23:13 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_ADJUST_STOCK"
 *     
 */

public class PdAdjustStock extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String asNo;
    private String companyCode;
    private String warehouseNo;
    private String astNo;
    private BigDecimal amount;
    private String createUsrCode;
    private Date createTime;
    private String remark;
    private Date checkTime;
    private String checkUsrCode;
    private String checkRemark;
    private Date okTime;
    private String okUsrCode;
    private String okRemark;
    private String editUsrCode;
    private Date editTime;
    private String stockFlag="0";
    private Integer orderFlag;

    private Set pdAdjustStockDetails = new HashSet();
    // Constructors

    /** default constructor */
    public PdAdjustStock() {
    }

	/** minimal constructor */
    public PdAdjustStock(String companyCode, BigDecimal amount, String createUsrCode, Date createTime) {
        this.companyCode = companyCode;
        this.amount = amount;
        this.createUsrCode = createUsrCode;
        this.createTime = createTime;
    }
    
    /** full constructor */
    public PdAdjustStock(String companyCode, String astNo, BigDecimal amount, String createUsrCode, Date createTime, String remark, Date checkTime, String checkUsrCode, String checkRemark, Date okTime, String okUsrCode, String okRemark, String editUsrCode, Date editTime, String stockFlag, Integer orderFlag) {
        this.companyCode = companyCode;
        this.astNo = astNo;
        this.amount = amount;
        this.createUsrCode = createUsrCode;
        this.createTime = createTime;
        this.remark = remark;
        this.checkTime = checkTime;
        this.checkUsrCode = checkUsrCode;
        this.checkRemark = checkRemark;
        this.okTime = okTime;
        this.okUsrCode = okUsrCode;
        this.okRemark = okRemark;
        this.editUsrCode = editUsrCode;
        this.editTime = editTime;
        this.stockFlag = stockFlag;
        this.orderFlag = orderFlag;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="AS_NO"
     *         
     */

    public String getAsNo() {
        return this.asNo;
    }
    
    public void setAsNo(String asNo) {
        this.asNo = asNo;
    }
    
    
    /**       
     *      *            @hibernate.property
     *             column="WAREHOUSE_NO"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getWarehouseNo() {
		return warehouseNo;
	}
    /**
     * @spring.validator type="required"
     */
	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	/**
	    * *
	    * 
	    * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan" order-by="product_no desc"
	    * @hibernate.collection-key column="AS_NO"
	    * @hibernate.collection-one-to-many class="com.joymain.jecs.pd.model.PdAdjustStockDetail"
	    * 
	    */
	public Set getPdAdjustStockDetails() {
		return pdAdjustStockDetails;
	}

	public void setPdAdjustStockDetails(Set pdAdjustStockDetails) {
		this.pdAdjustStockDetails = pdAdjustStockDetails;
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
     *             column="AST_NO"
     *             length="2"
     *         
     */

    public String getAstNo() {
        return this.astNo;
    }
    
    public void setAstNo(String astNo) {
        this.astNo = astNo;
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

    public String getCreateUsrCode() {
        return this.createUsrCode;
    }
    
    public void setCreateUsrCode(String createUsrCode) {
        this.createUsrCode = createUsrCode;
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

    public String getCheckUsrCode() {
        return this.checkUsrCode;
    }
    
    public void setCheckUsrCode(String checkUsrCode) {
        this.checkUsrCode = checkUsrCode;
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

    public String getOkUsrCode() {
        return this.okUsrCode;
    }
    
    public void setOkUsrCode(String okUsrCode) {
        this.okUsrCode = okUsrCode;
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

    public String getEditUsrCode() {
        return this.editUsrCode;
    }
    
    public void setEditUsrCode(String editUsrCode) {
        this.editUsrCode = editUsrCode;
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
      buffer.append("astNo").append("='").append(getAstNo()).append("' ");			
      buffer.append("amount").append("='").append(getAmount()).append("' ");			
      buffer.append("createUsrCode").append("='").append(getCreateUsrCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("checkUsrCode").append("='").append(getCheckUsrCode()).append("' ");			
      buffer.append("checkRemark").append("='").append(getCheckRemark()).append("' ");			
      buffer.append("okTime").append("='").append(getOkTime()).append("' ");			
      buffer.append("okUsrCode").append("='").append(getOkUsrCode()).append("' ");			
      buffer.append("okRemark").append("='").append(getOkRemark()).append("' ");			
      buffer.append("editUsrCode").append("='").append(getEditUsrCode()).append("' ");			
      buffer.append("editTime").append("='").append(getEditTime()).append("' ");			
      buffer.append("stockFlag").append("='").append(getStockFlag()).append("' ");			
      buffer.append("orderFlag").append("='").append(getOrderFlag()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdAdjustStock) ) return false;
		 PdAdjustStock castOther = ( PdAdjustStock ) other; 
         
		 return ( (this.getAsNo()==castOther.getAsNo()) || ( this.getAsNo()!=null && castOther.getAsNo()!=null && this.getAsNo().equals(castOther.getAsNo()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getAstNo()==castOther.getAstNo()) || ( this.getAstNo()!=null && castOther.getAstNo()!=null && this.getAstNo().equals(castOther.getAstNo()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && ( (this.getCreateUsrCode()==castOther.getCreateUsrCode()) || ( this.getCreateUsrCode()!=null && castOther.getCreateUsrCode()!=null && this.getCreateUsrCode().equals(castOther.getCreateUsrCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getCheckUsrCode()==castOther.getCheckUsrCode()) || ( this.getCheckUsrCode()!=null && castOther.getCheckUsrCode()!=null && this.getCheckUsrCode().equals(castOther.getCheckUsrCode()) ) )
 && ( (this.getCheckRemark()==castOther.getCheckRemark()) || ( this.getCheckRemark()!=null && castOther.getCheckRemark()!=null && this.getCheckRemark().equals(castOther.getCheckRemark()) ) )
 && ( (this.getOkTime()==castOther.getOkTime()) || ( this.getOkTime()!=null && castOther.getOkTime()!=null && this.getOkTime().equals(castOther.getOkTime()) ) )
 && ( (this.getOkUsrCode()==castOther.getOkUsrCode()) || ( this.getOkUsrCode()!=null && castOther.getOkUsrCode()!=null && this.getOkUsrCode().equals(castOther.getOkUsrCode()) ) )
 && ( (this.getOkRemark()==castOther.getOkRemark()) || ( this.getOkRemark()!=null && castOther.getOkRemark()!=null && this.getOkRemark().equals(castOther.getOkRemark()) ) )
 && ( (this.getEditUsrCode()==castOther.getEditUsrCode()) || ( this.getEditUsrCode()!=null && castOther.getEditUsrCode()!=null && this.getEditUsrCode().equals(castOther.getEditUsrCode()) ) )
 && ( (this.getEditTime()==castOther.getEditTime()) || ( this.getEditTime()!=null && castOther.getEditTime()!=null && this.getEditTime().equals(castOther.getEditTime()) ) )
 && ( (this.getStockFlag()==castOther.getStockFlag()) || ( this.getStockFlag()!=null && castOther.getStockFlag()!=null && this.getStockFlag().equals(castOther.getStockFlag()) ) )
 && ( (this.getOrderFlag()==castOther.getOrderFlag()) || ( this.getOrderFlag()!=null && castOther.getOrderFlag()!=null && this.getOrderFlag().equals(castOther.getOrderFlag()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAsNo() == null ? 0 : this.getAsNo().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getAstNo() == null ? 0 : this.getAstNo().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getCreateUsrCode() == null ? 0 : this.getCreateUsrCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getCheckUsrCode() == null ? 0 : this.getCheckUsrCode().hashCode() );
         result = 37 * result + ( getCheckRemark() == null ? 0 : this.getCheckRemark().hashCode() );
         result = 37 * result + ( getOkTime() == null ? 0 : this.getOkTime().hashCode() );
         result = 37 * result + ( getOkUsrCode() == null ? 0 : this.getOkUsrCode().hashCode() );
         result = 37 * result + ( getOkRemark() == null ? 0 : this.getOkRemark().hashCode() );
         result = 37 * result + ( getEditUsrCode() == null ? 0 : this.getEditUsrCode().hashCode() );
         result = 37 * result + ( getEditTime() == null ? 0 : this.getEditTime().hashCode() );
         result = 37 * result + ( getStockFlag() == null ? 0 : this.getStockFlag().hashCode() );
         result = 37 * result + ( getOrderFlag() == null ? 0 : this.getOrderFlag().hashCode() );
         return result;
   }   





}
