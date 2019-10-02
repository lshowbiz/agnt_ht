package com.joymain.jecs.sys.model;
// Generated 2008-7-30 10:49:00 by Hibernate Tools 3.1.0.beta4

import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_MODULE"
 *     
 */

public class SysModule extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long moduleId;
    private Long parentId;
    private String moduleCode;
    private String moduleName;
    private String moduleDesc;
    private Integer moduleType;
    private String linkUrl;
    private Long orderNo;
    private Integer isActive;
    private Integer isValidate;
    
    private String treeIndex;
    private Long treeLevel;
    private String otherUrl1;
    private String otherUrl2;
    private String otherUrl3;
    private String otherUrl4;
    private String otherUrl5;
    private Set sysCompanyPows = new HashSet(0);
    private Set sysUsrTypePows = new HashSet(0);

    // Constructors

    /** default constructor */
    public SysModule() {
    }

    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="MODULE_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getModuleId() {
        return this.moduleId;
    }
    
    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
    /**       
     *      *            @hibernate.property
     *             column="PARENT_ID"
     *             length="22"
     *             not-null="true"
     *         
     */

    public Long getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="MODULE_CODE"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getModuleCode() {
    	return moduleCode;
    }

    /**
	 * @spring.validator type="required"
	 * @param moduleName String
	 */
	public void setModuleCode(String moduleCode) {
    	this.moduleCode = moduleCode;
    }

	/**       
     *      *            @hibernate.property
     *             column="MODULE_NAME"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getModuleName() {
        return this.moduleName;
    }
    
    /**
	 * @spring.validator type="required"
	 * @param moduleName String
	 */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    /**       
     *      *            @hibernate.property
     *             column="MODULE_DESC"
     *             length="100"
     *         
     */

    public String getModuleDesc() {
        return this.moduleDesc;
    }
    
    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }
    /**       
     *      *            @hibernate.property
     *             column="MODULE_TYPE"
     *             length="4"
     *             not-null="true"
     *         
     */

    public Integer getModuleType() {
        return this.moduleType;
    }
    
    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }
    /**       
     *      *            @hibernate.property
     *             column="LINK_URL"
     *             length="250"
     *         
     */

    public String getLinkUrl() {
        return this.linkUrl;
    }
    
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_NO"
     *             length="22"
     *         
     */

    public Long getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_ACTIVE"
     *             length="2"
     *         
     */

    public Integer getIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_VALIDATE"
     *             length="4"
     *         
     */

    public Integer getIsValidate() {
        return this.isValidate;
    }
    
    public void setIsValidate(Integer isValidate) {
        this.isValidate = isValidate;
    }

    /**       
     *      *            @hibernate.property
     *             column="TREE_INDEX"
     *             length="500"
     *         
     */

    public String getTreeIndex() {
        return this.treeIndex;
    }
    
    public void setTreeIndex(String treeIndex) {
        this.treeIndex = treeIndex;
    }
    /**       
     *      *            @hibernate.property
     *             column="TREE_LEVEL"
     *             length="12"
     *         
     */

    public Long getTreeLevel() {
        return this.treeLevel;
    }
    
    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }
	
    /**       
     *      *            @hibernate.property
     *             column="OTHER_URL1"
     *             length="250"
     *         
     */
	public String getOtherUrl1() {
    	return otherUrl1;
    }

	public void setOtherUrl1(String otherUrl1) {
    	this.otherUrl1 = otherUrl1;
    }

	/**       
     *      *            @hibernate.property
     *             column="OTHER_URL2"
     *             length="250"
     *         
     */
	public String getOtherUrl2() {
    	return otherUrl2;
    }

	public void setOtherUrl2(String otherUrl2) {
    	this.otherUrl2 = otherUrl2;
    }

	/**       
     *      *            @hibernate.property
     *             column="OTHER_URL3"
     *             length="250"
     *         
     */
	public String getOtherUrl3() {
    	return otherUrl3;
    }

	public void setOtherUrl3(String otherUrl3) {
    	this.otherUrl3 = otherUrl3;
    }

	/**       
     *      *            @hibernate.property
     *             column="OTHER_URL4"
     *             length="250"
     *         
     */
	public String getOtherUrl4() {
    	return otherUrl4;
    }

	public void setOtherUrl4(String otherUrl4) {
    	this.otherUrl4 = otherUrl4;
    }

	/**       
     *      *            @hibernate.property
     *             column="OTHER_URL5"
     *             length="250"
     *         
     */
	public String getOtherUrl5() {
    	return otherUrl5;
    }

	public void setOtherUrl5(String otherUrl5) {
    	this.otherUrl5 = otherUrl5;
    }

	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all"
	 * @hibernate.collection-key column="MODULE_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.sys.model.SysCompanyPow"
	 * 
	 */
	public Set getSysCompanyPows() {
    	return sysCompanyPows;
    }

	public void setSysCompanyPows(Set sysCompanyPows) {
    	this.sysCompanyPows = sysCompanyPows;
    }

	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all"
	 * @hibernate.collection-key column="MODULE_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.sys.model.SysUsrTypePow"
	 * 
	 */
	public Set getSysUsrTypePows() {
    	return sysUsrTypePows;
    }

	public void setSysUsrTypePows(Set sysUsrTypePows) {
    	this.sysUsrTypePows = sysUsrTypePows;
    }

	

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("parentId").append("='").append(getParentId()).append("' ");			
      buffer.append("moduleName").append("='").append(getModuleName()).append("' ");			
      buffer.append("moduleDesc").append("='").append(getModuleDesc()).append("' ");			
      buffer.append("moduleType").append("='").append(getModuleType()).append("' ");			
      buffer.append("linkUrl").append("='").append(getLinkUrl()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("isActive").append("='").append(getIsActive()).append("' ");			
      buffer.append("isValidate").append("='").append(getIsValidate()).append("' ");			
      buffer.append("treeIndex").append("='").append(getTreeIndex()).append("' ");			
      buffer.append("treeLevel").append("='").append(getTreeLevel()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysModule) ) return false;
		 SysModule castOther = ( SysModule ) other; 
         
		 return ( (this.getModuleId()==castOther.getModuleId()) || ( this.getModuleId()!=null && castOther.getModuleId()!=null && this.getModuleId().equals(castOther.getModuleId()) ) )
 && ( (this.getParentId()==castOther.getParentId()) || ( this.getParentId()!=null && castOther.getParentId()!=null && this.getParentId().equals(castOther.getParentId()) ) )
 && ( (this.getModuleName()==castOther.getModuleName()) || ( this.getModuleName()!=null && castOther.getModuleName()!=null && this.getModuleName().equals(castOther.getModuleName()) ) )
 && ( (this.getModuleDesc()==castOther.getModuleDesc()) || ( this.getModuleDesc()!=null && castOther.getModuleDesc()!=null && this.getModuleDesc().equals(castOther.getModuleDesc()) ) )
 && ( (this.getModuleType()==castOther.getModuleType()) || ( this.getModuleType()!=null && castOther.getModuleType()!=null && this.getModuleType().equals(castOther.getModuleType()) ) )
 && ( (this.getLinkUrl()==castOther.getLinkUrl()) || ( this.getLinkUrl()!=null && castOther.getLinkUrl()!=null && this.getLinkUrl().equals(castOther.getLinkUrl()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) )
 && ( (this.getIsActive()==castOther.getIsActive()) || ( this.getIsActive()!=null && castOther.getIsActive()!=null && this.getIsActive().equals(castOther.getIsActive()) ) )
 && ( (this.getIsValidate()==castOther.getIsValidate()) || ( this.getIsValidate()!=null && castOther.getIsValidate()!=null && this.getIsValidate().equals(castOther.getIsValidate()) ) )
 && ( (this.getTreeIndex()==castOther.getTreeIndex()) || ( this.getTreeIndex()!=null && castOther.getTreeIndex()!=null && this.getTreeIndex().equals(castOther.getTreeIndex()) ) )
 && ( (this.getTreeLevel()==castOther.getTreeLevel()) || ( this.getTreeLevel()!=null && castOther.getTreeLevel()!=null && this.getTreeLevel().equals(castOther.getTreeLevel()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getModuleId() == null ? 0 : this.getModuleId().hashCode() );
         result = 37 * result + ( getParentId() == null ? 0 : this.getParentId().hashCode() );
         result = 37 * result + ( getModuleName() == null ? 0 : this.getModuleName().hashCode() );
         result = 37 * result + ( getModuleDesc() == null ? 0 : this.getModuleDesc().hashCode() );
         result = 37 * result + ( getModuleType() == null ? 0 : this.getModuleType().hashCode() );
         result = 37 * result + ( getLinkUrl() == null ? 0 : this.getLinkUrl().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         result = 37 * result + ( getIsActive() == null ? 0 : this.getIsActive().hashCode() );
         result = 37 * result + ( getIsValidate() == null ? 0 : this.getIsValidate().hashCode() );
         result = 37 * result + ( getTreeIndex() == null ? 0 : this.getTreeIndex().hashCode() );
         result = 37 * result + ( getTreeLevel() == null ? 0 : this.getTreeLevel().hashCode() );
         return result;
   }   
}
