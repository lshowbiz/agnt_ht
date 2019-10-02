package com.joymain.jecs.fi.model;
// Generated 2010-11-16 11:13:45 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

import com.joymain.jecs.pm.model.JpmProductSaleNew;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_SUN_ORDER_LIST"
 *     
 */

public class JfiSunOrderList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long molId;
    private JfiSunOrder jfiSunOrder;
	private JpmProductSaleNew jpmProductSaleNew;
    private BigDecimal price;
    private BigDecimal pv;
    private int qty;
    private BigDecimal weight;
    private BigDecimal volume;
    private BigDecimal sprice;
    private int rqty;
    private BigDecimal srprice;


    // Constructors

    /** default constructor */
    public JfiSunOrderList() {
    }

	/** minimal constructor */
    public JfiSunOrderList(long productId, BigDecimal price, BigDecimal pv, int qty) {
        this.price = price;
        this.pv = pv;
        this.qty = qty;
    }
    
    /** full constructor */
    public JfiSunOrderList(long productId, BigDecimal price, BigDecimal pv, int qty, BigDecimal weight, BigDecimal volume, BigDecimal sprice) {
        this.price = price;
        this.pv = pv;
        this.qty = qty;
        this.weight = weight;
        this.volume = volume;
        this.sprice = sprice;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="MOL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PO"
     *         
     */

    public Long getMolId() {
        return this.molId;
    }
    
    public void setMolId(Long molId) {
        this.molId = molId;
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

    public void setJpmProductSale(JpmProductSaleNew jpmProductSaleNew) {
    	this.jpmProductSaleNew = jpmProductSaleNew;
    }   

    /**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="MO_ID"
     * 
     */
    public JfiSunOrder getJfiSunOrder() {
    	return jfiSunOrder;
    }

    public void setJfiSunOrder(JfiSunOrder jfiSunOrder) {
    	this.jfiSunOrder = jfiSunOrder;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRICE"
     *             length="22"
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
     *             length="5"
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
     *      *            @hibernate.property
     *             column="S_PRICE"
     *             length="22"
     *         
     */

    public BigDecimal getSprice() {
        return this.sprice;
    }
    
    public void setSprice(BigDecimal sprice) {
        this.sprice = sprice;
    }

    /**       
     *      *            @hibernate.property
     *             column="S_RPRICE"
     *             length="22"
     *         
     */
    public BigDecimal getSrprice() {
    	return srprice;
    }

    public void setSrprice(BigDecimal srprice) {
    	this.srprice = srprice;
    }

    /**       
     *      *            @hibernate.property
     *             column="R_QTY"
     *             length="5"
     *         
     */
    public int getRqty() {
    	return rqty;
    }

    public void setRqty(int rqty) {
    	this.rqty = rqty;
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
      buffer.append("sprice").append("='").append(getSprice()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiSunOrderList) ) return false;
		 JfiSunOrderList castOther = ( JfiSunOrderList ) other; 
         
		 return ( (this.getMolId()==castOther.getMolId()) || ( this.getMolId()!=null && castOther.getMolId()!=null && this.getMolId().equals(castOther.getMolId()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && (this.getPv()==castOther.getPv())
 && (this.getQty()==castOther.getQty())
 && ( (this.getWeight()==castOther.getWeight()) || ( this.getWeight()!=null && castOther.getWeight()!=null && this.getWeight().equals(castOther.getWeight()) ) )
 && ( (this.getVolume()==castOther.getVolume()) || ( this.getVolume()!=null && castOther.getVolume()!=null && this.getVolume().equals(castOther.getVolume()) ) )
 && ( (this.getSprice()==castOther.getSprice()) || ( this.getSprice()!=null && castOther.getSprice()!=null && this.getSprice().equals(castOther.getSprice()) ) );
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
         result = 37 * result + ( getSprice() == null ? 0 : this.getSprice().hashCode() );
         return result;
   }

public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
	this.jpmProductSaleNew = jpmProductSaleNew;
}





}
