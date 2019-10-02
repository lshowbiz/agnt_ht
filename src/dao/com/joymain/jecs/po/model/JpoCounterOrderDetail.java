package com.joymain.jecs.po.model;
// Generated 2009-11-3 17:53:19 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

import com.joymain.jecs.pm.model.JpmProductSaleNew;




/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_COUNTER_ORDER_DETAIL"
 *     
 */

public class JpoCounterOrderDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long codNo;
    private JpmProductSaleNew jpmProductSaleNew;
    private BigDecimal price=new BigDecimal(0);
    private Integer qty;
    private JpoCounterOrder jpoCounterOrder;


    // Constructors

    /** default constructor */
    public JpoCounterOrderDetail() {
    }

    
    /** full constructor */
    public JpoCounterOrderDetail(long coNo, long productId, BigDecimal price, int qty) {
        this.price = price;
        this.qty = qty;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="COD_NO" 
     *@hibernate.generator-param name="sequence" value="SEQ_PO"
     *         
     */

    public Long getCodNo() {
        return this.codNo;
    }
    
    public void setCodNo(Long codNo) {
        this.codNo = codNo;
    }


    /**       
     *      *            @hibernate.property
     *             column="PRICE"
     *             length="11"
     *             not-null="true"
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
     *             length="8"
     *             not-null="true"
     *         
     */
    public Integer getQty() {
		return qty;
	}


	public void setQty(Integer qty) {
		this.qty = qty;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("price").append("='").append(getPrice()).append("' ");			
      buffer.append("qty").append("='").append(getQty()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoCounterOrderDetail) ) return false;
		 JpoCounterOrderDetail castOther = ( JpoCounterOrderDetail ) other; 
         
		 return ( (this.getCodNo()==castOther.getCodNo()) || ( this.getCodNo()!=null && castOther.getCodNo()!=null && this.getCodNo().equals(castOther.getCodNo()) ) )

 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && (this.getQty()==castOther.getQty());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCodNo() == null ? 0 : this.getCodNo().hashCode() );

         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + this.getQty();
         return result;
   }

   /**
    * *
    * @hibernate.many-to-one not-null="true"
    * @hibernate.column name="PRODUCT_ID"
    * 
    */
	public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}


	public void setJpmProductSaleNew(
			JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}

/*public JpmProductSale getJpmProductSale() {
	return jpmProductSale;
}


public void setJpmProductSale(JpmProductSale jpmProductSale) {
	this.jpmProductSale = jpmProductSale;
}*/

/**
 * *
 * @hibernate.many-to-one not-null="true" 
 * @hibernate.column name="CO_NO"
 * 
 */
public JpoCounterOrder getJpoCounterOrder() {
	return jpoCounterOrder;
}


public void setJpoCounterOrder(JpoCounterOrder jpoCounterOrder) {
	this.jpoCounterOrder = jpoCounterOrder;
}






}
