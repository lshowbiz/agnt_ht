package com.joymain.jecs.pm.model;
// Generated 2017-5-18 17:17:25 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_COUPON_RELATIONSHIP"
 *     
 */

public class JpmCouponRelationship extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Long cpId;
    private Long uniNo;
    private Date createTime;
    private String createUserCode;

    private JpmProductSaleNew jpmProductSaleNew = new JpmProductSaleNew();
    private JpmCouponInfo jpmCouponInfo = new JpmCouponInfo();
    // Constructors

    /** default constructor */
    public JpmCouponRelationship() {
    }

    
    /** full constructor */
    public JpmCouponRelationship(Long cpId, Long uniNo, Date createTime, String createUserCode) {
        this.cpId = cpId;
        this.uniNo = uniNo;
        this.createTime = createTime;
        this.createUserCode = createUserCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_PM"
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
     *             column="UNI_NO"
     *             length="22"
     *         
     */

    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("cpId").append("='").append(getCpId()).append("' ");			
      buffer.append("uniNo").append("='").append(getUniNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("createUserCode").append("='").append(getCreateUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmCouponRelationship) ) return false;
		 JpmCouponRelationship castOther = ( JpmCouponRelationship ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getCpId()==castOther.getCpId()) || ( this.getCpId()!=null && castOther.getCpId()!=null && this.getCpId().equals(castOther.getCpId()) ) )
 && ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getCreateUserCode()==castOther.getCreateUserCode()) || ( this.getCreateUserCode()!=null && castOther.getCreateUserCode()!=null && this.getCreateUserCode().equals(castOther.getCreateUserCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getCpId() == null ? 0 : this.getCpId().hashCode() );
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getCreateUserCode() == null ? 0 : this.getCreateUserCode().hashCode() );
         return result;
   }

   /**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" insert="false" update="false" 
	 * @hibernate.column name="UNI_NO"
	 * 
	 */
	public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}
	
	
	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}

	/**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" insert="false" update="false" 
	 * @hibernate.column name="CP_ID"
	 * 
	 */
	public JpmCouponInfo getJpmCouponInfo() {
		return jpmCouponInfo;
	}


	public void setJpmCouponInfo(JpmCouponInfo jpmCouponInfo) {
		this.jpmCouponInfo = jpmCouponInfo;
	}
}
