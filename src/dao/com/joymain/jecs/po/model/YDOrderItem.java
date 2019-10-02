package com.joymain.jecs.po.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="yd_order_item"   
 */
public class YDOrderItem  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long molId;
	private YDOrder ydOrder;
	private String productNo;
	private String productName;
	private BigDecimal price;
	private BigDecimal pv;
	private BigDecimal qty;
	
	/**       
     *  @hibernate.id generator-class="sequence" 
     *  type="java.lang.Long" column="MOL_ID"
     *	@hibernate.generator-param name="sequence" value="seq_pm"
     *         
     */
	public Long getMolId() {
		return molId;
	}
	public void setMolId(Long molId) {
		this.molId = molId;
	}
	/**
	 * *
	 * 
	 * @hibernate.many-to-one not-null="true" lazy="false"
	 * @hibernate.column name="MO_ID"
	 * 
	 */
	public YDOrder getYdOrder() {
		return ydOrder;
	}
	public void setYdOrder(YDOrder ydOrder) {
		this.ydOrder = ydOrder;
	}
	/**       
     * @hibernate.property column="PRODUCTNO"
     */
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	/**       
     * @hibernate.property column="PRODUCTNAME"
     */
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**       
     * @hibernate.property column="PRICE"
     */
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**       
     * @hibernate.property column="PV"
     */
	public BigDecimal getPv() {
		return pv;
	}
	public void setPv(BigDecimal pv) {
		this.pv = pv;
	}
	/**       
     * @hibernate.property column="QTY"
     */
	public BigDecimal getQty() {
		return qty;
	}
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
	   
}
