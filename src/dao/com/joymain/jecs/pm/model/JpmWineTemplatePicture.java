package com.joymain.jecs.pm.model;

// Generated 2013-11-22 14:51:50 by Hibernate Tools 3.1.0.beta4

import java.util.Date;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *                  table="JPM_WINE_TEMPLATE_PICTURE"
 * 
 */

public class JpmWineTemplatePicture extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    // Fields    

    private Long idf;

    //    private String subNo;

    private String subName;

    private String pictureName;

    private String picturePath;

    private Date createTime = new Date();

    private Long pictureSize;

    private String pictureExt;

    private String isInvalid;

    private String pictureNameSrc;//

    private String pictureNameDist;

    private JpmProductWineTemplateSub jpmProductWineTemplateSub = new JpmProductWineTemplateSub();

    // Constructors

    /** default constructor */
    public JpmWineTemplatePicture() {
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
     * *
     * 
     * @hibernate.many-to-one not-null="true" cascade="none"
     * @hibernate.column name="SUB_NO"
     * 
     */
    public JpmProductWineTemplateSub getJpmProductWineTemplateSub() {
        return jpmProductWineTemplateSub;
    }

    public void setJpmProductWineTemplateSub(JpmProductWineTemplateSub jpmProductWineTemplateSub) {
        this.jpmProductWineTemplateSub = jpmProductWineTemplateSub;
    }

    //    /**
    //     * * @hibernate.property
    //     * column="SUB_NO"
    //     * length="32"
    //     * 
    //     */

    //    public String getSubNo() {
    //        return this.subNo;
    //    }
    //
    //    public void setSubNo(String subNo) {
    //        this.subNo = subNo;
    //    }

    /**
     * * @hibernate.property
     * column="SUB_NAME"
     * length="200"
     * 
     */
    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    /**
     * * @hibernate.property
     * column="PICTURE_NAME"
     * length="100"
     * 
     */

    public String getPictureName() {
        return this.pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    /**
     * * @hibernate.property
     * column="PICTURE_PATH"
     * length="500"
     * 
     */

    public String getPicturePath() {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
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
     * column="PICTURE_SIZE"
     * 
     */

    public Long getPictureSize() {
        return this.pictureSize;
    }

    public void setPictureSize(Long pictureSize) {
        this.pictureSize = pictureSize;
    }

    /**
     * * @hibernate.property
     * column="PICTURE_EXT"
     * length="10"
     * 
     */

    public String getPictureExt() {
        return this.pictureExt;
    }

    public void setPictureExt(String pictureExt) {
        this.pictureExt = pictureExt;
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
        this.isInvalid = isInvalid;
    }

    /**
     * *@hibernate.property
     * column="PICTURE_NAME_SRC"
     * length="100"
     */
    public String getPictureNameSrc() {
        return pictureNameSrc;
    }

    public void setPictureNameSrc(String pictureNameSrc) {
        this.pictureNameSrc = pictureNameSrc;
    }

    /**
     * *@hibernate.property
     * column="PICTURE_NAME_DIST"
     * length="100"
     */
    public String getPictureNameDist() {
        return pictureNameDist;
    }

    public void setPictureNameDist(String pictureNameDist) {
        this.pictureNameDist = pictureNameDist;
    }

    @Override
    public String toString() {
        return "JpmWineTemplatePicture [idf=" + idf + ",  subName=" + subName + ", pictureName=" + pictureName + ", picturePath=" + picturePath + ", createTime=" + createTime + ", pictureSize=" + pictureSize + ", pictureExt=" + pictureExt + ", isInvalid=" + isInvalid + ", pictureNameSrc=" + pictureNameSrc + ", pictureNameDist=" + pictureNameDist + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((idf == null) ? 0 : idf.hashCode());
        result = prime * result + ((isInvalid == null) ? 0 : isInvalid.hashCode());
        result = prime * result + ((pictureExt == null) ? 0 : pictureExt.hashCode());
        result = prime * result + ((pictureName == null) ? 0 : pictureName.hashCode());
        result = prime * result + ((pictureNameDist == null) ? 0 : pictureNameDist.hashCode());
        result = prime * result + ((pictureNameSrc == null) ? 0 : pictureNameSrc.hashCode());
        result = prime * result + ((picturePath == null) ? 0 : picturePath.hashCode());
        result = prime * result + ((pictureSize == null) ? 0 : pictureSize.hashCode());
        result = prime * result + ((subName == null) ? 0 : subName.hashCode());
        //        result = prime * result + ((subNo == null) ? 0 : subNo.hashCode());
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
        JpmWineTemplatePicture other = (JpmWineTemplatePicture) obj;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (idf == null) {
            if (other.idf != null)
                return false;
        } else if (!idf.equals(other.idf))
            return false;
        if (isInvalid == null) {
            if (other.isInvalid != null)
                return false;
        } else if (!isInvalid.equals(other.isInvalid))
            return false;
        if (pictureExt == null) {
            if (other.pictureExt != null)
                return false;
        } else if (!pictureExt.equals(other.pictureExt))
            return false;
        if (pictureName == null) {
            if (other.pictureName != null)
                return false;
        } else if (!pictureName.equals(other.pictureName))
            return false;
        if (pictureNameDist == null) {
            if (other.pictureNameDist != null)
                return false;
        } else if (!pictureNameDist.equals(other.pictureNameDist))
            return false;
        if (pictureNameSrc == null) {
            if (other.pictureNameSrc != null)
                return false;
        } else if (!pictureNameSrc.equals(other.pictureNameSrc))
            return false;
        if (picturePath == null) {
            if (other.picturePath != null)
                return false;
        } else if (!picturePath.equals(other.picturePath))
            return false;
        if (pictureSize == null) {
            if (other.pictureSize != null)
                return false;
        } else if (!pictureSize.equals(other.pictureSize))
            return false;
        if (subName == null) {
            if (other.subName != null)
                return false;
        } else if (!subName.equals(other.subName))
            return false;
        //        if (subNo == null) {
        //            if (other.subNo != null)
        //                return false;
        //        } else if (!subNo.equals(other.subNo))
        //            return false;
        return true;
    }

    /**
     * JpmWineTemplatePicture 构造函数
     * 
     * @param idf
     * @param subNo
     * @param subName
     * @param pictureName
     * @param picturePath
     * @param createTime
     * @param pictureSize
     * @param pictureExt
     * @param isInvalid
     * @param pictureNameSrc
     * @param pictureNameDist
     */
    public JpmWineTemplatePicture(Long idf, String subNo, String subName, String pictureName, String picturePath, Date createTime, Long pictureSize, String pictureExt, String isInvalid, String pictureNameSrc, String pictureNameDist) {
        super();
        this.idf = idf;
        //        this.subNo = subNo;
        this.subName = subName;
        this.pictureName = pictureName;
        this.picturePath = picturePath;
        this.createTime = createTime;
        this.pictureSize = pictureSize;
        this.pictureExt = pictureExt;
        this.isInvalid = isInvalid;
        this.pictureNameSrc = pictureNameSrc;
        this.pictureNameDist = pictureNameDist;
    }

}
