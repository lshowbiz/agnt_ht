package com.joymain.jecs.am.model;
// Generated 2008-6-14 15:00:21 by Hibernate Tools 3.1.0.beta4

import java.util.Date;

import com.joymain.jecs.sys.model.SysUser;



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="AM_ANNOUNCE_RECORD"
 *     
 */

public class AmAnnounceRecord extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String uniNo;
    private SysUser browser = new SysUser();
    private Date browseTime;
    private AmAnnounce amAnnounce= new AmAnnounce();

    // Constructors

    /** default constructor */
    public AmAnnounceRecord() {
    }

    
    /** full constructor */
    public AmAnnounceRecord(String userNo, Date browseTime) {
        this.browser.setUserCode(userNo);
        this.browseTime = browseTime;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.String"
     *             
     *             column="UNI_NO"
     *        @hibernate.generator-param name="sequence" value="SEQ_AM"  
     */

    public String getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(String uniNo) {
        this.uniNo = uniNo;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="BROWSE_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */

    public Date getBrowseTime() {
        return this.browseTime;
    }
    
    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
//      buffer.append("userNo").append("='").append(getUserNo()).append("' ");			
      buffer.append("browseTime").append("='").append(getBrowseTime()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmAnnounceRecord) ) return false;
		 AmAnnounceRecord castOther = ( AmAnnounceRecord ) other; 
         
		 return ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
// && ( (this.getUserNo()==castOther.getUserNo()) || ( this.getUserNo()!=null && castOther.getUserNo()!=null && this.getUserNo().equals(castOther.getUserNo()) ) )
 && ( (this.getBrowseTime()==castOther.getBrowseTime()) || ( this.getBrowseTime()!=null && castOther.getBrowseTime()!=null && this.getBrowseTime().equals(castOther.getBrowseTime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
//         result = 37 * result + ( getUserNo() == null ? 0 : this.getUserNo().hashCode() );
         result = 37 * result + ( getBrowseTime() == null ? 0 : this.getBrowseTime().hashCode() );
         return result;
   }
   /**
    * *
    * @hibernate.many-to-one not-null="true"
    * @hibernate.column name="USER_NO"
    * 
    */

public SysUser getBrowser() {
	return browser;
}


public void setBrowser(SysUser browser) {
	this.browser = browser;
}

/**
 * *
 * @hibernate.many-to-one not-null="true" 
 * @hibernate.column name="AA_NO"
 * 
 */
public AmAnnounce getAmAnnounce() {
	return amAnnounce;
}


public void setAmAnnounce(AmAnnounce amAnnounce) {
	this.amAnnounce = amAnnounce;
}   





}
