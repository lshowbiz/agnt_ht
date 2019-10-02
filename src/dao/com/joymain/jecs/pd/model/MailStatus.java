package com.joymain.jecs.pd.model;

import java.util.ArrayList;
import java.util.List;



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="MAIL_STATUS"
 *     
 */
public class MailStatus extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {
    // Fields    

    private Long statusId;
    private String mail_no;//mail_no 
    private String logist_comp;//logist_comp

    private List<Items> items = new ArrayList();//items

    // Constructors

    /** default constructor */
    public MailStatus() {
    }

    
    /** full constructor */
    public MailStatus(String mail_no, String logist_comp) {
        this.mail_no = mail_no;
        this.logist_comp = logist_comp;
    }

    // Property accessors
	///**
	// * * @hibernate.id generator-class="sequence"
	// *             type="java.lang.Long"
	// * column="STATUS_ID"
	// * 
	// * @hibernate.generator-param name="sequence" value="SEQ_PD"
	// */

    /**
	  * * @hibernate.id generator-class="assigned"  
	 * 		type="java.lang.Long"
	 * 
	 * column="STATUS_ID"
	 * 
	 */
    public Long getStatusId() {
        return this.statusId;
    }
    
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
    /**       
     *      *            @hibernate.property
     *             column="MAIL_NO"
     *             length="100"
     *         
     */

    public String getMail_no() {
        return this.mail_no;
    }
    
    public void setMail_no(String mail_no) {
        this.mail_no = mail_no;
    }
    /**       
     *      *            @hibernate.property
     *             column="LOGIST_COMP"
     *             length="200"
     *         
     */

    public String getLogist_comp() {
        return this.logist_comp;
    }
    
    public void setLogist_comp(String logist_comp) {
        this.logist_comp = logist_comp;
    }
      
   
    /**
	 * *
	 * 
	 * @hibernate.list inverse="true" cascade="all"
	 * @hibernate.collection-key column="STATUS_ID"
	 * @hibernate.collection-index column="indexMailStatus"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pd.model.Items"
	 * 
	 */
    public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("mail_no").append("='").append(getMail_no()).append("' ");			
      buffer.append("logist_comp").append("='").append(getLogist_comp()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MailStatus) ) return false;
		 MailStatus castOther = ( MailStatus ) other; 
         
		 return ( (this.getStatusId()==castOther.getStatusId()) || ( this.getStatusId()!=null && castOther.getStatusId()!=null && this.getStatusId().equals(castOther.getStatusId()) ) )
 && ( (this.getMail_no()==castOther.getMail_no()) || ( this.getMail_no()!=null && castOther.getMail_no()!=null && this.getMail_no().equals(castOther.getMail_no()) ) )
 && ( (this.getLogist_comp()==castOther.getLogist_comp()) || ( this.getLogist_comp()!=null && castOther.getLogist_comp()!=null && this.getLogist_comp().equals(castOther.getLogist_comp()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getStatusId() == null ? 0 : this.getStatusId().hashCode() );
         result = 37 * result + ( getMail_no() == null ? 0 : this.getMail_no().hashCode() );
         result = 37 * result + ( getLogist_comp() == null ? 0 : this.getLogist_comp().hashCode() );
         return result;
   }   

}
