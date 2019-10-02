package com.joymain.jecs.pm.model;

// Generated 2013-12-3 14:38:13 by Hibernate Tools 3.1.0.beta4

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *                  table="JPM_WINE_SETTING_LIST_INF"
 * 
 */

public class JpmWineSettingListInf extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    // Fields    

    private Long idf;//主键

    private String materialNo;//材料编号

    private Integer qty;//材料数量

    private String sdate;//生效日期（格式：2013-01-12）

    private String edate;//失效日期（格式：2013-01-12）

    private String memo;//备注

    private Float lossRatio;//损耗系数

    private String isMainMaterial;//是否主料 1:是 0：否 默认：0

    private String isSendMaterial;//是否发料 1:是 0：否  默认：0

    private String isDelegateOut;//是否委外 1:是 0：否 默认：0

    private String isFeatureItem;//是否特征项 1:是 0：否 默认：0

    private String picName;//图片名称

    private JpmWineSettingInf JpmWineSettingListInf = new JpmWineSettingInf();//配置单

    // Constructors

    /** default constructor */
    public JpmWineSettingListInf() {
    }

    // Property accessors
    /**
     * * @hibernate.id
     * generator-class="native"
     * type="java.lang.Long"
     * column="IDF"
     * 
     */

    public Long getIdf() {
        return this.idf;
    }

    public void setIdf(Long idf) {
        this.idf = idf;
    }

    /**
     * * @hibernate.property
     * column="MATERIAL_NO"
     * length="20"
     * 
     */

    public String getMaterialNo() {
        return this.materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    /**
     * * @hibernate.property
     * column="QTY"
     * length="5"
     * 
     */

    public Integer getQty() {
        return this.qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    /**
     * * @hibernate.property
     * column="SDATE"
     * length="10"
     * 
     */

    public String getSdate() {
        return this.sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    /**
     * * @hibernate.property
     * column="EDATE"
     * length="10"
     * 
     */

    public String getEdate() {
        return this.edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
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
     * column="LOSS_RATIO"
     * length="22"
     * 
     */
    public Float getLossRatio() {
        return lossRatio;
    }

    public void setLossRatio(Float lossRatio) {
        this.lossRatio = lossRatio;
    }

    /**
     * * @hibernate.property
     * column="IS_MAIN_MATERIAL"
     * length="1"
     * 
     */
    public String getIsMainMaterial() {
        return isMainMaterial;
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
        return isSendMaterial;
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
        return isDelegateOut;
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
        return isFeatureItem;
    }

    public void setIsFeatureItem(String isFeatureItem) {
        this.isFeatureItem = isFeatureItem;
    }

    /**
     * * @hibernate.property
     * column="PIC_NAME"
     * length="100"
     * 
     */
    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    /**
     * 
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="SETTING_ID"
     * 
     * @return
     */
    public JpmWineSettingInf getJpmWineSettingListInf() {
        return JpmWineSettingListInf;
    }

    public void setJpmWineSettingListInf(JpmWineSettingInf jpmWineSettingListInf) {
        JpmWineSettingListInf = jpmWineSettingListInf;
    }

    @Override
    public String toString() {
        return "JpmWineSettingListInf [idf=" + idf + ", materialNo=" + materialNo + ", qty=" + qty + ", sdate=" + sdate + ", edate=" + edate + ", memo=" + memo + ", lossRatio=" + lossRatio + ", isMainMaterial=" + isMainMaterial + ", isSendMaterial=" + isSendMaterial + ", isDelegateOut=" + isDelegateOut + ", isFeatureItem=" + isFeatureItem + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((edate == null) ? 0 : edate.hashCode());
        result = prime * result + ((idf == null) ? 0 : idf.hashCode());
        result = prime * result + ((isDelegateOut == null) ? 0 : isDelegateOut.hashCode());
        result = prime * result + ((isFeatureItem == null) ? 0 : isFeatureItem.hashCode());
        result = prime * result + ((isMainMaterial == null) ? 0 : isMainMaterial.hashCode());
        result = prime * result + ((isSendMaterial == null) ? 0 : isSendMaterial.hashCode());
        result = prime * result + ((lossRatio == null) ? 0 : lossRatio.hashCode());
        result = prime * result + ((materialNo == null) ? 0 : materialNo.hashCode());
        result = prime * result + ((memo == null) ? 0 : memo.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((sdate == null) ? 0 : sdate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JpmWineSettingListInf other = (JpmWineSettingListInf) obj;
        if (edate == null) {
            if (other.edate != null)
                return false;
        } else if (!edate.equals(other.edate))
            return false;
        if (idf == null) {
            if (other.idf != null)
                return false;
        } else if (!idf.equals(other.idf))
            return false;
        if (isDelegateOut == null) {
            if (other.isDelegateOut != null)
                return false;
        } else if (!isDelegateOut.equals(other.isDelegateOut))
            return false;
        if (isFeatureItem == null) {
            if (other.isFeatureItem != null)
                return false;
        } else if (!isFeatureItem.equals(other.isFeatureItem))
            return false;
        if (isMainMaterial == null) {
            if (other.isMainMaterial != null)
                return false;
        } else if (!isMainMaterial.equals(other.isMainMaterial))
            return false;
        if (isSendMaterial == null) {
            if (other.isSendMaterial != null)
                return false;
        } else if (!isSendMaterial.equals(other.isSendMaterial))
            return false;
        if (lossRatio == null) {
            if (other.lossRatio != null)
                return false;
        } else if (!lossRatio.equals(other.lossRatio))
            return false;
        if (materialNo == null) {
            if (other.materialNo != null)
                return false;
        } else if (!materialNo.equals(other.materialNo))
            return false;
        if (memo == null) {
            if (other.memo != null)
                return false;
        } else if (!memo.equals(other.memo))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (sdate == null) {
            if (other.sdate != null)
                return false;
        } else if (!sdate.equals(other.sdate))
            return false;
        return true;
    }

    /**
     * JpmWineSettingListInf 构造函数
     * 
     * @param idf
     * @param materialNo
     * @param qty
     * @param sdate
     * @param edate
     * @param memo
     * @param lossRatio
     * @param isMainMaterial
     * @param isSendMaterial
     * @param isDelegateOut
     * @param isFeatureItem
     * @param picName
     * @param jpmWineSettingListInf
     */
    public JpmWineSettingListInf(Long idf, String materialNo, Integer qty, String sdate, String edate, String memo, Float lossRatio, String isMainMaterial, String isSendMaterial, String isDelegateOut, String isFeatureItem, String picName, JpmWineSettingInf jpmWineSettingListInf) {
        super();
        this.idf = idf;
        this.materialNo = materialNo;
        this.qty = qty;
        this.sdate = sdate;
        this.edate = edate;
        this.memo = memo;
        this.lossRatio = lossRatio;
        this.isMainMaterial = isMainMaterial;
        this.isSendMaterial = isSendMaterial;
        this.isDelegateOut = isDelegateOut;
        this.isFeatureItem = isFeatureItem;
        this.picName = picName;
        JpmWineSettingListInf = jpmWineSettingListInf;
    }

}
