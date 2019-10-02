package com.joymain.jecs.bd.model;
// Generated 2017-4-5 10:59:12 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_YK_JIANDIAN_LIST"
 *     
 */

public class JbdYkJiandianList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wweek;
    private String userCode;
    private String petName;
    private String userType;
    private Integer memberLevel;
    private BigDecimal ykRefMoney;
    private Date comTime;
    private String reuserCode;
    private Integer nlevel;


    // Constructors

    /** default constructor */
    public JbdYkJiandianList() {
    }

    
    /** full constructor */
    public JbdYkJiandianList(Integer wweek, String userCode, String petName, String userType, Integer memberLevel, BigDecimal ykRefMoney, Date comTime, String reuserCode, Integer nlevel) {
        this.wweek = wweek;
        this.userCode = userCode;
        this.petName = petName;
        this.userType = userType;
        this.memberLevel = memberLevel;
        this.ykRefMoney = ykRefMoney;
        this.comTime = comTime;
        this.reuserCode = reuserCode;
        this.nlevel = nlevel;
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
     *             column="PET_NAME"
     *             length="200"
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
     *             column="COM_TIME"
     *             length="7"
     *         
     */

    public Date getComTime() {
        return this.comTime;
    }
    
    public void setComTime(Date comTime) {
        this.comTime = comTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="REUSER_CODE"
     *             length="20"
     *         
     */

    public String getReuserCode() {
        return this.reuserCode;
    }
    
    public void setReuserCode(String reuserCode) {
        this.reuserCode = reuserCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="NLEVEL"
     *             length="22"
     *         
     */

    public Integer getNlevel() {
        return this.nlevel;
    }
    
    public void setNlevel(Integer nlevel) {
        this.nlevel = nlevel;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("petName").append("='").append(getPetName()).append("' ");			
      buffer.append("userType").append("='").append(getUserType()).append("' ");			
      buffer.append("memberLevel").append("='").append(getMemberLevel()).append("' ");			
      buffer.append("ykRefMoney").append("='").append(getYkRefMoney()).append("' ");			
      buffer.append("comTime").append("='").append(getComTime()).append("' ");			
      buffer.append("reuserCode").append("='").append(getReuserCode()).append("' ");			
      buffer.append("nlevel").append("='").append(getNlevel()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdYkJiandianList) ) return false;
		 JbdYkJiandianList castOther = ( JbdYkJiandianList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getPetName()==castOther.getPetName()) || ( this.getPetName()!=null && castOther.getPetName()!=null && this.getPetName().equals(castOther.getPetName()) ) )
 && ( (this.getUserType()==castOther.getUserType()) || ( this.getUserType()!=null && castOther.getUserType()!=null && this.getUserType().equals(castOther.getUserType()) ) )
 && ( (this.getMemberLevel()==castOther.getMemberLevel()) || ( this.getMemberLevel()!=null && castOther.getMemberLevel()!=null && this.getMemberLevel().equals(castOther.getMemberLevel()) ) )
 && ( (this.getYkRefMoney()==castOther.getYkRefMoney()) || ( this.getYkRefMoney()!=null && castOther.getYkRefMoney()!=null && this.getYkRefMoney().equals(castOther.getYkRefMoney()) ) )
 && ( (this.getComTime()==castOther.getComTime()) || ( this.getComTime()!=null && castOther.getComTime()!=null && this.getComTime().equals(castOther.getComTime()) ) )
 && ( (this.getReuserCode()==castOther.getReuserCode()) || ( this.getReuserCode()!=null && castOther.getReuserCode()!=null && this.getReuserCode().equals(castOther.getReuserCode()) ) )
 && ( (this.getNlevel()==castOther.getNlevel()) || ( this.getNlevel()!=null && castOther.getNlevel()!=null && this.getNlevel().equals(castOther.getNlevel()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getPetName() == null ? 0 : this.getPetName().hashCode() );
         result = 37 * result + ( getUserType() == null ? 0 : this.getUserType().hashCode() );
         result = 37 * result + ( getMemberLevel() == null ? 0 : this.getMemberLevel().hashCode() );
         result = 37 * result + ( getYkRefMoney() == null ? 0 : this.getYkRefMoney().hashCode() );
         result = 37 * result + ( getComTime() == null ? 0 : this.getComTime().hashCode() );
         result = 37 * result + ( getReuserCode() == null ? 0 : this.getReuserCode().hashCode() );
         result = 37 * result + ( getNlevel() == null ? 0 : this.getNlevel().hashCode() );
         return result;
   }   





}
