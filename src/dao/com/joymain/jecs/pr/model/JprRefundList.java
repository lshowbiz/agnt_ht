package com.joymain.jecs.pr.model;
// Generated 2009-9-22 10:21:23 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pr.model.JprRefund;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPR_REFUND_LIST"
 *     
 */

public class JprRefundList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long prlId;
	private JprRefund jprRefund;
	//private JpmProductSale jpmProductSale;
	private JpmProductSaleTeamType jpmProductSaleTeamType;
	private BigDecimal price;
    private int qty;
    private BigDecimal pv;
    private Long molId;

    //modify gw 2014-11-21  添加ERP商品编码和ERP商品价格
    private String erpProductNo;//商品ERP编码
    private BigDecimal erpPrice = new BigDecimal(0);//ERP价格
    
    
    // Constructors

    /** default constructor */
    public JprRefundList() {
    }

    
    /** full constructor */
    public JprRefundList(long productNo, BigDecimal price, int qty, BigDecimal pv) {
        this.price = price;
        this.qty = qty;
        this.pv = pv;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="PRL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PR"
     *         
     */

    public Long getPrlId() {
        return this.prlId;
    }
    
    public void setPrlId(Long prlId) {
        this.prlId = prlId;
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
     *             column="PV"
     *             length="18"
     *         
     */

    public BigDecimal getPv() {
        return this.pv;
    }
    
    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }
    
	/**
	 * @hibernate.many-to-one column="RO_NO" not-null="true"
	 * @return
	 */
    public JprRefund getJprRefund() {
		return jprRefund;
	}


	public void setJprRefund(JprRefund jprRefund) {
		this.jprRefund = jprRefund;
	}

	/**
	 * @hibernate.many-to-one column="PRODUCT_ID" cascade="none"
	 * @return
	 */
	public JpmProductSaleTeamType getJpmProductSaleTeamType() {
		return jpmProductSaleTeamType;
	}


	public void setJpmProductSaleTeamType(
			JpmProductSaleTeamType jpmProductSaleTeamType) {
		this.jpmProductSaleTeamType = jpmProductSaleTeamType;
	}   

	
	
/*	public JpmProductSale getJpmProductSale() {
		return jpmProductSale;
	}


	public void setJpmProductSale(JpmProductSale jpmProductSale) {
		this.jpmProductSale = jpmProductSale;
	}
   */
	
	
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
      buffer.append("price").append("='").append(getPrice()).append("' ");			
      buffer.append("qty").append("='").append(getQty()).append("' ");			
      buffer.append("pv").append("='").append(getPv()).append("' ");	
      
      buffer.append("erpProductNo").append("='").append(getErpProductNo()).append("' ");
      buffer.append("erpPrice").append("='").append(getErpPrice()).append("' ");
		
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JprRefundList) ) return false;
		 JprRefundList castOther = ( JprRefundList ) other; 
         
		 return ( (this.getPrlId()==castOther.getPrlId()) || ( this.getPrlId()!=null && castOther.getPrlId()!=null && this.getPrlId().equals(castOther.getPrlId()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && (this.getQty()==castOther.getQty())
 && (this.getPv()==castOther.getPv())
 && ((this.getErpProductNo() == castOther.getErpProductNo()) || (this
						.getErpProductNo() != null
						&& castOther.getErpProductNo() != null && this.getErpProductNo().equals(castOther.getErpProductNo()) ))
				&& ((this.getErpPrice() == castOther.getErpPrice()) || (this
						.getErpPrice() != null
						&& castOther.getErpPrice() != null ));
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPrlId() == null ? 0 : this.getPrlId().hashCode() );
         result = 37 * result + ( this.jpmProductSaleTeamType == null ? 0 : (int) this.jpmProductSaleTeamType.getPttId().hashCode());
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + this.getQty();
         result = 37 * result + ( getPv() == null ? 0 : this.getPv().hashCode() );
         
         result = 37 * result+ (getErpProductNo() == null ? 0 : this.getErpProductNo().hashCode());
 		 result = 37 * result+ (getErpPrice() == null ? 0 : this.getErpPrice().hashCode());
         
         return result;
   }


   /**       
    *      *            @hibernate.property
    *             column="MOL_ID"
    *             length="10"
    *             not-null="true"
    *         
    */
public Long getMolId() {
	return molId;
}


public void setMolId(Long molId) {
	this.molId = molId;
}







}
