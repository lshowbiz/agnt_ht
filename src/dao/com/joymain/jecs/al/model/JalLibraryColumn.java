package com.joymain.jecs.al.model;
// Generated 2013-8-16 9:29:05 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_LIBRARY_COLUMN"
 *     
 */

public class JalLibraryColumn extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long columnId;
    private String columnName;
    private long plateId;
    private String plateName;
    private String columnImgs;
    private String columnField1;
    private Integer status;
    private String createUserCode;
    private String createName;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JalLibraryColumn() {
    }

	/** minimal constructor */
    public JalLibraryColumn(String columnName, long plateId) {
        this.columnName = columnName;
        this.plateId = plateId;
    }
    
    /** full constructor */
    public JalLibraryColumn(String columnName, long plateId, String columnImgs, String columnField1, Integer status, String createUserCode, String createName, Date createTime) {
        this.columnName = columnName;
        this.plateId = plateId;
        this.columnImgs = columnImgs;
        this.columnField1 = columnField1;
        this.status = status;
        this.createUserCode = createUserCode;
        this.createName = createName;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="COLUMN_ID"
     *         
     */

    public Long getColumnId() {
        return this.columnId;
    }
    
    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }
    /**       
     *      *            @hibernate.property
     *             column="COLUMN_NAME"
     *             length="300"
     *             not-null="true"
     *         
     */

    public String getColumnName() {
        return this.columnName;
    }
    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    /**       
     *      *            @hibernate.property
     *             column="PLATE_ID"
     *             length="12"
     *             not-null="true"
     *         
     */

    public long getPlateId() {
        return this.plateId;
    }
    
    public void setPlateId(long plateId) {
        this.plateId = plateId;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="PLATE_NAME"
     *             length="300"
     *         
     */
    public String getPlateName() {
		return plateName;
	}

	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}
	
    /**       
     *      *            @hibernate.property
     *             column="COLUMN_IMGS"
     *             length="300"
     *         
     */

    public String getColumnImgs() {
        return this.columnImgs;
    }

	public void setColumnImgs(String columnImgs) {
        this.columnImgs = columnImgs;
    }
    /**       
     *      *            @hibernate.property
     *             column="COLUMN_FIELD1"
     *             length="300"
     *         
     */

    public String getColumnField1() {
        return this.columnField1;
    }
    
    public void setColumnField1(String columnField1) {
        this.columnField1 = columnField1;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="2"
     *         
     */

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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
     *             column="CREATE_NAME"
     *             length="300"
     *         
     */

    public String getCreateName() {
        return this.createName;
    }
    
    public void setCreateName(String createName) {
        this.createName = createName;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("columnName").append("='").append(getColumnName()).append("' ");			
      buffer.append("plateId").append("='").append(getPlateId()).append("' ");			
      buffer.append("columnImgs").append("='").append(getColumnImgs()).append("' ");			
      buffer.append("columnField1").append("='").append(getColumnField1()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("createName").append("='").append(getCreateName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JalLibraryColumn) ) return false;
		 JalLibraryColumn castOther = ( JalLibraryColumn ) other; 
         
		 return ( (this.getColumnId()==castOther.getColumnId()) || ( this.getColumnId()!=null && castOther.getColumnId()!=null && this.getColumnId().equals(castOther.getColumnId()) ) )
 && ( (this.getColumnName()==castOther.getColumnName()) || ( this.getColumnName()!=null && castOther.getColumnName()!=null && this.getColumnName().equals(castOther.getColumnName()) ) )
 && (this.getPlateId()==castOther.getPlateId())
 && ( (this.getColumnImgs()==castOther.getColumnImgs()) || ( this.getColumnImgs()!=null && castOther.getColumnImgs()!=null && this.getColumnImgs().equals(castOther.getColumnImgs()) ) )
 && ( (this.getColumnField1()==castOther.getColumnField1()) || ( this.getColumnField1()!=null && castOther.getColumnField1()!=null && this.getColumnField1().equals(castOther.getColumnField1()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getCreateName()==castOther.getCreateName()) || ( this.getCreateName()!=null && castOther.getCreateName()!=null && this.getCreateName().equals(castOther.getCreateName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getColumnId() == null ? 0 : this.getColumnId().hashCode() );
         result = 37 * result + ( getColumnName() == null ? 0 : this.getColumnName().hashCode() );
         result = 37 * result + (int) this.getPlateId();
         result = 37 * result + ( getColumnImgs() == null ? 0 : this.getColumnImgs().hashCode() );
         result = 37 * result + ( getColumnField1() == null ? 0 : this.getColumnField1().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getCreateName() == null ? 0 : this.getCreateName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
