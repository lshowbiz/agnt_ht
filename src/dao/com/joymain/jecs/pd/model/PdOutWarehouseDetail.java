package com.joymain.jecs.pd.model;
// Generated 2009-9-24 14:17:55 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_OUT_WAREHOUSE_DETAIL"
 *     
 */

public class PdOutWarehouseDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniNo;
    private String productNo;
    private long price;
    private long qty;
    private String owNo;

    // Constructors
    /**       
     *      *            @hibernate.property
     *             column="OW_NO"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getOwNo() {
		return owNo;
	}


	public void setOwNo(String owNo) {
		this.owNo = owNo;
	}


	/** default constructor */
    public PdOutWarehouseDetail() {
    }

    
    /** full constructor */
    public PdOutWarehouseDetail(String productNo, long price, long qty) {
        this.productNo = productNo;
        this.price = price;
        this.qty = qty;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="UNI_NO"
     *         @hibernate.generator-param name="sequence" value="SEQ_PD"
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
     *             not-null="true"
     *         
     */

    public long getPrice() {
        return this.price;
    }
    
    public void setPrice(long price) {
        this.price = price;
    }
    /**       
     *      *            @hibernate.property
     *             column="QTY"
     *             length="10"
     *             not-null="true"
     *         
     */

    public long getQty() {
        return this.qty;
    }
    
    public void setQty(long qty) {
        this.qty = qty;
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
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdOutWarehouseDetail) ) return false;
		 PdOutWarehouseDetail castOther = ( PdOutWarehouseDetail ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && (this.getPrice()==castOther.getPrice())
 && (this.getQty()==castOther.getQty());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + (int) this.getPrice();
         result = 37 * result + (int) this.getQty();
         return result;
   }   





}
