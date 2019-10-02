package com.joymain.jecs.pd.model;
// Generated 2015-6-15 15:01:21 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_MAIL_RECEIPT"
 *     
 */

public class PdMailReceipt extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long mailReceiptId;
    private String statusId;// 存储pd_logistics_base_num表主键的值 
    private String other;


    // Constructors

    /** default constructor */
    public PdMailReceipt() {
    }

    
    /** full constructor */
    public PdMailReceipt(String statusId, String other) {
        this.statusId = statusId;
        this.other = other;
    }
    

   
    // Property accessors
    /**
	  * * @hibernate.id generator-class="assigned"  
	 * 		type="java.lang.Long"
	 * 
	 * column="MAIL_RECEIPT_ID"
	 * 
	 */
    public Long getMailReceiptId() {
        return this.mailReceiptId;
    }
    
    public void setMailReceiptId(Long mailReceiptId) {
        this.mailReceiptId = mailReceiptId;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS_ID"
     *             length="20"
     *         
     */

    public String getStatusId() {
        return this.statusId;
    }
    
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER"
     *             length="500"
     *         
     */

    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("statusId").append("='").append(getStatusId()).append("' ");			
      buffer.append("other").append("='").append(getOther()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdMailReceipt) ) return false;
		 PdMailReceipt castOther = ( PdMailReceipt ) other; 
         
		 return ( (this.getMailReceiptId()==castOther.getMailReceiptId()) || ( this.getMailReceiptId()!=null && castOther.getMailReceiptId()!=null && this.getMailReceiptId().equals(castOther.getMailReceiptId()) ) )
 && ( (this.getStatusId()==castOther.getStatusId()) || ( this.getStatusId()!=null && castOther.getStatusId()!=null && this.getStatusId().equals(castOther.getStatusId()) ) )
 && ( (this.getOther()==castOther.getOther()) || ( this.getOther()!=null && castOther.getOther()!=null && this.getOther().equals(castOther.getOther()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMailReceiptId() == null ? 0 : this.getMailReceiptId().hashCode() );
         result = 37 * result + ( getStatusId() == null ? 0 : this.getStatusId().hashCode() );
         result = 37 * result + ( getOther() == null ? 0 : this.getOther().hashCode() );
         return result;
   }   





}
