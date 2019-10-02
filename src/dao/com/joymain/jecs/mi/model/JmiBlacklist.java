package com.joymain.jecs.mi.model;
// Generated 2010-9-7 11:27:13 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_BLACKLIST"
 *     
 */

public class JmiBlacklist extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long blId;
    private String papertype;
    private String papernumber;
    private String blackType;
    private String companyCode;
    private String createNo;
    private Date createTime;
    private String remark;
    private String userCode;
    private String userName;
    private String phone;
	private String status;
    
    

    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *         
     */
    public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

    // Constructors

    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *         
     */
    public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}



    /**       
     *      *            @hibernate.property
     *             column="USER_NAME"
     *         
     */
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


    /**       
     *      *            @hibernate.property
     *             column="PHONE"
     *         
     */
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	/** default constructor */
    public JmiBlacklist() {
    }

    
    /** full constructor */
    public JmiBlacklist(String papertype, String papernumber, String blackType, String companyCode, String createNo, Date createTime) {
        this.papertype = papertype;
        this.papernumber = papernumber;
        this.blackType = blackType;
        this.companyCode = companyCode;
        this.createNo = createNo;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="BL_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
     */

    public Long getBlId() {
        return this.blId;
    }
    
    public void setBlId(Long blId) {
        this.blId = blId;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAPERTYPE"
     *             length="20"
     *         
     */

    public String getPapertype() {
        return this.papertype;
    }
    
    public void setPapertype(String papertype) {
        this.papertype = papertype;
    }
    /**       
     *      *            @hibernate.property
     *             column="PAPERNUMBER"
     *             length="100"
     *         
     */

    public String getPapernumber() {
        return this.papernumber;
    }
    
    public void setPapernumber(String papernumber) {
        this.papernumber = papernumber;
    }
    /**       
     *      *            @hibernate.property
     *             column="BLACK_TYPE"
     *             length="20"
     *         
     */

    public String getBlackType() {
        return this.blackType;
    }
    
    public void setBlackType(String blackType) {
        this.blackType = blackType;
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
      buffer.append("papertype").append("='").append(getPapertype()).append("' ");			
      buffer.append("papernumber").append("='").append(getPapernumber()).append("' ");			
      buffer.append("blackType").append("='").append(getBlackType()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("createNo").append("='").append(getCreateNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiBlacklist) ) return false;
		 JmiBlacklist castOther = ( JmiBlacklist ) other; 
         
		 return ( (this.getBlId()==castOther.getBlId()) || ( this.getBlId()!=null && castOther.getBlId()!=null && this.getBlId().equals(castOther.getBlId()) ) )
 && ( (this.getPapertype()==castOther.getPapertype()) || ( this.getPapertype()!=null && castOther.getPapertype()!=null && this.getPapertype().equals(castOther.getPapertype()) ) )
 && ( (this.getPapernumber()==castOther.getPapernumber()) || ( this.getPapernumber()!=null && castOther.getPapernumber()!=null && this.getPapernumber().equals(castOther.getPapernumber()) ) )
 && ( (this.getBlackType()==castOther.getBlackType()) || ( this.getBlackType()!=null && castOther.getBlackType()!=null && this.getBlackType().equals(castOther.getBlackType()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getCreateNo()==castOther.getCreateNo()) || ( this.getCreateNo()!=null && castOther.getCreateNo()!=null && this.getCreateNo().equals(castOther.getCreateNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBlId() == null ? 0 : this.getBlId().hashCode() );
         result = 37 * result + ( getPapertype() == null ? 0 : this.getPapertype().hashCode() );
         result = 37 * result + ( getPapernumber() == null ? 0 : this.getPapernumber().hashCode() );
         result = 37 * result + ( getBlackType() == null ? 0 : this.getBlackType().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getCreateNo() == null ? 0 : this.getCreateNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }


   /**       
    *      *            @hibernate.property
    *             column="REMARK"
    *             length="500"
    *         
    */
public String getRemark() {
	return remark;
}


public void setRemark(String remark) {
	this.remark = remark;
}   





}
