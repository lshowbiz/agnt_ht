package com.joymain.jecs.pm.model;
// Generated 2016-3-25 20:04:48 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_COMBINATION_RELATED"
 *     
 */

public class JpmCombinationRelated extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniNo;
    private String productNo;
    private String productName;
    private String productDate;
    private String subProductNo;
    private String subProductName;
    private Integer qty;
    private BigDecimal price;
    private BigDecimal pv;
    private BigDecimal totalPrice;
    private BigDecimal totalPv;
    private String isFree;
    private String createUserCode;
    private Date createTime;
    private String updateUserCode;
    private Date updateTime;
    private String remark;


    // Constructors

    /** default constructor */
    public JpmCombinationRelated() {
    }

    
    /** full constructor */
    public JpmCombinationRelated(String productNo, String productName, String productDate, String subProductNo, String subProductName, Integer qty, BigDecimal price, BigDecimal pv, BigDecimal totalPrice, BigDecimal totalPv, String isFree, String createUserCode, Date createTime, String updateUserCode, Date updateTime, String remark) {
        this.productNo = productNo;
        this.productName = productName;
        this.productDate = productDate;
        this.subProductNo = subProductNo;
        this.subProductName = subProductName;
        this.qty = qty;
        this.price = price;
        this.pv = pv;
        this.totalPrice = totalPrice;
        this.totalPv = totalPv;
        this.isFree = isFree;
        this.createUserCode = createUserCode;
        this.createTime = createTime;
        this.updateUserCode = updateUserCode;
        this.updateTime = updateTime;
        this.remark = remark;
    }
    

   
    // Property accessors
    /**       
	 *      *            @hibernate.id
	 *             generator-class="sequence"
	 *             type="java.lang.Long"
	 *             column="UNI_NO"
	 *         @hibernate.generator-param name="sequence" value="SEQ_PM" 
	 */
    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NO"
     *             length="30"
     *         
     */

    public String getProductNo() {
        return this.productNo;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NAME"
     *             length="200"
     *         
     */

    public String getProductName() {
        return this.productName;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_DATE"
     *             length="20"
     *         
     */

    public String getProductDate() {
        return this.productDate;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUB_PRODUCT_NO"
     *             length="20"
     *         
     */

    public String getSubProductNo() {
        return this.subProductNo;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setSubProductNo(String subProductNo) {
        this.subProductNo = subProductNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUB_PRODUCT_NAME"
     *             length="200"
     *         
     */
    public String getSubProductName() {
        return this.subProductName;
    }

    /**
     * @spring.validator type="required"
     */
    public void setSubProductName(String subProductName) {
        this.subProductName = subProductName;
    }
    /**       
     *      *            @hibernate.property
     *             column="QTY"
     *             length="5"
     *         
     */

    public Integer getQty() {
        return this.qty;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRICE"
     *             length="18"
     *         
     */

    public BigDecimal getPrice() {
        return this.price;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    /**       
     *      *            @hibernate.property
     *             column="PV"
     *             length="10"
     *         
     */

    public BigDecimal getPv() {
        return this.pv;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }
    /**       
     *      *            @hibernate.property
     *             column="TOTAL_PRICE"
     *             length="18"
     *         
     */

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    /**       
     *      *            @hibernate.property
     *             column="TOTAL_PV"
     *             length="10"
     *         
     */

    public BigDecimal getTotalPv() {
        return this.totalPv;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setTotalPv(BigDecimal totalPv) {
        this.totalPv = totalPv;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_FREE"
     *             length="2"
     *         
     */

    public String getIsFree() {
        return this.isFree;
    }
    
    /**
     * @spring.validator type="required"
     */
    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER_CODE"
     *             length="20"
     *         
     */

    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
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
     *             column="UPDATE_USER_CODE"
     *             length="20"
     *         
     */

    public String getUpdateUserCode() {
        return this.updateUserCode;
    }
    
    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="UPDATE_TIME"
     *             length="7"
     *         
     */

    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="4000"
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
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("productName").append("='").append(getProductName()).append("' ");			
      buffer.append("productDate").append("='").append(getProductDate()).append("' ");			
      buffer.append("subProductNo").append("='").append(getSubProductNo()).append("' ");			
      buffer.append("subProductName").append("='").append(getSubProductName()).append("' ");			
      buffer.append("qty").append("='").append(getQty()).append("' ");			
      buffer.append("price").append("='").append(getPrice()).append("' ");			
      buffer.append("pv").append("='").append(getPv()).append("' ");			
      buffer.append("totalPrice").append("='").append(getTotalPrice()).append("' ");			
      buffer.append("totalPv").append("='").append(getTotalPv()).append("' ");			
      buffer.append("isFree").append("='").append(getIsFree()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("updateUserCode").append("='").append(getUpdateUserCode()).append("' ");			
      buffer.append("updateTime").append("='").append(getUpdateTime()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmCombinationRelated) ) return false;
		 JpmCombinationRelated castOther = ( JpmCombinationRelated ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getProductName()==castOther.getProductName()) || ( this.getProductName()!=null && castOther.getProductName()!=null && this.getProductName().equals(castOther.getProductName()) ) )
 && ( (this.getProductDate()==castOther.getProductDate()) || ( this.getProductDate()!=null && castOther.getProductDate()!=null && this.getProductDate().equals(castOther.getProductDate()) ) )
 && ( (this.getSubProductNo()==castOther.getSubProductNo()) || ( this.getSubProductNo()!=null && castOther.getSubProductNo()!=null && this.getSubProductNo().equals(castOther.getSubProductNo()) ) )
 && ( (this.getSubProductName()==castOther.getSubProductName()) || ( this.getSubProductName()!=null && castOther.getSubProductName()!=null && this.getSubProductName().equals(castOther.getSubProductName()) ) )
 && ( (this.getQty()==castOther.getQty()) || ( this.getQty()!=null && castOther.getQty()!=null && this.getQty().equals(castOther.getQty()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && ( (this.getPv()==castOther.getPv()) || ( this.getPv()!=null && castOther.getPv()!=null && this.getPv().equals(castOther.getPv()) ) )
 && ( (this.getTotalPrice()==castOther.getTotalPrice()) || ( this.getTotalPrice()!=null && castOther.getTotalPrice()!=null && this.getTotalPrice().equals(castOther.getTotalPrice()) ) )
 && ( (this.getTotalPv()==castOther.getTotalPv()) || ( this.getTotalPv()!=null && castOther.getTotalPv()!=null && this.getTotalPv().equals(castOther.getTotalPv()) ) )
 && ( (this.getIsFree()==castOther.getIsFree()) || ( this.getIsFree()!=null && castOther.getIsFree()!=null && this.getIsFree().equals(castOther.getIsFree()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getUpdateUserCode()==castOther.getUpdateUserCode()) || ( this.getUpdateUserCode()!=null && castOther.getUpdateUserCode()!=null && this.getUpdateUserCode().equals(castOther.getUpdateUserCode()) ) )
 && ( (this.getUpdateTime()==castOther.getUpdateTime()) || ( this.getUpdateTime()!=null && castOther.getUpdateTime()!=null && this.getUpdateTime().equals(castOther.getUpdateTime()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getProductName() == null ? 0 : this.getProductName().hashCode() );
         result = 37 * result + ( getProductDate() == null ? 0 : this.getProductDate().hashCode() );
         result = 37 * result + ( getSubProductNo() == null ? 0 : this.getSubProductNo().hashCode() );
         result = 37 * result + ( getSubProductName() == null ? 0 : this.getSubProductName().hashCode() );
         result = 37 * result + ( getQty() == null ? 0 : this.getQty().hashCode() );
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + ( getPv() == null ? 0 : this.getPv().hashCode() );
         result = 37 * result + ( getTotalPrice() == null ? 0 : this.getTotalPrice().hashCode() );
         result = 37 * result + ( getTotalPv() == null ? 0 : this.getTotalPv().hashCode() );
         result = 37 * result + ( getIsFree() == null ? 0 : this.getIsFree().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getUpdateUserCode() == null ? 0 : this.getUpdateUserCode().hashCode() );
         result = 37 * result + ( getUpdateTime() == null ? 0 : this.getUpdateTime().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   





}
