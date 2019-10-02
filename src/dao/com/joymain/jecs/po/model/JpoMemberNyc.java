package com.joymain.jecs.po.model;
// Generated 2016-8-30 9:55:20 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_MEMBER_NYC"
 *     
 */

public class JpoMemberNyc extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    public static final String STATUS_LOCK="1";
    public static final String STATUS_UNLOCK="0";

    // Fields    

    private Long id;
    private String yearOfMonth;
    private String memberNo;
    private Date pushAt;
    private String remarks;
    private String status;
    private String oldRemarks;

    // Constructors

    /** default constructor */
    public JpoMemberNyc() {
    }

    
    /** full constructor */
    public JpoMemberNyc(String yearOfMonth, String memberNo, Timestamp pushAt, String remarks, String status) {
        this.yearOfMonth = yearOfMonth;
        this.memberNo = memberNo;
        this.pushAt = pushAt;
        this.remarks = remarks;
        this.status = status;
    }


   
    // Property accessors
    /**
     * * @hibernate.id generator-class="sequence"  type="java.lang.Long"
     * column="ID"
     *
     * @hibernate.generator-param name="sequence" value="SEQ_PO"
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
     *             column="YEAR_OF_MONTH"
     *             length="100"
     *         
     */

    public String getYearOfMonth() {
        return this.yearOfMonth;
    }
    /**
     * @spring.validator type="required"
     */
    public void setYearOfMonth(String yearOfMonth) {
        this.yearOfMonth = yearOfMonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_NO"
     *             length="100"
     *         
     */

    public String getMemberNo() {
        return this.memberNo;
    }
    /**
     * @spring.validator type="required"
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PUSH_AT"
     *             length="11"
     *         
     */

    public Date getPushAt() {
        return this.pushAt;
    }
    /**
     * @spring.validator type="required"
     */
    public void setPushAt(Date pushAt) {
        this.pushAt = pushAt;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARKS"
     *             length="4000"
     *         
     */

    public String getRemarks() {
        return this.remarks;
    }
    /**
     * @spring.validator type="required"
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="100"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    /**
     * @spring.validator type="required"
     */
    public void setStatus(String status) {
        this.status = status;
    }


    public String getOldRemarks() {
        return oldRemarks;
    }

    public void setOldRemarks(String oldRemarks) {
        this.oldRemarks = oldRemarks;
    }

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("yearOfMonth").append("='").append(getYearOfMonth()).append("' ");			
      buffer.append("memberNo").append("='").append(getMemberNo()).append("' ");			
      buffer.append("pushAt").append("='").append(getPushAt()).append("' ");			
      buffer.append("remarks").append("='").append(getRemarks()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoMemberNyc) ) return false;
		 JpoMemberNyc castOther = ( JpoMemberNyc ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getYearOfMonth()==castOther.getYearOfMonth()) || ( this.getYearOfMonth()!=null && castOther.getYearOfMonth()!=null && this.getYearOfMonth().equals(castOther.getYearOfMonth()) ) )
 && ( (this.getMemberNo()==castOther.getMemberNo()) || ( this.getMemberNo()!=null && castOther.getMemberNo()!=null && this.getMemberNo().equals(castOther.getMemberNo()) ) )
 && ( (this.getPushAt()==castOther.getPushAt()) || ( this.getPushAt()!=null && castOther.getPushAt()!=null && this.getPushAt().equals(castOther.getPushAt()) ) )
 && ( (this.getRemarks()==castOther.getRemarks()) || ( this.getRemarks()!=null && castOther.getRemarks()!=null && this.getRemarks().equals(castOther.getRemarks()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getYearOfMonth() == null ? 0 : this.getYearOfMonth().hashCode() );
         result = 37 * result + ( getMemberNo() == null ? 0 : this.getMemberNo().hashCode() );
         result = 37 * result + ( getPushAt() == null ? 0 : this.getPushAt().hashCode() );
         result = 37 * result + ( getRemarks() == null ? 0 : this.getRemarks().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         return result;
   }   





}
