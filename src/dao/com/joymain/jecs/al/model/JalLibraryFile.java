package com.joymain.jecs.al.model;
// Generated 2013-8-16 9:29:33 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_LIBRARY_FILE"
 *     
 */

public class JalLibraryFile extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long fileId;
    private String fileName;
    private Long columnId;
    private String columnName;
    private String fileUrl;
    private String fileField1;
    private String fileField2;
    private String fileField3;


    // Constructors

    /** default constructor */
    public JalLibraryFile() {
    }

    
    /** full constructor */
    public JalLibraryFile(String fileName, Long columnId, String fileUrl, String fileField1, String fileField2, String fileField3) {
        this.fileName = fileName;
        this.columnId = columnId;
        this.fileUrl = fileUrl;
        this.fileField1 = fileField1;
        this.fileField2 = fileField2;
        this.fileField3 = fileField3;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="FILE_ID"
     *         
     */

    public Long getFileId() {
        return this.fileId;
    }
    
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
    /**       
     *      *            @hibernate.property
     *             column="FILE_NAME"
     *             length="300"
     *         
     */

    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**       
     *      *            @hibernate.property
     *             column="COLUMN_ID"
     *             length="12"
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
     *             length="50"
     *         
     */
    public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	/**       
     *      *            @hibernate.property
     *             column="FILE_URL"
     *             length="300"
     *         
     */

    public String getFileUrl() {
        return this.fileUrl;
    }
    
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    /**       
     *      *            @hibernate.property
     *             column="FILE_FIELD1"
     *             length="300"
     *         
     */

    public String getFileField1() {
        return this.fileField1;
    }
    
    public void setFileField1(String fileField1) {
        this.fileField1 = fileField1;
    }
    /**       
     *      *            @hibernate.property
     *             column="FILE_FIELD2"
     *             length="300"
     *         
     */

    public String getFileField2() {
        return this.fileField2;
    }
    
    public void setFileField2(String fileField2) {
        this.fileField2 = fileField2;
    }
    /**       
     *      *            @hibernate.property
     *             column="FILE_FIELD3"
     *             length="300"
     *         
     */

    public String getFileField3() {
        return this.fileField3;
    }
    
    public void setFileField3(String fileField3) {
        this.fileField3 = fileField3;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("fileName").append("='").append(getFileName()).append("' ");			
      buffer.append("columnId").append("='").append(getColumnId()).append("' ");			
      buffer.append("fileUrl").append("='").append(getFileUrl()).append("' ");			
      buffer.append("fileField1").append("='").append(getFileField1()).append("' ");			
      buffer.append("fileField2").append("='").append(getFileField2()).append("' ");			
      buffer.append("fileField3").append("='").append(getFileField3()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JalLibraryFile) ) return false;
		 JalLibraryFile castOther = ( JalLibraryFile ) other; 
         
		 return ( (this.getFileId()==castOther.getFileId()) || ( this.getFileId()!=null && castOther.getFileId()!=null && this.getFileId().equals(castOther.getFileId()) ) )
 && ( (this.getFileName()==castOther.getFileName()) || ( this.getFileName()!=null && castOther.getFileName()!=null && this.getFileName().equals(castOther.getFileName()) ) )
 && ( (this.getColumnId()==castOther.getColumnId()) || ( this.getColumnId()!=null && castOther.getColumnId()!=null && this.getColumnId().equals(castOther.getColumnId()) ) )
 && ( (this.getFileUrl()==castOther.getFileUrl()) || ( this.getFileUrl()!=null && castOther.getFileUrl()!=null && this.getFileUrl().equals(castOther.getFileUrl()) ) )
 && ( (this.getFileField1()==castOther.getFileField1()) || ( this.getFileField1()!=null && castOther.getFileField1()!=null && this.getFileField1().equals(castOther.getFileField1()) ) )
 && ( (this.getFileField2()==castOther.getFileField2()) || ( this.getFileField2()!=null && castOther.getFileField2()!=null && this.getFileField2().equals(castOther.getFileField2()) ) )
 && ( (this.getFileField3()==castOther.getFileField3()) || ( this.getFileField3()!=null && castOther.getFileField3()!=null && this.getFileField3().equals(castOther.getFileField3()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getFileId() == null ? 0 : this.getFileId().hashCode() );
         result = 37 * result + ( getFileName() == null ? 0 : this.getFileName().hashCode() );
         result = 37 * result + ( getColumnId() == null ? 0 : this.getColumnId().hashCode() );
         result = 37 * result + ( getFileUrl() == null ? 0 : this.getFileUrl().hashCode() );
         result = 37 * result + ( getFileField1() == null ? 0 : this.getFileField1().hashCode() );
         result = 37 * result + ( getFileField2() == null ? 0 : this.getFileField2().hashCode() );
         result = 37 * result + ( getFileField3() == null ? 0 : this.getFileField3().hashCode() );
         return result;
   }   





}
