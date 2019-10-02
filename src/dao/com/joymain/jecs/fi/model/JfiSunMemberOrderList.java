package com.joymain.jecs.fi.model;
// Generated 2010-1-14 14:33:37 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

import com.joymain.jecs.pm.model.JpmProductSaleNew;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_SUN_MEMBER_ORDER_LIST"
 *     
 */

public class JfiSunMemberOrderList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long molId;
    private JfiSunMemberOrder jfiSunMemberOrder;
	private JpmProductSaleNew jpmProductSaleNew;
	private BigDecimal price;
    private BigDecimal pv;
    private int qty;
    private BigDecimal weight;
    private BigDecimal volume;


    // Constructors

    /** default constructor */
    public JfiSunMemberOrderList() {
    }

	/** minimal constructor */
    public JfiSunMemberOrderList(long moId, long productId, BigDecimal price, BigDecimal pv, int qty) {
        this.price = price;
        this.pv = pv;
        this.qty = qty;
    }
    
    /** full constructor */
    public JfiSunMemberOrderList(long moId, long productId, BigDecimal price, BigDecimal pv, int qty, BigDecimal weight, BigDecimal volume) {
        this.price = price;
        this.pv = pv;
        this.qty = qty;
        this.weight = weight;
        this.volume = volume;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="MOL_ID"
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
     *             column="PV"
     *             length="18"
     *             not-null="true"
     *         
     */

    public BigDecimal getPv() {
        return this.pv;
    }
    
    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }
    /**       
     *      *            @hibernate.property
     *             column="QTY"
     *             length="5"
     *             not-null="true"
     *         
     */

    public int getQty() {
        return this.qty;
    }
    
    public void setQty(int qty) {
        this.qty = qty;
    }
    /**       
     *      *            @hibernate.property
     *             column="WEIGHT"
     *             length="10"
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
     *             column="VOLUME"
     *             length="10"
     *         
     */

    public BigDecimal getVolume() {
        return this.volume;
    }
    
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="MO_ID"
     * 
     */
    public JfiSunMemberOrder getJfiSunMemberOrder() {
		return jfiSunMemberOrder;
	}

	public void setJfiSunMemberOrder(JfiSunMemberOrder jfiSunMemberOrder) {
		this.jfiSunMemberOrder = jfiSunMemberOrder;
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

	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
      buffer.append("price").append("='").append(getPrice()).append("' ");			
      buffer.append("pv").append("='").append(getPv()).append("' ");			
      buffer.append("qty").append("='").append(getQty()).append("' ");			
      buffer.append("weight").append("='").append(getWeight()).append("' ");			
      buffer.append("volume").append("='").append(getVolume()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiSunMemberOrderList) ) return false;
		 JfiSunMemberOrderList castOther = ( JfiSunMemberOrderList ) other; 
         
		 return ( (this.getMolId()==castOther.getMolId()) || ( this.getMolId()!=null && castOther.getMolId()!=null && this.getMolId().equals(castOther.getMolId()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && ( (this.getPv()==castOther.getPv()) || ( this.getPv()!=null && castOther.getPv()!=null && this.getPv().equals(castOther.getPv()) ) )
 && (this.getQty()==castOther.getQty())
 && ( (this.getWeight()==castOther.getWeight()) || ( this.getWeight()!=null && castOther.getWeight()!=null && this.getWeight().equals(castOther.getWeight()) ) )
 && ( (this.getVolume()==castOther.getVolume()) || ( this.getVolume()!=null && castOther.getVolume()!=null && this.getVolume().equals(castOther.getVolume()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMolId() == null ? 0 : this.getMolId().hashCode() );
//         result = 37 * result + ( this.jpmProductSale == null ? 0 : (int) this.jpmProductSale.getUniNo().hashCode());
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + ( getPv() == null ? 0 : this.getPv().hashCode() );
         result = 37 * result + this.getQty();
         result = 37 * result + ( getWeight() == null ? 0 : this.getWeight().hashCode() );
         result = 37 * result + ( getVolume() == null ? 0 : this.getVolume().hashCode() );
         return result;
   }   





}
