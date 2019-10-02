package com.joymain.jecs.bd.model;
// Generated 2013-3-26 10:48:42 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_SPECIAL_STAR"
 *     
 */

public class JbdSpecialStar extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
    private BigDecimal crownEnvoyNum;
    private BigDecimal departmentNum;
    private BigDecimal minDepartmentNum;
    private Integer passStar;


    // Constructors

    /** default constructor */
    public JbdSpecialStar() {
    }

    
    /** full constructor */
    public JbdSpecialStar(BigDecimal crownEnvoyNum, BigDecimal departmentNum, BigDecimal minDepartmentNum, Integer passStar) {
        this.crownEnvoyNum = crownEnvoyNum;
        this.departmentNum = departmentNum;
        this.minDepartmentNum = minDepartmentNum;
        this.passStar = passStar;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             type="java.lang.String"
     *             column="USER_CODE"
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
     *             column="CROWN_ENVOY_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getCrownEnvoyNum() {
        return this.crownEnvoyNum;
    }
    
    public void setCrownEnvoyNum(BigDecimal crownEnvoyNum) {
        this.crownEnvoyNum = crownEnvoyNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="DEPARTMENT_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getDepartmentNum() {
        return this.departmentNum;
    }
    
    public void setDepartmentNum(BigDecimal departmentNum) {
        this.departmentNum = departmentNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="MIN_DEPARTMENT_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getMinDepartmentNum() {
        return this.minDepartmentNum;
    }
    
    public void setMinDepartmentNum(BigDecimal minDepartmentNum) {
        this.minDepartmentNum = minDepartmentNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_STAR"
     *             length="2"
     *         
     */

    public Integer getPassStar() {
        return this.passStar;
    }
    
    public void setPassStar(Integer passStar) {
        this.passStar = passStar;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("crownEnvoyNum").append("='").append(getCrownEnvoyNum()).append("' ");			
      buffer.append("departmentNum").append("='").append(getDepartmentNum()).append("' ");			
      buffer.append("minDepartmentNum").append("='").append(getMinDepartmentNum()).append("' ");			
      buffer.append("passStar").append("='").append(getPassStar()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdSpecialStar) ) return false;
		 JbdSpecialStar castOther = ( JbdSpecialStar ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCrownEnvoyNum()==castOther.getCrownEnvoyNum()) || ( this.getCrownEnvoyNum()!=null && castOther.getCrownEnvoyNum()!=null && this.getCrownEnvoyNum().equals(castOther.getCrownEnvoyNum()) ) )
 && ( (this.getDepartmentNum()==castOther.getDepartmentNum()) || ( this.getDepartmentNum()!=null && castOther.getDepartmentNum()!=null && this.getDepartmentNum().equals(castOther.getDepartmentNum()) ) )
 && ( (this.getMinDepartmentNum()==castOther.getMinDepartmentNum()) || ( this.getMinDepartmentNum()!=null && castOther.getMinDepartmentNum()!=null && this.getMinDepartmentNum().equals(castOther.getMinDepartmentNum()) ) )
 && ( (this.getPassStar()==castOther.getPassStar()) || ( this.getPassStar()!=null && castOther.getPassStar()!=null && this.getPassStar().equals(castOther.getPassStar()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCrownEnvoyNum() == null ? 0 : this.getCrownEnvoyNum().hashCode() );
         result = 37 * result + ( getDepartmentNum() == null ? 0 : this.getDepartmentNum().hashCode() );
         result = 37 * result + ( getMinDepartmentNum() == null ? 0 : this.getMinDepartmentNum().hashCode() );
         result = 37 * result + ( getPassStar() == null ? 0 : this.getPassStar().hashCode() );
         return result;
   }   





}
