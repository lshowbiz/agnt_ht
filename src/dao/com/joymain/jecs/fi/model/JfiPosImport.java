package com.joymain.jecs.fi.model;
// Generated 2012-9-12 16:09:36 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JFI_POS_IMPORT"
 *     
 */

public class JfiPosImport extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long jpiId;
    private String posNo;
    private String tel;
    private String pid;
    private BigDecimal amount;
    private String status;//1:待审，2：已审，3：取消，4：退款
    private Date createTime;
    private String createUser;
    private Date checkTime;
    private String checkUser;
    private String userCode;
    private String inc;//是否进存折，0：未，1：是
    private Date incTime;
    private Date messageTime;
    private String messageCode;

    private Integer moneyType;//POS支付资金类别 (89：快钱POS现场 ，35：银联POS，106：畅捷通POS)
    
    private String isBack;
    
    // Constructors

    /** default constructor */
    public JfiPosImport() {
    }

	/** minimal constructor */
    public JfiPosImport(String posNo, String tel, String pid, BigDecimal amount) {
        this.posNo = posNo;
        this.tel = tel;
        this.pid = pid;
        this.amount = amount;
    }
    
    /** full constructor */
    public JfiPosImport(String posNo, String tel, String pid, BigDecimal amount, String status, Date checkTime, String checkUser, String userCode, String inc, Date incTime, Date messageTime, String messageCode) {
        this.posNo = posNo;
        this.tel = tel;
        this.pid = pid;
        this.amount = amount;
        this.status = status;
        this.checkTime = checkTime;
        this.checkUser = checkUser;
        this.userCode = userCode;
        this.inc = inc;
        this.incTime = incTime;
        this.messageTime = messageTime;
        this.messageCode = messageCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="JPI_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BANKBOOK"
     *         
     */

    public Long getJpiId() {
        return this.jpiId;
    }
    
    public void setJpiId(Long jpiId) {
        this.jpiId = jpiId;
    }
    /**       
     *      *            @hibernate.property
     *             column="POS_NO"
     *             unique="true"
     *             length="200"
     *             not-null="true"
     *         
     */

    public String getPosNo() {
        return this.posNo;
    }
    
    public void setPosNo(String posNo) {
        this.posNo = posNo;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="isBack"
     *             length="1"
     *         
     */

    public String getIsBack() {
        return this.isBack;
    }
    
    public void setIsBack(String isBack) {
        this.isBack = isBack;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="TEL"
     *             length="200"
     *         
     */

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**       
     *      *            @hibernate.property
     *             column="P_ID"
     *             length="200"
     *             not-null="true"
     *         
     */

    public String getPid() {
        return this.pid;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
    }
    /**       
     *      *            @hibernate.property
     *             column="AMOUNT"
     *             length="22"
     *             not-null="true"
     *         
     */

    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
     *             column="CHECK_USER"
     *             length="200"
     *         
     */

    public String getCheckUser() {
        return this.checkUser;
    }
    
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="200"
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
     *             column="INC"
     *             length="2"
     *         
     */

    public String getInc() {
        return this.inc;
    }
    
    public void setInc(String inc) {
        this.inc = inc;
    }
    /**       
     *      *            @hibernate.property
     *             column="INC_TIME"
     *             length="7"
     *         
     */

    public Date getIncTime() {
        return this.incTime;
    }
    
    public void setIncTime(Date incTime) {
        this.incTime = incTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="MESSAGE_TIME"
     *             length="7"
     *         
     */

    public Date getMessageTime() {
        return this.messageTime;
    }
    
    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="MESSAGE_CODE"
     *             length="200"
     *         
     */

    public String getMessageCode() {
        return this.messageCode;
    }
    
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("posNo").append("='").append(getPosNo()).append("' ");			
      buffer.append("tel").append("='").append(getTel()).append("' ");			
      buffer.append("pid").append("='").append(getPid()).append("' ");			
      buffer.append("amount").append("='").append(getAmount()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("checkTime").append("='").append(getCheckTime()).append("' ");			
      buffer.append("checkUser").append("='").append(getCheckUser()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("inc").append("='").append(getInc()).append("' ");			
      buffer.append("incTime").append("='").append(getIncTime()).append("' ");			
      buffer.append("messageTime").append("='").append(getMessageTime()).append("' ");			
      buffer.append("messageCode").append("='").append(getMessageCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JfiPosImport) ) return false;
		 JfiPosImport castOther = ( JfiPosImport ) other; 
         
		 return ( (this.getJpiId()==castOther.getJpiId()) || ( this.getJpiId()!=null && castOther.getJpiId()!=null && this.getJpiId().equals(castOther.getJpiId()) ) )
 && ( (this.getPosNo()==castOther.getPosNo()) || ( this.getPosNo()!=null && castOther.getPosNo()!=null && this.getPosNo().equals(castOther.getPosNo()) ) )
 && ( (this.getTel()==castOther.getTel()) || ( this.getTel()!=null && castOther.getTel()!=null && this.getTel().equals(castOther.getTel()) ) )
 && ( (this.getPid()==castOther.getPid()) || ( this.getPid()!=null && castOther.getPid()!=null && this.getPid().equals(castOther.getPid()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getCheckTime()==castOther.getCheckTime()) || ( this.getCheckTime()!=null && castOther.getCheckTime()!=null && this.getCheckTime().equals(castOther.getCheckTime()) ) )
 && ( (this.getCheckUser()==castOther.getCheckUser()) || ( this.getCheckUser()!=null && castOther.getCheckUser()!=null && this.getCheckUser().equals(castOther.getCheckUser()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getInc()==castOther.getInc()) || ( this.getInc()!=null && castOther.getInc()!=null && this.getInc().equals(castOther.getInc()) ) )
 && ( (this.getIncTime()==castOther.getIncTime()) || ( this.getIncTime()!=null && castOther.getIncTime()!=null && this.getIncTime().equals(castOther.getIncTime()) ) )
 && ( (this.getMessageTime()==castOther.getMessageTime()) || ( this.getMessageTime()!=null && castOther.getMessageTime()!=null && this.getMessageTime().equals(castOther.getMessageTime()) ) )
 && ( (this.getMessageCode()==castOther.getMessageCode()) || ( this.getMessageCode()!=null && castOther.getMessageCode()!=null && this.getMessageCode().equals(castOther.getMessageCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJpiId() == null ? 0 : this.getJpiId().hashCode() );
         result = 37 * result + ( getPosNo() == null ? 0 : this.getPosNo().hashCode() );
         result = 37 * result + ( getTel() == null ? 0 : this.getTel().hashCode() );
         result = 37 * result + ( getPid() == null ? 0 : this.getPid().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getCheckTime() == null ? 0 : this.getCheckTime().hashCode() );
         result = 37 * result + ( getCheckUser() == null ? 0 : this.getCheckUser().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getInc() == null ? 0 : this.getInc().hashCode() );
         result = 37 * result + ( getIncTime() == null ? 0 : this.getIncTime().hashCode() );
         result = 37 * result + ( getMessageTime() == null ? 0 : this.getMessageTime().hashCode() );
         result = 37 * result + ( getMessageCode() == null ? 0 : this.getMessageCode().hashCode() );
         return result;
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
	 *             column="CREATE_USER"
	 *             length="200"
	 *         
	 */
	public String getCreateUser() {
		return createUser;
	}
	
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}



	/**       
	 *      *            @hibernate.property
	 *             column="MONEY_TYPE"
	 *             length="4"
	 *         
	 */
	public Integer getMoneyType() {
		return moneyType;
	}
	
	public void setMoneyType(Integer moneyType) {
		this.moneyType = moneyType;
	}
}
