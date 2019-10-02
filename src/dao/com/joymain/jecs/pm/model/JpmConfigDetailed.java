package com.joymain.jecs.pm.model;
// Generated 2013-12-17 18:53:34 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_CONFIG_DETAILED"
 *     
 */

public class JpmConfigDetailed extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long configdetailedNo;
    private String subNo;
    private String subName;
    private Long subAmount;
    private Date createtime;
    private String status;
    private Long specNo;
    private BigDecimal price;
    private String isMustSelected;
    private Long idf;
    private String isMainMaterial;
    private String remark;

    private String isCheck;
    
    //单价
    private Long unitPrice;
    
    //图片名称
    private String picName;
    
    //图片地址
    private String picUrl;
    
    //可选图片集合
    private List<JpmWineTemplatePicture> jpmWineTemplatePictureList = new ArrayList<JpmWineTemplatePicture>();
    // Constructors

    /** default constructor */
    public JpmConfigDetailed() {
    }

    
    /** full constructor */
    public JpmConfigDetailed(Long configdetailedNo, String subNo, String subName, Long subAmount, Date createtime, String status, Long specNo, BigDecimal price, String isMustSelected, Long idf, String isMainMaterial) {
        this.configdetailedNo = configdetailedNo;
        this.subNo = subNo;
        this.subName = subName;
        this.subAmount = subAmount;
        this.createtime = createtime;
        this.status = status;
        this.specNo = specNo;
        this.price = price;
        this.isMustSelected = isMustSelected;
        this.idf = idf;
        this.isMainMaterial = isMainMaterial;
    }
    
    public String getPicName()
    {
        return picName;
    }


    public void setPicName(String picName)
    {
        this.picName = picName;
    }


    public String getPicUrl()
    {
        return picUrl;
    }


    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }


    public List<JpmWineTemplatePicture> getJpmWineTemplatePictureList()
    {
        return jpmWineTemplatePictureList;
    }

    public void setJpmWineTemplatePictureList(List<JpmWineTemplatePicture> jpmWineTemplatePictureList)
    {
        this.jpmWineTemplatePictureList = jpmWineTemplatePictureList;
    }
    
    public Long getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice)
    {
        this.unitPrice = unitPrice;
    }
    
    public String getIsCheck()
    {
        return isCheck;
    }

    public void setIsCheck(String isCheck)
    {
        this.isCheck = isCheck;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="200"
     *         
     */
    public String getRemark()
    {
        return remark;
    }


    public void setRemark(String remark)
    {
        this.remark = remark;
    }


    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *         
     *                 column="CONFIGDETAILED_NO"
     *             
     */

    public Long getConfigdetailedNo() {
        return this.configdetailedNo;
    }
    
    public void setConfigdetailedNo(Long configdetailedNo) {
        this.configdetailedNo = configdetailedNo;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="SUB_NO"
     *             
     */

    public String getSubNo() {
        return this.subNo;
    }
    
    public void setSubNo(String subNo) {
        this.subNo = subNo;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="SUB_NAME"
     *             
     */

    public String getSubName() {
        return this.subName;
    }
    
    public void setSubName(String subName) {
        this.subName = subName;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="SUB_AMOUNT"
     *             
     */

    public Long getSubAmount() {
        return this.subAmount;
    }
    
    public void setSubAmount(Long subAmount) {
        this.subAmount = subAmount;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="CREATETIME"
     *             
     */

    public Date getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="STATUS"
     *             
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="SPEC_NO"
     *             
     */

    public Long getSpecNo() {
        return this.specNo;
    }
    
    public void setSpecNo(Long specNo) {
        this.specNo = specNo;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="PRICE"
     *             
     */

    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="IS_MUST_SELECTED"
     *             
     */

    public String getIsMustSelected() {
        return this.isMustSelected;
    }
    
    public void setIsMustSelected(String isMustSelected) {
        this.isMustSelected = isMustSelected;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="IDF"
     *             
     */

    public Long getIdf() {
        return this.idf;
    }
    
    public void setIdf(Long idf) {
        this.idf = idf;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="IS_MAIN_MATERIAL"
     *             
     */

    public String getIsMainMaterial() {
        return this.isMainMaterial;
    }
    
    public void setIsMainMaterial(String isMainMaterial) {
        this.isMainMaterial = isMainMaterial;
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 0;
        result = prime * result + ((configdetailedNo == null) ? 0 : configdetailedNo.hashCode());
        result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
        result = prime * result + ((idf == null) ? 0 : idf.hashCode());
        result = prime * result + ((isMainMaterial == null) ? 0 : isMainMaterial.hashCode());
        result = prime * result + ((isMustSelected == null) ? 0 : isMustSelected.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((specNo == null) ? 0 : specNo.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((subAmount == null) ? 0 : subAmount.hashCode());
        result = prime * result + ((subName == null) ? 0 : subName.hashCode());
        result = prime * result + ((subNo == null) ? 0 : subNo.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        JpmConfigDetailed other = (JpmConfigDetailed) obj;
        if (configdetailedNo == null)
        {
            if (other.configdetailedNo != null)
                return false;
        }
        else if (!configdetailedNo.equals(other.configdetailedNo))
            return false;
        if (createtime == null)
        {
            if (other.createtime != null)
                return false;
        }
        else if (!createtime.equals(other.createtime))
            return false;
        if (idf == null)
        {
            if (other.idf != null)
                return false;
        }
        else if (!idf.equals(other.idf))
            return false;
        if (isMainMaterial == null)
        {
            if (other.isMainMaterial != null)
                return false;
        }
        else if (!isMainMaterial.equals(other.isMainMaterial))
            return false;
        if (isMustSelected == null)
        {
            if (other.isMustSelected != null)
                return false;
        }
        else if (!isMustSelected.equals(other.isMustSelected))
            return false;
        if (price == null)
        {
            if (other.price != null)
                return false;
        }
        else if (!price.equals(other.price))
            return false;
        if (specNo == null)
        {
            if (other.specNo != null)
                return false;
        }
        else if (!specNo.equals(other.specNo))
            return false;
        if (status == null)
        {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        if (subAmount == null)
        {
            if (other.subAmount != null)
                return false;
        }
        else if (!subAmount.equals(other.subAmount))
            return false;
        if (subName == null)
        {
            if (other.subName != null)
                return false;
        }
        else if (!subName.equals(other.subName))
            return false;
        if (subNo == null)
        {
            if (other.subNo != null)
                return false;
        }
        else if (!subNo.equals(other.subNo))
            return false;
        return true;
    }


    @Override
    public String toString()
    {
        return "JpmConfigDetailed [configdetailedNo=" + configdetailedNo + ", createtime="
            + createtime + ", idf=" + idf + ", isMainMaterial=" + isMainMaterial
            + ", isMustSelected=" + isMustSelected + ", price=" + price + ", specNo=" + specNo
            + ", status=" + status + ", subAmount=" + subAmount + ", subName=" + subName
            + ", subNo=" + subNo + "]";
    }









}
