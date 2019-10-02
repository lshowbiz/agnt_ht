package com.joymain.jecs.pd.model;
// Generated Jul 9, 2014 2:28:49 PM by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_SH_URL"
 *     
 */

public class PdShUrl extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String valueCode;
    private String valueTitle;
    private String exCompanyCode;
    private Date createTime;
    private String createUserCode;
    private String shUrl;
    private String other;


    // Constructors

    /** default constructor */
    public PdShUrl() {
    }

    
    /** full constructor */
    public PdShUrl(String valueCode, String valueTitle, String exCompanyCode, Date createTime, String createUserCode, String shUrl, String other) {
        this.valueCode = valueCode;
        this.valueTitle = valueTitle;
        this.exCompanyCode = exCompanyCode;
        this.createTime = createTime;
        this.createUserCode = createUserCode;
        this.shUrl = shUrl;
        this.other = other;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_PD" 
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="VALUE_CODE"
     *             length="50"
     *         
     */

    public String getValueCode() {
        return this.valueCode;
    }
    
    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="VALUE_TITLE"
     *             length="150"
     *         
     */

    public String getValueTitle() {
        return this.valueTitle;
    }
    
    public void setValueTitle(String valueTitle) {
        this.valueTitle = valueTitle;
    }
    /**       
     *      *            @hibernate.property
     *             column="EX_COMPANY_CODE"
     *             length="200"
     *         
     */

    public String getExCompanyCode() {
        return this.exCompanyCode;
    }
    
    public void setExCompanyCode(String exCompanyCode) {
        this.exCompanyCode = exCompanyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *         
     */

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_USER_CODE"
     *             length="20"
     *         
     */

    public String getCreateUserCode() {
        return this.createUserCode;
    }
    
    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="SH_URL"
     *             length="200"
     *         
     */

    public String getShUrl() {
        return this.shUrl;
    }
    
    public void setShUrl(String shUrl) {
        this.shUrl = shUrl;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER"
     *             length="100"
     *         
     */

    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("valueCode").append("='").append(getValueCode()).append("' ");			
      buffer.append("valueTitle").append("='").append(getValueTitle()).append("' ");			
      buffer.append("exCompanyCode").append("='").append(getExCompanyCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("shUrl").append("='").append(getShUrl()).append("' ");			
      buffer.append("other").append("='").append(getOther()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdShUrl) ) return false;
		 PdShUrl castOther = ( PdShUrl ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getValueCode()==castOther.getValueCode()) || ( this.getValueCode()!=null && castOther.getValueCode()!=null && this.getValueCode().equals(castOther.getValueCode()) ) )
 && ( (this.getValueTitle()==castOther.getValueTitle()) || ( this.getValueTitle()!=null && castOther.getValueTitle()!=null && this.getValueTitle().equals(castOther.getValueTitle()) ) )
 && ( (this.getExCompanyCode()==castOther.getExCompanyCode()) || ( this.getExCompanyCode()!=null && castOther.getExCompanyCode()!=null && this.getExCompanyCode().equals(castOther.getExCompanyCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getShUrl()==castOther.getShUrl()) || ( this.getShUrl()!=null && castOther.getShUrl()!=null && this.getShUrl().equals(castOther.getShUrl()) ) )
 && ( (this.getOther()==castOther.getOther()) || ( this.getOther()!=null && castOther.getOther()!=null && this.getOther().equals(castOther.getOther()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getValueCode() == null ? 0 : this.getValueCode().hashCode() );
         result = 37 * result + ( getValueTitle() == null ? 0 : this.getValueTitle().hashCode() );
         result = 37 * result + ( getExCompanyCode() == null ? 0 : this.getExCompanyCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getShUrl() == null ? 0 : this.getShUrl().hashCode() );
         result = 37 * result + ( getOther() == null ? 0 : this.getOther().hashCode() );
         return result;
   }   





}
