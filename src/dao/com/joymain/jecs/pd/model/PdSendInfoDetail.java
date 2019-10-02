package com.joymain.jecs.pd.model;

// Generated 2009-9-21 11:37:31 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="PD_SEND_INFO_DETAIL"
 * 
 */

public class PdSendInfoDetail extends com.joymain.jecs.model.BaseObject
		implements java.io.Serializable {

	// Fields

	private Long uniNo;
	private String productNo;
	private BigDecimal price;
	private Long qty;
	private Long acceptQty;
	private BigDecimal weight;
	private String trackingNo;
	private String labelCode;
	
	 // modify gw 2014-11-12  发货标志及发货时间    2015-03-19注释两个冗余字段
   // private String shipped;
    //private Date shippedTime;
	
    //modify gw 2014-11-21  添加ERP商品编码和ERP商品价格
    private String erpProductNo;//商品ERP编码
    private BigDecimal erpPrice = new BigDecimal(0);//ERP价格

    
    //modify gw 2015-04-14 套餐拆开发货   
    //COMBINATION_PRODUCT_NO:所属套餐编码
    private String combinationProductNo;
    //CONFIRM_RECEIPT 确认收货(0未收货，1部分收货，2全部收货）--2015年11月25日优化后这个字段弃用
    private String confirmReceipt;
    //RECEIPT_QTY 已收货数量（数字型）--2015年11月25日优化后这个字段弃用(在商品是否收货的时候用到，其他地方均没有用到)
    private Long receiptQty;


	/**
	 * * @hibernate.property column="ACCEPT_QTY" length="10" 
	 * 
	 */
	public Long getAcceptQty() {
		return acceptQty;
	}

	public void setAcceptQty(Long acceptQty) {
		this.acceptQty = acceptQty;
	}

	
	
	public PdSendInfoDetail(String productNo, BigDecimal price, Long qty,
			String siNo) {
		super();
		this.productNo = productNo;
		this.price = price;
		this.qty = qty;
		this.siNo = siNo;
	}



	private String siNo;

	// private PdSendInfo pdSendInfo = new PdSendInfo();

	// Constructors
	/**
	 * * @hibernate.property column="SI_NO" length="20" not-null="true"
	 * 
	 */
	public String getSiNo() {
		return siNo;
	}

	public void setSiNo(String siNo) {
		this.siNo = siNo;
	}

	/** default constructor */
	public PdSendInfoDetail() {
	}

	/** full constructor */
	public PdSendInfoDetail(String productNo, BigDecimal price, Long qty) {
		this.productNo = productNo;
		this.price = price;
		this.qty = qty;
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
	 * * @hibernate.property column="LABEL_CODE" 
	 * 
	 */
	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	/**
	 * * @hibernate.property column="PRODUCT_NO" length="20" not-null="true"
	 * 
	 */

	public String getProductNo() {
		return this.productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	/**
	 * * @hibernate.property column="PRICE" length="18" not-null="true"
	 * 
	 */

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * * @hibernate.property column="QTY" length="10" not-null="true"
	 * 
	 */

	public Long getQty() {
		return this.qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	/**
	 * * @hibernate.property column="weight" length="10" 
	 * 
	 */
	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	/**
	 * * @hibernate.property column="TRACKING_NO" length="200"
	 * 
	 */

	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}
	
	/**       
     *      *            @hibernate.property
     *             column="SHIPPED"
     *             length="50"
     *         
     *//*
	public String getShipped() {
		return shipped;
	}


	public void setShipped(String shipped) {
		this.shipped = shipped;
	}*/


	/**       
     *      *            @hibernate.property
     *             column="SHIPPED_TIME"
     *             length="7"
     *         
     *//*
	public Date getShippedTime() {
		return shippedTime;
	}


	public void setShippedTime(Date shippedTime) {
		this.shippedTime = shippedTime;
	}*/
	
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
		 * * @hibernate.property column="COMBINATION_PRODUCT_NO" length="50"
		 * 
		 */
	    public String getCombinationProductNo() {
			return combinationProductNo;
		}

		public void setCombinationProductNo(String combinationProductNo) {
			this.combinationProductNo = combinationProductNo;
		}
		
		
		/**
		 * * @hibernate.property column="CONFIRM_RECEIPT" length="50"
		 * 
		 */
	    public String getConfirmReceipt() {
			return confirmReceipt;
		}

		public void setConfirmReceipt(String confirmReceipt) {
			this.confirmReceipt = confirmReceipt;
		}

		
		
		/**
		 * * @hibernate.property column="RECEIPT_QTY" length="10" 
		 * 
		 */
	    public Long getReceiptQty() {
			return receiptQty;
		}

		public void setReceiptQty(Long receiptQty) {
			this.receiptQty = receiptQty;
		}

	/**
	 * toString
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(
				Integer.toHexString(hashCode())).append(" [");
		buffer.append("productNo").append("='").append(getProductNo()).append(
				"' ");
		buffer.append("price").append("='").append(getPrice()).append("' ");
		buffer.append("qty").append("='").append(getQty()).append("' ");
		
		//buffer.append("shipped").append("='").append(getShipped()).append("' ");
		//buffer.append("shippedTime").append("='").append(getShippedTime()).append("' ");
		
		buffer.append("erpProductNo").append("='").append(getErpProductNo()).append("' ");
		buffer.append("erpPrice").append("='").append(getErpPrice()).append("' ");
		buffer.append("combinationProductNo").append("='").append(getCombinationProductNo()).append("' ");
		buffer.append("confirmReceipt").append("='").append(getConfirmReceipt()).append("' ");



		buffer.append("]");

		return buffer.toString();
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PdSendInfoDetail))
			return false;
		PdSendInfoDetail castOther = (PdSendInfoDetail) other;

		return ((this.getUniNo() == castOther.getUniNo()) || (this.getUniNo() != null
				&& castOther.getUniNo() != null && this.getUniNo().equals(
				castOther.getUniNo())))
				&& ((this.getProductNo() == castOther.getProductNo()) || (this
						.getProductNo() != null
						&& castOther.getProductNo() != null && this
						.getProductNo().equals(castOther.getProductNo())))
				&& ((this.getPrice() == castOther.getPrice()) || (this
						.getPrice() != null
						&& castOther.getPrice() != null && this.getPrice()
						.equals(castOther.getPrice())))
				&& (this.getQty() == castOther.getQty())
				/*&& ((this.getShipped() == castOther.getShipped()) || (this
						.getShipped() != null
						&& castOther.getShipped() != null && this.getShipped()
						.equals(castOther.getShipped())))
				&& ((this.getShippedTime() == castOther.getShippedTime()) || (this
						.getShippedTime() != null
						&& castOther.getShippedTime() != null ))*/
				&& ((this.getErpProductNo() == castOther.getErpProductNo()) || (this
						.getErpProductNo() != null
						&& castOther.getErpProductNo() != null && this.getErpProductNo()
						.equals(castOther.getErpProductNo()) ))
				&& ((this.getErpPrice() == castOther.getErpPrice()) || (this
						.getErpPrice() != null
						&& castOther.getErpPrice() != null ))
				&& ((this.getCombinationProductNo() == castOther.getCombinationProductNo()) || (this
						.getCombinationProductNo() != null
						&& castOther.getCombinationProductNo() != null ))
				&& ((this.getConfirmReceipt() == castOther.getConfirmReceipt()) || (this
						.getConfirmReceipt() != null
						&& castOther.getConfirmReceipt() != null ));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUniNo() == null ? 0 : this.getUniNo().hashCode());
		result = 37 * result
				+ (getProductNo() == null ? 0 : this.getProductNo().hashCode());
		result = 37 * result
				+ (getPrice() == null ? 0 : this.getPrice().hashCode());
		result = 37 * result + getQty().hashCode();
		//result = 37 * result+ (getShipped() == null ? 0 : this.getShipped().hashCode());
		//result = 37 * result+ (getShippedTime() == null ? 0 : this.getShippedTime().hashCode());
		
		result = 37 * result+ (getErpProductNo() == null ? 0 : this.getErpProductNo().hashCode());
		result = 37 * result+ (getErpPrice() == null ? 0 : this.getErpPrice().hashCode());
		result = 37 * result+ (getCombinationProductNo() == null ? 0 : this.getCombinationProductNo().hashCode());
		result = 37 * result+ (getConfirmReceipt() == null ? 0 : this.getConfirmReceipt().hashCode());
		
		
		return result;
	}

}
