package com.joymain.jecs.am.model;
// Generated 2008-9-5 17:00:20 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="AM_REGULAR_MSG"
 *     
 */

public class AmRegularMsg extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String uniNo;
    private String content;
    private String companyCode;
    private String addNo;
    private Date addTime;
    private Date modifyTime;


    // Constructors

    /** default constructor */
    public AmRegularMsg() {
    }

    
    /** full constructor */
    public AmRegularMsg(String uniNo, String content, String addNo, Date addTime, Date modifyTime, String companyCode) {
        this.uniNo = uniNo;
        this.content = content;
        this.addNo = addNo;
        this.addTime = addTime;
        this.modifyTime = modifyTime;
        this.companyCode = companyCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *         
     *                
     *                 column="UNI_NO"
     *             @hibernate.generator-param name="sequence" value="SEQ_AM" 
     */

    public String getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(String uniNo) {
        this.uniNo = uniNo;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="CONTENT"
     *             	not-null="true"
     */

    public String getContent() {
        return this.content;
    }
    /**
     * @spring.validator type="required"
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMPANY_CODE"
     *             length="2"
     *             
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="ADD_NO"
     *             
     */

    public String getAddNo() {
        return this.addNo;
    }
    
    public void setAddNo(String addNo) {
        this.addNo = addNo;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="ADD_TIME"
     *             
     */

    public Date getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="MODIFY_TIME"
     *             
     */

    public Date getModifyTime() {
        return this.modifyTime;
    }
    
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }


	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("uniNo", this.uniNo).append(
				"content", this.content).append(
						"companyCode", this.companyCode).append("addTime", this.addTime)
				.append("addNo", this.addNo).append("modifyTime",
						this.modifyTime).toString();
	}


	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
//		if (!super.equals(obj))
//			return false;
		if (getClass() != obj.getClass())
			return false;
		final AmRegularMsg other = (AmRegularMsg) obj;
		if (addNo == null) {
			if (other.addNo != null)
				return false;
		} else if (!addNo.equals(other.addNo))
			return false;
		if (companyCode == null) {
			if (other.companyCode != null)
				return false;
		} else if (!companyCode.equals(other.companyCode))
			return false;
		if (addTime == null) {
			if (other.addTime != null)
				return false;
		} else if (!addTime.equals(other.addTime))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (modifyTime == null) {
			if (other.modifyTime != null)
				return false;
		} else if (!modifyTime.equals(other.modifyTime))
			return false;
		if (uniNo == null) {
			if (other.uniNo != null)
				return false;
		} else if (!uniNo.equals(other.uniNo))
			return false;
		return true;
	}


	
	public int hashCode() {
		final int prime = 31;
		int result = 31;
		result = prime * result + ((addNo == null) ? 0 : addNo.hashCode());
		result = prime * result + ((companyCode == null) ? 0 : companyCode.hashCode());
		result = prime * result + ((addTime == null) ? 0 : addTime.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((modifyTime == null) ? 0 : modifyTime.hashCode());
		result = prime * result + ((uniNo == null) ? 0 : uniNo.hashCode());
		return result;
	}
   








}
