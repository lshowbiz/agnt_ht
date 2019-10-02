package com.joymain.jecs.pm.model;

// Generated 2013-11-21 18:02:43 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *                  table="JPM_PRODUCT_WINE_TEMPLATE"
 * 
 */

public class JpmProductWineTemplate extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    // Fields    

    private Long productTemplateNo;

    private String productTemplateName;

    private String productNo;

    private String productName;

    private String isDefault;

    private String isInvalid;

    private String memo;

    private Date createTime;

    private Integer parentQty;

    private Set<JpmProductWineTemplateSub> jpmProductWineTemplateSub = new HashSet<JpmProductWineTemplateSub>();

    // Constructors

    /** default constructor */
    public JpmProductWineTemplate() {
    }

    /**
     * *
     * 
     * @hibernate.set lazy="true" inverse="true" cascade="all"
     *                order-by="PRODUCT_TEMPLATE_NO"
     * @hibernate.collection-key column="PRODUCT_TEMPLATE_NO"
     * @hibernate.collection-one-to-many
     *                                   class=
     *                                   "com.joymain.jecs.pm.model.JpmProductWineTemplateSub"
     * 
     */
    public Set<JpmProductWineTemplateSub> getJpmProductWineTemplateSub() {
        return jpmProductWineTemplateSub;
    }

    public void setJpmProductWineTemplateSub(Set<JpmProductWineTemplateSub> jpmProductWineTemplateSub) {
        this.jpmProductWineTemplateSub = jpmProductWineTemplateSub;
    }

    /** full constructor */
    public JpmProductWineTemplate(String productNo, String productName, String isDefault, String isInvalid, String memo, Date createTime) {
        this.productNo = productNo;
        this.productName = productName;
        this.isDefault = isDefault;
        this.isInvalid = isInvalid;
        this.memo = memo;
        this.createTime = createTime;
    }

    // Property accessors
    /**
     * * @hibernate.id
     * generator-class="native"
     * type="java.lang.Long"
     * column="PRODUCT_TEMPLATE_NO"
     * 
     */

    public Long getProductTemplateNo() {
        return this.productTemplateNo;
    }

    public void setProductTemplateNo(Long productTemplateNo) {
        this.productTemplateNo = productTemplateNo;
    }

    /**
     * * @hibernate.property
     * column="PRODUCT_TEMPLATE_Name"
     * length="100"
     * 
     */
    public String getProductTemplateName() {
        return productTemplateName;
    }

    public void setProductTemplateName(String productTemplateName) {
        this.productTemplateName = productTemplateName;
    }

    /**
     * * @hibernate.property
     * column="PRODUCT_NO"
     * length="20"
     * 
     */

    public String getProductNo() {
        return this.productNo;
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
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * * @hibernate.property
     * column="IS_DEFAULT"
     * length="1"
     * 
     */

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? "0" : isDefault;
    }

    /**
     * * @hibernate.property
     * column="IS_INVALID"
     * length="1"
     * 
     */

    public String getIsInvalid() {
        return this.isInvalid;
    }

    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid == null ? "0" : isInvalid;
    }

    /**
     * * @hibernate.property
     * column="MEMO"
     * length="500"
     * 
     */

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * * @hibernate.property
     * column="CREATE_TIME"
     * length="7"
     * 
     */

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * * @hibernate.property
     * column="PARENT_QTY"
     * type="java.lang.Integer"
     * 
     * @return
     */
    public Integer getParentQty() {
        return parentQty;
    }

    public void setParentQty(Integer parentQty) {
        this.parentQty = parentQty;
    }

    /**
     * toString
     * 
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("productNo").append("='").append(getProductNo()).append("' ");
        buffer.append("productName").append("='").append(getProductName()).append("' ");
        buffer.append("isDefault").append("='").append(getIsDefault()).append("' ");
        buffer.append("isInvalid").append("='").append(getIsInvalid()).append("' ");
        buffer.append("memo").append("='").append(getMemo()).append("' ");
        buffer.append("createTime").append("='").append(getCreateTime()).append("' ");
        buffer.append("parentQty").append("='").append(getParentQty()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof JpmProductWineTemplate))
            return false;
        JpmProductWineTemplate castOther = (JpmProductWineTemplate) other;

        return ((this.getProductTemplateNo() == castOther.getProductTemplateNo()) || (this.getProductTemplateNo() != null && castOther.getProductTemplateNo() != null && this.getProductTemplateNo().equals(castOther.getProductTemplateNo()))) && ((this.getProductNo() == castOther.getProductNo()) || (this.getProductNo() != null && castOther.getProductNo() != null && this.getProductNo().equals(castOther.getProductNo()))) && ((this.getProductName() == castOther.getProductName()) || (this.getProductName() != null && castOther.getProductName() != null && this.getProductName().equals(castOther.getProductName()))) && ((this.getIsDefault() == castOther.getIsDefault()) || (this.getIsDefault() != null && castOther.getIsDefault() != null && this.getIsDefault().equals(castOther.getIsDefault()))) && ((this.getIsInvalid() == castOther.getIsInvalid()) || (this.getIsInvalid() != null && castOther.getIsInvalid() != null && this.getIsInvalid().equals(castOther.getIsInvalid()))) && ((this.getMemo() == castOther.getMemo()) || (this.getMemo() != null && castOther.getMemo() != null && this.getMemo().equals(castOther.getMemo()))) && ((this.getCreateTime() == castOther.getCreateTime()) || (this.getCreateTime() != null && castOther.getCreateTime() != null && this.getCreateTime().equals(castOther.getCreateTime()))) && ((this.getParentQty() == castOther.getParentQty()) || (this.getParentQty() != null && castOther.getParentQty() != null && this.getParentQty().equals(castOther.getParentQty())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getProductTemplateNo() == null ? 0 : this.getProductTemplateNo().hashCode());
        result = 37 * result + (getProductNo() == null ? 0 : this.getProductNo().hashCode());
        result = 37 * result + (getProductName() == null ? 0 : this.getProductName().hashCode());
        result = 37 * result + (getIsDefault() == null ? 0 : this.getIsDefault().hashCode());
        result = 37 * result + (getIsInvalid() == null ? 0 : this.getIsInvalid().hashCode());
        result = 37 * result + (getMemo() == null ? 0 : this.getMemo().hashCode());
        result = 37 * result + (getCreateTime() == null ? 0 : this.getCreateTime().hashCode());
        result = 37 * result + (getParentQty() == null ? 0 : this.getParentQty().hashCode());
        return result;
    }

}
