package com.joymain.jecs.fi.model;
// Generated 2014-1-10 17:43:49 by Hibernate Tools 3.1.0.beta4

import java.util.Date;
/**
 * 积分换购活动配置
 */

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_COIN_CONFIG"
 *     
 */

public class FiCoinConfig extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long configId;
    private String configName;
    private Date startTime;
    private Date endTime;
    private String memberTopCode; //限制参与的团队
    private String description;
    private String field1;
    private String createCode;
    private String createName;
    private Date createTime;


    // Constructors

    /** default constructor */
    public FiCoinConfig() {
    }

	/** minimal constructor */
    public FiCoinConfig(String configName, Date startTime, Date endTime) {
        this.configName = configName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    /** full constructor */
    public FiCoinConfig(String configName, Date startTime, Date endTime, String memberTopCode, String description, String field1, String createCode, String createName, Date createTime) {
        this.configName = configName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.memberTopCode = memberTopCode;
        this.description = description;
        this.field1 = field1;
        this.createCode = createCode;
        this.createName = createName;
        this.createTime = createTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="CONFIG_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BANKBOOK"
     *         
     */

    public Long getConfigId() {
        return this.configId;
    }
    
    public void setConfigId(Long configId) {
        this.configId = configId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONFIG_NAME"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getConfigName() {
        return this.configName;
    }
    
    public void setConfigName(String configName) {
        this.configName = configName;
    }
    /**       
     *      *            @hibernate.property
     *             column="START_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="END_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_TOP_CODE"
     *             length="300"
     *         
     */

    public String getMemberTopCode() {
        return this.memberTopCode;
    }
    
    public void setMemberTopCode(String memberTopCode) {
        this.memberTopCode = memberTopCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="DESCRIPTION"
     *             length="1000"
     *         
     */

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    /**       
     *      *            @hibernate.property
     *             column="FIELD1"
     *             length="20"
     *         
     */

    public String getField1() {
        return this.field1;
    }
    
    public void setField1(String field1) {
        this.field1 = field1;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_CODE"
     *             length="30"
     *         
     */

    public String getCreateCode() {
        return this.createCode;
    }
    
    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_NAME"
     *             length="30"
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
      buffer.append("configName").append("='").append(getConfigName()).append("' ");			
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("memberTopCode").append("='").append(getMemberTopCode()).append("' ");			
      buffer.append("description").append("='").append(getDescription()).append("' ");			
      buffer.append("field1").append("='").append(getField1()).append("' ");			
      buffer.append("createCode").append("='").append(getCreateCode()).append("' ");			
      buffer.append("createName").append("='").append(getCreateName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiCoinConfig) ) return false;
		 FiCoinConfig castOther = ( FiCoinConfig ) other; 
         
		 return ( (this.getConfigId()==castOther.getConfigId()) || ( this.getConfigId()!=null && castOther.getConfigId()!=null && this.getConfigId().equals(castOther.getConfigId()) ) )
 && ( (this.getConfigName()==castOther.getConfigName()) || ( this.getConfigName()!=null && castOther.getConfigName()!=null && this.getConfigName().equals(castOther.getConfigName()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) )
 && ( (this.getMemberTopCode()==castOther.getMemberTopCode()) || ( this.getMemberTopCode()!=null && castOther.getMemberTopCode()!=null && this.getMemberTopCode().equals(castOther.getMemberTopCode()) ) )
 && ( (this.getDescription()==castOther.getDescription()) || ( this.getDescription()!=null && castOther.getDescription()!=null && this.getDescription().equals(castOther.getDescription()) ) )
 && ( (this.getField1()==castOther.getField1()) || ( this.getField1()!=null && castOther.getField1()!=null && this.getField1().equals(castOther.getField1()) ) )
 && ( (this.getCreateCode()==castOther.getCreateCode()) || ( this.getCreateCode()!=null && castOther.getCreateCode()!=null && this.getCreateCode().equals(castOther.getCreateCode()) ) )
 && ( (this.getCreateName()==castOther.getCreateName()) || ( this.getCreateName()!=null && castOther.getCreateName()!=null && this.getCreateName().equals(castOther.getCreateName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getConfigId() == null ? 0 : this.getConfigId().hashCode() );
         result = 37 * result + ( getConfigName() == null ? 0 : this.getConfigName().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getMemberTopCode() == null ? 0 : this.getMemberTopCode().hashCode() );
         result = 37 * result + ( getDescription() == null ? 0 : this.getDescription().hashCode() );
         result = 37 * result + ( getField1() == null ? 0 : this.getField1().hashCode() );
         result = 37 * result + ( getCreateCode() == null ? 0 : this.getCreateCode().hashCode() );
         result = 37 * result + ( getCreateName() == null ? 0 : this.getCreateName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         return result;
   }   





}
