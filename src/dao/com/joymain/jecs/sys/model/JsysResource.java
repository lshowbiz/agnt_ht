package com.joymain.jecs.sys.model;
// Generated 2013-5-13 15:37:11 by Hibernate Tools 3.1.0.beta4




/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_RESOURCE"
 *     
 */

public class JsysResource extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long resId;
    private Long parentId;
    private String resCode;
    private String resName;
    private int resType;
    private String sysType;
    private String resUrl;
    private String resDes;
    private Long orderNo;
    private String active;
    private String validateFlag;
    private String treeIndex;
    private Long treeLevel;


    // Constructors

    /** default constructor */
    public JsysResource() {
    }

	/** minimal constructor */
    public JsysResource(Long parentId, int resType, String sysType) {
        this.parentId = parentId;
        this.resType = resType;
        this.sysType = sysType;
    }
    
    /** full constructor */
    public JsysResource(Long parentId, String resCode, String resName, int resType, String sysType, String resUrl, String resDes, Long orderNo, String active, String validateFlag, String treeIndex, Long treeLevel) {
        this.parentId = parentId;
        this.resCode = resCode;
        this.resName = resName;
        this.resType = resType;
        this.sysType = sysType;
        this.resUrl = resUrl;
        this.resDes = resDes;
        this.orderNo = orderNo;
        this.active = active;
        this.validateFlag = validateFlag;
        this.treeIndex = treeIndex;
        this.treeLevel = treeLevel;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="RES_ID"
     *    @hibernate.generator-param name="sequence" value="SEQ_SYS"     
     */

    public Long getResId() {
        return this.resId;
    }
    
    public void setResId(Long resId) {
        this.resId = resId;
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
     *             column="RES_CODE"
     *             length="100"
     *         
     */

    public String getResCode() {
        return this.resCode;
    }
    
    /**
	 * @spring.validator type="required"
	 * @param resCode String
	 */
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="RES_NAME"
     *             length="500"
     *         
     */

    public String getResName() {
        return this.resName;
    }
    
    /**
	 * @spring.validator type="required"
	 * @param resName String
	 */
    public void setResName(String resName) {
        this.resName = resName;
    }
    /**       
     *      *            @hibernate.property
     *             column="RES_TYPE"
     *             length="4"
     *             not-null="true"
     *         
     */

    public int getResType() {
        return this.resType;
    }
    
    public void setResType(int resType) {
        this.resType = resType;
    }
    /**       
     *      *            @hibernate.property
     *             column="SYS_TYPE"
     *             length="10"
     *             not-null="true"
     *         
     */

    public String getSysType() {
        return this.sysType;
    }
    
    public void setSysType(String sysType) {
        this.sysType = sysType;
    }
    /**       
     *      *            @hibernate.property
     *             column="RES_URL"
     *             length="500"
     *         
     */

    public String getResUrl() {
        return this.resUrl;
    }
    
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }
    /**       
     *      *            @hibernate.property
     *             column="RES_DES"
     *             length="500"
     *         
     */

    public String getResDes() {
        return this.resDes;
    }
    
    public void setResDes(String resDes) {
        this.resDes = resDes;
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
     *             column="ACTIVE"
     *             length="1"
     *         
     */

    public String getActive() {
        return this.active;
    }
    
    public void setActive(String active) {
        this.active = active;
    }
    /**       
     *      *            @hibernate.property
     *             column="VALIDATE_FLAG"
     *             length="1"
     *         
     */

    public String getValidateFlag() {
        return this.validateFlag;
    }
    
    public void setValidateFlag(String validateFlag) {
        this.validateFlag = validateFlag;
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
     *             length="10"
     *         
     */

    public Long getTreeLevel() {
        return this.treeLevel;
    }
    
    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("parentId").append("='").append(getParentId()).append("' ");			
      buffer.append("resCode").append("='").append(getResCode()).append("' ");			
      buffer.append("resName").append("='").append(getResName()).append("' ");			
      buffer.append("resType").append("='").append(getResType()).append("' ");			
      buffer.append("sysType").append("='").append(getSysType()).append("' ");			
      buffer.append("resUrl").append("='").append(getResUrl()).append("' ");			
      buffer.append("resDes").append("='").append(getResDes()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("active").append("='").append(getActive()).append("' ");			
      buffer.append("validateFlag").append("='").append(getValidateFlag()).append("' ");			
      buffer.append("treeIndex").append("='").append(getTreeIndex()).append("' ");			
      buffer.append("treeLevel").append("='").append(getTreeLevel()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JsysResource) ) return false;
		 JsysResource castOther = ( JsysResource ) other; 
         
		 return ( (this.getResId()==castOther.getResId()) || ( this.getResId()!=null && castOther.getResId()!=null && this.getResId().equals(castOther.getResId()) ) )
 && ( (this.getParentId()==castOther.getParentId()) || ( this.getParentId()!=null && castOther.getParentId()!=null && this.getParentId().equals(castOther.getParentId()) ) )
 && ( (this.getResCode()==castOther.getResCode()) || ( this.getResCode()!=null && castOther.getResCode()!=null && this.getResCode().equals(castOther.getResCode()) ) )
 && ( (this.getResName()==castOther.getResName()) || ( this.getResName()!=null && castOther.getResName()!=null && this.getResName().equals(castOther.getResName()) ) )
 && (this.getResType()==castOther.getResType())
 && ( (this.getSysType()==castOther.getSysType()) || ( this.getSysType()!=null && castOther.getSysType()!=null && this.getSysType().equals(castOther.getSysType()) ) )
 && ( (this.getResUrl()==castOther.getResUrl()) || ( this.getResUrl()!=null && castOther.getResUrl()!=null && this.getResUrl().equals(castOther.getResUrl()) ) )
 && ( (this.getResDes()==castOther.getResDes()) || ( this.getResDes()!=null && castOther.getResDes()!=null && this.getResDes().equals(castOther.getResDes()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) )
 && ( (this.getActive()==castOther.getActive()) || ( this.getActive()!=null && castOther.getActive()!=null && this.getActive().equals(castOther.getActive()) ) )
 && ( (this.getValidateFlag()==castOther.getValidateFlag()) || ( this.getValidateFlag()!=null && castOther.getValidateFlag()!=null && this.getValidateFlag().equals(castOther.getValidateFlag()) ) )
 && ( (this.getTreeIndex()==castOther.getTreeIndex()) || ( this.getTreeIndex()!=null && castOther.getTreeIndex()!=null && this.getTreeIndex().equals(castOther.getTreeIndex()) ) )
 && ( (this.getTreeLevel()==castOther.getTreeLevel()) || ( this.getTreeLevel()!=null && castOther.getTreeLevel()!=null && this.getTreeLevel().equals(castOther.getTreeLevel()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getResId() == null ? 0 : this.getResId().hashCode() );
         result = 37 * result + ( getParentId() == null ? 0 : this.getParentId().hashCode() );
         result = 37 * result + ( getResCode() == null ? 0 : this.getResCode().hashCode() );
         result = 37 * result + ( getResName() == null ? 0 : this.getResName().hashCode() );
         result = 37 * result + this.getResType();
         result = 37 * result + ( getSysType() == null ? 0 : this.getSysType().hashCode() );
         result = 37 * result + ( getResUrl() == null ? 0 : this.getResUrl().hashCode() );
         result = 37 * result + ( getResDes() == null ? 0 : this.getResDes().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         result = 37 * result + ( getActive() == null ? 0 : this.getActive().hashCode() );
         result = 37 * result + ( getValidateFlag() == null ? 0 : this.getValidateFlag().hashCode() );
         result = 37 * result + ( getTreeIndex() == null ? 0 : this.getTreeIndex().hashCode() );
         result = 37 * result + ( getTreeLevel() == null ? 0 : this.getTreeLevel().hashCode() );
         return result;
   }   





}
