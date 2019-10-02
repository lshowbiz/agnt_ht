package com.joymain.jecs.pm.model;

import java.io.File;
// Generated 2013-6-3 18:03:18 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_PRODUCT_SALE_IMAGE"
 *     
 */

public class JpmProductSaleImage extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	// Fields    

	private Long imageId;
	private Long uniNo;
	private String imageLink;
	private String imageType;
	private Integer imageOrder;
	private String status;

	private JpmProductSaleNew jpmProductSaleNew = new JpmProductSaleNew();
	// Constructors

	/** default constructor */
	public JpmProductSaleImage() {
	}


	/** full constructor */
	public JpmProductSaleImage(Long uniNo, String imageLink, String imageType, Integer imageOrder, String status) {
		this.uniNo = uniNo;
		this.imageLink = imageLink;
		this.imageType = imageType;
		this.imageOrder = imageOrder;
		this.status = status;
	}



	// Property accessors
	/**       
	 *      *            @hibernate.id
	 *             generator-class="sequence"
	 *             type="java.lang.Long"
	 *             column="IMAGE_ID"
	 *         @hibernate.generator-param name="sequence" value="SEQ_PM" 
	 */
	public Long getImageId() {
		return this.imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="UNI_NO"
	 *             length="10"
	 *         
	 */

	public Long getUniNo() {
		return this.uniNo;
	}

	public void setUniNo(Long uniNo) {
		this.uniNo = uniNo;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="IMAGE_LINK"
	 *             length="500"
	 *         
	 */

	public String getImageLink() {
		return this.imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="IMAGE_TYPE"
	 *             length="3"
	 *         
	 */

	public String getImageType() {
		return this.imageType;
	}
	/**
     * @spring.validator type="required"
     */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="IMAGE_ORDER"
	 *             length="3"
	 *         
	 */

	public Integer getImageOrder() {
		return this.imageOrder;
	}

	public void setImageOrder(Integer imageOrder) {
		this.imageOrder = imageOrder;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="STATUS"
	 *             length="1"
	 *         
	 */

	public String getStatus() {
		return this.status;
	}
	/**
     * @spring.validator type="required"
     */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * toString
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("uniNo").append("='").append(getUniNo()).append("' ");			
		buffer.append("imageLink").append("='").append(getImageLink()).append("' ");			
		buffer.append("imageType").append("='").append(getImageType()).append("' ");			
		buffer.append("imageOrder").append("='").append(getImageOrder()).append("' ");			
		buffer.append("status").append("='").append(getStatus()).append("' ");			
		buffer.append("]");

		return buffer.toString();
	}


	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof JpmProductSaleImage) ) return false;
		JpmProductSaleImage castOther = ( JpmProductSaleImage ) other; 

		return ( (this.getImageId()==castOther.getImageId()) || ( this.getImageId()!=null && castOther.getImageId()!=null && this.getImageId().equals(castOther.getImageId()) ) )
		&& ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
		&& ( (this.getImageLink()==castOther.getImageLink()) || ( this.getImageLink()!=null && castOther.getImageLink()!=null && this.getImageLink().equals(castOther.getImageLink()) ) )
		&& ( (this.getImageType()==castOther.getImageType()) || ( this.getImageType()!=null && castOther.getImageType()!=null && this.getImageType().equals(castOther.getImageType()) ) )
		&& ( (this.getImageOrder()==castOther.getImageOrder()) || ( this.getImageOrder()!=null && castOther.getImageOrder()!=null && this.getImageOrder().equals(castOther.getImageOrder()) ) )
		&& ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ( getImageId() == null ? 0 : this.getImageId().hashCode() );
		result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
		result = 37 * result + ( getImageLink() == null ? 0 : this.getImageLink().hashCode() );
		result = 37 * result + ( getImageType() == null ? 0 : this.getImageType().hashCode() );
		result = 37 * result + ( getImageOrder() == null ? 0 : this.getImageOrder().hashCode() );
		result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
		return result;
	}

	/**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" insert="false" update="false" 
	 * @hibernate.column name="UNI_NO"
	 * 
	 */
	public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}


	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}

}