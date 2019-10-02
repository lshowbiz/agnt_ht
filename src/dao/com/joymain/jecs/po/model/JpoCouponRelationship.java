package com.joymain.jecs.po.model;
// Generated 2017-5-18 17:22:37 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_COUPON_RELATIONSHIP"
 *     
 */

public class JpoCouponRelationship extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Long moId;
    private Long cpId;
    private Date createTime;
    private String createUserCode;
    private String remark;


    // Constructors

    /** default constructor */
    public JpoCouponRelationship() {
    }

    
    /** full constructor */
    public JpoCouponRelationship(Long moId, Long cpId, Date createTime, String createUserCode, String remark) {
        this.moId = moId;
        this.cpId = cpId;
        this.createTime = createTime;
        this.createUserCode = createUserCode;
        this.remark = remark;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PO"
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
     *             column="MO_ID"
     *             length="22"
     *         
     */

    public Long getMoId() {
        return this.moId;
    }
    
    public void setMoId(Long moId) {
        this.moId = moId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CP_ID"
     *             length="22"
     *         
     */

    public Long getCpId() {
        return this.cpId;
    }
    
    public void setCpId(Long cpId) {
        this.cpId = cpId;
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
     *             column="REMARK"
     *             length="1000"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("moId").append("='").append(getMoId()).append("' ");			
      buffer.append("cpId").append("='").append(getCpId()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoCouponRelationship) ) return false;
		 JpoCouponRelationship castOther = ( JpoCouponRelationship ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getMoId()==castOther.getMoId()) || ( this.getMoId()!=null && castOther.getMoId()!=null && this.getMoId().equals(castOther.getMoId()) ) )
 && ( (this.getCpId()==castOther.getCpId()) || ( this.getCpId()!=null && castOther.getCpId()!=null && this.getCpId().equals(castOther.getCpId()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getMoId() == null ? 0 : this.getMoId().hashCode() );
         result = 37 * result + ( getCpId() == null ? 0 : this.getCpId().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }   





}
