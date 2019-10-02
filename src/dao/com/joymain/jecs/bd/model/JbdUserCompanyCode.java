package com.joymain.jecs.bd.model;
// Generated 2010-1-4 15:33:08 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_USER_COMPANY_CODE"
 *     
 */

public class JbdUserCompanyCode extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Integer wweek;
    private String oldCompany;
    private String newCompany;
    private String updateType;


    // Constructors

    /** default constructor */
    public JbdUserCompanyCode() {
    }

    
    /** full constructor */
    public JbdUserCompanyCode(String userCode, Integer wweek, String oldCompany, String newCompany, String updateType) {
        this.userCode = userCode;
        this.wweek = wweek;
        this.oldCompany = oldCompany;
        this.newCompany = newCompany;
        this.updateType = updateType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
     *         
     */

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
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
     *             column="W_WEEK"
     *             length="22"
     *         
     */
    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
    }
    /**       
     *      *            @hibernate.property
     *             column="OLD_COMPANY"
     *             length="2"
     *         
     */

    public String getOldCompany() {
        return this.oldCompany;
    }
    
    public void setOldCompany(String oldCompany) {
        this.oldCompany = oldCompany;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_COMPANY"
     *             length="2"
     *         
     */

    public String getNewCompany() {
        return this.newCompany;
    }
    
    public void setNewCompany(String newCompany) {
        this.newCompany = newCompany;
    }
    /**       
     *      *            @hibernate.property
     *             column="UPDATE_TYPE"
     *             length="1"
     *         
     */

    public String getUpdateType() {
        return this.updateType;
    }
    
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("oldCompany").append("='").append(getOldCompany()).append("' ");			
      buffer.append("newCompany").append("='").append(getNewCompany()).append("' ");			
      buffer.append("updateType").append("='").append(getUpdateType()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdUserCompanyCode) ) return false;
		 JbdUserCompanyCode castOther = ( JbdUserCompanyCode ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getOldCompany()==castOther.getOldCompany()) || ( this.getOldCompany()!=null && castOther.getOldCompany()!=null && this.getOldCompany().equals(castOther.getOldCompany()) ) )
 && ( (this.getNewCompany()==castOther.getNewCompany()) || ( this.getNewCompany()!=null && castOther.getNewCompany()!=null && this.getNewCompany().equals(castOther.getNewCompany()) ) )
 && ( (this.getUpdateType()==castOther.getUpdateType()) || ( this.getUpdateType()!=null && castOther.getUpdateType()!=null && this.getUpdateType().equals(castOther.getUpdateType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getOldCompany() == null ? 0 : this.getOldCompany().hashCode() );
         result = 37 * result + ( getNewCompany() == null ? 0 : this.getNewCompany().hashCode() );
         result = 37 * result + ( getUpdateType() == null ? 0 : this.getUpdateType().hashCode() );
         return result;
   }   





}
