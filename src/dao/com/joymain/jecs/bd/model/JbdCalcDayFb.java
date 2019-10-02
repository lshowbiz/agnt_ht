package com.joymain.jecs.bd.model;
// Generated 2017-1-19 14:27:44 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_CALC_DAY_FB"
 *     
 */

public class JbdCalcDayFb extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wweek;
    private String userName;
    private BigDecimal memberLevel;
    private BigDecimal fbMoney;
    private BigDecimal sendMoney;
    private BigDecimal deductVolume;
    private BigDecimal rax;
    private BigDecimal deductMoney;
    private BigDecimal freezeStatus;
    private Integer status;
    private Date sendDate;
    private JmiMember jmiMember;

    // Constructors

    /** default constructor */
    public JbdCalcDayFb() {
    }

    
    /** full constructor */
    public JbdCalcDayFb(Integer wweek, String userCode, String userName, BigDecimal memberLevel, BigDecimal fbMoney, BigDecimal sendMoney, BigDecimal deductVolume, BigDecimal rax, BigDecimal deductMoney, BigDecimal freezeStatus, Integer status, Date sendDate) {

        this.userName = userName;
        this.memberLevel = memberLevel;
        this.fbMoney = fbMoney;
        this.sendMoney = sendMoney;
        this.deductVolume = deductVolume;
        this.rax = rax;
        this.deductMoney = deductMoney;
        this.freezeStatus = freezeStatus;
        this.status = status;
        this.sendDate = sendDate;
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
     *             column="USER_NAME"
     *             length="200"
     *         
     */

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_LEVEL"
     *             length="22"
     *         
     */

    public BigDecimal getMemberLevel() {
        return this.memberLevel;
    }
    
    public void setMemberLevel(BigDecimal memberLevel) {
        this.memberLevel = memberLevel;
    }
    /**       
     *      *            @hibernate.property
     *             column="FB_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getFbMoney() {
        return this.fbMoney;
    }
    
    public void setFbMoney(BigDecimal fbMoney) {
        this.fbMoney = fbMoney;
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
     *             column="DEDUCT_VOLUME"
     *             length="22"
     *         
     */

    public BigDecimal getDeductVolume() {
        return this.deductVolume;
    }
    
    public void setDeductVolume(BigDecimal deductVolume) {
        this.deductVolume = deductVolume;
    }
    /**       
     *      *            @hibernate.property
     *             column="RAX"
     *             length="22"
     *         
     */

    public BigDecimal getRax() {
        return this.rax;
    }
    
    public void setRax(BigDecimal rax) {
        this.rax = rax;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEDUCT_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getDeductMoney() {
        return this.deductMoney;
    }
    
    public void setDeductMoney(BigDecimal deductMoney) {
        this.deductMoney = deductMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="FREEZE_STATUS"
     *             length="22"
     *         
     */

    public BigDecimal getFreezeStatus() {
        return this.freezeStatus;
    }
    
    public void setFreezeStatus(BigDecimal freezeStatus) {
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
     *             length="7"
     *         
     */

    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
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
 	
    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("wweek").append("='").append(getWweek()).append("' ");				
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("memberLevel").append("='").append(getMemberLevel()).append("' ");			
      buffer.append("fbMoney").append("='").append(getFbMoney()).append("' ");			
      buffer.append("sendMoney").append("='").append(getSendMoney()).append("' ");			
      buffer.append("deductVolume").append("='").append(getDeductVolume()).append("' ");			
      buffer.append("rax").append("='").append(getRax()).append("' ");			
      buffer.append("deductMoney").append("='").append(getDeductMoney()).append("' ");			
      buffer.append("freezeStatus").append("='").append(getFreezeStatus()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("sendDate").append("='").append(getSendDate()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdCalcDayFb) ) return false;
		 JbdCalcDayFb castOther = ( JbdCalcDayFb ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getMemberLevel()==castOther.getMemberLevel()) || ( this.getMemberLevel()!=null && castOther.getMemberLevel()!=null && this.getMemberLevel().equals(castOther.getMemberLevel()) ) )
 && ( (this.getFbMoney()==castOther.getFbMoney()) || ( this.getFbMoney()!=null && castOther.getFbMoney()!=null && this.getFbMoney().equals(castOther.getFbMoney()) ) )
 && ( (this.getSendMoney()==castOther.getSendMoney()) || ( this.getSendMoney()!=null && castOther.getSendMoney()!=null && this.getSendMoney().equals(castOther.getSendMoney()) ) )
 && ( (this.getDeductVolume()==castOther.getDeductVolume()) || ( this.getDeductVolume()!=null && castOther.getDeductVolume()!=null && this.getDeductVolume().equals(castOther.getDeductVolume()) ) )
 && ( (this.getRax()==castOther.getRax()) || ( this.getRax()!=null && castOther.getRax()!=null && this.getRax().equals(castOther.getRax()) ) )
 && ( (this.getDeductMoney()==castOther.getDeductMoney()) || ( this.getDeductMoney()!=null && castOther.getDeductMoney()!=null && this.getDeductMoney().equals(castOther.getDeductMoney()) ) )
 && ( (this.getFreezeStatus()==castOther.getFreezeStatus()) || ( this.getFreezeStatus()!=null && castOther.getFreezeStatus()!=null && this.getFreezeStatus().equals(castOther.getFreezeStatus()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getSendDate()==castOther.getSendDate()) || ( this.getSendDate()!=null && castOther.getSendDate()!=null && this.getSendDate().equals(castOther.getSendDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getMemberLevel() == null ? 0 : this.getMemberLevel().hashCode() );
         result = 37 * result + ( getFbMoney() == null ? 0 : this.getFbMoney().hashCode() );
         result = 37 * result + ( getSendMoney() == null ? 0 : this.getSendMoney().hashCode() );
         result = 37 * result + ( getDeductVolume() == null ? 0 : this.getDeductVolume().hashCode() );
         result = 37 * result + ( getRax() == null ? 0 : this.getRax().hashCode() );
         result = 37 * result + ( getDeductMoney() == null ? 0 : this.getDeductMoney().hashCode() );
         result = 37 * result + ( getFreezeStatus() == null ? 0 : this.getFreezeStatus().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getSendDate() == null ? 0 : this.getSendDate().hashCode() );
         return result;
   }   





}
