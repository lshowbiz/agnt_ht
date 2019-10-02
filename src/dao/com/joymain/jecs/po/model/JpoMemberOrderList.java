package com.joymain.jecs.po.model;
// Generated 2009-9-22 10:19:51 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

import java.util.Date;

import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.po.model.JpoMemberOrder;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_MEMBER_ORDER_LIST"
 *     
 */

public class JpoMemberOrderList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long molId;
    private JpoMemberOrder jpoMemberOrder;
	private JpmProductSaleTeamType jpmProductSaleTeamType;
	private BigDecimal price;
    private BigDecimal pv;
    private int qty;
    private BigDecimal weight;
    private BigDecimal volume;
    private String productType;
    private BigDecimal disPrice;
    
    private String productName;

    private Integer productConfigAmount;
    
    // modify gw 2014-11-12  发货标志及发货时间
    private String shipped;
    private Date shippedTime;
    
    //modify gw 2014-11-28 新加三个字段:
    //WAREHOUSE_OPERATION 仓内作业（0001：已接单,0002：已接单,0003：已拣货,00013：已扫描,0005：已打包,0006：已发货）
    private String warehouseOperation;
    //TRACKING_SINGLE 物流跟踪单号
    private String trackingSingle;
    //CONFIRM_RECEIPT 确认收货(0未收货，1部分收货，2全部收货）
    private String confirmReceipt;
    private String proNo;
    
    
    // Constructors


	/** default constructor */
    public JpoMemberOrderList() {
    }

    
    /** full constructor */
    public JpoMemberOrderList(long moId, long productId, BigDecimal price, BigDecimal pv, int qty,String shipped,Date shippedTime){
        this.price = price;
        this.pv = pv;
        this.qty = qty;
        
        this.shipped = shipped;
        this.shippedTime = shippedTime;

        
    }
    
    public String getProductName()
    {
        if(null!=jpmProductSaleTeamType){
            productName = jpmProductSaleTeamType.getJpmProductSaleNew().getProductName();
        }
        return productName;
    }


    public void setProductName(String productName)
    {
        this.productName = productName;
    }


    public Integer getProductConfigAmount()
    {
        return productConfigAmount;
    }


    public void setProductConfigAmount(Integer productConfigAmount)
    {
        this.productConfigAmount = productConfigAmount;
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
     *             column="WEIGHT"
     *             length="18"
     *         
     */

    public BigDecimal getWeight() {
		return weight;
	}


	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
    /**       
     *      *            @hibernate.property
     *             column="VOLUME"
     *             length="18"
     *         
     */

	public BigDecimal getVolume() {
		return volume;
	}


	public void setVolume(BigDecimal volume) {
		this.volume = volume;
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
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="MO_ID"
     * 
     */
    public JpoMemberOrder getJpoMemberOrder() {
		return jpoMemberOrder;
	}


	public void setJpoMemberOrder(JpoMemberOrder jpoMemberOrder) {
		this.jpoMemberOrder = jpoMemberOrder;
	}

    /**
     * *
     * @hibernate.many-to-one not-null="true" lazy="false"
     * @hibernate.column name="PRODUCT_ID"
     * @hibernate.class lazy="false"
     */
	public JpmProductSaleTeamType getJpmProductSaleTeamType() {
		return jpmProductSaleTeamType;
	}


	public void setJpmProductSaleTeamType(
			JpmProductSaleTeamType jpmProductSaleTeamType) {
		this.jpmProductSaleTeamType = jpmProductSaleTeamType;
	}

	
	
	
/*    public JpmProductSale getJpmProductSale() {
		return jpmProductSale;
	}


	public void setJpmProductSale(JpmProductSale jpmProductSale) {
		this.jpmProductSale = jpmProductSale;
	}*/
	
	   /**       
	    *      *            @hibernate.property
	    *             column="PRODUCT_TYPE"
	    *             length="20"
	    *         
	    */

	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}   
	/**       
     *      *            @hibernate.property
     *             column="DISPRICE"
     *             length="18"
     *     
     *         
     */
	public BigDecimal getDisPrice() {
		return disPrice;
	}


	public void setDisPrice(BigDecimal disPrice) {
		this.disPrice = disPrice;
	}


	
	/**       
     *      *            @hibernate.property
     *             column="SHIPPED"
     *             length="50"
     *         
     */
	public String getShipped() {
		return shipped;
	}


	public void setShipped(String shipped) {
		this.shipped = shipped;
	}


	/**       
     *      *            @hibernate.property
     *             column="SHIPPED_TIME"
     *             length="7"
     *         
     */
	public Date getShippedTime() {
		return shippedTime;
	}


	public void setShippedTime(Date shippedTime) {
		this.shippedTime = shippedTime;
	}

	/**       
     *      *            @hibernate.property
     *             column="WAREHOUSE_OPERATION"
     *             length="50"
     *         
     */
	public String getWarehouseOperation() {
		return warehouseOperation;
	}


	public void setWarehouseOperation(String warehouseOperation) {
		this.warehouseOperation = warehouseOperation;
	}


	/**       
     *      *            @hibernate.property
     *             column="TRACKING_SINGLE"
     *             length="1000"
     *         
     */
	public String getTrackingSingle() {
		return trackingSingle;
	}


	public void setTrackingSingle(String trackingSingle) {
		this.trackingSingle = trackingSingle;
	}


	/**       
     *      *            @hibernate.property
     *             column="CONFIRM_RECEIPT"
     *             length="50"
     *         
     */
	public String getConfirmReceipt() {
		return confirmReceipt;
	}


	public void setConfirmReceipt(String confirmReceipt) {
		this.confirmReceipt = confirmReceipt;
	}

	/**       
     * @hibernate.property column="PRODUCT_NO" length="50"        
     */
	public String getProNo() {
		return proNo;
	}


	public void setProNo(String proNo) {
		this.proNo = proNo;
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
      buffer.append("productType").append("='").append(getProductType()).append("' ");	
      
      buffer.append("shipped").append("='").append(getShipped()).append("' ");		
      buffer.append("shippedTime").append("='").append(getShippedTime()).append("' ");
      
      buffer.append("warehouseOperation").append("='").append(getWarehouseOperation()).append("' ");
      buffer.append("trackingSingle").append("='").append(getTrackingSingle()).append("' ");
      buffer.append("confirmReceipt").append("='").append(getConfirmReceipt()).append("' ");

      
      buffer.append("]");
      
      return buffer.toString();
     }

   
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoMemberOrderList) ) return false;
		 JpoMemberOrderList castOther = ( JpoMemberOrderList ) other; 
         
		 return ( (this.getMolId()==castOther.getMolId()) || ( this.getMolId()!=null && castOther.getMolId()!=null && this.getMolId().equals(castOther.getMolId()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
 && (this.getPv()==castOther.getPv())
 && (this.getProductType()==castOther.getProductType())
 && (this.getQty()==castOther.getQty())
 && (this.getShipped()==castOther.getShipped())
 && (this.getShippedTime()==castOther.getShippedTime())
 && (this.getWarehouseOperation()==castOther.getWarehouseOperation())
 && (this.getTrackingSingle()==castOther.getTrackingSingle())
 && (this.getConfirmReceipt()==castOther.getConfirmReceipt());
		 
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMolId() == null ? 0 : this.getMolId().hashCode() );
         result = 37 * result + ( this.jpmProductSaleTeamType == null ? 0 : (int) this.jpmProductSaleTeamType.getPttId().hashCode());
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
         result = 37 * result + ( getPv() == null ? 0 : this.getPv().hashCode() );
         result = 37 * result + ( getVolume() == null ? 0 : this.getVolume().hashCode() );
         result = 37 * result + ( getWeight() == null ? 0 : this.getWeight().hashCode() );
         result = 37 * result + ( getProductType() == null ? 0 : this.getProductType().hashCode() );
         
         result = 37 * result + ( getShipped() == null ? 0 : this.getShipped().hashCode() );
         result = 37 * result + ( getShippedTime() == null ? 0 : this.getShippedTime().hashCode() );
         
         result = 37 * result + ( getWarehouseOperation() == null ? 0 : this.getWarehouseOperation().hashCode() );
         result = 37 * result + ( getTrackingSingle() == null ? 0 : this.getTrackingSingle().hashCode() );
         result = 37 * result + ( getConfirmReceipt() == null ? 0 : this.getConfirmReceipt().hashCode() );


         
         result = 37 * result + this.getQty();
         return result;
   }
   

}
