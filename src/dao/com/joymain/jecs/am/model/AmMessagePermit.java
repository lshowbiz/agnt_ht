package com.joymain.jecs.am.model;
// Generated 2009-10-13 16:41:47 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="AM_MESSAGE_PERMIT"
 *     
 */

public class AmMessagePermit extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long ampNo;
    private String msgClassNo;
    private String userCode;


    // Constructors

    /** default constructor */
    public AmMessagePermit() {
    }

    
    /** full constructor */
    public AmMessagePermit(String msgClassNo, String userCode) {
        this.msgClassNo = msgClassNo;
        this.userCode = userCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="AMP_NO"
     *             @hibernate.generator-param name="sequence" value="SEQ_AM" 
     *         
     */

    public Long getAmpNo() {
        return this.ampNo;
    }
    
    public void setAmpNo(Long ampNo) {
        this.ampNo = ampNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="MSG_CLASS_NO"
     *             length="2"
     *         
     */

    public String getMsgClassNo() {
        return this.msgClassNo;
    }
    
    public void setMsgClassNo(String msgClassNo) {
        this.msgClassNo = msgClassNo;
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
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("msgClassNo").append("='").append(getMsgClassNo()).append("' ");			
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmMessagePermit) ) return false;
		 AmMessagePermit castOther = ( AmMessagePermit ) other; 
         
		 return ( (this.getAmpNo()==castOther.getAmpNo()) || ( this.getAmpNo()!=null && castOther.getAmpNo()!=null && this.getAmpNo().equals(castOther.getAmpNo()) ) )
 && ( (this.getMsgClassNo()==castOther.getMsgClassNo()) || ( this.getMsgClassNo()!=null && castOther.getMsgClassNo()!=null && this.getMsgClassNo().equals(castOther.getMsgClassNo()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAmpNo() == null ? 0 : this.getAmpNo().hashCode() );
         result = 37 * result + ( getMsgClassNo() == null ? 0 : this.getMsgClassNo().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         return result;
   }   





}
