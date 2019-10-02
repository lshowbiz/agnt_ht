package com.joymain.jecs.pm.model;
// Generated 2013-12-17 18:08:34 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_MEMBER_CONFIG"
 *     
 */

public class JpmMemberConfig extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long configNo;
    private String companyCode;
    private Long molId;
    private String userCode;
    private Long moId;
//    private Long productId;
    private String productName;
    private Long amount;
    private Long completeamount;
    private String status;
    private Date createtime;
    private Integer sysNo;
    private BigDecimal weight;
    private BigDecimal price;

    private Set jpmConfigSpecDetailed = new HashSet(0);
    private JpmProductSaleTeamType jpmProductSaleTeamType;
    
    /**
     * 剩余重量
     */
    private Integer oddWeight;
    
    /**
     * 是否允许支付
     * false：不允许
     * true：允许
     */
    private boolean payment;
    // Constructors

    /** default constructor */
    public JpmMemberConfig() {
    }

    
    /** full constructor */
    public JpmMemberConfig(String companyCode, Long molId, String userCode, Long moId, Long productId, String productName, Long amount, Long completeamount, String status, Date createtime, Integer sysNo, BigDecimal weight, BigDecimal price) {
        this.companyCode = companyCode;
        this.molId = molId;
        this.userCode = userCode;
        this.moId = moId;
//        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
        this.completeamount = completeamount;
        this.status = status;
        this.createtime = createtime;
        this.sysNo = sysNo;
        this.weight = weight;
        this.price = price;
    }
    
    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="PRODUCT_ID"
     * 
     */
    public JpmProductSaleTeamType getJpmProductSaleTeamType() {
        return jpmProductSaleTeamType;
    }


    public void setJpmProductSaleTeamType(
            JpmProductSaleTeamType jpmProductSaleTeamType) {
        this.jpmProductSaleTeamType = jpmProductSaleTeamType;
    }

    public Integer getOddWeight()
    {
        return oddWeight;
    }


    public void setOddWeight(Integer oddWeight)
    {
        this.oddWeight = oddWeight;
    }


    public boolean isPayment()
    {
        return payment;
    }


    public void setPayment(boolean payment)
    {
        this.payment = payment;
    }


    /**
     * *
     * 
     * @hibernate.set lazy="true" inverse="true" cascade="all"
     * @hibernate.collection-key column="CONFIG_NO"
     * @hibernate.collection-one-to-many class="com.joymain.jecs.pm.model.JpmConfigSpecDetailed" 
     *                                        
     * 
     */
    public Set getJpmConfigSpecDetailed()
    {
        return jpmConfigSpecDetailed;
    }


    public void setJpmConfigSpecDetailed(Set jpmConfigSpecDetailed)
    {
        this.jpmConfigSpecDetailed = jpmConfigSpecDetailed;
    }


    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="CONFIG_NO"
     *         
     */

    public Long getConfigNo() {
        return this.configNo;
    }
    
    public void setConfigNo(Long configNo) {
        this.configNo = configNo;
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
     *             column="MOL_ID"
     *             length="10"
     *         
     */

    public Long getMolId() {
        return this.molId;
    }
    
    public void setMolId(Long molId) {
        this.molId = molId;
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
     *             column="MO_ID"
     *             length="10"
     *         
     */

    public Long getMoId() {
        return this.moId;
    }
    
    public void setMoId(Long moId) {
        this.moId = moId;
    }
//    /**       
//     *      *            @hibernate.property
//     *             column="PRODUCT_ID"
//     *             length="10"
//     *         
//     */
//
//    public Long getProductId() {
//        return this.productId;
//    }
//    
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NAME"
     *             length="200"
     *         
     */

    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**       
     *      *            @hibernate.property
     *             column="AMOUNT"
     *             length="10"
     *         
     */

    public Long getAmount() {
        return this.amount;
    }
    
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPLETEAMOUNT"
     *             length="10"
     *         
     */

    public Long getCompleteamount() {
        return this.completeamount;
    }
    
    public void setCompleteamount(Long completeamount) {
        this.completeamount = completeamount;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="2"
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
     *             column="CREATETIME"
     *             length="7"
     *         
     */

    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    /**       
     *      *            @hibernate.property
     *             column="SYS_NO"
     *             length="2"
     *         
     */

    public Integer getSysNo() {
        return this.sysNo;
    }
    
    public void setSysNo(Integer sysNo) {
        this.sysNo = sysNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="WEIGHT"
     *             length="22"
     *         
     */

    public BigDecimal getWeight() {
        return this.weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRICE"
     *             length="10"
     *         
     */

    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("molId").append("='").append(getMolId()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("moId").append("='").append(getMoId()).append("' ");			
//      buffer.append("productId").append("='").append(getProductId()).append("' ");			
      buffer.append("productName").append("='").append(getProductName()).append("' ");			
      buffer.append("amount").append("='").append(getAmount()).append("' ");			
      buffer.append("completeamount").append("='").append(getCompleteamount()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("createtime").append("='").append(getCreatetime()).append("' ");			
      buffer.append("sysNo").append("='").append(getSysNo()).append("' ");			
      buffer.append("weight").append("='").append(getWeight()).append("' ");			
      buffer.append("price").append("='").append(getPrice()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmMemberConfig) ) return false;
		 JpmMemberConfig castOther = ( JpmMemberConfig ) other; 
         
		 return ( (this.getConfigNo()==castOther.getConfigNo()) || ( this.getConfigNo()!=null && castOther.getConfigNo()!=null && this.getConfigNo().equals(castOther.getConfigNo()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getMolId()==castOther.getMolId()) || ( this.getMolId()!=null && castOther.getMolId()!=null && this.getMolId().equals(castOther.getMolId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getMoId()==castOther.getMoId()) || ( this.getMoId()!=null && castOther.getMoId()!=null && this.getMoId().equals(castOther.getMoId()) ) )
// && ( (this.getProductId()==castOther.getProductId()) || ( this.getProductId()!=null && castOther.getProductId()!=null && this.getProductId().equals(castOther.getProductId()) ) )
 && ( (this.getProductName()==castOther.getProductName()) || ( this.getProductName()!=null && castOther.getProductName()!=null && this.getProductName().equals(castOther.getProductName()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && ( (this.getCompleteamount()==castOther.getCompleteamount()) || ( this.getCompleteamount()!=null && castOther.getCompleteamount()!=null && this.getCompleteamount().equals(castOther.getCompleteamount()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCreatetime()==castOther.getCreatetime()) || ( this.getCreatetime()!=null && castOther.getCreatetime()!=null && this.getCreatetime().equals(castOther.getCreatetime()) ) )
 && ( (this.getSysNo()==castOther.getSysNo()) || ( this.getSysNo()!=null && castOther.getSysNo()!=null && this.getSysNo().equals(castOther.getSysNo()) ) )
 && ( (this.getWeight()==castOther.getWeight()) || ( this.getWeight()!=null && castOther.getWeight()!=null && this.getWeight().equals(castOther.getWeight()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getConfigNo() == null ? 0 : this.getConfigNo().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getMolId() == null ? 0 : this.getMolId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getMoId() == null ? 0 : this.getMoId().hashCode() );
//         result = 37 * result + ( getProductId() == null ? 0 : this.getProductId().hashCode() );
         result = 37 * result + ( getProductName() == null ? 0 : this.getProductName().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getCompleteamount() == null ? 0 : this.getCompleteamount().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCreatetime() == null ? 0 : this.getCreatetime().hashCode() );
         result = 37 * result + ( getSysNo() == null ? 0 : this.getSysNo().hashCode() );
         result = 37 * result + ( getWeight() == null ? 0 : this.getWeight().hashCode() );
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         return result;
   }   





}
