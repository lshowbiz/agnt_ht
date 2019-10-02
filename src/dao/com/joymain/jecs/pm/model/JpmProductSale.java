package com.joymain.jecs.pm.model;
// Generated 2009-9-16 16:02:16 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_PRODUCT_SALE"
 *     
 */

public class JpmProductSale extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	// Fields    

	private Long uniNo;
	private String companyCode;
	private String productName;
	private JpmProduct jpmProduct = new JpmProduct();


	private BigDecimal fpPrice=new BigDecimal(0);
	private BigDecimal fpPv=new BigDecimal(0);
	private BigDecimal mpPrice=new BigDecimal(0);
	private BigDecimal mpPv=new BigDecimal(0);
	private BigDecimal storeFpPrice=new BigDecimal(0);
	private BigDecimal storeFpPv=new BigDecimal(0);
	private BigDecimal storeMpPrice=new BigDecimal(0);
	private BigDecimal storeMpPv=new BigDecimal(0);


	private BigDecimal substoreFpPrice=new BigDecimal(0);
	private BigDecimal substoreFpPv=new BigDecimal(0);
	private BigDecimal substoreMpPrice=new BigDecimal(0);
	private BigDecimal substoreMpPv=new BigDecimal(0);

	private BigDecimal specialPrice=new BigDecimal(0);
	private BigDecimal specialPv=new BigDecimal(0);

	private BigDecimal dreamFpPrice=new BigDecimal(0);
	private BigDecimal dreamFpPv=new BigDecimal(0);
	private BigDecimal dreamMpPrice=new BigDecimal(0);
	private BigDecimal dreamMpPv=new BigDecimal(0);



	private BigDecimal whoPrice=new BigDecimal(0);
	private BigDecimal discountPrice=new BigDecimal(0);
	private BigDecimal weight=new BigDecimal(0);
	private BigDecimal volume=new BigDecimal(0);
	private BigDecimal length=new BigDecimal(0);
	private BigDecimal width=new BigDecimal(0);
	private BigDecimal height=new BigDecimal(0);
	private String imageLink;
	private String albumLink;
	private String status;
	private String confirm;
	private String controlFirst;
	private String controlUpdate;
	private String controlRepurchase;
	private String controlAutosale;

	private String controlPointExchange;
	private String storeControlFirst;
	private String storeControlUpdate;
	private String storeControlRepurchase;

	private String subStoreFirst;
	private String subStoreUpdate;
	private String subStoreRepurchase;

	private String remark;
	private String productDesc;

	private String changeabledFlag;

	private Long storageCordon=new Long(0);
	private Date startOnSale;
	private Date endOnSale;
	private String shipStrategy;

	private String hotFlag;
	private String isHidden;//Add By WuCF 20130517 是否隐藏
	private Long sortFlag;

	private Set jmiMemberTeams = new HashSet();

	private String briefIntroduction;//简介
	private String healthKnowledge;//健康知识
	private String productSpecification;//规格
	
	// Constructors

	/** default constructor */
	public JpmProductSale() {
	}


	/** full constructor */
	public JpmProductSale(String companyCode, String productName, BigDecimal fpPrice, BigDecimal fpPv, BigDecimal mpPrice, BigDecimal mpPv, BigDecimal storeFpPrice, BigDecimal storeFpPv, BigDecimal storeMpPrice, BigDecimal storeMpPv, BigDecimal whoPrice, BigDecimal discountPrice, BigDecimal weight, BigDecimal volume, BigDecimal length, BigDecimal width, BigDecimal height, String imageLink, String albumLink, String status, String confirm, String controlFirst, String controlUpdate, String controlRepurchase, String remark) {
		this.companyCode = companyCode;
		this.productName = productName;
		this.fpPrice = fpPrice;
		this.fpPv = fpPv;
		this.mpPrice = mpPrice;
		this.mpPv = mpPv;
		this.storeFpPrice = storeFpPrice;
		this.storeFpPv = storeFpPv;
		this.storeMpPrice = storeMpPrice;
		this.storeMpPv = storeMpPv;
		this.whoPrice = whoPrice;
		this.discountPrice = discountPrice;
		this.weight = weight;
		this.volume = volume;
		this.length = length;
		this.width = width;
		this.height = height;
		this.imageLink = imageLink;
		this.albumLink = albumLink;
		this.status = status;
		this.confirm = confirm;
		this.controlFirst = controlFirst;
		this.controlUpdate = controlUpdate;
		this.controlRepurchase = controlRepurchase;
		this.remark = remark;
	}



	// Property accessors
	/**       
	 *      *            @hibernate.id
	 *             generator-class="sequence"
	 *             type="java.lang.Long"
	 *             column="UNI_NO"
	 *         @hibernate.generator-param name="sequence" value="SEQ_PM" 
	 */

	public Long getUniNo() {
		return this.uniNo;
	}

	public void setUniNo(Long uniNo) {
		this.uniNo = uniNo;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="HOT_FLAG"
	 *             length="1"
	 *         
	 */
	public String getHotFlag() {
		return hotFlag;
	}


	public void setHotFlag(String hotFlag) {
		this.hotFlag = hotFlag;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="IS_HIDDEN"
	 *             length="1"
	 *         
	 */
	public String getIsHidden() {
		return isHidden;
	}


	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="SORT_FLAG"
	 *              
	 *         
	 */
	public Long getSortFlag() {
		return sortFlag;
	}


	public void setSortFlag(Long sortFlag) {
		this.sortFlag = sortFlag;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="STORE_CONTROL_FIRST"
	 *             length="1"
	 *         
	 */
	public String getStoreControlFirst() {
		return storeControlFirst;
	}


	public void setStoreControlFirst(String storeControlFirst) {
		this.storeControlFirst = storeControlFirst;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="STORE_CONTROL_UPDATE"
	 *             length="1"
	 *         
	 */
	public String getStoreControlUpdate() {
		return storeControlUpdate;
	}


	public void setStoreControlUpdate(String storeControlUpdate) {
		this.storeControlUpdate = storeControlUpdate;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="STORE_CONTROL_REPURCHASE"
	 *             length="1"
	 *         
	 */
	public String getStoreControlRepurchase() {
		return storeControlRepurchase;
	}


	public void setStoreControlRepurchase(String storeControlRepurchase) {
		this.storeControlRepurchase = storeControlRepurchase;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="CONTROL_AUTOSALE"
	 *             length="1"
	 *         
	 */
	public String getControlAutosale() {
		return controlAutosale;
	}


	public void setControlAutosale(String controlAutosale) {
		this.controlAutosale = controlAutosale;
	}
	/**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true"  
	 * @hibernate.column name="PRODUCT_NO"
	 * 
	 */
	public JpmProduct getJpmProduct() {
		return jpmProduct;
	}


	public void setJpmProduct(JpmProduct jpmProduct) {
		this.jpmProduct = jpmProduct;
	}



	/**       
	 *      *            @hibernate.property
	 *             column="COMPANY_CODE"
	 *             length="2"
	 *         
	 */

	public String getCompanyCode() {
		return this.companyCode;
	}
	/**
	 * @spring.validator type="required"
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="PRODUCT_NAME"
	 *             length="100"
	 *         
	 */

	public String getProductName() {
		return this.productName;
	}
	/**
	 * @spring.validator type="required"
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="FP_PRICE"
	 *             length="18"
	 *         
	 */

	public BigDecimal getFpPrice() {
		return this.fpPrice;
	}
	/**
	 * @spring.validator type="required"
	 */
	public void setFpPrice(BigDecimal fpPrice) {
		this.fpPrice = fpPrice;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="FP_PV"
	 *             length="8"
	 *         
	 */

	public BigDecimal getFpPv() {
		return this.fpPv;
	}
	/**
	 * @spring.validator type="required"
	 */
	public void setFpPv(BigDecimal fpPv) {
		this.fpPv = fpPv;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="MP_PRICE"
	 *             length="18"
	 *         
	 */

	public BigDecimal getMpPrice() {
		return this.mpPrice;
	}

	public void setMpPrice(BigDecimal mpPrice) {
		this.mpPrice = mpPrice;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="MP_PV"
	 *             length="8"
	 *         
	 */

	public BigDecimal getMpPv() {
		return this.mpPv;
	}

	public void setMpPv(BigDecimal mpPv) {
		this.mpPv = mpPv;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="STORE_FP_PRICE"
	 *             length="18"
	 *         
	 */

	public BigDecimal getStoreFpPrice() {
		return this.storeFpPrice;
	}

	public void setStoreFpPrice(BigDecimal storeFpPrice) {
		this.storeFpPrice = storeFpPrice;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="STORE_FP_PV"
	 *             length="8"
	 *         
	 */

	public BigDecimal getStoreFpPv() {
		return this.storeFpPv;
	}

	public void setStoreFpPv(BigDecimal storeFpPv) {
		this.storeFpPv = storeFpPv;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="STORE_MP_PRICE"
	 *             length="18"
	 *         
	 */

	public BigDecimal getStoreMpPrice() {
		return this.storeMpPrice;
	}

	public void setStoreMpPrice(BigDecimal storeMpPrice) {
		this.storeMpPrice = storeMpPrice;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="STORE_MP_PV"
	 *             length="8"
	 *         
	 */

	public BigDecimal getStoreMpPv() {
		return this.storeMpPv;
	}

	public void setStoreMpPv(BigDecimal storeMpPv) {
		this.storeMpPv = storeMpPv;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="WHO_PRICE"
	 *             length="18"
	 *         
	 */

	public BigDecimal getWhoPrice() {
		return this.whoPrice;
	}

	public void setWhoPrice(BigDecimal whoPrice) {
		this.whoPrice = whoPrice;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="DISCOUNT_PRICE"
	 *             length="18"
	 *         
	 */

	public BigDecimal getDiscountPrice() {
		return this.discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
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
	/**
	 * @spring.validator type="required"
	 */
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
	/**
	 * @spring.validator type="required"
	 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="LENGTH"
	 *             length="10"
	 *         
	 */

	public BigDecimal getLength() {
		return this.length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="WIDTH"
	 *             length="10"
	 *         
	 */

	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="HEIGHT"
	 *             length="10"
	 *         
	 */

	public BigDecimal getHeight() {
		return this.height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
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
	 *             column="ALBUM_LINK"
	 *             length="500"
	 *         
	 */

	public String getAlbumLink() {
		return this.albumLink;
	}

	public void setAlbumLink(String albumLink) {
		this.albumLink = albumLink;
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

	public void setStatus(String status) {
		this.status = status;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CONFIRM"
	 *             length="1"
	 *         
	 */

	public String getConfirm() {
		return this.confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CONTROL_FIRST"
	 *             length="1"
	 *         
	 */

	public String getControlFirst() {
		return this.controlFirst;
	}

	public void setControlFirst(String controlFirst) {
		this.controlFirst = controlFirst;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CONTROL_UPDATE"
	 *             length="1"
	 *         
	 */

	public String getControlUpdate() {
		return this.controlUpdate;
	}

	public void setControlUpdate(String controlUpdate) {
		this.controlUpdate = controlUpdate;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CONTROL_REPURCHASE"
	 *             length="1"
	 *         
	 */

	public String getControlRepurchase() {
		return this.controlRepurchase;
	}

	public void setControlRepurchase(String controlRepurchase) {
		this.controlRepurchase = controlRepurchase;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="REMARK"
	 *             length="500"
	 *         
	 */

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="PRODUCT_DESC"
	 *             length="500"
	 *         
	 */

	public String getProductDesc() {
		return productDesc;
	}


	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}



	/**       
	 *      *            @hibernate.property
	 *             column="SUB_STORE_FIRST"
	 *             length="1"
	 *         
	 */
	public String getSubStoreFirst() {
		return subStoreFirst;
	}


	public void setSubStoreFirst(String subStoreFirst) {
		this.subStoreFirst = subStoreFirst;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="SUB_STORE_UPDATE"
	 *             length="1"
	 *         
	 */
	public String getSubStoreUpdate() {
		return subStoreUpdate;
	}


	public void setSubStoreUpdate(String subStoreUpdate) {
		this.subStoreUpdate = subStoreUpdate;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="SUB_STORE_REPURCHASE"
	 *             length="1"
	 *         
	 */

	public String getSubStoreRepurchase() {
		return subStoreRepurchase;
	}


	public void setSubStoreRepurchase(String subStoreRepurchase) {
		this.subStoreRepurchase = subStoreRepurchase;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="CHANGEABLED_FLAG"
	 *             length="1"
	 *         
	 */

	public String getChangeabledFlag() {
		return changeabledFlag;
	}


	public void setChangeabledFlag(String changeabledFlag) {
		this.changeabledFlag = changeabledFlag;
	}



	/**       
	 *      *            @hibernate.property
	 *             column="SUBSTORE_FP_PRICE"
	 *             
	 *         
	 */

	public BigDecimal getSubstoreFpPrice() {
		return substoreFpPrice;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setSubstoreFpPrice(BigDecimal substoreFpPrice) {
		this.substoreFpPrice = substoreFpPrice;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="SUBSTORE_FP_PV"
	 *             
	 *         
	 */
	public BigDecimal getSubstoreFpPv() {
		return substoreFpPv;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setSubstoreFpPv(BigDecimal substoreFpPv) {
		this.substoreFpPv = substoreFpPv;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="SUBSTORE_MP_PRICE"
	 *             
	 *         
	 */
	public BigDecimal getSubstoreMpPrice() {
		return substoreMpPrice;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setSubstoreMpPrice(BigDecimal substoreMpPrice) {
		this.substoreMpPrice = substoreMpPrice;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="SUBSTORE_MP_PV"
	 *             
	 *         
	 */
	public BigDecimal getSubstoreMpPv() {
		return substoreMpPv;
	}

	/**
	 * @spring.validator type="required"
	 */
	public void setSubstoreMpPv(BigDecimal substoreMpPv) {
		this.substoreMpPv = substoreMpPv;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="STORAGE_CORDON"
	 *             
	 *         
	 */
	public Long getStorageCordon() {
		return storageCordon;
	}
	/**
	 * @spring.validator type="required"
	 */

	public void setStorageCordon(Long storageCordon) {
		this.storageCordon = storageCordon;
	}



	/**       
	 *      *            @hibernate.property
	 *             column="START_ON_SALE"
	 *             
	 *         
	 */
	public Date getStartOnSale() {
		return startOnSale;
	}


	/**
	 * @param startOnSale the startOnSale to set
	 */
	public void setStartOnSale(Date startOnSale) {
		this.startOnSale = startOnSale;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="END_ON_SALE"
	 *             
	 *         
	 */
	public Date getEndOnSale() {
		return endOnSale;
	}


	/**
	 * @param endOnSale the endOnSale to set
	 */
	public void setEndOnSale(Date endOnSale) {
		this.endOnSale = endOnSale;
	}



	/**       
	 *      *            @hibernate.property
	 *             column="CONTROL_POINT_EXCHANGE"
	 *             
	 *         
	 */
	public String getControlPointExchange() {
		return controlPointExchange;
	}


	/**
	 * @param controlPointExchange the controlPointExchange to set
	 */
	public void setControlPointExchange(String controlPointExchange) {
		this.controlPointExchange = controlPointExchange;
	}


	/**       
	 *      *            @hibernate.property
	 *             column="SHIP_STRATEGY"
	 *             
	 *         
	 */
	public String getShipStrategy() {
		return shipStrategy;
	}


	/**
	 * @param shipStrategy the shipStrategy to set
	 */
	public void setShipStrategy(String shipStrategy) {
		this.shipStrategy = shipStrategy;
	}



	/**
	 * @return the specialPrice
	 */
	public BigDecimal getSpecialPrice() {
		return this.getStoreFpPrice();
	}


	/**
	 * @return the specialPv
	 */
	public BigDecimal getSpecialPv() {
		return this.getStoreFpPv();
	}


	/**
	 * toString
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
		buffer.append("productName").append("='").append(getProductName()).append("' ");			
		buffer.append("fpPrice").append("='").append(getFpPrice()).append("' ");			
		buffer.append("fpPv").append("='").append(getFpPv()).append("' ");			
		buffer.append("mpPrice").append("='").append(getMpPrice()).append("' ");			
		buffer.append("mpPv").append("='").append(getMpPv()).append("' ");			
		buffer.append("storeFpPrice").append("='").append(getStoreFpPrice()).append("' ");			
		buffer.append("storeFpPv").append("='").append(getStoreFpPv()).append("' ");			
		buffer.append("storeMpPrice").append("='").append(getStoreMpPrice()).append("' ");			
		buffer.append("storeMpPv").append("='").append(getStoreMpPv()).append("' ");			
		buffer.append("whoPrice").append("='").append(getWhoPrice()).append("' ");			
		buffer.append("discountPrice").append("='").append(getDiscountPrice()).append("' ");			
		buffer.append("weight").append("='").append(getWeight()).append("' ");			
		buffer.append("volume").append("='").append(getVolume()).append("' ");			
		buffer.append("length").append("='").append(getLength()).append("' ");			
		buffer.append("width").append("='").append(getWidth()).append("' ");			
		buffer.append("height").append("='").append(getHeight()).append("' ");			
		buffer.append("imageLink").append("='").append(getImageLink()).append("' ");			
		buffer.append("albumLink").append("='").append(getAlbumLink()).append("' ");			
		buffer.append("status").append("='").append(getStatus()).append("' ");			
		buffer.append("confirm").append("='").append(getConfirm()).append("' ");			
		buffer.append("controlFirst").append("='").append(getControlFirst()).append("' ");			
		buffer.append("controlUpdate").append("='").append(getControlUpdate()).append("' ");			
		buffer.append("controlRepurchase").append("='").append(getControlRepurchase()).append("' ");			
		buffer.append("remark").append("='").append(getRemark()).append("' ");			
		buffer.append("]");

		return buffer.toString();
	}


	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof JpmProductSale) ) return false;
		JpmProductSale castOther = ( JpmProductSale ) other; 

		return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
		&& ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
		&& ( (this.getProductName()==castOther.getProductName()) || ( this.getProductName()!=null && castOther.getProductName()!=null && this.getProductName().equals(castOther.getProductName()) ) )
		&& ( (this.getFpPrice()==castOther.getFpPrice()) || ( this.getFpPrice()!=null && castOther.getFpPrice()!=null && this.getFpPrice().equals(castOther.getFpPrice()) ) )
		&& ( (this.getFpPv()==castOther.getFpPv()) || ( this.getFpPv()!=null && castOther.getFpPv()!=null && this.getFpPv().equals(castOther.getFpPv()) ) )
		&& ( (this.getMpPrice()==castOther.getMpPrice()) || ( this.getMpPrice()!=null && castOther.getMpPrice()!=null && this.getMpPrice().equals(castOther.getMpPrice()) ) )
		&& ( (this.getMpPv()==castOther.getMpPv()) || ( this.getMpPv()!=null && castOther.getMpPv()!=null && this.getMpPv().equals(castOther.getMpPv()) ) )
		&& ( (this.getStoreFpPrice()==castOther.getStoreFpPrice()) || ( this.getStoreFpPrice()!=null && castOther.getStoreFpPrice()!=null && this.getStoreFpPrice().equals(castOther.getStoreFpPrice()) ) )
		&& ( (this.getStoreFpPv()==castOther.getStoreFpPv()) || ( this.getStoreFpPv()!=null && castOther.getStoreFpPv()!=null && this.getStoreFpPv().equals(castOther.getStoreFpPv()) ) )
		&& ( (this.getStoreMpPrice()==castOther.getStoreMpPrice()) || ( this.getStoreMpPrice()!=null && castOther.getStoreMpPrice()!=null && this.getStoreMpPrice().equals(castOther.getStoreMpPrice()) ) )
		&& ( (this.getStoreMpPv()==castOther.getStoreMpPv()) || ( this.getStoreMpPv()!=null && castOther.getStoreMpPv()!=null && this.getStoreMpPv().equals(castOther.getStoreMpPv()) ) )
		&& ( (this.getWhoPrice()==castOther.getWhoPrice()) || ( this.getWhoPrice()!=null && castOther.getWhoPrice()!=null && this.getWhoPrice().equals(castOther.getWhoPrice()) ) )
		&& ( (this.getDiscountPrice()==castOther.getDiscountPrice()) || ( this.getDiscountPrice()!=null && castOther.getDiscountPrice()!=null && this.getDiscountPrice().equals(castOther.getDiscountPrice()) ) )
		&& ( (this.getWeight()==castOther.getWeight()) || ( this.getWeight()!=null && castOther.getWeight()!=null && this.getWeight().equals(castOther.getWeight()) ) )
		&& ( (this.getVolume()==castOther.getVolume()) || ( this.getVolume()!=null && castOther.getVolume()!=null && this.getVolume().equals(castOther.getVolume()) ) )
		&& ( (this.getLength()==castOther.getLength()) || ( this.getLength()!=null && castOther.getLength()!=null && this.getLength().equals(castOther.getLength()) ) )
		&& ( (this.getWidth()==castOther.getWidth()) || ( this.getWidth()!=null && castOther.getWidth()!=null && this.getWidth().equals(castOther.getWidth()) ) )
		&& ( (this.getHeight()==castOther.getHeight()) || ( this.getHeight()!=null && castOther.getHeight()!=null && this.getHeight().equals(castOther.getHeight()) ) )
		&& ( (this.getImageLink()==castOther.getImageLink()) || ( this.getImageLink()!=null && castOther.getImageLink()!=null && this.getImageLink().equals(castOther.getImageLink()) ) )
		&& ( (this.getAlbumLink()==castOther.getAlbumLink()) || ( this.getAlbumLink()!=null && castOther.getAlbumLink()!=null && this.getAlbumLink().equals(castOther.getAlbumLink()) ) )
		&& ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
		&& ( (this.getConfirm()==castOther.getConfirm()) || ( this.getConfirm()!=null && castOther.getConfirm()!=null && this.getConfirm().equals(castOther.getConfirm()) ) )
		&& ( (this.getControlFirst()==castOther.getControlFirst()) || ( this.getControlFirst()!=null && castOther.getControlFirst()!=null && this.getControlFirst().equals(castOther.getControlFirst()) ) )
		&& ( (this.getControlUpdate()==castOther.getControlUpdate()) || ( this.getControlUpdate()!=null && castOther.getControlUpdate()!=null && this.getControlUpdate().equals(castOther.getControlUpdate()) ) )
		&& ( (this.getControlRepurchase()==castOther.getControlRepurchase()) || ( this.getControlRepurchase()!=null && castOther.getControlRepurchase()!=null && this.getControlRepurchase().equals(castOther.getControlRepurchase()) ) )
		&& ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
		result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
		result = 37 * result + ( getProductName() == null ? 0 : this.getProductName().hashCode() );
		result = 37 * result + ( getFpPrice() == null ? 0 : this.getFpPrice().hashCode() );
		result = 37 * result + ( getFpPv() == null ? 0 : this.getFpPv().hashCode() );
		result = 37 * result + ( getMpPrice() == null ? 0 : this.getMpPrice().hashCode() );
		result = 37 * result + ( getMpPv() == null ? 0 : this.getMpPv().hashCode() );
		result = 37 * result + ( getStoreFpPrice() == null ? 0 : this.getStoreFpPrice().hashCode() );
		result = 37 * result + ( getStoreFpPv() == null ? 0 : this.getStoreFpPv().hashCode() );
		result = 37 * result + ( getStoreMpPrice() == null ? 0 : this.getStoreMpPrice().hashCode() );
		result = 37 * result + ( getStoreMpPv() == null ? 0 : this.getStoreMpPv().hashCode() );
		result = 37 * result + ( getWhoPrice() == null ? 0 : this.getWhoPrice().hashCode() );
		result = 37 * result + ( getDiscountPrice() == null ? 0 : this.getDiscountPrice().hashCode() );
		result = 37 * result + ( getWeight() == null ? 0 : this.getWeight().hashCode() );
		result = 37 * result + ( getVolume() == null ? 0 : this.getVolume().hashCode() );
		result = 37 * result + ( getLength() == null ? 0 : this.getLength().hashCode() );
		result = 37 * result + ( getWidth() == null ? 0 : this.getWidth().hashCode() );
		result = 37 * result + ( getHeight() == null ? 0 : this.getHeight().hashCode() );
		result = 37 * result + ( getImageLink() == null ? 0 : this.getImageLink().hashCode() );
		result = 37 * result + ( getAlbumLink() == null ? 0 : this.getAlbumLink().hashCode() );
		result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
		result = 37 * result + ( getConfirm() == null ? 0 : this.getConfirm().hashCode() );
		result = 37 * result + ( getControlFirst() == null ? 0 : this.getControlFirst().hashCode() );
		result = 37 * result + ( getControlUpdate() == null ? 0 : this.getControlUpdate().hashCode() );
		result = 37 * result + ( getControlRepurchase() == null ? 0 : this.getControlRepurchase().hashCode() );
		result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
		return result;
	}


	/**
	 * @return the dreamFpPrice
	 */
	 public BigDecimal getDreamFpPrice() {
		return this.getSubstoreFpPrice();
	}


	/**
	 * @return the dreamFpPv
	 */
	 public BigDecimal getDreamFpPv() {
		 return this.getSubstoreFpPv();
	 }


	 /**
	  * @return the dreamMpPrice
	  */
	 public BigDecimal getDreamMpPrice() {
		 return this.getSubstoreMpPrice();
	 }


	 /**
	  * @return the dreamMpPv
	  */
	 public BigDecimal getDreamMpPv() {
		 return this.getSubstoreMpPv();
	 }

	 /**
	  * Add By WuCF 20130523 新增关系表
	  * @hibernate.set table="pm_team_product" cascade="save-update" lazy="false"
	  * @hibernate.collection-key column="uni_no"
	  * @hibernate.collection-many-to-many class="com.joymain.jecs.pm.model.JmiMemberTeam" column="code"
	  *                                           
	  */
	 public Set getJmiMemberTeams() {
		 return jmiMemberTeams;
	 }


	 public void setJmiMemberTeams(Set jmiMemberTeams) {
		 this.jmiMemberTeams = jmiMemberTeams;
	 }

	 /**       
	 *@hibernate.property
	 *column="BRIEF_INTRODUCTION"
	 *length="500"
	 */
	public String getBriefIntroduction() {
		return briefIntroduction;
	}


	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	/**       
	 *@hibernate.property
	 *column="HEALTH_KNOWLEDGE"
	 *length="500"
	 */
	public String getHealthKnowledge() {
		return healthKnowledge;
	}


	public void setHealthKnowledge(String healthKnowledge) {
		this.healthKnowledge = healthKnowledge;
	}

	/**       
	 *@hibernate.property
	 *column="PRODUCT_SPECIFICATION"
	 *length="50"
	 */
	public String getProductSpecification() {
		return productSpecification;
	}


	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}  
}
