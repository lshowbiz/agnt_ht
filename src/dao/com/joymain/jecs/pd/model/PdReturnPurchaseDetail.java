package com.joymain.jecs.pd.model;

import java.math.BigDecimal;

// Generated 2009-9-24 14:22:42 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_RETURN_PURCHASE_DETAIL"
 *     
 */

public class PdReturnPurchaseDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniNo;
    private String productNo;
    private BigDecimal price= new BigDecimal(0);
    private Long qty;
    private String rpNo;
    private String erpProductNo;

    // Constructors
    /**       
     *      *            @hibernate.property
     *             column="RP_NO"
     *             length="17"
     *         
     */
    public String getRpNo() {
		return rpNo;
	}


	public void setRpNo(String rpNo) {
		this.rpNo = rpNo;
	}


	/** default constructor */
    public PdReturnPurchaseDetail() {
    }

    
    /** full constructor */
    public PdReturnPurchaseDetail(String productNo, BigDecimal price, long qty,String erpProductNo) {
        this.productNo = productNo;
        this.price = price;
        this.qty = qty;
        this.erpProductNo = erpProductNo;
        
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="UNI_NO"
     *         
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
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getProductNo() {
        return this.productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRICE"
     *             length="10"
     *             
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
     *             column="QTY"
     *             length="10"
     *             not-null="true"
     *         
     */

    public Long getQty() {
        return this.qty;
    }
    
    public void setQty(Long qty) {
        this.qty = qty;
    }
   
    
    /**       
     *      *            @hibernate.property
     *             column="ERP_PRODUCT_NO"
     *             length="30"
     *         
     */
    public String getErpProductNo() {
		return erpProductNo;
	}


	public void setErpProductNo(String erpProductNo) {
		this.erpProductNo = erpProductNo;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("price").append("='").append(getPrice()).append("' ");			
      buffer.append("qty").append("='").append(getQty()).append("' ");
      buffer.append("erpProductNo").append("='").append(getErpProductNo()).append("' ");

      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdReturnPurchaseDetail) ) return false;
		 PdReturnPurchaseDetail castOther = ( PdReturnPurchaseDetail ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && (this.getPrice()==castOther.getPrice())
 && (this.getQty()==castOther.getQty())
 && ( (this.getErpProductNo()==castOther.getErpProductNo()) || ( this.getErpProductNo()!=null && castOther.getErpProductNo()!=null && this.getErpProductNo().equals(castOther.getErpProductNo()) ) );

   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + this.getPrice().hashCode();
         result = 37 * result + this.getQty().hashCode();
         result = 37 * result + ( getErpProductNo() == null ? 0 : this.getErpProductNo().hashCode() );
         
         return result;
   }   





}
