package com.joymain.jecs.am.model;
// Generated 2010-9-7 10:42:02 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAM_DOWNLOAD"
 *     
 */

public class JamDownload extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long dlId;
    private String fileType;
    private String fileName;
    private String fileLink;
    private String fileDesc;
    private String status;
    private String companyCode;
    private String createNo;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JamDownload() {
    }

    
    /** full constructor */
    public JamDownload(String fileType, String fileName, String fileLink, String fileDesc, String status, String companyCode, String createNo, Date createTime) {
        this.fileType = fileType;
        this.fileName = fileName;
        this.fileLink = fileLink;
        this.fileDesc = fileDesc;
        this.status = status;
        this.companyCode = companyCode;
        this.createNo = createNo;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="DL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AM"
     *         
     */

    public Long getDlId() {
        return this.dlId;
    }
    
    public void setDlId(Long dlId) {
        this.dlId = dlId;
    }
    /**       
     *      *            @hibernate.property
     *             column="FILE_TYPE"
     *             length="20"
     *         
     */

    public String getFileType() {
        return this.fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    /**       
     *      *            @hibernate.property
     *             column="FILE_NAME"
     *             length="100"
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
     *             column="FILE_LINK"
     *             length="200"
     *         
     */

    public String getFileLink() {
        return this.fileLink;
    }
    
    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
    /**       
     *      *            @hibernate.property
     *             column="FILE_DESC"
     *             length="500"
     *         
     */

    public String getFileDesc() {
        return this.fileDesc;
    }
    
    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="1"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
     *             column="CREATE_NO"
     *             length="20"
     *         
     */

    public String getCreateNo() {
        return this.createNo;
    }
    
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
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
      buffer.append("fileType").append("='").append(getFileType()).append("' ");			
      buffer.append("fileName").append("='").append(getFileName()).append("' ");			
      buffer.append("fileLink").append("='").append(getFileLink()).append("' ");			
      buffer.append("fileDesc").append("='").append(getFileDesc()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JamDownload) ) return false;
		 JamDownload castOther = ( JamDownload ) other; 
         
		 return ( (this.getDlId()==castOther.getDlId()) || ( this.getDlId()!=null && castOther.getDlId()!=null && this.getDlId().equals(castOther.getDlId()) ) )
 && ( (this.getFileType()==castOther.getFileType()) || ( this.getFileType()!=null && castOther.getFileType()!=null && this.getFileType().equals(castOther.getFileType()) ) )
 && ( (this.getFileName()==castOther.getFileName()) || ( this.getFileName()!=null && castOther.getFileName()!=null && this.getFileName().equals(castOther.getFileName()) ) )
 && ( (this.getFileLink()==castOther.getFileLink()) || ( this.getFileLink()!=null && castOther.getFileLink()!=null && this.getFileLink().equals(castOther.getFileLink()) ) )
 && ( (this.getFileDesc()==castOther.getFileDesc()) || ( this.getFileDesc()!=null && castOther.getFileDesc()!=null && this.getFileDesc().equals(castOther.getFileDesc()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDlId() == null ? 0 : this.getDlId().hashCode() );
         result = 37 * result + ( getFileType() == null ? 0 : this.getFileType().hashCode() );
         result = 37 * result + ( getFileName() == null ? 0 : this.getFileName().hashCode() );
         result = 37 * result + ( getFileLink() == null ? 0 : this.getFileLink().hashCode() );
         result = 37 * result + ( getFileDesc() == null ? 0 : this.getFileDesc().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
