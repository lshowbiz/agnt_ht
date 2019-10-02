package com.joymain.jecs.pm.model;

// Generated 2013-11-22 14:23:50 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *                  table="JPM_PRODUCT_WINE_TEMPLATE_SUB"
 * 
 */

public class JpmProductWineTemplateSub extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    // Fields    

    private String subNo;

    private JpmProductWineTemplate jpmProductWineTemplate;

    private String subName;

    private String productNo;

    private String productName;

    private BigDecimal price;

    private String specification;

    private BigDecimal num;

    private String unit;

    private BigDecimal lossRatio;

    private String isMainMaterial;

    private String isSendMaterial;

    private String isDelegateOut;

    private String isFeatureItem;

    private String isMustSelected;

    private String isDefaultSelected;

    private String isNumChange;

    private BigDecimal numMax;

    private BigDecimal numMin;

    private String isInvalid;

    private Set jpmWineTemplatePicture = new HashSet(0);
    // Constructors

    /** default constructor */
    public JpmProductWineTemplateSub() {
    }

    /** full constructor */
    public JpmProductWineTemplateSub(JpmProductWineTemplate jpmProductWineTemplate, String isInvalid, String productNo, String productName, String subName, BigDecimal price, String specification, BigDecimal num, String unit, BigDecimal lossRatio, String isMainMaterial, String isSendMaterial, String isDelegateOut, String isFeatureItem, String isMustSelected, String isDefaultSelected, String isNumChange, BigDecimal numMax, BigDecimal numMin) {
        this.jpmProductWineTemplate = jpmProductWineTemplate;
        this.subName = subName;
        this.productNo = productNo;
        this.productName = productName;
        this.price = price;
        this.specification = specification;
        this.num = num;
        this.unit = unit;
        this.lossRatio = lossRatio;
        this.isMainMaterial = isMainMaterial;
        this.isSendMaterial = isSendMaterial;
        this.isDelegateOut = isDelegateOut;
        this.isFeatureItem = isFeatureItem;
        this.isMustSelected = isMustSelected;
        this.isDefaultSelected = isDefaultSelected;
        this.isNumChange = isNumChange;
        this.numMax = numMax;
        this.numMin = numMin;
        this.isInvalid = isInvalid;
    }
    
    /**
     * *
     * 
     * @hibernate.set lazy="true" inverse="true" cascade="all"
     * @hibernate.collection-key column="SUB_NO"
     * @hibernate.collection-one-to-many class="com.joymain.jecs.pm.model.JpmWineTemplatePicture" 
     *                                        
     * 
     */
    public Set getJpmWineTemplatePicture()
    {
        return jpmWineTemplatePicture;
    }


    public void setJpmWineTemplatePicture(Set jpmWineTemplatePicture)
    {
        this.jpmWineTemplatePicture = jpmWineTemplatePicture;
    }

    // Property accessors
    /**
     * * @hibernate.id
     * generator-class="sequence"
     * type="java.lang.String"
     * column="SUB_NO"
     * * @hibernate.generator-param name="sequence" value="SEQ_PM"
     */

    public String getSubNo() {
        return this.subNo;
    }

    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }

    /**
     * @hibernate.many-to-one not-null="true" inverse="true"
     * @hibernate.column name="PRODUCT_TEMPLATE_NO"
     * 
     */
    public JpmProductWineTemplate getJpmProductWineTemplate() {
        return this.jpmProductWineTemplate;
    }

    public void setJpmProductWineTemplate(JpmProductWineTemplate jpmProductWineTemplate) {
        this.jpmProductWineTemplate = jpmProductWineTemplate;
    }

    /**
     * * @hibernate.property
     * column="SUB_NAME"
     * length="200"
     * 
     */

    public String getSubName() {
        return this.subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    /**
     * * @hibernate.property
     * column="PRICE"
     * length="22"
     * 
     */

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * * @hibernate.property
     * column="SPECIFICATION"
     * length="50"
     * 
     */

    public String getSpecification() {
        return this.specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /**
     * * @hibernate.property
     * column="NUM"
     * length="22"
     * 
     */

    public BigDecimal getNum() {
        return this.num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    /**
     * * @hibernate.property
     * column="UNIT"
     * length="10"
     * 
     */

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * * @hibernate.property
     * column="LOSS_RATIO"
     * length="22"
     * 
     */

    public BigDecimal getLossRatio() {
        return this.lossRatio;
    }

    public void setLossRatio(BigDecimal lossRatio) {
        this.lossRatio = lossRatio;
    }

    /**
     * * @hibernate.property
     * column="IS_MAIN_MATERIAL"
     * length="1"
     * 
     */

    public String getIsMainMaterial() {
        return this.isMainMaterial;
    }

    public void setIsMainMaterial(String isMainMaterial) {
        this.isMainMaterial = isMainMaterial;
    }

    /**
     * * @hibernate.property
     * column="IS_SEND_MATERIAL"
     * length="1"
     * 
     */

    public String getIsSendMaterial() {
        return this.isSendMaterial;
    }

    public void setIsSendMaterial(String isSendMaterial) {
        this.isSendMaterial = isSendMaterial;
    }

    /**
     * * @hibernate.property
     * column="IS_DELEGATE_OUT"
     * length="1"
     * 
     */

    public String getIsDelegateOut() {
        return this.isDelegateOut;
    }

    public void setIsDelegateOut(String isDelegateOut) {
        this.isDelegateOut = isDelegateOut;
    }

    /**
     * * @hibernate.property
     * column="IS_FEATURE_ITEM"
     * length="1"
     * 
     */

    public String getIsFeatureItem() {
        return this.isFeatureItem;
    }

    public void setIsFeatureItem(String isFeatureItem) {
        this.isFeatureItem = isFeatureItem;
    }

    /**
     * * @hibernate.property
     * column="IS_MUST_SELECTED"
     * length="1"
     * 
     */

    public String getIsMustSelected() {
        return this.isMustSelected;
    }

    public void setIsMustSelected(String isMustSelected) {
        this.isMustSelected = isMustSelected;
    }

    /**
     * * @hibernate.property
     * column="IS_DEFAULT_SELECTED"
     * length="1"
     * 
     */

    public String getIsDefaultSelected() {
        return this.isDefaultSelected;
    }

    public void setIsDefaultSelected(String isDefaultSelected) {
        this.isDefaultSelected = isDefaultSelected;
    }

    /**
     * * @hibernate.property
     * column="IS_NUM_CHANGE"
     * length="1"
     * 
     */

    public String getIsNumChange() {
        return this.isNumChange;
    }

    public void setIsNumChange(String isNumChange) {
        this.isNumChange = isNumChange;
    }

    /**
     * * @hibernate.property
     * column="NUM_MAX"
     * length="22"
     * 
     */

    public BigDecimal getNumMax() {
        return this.numMax;
    }

    public void setNumMax(BigDecimal numMax) {
        this.numMax = numMax;
    }

    /**
     * * @hibernate.property
     * column="NUM_MIN"
     * length="22"
     * 
     */

    public BigDecimal getNumMin() {
        return this.numMin;
    }

    public void setNumMin(BigDecimal numMin) {
        this.numMin = numMin;
    }

    /**
     * * @hibernate.property
     * column="PRODUCT_NO"
     * length="20"
     * 
     */
    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    /**
     * * @hibernate.property
     * column="PRODUCT_NAME"
     * length="200"
     * 
     */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * * @hibernate.property
     * column="IS_INVALID"
     * length="1"
     * 
     */
    public String getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid;
    }

    /**
     * toString
     * 
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("productTemplateNo").append("='").append(getJpmProductWineTemplate()).append("' ");
        buffer.append("subName").append("='").append(getSubName()).append("' ");
        buffer.append("productNo").append("='").append(getProductNo()).append("' ");
        buffer.append("productName").append("='").append(getProductName()).append("' ");
        buffer.append("price").append("='").append(getPrice()).append("' ");
        buffer.append("specification").append("='").append(getSpecification()).append("' ");
        buffer.append("num").append("='").append(getNum()).append("' ");
        buffer.append("unit").append("='").append(getUnit()).append("' ");
        buffer.append("lossRatio").append("='").append(getLossRatio()).append("' ");
        buffer.append("isMainMaterial").append("='").append(getIsMainMaterial()).append("' ");
        buffer.append("isSendMaterial").append("='").append(getIsSendMaterial()).append("' ");
        buffer.append("isDelegateOut").append("='").append(getIsDelegateOut()).append("' ");
        buffer.append("isFeatureItem").append("='").append(getIsFeatureItem()).append("' ");
        buffer.append("isMustSelected").append("='").append(getIsMustSelected()).append("' ");
        buffer.append("isDefaultSelected").append("='").append(getIsDefaultSelected()).append("' ");
        buffer.append("isNumChange").append("='").append(getIsNumChange()).append("' ");
        buffer.append("isInvalid").append("='").append(getIsInvalid()).append("' ");
        buffer.append("numMax").append("='").append(getNumMax()).append("' ");
        buffer.append("numMin").append("='").append(getNumMin()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof JpmProductWineTemplateSub))
            return false;
        JpmProductWineTemplateSub castOther = (JpmProductWineTemplateSub) other;

        return ((this.getSubNo() == castOther.getSubNo()) || (this.getSubNo() != null && castOther.getSubNo() != null && this.getSubNo().equals(castOther.getSubNo()))) && ((this.getIsInvalid() == castOther.getIsInvalid()) || (this.getIsInvalid() != null && castOther.getIsInvalid() != null && this.getIsInvalid().equals(castOther.getIsInvalid()))) && ((this.getProductNo() == castOther.getProductNo()) || (this.getProductNo() != null && castOther.getProductNo() != null && this.getProductNo().equals(castOther.getProductNo()))) && ((this.getProductName() == castOther.getProductName()) || (this.getProductName() != null && castOther.getProductName() != null && this.getProductName().equals(castOther.getProductName()))) && ((this.getJpmProductWineTemplate() == castOther.getJpmProductWineTemplate()) || (this.getJpmProductWineTemplate() != null && castOther.getJpmProductWineTemplate() != null && this.getJpmProductWineTemplate().equals(castOther.getJpmProductWineTemplate()))) && ((this.getSubName() == castOther.getSubName()) || (this.getSubName() != null && castOther.getSubName() != null && this.getSubName().equals(castOther.getSubName()))) && ((this.getPrice() == castOther.getPrice()) || (this.getPrice() != null && castOther.getPrice() != null && this.getPrice().equals(castOther.getPrice()))) && ((this.getSpecification() == castOther.getSpecification()) || (this.getSpecification() != null && castOther.getSpecification() != null && this.getSpecification().equals(castOther.getSpecification()))) && ((this.getNum() == castOther.getNum()) || (this.getNum() != null && castOther.getNum() != null && this.getNum().equals(castOther.getNum()))) && ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null && castOther.getUnit() != null && this.getUnit().equals(castOther.getUnit()))) && ((this.getLossRatio() == castOther.getLossRatio()) || (this.getLossRatio() != null && castOther.getLossRatio() != null && this.getLossRatio().equals(castOther.getLossRatio()))) && ((this.getIsMainMaterial() == castOther.getIsMainMaterial()) || (this.getIsMainMaterial() != null && castOther.getIsMainMaterial() != null && this.getIsMainMaterial().equals(castOther.getIsMainMaterial()))) && ((this.getIsSendMaterial() == castOther.getIsSendMaterial()) || (this.getIsSendMaterial() != null && castOther.getIsSendMaterial() != null && this.getIsSendMaterial().equals(castOther.getIsSendMaterial()))) && ((this.getIsDelegateOut() == castOther.getIsDelegateOut()) || (this.getIsDelegateOut() != null && castOther.getIsDelegateOut() != null && this.getIsDelegateOut().equals(castOther.getIsDelegateOut()))) && ((this.getIsFeatureItem() == castOther.getIsFeatureItem()) || (this.getIsFeatureItem() != null && castOther.getIsFeatureItem() != null && this.getIsFeatureItem().equals(castOther.getIsFeatureItem()))) && ((this.getIsMustSelected() == castOther.getIsMustSelected()) || (this.getIsMustSelected() != null && castOther.getIsMustSelected() != null && this.getIsMustSelected().equals(castOther.getIsMustSelected()))) && ((this.getIsDefaultSelected() == castOther.getIsDefaultSelected()) || (this.getIsDefaultSelected() != null && castOther.getIsDefaultSelected() != null && this.getIsDefaultSelected().equals(castOther.getIsDefaultSelected()))) && ((this.getIsNumChange() == castOther.getIsNumChange()) || (this.getIsNumChange() != null && castOther.getIsNumChange() != null && this.getIsNumChange().equals(castOther.getIsNumChange()))) && ((this.getNumMax() == castOther.getNumMax()) || (this.getNumMax() != null && castOther.getNumMax() != null && this.getNumMax().equals(castOther.getNumMax()))) && ((this.getNumMin() == castOther.getNumMin()) || (this.getNumMin() != null && castOther.getNumMin() != null && this.getNumMin().equals(castOther.getNumMin())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getSubNo() == null ? 0 : this.getSubNo().hashCode());
        result = 37 * result + (getJpmProductWineTemplate() == null ? 0 : this.getJpmProductWineTemplate().hashCode());
        result = 37 * result + (getSubName() == null ? 0 : this.getSubName().hashCode());
        result = 37 * result + (getProductNo() == null ? 0 : this.getProductNo().hashCode());
        result = 37 * result + (getProductName() == null ? 0 : this.getProductName().hashCode());
        result = 37 * result + (getPrice() == null ? 0 : this.getPrice().hashCode());
        result = 37 * result + (getSpecification() == null ? 0 : this.getSpecification().hashCode());
        result = 37 * result + (getNum() == null ? 0 : this.getNum().hashCode());
        result = 37 * result + (getUnit() == null ? 0 : this.getUnit().hashCode());
        result = 37 * result + (getLossRatio() == null ? 0 : this.getLossRatio().hashCode());
        result = 37 * result + (getIsMainMaterial() == null ? 0 : this.getIsMainMaterial().hashCode());
        result = 37 * result + (getIsSendMaterial() == null ? 0 : this.getIsSendMaterial().hashCode());
        result = 37 * result + (getIsDelegateOut() == null ? 0 : this.getIsDelegateOut().hashCode());
        result = 37 * result + (getIsFeatureItem() == null ? 0 : this.getIsFeatureItem().hashCode());
        result = 37 * result + (getIsMustSelected() == null ? 0 : this.getIsMustSelected().hashCode());
        result = 37 * result + (getIsDefaultSelected() == null ? 0 : this.getIsDefaultSelected().hashCode());
        result = 37 * result + (getIsNumChange() == null ? 0 : this.getIsNumChange().hashCode());
        result = 37 * result + (getNumMax() == null ? 0 : this.getNumMax().hashCode());
        result = 37 * result + (getNumMin() == null ? 0 : this.getNumMin().hashCode());
        result = 37 * result + (getIsInvalid() == null ? 0 : this.getIsInvalid().hashCode());
        return result;
    }

}
