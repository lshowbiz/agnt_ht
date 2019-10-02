package com.joymain.jecs.pd.model;
// Generated 2009-9-24 14:26:03 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_STATUS_EXC_STOCK_DETAIL"
 *     
 */

public class PdStatusExcStockDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long uniNo;
    private String productNo;
    private BigDecimal price=new BigDecimal(0);
    private Long damageQty;
    private Long normalQty;
    private Long unknownQty;
    private String seNo;

    // Constructors

    /** default constructor */
    public PdStatusExcStockDetail() {
    }

	/** minimal constructor */
    public PdStatusExcStockDetail(String productNo, BigDecimal price) {
        this.productNo = productNo;
        this.price = price;
    }
    
    /** full constructor */
    public PdStatusExcStockDetail(String productNo, BigDecimal price, Long damageQty, Long normalQty, Long unknownQty) {
        this.productNo = productNo;
        this.price = price;
        this.damageQty = damageQty;
        this.normalQty = normalQty;
        this.unknownQty = unknownQty;
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
     *             column="SE_NO"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getSeNo() {
		return seNo;
	}

	public void setSeNo(String seNo) {
		this.seNo = seNo;
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
     *             column="DAMAGE_QTY"
     *             length="10"
     *         
     */

    public Long getDamageQty() {
        return this.damageQty;
    }
    
    public void setDamageQty(Long damageQty) {
        this.damageQty = damageQty;
    }
    /**       
     *      *            @hibernate.property
     *             column="NORMAL_QTY"
     *             length="10"
     *         
     */

    public Long getNormalQty() {
        return this.normalQty;
    }
    
    public void setNormalQty(Long normalQty) {
        this.normalQty = normalQty;
    }
    /**       
     *      *            @hibernate.property
     *             column="UNKNOWN_QTY"
     *             length="10"
     *         
     */

    public Long getUnknownQty() {
        return this.unknownQty;
    }
    
    public void setUnknownQty(Long unknownQty) {
        this.unknownQty = unknownQty;
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
      buffer.append("damageQty").append("='").append(getDamageQty()).append("' ");			
      buffer.append("normalQty").append("='").append(getNormalQty()).append("' ");			
      buffer.append("unknownQty").append("='").append(getUnknownQty()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdStatusExcStockDetail) ) return false;
		 PdStatusExcStockDetail castOther = ( PdStatusExcStockDetail ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && ( (this.getDamageQty()==castOther.getDamageQty()) || ( this.getDamageQty()!=null && castOther.getDamageQty()!=null && this.getDamageQty().equals(castOther.getDamageQty()) ) )
 && ( (this.getNormalQty()==castOther.getNormalQty()) || ( this.getNormalQty()!=null && castOther.getNormalQty()!=null && this.getNormalQty().equals(castOther.getNormalQty()) ) )
 && ( (this.getUnknownQty()==castOther.getUnknownQty()) || ( this.getUnknownQty()!=null && castOther.getUnknownQty()!=null && this.getUnknownQty().equals(castOther.getUnknownQty()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + ( getDamageQty() == null ? 0 : this.getDamageQty().hashCode() );
         result = 37 * result + ( getNormalQty() == null ? 0 : this.getNormalQty().hashCode() );
         result = 37 * result + ( getUnknownQty() == null ? 0 : this.getUnknownQty().hashCode() );
         return result;
   }   





}
