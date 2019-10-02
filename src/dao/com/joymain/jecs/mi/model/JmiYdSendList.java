package com.joymain.jecs.mi.model;
// Generated 2019-4-29 14:38:32 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_YD_SEND_LIST"
 *     
 */

public class JmiYdSendList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private String recommendNo;
    private String sourceCode;
    private Date createTime;
    private Date sendTime;
    private BigDecimal sendNum;


    // Constructors

    /** default constructor */
    public JmiYdSendList() {
    }

    
    /** full constructor */
    public JmiYdSendList(String userCode, String recommendNo, String sourceCode, Date createTime, Date sendTime, BigDecimal sendNum) {
        this.userCode = userCode;
        this.recommendNo = recommendNo;
        this.sourceCode = sourceCode;
        this.createTime = createTime;
        this.sendTime = sendTime;
        this.sendNum = sendNum;
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
     *             column="RECOMMEND_NO"
     *             length="20"
     *         
     */

    public String getRecommendNo() {
        return this.recommendNo;
    }
    
    public void setRecommendNo(String recommendNo) {
        this.recommendNo = recommendNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SOURCE_CODE"
     *             length="2"
     *         
     */

    public String getSourceCode() {
        return this.sourceCode;
    }
    
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
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
     *             column="SEND_TIME"
     *             length="7"
     *         
     */

    public Date getSendTime() {
        return this.sendTime;
    }
    
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getSendNum() {
        return this.sendNum;
    }
    
    public void setSendNum(BigDecimal sendNum) {
        this.sendNum = sendNum;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("recommendNo").append("='").append(getRecommendNo()).append("' ");			
      buffer.append("sourceCode").append("='").append(getSourceCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("sendTime").append("='").append(getSendTime()).append("' ");			
      buffer.append("sendNum").append("='").append(getSendNum()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiYdSendList) ) return false;
		 JmiYdSendList castOther = ( JmiYdSendList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getRecommendNo()==castOther.getRecommendNo()) || ( this.getRecommendNo()!=null && castOther.getRecommendNo()!=null && this.getRecommendNo().equals(castOther.getRecommendNo()) ) )
 && ( (this.getSourceCode()==castOther.getSourceCode()) || ( this.getSourceCode()!=null && castOther.getSourceCode()!=null && this.getSourceCode().equals(castOther.getSourceCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getSendTime()==castOther.getSendTime()) || ( this.getSendTime()!=null && castOther.getSendTime()!=null && this.getSendTime().equals(castOther.getSendTime()) ) )
 && ( (this.getSendNum()==castOther.getSendNum()) || ( this.getSendNum()!=null && castOther.getSendNum()!=null && this.getSendNum().equals(castOther.getSendNum()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getRecommendNo() == null ? 0 : this.getRecommendNo().hashCode() );
         result = 37 * result + ( getSourceCode() == null ? 0 : this.getSourceCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getSendTime() == null ? 0 : this.getSendTime().hashCode() );
         result = 37 * result + ( getSendNum() == null ? 0 : this.getSendNum().hashCode() );
         return result;
   }   





}
