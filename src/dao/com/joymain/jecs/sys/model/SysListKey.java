package com.joymain.jecs.sys.model;

import java.util.HashSet;
import java.util.Set;
// Generated 2008-3-17 12:00:41 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_LIST_KEY"
 *     
 */

public class SysListKey extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long keyId;
    private String listCode;
    private String listName;
    private Integer isReadOnly;
    private String remark;
    private Set sysListValues = new HashSet(0);

    // Constructors

    /** default constructor */
    public SysListKey() {
    }

	/** minimal constructor */
    public SysListKey(String listCode, String listName) {
        this.listCode = listCode;
        this.listName = listName;
    }
    
    /** full constructor */
    public SysListKey(String listCode, String listName, Integer isReadOnly, String remark) {
        this.listCode = listCode;
        this.listName = listName;
        this.isReadOnly = isReadOnly;
        this.remark = remark;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="KEY_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getKeyId() {
        return this.keyId;
    }
    
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }
    /**       
     *      *            @hibernate.property
     *             column="LIST_CODE"
     *             length="50"
     *             not-null="true"
     *         
     */

    public String getListCode() {
        return this.listCode;
    }
    
    public void setListCode(String listCode) {
        this.listCode = listCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LIST_NAME"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getListName() {
        return this.listName;
    }
    
    public void setListName(String listName) {
        this.listName = listName;
    }
    /**       
     *      *            @hibernate.property
     *             column="IS_READ_ONLY"
     *             length="2"
     *         
     */

    public Integer getIsReadOnly() {
        return this.isReadOnly;
    }
    
    public void setIsReadOnly(Integer isReadOnly) {
        this.isReadOnly = isReadOnly;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="500"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   
    /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all" order-by="ORDER_NO"
	 * @hibernate.collection-key column="KEY_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.sys.model.SysListValue"
	 * 
	 */
    public Set getSysListValues() {
    	return sysListValues;
    }

	public void setSysListValues(Set sysListValues) {
    	this.sysListValues = sysListValues;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("listCode").append("='").append(getListCode()).append("' ");			
      buffer.append("listName").append("='").append(getListName()).append("' ");			
      buffer.append("isReadOnly").append("='").append(getIsReadOnly()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysListKey) ) return false;
		 SysListKey castOther = ( SysListKey ) other; 
         
		 return ( (this.getKeyId()==castOther.getKeyId()) || ( this.getKeyId()!=null && castOther.getKeyId()!=null && this.getKeyId().equals(castOther.getKeyId()) ) )
 && ( (this.getListCode()==castOther.getListCode()) || ( this.getListCode()!=null && castOther.getListCode()!=null && this.getListCode().equals(castOther.getListCode()) ) )
 && ( (this.getListName()==castOther.getListName()) || ( this.getListName()!=null && castOther.getListName()!=null && this.getListName().equals(castOther.getListName()) ) )
 && ( (this.getIsReadOnly()==castOther.getIsReadOnly()) || ( this.getIsReadOnly()!=null && castOther.getIsReadOnly()!=null && this.getIsReadOnly().equals(castOther.getIsReadOnly()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getKeyId() == null ? 0 : this.getKeyId().hashCode() );
         result = 37 * result + ( getListCode() == null ? 0 : this.getListCode().hashCode() );
         result = 37 * result + ( getListName() == null ? 0 : this.getListName().hashCode() );
         result = 37 * result + ( getIsReadOnly() == null ? 0 : this.getIsReadOnly().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   





}
