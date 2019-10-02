package com.joymain.jecs.bd.model;
// Generated 2009-10-5 17:15:31 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_MANUALLY_ADJUST_STAR"
 *     
 */

public class JbdManuallyAdjustStar extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wweek;
    private String userCode;
    private Integer honorStar;
    private Integer passStar;
    private Integer honorGrade;
    private Integer passGrade;
    private String createNo;
    private Date createTime;


    // Constructors

    /** default constructor */
    public JbdManuallyAdjustStar() {
    }

    
    /** full constructor */
    public JbdManuallyAdjustStar(Integer wweek, String userCode, Integer honorStar, Integer passStar, Integer honorGrade, Integer passGrade) {
        this.wweek = wweek;
        this.userCode = userCode;
        this.honorStar = honorStar;
        this.passStar = passStar;
        this.honorGrade = honorGrade;
        this.passGrade = passGrade;
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
    	return id;
    }


    public void setId(Long id) {
    	this.id = id;
    }   

	/**       
     *      *            @hibernate.property
     *             column="CREATE_NO"
     *             length="20"
     *         
     */

    public String getCreateNo() {
		return createNo;
	}

	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}
	/**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *         
     */
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


    /**       
     *      *            @hibernate.property
     *             column="W_WEEK"
     *             length="22"
     *         
     */
	public Integer getWweek() {
		return wweek;
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
     *             column="HONOR_STAR"
     *             length="2"
     *         
     */

    public Integer getHonorStar() {
        return this.honorStar;
    }
    
    public void setHonorStar(Integer honorStar) {
        this.honorStar = honorStar;
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
     *      *            @hibernate.property
     *             column="HONOR_GRADE"
     *             length="2"
     *         
     */

    public Integer getHonorGrade() {
        return this.honorGrade;
    }
    
    public void setHonorGrade(Integer honorGrade) {
        this.honorGrade = honorGrade;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_GRADE"
     *             length="2"
     *         
     */

    public Integer getPassGrade() {
        return this.passGrade;
    }
    
    public void setPassGrade(Integer passGrade) {
        this.passGrade = passGrade;
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
      buffer.append("honorStar").append("='").append(getHonorStar()).append("' ");			
      buffer.append("passStar").append("='").append(getPassStar()).append("' ");			
      buffer.append("honorGrade").append("='").append(getHonorGrade()).append("' ");			
      buffer.append("passGrade").append("='").append(getPassGrade()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdManuallyAdjustStar) ) return false;
		 JbdManuallyAdjustStar castOther = ( JbdManuallyAdjustStar ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getHonorStar()==castOther.getHonorStar()) || ( this.getHonorStar()!=null && castOther.getHonorStar()!=null && this.getHonorStar().equals(castOther.getHonorStar()) ) )
 && ( (this.getPassStar()==castOther.getPassStar()) || ( this.getPassStar()!=null && castOther.getPassStar()!=null && this.getPassStar().equals(castOther.getPassStar()) ) )
 && ( (this.getHonorGrade()==castOther.getHonorGrade()) || ( this.getHonorGrade()!=null && castOther.getHonorGrade()!=null && this.getHonorGrade().equals(castOther.getHonorGrade()) ) )
 && ( (this.getPassGrade()==castOther.getPassGrade()) || ( this.getPassGrade()!=null && castOther.getPassGrade()!=null && this.getPassGrade().equals(castOther.getPassGrade()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getHonorStar() == null ? 0 : this.getHonorStar().hashCode() );
         result = 37 * result + ( getPassStar() == null ? 0 : this.getPassStar().hashCode() );
         result = 37 * result + ( getHonorGrade() == null ? 0 : this.getHonorGrade().hashCode() );
         result = 37 * result + ( getPassGrade() == null ? 0 : this.getPassGrade().hashCode() );
         return result;
   }






}
