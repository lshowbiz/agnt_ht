package com.joymain.jecs.sun.model;
// Generated 2010-11-22 18:11:41 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.pm.model.JpmProductSaleNew;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="SUN_PRODUCT_INFO"
 *     
 */

public class SunProductInfo extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long jpiId;
//    private Long productId;
    private JpmProductSaleNew jpmProductSaleNew=new JpmProductSaleNew();
    private BigDecimal discount;
    private Date beginDate;
    private Date endDate;


    // Constructors

    /** default constructor */
    public SunProductInfo() {
    }

    
    /** full constructor */
    public SunProductInfo(Long productId, BigDecimal discount, Date beginDate, Date endDate) {
//        this.productId = productId;
        this.discount = discount;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="JPI_ID"
      *         @hibernate.generator-param name="sequence" value="SEQ_PM"        
     */

    public Long getJpiId() {
        return this.jpiId;
    }
    
    public void setJpiId(Long jpiId) {
        this.jpiId = jpiId;
    }
    
    
    /**
     * *
     * @hibernate.many-to-one not-null="true" cascade="none"
     * @hibernate.column name="PRODUCT_ID"
     * 
     */
    public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}


	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}


	/**       
     *      *            @hibernate.property
     *             column="DISCOUNT"
     *             length="10"
     *         
     */

    public BigDecimal getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEGIN_DATE"
     *             length="7"
     *         
     */

    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    /**       
     *      *            @hibernate.property
     *             column="END_DATE"
     *             length="7"
     *         
     */

    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
//      buffer.append("productId").append("='").append(this.getJpmProductSale().getUniNo()).append("' ");			
      buffer.append("discount").append("='").append(getDiscount()).append("' ");			
      buffer.append("beginDate").append("='").append(getBeginDate()).append("' ");			
      buffer.append("endDate").append("='").append(getEndDate()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SunProductInfo) ) return false;
		 SunProductInfo castOther = ( SunProductInfo ) other; 
         
		 return ( (this.getJpiId()==castOther.getJpiId()) || ( this.getJpiId()!=null && castOther.getJpiId()!=null && this.getJpiId().equals(castOther.getJpiId()) ) )
// && ( (this.getJpmProductSale()==castOther.getJpmProductSale()) || ( this.getJpmProductSale()!=null && castOther.getJpmProductSale()!=null && this.getJpmProductSale().equals(castOther.getJpmProductSale()) ) )
 && ( (this.getDiscount()==castOther.getDiscount()) || ( this.getDiscount()!=null && castOther.getDiscount()!=null && this.getDiscount().equals(castOther.getDiscount()) ) )
 && ( (this.getBeginDate()==castOther.getBeginDate()) || ( this.getBeginDate()!=null && castOther.getBeginDate()!=null && this.getBeginDate().equals(castOther.getBeginDate()) ) )
 && ( (this.getEndDate()==castOther.getEndDate()) || ( this.getEndDate()!=null && castOther.getEndDate()!=null && this.getEndDate().equals(castOther.getEndDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJpiId() == null ? 0 : this.getJpiId().hashCode() );
//         result = 37 * result + ( getJpmProductSale() == null ? 0 : this.getJpmProductSale().hashCode() );
         result = 37 * result + ( getDiscount() == null ? 0 : this.getDiscount().hashCode() );
         result = 37 * result + ( getBeginDate() == null ? 0 : this.getBeginDate().hashCode() );
         result = 37 * result + ( getEndDate() == null ? 0 : this.getEndDate().hashCode() );
         return result;
   }   





}
