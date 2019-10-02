package com.joymain.jecs.bd.model;
// Generated 2017-1-19 14:33:21 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_CALC_DAY_KB_LIST"
 *     
 */

public class JbdCalcDayKbList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private Integer wweek;
    private String userCode;
    private String userName;
    private BigDecimal kbMoney;
    private String kbReason;
    private String operationNo;
    private Date operationDate;
    private Integer status;

    // Constructors

    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *         
     */

    public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	/** default constructor */
    public JbdCalcDayKbList() {
    }

    
    /** full constructor */
    public JbdCalcDayKbList(Integer wweek, String userCode, String userName, BigDecimal kbMoney, String kbReason, String operationNo, Date operationDate) {
        this.wweek = wweek;
        this.userCode = userCode;
        this.userName = userName;
        this.kbMoney = kbMoney;
        this.kbReason = kbReason;
        this.operationNo = operationNo;
        this.operationDate = operationDate;
    }
    

   
    // Property accessors

    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
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
     *             column="KB_MONEY"
     *             length="22"
     *         
     */

    public BigDecimal getKbMoney() {
        return this.kbMoney;
    }
    
    public void setKbMoney(BigDecimal kbMoney) {
        this.kbMoney = kbMoney;
    }
    /**       
     *      *            @hibernate.property
     *             column="KB_REASON"
     *             length="200"
     *         
     */

    public String getKbReason() {
        return this.kbReason;
    }
    
    public void setKbReason(String kbReason) {
        this.kbReason = kbReason;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATION_NO"
     *             length="20"
     *         
     */

    public String getOperationNo() {
        return this.operationNo;
    }
    
    public void setOperationNo(String operationNo) {
        this.operationNo = operationNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATION_DATE"
     *             length="7"
     *         
     */

    public Date getOperationDate() {
        return this.operationDate;
    }
    
    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
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
      buffer.append("userName").append("='").append(getUserName()).append("' ");			
      buffer.append("kbMoney").append("='").append(getKbMoney()).append("' ");			
      buffer.append("kbReason").append("='").append(getKbReason()).append("' ");			
      buffer.append("operationNo").append("='").append(getOperationNo()).append("' ");			
      buffer.append("operationDate").append("='").append(getOperationDate()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdCalcDayKbList) ) return false;
		 JbdCalcDayKbList castOther = ( JbdCalcDayKbList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getUserName()==castOther.getUserName()) || ( this.getUserName()!=null && castOther.getUserName()!=null && this.getUserName().equals(castOther.getUserName()) ) )
 && ( (this.getKbMoney()==castOther.getKbMoney()) || ( this.getKbMoney()!=null && castOther.getKbMoney()!=null && this.getKbMoney().equals(castOther.getKbMoney()) ) )
 && ( (this.getKbReason()==castOther.getKbReason()) || ( this.getKbReason()!=null && castOther.getKbReason()!=null && this.getKbReason().equals(castOther.getKbReason()) ) )
 && ( (this.getOperationNo()==castOther.getOperationNo()) || ( this.getOperationNo()!=null && castOther.getOperationNo()!=null && this.getOperationNo().equals(castOther.getOperationNo()) ) )
 && ( (this.getOperationDate()==castOther.getOperationDate()) || ( this.getOperationDate()!=null && castOther.getOperationDate()!=null && this.getOperationDate().equals(castOther.getOperationDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getUserName() == null ? 0 : this.getUserName().hashCode() );
         result = 37 * result + ( getKbMoney() == null ? 0 : this.getKbMoney().hashCode() );
         result = 37 * result + ( getKbReason() == null ? 0 : this.getKbReason().hashCode() );
         result = 37 * result + ( getOperationNo() == null ? 0 : this.getOperationNo().hashCode() );
         result = 37 * result + ( getOperationDate() == null ? 0 : this.getOperationDate().hashCode() );
         return result;
   }   





}
