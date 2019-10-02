package com.joymain.jecs.pd.model;
// Generated 2009-11-21 16:05:41 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_CHECKSTOCK_ORDER_DETAIL"
 *     
 */

public class PdCheckstockOrderDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniNo;
    private String productNo;
    private BigDecimal price;
    private Long sysQty;
    private Long realQty;
    private PdCheckstockOrder pdCheckstockOrder;

    // Constructors

    /** default constructor */
    public PdCheckstockOrderDetail() {
    }

    
    /** full constructor */
    public PdCheckstockOrderDetail(String productNo, BigDecimal price, Long sysQty, Long realQty) {
        this.productNo = productNo;
        this.price = price;
        this.sysQty = sysQty;
        this.realQty = realQty;
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
     *             length="18"
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
     *             column="SYS_QTY"
     *             length="10"
     *         
     */

    public Long getSysQty() {
        return this.sysQty;
    }
    
    public void setSysQty(Long sysQty) {
        this.sysQty = sysQty;
    }
    /**       
     *      *            @hibernate.property
     *             column="REAL_QTY"
     *             length="10"
     *         
     */

    public Long getRealQty() {
        return this.realQty;
    }
    
    public void setRealQty(Long realQty) {
        this.realQty = realQty;
    }
    /**
     * *
     * @hibernate.many-to-one not-null="true" inverse="true"  
     * @hibernate.column name="PCO_NO"
     * 
     */

    public PdCheckstockOrder getPdCheckstockOrder() {
		return pdCheckstockOrder;
	}


	public void setPdCheckstockOrder(PdCheckstockOrder pdCheckstockOrder) {
		this.pdCheckstockOrder = pdCheckstockOrder;
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
      buffer.append("sysQty").append("='").append(getSysQty()).append("' ");			
      buffer.append("realQty").append("='").append(getRealQty()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdCheckstockOrderDetail) ) return false;
		 PdCheckstockOrderDetail castOther = ( PdCheckstockOrderDetail ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && ( (this.getSysQty()==castOther.getSysQty()) || ( this.getSysQty()!=null && castOther.getSysQty()!=null && this.getSysQty().equals(castOther.getSysQty()) ) )
 && ( (this.getRealQty()==castOther.getRealQty()) || ( this.getRealQty()!=null && castOther.getRealQty()!=null && this.getRealQty().equals(castOther.getRealQty()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + ( getSysQty() == null ? 0 : this.getSysQty().hashCode() );
         result = 37 * result + ( getRealQty() == null ? 0 : this.getRealQty().hashCode() );
         return result;
   }   





}
