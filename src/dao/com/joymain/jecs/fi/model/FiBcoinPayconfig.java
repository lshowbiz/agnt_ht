package com.joymain.jecs.fi.model;
// Generated 2014-3-31 15:58:01 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_BCOIN_PAYCONFIG"
 *     
 */

public class FiBcoinPayconfig extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long configId;
    private String startTime;
    private String endTime;
    private String title;
    private String limit;//规则： 0:不限, 1：限制部分产品不参加，2：开放部分产品参加
    private String createCode;
    private String createName;
    private Date createTime;
    private String proportion;//换购比例


    // Constructors

    /** default constructor */
    public FiBcoinPayconfig() {
    }

	/** minimal constructor */
    public FiBcoinPayconfig(String startTime, String endTime, String title, String limit, String proportion) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.limit = limit;
        this.proportion = proportion;
    }
    
    /** full constructor */
    public FiBcoinPayconfig(String startTime, String endTime, String title, String limit, String createCode, String createName, Date createTime, String proportion) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.limit = limit;
        this.createCode = createCode;
        this.createName = createName;
        this.createTime = createTime;
        this.proportion = proportion;
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
     *             column="START_TIME"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="END_TIME"
     *             length="20"
     *             not-null="true"
     *         
     */

    public String getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="TITLE"
     *             length="100"
     *             not-null="true"
     *         
     */

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    /**       
     *      *            @hibernate.property
     *             column="LIMIT"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getLimit() {
        return this.limit;
    }
    
    public void setLimit(String limit) {
        this.limit = limit;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_CODE"
     *             length="20"
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
     *             length="20"
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
     *      *            @hibernate.property
     *             column="PROPORTION"
     *             length="1"
     *             not-null="true"
     *         
     */

    public String getProportion() {
        return this.proportion;
    }
    
    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("startTime").append("='").append(getStartTime()).append("' ");			
      buffer.append("endTime").append("='").append(getEndTime()).append("' ");			
      buffer.append("title").append("='").append(getTitle()).append("' ");			
      buffer.append("limit").append("='").append(getLimit()).append("' ");			
      buffer.append("createCode").append("='").append(getCreateCode()).append("' ");			
      buffer.append("createName").append("='").append(getCreateName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("proportion").append("='").append(getProportion()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FiBcoinPayconfig) ) return false;
		 FiBcoinPayconfig castOther = ( FiBcoinPayconfig ) other; 
         
		 return ( (this.getConfigId()==castOther.getConfigId()) || ( this.getConfigId()!=null && castOther.getConfigId()!=null && this.getConfigId().equals(castOther.getConfigId()) ) )
 && ( (this.getStartTime()==castOther.getStartTime()) || ( this.getStartTime()!=null && castOther.getStartTime()!=null && this.getStartTime().equals(castOther.getStartTime()) ) )
 && ( (this.getEndTime()==castOther.getEndTime()) || ( this.getEndTime()!=null && castOther.getEndTime()!=null && this.getEndTime().equals(castOther.getEndTime()) ) )
 && ( (this.getTitle()==castOther.getTitle()) || ( this.getTitle()!=null && castOther.getTitle()!=null && this.getTitle().equals(castOther.getTitle()) ) )
 && ( (this.getLimit()==castOther.getLimit()) || ( this.getLimit()!=null && castOther.getLimit()!=null && this.getLimit().equals(castOther.getLimit()) ) )
 && ( (this.getCreateCode()==castOther.getCreateCode()) || ( this.getCreateCode()!=null && castOther.getCreateCode()!=null && this.getCreateCode().equals(castOther.getCreateCode()) ) )
 && ( (this.getCreateName()==castOther.getCreateName()) || ( this.getCreateName()!=null && castOther.getCreateName()!=null && this.getCreateName().equals(castOther.getCreateName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getProportion()==castOther.getProportion()) || ( this.getProportion()!=null && castOther.getProportion()!=null && this.getProportion().equals(castOther.getProportion()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getConfigId() == null ? 0 : this.getConfigId().hashCode() );
         result = 37 * result + ( getStartTime() == null ? 0 : this.getStartTime().hashCode() );
         result = 37 * result + ( getEndTime() == null ? 0 : this.getEndTime().hashCode() );
         result = 37 * result + ( getTitle() == null ? 0 : this.getTitle().hashCode() );
         result = 37 * result + ( getLimit() == null ? 0 : this.getLimit().hashCode() );
         result = 37 * result + ( getCreateCode() == null ? 0 : this.getCreateCode().hashCode() );
         result = 37 * result + ( getCreateName() == null ? 0 : this.getCreateName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getProportion() == null ? 0 : this.getProportion().hashCode() );
         return result;
   }   





}
