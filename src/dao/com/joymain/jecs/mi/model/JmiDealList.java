package com.joymain.jecs.mi.model;
// Generated 2015-7-31 14:24:28 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_DEAL_LIST"
 *     
 */

public class JmiDealList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Integer inType;
    private String linkNo;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JmiDealList() {
    }

    
    /** full constructor */
    public JmiDealList(String userCode, Integer inType, String linkNo, Date createTime) {
        this.userCode = userCode;
        this.inType = inType;
        this.linkNo = linkNo;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID" 
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
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
     *             column="USER_CODE"
     *             length="20"
     *         
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="IN_TYPE"
     *             length="22"
     *         
     */

    public Integer getInType() {
        return this.inType;
    }
    
    public void setInType(Integer inType) {
        this.inType = inType;
    }
    /**       
     *      *            @hibernate.property
     *             column="LINK_NO"
     *             length="20"
     *         
     */

    public String getLinkNo() {
        return this.linkNo;
    }
    
    public void setLinkNo(String linkNo) {
        this.linkNo = linkNo;
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
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("inType").append("='").append(getInType()).append("' ");			
      buffer.append("linkNo").append("='").append(getLinkNo()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiDealList) ) return false;
		 JmiDealList castOther = ( JmiDealList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getInType()==castOther.getInType()) || ( this.getInType()!=null && castOther.getInType()!=null && this.getInType().equals(castOther.getInType()) ) )
 && ( (this.getLinkNo()==castOther.getLinkNo()) || ( this.getLinkNo()!=null && castOther.getLinkNo()!=null && this.getLinkNo().equals(castOther.getLinkNo()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getInType() == null ? 0 : this.getInType().hashCode() );
         result = 37 * result + ( getLinkNo() == null ? 0 : this.getLinkNo().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
