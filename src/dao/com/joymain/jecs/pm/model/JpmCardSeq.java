package com.joymain.jecs.pm.model;
// Generated 2010-1-14 14:32:12 by Hibernate Tools 3.1.0.beta4

import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_CARD_SEQ"
 *           	dynamic-update="true"
 *		dynamic-insert="true"
 *		optimistic-lock="version"
 */

public class JpmCardSeq extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String cardNo;
    private String memberOrderNo;
    private String password;
    private String userCode;
    private Date createTime;
    private String state;
    private Long molId;
    private Integer version=new Integer(0);
    private String grade;

    // Constructors

	/** default constructor */
    public JpmCardSeq() {
    }

    
    /** full constructor */
    public JpmCardSeq(String cardNo, String memberOrderNo, String password, String userCode, Date createTime, String state) {
        this.cardNo = cardNo;
        this.memberOrderNo = memberOrderNo;
        this.password = password;
        this.userCode = userCode;
        this.createTime = createTime;
        this.state = state;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_SYS"   
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
     *             column="CARD_NO"
     *             length="20"
     *         
     */

    public String getCardNo() {
        return this.cardNo;
    }
    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_ORDER_NO"
     *             length="20"
     *         
     */
    public String getMemberOrderNo() {
		return memberOrderNo;
	}


	public void setMemberOrderNo(String memberOrderNo) {
		this.memberOrderNo = memberOrderNo;
	}


	/**       
     *      *            @hibernate.property
     *             column="PASSWORD"
     *             length="30"
     *         
     */

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
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
     *             column="STATE"
     *             length="1"
     *         
     */

    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("cardNo").append("='").append(getCardNo()).append("' ");			
      buffer.append("memberOrderNo").append("='").append(getMemberOrderNo()).append("' ");			
      buffer.append("password").append("='").append(getPassword()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("state").append("='").append(getState()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmCardSeq) ) return false;
		 JpmCardSeq castOther = ( JpmCardSeq ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getCardNo()==castOther.getCardNo()) || ( this.getCardNo()!=null && castOther.getCardNo()!=null && this.getCardNo().equals(castOther.getCardNo()) ) )
 && ( (this.getMemberOrderNo()==castOther.getMemberOrderNo()) || ( this.getMemberOrderNo()!=null && castOther.getMemberOrderNo()!=null && this.getMemberOrderNo().equals(castOther.getMemberOrderNo()) ) )
 && ( (this.getPassword()==castOther.getPassword()) || ( this.getPassword()!=null && castOther.getPassword()!=null && this.getPassword().equals(castOther.getPassword()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getState()==castOther.getState()) || ( this.getState()!=null && castOther.getState()!=null && this.getState().equals(castOther.getState()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getCardNo() == null ? 0 : this.getCardNo().hashCode() );
         result = 37 * result + ( getMemberOrderNo() == null ? 0 : this.getMemberOrderNo().hashCode() );
         result = 37 * result + ( getPassword() == null ? 0 : this.getPassword().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getState() == null ? 0 : this.getState().hashCode() );
         return result;
   }


   /**       
    *      *            @hibernate.version
    *             column="VERSION"
    *             length="5"
    *         
    */
public Integer getVersion() {
	return version;
}


public void setVersion(Integer version) {
	this.version = version;
}


/**       
 *      *            @hibernate.property
 *             column="MOL_ID"
 *             length="10"
 *         
 */

public Long getMolId() {
	return molId;
}


public void setMolId(Long molId) {
	this.molId = molId;
}



/**       
 *      *            @hibernate.property
 *             column="GRADE"
 *             length="1"
 *         
 */
public String getGrade() {
	return grade;
}


public void setGrade(String grade) {
	this.grade = grade;
}   





}
