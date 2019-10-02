package com.joymain.jecs.bd.model;
// Generated 2008-6-17 10:39:54 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;

import com.joymain.jecs.mi.model.JmiMember;



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_BOUNS_DEDUCT"
 *     
 */

public class BdBounsDeduct extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private String companyCode;
    private JmiMember jmiMember;
    private String summary;
    private String type;
    private BigDecimal money;
    private BigDecimal remainMoney;
    private BigDecimal deductMoney;
    private String status;
    private String checkerCode;
    private String checkerName;
    private Date checkTime;
    private String deducterCode;
    private String deducterName;
    private Date deductTime;
    private String createCode;
    private String createName;
    private Date createTime;
    private String treatedType;
    private Integer treatedWeek;
    private String treatedNo;


    // Constructors

    /** default constructor */
    public BdBounsDeduct() {
    }

    
    /** full constructor */
    public BdBounsDeduct(Integer wyear, Integer wmonth, Integer wweek, String companyCode, String memberNo, String summary, String type, BigDecimal money, BigDecimal remainMoney, BigDecimal deductMoney, String status, String checkerCode, String checkerName, Date checkTime, String deducterCode, String deducterName, Date deductTime, String createCode, String createName, Date createTime, String treatedType, Integer treatedWeek, String treatedNo) {
        this.wyear = wyear;
        this.wmonth = wmonth;
        this.wweek = wweek;
        this.companyCode = companyCode;
        this.summary = summary;
        this.type = type;
        this.money = money;
        this.remainMoney = remainMoney;
        this.deductMoney = deductMoney;
        this.status = status;
        this.checkerCode = checkerCode;
        this.checkerName = checkerName;
        this.checkTime = checkTime;
        this.deducterCode = deducterCode;
        this.deducterName = deducterName;
        this.deductTime = deductTime;
        this.createCode = createCode;
        this.createName = createName;
        this.createTime = createTime;
        this.treatedType = treatedType;
        this.treatedWeek = treatedWeek;
        this.treatedNo = treatedNo;
    }
    

   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_YEAR"
     *             length="8"
     *         
     */

    public Integer getWyear() {
        return this.wyear;
    }
    
    public void setWyear(Integer wyear) {
        this.wyear = wyear;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_MONTH"
     *             length="8"
     *         
     */

    public Integer getWmonth() {
        return this.wmonth;
    }
    
    public void setWmonth(Integer wmonth) {
        this.wmonth = wmonth;
    }
    /**       
     *      *            @hibernate.property
     *             column="W_WEEK"
     *             length="8"
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
     *             column="COMPANY_CODE"
     *             length="2"
     *         
     */

    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUMMARY"
     *             length="4000"
     *         
     */

    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    /**       
     *      *            @hibernate.property
     *             column="TYPE"
     *             length="10"
     *         
     */

    public String getType() {
        return this.type;
    }
    /**
     * @spring.validator type="required"
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    /**       
     *      *            @hibernate.property
     *             column="MONEY"
     *             length="18"
     *         
     */

    public BigDecimal getMoney() {
        return this.money;
    }
    /**
     * @spring.validator type="required"
     * @param money
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMAIN_MONEY"
     *             length="18"
     *         
     */

    public BigDecimal getRemainMoney() {
        return this.remainMoney;
    }
    
    public void setRemainMoney(BigDecimal remainMoney) {
        this.remainMoney = remainMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEDUCT_MONEY"
     *             length="18"
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
     *             column="STATUS"
     *             length="2"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECKER_CODE"
     *             length="20"
     *         
     */

    public String getCheckerCode() {
        return this.checkerCode;
    }
    
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECKER_NAME"
     *             length="60"
     *         
     */

    public String getCheckerName() {
        return this.checkerName;
    }
    
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }
    /**       
     *      *            @hibernate.property
     *             column="CHECK_TIME"
     *             length="7"
     *         
     */

    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEDUCTER_CODE"
     *             length="20"
     *         
     */

    public String getDeducterCode() {
        return this.deducterCode;
    }
    
    public void setDeducterCode(String deducterCode) {
        this.deducterCode = deducterCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEDUCTER_NAME"
     *             length="60"
     *         
     */

    public String getDeducterName() {
        return this.deducterName;
    }
    
    public void setDeducterName(String deducterName) {
        this.deducterName = deducterName;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEDUCT_TIME"
     *             length="7"
     *         
     */

    public Date getDeductTime() {
        return this.deductTime;
    }
    
    public void setDeductTime(Date deductTime) {
        this.deductTime = deductTime;
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
     *             length="60"
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
     *             column="TREATED_TYPE"
     *             length="1"
     *         
     */

    public String getTreatedType() {
        return this.treatedType;
    }
    
    public void setTreatedType(String treatedType) {
        this.treatedType = treatedType;
    }
    /**       
     *      *            @hibernate.property
     *             column="TREATED_WEEK"
     *             length="8"
     *         
     */

    public Integer getTreatedWeek() {
        return this.treatedWeek;
    }
    
    public void setTreatedWeek(Integer treatedWeek) {
        this.treatedWeek = treatedWeek;
    }
    /**       
     *      *            @hibernate.property
     *             column="TREATED_NO"
     *             length="10"
     *         
     */

    public String getTreatedNo() {
        return this.treatedNo;
    }
    
    public void setTreatedNo(String treatedNo) {
        this.treatedNo = treatedNo;
    }

    /**
     * *
     * @hibernate.many-to-one not-null="true"
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
      buffer.append("wyear").append("='").append(getWyear()).append("' ");			
      buffer.append("wmonth").append("='").append(getWmonth()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("summary").append("='").append(getSummary()).append("' ");			
      buffer.append("type").append("='").append(getType()).append("' ");			
      buffer.append("money").append("='").append(getMoney()).append("' ");			
      buffer.append("remainMoney").append("='").append(getRemainMoney()).append("' ");			
      buffer.append("deductMoney").append("='").append(getDeductMoney()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("checkerCode").append("='").append(getCheckerCode()).append("' ");			
      buffer.append("checkerName").append("='").append(getCheckerName()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("deducterCode").append("='").append(getDeducterCode()).append("' ");			
      buffer.append("deducterName").append("='").append(getDeducterName()).append("' ");			
      buffer.append("deductTime").append("='").append(getDeductTime()).append("' ");			
      buffer.append("createCode").append("='").append(getCreateCode()).append("' ");			
      buffer.append("createName").append("='").append(getCreateName()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("treatedType").append("='").append(getTreatedType()).append("' ");			
      buffer.append("treatedWeek").append("='").append(getTreatedWeek()).append("' ");			
      buffer.append("treatedNo").append("='").append(getTreatedNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BdBounsDeduct) ) return false;
		 BdBounsDeduct castOther = ( BdBounsDeduct ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWyear()==castOther.getWyear()) || ( this.getWyear()!=null && castOther.getWyear()!=null && this.getWyear().equals(castOther.getWyear()) ) )
 && ( (this.getWmonth()==castOther.getWmonth()) || ( this.getWmonth()!=null && castOther.getWmonth()!=null && this.getWmonth().equals(castOther.getWmonth()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getSummary()==castOther.getSummary()) || ( this.getSummary()!=null && castOther.getSummary()!=null && this.getSummary().equals(castOther.getSummary()) ) )
 && ( (this.getType()==castOther.getType()) || ( this.getType()!=null && castOther.getType()!=null && this.getType().equals(castOther.getType()) ) )
 && ( (this.getMoney()==castOther.getMoney()) || ( this.getMoney()!=null && castOther.getMoney()!=null && this.getMoney().equals(castOther.getMoney()) ) )
 && ( (this.getRemainMoney()==castOther.getRemainMoney()) || ( this.getRemainMoney()!=null && castOther.getRemainMoney()!=null && this.getRemainMoney().equals(castOther.getRemainMoney()) ) )
 && ( (this.getDeductMoney()==castOther.getDeductMoney()) || ( this.getDeductMoney()!=null && castOther.getDeductMoney()!=null && this.getDeductMoney().equals(castOther.getDeductMoney()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCheckerCode()==castOther.getCheckerCode()) || ( this.getCheckerCode()!=null && castOther.getCheckerCode()!=null && this.getCheckerCode().equals(castOther.getCheckerCode()) ) )
 && ( (this.getCheckerName()==castOther.getCheckerName()) || ( this.getCheckerName()!=null && castOther.getCheckerName()!=null && this.getCheckerName().equals(castOther.getCheckerName()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getDeducterCode()==castOther.getDeducterCode()) || ( this.getDeducterCode()!=null && castOther.getDeducterCode()!=null && this.getDeducterCode().equals(castOther.getDeducterCode()) ) )
 && ( (this.getDeducterName()==castOther.getDeducterName()) || ( this.getDeducterName()!=null && castOther.getDeducterName()!=null && this.getDeducterName().equals(castOther.getDeducterName()) ) )
 && ( (this.getDeductTime()==castOther.getDeductTime()) || ( this.getDeductTime()!=null && castOther.getDeductTime()!=null && this.getDeductTime().equals(castOther.getDeductTime()) ) )
 && ( (this.getCreateCode()==castOther.getCreateCode()) || ( this.getCreateCode()!=null && castOther.getCreateCode()!=null && this.getCreateCode().equals(castOther.getCreateCode()) ) )
 && ( (this.getCreateName()==castOther.getCreateName()) || ( this.getCreateName()!=null && castOther.getCreateName()!=null && this.getCreateName().equals(castOther.getCreateName()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getTreatedType()==castOther.getTreatedType()) || ( this.getTreatedType()!=null && castOther.getTreatedType()!=null && this.getTreatedType().equals(castOther.getTreatedType()) ) )
 && ( (this.getTreatedWeek()==castOther.getTreatedWeek()) || ( this.getTreatedWeek()!=null && castOther.getTreatedWeek()!=null && this.getTreatedWeek().equals(castOther.getTreatedWeek()) ) )
 && ( (this.getTreatedNo()==castOther.getTreatedNo()) || ( this.getTreatedNo()!=null && castOther.getTreatedNo()!=null && this.getTreatedNo().equals(castOther.getTreatedNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWyear() == null ? 0 : this.getWyear().hashCode() );
         result = 37 * result + ( getWmonth() == null ? 0 : this.getWmonth().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getSummary() == null ? 0 : this.getSummary().hashCode() );
         result = 37 * result + ( getType() == null ? 0 : this.getType().hashCode() );
         result = 37 * result + ( getMoney() == null ? 0 : this.getMoney().hashCode() );
         result = 37 * result + ( getRemainMoney() == null ? 0 : this.getRemainMoney().hashCode() );
         result = 37 * result + ( getDeductMoney() == null ? 0 : this.getDeductMoney().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCheckerCode() == null ? 0 : this.getCheckerCode().hashCode() );
         result = 37 * result + ( getCheckerName() == null ? 0 : this.getCheckerName().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getDeducterCode() == null ? 0 : this.getDeducterCode().hashCode() );
         result = 37 * result + ( getDeducterName() == null ? 0 : this.getDeducterName().hashCode() );
         result = 37 * result + ( getDeductTime() == null ? 0 : this.getDeductTime().hashCode() );
         result = 37 * result + ( getCreateCode() == null ? 0 : this.getCreateCode().hashCode() );
         result = 37 * result + ( getCreateName() == null ? 0 : this.getCreateName().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getTreatedType() == null ? 0 : this.getTreatedType().hashCode() );
         result = 37 * result + ( getTreatedWeek() == null ? 0 : this.getTreatedWeek().hashCode() );
         result = 37 * result + ( getTreatedNo() == null ? 0 : this.getTreatedNo().hashCode() );
         return result;
   }   





}
