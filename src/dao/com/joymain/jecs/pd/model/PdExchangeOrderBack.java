package com.joymain.jecs.pd.model;
// Generated 2010-4-7 11:23:14 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_EXCHANGE_ORDER_BACK"
 *     
 */

public class PdExchangeOrderBack extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniNo;
    private String productNo;
    private BigDecimal price;
    private Long originalQty;
    private BigDecimal pv;
    private Long qty=new Long(0);
    private String eoNo;
    
    //modify gw 2014-11-21  添加ERP商品编码和ERP商品价格
    private String erpProductNo;//商品ERP编码
    private BigDecimal erpPrice = new BigDecimal(0);//ERP价格


    // Constructors

    /** default constructor */
    public PdExchangeOrderBack() {
    }

	/** minimal constructor */
    public PdExchangeOrderBack(String productNo, BigDecimal price, long originalQty, long qty) {
        this.productNo = productNo;
        this.price = price;
        this.originalQty = originalQty;
        this.qty = qty;
    }
    
    /** full constructor */
    public PdExchangeOrderBack(String productNo, BigDecimal price, long originalQty, long qty, String eoNo,String erpProductNo, BigDecimal erpPrice) {
        this.productNo = productNo;
        this.price = price;
        this.originalQty = originalQty;
        this.qty = qty;
        this.eoNo = eoNo;
        
        this.erpProductNo = erpProductNo;
        this.erpPrice = erpPrice;
        
    }
    

   
    // Property accessors
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
     *             length="12"
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
     *             column="ORIGINAL_QTY"
     *             length="10"
     *             not-null="true"
     *         
     */

    public Long getOriginalQty() {
        return this.originalQty;
    }
    
    public void setOriginalQty(Long originalQty) {
        this.originalQty = originalQty;
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
     *             column="EO_NO"
     *             length="17"
     *         
     */

    public String getEoNo() {
        return this.eoNo;
    }
    
    public void setEoNo(String eoNo) {
        this.eoNo = eoNo;
    }
   

    
    /**       
     *      *            @hibernate.property
     *             column="PV"
     *             length="12"
     *             not-null="true"
     *         
     */
    public BigDecimal getPv() {
		return pv;
	}

	public void setPv(BigDecimal pv) {
		this.pv = pv;
	}

	 /**       
	    * @hibernate.property
	    *   column="ERP_PRODUCT_NO" length="20"
	    *             
	    *         
	    */
		public String getErpProductNo() {
			return erpProductNo;
		}
		
		public void setErpProductNo(String erpProductNo) {
			this.erpProductNo = erpProductNo;
		}

		/**       
	    * @hibernate.property
	    *   column="ERP_PRICE"
	    *             
	    *         
	    */
		public BigDecimal getErpPrice() {
			return erpPrice;
		}


		public void setErpPrice(BigDecimal erpPrice) {
			this.erpPrice = erpPrice;
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
      buffer.append("originalQty").append("='").append(getOriginalQty()).append("' ");			
      buffer.append("qty").append("='").append(getQty()).append("' ");			
      buffer.append("eoNo").append("='").append(getEoNo()).append("' ");	
      
      buffer.append("erpProductNo").append("='").append(getErpProductNo()).append("' ");
      buffer.append("erpPrice").append("='").append(getErpPrice()).append("' ");
		
      
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdExchangeOrderBack) ) return false;
		 PdExchangeOrderBack castOther = ( PdExchangeOrderBack ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && (this.getOriginalQty()==castOther.getOriginalQty())
 && (this.getQty()==castOther.getQty())
 && ( (this.getEoNo()==castOther.getEoNo()) || ( this.getEoNo()!=null && castOther.getEoNo()!=null && this.getEoNo().equals(castOther.getEoNo()) ) )
 && ((this.getErpProductNo() == castOther.getErpProductNo()) || (this.getErpProductNo() != null&& castOther.getErpProductNo() != null && this.getErpProductNo().equals(castOther.getErpProductNo()) ))
 && ((this.getErpPrice() == castOther.getErpPrice()) || (this.getErpPrice() != null && castOther.getErpPrice() != null ));
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + ( getOriginalQty() == null ? 0 : this.getOriginalQty().hashCode() ) ;
         result = 37 * result + ( getOriginalQty() == null ? 0 : this.getOriginalQty().hashCode() ) ;
         result = 37 * result + ( getEoNo() == null ? 0 : this.getEoNo().hashCode() );
         
         result = 37 * result+ (getErpProductNo() == null ? 0 : this.getErpProductNo().hashCode());
 		 result = 37 * result+ (getErpPrice() == null ? 0 : this.getErpPrice().hashCode());
 		 
         
         return result;
   }   





}
