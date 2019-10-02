package com.joymain.jecs.bd.model;
// Generated 2017-4-5 10:58:52 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_JD_SEND_RECORD"
 *     
 */

public class JbdJdSendRecord extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String petName;
    private String userType;
    private Integer wweek;
    private Integer memberLevel;
    private BigDecimal ykRefMoney;
    private BigDecimal sendMoney;
    private Integer freezeStatus;
    private Integer status;
    private Date sendDate;
    private JmiMember jmiMember;

    // Constructors

    /** default constructor */
    public JbdJdSendRecord() {
    }

    
    /** full constructor */
    public JbdJdSendRecord(String userCode, String petName, String userType, Integer wweek, Integer memberLevel, BigDecimal ykRefMoney, BigDecimal sendMoney, Integer freezeStatus, Integer status, Date sendDate) {
        this.petName = petName;
        this.userType = userType;
        this.wweek = wweek;
        this.memberLevel = memberLevel;
        this.ykRefMoney = ykRefMoney;
        this.sendMoney = sendMoney;
        this.freezeStatus = freezeStatus;
        this.status = status;
        this.sendDate = sendDate;
    }
    
    /**
     * *
     * @hibernate.many-to-one
     * @hibernate.column name="USER_CODE"
     * 
     */
 	public JmiMember getJmiMember() {
 		return jmiMember;
 	}
 	
 	
 	public void setJmiMember(JmiMember jmiMember) {
 		this.jmiMember = jmiMember;
 	}
 	

    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="ID"
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
     *             column="PET_NAME"
     *             length="100"
     *         
     */

    public String getPetName() {
        return this.petName;
    }
    
    public void setPetName(String petName) {
        this.petName = petName;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_TYPE"
     *             length="4"
     *         
     */

    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_WEEK"
     *             length="22"
     *         
     */

    public Integer getWweek() {
        return this.wweek;
    }
    
    public void setWweek(Integer wweek) {
        this.wweek = wweek;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_LEVEL"
     *             length="22"
     *         
     */

    public Integer getMemberLevel() {
        return this.memberLevel;
    }
    
    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }
    /**       
     *      *            @hibernate.property
     *             column="YK_REF_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getYkRefMoney() {
        return this.ykRefMoney;
    }
    
    public void setYkRefMoney(BigDecimal ykRefMoney) {
        this.ykRefMoney = ykRefMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getSendMoney() {
        return this.sendMoney;
    }
    
    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="FREEZE_STATUS"
     *             length="22"
     *         
     */

    public Integer getFreezeStatus() {
        return this.freezeStatus;
    }
    
    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="22"
     *         
     */

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_DATE"
     *         
     */

    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");	
      buffer.append("petName").append("='").append(getPetName()).append("' ");			
      buffer.append("userType").append("='").append(getUserType()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("memberLevel").append("='").append(getMemberLevel()).append("' ");			
      buffer.append("ykRefMoney").append("='").append(getYkRefMoney()).append("' ");			
      buffer.append("sendMoney").append("='").append(getSendMoney()).append("' ");			
      buffer.append("freezeStatus").append("='").append(getFreezeStatus()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("sendDate").append("='").append(getSendDate()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdJdSendRecord) ) return false;
		 JbdJdSendRecord castOther = ( JbdJdSendRecord ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getPetName()==castOther.getPetName()) || ( this.getPetName()!=null && castOther.getPetName()!=null && this.getPetName().equals(castOther.getPetName()) ) )
 && ( (this.getUserType()==castOther.getUserType()) || ( this.getUserType()!=null && castOther.getUserType()!=null && this.getUserType().equals(castOther.getUserType()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getMemberLevel()==castOther.getMemberLevel()) || ( this.getMemberLevel()!=null && castOther.getMemberLevel()!=null && this.getMemberLevel().equals(castOther.getMemberLevel()) ) )
 && ( (this.getYkRefMoney()==castOther.getYkRefMoney()) || ( this.getYkRefMoney()!=null && castOther.getYkRefMoney()!=null && this.getYkRefMoney().equals(castOther.getYkRefMoney()) ) )
 && ( (this.getSendMoney()==castOther.getSendMoney()) || ( this.getSendMoney()!=null && castOther.getSendMoney()!=null && this.getSendMoney().equals(castOther.getSendMoney()) ) )
 && ( (this.getFreezeStatus()==castOther.getFreezeStatus()) || ( this.getFreezeStatus()!=null && castOther.getFreezeStatus()!=null && this.getFreezeStatus().equals(castOther.getFreezeStatus()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getSendDate()==castOther.getSendDate()) || ( this.getSendDate()!=null && castOther.getSendDate()!=null && this.getSendDate().equals(castOther.getSendDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getPetName() == null ? 0 : this.getPetName().hashCode() );
         result = 37 * result + ( getUserType() == null ? 0 : this.getUserType().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getMemberLevel() == null ? 0 : this.getMemberLevel().hashCode() );
         result = 37 * result + ( getYkRefMoney() == null ? 0 : this.getYkRefMoney().hashCode() );
         result = 37 * result + ( getSendMoney() == null ? 0 : this.getSendMoney().hashCode() );
         result = 37 * result + ( getFreezeStatus() == null ? 0 : this.getFreezeStatus().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getSendDate() == null ? 0 : this.getSendDate().hashCode() );
         return result;
   }   





}
