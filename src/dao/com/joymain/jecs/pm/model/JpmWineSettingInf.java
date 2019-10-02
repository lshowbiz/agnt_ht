package com.joymain.jecs.pm.model;

import java.util.HashSet;
import java.util.Set;

// Generated 2013-12-3 14:41:25 by Hibernate Tools 3.1.0.beta4

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *                  table="JPM_WINE_SETTING_INF"
 * 
 */

public class JpmWineSettingInf extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    // Fields    

    private Long settingId;//配置单号（主键）

    private Long productId;//商品编码

    private String productName;//商品名称

    private Integer productQty;//商品数量

    private String unitNo;//商品单位

    private Integer status;//是否发送成功 0：发送成功，1：未发送或发送失败

    private Integer resultcode;//响应体的响应码

    private String resultdescription;//响应体的响应描述

    private Set<JpmWineSettingListInf> jpmWineSettingListInfSet = new HashSet<JpmWineSettingListInf>();//配置单的明细

    // Constructors

    /** default constructor */
    public JpmWineSettingInf() {
    }

    /** full constructor */
    public JpmWineSettingInf(Long productId, String productName, Integer productQty, String unitNo, Integer status, Integer resultcode, String resultdescription) {
        this.productId = productId;
        this.productName = productName;
        this.productQty = productQty;
        this.unitNo = unitNo;
        this.status = status;
        this.resultcode = resultcode;
        this.resultdescription = resultdescription;
    }

    // Property accessors
    /**
     * * @hibernate.id
     * generator-class="native"
     * type="java.lang.Long"
     * column="SETTING_ID"
     * 
     */

    public Long getSettingId() {
        return this.settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    /**
     * * @hibernate.property
     * column="PRODUCT_ID"
     * length="10"
     * 
     */

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
     * column="PRODUCT_QTY"
     * length="5"
     * 
     */

    public Integer getProductQty() {
        return this.productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    /**
     * * @hibernate.property
     * column="UNIT_NO"
     * length="10"
     * 
     */

    public String getUnitNo() {
        return this.unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    /**
     * * @hibernate.property
     * column="STATUS"
     * length="5"
     * 
     */

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * * @hibernate.property
     * column="RESULTCODE"
     * length="5"
     * 
     */

    public Integer getResultcode() {
        return this.resultcode;
    }

    public void setResultcode(Integer resultcode) {
        this.resultcode = resultcode;
    }

    /**
     * * @hibernate.property
     * column="RESULTDESCRIPTION"
     * length="500"
     * 
     */

    public String getResultdescription() {
        return this.resultdescription;
    }

    public void setResultdescription(String resultdescription) {
        this.resultdescription = resultdescription;
    }

    /**
     * @hibernate.set lazy="true" cascade="all"
     * @hibernate.collection-key column="SETTING_ID"
     * @hibernate.one-to-many
     *                        class=
     *                        "com.joymain.jecs.pm.model.JpmWineSettingListInf"
     */
    public Set<JpmWineSettingListInf> getJpmWineSettingListInfSet() {
        return jpmWineSettingListInfSet;
    }

    public void setJpmWineSettingListInfSet(Set<JpmWineSettingListInf> jpmWineSettingListInfSet) {
        this.jpmWineSettingListInfSet = jpmWineSettingListInfSet;
    }

    /**
     * toString
     * 
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("productId").append("='").append(getProductId()).append("' ");
        buffer.append("productName").append("='").append(getProductName()).append("' ");
        buffer.append("productQty").append("='").append(getProductQty()).append("' ");
        buffer.append("unitNo").append("='").append(getUnitNo()).append("' ");
        buffer.append("status").append("='").append(getStatus()).append("' ");
        buffer.append("resultcode").append("='").append(getResultcode()).append("' ");
        buffer.append("resultdescription").append("='").append(getResultdescription()).append("' ");
        buffer.append("]");

        return buffer.toString();
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof JpmWineSettingInf))
            return false;
        JpmWineSettingInf castOther = (JpmWineSettingInf) other;

        return ((this.getSettingId() == castOther.getSettingId()) || (this.getSettingId() != null && castOther.getSettingId() != null && this.getSettingId().equals(castOther.getSettingId()))) && ((this.getProductId() == castOther.getProductId()) || (this.getProductId() != null && castOther.getProductId() != null && this.getProductId().equals(castOther.getProductId()))) && ((this.getProductName() == castOther.getProductName()) || (this.getProductName() != null && castOther.getProductName() != null && this.getProductName().equals(castOther.getProductName()))) && ((this.getProductQty() == castOther.getProductQty()) || (this.getProductQty() != null && castOther.getProductQty() != null && this.getProductQty().equals(castOther.getProductQty()))) && ((this.getUnitNo() == castOther.getUnitNo()) || (this.getUnitNo() != null && castOther.getUnitNo() != null && this.getUnitNo().equals(castOther.getUnitNo()))) && ((this.getStatus() == castOther.getStatus()) || (this.getStatus() != null && castOther.getStatus() != null && this.getStatus().equals(castOther.getStatus()))) && ((this.getResultcode() == castOther.getResultcode()) || (this.getResultcode() != null && castOther.getResultcode() != null && this.getResultcode().equals(castOther.getResultcode()))) && ((this.getResultdescription() == castOther.getResultdescription()) || (this.getResultdescription() != null && castOther.getResultdescription() != null && this.getResultdescription().equals(castOther.getResultdescription())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getSettingId() == null ? 0 : this.getSettingId().hashCode());
        result = 37 * result + (getProductId() == null ? 0 : this.getProductId().hashCode());
        result = 37 * result + (getProductName() == null ? 0 : this.getProductName().hashCode());
        result = 37 * result + (getProductQty() == null ? 0 : this.getProductQty().hashCode());
        result = 37 * result + (getUnitNo() == null ? 0 : this.getUnitNo().hashCode());
        result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode());
        result = 37 * result + (getResultcode() == null ? 0 : this.getResultcode().hashCode());
        result = 37 * result + (getResultdescription() == null ? 0 : this.getResultdescription().hashCode());
        return result;
    }

}
