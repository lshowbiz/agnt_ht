package com.joymain.jecs.pm.model;
// Generated 2013-6-3 18:00:54 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.joymain.jecs.pm.model.JpmCouponRelationship;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_PRODUCT_SALE_NEW"
 *     
 */

@SuppressWarnings("unchecked")
public class JpmProductSaleNew extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	// Fields    

	private Long uniNo;
	private String companyCode;
	
	private String productName;
	private BigDecimal whoPrice = new BigDecimal(0);
	private BigDecimal discountPrice = new BigDecimal(0);
	private BigDecimal weight = new BigDecimal(0);
	private BigDecimal volume = new BigDecimal(0);
	private BigDecimal length = new BigDecimal(0);
	private BigDecimal width = new BigDecimal(0);
	private BigDecimal height = new BigDecimal(0);
	private String status;
	private String confirm;
	private String remark;
	private String productDesc;
	private String changeabledFlag;
	private Long storageCordon;
	private Date startOnSale;
	private Date endOnSale;
	private String shipStrategy;
	private String hotFlag;
	private Long sortFlag;
	private String isHidden;
	private Set<JpmProductSaleImage> jpmProductSaleImages = new HashSet(); 
	private Set<JpmProductSaleRelated> jpmProductSaleRelateds = new HashSet(); 
	private Set<JpmProductSaleTeamType> jpmProductSaleTeamTypes = new HashSet(); 
	
	private String productNo;
	private JpmProduct jpmProduct = new JpmProduct();
	 
	private String createUserCode;
	private Date createTime;
	private String updateUserCode;
	private Date updateTime;
	private String briefIntroduction;//简介
	private String healthKnowledge;//健康知识
	private String productSpecification;//规格
	
	private String isRecommend;//是否推荐
	
	//抵用券数据
	private Set<JpmCouponRelationship> jpmCouponRelationships = new HashSet(); 
	
	/**       
	 *      *            @hibernate.property
	 *             column="IS_RECOMMEND"
	 *             length="1"
	 *         
	 */
	public String getIsRecommend() {
		return isRecommend;
	}


	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}


	//modify gw 20150107 物流策略
	private String logisticsStrategy;
	
	// Constructors

	/** default constructor */
	public JpmProductSaleNew() {
	}


	/** full constructor */
	public JpmProductSaleNew(JpmProduct jpmProduct, String companyCode, String productName, BigDecimal whoPrice, BigDecimal discountPrice, BigDecimal weight, BigDecimal volume, BigDecimal length, BigDecimal width, BigDecimal height, String status, String confirm, String remark, String productDesc, String changeabledFlag, Long storageCordon, Date startOnSale, Date endOnSale, String shipStrategy, String hotFlag, Long sortFlag, String isHidden,String logisticsStrategy) {
		this.jpmProduct = jpmProduct;
		this.companyCode = companyCode;
		this.productName = productName;
		this.whoPrice = whoPrice;
		this.discountPrice = discountPrice;
		this.weight = weight;
		this.volume = volume;
		this.length = length;
		this.width = width;
		this.height = height;
		this.status = status;
		this.confirm = confirm;
		this.remark = remark;
		this.productDesc = productDesc;
		this.changeabledFlag = changeabledFlag;
		this.storageCordon = storageCordon;
		this.startOnSale = startOnSale;
		this.endOnSale = endOnSale;
		this.shipStrategy = shipStrategy;
		this.hotFlag = hotFlag;
		this.sortFlag = sortFlag;
		this.isHidden = isHidden;
		this.logisticsStrategy = logisticsStrategy;

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
	 *             column="COMPANY_CODE"
	 *             length="2"
	 *         
	 */

	public String getCompanyCode() {
		return this.companyCode;
	}
	
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="PRODUCT_NAME"
	 *             length="200"
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
	 *             column="WHO_PRICE"
	 *             length="18"
	 *         
	 */

	public BigDecimal getWhoPrice() {
		return this.whoPrice;
	}
	
	/**
     * @spring.validator type="required"
     */
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
	
	/**
     * @spring.validator type="required"
     */
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
	 *             length="15"
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

	/**
     * @spring.validator type="required"
     */
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

	/**
     * @spring.validator type="required"
     */
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

	/**
     * @spring.validator type="required"
     */
	public void setHeight(BigDecimal height) {
		this.height = height;
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
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="CHANGEABLED_FLAG"
	 *             length="1"
	 *         
	 */

	public String getChangeabledFlag() {
		return this.changeabledFlag;
	}

	public void setChangeabledFlag(String changeabledFlag) {
		this.changeabledFlag = changeabledFlag;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="STORAGE_CORDON"
	 *             length="10"
	 *         
	 */

	public Long getStorageCordon() {
		return this.storageCordon;
	}

	public void setStorageCordon(Long storageCordon) {
		this.storageCordon = storageCordon;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="START_ON_SALE"
	 *             length="7"
	 *         
	 */

	public Date getStartOnSale() {
		return this.startOnSale;
	}
	 
	public void setStartOnSale(Date startOnSale) {
		this.startOnSale = startOnSale;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="END_ON_SALE"
	 *             length="7"
	 *         
	 */

	public Date getEndOnSale() {
		return this.endOnSale;
	}
	 
	public void setEndOnSale(Date endOnSale) {
		this.endOnSale = endOnSale;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="SHIP_STRATEGY"
	 *             length="20"
	 *         
	 */

	public String getShipStrategy() {
		return this.shipStrategy;
	}
	
	/**
     * @spring.validator type="required"
     */
	public void setShipStrategy(String shipStrategy) {
		this.shipStrategy = shipStrategy;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="HOT_FLAG"
	 *             length="5"
	 *         
	 */

	public String getHotFlag() {
		return this.hotFlag;
	}

	public void setHotFlag(String hotFlag) {
		this.hotFlag = hotFlag;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="SORT_FLAG"
	 *             length="10"
	 *         
	 */

	public Long getSortFlag() {
		return this.sortFlag;
	}

	public void setSortFlag(Long sortFlag) {
		this.sortFlag = sortFlag;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="IS_HIDDEN"
	 *             length="1"
	 *         
	 */

	public String getIsHidden() {
		return this.isHidden;
	}
	
	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}

	/**       
	 *      *            @hibernate.property
	 *             column="LOGISTICS_STRATEGY"
	 *             length="20"
	 *         
	 */
	public String getLogisticsStrategy() {
		return logisticsStrategy;
	}


	/**
     * @spring.validator type="required"
     */
	public void setLogisticsStrategy(String logisticsStrategy) {
		this.logisticsStrategy = logisticsStrategy;
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
		buffer.append("whoPrice").append("='").append(getWhoPrice()).append("' ");			
		buffer.append("discountPrice").append("='").append(getDiscountPrice()).append("' ");			
		buffer.append("weight").append("='").append(getWeight()).append("' ");			
		buffer.append("volume").append("='").append(getVolume()).append("' ");			
		buffer.append("length").append("='").append(getLength()).append("' ");			
		buffer.append("width").append("='").append(getWidth()).append("' ");			
		buffer.append("height").append("='").append(getHeight()).append("' ");			
		buffer.append("status").append("='").append(getStatus()).append("' ");			
		buffer.append("confirm").append("='").append(getConfirm()).append("' ");			
		buffer.append("remark").append("='").append(getRemark()).append("' ");			
		buffer.append("productDesc").append("='").append(getProductDesc()).append("' ");			
		buffer.append("changeabledFlag").append("='").append(getChangeabledFlag()).append("' ");			
		buffer.append("storageCordon").append("='").append(getStorageCordon()).append("' ");			
		buffer.append("startOnSale").append("='").append(getStartOnSale()).append("' ");			
		buffer.append("endOnSale").append("='").append(getEndOnSale()).append("' ");			
		buffer.append("shipStrategy").append("='").append(getShipStrategy()).append("' ");			
		buffer.append("hotFlag").append("='").append(getHotFlag()).append("' ");			
		buffer.append("sortFlag").append("='").append(getSortFlag()).append("' ");			
		buffer.append("isHidden").append("='").append(getIsHidden()).append("' ");	
		buffer.append("logisticsStrategy").append("='").append(getLogisticsStrategy()).append("' ");	

		buffer.append("]");

		return buffer.toString();
	}


	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof JpmProductSaleNew) ) return false;
		JpmProductSaleNew castOther = ( JpmProductSaleNew ) other; 

		return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
		&& ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
		&& ( (this.getProductName()==castOther.getProductName()) || ( this.getProductName()!=null && castOther.getProductName()!=null && this.getProductName().equals(castOther.getProductName()) ) )
		&& ( (this.getWhoPrice()==castOther.getWhoPrice()) || ( this.getWhoPrice()!=null && castOther.getWhoPrice()!=null && this.getWhoPrice().equals(castOther.getWhoPrice()) ) )
		&& ( (this.getDiscountPrice()==castOther.getDiscountPrice()) || ( this.getDiscountPrice()!=null && castOther.getDiscountPrice()!=null && this.getDiscountPrice().equals(castOther.getDiscountPrice()) ) )
		&& ( (this.getWeight()==castOther.getWeight()) || ( this.getWeight()!=null && castOther.getWeight()!=null && this.getWeight().equals(castOther.getWeight()) ) )
		&& ( (this.getVolume()==castOther.getVolume()) || ( this.getVolume()!=null && castOther.getVolume()!=null && this.getVolume().equals(castOther.getVolume()) ) )
		&& ( (this.getLength()==castOther.getLength()) || ( this.getLength()!=null && castOther.getLength()!=null && this.getLength().equals(castOther.getLength()) ) )
		&& ( (this.getWidth()==castOther.getWidth()) || ( this.getWidth()!=null && castOther.getWidth()!=null && this.getWidth().equals(castOther.getWidth()) ) )
		&& ( (this.getHeight()==castOther.getHeight()) || ( this.getHeight()!=null && castOther.getHeight()!=null && this.getHeight().equals(castOther.getHeight()) ) )
		&& ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
		&& ( (this.getConfirm()==castOther.getConfirm()) || ( this.getConfirm()!=null && castOther.getConfirm()!=null && this.getConfirm().equals(castOther.getConfirm()) ) )
		&& ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
		&& ( (this.getProductDesc()==castOther.getProductDesc()) || ( this.getProductDesc()!=null && castOther.getProductDesc()!=null && this.getProductDesc().equals(castOther.getProductDesc()) ) )
		&& ( (this.getChangeabledFlag()==castOther.getChangeabledFlag()) || ( this.getChangeabledFlag()!=null && castOther.getChangeabledFlag()!=null && this.getChangeabledFlag().equals(castOther.getChangeabledFlag()) ) )
		&& ( (this.getStorageCordon()==castOther.getStorageCordon()) || ( this.getStorageCordon()!=null && castOther.getStorageCordon()!=null && this.getStorageCordon().equals(castOther.getStorageCordon()) ) )
		&& ( (this.getStartOnSale()==castOther.getStartOnSale()) || ( this.getStartOnSale()!=null && castOther.getStartOnSale()!=null && this.getStartOnSale().equals(castOther.getStartOnSale()) ) )
		&& ( (this.getEndOnSale()==castOther.getEndOnSale()) || ( this.getEndOnSale()!=null && castOther.getEndOnSale()!=null && this.getEndOnSale().equals(castOther.getEndOnSale()) ) )
		&& ( (this.getShipStrategy()==castOther.getShipStrategy()) || ( this.getShipStrategy()!=null && castOther.getShipStrategy()!=null && this.getShipStrategy().equals(castOther.getShipStrategy()) ) )
		&& ( (this.getHotFlag()==castOther.getHotFlag()) || ( this.getHotFlag()!=null && castOther.getHotFlag()!=null && this.getHotFlag().equals(castOther.getHotFlag()) ) )
		&& ( (this.getSortFlag()==castOther.getSortFlag()) || ( this.getSortFlag()!=null && castOther.getSortFlag()!=null && this.getSortFlag().equals(castOther.getSortFlag()) ) )
		&& ( (this.getIsHidden()==castOther.getIsHidden()) || ( this.getIsHidden()!=null && castOther.getIsHidden()!=null && this.getIsHidden().equals(castOther.getIsHidden()) ) )
		&& ( (this.getLogisticsStrategy()==castOther.getLogisticsStrategy()) || ( this.getLogisticsStrategy()!=null && castOther.getLogisticsStrategy()!=null && this.getLogisticsStrategy().equals(castOther.getLogisticsStrategy()) ) );

	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
		result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
		result = 37 * result + ( getProductName() == null ? 0 : this.getProductName().hashCode() );
		result = 37 * result + ( getWhoPrice() == null ? 0 : this.getWhoPrice().hashCode() );
		result = 37 * result + ( getDiscountPrice() == null ? 0 : this.getDiscountPrice().hashCode() );
		result = 37 * result + ( getWeight() == null ? 0 : this.getWeight().hashCode() );
		result = 37 * result + ( getVolume() == null ? 0 : this.getVolume().hashCode() );
		result = 37 * result + ( getLength() == null ? 0 : this.getLength().hashCode() );
		result = 37 * result + ( getWidth() == null ? 0 : this.getWidth().hashCode() );
		result = 37 * result + ( getHeight() == null ? 0 : this.getHeight().hashCode() );
		result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
		result = 37 * result + ( getConfirm() == null ? 0 : this.getConfirm().hashCode() );
		result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
		result = 37 * result + ( getProductDesc() == null ? 0 : this.getProductDesc().hashCode() );
		result = 37 * result + ( getChangeabledFlag() == null ? 0 : this.getChangeabledFlag().hashCode() );
		result = 37 * result + ( getStorageCordon() == null ? 0 : this.getStorageCordon().hashCode() );
		result = 37 * result + ( getStartOnSale() == null ? 0 : this.getStartOnSale().hashCode() );
		result = 37 * result + ( getEndOnSale() == null ? 0 : this.getEndOnSale().hashCode() );
		result = 37 * result + ( getShipStrategy() == null ? 0 : this.getShipStrategy().hashCode() );
		result = 37 * result + ( getHotFlag() == null ? 0 : this.getHotFlag().hashCode() );
		result = 37 * result + ( getSortFlag() == null ? 0 : this.getSortFlag().hashCode() );
		result = 37 * result + ( getIsHidden() == null ? 0 : this.getIsHidden().hashCode() );
		result = 37 * result + ( getLogisticsStrategy() == null ? 0 : this.getLogisticsStrategy().hashCode() );

		return result;
	}

	/**
	 * * @hibernate.property column="PRODUCT_NO" length="20" update="false" insert="false"
	 */
	public String getProductNo() {
		return productNo;
	}


	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	/**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" lazy="false"
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
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="uni_No"
	 * @hibernate.collection-key column="uni_no"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pm.model.JpmProductSaleImage"
	 * 
	 */
	public Set<JpmProductSaleImage> getJpmProductSaleImages() {
		return jpmProductSaleImages;
		
	}


	public void setJpmProductSaleImages(
			Set<JpmProductSaleImage> jpmProductSaleImages) {
		this.jpmProductSaleImages = jpmProductSaleImages;
	}


	 /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="uni_No"
	 * @hibernate.collection-key column="uni_no"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pm.model.JpmProductSaleRelated"
	 * 
	 */
	public Set<JpmProductSaleRelated> getJpmProductSaleRelateds() {
		return jpmProductSaleRelateds;
	}


	public void setJpmProductSaleRelateds(
			Set<JpmProductSaleRelated> jpmProductSaleRelateds) {
		this.jpmProductSaleRelateds = jpmProductSaleRelateds;
	}


	 /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="uni_No"
	 * @hibernate.collection-key column="uni_no"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pm.model.JpmProductSaleTeamType"
	 * 
	 */
	public Set<JpmProductSaleTeamType> getJpmProductSaleTeamTypes() {
		return jpmProductSaleTeamTypes;
	}


	public void setJpmProductSaleTeamTypes(
			Set<JpmProductSaleTeamType> jpmProductSaleTeamTypes) {
		this.jpmProductSaleTeamTypes = jpmProductSaleTeamTypes;
	}


	
	/**       
	 *      @hibernate.property
	 *       column="CREATE_USER_CODE"
	 *       length="20"
	 *         
	 */
	public String getCreateUserCode() {
		return createUserCode;
	}


	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	/**       
	 *     @hibernate.property
	 *      column="CREATE_TIME"
	 *         
	 */
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**       
	 *      @hibernate.property
	 *       column="UPDATE_USER_CODE"
	 *       length="20"
	 *         
	 */
	public String getUpdateUserCode() {
		return updateUserCode;
	}


	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

 
	/**       
	 *      *            @hibernate.property
	 *             column="UPDATE_TIME"
	 *             length="7"
	 *         
	 */
	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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


	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                order-by="uni_No"
	 * @hibernate.collection-key column="uni_no"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pm.model.JpmCouponRelationship"
	 * 
	 */
	public Set<JpmCouponRelationship> getJpmCouponRelationships() {
		return jpmCouponRelationships;
	}


	public void setJpmCouponRelationships(
			Set<JpmCouponRelationship> jpmCouponRelationships) {
		this.jpmCouponRelationships = jpmCouponRelationships;
	}
	
	public List<JpmCouponRelationship> getJpmCouponRelationshipList() {
		List list = new ArrayList<JpmCouponRelationship>(); 
		
		Set<JpmCouponRelationship> returnSet = this.getJpmCouponRelationships();
		
		list.addAll(returnSet);
		return list;
	}
	
}
