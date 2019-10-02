package com.joymain.jecs.pd.model;
// Generated 2009-11-10 15:31:27 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_COMBINATION_DETAIL"
 *     
 */

public class PdCombinationDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniNo;
    private String pcNo;
    private String productNo;
    private BigDecimal price=new BigDecimal(0);
    private Long qty;


    // Constructors

    /** default constructor */
    public PdCombinationDetail() {
    }

	/** minimal constructor */
    public PdCombinationDetail(String productNo, BigDecimal price) {
        this.productNo = productNo;
        this.price = price;
    }
    
    /** full constructor */
    public PdCombinationDetail(String pcNo, String productNo, BigDecimal price, Long qty) {
        this.pcNo = pcNo;
        this.productNo = productNo;
        this.price = price;
        this.qty = qty;
    }
    

   
    /**
	 * * @hibernate.id generator-class="sequence"
	 * 
	 * column="UNI_NO"
	 * 
	 * @hibernate.generator-param name="sequence" value="SEQ_PD"
	 */

    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PC_NO"
     *             length="17"
     *         
     */

    public String getPcNo() {
        return this.pcNo;
    }
    
    public void setPcNo(String pcNo) {
        this.pcNo = pcNo;
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
     *             length="18"
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
     *             length="10"
     *         
     */

    public Long getQty() {
        return this.qty;
    }
    
    public void setQty(Long qty) {
        this.qty = qty;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("pcNo").append("='").append(getPcNo()).append("' ");			
      buffer.append("productNo").append("='").append(getProductNo()).append("' ");			
      buffer.append("price").append("='").append(getPrice()).append("' ");			
      buffer.append("qty").append("='").append(getQty()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdCombinationDetail) ) return false;
		 PdCombinationDetail castOther = ( PdCombinationDetail ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getPcNo()==castOther.getPcNo()) || ( this.getPcNo()!=null && castOther.getPcNo()!=null && this.getPcNo().equals(castOther.getPcNo()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && ( (this.getQty()==castOther.getQty()) || ( this.getQty()!=null && castOther.getQty()!=null && this.getQty().equals(castOther.getQty()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getPcNo() == null ? 0 : this.getPcNo().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + ( getQty() == null ? 0 : this.getQty().hashCode() );
         return result;
   }   





}
