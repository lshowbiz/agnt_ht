package com.joymain.jecs.sys.model;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_DEPARTMENT"
 *     
 */

public class SysDepartment extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long departmentId;
    private Long parentId;
    private String departmentName;
    private String fullName;
    private String principal;
    private String tel;
    private String fax;
    private String companyCode;
    private String treeIndex;
    private Long orderNo;
    private Long treeLevel;
    private String allowedUser;

    // Constructors

    /** default constructor */
    public SysDepartment() {
    }

	/** minimal constructor */
    public SysDepartment(Long parentId, String departmentName, long treeLevel) {
        this.parentId = parentId;
        this.departmentName = departmentName;
        this.treeLevel = treeLevel;
    }

    // Property accessors
    /**
     * @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="DEPARTMENT_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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
     *             column="DEPARTMENT_NAME"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getDepartmentName() {
        return this.departmentName;
    }
    
    /**
	 * @spring.validator type="required"
	 * @param departmentName String
	 */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    /**       
     *      *            @hibernate.property
     *             column="FULL_NAME"
     *             length="100"
     *         
     */

    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRINCIPAL"
     *             length="20"
     *         
     */

    public String getPrincipal() {
        return this.principal;
    }
    
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    /**       
     *      *            @hibernate.property
     *             column="TEL"
     *             length="30"
     *         
     */

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**       
     *      *            @hibernate.property
     *             column="FAX"
     *             length="30"
     *         
     */

    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
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
     *             column="TREE_INDEX"
     *             unique="true"
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
     *             column="ALLOWED_USER"
     *             length="500"
     *         
     */
    public String getAllowedUser() {
    	return allowedUser;
    }


	public void setAllowedUser(String allowedUser) {
    	this.allowedUser = allowedUser;
    }

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("parentId").append("='").append(getParentId()).append("' ");			
      buffer.append("departmentName").append("='").append(getDepartmentName()).append("' ");			
      buffer.append("fullName").append("='").append(getFullName()).append("' ");			
      buffer.append("principal").append("='").append(getPrincipal()).append("' ");			
      buffer.append("tel").append("='").append(getTel()).append("' ");			
      buffer.append("fax").append("='").append(getFax()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("treeIndex").append("='").append(getTreeIndex()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("treeLevel").append("='").append(getTreeLevel()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysDepartment) ) return false;
		 SysDepartment castOther = ( SysDepartment ) other; 
         
		 return ( (this.getDepartmentId()==castOther.getDepartmentId()) || ( this.getDepartmentId()!=null && castOther.getDepartmentId()!=null && this.getDepartmentId().equals(castOther.getDepartmentId()) ) )
 && ( (this.getParentId()==castOther.getParentId()) || ( this.getParentId()!=null && castOther.getParentId()!=null && this.getParentId().equals(castOther.getParentId()) ) )
 && ( (this.getDepartmentName()==castOther.getDepartmentName()) || ( this.getDepartmentName()!=null && castOther.getDepartmentName()!=null && this.getDepartmentName().equals(castOther.getDepartmentName()) ) )
 && ( (this.getFullName()==castOther.getFullName()) || ( this.getFullName()!=null && castOther.getFullName()!=null && this.getFullName().equals(castOther.getFullName()) ) )
 && ( (this.getPrincipal()==castOther.getPrincipal()) || ( this.getPrincipal()!=null && castOther.getPrincipal()!=null && this.getPrincipal().equals(castOther.getPrincipal()) ) )
 && ( (this.getTel()==castOther.getTel()) || ( this.getTel()!=null && castOther.getTel()!=null && this.getTel().equals(castOther.getTel()) ) )
 && ( (this.getFax()==castOther.getFax()) || ( this.getFax()!=null && castOther.getFax()!=null && this.getFax().equals(castOther.getFax()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getTreeIndex()==castOther.getTreeIndex()) || ( this.getTreeIndex()!=null && castOther.getTreeIndex()!=null && this.getTreeIndex().equals(castOther.getTreeIndex()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) )
  && ( (this.getTreeLevel()==castOther.getTreeLevel()) || ( this.getTreeLevel()!=null && castOther.getTreeLevel()!=null && this.getTreeLevel().equals(castOther.getTreeLevel()) ) );
  }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDepartmentId() == null ? 0 : this.getDepartmentId().hashCode() );
         result = 37 * result + ( getParentId() == null ? 0 : this.getParentId().hashCode() );
         result = 37 * result + ( getDepartmentName() == null ? 0 : this.getDepartmentName().hashCode() );
         result = 37 * result + ( getFullName() == null ? 0 : this.getFullName().hashCode() );
         result = 37 * result + ( getPrincipal() == null ? 0 : this.getPrincipal().hashCode() );
         result = 37 * result + ( getTel() == null ? 0 : this.getTel().hashCode() );
         result = 37 * result + ( getFax() == null ? 0 : this.getFax().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getTreeIndex() == null ? 0 : this.getTreeIndex().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         result = 37 * result + ( getTreeLevel() == null ? 0 : this.getTreeLevel().hashCode() );
         return result;
   }   
}