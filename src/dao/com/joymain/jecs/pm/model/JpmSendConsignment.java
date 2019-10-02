package com.joymain.jecs.pm.model;
// Generated 2013-12-19 15:32:46 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_SEND_CONSIGNMENT"
 *     
 */

public class JpmSendConsignment extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long consignmentNo;
    private Long fabId;
    private Date sendDate;
    private String status;
    private Long specNo;
    private BigDecimal consignmenNum;
    private String sendUserCode;
    private String address;
    private String specName;

    // Constructors

    /** default constructor */
    public JpmSendConsignment() {
    }

    
    /** full constructor */
    public JpmSendConsignment(Long fabId, Date sendDate, String status, Long specNo, BigDecimal consignmenNum, String sendUserCode) {
        this.fabId = fabId;
        this.sendDate = sendDate;
        this.status = status;
        this.specNo = specNo;
        this.consignmenNum = consignmenNum;
        this.sendUserCode = sendUserCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="CONSIGNMENT_NO"
     *         
     */

    public Long getConsignmentNo() {
        return this.consignmentNo;
    }
    
    public void setConsignmentNo(Long consignmentNo) {
        this.consignmentNo = consignmentNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="FAB_ID"
     *             length="10"
     *         
     */

    public Long getFabId() {
        return this.fabId;
    }
    
    public void setFabId(Long fabId) {
        this.fabId = fabId;
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
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="1"
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
     *             column="SPEC_NO"
     *             length="10"
     *         
     */

    public Long getSpecNo() {
        return this.specNo;
    }
    
    public void setSpecNo(Long specNo) {
        this.specNo = specNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONSIGNMEN_NUM"
     *             length="22"
     *         
     */

    public BigDecimal getConsignmenNum() {
        return this.consignmenNum;
    }
    
    public void setConsignmenNum(BigDecimal consignmenNum) {
        this.consignmenNum = consignmenNum;
    }
    /**       
     *      *            @hibernate.property
     *             column="SEND_USER_CODE"
     *             length="30"
     *         
     */

    public String getSendUserCode() {
        return this.sendUserCode;
    }
    
    public void setSendUserCode(String sendUserCode) {
        this.sendUserCode = sendUserCode;
    }
   

    public String getAddress()
    {
        return address;
    }


    public void setAddress(String address)
    {
        this.address = address;
    }


    public String getSpecName()
    {
        return specName;
    }


    public void setSpecName(String specName)
    {
        this.specName = specName;
    }


    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("fabId").append("='").append(getFabId()).append("' ");			
      buffer.append("sendDate").append("='").append(getSendDate()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("specNo").append("='").append(getSpecNo()).append("' ");			
      buffer.append("consignmenNum").append("='").append(getConsignmenNum()).append("' ");			
      buffer.append("sendUserCode").append("='").append(getSendUserCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmSendConsignment) ) return false;
		 JpmSendConsignment castOther = ( JpmSendConsignment ) other; 
         
		 return ( (this.getConsignmentNo()==castOther.getConsignmentNo()) || ( this.getConsignmentNo()!=null && castOther.getConsignmentNo()!=null && this.getConsignmentNo().equals(castOther.getConsignmentNo()) ) )
 && ( (this.getFabId()==castOther.getFabId()) || ( this.getFabId()!=null && castOther.getFabId()!=null && this.getFabId().equals(castOther.getFabId()) ) )
 && ( (this.getSendDate()==castOther.getSendDate()) || ( this.getSendDate()!=null && castOther.getSendDate()!=null && this.getSendDate().equals(castOther.getSendDate()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getSpecNo()==castOther.getSpecNo()) || ( this.getSpecNo()!=null && castOther.getSpecNo()!=null && this.getSpecNo().equals(castOther.getSpecNo()) ) )
 && ( (this.getConsignmenNum()==castOther.getConsignmenNum()) || ( this.getConsignmenNum()!=null && castOther.getConsignmenNum()!=null && this.getConsignmenNum().equals(castOther.getConsignmenNum()) ) )
 && ( (this.getSendUserCode()==castOther.getSendUserCode()) || ( this.getSendUserCode()!=null && castOther.getSendUserCode()!=null && this.getSendUserCode().equals(castOther.getSendUserCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getConsignmentNo() == null ? 0 : this.getConsignmentNo().hashCode() );
         result = 37 * result + ( getFabId() == null ? 0 : this.getFabId().hashCode() );
         result = 37 * result + ( getSendDate() == null ? 0 : this.getSendDate().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getSpecNo() == null ? 0 : this.getSpecNo().hashCode() );
         result = 37 * result + ( getConsignmenNum() == null ? 0 : this.getConsignmenNum().hashCode() );
         result = 37 * result + ( getSendUserCode() == null ? 0 : this.getSendUserCode().hashCode() );
         return result;
   }   





}
