package com.joymain.jecs.pm.model;
// Generated 2013-12-18 11:52:23 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_CONFIG_SPEC_DETAILED"
 *     
 */

public class JpmConfigSpecDetailed extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long specNo;
//    private long configNo;
    private String productTemplateNo;
    private String productTemplateName;
    private Long complateAmount;
    private Long complateWeight;
    private Date createTime;
    private BigDecimal price;
    private BigDecimal productNum;
    
    private JpmMemberConfig jpmMemberConfig;

    private List<JpmConfigDetailed> jpmConfigDetailedList = new ArrayList<JpmConfigDetailed>(); 
    // Constructors

    /** default constructor */
    public JpmConfigSpecDetailed() {
    }

	/** minimal constructor */
    public JpmConfigSpecDetailed(long configNo, String productTemplateNo) {
//        this.configNo = configNo;
        this.productTemplateNo = productTemplateNo;
    }
    
    /** full constructor */
    public JpmConfigSpecDetailed(String productTemplateNo, String productTemplateName, Long complateAmount, Long complateWeight, Date createTime, BigDecimal price, BigDecimal productNum) {
//        this.configNo = configNo;
        this.productTemplateNo = productTemplateNo;
        this.productTemplateName = productTemplateName;
        this.complateAmount = complateAmount;
        this.complateWeight = complateWeight;
        this.createTime = createTime;
        this.price = price;
        this.productNum = productNum;
    }
    
    public List<JpmConfigDetailed> getJpmConfigDetailedList()
    {
        return jpmConfigDetailedList;
    }

    public void setJpmConfigDetailedList(List<JpmConfigDetailed> jpmConfigDetailedList)
    {
        this.jpmConfigDetailedList = jpmConfigDetailedList;
    }
    
    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="CONFIG_NO"
     * 
     */
    public JpmMemberConfig getJpmMemberConfig()
    {
        return jpmMemberConfig;
    }

    public void setJpmMemberConfig(JpmMemberConfig jpmMemberConfig)
    {
        this.jpmMemberConfig = jpmMemberConfig;
    }

    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="SPEC_NO"
     *         
     */

    public Long getSpecNo() {
        return this.specNo;
    }
    
    public void setSpecNo(Long specNo) {
        this.specNo = specNo;
    }
//    /**       
//     *      *            @hibernate.property
//     *             column="CONFIG_NO"
//     *             length="10"
//     *             not-null="true"
//     *         
//     */
//
//    public long getConfigNo() {
//        return this.configNo;
//    }
//    
//    public void setConfigNo(long configNo) {
//        this.configNo = configNo;
//    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_TEMPLATE_NO"
     *             length="32"
     *             not-null="true"
     *         
     */

    public String getProductTemplateNo() {
        return this.productTemplateNo;
    }
    
    public void setProductTemplateNo(String productTemplateNo) {
        this.productTemplateNo = productTemplateNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_TEMPLATE_NAME"
     *             length="100"
     *         
     */

    public String getProductTemplateName() {
        return this.productTemplateName;
    }
    
    public void setProductTemplateName(String productTemplateName) {
        this.productTemplateName = productTemplateName;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPLATE_AMOUNT"
     *             length="10"
     *         
     */

    public Long getComplateAmount() {
        return this.complateAmount;
    }
    
    public void setComplateAmount(Long complateAmount) {
        this.complateAmount = complateAmount;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPLATE_WEIGHT"
     *             length="10"
     *         
     */

    public Long getComplateWeight() {
        return this.complateWeight;
    }
    
    public void setComplateWeight(Long complateWeight) {
        this.complateWeight = complateWeight;
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
     *             column="PRICE"
     *             length="22"
     *         
     */

    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getProductNum() {
        return this.productNum;
    }
    
    public void setProductNum(BigDecimal productNum) {
        this.productNum = productNum;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
//      buffer.append("configNo").append("='").append(getConfigNo()).append("' ");			
      buffer.append("productTemplateNo").append("='").append(getProductTemplateNo()).append("' ");			
      buffer.append("productTemplateName").append("='").append(getProductTemplateName()).append("' ");			
      buffer.append("complateAmount").append("='").append(getComplateAmount()).append("' ");			
      buffer.append("complateWeight").append("='").append(getComplateWeight()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("price").append("='").append(getPrice()).append("' ");			
      buffer.append("productNum").append("='").append(getProductNum()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmConfigSpecDetailed) ) return false;
		 JpmConfigSpecDetailed castOther = ( JpmConfigSpecDetailed ) other; 
         
		 return ( (this.getSpecNo()==castOther.getSpecNo()) || ( this.getSpecNo()!=null && castOther.getSpecNo()!=null && this.getSpecNo().equals(castOther.getSpecNo()) ) )
// && (this.getConfigNo()==castOther.getConfigNo())
 && ( (this.getProductTemplateNo()==castOther.getProductTemplateNo()) || ( this.getProductTemplateNo()!=null && castOther.getProductTemplateNo()!=null && this.getProductTemplateNo().equals(castOther.getProductTemplateNo()) ) )
 && ( (this.getProductTemplateName()==castOther.getProductTemplateName()) || ( this.getProductTemplateName()!=null && castOther.getProductTemplateName()!=null && this.getProductTemplateName().equals(castOther.getProductTemplateName()) ) )
 && ( (this.getComplateAmount()==castOther.getComplateAmount()) || ( this.getComplateAmount()!=null && castOther.getComplateAmount()!=null && this.getComplateAmount().equals(castOther.getComplateAmount()) ) )
 && ( (this.getComplateWeight()==castOther.getComplateWeight()) || ( this.getComplateWeight()!=null && castOther.getComplateWeight()!=null && this.getComplateWeight().equals(castOther.getComplateWeight()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && ( (this.getProductNum()==castOther.getProductNum()) || ( this.getProductNum()!=null && castOther.getProductNum()!=null && this.getProductNum().equals(castOther.getProductNum()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSpecNo() == null ? 0 : this.getSpecNo().hashCode() );
//         result = 37 * result + (int) this.getConfigNo();
         result = 37 * result + ( getProductTemplateNo() == null ? 0 : this.getProductTemplateNo().hashCode() );
         result = 37 * result + ( getProductTemplateName() == null ? 0 : this.getProductTemplateName().hashCode() );
         result = 37 * result + ( getComplateAmount() == null ? 0 : this.getComplateAmount().hashCode() );
         result = 37 * result + ( getComplateWeight() == null ? 0 : this.getComplateWeight().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + ( getProductNum() == null ? 0 : this.getProductNum().hashCode() );
         return result;
   }   





}
