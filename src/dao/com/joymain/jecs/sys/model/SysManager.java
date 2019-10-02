package com.joymain.jecs.sys.model;
// Generated 2008-7-30 10:43:27 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_MANAGER"
 *     
 */

public class SysManager extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
    private Long departmentId;
    private String email;
    private String tel;
    private String mobile;
    private String fax;
    private String address;
    private Long depOrder;
    private String companyCode;
    private String countryCode;
    private SysUser sysUser;

    // Constructors

    /** default constructor */
    public SysManager() {
    }

	/** minimal constructor */
    public SysManager(Long depOrder) {
        this.depOrder = depOrder;
    }
    
    /** full constructor */
    public SysManager(String email, String tel, String mobile, String fax, String address, Long depOrder, String companyCode) {
        this.email = email;
        this.tel = tel;
        this.mobile = mobile;
        this.fax = fax;
        this.address = address;
        this.depOrder = depOrder;
        this.companyCode = companyCode;
    }
    

   
    // Property accessors
    /**
	 * *
	 * 
	 * @hibernate.id generator-class="assigned" type="java.lang.String" column="USER_CODE"
	 * 
	 */
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="DEPARTMENT_ID"
     *             length="10"
     *         
     */
    public Long getDepartmentId() {
    	return departmentId;
    }

	public void setDepartmentId(Long departmentId) {
    	this.departmentId = departmentId;
    }

	/**       
     *      *            @hibernate.property
     *             column="EMAIL"
     *             length="80"
     *         
     */

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    /**       
     *      *            @hibernate.property
     *             column="TEL"
     *             length="20"
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
     *             column="MOBILE"
     *             length="20"
     *         
     */

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**       
     *      *            @hibernate.property
     *             column="FAX"
     *             length="20"
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
     *             column="ADDRESS"
     *             length="120"
     *         
     */

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEP_ORDER"
     *             length="12"
     *             not-null="true"
     *         
     */

    public Long getDepOrder() {
        return this.depOrder;
    }
    
    public void setDepOrder(Long depOrder) {
        this.depOrder = depOrder;
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
     *             column="COUNTRY_CODE"
     *             length="2"
     *         
     */
    public String getCountryCode() {
    	return countryCode;
    }

	public void setCountryCode(String countryCode) {
    	this.countryCode = countryCode;
    }

	/**
	 * @hibernate.one-to-one cascade="all" constrained="true"
	 */
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("email").append("='").append(getEmail()).append("' ");			
      buffer.append("tel").append("='").append(getTel()).append("' ");			
      buffer.append("mobile").append("='").append(getMobile()).append("' ");			
      buffer.append("fax").append("='").append(getFax()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("depOrder").append("='").append(getDepOrder()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysManager) ) return false;
		 SysManager castOther = ( SysManager ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getEmail()==castOther.getEmail()) || ( this.getEmail()!=null && castOther.getEmail()!=null && this.getEmail().equals(castOther.getEmail()) ) )
 && ( (this.getTel()==castOther.getTel()) || ( this.getTel()!=null && castOther.getTel()!=null && this.getTel().equals(castOther.getTel()) ) )
 && ( (this.getMobile()==castOther.getMobile()) || ( this.getMobile()!=null && castOther.getMobile()!=null && this.getMobile().equals(castOther.getMobile()) ) )
 && ( (this.getFax()==castOther.getFax()) || ( this.getFax()!=null && castOther.getFax()!=null && this.getFax().equals(castOther.getFax()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getDepOrder()==castOther.getDepOrder()) || ( this.getDepOrder()!=null && castOther.getDepOrder()!=null && this.getDepOrder().equals(castOther.getDepOrder()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getEmail() == null ? 0 : this.getEmail().hashCode() );
         result = 37 * result + ( getTel() == null ? 0 : this.getTel().hashCode() );
         result = 37 * result + ( getMobile() == null ? 0 : this.getMobile().hashCode() );
         result = 37 * result + ( getFax() == null ? 0 : this.getFax().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getDepOrder() == null ? 0 : this.getDepOrder().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         return result;
   }   





}
